/**
 * 
 */
package com.imooc.web.async;

import java.util.concurrent.Callable;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;


/**
 * @author 杨Sir
 * 多线程处理提高服务性能
 */
@RestController
public class AsyncController {
	
	//logger 可以打印日志中含有线程名
	private Logger logger = LoggerFactory.getLogger(AsyncController.class);

	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder;
	
	/**
	 * 使用 DeferredResult 进行异步处理
	 * @throws InterruptedException 
	 */
	@RequestMapping("/order")
	public DeferredResult<String> order3() throws InterruptedException {
		
		logger.info("主线程开始...");
		//生成一个8位的订单号
		String orderNumber = RandomStringUtils.randomNumeric(8);
		mockQueue.setPlaceOrder(orderNumber);
		DeferredResult<String> deferredResult = new DeferredResult<>();
		deferredResultHolder.getMap().put(orderNumber, deferredResult);
		
		logger.info("主线程返回...");
		return deferredResult;
	}
	
	/**
	 * 使用Callable进行异步处理
	 */
//	@RequestMapping("/order")
	public Callable<String> order2(){
		logger.info("主线程开始...");
		Callable<String> result = new Callable<String>() {

			@Override
			public String call() throws Exception {

				logger.info("副线程开始...");
				Thread.sleep(1000);
				logger.info("副线程返回...");

				return "success callable";
			}
		};
		
		logger.info("主线程返回...");

		return result;
	}
	
	
	/**
	 * 同步处理
	 * @return
	 * @throws InterruptedException
	 */
//	@RequestMapping("/order")
	public String order() throws InterruptedException {
			
		logger.info("主线程开始...");
		//模拟执行业务逻辑
		Thread.sleep(1000);
		logger.info("主线程结束...");
		
		return "success";
		
	}
	
}

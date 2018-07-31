/**
 * 
 */
package com.imooc.web.async;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author 杨Sir
 * 队列监听器，监听消息队列是否有任务完成
 * ContextRefreshedEvent: 这个监听器在 Spring容器加载完成时启动，这里的处理程序要用副线程，否则循环监听会死循环
 */
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent>{

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

		//这里的处理程序要用副线程，否则循环监听会死循环
		new Thread(()->{
			//无限进行循环监听
			while(true) {
				if(StringUtils.isNotBlank(mockQueue.getCompleteOrder())) {
					
					String orderNumber = mockQueue.getCompleteOrder();
					logger.info("返回订单处理结果： "+ orderNumber);
					deferredResultHolder.getMap().get(orderNumber).setResult("place order success");
					mockQueue.setCompleteOrder(null);
				}else {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
}

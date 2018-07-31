/**
 * 
 */
package com.imooc.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author 杨Sir
 * 模拟 消息队列
 */
@Component
public class MockQueue {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//接到请求订单
	private String placeOrder;
	
	//订单请求完成
	private String CompleteOrder;

	public String getPlaceOrder() {
		return placeOrder;
	}

	public void setPlaceOrder(String placeOrder){	
		//模拟应用服务器2处理，创建一个线程
		new Thread(()-> {
			logger.info("接到订单请求： "+ placeOrder);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.CompleteOrder = placeOrder;
			logger.info("订单请求处理完成： "+ CompleteOrder);
		}).start();
		
	}

	public String getCompleteOrder() {
		return CompleteOrder;
	}

	public void setCompleteOrder(String completeOrder) {
		CompleteOrder = completeOrder;
	}
	
}

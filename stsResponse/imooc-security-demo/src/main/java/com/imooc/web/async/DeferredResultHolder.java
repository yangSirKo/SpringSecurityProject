/**
 * 
 */
package com.imooc.web.async;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author 杨Sir
 * 传递 线程1(MockQueue中业务处理结果) 到  应用1的线程2(监听器得到的结果)
 * 即：将请求队列中的请求的处理结果 同步到 响应队列中，然后返回给用户
 */
@Component
public class DeferredResultHolder {

	private Map<String,DeferredResult<String>> map = new HashMap<>();

	public Map<String, DeferredResult<String>> getMap() {
		return map;
	}

	public void setMap(Map<String, DeferredResult<String>> map) {
		this.map = map;
	}
}

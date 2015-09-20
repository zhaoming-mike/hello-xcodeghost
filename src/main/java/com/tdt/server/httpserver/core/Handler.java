package com.tdt.server.httpserver.core;
/**
 * @author chuer
 * @Description: 消息处理接口
 * @date 2014年11月12日 下午3:55:10 
 * @version V1.0
 */
public interface Handler {
	public void service(Request request, Response response);

	public void doGet(Request request, Response response);

	public void doPost(Request request, Response response);

}

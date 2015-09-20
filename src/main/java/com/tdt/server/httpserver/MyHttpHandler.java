package com.tdt.server.httpserver;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.tdt.server.httpserver.core.Handler;
import com.tdt.server.httpserver.core.impl.HttpRequest;
import com.tdt.server.httpserver.core.impl.HttpResponse;

/**
 * @author chuer
 * @Description: 内部消息处理类
 * @date 2014年11月12日 下午3:53:44 
 * @version V1.0
 */
public class MyHttpHandler implements HttpHandler {

	public void handle(HttpExchange httpExchange) throws IOException {
		HttpRequest request = new HttpRequest(httpExchange);
		HttpResponse response = new HttpResponse(httpExchange);
		String path = request.getReuestURI().getPath();
		Handler handler = Context.getHandler(path);
		handler.service(request, response);

	}
}

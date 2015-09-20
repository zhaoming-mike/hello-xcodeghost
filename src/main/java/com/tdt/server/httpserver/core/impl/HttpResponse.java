package com.tdt.server.httpserver.core.impl;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.tdt.server.httpserver.core.Response;

public class HttpResponse implements Response{
	private HttpExchange httpExchange;
	public HttpResponse(HttpExchange httpExchange){
		this.httpExchange = httpExchange;
	}
	
	@Override
	public void write(String result) {
		try {
			httpExchange.sendResponseHeaders(200, result.length());// 设置响应头属性及响应信息的长度
			OutputStream out = httpExchange.getResponseBody(); // 获得输出流
			out.write(result.getBytes());
			out.flush();
			httpExchange.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}

	@Override
	public void writeBytes(byte[] result) {
		try {
			httpExchange.sendResponseHeaders(200, result.length);// 设置响应头属性及响应信息的长度
			OutputStream out = httpExchange.getResponseBody(); // 获得输出流
			out.write(result);
			out.flush();
			httpExchange.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	
}

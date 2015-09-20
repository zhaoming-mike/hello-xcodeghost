package com.tdt.server.httpserver;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.spi.HttpServerProvider;
import com.tdt.server.httpserver.sample.GhostHandler;

/**
 * 
 * @author zhaoming23@gmail.com
 *
 */
public class MyHttpServer {
    //启动服务，监听来自客户端的请求
	public static void start() throws IOException {
		Context.init("/", "com.tdt.server.httpserver.sample.GhostHandler");
		
		HttpServerProvider provider = HttpServerProvider.provider();
		//监听端口8080,能同时接 受100个请求
		HttpServer httpserver = provider.createHttpServer(new InetSocketAddress(80), 100);
		httpserver.createContext(Context.contextPath, new MyHttpHandler()); 
		httpserver.setExecutor(null);
		httpserver.start();
		System.out.println("dummy XcodeGhost server started.");
	}
	
	
	public static void main(String[] args) throws IOException {
		start();
	}
	
	
}
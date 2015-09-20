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
    //�������񣬼������Կͻ��˵�����
	public static void start() throws IOException {
		Context.init("/", "com.tdt.server.httpserver.sample.GhostHandler");
		
		HttpServerProvider provider = HttpServerProvider.provider();
		//�����˿�8080,��ͬʱ�� ��100������
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
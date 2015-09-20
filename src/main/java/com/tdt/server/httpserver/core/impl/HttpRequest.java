package com.tdt.server.httpserver.core.impl;

import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.tdt.server.httpserver.core.Request;

public class HttpRequest implements Request {
	private HttpExchange httpExchange;
	private Map<String, String> paramMap = new HashMap<String, String>();
	private Map<String, List<String>> headMap = new HashMap<String, List<String>>();
	private byte[] requestBodyByes = null;
	private InputStream body;
	

	public HttpRequest(HttpExchange httpExchange) {
		this.httpExchange = httpExchange;
	}

	@Override
	public String getParamter(String param) {
		return paramMap.get(param);
	}

	@Override
	public String getMethod() {
		return httpExchange.getRequestMethod().trim().toUpperCase();
	}

	@Override
	public URI getReuestURI() {
		return httpExchange.getRequestURI();
	}

	@Override
	public void initRequestParam() {
		String query = getReuestURI().getQuery();
		if(query != null) {
			String [] arrayStr = query.split("&");
			for(String str : arrayStr){
				paramMap.put(str.split("=")[0], str.split("=")[1]);
			}
		}
		
	}

	@Override
	public void initRequestHeader() {
		for(String s : httpExchange.getRequestHeaders().keySet()){
			headMap.put(s, httpExchange.getRequestHeaders().get(s));
		}
	}

	@Override
	public void initRequestBody() {
		body = httpExchange.getRequestBody();
	}

	@Override
	public InputStream getRequestBody() {
		return body;
	}

	@Override
	public Map<String, List<String>> getHeaderMap() {
		return headMap;
	}

}

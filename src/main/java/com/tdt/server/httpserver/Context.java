package com.tdt.server.httpserver;

import java.util.HashMap;
import java.util.Map;


import com.tdt.server.httpserver.core.impl.HttpHandler;

/**
 * 
 * @author zhaoming23@gmail.com
 *
 */
public class Context {
	
	private static Map<String,HttpHandler> contextMap = new HashMap<String,HttpHandler>();
	public static String contextPath;
	
	/**
	 * 
	 * @param key
	 * @return
	 */
	public static HttpHandler getHandler(String key){
		return contextMap.get(key);
	}

	public static void init(String path, String handlerClassName) {
		try {
			
			Class<?> cls = Class.forName(handlerClassName);
			Object newInstance = cls.newInstance();
			if(newInstance instanceof HttpHandler){
				contextMap.put(path, (HttpHandler) newInstance);
			}
			contextPath = path;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
	
	
}
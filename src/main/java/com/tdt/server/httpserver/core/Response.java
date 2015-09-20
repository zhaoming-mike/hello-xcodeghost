package com.tdt.server.httpserver.core;
/**
 * 
 * @author chuer
 * @Description: 相应类接口
 * @date 2014年11月12日 下午3:54:02 
 * @version V1.0
 */
public interface Response {
	
	public void write(String result);
	public void writeBytes(byte[] result);
	
}

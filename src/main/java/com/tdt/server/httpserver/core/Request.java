package com.tdt.server.httpserver.core;

import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.Map;
/**
 * @author chuer
 * @Description: 请求接口
 * @date 2014年11月12日 下午3:54:58 
 * @version V1.0
 */
public interface Request {
	public final static String GET = "GET";
	public final static String POST = "POST";

	public String getParamter(String param);

	public String getMethod();

	public URI getReuestURI();

	public void initRequestHeader();
	
	public void initRequestParam();

	public void initRequestBody();

	public Map<String, List<String>> getHeaderMap();
	
	public InputStream getRequestBody();
	
}

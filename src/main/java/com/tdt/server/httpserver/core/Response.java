package com.tdt.server.httpserver.core;
/**
 * 
 * @author chuer
 * @Description: ��Ӧ��ӿ�
 * @date 2014��11��12�� ����3:54:02 
 * @version V1.0
 */
public interface Response {
	
	public void write(String result);
	public void writeBytes(byte[] result);
	
}

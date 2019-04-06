package com.enjoyhis.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;
import com.enjoyhis.controllers.json.Status;

public class MysqlImpExpUtil {

	public static void exportsql(String tablename, String where, String path) throws IOException {
		String command = Constants.getConfig("mysql_bin") + "/mysqldump -u" + Constants.getConfig("mysql_user") + " -p" + Constants.getConfig("mysql_pw") + " -t " + Constants.getConfig("mysql_dbname") + " " + tablename + " --where=\"" + where + "\" > " + path;
		System.out.println(command);
		Process p = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", command });
		// Process p = Runtime.getRuntime().exec(new String[]{"cmd", "/c",command});
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();
	}

	public static void importsql(String path) throws IOException {
		String command = Constants.getConfig("mysql_bin") + "/mysql -h rm-2zeq799vnwn0453t0.mysql.rds.aliyuncs.com -u" + Constants.getConfig("mysql_user") + " -p" + Constants.getConfig("mysql_pw") + " -f " + Constants.getConfig("mysql_dbname") + " < " + path;
		System.out.println(command);
		Process p = Runtime.getRuntime().exec(new String[] { "/bin/sh", "-c", command });
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();
	}

	public static boolean uploadFile(String url, Map<String, String> param, Map<String, File> filemap) {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httppost = new HttpPost(url);

			MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
			if (param != null) {
				Iterator<String> iterator = param.keySet().iterator();
				while (iterator.hasNext()) {
					String k = iterator.next();
					multipartEntityBuilder.addPart(k, new StringBody(param.get(k), ContentType.TEXT_PLAIN));
				}
			}

			if (filemap != null) {
				Iterator<String> iterator = filemap.keySet().iterator();
				while (iterator.hasNext()) {
					String k = iterator.next();
					multipartEntityBuilder.addPart(k, new FileBody(filemap.get(k)));
				}
			}
			HttpEntity reqEntity = multipartEntityBuilder.build();
			httppost.setEntity(reqEntity);

			System.out.println("executing request " + httppost.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					System.out.println("Response content length: " + resEntity.getContentLength());
				}

				String returndate = getInputStr(resEntity.getContent(), resEntity.getContentLength());
				EntityUtils.consume(resEntity);

				if (returndate == null)
					return false;
				System.out.println(returndate);
				JSONObject jsonObject = JSONUtils.toJSONObject(returndate);
				Status status = JSONUtils.toBean(jsonObject.getJSONObject("status").toString(), Status.class);
				if (status.getCode() != 0) {
					return false;
				} else
					return true;

			} catch (Exception ex) {
				ex.printStackTrace();
			} finally {
				response.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static String getInputStr(InputStream input, long content_length) throws IOException {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		readStreamflush(input, bout, (int) content_length);
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

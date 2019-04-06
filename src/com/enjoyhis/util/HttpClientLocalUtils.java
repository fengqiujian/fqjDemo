package com.enjoyhis.util;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

/**
 * 以post或get方式调用远程方法
 *
 * @author leoliang
 *
 */
public class HttpClientLocalUtils {

	private static Logger logger = LogUtils.INFO;
	private static Logger errorLog = LogUtils.ERROR;

	// 设置请求和传输超时时间
	public static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(5000).build();

	/**
	 *
	 * @param obj 请求类型，'get'为HttpGet，'post'为HttpPost
	 * @param requestUrl 请求的URL
	 * @param param 请求的参数以key-value的形式存放
	 * @param headParam 请求的头信息
	 * @return
	 * @throws IOException
	 */
	public static String getTelnetData(Object obj, String requestUrl, Map<String, Object> param, Map<String, Object> headParam) {
		String responseStr = null;
		final HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		final CloseableHttpClient httpclient = httpClientBuilder.build();

		/**
		 * 远程方法为get请求
		 */
		if (obj instanceof HttpGet) {
			try {
				final HttpGet httpget = (HttpGet) obj;
				httpget.setConfig(requestConfig);
				final UrlEncodedFormEntity uefEntity = setSendParams(param);

				// get请求需要拼接参数
				if (uefEntity != null) {
					requestUrl = requestUrl + "?" + EntityUtils.toString(uefEntity);
				}

				final URI uri = new URI(requestUrl);
				httpget.setURI(uri);
				// 请求头信息
				if (headParam != null) {
					final Iterator<Entry<String, Object>> it = headParam.entrySet().iterator();
					while (it.hasNext()) {
						final Entry<String, Object> entry = it.next();
						if (null != entry.getValue()) {
							httpget.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
						}
					}
				}
				final HttpResponse response = httpclient.execute(httpget);
				final HttpEntity entity = response.getEntity();
				final StatusLine statusLine = response.getStatusLine();
				if (uefEntity != null) {
					logger.info("请求参数:" + EntityUtils.toString(uefEntity));
				}

				if (statusLine.getStatusCode() == 200) {
					responseStr = EntityUtils.toString(entity, "utf-8");// 返回请求实体
				} else {
					logger.info("httpClient响应状态" + statusLine);
				}
				logger.info("请求返回内容:" + responseStr);
			} catch (final Exception e) {
				e.printStackTrace();
				errorLog.error(e.toString(), e);
				errorLog.error("get请求调用远程方法出现异常：" + requestUrl);
				errorLog.error("请求头信息：" + headParam);
				errorLog.error("请求参数：" + param);
			} finally {
				try {
					httpclient.close();// 关闭httpclient
				} catch (final IOException e) {
					e.printStackTrace();
					errorLog.error(e.toString(), e);
				}
			}
		}

		/**
		 * post请求
		 */
		if (obj instanceof HttpPost) {
			try {
				final HttpPost httpPost = new HttpPost();
				httpPost.setConfig(requestConfig);
				final UrlEncodedFormEntity uefEntity = setSendParams(param);

				httpPost.setEntity(uefEntity);
				final URI uri = new URI(requestUrl);
				httpPost.setURI(uri);

				// 请求头信息
				if (headParam != null) {
					final Iterator<Entry<String, Object>> it = headParam.entrySet().iterator();
					while (it.hasNext()) {
						final Entry<String, Object> entry = it.next();
						if (null != entry.getValue()) {
							httpPost.addHeader(entry.getKey(), String.valueOf(entry.getValue()));
						}
					}
				}
				final HttpResponse response = httpclient.execute(httpPost);
				final HttpEntity entity = response.getEntity();
				final StatusLine statusLine = response.getStatusLine();

				if (uefEntity != null) {
					logger.info("请求参数:" + EntityUtils.toString(uefEntity));
				}

				if (statusLine.getStatusCode() == 200) {
					responseStr = EntityUtils.toString(entity, "utf-8");// 返回请求实体
				} else {
					logger.info("httpClient响应状态" + statusLine);
				}
				logger.info("请求返回内容:" + responseStr);

			} catch (final Exception e) {
				e.printStackTrace();
				errorLog.error(e.toString(), e);
				errorLog.error("post请求调用远程方法出现异常：" + requestUrl);
				errorLog.error("请求头信息：" + headParam);
				errorLog.error("请求参数：" + param);
			} finally {
				try {
					httpclient.close();
				} catch (final IOException e) {
					errorLog.error(e.toString(), e);
					e.printStackTrace();
				}
			}

		}
		return responseStr;
	}

	public static String getTelnetData2File(String url, MultipartFile file[], Map<String, Object> paramsMap) {
		final HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		final CloseableHttpClient httpclient = httpClientBuilder.build();
		String responseStr = "";
		try {
			final HttpPost httpPost = new HttpPost(url);

			final MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
			if (file != null && file.length > 0) {
				if (file.length == 1) {
					if (file[0] != null && !file[0].getOriginalFilename().isEmpty()) {
						builder.addBinaryBody("file", file[0].getBytes(), ContentType.DEFAULT_BINARY, file[0].getOriginalFilename());
					}
				} else {
					for (int i = 0; i < file.length; i++) {
						if (file[i] != null && !file[i].getOriginalFilename().isEmpty()) {
							builder.addBinaryBody("file" + i, file[i].getBytes(), ContentType.DEFAULT_BINARY, file[i].getOriginalFilename());
						}
					}
				}
			}

			if (null != paramsMap) {
				final Iterator<Entry<String, Object>> it = paramsMap.entrySet().iterator();
				while (it.hasNext()) {
					final Entry<String, Object> entry = it.next();
					if (entry.getValue() != null) {
						builder.addPart(entry.getKey(), new StringBody(String.valueOf(entry.getValue()), ContentType.create("text/plain", Consts.UTF_8)));
					}
				}
			}

			final HttpEntity entity = builder.build();
			httpPost.setEntity(entity);
			final HttpResponse responses = httpclient.execute(httpPost);

			final StatusLine statusLine = responses.getStatusLine();

			if (statusLine.getStatusCode() == 200) {
				final HttpEntity entitys = responses.getEntity();
				responseStr = EntityUtils.toString(entitys, "utf-8");// 返回请求实体
			} else {
				logger.info("httpClient响应状态" + statusLine);
			}
			logger.info("请求返回内容:" + responseStr);
		} catch (final Exception e) {
			e.printStackTrace();
			errorLog.error(e.toString(), e);
			errorLog.error("post请求调用远程方法出现异常：" + url);
			errorLog.error("请求参数：" + paramsMap + "，请求文件：" + file);
		} finally {
			try {
				httpclient.close();
			} catch (final IOException e) {
				errorLog.error(e.toString(), e);
				e.printStackTrace();
			}

		}
		return responseStr;
	}

	/**
	 * 设置请求参数
	 *
	 * @param map
	 * @return
	 * @throws IOException
	 */
	public static UrlEncodedFormEntity setSendParams(Map<String, Object> map) throws IOException {
		UrlEncodedFormEntity urlentity = null;

		if (null != map) {
			final List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			final Iterator<Entry<String, Object>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				final Entry<String, Object> entry = it.next();
				if (entry.getValue() != null) {
					formparams.add(new BasicNameValuePair(entry.getKey(), String.valueOf(entry.getValue())));
				}
			}
			urlentity = new UrlEncodedFormEntity(formparams, "UTF-8");
		}
		return urlentity;
	}

	public static MultipartRequestEntity setSendParams2File(List<File> files, Map<String, Object> map, PostMethod postMethod) throws IOException {
		final List<Part> formparams = new ArrayList<Part>();
		if (files != null && files.size() >= 0) {
			for (int i = 0; i < files.size(); i++) {
				formparams.add(new FilePart("file", files.get(i)));
			}
		}
		if (null != map) {
			final Iterator<Entry<String, Object>> it = map.entrySet().iterator();
			while (it.hasNext()) {
				final Entry<String, Object> entry = it.next();
				if (entry.getValue() != null) {
					formparams.add(new StringPart(entry.getKey(), String.valueOf(entry.getValue())));
				}
			}
		}
		return new MultipartRequestEntity(formparams.toArray(new Part[formparams.size()]), postMethod.getParams());
	}

}

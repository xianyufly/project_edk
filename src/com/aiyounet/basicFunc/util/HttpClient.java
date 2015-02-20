package com.aiyounet.basicFunc.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * <p>Title: Http客户端</p>
 * <p>Description: 使用Http协议与Web服务器通讯</p>
 * <p>Modify: 2010-10-09</p>
 * @author 林通
 */
public class HttpClient {
	
	private static Logger logger = LoggerFactory.getLogger(HttpClient.class);

	// Http服务器域名/IP + PORT
	private String httpServerUrl;
	// 是否编码请求参数
	private boolean isEncode;
	// 是否采用POST发送请求
	private boolean isPost;
	// 读取cookie用于保持session
	private String cookie;
	
	public HttpClient(String httpServerUrl) {
		this(httpServerUrl, true, true);
	}

	public HttpClient(String httpServerUrl, boolean isEncode, boolean isPost) {
		this.httpServerUrl = httpServerUrl;
		this.isEncode = isEncode;
		this.isPost = isPost;
	}
	
	/**
	 * 获取字符串
	 * @param uri
	 * @param paramMap
	 * @return
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public String getString(String uri, Map<String, ?> paramMap) 
		throws IOException {
		HttpURLConnection urlConnection = getHttpURLConnection(uri, paramMap);
		BufferedReader in = null;
		try {
			StringBuilder builder = new StringBuilder();
			in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
			for (String line; (line = in.readLine()) != null; ) {
				builder.append(line);
			}
			cookie = urlConnection.getHeaderField("set-cookie");
			return builder.toString();
		}		 
		finally {
			if (in != null) {
				try {in.close();}
				catch (IOException ex) {
					logger.error("HttpClient.getString",ex);
				} //忽略
				in = null;
			}
		}
	}

	/**
	 * 获取XML Document
	 * @param uri
	 * @param paramMap
	 * @return
	 * @throws IOException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public Document getXml(String uri, Map<String, ?> paramMap) 
		throws IOException, ParserConfigurationException, SAXException {
		HttpURLConnection urlConnection = getHttpURLConnection(uri, paramMap);
		InputStream in = null;
		try {
			in = new BufferedInputStream(urlConnection.getInputStream());
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = builder.parse(in);
			cookie = urlConnection.getHeaderField("set-cookie");
			return doc;
		}		 
		finally {
			if (in != null) {
				try {in.close();}
				catch (IOException ex) {
					logger.error("HttpClient.getXml",ex);
				} //忽略
				in = null;
			}
		}
	}
	
	/**
	 * 获取Http连接
	 * @param uri
	 * @param paramMap
	 * @return
	 * @throws IOException
	 */
	private HttpURLConnection getHttpURLConnection(String uri, Map<String, ?> paramMap) 
		throws IOException {
		// 编码请求参数
		StringBuilder paramBuilder = new StringBuilder(256);
		if (paramMap != null && !paramMap.isEmpty()) {
			for (Map.Entry<String, ?> entry : paramMap.entrySet()) {
				Object paramValue = entry.getValue();
				if (paramValue != null && paramValue.getClass().isArray()) {
					Object[] values = (Object[]) paramValue;
					for (int i = 0; i < values.length; i ++) {
						paramBuilder.append(isEncode ? URLEncoder.encode(entry.getKey(), "UTF-8") : entry.getKey());
						paramBuilder.append("=");
						if (values[i] != null) {
							paramBuilder.append(isEncode ? URLEncoder.encode(values[i].toString(), "UTF-8") : values[i].toString());
						}
						paramBuilder.append("&");
					}
				}
				else {
					paramBuilder.append(isEncode ? URLEncoder.encode(entry.getKey(), "UTF-8") : entry.getKey());
					paramBuilder.append("=");
					if (paramValue != null) {
						paramBuilder.append(isEncode ? URLEncoder.encode(paramValue.toString(), "UTF-8") : paramValue.toString());
					}
					paramBuilder.append("&");
				}
			}
			paramBuilder.deleteCharAt(paramBuilder.length() - 1);
		}
		String paramString = paramBuilder.toString();
		logger.debug("{}{}?{}", new String[]{httpServerUrl, uri, paramString});
		// 获取Http连接
		URL url = isPost 
			? new URL(httpServerUrl + uri)
			: new URL(httpServerUrl + uri + "?" + paramString);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		//设置超时
		urlConnection.setConnectTimeout(30000);
		urlConnection.setReadTimeout(30000);
		// 处理数据发送
		urlConnection.setDoOutput(true);
		// 禁止使用缓存
		urlConnection.setUseCaches(false);
		// 模拟firefox4头部
		urlConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 5.1; rv:2.0) Gecko/20100101 Firefox/4.0"); 
		urlConnection.setRequestProperty("Accept-Language", "zh-cn,zh;q=0.5"); 
		urlConnection.setRequestProperty("Accept-Charset", "utf-8;q=0.7,*;q=0.7"); 
		// 若cookie存在，则增加请求属性"cookie"
		if (cookie != null) {
			urlConnection.setRequestProperty("cookie", cookie);
		}
		// 发送POST请求参数
		if (isPost) {
			PrintWriter out = null;
			try {
				out = new PrintWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
				out.write(paramString);
			}
			finally {
				if (out != null) {
					out.close();
					out = null;
				}
			}
		}
		return urlConnection;
	}
	
}
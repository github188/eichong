package com.wanma.support.http;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

public class SSLClient  {
	private static HttpClient httpClient;
	
	public static HttpClient getHttpClient() throws Exception {
		if(httpClient!=null)return httpClient;
		// 信任证书相关的配置（单向验证，client不准备自己的证书）
		SSLContextBuilder builder = new SSLContextBuilder();
		// 信任自签名证书
		builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
				builder.build(), new HostnameVerifier() {

					@Override
					public boolean verify(String string, SSLSession ssls) {
						// 方便测试，先全部信任看能否调通
						return true;
					}
				});

		// 首先设置全局的标准cookie策略
		RequestConfig config = RequestConfig.custom()
				.setCookieSpec(CookieSpecs.STANDARD_STRICT).build();

		// 设置可关闭的httpclient
		httpClient = HttpClients.custom()
				.setSSLSocketFactory(sslsf).setDefaultRequestConfig(config)
				.build();
		return httpClient;
	}

	

}

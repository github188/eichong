package com.wanma.support.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.wanma.controller.wechat.utils.PayConfig;
import com.wanma.controller.wechat.utils.XMLUtil;

import net.sf.json.JSONObject;

public class WeChatUtil {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(WeChatUtil.class);

	private static String AppId = PayConfig.WX_APP_ID;
	private static String secret = PayConfig.WX_APP_SECRET;

	public static String getAccessToken() {
		String access_token = "";
		String grant_type = "client_credential";// 获取access_token填写client_credential

		// 这个url链接地址和参数皆不能变
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type="
				+ grant_type + "&appid=" + AppId + "&secret=" + secret;

		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet
					.openConnection();
			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			JSONObject demoJson = JSONObject.fromObject(message);
			LOGGER.info("access_token_JSON字符串：" + demoJson);
			access_token = demoJson.getString("access_token");
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return access_token;
	}

	public static String getTicket(String access_token) {
		String ticket = null;
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
				+ access_token + "&type=jsapi";// 这个url链接和参数不能变
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet
					.openConnection();
			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			JSONObject demoJson = JSONObject.fromObject(message);
			LOGGER.info("ticket_JSON字符串：" + demoJson);
			ticket = demoJson.getString("ticket");
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticket;
	}

	public static String getOpenId(String code) {
		String openId = "";

		// 这个url链接地址和参数皆不能变
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
				+ AppId + "&secret=" + secret + "&code=" + code
				+ "&grant_type=authorization_code";
		try {
			URL urlGet = new URL(url);
			HttpURLConnection http = (HttpURLConnection) urlGet
					.openConnection();
			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			JSONObject demoJson = JSONObject.fromObject(message);
			LOGGER.info("openid_JSON字符串：" + demoJson);
			openId = demoJson.getString("openid");
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return openId;
	}

	public static String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	// 获取随机字符串
	public static String getNonceStr() {
		Random random = new Random();
		return MD5Util
				.MD5Encode(String.valueOf(random.nextInt(10000)), "UTF-8");
	}
	// 获取时间戳
	public static String getTimeStamp() {
		return String.valueOf(System.currentTimeMillis() / 1000);
	}

	// 微信支付签名验证
	public static boolean checkSign(String xmlString) {
		Map<String, String> map = null;
		try {
			map = XMLUtil.doXMLParse(xmlString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String signFromAPIResponse = map.get("sign").toString();
		if (signFromAPIResponse == "" || signFromAPIResponse == null) {
			LOGGER.error("API返回的数据签名数据不存在，有可能被第三方篡改!!!");
			return false;
		}
		LOGGER.info("服务器回包里面的签名是:" + signFromAPIResponse);
		// 清掉返回数据对象里面的Sign数据（不能把这个数据也加进去进行签名），然后用签名算法进行签名
		map.put("sign", "");
		// 将API返回的数据根据用签名算法进行计算新的签名，用来跟API返回的签名进行比较
		String signForAPIResponse = getSign(map);
		if (!signForAPIResponse.equals(signFromAPIResponse)) {
			// 签名验不过，表示这个API返回的数据有可能已经被篡改了
			LOGGER.error("API返回的数据签名验证不通过，有可能被第三方篡改!!! signForAPIResponse生成的签名为"
					+ signForAPIResponse);
			return false;
		}
		LOGGER.info("恭喜，API返回的数据签名验证通过!!!");
		return true;
	}

	// 微信签名验证工具类
	public static String getSign(Map<String, String> map) {
		SortedMap<String, String> signParams = new TreeMap<String, String>();
		for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
			signParams.put(stringStringEntry.getKey(),
					stringStringEntry.getValue());
		}
		signParams.remove("sign");
	//	signParams.remove("ext");
		String sign = createSign("UTF-8", signParams);
		return sign;
	}
	// 本地生成域名
	public static String createSign(String characterEncoding,
			SortedMap<String, String> parameters) {
		StringBuffer sb = new StringBuffer();
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			Object v = entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + PayConfig.WX_PARTNER_KEY);// 最后加密时添加商户密钥，由于key值放在最后，所以不用添加到SortMap里面去，单独处理，编码方式采用UTF-8
		String sign = MD5Util.MD5Encode(sb.toString(), characterEncoding)
				.toUpperCase();
		return sign;
	}

	// ascii排序工具类
	public static String formatUrlMap(Map<String, String> paraMap,
			boolean urlEncode, boolean keyToLower) {
		String buff = "";
		Map<String, String> tmpMap = paraMap;
		try {
			List<Map.Entry<String, String>> infoIds = new ArrayList<Map.Entry<String, String>>(
					tmpMap.entrySet());
			// 对所有传入参数按照字段名的 ASCII 码从小到大排序（字典序）
			Collections.sort(infoIds,
					new Comparator<Map.Entry<String, String>>() {

						public int compare(Map.Entry<String, String> o1,
								Map.Entry<String, String> o2) {
							return (o1.getKey()).toString().compareTo(
									o2.getKey());
						}
					});
			// 构造URL 键值对的格式
			StringBuilder buf = new StringBuilder();
			for (Map.Entry<String, String> item : infoIds) {
				if (StringUtils.isNotBlank(item.getKey())) {
					String key = item.getKey();
					String val = item.getValue();
					if (keyToLower) {
						buf.append(key.toLowerCase() + "=" + val);
					} else {
						buf.append(key + "=" + val);
					}
					buf.append("&");
				}
			}
			buff = buf.toString();
			if (buff.isEmpty() == false) {
				buff = buff.substring(0, buff.length() - 1);
			}
		} catch (Exception e) {
			return null;
		}
		return buff;
	}
	
}
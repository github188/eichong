package com.wanma.support.utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class CecPost {

	public static long lastTimeStamp = 0;
	public static int lastSeq = 1;
/**
 * 
 * @param Url
 * @param OperatorID 组织机构编码
 * @param Token token
 * @param SigSecret 签名密匙
 * @param Data 加密前数据
 * @param DataSecret 加密消息密匙
 * @param DataSecretIv 加密消息密匙向量
 * @return
 * @throws Exception
 */
	public static JSONObject Post(String Url, String OperatorID, String Token,
			String SigSecret, String Data, String DataSecret,
			String DataSecretIv) throws Exception {

		Long TimeStamp = getNowTimeStamp();
		String Seq = getSeq(TimeStamp);
		Data = AesCBC.getInstance().encrypt(Data, "utf-8", DataSecret,
				DataSecretIv);
		String Sig = CecSign.sign(SigSecret, OperatorID, Data, TimeStamp, Seq);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("TimeStamp", TimeStamp + "");
		map.put("Seq", Seq);
		map.put("OperatorID", OperatorID);
		map.put("Data", Data);
		map.put("Sig", Sig);

		// 把token放到头部
		HashMap<String, String> headers = new HashMap<String, String>();
		if (null != Token && "" != Token) {
			headers.put("Authorization", "Bearer " + Token);
			headers.put("Content-Type", "application/json;charset=utf-8");
		}else{
			headers.put("Content-Type", "application/json;charset=utf-8");
		}
		// 发送请求
		String answerStr = doPost(Url, headers, map);
		JSONObject result = JSON.parseObject(answerStr);
		return result;
	}
	
	public static String doPost(String connectURL, Map<String, String> headers,
			Map<String, String> params) {
		HttpPost httpPost = new HttpPost(connectURL);
		for (String headerName : headers.keySet()) {
			httpPost.setHeader(headerName, headers.get(headerName));
		}
		httpPost.setEntity(new StringEntity(JSON.toJSONString(params), Charset
				.forName("UTF-8")));
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			return EntityUtils.toString(entity, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
		return null;
	}
	
	public static JSONObject HttpPost(String Url, String OperatorID, String Token,
			String SigSecret, String Data, String DataSecret,
			String DataSecretIv) throws Exception {

		Long TimeStamp = getNowTimeStamp();
		String Seq = getSeq(TimeStamp);
		Data = AesCBC.getInstance().encrypt(Data, "utf-8", DataSecret,
				DataSecretIv);
		String Sig = CecSign.sign(SigSecret, OperatorID, Data, TimeStamp, Seq);

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("TimeStamp", TimeStamp + "");
		map.put("Seq", Seq);
		map.put("OperatorID", OperatorID);
		map.put("Data", Data);
		map.put("Sig", Sig);

		// 把token放到头部
		HashMap<String, String> headers = new HashMap<String, String>();
		if (null != Token && "" != Token) {
			headers.put("Authorization", "Bearer " + Token);
			headers.put("Content-Type", "application/json;charset=utf-8");
		}else{
			headers.put("Content-Type", "application/json;charset=utf-8");
		}
		// 发送请求
		String answerStr = doPost(Url, headers, map);
		JSONObject jsonObject = JSON.parseObject(answerStr);
	    String data = jsonObject.get("Data").toString();
	    JSONObject result = JSON.parseObject(AesCBC.getInstance().decrypt(data,"utf-8",DataSecret,DataSecretIv));
		return result;
	}

	public static byte[] getHmacMd5Bytes(byte[] key, byte[] data)
			throws NoSuchAlgorithmException {
		/*
		 * HmacMd5 calculation formula: H(K XOR opad, H(K XOR ipad, text))
		 * HmacMd5 计算公式：H(K XOR opad, H(K XOR ipad, text))
		 * H代表hash算法，本类中使用MD5算法，K代表密钥，text代表要加密的数据 ipad为0x36，opad为0x5C。
		 */
		int length = 64;
		byte[] ipad = new byte[length];
		byte[] opad = new byte[length];
		for (int i = 0; i < 64; i++) {
			ipad[i] = 0x36;
			opad[i] = 0x5C;
		}
		byte[] actualKey = key; // Actual key.
		byte[] keyArr = new byte[length]; // Key bytes of 64 bytes length
		/*
		 * If key's length is longer than 64,then use hash to digest it and use
		 * the result as actual key. 如果密钥长度，大于64字节，就使用哈希算法，计算其摘要，作为真正的密钥。
		 */
		if (key.length > length) {
			actualKey = md5(key);
		}
		for (int i = 0; i < actualKey.length; i++) {
			keyArr[i] = actualKey[i];
		}
		/*
		 * append zeros to K 如果密钥长度不足64字节，就使用0x00补齐到64字节。
		 */
		if (actualKey.length < length) {
			for (int i = actualKey.length; i < keyArr.length; i++)
				keyArr[i] = 0x00;
		}

		/*
		 * calc K XOR ipad 使用密钥和ipad进行异或运算。
		 */
		byte[] kIpadXorResult = new byte[length];
		for (int i = 0; i < length; i++) {
			kIpadXorResult[i] = (byte) (keyArr[i] ^ ipad[i]);
		}

		/*
		 * append "text" to the end of "K XOR ipad" 将待加密数据追加到K XOR ipad计算结果后面。
		 */
		byte[] firstAppendResult = new byte[kIpadXorResult.length + data.length];
		for (int i = 0; i < kIpadXorResult.length; i++) {
			firstAppendResult[i] = kIpadXorResult[i];
		}
		for (int i = 0; i < data.length; i++) {
			firstAppendResult[i + keyArr.length] = data[i];
		}

		/*
		 * calc H(K XOR ipad, text) 使用哈希算法计算上面结果的摘要。
		 */
		byte[] firstHashResult = md5(firstAppendResult);

		/*
		 * calc K XOR opad 使用密钥和opad进行异或运算。
		 */
		byte[] kOpadXorResult = new byte[length];
		for (int i = 0; i < length; i++) {
			kOpadXorResult[i] = (byte) (keyArr[i] ^ opad[i]);
		}

		/*
		 * append "H(K XOR ipad, text)" to the end of "K XOR opad" 将H(K XOR
		 * ipad, text)结果追加到K XOR opad结果后面
		 */
		byte[] secondAppendResult = new byte[kOpadXorResult.length
				+ firstHashResult.length];
		for (int i = 0; i < kOpadXorResult.length; i++) {
			secondAppendResult[i] = kOpadXorResult[i];
		}
		for (int i = 0; i < firstHashResult.length; i++) {
			secondAppendResult[i + keyArr.length] = firstHashResult[i];
		}

		/*
		 * H(K XOR opad, H(K XOR ipad, text)) 对上面的数据进行哈希运算。
		 */
		byte[] hmacMd5Bytes = md5(secondAppendResult);
		return hmacMd5Bytes;
	}

	/**
	 * 计算参数的md5信息
	 * 
	 * @param str
	 *            待处理的字节数组
	 * @return md5摘要信息
	 * @throws NoSuchAlgorithmException
	 */
	private static byte[] md5(byte[] str) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(str);
		return md.digest();
	}

	public static Long getNowTimeStamp() {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String ctime = formatter.format(new Date());
		return Long.parseLong(ctime);
	}

	/**
	 * 4位自增序列，同一秒内自增，如0001
	 */
	public static String getSeq(long thisTimeStamp) {
		// 第一次时
		if (lastTimeStamp == thisTimeStamp) {
			lastSeq++;
			return String.format("%04d", lastSeq);
		}
		lastSeq = 1;
		lastTimeStamp = thisTimeStamp;
		return String.format("%04d", lastSeq);
	}

	
	public static void main(String[] args) {
		//String data="{\"OperatorID\":\"MA5EHL6T4\",\"OperatorSecret\":\"6CFA3CA9BC363FC4BC582165E47DAA20\"}";
	       // String data="{\"StationIDs\":[\"251\"]}"; 
			String url="http://cs.ep.eichong.com:74/html/v1.0.0/query_stations_info";	
			 Map<String, Object> data = new HashMap<String, Object>();
			// data.put("OperatorID", "MA5EHL6T4");
			 //data.put("OperatorSecret", "6CFA3CA9BC363FC4BC582165E47DAA20");
			 data.put("LastQueryTime", "2017-05-11 08:08:01");
			 data.put("PageNo", "1");
			 data.put("PageSize", "3");
	        String OperatorID="MA5EHL6T4";
	        String key="1234567890abcdeA";
	        String keys="1234567890abcdeB";
	        String SigSecret="1234567890abcdeC";
	        String token ="1f0cdeccdd145ab5f06fc21c643748ff";
			try {
			JSONObject object = CecPost.HttpPost(url, OperatorID, token, SigSecret, new JSONObject(data).toString(), key, keys);	
				JSONArray DisputeOrders = JSONArray.fromObject(object.get("StationInfos"));
				System.out.println(DisputeOrders);
				//JSONArray parseArray = JSONArray.parseArray(object.get("StationInfos").toString());
				//String s = "{\"error\":0,\"status\":\"success\",\"results\":[{\"currentCity\":\"青岛\",\"index\":[{\"title\":\"穿衣\",\"zs\":\"较冷\"]}"\"}"
				//String json="[{'name':'zhuangsan','age':18},{'name':'lisi','age':20},{'name':'lisi','age':20}]";
				//JSONArray list = JSONArray.fromObject(json);
                //List list = JSONArray.toList(jsonArray);
				//JSONArray array = JSONArray.parseArray(json);
				
                for (int i = 0; i < DisputeOrders.size(); i++) {
                	 String string = DisputeOrders.getJSONObject(i).getString("ConnectorInfos");
                	 System.out.println(string);
                }

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}


}

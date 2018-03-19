package com.wanma.support.utils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;


public class JsonResult {	
	/**返回标识 :系统繁忙请求方稍后重试*/
	public static final int RESULT_No = -1;
    /**返回标识 :请求成功*/
	public static final int RESULT_OK = 0;
	/**返回标识 :请求参数不能为空*/
	public static final int RESULT_Null =2;
	/**返回标识 :密钥错误*/
	public static final int RESULT_Key =3;
	/**返回标识 :参数输入错误*/
	public static final int RESULT_Data_Error=4;
	/**返回标识 :查询结果为空*/
	public static final int RESULT_No_Data=5;
	/**返回标识 :签名错误*/
	public static final int RESULT_Sig=4001;
	/**返回标识 :token错误*/
	public static final int RESULT_Token=4002;
	/**返回标识 :缺少必须的post参数(OperatorID,Sig,TimeStamp,Data,Seq)*/
	public static final int RESULT_Post=4003;
	/**返回标识 :该电桩不支持该第三方充电*/
	public static final int RESULT_NoStart=4009;
	/**返回标识 :缺少必须的post参数(OperatorID,AccessToken)*/
	public static final int RESULT_Headers=4008;
	/**返回标识 :业务参数不合法*/
	public static final int RESULT_Data=4004;
	/**返回标识 :系统错误*/
	public static final int RESULT_Error=500;
	/**返回标识 :第三方没有充电业务 */
	public static final int RESULT_ErrorService=4005;
	/**返回标识 :第三方充电订单重复 */
	public static final int RESULT_ChargeSeqEcho=4006;
	/**返回标识 :电桩占用 */
	public static final int RESULT_Employ=4007;
	/**提示信息 :参数不能为空*/
	public static final String MSG_Null="Request Parameter Not Null";
	/**提示信息 :token错误*/
	public static final String MSG_Token_Error="Request Token Error";
	/**提示信息 :签名错误*/
	public static final String MSG_Sig_Error="Request Sig Error";
	/**提示信息 :密钥错误*/
	public static final String MSG_Key="Request Key Error";
	/**提示信息 :请求成功*/
	public static final String MSG_Ok="Request Success";
	/**提示信息 :该电桩不支持第三方充电*/
	public static final String MSG_NoStart="Request No charge up";
	/**提示信息 :订单编号输入有误,数据库不存在*/
	public static final String MSG_No_ChargeOrder="Request StartChargeSeq Nonentity";
	/**提示信息 :充电站ID输入有误,数据库不存在*/
	public static final String MSG_No_StationID="Request StationID Nonentity";
	/**提示信息 :第三方订单编号不合法*/
	public static final String MSG_ChargeOrder_Error="Request ChargeOrder Error";
	/**提示信息 :充电设备接口编码输入有误，数据库不存在 */
	public static final String MSG_No_ConnectorID="Request ConnectorID Error";
	/**提示信息 :该第三方没有充电业务*/
	public static final String MSG_No_ChargeService="Request No Charge Sevice";
	/**提示信息 :该第三方充电订单重复*/
	public static final String MSG_StartChargeSeq_Echo="Request ChargeOrder Echo";
	/**提示信息 :第三方设备编号不合法*/
	public static final String MSG_EquipAuthSeq_Error="Request EquipAuthSeq Error";
	/**提示信息 :电桩占用,不能充电*/
	public static final String MSG_ElectricPile_Employ="Request ElectricPile Employ";
	/**提示信息 :查询充电实时信息失败*/
	public static final String MSG_No_CahrgingOrder_Info="Request No ChargingOrder Info";
	
	private StringBuffer sb;


	/**
	 * 构造函数
	 @param ret 返回参数标识
	 * @param MsgKey 提示信息
	 * @param data 参数内容
	 * @param key 签名内容
	 * @param sig 签名密钥
	 */
	public JsonResult(int ret,String MsgKey, String data,String key,String sig) {
		handleResult(ret,MsgKey,data,key,sig);
	}

	
	/**
	 * 生成JSON数据
	 * @param ret 返回参数标识
	 * @param MsgKey 提示信息
	 * @param data 参数内容
	 * @param key 签名内容
	 * @param sig 签名密钥
	 */
	public static String handleResult(int ret,String MsgKey, String data,String key,String sig) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Ret", ret);
		map.put("Msg", MsgKey);
	    map.put("Data", data);
		map.put("Sig", HMacMD5.getHmacMd5Str(sig,key));
		String json = new Gson().toJson(map);	
		return json;
	
		
		/*  JSONObject sb = new JSONObject();
		sb.put("Data", data);
		sb.put("Ret", ret);
		sb.put("Msg",MsgKey);
		sb.put("Sig", HMacMD5.getHmacMd5Str(sig,key));
		sb = new StringBuffer();
		sb.append("{");
		sb.append("\"Ret\": " + ret + ",");
		sb.append("\"Msg\": \"" + MsgKey + "\",");
		sb.append("\"Data\":");
		sb.append(data);
		sb.append("\"Sig\": \"" +  HMacMD5.getHmacMd5Str(sig, key) + "\",");
		sb.append("}");*/

	}
	
	/**
	 * 返回JSON文字列
	 */
	public String toString() {
		return sb.toString();
	}

	/**
	 * 返回JSON文字列
	 */
	public byte[]  toBytes() {

		byte[] reBytes = new byte[0];
		try {
			reBytes =  sb.toString().getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return reBytes;
	}



	
	

	
}

package com.wanma.support.utils;

import java.util.concurrent.ConcurrentMap;

import com.wanma.support.common.ResultResponse;
import com.wanma.support.common.WanmaConstants;

@SuppressWarnings({"rawtypes","unchecked"})
public class ControllerUtil {
	public static String doReturn(ConcurrentMap<String,Object> evtMap,String key) throws Exception{
		Object data = null;
		ResultResponse resultRespone = null;
		String errorKey = "errorCode"+key;
		for(int i=0;i<WanmaConstants.DATA_LOOP_COUNT;i++){
			Object errorCode = evtMap.get(errorKey);
			if(errorCode != null){
				evtMap.remove(errorKey);
				resultRespone = new ResultResponse();
				resultRespone.setStatus(Integer.parseInt(errorCode.toString()));
				resultRespone.setMsg("操作失败！");
				break;
			}else{
				data = evtMap.get(key);
				resultRespone = new ResultResponse(data);
				if(data != null){
					evtMap.remove(key);
					break;
				}
			}
			Thread.sleep(WanmaConstants.DATA_LOOP_SLEEP);
		}
		return resultRespone.toString();
	}
	public static String back(ConcurrentMap<String,Object> evtMap,String key) throws Exception{
		Object data = null;
		String result="";
		//ResultResponse resultRespone = null;
		String errorKey = "errorCode"+key;
		for(int i=0;i<WanmaConstants.DATA_LOOP_COUNT;i++){
			Object errorCode = evtMap.get(errorKey);
			if(errorCode != null&&errorCode!="success"){
				evtMap.remove(errorKey);
				
				result=errorCode.toString();
				break;
				
			}else{
				data = evtMap.get(key);
				result="success";
				
				if(data != null){
					evtMap.remove(key);
					break;
				}
			}
			Thread.sleep(WanmaConstants.DATA_LOOP_SLEEP);
		}
		return result;
	}
}

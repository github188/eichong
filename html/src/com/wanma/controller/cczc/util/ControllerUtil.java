package com.wanma.controller.cczc.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wanma.controller.cczc.CczcChargeController;
import com.wanma.support.common.ResultResponse;
import com.wanma.support.common.WanmaConstants;

@SuppressWarnings({"rawtypes","unchecked"})
public class ControllerUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CczcChargeController.class);
    
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
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		LOGGER.info("charge signal end time:"+df.format(new Date()));
		
		return resultRespone.toString();
	}
	
	public static String doReturnCCZC(ConcurrentMap<String,Object> evtMap,String key,String outOrderId) throws Exception{
		HashMap<String, Object> data1 = new HashMap<String, Object>();
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
				
				 
				data1 =  (HashMap<String, Object>)evtMap.get(key);
			/*	if(data1==null&&){
			
				if("".equals(data1.get("outOrderId"))&&data1!=null)
					data1.put("outOrderId", outOrderId);
				}*/
				resultRespone = new ResultResponse(data1);
				if(data1 != null){
					if("".equals(data1.get("outOrderId")))
					data1.put("outOrderId", outOrderId);
					evtMap.remove(key);
					break;
				}
			}
			Thread.sleep(WanmaConstants.DATA_LOOP_SLEEP);
		}
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		LOGGER.info("charge signal end time:"+df.format(new Date()));
		
		return resultRespone.toString();
	}
}

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.wanma.app.util.ConstantUtil" %>
<%@ page import="com.wanma.app.util.XMLUtil" %>
<%@ page import="com.wanma.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="org.apache.commons.logging.*" %>
<%@ page import="com.wanma.app.service.impl.WebUserinfoServiceImpl" %>
<%@ page import="com.wanma.app.service.impl.GovBusiPayServiceImpl" %>
<%@ page import="org.springframework.web.context.support.WebApplicationContextUtils" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.jdom.JDOMException" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
ServletContext sc=this.getServletConfig().getServletContext(); ; 
ApplicationContext ac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc); 
WebUserinfoServiceImpl userinfoService=(WebUserinfoServiceImpl)ac.getBean("webUserinfoServiceImpl");
GovBusiPayServiceImpl userBusiService=(GovBusiPayServiceImpl)ac.getBean("govBusiPayServiceImpl"); 
Log log=LogFactory.getLog("notify.jsp");
log.error("log4j in notify.jsp");

//---------------------------------------------------------
//财付通支付通知（后台通知）示例，商户按照此文档进行开发即可
//---------------------------------------------------------
//商户号
String partner = ConstantUtil.WX_PARTNER;

//密钥
String key = ConstantUtil.WX_PARTNER_KEY;

InputStream inStream = request.getInputStream();
ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
byte[] buffer = new byte[1024];
int len = 0;
while ((len = inStream.read(buffer)) != -1) {
    outSteam.write(buffer, 0, len);
}
outSteam.flush();
outSteam.close();
inStream.close();
log.error("~~~~~~~~~~~~~~~~关闭文件流~~~~~~~~~");
String result  = new String(buffer,"utf-8");
log.error("buffer转换后的参数集：" + result);
result=result.substring(0,result.indexOf("</xml>")+6); 
Map<String,Object> map=XMLUtil.doXMLParse(result);
if(map!=null&&map.size()>0){
	for(Map.Entry<String,Object> entry : map.entrySet()){
		log.error(entry.getKey()+":"+entry.getValue());
	}
	//通知id
	String return_code=(String)map.get("return_code");
	String result_code=(String)map.get("result_code");
	//错误时记录的日志信息
	//out_trade_no参数格式：XXXech后面加userId_充值类型_订单id
	String tradeNo = (String) map.get("out_trade_no");
	String totalFee = (String) map.get("total_fee");
	String tradeType = (String) map.get("trade_type");
	String attach = (String) map.get("attach");
	String extString = tradeNo.split("ech")[1];
	String[] extArray = extString.split("_");
	String userId=extArray[0];
	String puhiType=extArray[1];
	
	log.info("attach"+attach);
	
	CommitLog commitLog = new CommitLog();
	commitLog.setLogName("微信支付");
	commitLog.setLogContent(totalFee);
	commitLog.setCoLoUserId(userId);
	if("SUCCESS".equals(return_code)&&"SUCCESS".equals(result_code)){
		Boolean flag;
		if("business".equals(attach)){
			 flag=userBusiService.addReChargeRecordForTenpay(map,request);
		}
		else{
		//处理数据库逻辑
		 flag=userinfoService.addReChargeRecordForTenpay(map,request);
		}
		log.error("数据库操作:"+flag);
		if(flag){
			out.print("<xml><return_code><![CDATA[SUCCESS]]></return_code>  <return_msg><![CDATA[支付成功]]></return_msg></xml>");
			log.error("------------------微信支付通知成功--------------------");
		}else{
			out.print("fail1");
			log.error("------------------ --------------------");
		}
	}else{
		userinfoService.chargeErrorLog(commitLog, request);
	}
}else{
	out.print("fail2");
	log.error("------------------微信支付通知失败2--------------------");
}


%>


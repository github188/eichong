<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Test-Service</title>
<style type="text/css">
	html,body{
		width:100%; 
		height:100%;
	}  
</style>
<script src="common/jquery.min.js" type="text/javascript"></script>
<script type="text/javascript">
	var basePath = "test/";
	function testFun(value){
		document.getElementById('rightFrame').src= basePath + value;
		return false;
	}
</script>

</head>
<body>

<table width="100%" height="100%">
<tr  style="background: lightblue;">
<td style="background: lightblue;">
Service Test 
<a href="version.jsp" target="_blank" >1.0.0</a>
</td>
<td style="background: lightblue;">

</td>
</tr>

<tr valign="top">
<td style="background: lightgreen; width:11% ;height:100%">
<p>爱充对外公用接口</p>
 
 <a href="#" onclick="testFun('Query_Token.jsp');">获取Token</a><br>
 <a href="#" onclick="testFun('Sync_Stub_Group_Info.jsp');">查询充电点</a><br>
 <a href="#" onclick="testFun('Sync_Stub_Info.jsp');">充电点详情</a><br>
 <a href="#" onclick="testFun('Get_StubId.jsp');">查询充电桩</a><br>
 <a href="#" onclick="testFun('Charge_Start.jsp');">开始充电</a><br>
 <a href="#" onclick="testFun('Charge_Stop.jsp');">结束充电</a><br>

 <p>曹操接口测试</p>
 <a href="#" onclick="testFun('CCZC_Query_Token.jsp');">获取Token</a><br>
 <a href="#" onclick="testFun('CCZC_Charge_Start.jsp');">开始充电</a><br>
 <a href="#" onclick="testFun('CCZC_Charge_Stop.jsp');">结束充电</a><br>
 <a href="#" onclick="testFun('CCZC_Select_Order.jsp');">订单详情</a><br>
 <a href="#" onclick="testFun('CCZC_Check_Charge_Orders.jsp');">订单汇总</a><br>
 <a href="#" onclick="testFun('CCZC_Sync_Stub_Group_Info.jsp');">桩群信息</a><br>
 <a href="#" onclick="testFun('CCZC_Sync_Stub_Info.jsp');">桩群详情</a><br>
 <a href="#" onclick="testFun('CCZC_Get_StubId.jsp');">查询电桩</a><br>

 <p>停简单接口测试</p>
 <a href="#" onclick="testFun('Simple_Query_Token.jsp');">获取Token</a><br>
 <a href="#" onclick="testFun('Simple_Create_Station.jsp');">创建电站</a><br>
 <a href="#" onclick="testFun('Simple_Create_Station_Single.jsp');">创建单个电站</a><br>
 <a href="#" onclick="testFun('Simple_Create_Point.jsp');">创建电桩</a><br>
 <a href="#" onclick="testFun('Simple_Bind_Gun.jsp');">绑定充电枪</a><br>
 <a href="#" onclick="testFun('Simple_Charge_Start.jsp');">开始充电</a><br>
 <a href="#" onclick="testFun('Simple_Charge_Stop.jsp');">结束充电</a><br>
 <a href="#" onclick="testFun('Simple_Select_Order.jsp');">订单查询</a><br>

  <p>高德接口测试</p>
  
 <a href="#" onclick="testFun('AMAP_Serach_Point.jsp');">查询全国充电点</a><br>
 <a href="#" onclick="testFun('AMAP_Charge_Point.jsp');">查询电桩</a><br>

 <br>
</td>
<td id="tt" style="width:89%;height:100%">
<iframe id="rightFrame" name="rightFrame" style="width:100%;height:100%" frameborder="0" scrolling="yes" src="" >

</iframe>
</td>
</tr>

</table>

</body>
<script type="text/javascript">
	$(function(){
	    $("#rightFrame").on("load",function(){
	    	//加载完成，需要执行的代码
	    	var obj = $(document.getElementById('rightFrame').contentWindow.document.body).html();
	    	var str="status";
	   	    if(obj.indexOf(str) >= 0){
	   	    	setJsonResult(obj);
	   	    }
	    });
	}); 
	
	function setJsonResult(obj){
		var start = obj.indexOf('{');
		var last = obj.lastIndexOf('}');
		obj = obj.substring(start,last+1);	
		 var result = formatJson(obj);
		 var ss=window.frames["rightFrame"];
		 ss.document.body.innerHTML = '<pre>' +result + '</pre>';
	}
	
	var formatJson = function (json, options) {
         var reg = null,
                 formatted = '',
                 pad = 0,
                 PADDING = '    ';
         options = options || {};
         options.newlineAfterColonIfBeforeBraceOrBracket = (options.newlineAfterColonIfBeforeBraceOrBracket === true) ? true : false;
         options.spaceAfterColon = (options.spaceAfterColon === false) ? false : true;
         if (typeof json !== 'string') {
             json = JSON.stringify(json);
         } else {
             json = JSON.parse(json);
             json = JSON.stringify(json);
         }
         reg = /([\{\}])/g;
         json = json.replace(reg, '\r\n$1\r\n');
         reg = /([\[\]])/g;
         json = json.replace(reg, '\r\n$1\r\n');
         reg = /(\,)/g;
         json = json.replace(reg, '$1\r\n');
         reg = /(\r\n\r\n)/g;
         json = json.replace(reg, '\r\n');
         reg = /\r\n\,/g;
         json = json.replace(reg, ',');
         if (!options.newlineAfterColonIfBeforeBraceOrBracket) {
             reg = /\:\r\n\{/g;
             json = json.replace(reg, ':{');
             reg = /\:\r\n\[/g;
             json = json.replace(reg, ':[');
         }
         if (options.spaceAfterColon) {
             reg = /\:/g;
             json = json.replace(reg, ':');
         }
         (json.split('\r\n')).forEach(function (node, index) {
                     var i = 0,
                             indent = 0,
                             padding = '';
 
                     if (node.match(/\{$/) || node.match(/\[$/)) {
                         indent = 1;
                     } else if (node.match(/\}/) || node.match(/\]/)) {
                         if (pad !== 0) {
                             pad -= 1;
                         }
                     } else {
                         indent = 0;
                     }
 
                     for (i = 0; i < pad; i++) {
                         padding += PADDING;
                     }
 
                     formatted += padding + node + '\r\n';
                     pad += indent;
                 }
         );
         return formatted;
     };

 
</script>
</html>
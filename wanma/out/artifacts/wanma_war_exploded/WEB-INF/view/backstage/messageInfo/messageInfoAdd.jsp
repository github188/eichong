<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<link href="<%=request.getContextPath()%>/static/css/messageInfo/messageInfoAdd.css"
	rel="stylesheet" />
<script type="text/javascript">
var webroot = "${webroot}";
function ajaxDoneCallback(json){
}
function upperCase(obj){
		var val = obj.value;
		var proviceId = $("#messageInfoProvinceCodeAdd").val();
		var cityId = $("#messageInfoCityCodeAdd").val();
		var reg = new RegExp("^[\\u4E00-\\u9FFF]{3,}","g");
		if(reg.test(val)){
			 $.get(webroot + "/admin/messageInfo/getPowerstation.do?powerstationName="+val+"&proviceId="+proviceId+"&cityId="+cityId+"",{}, function(data) {
				 var json = eval("(" + data + ")");
					$("#listAdd").html("");
					 $("<option value=''>-请选择-</option>").appendTo(
							$(".messageInfoCityId"));
					$.each(json, function(i) { 
						$( '<li value="'+json[i].pkPowerstation+'">'+json[i].mprName+'</li>').appendTo(
								$("#listAdd"));
					}); 	
			});
			 $("#listAdd").show();
		}
	}
$("#listAdd").on("click","li", function(){
	var flag = true;
		var mprName = $(this).text();
		var pkPowerstation = $(this).val();
		$(".hiddenPkPowerstationAdd").each(function(){
			var selectedPkPowerstation = $(this).val();
			if(selectedPkPowerstation==pkPowerstation){
				flag = false;
			}
		});
		if(flag === true){
			html = '<div class="historyTip">'+
			'<input type="hidden" value="'+pkPowerstation+'" name="pkPowerstationList" class="hiddenPkPowerstationAdd"/>'+
			'<input type="text"  readonly="readonly" class="topInputAdd" value="'+mprName+'"  /><sapn class="pic"></sapn></div>';
			$("#allDivAdd").prepend(html);
		}else{
			return;
		}
})
$("#allDivAdd").on("click", ".pic",function(){
	$(this).parent('div').remove();
})
//获取城市
function getCity(proviceId){
	 if(proviceId.value==""){
		$("#messageInfoCityId").hide();
		$("#messageInfoCityCodeAdd").val("");
	 }else{
		 $.get(webroot + "/admin/electric/getCityCode.do?proviceId="+proviceId.value+"",{}, function(data) {
				var json = eval("(" + data + ")");
				$(".messageInfoCityId").html("");
				 $("<option value=''>-请选择-</option>").appendTo(
						$(".messageInfoCityId"));
				$.each(json, function(i) { 
					$(  
							"<option value='"+json[i].cityId+"'>"
									+ json[i].cityName + "</option>").appendTo(
							$(".messageInfoCityId"));
				}); 
			});
	 }
	
}
function formatMessageInfo_add() {
	var reg = new RegExp("^[\u4e00-\u9fa5|A-Za-z0-9|,\，\.\。\?\？\……\——\\-\、\*\(\)\[\\]\【\\】\{\\}\（\）\!\！\\s\]+$","g");
	var messageInfoName = $("#messageInfoName").val();
	if(messageInfoName==""){
		alertMsg.error("标题不能为空!");
		return;
	}
	if(!reg.test(messageInfoName)){
		alertMsg.error("标题不符合要求!");
		return;
	}
	var messageInfoProvinceCodeAdd = $("#messageInfoProvinceCodeAdd").val();
	var messageInfoCityCodeAdd = $("#messageInfoCityCodeAdd").val();
	if(messageInfoProvinceCodeAdd==""){
		alertMsg.error("省份不能为空！");
		return;
	}
	if(messageInfoCityCodeAdd==""){
		alertMsg.error("地区不能为空！");
		return;
	}
	$("#messageInfoFormAdd").submit();
}
</script>
<head>
 </head>

<h2 class="contentTitle">新增首页消息</h2>
<div  class="pageContent">
	<form  method="post" action="messageInfo/addMessageInfo.do"  id="messageInfoFormAdd"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
	<div class="pageFormContent nowrap" layoutH="97"> 
		<dl>
			<dt>类型</dt>
			<dd>
				<input type="radio"  value='1' name='messageInfoType' checked="checked"/>故障 
				<input type="radio"  value='2' name='messageInfoType'/>新建
			</dd>
		</dl>
		<dl>
			<dt>标题</dt>
			<dd>
				<input name="messageInfoName" id="messageInfoName"  class="required"  maxlength="64"  style="width:300px"/> <span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt>正文</dt>
			<dd>
				<textarea class="required" rows="5" cols="50" name="messageInfoContent" maxlength="250"></textarea>
			</dd>
		</dl>
		<dl>
			<dt>省份</dt>
			<dd>
				<select name="messageInfoProvinceCode" id="messageInfoProvinceCodeAdd" style="width: 172px !important" class="select_Style" onchange="getCity(this)" >
								<option value="">-请选择-</option>
								<c:forEach var="item" items="${proviceMap}">
									<option value="${item.key}" >${item.value.PROVINCE_NAME}
									</option>
								</c:forEach>
				</select>
			</dd>
		</dl>
		<dl id="messageInfoCityId" >
			<dt>
				<bmtag:message messageKey="城市" />
			</dt>
			<dd>
				<select  name="messageInfoCityCode" class="select_Style messageInfoCityId" id="messageInfoCityCodeAdd"  >
					<option value="">-请选择-</option>
				</select> <span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt>开始时间</dt>
			<dd>
				 <input  class="textInput required"  id="messageInfoBegintimeAdd" name="messageInfoBegintime"  readonly="true"  class="date"  style="width:155px"
					 onClick="WdatePicker({el:'messageInfoBegintimeAdd',dateFmt:'yyyy-MM-dd HH:mm:ss'})"> <span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt>关联充电点</dt>
			<dd>
				<div id="allDivAdd">
				<div class="historyTip" id="downDiv">
					<input type="text" id="pkPowerstation"  onkeyup="upperCase(this)"/>
					<ul id="listAdd">
					</ul>
				</div>
				</div>
			</dd>
		</dl>
	</div>	
		<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit" 	 onclick="formatMessageInfo_add()"
						type="button" id="formSubmitter" />
				</li>
				<li><bmtag:button messageKey="common.button.back" type="button"
						dwzClass="close" />
				</li>
			</ul>
		</div>
	</form>
</div>

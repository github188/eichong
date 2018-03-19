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
<link href="<%=request.getContextPath()%>/static/css/messageInfo/messageInfoEdit.css"
	rel="stylesheet" />
<script type="text/javascript">
var webroot = "${webroot}";
function ajaxDoneCallback(json){
}
windowLoad();
//进入页面就执行
function windowLoad(){
	var messageInfoProvinceCode = $("#messageInfoProvinceCode").val();
	LoadGetCity(messageInfoProvinceCode);
}
function LoadGetCity(proviceId){
	if(proviceId!=""){
		 var messageInfoCityCode = $("#messageInfoCityCode").val();
		 $.get(webroot + "/admin/electric/getCityCode.do?proviceId="+proviceId+"",{}, function(data) {
				var json = eval("(" + data + ")");
				$(".messageInfoCityId").html("");
				 $("<option value=''>-请选择-</option>").appendTo(
						$(".bannerCityId"));
				$.each(json, function(i) { 
					if(json[i].cityId==messageInfoCityCode){
						$("<option value='"+json[i].cityId+"' selected='selected'>"+ json[i].cityName + "</option>").appendTo($(".bannerCityId"));
					}else{
						$("<option value='"+json[i].cityId+"'>"+ json[i].cityName + "</option>").appendTo($(".messageInfoCityId"));
					}
					}); 
			});	
	}
}
function upperCase(obj){
		var val = obj.value;
		var reg = new RegExp("^[\\u4E00-\\u9FFF]{3,}","g");
		var proviceId = $("#messageInfoProvinceCode").val();
		var cityId = $("#messageInfoCityCode").val();
		var messageInfoProvinceCode
		if(reg.test(val)){
			 $.get(webroot + "/admin/messageInfo/getPowerstation.do?powerstationName="+val+"&proviceId="+proviceId+"&cityId="+cityId+"",{}, function(data) {
					var json = eval("(" + data + ")");
					$("#listEdit").html("");
					 $("<option value=''>-请选择-</option>").appendTo(
							$(".messageInfoCityId"));
					$.each(json, function(i) { 
						$( '<li value="'+json[i].pkPowerstation+'">'+json[i].mprName+'</li>').appendTo(
								$("#listEdit"));
					}); 	
			});
			 $("#listEdit").show();
		}
	}
$("#listEdit").on("click","li", function(){
	var flag = true;
		var mprName = $(this).text();
		var pkPowerstation = $(this).val();
		$(".hiddenPkPowerstationEdit").each(function(){
			var selectedPkPowerstation = $(this).val();
			if(selectedPkPowerstation==pkPowerstation){
				flag = false;
			}
		});
		if(flag === true){
			html = '<div class="historyTip">'+
			'<input type="hidden" value="'+pkPowerstation+'" name="pkPowerstationList" class="hiddenPkPowerstationEdit"/>'+
			'<input type="text"  readonly="readonly" class="topInputEdit" value="'+mprName+'"  /><sapn class="pic"></sapn></div>';
			$("#allDivEdit").prepend(html);
		}else{
			return;
		}
})
$("#allDivEdit").on("click", ".pic",function(){
	$(this).parent('div').remove();
})
//获取城市
function getCity(proviceId){
	 if(proviceId.value==""){
		$(".messageInfoCityId").html("");
		 $("<option value=''>-请选择-</option>").appendTo(
					$(".messageInfoCityId"));
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
				$("#messageInfoCityId").show();
			});
	 }
	
}

function formatMessageInfo_edit() {
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
	var messageInfoProvinceCode = $("#messageInfoProvinceCode").val();
	var messageInfoCityCode = $("#messageInfoCityCode").val();
	if(messageInfoProvinceCode==""){
		alertMsg.error("省份不能为空！");
		return;
	}
	if(messageInfoCityCode==""){
		alertMsg.error("地区不能为空！");
		return;
	}
	$("#messageInfoFormEdit").submit();
}
</script>
<head>
 </head>

<h2 class="contentTitle">编辑首页消息</h2>
<div  class="pageContent">
	<form  method="post" action="messageInfo/editMessageInfo.do"  id="messageInfoFormEdit"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<input type="hidden" name="pkMessageInfoId" value="${messageInfo.pkMessageInfoId}" />
	<div class="pageFormContent nowrap" layoutH="97"> 
		<dl>
			<dt>类型</dt>
			<dd>
				<input type="radio"  value='1' name='messageInfoType' ${messageInfo.messageInfoType==1?'checked' : ''}/>故障 
				<input type="radio"  value='2' name='messageInfoType' ${messageInfo.messageInfoType==2?'checked' : ''}/>新建
			</dd>
		</dl>
		<dl>
			<dt>标题</dt>
			<dd>
				<input name="messageInfoName" id="messageInfoName"  class="required"  maxlength="64" value="${messageInfo.messageInfoName}" style="width:300px"/><span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt>正文</dt>
			<dd>
				<textarea class="required" rows="5" cols="50" name="messageInfoContent" maxlength="250">${messageInfo.messageInfoContent}</textarea>
			</dd>
		</dl>
		<dl>
			<dt>省份</dt>
			<dd>
				<select name="messageInfoProvinceCode" id="messageInfoProvinceCode" style="width: 172px !important" class="select_Style" onchange="getCity(this)" >
								<option value="">-请选择-</option>
								<c:forEach var="item" items="${proviceMap}">
									<option value="${item.key}" ${item.key== messageInfo.messageInfoProvinceCode ? 'selected="selected"' : ''}>${item.value.PROVINCE_NAME}
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
				<select  name="messageInfoCityCode" class="select_Style messageInfoCityId" id="messageInfoCityCode"  >
					<option value="">-请选择-</option>
					<c:forEach var="item" items="${cityList}">
							<option value="${item.cityId}" ${item.cityId==
								messageInfo.messageInfoCityCode ? 'selected="selected"' : ''} >${item.cityName}</option>
					</c:forEach>
				</select> <span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt>开始时间</dt>
			<dd>
				 <input  class="textInput required"  id="messageInfoBegintimeEdit" name="messageInfoBegintime"  readonly="true"  class="date"  style="width:155px"  
				  value="<fmt:formatDate value='${messageInfo.messageInfoBegintime}' pattern='yyyy-MM-dd HH:mm:ss'/>"
					 onClick="WdatePicker({el:'messageInfoBegintimeEdit',dateFmt:'yyyy-MM-dd HH:mm:ss'})"> <span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt>关联充电点</dt>
			<dd>
				<div id="allDivEdit">
				<c:forEach items="${powerstationList}" var="powerstationList" varStatus="status">
					<div class="historyTip">
					<input type="hidden" value="${powerstationList.pkPowerstation } " name="pkPowerstationList" class="hiddenPkPowerstationEdit"/>
					<input type="text"  readonly="readonly" style="display: block;float: left;border: 0;font-size: 14px;height: 24px;width: 279px;line-height: 15px;font-family: "微软雅黑";outline: none;" 
					class="topInputEdit" value="${powerstationList.mprName } "  /><sapn class="pic"></sapn></div>
				</c:forEach>
				<div class="historyTip" id="downDiv">
					<input type="text" id="pkPowerstation"  onkeyup="upperCase(this)"/>
					<ul id="listEdit">
					</ul>
				</div>
				</div>
			</dd>
		</dl>
	</div>	
		<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit" 	onclick="formatMessageInfo_edit()"
						type="button" id="formSubmitter" />
				</li>
				<li><bmtag:button messageKey="common.button.back" type="button"
						dwzClass="close" />
				</li>
			</ul>
		</div>
	</form>
</div>

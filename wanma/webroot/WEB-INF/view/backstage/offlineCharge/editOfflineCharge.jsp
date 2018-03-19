<%@ page language="java"  import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript">
	var webroot = "${webroot}";

	function ajaxDoneCallback(json){
	}
	function offlineCharge_edit(){
	 	var cpyNum = $("#cpyNum").val();
	 	if(cpyNum<0||cpyNum==0){
	 		alertMsg.error("请输入正确的离线充电次数")
	 	}else if(cpyNum>100){
	 		alertMsg.error("最大离线充电次数不能超过100")
	 	}else{
	 		$("#offlineCharge_edit").submit();
	 	}

	}
	
</script>
<h2 class="contentTitle">
	<bmtag:message messageKey="编辑用户标示" />
</h2>
<div class="pageContent">
<form method="post" action="offlineCharge/OfflineChargeEdit.do" class="pageForm required-validate"  id="offlineCharge_edit"
	enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone)">
	<div class="pageFormContent nowrap" layoutH="97">
		<input type="hidden" name="pkCompanyid" value="${company.pkCompanyid}">
		<dl>
			<dt><bmtag:message messageKey="公司名称"/></dt>
			<dd>
				<span name="cpyCompanyNumber" >${company.cpyCompanyname}</span>
			</dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey="公司标识"/></dt>
			<dd>
				<span  id="cpyCompanyNumber">${company.cpyCompanyNumber}</span>
				<input name="cpyCompanyNumber" type="hidden"   value="${company.cpyCompanyNumber}"/>
			</dd>
		</dl>
		<dl>
			<dt>
				<bmtag:message messageKey="最大离线充电次数" />
			</dt>
			<dd>
				<input name="cpyNum" class="required number textInput"   value="${company.cpyNum}" id="cpyNum"/>
				<span class="info"></span>
			</dd>
		</dl>
	</div>
	<div class="formBar" >
			<ul>
				<li><bmtag:button messageKey="common.button.submit" onclick="offlineCharge_edit()"
						type="button" id="formSubmitter"/></li>
				<li><bmtag:button messageKey="common.button.back"  type="button" dwzClass="close"/></li>
			</ul>
	</div>
</form>
</div>
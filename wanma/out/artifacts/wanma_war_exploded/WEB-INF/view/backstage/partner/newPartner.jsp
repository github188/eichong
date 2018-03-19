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

	
</script>
<h2 class="contentTitle">
	<bmtag:message messageKey="新增用户标示" />
</h2>
<div class="pageContent">
<form method="post" action="partner/savePartner.do" class="pageForm required-validate" 
	enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone)">
	<div class="pageFormContent nowrap" layoutH="97">
	<input type="hidden" value="1" name="normRegisteType" />
	<input type="hidden" value="6" name="userLevel" />
		<dl>
			<dt><bmtag:message messageKey="企业名字"/></dt>
			<dd>
				<input name="partnerName" class="required   textInput"   style="width:130px;" id="partnerName"/>
				<span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey="用户身份标识"/></dt>
			<dd>
				<input name="partnerKey" class="required  partnerName textInput"   style="width:130px;" id="partnerName"/>
				<span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt>
				<bmtag:message messageKey="付费类型" />
			</dt>
			<dd>
				<select name="paymod" style="width: 130px  class="select_Style required" >
						<option value="0">-请选择-</option>			
						<option value="1">先付费</option>
						<option value="2">后付费</option>
				</select>
				<span class="info"></span>
			</dd>
		</dl>
	</div>
	<div class="formBar" >
			<ul>
				<li><bmtag:button messageKey="common.button.submit" type="submit" id="formSubmitter"/></li>
				<li><bmtag:button messageKey="common.button.back"  type="button" dwzClass="close"/></li>
			</ul>
	</div>
</form>
</div>
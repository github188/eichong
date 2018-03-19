<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/user/user-list.js" />
<script type="text/javascript">
	var webroot = "${webroot}";

	function ajaxDoneCallback(json) {
	}	
	  
	
</script>

<h2 class="contentTitle">
	<bmtag:message messageKey="修改用户标识信息" />
</h2>
<div class="pageContent">
	<form method="post" action="partner/modifyPartner.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<input type="hidden" name="partnerId" value="${partner.partnerId}" />
			<input type="hidden" name="partnerToken" value="${partner.partnerToken}" />
			<dl>
				<dt>
					<bmtag:message messageKey="公司名字" />
				</dt>
				<dd>
					<input  name="partnerName" value="${partner.partnerName}" />
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="用户身份标示" />
				</dt>
				<dd>
					<input  name="partnerKey" value="${partner.partnerKey}" />
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="付费类型" />
				</dt>
				<dd>
					<select name="paymod" style="width: 130px  class="select_Style required" >
						<option value="1" ${partner.paymod==1 ? 'selected="selected"' : ''}>先付费</option>
						<option value="2" ${partner.paymod==2 ? 'selected="selected"' : ''}>后付费</option>
					</select>
				</dd>
			</dl>	
		</div>
		<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit"
						type="submit" id="formSubmitter" /></li>
				<li><bmtag:button messageKey="common.button.back" type="button"
						dwzClass="close" /></li>
			</ul>
		</div>
	</form>
</div>
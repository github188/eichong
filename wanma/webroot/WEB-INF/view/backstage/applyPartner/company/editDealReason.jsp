<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>

 <link href="<%=request.getContextPath()%>/res/commen.css" rel="stylesheet"/>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
</script>
<h2 class="contentTitle"><bmtag:message messageKey="编辑处理原因"/></h2>
<div class="pageContent">
<form method="post" action="applyPartner/updateApplyPartnerReason.do" class="pageForm required-validate" 
	enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone)">
 <input type="hidden" name="pkApplyPartner" value="${tblApplyPartner.pkApplyPartner}" />
  <input type="hidden" name="navTabId" value="applyCompanyPartner" />
	<div class="pageFormContent nowrap" layoutH="97">		
		<dl><dt>联系人姓名</dt>
		<dd><input name="aa" value="${tblApplyPartner.apPaUserName}" readonly="readonly" class="textInput required" maxlength="20" style="width:165px;"/>
			<span class="info"></span></dd></dl>
		 <dl><dt>联系人电话</dt><dd>
			<input name="bb" maxlength="20" class="textInput " style="width:165px;" readonly="readonly" value="${tblApplyPartner.apPaUserPhone}"/>
			<span class="info"></span></dd></dl>
	 		<dl><dt>创建时间</dt><dd>
	        <fmt:formatDate value="${tblApplyPartner.apPaCreatedate}" pattern="yyyy/MM/dd  HH:mm:ss" var="d" />
			<input name="cc"  maxlength="30" class="textInput required" style="width:165px;" readonly="readonly" value=" ${d}"+".toLocaleString()"/>
			<span class="info"></span></dd></dl> 
			 </dd></dl>
			 <dl><dt>驳回原因</dt><dd>
			 <textarea name="apPaDealReason"  maxlength="500" rols="10" rows="6" type="textarea"  style="width:265px;">${tblApplyPartner.apPaDealReason}</textarea>			
			 </dd></dl>
	
	
	</div>
	<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="确定驳回" type="submit" id="formSubmitter"/></li>
				<li><bmtag:button messageKey="取消"  type="button" dwzClass="close"/></li>
			</ul>
	</div>
</form>
</div>
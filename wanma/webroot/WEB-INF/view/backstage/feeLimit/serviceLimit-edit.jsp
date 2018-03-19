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
<h2 class="contentTitle"><bmtag:message messageKey="费率上限编辑"/></h2>
<div class="pageContent">
<form method="post" action="feelimit/updateServiceLimit.do" class="pageForm required-validate" 
	enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone)">
 		    <input type="hidden" name="cityId" value="${serviceLimitList[0].cityId}" />
			<input type="hidden" name="provinceId" value="${serviceLimitList[0].provinceId}" />		
			<fmt:formatDate value="${serviceLimitList[0].createDate}" pattern="yyyy/MM/dd  HH:mm:ss" var="d" />
			<input name="createDate" type="hidden" maxlength="30" class="textInput required" style="width:165px;" readonly="readonly" value=" ${d}"+".toLocaleString()"/>  		
			<div class="pageFormContent nowrap" layoutH="97">			  
		    <dl><dt>城市名称</dt><dd>
			<input name="cityName"  maxlength="20" class="textInput" readonly="readonly" style="width:165px;" value="${serviceLimitList[0].cityName}"  />
			 </dd></dl>
			 <dl><dt>城市费率</dt><dd>
			<input name="serviceLimit"  maxlength="20" class="textInput" style="width:165px;" value="${serviceLimitList[0].serviceLimit}" />			
			 </dd></dl>
	 
	</div>
	<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit"   type="submit" id="formSubmitter"/></li>
				<li><bmtag:button messageKey="common.button.back"  type="button" dwzClass="close"/></li>
			</ul>
	</div>
</form>
</div>
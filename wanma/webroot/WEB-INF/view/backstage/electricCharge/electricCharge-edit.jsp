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
<h2 class="contentTitle"><bmtag:message messageKey="尖峰平谷电费编辑"/></h2>
<div class="pageContent">
<form method="post" action="electricCharge/updateElectricCharge.do" class="pageForm required-validate" 
	enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone)"> 	  
			<input type="hidden" name="provinceId" value="${electricChargeList[0].PROVINCE_ID}" />		
			<fmt:formatDate value="${electricChargeList[0].CREATE_DATE}" pattern="yyyy/MM/dd  HH:mm:ss" var="d" />
			<input name="CREATE_DATE" type="hidden" maxlength="30" class="textInput required" style="width:165px;" readonly="readonly" value=" ${d}"+".toLocaleString()"/>  		
			<div class="pageFormContent nowrap" layoutH="97">			  
		    <dl><dt>省份名称</dt><dd>
			<input name="PROVINCE_NAME"  maxlength="20" class="textInput" readonly="readonly" style="width:165px;" value="${electricChargeList[0].PROVINCE_NAME}"  />
			 </dd></dl>
			 <dl><dt>尖段电费</dt><dd>
			<input name="Tip_Electricity" max="10" maxlength="5" class="textInput" style="width:165px;" value="${electricChargeList[0].Tip_Electricity}" />			
			 </dd></dl>
			  <dl><dt>峰段电费</dt><dd>
			<input name="Peak_Electricity" max="10" maxlength="5" class="textInput" style="width:165px;" value="${electricChargeList[0].Peak_Electricity}" />			
			 </dd></dl>
			  <dl><dt>平段电费</dt><dd>
			<input name="Flat_Electricity" max="10" maxlength="5" class="textInput" style="width:165px;" value="${electricChargeList[0].Flat_Electricity}" />			
			 </dd></dl>
			  <dl><dt>谷段电费</dt><dd>
			<input name="Valley_Electricity"  max="10" maxlength="5" class="textInput" style="width:165px;" value="${electricChargeList[0].Valley_Electricity}" />			
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
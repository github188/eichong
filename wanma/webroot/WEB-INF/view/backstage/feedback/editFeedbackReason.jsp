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
<form id="updateFeedbackForm" method="post" action="feedback/updateFeedback.do" class="pageForm required-validate" 
	enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone)">
 <input type="hidden" name="feedbackId" value="${feedback.feedbackId}" />
 <input type="hidden" name="userId" value="${feedback.userId}" />
	<div class="pageFormContent nowrap" layoutH="97">		
		<dl><dt>用户账号</dt>
		<dd><input name="phone" value="${feedback.phone}" readonly="readonly" class="textInput required" maxlength="20" style="width:165px;"/>
			<span class="info"></span></dd></dl>
		 <dl><dt>联系人</dt><dd>
			<input name="userIname" maxlength="20" class="textInput " style="width:165px;" readonly="readonly" value="${feedback.userIname}"/>
			<span class="info"></span></dd></dl>
	 <dl><dt>创建时间</dt><dd>
	         <fmt:formatDate value="${feedback.createDate}" pattern="yyyy/MM/dd" var="d" />
			<input disabled name="createDate"  maxlength="30" class="textInput required" style="width:165px;" readonly="readonly" value=" ${d}"+".toLocaleString()"/>
			<span class="info"></span></dd></dl>
		 <dl><dt>反馈内容</dt><dd>
			<textarea name="content"  maxlength="480" class="textInput" style="width:165px;" readonly="readonly">${feedback.content}</textarea>
			 </dd></dl>
			 <dl><dt>处理原因</dt><dd>
			 <textarea id="reasonDiv" name="reason" class="" maxlength="500" rols="10" rows="6" type="textarea"  style="width:265px;">${feedback.reason}</textarea>			
			 </dd></dl>
	 <dl><dt>处理状态</dt><dd>
					  <select id="processStatusDiv" name="processStatus" class="select_Style">							
							<option value="1" ${feedback.processStatus==1 ? 'selected="selected"' : ''}>接收正在处理</option>
							<option value="2" ${feedback.processStatus==2 ? 'selected="selected"' : ''}>处理完成</option>							
						 </select>
			<span class="info"></span></dd></dl>
	</div>
	<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit" type="button" id="formSubmitter" onclick="doSubmit()"/></li>
				<li><bmtag:button messageKey="common.button.back"  type="button" dwzClass="close"/></li>
			</ul>
	</div>
</form>
</div>
<script>
	function doSubmit(){
		if($("#processStatusDiv").val() == "2" && $("#reasonDiv").val().trim() == ""){
			alertMsg.error("请填写反馈内容！");
			return false;
		}
		$("#updateFeedbackForm").submit();
	}
</script>
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
<form method="post" action="applyElecPile/updateApplyElecPileReason.do" class="pageForm required-validate" 
	enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone)">
 <input type="hidden" name="pkApplyElecPile" value="${tblApplyElecPile.pkApplyElecPile}" />
 <input type="hidden" name="navTabId" value="applyElecPile" />
	<div class="pageFormContent nowrap" layoutH="97">		
		<dl><dt>联系人姓名</dt>
		<dd><input name="aa" value="${tblApplyElecPile.apEpUserName}" readonly="readonly" class="textInput " maxlength="20" style="width:165px;"/>
			<span class="info"></span></dd></dl>
		 <dl><dt>联系人电话</dt><dd>
			<input name="bb" maxlength="20" class="textInput " style="width:165px;" readonly="readonly" value="${tblApplyElecPile.apEpUserPhone}"/>
			<span class="info"></span></dd></dl>
	 		<dl><dt>创建时间</dt><dd>
	        <fmt:formatDate value="${tblApplyElecPile.apEpCreatedate}" pattern="yyyy/MM/dd  HH:mm:ss" var="d" />
			<input name="cc"  maxlength="30" class="textInput " style="width:165px;" readonly="readonly" value=" ${d}"+".toLocaleString()"/>
			<span class="info"></span></dd></dl> 
			 </dd></dl>
			 <c:if test="${tblApplyElecPile.apEpProcessState != 4}">
			 <dl><dt>驳回原因</dt><dd>
			 <textarea name="apEpDealReason"  maxlength="500" rols="10" rows="6" type="textarea"  style="width:265px;">${tblApplyElecPile.apEpDealReason}</textarea>			
			 </dd></dl>
			 </c:if>
			  <c:if test="${tblApplyElecPile.apEpProcessState == 4}">
			  <dl><dt>驳回原因</dt><dd>
			  <span>申请完成，不能驳回。请点右下角“取消”</span>
			  </dd></dl>
			  </c:if>
	
	</div>
	<div class="formBar">
			<ul>
				<c:if test="${tblApplyElecPile.apEpProcessState != 4}"><li><bmtag:button messageKey="确定驳回" type="submit" id="formSubmitter"/></li></c:if>
				<li><bmtag:button messageKey="取消"  type="button" dwzClass="close"/></li>
			</ul>
	</div>
</form>
</div>
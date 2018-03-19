<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
</script>
<div class="pageHeader">
	<form method="post" action="electric/examineReason.do" id="addForm"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="ids" value="${elpiElectricpilecode}" />
		<dt>
		 <dl> <textarea name="elpiRejectionreason" rows="13" cols="85" id="textarea"
					onblur="if(this.innerHTML==''){this.innerHTML='请输入回驳原因';}"
					onfocus="if(this.innerHTML=='请输入回驳原因'){this.innerHTML=''; }">请输入回驳原因</textarea></dl>
		 <dl><div style="margin-left: 240px;margin-top: 10px;"><bmtag:button messageKey="common.button.submit" type="submit" id="formSubmitter"/></div></dl>
		</dt>
	</form>
</div>

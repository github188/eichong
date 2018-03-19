<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<script type="text/javascript">
	var webroot = "${webroot}";

	function ajaxDoneCallback(json){
	}   	
</script>
<div class="pageContent">
<form method="post" action="comquestion/saveCommonquestion.do" class="pageForm required-validate" 
	enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone)">
	<div class="pageFormContent nowrap" layoutH="56">
		<dl>
			<dt>常见问题：</dt>
			<dd>
				<textarea rows="10" cols="100" name="questionName" class="required" >
				</textarea>
				<span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt>回复内容：</dt>
			<dd>
				<textarea rows="10" cols="100" name="replyContent" class="required">
				</textarea>
				<span class="info"></span>
			</dd>
		</dl>
	</div>
	<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit" type="submit" id="formSubmitter"/></li>
				<li><bmtag:button messageKey="common.button.back"  type="button" dwzClass="close"/></li>
			</ul>
	</div>
</form>
</div>
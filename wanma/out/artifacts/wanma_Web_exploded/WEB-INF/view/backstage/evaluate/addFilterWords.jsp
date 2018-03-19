<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<div class="pageContent">
<form method="post" action="evaluate/saveFilterWord.do" class="pageForm required-validate" 
	enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone)">
	<div class="pageFormContent nowrap" layoutH="97">
		<dl><dt>添加过滤字</dt><dd>
			<input name="wordsName" value="" class="required normal" maxlength="10" style="width:130px;"/>
			<span class="info"></span></dd></dl>
	</div>
	<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit" type="submit" id="formSubmitter"/></li>
				<li><bmtag:button messageKey="common.button.back"  type="button" dwzClass="close"/></li>
			</ul>
	</div>
</form>
</div>
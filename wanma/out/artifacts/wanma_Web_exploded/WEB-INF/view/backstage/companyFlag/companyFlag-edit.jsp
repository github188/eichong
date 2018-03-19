<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<script type="text/javascript">
	var webroot = "${webroot}";

	function ajaxDoneCallback(json){
	}
	
</script>
<h2 class="contentTitle">
	<bmtag:message messageKey="编辑公司" />
</h2>
<div class="pageContent">
	<form method="post" action="companyFlag/changeCompanyFlag.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<input type="hidden" name="pkCompanyid"
			value="${tblCompany.pkCompanyid}" />
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dt>
					<bmtag:message messageKey="企业名称" />
				</dt>
				<dd>
					<input name="cpyCompanyname" value="${tblCompany.cpyCompanyname}" class="textInput required normal" readonly
						style="width: 130px;" /> 
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="公司标识" />
				</dt>
				<dd>
					<input name="cpyCompanyNumber" class="textInput required digits" minlength="4" size="4"
						maxlength="4" style="width: 130px;" value="${tblCompany.cpyCompanyNumber}" remote="companyFlag/checkCpyCompanyNumber.do?pkCompanyid=${tblCompany.pkCompanyid}"/> <span class="info"></span>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit"
						type="submit" id="formSubmitter" />
				</li>
				<li><bmtag:button messageKey="common.button.back" type="button"
						dwzClass="close" />
				</li>
			</ul>
		</div>
	</form>
</div>
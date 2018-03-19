<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<script type="text/javascript">
	var webroot = "${webroot}";
	$(function() {
		$.get(webroot + "/codegroup/codeList.do?codeGroupId=CD_G_001", {},
				function(data) {
					var json = eval("(" + data + ")");
					$.each(json, function(i) {
						$(
								"<option value='"+json[i].codeId+"'>"
										+ json[i].codeName + "</option>")
								.appendTo($("#companyLevelList_companyNew"));
					});
					$("#formSubmitter").removeAttr("disabled");
				});
	});
	function ajaxDoneCallback(json) {
	}
</script>
<h2 class="contentTitle">
	<bmtag:message messageKey="company.title.company.new" />
</h2>
<div class="pageContent">
	<form method="post" action="company/saveCompany.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">

			<dl>
				<dt>
					<bmtag:message messageKey="company.label.company_id" />
				</dt>
				<dd>
					<input name="companyId" class="textInput alphanumeric required"
						value="${companyModel.companyId}"
						remote="company/checkCompanyUnique.do" maxlength="10"
						style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="company.label.company_name" />
				</dt>
				<dd>
					<input name="companyName" class="textInput required" maxlength="20"
						style="width: 227px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="company.label.notes" />
				</dt>
				<dd>
					<textarea name="notes" cols="30" rows="4" maxlength="80"></textarea>
					<span class="info"></span>
				</dd>
			</dl>
		<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit"
						type="submit" id="formSubmitter" /></li>
				<li><bmtag:button messageKey="common.button.back" type="button"
						dwzClass="close" /></li>
			</ul>
		</div>
		</div>
	</form>
</div>

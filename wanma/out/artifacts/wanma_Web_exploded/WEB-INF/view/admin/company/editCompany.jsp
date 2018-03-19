<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<style type="text/css">
ul.rightTools {
	float: right;
	display: block;
}

ul.centerTools {
	float: none;
	display: block;
}

ul.centerTools li {
	float: none;
	display: block;
	margin-left: 160px;
}

ul.rightTools li {
	float: left;
	display: block;
	margin-left: 5px
}
</style>
<div class="pageHeader" style="border: 1px #B8D0D6 solid">
	<div class="panel" defH="25">
		<h1>
			<bmtag:message messageKey="company.title.company.new" />
		</h1>
		<div>
			<ul class="rightTools">
				<li><bmtag:link messageKey="common.button.copy"
						href="company/editCompany.do?companyId=001" target="ajax"
						rel="companyBox" dwzClass="button" /></li>
				<li><bmtag:link messageKey="common.button.new"
						dwzClass="button" /></li>
				<li><bmtag:link messageKey="common.button.delete"
						dwzClass="button" /></li>
			</ul>
		</div>
	</div>
	<div class="divider"></div>

	<div class="pageFormContent nowrap" layoutH="147">
		<form method="post" action="company/modifyCompany.do"
			class="pageForm required-validate" enctype="multipart/form-data"
			onsubmit="return iframeCallback(this, navTabAjaxDone)">
			<dl>
				<dt>
					<bmtag:message messageKey="company.label.company_id" />
				</dt>
				<dd>
					<input name="companyId" class="textInput alphanumeric required"
						value="${companyModel.companyId}" maxlength="10"
						style="width: 130px;" readonly /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="company.label.company_name" />
				</dt>
				<dd>
					<input name="companyName" class="textInput required"
						value="${companyModel.companyName}" maxlength="20"
						style="width: 227px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="company.label.notes" />
				</dt>
				<dd>
					<textarea name="notes" cols="30" rows="4" maxlength="80">${companyModel.notes}</textarea>
					<span class="info"></span>
				</dd>
			</dl>
			<dl></dl>
			<div class="divider"></div>
			<ul class="centerTools">
				<li><bmtag:button
						messageKey="common.button.submit" type="submit" id="formSubmitter" /></li>
			</ul>
		</form>
	</div>
</div>
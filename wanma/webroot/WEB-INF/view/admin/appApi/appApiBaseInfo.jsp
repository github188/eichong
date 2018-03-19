
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>

<form method="post" action="appApi/saveAppApi.do" id="appApiForm"
	name="appApiForm" class="pageForm required-validate"
	enctype="multipart/form-data"
	onsubmit="return iframeCallback(this, navTabAjaxDone)">
	<dl>
		<dt>
			<bmtag:message messageKey="appApi.label.appApi_id" />
		</dt>
		<dd>
			<input name="appApiId" class="textInput alphanumeric required" id="appApiId"
				value="${appApiModel.appApiId}" maxlength="20" style="width: 130px;" 
				remote="appApi/checkAppApiUnique.do"/>
			<span class="info"></span>
		</dd>
	</dl>
	<dl>
		<dt>
			<bmtag:message messageKey="appApi.label.appApi_name" />
		</dt>
		<dd>
			<input name="appApiName" class="textInput required"
				value="${appApiModel.appApiName}" maxlength="20"
				style="width: 130px;" /> <span class="info"></span>
		</dd>
	</dl>
	<dl>
		<dt>
			<bmtag:message messageKey="appApi.label.url" />
		</dt>
		<dd>
			<input name="url" class="textInput halfsymbol required" id="url"
				value="${appApiModel.url}" maxlength="20" style="width: 230px;" 
				remote="appApi/checkUrlUnique.do"/>
			<span class="info"></span>
		</dd>
	</dl>
	<dl>
		<dt>
			<bmtag:message messageKey="appApi.label.notes" />
		</dt>
		<dd>
			<textarea name="notes" style="width: 230px;">${appApiModel.notes}</textarea>
			<span class="info"></span>
		</dd>
	</dl>
</form>
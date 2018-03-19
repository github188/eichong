
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<dl>
	<dt>
		<bmtag:message messageKey="action.label.page_id" />
	</dt>
	<dd>
		<input name="pageId" class="textInput halfsymbol"
			id="pageId" value="${actionModel.pageId}" maxlength="20" readonly
			style="width: 130px;" /> <span class="info"></span>
	</dd>
</dl>
<dl>
	<dt>
		<bmtag:message messageKey="action.label.page_name" />
	</dt>
	<dd>
		<input name="pageName" class="textInput"
			value="${actionModel.pageName}" maxlength="20" readonly
			style="width: 130px;" /> <span class="info"></span>
	</dd>
</dl>
<dl>
	<dt>
		<bmtag:message messageKey="action.label.action_id" />
	</dt>
	<dd>
		<input name="actionId" class="textInput halfsymbol"
			id="actionId" value="${actionModel.actionId}" maxlength="20"
			style="width: 130px;" /> <span class="info"></span>
	</dd>
</dl>
<dl>
	<dt>
		<bmtag:message messageKey="action.label.action_name" />
	</dt>
	<dd>
		<input name="contents" class="textInput"
			value="${actionModel.actionName}" maxlength="20"
			style="width: 130px;" /> <span class="info"></span>
	</dd>
</dl>
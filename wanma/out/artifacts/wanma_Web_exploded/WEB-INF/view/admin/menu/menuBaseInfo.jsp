
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<dl>
	<dt>
		<bmtag:message messageKey="menu.label.menu_id" />
	</dt>
	<dd>
		<input name="menuId" class="textInput halfsymbol required"
			id="menuId" value="${menuModel.menuId}"
			remote="menu/checkMenuUnique.do" maxlength="20" style="width: 130px;" />
		<span class="info"></span>
	</dd>
</dl>
<dl>
	<dt>
		<bmtag:message messageKey="menu.label.contents" />
	</dt>
	<dd>
		<input name="contents" class="textInput required"
			value="${menuModel.contents}" maxlength="20" style="width: 130px;" />
		<span class="info"></span>
	</dd>
</dl>
<dl>
	<dt>
		<bmtag:message messageKey="menu.label.url" />
	</dt>
	<dd>
		<input name="url" class="textInput halfsymbol" id="url"
			value="${menuModel.url}" maxlength="80" style="width: 230px;" /> <span
			class="info"></span>
	</dd>
</dl>
<dl>
	<dt>
		<bmtag:message messageKey="menu.label.rel" />
	</dt>
	<dd>
		<input name="rel" class="textInput alphanumeric" id="rel"
			value="${menuModel.rel}" maxlength="20" style="width: 130px;" /> <span
			class="info"></span>
	</dd>
</dl>
<dl>
	<dt>
		<bmtag:message messageKey="menu.label.sort_number" />
	</dt>
	<dd>
		<input name="sortNumber" class="textInput digits required" id="sortNumber"
			value="${menuModel.sortNumber}" maxlength="20" style="width: 130px;" /> <span
			class="info"></span>
	</dd>
</dl>
<dl>
	<dt>
		<bmtag:message messageKey="menu.label.parent_menu_id" />
	</dt>
	<dd>
		<input name="parentMenuId" class="textInput alphanumeric" id="parentMenuId"
			value="${menuModel.parentMenuId}" maxlength="20"
			style="width: 130px;" readonly /> <span class="info"></span>
	</dd>
</dl>
<dl>
	<dt>
		<bmtag:message messageKey="menu.label.notes" />
	</dt>
	<dd>
		<textarea name="notes"  style="width: 230px;" >${menuModel.notes}</textarea> <span
			class="info"></span>
	</dd>
</dl>
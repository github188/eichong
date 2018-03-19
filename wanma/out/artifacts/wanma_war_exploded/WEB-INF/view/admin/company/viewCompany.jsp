<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<h2 class="contentTitle">
	<bmtag:message messageKey="company.title.company.view" />
</h2>
<div class="pageContent">
	<div class="pageFormContent nowrap" layoutH="97">
		<dl>
			<dt>公司ID:</dt>
			<dd>
				${companyModel.companyId } <span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt>公司名称:</dt>
			<dd>
				${companyModel.companyName } <span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt>备注:</dt>
			<dd>
				${companyModel.notes } <span class="info"></span>
			</dd>
		</dl>
	</div>
	<div class="formBar">
		<ul>
			<li><bmtag:button messageKey="common.button.back" type="button"
					dwzClass="close" /></li>
		</ul>
	</div>
</div>

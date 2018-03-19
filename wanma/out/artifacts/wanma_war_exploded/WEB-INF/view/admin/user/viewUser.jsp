<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<h2 class="contentTitle">
	<bmtag:message messageKey="user.title.user.view" />
</h2>
<div class="pageContent">
	<div class="pageFormContent nowrap" layoutH="92">
		<dl>
			<dt>
				<bmtag:message messageKey="用户名" />
			</dt>
			<dd>
				${user.userAccount} <span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt>
				<bmtag:message messageKey="姓名" />
			</dt>
			<dd>
				${user.adminName} <span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt>
				<bmtag:message messageKey="手机号" />
			</dt>
			<dd>
				${user.adminPhone} <span class="info"></span>
			</dd>
		</dl>
		<dl>
				<dt>
					<bmtag:message messageKey="拥有角色"/>
				</dt>
				<dd>
					<c:forEach var="selectItem" items="${selectRoleList}">
						${selectItem.roleName}&nbsp;&nbsp;&nbsp;&nbsp;
					</c:forEach>
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
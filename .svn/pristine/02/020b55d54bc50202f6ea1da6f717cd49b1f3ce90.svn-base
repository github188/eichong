<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>
<h2 class="contentTitle"><bmtag:message messageKey="菜单编辑"/></h2>
<div class="pageContent">
<form id="editMenuForm" method="post" action="menu/modifyMenu.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone)">
	<input type="hidden" name="menuId" value="${menu.menuId}" />
	<div class="pageFormContent nowrap" layoutH="97" >
			<dl>
				<dt>菜单名称：</dt>
				<dd>
					<input name="contents" value="${menu.contents}" type="text" class="required normal" size="25"
						maxlength="20" remote="<%=request.getContextPath()%>/common/checkUnique.do?tName=p_m_menu&tProperty=contents&property=contents&pkTProperty=menu_id&pkTValue=${menu.menuId}"/>
				</dd>
			</dl>
			<dl>
				<dt>菜单链接：</dt>
				<dd>
					<input name="url" value="${menu.url}" type="text" class="halfsymbol" size="25"
						maxlength="60" />
				</dd>
			</dl>
			<dl>
				<dt>上级菜单：</dt>
				<dd>
					<input name="org.id" value="${menu.parentMenuId}" type="hidden"/>
					<input name="org.name" value="${menu.parentMenuName}" type="text" class="normal" readonly />
					<a class="btnLook" href="menu/getMenuTreeLookup.do" width="400" lookupGroup="org">查找带回</a> 	
				</dd>
			</dl>
			<dl>
				<dt>菜单类别：</dt>
				<dd>
					<select name="menuType" class="required">
						<option value="1" ${menu.menuType=='1' ? 'selected="selected"' : ''}>菜单权限</option>
						<option value="2" ${menu.menuType=='2' ? 'selected="selected"' : ''}>按钮权限</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>菜单标签：</dt>
				<dd>
					<input name="rel" value="${menu.rel}" type="text" class="alphanumeric" maxlength="20" />
				</dd>
			</dl>
			<dl>
				<dt>菜单排序：</dt>
				<dd>
					<input name="sortNumber" value="${menu.sortNumber}" type="text" class="required digits" size="2"
						maxlength="2" />
				</dd>
			</dl>
	</div>
	<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit" type="submit" /></li>
				<li><bmtag:button messageKey="common.button.back"  type="button" dwzClass="close"/></li>
			</ul> 
	</div>
</form>
</div>

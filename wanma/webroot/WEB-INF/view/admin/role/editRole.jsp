<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>
<h2 class="contentTitle">角色编辑</h2>
<div class="pageContent">
<form id="editRoleForm" method="post" action="role/modifyRole.do" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone)">
	<input type="hidden" name="roleId" value="${role.roleId}" />
	<input type="hidden" id="menuIds" name="menuIds" value="${role.roleId}" />
	<div class="pageFormContent nowrap" layoutH="97" >
			<dl>
				<dt>角色名称：</dt>
				<dd>
					<input name="roleName" value="${role.roleName}" type="text" class="required normal" size="25"
						maxlength="20" remote="role/checkUnique.do?roleId=${role.roleId}" />
				</dd>
			</dl>
			<dl>
				<dt>角色类别：</dt>
				<dd>
					<select name="category" class="required">
						<c:if test="${userLevel<=2}">
							<option value="2" ${role.category=='2' ? 'selected="selected"' : ''}>普通管理员</option>
							<option value="3" ${role.category=='3' ? 'selected="selected"' : ''}>纯商家</option>
						</c:if>
						<c:if test="${userLevel==3}">
							<option value="4" ${role.category=='4' ? 'selected="selected"' : ''}>商家子角色</option>
						</c:if>
						<c:if test="${userLevel==5||userLevel<=2}">
							<option value="5" ${role.category=='5' ? 'selected="selected"' : ''}>个体商家</option>
						</c:if>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>权限分配：</dt>
				<dd>
					<ul id="treeDemo" class="ztree"></ul> 
				</dd>
			</dl>
	</div>
	<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit" type="button" onclick="submitRoleEditForm()"/></li>
				<li><bmtag:button messageKey="common.button.back"  type="button" dwzClass="close"/></li>
			</ul> 
	</div>
</form>
</div>
<script type="text/javascript">
var setting = {
		check: {
			enable: true,
			chkboxType: { "Y" : "s", "N" : "s" }
		},
		data: {
			simpleData: {
				enable: true
			}
		}
	};

	var zNodes =${menuData};
	$(document).ready(function(){
		$.fn.zTree.init(navTab.getCurrentPanel().find("#treeDemo"), setting, zNodes);
	});
function submitRoleEditForm(){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes =  treeObj.getCheckedNodes(true);
	var menuIds="";
	for(var i=0;i<nodes.length;i++){
		menuIds+=nodes[i].id+",";
	}
	$("#menuIds").val(menuIds.substring(0,menuIds.length-1));
	validateCallback(editRoleForm, navTabAjaxDone)
}	
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json) {
	}
</script>

<div class="pageContent"
	style="border-left: 1px #B8D0D6 solid; border-right: 1px #B8D0D6 solid">
	<div class="panelBar">
		<ul class="toolBar">
			<li><bmtag:link href="common/departmentSelectList.do?backRel=menuDepartmentList&processType=menuDept" target="dialog"
					rel="selMenuDepartment" messageKey="common.icon.new"
					dwzClass="add" id="addMenuDeptLink"/></li>
			<li><bmtag:link
					href="menu/removeMenuDept.do?departmentIds={departmentIds}"
					target="ajax" rel="menuDepartmentList" id="delMenuDeptLink"
					altKey="common.msg.delete.confirm"
					messageKey="common.icon.delete" dwzClass="delete" /></li>
		</ul>
	</div>
	<div  id="menuDepartmentList">
	<table class="table" width="100%"  layoutH="310" >
		<thead>
			<tr>
				<th align="center"><bmtag:message messageKey="common.label.index" /></th>
				<th align="center"><bmtag:message messageKey="company.label.company_name" /></th>
				<th align="center"><bmtag:message messageKey="department.label.department_id" /></th>
				<th align="center"><bmtag:message messageKey="department.label.department_name" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${menuModel.deptList}" var="department" varStatus="status">
				<tr target="departmentIds" rel="${department.companyId},${department.departmentId}" align="center">
					<td align="center">${ status.index + 1}</td>
					<td align="center">${department.companyName}</td>
					<td align="center">${department.departmentId}</td>
					<td align="center">${department.departmentName}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<div class="panelBar">
	</div>
</div>

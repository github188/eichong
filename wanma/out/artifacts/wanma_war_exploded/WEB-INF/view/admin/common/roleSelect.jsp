<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<script type="text/javascript">

	function ajaxDoneCallback(json){
	}
</script>
<div class="pageHeader"> 
<form id="pagerForm" rel="pagerForm" method="post" action="common/roleSelectList.do" onsubmit="return dwzSearch(this, 'dialog');"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<input type="hidden" name="backRel" value="${backRel}"/>
	<input type="hidden" name="processType" value="${processType}"/>
	<div class="searchBar">
		<table class="searchContent">
		<tbody>
			<tr>
				<td style="align:left">
					<bmtag:message messageKey="role.label.role_id"/>
				</td>
				<td>
					<input name="roleId" alt="" value="${roleModel.roleId}" style="width:120px"/>
				</td>
				<td>
					<bmtag:message messageKey="role.label.role_name"/>
				</td>
				<td>
					<input name="roleName" alt="" value="${roleModel.roleName}" style="width:120px"/>
				</td>
				<td>
				</td>
				<td align="right">
					<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter"/>
				</td>
			</tr>
		</tbody>
		</table>
	</div>
</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><bmtag:link href="common/roleSelect.do?roleId={addRoleId}&processType=${processType}"
				target="ajax" rel="${backRel}" messageKey="common.icon.confirm" dwzClass="add"/></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="113">
		<thead>
		<tr><th><bmtag:message messageKey="common.label.index"/></th>
		       <th><bmtag:message messageKey="role.label.role_id"/></th>
		       <th><bmtag:message messageKey="role.label.role_name"/></th>
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${roleSelectList}" var="role" varStatus="status">
			<tr target="addRoleId" rel="${role.roleId}" align="center">
				<%-- <td>${ status.index + 1}</td> --%>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td align="center">${role.roleId }</td>
				<td align="center">${role.roleName }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>
			<select class="combox" name="numPerPage" onchange="dialogPageBreak({numPerPage:this.value})">
				<option value="4" ${pager.numPerPage == 4?"selected":""}>4</option> 
				<option value="20" ${pager.numPerPage == 20?"selected":""}>20</option> 
				<option value="100" ${pager.numPerPage == 100?"selected":""}>100</option> 
				<option value="200" ${pager.numPerPage == 200?"selected":""}>200</option> 
			</select>
			共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="dialog" totalCount="${pager.total}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>

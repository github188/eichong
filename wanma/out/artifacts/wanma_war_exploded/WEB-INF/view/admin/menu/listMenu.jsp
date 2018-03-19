<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<script type="text/javascript">
	var webroot = "${webroot}";
</script>
<div class="pageHeader"> 
<form id="pagerForm" method="post" action="menu/findMenuList.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	
	<div class="searchBar">
		<table class="searchContent">
		<tbody>
			<tr>	
				<td style="align:left">
					<label style="align:left"><bmtag:message messageKey="菜单名称"/></label>
				</td>
				<td>
					<input type="text" name="contents" value="${menu.contents}">
				</td>
				<td style="align:left">
					<label style="align:left"><bmtag:message messageKey="菜单链接"/></label>
				</td>
				<td>
					<input type="text" name="url" value="${menu.url}">
				</td>
				<td align="right">
					<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter"/>
				</td>
			</tr>
			<tr>	
				<td style="align:left">
					<label style="align:left"><bmtag:message messageKey="菜单类别"/></label>
				</td>
				<td >
					<select  name="menuType" style="width:130px;">
						<option value="">--请选择--</option>
						<option value="1" ${menu.menuType==1 ? 'selected="selected"' : ''}>菜单权限</option>
						<option value="2" ${menu.menuType==2 ? 'selected="selected"' : ''}>按钮权限</option>
					</select>
				</td>
				<td style="align:left">
					<label style="align:left"><bmtag:message messageKey="上级菜单"/></label>
				</td>
				<td>
					<input name="org.id" value="${menu.parentMenuId}" type="hidden"/>
					<input name="org.name" value="${empty menu.parentMenuId?"根菜单":menu.parentMenuName}" type="text" class="required normal" readonly />
					<a class="btnLook" style="float:right;" href="menu/getMenuTreeLookup.do" width="400" lookupGroup="org">查找带回</a>	
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
			<li>
				<bmtag:link isAuth="true"  target="navTab" href="menu/newMenu.do" 
					rel="menuAdd" messageKey="common.icon.new" dwzClass="add" />
			</li>
			<li>
				<bmtag:link isAuth="true" target="navTab" href="menu/editMenu.do?menuId={menuId}"
					rel="roleEditPage" messageKey="common.icon.edit" dwzClass="edit" />
			</li>
			<li>
				<bmtag:link isAuth="true" target="selectedTodo" href="menu/removeMenu.do" rel="ids"
					altKey="common.msg.delete.confirm" messageKey="批量删除" dwzClass="delete" />
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="155">
		<thead>
		<tr>
			<th width="10"><input type="checkbox" group="ids"
					class="checkboxCtrl" />
				</th>
			<th><bmtag:message messageKey="序号"/></th>
			<th><bmtag:message messageKey="菜单ID"/></th>
			<th><bmtag:message messageKey="菜单名称"/></th>
			<th><bmtag:message messageKey="菜单链接"/></th>
			<th><bmtag:message messageKey="上级菜单"/></th>
			<th><bmtag:message messageKey="菜单类别"/></th>
			<th><bmtag:message messageKey="菜单排序"/></th>
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${menuList}" var="menu" varStatus="status">
			<tr target="menuId" rel="${menu.menuId }" align="center">
				<td><input name="ids"  value="${menu.menuId }"
						type="checkbox" />
				</td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${menu.menuId }</td>
				<td>${menu.contents }</td>
				<td>${menu.url }</td>
				<td>${empty menu.parentMenuId?"根菜单":menu.parentMenuName}</td>
				<td>
					<c:choose>
						<c:when test="${menu.menuType=='1'}">菜单权限</c:when>
						<c:when test="${menu.menuType=='2'}">按钮权限</c:when>
					</c:choose>
				</td>
				<td>${menu.sortNumber }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>
			<!-- <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="4" ${pager.numPerPage == 4?"selected":""}>4</option> 
				<option value="20" ${pager.numPerPage == 20?"selected":""}>20</option> 
				<option value="100" ${pager.numPerPage == 100?"selected":""}>100</option> 
				<option value="200" ${pager.numPerPage == 200?"selected":""}>200</option> 
			</select> -->
			共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${pager.total}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>

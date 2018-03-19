<%@page import="com.wanma.model.TblUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/user/user-list.js" />
<script type="text/javascript">
$(function() {
	var userIdForShow="<%=((TblUser)session.getAttribute("user")).getUserId()%>";
	if(userIdForShow==8231){
	$(".pages").html("");
}
});
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
</script>
<div class="pageHeader"> 
<form id="pagerForm" method="post" action="userManager/unitShopList.do?userLevel=5" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<div class="searchBar">
		<table class="searchContent">
		<tbody>
			<tr>
				<td>
					<span>用户姓名</span>
					<input name="normRealName" placeholder="请输入" value="${user.normRealName }"/>
				</td>
				<td>
					<span>手机账号</span>
					<input name="userAccount" placeholder="请输入" value="${user.userAccount }"/>
				</td>
				<td>
					<span>邮箱</span>
					<input name="normEmail" placeholder="请输入" value="${user.normEmail }"/>
				</td>
			</tr>
			<tr>
				<td>
					<span>品牌/车型</span>
					<select  name="normCarCompanyId" ref="unbrand" onchange="getCarTypeName(this,'unitCartype')" style="width:136px;">
						<option value="">--请选择品牌--</option>
						<c:forEach items="${brandList}" var="brand">
							<option value="${brand.pk_carCompany}" ${brand.pk_carCompany==user.normCarCompanyId ? 'selected="selected"' : ''}>${brand.carCompany_Name}</option>
						</c:forEach>
					</select>
					<select  name="normCarTypeId" id="unitCartype" style="width:136px;">
						<option value="">--请选择车型--</option>
						<c:if test="${carTypeList!=null}">
						<c:forEach items="${carTypeList}" var="carType">
							<option value="${carType.pkCarinfo}" ${carType.pkCarinfo==user.normCarTypeId ? 'selected="selected"' : ''}>${carType.carinfoStylename}</option>
						</c:forEach>
						</c:if>
					</select>
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
			<li>
				<bmtag:link isAuth="true" target="navTab"  href="userManager/newUnit.do"  
					rel="unitAddPage" messageKey="common.icon.new" dwzClass="add"  id="addLink"/>
			</li>
			<li>
				<bmtag:link isAuth="true" target="navTab" href="userManager/editUnit.do?userId={pkUserinfo}&userLevel=5"
					rel="unitEditPage" messageKey="common.icon.edit" dwzClass="edit" />
			</li>
			<li>
				<bmtag:link isAuth="true" target="selectedTodo" href="userManager/removeUnit.do" rel="pkUserinfos"
					 altKey="common.msg.delete.confirm" messageKey="批量删除" dwzClass="delete" />
			</li>			
			<li>
				<bmtag:link isAuth="true" target="navTab"  href="userManager/viewUnit.do?userId={pkUserinfo}&userLevel=5"
					rel="unitViewPage" messageKey="common.icon.view" dwzClass="icon"/>
			</li>
			<li>
				<bmtag:link isAuth="true" href="userManager/frostAllList.do?rel=unitShopList" 
					target="selectedTodo" altKey="确定要 冻结吗？" rel="pkUserinfos"
					 postType="string" messageKey="冻结/批量冻结" dwzClass="edit" />
			</li>
			<li>
				<bmtag:link isAuth="true" href="userManager/unFrost.do?rel=unitShopList" 
					target="selectedTodo" altKey="确定要 解冻吗？" rel="pkUserinfos"
					 postType="string" messageKey="解冻/批量解冻" dwzClass="edit" />
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
		<tr align="center">
			<th width="10"><input type="checkbox" group="pkUserinfos"
					class="checkboxCtrl" /></th>
			<th><bmtag:message messageKey="序号"/></th>
		    <th><bmtag:message messageKey="姓名"/></th>
		    <th><bmtag:message messageKey="手机号"/></th>
		    <%-- <th><bmtag:message messageKey="用户类型"/></th> --%>
		    <th><bmtag:message messageKey="邮箱"/></th>
		    <th><bmtag:message messageKey="联系地址"/></th>
		    <th><bmtag:message messageKey="品牌"/></th>
		    <th><bmtag:message messageKey="车型"/></th>
		     <th><bmtag:message messageKey="升级时间"/></th>
		    <th><bmtag:message messageKey="状态"/>
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${unitList}" var="unit" varStatus="status">
			<tr target="pkUserinfo" rel="${unit.userId }" align="center">
				<td>
					<input name="pkUserinfos"  value="${unit.userId}"
						type="checkbox" />
				</td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${empty unit.normRealName?unit.userAccount:unit.normRealName}</td>
				<td>${unit.userAccount }</td>
				<td>${unit.normEmail }</td>
				<td title="${unit.normAddress }">${unit.normAddress }</td>
				<td>${unit.normCarCompany }</td>
				<td>${unit.normCarType }</td>
				<td>
					<fmt:formatDate value="${unit.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:if test="${unit.userStatus == 1}">正常</c:if>
					<c:if test="${unit.userStatus == 2}">冻结</c:if>
				</td>
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

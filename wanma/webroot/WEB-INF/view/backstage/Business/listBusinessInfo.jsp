<%@page import="com.wanma.model.TblUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
</script>
<div class="pageHeader"> 
<form id="pagerForm" method="post" action="userManager/ShopList.do?userLevel=3" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<div class="searchBar">
		<table class="searchContent">
		<tbody>
			<tr>
				<td>
					<span>账号</span>
					<input name="userAccount"  value="${tblPureBusiness.userAccount}"/>
				</td>
				<td>
					<span>所属企业</span>
					<input name="busiCompany"  value="${tblPureBusiness.busiCompany}"/>
				</td>
				<td>
					<span>角色</span>
					<select name="busiParentId"  style="width: 135px !important" class="select_Style" >
						 <option value="">全部</option>
						 <option value="0" ${tblPureBusiness.busiParentId==0?'selected':''} >纯商家</option>
						 <option value="1" ${tblPureBusiness.busiParentId==1?'selected':''}>子商家</option>
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
			<li><bmtag:link isAuth="true" target="navTab" href="userManager/newBusiness.do"  
				 rel="businessAddPage" messageKey="common.icon.new" dwzClass="add"  id="addLink"/>
			</li>
			<li>
				<bmtag:link isAuth="true" target="navTab"  href="userManager/editBusiness.do?userId={businessId}&userLevel=3"
					 rel="businessEditPage" messageKey="common.icon.edit" dwzClass="edit" />
			</li>
			<li>
				<bmtag:link isAuth="true" target="selectedTodo" href="userManager/removeBusiness.do" rel="pkUserinfos"
					 altKey="common.msg.delete.confirm" messageKey="批量删除" dwzClass="delete" />
			</li>
			<li>
				<bmtag:link isAuth="true" target="ajaxTodo" href="user/resetPassword.do?userId={businessId}"
					 messageKey="common.icon.reset.password" dwzClass="edit" />
			</li>		
			<li>
				<bmtag:link isAuth="true" target="navTab" href="userManager/viewBusiness.do?userId={businessId}&userLevel=3"
					 rel="unitViewPage" messageKey="common.icon.view" dwzClass="icon"/>
			</li>
			<li>
				<bmtag:link isAuth="true" href="userManager/frostAllList.do?rel=ShopList" 
					target="selectedTodo" altKey="确定要 冻结吗？" rel="pkUserinfos"
					 postType="string" messageKey="冻结/批量冻结" dwzClass="edit" />
			</li>
			<li>
				<bmtag:link isAuth="true" href="userManager/unFrost.do?rel=ShopList" 
					target="selectedTodo" altKey="确定要 解冻吗？" rel="pkUserinfos"
					 postType="string" messageKey="解冻/批量解冻" dwzClass="edit" />
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="113">
		<thead>
		<tr>
			<th width="10"><input type="checkbox" group="pkUserinfos"
					class="checkboxCtrl" /></th>
			<th align="center"><bmtag:message messageKey="账号"/></th>
			<th align="center"><bmtag:message messageKey="姓名"/></th>
			<th align="center"><bmtag:message messageKey="手机"/></th>
		    <th align="center"><bmtag:message messageKey="所属企业"/></th>
		    <th align="center"><bmtag:message messageKey="角色"/></th>
		    <th align="center"><bmtag:message messageKey="状态"/></th>
		    <th align="center"><bmtag:message messageKey="创建时间"/></th>
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${businessList}" var="business" varStatus="status">
			<tr target="businessId" rel="${business.userId }" align="center">
				<td>
					<input name="pkUserinfos"  value="${business.userId}"
						type="checkbox" />
				</td>
				<td>${business.userAccount}</td>
				<td>${business.busiName}</td>
				<td>${business.busiPhone}</td>
				<td>${business.busiCompany}</td>
				<td>
					<c:if test="${business.busiParentId == 0}">纯商家</c:if>
					<c:if test="${business.busiParentId != 0}">子商家</c:if>
				</td>
				<td>
					<c:if test="${business.userStatus == 1}">正常</c:if>
					<c:if test="${business.userStatus == 2}">冻结</c:if>
				</td>
				<td>
					<fmt:formatDate value="${business.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
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
<script type="text/javascript">
$(function() {
	var userIdForShow="<%=((TblUser)session.getAttribute("user")).getUserId()%>";
if(userIdForShow==8231){
	$(".pages").html("");
}
});
</script>
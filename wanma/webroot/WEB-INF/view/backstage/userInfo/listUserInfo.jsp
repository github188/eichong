<%@page import="com.wanma.model.TblUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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


<form id="pagerForm" method="post" action="userManager/userManagerList.do?userLevel=6" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<div class="searchBar">
		<table class="searchContent">
		<tbody>
			<tr>
				<td>
					<span>姓名</span>
					<input name="normRealName" placeholder="请输入" value="${user.normRealName}"/>
				</td>
				<td>
					<span>手机</span>
					<input name="userAccount" placeholder="请输入" value="${user.userAccount}"/>
				</td>
				<td align="right">
					<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter"/>
				</td>
			</tr>
			<tr>
				<td>
					<span>品牌/车型</span>
					<select  name="normCarCompanyId" onchange="getCarTypeName(this,'userSearchCarinfo')" style="width:130px;">
						<option value="0">--请选择品牌--</option>
						<c:forEach items="${brandList}" var="brand">
							<option value="${brand.pk_carCompany}" ${brand.pk_carCompany==user.normCarCompanyId ? 'selected="selected"' : ''}>${brand.carCompany_Name}</option>
						</c:forEach>
					</select>
					<select id="userSearchCarinfo"  name="normCarTypeId"  style="width:136px;">
						<option value="0">--请选择车型--</option>
						<c:if test="${carTypeList!=null}">
						<c:forEach items="${carTypeList}" var="carType">
							<option value="${carType.pkCarinfo}" ${carType.pkCarinfo==user.normCarTypeId ? 'selected="selected"' : ''}>${carType.carinfoStylename}</option>
						</c:forEach>
						</c:if>
					</select>
				</td>
				<td>
					<span>注册来源</span>
					<select name="normRegisteType"  style="width:108px">
						<option value="">全部</option>
						<option value="1" ${user.normRegisteType == 1?"selected":""}>管理后台</option>
						<option value="2" ${user.normRegisteType == 2?"selected":""}>Web</option>
						<option value="3" ${user.normRegisteType == 3?"selected":""}>android</option>
						<option value="4" ${user.normRegisteType == 4?"selected":""}>ios</option>
					</select>
				</td>
				
		            <td style="padding-right: 0 !important;"><span>注册时间</span> <input id="startDate_f6"
							name="createTime" placeholder="请选择"
							value="${user.createTime}" class="date"
							onClick="WdatePicker({el:'startDate_f6',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'endDate_f6\')}'})">
						</td><td>
						<span>至</span>	&nbsp;&nbsp;&nbsp;&nbsp;
							<input id="endDate_f6" name="endTime"
							value="${user.endTime}" class="date"
							placeholder="请选择"
							onClick="WdatePicker({el:'endDate_f6',minDate:'#F{$dp.$D(\'startDate_f6\')}'})">
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
				<bmtag:link isAuth="true" href="userManager/newUser.do"  
					target="navTab" rel="userAddPage" messageKey="common.icon.new" dwzClass="add" id="addLink"/>
			</li>
			<%-- <li>
				<bmtag:link  isAuth="true" href="userManager/removeUser.do?userId={pkUserinfo}&userLevel=6"
					target="ajaxTodo" altKey="确定要删除选中的记录吗?"
					messageKey="common.icon.delete" dwzClass="delete" />
			</li> --%>
			<li>
				<bmtag:link isAuth="true" href="${webroot}/admin/userManager/removeAll.do" 
					target="selectedTodo" altKey="确定要删除选中的记录吗?" rel="pkUserinfos"
					 postType="string" messageKey="删除" dwzClass="delete" />
			</li>
			<li>
				<bmtag:link isAuth="true" href="userManager/editUser.do?pkUserinfo={pkUserinfo}"
					target="navTab" rel="userEditPage" messageKey="common.icon.edit" dwzClass="edit" />
			</li>	
			<li>
				<bmtag:link isAuth="true" href="userManager/upgradeUser.do?userId={pkUserinfo}&userLevel=6"
					target="ajaxTodo" altKey="确定要提升为个体商家吗？" messageKey="升级为个体商家" dwzClass="edit" />
			</li>
			<li>
				<bmtag:link isAuth="true" href="userManager/approvalAllList.do?userLevel=5" 
					target="selectedTodo" altKey="确定要批量审批吗？" rel="pkUserinfos"
					 postType="string" messageKey="审批/批量审批" dwzClass="edit" />
			</li>
			<li>
				<bmtag:link isAuth="true" href="userManager/frostAllList.do?rel=userManagerList&userlevel=6" 
					target="selectedTodo" altKey="确定要 冻结吗？" rel="pkUserinfos"
					 postType="string" messageKey="冻结/批量冻结" dwzClass="edit" />
			</li>
			<li>
				<bmtag:link isAuth="true" href="userManager/unFrost.do?rel=userManagerList" 
					target="selectedTodo" altKey="确定要 解冻吗？" rel="pkUserinfos"
					 postType="string" messageKey="解冻/批量解冻" dwzClass="edit" />
			</li>
			<li>
				<bmtag:link isAuth="true" href="userManager/cardListLookup.do?userId={pkUserinfo}"
					target="dialog"  messageKey="绑定" dwzClass="edit" />
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="139">
		<thead>
		<tr align="center" >
			<th width="10"><input type="checkbox" group="pkUserinfos"
					class="checkboxCtrl" /></th>
			<th><bmtag:message messageKey="序号"/></th>
		    <th><bmtag:message messageKey="用户姓名"/></th>
		    <th><bmtag:message messageKey="手机号"/></th>
		    <%-- <th><bmtag:message messageKey="用户类型"/></th> --%>
		    <th><bmtag:message messageKey="注册来源"/></th>
		    <th><bmtag:message messageKey="邮箱"/></th>
		    <th><bmtag:message messageKey="注册时间"/></th>
		    <th width="50"><bmtag:message messageKey="联系地址"/></th>
		    <th><bmtag:message messageKey="品牌"/></th>
		    <th><bmtag:message messageKey="车型"/></th>
		    <th><bmtag:message messageKey="状态"/></th>
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${userList}" var="user" varStatus="status">
			<tr target="pkUserinfo" rel="${user.userId }" align="center">
				<td>
					<input name="pkUserinfos"  value="${user.userId}"
						type="checkbox" />
				</td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${empty user.normRealName?user.userAccount:user.normRealName}</td>
				<td>${user.userAccount }</td>
				<td>
				<c:if test="${user.normRegisteType == 0}">---</c:if>
				<c:if test="${user.normRegisteType == 1}">管理后台</c:if>
				<c:if test="${user.normRegisteType == 2}">Web</c:if>
				<c:if test="${user.normRegisteType == 3}">android</c:if>
				<c:if test="${user.normRegisteType == 4}">ios</c:if>
				</td>
				<td>${user.normEmail }</td>
				<td>
					<fmt:formatDate value="${user.createDate }" pattern="yyyy-MM-dd"/>
				</td>
				<td title="${user.normAddress }">${user.normAddress }</td>
				<td>${user.normCarCompany }</td>
				<td><c:if test="${user.normCarCompany != null}">${user.normCarType }</c:if></td>
				<td>
					<c:choose>
						<c:when test="${user.userStatus == '1'}">
							<c:if test="${user.normStatus == '2'}">
								个体商家待审批
							</c:if>
							<c:if test="${user.normStatus == '3'}">
								已审批通过
							</c:if>
							<c:if test="${user.normStatus == '1'}">
								正常
							</c:if>
						</c:when>
						<c:when test="${user.userStatus == '2'}">
							冻结
						</c:when>
					</c:choose>
				</td>
				<%-- <td>
					<c:choose>
						<c:when test="${user.usinUserstatus == '1'}">
							<a target="ajaxTodo" href="<c:url value='/admin/userManager/approvalUserList.do?pkUserinfo=${user.pkUserinfo}&usinUserstatus=4'/>" title="确定要处理吗？" style='color:#0099FF'>升级个体商家</a>
						</c:when>
						<c:when test="${user.usinUserstatus == '4'}">
							<a target="ajaxTodo" href="<c:url value='/admin/userManager/approvalUserList.do?pkUserinfo=${user.pkUserinfo}&usinUserstatus=5'/>" title="确定要处理吗？" style='color:#0099FF'>审批通过</a>
						</c:when>
					</c:choose> 
					<c:choose>
						<c:when test="${user.userStatus == '2'}">
							<a target="ajaxTodo" href="<c:url value='/admin/userManager/frostUserList.do?userId=${user.userId}&userStatus=1&userLevel=6'/>" title="确定要处理吗？" style='color:#0099FF'>解除冻结</a>
						</c:when>
					</c:choose>
				</td> --%>
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

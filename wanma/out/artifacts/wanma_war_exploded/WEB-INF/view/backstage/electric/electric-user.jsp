<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
</script>
<style type="text/css">
.binddiv {
	margin-left: -120px; /*Chrome、Safari、Firefox、Opera */
	margin-left: -70px; /* Firefox */
}

.bindfont {
	font-size: 5px; /*Chrome、Safari、Firefox、Opera */
	font-size: 13px; /* Firefox */
}
</style>

<div class="pageHeader">
	<form id="pagerForm" method="post" action="electric/userSelectList.do"
		onsubmit="return dwzSearch(this, 'dialog');">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>用户名称:</label> <input class="textInput"
					name="userName" value="${userModel.userName}" type="text">
				</li>
				<li><label>用户级别:</label> <select name="userLevel"
					class="select_Style">
						<option value="">全部</option>
						<option value="1" ${userModel.userLevel==1 ? 'selected="selected"' : ''}>系统管理员</option>
						<option value="2" ${userModel.userLevel==2 ? 'selected="selected"' : ''}>商家用户</option>
						<option value="3" ${userModel.userLevel==3 ? 'selected="selected"' : ''}>个体商家用户</option>
				</select></li>
			</ul>
			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">查询</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th>用户名称</th>
				<th>用户级别</th>
				<th width="80">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userSelectList}" var="user" varStatus="status">
				<tr>
					<td>${user.userName}</td>
					<td><c:if test="${user.userLevel==0}">超级管理员</c:if> <c:if
							test="${user.userLevel==1}">系统管理员</c:if> <c:if
							test="${user.userLevel==2}">商家用户</c:if> <c:if
							test="${user.userLevel==3}">个体商家用户</c:if></td>
					<td><c:if test="${user.userLevel==0 || user.userLevel==1}">
							<a class="btnSelect"
								href="javascript:$.bringBack({elPiUserName:'${user.userName}',elPiOwner:'${acwCompanyName}',userLevel:${user.userLevel}})"
								title="查找带回">选择</a>
						</c:if> <c:if test="${user.userLevel==2}">
							<a class="btnSelect"
								href="javascript:$.bringBack({elPiUserName:'${user.userName}',elPiOwner:'${user.userName}',userLevel:${user.userLevel}})"
								title="查找带回">选择</a>
						</c:if> <c:if test="${user.userLevel==3}">
							<a class="btnSelect"
								href="javascript:$.bringBack({elPiUserName:'${user.userName}',elPiOwner:'${user.userId}',userLevel:${user.userLevel}})"
								title="查找带回">选择</a>
						</c:if></td>
				</tr>
			</c:forEach>

		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span> <select class="combox" name="numPerPage"
				onchange="dialogPageBreak({numPerPage:this.value})">
					<option value="4" ${pager.numPerPage==4?"selected":""}>4</option>
					<option value="20" ${pager.numPerPage==20?"selected":""}>20</option>
					<option value="100" ${pager.numPerPage==100?"selected":""}>100</option>
					<option value="200" ${pager.numPerPage==200?"selected":""}>200</option>
			</select> 共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="dialog"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
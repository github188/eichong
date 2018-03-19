<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
 <link href="<%=request.getContextPath()%>/res/commen.css" rel="stylesheet"/>
<script type="text/javascript">
	function ajaxDoneCallback(json){
	}
</script>
<div class="pageHeader"> 
<form id="pagerForm" rel="pagerForm" method="post" action="common/userSelectList.do" onsubmit="return dwzSearch(this, 'dialog');"> 
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
					<bmtag:message messageKey="user.label.user_id"/>
				</td>
				<td>
					<input name="userAccount" alt="" value="${userModel.userAccount}" style="width:120px"/>
				</td>
				<td></td>
				<%-- <c:if test="${isSuperUser!=true}"><input type="hidden" name="userName" alt="" value="${userModel.userName}" style="width:120px"/>
				<input type="hidden" name="userId" alt="" value="${userModel.userId}" style="width:120px"/></c:if>
				<c:if test="${isSuperUser==true}">
				<td>
					<bmtag:message messageKey="user.label.user_name"/>
				</td>
				<td>
					<input name="userName" alt="" value="${userModel.userName}" style="width:120px"/>
				</td>
				</c:if> 
				<c:if test="${isSuperUser!=true}">
					<td align="right">
						<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter"/>
					</td>	
				</c:if>--%>
			</tr>
			<c:if test="${isSuperUser==true}">
			<tr>
				 <td>
					<bmtag:message messageKey="user.label.userType"/>
				</td>
				<td><input type="hidden" name="userLevel" value='${userModel.userLevel}' />
					<select class="select_Style" style="width:125px;" disabled="disabled">
						<option value="1" ${userModel.userLevel==1 ? 'selected="selected"' : ''}>管理员</option>
						<option value="2" ${userModel.userLevel==3 ? 'selected="selected"' : ''}>纯商家用户</option>
						<option value="3" ${userModel.userLevel==5 ? 'selected="selected"' : ''}>个体商家用户</option>
					</select>
				</td> 
			 <td></td> 
				<td align="right">
					<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter"/>
				</td>			
			</tr>
			</c:if>
		</tbody>
		</table>
	</div>
</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><bmtag:link href="common/userSelect.do?userId={addUserId}&processType=${processType}&userLevel=${userModel.userLevel}"
				target="ajax" rel="${backRel}" messageKey="common.icon.confirm" dwzClass="add"/></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="133" targetType="dialog">
		<thead>
		<tr><%-- <th><bmtag:message messageKey="common.label.index"/></th> --%>
		       <th align="center"><bmtag:message messageKey="账户id"/></th>
		       <th align="center"><bmtag:message messageKey="账户归属"/></th>
		       <th align="center"><bmtag:message messageKey="账户级别"/></th>
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${userSelectList}" var="user" varStatus="status">
			<tr target="addUserId" rel="${user.userId }" align="center">
				<%-- <td align="center">${ status.index + 1}</td> --%>
				<td align="center">${user.userId }</td>
				<td align="center">${user.userAccount }</td>
				<td align="center">
					<c:choose>
						<c:when test="${user.userLevel == 1}">超级管理员</c:when>
						<c:when test="${user.userLevel == 2}">普通管理员</c:when>
						<c:when test="${user.userLevel == 3}">纯商家用户</c:when>
						<c:when test="${user.userLevel == 5}">个体商家用户</c:when>
					</c:choose>
				</td>
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

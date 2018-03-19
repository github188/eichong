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

<div class="pageContent">
	<form id="pagerForm" method="post" action="partner/partnerList.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
				<bmtag:link isAuth="true" href="partner/newPartner.do"  
					target="navTab" rel="userAddPage" messageKey="新增密钥" dwzClass="add" id="addLink"/>
			</li>
			<li>
				<bmtag:link isAuth="true" href="partner/resetPartnerKey.do" 
					target="selectedTodo" altKey="确定要重置密钥吗？" rel="partnerId"
					 postType="string" messageKey="重置密钥" dwzClass="edit" />
			</li>
			<li>
				<bmtag:link isAuth="true" href="partner/editPartner.do?partnerId={partnerId}"
					target="navTab" rel="userEditPage" messageKey="common.icon.edit" dwzClass="edit" />
			</li>	
			<li>
				<bmtag:link isAuth="true" href="partner/deletePartner.do" 
					target="selectedTodo" altKey="确定要删除选中的记录吗?" rel="partnerId"
					 postType="string" messageKey="删除" dwzClass="delete" />
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="75">
		<thead>
			<tr align="center" >
					<th width="10"><input type="checkbox" group="pkUserinfos" class="checkboxCtrl" /></th>
					<th><bmtag:message messageKey="序号"/></th>
				    <th><bmtag:message messageKey="企业名字"/></th>
				    <th><bmtag:message messageKey="用户身份标识"/></th>
				    <th><bmtag:message messageKey="用户密钥"/></th>
				    <th><bmtag:message messageKey="付费类型"/></th>
	        </tr>
		</thead>
		<tbody>
		<c:forEach items="${partnerList}" var="partner" varStatus="status">
			<tr target="partnerId" rel="${partner.partnerId }" align="center">
				<td>
					<input name="partnerIds"  value="${partner.partnerId}"
						type="checkbox" />
				</td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${partner.partnerName}</td>
				<td>${partner.partnerKey }</td>
				<td>${partner.partnerToken }</td>
				<td>
					<c:if test="${partner.paymod ==1}" >
						先付费
					</c:if>
				    <c:if test="${partner.paymod ==2}" >
				    	后付费 
				    </c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>
			共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${pager.total}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
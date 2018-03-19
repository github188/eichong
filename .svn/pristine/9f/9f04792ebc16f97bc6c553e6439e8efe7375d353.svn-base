<%@page import="com.wanma.model.TblUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
<form id="pagerForm" method="post" action="order/subsInstall.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<div class="searchBar">
		<table class="searchContent">
		<tbody>
			<tr>
				<td>
					<span>订单编号</span>
					<input name="alorInstallationordercode" placeholder="请输入" value="${tblInsatall.alorInstallationordercode }"/>
				</td>
				<td>
					<span>订单状态</span>
					<select name="alorInstallationorderstatus" style="width:155px">
						<option value=""}>全部</option>
						<option value="1" ${tblInsatall.alorInstallationorderstatus == 1?"selected":""}>待安装</option>
						<option value="2" ${tblInsatall.alorInstallationorderstatus == 2?"selected":""}>安装成功</option>
						<option value="3" ${tblInsatall.alorInstallationorderstatus == 3?"selected":""}>完成操作</option>
					</select>
				</td>
				<td>
					<span>用户姓名</span>
					<input name="factNames" placeholder="请输入"  value="${tblInsatall.factNames }"/>
				</td>
				<td>
					<span>手机账号</span>
					<input name="alorTellogin" placeholder="请输入"  value="${tblInsatall.alorTellogin }"/>
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
	<table class="table" width="100%" layoutH="86">
		<thead>
		<tr>
			<th width="10"><input type="checkbox" group="pkAppointmentinstallationorders"
					class="checkboxCtrl" /></th>
			<th><bmtag:message messageKey="序号"/></th>
		    <th><bmtag:message messageKey="订单编号"/></th>
		   <%--  <th><bmtag:message messageKey="商品名称"/></th>
		    <th><bmtag:message messageKey="商品编号"/></th> --%>
		    <th><bmtag:message messageKey="安装数量"/></th>
		    <th><bmtag:message messageKey="用户姓名"/></th>
		    <th><bmtag:message messageKey="手机账号"/></th>
		    <th><bmtag:message messageKey="金额(元)"/></th>
		    <th><bmtag:message messageKey="订单状态"/></th>
		    <th><bmtag:message messageKey="操作"/></th>
		    <%-- <th><bmtag:message messageKey="购买时间"/></th>
		    <th><bmtag:message messageKey="安装地址"/></th>
		    <th><bmtag:message messageKey="联系人"/></th>
		    <th><bmtag:message messageKey="联系电话"/></th> --%>
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${installList}" var="install" varStatus="status">
			<tr target="pkAppointmentinstallationorder" rel="${install.pkAppointmentinstallationorder }" align="center">
				<td>
					<input name="pkAppointmentinstallationorders"  value="${install.pkAppointmentinstallationorder }"
						type="checkbox" />
				</td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${install.alorInstallationordercode }</td>
				<td>${install.alorCommoditytotal }</td>
				<%-- <td>${install.alorInstallationorderproductid }</td>
				<td>${install.alorInstallationorderproductname }</td> --%>
				<td>${install.factNames }<c:if test="${install.factNames==''||install.factNames==null}">${install.userPhone }</c:if></td>
				<td>${install.userPhone }</td>
				<td>${install.alorMoney }</td>
				<td><c:if test="${install.alorInstallationorderstatus==1}" >待安装</c:if>
				<c:if test="${install.alorInstallationorderstatus==2}" >安装成功</c:if>
				<c:if test="${install.alorInstallationorderstatus==3}" >操作完成</c:if></td>
				<td>
					<a target="navTab" rel="findInstalldetailList" href="<c:url value='/admin/order/findInstalldetailList.do?indeParentid=${install.pkAppointmentinstallationorder}'/>" title="订单详情" style='color:#0099FF'>订单详情</a>
				</td>
				<%-- <td>
					<fmt:formatDate value="${install.alorBuyingtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>${install.aiorInstallationaddress }</td>
				<td>${install.alorInstallationperson }</td>
				<td>${install.alorLnstallationtel }</td> --%>
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

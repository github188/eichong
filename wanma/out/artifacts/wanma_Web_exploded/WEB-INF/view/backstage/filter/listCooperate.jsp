<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="pageHeader">
	<form id="pagerForm" method="post" action="filter/listCooperate.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<%-- 	 <input type="hidden" name="companyId" value="${model.numPerPage}" /> --%>
	 	<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td>
					<span>合作商名称</span>
					<input name="companyName"  value="${Cooperate.companyName}"/>
				</td>

						<td align="right"><bmtag:button
								messageKey="common.button.search" type="submit"
								id="formSubmitter_cooprate" /></td>

					</tr>
				</tbody>
			</table>
		</div>

	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
		<li><bmtag:link isAuth="true" target="navTab"
					href="filter/addCooperate.do" rel="addCooperate"
					messageKey="common.icon.new" dwzClass="add" id="addCooperate" /></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="132">
		<thead>
			<tr align="center">
				<!-- <th><input type="checkbox" group="ids" class="checkboxCtrl">
				</th> -->
				<%-- <th><bmtag:message messageKey="编号" /></th> --%>
                <th><bmtag:message messageKey="合作商名称" /></th>
				<th><bmtag:message messageKey="公司标识" /></th>
				<th><bmtag:message messageKey="联营充电站数量" /></th>
			    <th><bmtag:message messageKey="联营充电桩数量" /></th>
				<th  align="center" ><bmtag:message messageKey="查看" /></th>
				<th  align="center" ><bmtag:message messageKey="删除" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${cooperateList}" var="item" varStatus="status">
				<tr target="id" rel="${item.pkCompanyId}" align="center">
					<%-- <td><input name="ids" value="${item.pkCompanyId}"
						type="checkbox"></td> --%>
					<!-- <td></td> -->
					<td  title="${item.companyName}">${item.companyName}</td>
					<td>${item.companyNumber}
					</td>
					<td>${item.powerStationNum}</td>
					<td>${item.pileNum}</td>
					
					<td>
					<a title="${item.companyName}" rel="${item.companyName}" target="navTab" href="filter/lookPowerStation.do?id=${item.pkCompanyId}&companyName=${item.companyName}" class="btnView">${item.companyName}</a>
                    </td>
                    <td>
						<a title="确定进行删除吗？" target="ajaxTodo" href="filter/deleteCooperate.do?id=${item.pkCompanyId}" class="btnDel">下架</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>

<script type="text/javascript">

	
</script>



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
<div class="pageHeader">
	<form id="pagerForm" method="post" action="carCompany/findCarCompanyList.do" onsubmit="return navTabSearch(this)">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> 
			<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">		
			<table class="searchContent">
				<tbody>
					<tr>	
						<td><label><bmtag:message
									messageKey="电动车厂家名称" /> </label>
						</td>					
						<td><input name="carCompany_Name"
							value="${params.carCompany_Name}" />
						</td>
						<td align="right"><bmtag:button
								messageKey="common.button.search" type="submit"
								id="formSubmitter" />
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
				<bmtag:link isAuth="true" target="navTab" href="carCompany/newCarCompany.do"
				   messageKey="common.icon.new" dwzClass="add"  />
			</li>
			<li>
				<bmtag:link isAuth="true" target="navTab" href="carCompany/editCarCompany.do?pk_carCompany={id}"
					messageKey="common.icon.edit" dwzClass="edit" />
			</li>
			<li>
				<bmtag:link isAuth="true" target="selectedTodo"  href="carCompany/deleteById.do" rel="ids"
					altKey="common.msg.delete.confirm" messageKey="批量删除" dwzClass="delete" />
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="110">
		<thead>
			<tr align="center">
				<th width="10"><input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th><bmtag:message messageKey="common.label.index" /></th>
				<th><bmtag:message messageKey="厂家名称" />
				</th>
				<th><bmtag:message messageKey="创建时间" />
				</th>
				<th><bmtag:message messageKey="备注" />
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${carCompanyList}" var="carCompanyinfo" varStatus="status">
				<tr target="id" rel="${carCompanyinfo.pk_carCompany}" align="center">
					<td><input name="ids" value="${carCompanyinfo.pk_carCompany}"
						type="checkbox">
					</td>
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td>${carCompanyinfo.carCompany_Name}</td>
					<td>${carCompanyinfo.carCompany_CreateDate}</td>
					<td>${carCompanyinfo.carCompany_Remark}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span style="display: none;"> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})" >
					<option value="4" ${pager.numPerPage==4?"selected":""}>4</option>
					<option value="20" ${pager.numPerPage==20?"selected":""}>20</option>
					<option value="100" ${pager.numPerPage==100?"selected":""}>100</option>
					<option value="200" ${pager.numPerPage==200?"selected":""}>200</option>
			</select></span><span> 共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>

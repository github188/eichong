<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="pageHeader">
<form id="pagerForm" method="post" action="finance/customerBillManage.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/> 
	<div class="searchBar">
		<table class="searchContent">
			<tbody>
				<tr>
				<!-- value="搜索关键字"  -->
		             <td style="align:left">
						<span>大客户名称</span>
						<input name="busiName" value="${params.busiName}" placeholder="大客户名称" />
					</td>           
					 <td style="align:left">
						<span>账户号</span>
						<input name="userAccount" value="${params.userAccount}" placeholder="账户号" />
					</td>                     
		             <td align="right">
						<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter_invoice"/>
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
		</ul>
	</div>
<table class="table" width="100%" layoutH="132">
		<thead>
			<tr align="center">
			    <th><bmtag:message messageKey="common.label.index" /></th>   
                <th><bmtag:message messageKey="大客户名称" />
				</th>								
				<th><bmtag:message messageKey="账户号" />
				</th>
				<th><bmtag:message messageKey="操作"/></th>
				
		</thead>
		<tbody>
			<c:forEach items="${billManageList}" var="customer" varStatus="status">
				<tr target="id" rel="${customer.userId}" align="center">
				    <td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td>${customer.busiName} </td>
                    <td >${customer.userAccount}</td>
					<td>
					<!--  <a href="#" onclick='toLooks("${customer.userId}")'  style = "color:blue;" >查看开票记录</a>-->
				     <a title="查看开票记录" rel="${customer.userId}" target="navTab" href="finance/invoiceRecord.do?userId=${customer.userId}" style = "color:blue;" >查看开票记录</a>
				 </td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
<script type="text/javascript">
  <%-- function toLooks(j){
	//var $this = $(obj);
	var id=j
	navTab.openTab("", basepath+"/admin/finance/invoiceRecord.do", { title:"查看开票记录", fresh:false, data:{userId:id}});
}--%>
var webroot = "${webroot}";
function checkDetails(){
}
</script>
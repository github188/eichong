<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript">
	var webroot = "${webroot}";
</script>
<div class="pageHeader">
<form id="pagerForm" method="post" action="finance/invoiceRecord.do?pkCompanyId=${pkCompanyId }" onsubmit="return navTabSearch(this);"> 
    <input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/> 
	<input type="hidden" name="userId" value="${userId}"/>  
	<div class="searchBar">
		<table class="searchContent">
			<tbody>
				<tr>
	            <!-- value="搜索关键字"  -->
					<td style="align:left">
						<span>名称</span>
						<input name="ivInvoiceName" value="${params.ivInvoiceName}" placeholder="开票名称" />
					</td>
					<td>
			            <span>开票时间&nbsp;</span>				             	 
			             <input id="createDates_f1" name="createDates" value="${params.createDates}" class="date"  style="width:155px"
						 onClick="WdatePicker({el:'createDates_f1',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'updateDates_f1\')}'})">
			                                    至 <input id="updateDates_f1" name="updateDates" value="${params.updateDates}" class="date"  style="width:155px"
			             onClick="WdatePicker({el:'updateDates_f1',minDate:'#F{$dp.$D(\'createDates_f1\')}'})">                       	            
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
				<li><bmtag:link isAuth="false" target="navTab"
					href="finance/BillOrder.do?userId=${userId }&flag=0" rel="userId"
					messageKey="添加开票记录" dwzClass="add"   />
			</li>  
			<li>		
				<bmtag:link isAuth="true" target="navTab" 
				    href="finance/changeInvoiceRecordUi.do?id={id}&userId=${userId}"
					 rel="userId" messageKey="common.icon.edit" dwzClass="edit" />
			</li>
			<li>
				<bmtag:link isAuth="true" target="navTab" href="finance/viewInvoiceRecord.do?id={id}"
					 rel="invoiceRecordview" messageKey="common.icon.view" dwzClass="icon" />
			</li>	
			<li><bmtag:link isAuth="true" target="selectedTodo"
					href="finance/removeInvoiceRecord.do?userId=${userId }" rel="ids" postType="string"
					altKey="确定删除吗？" dwzClass="delete" messageKey="删除" />
			</li>
			
			
		</ul>
	</div>
    <table class="table" width="100%" layoutH="132">
		<thead>
			<tr align="center">
				<th><input type="checkbox" group="ids" class="checkboxCtrl"></th>
                <th><bmtag:message messageKey="名称" /></th>
                <th><bmtag:message messageKey="优惠后电费" /></th>	
				<th><bmtag:message messageKey="优惠后服务费" /></th>
				<th><bmtag:message messageKey="发票类型"/></th>
				<th><bmtag:message messageKey="发票号码"/></th>
				<th><bmtag:message messageKey="开票时间"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${RecordList}" var="record" varStatus="status">
				<tr target="id" rel="${record.pkInvoice}" align="center">
					<td><input name="ids" value="${record.pkInvoice}" type="checkbox"></td>
                    <td>${record.InvoiceName }</td>
					<td>${record.DiscountChangMoney }</td>
					<td>${record.DiscountServiceMoney }</td>
					<td>
					<c:if test="${record.ReceipType ==0 }">普通发票</c:if>
					<c:if test="${record.ReceipType ==1 }">增值税专用发票</c:if>
					</td>
					<td>${record.InvoiceNumber }</td>
					<td>${record.Createdate }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
		<span> 共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
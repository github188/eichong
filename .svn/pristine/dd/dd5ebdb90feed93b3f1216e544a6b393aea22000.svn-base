<%@page import="com.wanma.model.TblUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="pageHeader">
<form id="pagerForm" method="post" action="finance/invoiceManage.do" onsubmit="return navTabSearch(this);"> 
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
						<span>用户手机号</span>
						<input name="ivRecipientsNumber" value="${TblInvoice.ivRecipientsNumber}" placeholder="用户手机号" />
					</td>
					<td style="align:left">
						<span>发票号码&nbsp;</span>
						<input name="ivNumber" value="${TblInvoice.ivNumber}" placeholder="发票号码" />
					</td>
					<td>
			            <span>申请时间&nbsp;</span>				             	 
			             <input id="createDates_f1" name="createDates" value="${TblInvoice.createDates}" class="date"  style="width:155px"
						 onClick="WdatePicker({el:'createDates_f1',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'updateDates_f1\')}'})">
			                                    至 <input id="updateDates_f1" name="updateDates" value="${TblInvoice.updateDates}" class="date"  style="width:155px"
			             onClick="WdatePicker({el:'updateDates_f1',minDate:'#F{$dp.$D(\'createDates_f1\')}'})">                       	            
		            </td>
		          </tr>
		          <tr>
		         
		            <td style="align:left">
		            <span>处理状态&nbsp;&nbsp;&nbsp;</span>
					<select name="ivState" id="ivState" class="select_Style" style="width:150px;" >
								<option value="">全部</option>
								<option value="0" ${tblInvoice.ivState=='0'
									? 'selected' : ''}>受理中</option>
								<option value="1" ${tblInvoice.ivState=='1'
									? 'selected' : ''}>处理完成</option>
								<option value="2" ${tblInvoice.ivState=='2'
									? 'selected' : ''}>未支付</option>
						</select>
					</td>
				
                <td style="align:left">
                 <span>发票类型&nbsp;</span>
					<select name="ivType"  id="ivType" style="width:150px;">
								<option value="" >全部</option>
								<option value="0" ${tblInvoice.ivType=='0'
									? 'selected' : ''}>个人开票</option>
								<option value="1" ${tblInvoice.ivType=='1'
									? 'selected' : ''}>公司开票</option>
						</select>
					</td>
		           <td></td>
			
					<td align="right">
						<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter_invoice"/>
					</td>
			
					<td align="right">
						<bmtag:button messageKey="excel导出" type="button" onclick="exportSubmit('pagerForm','orderExport/invoice.do')"/>
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
				<th><input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
                <th><bmtag:message messageKey="用户帐号" />
                </th>
                <th><bmtag:message messageKey="联系人手机号" />
				</th>								
				<th><bmtag:message messageKey="开票内容" />
				</th>
				<th><bmtag:message messageKey="处理状态"/></th>
				<th><bmtag:message messageKey="邮费支付方式"/></th>
				<th><bmtag:message messageKey="发票号码"/></th>
				<th><bmtag:message messageKey="申请日期" />
				</th>
				<th><bmtag:message messageKey="完成时间" /></th>
				<th><bmtag:message messageKey="发票类型"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${invoiceList}" var="invoice" varStatus="status">
				<tr target="id" rel="${invoice.pkInvoice}" align="center">
					<td><input name="ids" value="${invoice.pkInvoice}" 
						type="checkbox">
					</td>
                    <td>${invoice.userAccount}</td>
					<td>${invoice.ivRecipientsNumber}</td>
					<td><a href="#" onclick='toLook("${invoice.pkInvoice}")'  style = "color:blue;" >开票申请详情</a></td>
					<td>
					 <c:if test="${invoice.ivState ==0}">受理中</c:if>
					 <c:if test="${invoice.ivState ==1}">处理完成</c:if>
					 <c:if test="${invoice.ivState ==2}">未支付 </c:if>
					</td>
					<td>
					 <c:if test="${invoice.ivPayFreight ==0}">账户余额支付</c:if>
					 <c:if test="${invoice.ivPayFreight ==1}">支付宝支付</c:if>
					 <c:if test="${invoice.ivPayFreight ==2}">微信支付</c:if>
					 <c:if test="${invoice.ivPayFreight ==3}">银联支付</c:if>
					 <c:if test="${invoice.ivPayFreight ==4}">货到付款</c:if>
					</td>
					<td style="width:170px;"><input onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" style="width:110px;" type="text" name="lname" id="invoc${invoice.pkInvoice}"value="${invoice.ivNumber}"/>
                    <a style = "color:blue;" onclick='toEdit("${invoice.pkInvoice}","${invoice.ivState}")'title="确定要处理吗？">修改</a>
                    </td>
					<td>${invoice.createDates}</td>
					<td>${invoice.updateDates}</td>
					<td>
					 <c:if test="${invoice.ivType ==0}">个人发票</c:if>
					 <c:if test="${invoice.ivType ==1}">公司发票</c:if>
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
$(function(){
	var userIdForShow="<%=((TblUser)session.getAttribute("user")).getUserId()%>";
	if(userIdForShow==8231){
		$(".pages").html("");
	}
	loadCity($("#provinceCodef4"));
});
function toLook(i){

	//var $this = $(obj);
	var ids = i;
	navTab.openTab("", basepath+"/admin/finance/invoiceDetail.do", { title:"开票申请详情", fresh:false, data:{id:ids}});
	

}


function toEdit(i,j) {

	var pkInvoice=i;
	var ids="invoc"+i;
	var ivNumber=document.getElementById(ids).value;
	if(ivNumber==""){
		alertMsg.error("请输入发票号码！");
	}
	else if(j=="2"){
		
		alertMsg.error("未支付状态不能进行修改操作！");
		document.getElementById(ids).value="";
		}
	else{
	$.ajax({
		type : 'post',
		url : "/wanma/admin/finance/changeInvoiceDetail.do?pkInvoice="+pkInvoice+"&ivNumber="+ivNumber,
		dataType : "json",
		success : function(datas) {
		if(datas==1){
		
		   document.getElementById("formSubmitter_invoice").click();
			alertMsg.info("修改成功！");
		}
		else{
			alertMsg.error("修改失败！");
		}
		}
	});
	}
}

</script>



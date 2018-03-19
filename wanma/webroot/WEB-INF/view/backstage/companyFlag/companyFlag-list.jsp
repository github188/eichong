<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">
td.rightTools {
	float: right;
}
</style>

<div class="pageHeader">
	<form id="pagerForm" method="post" action="companyFlag/getCompanyFlagList.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
			
		<div class="searchBar">
		<table class="searchContent">
		<tbody>
			<tr>	
				<td style="align:left">
					<label style="align:left"><bmtag:message messageKey="公司名称"/></label>
				</td>
				<td>
					<input type="text" name="cpyCompanyname" value="${tblCompany.cpyCompanyname}">
				</td>
				<td style="align:left">
					<label style="align:left"><bmtag:message messageKey="公司标识"/></label>
				</td>
				<td>
					<input type="text" name="cpyCompanyNumber" value="${tblCompany.cpyCompanyNumber}">
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
	<div class="panelBar">
		<ul class="toolBar">
			<li><bmtag:link authUrl="companyFlag/changeCompanyFlagUI.do?pkCompanyid={id}"
				 rel="changeCompanyUI" messageKey="编辑" dwzClass="edit" onclick="checkSelect(this)"/></li>
			<li>
				<bmtag:link isAuth="true" target="selectedTodo" href="companyFlag/removeCompanyFlag.do"
					altKey="common.msg.delete.confirm" messageKey="批量删除" dwzClass="delete" />
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="109">
		<thead>
			<tr align="center">
				<th width="10"><input type="checkbox" group="ids"
					class="checkboxCtrl" />
				</th>
				<th><bmtag:message messageKey="公司名称" /></th>
				<th><bmtag:message messageKey="公司标识" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${companyList}" var="company" varStatus="status">
				<tr target="id" rel="${company.pkCompanyid}" align="center">
					<td><input name="ids"  value="${company.pkCompanyid}"
						type="checkbox" />
					</td>
					<td>${company.cpyCompanyname}</td>
					<td>
				    	<c:choose>
							<c:when test="${company.cpyCompanyNumber == '0'}">无</c:when>
							<c:otherwise>${company.cpyCompanyNumber}</c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>  <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="4" ${pager.numPerPage == 4?"selected":""}>4</option>
					<option value="20" ${pager.numPerPage == 20?"selected":""}>20</option>
					<option value="100" ${pager.numPerPage == 100?"selected":""}>100</option>
					<option value="200" ${pager.numPerPage == 200?"selected":""}>200</option>
			</select>  共${pager.total }条, 共${pager.pageTotal}页
			</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div> 
	</div>
</div>
<script type="text/javascript">
/**
 * 校验是否可编辑
 */
function checkSelect(obj){
	var $this = $(obj);
	var ids = getIds();
	if (ids.length==0) {
		alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
		return false;
	}else if(ids.length > 1){
		alertMsg.error("只能选择一条信息！");
	}else{
		var id = ids[0]
	  	$.ajax({
	        type: 'POST',
	        url: basepath+"/admin/companyFlag/isFlagBeUsed.do?pkCompanyid="+id,
	        dataType: "json",
	        success: function(datas){
	        	if(datas){
	        		alertMsg.error("该公司标识已被使用，不可编辑！");
	        	}else{
		    		navTab.openTab("changeCompanyUI", basepath+"/admin/companyFlag/changeCompanyFlagUI.do?companyId="+id, { title:"编辑", fresh:false});
	        	}
	        }
	   });
	}

}

/**
 * 获取选中的ID
 */
function getIds(){
	var $box =navTab.getCurrentPanel();
	var ids = [];
	$box.find("input:checked").filter("[name='ids']").each(function(i){
		ids.push($(this).attr("value"));
	})
	if(ids == null || ids.length == 0){
		var trSelected = $box.find("tr.selected");
		var trSelectEdId = trSelected.attr('rel');
		if(trSelectEdId){
			ids.push(trSelectEdId);
		}
	}
	return ids;
}
</script>

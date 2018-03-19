<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style type="text/css">
.green {
	background:green;
}
.yellow{
	background:yellow;
}

.dd_content {
	width: 180px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	course: hand;
}
</style>

<script type="text/javascript">
	function showContent(this_e) {
		alertMsg.info($(this_e).html());
	}
</script>


<div class="pageHeader">
	<form id="pagerForm" method="post" action="publishEp/findPublishEpList.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> 
		<input type="hidden" name="keywords" value="${pager.keywords}" />
		<input type="hidden" name="pageNum" value="${pager.pageNum}" /> 
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
	<div class="searchBar">
		<table class="searchContent">
			<tbody>
				<tr>
				<!-- value="搜索关键字"  -->
					<td style="align:left">
						<span>地址&nbsp;</span>
						<input name="address" value="${publishEp.address }" placeholder="地址" />
					</td>
					
					<td>
			            <span>提交时间&nbsp;</span>				             	 
			             <input id="publishEp_createDate" name="startDate" value="${publishEp.startDate }" class="date"  style="width:155px"
						 onClick="WdatePicker({el:'publishEp_createDate',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'publishEp_lastUpdateDate\')}'})">
			                                    至 <input id="publishEp_lastUpdateDate" name="endDate" value="${publishEp.endDate}" class="date"  style="width:155px"
			             onClick="WdatePicker({el:'publishEp_lastUpdateDate',minDate:'#F{$dp.$D(\'publishEp_createDate\')}'})">                       	            
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
			<li><bmtag:link authUrl="publishEp/editPublishEp.do?id={id}"
					rel="editEpsc" messageKey="处理" dwzClass="edit" onclick="checkSelect(this)"/></li>
			<li><a class="delete" target="selectedTodo" rel="ids"
				postType="string" title="确定要批量删除吗？"
				href="<c:url value='/admin/publishEp/deletePublishEps.do'/>"
				title="批量删除"><span>批量删除</span> </a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="115">
		<thead>
			<tr align="center">
				<th width="10"><input type="checkbox" group="ids"
					class="checkboxCtrl" />
				</th>
				<th><bmtag:message messageKey="排序号" /></th>
				<th><bmtag:message messageKey="所在地址" /></th>
				<th><bmtag:message messageKey="经度" /></th>
				<th><bmtag:message messageKey="纬度" /></th>
				<th><bmtag:message messageKey="桩体信息" /></th>
				<th><bmtag:message messageKey="提交时间" /></th>
				<th><bmtag:message messageKey="状态" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${publishEpList}" var="ep" varStatus="status">
				<tr target="id" rel="${ep.id}" align="center" data-state="${ep.status}" >
					<td><input name="ids"  value="${ep.id}" data-state="${ep.status}"
						type="checkbox" />
					</td>
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td>${ep.address}</td>
					<td>${ep.longitude}</td>
					<td>${ep.latitude}</td>
					<td title="${ep.parameter_note}">
						${fn:length(ep.parameter_note) > 10 ? fn:substring(ep.parameter_note,0,10) : ep.parameter_note}
					</td>
					<td>${ep.createtime}</td>
					<td>
						<c:if test="${ep.status==0}"><font color="#CC6600">未处理</font></c:if>
						<c:if test="${ep.status==1}"><font color="#336600">已处理</font></c:if>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="4" ${pager.numPerPage == 4?"selected":""}>4</option>
					<option value="20" ${pager.numPerPage == 20?"selected":""}>20</option>
					<option value="100" ${pager.numPerPage == 100?"selected":""}>100</option>
					<option value="200" ${pager.numPerPage == 200?"selected":""}>200</option>
			</select> 共${pager.total }条, 共${pager.pageTotal}页
			</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>

<script>
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
		var idAndState = ids[0].split(',')
		if(idAndState[1] == 1){
			alertMsg.error("已处理完成，无需再处理！");
		}else{
			navTab.openTab("editEpsc", basepath+"/admin/publishEp/editPublishEp.do?id="+idAndState[0], { title:"处理", fresh:false});
		}
	}

}

/**
 * 获取选中的ID
 */
function getIds(){
	var $box =navTab.getCurrentPanel();
	var ids = [];
	$box.find("input:checked").filter("[name='ids']").each(function(i){
		ids.push($(this).attr("value")+','+$(this).attr("data-state"));
	})
	if(ids == null || ids.length == 0){
		var trSelected = $box.find("tr.selected");
		var trSelectEdId = trSelected.attr('rel');
		if(trSelectEdId){
			ids.push(trSelectEdId+','+ trSelected.attr("data-state"));
		}
	}
	return ids;
}
</script>

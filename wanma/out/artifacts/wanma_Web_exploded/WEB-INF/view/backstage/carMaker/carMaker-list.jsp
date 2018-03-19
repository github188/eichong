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
	<form id="pagerForm" method="post" action="carmaker/findCarMakerList.do"
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
				<bmtag:link isAuth="true" target="navTab" href="carmaker/newCarmaker.do" 
					 rel="newEpsc" messageKey="添加" dwzClass="add" />
			</li>
			<li>
				<bmtag:link authUrl="carmaker/editCarMaker.do?pkCarmaker={id}"
					 rel="editEpsc" messageKey="编辑" dwzClass="edit" onclick="checkSelect(this)" />
			</li>
			
			<li>
				<bmtag:link isAuth="true" target="selectedTodo" href="carmaker/deleteCarMakers.do"
					rel="ids" postType="string" altKey="确定要删除吗？" dwzClass="delete" messageKey="批量删除"/>
			</li> 
			<%-- <li><bmtag:link
					href="carmaker/deleteById.do?pageNum=${pager.pageNum }&pkCarmaker={id}"
					target="ajaxTodo" altKey="common.msg.delete.confirm"
					messageKey="common.icon.delete" dwzClass="delete" /></li> --%>	
		</ul>
	</div>
	<table class="table" width="100%" layoutH="87">
		<thead>
			<tr align="center">
				<th width="10"><input type="checkbox" group="ids"
					class="checkboxCtrl" />
				</th>
				<th><bmtag:message messageKey="排序号" /></th>
				<th><bmtag:message messageKey="制造商" /></th>
				<th><bmtag:message messageKey="标识" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${carmakerList}" var="car" varStatus="status">
				<tr target="id" rel="${car.pkCarmaker}" align="center" data-bindCount="${car.bindCount}">
					<td><input name="ids"  value="${car.pkCarmaker}"
						type="checkbox" />
					</td>
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td>${car.makerName}</td>
					<td>${car.makerRemark}</td>
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
		var idAndState = ids[0].split(',')
		if(idAndState[1] != 0){
			alertMsg.error("该厂商已被绑定电桩，不可编辑！");
		}else{
			navTab.openTab("concentratorEditPage", basepath+"/admin/carmaker/editCarMaker.do?pkCarmaker="+idAndState[0], { title:"编辑", fresh:false});
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
			ids.push(trSelectEdId+','+ trSelected.attr("data-bindCount"));
		}
	}
	return ids;
}
</script>

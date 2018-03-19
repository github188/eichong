<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>


<div class="pageHeader">

	<c:set var="activityType" value="${params.activityType}"></c:set>

	<form  id="pagerForm" method="post"  action="activity/listActivity.do"  onsubmit="return navTabSearch(this);" >
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	</form>
</div>


<div class="pageContent">

	<table class="table" width="100%" layoutH="60">
		<thead>
			<tr>
				<th width="10">序号</th>
				<th width="30">配置类型</th>
				<th width="20">操作</th>
			</tr>
		</thead>
		<tbody>
				<tr>
					<td>1</td>
					<td>商品大分类</td>
					<td>
						<a target="navTab" rel="findCategoryList" href="<c:url value='/admin/productCategory/findCategoryList.do'/>" title="子项管理" style='color:#0099FF'>子项管理</a>
					</td>
				</tr>
				<tr>
					<td>2</td>
					<td>商品小分类</td>
					<td>
						<a target="navTab" rel="findCategoryList" href="<c:url value='/admin/productCategory/findCategoryList.do'/>" title="子项管理" style='color:#0099FF'>子项管理</a>
					</td>
				</tr>
		</tbody>
	</table>
	
	
	<div class="panelBar">
		<div class="pages">
			<span> <!-- <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="4" ${pager.numPerPage==4?"selected":""}>4</option>
					<option value="20" ${pager.numPerPage==20?"selected":""}>20</option>
					<option value="100" ${pager.numPerPage==100?"selected":""}>100</option>
					<option value="200" ${pager.numPerPage==200?"selected":""}>200</option>
			</select>  -->共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${pager.total}" numPerPage="${pager.numPerPage}"  currentPage="${pager.pageNum}"></div>
	</div>
	
	
	
	
</div>

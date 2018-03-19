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

	<%-- <div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" target="navTab"  rel="newActivity" href="<c:url value='/admin/activity/newActivity.do'/>" title="添加活动"><span>添加</span></a></li>
			<li><a class="edit" target="navTab" rel="editActivity" href="<c:url value='/admin/activity/editActivity.do?id={id}'/>" title="编辑活动"><span>编辑</span></a></li>
			<li><a class="edit" target="navTab" rel="viewActivity" href="<c:url value='/admin/activity/viewActivity.do?id={id}'/>" title="查看活动"><span>查看</span></a></li>
			<li><a class="delete" href="<c:url value='/admin/activity/delActivity.do?id={id}'/>" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
		</ul>
	</div> --%>
	
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
					<td>电桩充电方式</td>
					<td>
						<a target="navTab" rel="findByEpscType" href="<c:url value='/admin/elctrcplscrnconfgurtn/findByEpscType.do?epscType=3'/>" title="子项管理" style='color:#0099FF'>子项管理</a>
					</td>
				</tr>
				<tr>
					<td>2</td>
					<td>电桩类型</td>
					<td>
						<a target="navTab" rel="findByEpscType" href="<c:url value='/admin/elctrcplscrnconfgurtn/findByEpscType.do?epscType=4'/>" title="子项管理" style='color:#0099FF'>子项管理</a>
					</td>
				</tr>
				<tr>
					<td>3</td>
					<td>电桩枪口连接方式</td>
					<td>
						<a target="navTab" rel="findByEpscType" href="<c:url value='/admin/elctrcplscrnconfgurtn/findByEpscType.do?epscType=5'/>" title="子项管理" style='color:#0099FF'>子项管理</a>
					</td>
				</tr>
				<tr>
					<td>4</td>
					<td>电桩额定功率</td>
					<td>
						<a target="navTab" rel="findByEpscType" href="<c:url value='/admin/elctrcplscrnconfgurtn/findByEpscType.do?epscType=6'/>" title="子项管理" style='color:#0099FF'>子项管理</a>
					</td>
				</tr>
				<tr>
					<td>5</td>
					<td>最大电流</td>
					<td>
						<a target="navTab" rel="findByEpscType" href="<c:url value='/admin/elctrcplscrnconfgurtn/findByEpscType.do?epscType=7'/>" title="子项管理" style='color:#0099FF'>子项管理</a>
					</td>
				</tr>
				<tr>
					<td>6</td>
					<td>桩体用途</td>
					<td>
						<a target="navTab" rel="findByEpscType" href="<c:url value='/admin/elctrcplscrnconfgurtn/findByEpscType.do?epscType=8'/>" title="子项管理" style='color:#0099FF'>子项管理</a>
					</td>
				</tr>
				<tr>
					<td>7</td>
					<td>电桩搜索半径</td>
					<td>
						<a target="navTab" rel="findByEpscType" href="<c:url value='/admin/elctrcplscrnconfgurtn/findByEpscType.do?epscType=9'/>" title="子项管理" style='color:#0099FF'>子项管理</a>
					</td>
				</tr>
		</tbody>
	</table>
	
	
	<div class="panelBar">
		<div class="pages">
			<span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="4" ${pager.numPerPage==4?"selected":""}>4</option>
					<option value="20" ${pager.numPerPage==20?"selected":""}>20</option>
					<option value="100" ${pager.numPerPage==100?"selected":""}>100</option>
					<option value="200" ${pager.numPerPage==200?"selected":""}>200</option>
			</select> 共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="${pager.total}" numPerPage="${pager.numPerPage}"  currentPage="${pager.pageNum}"></div>
	</div>
	
	
	
	
</div>

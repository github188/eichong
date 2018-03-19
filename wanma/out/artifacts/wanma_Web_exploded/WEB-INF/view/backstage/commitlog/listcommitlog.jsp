<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
</script>
<div class="pageHeader">
<form id="pagerForm" method="post" action="commitlog/commitlogList.do" onsubmit="return navTabSearch(this);"> 
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
						<span>用户名:<span>
						<input name="logName" value="${commitLog.logName }" placeholder="请输入用户名称" />
					</td>
					
					<td style="align:left">
						<span>IP地址:<span>
						<input name="ipAddress" value="${commitLog.ipAddress }" placeholder="请输入IP地址" />
					</td>
					
					<td>
			            <span>提交时间:</span>
			            <input id="start_create_date" name="start_create_date" value="${commitLog.start_create_date }" class="date" onClick="WdatePicker()" dateFmt="yyyy-MM-dd HH:mm:ss" style="width:155px">
			                                    至 <input id="end_create_date"name="end_create_date" value="${commitLog.end_create_date }" class="date" onClick="WdatePicker()" dateFmt="yyyy-MM-dd HH:mm:ss" style="width:155px">
		            
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
			<li><a class="delete" target="selectedTodo" rel="ids"
				postType="string" title="确定要批量删除吗？"
				href="<c:url value='/admin/commitlog/removecommitlogs.do'/>"
				title="批量删除"><span>批量删除</span> </a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="112">
		<thead>
			<tr>
				<th width="10" align="center"><input type="checkbox" group="ids"
					class="checkboxCtrl" />
				</th>
				
				<th width="20" align="center"><bmtag:message messageKey="序号" /></th>
				<th width="50" align="center"><bmtag:message messageKey="账号" /></th>
				<th width="50" align="center"><bmtag:message messageKey="用户姓名" /></th>
				<th width="50" align="center"><bmtag:message messageKey="公司名" /></th>
				<th width="80" align="center"><bmtag:message messageKey="IP地址" /></th>
				<th width="80" align="center"><bmtag:message messageKey="操作时间" /></th>
				<th width="100" align="center"><bmtag:message messageKey="操作内容" /></th>
	        </tr>
		</thead>
		<tbody>
		<c:forEach items="${commitLogList}" var="Logl" varStatus="status">
			<tr target="id" rel="${Logl.commitLog }" align="center">
				<td><input name="ids"  value="${Logl.commitLog }"
						type="checkbox" />
					</td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${Logl.logName }</td>
				<td>${Logl.userName }</td>
				<td>${Logl.companyName }</td>
				<td>${Logl.ipAddress }</td>
				<%-- <td>
					<fmt:formatDate value="${Logl.createdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
				<td><fmt:formatDate  value="${Logl.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss" /></td>
				<td>${Logl.logContent }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>
			<!-- <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="4" ${pager.numPerPage == 4?"selected":""}>4</option> 
				<option value="20" ${pager.numPerPage == 20?"selected":""}>20</option> 
				<option value="100" ${pager.numPerPage == 100?"selected":""}>100</option> 
				<option value="200" ${pager.numPerPage == 200?"selected":""}>200</option> 
			</select> -->
			共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${pager.total}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>

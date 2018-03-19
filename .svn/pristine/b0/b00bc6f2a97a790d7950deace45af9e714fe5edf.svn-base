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
<form id="pagerForm" method="post" action="consult/consultList.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<div class="searchBar">
		<table class="searchContent">
			<tbody>
				<tr>
					<td style="align:left">
						<span>用户账号&nbsp;</span>
						<input type="text" name="userLogin" value="${consult.userLogin }" placeholder="搜索关键字"/>
					</td>
					
					<td>
			            <span>提交时间&nbsp;</span>
			            <input id="start_create_date" name="start_create_date" value="${consult.start_create_date }" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" style="width:155px">
			                                    至 <input id="end_create_date"name="end_create_date" value="${consult.end_create_date }" class="date" dateFmt="yyyy-MM-dd HH:mm:ss" style="width:155px">
		            
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
	<table class="table" width="100%" layoutH="86">
		<thead>
			<tr>
				<th>序号</th>
				<th>用户账号</th>
				<th>联系人</th>
				<th>联系地址</th>
				<th>提交时间</th>
				<th>咨询信息</th>
	        </tr>
		</thead>
		<tbody>
		<c:forEach items="${consultList}" var="consult" varStatus="status">
			<tr target="userId" rel="${consult.consultId }" align="center">
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${consult.userLogin }</td>
				<td>${consult.contact }</td>
				<td>${consult.consultAddress }</td>
				<td>
					<fmt:formatDate value="${consult.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>${consult.consultContent }</td>
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

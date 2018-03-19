<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
</script>
<div class="pageHeader"> 
<form id="pagerForm" method="post" action="evaluate/evaluateList.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<div class="searchBar">
		<table class="searchContent">
		<tbody>
			<tr>
				<td><span>用户账号/订单号&nbsp;</span>
					<input type="text" name="choevaluate" value="${tblProductcomment.choevaluate }" placeholder="用户账号/订单号评价"/>
				</td>
				<td>
			        <span>评价时间&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
			          
			          <input id="evaluate_start_create_date" name="start_create_date" value="${tblProductcomment.start_create_date }" class="date"  style="width:155px"
						 onClick="WdatePicker({el:'evaluate_start_create_date',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'evaluate_end_create_date\')}'})">
			                                    至 <input id="evaluate_end_create_date" name="end_create_date" value="${tblProductcomment.end_create_date}" class="date"  style="width:155px"
			             onClick="WdatePicker({el:'evaluate_end_create_date',minDate:'#F{$dp.$D(\'evaluate_start_create_date\')}'})">  
			             
		        </td>
				<td align="right">
					<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter"/>
				</td>
			</tr>
			<tr>
				<td>
			        <span>商品/桩体编号</span>
					<input type="text" name="proCode" style="width:155px" value="${tblProductcomment.proCode }"  placeholder="搜索关键字"/>
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
			<li><bmtag:link href="evaluate/FilterWordList.do"  
				target="navTab" rel="filterWordList" messageKey="过滤字管理" dwzClass="add"  id="addLink"/></li>							
			<li><a class="delete" target="selectedTodo" rel="ids"
				postType="string" title="确定要删除吗？"
				href="<c:url value='/admin/evaluate/deleteProductAll.do'/>"
				title="批量删除"><span>批量删除</span>
			</a></li>
		</ul>
	</div>
	
	<table class="table" width="100%" layoutH="138" >
		<thead>
		<tr align="center">
		<th width="10"><input type="checkbox" group="ids"
					class="checkboxCtrl" />
				</th>
			<th><bmtag:message messageKey="序号"/></th>
			<th><bmtag:message messageKey="用户账号"/></th>
			<th><bmtag:message messageKey="评论时间"/></th>
			<th><bmtag:message messageKey="评论类型"/></th>
			<th><bmtag:message messageKey="商品/桩体编号"/></th>
			<th><bmtag:message messageKey="商品/桩体名称"/></th>
			<%-- <th><bmtag:message messageKey="订单号"/></th> --%>
			<th><bmtag:message messageKey="评论内容"/></th>
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${contentList}" var="content" varStatus="status">
			<tr target="pkProductcomment" rel="${content.prcoCommentType}:${content.pkProductcomment}"  align="center">
				<td>
					<input name="ids"  value="${content.prcoCommentType}:${content.pkProductcomment}"
						type="checkbox" />
				</td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${content.phone }</td>
				<td>
					<fmt:formatDate value="${content.prcoCreatetime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<c:choose>
						<c:when test="${content.prcoCommentType == '1'}">
							桩体评价
						</c:when>
						<c:when test="${content.prcoCommentType == '2'}">
							商城商品评价
						</c:when>	
						<c:when test="${content.prcoCommentType == '3'}">
							充电点评价
						</c:when>
					</c:choose>
				</td>
				<td>${content.proCode }</td>
				<td>${content.proCodeName }</td>
				<c:set var="prcoContent" value="${content.prcoContent}" />
				<c:if test="${content.prcoContent.length()>15}">
					<c:set var="prcoContent" value="${fn:substring(content.prcoContent, 0, 15)}..." />
				</c:if>
				<td title="${content.prcoContent }">${prcoContent}</td>
				<%-- <td title="${content.prcoContent }">
				<c:set var="contentStr" value="${content.prcoContent }"  />
				<c:choose>  
				    <c:when test="${fn:length(contentStr) >8}">  
				        <c:out value="${fn:substring(contentStr, 0, 8)}..." />  
				    </c:when>  
				    <c:otherwise>  
				       <c:out value="${contentStr}" />  
				    </c:otherwise>  
				</c:choose>  
     
				 </td> --%>
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
			共${pager.total }条,共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${pager.total}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>

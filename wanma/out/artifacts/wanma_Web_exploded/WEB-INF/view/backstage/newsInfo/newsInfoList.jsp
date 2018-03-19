<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<div id="float_news" style="width:100%;height:100%;background:#ccc;opacity: 0.9;z-index: 8000;position:absolute;display: none;">
	<div class="phoneModel"  style="top: 30px;position:relative;">
		 <img id="img_news"   style="	width:400px;height:300px;	display:block;margin:0 auto;margin-left:650px;" 
		  src="<%=basePath%>/static/images/advertise/u0.jpg" name=""  scrolling="no" frameborder="0"/>
	  </div>
	  <div style="text-align:center;top: 50px;position:relative;" onclick="hiddens_news()"><img src="<%=basePath%>/static/images/advertise/hcx.png"/></div>
	  </div>
<div class="pageHeader">
<form id="pagerForm" method="post" action="newsInfo/listNewsInfo.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td><span>标题</span> <input name="newsInfoName"
							placeholder="请输入" value="${newsInfo.newsInfoName }" /></td>
						<td><span>发布/编辑时间</span> <input id="startDate_payOrder"
							name="startTime" placeholder="请选择"
							value="${newsInfo.startTime}" class="date"
							onClick="WdatePicker({el:'startDate_payOrder',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'endDate_payOrder\')}'})">
						<span>至</span><input
							id="endDate_payOrder" name="endTime"
							value="${newsInfo.endTime}" class="date"
							placeholder="请选择"
							onClick="WdatePicker({el:'endDate_payOrder',minDate:'#F{$dp.$D(\'startDate_payOrder\')}'})">
						</td>
						<td align="right"><bmtag:button messageKey="common.button.search" type="submit"
								id="formSubmitter" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>

</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li>
					<bmtag:link isAuth="true" target="navTab" href="newsInfo/addNewsInfoUi.do"
						rel="newsInfoAddPage" messageKey="common.icon.new" dwzClass="add" id="newsInfoAddPage" />
			</li>
		 	<li>
					<bmtag:link isAuth="true" target="navTab" href="newsInfo/editNewsInfoUi.do?id={id}"
						 rel="newsInfoEditPage" messageKey="common.icon.edit" dwzClass="edit" />
			</li>
			<li>
					<bmtag:link isAuth="true" target="selectedTodo" href="newsInfo/deleteNewsInfo.do"
						rel="id"  altKey="确定删除该条资讯？" dwzClass="delete" messageKey="删除" />
			</li>
			<li>
					<bmtag:link isAuth="true" target="selectedTodo" href="newsInfo/downNewsInfo.do"
						 rel="id"  dwzClass="icon" altKey="确定下架该条资讯？" messageKey="下架"/>
			</li>
		</ul>
	</div>
	
	<table class="table" width="100%" layoutH="113">
		<thead>
			<tr align="center">
				<th style="width:7%"><bmtag:message messageKey="common.label.index" /></th>
				<th style="width: 20%"><bmtag:message messageKey="标题" /></th>
				<th style="width: 25%"><bmtag:message messageKey="图片" /></th>
				<th style="width: 25%"><bmtag:message messageKey="发布/编辑时间" /></th>
				<th style="width: 20%"><bmtag:message messageKey="状态" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${newsInfoList}" var="item" varStatus="status">
				<tr target="id" rel="${item.newsInfoId}" align="center">
					<td style="width:7%">${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td ><a onclick='lookThumbnail_news("${item.newsInfoUrl}")'>${item.newsInfoName}</a></td>
					<td><a title="预览"  onclick='toLook_thumbnail_news("${item.newsInfoPicUrl}","${item.newsInfoUrl}")'>缩略图</a></td>
					<td><fmt:formatDate value="${item.newsInfoUpdatedate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>
						<c:if test="${item.newsInfoStatus ==1}">有效</c:if> 
						<c:if test="${item.newsInfoStatus ==2}">已过期</c:if> 
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

function toLook_thumbnail_news(i,j) {
	 $("#img_news").attr("src",i);
	 $("#img_news").attr("name",j)
	 $("#float_news").show();
	 
}	

function lookThumbnail_news(j) {
	var url = $.trim(j);
	if(url!=""){
		window.open(url);
	}
}	
function hiddens_news() {
	$("#float_news").hide();
}
</script>



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
 <style type="text/css">
	@media (min-width: 425px){
		
		  
		  .phoneModel:before {
		    content: '';
		    width: 30px;
		    height: 5px;
		    border-radius: 5px;
		    position: absolute;
		     left:50%; 
		    margin-left: -15px;
		     background:#333; 
		    top: 25px;
		}
		  	
		  .phoneModel .statusbar {
		    position: absolute;
		    width: 160px;
		    height: 10px;
		   	background:url('data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAoAAAAAoCAIAAADhf9zeAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyhpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDE0IDc5LjE1Njc5NywgMjAxNC8wOC8yMC0wOTo1MzowMiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTQgKE1hY2ludG9zaCkiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6REEyN0EzRUU1QzM3MTFFNEE1ODA5RkNEOEU4MEU4ODYiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6REEyN0EzRUY1QzM3MTFFNEE1ODA5RkNEOEU4MEU4ODYiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDpEQTI3QTNFQzVDMzcxMUU0QTU4MDlGQ0Q4RTgwRTg4NiIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDpEQTI3QTNFRDVDMzcxMUU0QTU4MDlGQ0Q4RTgwRTg4NiIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PuHAU7gAAA6nSURBVHja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');
		    left: 50%;
		    margin-left: -80px;
		    top: 50px;
		    -webkit-background-size: 100% auto;
		    background-size: 100% auto;
		}
		  
		  .phoneModel:after {
		    content: '';
		    position: absolute;
		    width: 30px;
		    height: 30px;
		    left: 50%;
		    margin-left: -15px;
		    bottom: 10px;
		    border-radius: 100%;
		    box-sizing: border-box;
		    border: 2.5px solid #333;
		}
	}
	
	.phoneModel {
		margin: 0 auto;
		position: relative;
	    background: #111;
	    border-radius: 27.5px;
	    box-shadow: 0px 0px 0px 2px #aaa;
	    width: 160px;
	    height: 284px;
	    padding: 52.5px 12.5px;
	    -webkit-box-sizing: content-box;
	    box-sizing: content-box;
	}

	/* .phoneModel img {
	    width: 160px;
	    height: 100%;
	    display: block;
	    width: 100%;
	    margin-top: 10px;
	} */

</style>
<div id="float" style="width:100%;height:100%;background:#ccc;opacity: 0.9;z-index: 8000;position:absolute;display: none;">
	<div class="phoneModel"  style="top: 30px;position:relative;">
		 <img id="img"   width="160px" height="248.8px" src="<%=basePath%>/static/images/advertise/u0.jpg" name="" onclick='look(name)' scrolling="no" frameborder="0"/>
			 <img id="img2" width="160px" height="35.2px" src="<%=basePath%>/static/images/advertise/bottom.jpg"/> 
		<div class="statusbar"></div>
	  </div>
	  
	  <div style="text-align:center;top: 50px;position:relative;" onclick="hiddens()"><img src="<%=basePath%>/static/images/advertise/hcx.png"/></div>
	  </div>
<div class="pageHeader">

	<form id="pagerForm" method="post" action="advertisement/listSplash.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="keywords" value="${pager.keywords}"/> 
		<input type="hidden" name="pageNum" value="${pager.pageNum}" /> 
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		 <input type="hidden" name="adType" value="1" /> 
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td style="align: left"><span>状态</span> <select
							name="advSts" id="advSts" class="select_Style"
							style="width: 150px;">
								<option value="">全部</option>
								<option value="0"
									${advModel.advSts=="0"
									? 'selected' : ''}>未开始</option>
								<option value="1"
									${advModel.advSts=="1"
									? 'selected' : ''}>进行中</option>
								<option value="2"
									${advModel.advSts=="2"
									? 'selected' : ''}>已结束</option>
						</select></td>
						<td align="right"><bmtag:button
								messageKey="common.button.search" type="submit"
								id="formSubmitter_splash" /></td>
						
					</tr>
				</tbody>
			</table>
		</div>

	</form>
</div>

<div class="pageContent">
         
 
	<div class="panelBar">
		<ul class="toolBar">
			<li><bmtag:link isAuth="true" target="navTab"
					href="advertisement/addSplash.do" rel="ids"
					messageKey="common.icon.new" dwzClass="add" id="addSplash" /></li>
		</ul>
	</div>
	
	<table class="table" width="100%" layoutH="132">
		<thead>
			<tr align="center">
				<th style="width: 10%"><bmtag:message messageKey="名称" /></th>
				<th style="width: 10%"><bmtag:message messageKey="状态" /></th>
				<th style="width: 15%"><bmtag:message messageKey="开始时间" /></th>
				<th style="width: 15%"><bmtag:message messageKey="结束时间" /></th>
				<th style="width: 10%"><bmtag:message messageKey="说明" /></th>
				<th style="width: 10%"><bmtag:message messageKey="地址" /></th>
				<th style="width: 15%"><bmtag:message messageKey="创建时间" /></th>
				<th style="width: 6%"><bmtag:message messageKey="操作人" /></th>
				<th align="center" style="width: 9%"><bmtag:message messageKey="操作" /></th>
			</tr>
		</thead>
		<tbody>
		
			<c:forEach items="${advList}" var="item" varStatus="status">
				<tr target="id" rel="${item.pkAdId}" align="center">
					
					<td>${item.adName}</td>
					<td>
					<c:if test="${item.advSts ==0}">未开始</c:if> 
					<c:if test="${item.advSts ==1}">进行中</c:if> 
					<c:if test="${item.advSts ==2}">已结束 </c:if> 
					</td>
					<td><fmt:formatDate value="${item.beginAdTime }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${item.endAdTime }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td title="${item.adDesc}">${item.adDesc }</td>
					<td title="${item.adURL}"><div style="width:150px">${item.adURL }</div></td>
					<td><fmt:formatDate value="${item.adCreatedate }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>
						<c:choose>
							<c:when test="${item.fkUserId == 1}">admin</c:when>
							<c:otherwise>${item.uName }</c:otherwise>
						</c:choose>
					</td>
					<td>
						<a title="预览"  onclick='toLook_splash("${item.advPicUrl}","${item.adURL}")' class="btnView">预览</a>
						<c:if test="${item.advSts==0}"><a title="编辑" target="navTab" href="advertisement/editSplash.do?pkAdId=${item.pkAdId}" class="btnEdit"></a></c:if>
						<c:if test="${item.advSts!=2}"><a title="确定进行下架吗？下架后,无法进行上架" target="ajaxTodo" href="advertisement/downAdvertisement.do?pkAdId=${item.pkAdId}&advSts=${item.advSts}&adType=1" class="btnDel">下架</a></c:if>
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
function toLook_splash(i,j) {
	 $("#img").attr("src",i);
	 $("#img").attr("name",j)
	 $("#float").show();
	 
}	

function look(j) {
	var url = $.trim(j);
	if (url != "") {
		window.open(url);
	}
}	
function hiddens() {

	$("#float").hide();
}

	
</script>



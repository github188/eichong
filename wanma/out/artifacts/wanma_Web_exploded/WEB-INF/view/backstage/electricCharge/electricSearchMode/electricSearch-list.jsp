<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<style>
.shaixuan{ background:#FFF; padding:20px; margin-top:20px; height:200px;}
.shaixuan dt{ width:120px; text-align:center; line-height:100px; float:left;}
.shaixuan dd{ width:700px; border-left:1px #ccc dashed; float:left; padding-left:30px;}
.shaixuan_main{ width:700px;}
.shaixuan_main span,.shaixuan_main strong{ display:block; float:left; font-size:14px; line-height:24px; margin:8px 5px;}
.shaixuan_main strong{ list-style:none; font-weight:normal; width:70px;}
.shaixuan_main span{ width:150px; text-align:center; border:0;}
span.shaix_cose_bai{ display:block; width:150px; height:24px; border:1px #999 solid; color:#999;cursor:pointer;}
span.shaix_cose_bai:hover{ border:1px #ff8800 solid; color:#FFF; background:#ff8800;}
span.shaix_cose{ display:block; width:150px; height:24px; border:1px #ff8800 solid; color:#FFF; background:#ff8800;cursor:pointer;}
span.shaix_cose_hui{ display:block; width:150px; height:24px; border:1px #CCC solid; background:#CCC; color:#999;}
span.shaix_cose_hui:hover{ display:block; width:150px; height:24px; border:1px #CCC solid; background:#CCC; color:#999;}
span.shaix_cose_hui2{ display:block; width:150px; height:24px; border:1px #CCC solid; background:#CCC; color:#999;}
span.shaix_cose_hui2:hover{ display:block; width:150px; height:24px; border:1px #CCC solid; background:#CCC; color:#999;}
h2 input.search{ width:200px; height:28px; background:#cccccc; float:right; margin-top:7px; font-size:12px; color:#666;}
h2 span.searchSB{ float:right; margin-left:5px; margin-top:7px;}
h2 span.searchSB img{ border:0px;}
</style>
<div class="pageHeader">
	<form id="pagerForm" method="post"
		action="electricPileMap/getElectricPileMapList.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" id="pageNum" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
		<h2 class="no-subtext"><span class="searchSB" id="search"><a><img src="<%=basePath%>/static/images/buttons/seach.jpg" width="82" height="32" alt="搜索" /></a></span>
	     		 				<input id="address" class="search" name="" type="text" placeholder="请输入地址" /></h2>		
			<table class="searchContent">
				<tbody>																										
						<tr>									
						<td>
							<div class="shaixuan_main props">
					            <strong>电桩快慢：</strong>
					            <span data-type="" class="shaix_cose_bai shaix_cose">不限</span>  
					            <span data-type="5" class="shaix_cose_bai">快充</span>
					            <span data-type="14" class="shaix_cose_bai">慢充</span>
				           </div>
						</td>
					</tr>							
				</tbody>
			</table>
		</div>
	</form>
</div>
	<div class="panelBar">
	</div>
	<div class="map" id="gdMap" style="width:100%;height:790px;">
    </div>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/electric/electricSearch-list.js"></script>
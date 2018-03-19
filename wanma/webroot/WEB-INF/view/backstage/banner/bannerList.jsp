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
<link href="<%=request.getContextPath()%>/static/css/bannerList.css" rel="stylesheet" />
<div id="float_banner" style="width:100%;height:100%;background:#ccc;opacity: 0.9;z-index: 8000;position:absolute;display: none;">
	<div class="bannerModel"  style="top: 30px;position:relative;">
		 <img id="img_banner" style="	width:400px;height:300px;	display:block;margin:0 auto;margin-left:650px;"  
		 src="<%=basePath%>/static/images/advertise/u0.jpg" name="" onclick='lookThumbnail_banner(name)' scrolling="no" frameborder="0"/>
	  </div>
	  <div style="text-align:center;top: 50px;position:relative;" onclick="hiddens_banner()"><img src="<%=basePath%>/static/images/advertise/hcx.png"/></div>
</div>
<div class="pageHeader">
<form id="pagerForm" method="post" action="banner/listBanner.do"
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
					<bmtag:link isAuth="true" target="navTab" href="banner/addBannerUi.do"
						rel="bannerAddPage" messageKey="common.icon.new" dwzClass="add" id="bannerAddPage" />
			</li>
		 	<li>
					<bmtag:link isAuth="true" target="navTab" href="banner/editBannerUi.do?id={id}"
						 rel="bannerEditPage" messageKey="common.icon.edit" dwzClass="edit" />
			</li>
			<li>
					<bmtag:link isAuth="true" target="selectedTodo" href="banner/deleteBanner.do"
						rel="id"  altKey="确定删除该banner广告？" dwzClass="delete" messageKey="删除" />
			</li>
			<li>
					<bmtag:link isAuth="true" target="selectedTodo" href="banner/downBanner.do"
						 rel="id"  dwzClass="icon" altKey="确定下架该banner广告？" messageKey="下架"/>
			</li>
			
		</ul>
		<div class="myEdit" id="editBannerOrder">
			修改顺序
		</div>
	</div>
	
	<table class="table" width="100%" layoutH="88">
		<thead>
			<tr align="center">
				<th style="width: 10%"><bmtag:message messageKey="播放顺序" /></th>
				<th style="width: 10%"><bmtag:message messageKey="图片" /></th>
				<th style="width: 15%"><bmtag:message messageKey="地区" /></th>
				<th style="width: 15%"><bmtag:message messageKey="开始时间" /></th>
				<th style="width: 10%"><bmtag:message messageKey="结束时间" /></th>
				<th style="width: 10%"><bmtag:message messageKey="发布时间" /></th>
				<th style="width: 15%"><bmtag:message messageKey="状态" /></th>
				<th style="width: 6%"><bmtag:message messageKey="备注" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${bannerList}" var="item" varStatus="status">
				<tr target="id" rel="${item.pkBannerId}" align="center">
					<td>
					<input type="hidden" class="oldBannerSort"  value="${item.bannerSort }" />
					<input type="text" class="newBannerSort" size="1" style="float: none;text-align:center " onkeyup="this.value=this.value.replace(/^((1[1-9])|([2-9]\d)|([1-9]\d{2,}))$|\D/g,'')" 
					onafterpaste="this.value=this.value.replace(/^((1[1-9])|([2-9]\d)|([1-9]\d{2,}))$|\D/g,'')" value="${item.bannerSort }" 
					data-value="${item.bannerSort}" data-id="${item.pkBannerId}" data-type="${item.bannerStatusType}"/>
					</td>
					<td><a title="预览"  onclick='toLook_thumbnail_banner("${item.bannerPicUrl}","${item.bannerUrl}")'>缩略图</a></td>
					<td>${item.bannerRegion}</td>
					<td><fmt:formatDate value="${item.bannerBeginTime }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${item.bannerEndTime }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${item.bannerCreatedate }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>
						${item.bannerStatusType}
					</td>
					<td title="${item.bannerDesc}">${item.bannerDesc}</td>
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
windowLoad();
function windowLoad(){
	$(".newBannerSort").each(function(){
		var type = $(this).attr("data-type"); //如果是已结束状态 播放顺序为空 切不可输入数字
		if(type=="已结束"){
			$(this).hide(); 
		}
	});
}
function toLook_thumbnail_banner(i,j) {
	 $("#img_banner").attr("src",i);
	 $("#img_banner").attr("name",j)
	 $("#float_banner").show();
	 
}	

function lookThumbnail_banner(j) {
	var url = $.trim(j);
	if(url!=""){
		window.open(url);
	}
}	
function hiddens_banner() {
	$("#float_banner").hide();
}
$("#editBannerOrder").click(function(){
	var bannerList = new Array();
	var i = 0;
	var flag = 0;
	$(".newBannerSort").each(function(){
		var newBannerSort = $(this).val();
		var type = $(this).attr("data-type");
		if(newBannerSort==0){
			alertMsg.error("播放顺序不能为0");
			flag=1;
		}
		if(type=="开启"||type=="未开始"){
			bannerList[i]=newBannerSort;
			i++;
		}
	});
	if(flag==1){
		return;
	}
	if(isRepeat(bannerList)){
		alertMsg.error("播放循序重复");
		return;
	}else {
		$(".newBannerSort").each(function(){
			var newBannerSort = $(this).val();
			var oldBannerSort =$(this).attr("data-value");
			var pkBannerId =$(this).attr("data-id");
			var type = $(this).attr("data-type");
			if(newBannerSort!=oldBannerSort && (type=="开启"||type=="未开始")){
				changeSortAjax(pkBannerId,newBannerSort);
			}else{
				navTab.reloadFlag("bannerList");
			}
		});
	}
});
function changeSortAjax(a,b){
	$.ajax({
		type : 'post',
		url : basepath+ "/admin/banner/editBannerOrder.do",
		dataType : "json",
		data :{
			pkBannerId:a,
			newBannerSort:b
		},
		success : function(datas) {
			if(datas.statusCode==200){
				navTab.reloadFlag("bannerList");
			}else{
				alertMsg.error(data.message);
			}
		}
	});
}

//判断开启的banner播放顺序是否有重复
function isRepeat(arr){ 
	var hash = {}; 
	for(var i in arr) { 
	if(hash[arr[i]]) 
		return true; 
		hash[arr[i]] = true; 
	} 
	return false; 
	} 
</script>



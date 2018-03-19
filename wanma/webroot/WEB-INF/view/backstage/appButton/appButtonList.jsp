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
<div id="float_button" style="width:100%;height:100%;background:#ccc;opacity: 0.9;z-index: 8000;position:absolute;display: none;">
	<div class="phoneModel"  style="top: 30px;position:relative;">
		 <img id="img_button"   style="	width:400px;height:300px;	display:block;margin:0 auto;margin-left:650px;"
		  src="<%=basePath%>/static/images/advertise/u0.jpg" name="" onclick='lookAppButton(name)' scrolling="no" frameborder="0"/>
	  </div>
	  <div style="text-align:center;top: 50px;position:relative;" onclick="hiddens_button()"><img src="<%=basePath%>/static/images/advertise/hcx.png"/></div>
	  </div>
<div class="pageHeader">
<form id="pagerForm" method="post" action="appButton/listAppButton.do"
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
					<bmtag:link isAuth="true" target="navTab" href="appButton/addAppButtonUi.do"
						rel="buttonAddPage" messageKey="common.icon.new" dwzClass="add"  />
			</li>
			<li>
					<bmtag:link isAuth="true" target="navTab" href="appButton/editButtonUi.do?id={id}"
						rel="buttonEditPage" messageKey="common.icon.edit" dwzClass="edit"  />
			</li>
			<li>
					<bmtag:link isAuth="true" target="selectedTodo" href="appButton/deleteAppButton.do"
						rel="id"  altKey="确定删除该按钮？" dwzClass="delete" messageKey="删除" />
			</li>
			<li>
					<bmtag:link isAuth="true" target="selectedTodo" href="appButton/downAppButton.do"
						 rel="id"  dwzClass="icon" altKey="确定下架该按钮？" messageKey="下架"/>
			</li>
		</ul>
		<div class="myEdit" id="editButtonOrder">
			修改顺序
		</div>
	</div>
	
	<table class="table" width="100%" layoutH="88">
		<thead>
			<tr align="center">
				<th style="width: 5%"><bmtag:message messageKey="顺序" /></th>
				<th style="width: 10%"><bmtag:message messageKey="图片" /></th>
				<th style="width: 15%"><bmtag:message messageKey="名称" /></th>
				<th style="width: 15%"><bmtag:message messageKey="配置/编辑时间" /></th>
				<th style="width: 10%"><bmtag:message messageKey="状态" /></th>
				<th style="width: 10%"><bmtag:message messageKey="备注" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${buttonList}" var="item" varStatus="status">
				<tr target="id" rel="${item.pkButtonId}" align="center">
					<td>
					<input type="hidden" class="oldButtonSort"  value="${item.buttonSort }" />
					<input type="text" class="newButtonSort" size="1" style="float: none;text-align:center " onkeyup="this.value=this.value.replace(/^((2[1-9])|([3-9]\d)|([1-9]\d{2,}))$|\D/g,'')" 
					onafterpaste="this.value=this.value.replace(/^((2[1-9])|([3-9]\d)|([1-9]\d{2,}))$|\D/g,'')" value="${item.buttonSort }" 
					data-value="${item.buttonSort}" data-id="${item.pkButtonId}" data-type="${item.buttonStatus}"/>
					</td>
					<td><a title="预览"  onclick='toLook_AppButton("${item.buttonPicUrl}","${item.buttonUrl}","${item.buttonType}")'>缩略图</a></td>
					<td>${item.buttonName}</td>
					<td><fmt:formatDate value="${item.buttonUpdatedate }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>
						<c:if test="${item.buttonStatus ==1}">开启</c:if> 
						<c:if test="${item.buttonStatus ==3}">关闭</c:if> 
					</td>
					<td title="${item.buttonDesc}">${item.buttonDesc}</td>
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
	$(".newButtonSort").each(function(){
		var type = $(this).attr("data-type");//如果是关闭状态 播放顺序为空 切不可输入数字
		if(type==3){
			$(this).hide(); 
		}
	});
}

function toLook_AppButton(i,j,z) {
	 $("#img_button").attr("name",""); 
	 $("#img_button").attr("src",i);
	 if(j!=""){
		 if(z==2){
			 $("#img_button").attr("name",j) 
		 }
	 }
	 $("#float_button").show();
	 
}	

function lookAppButton(j) {
	var url = $.trim(j);
	if (url != "") {
		window.open(url);
	}
}	
function hiddens_button() {
	$("#float_button").hide();
}
$("#editButtonOrder").click(function(){
	var buttonList = new Array();
	var i = 0;
	var flag = 0;
	$(".newButtonSort").each(function(){
		var newButtonSort = $(this).val();
		var type = $(this).attr("data-type");
		if(newButtonSort==0){
			alertMsg.error("播放顺序不能为0");
			flag=1;
		}
		if(type=="1"){
			buttonList[i]=newButtonSort;
			i++;
		}
	});
	if(flag==1){
		return;
	}
	if(isRepeat(buttonList)){
		alertMsg.error("播放循序重复");
		return;
	}else {
		$(".newButtonSort").each(function(){
			var newButtonSort = $(this).val();
			var oldButtonSort =$(this).attr("data-value");
			var pkButtonId =$(this).attr("data-id");
			var type = $(this).attr("data-type");
			if(newButtonSort!=oldButtonSort && type=="1"){
				changeSortAjax(pkButtonId,newButtonSort);
			}else{
				navTab.reloadFlag("listAppButton");
			}
		});
	}
});
function changeSortAjax(a,b){
	$.ajax({
		type : 'post',
		url : basepath+ "/admin/appButton/editButtonOrder.do",
		dataType : "json",
		data :{
			pkButtonId:a,
			newButtonSort:b
		},
		success : function(datas) {
			if(datas.statusCode==200){
				return;
			}else{
				alertMsg.error(data.message);
			}
		}
	});
}

//判断开启的button播放顺序是否有重复
function isRepeat(arr){ 
	var hash = {}; 
	for(var i in arr) { 
	if(hash[arr[i]]&&arr[i]!=99) 
		return true; 
		hash[arr[i]] = true; 
	} 
	return false; 
	} 
</script>



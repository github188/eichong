<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/user/user-list.js" />
<script type="text/javascript">
	var webroot = "${webroot}";

	function ajaxDoneCallback(json) {
	}	
	  
	
</script>

<h2 class="contentTitle">
	<bmtag:message messageKey="绑定用户" />
</h2>
<div class="pageContent">
	<form method="post" action="userCard/bindCard.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<input type="hidden" name="pkUserCard" value="${card.pkUserCard}" />
			<input type="hidden" id="applyId" name="pkCardapplicationform" value=""/>
			<dl id="appUserf1">
				<c:if test="${card.ucPayMode==10||card.ucPayMode==11}">
						<dt>选择APP用户：</dt>
				</c:if>
				<c:if test="${card.ucPayMode==12||card.ucPayMode==13||card.ucPayMode==14}">
						<dt>选择大账户用户：</dt>
				</c:if>
				<dd>
					<input id="userId" name="org4.id" value="" type="hidden"/>
					<input id="userRealName" class="required" name="org4.orgName" type="text" readonly />
					<input type="hidden" id="cardType" name="org4.ucPayMode" value="${card.ucPayMode}" />
					<a class="btnLook" id='businessListLookupA' href="${webroot}/admin/userManager/businessListLookup.do?ucPayMode=${card.ucPayMode}"  lookupGroup="org4" callback="setApplyId()">查找带回</a>
					<c:if test="${card.ucPayMode == 10}">
						<input type="checkBox" onclick="setQuicklyApplyFlag(this)"/> 快捷发卡<b>（若不勾选，直接绑定用户卡申请记录）</b>
					</c:if>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="绑定用户"
						type="submit" id="formSubmitter" /></li>
				<li><bmtag:button messageKey="common.button.back" type="button"
						dwzClass="close" /></li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript">
function showCardApplicationform(){
	var userId=$("#userId").val();
	$.ajax({
		type:'POST', 
		url:basepath+"/admin/cardApply/getCardApplyListSelect.do", 
		data: {cafUserId:userId},
		dataType:'json',
		cache: false,
		success: function(datas) {
			if(datas.status==100){
				var data=datas.data;
				if(data&&data.length>0){
					$("#applyRecode").remove();
					var content='<dl id="applyRecode"><dt>申请记录：</dt><dd><select name="pkCardapplicationform" >';
					for(var i=0;i<data.length;i++){
						content+='<option value="'+data[i].pkCardapplicationform+'">记录ID:'+data[i].pkCardapplicationform+',姓名:'+data[i].cafRealName+",联系地址:"+data[i].cafAddress+"</option>";
					}
					content+='</select></dd></dl>';
					$("#appUserf1").after(content);
				}
			}else{
				alertMsg.error(datas.msg);
			}
			
        }
	});
}

function setApplyId(){
	var userId=$("#userId").val();
	$.ajax({
		type:'POST', 
		url:basepath+"/admin/cardApply/getCardApplyId.do", 
		data: {cafUserId:userId},
		dataType:'json',
		cache: false,
		success: function(datas) {
			if(datas.status==100){
				var data=datas.data;
				if(data&&data.length>0){
					$("#applyId").val(data);
				}
			}else{
				alertMsg.error(data.msg);
			}
			
        }
	});
}

var getUsersURL =  $("#businessListLookupA").attr("href");
function setQuicklyApplyFlag(this_e){
	if(this_e.checked){
		$("#businessListLookupA").attr("href",getUsersURL+"&isQuicklyApply=1");
	}else{
		$("#businessListLookupA").attr("href",getUsersURL);
	}
}
</script>
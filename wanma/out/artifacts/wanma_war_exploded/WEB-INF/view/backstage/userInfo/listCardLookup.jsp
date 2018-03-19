<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
	
	$.pdialog.resizeDialog({style: {width:900,height: 500}}, $.pdialog.getCurrent(), "");
	
	$("#binding").click(function(){
		var i =0;
		var pkUserCards = new Array();
		$(".pkUserCards").each(function(){
			var pkUserCard = $(this).val();
			if ($(this).attr('checked')){
				pkUserCards[i]=pkUserCard;
				i++;
			}
		});
		var userId = $("#userId").val();
		var userAccount = $("#userAccount").val();
		if(pkUserCards.length==0){
			alertMsg.error("卡号不能为空");
			return;
		}
		if(userAccount.length==11&&pkUserCards.length>1){
			alertMsg.error("普通用户只能绑1张");
			return;
		}
		bindingAjax(userId,pkUserCards.join(","));
		
	}) 
	function bindingAjax(a,b){
	$.ajax({
		type : 'post',
		url : basepath+ "/admin/userManager/bindingCard.do",
		dataType : "json",
		data :{
			userId:a,
			cardIds:b
		},
		success : function(datas) {
			if(datas.statusCode==200){
				$.pdialog.closeCurrent();
				navTab.reloadFlag("userManagerList");
			}else{
				alertMsg.error("绑定失败");
			}
		}
	});
	}
	
</script>
<div class="pageHeader"> 


<form id="pagerForm" method="post" action="${webroot}/admin/userManager/cardListLookup.do" onsubmit="return dwzSearch(this, 'dialog');"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<input type="hidden" name="userId" value="${user.userId }" id="userId"/>
	<input type="hidden" value="${user.userAccount }" id="userAccount"/>
	<div class="searchBar">
		<table class="searchContent">
		<tbody>
			<tr>
				<td>
					<span>手机号</span>
					<input name="userAccount" placeholder="请输入" value="${userCard.userAccount }"/>
				</td>
				<td>
					<span>外卡号</span>
					<input name="ucExternalCardNumber" placeholder="请输入" value="${userCard.ucExternalCardNumber}"/>
				</td>
				<td>
					<span>所属公司</span>
					<input name="cpyCompanyname" placeholder="请输入" value="${userCard.cpyCompanyname}"/>
				</td>
			</tr>
			<tr>
				<td>
					<span>卡类型</span>
					<select name="ucPayMode"  style="width:108px">
						<option value="">全部</option>
						<option value="10" ${userCard.ucPayMode == 10?"selected":""}>爱充普通公共储蓄卡</option>
						<option value="11" ${userCard.ucPayMode == 11?"selected":""}>爱充普通专属储蓄卡</option>
						<option value="12" ${userCard.ucPayMode == 12?"selected":""}>爱充企业信用卡</option>
						<option value="13" ${userCard.ucPayMode == 13?"selected":""}>爱充合作公共储蓄卡</option>
						<option value="14" ${userCard.ucPayMode == 14?"selected":""}>爱充合作专属储蓄卡</option>
					</select>
				</td>
				<td></td>
				<td >
					<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter"/>
					<bmtag:button messageKey="&nbsp;&nbsp;&nbsp;&nbsp;绑卡&nbsp;&nbsp;&nbsp;&nbsp;" type="button" id="binding"/>
				</td>
				
			</tr>
		</tbody>
		</table>
	</div>
</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="85" targetType="dialog">
		<thead>
		<tr>
			<th width="10"><input type="checkbox" group="pkUserinfos" class="checkboxCtrl" /></th>
			<th><bmtag:message messageKey="序号"/></th>
		    <th><bmtag:message messageKey="内卡号"/></th>
		    <th><bmtag:message messageKey="外卡号"/></th>
		    <th><bmtag:message messageKey="卡类型"/></th>
		    <th><bmtag:message messageKey="金额"/></th>
		    <th><bmtag:message messageKey="用户姓名"/></th>
		    <th><bmtag:message messageKey="手机号"/></th>
		    <th><bmtag:message messageKey="公司标示"/></th>
		    <th><bmtag:message messageKey="公司名称"/></th>
		    
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${userCardList}" var="item" varStatus="status">
			<tr target="pkUserCard" rel="${item.pkUserCard }" align="left">
				<td>
					<input name="pkUserCards" class="pkUserCards" value="${item.pkUserCard}" type="checkbox" />
				</td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${item.ucInternalCardNumber}</td>
				<td>${item.ucExternalCardNumber }</td>
				<td>
					<c:if test="${item.ucPayMode == 10}">
						爱充普通公共储蓄卡
					</c:if>
					<c:if test="${item.ucPayMode == 11}">
						爱充普通专属储蓄卡
					</c:if>
					<c:if test="${item.ucPayMode == 12}">
						爱充企业信用卡
					</c:if>
					<c:if test="${item.ucPayMode == 13}">
						爱充合作公共储蓄卡
					</c:if>
					<c:if test="${item.ucPayMode == 14}">
						爱充合作专属储蓄卡
					</c:if>
				</td>
				<td>${item.ucBalance}</td>
				<td>${item.normRealName }</td>
				<td>${item.userAccount }</td>
				<td>${item.ucCompanyNumber}</td>
				<td>${item.cpyCompanyname }</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>
			共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="dialog" totalCount="${pager.total}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>

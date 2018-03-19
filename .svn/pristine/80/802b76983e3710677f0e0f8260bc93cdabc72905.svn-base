<%@page import="com.wanma.model.TblUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/user/user-list.js" />
<script type="text/javascript">
$(function() {
	var userIdForShow="<%=((TblUser)session.getAttribute("user")).getUserId()%>";
	if(userIdForShow==8231){
	$(".pages").html("");
}
});
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
</script>
<div class="pageHeader">


<form id="pagerForm" method="post" action="userCard/userCardList.do" onsubmit="return navTabSearch(this);">
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<div class="searchBar">
		<table class="searchContent">
		<tbody>
			<tr>
				<td>
					<span>姓名</span>
					<input name="normRealName" placeholder="请输入" value="${userCard.normRealName}"/>
				</td>
				<td>
					<span>手机号</span>
					<input name="userAccount" placeholder="请输入" value="${userCard.userAccount}"/>&nbsp;&nbsp;&nbsp;
					<span>外卡号</span>
					<input name="ucExternalCardNumber" placeholder="请输入" value="${userCard.ucExternalCardNumber}"/>
				</td>
				<td align="right">
					<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter"/>
				</td>
				<td align="right">
					<bmtag:button messageKey="excel导出" type="button"
								onclick="exportSubmit('pagerForm','orderExport/cardInfoExport.do')" />
						</td>
			</tr>
			<tr>
				<td>
					<span>所属公司</span>
					<input name="cpyCompanyname" placeholder="请输入" value="${userCard.cpyCompanyname}"/>
				</td>
				<td>
					<span>状态</span>
					<select name="ucStatus"  style="width:108px">
						<option value="">全部</option>
						<option value="0" ${userCard.ucStatus == 0?"selected":""}>正常</option>
						<option value="1" ${userCard.ucStatus == 1?"selected":""}>挂失</option>
					</select>&nbsp;&nbsp;&nbsp;
					<span>卡类型</span>
					<select name="ucPayMode"  style="width:108px">
						<option value="">全部</option>
						<option value="10" ${userCard.ucPayMode == 10?"selected":""}>爱充普通公共储蓄卡</option>
						<option value="11" ${userCard.ucPayMode == 11?"selected":""}>爱充普通专属储蓄卡</option>
						<option value="12" ${userCard.ucPayMode == 12?"selected":""}>爱充企业信用卡</option>
						<option value="13" ${userCard.ucPayMode == 13?"selected":""}>爱充合作公共储蓄卡</option>
						<option value="14" ${userCard.ucPayMode == 14?"selected":""}>爱充合作专属储蓄卡</option>
					</select>&nbsp;&nbsp;&nbsp;
					<span>是否绑定用户</span>
					<select name="bindFlag"  style="width:108px">
						<option value="">全部</option>
						<option value="2" ${userCard.bindFlag == 2?"selected":""}>未绑定</option>
						<option value="1" ${userCard.bindFlag == 1?"selected":""}>已绑定</option>
					</select>
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
			<li>
				<%--<bmtag:link  authUrl="userCard/bindCardUi.do"--%>
					 <%--rel="cardOperate" id="cardOperate" messageKey="绑定用户" dwzClass="edit" onclick="checkCardInfo(this)" />--%>
				<%--<bmtag:link authUrl="userCard/cardLoss.do"--%>
				 	 <%--rel="cardOperate" id="cardOperate" messageKey="挂失" dwzClass="edit" onclick="cardLoss(this)"/>--%>
                <bmtag:link isAuth="true" authUrl="userCard/bindCardUi.do"
								rel="cardOperate" id="cardOperate" messageKey="绑定用户" dwzClass="edit" onclick="checkCardInfo(this)"/>
				<bmtag:link isAuth="true" target="ajaxTodo" href="userCard/cardLoss.do?pkUserCard={id}"
					 rel="reportedLoss" id="reportedLoss" messageKey="挂失" dwzClass="edit" altKey="请确认要挂失充电卡吗？挂失后，不可再使用" />
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="136">
		<thead>
		<tr align="center" >
			<th width="10"><input type="checkbox" group="ids"
					class="checkboxCtrl" /></th>
			<th><bmtag:message messageKey="序号"/></th>
		    <th><bmtag:message messageKey="内卡号"/></th>
		    <th><bmtag:message messageKey="外卡号"/></th>
		    <th><bmtag:message messageKey="卡类型"/></th>
		    <th><bmtag:message messageKey="金额"/></th>
		    <th><bmtag:message messageKey="用户姓名"/></th>
		    <th><bmtag:message messageKey="手机号"/></th>
		    <th><bmtag:message messageKey="公司标识"/></th>
		    <th><bmtag:message messageKey="公司名称"/></th>
		    <th><bmtag:message messageKey="状态"/></th>
		    <th><bmtag:message messageKey="绑定时间"/></th>
        </tr>
		</thead>
		<tbody>
		<c:forEach items="${userCardList}" var="card" varStatus="status">
			<tr target="id" rel="${card.pkUserCard}" rel-data="${card.ucUserId}" rel-phone="${card.userAccount}" rel-cardType="${card.ucPayMode}" align="center">
				<td>
					<input name="ids"  value="${card.pkUserCard}"
						type="checkbox" />
				</td>
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<td>${card.ucInternalCardNumber}</td>
				<td>${card.ucExternalCardNumber}</td>
				<td>
					<c:if test="${card.ucPayMode == 10}">
						爱充普通公共储蓄卡
					</c:if>
					<c:if test="${card.ucPayMode == 11}">
						爱充普通专属储蓄卡
					</c:if>
					<c:if test="${card.ucPayMode == 12}">
						爱充企业信用卡
					</c:if>
					<c:if test="${card.ucPayMode == 13}">
						爱充合作公共储蓄卡
					</c:if>
					<c:if test="${card.ucPayMode == 14}">
						爱充合作专属储蓄卡
					</c:if>
				</td>
				<td>${card.ucBalance}</td>
				<td>${card.normRealName}</td>
				<td>${card.userAccount}</td>
				<td>${card.ucCompanyNumber}</td>
				<td>${card.cpyCompanyname}</td>
				<td>
					<c:if test="${card.ucStatus == 0}">
						正常
					</c:if>
					<c:if test="${card.ucStatus == 1}">
						挂失
					</c:if>
				</td>
				<td>
					<fmt:formatDate value="${card.ucUpdateDate }" pattern="yyyy-MM-dd HH:mm:ss"/> 
				</td>
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
<script type="text/javascript">
function checkCardInfo(obj){
	var $box =navTab.getCurrentPanel();
	var ids = [];
	var trSelected = $box.find("tr.selected");
	var id=trSelected.attr("rel");
	var relData = trSelected.attr('rel-data');
	var cardStatus = trSelected.attr('rel-cardStatus');
	if(!id){
		alertMsg.error("请选择信息!");
		return;
	}
	if(relData&&relData!=0){
		alertMsg.error("一张卡只能关联一个用户，不可重复关联。");
	}else{
		navTab.openTab("bindCard", basepath+"/admin/userCard/bindCardUi.do?pkUserCard="+id+"&ucPayMode="+trSelected.attr("rel-cardType"), { title:"绑定用户", fresh:false});
	}
}


function cardLoss(obj){
	var $box =navTab.getCurrentPanel();
	var ids = [];
	var trSelected = $box.find("tr.selected");
	var id=trSelected.attr("rel");
	var relData = trSelected.attr('rel-data');
	if(!id){
		alertMsg.error("请选择信息!");
		return;
	}
	if(relData=="0"){
		alertMsg.error("该卡未绑定用户，不能挂失!");
		return;
	}
	alertMsg.confirm("请确认要挂失充电卡吗？挂失后，不可再使用", {
		okCall: function(){
			cardLossAjax(id);
		}
	});

}
function cardLossAjax(id){
	 $.ajax({
	         type: 'POST',
	         url:basepath+"/admin/userCard/cardLoss.do" ,
	         dataType: "json",
	         data:{pkUserCard:id},
	         contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	         success: function(data){
	        	 if(data.statusCode == "200"){
	        		alertMsg.info("挂失成功！");
	        		$("#formSubmitter").click();
	        	 }
		     },
	         error: function(XMLHttpRequest, textStatus, errorThrown) {

	         }
	    });
}
function cardFind(obj){
	var $box =navTab.getCurrentPanel();
	var ids = [];
	var trSelected = $box.find("tr.selected");
	var id=trSelected.attr("rel");
	var relData = trSelected.attr('rel-data');
	if(!id){
		alertMsg.error("请选择信息!");
		return;
	}
	alertMsg.confirm("请确认要取消挂失充电卡吗？取消挂失后，此卡可继续使用", {
		okCall: function(){
			validAuthCode(trSelected,'userCard/cardFind.do');
		}
	});

}

function validAuthCode(trSelected,actionUrl){
	var userPhone = trSelected.attr('rel-phone');
	var id=trSelected.attr("rel");
	$.ajax({
         type: 'POST',
         url: basepath+"/web/user/validPhone.do" ,
         dataType: "json",
         data:{phone:userPhone},
         contentType: "application/x-www-form-urlencoded; charset=UTF-8",
         success: function(data){
        	 if(data.statusCode == "200"){
             	 $.ajax({
     		         type: 'POST',
     		         url:basepath+"/web/user/sendMsg.do" ,
     		         dataType: "json",
     		         data:{phone:userPhone},
     		         contentType: "application/x-www-form-urlencoded; charset=UTF-8",
     		         success: function(data){
     		        	 if(data.statusCode == "200"){
     		        		alert("已向用户发送验证码，请联系获取！");
     		        		navTab.openTab("bindCard", basepath+"/admin/userCard/inputAuthCode.do?pkUserCard="+id+"&userAccount="+trSelected.attr('rel-phone')+"&actionUrl="+actionUrl, { title:"挂失", fresh:false});
     		        	 }
	     		     },
     		         error: function(XMLHttpRequest, textStatus, errorThrown) {

     		         }
     		    });
        	 }else{
        		 alert(data.message);
        	 }
         },
         error: function(XMLHttpRequest, textStatus, errorThrown) {

         }
    });
}
</script>
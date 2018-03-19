<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<div class="pageHeader">
	<form id="pagerForm" method="post"
		action="userManager/userCouponList.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> 
		<input type="hidden" name="keywords" value="${pager.keywords}" /> 
		<input type="hidden" id="pageNum" name="pageNum" value="${pager.pageNum}" /> 
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td ><label style="width:50px;padding:0 0 0 5px;"><bmtag:message
									messageKey="状态" /> </label>
						</td>
						<td><select name="cpStatus" class="select_Style" id="covaLimitation">
								<option value="" ${userCoupon.cpStatus==""
									? 'selected="selected"' : ''}>全部</option>
								<option value="1" ${userCoupon.cpStatus=="1"
									? 'selected="selected"' : ''}>未兑换</option>
								<option value="2" ${userCoupon.cpStatus==2
									? 'selected="selected"' : ''}>已兑换</option>
								<option value="3" ${userCoupon.cpStatus==3
									? 'selected="selected"' : ''}>已使用</option>
								<option value="4" ${userCoupon.cpStatus==4
									? 'selected="selected"' : ''}>已过期</option>
						</select>
						</td>
						
						<td ><label style="width:40px;padding:0 0 0 5px;"><bmtag:message
									messageKey="活动类型" /> </label>
						</td>
						<td><select name="actType" class="select_Style" onchange="getSelect(this,'userActivity')" id="covaStutas">
						<option value="3" ${userCoupon.actType==""
									? 'selected="selected"' : ''}>全部</option>
								<option value="2" ${userCoupon.actType=="2"
									? 'selected="selected"' : ''}>渠道活动</option>
								<option value="1" ${userCoupon.actType==1
									? 'selected="selected"' : ''}>用户活动</option>
									
						</select>
						</td>
						
						<td ><label style="width:40px;padding:0 0 0 5px;"><bmtag:message
									messageKey="活动" /> </label>
						</td>
						<td><select  name="pkActivity"  id="userActivity" style="width:130px;">
							<option value="">全部</option>
							<c:forEach items="${activityList}" var="activity">
								<option value="${activity.pkActivity}" ${activity.pkActivity==userCoupon.pkActivity ? 'selected="selected"' : ''}>${activity.actActivityname}</option>
							</c:forEach>
						</select>
						</td>
						<td ><label style="width:40px;padding:0 0 0 5px;"><bmtag:message
									messageKey="手机号" /> </label>
						</td>
						<td><input name="userPhone"
							value="${userCoupon.userPhone}" /></td>
							
						<td ><label style="width:40px;"><bmtag:message
									messageKey="兑换码" /> </label>
						</td>
						<td><input name="couponCode"
							value="${userCoupon.couponCode}" /></td>
						</td>
						
						<td align="right"><bmtag:button
								messageKey="common.button.search" type="submit"
								id="formSubmitter" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="85">
		<thead>
			<tr align="center">
				<th><bmtag:message messageKey="手机号" /></th>
				<th><bmtag:message messageKey="用户姓名" /></th>
				<th><bmtag:message messageKey="兑换码" /></th>
				<th><bmtag:message messageKey="优惠券状态" /></th>
				<th><bmtag:message messageKey="获取时间" /></th>
				<th><bmtag:message messageKey="使用时间" /></th>
				<th><bmtag:message messageKey="活动类型" /></th>
				<th><bmtag:message messageKey="活动名称" /></th>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userCouponList}" var="userCoupon" varStatus="status">
				<tr target="id" rel="" data-state="" align="center">
					<td>${userCoupon.userPhone} </td>
					<td>${userCoupon.userName}</td>
					<td>${userCoupon.couponCode} </td>
					<td><c:if test="${userCoupon.cpStatus == '1'}">未兑换</c:if>
						<c:if test="${userCoupon.cpStatus == '2'}">已兑换</c:if>
						<c:if test="${userCoupon.cpStatus == '3'}">已使用</c:if>
						<c:if test="${userCoupon.cpStatus == '4'}">已过期</c:if>
					</td>
					<td>${userCoupon.cpBeginDate}</td>
					<td>${userCoupon.cpUpdatedate}</td>
					<td>
						<c:if test="${userCoupon.actType == '1'}">用户活动</c:if>
						<c:if test="${userCoupon.actType == '2' }">渠道活动</c:if>
					</td>
					<td>${userCoupon.actName}</td>
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
<script>
function getSelect(obj,selectId){
	 if(obj.value==""){
		return ;
	} 
	 $.ajax({
			type:'POST', 
			url:basepath+"/admin/userManager/getActivityList.do", 
			data: {state:obj.value},
			dataType:'json',
			cache: false,
			success: function(datas) {
				if(datas.status==100){
					var data=datas.data;
					var content='<option value="">全部</option>';
					for(var i=0;i<data.length;i++){
						content+='<option value="'+data[i].pkActivity+'">'+data[i].actActivityname+'</option>';
					}
					 $("#"+selectId).html(content);
				}else{
					alertMsg.error(datas.msg);
				}
				
           }
		});
}
</script>

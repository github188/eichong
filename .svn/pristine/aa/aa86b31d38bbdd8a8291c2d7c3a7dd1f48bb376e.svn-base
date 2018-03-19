<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript">
	var webroot = "${webroot}";
	function checkDetails(){
		
	}
</script>
<div class="pageHeader">
<form id="pagerForm" method="post" action="moneyRecord/getMoneyRecordList.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<div class="searchBar">
		<table class="searchContent">
			<tbody>
				<tr>
				<!-- value="搜索关键字"  -->
					<td style=" padding-left: 25px;">
						<span>用户账号&nbsp;</span>
						<input name="phone" value="${params.user_account }" placeholder="搜索用户账户" />
					</td>				
					<!--  <td style="display: none;">
			            <span>消费时间&nbsp;</span>			           
			             <input id="startTime" name="startTime" value="${params.startTime }" class="date"  style="width:155px"
						 onClick="WdatePicker({el:'startTime',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'endTime\')}'})" >
			                                    至 <input id="endTime" name="endTime" value="${params.endTime }" class="date"  style="width:155px"
			             onClick="WdatePicker({el:'endTime',minDate:'#F{$dp.$D(\'startTime\')}'})">                     		            
		            </td>-->
					<td >
						<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter"/>
					</td>
				</tr>
			 </tbody>
		</table>
		<table class="searchContent">
				<tr style="color: green;">
					<td style="font-size:20px;  padding-left: 20px;line-height: 30px;">总充值:${totalRecharge.AllPaypuHi_Monetary}元</td>
					<td style="font-size:18px;">支付宝充值:${totalRecharge.aliPayPu_Monetary}元</td>
					<td style="font-size:18px;">微信充值:${totalRecharge.weChatPu_Monetary}元</td>
					<td style="font-size:18px;">平台人工充值:${totalRecharge.chargeCardPu_Monetary}元</td>
					<td style="font-size:18px;">换卡转账:${totalRecharge.TransferAccount_Monetary}元</td>
					<td style="font-size:18px;">7月活动送:${totalRecharge.activity_Monetary}元</td>
					<!-- <td style="font-size:18px;">银联充值:${totalRecharge.unionpayPu_Monetary}</td> -->
				</tr>
				<tr style="color: red;">
					<td style="font-size:20px;  padding-left: 20px;line-height: 30px;">总消费:${totalPurchase.AllConsume}元</td>
					<td style="font-size:18px;">充电消费:${totalPurchase.chargeConsume}元</td>
					<td style="font-size:18px;">预约消费:${totalPurchase.orderConsume}元</td>
					<!-- <td style="font-size:18px;">购物消费:${totalPurchase.shopConsume}</td> -->
				</tr>	
					<tr style="color: red;">
					<td style="font-size:20px;  padding-left: 20px;line-height: 30px;">总余额:${totalAccount.All_normal_account}元</td>
					
				</tr>		
		</table>
	</div>
</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="172">
			
		<thead>
			<tr align="center">					
				<th width="30" ><divmtag:message messageKey="序号" /></th>
				<th><bmtag:message messageKey="用户账号" /></th>
				<th><bmtag:message messageKey="昵称" /></th>
				<th><bmtag:message messageKey="真实姓名" /></th>
				<th><bmtag:message messageKey="总充值(元)" /></th>
				<th><bmtag:message messageKey="总消费(元)" /></th>
			   <th><bmtag:message messageKey="余额(元)" /></th>
	        </tr>
		</thead>		
		<tbody>
		 
		<c:forEach items="${userMoneyRecordList}" var="userMoneyRecord" varStatus="status">
			<tr target="puHi_UserId" rel="${userMoneyRecord.puHi_UserId }" align="center">			
				<td value="${userMoneyRecord.pk_PurchaseHistory}"  name="pk_PurchaseHistory" >${ status.index + 1 }</td>
				<td><a target="navTab"  href="<c:url value='/admin/moneyRecord/getUserMoneyRecordDetials.do?puHi_UserId=${userMoneyRecord.user_id }&all_account=${userMoneyRecord.norm_account }'/>" style='color:#0099FF'>${userMoneyRecord.user_account}</a></td>
				<td>${userMoneyRecord.usIn_UserName }</td>
				<td>
				<c:if test="${userMoneyRecord.usIn_FacticityName == ''}">---</c:if>
				<c:if test="${userMoneyRecord.usIn_FacticityName != ''}">${userMoneyRecord.usIn_FacticityName }</c:if>
				</td>
				<td>${userMoneyRecord.userTotal_recharge }</td>
				<td>${userMoneyRecord.userTotal_Purchase }</td>
				<td>${userMoneyRecord.norm_account }</td>
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

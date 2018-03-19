<%@page import="com.wanma.model.TblUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript">

function exportSubmit_coupon(formId,url){
	var form = navTab.getCurrentPanel().find("#"+formId);
	var oldUrl = form.attr("action");
	var oldSubmitMethod = form.attr("onsubmit");
	form.attr("onsubmit",null)
	form.attr("action",url);
	form.submit();
	form.attr("action",oldUrl);
	form.attr("onsubmit",oldSubmitMethod);
}

</script>
<div class="pageHeader">
	<form id="pagerForm" method="post" action="coupon/couponDetail.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<!-- value="搜索关键字"  -->
						<td style="align: left"><span>优惠码</span> <input
							name="cpCouponcode" value="${TblCoupon.cpCouponcode}"
							placeholder="优惠码" /></td>
						<td style="align: left"><span>编号&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<input name="pkCoupon" value="${TblCoupon.pkCoupon}"
							placeholder="编号" /></td>
						 <td style="align: left" ><span>活动类型&nbsp;</span><select
							name="cpActivitytype" id="cpActivitytype" class="select_Style" onchange="getSelect_coupon(this,'pkActivity')" style="width: 150px;">
									<option value="3">全部</option>
								<option value="2"
									${TblCoupon.cpActivitytype=="2"
									? 'selected' : ''}>渠道活动</option>
								
								<option value="1"
									${TblCoupon.cpActivitytype=="1"
									? 'selected' : ''}>用户活动</option>
								
						</select></td>
						<td style="align: left"><span>使用限制</span> <select
							name="cpLimitation" id="cpLimitation" class="select_Style"
							style="width: 150px;">
								<option value="">全部</option>
								<option value="1"
									${TblCoupon.cpLimitation=='1'
									? 'selected' : ''}>仅限交流电桩</option>
								<option value="2"
									${TblCoupon.cpLimitation=='2'
									? 'selected' : ''}>仅限直流电装</option>
								<option value="3"
									${TblCoupon.cpLimitation=='3'
									? 'selected' : ''}>不限充电桩</option>
						</select></td>
                       
					</tr>
					<tr>

						<td style="align: left"><span>状态&nbsp;&nbsp;&nbsp;</span> <select
							name="cpstates" id="cpstates" class="select_Style"
							style="width: 150px;">
							<option value="">全部</option>
							  <option value=1
									${TblCoupon.cpstates==1
									? 'selected' : ''}>未兑换未过期</option>
								<option value=2
									${TblCoupon.cpstates==2
									? 'selected' : ''}>未兑换已过期</option>
								<option value=3
									${TblCoupon.cpstates==3
									? 'selected' : ''}>已兑换已使用</option>
								<option value=4
									${TblCoupon.cpstates==4
									? 'selected' : ''}>已兑换未使用未过期</option>
								<option value=5
									${TblCoupon.cpstates==5
									? 'selected' : ''}>已兑换未使用已过期</option>	
						</select></td>

						<td style="align: left"><span>现金券品种</span> <select
							name="pkCouponvariety" class="select_Style" style="width: 150px;">
							<option value="">全部</option>
								<c:forEach var="item" items="${couponType}">
									<option value="${item.pkCouponvariety}"
										${item.pkCouponvariety==TblCoupon.pkCouponvariety?'selected="selected"' : ''}>${item.covaActivityname}</option>
								</c:forEach>
						</select></td>
						<td style="align: left"><span>活动&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<select name="pkActivity" id="pkActivity" style="width: 150px;" >
							<option value="">全部</option>
								<c:forEach var="item" items="${activityType}">
									<option value="${item.pkActivity}"
										${item.pkActivity==TblCoupon.pkActivity?'selected="selected"' : ''}>${item.actActivityname}</option>
								</c:forEach>
						</select></td>

						<td align="right"><bmtag:button
								messageKey="common.button.search" type="submit"
								id="formSubmitter_coupon" /></td>

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
				<bmtag:link isAuth="true" target="selectedTodo" href="coupon/removeCouponDetail.do"
					rel="ids" postType="string" altKey="如果优惠券未兑换，删除优惠券后，对应的优惠码会失效；如果优惠券已经兑换，删除优惠券后，用户APP上优惠券会消失。" dwzClass="delete" messageKey="批量删除" />
			</li>
			<li><a class="icon" targettype="navTab"
				target="dwzExport" onclick="exportSubmit_coupon('pagerForm','orderExport/couponDeatil.do')"> <span>导出EXCEL</span>
			</a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="132">
		<thead>
			<tr align="center">
				<th><input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th style="width:5%"><bmtag:message messageKey="编号" /></th>
				<th style="width:10%"><bmtag:message messageKey="现金券品种" /></th>
				<th style="width:10%"><bmtag:message messageKey="电桩限制" /></th>
				<th style="width:10%" ><bmtag:message messageKey="状态" /></th>
				<th style="width:10%"><bmtag:message messageKey="获取时间" /></th>
				<th style="width:10%"><bmtag:message messageKey="使用时间" /></th>
				<th style="width:10%"><bmtag:message messageKey="生效时间" /></th>
				<th style="width:10%"><bmtag:message messageKey="到期时间" /></th>
				<th style="width:7%"><bmtag:message messageKey="优惠码" /></th>
				<th style="width:8%"><bmtag:message messageKey="用户手机号" /></th>
				<th style="width:10%"><bmtag:message messageKey="活动名称" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${couponList}" var="coupon" varStatus="status">
				<tr target="id" rel="${coupon.pkCoupon}" align="center">
					<td><input name="ids" value="${coupon.pkCoupon}"
						type="checkbox"></td>
					<td>${coupon.pkCoupon}</td>
					<td>${coupon.covaActivityname}</td>
					<td><c:if test="${coupon.cpLimitation ==1}">仅限交流电桩</c:if> <c:if
							test="${coupon.cpLimitation ==2}">仅限直流电装</c:if> <c:if
							test="${coupon.cpLimitation ==0}">-</c:if> <c:if
							test="${coupon.cpLimitation ==3}">不限充电桩 </c:if></td>
					<td><c:if test="${coupon.cpstates ==1}">未兑换未过期 </c:if> <c:if
							test="${coupon.cpstates ==2}">未兑换已过期</c:if> <c:if
							test="${coupon.cpstates ==3}">已兑换已使用</c:if> <c:if
							test="${coupon.cpstates ==4}">已兑换未使用未过期</c:if> <c:if
							test="${coupon.cpstates ==5}">已兑换未使用已过期 </c:if>
							</td>
				<td><fmt:formatDate value="${coupon.cpBegindate }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${coupon.cpUpdatedate }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>		
					<td><fmt:formatDate value="${coupon.cpBegindate }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td><fmt:formatDate value="${coupon.cpEnddate }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${coupon.cpCouponcode}</td>
					<td>${coupon.userPhone}</td>
					<td>${coupon.actActivityname}</td>
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
$(function() {
	var userIdForShow="<%=((TblUser)session.getAttribute("user")).getUserId()%>";
	
if(userIdForShow==8231){
	$(".pages").html("");
}
});
function getSelect_coupon(obj,selectId){
	 if(obj.value==""){
		return;
	} 
	 $.ajax({
			type:'POST', 
			url:basepath+"/admin/coupon/searchActivityList.do", 
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



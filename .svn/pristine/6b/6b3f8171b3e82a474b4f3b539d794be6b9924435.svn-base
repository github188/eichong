<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<link href="<%=basePath%>/static/css/rateInfoDisplay.css"
	rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=basePath%>/static/js/other/rateInfoDisplay.js"></script>
</script>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json) {
	}
</script>
<div class="pageHeader">
<form id="pagerForm" method="post" action="finance/personChargeDetail.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<div class="searchBar">
		<table class="searchContent">
			<tbody>
				<tr>
				<!-- value="搜索关键字"  -->
					<td style="align:left">
						<span>手机号&nbsp;</span>
						<input name="userAccount" value="${params.userAccount}" placeholder="手机号" />
					</td>
					<td>
			            <span>充电时间&nbsp;</span>				             	 
			             <input id="startDate_f7" name="startDate" value="${params.startDate}" class="date"  style="width:155px"
						 onClick="WdatePicker({el:'startDate_f7',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'endDate_f7\')}'})">
			                                    至 <input id="endDate_f7" name="endDate" value="${params.endDate}" class="date"  style="width:155px"
			             onClick="WdatePicker({el:'endDate_f7',minDate:'#F{$dp.$D(\'startDate_f7\')}'})">                       	            
		            </td>
		             <td style="align:left">
		            <span>订单状态&nbsp;&nbsp;&nbsp;</span>
					<select name="chOrChargingStatus" class="select_Style" style="width:150px;" >
								<option value="">全部</option>
								<option value="1" ${params.chOrChargingStatus==1
									? 'selected="selected"' : ''}>待支付</option>
								<option value="2" ${params.chOrChargingStatus==2
									? 'selected="selected"' : ''}>支付完成</option>
								<option value="3" ${params.chOrChargingStatus==3
									? 'selected="selected"' : ''}>完成未结算</option>
						</select>
					</td>
		          </tr>
		          <tr>
		          	<td style="align:left">
						<span>姓&nbsp;&nbsp;&nbsp;名</span>
						<input name="normRealName" value="${params.normRealName}" placeholder="姓名" />
					</td>
		            <td style="align:left">
						<span>城市&nbsp;</span>
						<select class="provinceCode required"  id="provinceCodef7" name="provinceCode" next="cityCodef7"  style="float: none;width:130px;">
							<option value="">--请选择省份--</option>
							<c:forEach var="item" items="${proviceMap}">
								<option value="${item.key}"
									${item.key== params.provinceCode ? 'selected="selected"' : ''} >
									${item.value.PROVINCE_NAME}
								</option>
							</c:forEach>
						</select>
						<select id="cityCodef7" data-val="${params.cityCode}" name="cityCode" class="required" style="float: none;width:130px;">
							 <option value="">--请选择城市--</option>
						</select>
					</td>
					<td></td>
			
					<td align="right">
						<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter"/>
					</td>
			
					<td align="right">
						<bmtag:button messageKey="excel导出" type="button" onclick="exportSubmit('pagerForm','financeExcelReport/getPersonChargeDetailData.do')"/>
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
				<bmtag:link isAuth="true"  target="selectedTodo" href="finance/orderAccounts.do?type=personChargeDetail"
			 		rel="ids"  postType="string" altKey="确定结算吗？" dwzClass="edit" messageKey="结算" />
			</li>
		</ul>
	</div>
		<!--浮动的尖峰平谷汇率-->
	<div id="JFPG_BOX_DIV_PersonP"
		style="height: auto; position: absolute; right: 20px; display: none">
		<ul id="JFPG_BOX_DIV_Person" class="JFPG_BOX">
		</ul>
		<span class="closeP" onclick="closePersonChargeWindow();">X</span>
	</div>
	<table class="table" width="100%" layoutH="137">
		<thead>
			<tr align="center">
			<th><input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				
				<th><bmtag:message messageKey="城市" /></th>
				<th><bmtag:message messageKey="手机号" /></th>
				<th><bmtag:message messageKey="姓名" /></th>
				<th><bmtag:message messageKey="电桩编号" /></th>
				<th><bmtag:message messageKey="充电订单编号" /></th>
				<th><bmtag:message messageKey="订单状态" /></th>
				<th><bmtag:message messageKey="总电量(度数)" /></th>
				<th><bmtag:message messageKey="收益金额(元)" /></th>
				<th><bmtag:message messageKey="充电电费(元)" /></th>
				<th><bmtag:message messageKey="充电服务费(元)" /></th>
				<th><bmtag:message messageKey="实际优惠金额(元)" /></th>
				<th><bmtag:message messageKey="充电开始时间" /></th>
				<th><bmtag:message messageKey="充电结束时间" /></th>
				<th><bmtag:message messageKey="费率" /></th>
	        </tr>
		</thead>		
		<tbody>
		<c:forEach items="${list}" var="obj" varStatus="status">
			<tr target="objId" align="center">
			<td><input name="ids" value="${obj['主键']}" 
						type="checkbox">
					</td>
				<td>${obj['城市']}</td>
				<td>${obj['手机号']}</td>
				<td>${obj['姓名']}</td>
				<td>${obj['电桩编号']}</td>
				<td>${obj['充电订单编号']}</td>
			    <td>
	            <c:if test="${obj['订单状态'] ==1}">待支付</c:if>
			    <c:if test="${obj['订单状态'] ==2}">支付成功</c:if>
				<c:if test="${obj['订单状态'] ==3}">完成未结算</c:if>
			    </td>
				<td>${obj['总电量(度数)']}</td>
				<td>${obj['收益金额(元)']}</td>
				<td>${obj['充电电费(元)']}</td>
				<td>${obj['充电服务费(元)']}</td>
				<td>${obj['实际优惠金额(元)']}</td>
				<td>${obj['充电开始时间']}</td>
				<td>${obj['充电结束时间']}</td>
				<td><div class="FengZhi" data-index="${status.index + 1}"
							data-rateTime='${obj.chRe_QuantumDate}'
							data-rateJ='${obj.chRe_JPrice}'
							data-rateF='${obj.chRe_FPrice}'
							data-rateP='${obj.chRe_PPrice}'
							data-rateG='${obj.chRe_GPrice}'
							data-rateS='${obj.chRe_ServiceCharge}'
							data-flag='2'
							onclick="makeRateInfoWindow(this)">查看费率</div></td>
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
$(function(){
	loadCity($("#provinceCodef7"));
});
</script>

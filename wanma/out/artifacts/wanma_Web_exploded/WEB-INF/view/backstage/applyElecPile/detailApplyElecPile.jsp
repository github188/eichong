<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript">
	var webroot = "${webroot}";

	function ajaxDoneCallback(json) {
	}
	
/* 	function getNowFormatDate(){
	    var day = new Date();
	    var Year = 0;
	    var Month = 0;
	    var Day = 0;
	    var CurrentDate = "";
	    Year= day.getFullYear();//支持IE和火狐浏览器.
	    Month= day.getMonth()+1;
	    Day = day.getDate();
	    CurrentDate += Year;
	    if (Month >= 10 ){
	     CurrentDate += Month;
	    }
	    else{
	     CurrentDate += "0" + Month;
	    }
	    if (Day >= 10 ){
	     CurrentDate += Day ;
	    }
	    else{
	     CurrentDate += "0" + Day ;
	    }
	    return CurrentDate;
	 } */
	
/* 	//获取车型
	function getCarTypeName(cocoContent){
		if(cocoContent=="0"){
			$("#usinCarinfoId").html('<option value="0">--请选择车型--</option>');
			return;
		}
		$.get(webroot + "/admin/cartype/getCarTypeName.do?carInfoCompanyId="+cocoContent.value+"",{}, function(data) {
			var json = eval("(" + data + ")");
			console.debug(json);
			console.debug($(".editUnbrandadd"));
			$(".editUnbrandadd").html("");
			 $("<option value='0'>-请选择-</option>").appendTo(
					$(".editUnbrandadd"));
			$.each(json, function(i) { 
				$(  
						
						"<option value='"+json[i].pkCarinfo+"'>"
								+ json[i].carinfoStylename + "</option>").appendTo(
						$(".editUnbrandadd"));
			}); 
		});
	} */
</script>
<h2 class="contentTitle">
	<bmtag:message messageKey="查看详情" />
</h2>
<div class="pageContent">
	<form method="post" 
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dt>
					<bmtag:message messageKey="在线客服QQ" />
				</dt>
				<dd>${tblApplyElecPile.apEpServiceQQ}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="联系人姓名" />
				</dt>
				<dd>${tblApplyElecPile.apEpUserName}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="联系电话" />
				</dt>
				<dd>${tblApplyElecPile.apEpUserPhone}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="充电点地址" />
				</dt>
				<dd>${tblApplyElecPile.apEpPointAddress}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="处理状态" />
				</dt>
				<dd> 
					<c:choose><c:when test="${tblApplyElecPile.apEpProcessState == 1}">提交申请</c:when><c:when test="${tblApplyElecPile.apEpProcessState == '2'}">勘场施工</c:when><c:when test="${tblApplyElecPile.apEpProcessState == '3'}">入网爱充</c:when><c:when test="${tblApplyElecPile.apEpProcessState == '4'}">建站成功</c:when><c:when test="${tblApplyElecPile.apEpProcessState == '10'}">已驳回</c:when></c:choose>				 						 												 					
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="驳回原因" />
				</dt>
				<dd><c:choose><c:when test="${tblApplyElecPile.apEpProcessState == 10}">${tblApplyElecPile.apEpDealReason}</c:when><c:when test="${tblApplyElecPile.apEpProcessState != 10}">未驳回，无原因</c:when></c:choose>
					<span class="info"></span>
				</dd>
			</dl>
			
			<dl>
				<dt>
					<bmtag:message messageKey="申请日期" />
				</dt>
				<dd><fmt:formatDate value="${tblApplyElecPile.apEpCreatedate }" pattern="yyyy-MM-dd"/>						 
					<span class="info"></span>
				</dd>
			</dl> 
			
			<dl>
				<dt>
					<bmtag:message messageKey="品牌" />
				</dt>
				<dd>						
					<c:forEach items="${brandList}" var="brand">
						<c:if test="${brand.pk_carCompany == tblApplyElecPile.apEpCarcompanyId}">${brand.carCompany_Name }</c:if> 
					</c:forEach>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="车型" />
				</dt>
				<dd>
						<c:forEach items="${carTypeList}" var="carType">
						<c:if test="${carType.pkCarinfo == tblApplyElecPile.apEpCarinfoId}">${carType.carinfoStylename}</c:if> 
						</c:forEach>
				</dd>
			</dl>
			
			
			
			<dl>
				<dt>
					<bmtag:message messageKey="物业性质" />
				</dt>
				<dd><c:if test="${tblApplyElecPile.apEpPropertyNature == 1}">零售</c:if><c:if test="${tblApplyElecPile.apEpPropertyNature == 2}">酒店</c:if><c:if test="${tblApplyElecPile.apEpPropertyNature == 3}">度假村</c:if>	<c:if test="${tblApplyElecPile.apEpPropertyNature == 4}">娱乐场所</c:if><c:if test="${tblApplyElecPile.apEpPropertyNature == 5}">办公写字楼</c:if><c:if test="${tblApplyElecPile.apEpPropertyNature == 6}">住宅</c:if><c:if test="${tblApplyElecPile.apEpPropertyNature == 7}">汽车服务</c:if><c:if test="${tblApplyElecPile.apEpPropertyNature == 8}">综合体</c:if><c:if test="${tblApplyElecPile.apEpPropertyNature == 9}">市政</c:if><c:if test="${tblApplyElecPile.apEpPropertyNature == 10}">收费停车场</c:if><c:if test="${tblApplyElecPile.apEpPropertyNature == 11}">餐厅</c:if>
				  
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="是否为标准停车位" />
				</dt>
				<dd><c:if test="${tblApplyElecPile.apEpStandardParking == 1}">是</c:if><c:if test="${tblApplyElecPile.apEpStandardParking == 2}">否</c:if><c:if test="${tblApplyElecPile.apEpStandardParking == 0}">非必填项,用户未填</c:if>	
					<span class="info"></span>
				</dd>
			</dl>
						

			<dl>
				<dt>
					<bmtag:message messageKey="车位位置" />
				</dt>
				<dd>
					<c:if test="${tblApplyElecPile.apEpParkingPlace == 1}">室内</c:if><c:if test="${tblApplyElecPile.apEpParkingPlace == 2}">室外</c:if>
						 <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="是否对外开放" />
				</dt>
				<dd><c:if test="${tblApplyElecPile.apEpOpenShare == 1}">是</c:if><c:if test="${tblApplyElecPile.apEpOpenShare == 2}">部分开放</c:if><c:if test="${tblApplyElecPile.apEpOpenShare == 3}">否</c:if>			  							
						 <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="是否车位硬化" />
				</dt>
				<dd><c:if test="${tblApplyElecPile.apEpParkingHard == 1}">是</c:if><c:if test="${tblApplyElecPile.apEpParkingHard == 2}">否</c:if><c:if test="${tblApplyElecPile.apEpParkingHard == 0}">非必填项,用户未填</c:if>					  	
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="车位所属权" />
				</dt>
				<dd>
					<c:if test="${tblApplyElecPile.apEpParkingOwner == 1}">自有</c:if><c:if test="${tblApplyElecPile.apEpParkingOwner == '2'}">租赁(备注:${tblApplyElecPile.apEpParkingOwnerText})</c:if><c:if test="${tblApplyElecPile.apEpParkingOwner == '3'}">其他(备注:${tblApplyElecPile.apEpParkingOwnerText})</c:if><c:if test="${tblApplyElecPile.apEpParkingOwner == ''}">非必填项,用户未填</c:if> 
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="电容量" />
				</dt>
				<dd>
				<c:if test="${tblApplyElecPile.apEpCapacitance == ''}">用户未填</c:if>
				<c:if test="${tblApplyElecPile.apEpCapacitance != ''}">${tblApplyElecPile.apEpCapacitance}</c:if>					
						 <span class="info">
						</span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="电源位置" />
				</dt>
				<dd>
					<c:if test="${tblApplyElecPile.apEpPowerPosition == 1}">地面</c:if><c:if test="${tblApplyElecPile.apEpPowerPosition == '2'}">地下</c:if><c:if test="${tblApplyElecPile.apEpPowerPosition == '3'}">其他(备注:${tblApplyElecPile.apEpPowerPositionText})</c:if><c:if test="${tblApplyElecPile.apEpPowerPosition == ''}">非必填项,用户未填</c:if> 
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="电源到车位距离" />
				</dt>
				<dd>
					<c:if test="${tblApplyElecPile.apEpDistance == 1}">小于等于30M</c:if><c:if test="${tblApplyElecPile.apEpDistance == '2'}">大于30M</c:if><c:if test="${tblApplyElecPile.apEpDistance == '3'}">其他(备注:${tblApplyElecPile.apEpDistanceText}M)</c:if><c:if test="${tblApplyElecPile.apEpDistance == ''}">非必填项,用户未填</c:if>
						<span class="info"></span>
				</dd>
			</dl>


		</div>
		<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.back" type="button"
						dwzClass="close" /></li>
			</ul>
		</div>
	</form>
</div>
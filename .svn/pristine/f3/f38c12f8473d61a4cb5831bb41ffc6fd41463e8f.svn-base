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
				<dd>${tblApplyPartner.apPaServiceQQ}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="联系人姓名" />
				</dt>
				<dd>${tblApplyPartner.apPaUserName}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="联系电话" />
				</dt>
				<dd>${tblApplyPartner.apPaUserPhone}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="站点名称" />
				</dt>
				<dd>${tblApplyPartner.apPaPointName}
					<span class="info"></span>
				</dd>
			</dl>
			
			<dl>
				<dt>
					<bmtag:message messageKey="建桩地址" />
				</dt>
				<dd>${tblApplyPartner.apPaStationAddr}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="站点类型" />
				</dt>
				<dd><c:if test="${tblApplyPartner.apPaStationType == 1}">家庭充电点</c:if><c:if test="${tblApplyPartner.apPaStationType == 2}">目的地充电点</c:if>
					<c:if test="${tblApplyPartner.apPaStationType == 3}">其他(备注:${tblApplyPartner.apPaStationTypeText})</c:if>
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="是否已安装电桩" />
				</dt>
				<dd><c:if test="${tblApplyPartner.apPaInstallation == 1}">是</c:if><c:if test="${tblApplyPartner.apPaInstallation == 2}">否</c:if>			
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="交流桩数量" />
				</dt>
				<dd> 
					${tblApplyPartner.apPaAcPileNum}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="直流装数量" />
				</dt>
				<dd> 
					${tblApplyPartner.apPaDcPileNum}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="开放时间" />
				</dt>
				<dd> 
				<c:if test="${tblApplyPartner.apPaOpenTimeWorkDay != ''}">					
						工作日:${tblApplyPartner.apPaOpenTimeWorkDay}	&nbsp;&nbsp;										
				</c:if>
				<c:if test="${tblApplyPartner.apPaOpenTimeHoliday != ''}">					 
						节假日:${tblApplyPartner.apPaOpenTimeHoliday}	&nbsp;&nbsp;					
				</c:if>
				<c:if test="${tblApplyPartner.apPaOpenTimeHoliday == '' && tblApplyPartner.apPaOpenTimeWorkDay == ''}"> 					
						用户未填										
				</c:if>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="充电费用" />
				</dt>
				<dd>
				<c:if test="${tblApplyPartner.apPaChargingFee != ''}">					 
						电费:${tblApplyPartner.apPaChargingFee}&nbsp;&nbsp;						
				</c:if>
				<c:if test="${tblApplyPartner.apPaServiceFee != ''}">
					服务费:${tblApplyPartner.apPaServiceFee}&nbsp;&nbsp;					
				</c:if>
				<c:if test="${tblApplyPartner.apPaParkingFee != ''}">
					停车费:${tblApplyPartner.apPaParkingFee}&nbsp;&nbsp;					
				</c:if>
				<c:if test="${tblApplyPartner.apPaParkingFee == ''  && tblApplyPartner.apPaServiceFee == '' && tblApplyPartner.apPaServiceFee ==''}">
					用户未填												
				</c:if>		 						 												 					
				</dd>
			</dl>
			<dl>
			<dt>
					<bmtag:message messageKey="分享图片" />
				</dt>
				<dd>
					<c:forEach items="${images}" var="img" varStatus="status">
						<img src="${path}/${img}" style="width:150px;height:150px;"/>
					</c:forEach>				
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="处理状态" />
				</dt>
				<dd> 
					<c:choose><c:when test="${tblApplyPartner.apPaProcessState == 1}">提交申请</c:when><c:when test="${tblApplyPartner.apPaProcessState == '2'}">信息审核</c:when><c:when test="${tblApplyPartner.apPaProcessState == '3'}">合作洽谈</c:when><c:when test="${tblApplyPartner.apPaProcessState == '4'}">达成合作</c:when><c:when test="${tblApplyPartner.apPaProcessState == '10'}">已驳回</c:when></c:choose>				 						 												 					
					<span class="info"></span>
				</dd>
			</dl>
			
			<dl>
				<dt>
					<bmtag:message messageKey="驳回原因" />
				</dt>
				<dd><c:choose>
				<c:when test="${tblApplyPartner.apPaProcessState == 10}">${tblApplyPartner.apPaDealReason}</c:when>
				<c:when test="${tblApplyPartner.apPaProcessState != 10}">未驳回，无原因</c:when>
				</c:choose>
					<span class="info"></span>
				</dd>
			</dl>
			
			<dl>
				<dt>
					<bmtag:message messageKey="申请日期" />
				</dt>
				<dd><fmt:formatDate value="${tblApplyPartner.apPaCreatedate }" pattern="yyyy-MM-dd"/>						 
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
<%@page import="com.wanma.model.TblUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="pageHeader">
<form id="pagerForm" method="post" action="applyBuildElecPile/applyBuildElecPileList.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<div class="searchBar">
		<table class="searchContent" >
			<tbody>
				<tr >
					 <td>
					 	联系人姓名&nbsp;
						<span><input  style="align:right"  name="aepName" value="${tblApplyBuildElecPile.aepName}" placeholder="搜索联系人姓名" style="width:162px"/></span>									
					</td>
					 <td>
					 
						<span>联系人电话&nbsp;</span>
						<span><input  style="align:right"  name="aepPhone" value="${tblApplyBuildElecPile.aepPhone}" placeholder="联系人电话" style="width:162px"/></span>									
					</td>
					 <td>
						分类&nbsp;
						<select name="aepCType" style="align:left;width:162px">
								<option value="">全部分类</option>
								<option value="1" ${tblApplyBuildElecPile.aepCType == 1 ? 'selected="selected"' : ''} >个人</option>
						        <option value="2" ${tblApplyBuildElecPile.aepCType == 2 ? 'selected="selected"' : ''} >企业</option>				        									
						</select>
					</td>
					 <td>
						审批状态&nbsp;
						<select name="aepStatus" style="align:left;width:162px">
								<option value="">全部状态</option>
								<option value="1" ${tblApplyBuildElecPile.aepStatus == 1 ? 'selected="selected"' : ''} >提交申请</option>
						        <option value="2" ${tblApplyBuildElecPile.aepStatus == 2 ? 'selected="selected"' : ''} >勘探现场</option>
						        <option value="3" ${tblApplyBuildElecPile.aepStatus == 3 ? 'selected="selected"' : ''} >现场施工</option>
						        <option value="4" ${tblApplyBuildElecPile.aepStatus == 4 ? 'selected="selected"' : ''} >建站成功</option>					        									
						</select>
					</td>
				</tr>
				<tr>
					
				</tr>
						<td>
							区域选择
							<select id="electricSelProvince" name="aepPCode" class="required" style="float: none;width:130px;">
								<option value="">--请选择省份--</option>
								<c:forEach var="item" items="${proviceMap}">
									<option value="${item.key}"
										${item.key== tblApplyBuildElecPile.aepPCode ? 'selected="selected"' : ''} >
										${item.value.PROVINCE_NAME}
									</option>
								</c:forEach>
							</select>
							<select id="electricSelCity" name="aepCCode" class="required" style="float: none;width:130px;">
								 <option value="">--请选择城市--</option>
								 <c:forEach var="item" items="${cityList}">
									<option value="${item.CITY_ID}"
										${item.CITY_ID==tblApplyBuildElecPile.aepCCode ? 'selected="selected"' : ''} >
										${item.CITY_NAME}
									</option>
								</c:forEach>
							</select>
							<select id="electricSelDistrict" name="aepACode" class="required"  style="float: none;width:130px;">
								<option value="">--请选择区/县--</option>
								<c:forEach var="item" items="${areaList}">
									<option value="${item.area_id}"
										${item.area_id==tblApplyBuildElecPile.aepACode ? 'selected="selected"' : ''} >
										${item.area_name}
									</option>
								</c:forEach>
							</select>	
						</td>
					<td>
					 	建桩地址&nbsp;
						<span><input  style="align:right"  name="aepAddr" value="${tblApplyBuildElecPile.aepAddr}"  style="width:162px"/></span>									
					</td>
					<td align="left">
						<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter"/>
					</td>
			 </tbody>
		</table>
	</div>
</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="115">
		<thead>
			<tr align="center">
				<th width="10"><input type="checkbox" group="ids"
					class="checkboxCtrl" />
				</th>							
				<th>联系人姓名</th>
				<th>联系人电话</th>
				<th>建桩地址</th>
				<th>申请类型</th>
				<th>分类</th>
				<th>是否自有车位</th>
				<th>车位数量</th>
				<th>设备需求</th>
				<th>物业是否同意安装</th>
				<th>电力是否通过报备</th>
				<th>安装地点</th>
				<th>申请时间</th>
				<th>审批状态</th>								
				<th>审批操作</th>
	        </tr>
		</thead>
		<tbody>
		<c:forEach items="${applyBuildElecPileList}" var="applyEp" varStatus="status">
			<tr target="pkApplyElecPile" rel="${applyEp.aepId }" align="center">				
				<td><input name="ids" value="${applyEp.aepId}" status = "${applyEp.aepStatus}" 
						type="checkbox">
					</td>
				<td>${applyEp.aepName }</td>						
				<td>${applyEp.aepPhone }</td>	
				<td>${applyEp.aepAddr }</td>			
				<td><c:choose>
						<c:when test="${applyEp.aepAType == '1'}">
							免费
						</c:when>
						<c:when test="${applyEp.aepAType == '2'}">
							自费
						</c:when>
					</c:choose></td>			
				<td><c:choose>
						<c:when test="${applyEp.aepCType == '1'}">
							个人
						</c:when>
						<c:when test="${applyEp.aepCType == '2'}">
							企业
						</c:when>
					</c:choose></td>			
				<td><c:choose>
						<c:when test="${applyEp.aepHPark == '1'}">
							是
						</c:when>
						<c:when test="${applyEp.aepHPark == '2'}">
							否
						</c:when>
					</c:choose></td>			
				<td><c:choose>
						<c:when test="${applyEp.aepParkNum == '1'}">
							少于等于10个
						</c:when>
						<c:when test="${applyEp.aepParkNum == '2'}">
							大于10个
						</c:when>
					</c:choose></td>			
				<td><c:choose>
						<c:when test="${applyEp.aepEpType == '1'}">
							直流
						</c:when>
						<c:when test="${applyEp.aepEpType == '2'}">
							交流
						</c:when>
						<c:when test="${applyEp.aepEpType == '3'}">
							交流/直流
						</c:when>
					</c:choose></td>			
				<td><c:choose>
						<c:when test="${applyEp.aepIAgree == '1'}">
							是
						</c:when>
						<c:when test="${applyEp.aepIAgree == '2'}">
							否
						</c:when>
					</c:choose></td>			
				<td><c:choose>
						<c:when test="${applyEp.aepIReport == '1'}">
							是
						</c:when>
						<c:when test="${applyEp.aepIReport == '2'}">
							否
						</c:when>
					</c:choose></td>			
				<td><c:choose>
						<c:when test="${applyEp.aepIGround == '1'}">
							地面
						</c:when>
						<c:when test="${applyEp.aepIGround == '2'}">
							地下
						</c:when>
					</c:choose></td>			
				<td>
					<fmt:formatDate value="${applyEp.createdate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>		
				<td>
					<c:choose>
						<c:when test="${applyEp.aepStatus == '1'}">
							提交申请
						</c:when>
						<c:when test="${applyEp.aepStatus == '2'}">
							勘探现场
						</c:when>
						<c:when test="${applyEp.aepStatus == '3'}">
							现场施工
						</c:when>
						<c:when test="${applyEp.aepStatus == '4'}">
							建站成功
						</c:when>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${applyEp.aepStatus == '1'}">
							<a target="ajaxTodo" href="<c:url value='/admin/applyBuildElecPile/updateApplyState.do?aepId=${applyEp.aepId}&aepStatus=2'/>" title="确定通过该申请？" style='color:#0099FF'>通过申请</a>
						</c:when>
						<c:when test="${applyEp.aepStatus == '2'}">
							<a target="ajaxTodo" href="<c:url value='/admin/applyBuildElecPile/updateApplyState.do?aepId=${applyEp.aepId}&aepStatus=3'/>" title="确定完成勘探现场？"   style='color:#0099FF'>完成勘探现场</a>
						</c:when>
						<c:when test="${applyEp.aepStatus == '3'}">
							<a target="ajaxTodo" href="<c:url value='/admin/applyBuildElecPile/updateApplyState.do?aepId=${applyEp.aepId}&aepStatus=4'/>"  title="确定完成建桩？"  style='color:#0099FF'>完成建桩</a>
						</c:when>								
					</c:choose>
				</td>
				
			
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>
			共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${pager.total}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
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
	
	$("#electricSelProvince").change(function(){
		//初始化区县下拉框
		$("#electricSelDistrict").html('<option value="">--请选择区/县--</option>');
		var provinceId=$(this).val();
		if(!provinceId){
			$("#electricSelCity").html('<option value="">--请选择城市--</option>');
			return;
		}
		$.ajax({
			type:'POST', 
			url:basepath+"/admin/rateinfo/searchCityList.do", 
			data: {provinceId:provinceId},
			dataType:'json',
			cache: false,
			success: function(datas) {
				if(datas.status==100){
					var data=datas.data;
					var content='<option value="">--请选择城市--</option>';
					for(var i=0;i<data.length;i++){
						content+='<option value="'+data[i].CITY_ID+'">'+data[i].CITY_NAME+'</option>';
					}
					 $("#electricSelCity").html(content);
				}else{
					alertMsg.error(datas.msg);
				}
				
            }
		});
	});
	
	$("#electricSelCity").change(function(){
		var cityId=$(this).val();
		if(!cityId){
			$("#electricSelDistrict").html('<option value="">--请选择区/县--</option>');
			return;
		}
		$.ajax({
			type:'POST', 
			url:basepath+"/admin/rateinfo/searchAreaList.do", 
			data: {cityId:cityId},
			dataType:'json',
			cache: false,
			success: function(datas) {
				if(datas.status==100){
					var data=datas.data;
					var content='<option value="">--请选择区/县--</option>';
					for(var i=0;i<data.length;i++){
						content+='<option value="'+data[i].area_id+'">'+data[i].area_name+'</option>';
					}
					 $("#electricSelDistrict").html(content);
				}else{
					alertMsg.error(datas.msg);
				}
				
            }
		});
	});
</script>

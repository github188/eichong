<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
	<style>

  .one{
  	width:100%;
  	margin-top:40px;
  }
  .typeSpanBtn{
  	display:inline-block;
  	width:80px;
  	height:30px;
  	border:1px solid #ccc;
  	text-align:center;
  	line-height:30px;
  	border-radius:8px;
  	cursor:pointer; 
  	color:#000;
  	background-color:#ebebeb;
  }
  #select{
  	margin-left:80px;
  }
 
</style>
 <link href="<%=request.getContextPath()%>/static/css/bannerList.css" rel="stylesheet" />
<div id="typeSpanPloat" style="min-width:300px;height:180px;z-index:9999;position:absolute;left:20%;top:100px;background:#e6edef;padding:40px;padding-right:60px;display:none">
	<div class="one" >
		<select id="select">
			<option value=""> ---选择升级列表--- </option>
		</select>
	</div><br>
	<div class="one" style="margin-left:100px;">
		<div class="typeSpanBtn" style="margin-left:50px" id="typeSpan_confirm" >确定</div>
		<div class="typeSpanBtn" style="margin-left:50px" id="typeSpan_cancel">取消</div>
	</div>
</div>


<div class="pageHeader">
	<form id="pagerForm" method="post"
		action="product/updateEpVisionUi.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> 
		<input type="hidden" name="keywords" value="${pager.keywords}" /> 
		<input type="hidden" id="pageNum" name="pageNum" value="${pager.pageNum}" /> 
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<input type="hidden" name="pkTypeSpanId" id="pkTypeSpanId" value="${tblTypespan.pkTypeSpanId}" />
		<input type="hidden" name="tsTypeSpan" id="tsTypeSpan" value="${tblTypespan.tsTypeSpan}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					
						<td><select class="provinceCode required"  id="electricSelProvince" next="electricSelCity" name="elPiOwnProvinceCode"  style="float: none;width:130px;">
								<option value="">--请选择省份--</option>
								<c:forEach var="item" items="${proviceMap}">
									<option value="${item.key}"
										${item.key== electricpile.elPiOwnProvinceCode ? 'selected="selected"' : ''} >
										${item.value.PROVINCE_NAME}
									</option>
								</c:forEach>
							</select>
							<select class="cityCode required" id="electricSelCity" next="electricSelDistrict" data-val="${electricpile.elPiOwnCityCode}" name="elPiOwnCityCode"  style="float: none;width:130px;">
								 <option value="">--请选择城市--</option>
							</select>
							<select id="electricSelDistrict" data-val="${electricpile.elPiOwnCountyCode}" name="elPiOwnCountyCode" class="required"  style="float: none;width:130px;">
								<option value="">--请选择区/县--</option>
							</select>	
						</td>
						<td><label><bmtag:message messageKey="桩体编码" /> </label></td>
						<td><input name="elpiElectricpilecode" value="${electricpile.elpiElectricpilecode}" /></td>
						<td><label><bmtag:message messageKey="充电点名称" /> </label></td>
						<td><input name="powerName" value="${electricpile.powerName}" /></td>
					<td align="right"><bmtag:button messageKey="common.button.search" type="submit" 	id="formSubmitter" /></td>
				</tbody>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar" style="padding:0">
			<li style="padding-left:0">
				<div class="myEditForTypeSpan" id="upgrade">升级</div>
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="115">
		<thead>
			<tr align="center">
				<th><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th><bmtag:message messageKey="common.label.index" /></th>
				<th>桩体编码</th>
				<th>充点电名称</th>
				<th>制造厂商</th>
				<th>充电方式</th>
				<th>电桩类型</th>
				<th>固件编号</th>
				<th>固件版本</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${electricList}" var="electric" varStatus="status">
				<tr target="ids" rel="${electric.pkElectricPile}" align="center">
					<td><input name="id" value="${electric.pkElectricPile}" class="typeSpanCheckbox"  id="${electric.pkElectricPile}" data-value="${electric.pkElectricPile}"  type="checkbox"></td>
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td>${electric.elpiElectricpilecode}</td>
					<td>${electric.powerName}</td>
					<td>${electric.elPi_Maker_Name}</td>
					<td>
						<c:if test="${electric.elpiChargingmode =='14'}">交流</c:if> 
						<c:if test="${electric.elpiChargingmode =='5'}">直流</c:if> 
					</td>
					<td>
						<c:if test="${electric.elpiType =='1'}">落地式</c:if> 
						<c:if test="${electric.elpiType =='2'}">壁挂式</c:if> 
						<c:if test="${electric.elpiType =='11'}">一体式</c:if> 
						<c:if test="${electric.elpiType =='12'}">分体式</c:if> 
					</td>
					<td>${electric.firmwareNumber}</td>
					<td>${electric.firmwareVersion}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
<script type="text/javascript">
html = "<span>"+$("#tsTypeSpan").val()+"</span>";
$(".updateEpVisionUi").html(html);

$(function(){
	loadCity($("#electricSelProvince"));
	loadArea($("#electricSelCity"));
});

var pkTypeSpanId = $("#pkTypeSpanId").val();
var pkElectricpiles="";
$("#upgrade").click(function(){
	pkElectricpiles="";
		$(".typeSpanCheckbox").each(function(){
			var pkElectricpile =$(this).attr('data-value');
			if ($("#"+pkElectricpile).attr('checked')) {
				pkElectricpiles=pkElectricpiles+pkElectricpile+":";
			}
		});
		if(pkElectricpiles.length>0){
			getBomListByTypeSpanId(pkTypeSpanId);
			$("#typeSpanPloat").show();
		}else{
			return;
		}
})
$("#typeSpan_cancel").click(function(){
		$("#typeSpanPloat").hide();
		pkElectricpiles="";
})
$("#typeSpan_confirm").click(function(){
	if(pkElectricpiles.length>0){
		var pkBomListId = $('#select option:selected') .val();
		updateEpVision(pkElectricpiles,pkBomListId,pkTypeSpanId);
	}else{
		return;
	}
		
})
function getBomListByTypeSpanId(pkTypeSpanId){
	$.ajax({
		type : 'post',
		url : basepath+ "/admin/product/getBomListByTypeSpanId.do",
		dataType : "json",
		data :{
			pkTypeSpanId:pkTypeSpanId
		},
		success : function(json) {
			var html=""
			for(var i in json){
				html+='<option value="'+json[i].pkBomListId+'">硬件编号 :'+json[i].blHardwareNumber+',硬件版本:'+json[i].blHardwareVersion+',固件编号:'+json[i].blFirmwareNumber+',固件版本号:'+json[i].blFirmwareVersion+'</option>'
			}
			$("#select").html(html);
		}
	});
	
}

function updateEpVision(pkElectricpiles,pkBomListId,pkTypeSpanId){
$.ajax({
	type : 'post',
	url : basepath+ "/admin/product/updateEpVision.do",
	dataType : "json",
	data :{
		pkElectricpiles:pkElectricpiles,
		pkBomListId:pkBomListId,
		pkTypeSpanId:pkTypeSpanId
	},
	success : function(datas) {
		if(datas.statusCode==200){
			$("#typeSpanPloat").hide();
			navTab.closeCurrentTab();
			navTab.reloadFlag("typeSpanList");
		}else{
			alertMsg.error(datas.message);
		}
	}
});
}
</script>
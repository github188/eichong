<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
				  <tr>
				  
				  <td style="align:left">
						<span>充电点名称</span>
						<input name="powerName" value="${electricpile.powerName}" placeholder="充电点名称" />
					    </td> 
				  
					<td><span>区域选择</span>
						<select class="provinceCode required"  id="spanSelProvince" next="spanSelCity" name="elPiOwnProvinceCode"  style="float: none;width:130px;">
								<option value="">--请选择省份--</option>
								<c:forEach var="item" items="${proviceMap}">
									<option value="${item.key}"
										${item.key== electricpile.elPiOwnProvinceCode ? 'selected="selected"' : ''} >
										${item.value.PROVINCE_NAME}
									</option>
								</c:forEach>
							</select>
							<select class="cityCode required" id="spanSelCity" next="spanSelDistrict" data-val="${electricpile.elPiOwnCityCode}" name="elPiOwnCityCode"  style="float: none;width:130px;">
								 <option value="">--请选择城市--</option>
							</select>
							<select id="spanSelDistrict" data-val="${electricpile.elPiOwnCountyCode}" name="elPiOwnCountyCode" class="required"  style="float: none;width:130px;">
								<option value="">--请选择区/县--</option>
							</select>	
						</td>
				    </tr>
				     
					<tr>	        
						<td style="align:left">
						   <span>桩体编码</span>
						   <input name="elpiElectricpilecode" value="${electricpile.elpiElectricpilecode}" placeholder="桩体编码" />
						</td> 
					    
						<td><bmtag:message messageKey="连接状态" />
						<select name="comm_status" class="select_Style">
								<option value="">全部</option>
								<option value="1"
									${electricpile.comm_status=="1"
									? 'selected="selected"' : ''}>已连接</option>
								<option value="0"
									${electricpile.comm_status=="0"
									? 'selected="selected"' : ''}>未连接</option>
						       </select>
						</td>
						
						<td align="right"><bmtag:button messageKey="common.button.search" type="submit" 	id="formSubmitter" /></td>
						</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul>
			 <li>
				<div class="myEditForTypeSpan" id="upgrade">升级</div> 			
			</li> 
			<%-- <li>
				<bmtag:link isAuth="false" target="selectedTodo"
					href="product/checkUpgradeVersion" rel="ids" postType="string"
					altKey="确定要进行查询并升级吗？" dwzClass="add" messageKey="查询并升级" /></li> --%>
			 <li>
				<div class="myEditForTypeSpan" id="checkUpgrade">查询并升级</div>
			</li> 
		</ul>
	</div>
	<table class="table" width="100%" layoutH="140">
		<thead>
			<tr align="center">
				<th><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th><bmtag:message messageKey="common.label.index" /></th>
				<th>桩体编码</th>
				<th>连接状态</th>
				<th>充电点名称</th>
				<th>制造厂商</th>
				<th>充电方式</th>
				<th>电桩类型</th>
				<th>升级固件编号</th>
				<th>升级固件版本</th>
				<th>数据库固件编号</th>
				<th>数据库固件版本</th>
				<th>升级时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${electricList}" var="electric" varStatus="status">
				<tr target="id" rel="${electric.pkElectricPile}" align="center">
					<td><input name="ids" value="${electric.pkElectricPile}" class="typeSpanCheckbox"  id="${electric.pkElectricPile}" data-value="${electric.pkElectricPile}"  type="checkbox"></td>
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td>${electric.elpiElectricpilecode}</td>
					<td>
					<c:if test="${electric.comm_status =='0'}">未连接</c:if> 
					<c:if test="${electric.comm_status =='1'}">已连接</c:if> 
					</td>
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
					<td>${electric.ugFirmwareNumber}</td>
					<td>${electric.ugFirmwareVersion}</td>
					<td>${electric.firmwareNumber}</td>
					<td>${electric.firmwareVersion}</td>
					<td>${electric.ev_Updatedate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
		<span>显示</span>
		 <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
		       <option value="10" <c:if test="${10 == pager.numPerPage }">selected="selected"</c:if>>10</option>
               <option value="20" <c:if test="${20 == pager.numPerPage }">selected="selected"</c:if>>20</option>
               <option value="50" <c:if test="${50 == pager.numPerPage }">selected="selected"</c:if>>50</option>
               <option value="100" <c:if test="${100 == pager.numPerPage }">selected="selected"</c:if>>100</option>
		 </select>
		<span>共${pager.total }条, 共${pager.pageTotal}页</span>
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
	loadCity($("#spanSelProvince"));
	loadArea($("#spanSelCity"));
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
				var blForceUpdate ;
				if(json[i].blForceUpdate==1){
					blForceUpdate="是";
				}else{
					blForceUpdate="否";
				}
				html+='<option value="'+json[i].pkBomListId+'">硬件编号 :'+json[i].blHardwareNumber+',硬件版本:'+json[i].blHardwareVersion+',固件编号:'+json[i].blFirmwareNumber+',固件版本号:'+json[i].blFirmwareVersion+',强制升级:'+blForceUpdate+'</option>'
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
// 查询并升级
$("#checkUpgrade").click(function(){
	pkElectricpiles="";
	$(".typeSpanCheckbox").each(function(){
		var pkElectricpile =$(this).attr('data-value');
		if ($("#"+pkElectricpile).attr('checked')) {
			pkElectricpiles=pkElectricpiles+pkElectricpile+",";
		}
	});
	if(pkElectricpiles.length>0){
		checkUpgradeVersion(pkElectricpiles,pkTypeSpanId);
	}else{
		return;
	}
})

 function checkUpgradeVersion(pkElectricpiles,pkTypeSpanId){
	$.ajax({
		type : 'post',
		url : basepath+ "/admin/product/checkUpgradeVersion.do",
		dataType : "json",
		data :{
			pkElectricpiles:pkElectricpiles.substr(0,pkElectricpiles.length-1),
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
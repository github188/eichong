<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<style>
  .pCenterRed {
  	color:red;
  	margin-top:5px;
  }
  
  .pCenterRedGreen {
  	color:green;
  	margin-top:5px;
  }
  .title{
  	text-align:center;
  	font-size:16px;
  	
  }
  .one{
  	width:100%;
  	margin-top:40px;
  	text-align:center;
  	font-size:14px;
  }
  .myBtn{
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
</style>
 <link href="<%=request.getContextPath()%>/static/css/bannerList.css" rel="stylesheet" />
<div id="socPloat" style="width:300px;height:180px;left:550px;top:200px;z-index:9999;position:absolute;background:#e6edef;padding:40px;padding-right:60px;display: none">
	<div class="title">定soc充电开关</div>
	<div class="one" >
		<input id="open"  name='argValue' type="radio"  value="1" />开
		<input id="close"  name='argValue' type="radio" value="0" checked="checked" style="margin-left:40px"  />关
	</div><br>
	<div class="one">
		<div class="myBtn" style="margin-left:50px" id="socConfirm" >确定</div>
		<div class="myBtn" style="margin-left:50px" id="socCancel">取消</div>
	</div>
</div>
<div class="pageHeader">
	<form id="pagerForm" method="post"
		action="socCharge/socChargeList.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> 
		<input type="hidden" name="keywords" value="${pager.keywords}" /> 
		<input type="hidden" id="pageNum" name="pageNum" value="${pager.pageNum}" /> 
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td><label><bmtag:message
									messageKey="electric.label.code" /> </label>
						</td>
						<td><input name="elpiElectricpilecode"
							value="${socCharge.elpiElectricpilecode}" />
						</td>
						<td style="align: left"><label style="align: left"><bmtag:message
									messageKey="充电点名称" /> </label>
						</td>
						<td><input name="postName"
							value="${socCharge.postName}" />
						</td>
						<td>
							<label>区域选择</label>
						</td>
						<td colspan="2">
							<select class="provinceCode required"  id="powerStationSelProvince" next="powerStationSelCity" name="elPiOwnProvinceCode"  style="float: none;width:130px;">
								<option value="">--请选择省份--</option>
								<c:forEach var="item" items="${proviceMap}">
									<option value="${item.key}"
										${item.key== socCharge.elPiOwnProvinceCode ? 'selected="selected"' : ''} >
										${item.value.PROVINCE_NAME}
									</option>
								</c:forEach>
							</select>
							<select class="cityCode required" id="powerStationSelCity" next="powerStationSelDistrict" data-val="${socCharge.elPiOwnCityCode}" name="elPiOwnCityCode"  style="float: none;width:130px;">
								 <option value="">--请选择城市--</option>
							</select>
							<select id="powerStationSelDistrict" data-val="${socCharge.elPiOwnCountyCode}" name="elPiOwnCountyCode" class="required"  style="float: none;width:130px;">
								<option value="">--请选择区/县--</option>
							</select>	
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
	<div class="panelBar">
		<ul>
			<li>
				<div class="myEdit" id="socStartUp">定soc充电开关</div>
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="125">
		<thead>
			<tr align="center">
				<th><input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th><bmtag:message messageKey="common.label.index" />
				</th> 
				<th><bmtag:message messageKey="桩体编号" />
				</th>
				<th><bmtag:message messageKey="电桩名称" />
				</th>
				<th><bmtag:message messageKey="充电点名称" />
				</th>
				<th><bmtag:message messageKey="制造厂商" />
				</th>
				<th><bmtag:message messageKey="定soc充电开关" />
				</th>
				<th><bmtag:message messageKey="下发状态" />
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${socChargeList}" var="electric" varStatus="status">
				<tr target="id" rel="${electric.pkElectricpile}" align="center">
					<td><input name="ids" value="" type="checkbox" class="checkbox2" id="${electric.elpiElectricpilecode}" data-value1="${electric.elpiElectricpilecode}" data-status="${electric.status}"></td>
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td> 
					<td>${electric.elpiElectricpilecode}</td>
					<td>${electric.elpiElectricpilename}</td>
					<td>${electric.postName}</td>
					<td>${electric.elPiMakerName}</td>
					<td>
						<c:if test="${electric.argValue == 0 }">关</c:if>
						<c:if test="${electric.argValue == 1 }">开</c:if>
					</td>
					<td>
						<c:if test="${electric.issuedStatus == 0}">
							未下发
						</c:if>
						<c:if test="${electric.issuedStatus == 1}">
							已下发但未响应
						</c:if>
						<c:if test="${electric.issuedStatus == 2}">
							下发成功
						</c:if>
						<c:if test="${electric.issuedStatus == 3}">
							下发失败
						</c:if>
					</td>
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
$(function(){
	loadCity($("#powerStationSelProvince"));
	loadArea($("#powerStationSelCity"));
});
var elpiElectricpilecodes="";
	$("#socStartUp").click(function(){
			$(".checkbox2").each(function(){
				var elpiElectricpilecode =$(this).attr("data-value1");
				if ($(this).attr('checked')) {
					elpiElectricpilecodes=elpiElectricpilecodes+elpiElectricpilecode+":";
				}
			});
			
			if(elpiElectricpilecodes.length>0){
				$("#socPloat").show();
				
			}else{
				return;
			}
	})
	$("#socCancel").click(function(){
			$("#socPloat").hide();
			elpiElectricpilecodes="";
	})
	$("#socConfirm").click(function(){
		if(elpiElectricpilecodes.length>0){			
			var argValue = $(' input[name="argValue"]:checked ').val();
			setSocChargeAjax(elpiElectricpilecodes,argValue);
		}else{
			return;
		}
			
	})
function setSocChargeAjax(elpiElectricpilecodes,argValue){
	$.ajax({
		type : 'post',
		url : basepath+ "/admin/socCharge/setSocCharge.do",
		dataType : "json",
		data :{
			elpiElectricpilecode:elpiElectricpilecodes,
			argValue:argValue
		},
		success : function(datas) {
			if(datas.statusCode==200){
				$("#socPloat").hide();
				navTab.reloadFlag("socChargeList");				
				elpiElectricpilecodes.length=0;
			}else{
				alertMsg.error(data.message);
			}
		}
	});
}
</script>
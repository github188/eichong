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
  	margin-top:10px;
  	text-align:center;
  	font-size:16px;
  }
   .ones{
  	width:100%;
  	margin-top:30px;
  	text-align:center;
  	font-size:16px;
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
<div id="eolPloat" style="width:300px;height:180px;left:550px;top:200px;z-index:9999;position:absolute;background:#e6edef;padding:40px;padding-right:60px;display: none">	
	<div class="one" >
		已选择<span id="recordSum" style="font-size:16px;"></span>条记录
	</div>
	<div class="ones" >
		离线充电次数<input id="eleOffNum"  value="0"maxlength="2" style="width: 160px;float:right;" 
				 onkeyup="this.value=/^(?:10|[0-9])$/.test(this.value) ? this.value : ''" />
	</div><br>
	<div class="ones">
		<div class="myBtn" style="margin-left:20px" id="eolConfirm" >确定</div>
		<div class="myBtn" style="margin-left:70px" id="eolCancel">取消</div>
	</div>
</div>
<div class="pageHeader">
	<form id="pagerForm" method="post"
		action="electricOffLine/ecOffLineList.do"
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
							value="${ecOffLine.elpiElectricpilecode}" />
						</td>
						<td style="align: left"><label style="align: left"><bmtag:message
									messageKey="充电点名称" /> </label>
						</td>
						<td><input name="postName"
							value="${ecOffLine.postName}" />
						</td>
						<td>
							<label>区域选择</label>
						</td>
						<td colspan="2">
							<select class="provinceCode required"  id="powerStationSelProvince" next="powerStationSelCity" name="elPiOwnProvinceCode"  style="float: none;width:130px;">
								<option value="">--请选择省份--</option>
								<c:forEach var="item" items="${proviceMap}">
									<option value="${item.key}"
										${item.key== ecOffLine.elPiOwnProvinceCode ? 'selected="selected"' : ''} >
										${item.value.PROVINCE_NAME}
									</option>
								</c:forEach>
							</select>
							<select class="cityCode required" id="powerStationSelCity" next="powerStationSelDistrict" data-val="${ecOffLine.elPiOwnCityCode}" name="elPiOwnCityCode"  style="float: none;width:130px;">
								 <option value="">--请选择城市--</option>
							</select>
							<select id="powerStationSelDistrict" data-val="${ecOffLine.elPiOwnCountyCode}" name="elPiOwnCountyCode" class="required"  style="float: none;width:130px;">
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
				<div class="myEdit" id="eolStartUp">离线充电设置</div>
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
				<th><bmtag:message messageKey="离线充电次数" />
				</th>
				<th><bmtag:message messageKey="下发状态" />
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ecOffLineList}" var="electric" varStatus="status">
				<tr target="id" rel="${electric.pkElectricpile}" align="center">
					<td><input name="ids" value="" type="checkbox" class="checkbox3" id="${electric.elpiElectricpilecode}" 
						data-value="${electric.elpiElectricpilecode}" data-status="${electric.status}"></td>
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td> 
					<td>${electric.elpiElectricpilecode}</td>
					<td>${electric.elpiElectricpilename}</td>
					<td>${electric.postName}</td>
					<td>${electric.elPiMakerName}</td>
					<td>${electric.argValue}</td>
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
	$("#eolStartUp").click(function(){
			$(".checkbox3").each(function(){
				var elpiElectricpilecode = $(this).attr("data-value");
				if ($(this).attr('checked')) {
					elpiElectricpilecodes=elpiElectricpilecodes+elpiElectricpilecode+":";
				}
				
			});			
			if(elpiElectricpilecodes.length>0){
				var div = document.getElementById("recordSum");
				var sum=":";
				var regex = new RegExp(sum, 'g'); // 使用g表示整个字符串都要匹配
				var result = elpiElectricpilecodes.match(regex);
				var count = !result ? 0 : result.length;
				div.innerHTML = count + "";
				$("#eolPloat").show();
				
			}else{
				alertMsg.error("请选择至少一条记录！");
				return;
			}
	})
	$("#eolCancel").click(function(){
			$("#eolPloat").hide();
			elpiElectricpilecodes="";
	})
	$("#eolConfirm").click(function(){
		if(elpiElectricpilecodes.length>0){			
			var argValue = document.getElementById("eleOffNum").value;
			setEolChargeAjax(elpiElectricpilecodes,argValue);
		}else{
			return;
		}
			
	})
function setEolChargeAjax(elpiElectricpilecodes,argValue){
	$.ajax({
		type : 'post',
		url : basepath+ "/admin/electricOffLine/setEcOffLine.do",
		dataType : "json",
		data :{
			elpiElectricpilecode:elpiElectricpilecodes,
			argValue:argValue
		},
		success : function(datas) {
			if(datas.statusCode==200){
				$("#eolPloat").hide();
				navTab.reloadFlag("ecOffLineList");				
				elpiElectricpilecodes.length=0;
			}else{
				alertMsg.error(data.message);
			}
		}
	});
}
</script>
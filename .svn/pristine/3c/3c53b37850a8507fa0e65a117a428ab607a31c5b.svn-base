<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
	//获取城市
	function getCity(proviceId){
		 
		$.get(webroot + "/admin/electric/getCityCode.do?proviceId="+proviceId.value+"",{}, function(data) {
			var json = eval("(" + data + ")");
			$(".addCityId").html("");
			 $("<option value=''>-请选择-</option>").appendTo(
					$(".addCityId"));
			$.each(json, function(i) { 
				$(  
						
						"<option value='"+json[i].cityId+"'>"
								+ json[i].cityName + "</option>").appendTo(
						$(".addCityId"));
			}); 
		});
	}
	//获取区县
	function getCounty(cityId){
		$.get(webroot + "/admin/electric/getCountyCode.do?cityId="+cityId.value+"", {}, function(data) {
			var json = eval("(" + data + ")");
			$(".addCountyId").html("");
			$("<option value=''>-请选择-</option>").appendTo(
					$(".addCountyId"));
			$.each(json, function(i) {
				$(
						"<option value='"+json[i].areaId+"'>"
								+ json[i].areaName + "</option>").appendTo(
						$(".addCountyId"));
			});
			 
		});
	}		
	
	function getRateinfo(countyCode){
		$.get(webroot + "/admin/electric/getRateInfo.do?countyCode="+countyCode.value+"",{}, function(data) {
			if(data){
				var json = eval("(" + data + ")");
				$("#elPiRateInformationId").html("");
				$("<option value=''>-请选择-</option>").appendTo(
						$("#elPiRateInformationId"));
				
				if(json.length>0){
					$.each(json, function(i) { 
						$(  
								"<option value='"+json[i].pk_RateInformation+"'>id:"+json[i].pk_RateInformation+"&nbsp;,&nbsp;预约单价:"
										+ json[i].raIn_ReservationRate+"&nbsp;,&nbsp;服务费:"+json[i].raIn_ServiceCharge+ "</option>").appendTo(
								$("#elPiRateInformationId"));
					});					
				}else{
					alertMsg.error("该地区未设置费率");
				}
			
			}
			
		});
	}
	
	function changePileNum(num){
		if(num == 5){
			$("input[name=elpiPowernumber]").attr("max",4)
		}else if(num == 14){
			$("input[name=elpiPowernumber]").attr("max",2)
		}
		
	}
	//删除公司标志
	$("#deleteCompanyNumber").on("click",function(){
		$("#companyNumber").val(0);
		$("#companyName").val("");
	});
	
	
</script>
<h2 class="contentTitle">
	<bmtag:message messageKey="electriclabel.addUser" />
</h2>
<div class="pageContent">
	<form method="post" action="electric/addElectricPile.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<input type="hidden" name="elpiPoweruser" value="3"/>
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dt>
					<bmtag:message messageKey="electric.label.name" />
				</dt>
				<dd>
					<input name="elpiElectricpilename" class="textInput required"
						maxlength="20" style="width: 165px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="electric.label.longitude" />
				</dt>
				<dd>
					<input name="elpiLongitude" id="longitude"
						class="textInput required" maxlength="11" max="136" min="74" style="width: 165px;" />
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="electric.label.latitude" />
				</dt>
				<dd>
					<input name="elpiLatitude" class="textInput required"
						maxlength="11" style="width: 165px;" max="54" min="18"/> <span class="info"></span>
				</dd>
			</dl>
			
			<dl>
				<dt>
					<bmtag:message messageKey="electric.label.way" />
				</dt>
				<dd>
					<select name="elpiChargingmode" class="select_Style" onchange="changePileNum(this.value)">
						<c:forEach var="item" items="${chargeList}">
							<option value="${item.pkConfigcontent}" ${item.pkConfigcontent==
								tblElectricpile.elpiChargingmode ? 'selected="selected"' : ''} >
								${item.cocoContent}</option>
						</c:forEach>
					</select> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="electric.label.power" />
				</dt>
				<dd>
					<select name="elpiPowersize" class="select_Style">
						<c:forEach var="item" items="${powerList}">
							<option value="${item.pkConfigcontent}" ${item.pkConfigcontent==
								tblElectricpile.elpiPowersize ? 'selected="selected"' : ''} >
								${item.cocoContent}</option>

						</c:forEach>
					</select> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="electric.label.address" />
				</dt>
				<dd>
					<input name="elpiElectricpileaddress" class="textInput"
						maxlength="50" style="width: 165px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="electric.label.type" />
				</dt>
				<dd>
					<select name="elpiType" class="select_Style">
						<c:forEach var="item" items="${typeList}">
							<option value="${item.pkConfigcontent}" ${item.pkConfigcontent==
								tblElectricpile.elpiType ? 'selected="selected"' : ''} >
								${item.cocoContent}</option>

						</c:forEach>
					</select> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="接口标准" />
				</dt>
				<dd>
					<select name="elpiPowerinterface" class="select_Style">
						<c:forEach var="item" items="${connectorList}">
							<option value="${item.pkConfigcontent}" ${item.pkConfigcontent==
								tblElectricpile.elpiType ? 'selected="selected"' : ''} >
								${item.cocoContent}</option>

						</c:forEach>
					</select> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="制造厂家" />
				</dt>
				<dd>
					<select name="elpiMaker" class="select_Style required">
						<c:forEach var="item" items="${markList}">
							<option value="${item.pkCarmaker}" ${item.pkCarmaker==
								tblElectricpile.elpiMaker ? 'selected="selected"' : ''} >
								${item.makerName}</option>

						</c:forEach>
					</select> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="产品型号" />
				</dt>
				<dd>
					<select name="elpiTypeSpanId" class="select_Style required">
						<option value="">请选择</option>
						<c:forEach var="item" items="${typespanList}">
							<option value="${item.pkTypeSpanId}" ${item.pkTypeSpanId==
								tblElectricpile.elpiTypeSpanId ? 'selected="selected"' : ''} >
								${item.tsTypeSpan}</option>

						</c:forEach>
					</select> <span class="info"></span>
				</dd>
			</dl>
			<c:if test="${loginUserLevel != 5}">
				<dl>
					<dt>
						<bmtag:message messageKey="公司标识" />
					</dt>
					<dd>
						<input name="companyNumber" type="hidden" value="0" id="companyNumber"/>
						<input name="companyName" type="text"
							readonly="true" id="companyName"/><a class="btnLook"
							href="../admin/electric/getCompanyNumberList.do"
							lookupGroup="">查找公司</a> <span class="info">(请选择公司)</span>
						<input type="button" value="删除" class="delete" id="deleteCompanyNumber"/>
					</dd>
				
				</dl>
			</c:if>
			<dl>
				<dt>
					<bmtag:message messageKey="运营平台" />
				</dt>
				<dd>		 
						<select name="elPiOwnerCompany" class="select_Style required">							
							<option value="1" >爱充</option>
							<option value="2" >国网</option>
							<option value="3" >特斯拉</option>
							<option value="0" >其他</option>
						</select>
						<span class="info"></span>	
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="所有权" />
				</dt>
				<dd>
					<input name="ownerShip" class="textInput required" 
						maxlength="20" style="width: 165px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="省份" />
				</dt>
				<dd>
					<select name="elPiOwnProvinceCode" style="width: 172px !important" class="select_Style" onchange="getCity(this)">
						<option value="">-请选择-</option>
						 <c:forEach var="item" items="${provinceCodeList}">
							<option value="${item.provinceId}" ${item.provinceId==
								tblElectricpile.elPiOwnProvince ? 'selected="selected"' : ''} >${item.provinceName}</option>
						</c:forEach>
					</select> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="城市" />
				</dt>
				<dd>
					<select id="cityId" name="elPiOwnCityCode" class="select_Style addCityId" onchange="getCounty(this)" >
						<option value="">-请选择-</option>
					</select> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="区/县" />
				</dt>
				<dd>
					<select id="countyId" name="elPiOwnCountyCode" class="select_Style addCountyId" onchange="getRateinfo(this)">
					<option value="">-请选择-</option>
					</select> <span class="info"></span>
				</dd>
			</dl>
			<%-- 	 <dl><dt><bmtag:message messageKey="electric.label.user" /></dt><dd>
                        <select name="elpiUsertype" class="select_Style">
							<option value="1">普通用户</option>
							<option value="2">商家</option>
							<option value="3">个体商家</option>
						 </select>
			<span class="info"></span></dd></dl> --%>
			<dl>
				<dt>
					<bmtag:message messageKey="electric.label.listImage" />
				</dt>
				<dd id="imageDD">
					<input type="hidden" id="businessType" name="businessType" value="electricListImage" />
					<input type="hidden" id="fileId" name="fileId" />
					<input  id="electricAddFileInput"  type="file" name="listImage" class="file" style="width: 260px;" 
					uploaderOption="{
							swf:'<%=request.getContextPath()%>/res/dwz/uploadify/scripts/uploadify.swf',
							uploader:'<%=request.getContextPath()%>/common/uploadFile.do',
							formData:{type:'electricListImage',isZip:'1'},
							buttonText:'请选择文件',
							queueSizeLimit:'6',
							fileSizeLimit:'8MB',
							fileTypeDesc:'*.jpg;*.jpeg;*.gif;*.png;',
							fileTypeExts:'*.jpg;*.jpeg;*.gif;*.png;',
							fileObjName: 'file', 
							auto:true,
							multi:true,
							overrideEvents:[ 'onDialogClose', 'onUploadSuccess', 'onUploadError', 'onSelectError' ],
							onSelectError:uploadify_onSelectError,
   							onUploadError:uploadify_onUploadError,
							onUploadStart:electricFileUploadStart,
							onUploadSuccess:electricFileUploadSuccess,
							onQueueComplete:electricFileUploadComplete
						}"
					/> 
					
				</dd>
			</dl>
			<%-- <dl>
				<dt>
					<bmtag:message messageKey="electric.label.detailImage" />
				</dt>
				<dd>
					<input type="file" name="detailImage" class="file"
						style="width: 260px;" /> <span class="info"></span>
				</dd>
			</dl> --%>
			<dl>
			<dt>
				<bmtag:message messageKey="联系电话" />
			</dt>
			<dd>
				<input name="elPi_Tell" value="4000850006" class="textInput" maxlength="50"
					style="width: 165px;" /> <span class="info"></span>
			</dd>
		</dl>
		<dl>
				<dt>
					<bmtag:message messageKey="开放时间" />
				</dt>
				<dd>
					  <input name="elPiOnlineTime" class="textInput" style="width:165px" >
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="费率 / 分钟" />
				</dt>
				<dd>
					<select class="required"  id="elPiRateInformationId" name="elPiRateInformationId" class="select_Style">
						<option value="">-请选择-</option>
					</select> 
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="sim卡卡号" />
				</dt>
				<dd>
					<input name="elPiSimPhoneNum" class=" textInput "
						maxlength="13" style="width: 165px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="sim卡编码" />
				</dt>
				<dd>
					<input name="elPiSimMac" class="textInput "
						maxlength="20" style="width: 165px;" /> <span class="info"></span>
				</dd>
			</dl>
		
			<%-- <c:if test="${loginUserLevel == '3'}"> --%>
			  <dl>
				<dt>
					<bmtag:message messageKey="下线时间" />
				</dt>
				
					<input id="new_offlineTime" class="date" dateFmt="yyyy-MM-dd HH:mm" type="text" readonly="true"  name="offlineTime" style="width:130px" onClick="WdatePicker({el:'new_offlineTime',dateFmt:'yyyy-MM-dd HH:mm',minDate:'1970-01-01'})">
					<span class="info"></span>
				
			</dl>
			<%-- </c:if> --%>
			<dl>
				<dt>
					<bmtag:message messageKey="是否支持预约" />
				</dt>
				<dd>		 
						<select name="elpiIsappoint" class="select_Style required">
							<option value="">-请选择-</option>							
							<option value="1" >是</option>
							<option value="0" >否</option>
						</select>
						<span class="info"></span>	
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="是否有支持通讯" />
				</dt>
				<dd>		 
						<select name="elPiHaveGps" class="select_Style required">
							<option value="">-请选择-</option>							
							<option value="1" >是</option>
							<option value="0" >否</option>
						</select>
						<span class="info"></span>	
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="是否有LED灯" />
				</dt>
				<dd>		 
						<select name="elPiHaveLedFlash" class="select_Style required">
							<option value="">-请选择-</option>							
							<option value="1" >是</option>
							<option value="0" >否</option>
						</select>
						<span class="info"></span>	
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="是否有枪" />
				</dt>
				<dd>		 
						<select name="elPiHaveConnectLine" class="select_Style required">
							<option value="">-请选择-</option>							
							<option value="1" >是</option>
							<option value="0" >否</option>
						</select>
						<span class="info"></span>	
				</dd>
			</dl>
	 		<input type="hidden" name="gateId" value="0" />
			<%-- <c:choose>
			  <c:when test="${loginUserLevel == '1' || loginUserLevel == '2'}">
			    <dl>
				<dt>
					<bmtag:message messageKey="所属Gate服务器" />
				</dt>
				<dd>
					<select class="required"  id="gateId" name="gateId" class="select_Style">
						 <c:forEach var="item" items="${gateList}">
							<option value="${item.pkGateid}">${item.gtseGatename}</option>
						</c:forEach>
					</select> 
					<span class="info"></span>
				</dd>
			</dl>
			  </c:when>
			  <c:otherwise>
			     <input type="hidden" name="gateId"
								value="0" />
			   </c:otherwise>
			</c:choose> --%>
			<dl>
				<dt>
					<bmtag:message messageKey="最大输出电压" />
				</dt>
				<dd>
					<input id="elpiOutputvoltage" name="elpiOutputvoltage" class="textInput required number"
						maxlength="20" style="width: 165px;" value="${tblElectricpile.elpiOutputvoltage}" /> 
						<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="最大输出电流" />
				</dt>
				<dd>
					<input id="elpiOutputcurrent" name="elpiOutputcurrent" class="textInput required number"
						maxlength="20" style="width: 165px;" value="${tblElectricpile.elpiOutputcurrent}" /> 
						<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="electric.icon.muzzleSum" />
				</dt>
				<dd>
					<input id="elpiPowernumber" name=elpiPowernumber class="textInput required"
						maxlength="20" style="width: 165px;" max="4" value="1" readonly/> <span class="info"></span>
				</dd>
			</dl>
			
			<dl>
				<dt>
					<strong>添加枪头信息：</strong>
				</dt>
			</dl>
			<dl>
				<dt>
					<table>
						<colgroup width="30%">
						<colgroup width="25%">
						<colgroup width="25%">
						<colgroup width="20%">
						<thead>
							<tr height="30">
								<th align="left">车位号</th>
								<th align="left">地锁</th>
								<th align="left">雷达</th>
								<th align="left"><div class="button"><div class="buttonContent" style="width: 30px"><button type="button" onclick="addNew()">新增</button></div></div></th>
							</tr>
						</thead>
						<tbody id="row_head">
							<tr>
								<td><input class="required" name="headList[0].ephNum" type="text"></td>
								<td><select style="width: 80px" class="required" name="headList[0].havaCarPlaceLock">
									<option value="0">无</option>
									<option value="1">有</option>
								</select></td>
								<td><select style="width: 80px" class="required" name="headList[0].haveRadar">
									<option value="0">无</option>
									<option value="1">有</option>
								</select></td>
								<td><div class="button"><div class="buttonContent" style="width: 30px"><button type="button" onclick="removeTr(this)">删除</button></div></div></td>
							</tr>
						</tbody>
					</table>
				</dt>
			</dl>
			
		</div>
		
		<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit"
						type="submit" id="formSubmitter" />
				</li>
				<li><bmtag:button messageKey="common.button.back" type="button"
						dwzClass="close" />
				</li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/electric/electric-uploadFile.js" />
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/electric/electric-pileHead.js" />
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
	//获取城市
	function getCity(proviceId){
		 
		$.get(webroot + "/admin/electric/getCityCode.do?proviceId="+proviceId.value+"",{}, function(data) {
			var json = eval("(" + data + ")");
			$(".editCityId").html("");
			$("<option value=''>-请选择-</option>").appendTo(
					$(".editCityId"));
			$.each(json, function(i) { 
				$(  
						"<option value='"+json[i].cityId+"'>"
								+ json[i].cityName + "</option>").appendTo(
						$(".editCityId"));
			});
		});
	}
	//获取区县
	function getCounty(cityId){
		$.get(webroot + "/admin/electric/getCountyCode.do?cityId="+cityId.value+"", {}, function(data) {
			var json = eval("(" + data + ")");
			$(".editCountyId").html("");
			$("<option value=''>-请选择-</option>").appendTo(
					$(".editCountyId"));
			$.each(json, function(i) {
				$(
						"<option value='"+json[i].areaId+"'>"
								+ json[i].areaName + "</option>").appendTo(
						$(".editCountyId"));
			});
			 
		});
	}
	

</script>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<style>
.border-left{
	border-left:1px solid #d0d0d0;
}
.border-top{
	border-top:1px solid #d0d0d0;
}
</style>
<h2 class="contentTitle">
	<bmtag:message messageKey="修改充电点" />
</h2>
<div class="pageContent">
	<form method="post" action="powerstation/changePower.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">

		<div class="pageFormContent nowrap" layoutH="97">

			<dl>
				<dt>
					<bmtag:message messageKey="powerstation.label.name" />
				</dt>
				<dd>
					<input type="hidden" name="pkPowerstation"
						value="${tblPowerstation.pkPowerstation}" /> <input
						name="postName" class="textInput" style="width: 165px;"
						value="${tblPowerstation.postName}" maxlength="80"
						remote="<%=request.getContextPath()%>/common/checkUnique.do?tName=tbl_powerstation&tProperty=poSt_Name&property=postName&pkTProperty=pk_PowerStation&pkTValue=${tblPowerstation.pkPowerstation}" />
						<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="省份" />
				</dt>
				<dd>
					<select name="postOwnProvinceCode" id="postOwnProvinceCode" style="width: 172px !important" class="select_Style" onchange="getCity(this)">
						<option value="">-请选择-</option>
						 <c:forEach var="item" items="${provinceCodeList}">
							<option value="${item.provinceId}" ${item.provinceId==
								tblPowerstation.postOwnProvinceCode ? 'selected="selected"' : ''} >${item.provinceName}</option>
						</c:forEach>
					</select> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="城市" />
				</dt>
				<dd>
					<select id="editCityId" name="postOwnCityCode" class="select_Style editCityId" onchange="getCounty(this)" >
						<option value="">-请选择-</option>
						<c:forEach var="item" items="${cityList}">
							<option value="${item.cityId}" ${item.cityId==
								tblPowerstation.postOwnCityCode ? 'selected="selected"' : ''} >${item.cityName}</option>
						</c:forEach>
					</select> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="区/县" />
				</dt>
				<dd>
					<select id="editCountyId" name="postOwnCountyCode" class="select_Style editCountyId" >
					<option value="">-请选择-</option>
					<c:forEach var="item" items="${countyList}">
							<option value="${item.areaId}" ${item.areaId==
								tblPowerstation.postOwnCountyCode ? 'selected="selected"' : ''} >${item.areaName}</option>
						</c:forEach>
					</select> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="powerstation.label.address" />
				</dt>
				<dd>
					<input name="postAddress" class="textInput required"
						value="${tblPowerstation.postAddress}" maxlength="199"
						style="width: 165px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="powerstation.label.tel" />
				</dt>
				<dd>
					<input name="postPhone" class="textInput required"
						value="${tblPowerstation.postPhone}" maxlength="50"
						style="width: 165px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="electric.label.longitude" />
				</dt>
				<dd>
					<input name="postLongitude" class="textInput required" max="136" min="74"
						value="${tblPowerstation.postLongitude}" maxlength="20"
						style="width: 165px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="electric.label.latitude" />
				</dt>
				<dd>
					<input name="postLatitude" class="textInput required" max="54" min="18"
						value="${tblPowerstation.postLatitude}" maxlength="20"
						style="width: 165px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="充电点状态" />
				</dt>
				<dd>
					<c:choose>
						<c:when test="${tblPowerstation.postStatus!=3}">
							<input type="hidden" name="postStatus"
								value="${tblPowerstation.postStatus}" />
							<c:if test="${tblPowerstation.postStatus ==0}">草稿</c:if>
							<c:if test="${tblPowerstation.postStatus ==5}">提交审核 </c:if>
							<c:if test="${tblPowerstation.postStatus ==12}">分享申请中 </c:if>
							<c:if test="${tblPowerstation.postStatus ==3}">已驳回 </c:if>
							<c:if test="${tblPowerstation.postStatus ==10}">专属 </c:if>
							<c:if test="${tblPowerstation.postStatus ==15}">分享</c:if>
						</c:when>
						<c:otherwise>
							<select name="postStatus" class="select_Style">
								<!-- <option value="0" ${tblPowerstation.postStatus==0
									? 'selected="selected"' : ''}>草稿</option>
								<option value="5" ${tblPowerstation.postStatus==5
									? 'selected="selected"' : ''}>提交审核</option> -->
								<option value="3" ${tblPowerstation.postStatus==3
									? 'selected="selected"' : ''}>已驳回</option>
								<option value="10" ${tblPowerstation.postStatus==10
									? 'selected="selected"' : ''}>专属</option>
								<!-- 		<option value="15" ${tblPowerstation.postStatus==15
									? 'selected="selected"' : ''}>上线</option> -->
							</select>
						</c:otherwise>
					</c:choose>
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="是否支持预约" />
				</dt>
				<dd>		 
						<select name="postIsappoint" class="select_Style required">
							<option value="">-请选择-</option>				
							<option value="1" <c:if test="${tblPowerstation.postIsappoint == 1}">selected="selected"</c:if>>是</option>
							<option value="0" <c:if test="${tblPowerstation.postIsappoint == 0}">selected="selected"</c:if>>否</option>
						</select>
						<span class="info"></span>	
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="electric.label.listImage" />
				</dt>
				<dd id="imageDD">
					<c:forEach var="item" items="${tblPowerstation.postPicUrl}" varStatus="status" >
							<t>
							<img  src="${item}" style="width: 150px; height: 150px;" />
							<a href="javascript:void(0);" onclick="deleteFile('${item}',this,'${tblPowerstation.pkPowerstation}')">删除</a>
							</t>
					</c:forEach>
					<input type="hidden" id="businessType" name="businessType" value="powerListImage" />
					<input type="hidden" id="fileId" name="fileId" />
					<input id="powerstationEditFileInput" type="file" name="listImage" class="file"
						style="width: 260px; margin-top: 25px" 
						uploaderOption="{
							swf:'<%=request.getContextPath()%>/res/dwz/uploadify/scripts/uploadify.swf',
							uploader:'<%=request.getContextPath()%>/common/uploadFile.do;jsessionid=<%=session.getId()%>',
							formData:{type:'powerListImage',isZip:'1',referenceId:'${tblPowerstation.pkPowerstation}'},
							buttonText:'请选择文件',
							queueID:'fileQueue',
							queueSizeLimit:'6',
							fileSizeLimit:'8MB',
							fileTypeDesc:'*.jpg;*.jpeg;*.gif;*.png;',
							fileTypeExts:'*.jpg;*.jpeg;*.gif;*.png;',
							fileObjName: 'file', 
							auto:true,
							multi:true,
							onUploadStart:electricFileUploadStart,
							onUploadSuccess:electricFileUploadSuccess,
							onQueueComplete:electricFileUploadComplete
						}" /> 
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="开放时间" />
				</dt>
				<dd>
            <input name="poStOnlineTime" class="textInput" value="${tblPowerstation.poStOnlineTime}" style="width: 165px">
				</dd>
			</dl>
			<dl></dl>
			<dl>
				<dt>
					<bmtag:message messageKey="绑定电桩" />
				</dt>
				<dd>
					<input name="tblElectricpile.pkElectricpile" value="" type="hidden">
					<input name="tblElectricpile.elpiElectricpilename" type="text"
						readonly="true" /> 
						<c:if test="${tblPowerstation.postStatus==10}"><a class="btnLook"
						href="../admin/powerstation/getPowersElectricPileList.do"
						lookupGroup="tblElectricpile" callback="selectPile">查找电桩</a> <span class="info">(请选择电桩)</span></c:if>
				</dd>
			</dl>
			<dl>已绑电桩列表
			</dl>
			<dl></dl>
			<table class="table" width="100%">
				<thead>
					<tr class="border-left border-top" align="center">
						<!-- <th><input type="checkbox" group="ids" class="checkboxCtrl">
						</th> -->
						<th style="width: 5%"><bmtag:message
								messageKey="common.label.index" /></th>
						<th style="width: 15%"><bmtag:message
								messageKey="electric.label.code" /></th>
						<th style="width: 10%"><bmtag:message messageKey="编号" /></th>
						<th style="width: 10%"><bmtag:message
								messageKey="electric.label.name" /></th>
						<th style="width: 10%"><bmtag:message
								messageKey="electric.label.state" /></th>
						<th style="width: 10%"><bmtag:message
								messageKey="electric.label.way" /></th>
						<th style="width: 10%"><bmtag:message
								messageKey="electric.label.connector" /></th>
						<th style="width: 10%"><bmtag:message
								messageKey="electric.label.type" /></th>
						<th style="width: 10%"><bmtag:message
								messageKey="electric.label.power" /></th>
						<th style="width: 5%"><bmtag:message
								messageKey="费率id" /></th>
						<th style="width: 5%"><bmtag:message messageKey="操作" /></th>
					</tr>
				</thead>
				<tbody id="selectPileTable">
					<c:forEach items="${electricList}" var="electric"
						varStatus="status">
						<tr class="border-left" align="center" target="id"
							rel="${electric.pk_ElectricPile}">
							<td style="width: 5%">${ status.index + 1 +
								pager.numPerPage*(pager.pageNum-1)}</td>
							<td style="width: 15%">${electric.elPi_ElectricPileCode}</td>
							<td style="width: 10%">
								<input type="hidden" name="pkElectricpile" value="${electric.pk_ElectricPile}" /> 
								<input type="hidden" name="index" value="${ status.index + 1 +pager.numPerPage*(pager.pageNum-1)}" />
								<input type="hidden" name="oldEpNum" value="${electric.ep_num}" />
								<input type="hidden" name="electricPileCode" value="${electric.elPi_ElectricPileCode}" />
								<input type="text" class="epNum" name="epNum" value="${electric.ep_num}" size="1" style="float: none;" /> 号桩
							</td>
							<td style="width: 10%">${electric.elPi_ElectricPileName}</td>
							<td style="width: 10%">
							<c:if test="${electric.elPi_State==0}">草稿</c:if> 
							<c:if test="${electric.elPi_State ==5}">提交审核 </c:if>
						    <c:if test="${electric.elPi_State ==12}">分享申请中 </c:if> 
						    <c:if test="${electric.elPi_State ==10}">专属 </c:if> 
						    <c:if test="${electric.elPi_State ==15}">分享</c:if>
						    </td>
							<td style="width: 10%">${electric.chargingModeName}</td>
							<td style="width: 10%">${electric.powerName}</td>
							<td style="width: 10%">${electric.typeName}</td>
							<td style="width: 10%">${electric.powerSizeName}</td>
							<td style="width: 5%"><input type="hidden" name="oldRateId" value="${electric.elPi_RateInformationId}"/>
								<c:if test="${electric.elPi_State ==5}"><input type="text" name="rateId" readonly value="${electric.elPi_RateInformationId}" size="1" style="float: none;" /> </c:if>
							    <c:if test="${electric.elPi_State ==12}"><input type="text" name="rateId" readonly  value="${electric.elPi_RateInformationId}" size="1" style="float: none;" /> </c:if> 
							    <c:if test="${electric.elPi_State ==10}"><input type="text" name="rateId"  value="${electric.elPi_RateInformationId}" size="1" style="float: none;" /> </c:if> 
							    <c:if test="${electric.elPi_State ==15}"><input type="text" name="rateId"  value="${electric.elPi_RateInformationId}" size="1" style="float: none;" /></c:if>
							</td>
							<td style="width: 5%"><c:if
									test="${tblPowerstation.postStatus==10}">
									<a target="ajaxTodo"
										href="<c:url value='powerstation/unbindElectricPile.do?pkElectricpile=${electric.pk_ElectricPile}'/>"
										title="确定要处理吗？">解绑</a>
							</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
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
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/powerstation/powerstation-edit.js" />
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/electric/electric-uploadFile.js" />
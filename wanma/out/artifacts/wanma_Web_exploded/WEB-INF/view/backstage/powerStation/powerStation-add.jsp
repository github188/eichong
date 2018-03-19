<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		 
		$.get(webroot + "/admin/powerstation/getCityCode.do?proviceId="+proviceId.value+"",{}, function(data) {
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
		$.get(webroot + "/admin/powerstation/getCountyCode.do?cityId="+cityId.value+"", {}, function(data) {
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
	
</script>
<h2 class="contentTitle">
	<bmtag:message messageKey="powerstation.addPower" />
</h2>
<div class="pageContent">
	<form method="post" action="powerstation/addPowers.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">

		<div class="pageFormContent nowrap" layoutH="97">

			<dl>
				<dt>
					<bmtag:message messageKey="powerstation.label.name" />
				</dt>
				<dd>
					<input name="postName" class="textInput required"
						value="${tblPowerstation.postName}" style="width: 165px;" 
						remote="<%=request.getContextPath()%>/common/checkUnique.do?tName=tbl_powerstation&tProperty=poSt_Name&property=postName" />
						 <span
						class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="省份" />
				</dt>
				<dd>
					<select name="postOwnProvinceCode" style="width: 172px !important" class="select_Style" onchange="getCity(this)">
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
					<select id="cityId" name="postOwnCityCode" class="select_Style addCityId" onchange="getCounty(this)" >
						<option value="">-请选择-</option>
					</select> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="区/县" />
				</dt>
				<dd>
					<select id="countyId" name="postOwnCountyCode" class="select_Style addCountyId" >
					<option value="">-请选择-</option>
					</select> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="详细地址" />
				</dt>
				<dd>
					<input name="postAddress" class="textInput required"
						maxlength="199" style="width: 165px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="powerstation.label.tel" />
				</dt>
				<dd>
					<input name="postPhone" value="4000850006" class="textInput" maxlength="20"
						style="width: 165px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="electric.label.longitude" />
				</dt>
				<dd>
					<input name="postLongitude" class="textInput required" max="136" min="74"
						maxlength="11" style="width: 165px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="electric.label.latitude" />
				</dt>
				<dd>
					<input name="postLatitude" class="textInput required" max="54" min="18"
						maxlength="11" style="width: 165px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="是否支持预约" />
				</dt>
				<dd>		 
						<select name="postIsappoint" class="select_Style required">
							<option value="">-请选择-</option>			
							<option value="1">是</option>
							<option value="0">否</option>
						</select>
						<span class="info"></span>	
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
					<input type="hidden" id="businessType" name="businessType" value="powerListImage" />
					<input type="hidden" id="fileId" name="fileId" />
					<input id="powerstationAddFileInput" type="file" name="listImage" class="file"
						style="width: 260px;" 
						uploaderOption="{
							swf:'<%=request.getContextPath()%>/res/dwz/uploadify/scripts/uploadify.swf',
							uploader:'<%=request.getContextPath()%>/common/uploadFile.do;jsessionid=<%=session.getId()%>',
							formData:{type:'powerListImage',isZip:'1'},
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
						}"/> 
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="powerstation.label.electricunbind" />
				</dt>
				<dd>
					<input name="tblElectricpile.pkElectricpile" value="" type="hidden">
					<input name="tblElectricpile.elpiElectricpilename" type="text"  readonly="true"
						readonly="true" /> <!-- <a class="btnLook"
						href="../admin/powerstation/getPowersElectricPileList.do"
						lookupGroup="tblElectricpile" >查找电桩</a><span class="info">(请选择电桩)</span> --> 
				</dd>
			</dl>
					<dl>
			<dt>
				<bmtag:message messageKey="开放时间" />
			</dt>
			<dd>
				<input name="poStOnlineTime" class="textInput" style="width: 165px">
				<span class="info"></span>
			</dd>
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
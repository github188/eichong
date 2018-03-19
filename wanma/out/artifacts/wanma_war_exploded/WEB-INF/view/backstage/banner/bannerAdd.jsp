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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript">
var webroot = "${webroot}";
function ajaxDoneCallback(json){
}
//获取城市
function getCity(proviceId){
	 if(proviceId.value==""){
		$("#bannerCityId").hide();
		$("#bannerCityCode").val("");
	 }else{
		 $.get(webroot + "/admin/electric/getCityCode.do?proviceId="+proviceId.value+"",{}, function(data) {
				var json = eval("(" + data + ")");
				$(".bannerCityId").html("");
				 $("<option value=''>-请选择-</option>").appendTo(
						$(".bannerCityId"));
				$.each(json, function(i) { 
					$(  
							"<option value='"+json[i].cityId+"'>"
									+ json[i].cityName + "</option>").appendTo(
							$(".bannerCityId"));
				}); 
				$("#bannerCityId").show();
			});
	 }
	
}

function formatBanner_add() {
	var endTime = $("#bannerEndTime_f1").val();
	var str=endTime.toString();
    str =  str.replace(/-/g,"/");
    var end = new Date(str);
    var now = new Date();
    if(end<now){
    	alertMsg.error("结束时间不能小于当前时间！");
    	return;
    }
    var AdSize = $("#addbaImage t").length;
	if (AdSize == 0) {
		alertMsg.error("banner图片不可为空！");
		return;
	}
	var bannerProvinceCode = $("#bannerProvinceCode").val();
	var bannerCityCode = $("#bannerCityCode").val();
	if(bannerProvinceCode!=""&&bannerCityCode==""){
		alertMsg.error("地区不能为空！");
		return;
	}
	$("#bannerFormAdd").submit();
}
</script>
<head>

 <style type="text/css">
.divcss5{ border-left:10px solid #00F} ;
 </style>
 </head>

<h2 class="contentTitle">新增banner</h2>
<div  class="pageContent">
	<form  method="post" action="banner/addBanner.do" id="bannerFormAdd"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97"> 
		<dl><dt>banner图片</dt>
			<dd id="addbaImage">
					<input type="hidden" id="businessType_add" name="businessType" value="bannerPic" />
					<input type="hidden" id="bannerFileId_add" name="bannerPicFileId" />
					<input  id="bannerFileInput_add"  type="file" name="listImage" class="file" style="width: 260px;" 
					uploaderOption="{
							swf:'<%=request.getContextPath()%>/res/dwz/uploadify/scripts/uploadify.swf',
							uploader:'<%=request.getContextPath()%>/common/uploadFile.do',
							formData:{type:'bannerPic',isZip:'1'},
							buttonText:'上传',
							queueSizeLimit:'1',
							fileSizeLimit:'4096K',
							fileTypeDesc:'*.jpg;*.png;',
							fileTypeExts:'*.jpg;*.png;',
							fileObjName: 'file', 
							auto:true,
							multi:true,
							overrideEvents:[ 'onDialogClose', 'onUploadSuccess', 'onUploadError', 'onSelectError' ],
							onSelectError:uploadify_onSelectError,
   							onUploadError:uploadify_onUploadError,
							onUploadStart:FileUploadStart,
							onUploadSuccess:FileUploadSuccess,
							onQueueComplete:FileUploadComplete
						}"
					/> 	
			</dd>
		</dl>
		<dl>
			<dt>链接url</dt>
			<dd>
				<input name="bannerUrl" id="bannerUrl"  class="required url" style="width: 165px;" /> <span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt>省份</dt>
			<dd>
				<select name="bannerProvinceCode" style="width: 172px !important" class="select_Style" onchange="getCity(this)" id="bannerProvinceCode">
								<option value="">全国</option>
								<c:forEach var="item" items="${proviceMap}">
									<option value="${item.key}"
										${item.key== tblElectricpile.elPiOwnProvinceCode ? 'selected="selected"' : ''} >
										${item.value.PROVINCE_NAME}
									</option>
								</c:forEach>
				</select>
			</dd>
		</dl>
		<dl id="bannerCityId" style="display:none">
			<dt>
				<bmtag:message messageKey="城市" />
			</dt>
			<dd>
				<select  name="bannerCityCode" class="select_Style bannerCityId" id="bannerCityCode"  >
					<option value="">-请选择-</option>
				</select> <span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt>开始时间</dt>
			<dd>
				 <input  class="textInput required"  id="bannerBeginTime_f1" name="bannerBeginTime"  readonly="true" value="${advModel.bannerBeginTime}" class="date"  style="width:155px"
					 onClick="WdatePicker({el:'bannerBeginTime_f1',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'1990-01-01',maxDate:'#F{$dp.$D(\'bannerEndTime_f1\')}'})"> <span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt>结束时间</dt>
			<dd>
				<input  class="textInput required" id="bannerEndTime_f1"   name="bannerEndTime" readonly="true" value="${advModel.bannerEndTime}" class="date"  style="width:155px"
		             onClick="WdatePicker({el:'bannerEndTime_f1',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'bannerBeginTime_f1\')}'})"><span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt>备注</dt>
			<dd>
				<textarea class="required" rows="5" cols="50" name="bannerDesc" maxlength="250"></textarea>
			</dd>
		</dl>
	</div>	
		<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit" onclick="formatBanner_add()"
						type="button" id="formSubmitter" />
				</li>
				<li><bmtag:button messageKey="common.button.back" type="button"
						dwzClass="close" />
				</li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/banner/uploadBanner_add.js" />
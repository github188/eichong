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
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript">
	var obj = document.getElementById('PadIsGoto');
	init_change();
	function init_change() {
		var index = obj.selectedIndex; // 选中索引
		var text = obj.options[index].text; // 选中文本
		var value = obj.options[index].value; // 选中值
		if (value == 1) {
			$("#Pdivs").show();
			$('#PadDesc').attr('class', 'textInput required');
			$('#PadURL').attr('class', 'textInput required');

		} else if (value == 0) {
			$("#Pdivs").hide();
			$('#PadDesc').attr('class', '');
			$('#PadURL').attr('class', '');

		}
	}

	obj.onchange = function() {

		var index = obj.selectedIndex; // 选中索引
		var text = obj.options[index].text; // 选中文本
		var value = obj.options[index].value; // 选中值

		if (value == 1) {
			$("#Pdivs").show();
			$('#showPicMsg').val('1');
			$('#PadDesc').attr('class', 'textInput required');
			$('#PadURL').attr('class', 'textInput required');

		} else if (value == 0) {
			$("#Pdivs").hide();
			$('#showPicMsg').val('0');
			$('#PadDesc').attr('class', '');
			$('#PadURL').attr('class', '');

		}
	}
	function PformatJson() {
		var endTime = $("#endAdTime_f4").val();
		var str = endTime.toString();
		str = str.replace(/-/g, "/");
		var end = new Date(str);
		var now = new Date();
		var flag = $('#PadIsGoto').val();
		if (end < now) {
			alertMsg.error("结束时间不能小于当前时间！");
			return;
		}
		if (flag == 0) {
			$('#PadDesc').val(' ');
			$('#PadURL').val(' ');
			$("#Pdelete_edit").click();
			$("#Pdelete_img").click();

		}
		if (flag == 1) {
			var PlSize = $("#editPeImageList t").length;
			var urllink = $('#PadURL').val();
			var reg = /^http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- ./?%&=]*)?$/;
			if (PlSize == 0) {
				alertMsg.error("插屏列表图片不可为空！");
				return;
			}
			if (!reg.test(urllink)) {
				alertMsg.error('输入正确的url链接');
				return;
			}
		}
		var PdSize = $("#editPeImage t").length;
		if (PdSize == 0) {
			alertMsg.error("插屏图片不可为空！");
			return;
		}
		img_d = $("#editPeImage");
		var delImgUrl = img_d.attr("data-delImgUrl");
		var refId = $("#pkAdIdDivPe").val();
		if (delImgUrl) {
			var fileName=delImgUrl.substring(delImgUrl.lastIndexOf("/")+1,delImgUrl.length);
			var businessType=$("#PbusinessType").val();
			$.ajax({
				type : 'post',
				url : basepath+"/common/deleteFile.do",
				dataType : "json",
				data:{
						businessType:businessType,
						referenceId:refId,
						fileName:fileName
				},
				success : function(data) {
				}
			});	
		}
		imgList_d = $("#editPeImageList");
		var delImgListUrl = imgList_d.attr("data-delImgUrl");
		if (delImgListUrl) {
			var fileName=delImgListUrl.substring(delImgListUrl.lastIndexOf("/")+1,delImgListUrl.length);
			var businessType=$("#plaqueListType").val();
			$.ajax({
				type : 'post',
				url : basepath+"/common/deleteFile.do",
				dataType : "json",
				data:{
						businessType:businessType,
						referenceId:refId,
						fileName:fileName
				},
				success : function(data) {
				}
			});	
		}

		$("#plaqueForm").submit();

	}

	function removeImgDiv(this_e, imgDd, imgUrl) {
		$(this_e).parent().remove();
		var img_d = $("#" + imgDd);
		img_d.attr("data-delImgUrl", imgUrl)
	}
</script>
<head>

<style type="text/css">
.divcss5 {
	border-left: 10px solid #00F
}
;
</style>
</head>

<h2 class="contentTitle">编辑闪屏</h2>
<div class="pageContent">

	<form method="post" action="advertisement/updateAdvertisement.do"
		id="plaqueForm" class="pageForm required-validate"
		enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<input id="pkAdIdDivPe" type="hidden" name="pkAdId"
			value="${advModel.pkAdId }" /> <input type="hidden" name="adType"
			value="2" />
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dt style="width: 80px;">名称</dt>
				<dd>
					<input name="adName" class="textInput required" maxlength="20"
						style="width: 165px;" value="${advModel.adName }" /><span
						class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt style="width: 80px;">曝光时间</dt>
				<dd>
					<select name="adLocation" class="select_Style"
						style="width: 150px;">

						<option value="1"
							${advModel.adLocation==1
									? 'selected="selected"' : ''}>左一button</option>
						<option value="2"
							${advModel.adLocation==2
									? 'selected="selected"' : ''}>左二button</option>
						<option value="3"
							${advModel.adLocation==3
									? 'selected="selected"' : ''}>右二button</option>
						<option value="4"
							${advModel.adLocation==4
									? 'selected="selected"' : ''}>右一button</option>

					</select> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>插屏图片</dt>
				<dd id="editPeImage" data-delImgUrl="">
					<c:if test="${advModel.advPicUrl!=null}">
						<t> <img src="${advModel.advPicUrl}"
							style="width: 150px; height: 150px;" /> <a
							href="javascript:void(0);"
							onclick="removeImgDiv(this,'editPeImage','${advModel.advPicUrl}')">删除</a>
					</c:if>
					</t>
					<input type="hidden" id="PbusinessType" name="businessType"
						value="advPic" /> <input type="hidden" id="PadvPicFileId"
						name="advPicFileId" /> <input type="hidden" id="showPicMsg"
						value="1" /> <input id="plaqueFileInput" type="file"
						name="listImage" class="file" style="width: 260px;"
						uploaderOption="{
							swf:'<%=request.getContextPath()%>/res/dwz/uploadify/scripts/uploadify.swf',
							uploader:'<%=request.getContextPath()%>/common/uploadFile.do',
							formData:{type:'advPic',isZip:'1'},
							buttonText:'请选择文件',
							queueSizeLimit:'1',
							fileSizeLimit:'200K',
							fileTypeDesc:'*.jpg;',
							fileTypeExts:'*.jpg;',
							fileObjName: 'file', 
							auto:true,
							multi:true,
							overrideEvents:[ 'onDialogClose', 'onUploadSuccess', 'onUploadError', 'onSelectError' ],
							onSelectError:uploadify_onSelectError,
   							onUploadError:uploadify_onUploadError,
							onUploadStart:FileUploadStart,
							onUploadSuccess:FileUploadSuccess,
							onQueueComplete:FileUploadComplete
						}" />

				</dd>
			</dl>
			<dl>
				<dt style="width: 80px;">是否点击跳转</dt>
				<dd>
					<select id="PadIsGoto" name="adIsGoto" class="select_Style"
						style="width: 150px;">

						<option value="0"
							${advModel.adIsGoto==0
									? 'selected="selected"' : ''}>否</option>
						<option value="1"
							${advModel.adIsGoto==1
									? 'selected="selected"' : ''}>是</option>
					</select> <span class="info"></span>
				</dd>
			</dl>

			<div id="Pdivs" style="display: none">
				<dl>
					<dt style="width: 80px;">说明</dt>
					<dd>
						<input name="adDesc" id="PadDesc" value="${advModel.adDesc }"
							maxlength="35" style="width: 165px;" /> <span class="info"></span>
						<span class="info"></span>
					</dd>
				</dl>
				<dl>
					<dt style="width: 80px;">url地址</dt>
					<dd>
						<input name="adURL" id="PadURL" value="${advModel.adURL }"
							 style="width: 165px;" /> <span class="info"></span>
						<span class="info"></span>
					</dd>
				</dl>
				<dl>
					<dt>动态列表图片</dt>
					<dd id="editPeImageList" data-delImgUrl="">
						<c:if test="${advModel.advListPicUrl!=null}">
							<t> <img src="${advModel.advListPicUrl}"
								style="width: 150px; height: 150px;" /> <a id="Pdelete_img"
								href="javascript:void(0);"
								onclick="removeImgDiv(this,'editPeImageList','${advModel.advListPicUrl}')">删除</a>
							</t>
						</c:if>
						<input type="hidden" id="plaqueListType" name="plaqueListType"
							value="advListPic" /> <input type="hidden"
							id="PadvPicFileId_list" name="advListFileId" /> <input
							id="plaqueFileInput_list" type="file" name="listImage"
							class="file" style="width: 260px;"
							uploaderOption="{
							swf:'<%=request.getContextPath()%>/res/dwz/uploadify/scripts/uploadify.swf',
							uploader:'<%=request.getContextPath()%>/common/uploadFile.do',
							formData:{type:'advListPic',isZip:'1'},
							buttonText:'请选择文件',
							queueSizeLimit:'1',
							fileSizeLimit:'200K',
							fileTypeDesc:'*.jpg;',
							fileTypeExts:'*.jpg;',
							fileObjName: 'file', 
							auto:true,
							multi:true,
							overrideEvents:[ 'onDialogClose', 'onUploadSuccess', 'onUploadError', 'onSelectError' ],
							onSelectError:uploadify_onSelectError,
   							onUploadError:uploadify_onUploadError,
							onUploadStart:splashUploadStart_list,
							onUploadSuccess:splashUploadSuccess_list,
							onQueueComplete:FileUploadComplete
						}" />

					</dd>
				</dl>
			</div>
			<dl>
				<dt style="width: 80px;">开始时间</dt>
				<dd>
					<input class="textInput required" id="beginAdTime_f4"
						name="beginAdTime" readonly="true"
						value="<fmt:formatDate value='${advModel.beginAdTime}' pattern='yyyy-MM-dd HH:mm:ss'/>"
						class="date" style="width: 155px"
						onClick="WdatePicker({el:'beginAdTime_f4',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'1990-01-01',maxDate:'#F{$dp.$D(\'endAdTime_f4\')}'})">
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt style="width: 80px;">结束时间</dt>
				<dd>
					<input class="textInput required" id="endAdTime_f4"
						name="endAdTime" readonly="true"
						value="<fmt:formatDate value='${advModel.endAdTime }' pattern='yyyy-MM-dd HH:mm:ss'/>"
						class="date" style="width: 155px"
						onClick="WdatePicker({el:'endAdTime_f4',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginAdTime_f4\')}'})"><span
						class="info"></span>
				</dd>
			</dl>

		</div>

		<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit"
						onclick="PformatJson()" type="button" id="formSubmitter" /></li>
				<li><bmtag:button messageKey="common.button.back" type="button"
						dwzClass="close" /></li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/static/js/advertisement/uploadPlaque_edit.js" />

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
var obj_add = document.getElementById('adIsGoto_add');

obj_add.onchange = function() {
	
	var index = obj_add.selectedIndex; // 选中索引
	var text = obj_add.options[index].text; // 选中文本
	var value = obj_add.options[index].value; // 选中值

	if (value == 1) {
		$("#divs_add").show();
		$('#showPicMsg').val('1');
		$('#adDesc_add').attr('class', 'textInput required');
		$('#adURL_add').attr('class', 'textInput required'); 

	} else if (value == 0) {
		$("#divs_add").hide();
		$('#showPicMsg').val('0');
		$('#adDesc_add').attr('class', '');
		$('#adURL_add').attr('class', '');
		
	
		
	}
}
function formatJson_add(){
	  var endTime = $("#endAdTime_f1").val();  
	  var  str=endTime.toString();
	       str =  str.replace(/-/g,"/");
	  var end = new Date(str);
	  var now = new Date();
	  var flag=$('#adIsGoto_add').val();
	  
	   
	    if(end<now){
	    	alertMsg.error("结束时间不能小于当前时间！");
	    	return;
	    }
	    if(flag==0){
	   
			$('#adDesc_add').val('');
		
			$('#adURL_add').val('');
		
			$("#delete_list").click();
			
	    }
	    if (flag ==1) {
			var AlSize = $("#addShImageList t").length;
			var urllink = $('#adURL_add').val();
			var reg = /^http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- ./?%&=]*)?$/;
			if (AlSize == 0) {
				alertMsg.error("闪屏列表图片不可为空！");
				return;
			}
			if (!reg.test(urllink)) {
				alertMsg.error('输入正确的url链接');
				return;
			}
		}
		var AdSize = $("#addShImage t").length;
		if (AdSize == 0) {
			alertMsg.error("闪屏图片不可为空！");
			return;
		}

	  $("#splashForm_add").submit();
	
}

</script>
<head>

 <style type="text/css">
.divcss5{ border-left:10px solid #00F} ;
 </style>
 </head>
<h2 class="contentTitle">新增闪屏</h2>
<div class="pageContent">

	<form method="post" action="advertisement/addAdvertisement.do" id="splashForm_add"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
	    <input type="hidden" name="adType" value="1" /> 
	 	<input type="hidden" id="showPicMsg" value="1" />
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dt style="width:80px;">名称</dt>
				<dd>
					<input name="adName" class="textInput required"
						maxlength="20" style="width: 165px;" /><span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt style="width:80px;">曝光时间</dt>
				<dd>
					 <select name="adTime" class="select_Style" style="width: 150px;">
							<option value="3" >3秒</option>
							<option value="4" >4秒</option>
							<option value="5" >5秒</option>	
						</select>
					<span class="info"></span>
				</dd>
			</dl>
			<dl><dt>
				闪屏图片
				</dt>
				<dd id="addShImage">
					<input type="hidden" id="businessType_add" name="businessType" value="advPic" />
					<input type="hidden" id="advPicFileId_add" name="advPicFileId" />
					<input  id="splashFileInput_add"  type="file" name="listImage" class="file" style="width: 260px;" 
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
						}"
					/> 
					
				</dd></dl>
				<dl>
				<dt style="width:80px;">是否点击跳转</dt>
				<dd>
					 <select id="adIsGoto_add" name="adIsGoto" class="select_Style" style="width: 150px;">
							<option value="0" >否</option>
							<option value="1" >是</option>
						</select>
					<span class="info"></span>
				</dd>
			</dl>
		
		<div id="divs_add" style="display: none" >
			<dl>
				<dt style="width:80px;">说明</dt>
				<dd>
					<input name="adDesc"  id="adDesc_add"
						maxlength="35" style="width: 165px;" /> <span class="info"></span>
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt style="width:80px;">url地址</dt>
				<dd>
					<input name="adURL" id="adURL_add"
					 style="width: 165px;" /> <span class="info"></span>
					<span class="info"></span>
				</dd>
			</dl>
			<dl><dt>
				动态列表图片
				</dt>
				<dd id="addShImageList">
					 <input type="hidden" id="splashListType_add" name="splashListType" value="advListPic" />
					<input type="hidden" id="advListFileId_list_add" name="advListFileId" /> 
					<input  id="splashFileInput_list_add"  type="file" name="listImage" class="file" style="width: 260px;" 
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
						}"
					/> 
					
				</dd></dl>
			</div>
			<dl>
				<dt style="width:80px;">开始时间</dt>
				<dd>
					 <input  class="textInput required"  id="beginAdTime_f1" name="beginAdTime"  readonly="true" value="${advModel.beginAdTime}" class="date"  style="width:155px"
						 onClick="WdatePicker({el:'beginAdTime_f1',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'1990-01-01',maxDate:'#F{$dp.$D(\'endAdTime_f1\')}'})"> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt style="width:80px;">结束时间</dt>
				<dd>
					<input  class="textInput required" id="endAdTime_f1"   name="endAdTime" readonly="true" value="${advModel.endAdTime}" class="date"  style="width:155px"
			             onClick="WdatePicker({el:'endAdTime_f1',dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'#F{$dp.$D(\'beginAdTime_f1\')}'})"><span class="info"></span>
				</dd>
			</dl>
			
		</div>
		
		<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit" onclick="formatJson_add()"
						type="button" id="formSubmitter" />
				</li>
				<li><bmtag:button messageKey="common.button.back" type="button"
						dwzClass="close" />
				</li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/advertisement/uploadSplash_add.js" />

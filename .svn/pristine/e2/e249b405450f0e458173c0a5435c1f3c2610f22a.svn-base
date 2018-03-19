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

<head>

 <style type="text/css">
.divcss5{ border-left:10px solid #00F} ;
 </style>
 </head>

<h2 class="contentTitle">编辑资讯</h2>
<div  class="pageContent">
	<form  method="post" action="newsInfo/editNewsInfo.do" id="newsInfoFormEdit"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<input type="hidden" name="newsInfoId" value="${newsInfo.newsInfoId}" />
		<div class="pageFormContent nowrap" layoutH="97"> 
		<dl><dt>资讯图片</dt>
			<dd id="editniImage">
					<c:forEach var="item" items="${newsInfo.newsInfoPicUrl}" varStatus="status" >
							<t>
							<img  src="${item}" style="width: 150px; height: 150px;" />
							<a href="javascript:void(0);" onclick="deleteFile('${item}',this,'${newsInfo.newsInfoId}')">删除</a>
							</t>
					</c:forEach>
					<input type="hidden" id="businessType_edit" name="businessType" value="newInfoPic" />
					<input type="hidden" id="newsInfoFileId_edit" name="newsInfoFileId" />
					<input  id="newsInfoFileInput_edit"  type="file" name="listImage" class="file" style="width: 260px;" 
					uploaderOption="{
							swf:'<%=request.getContextPath()%>/res/dwz/uploadify/scripts/uploadify.swf',
							uploader:'<%=request.getContextPath()%>/common/uploadFile.do',
							formData:{type:'newInfoPic',isZip:'1'},
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
			<dt>标题</dt>
			<dd>
				<input name="newsInfoName" id="newsInfoName" maxlength="64" class="required" style="width: 300px;"  value="${newsInfo.newsInfoName}"/> <span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt>链接</dt>
			<dd>
				<input name="newsInfoUrl" id="newsInfoUrl"  class="required url" style="width: 300px;"  value="${newsInfo.newsInfoUrl}"/> <span class="info"></span>
			</dd>
		</dl>
	</div>	
		<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit" onclick="formatNewsInfo_edit()"
						type="button" id="formSubmitter" />
				</li>
				<li><bmtag:button messageKey="common.button.back" type="button"
						dwzClass="close" />
				</li>
			</ul>
		</div>
	</form>
</div>

<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/newsInfo/uploadNewsInfo_edit.js" />
<script type="text/javascript">
var webroot = "${webroot}";
function ajaxDoneCallback(json){
}

function formatNewsInfo_edit() {
    var AdSize = $("#editniImage t").length;
    if (AdSize == 0) {
		alertMsg.error("资讯图片不可为空！");
		return;
	}
	$("#newsInfoFormEdit").submit();
}
</script>
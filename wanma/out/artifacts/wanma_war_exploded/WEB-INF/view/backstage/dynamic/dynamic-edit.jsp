<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">
showFile();	
$("input[name='releImg']").click(function(){
	showFile();	
	});
function showFile(){
	var obj=$("input[name='releImg']:checked")[0];
	if(obj.value==1){
		  $("#file").show();
	  }else{
		  $("#file").hide();
	  }
}	
	
</script>
<div class="pageContent">
	<form method="post" action="dynamic/update.do" id="editForm"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone);">
		
		<input type="hidden" name="pkRelease" value="${dynamic.pkRelease}" />
		
		
		<div class="pageFormContent nowrap" layoutH="55">
			<dl>
				<dt>标题：</dt>
				<dd>
					<input name="releTitle" type="text" class="required" value="${dynamic.releTitle}"  size="25"/>
				</dd>
			</dl>
			<dl>
				<dt>简介：</dt>
				<dd>
					<textarea name="briefIntroduction" type="text" class="required" size="80">${dynamic.briefIntroduction}</textarea>
				</dd>
			</dl>
			<dl>
				<dt>内容：</dt>
				<dd>
					<textarea class="editor" name="releContent" upImgUrl="dynamic/upload.do" upImgExt="jpg,jpeg,gif,png" rows="16" cols="100">${dynamic.releContent}</textarea>
				</dd>
			</dl>
			<dl>
				<dt>类型：</dt>
				<dd>
					<select name="releType">
						<option value="1" ${dynamic.releType==1?'selected' : ''}>活动中心</option>
						<option value="2" ${dynamic.releType==2?'selected' : ''}>爱充动态</option>
						<option value="3" ${dynamic.releType==3?'selected' : ''} >行业资讯</option>
					</select>
				</dd>
			</dl>
			<dl >
				<dt style="margin-top:50px;">是否首图：</dt>
				<dd>
					是 <input type="radio" name="releImg" value="1" ${dynamic.releImg==1?'checked' : ''}/>
					否 <input type="radio" name="releImg" value="0" ${dynamic.releImg==0?'checked' : ''}/>
					<img alt="" src="${dynamic.imageUrl}" 
							width="100px" height="100px">
					<input style="display:none" id="file" type="file" name="file" class="file"  style="width:360px;"/>
				</dd>
			</dl>
			<dl>
				<dt>有效：</dt>
				是 <input type="radio" name="validFlag" value="1" ${dynamic.validFlag==1?'checked' : ''} />
				否 <input type="radio" name="validFlag" value="0" ${dynamic.validFlag==0?checked : ''} />
			</dl>
			<dl>
				<dt>排序号：</dt>
				 <input type="text" class="digits" name="releOrder" value="${dynamic.releOrder}" style="width:30px;" />
			</dl>
		</div>
		
		
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button id="addButton" type="submit">
								保存
							</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">
								取消
							</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
	
</div>

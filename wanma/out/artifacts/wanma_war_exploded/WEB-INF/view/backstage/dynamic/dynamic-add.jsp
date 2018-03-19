<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">
$("input[name='releImg']").click(function(){
	  if(this.value==1){
		  $("#file").show();
	  }else{
		  $("#file").hide();
	  }
	});
</script>
<div class="pageContent">
	<form method="post" action="dynamic/add.do" id="addForm"
		class="pageForm required-validate"  enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone);">

		<div class="pageFormContent nowrap" layoutH="55">
			<dl>
				<dt>标题：</dt>
				<dd>
					<input name="releTitle" type="text" class="required" size="25"/>
				</dd>
			</dl>
			<dl>
				<dt>简介：</dt>
				<dd>
					<textarea name="briefIntroduction" type="text" class="required" size="80"></textarea>
				</dd>
			</dl>
			<dl>
				<dt>内容：</dt>
				<dd>
					<textarea class="editor" name="releContent" upImgUrl="dynamic/upload.do" upImgExt="jpg,jpeg,gif,png"  rows="16" cols="100"></textarea>
				</dd>
			</dl>
			<dl>
				<dt>类型：</dt>
				<dd>
					<select name="releType">
						<option value="1">活动中心</option>
						<option value="2">爱充动态</option>
						<option value="3">行业资讯</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>是否首图：</dt>
				<dd>
					是 <input type="radio" name="releImg" value="1" />
					否 <input type="radio" name="releImg" value="0" checked/>
					<input style="display:none" id="file" type="file" name="file" class="file"  style="width:360px;"/>
				</dd>
			</dl>
			<dl>
				<dt>有效：</dt>
				是 <input type="radio" name="validFlag" value="1" checked/>
				否 <input type="radio" name="validFlag" value="0" />
			</dl>
			<dl>
				<dt>排序号：</dt>
				 <input type="text" class="digits" name="releOrder" value="0" style="width:30px;" />
			</dl>
		</div>

		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button id="addButton" type="submit" >保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>

</div>

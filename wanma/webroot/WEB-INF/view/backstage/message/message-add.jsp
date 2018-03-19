<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">
 function submitForm(type){
	 var form=document.addForm;
	 form.action="message/insertMessage.do?type="+type;
	 validateCallback(form, navTabAjaxDone);
 }
</script>

<div class="pageContent">
	<form name="addForm" method="post" action="message/insertMessage.do" id="addForm"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="55">
			<dl>
				<dt>接收人：</dt>
				<dd>
					<input name="org2.id" value="" type="hidden"/>
					<input class="required" name="org2.orgName" type="text" readonly />
					<a class="btnLook" href="${webroot}/admin/userManager/userListLookup.do"  lookupGroup="org2">查找带回</a>
				</dd>
			</dl>
			<dl>
				<dt>信息标题：</dt>
				<dd>
					<input name="title" type="text" class="required" size="25" maxlength="30" />
				</dd>
			</dl>
			<dl>
				<dt>信息内容：</dt>
				<dd>
					<textarea name="content"  class="required"  cols="40" rows="3" maxlength="234"/>
				</dd>
			</dl>
		</div>

		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button id="addButton" type="button" onclick="submitForm(1)" >保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button id="addButton" type="button" onclick="submitForm(2)" >保存并推送</button>
						</div>
					</div>
				</li>
				<li>
				
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>

</div>

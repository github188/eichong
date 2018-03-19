<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="message/updateMessage.do" id="editForm"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<input type="hidden" name="id" value="${message.id}" />
		<input type="hidden" name="fromUsername" value="${message.fromUsername}" />
		<input type="hidden" name="status" value="${message.status}" />
		<input type="hidden" name="fromUserid" value="${message.fromUserid}"  />
		<input type="hidden" name="userId" value="${message.userId}"  />
		<div class="pageFormContent nowrap" layoutH="55">
			<dl>
				<dt>信息标题：</dt>
				<dd>
					<input name="title" value="${message.title}"  type="text" class="required" size="25" maxlength="30" />
				</dd>
			</dl>
			<dl>
				<dt>信息内容：</dt>
				<dd>
					<textarea name="content"  class="required"  cols="40" rows="3"  maxlength="234">${message.content}</textarea>
				</dd>
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

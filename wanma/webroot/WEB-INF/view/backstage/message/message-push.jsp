<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>


<div class="pageContent">
	<form method="post" action="message/push.do" id="pushForm"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">

		<div class="pageFormContent nowrap" layoutH="55">
			
			<dl>
				<dt>信息内容：</dt>
				<dd>
					<textarea name="content"  class="required"   />
				</dd>
			</dl>
		</div>

		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button id="addButton" type="submit" >发送</button>
						</div>
					</div></li>
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

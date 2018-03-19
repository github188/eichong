<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="invoiceConfigContent/updateContent.do" id="editForm"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		
		<input type="hidden" name="pkConfigcontent" value="${configcontent.pkConfigcontent}"/>
		
		<div class="pageFormContent nowrap" layoutH="55">
			<dl>
				<dt>
					配置参数内容：
				</dt>
				<dd>
					<input name="cocoContent" type="text" class="required" value="${configcontent.cocoContent}" size="25" maxlength="20"/>
				</dd>
			</dl>
			<dl>
				<dt>备注：</dt>
				<dd>
					<textarea name="CoCoMemo" class="required">${configcontent.coCoMemo}</textarea>
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

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>



<div class="pageContent">
	<form method="post" action="configParameter/insertConPara.do" id="addForm"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		
		<input type="hidden"  name = "copaType" value="${configparameter.copaType}"/>
	
		<div class="pageFormContent nowrap" layoutH="55">
			<dl>
				<dt>配置参数名称：</dt>
				<dd>
					<input name="copaName" type="text" class="required" size="25"
						maxlength="20" />
				</dd>
			</dl>
			<dl>
				<dt>备注：</dt>
				<dd>
					<textarea name="CoCoMemo" class="required"></textarea>
				</dd>
			</dl>
		</div>

		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button id="addButton" type="submit">保存</button>
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

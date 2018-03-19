<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="carmaker/updateCarMaker.do" id="editForm"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		
		<input type="hidden" name="pkCarmaker" value="${carMaker.pkCarmaker}" />
		
		
		<div class="pageFormContent nowrap" layoutH="55">
			<dl>
				<dt>制造厂商名称：</dt>
				<dd>
					<input name="makerName" value="${carMaker.makerName}" type="text" class="required normal" size="25"
						maxlength="20" />
				</dd>
			</dl>
			<dl>
				<dt>制造厂商标识：</dt>
				<dd>
					<input name="makerRemark" min="0" max="99"  value="${carMaker.makerRemark}" type="text" class="required digits" size="25" readonly
						maxlength="20" />
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

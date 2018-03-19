<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="gate/addGate.do" id="editForm"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		
		<input type="hidden" name="pkGateid"  />
		
		
		<div class="pageFormContent nowrap" layoutH="55">
			<dl>
				<dt>名称：</dt>
				<dd>
					<input name="gtseGatename"  type="text" class="required" 
						maxlength="51" />
				</dd>
			</dl>
			<dl>
				<dt>IP：</dt>
				<dd>
					<input name="gtseGateip"  maxlength="50" type="text" class="required ipvalidator"/>
				</dd>
			</dl>
				<dl>
				<dt>端口：</dt>
				<dd>
					<input name="gtseGateport"   type="text" class="required" maxlength="6" readonly value='30012' />
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

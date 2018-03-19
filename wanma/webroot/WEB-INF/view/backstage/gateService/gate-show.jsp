<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="gate/chageGate.do" id="editForm"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		
		<input type="hidden" name="pkGateid" value="${tblGateservice.pkGateid}" />
		
		
		<div class="pageFormContent nowrap" layoutH="55">
			<dl>
				<dt>名称：</dt>
				<dd>
					<input name="gtseGatename" readonly="true" value="${tblGateservice.gtseGatename}" type="text" class="required" 
						maxlength="51" />
				</dd>
			</dl>
			<dl>
				<dt>IP：</dt>
				<dd>
					<input name="gtseGateip" readonly="true"  maxlength="50"  value="${tblGateservice.gtseGateip}" type="text" class="required"/>
				</dd>
			</dl>
				<dl>
				<dt>端口：</dt>
				<dd>
					<input name="gtseGateport" readonly="true"  value="${tblGateservice.gtseGateport}" type="text" class="required digits" maxlength="6" />
				</dd>
			</dl>
		</div>
	</form>
	
</div>

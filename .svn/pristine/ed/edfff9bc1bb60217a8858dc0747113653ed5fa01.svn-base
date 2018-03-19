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
					<input name="gtseGatename" value="${tblGateservice.gtseGatename}" type="text" class="required" 
						maxlength="51" />
				</dd>
			</dl>
			<dl>
				<dt>IP：</dt>
				<dd>
					<input name="gtseGateip"  maxlength="50"  value="${tblGateservice.gtseGateip}" type="text" class="required ipvalidator"/>
				</dd>
			</dl>
				<dl>
				<dt>端口：</dt>
				<dd>
					<input name="gtseGateport"  value="${tblGateservice.gtseGateport}" type="text" class="required" maxlength="6" readonly value='30012'/>
				</dd>
			</dl>
			<dl style="  margin-top: 20px; margin-left: 5px;">
				<c:if test="${gateCount>0}"><span style="color:red">提示：该电桩服务器已绑定电桩，不能编辑</span></c:if>
			</dl>
		</div>
		
		
		<div class="formBar">
			<ul>
		<c:if test="${gateCount<=0}">
					<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button id="addButton" type="submit">
								保存
							</button>
						</div>
					</div>
				</li>
			</c:if>
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

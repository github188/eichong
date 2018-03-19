<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="elctrcplscrnconfgurtn/updateEpsc.do" id="editForm"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		
		<input type="hidden" name="pkElctrcplscrnconfgurtn" value="${epsc.pkElctrcplscrnconfgurtn}" />
		
		
		<div class="pageFormContent nowrap" layoutH="55">
			<dl>
				<dt>
					配置名称：
				</dt>
				<dd>
					<input name="epscName" type="text" class="required" value="${epsc.epscName}" size="25" maxlength="20"/>
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

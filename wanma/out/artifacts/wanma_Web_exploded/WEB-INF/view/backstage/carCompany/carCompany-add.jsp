<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<script type="text/javascript">
</script>
<div class="pageContent">
	<form method="post" action="carCompany/insertCarCompany.do" id="addForm"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent nowrap" layoutH="55">
			
			<dl>
				<dt>汽车厂商名称：</dt>
				<dd>
					<input name="carCompany_Name" type="text" class="required normal" size="25" maxlength="20" />
				</dd>
			</dl>
			<dl>
				<dt>备注：</dt>
				<dd>
					<input name="carCompany_Remark"  class="normal" type="text "  size="25" maxlength="20" />
				</dd>
			</dl>
		</div>

		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button id="addButton" type="submit" >保存</button>
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

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
</script>
<h2 class="contentTitle">
	新增集中器
</h2>
<div class="pageContent">
	<form method="post" action="concentrator/addConcentrator.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">

		<div class="pageFormContent nowrap" layoutH="97">

			<dl>
				<dt>
					集中器名称
				</dt>
				<dd>
					<input name="coctConcentratorName" class="textInput required"
						value="" style="width: 165px;" /> <span
						class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					SIM卡号
				</dt>
				<dd>
					<input name="coctSIMCODE" value="" class="textInput phone required" maxlength="11"
						style="width: 165px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					SIM卡编码
				</dt>
				<dd>
					<input name="coctSIMMAC" class="textInput required"
						maxlength="20" style="width: 165px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					卡号来源
				</dt>
				<dd>		 
						<select name="coctSIMTYPE" class="select_Style required">
							<option value="">-请选择-</option>			
							<option value="1">联通</option>
							<option value="2">电信</option>
							<option value="3">移动</option>
						</select>
						<span class="info"></span>	
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="绑定电桩" />
				</dt>
				<dd>
					<input name="tblElectricpile.pkElectricpile" value="" type="hidden">
					<input name="tblElectricpile.elpiElectricpilename" type="text"
						readonly="true" /><a class="btnLook"
						href="../admin/concentrator/getConcentratorElectricPileList.do"
						lookupGroup="tblElectricpile">查找电桩</a> <span class="info">(请选择电桩)</span>
				</dd>
			</dl>
		</div>

		<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit"
						type="submit" id="formSubmitter" />
				</li>
				<li><bmtag:button messageKey="common.button.back" type="button"
						dwzClass="close" />
				</li>
			</ul>
		</div>
	</form>
</div>
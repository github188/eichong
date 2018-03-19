<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>

<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
	$('#button').attr('disabled',"true")
</script>
<h2 class="contentTitle">
	<bmtag:message messageKey="编辑" />
</h2>
<div class="pageContent">
		<form id="formDiv" method="post" action="finance/changeInvoiceRecord.do?userId=${userId}"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
		<input type="hidden" name="userId" value="${userId}"/> 
		<input type="hidden" name="pkInvoice" value="${tblInvoice.pkInvoice}" />
	
			<dl>
				<dt>
						<bmtag:message messageKey="发票名称"/>
				</dt>
				<dd>
					<input name="ivInvoiceName" value="${tblInvoice.ivInvoiceName}" class="textInput required" maxlength="30"
						style="width: 165px;" /><span class="info"></span>
				</dd>
			</dl>
			<!--  
			<dl >
				<dt>
				<bmtag:message messageKey="选择开票订单"/>
				</dt>
				<dd>
				 <a  class="button" rel="billingOrder"  href="javascript:volid(0);">
				<span>查看</span></a>
			 </dd>
			</dl>
			-->
			<dl>
			<dt>
				<bmtag:message messageKey="选择发票类型"/>
			</dt>
			<dd>
			<input type="radio" value="0" name="ivReceipType" ${tblInvoice.ivReceipType==
					0?'checked="checked" ':''}/>普通发票
			<input type="radio" value="1" name="ivReceipType"
					${tblInvoice.ivReceipType== 1?'checked="checked" ':''}/>增值税专用发票
					<span class="info"></span>
			</dd>
		</dl>
		<dl>
				<dt>
						<bmtag:message messageKey="发票号码"/>
				</dt>
				<dd>
					<input name="ivNumber" value="${tblInvoice.ivNumber }" class="textInput required" maxlength="100"
						style="width: 165px;" /><span class="info"></span>
				</dd>
			</dl>
		</div>
		
	<div class="formBar">
				<ul>
					<li><bmtag:button messageKey="common.button.submit"
							type="submit" id="formSubmitter" /></li>
					<li><bmtag:button messageKey="common.button.back" type="button"
							dwzClass="close" /></li>
				</ul>
		</div>
	</form>
</div>

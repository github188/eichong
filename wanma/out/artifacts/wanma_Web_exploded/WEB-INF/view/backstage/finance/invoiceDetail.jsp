<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
 <link href="<%=request.getContextPath()%>/res/commen.css" rel="stylesheet"/>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
</script>
<h2 class="contentTitle"><bmtag:message messageKey="查看申请详情"/></h2>
<div class="pageContent">
<form method="post" action="" class="pageForm required-validate" 
	enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone)">
	<div class="pageFormContent nowrap" layoutH="97">
		<dl>
			<dt>
				<bmtag:message messageKey="公司抬头" />
			</dt>
			<dd><bmtag:message messageKey="${TblInvoice.ivCompanyName}" />
			</dd>
		</dl>
		<%-- <dl>
			<dt>
				<bmtag:message messageKey="发票内容" />
			</dt>
			<dd><bmtag:message messageKey="${TblInvoice.ivInvoiceAmount}" />
			</dd>
		</dl> --%>
		<dl>
			<dt>
				<bmtag:message messageKey="发票金额" />
			</dt>
			<dd><bmtag:message messageKey="${TblInvoice.ivInvoiceAmount}" />
			</dd>
		</dl>
		<dl>
				<dt>
					<bmtag:message messageKey="纳税人识别号" />
				</dt>
				<dd>
					<bmtag:message messageKey="${TblInvoice.ivTaxpayerNumber}" />
				 	<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="公司地址" />
				</dt>
				<dd>
					<bmtag:message messageKey="${TblInvoice.ivCompanyAddress}" />
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="公司电话" />
				</dt>
				<dd>
					<bmtag:message messageKey="${TblInvoice.ivPhoneNumber}" />
					<span class="info"></span>
				</dd>
			</dl>
			
			<dl>
				<dt>
					<bmtag:message messageKey="开户行" />
				</dt>
				<dd>
					<bmtag:message messageKey="${TblInvoice.ivBankName}" />
					<span class="info"></span>
				</dd>
				<%-- <dd> 
					<c:if test="${tblElectricpile.elpiIsappoint == 1}">是</c:if>		 
					<c:if test="${tblElectricpile.elpiIsappoint == 0}">否</c:if>
				</dd> --%>
			</dl>
			
			<dl>
				<dt>
					<bmtag:message messageKey="对公账户" />
				</dt>
				<dd>
					<bmtag:message messageKey="${TblInvoice.ivBankAccount}" />
					<span class="info"></span>
				</dd>
			</dl>
			
			<dl>
				<dt>
					<bmtag:message messageKey="收件人姓名" />
				</dt>
				<dd>
					<bmtag:message messageKey="${TblInvoice.ivRecipients}" />
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="收件人号码" />
				</dt>
				<dd>
					<bmtag:message messageKey="${TblInvoice.ivRecipientsNumber}" />
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="收件人所在地区" />
				</dt>
				<dd>
					<bmtag:message messageKey="${TblInvoice.belongArea}" />
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="收件人详细地址" />
				</dt>
				<dd>
					<bmtag:message messageKey="${TblInvoice.ivConsigneeAddress}" />
					<span class="info"></span>
				</dd>
			</dl>
		
			</div>

</form>
</div>
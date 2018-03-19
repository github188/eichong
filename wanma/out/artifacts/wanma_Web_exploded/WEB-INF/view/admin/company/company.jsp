<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="jstltag"%>
<script type="text/javascript">
	var webroot = "${webroot}";
	var companyId = "${companyModel.companyId}";
	var createUser = "${companyModel.createUser}";
	$(function() {
		if (companyId == "") {
			disableItems("init");
		} else if (companyId != "" && createUser == "") {
			disableItems("new");
		} else {
			disableItems("edit");
		}
	});
	function ajaxDoneCallback(json) {
	}

	function disableItems(type) {
		if ("init" == type) {
			$("input").attr("readonly", true);
			$("textarea").attr("readonly", true);
			$("#copyLink").attr("disabled", true);
			$("#deleteLink").attr("disabled", true);
			$("#addCompany").attr("disabled", true);
		} else if ("new" == type) {
			$("#copyLink").attr("disabled", true);
			$("#deleteLink").attr("disabled", true);
			$("#addLink").attr("disabled", true);
		} else if ("edit" == type) {
			$("#companyId").attr("readonly", true);
			$("#companyId").removeAttr("remote");
		}
	}

	function onAddClick() {
		var isCheckOK = false;
		document.companyForm.action = "company/saveCompany.do";
		isCheckOK = document.companyForm.onsubmit();
		if (undefined == isCheckOK) {
			document.companyForm.submit();
		}
	}

	function onModifyClick() {
		var isCheckOK = false;
		document.companyForm.action = "company/modifyCompany.do";
		isCheckOK = document.companyForm.onsubmit();
		if (undefined == isCheckOK) {
			document.companyForm.submit();
		}
	}
</script>
<style type="text/css">
ul.rightTools {
	float: right;
	display: block;
}

ul.centerTools {
	float: left;
	display: block;
}

ul.centerTools li {
	float: left;
	display: block;
	margin-left: 160px;
}

ul.rightTools li {
	float: left;
	display: block;
	margin-left: 5px
}
</style>
<div class="pageHeader" style="border: 1px #B8D0D6 solid">
	<div class="panel" defH="25">
		<h1>
			<bmtag:message messageKey="company.title.company.new" />
		</h1>
		<div>
			<ul class="rightTools">
				<li><bmtag:link messageKey="common.button.copy"
						href="company/copyCompany.do?companyId=${companyModel.companyId}"
						target="ajax" rel="companyBox" dwzClass="button" id="copyLink" /></li>
				<li><bmtag:link messageKey="common.button.new"
						href="company/newCompany.do" target="ajax" rel="companyBox"
						dwzClass="button" id="addLink" /></li>
				<li><bmtag:link messageKey="common.button.delete"
						href="company/removeCompany.do?companyId=${companyModel.companyId}"
						target="ajax" rel="companyM" dwzClass="button" id="deleteLink"
						onclick="return iframeCallback(this, navTabAjaxDone)" /></li>
			</ul>
		</div>
	</div>
	<div class="divider"></div>

	<div class="pageFormContent nowrap" layoutH="97">
		<form method="post" action="company/saveCompany.do" id="companyForm"
			name="companyForm" class="pageForm required-validate"
			enctype="multipart/form-data"
			onsubmit="return iframeCallback(this, navTabAjaxDone)">
			<dl>
				<dt>
					<bmtag:message messageKey="company.label.company_id" />
				</dt>
				<dd>
					<input name="companyId" id="companyId"
						class="textInput halfsymbol required"
						value="${companyModel.companyId}"
						remote="company/checkCompanyUnique.do" maxlength="10"
						style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="company.label.company_name" />
				</dt>
				<dd>
					<input name="companyName" class="textInput required" maxlength="20"
						value="${companyModel.companyName}" style="width: 227px;" /> <span
						class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="company.label.notes" />
				</dt>
				<dd>
					<textarea name="notes" cols="30" rows="4" maxlength="80">${companyModel.notes}</textarea>
					<span class="info"></span>
				</dd>
			</dl>
			<dl></dl>
			<div class="divider"></div>
			<ul class="centerTools">
				<li><jstltag:if
						test="${companyModel.createUser==null ||companyModel.createUser==''}">
						<bmtag:button messageKey="common.button.save" type="button"
							id="addCompany" onclick="return onAddClick();" />
					</jstltag:if> <jstltag:if
						test="${companyModel.createUser!=null && companyModel.createUser != ''}">
						<bmtag:button messageKey="common.button.update" type="button"
							id="modifyCompany" onclick="return onModifyClick();" />
					</jstltag:if></li>
			</ul>
		</form>
	</div>
</div>
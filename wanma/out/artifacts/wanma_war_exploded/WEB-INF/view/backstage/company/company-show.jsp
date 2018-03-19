<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<script type="text/javascript">
	var webroot = "${webroot}";

	function ajaxDoneCallback(json){
	}
	
</script>
<h2 class="contentTitle">
	<bmtag:message messageKey="查看公司" />
</h2>
<div class="pageContent">
	<form method="post" action="companyManager/changeCompany.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dt>
					<bmtag:message messageKey="企业名称" />
				</dt>
				<dd>
					<input name="cpyCompanyname" disabled="disabled" value="${tblCompany.cpyCompanyname}" class="textInput" minlength="6"
						style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="企业地址" />
				</dt>
				<dd>
					<input name="cpyCompanyaddress" disabled="disabled" value="${tblCompany.cpyCompanyaddress}"  class="textInput"
						minlength="6" style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="企业邮箱" />
				</dt>
				<dd>
					<input name="cpyCompanyemail" disabled="disabled" value="${tblCompany.cpyCompanyemail}"  class="email" maxlength="50"
						style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="邮编" />
				</dt>
				<dd>
					<input name="cpyPostcode" disabled="disabled" value="${tblCompany.cpyPostcode}"  class="textInput" minlength="6"
						style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="经营范围" />
				</dt>
				<dd>
					<input name="cpyScopebusiness" disabled="disabled" value="${tblCompany.cpyScopebusiness}"  minlength="6" style="width: 130px;" />
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="授权人姓名" />
				</dt>
				<dd>
					<input name="cpyAuthorizedname" disabled="disabled" value="${tblCompany.cpyAuthorizedname}"  class="textInput"
						minlength="2" maxlength="80" style="width: 130px;" /> <span
						class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="授权人联系电话" />
				</dt>
				<dd>
					<input name="cpyAuthorizedphone" disabled="disabled" value="${tblCompany.cpyAuthorizedphone}"   class="phone" maxlength="11"
						style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>

			<dl>
				<dt>
					<bmtag:message messageKey="授权人身份证" />
				</dt>
				<dd style="width: 200px;">
					<img src="${tblCompany.cpyAuthorizedcard}"
						style="width: 150px; height: 150px;"
						 >
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="营业执照" />
				</dt>
				<dd style="width: 200px;">
					<img src="${tblCompany.cpyBusinesslicence}"
						style="width: 150px; height: 150px;"
						 >
				</dd>
				 
			</dl>

			<dl>
				<dt>
					<bmtag:message messageKey="组织机构代码" />
				</dt>
				<dd>
					<input name="cpyOrganizationcode" disabled="disabled" value="${tblCompany.cpyOrganizationcode}" minlength="6" style="width: 130px;" />
					<span class="info"></span>
				</dd>
			</dl>

			<dl>
				<dt>
					<bmtag:message messageKey="税务登记证" />
				</dt>
				<dd style="width: 200px;">
					<img src="${tblCompany.cpyTorontohospital}"
						style="width: 150px; height: 150px;"
						 >
				</dd>
				 
			</dl>

			<dl>
				<dt>
					<bmtag:message messageKey="授权证明" />
				</dt>
				<dd style="width: 200px;">
					<img src="${tblCompany.cpyAuthorization}" 
						style="width: 150px; height: 150px;"
						 >
				</dd>
				 
			</dl>

			<dl>
				<dt>
					<bmtag:message messageKey="邮寄地址" />
				</dt>
				<dd>
					<input name="cpyMailingaddress" disabled="disabled" value="${tblCompany.cpyMailingaddress}" class="textInput"
						minlength="6" maxlength="80" style="width: 130px;" /> <span
						class="info"></span>
				</dd>
			</dl>
		</div>
 
	</form>
</div>
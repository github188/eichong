<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<script type="text/javascript">
	$(function() {
		loadCity($("#companyProvince"));

	});
	var webroot = "${webroot}";

	function ajaxDoneCallback(json) {
	}

	/* 	获取城市 */
	function getCity(proviceId) {

		$.get(webroot + "/admin/companyManager/getCityCode.do?proviceId="
				+ proviceId.value + "", {}, function(data) {
			var json = eval("(" + data + ")");
			$(".companyCityId").html("");
			$("<option value=''>-请选择-</option>").appendTo($(".companyCityId"));
			$.each(json, function(i) {
				$(
						"<option value='"+json[i].cityId+"'>"
								+ json[i].cityName + "</option>").appendTo(
						$(".companyCityId"));
			});
		});
	}
</script>
<h2 class="contentTitle">
	<bmtag:message messageKey="编辑公司" />
</h2>
<div class="pageContent">
	<form method="post" action="companyManager/changeCompany.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<input type="hidden" name="pkCompanyid"
			value="${tblCompany.pkCompanyid}" />
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dt>
					<bmtag:message messageKey="企业名称" />
				</dt>
				<dd>
					<input name="cpyCompanyname" value="${tblCompany.cpyCompanyname}"
						class="textInput required normal" minlength="6"
						style="width: 130px;"
						remote="<%=request.getContextPath()%>/common/checkUnique.do?tName=tbl_company&tProperty=cpy_Companyname&property=cpyCompanyname&pkTProperty=pk_Companyid&pkTValue=${tblCompany.pkCompanyid}" />
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="公司标识" />
				</dt>
				<dd>
					<input name="cpyCompanyNumber" disabled="disabled"
						value="${tblCompany.cpyCompanyNumber}" style="width: 130px;"
						class="textInput required normal" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="省份" />
				</dt>
				<dd>
					<%--  <select class="provinceCode required" id="companyProvince"
						next="companyEdit_selCity" name="cpyProvince"
						style="float: none; width: 130px;">
						<option value="">--请选择省份--</option>
						<c:forEach var="item" items="${proviceMap}">
							<option value="${item.key}">${item.value.PROVINCE_NAME}</option>
						</c:forEach>
					</select> <span class="info"></span> --%>
					<select name="cpyProvince" id="companyProvince"
						style="width: 130px !important" class="select_Style"
						onchange="getCity(this)">
						<option value="">-请选择-</option>
						<c:forEach var="item" items="${provinceCodeList}">
							<option value="${item.provinceId}"
								${item.provinceId==
								tblCompany.cpyProvince ? 'selected="selected"' : ''}>${item.provinceName}</option>
						</c:forEach>
					</select> <span class="info"></span>


				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="市区" />
				</dt>
				<dd>
					<!-- <select class="cityCode required" id="companyEdit_selCity"
						name="cpyCity" style="float: none; width: 130px;">
						<option value="">--请选择城市--</option>
					</select> <span class="info"></span> <span class="info"></span> -->

					<select id="companyCityId" name="cpyCity"
						class="select_Style companyCityId" style="width: 130px !important">
						<option value="">-请选择-</option>
						<c:forEach var="item" items="${cityList}">
							<option value="${item.cityId}"
								${item.cityId==
								tblCompany.cpyCity ? 'selected="selected"' : ''}>${item.cityName}</option>
						</c:forEach>
					</select> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="企业地址" />
				</dt>
				<dd>
					<input name="cpyCompanyaddress"
						value="${tblCompany.cpyCompanyaddress}" class="textInput  normal"
						minlength="6" style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="企业邮箱" />
				</dt>
				<dd>
					<input name="cpyCompanyemail" value="${tblCompany.cpyCompanyemail}"
						class="textInput email" maxlength="50" style="width: 130px;" /> <span
						class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="邮编" />
				</dt>
				<dd>
					<input name="cpyPostcode"
						value="${tblCompany.cpyPostcode==0?'':tblCompany.cpyPostcode}"
						class="textInput digits" minlength="6" maxlength="6"
						style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="经营范围" />
				</dt>
				<dd>
					<input name="cpyScopebusiness"
						value="${tblCompany.cpyScopebusiness}" class="textInput normal"
						minlength="6" style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="授权人姓名" />
				</dt>
				<dd>
					<input name="cpyAuthorizedname"
						value="${tblCompany.cpyAuthorizedname}" class="textInput  normal"
						minlength="2" maxlength="80" style="width: 130px;" /> <span
						class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="授权人联系电话" />
				</dt>
				<dd>
					<input name="cpyAuthorizedphone"
						value="${tblCompany.cpyAuthorizedphone}" class="phone "
						maxlength="11" style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>

			<dl>
				<dt>
					<bmtag:message messageKey="授权人身份证" />
				</dt>
				<dd style="width: 200px;">
					<img src="${tblCompany.cpyAuthorizedcard}"
						style="width: 150px; height: 150px;">
				</dd>
				<dd>
					<input type="file" name="IdUnitCardImage" class="file"
						style="width: 260px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="营业执照" />
				</dt>
				<dd style="width: 200px;">
					<img src="${tblCompany.cpyBusinesslicence}"
						style="width: 150px; height: 150px;">
				</dd>
				<dd>
					<input type="file" name="LicenseImage" class="file"
						style="width: 260px;" /> <span class="info"></span>
				</dd>
			</dl>

			<dl>
				<dt>
					<bmtag:message messageKey="组织机构代码" />
				</dt>
				<dd>
					<input name="cpyOrganizationcode"
						value="${tblCompany.cpyOrganizationcode}" class="textInput normal"
						minlength="6" style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>

			<dl>
				<dt>
					<bmtag:message messageKey="税务登记证" />
				</dt>
				<dd style="width: 200px;">
					<img src="${tblCompany.cpyTorontohospital}"
						style="width: 150px; height: 150px;">
				</dd>
				<dd>
					<input type="file" name="AffairsImage" class="file"
						style="width: 260px;" /> <span class="info"></span>
				</dd>
			</dl>

			<dl>
				<dt>
					<bmtag:message messageKey="授权证明" />
				</dt>
				<dd style="width: 200px;">
					<img src="${tblCompany.cpyAuthorization}"
						style="width: 150px; height: 150px;">
				</dd>
				<dd>
					<input type="file" name="AccreditImage" class="file"
						style="width: 260px;" /> <span class="info"></span>
				</dd>
			</dl>

			<dl>
				<dt>
					<bmtag:message messageKey="邮寄地址" />
				</dt>
				<dd>
					<input name="cpyMailingaddress"
						value="${tblCompany.cpyMailingaddress}" class="normal textInput"
						minlength="6" maxlength="80" style="width: 130px;" /> <span
						class="info"></span>
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
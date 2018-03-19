<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="jstltag"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	var webroot = "${webroot}";
	var departmentId = "${departmentModel.departmentId}";
	var createUser = "${departmentModel.createUser}";
	// 	var userLevel = "${userLevel}";

	// 	if(userLevel == 4) {
	// 		$("#departmentResponsibleEdit").css("display", "");	
	// 	}

	$(function() {
		if (departmentId == "") {
			disableItems("init");
		} else if (departmentId != "" && createUser == "") {
			disableItems("new");
		} else {
			disableItems("edit");
		}
	});
	function ajaxDoneCallback(json) {
	}

	function disableItems(type) {
		if ("init" == type) {
			$("select").attr("readonly", true);
			$("input").attr("readonly", true);
			$("textarea").attr("readonly", true);
			$("#addDepartment").attr("disabled", true);
			disabledButton($("#addDepartment_B"));
			$("#deleteDepartment").attr("disabled", true);
			disabledButton($("#deleteDepartment_B"));
			disabledButtonLink($("#deptCopyLink"));
			disabledLink($("#addDeptUserLink"));
			disabledLink($("#delDeptUserLink"));
		} else if ("new" == type) {
			disabledButtonLink($("#deptCopyLink"));
			$("#deleteDepartment").attr("disabled", true);
			disabledButton($("#deleteDepartment_B"));
			disabledButtonLink($("#deptAddLink"));
		} else if ("edit" == type) {
			$("#departmentId").attr("readonly", true);
			$("#departmentId").removeAttr("remote");
		}
	}

	function onAddClick() {
		var isCheckOK = false;
		document.departmentForm.action = "department/saveDepartment.do";
		isCheckOK = document.departmentForm.onsubmit();

		if (undefined == isCheckOK) {
			document.departmentForm.submit();
		}
	}

	function onModifyClick() {
		var isCheckOK = false;
		document.departmentForm.action = "department/modifyDepartment.do";
		isCheckOK = document.departmentForm.onsubmit();
		if (undefined == isCheckOK) {
			document.departmentForm.submit();
		}
	}

	function onDeleteClick() {
		var isCheckOK = false;
		document.departmentForm.action = "department/removeDepartment.do";
		isCheckOK = document.departmentForm.onsubmit();

		if (undefined == isCheckOK) {
			document.departmentForm.submit();
		}
	}

	function onSearchClick() {
		var isCheckOK = false;
		document.deptUserSearchFrom.userId.value = document.departmentForm.userId.value;
		document.deptUserSearchFrom.userName.value = document.departmentForm.userName.value;
		document.deptUserSearchFrom.departmentId.value = document.departmentForm.departmentId.value;
		document.deptUserSearchFrom.companyId.value = document.departmentForm.companyId.value;
		isCheckOK = document.deptUserSearchFrom.onsubmit();
		if (undefined == isCheckOK) {
			document.deptUserSearchFrom.submit();
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
	margin-left: 220px;
}

ul.centerTools li {
	float: left;
	display: block;
	margin-left: 220px;
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
			<bmtag:message messageKey="department.title.department.new" />
		</h1>
		<div>
			<ul class="rightTools">
				<li><bmtag:link messageKey="common.button.copy"
						href="department/copyDepartment.do?companyId=${departmentModel.companyId}&departmentId=${departmentModel.departmentId}"
						target="ajax" rel="deptBox" dwzClass="button" id="deptCopyLink" /></li>
				<li><bmtag:link messageKey="common.button.new"
						href="department/newDepartment.do?companyId=${departmentModel.companyId}&departmentId=${departmentModel.departmentId}"
						target="ajax" rel="deptBox" dwzClass="button" id="deptAddLink" /></li>
				<li><bmtag:button messageKey="common.button.delete"
						type="button" id="deleteDepartment" onclick="onDeleteClick();" /></li>
			</ul>
		</div>
	</div>
	<div class="divider"></div>

	<div class="pageFormContent nowrap" layoutH="97">
		<form method="post" action="department/saveDepartment.do"
			id="departmentForm" name="departmentForm"
			class="pageForm required-validate" enctype="multipart/form-data"
			onsubmit="return iframeCallback(this, navTabAjaxDone)">
			<input type="hidden" name="companyId"
				value="${departmentModel.companyId}" /><input name="obj.companyId"
				type="hidden" value="${departmentModel.responsibleCompany}" /> <input
				name="obj.departmentId" type="hidden"
				value="${departmentModel.responsibleDepart}" />
			<div class="tabs">
				<div class="tabsHeader">
					<div class="tabsHeaderContent">
						<ul>
							<li><a href="javascript:;"><span><bmtag:message
											messageKey="common.tab.title.base" /></span></a></li>
							<li><a href="javascript:;"><span><bmtag:message
											messageKey="common.tab.title.detail" /></span></a></li>
							<li><a href="javascript:;"><span><bmtag:message
											messageKey="common.tab.title.dept_users" /></span></a></li>
						</ul>
					</div>
				</div>
				<div class="tabsContent">
					<div layoutH="234">
						<dl>
							<dt>
								<bmtag:message messageKey="department.label.department_id" />
							</dt>
							<dd>
								<input name="departmentId" class="textInput halfsymbol required"
									id="departmentId" value="${departmentModel.departmentId}"
									remote="department/checkDepartmentUnique.do?companyId=${departmentModel.companyId}"
									maxlength="20" style="width: 130px;" /> <span class="info"></span>
							</dd>
						</dl>
						<dl>
							<dt>
								<bmtag:message messageKey="department.label.department_name" />
							</dt>
							<dd>
								<input name="departmentName" class="textInput required"
									value="${departmentModel.departmentName}" maxlength="20"
									style="width: 130px;" /> <span class="info"></span>
							</dd>
						</dl>
						<dl>
							<dt>
								<bmtag:message
									messageKey="department.label.parent_department_id" />
							</dt>
							<dd>
								<input name="parentDepartmentId" class="textInput"
									id="parentDepartmentId"
									value="${departmentModel.parentDepartmentId}" maxlength="20"
									style="width: 130px;" readonly /> <span class="info">${departmentModel.parentDepartmentName}</span>
							</dd>
						</dl>
						<%-- 						责任部门 --%>
						<!-- 						<div id="departmentResponsibleEdit" style="display:none"> -->
						<!-- 							<dl> -->
						<!-- 								<dt> -->
						<%-- 									<bmtag:message messageKey="department.label.responsible_depart" /> --%>
						<!-- 								</dt> -->
						<!-- 								<dd> -->
						<!-- 									<input name="obj.departmentName" type="text" size="25" -->
						<%-- 										readonly="readonly" value="${departmentModel.responsibleDepartName}" /> <input --%>
						<%-- 										name="obj.companyId" type="hidden" value="${departmentModel.responsibleCompany}" /> <input --%>
						<%-- 										name="obj.departmentId" type="hidden" value="${departmentModel.responsibleDepart}" /> --%>
						<%-- 									<bmtag:link href="community/lookupDepartment.do?companyType=001" --%>
						<%-- 										lookupGroup="obj" lookupPk="orgNum" --%>
						<%-- 										dwzClass="btnLook" /> --%>
						<!-- 								</dd> -->
						<!-- 							</dl> -->
						<!-- 						</div> -->

						<%-- 						省份ID --%>
						<!-- 						<dl> -->
						<!-- 							<dt> -->
						<%-- 								<bmtag:message messageKey="department.label.provinceId" /> --%>
						<!-- 							</dt> -->
						<!-- 							<dd> -->
						<!-- 								<select class="combox" name="provinceId" -->
						<!-- 									ref="departmentModel_city_edit" -->
						<!-- 									refUrl="commonData/cityList.do?provinceId={value}&titleVal=-1"> -->
						<!-- 									<option value="">不限</option> -->
						<%-- 									<c:forEach var="item" items="${provinceList}"> --%>
						<%-- 										<option value="${item.provinceId}" --%>
						<%-- 											${item.provinceId == departmentModel.provinceId ? 'selected="selected"' : ''}> --%>
						<%-- 											${item.provinceName}</option> --%>
						<%-- 									</c:forEach> --%>
						<!-- 								</select> -->
						<!-- 							</dd> -->
						<!-- 						</dl> -->
						<%-- 						城市ID --%>
						<!-- 						<dl> -->
						<!-- 							<dt> -->
						<%-- 								<bmtag:message messageKey="department.label.cityId" /> --%>
						<!-- 							</dt> -->
						<!-- 							<dd> -->
						<!-- 								<select class="combox" name="cityId" -->
						<!-- 									id="departmentModel_city_edit" ref="departmentModel_area_edit" -->
						<!-- 									refUrl="commonData/areaList.do?cityId={value}&titleVal=-1"> -->
						<!-- 									<option value="">不限</option> -->
						<%-- 									<c:forEach var="item" items="${cityList}"> --%>
						<%-- 										<option value="${item.cityId}" --%>
						<%-- 											${item.cityId == departmentModel.cityId ? 'selected="selected"' : ''}> --%>
						<%-- 											${item.cityName}</option> --%>
						<%-- 									</c:forEach> --%>
						<!-- 								</select> -->
						<!-- 							</dd> -->
						<!-- 						</dl> -->
						<%-- 						区县ID --%>
						<!-- 						<dl> -->
						<!-- 							<dt> -->
						<%-- 								<bmtag:message messageKey="department.label.areaId" /> --%>
						<!-- 							</dt> -->
						<!-- 							<dd> -->
						<!-- 								<select class="combox" name="areaId" -->
						<!-- 									id="departmentModel_area_edit" -->
						<!-- 									ref="departmentModel_community_edit" -->
						<!-- 									refUrl="commonData/communityList.do?areaId={value}&titleVal=-1"> -->
						<!-- 									<option value="">不限</option> -->
						<%-- 									<c:forEach var="item" items="${areaList}"> --%>
						<%-- 										<option value="${item.areaId}" --%>
						<%-- 											${item.areaId == departmentModel.areaId ? 'selected="selected"' : ''}> --%>
						<%-- 											${item.areaName}</option> --%>
						<%-- 									</c:forEach> --%>
						<!-- 								</select> -->
						<!-- 							</dd> -->
						<!-- 						</dl> -->
					</div>
					<div layoutH="234">
						<dl>
							<dt>
								<bmtag:message messageKey="department.label.telephone_number" />
							</dt>
							<dd>
								<input name="telephoneNumber" class="phone"
									value="${departmentModel.telephoneNumber}" maxlength="12"
									style="width: 130px;" /> <span class="info"></span>
							</dd>
						</dl>
						<dl>
							<dt>
								<bmtag:message messageKey="department.label.fax_number" />
							</dt>
							<dd>
								<input name="faxNumber" class="phone"
									value="${departmentModel.faxNumber}" maxlength="12"
									style="width: 130px;" /> <span class="info"></span>
							</dd>
						</dl>
						<dl>
							<dt>
								<bmtag:message messageKey="department.label.zip_code" />
							</dt>
							<dd>
								<input name="zipCode" class="digits" minlength="6"
									value="${departmentModel.zipCode}" maxlength="6"
									style="width: 130px;" /> <span class="info"></span>
							</dd>
						</dl>
						<dl>
							<dt>
								<bmtag:message messageKey="department.label.address1" />
							</dt>
							<dd>
								<input name="address1" class=""
									value="${departmentModel.address1}" maxlength="80"
									style="width: 260px;" /> <span class="info"></span>
							</dd>
						</dl>
						<dl>
							<dt>
								<bmtag:message messageKey="department.label.address2" />
							</dt>
							<dd>
								<input name="address2" class=""
									value="${departmentModel.address2}" maxlength="80"
									style="width: 260px;" /> <span class="info"></span>
							</dd>
						</dl>
						<dl>
							<dt>
								<bmtag:message messageKey="department.label.email_address1" />
							</dt>
							<dd>
								<input name="emailAddress1" class="email"
									value="${departmentModel.emailAddress1}" maxlength="20"
									style="width: 260px;" /> <span class="info"></span>
							</dd>
						</dl>
						<dl>
							<dt>
								<bmtag:message messageKey="department.label.email_address2" />
							</dt>
							<dd>
								<input name="emailAddress2" class="email"
									value="${departmentModel.emailAddress2}" maxlength="20"
									style="width: 260px;" /> <span class="info"></span>
							</dd>
						</dl>
						<dl>
							<dt>
								<bmtag:message messageKey="department.label.url" />
							</dt>
							<dd>
								<input name="url" class="url" value="${departmentModel.url}"
									maxlength="80" style="width: 260px;" /> <span class="info"></span>
							</dd>
						</dl>
					</div>
					<div id="deptUser" class="unitBox">
						<%@include file="departmentUser.jsp"%>
					</div>
				</div>
				<div class="tabsFooter">
					<div class="tabsFooterContent"></div>
				</div>
			</div>
			<br />
			<ul class="centerTools">
				<li><jstltag:if
						test="${departmentModel.createUser==null ||departmentModel.createUser==''}">
						<bmtag:button messageKey="common.button.save" type="button"
							id="addDepartment" onclick="onAddClick();" />
					</jstltag:if> <jstltag:if
						test="${departmentModel.createUser!=null && departmentModel.createUser != ''}">
						<bmtag:button messageKey="common.button.update" type="button"
							id="modifyDepartment" onclick="onModifyClick();" />
					</jstltag:if></li>
			</ul>
		</form>
		<form id="deptUserSearchFrom" name="deptUserSearchFrom" method="post"
			action="department/searchDeptUserList.do"
			onsubmit="return navTabSearch(this);">
			<input type="hidden" name="status" value="${pager.status}" /> <input
				type="hidden" name="keywords" value="${pager.keywords}" /> <input
				type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
				type="hidden" name="numPerPage" value="${pager.numPerPage}" /><input
				type="hidden" name="userId" value="${deptUserSearchModel.userId}" /><input
				type="hidden" name="userName"
				value="${deptUserSearchModel.userName}" /><input type="hidden"
				name="companyId" value="${departmentModel.companyId}" /><input
				type="hidden" name="departmentId"
				value="${departmentModel.departmentId}" />
		</form>
	</div>
</div>
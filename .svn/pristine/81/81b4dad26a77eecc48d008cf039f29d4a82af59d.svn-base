<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<script type="text/javascript">
$(function(){
	loadCity($("#companyEdit_selProvince"));
	
});
	var webroot = "${webroot}";

	function ajaxDoneCallback(json){
	}
	
</script>
<h2 class="contentTitle">
	<bmtag:message messageKey="新增公司" />
</h2>
<div class="pageContent">
	<form method="post" action="companyManager/addCompany.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dt>
					<bmtag:message messageKey="企业名称" />
				</dt>
				<dd>
					<input name="cpyCompanyname" class="textInput required normal" minlength="6"
						style="width: 130px;" remote="<%=request.getContextPath()%>/common/checkUnique.do?tName=tbl_company&tProperty=cpy_Companyname&property=cpyCompanyname" /> 
				    <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="公司标识" />
				</dt>
				<dd>
					<input name="cpyCompanyNumber"  style="width: 130px;" class="textInput required normal"/>
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="省份" />
				</dt>
				<dd>
					<select class="provinceCode required"
							id="companyEdit_selProvince" next="companyadd_selCity" name="cpyProvince"
							style="float: none; width: 130px;">
								<option value="">--请选择省份--</option>
								<c:forEach var="item" items="${proviceMap}">
									<option value="${item.key}">
										${item.value.PROVINCE_NAME}</option>
								</c:forEach>
						</select>
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="市区" />
				</dt>
				<dd>
					<select class="cityCode required" id="companyadd_selCity" 
							 name="cpyCity"
							style="float: none; width: 130px;">
								<option value="">--请选择城市--</option>
						</select>
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="组织机构代码" />
				</dt>
				<dd>
					<input name="cpyOrganizationcode"  style="width: 130px;" class="textInput  normal"/>
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="企业地址" />
				</dt>
				<dd>
					<input name="cpyCompanyaddress" class="textInput  normal"
						minlength="6" style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="企业邮箱" />
				</dt>
				<dd>
					<input name="cpyCompanyemail" class="textInput email" maxlength="50"
						style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="邮编" />
				</dt>
				<dd>
					<input name="cpyPostcode" class="textInput digits" minlength="6" maxlength="6"
						style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="经营范围" />
				</dt>
				<dd>
					<input name="cpyScopebusiness" minlength="6" style="width: 130px;" class="textInput normal"/>
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="授权人姓名" />
				</dt>
				<dd>
					<input name="cpyAuthorizedname" class="textInput  normal"
						minlength="2" maxlength="80" style="width: 130px;" /> <span
						class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="授权人联系电话" />
				</dt>
				<dd>
					<input name="cpyAuthorizedphone" class="phone" maxlength="11"
						style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>

			<dl>
				<dt>
					<bmtag:message messageKey="授权人身份证" />
				</dt>
				<dd>
					<input type="file" name="IdUnitCardImage" class="file "
						style="width: 260px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="营业执照" />
				</dt>
				<dd>
					<input type="file" name="LicenseImage" class="file "
						style="width: 260px;" /> <span class="info"></span>
				</dd>
			</dl>

			<dl>
				<dt>
					<bmtag:message messageKey="税务登记证" />
				</dt>
				<dd>
					<input type="file" name="AffairsImage" class="file "
						style="width: 260px;" /> <span class="info"></span>
				</dd>
			</dl>

			<dl>
				<dt>
					<bmtag:message messageKey="授权证明" />
				</dt>
				<dd>
					<input type="file" name="AccreditImage" class="file "
						style="width: 260px;" /> <span class="info"></span>
				</dd>
			</dl>

			<dl>
				<dt>
					<bmtag:message messageKey="邮寄地址" />
				</dt>
				
				<dd>
					<input name="cpyMailingaddress" class="textInput normal"
						minlength="6" maxlength="80" style="width: 130px;" /> <span
						class="info"></span>
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
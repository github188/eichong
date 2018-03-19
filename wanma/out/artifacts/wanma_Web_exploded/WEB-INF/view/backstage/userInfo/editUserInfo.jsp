<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/user/user-list.js" />
<script type="text/javascript">
	var webroot = "${webroot}";

	function ajaxDoneCallback(json) {
	}	
	$("#updatePassword").on("click",function(){
		$("#password").show();
		$("#userPassword").addClass("required");
	})  
	
</script>
<style>
	.updatePassword{
	display:inline-block;
	width:50px;
	height:25px;
	line-height:25px;
	text-align:center;
	border:1px solid #fff;
	margin-left:50px;
	border-radius:5px;
	cursor:pointer;
	background:#ffa400;
	color:#fff;
	}
	.updatePassword:link,.updatePassword:visited,.updatePassword:hover,.updatePassword:active{
	text-decoration:none;
	}
</style>
<h2 class="contentTitle">
	<bmtag:message messageKey="user.title.user.edit" />
</h2>
<div class="pageContent">
	<form method="post" action="userManager/modifyUser.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<input type="hidden" name="userLevel" value="${user.userLevel}" />
			<input type="hidden" name="userId" value="${user.userId}" />
			<input type="hidden" name="isBigAccount" value="${user.isBigAccount}" />
			<input type="hidden" value="${user.normRegisteType}"  name="normRegisteType" />
			
				<dl>
					<dt>
						<bmtag:message messageKey="手机号" />
					</dt>
					<dd>
						<input name="userAccount" class="phone required" idName="userId" data-id="${user.userId}" 
							value="${user.userAccount}" maxlength="11"
							style="width: 130px;" /> <span class="info"></span>
					</dd>
				</dl>
				<dl>
					<dt>
						<bmtag:message messageKey="是否大账户"/>
					</dt>
					<dd>
						<c:if test="${user.isBigAccount == 1}">是<a id="updatePassword" class="updatePassword" >修改密码</a></c:if>
						<c:if test="${user.isBigAccount == 0}">否</c:if>
						<span class="info"></span>
					</dd>
				</dl>
				<dl id="password" style="display:none">
					<dt>
						<bmtag:message messageKey="充电卡密码" />
					</dt>
					<dd>
						<input name="userPassword"  id="userPassword" class="bigPassword"
							value="" 
							style="width: 130px;" maxlength="6" /> <span class="info"></span>
					</dd>
				</dl>
				<dl>
				<dt>
					<bmtag:message messageKey="姓名" />
				</dt>
				<dd>
					<input name="normRealName" class="textInput"
						value="${user.normRealName}" maxlength="20" style="width: 130px;" />
					<span class="info"></span>
				</dd>
			</dl>	
			<dl>
				<dt>
					<bmtag:message messageKey="昵称" />
				</dt>
				<dd>
					<input name="normName" class="textInput"
						value="${user.normName}" maxlength="20" style="width: 130px;" />
					<span class="info"></span>
				</dd>
			</dl>		
			<dl>
				<dt>
					<bmtag:message messageKey="头像" />
				</dt>
				<dd>
					<img alt="" src="${user.userImage}"  style="float: left;"
							width="100px" height="100px">
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<input type="file" name="allMultiFile" class="file"  style="width:360px;margin-left: 130px;"/>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="性别" />
				</dt>
				<dd>
					<input type="radio" value="0" name="normSex" ${user.normSex==
					0?'checked="checked" ':''}/>男 <input type="radio" value="1" name="normSex"
					${user.normSex== 1?'checked="checked" ':''}/>女 <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="出生日期" />
				</dt>
				<dd>				
					  <input id="editUserInfo_createDate" name="normBirthday"  class="date"  style="width:130px"
					        type="text"  readonly="true" value="${user.normBirthday}"
						 onClick="WdatePicker({el:'editUserInfo_createDate',minDate:'1970-01-01'})" >
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="电子邮件" />
				</dt>
				<dd>
					<input name="normEmail" class="email"
						value="${user.normEmail}" maxlength="50"
						style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="联系地址" />
				</dt>
				<dd>
					<input name="normAddress" minlength="6"
						value="${user.normAddress}" maxlength="80" style="width: 130px;" />
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="驾驶证号" />
				</dt>
				<dd>
					<input name="normDrivingLicence" value="${user.normDrivingLicence}"
						maxlength="80" style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl>
			<%-- <dl>
				<dt>
					<bmtag:message messageKey="IC卡号" />
				</dt>
				<dd>
					<input name="normIdCard"
						value="${user.normIdCard}" maxlength="20"
						style="width: 130px;" /> <span class="info"></span>
				</dd>
			</dl> --%>
			<dl>
				<dt>
					<bmtag:message messageKey="品牌" />
				</dt>
				<dd>
					<select name="normCarCompanyId"  class="select_Style" onchange="getCarTypeName(this,'usinEditCarinfo')" style="width: 172px !important">
						<option value="0">--请选择--</option>
						<c:forEach items="${brandList}" var="brand">
							<option value="${brand.pk_carCompany }" ${brand.pk_carCompany == user.normCarCompanyId ? 'selected="selected"' : ''}>${brand.carCompany_Name}</option>
						</c:forEach>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="车型" />
				</dt>
				<dd>
					<select id="usinEditCarinfo" name="normCarTypeId"  class="select_Style editUsbrandedit" style="width: 172px !important">
						<option value = "0">--请选择--</option>
						<c:forEach items="${carTypeList}" var="carType">
						<option value="${user.normCarTypeId }" ${carType.pkCarinfo == user.normCarTypeId?'selected="selected"':''}>${carType.carinfoStylename }</option>
						</c:forEach>
					</select>
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
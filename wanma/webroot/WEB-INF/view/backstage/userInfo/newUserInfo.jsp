<%@ page language="java"  import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/user/user-list.js" />
<script type="text/javascript">
	var webroot = "${webroot}";

	function ajaxDoneCallback(json){
	}
	function formatUserInfo_add() {
		var isBigAccount =  $('input:radio[name="isBigAccount"]:checked').val();
		var userAccount = $("#userAccount").val();
		var phone = $("#phone").val();
		if(isBigAccount==1&&userAccount.length!=12){
				alertMsg.error("大账户的账号必须为12位");
				return;
		}else if(isBigAccount==0&&userAccount!=phone){
				alertMsg.error("非大账户手机号和账号必须相同");
				return;
		}
		$("#newUserInfoForm").submit();
	}
	
	
</script>
<h2 class="contentTitle"><bmtag:message messageKey="user.title.user.new"/></h2>
<div class="pageContent">
<form method="post" action="userManager/saveUser.do" class="pageForm required-validate"  id="newUserInfoForm"
	enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone)">
	<div class="pageFormContent nowrap" layoutH="97">
	<input type="hidden" value="1" name="normRegisteType" />
	<input type="hidden" value="6" name="userLevel" />
		<dl>
			<dt><bmtag:message messageKey="手机号"/></dt>
			<dd>
				<input name="phone" id="phone" class="required phone textInput"  maxlength="11" style="width:130px;"/>
				<span class="info"></span>
			</dd>
		</dl>
		
		<dl>
			<dt><bmtag:message messageKey="账号"/></dt>
			<dd>
				<input name="userAccount" id="userAccount" class="required number textInput"  maxlength="12" style="width:130px;"/>
				<span class="info"></span>
			</dd>
		</dl>
		
		<dl>
			<dt>
				<bmtag:message messageKey="是否大账户"/>
			</dt>
			<dd>
				<input type="radio" value="1" name="isBigAccount" />是 
				<input type="radio" value="0" name="isBigAccount" checked/>否
				<span class="info"></span>
			</dd>
		</dl>
			
		<%-- <dl>
			<dt><bmtag:message messageKey="密码"/></dt>
			<dd>
				<input name="userPassword" class="required digits textInput"  minlength="6" maxlength="8"  style="width:130px;"/>
				<span class="info"></span>
			</dd>
		</dl> --%>
		<dl>
			<dt><bmtag:message messageKey="姓名"/></dt>
			<dd>
				<input name="normRealName" class="textInput" maxlength="20" style="width:130px;"/>
				<span class="info"></span>
			</dd>
		</dl>
		
		<dl>
			<dt><bmtag:message messageKey="昵称"/></dt>
			<dd>
				<input name="normName" class="textInput" maxlength="20" style="width:130px;"/>
				<span class="info"></span>
			</dd>
		</dl>
		
		
		<dl>
			<dt><bmtag:message messageKey="头像"/></dt>
			<dd>
				<input type="file" name="allMultiFile" class="file"  style="width:360px;"/>
				<span class="info"></span>
			</dd>
		</dl>
		
		<dl>
			<dt>
				<bmtag:message messageKey="性别"/>
			</dt>
			<dd>
				<input type="radio" value="0" name="normSex" />男 
				<input type="radio" value="1" name="normSex" />女 
				<span class="info"></span>
			</dd>
		</dl>
 
		<dl>
			<dd>
				<label>出生日期</label>
				  <input id="newUser_createDate" name="normBirthday"  class="date"  style="width:130px"
					        type="text"  readonly="true" 
						 onClick="WdatePicker({el:'newUser_createDate',minDate:'1970-01-01'})" >
				<span class="info"></span>
			</dd>
		</dl>
		
		
		<dl>
			<dt><bmtag:message messageKey="电子邮件"/></dt>
			<dd>
				<input name="normEmail" class="email textInput" maxlength="50" style="width:130px;"/>
				<span class="info"></span>
			</dd>
		</dl>
		
		<dl>
			<dt><bmtag:message messageKey="联系地址"/></dt>
			<dd>
				<input name="normAddress" minlength="6" maxlength="80" style="width:130px;"/>
				<span class="info"></span>
			</dd>
		</dl>
		
		<dl>
			<dt><bmtag:message messageKey="驾驶证号"/></dt>
			<dd>
				<input name="normDrivingLicence" maxlength="48" style="width:130px;"/>
				<span class="info"></span>
			</dd>
		</dl>
				
		<%-- <dl>
			<dt><bmtag:message messageKey="IC卡号"/></dt>
			<dd>
				<input name="normIdCard" maxlength="20" style="width:130px;"/>
				<span class="info"></span>
			</dd>
		</dl> --%>
		
		<dl>
			<dt><bmtag:message messageKey="品牌"/></dt>
			<dd>
				<select name="normCarCompanyId" ref="usbrandadd" class="select_Style" onchange="getCarTypeName(this,'usinAddCarinfo')" style="width: 172px !important">
					<option value="0">--请选择--</option>
					<c:forEach items="${brandList}" var="brand">
						<option value="${brand.pk_carCompany }">${brand.carCompany_Name }</option>
					</c:forEach>
				</select>
			</dd>
		</dl>
		
		<dl>
			<dt><bmtag:message messageKey="车型"/></dt>
			<dd>
				<select id="usinAddCarinfo" name="normCarTypeId" id="usbrandadd" class="select_Style addUsbrandedit" style="width: 172px !important">
					<option value="0">--请选择--</option>
				</select>
			</dd>
		</dl>
		
		<dl>
			<dd>
				<input name="usinGroupid" value="1" type="hidden" />
			</dd>
		</dl>
	</div>
	<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit" onclick="formatUserInfo_add()"
						type="button" id="formSubmitter" />
				</li>
				<li><bmtag:button messageKey="common.button.back"  type="button" dwzClass="close"/></li>
			</ul>
	</div>
</form>
</div>
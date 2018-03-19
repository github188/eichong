<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript">
	var webroot = "${webroot}";

	function ajaxDoneCallback(json){
	}
	
	//获取车型
	function getCarTypeName(cocoContent){
		if(cocoContent.value==""){
			return;
		}
		$.get(webroot + "/admin/cartype/getCarTypeName.do?carInfoCompanyId="+cocoContent.value+"",{}, function(data) {
			var json = eval("(" + data + ")");
			//console.debug(json);
			//console.debug($(".addUnbrandadd"));
			$(".addUnbrandadd").html("");
			 $("<option value='0'>-请选择-</option>").appendTo(
					$(".addUnbrandadd"));
			$.each(json, function(i) { 
				$(  
						
						"<option value='"+json[i].pkCarinfo+"'>"
								+ json[i].carinfoStylename + "</option>").appendTo(
						$(".addUnbrandadd"));
			}); 
		});
	}
	
</script>
<h2 class="contentTitle"><bmtag:message messageKey="user.title.user.new"/></h2>
<div class="pageContent">
<form method="post" action="userManager/saveUnit.do" class="pageForm required-validate" 
	enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone)">
	<div class="pageFormContent nowrap" layoutH="97">
	<input type="hidden" value="5" name="userLevel" />
	<input type="hidden" value="3" name="normStatus" />
		<dl><dt><bmtag:message messageKey="姓名"/></dt><dd>
			<input name="normRealName" class="textInput required" maxlength="20" style="width:130px;"/>
			<span class="info"></span></dd></dl>
		<dl><dt><bmtag:message messageKey="手机号"/></dt><dd>
			<input name="userAccount" class="required phone" remote="userManager/checkUserPhone.do" maxlength="11" style="width:130px;"/>
			<span class="info"></span></dd></dl>
		<dl><dt><bmtag:message messageKey="昵称"/></dt><dd>
			<input name="normName" class="textInput" maxlength="20" style="width:130px;"/>
			<span class="info"></span></dd></dl>
		<dl><dt><bmtag:message messageKey="密码"/></dt><dd>
			<input name="userPassword"   class="required digits textInput"  minlength="6" maxlength="8" style="width:130px;"/>
			<span class="info"></span></dd></dl>
		<dl>
			<dt>
				<bmtag:message messageKey="选择角色" />
			</dt>
			<dd>
			  	<c:forEach var="item" items="${roleList}">
					<input class="required" type="checkbox" name="roleId" value="${item.roleId}" />${item.roleName}
			  	</c:forEach>  
			</dd>
		</dl>		
		<dl><dt><bmtag:message messageKey="头像"/></dt><dd>
			<input type="file" name="allMultiFile" class="file"  style="width:360px;"/>
			<span class="info"></span></dd></dl>
		<dl><dt><bmtag:message messageKey="性别"/></dt><dd>
			<input type="radio" value="0" name="normSex" />男 <input type="radio" value="1" name="normSex" />女 
					<span class="info"></span></dd></dl>
		<dl>
			<dd>
				<label>出生日期</label>				
				 <input id="newUnit_createDate" name="normBirthday"  class="date"  style="width:130px" class="inputDateButton"
					        type="text"  readonly="true" 
						 onClick="WdatePicker({el:'newUnit_createDate',minDate:'1970-01-01'})" >
				<span class="info"></span>
			</dd>
		</dl>
		<dl><dt><bmtag:message messageKey="电子邮件"/></dt><dd>
			<input name="normEmail" class="email" maxlength="50" style="width:130px;"/>
			<span class="info"></span></dd></dl>
		<dl><dt><bmtag:message messageKey="联系地址"/></dt><dd>
			<input name="normAddress" minlength="6" maxlength="80" style="width:130px;"/>
			<span class="info"></span></dd></dl>
		<dl><dt><bmtag:message messageKey="驾驶证号"/></dt><dd>
			<input name="normDrivingLicence" maxlength="80" style="width:130px;"/>
			<span class="info"></span></dd></dl>
		<%-- <dl><dt><bmtag:message messageKey="IC卡号"/></dt><dd>
			<input name="normIdCard" maxlength="20" style="width:130px;"/>
			<span class="info"></span></dd></dl> --%>
		<dl>
			<dt><bmtag:message messageKey="品牌"/></dt>
			<dd>
				<select name="normCarCompanyId" ref="unbrandadd" class="select_Style" onchange="getCarTypeName(this)" style="width: 172px !important">
					<option value="">--请选择--</option>
					<c:forEach items="${brandList}" var="brand">
						<option value="${brand.pk_carCompany}" >${brand.carCompany_Name }</option>
					</c:forEach>
				</select>
			</dd>
		</dl>
		
		<dl>
			<dt><bmtag:message messageKey="车型"/></dt>
			<dd>
				<select name="normCarTypeId" id="unbrandadd" class="select_Style addUnbrandadd" style="width: 172px !important">
					<option value="">--请选择--</option>
				</select>
			</dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey="授权人身份证"/></dt>
				<dd>
					<input type="file" name="idCardPic" class="file"  style="width:360px;"/>
					<span class="info"></span>
				</dd>
			</dl>
		
		<dl>	
			<dd>
				<input name="usinGroupid" value="2" type="hidden" />
			</dd>
		</dl>
	</div>
	<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit" type="submit" id="formSubmitter"/></li>
				<li><bmtag:button messageKey="common.button.back"  type="button" dwzClass="close"/></li>
			</ul>
	</div>
</form>
</div>
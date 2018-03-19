<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript">
	var webroot = "${webroot}";

	function ajaxDoneCallback(json) {
	}
	
	function getNowFormatDate(){
	    var day = new Date();
	    var Year = 0;
	    var Month = 0;
	    var Day = 0;
	    var CurrentDate = "";
	    Year= day.getFullYear();//支持IE和火狐浏览器.
	    Month= day.getMonth()+1;
	    Day = day.getDate();
	    CurrentDate += Year;
	    if (Month >= 10 ){
	     CurrentDate += Month;
	    }
	    else{
	     CurrentDate += "0" + Month;
	    }
	    if (Day >= 10 ){
	     CurrentDate += Day ;
	    }
	    else{
	     CurrentDate += "0" + Day ;
	    }
	    return CurrentDate;
	 }
	
	//获取车型
	function getCarTypeName(cocoContent){
		if(cocoContent.value==""){
			return;
		}
		$.get(webroot + "/admin/cartype/getCarTypeName.do?carInfoCompanyId="+cocoContent.value+"",{}, function(data) {
			var json = eval("(" + data + ")");
			console.debug(json);
			console.debug($(".editUnbrandadd"));
			$(".editUnbrandadd").html("");
			 $("<option value='0'>-请选择-</option>").appendTo(
					$(".editUnbrandadd"));
			$.each(json, function(i) { 
				$(  
						
						"<option value='"+json[i].pkCarinfo+"'>"
								+ json[i].carinfoStylename + "</option>").appendTo(
						$(".editUnbrandadd"));
			}); 
		});
	}
</script>
<h2 class="contentTitle">
	<bmtag:message messageKey="user.title.user.edit" />
</h2>
<div class="pageContent">
	<form method="post" action="userManager/modifyUnit.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<input type="hidden" name="userId" value="${user.userId}" />
			<input type="hidden" name="userLevel" value="${user.userLevel}" />
			<dl>
				<dt>
					<bmtag:message messageKey="姓名" />
				</dt>
				<dd>
					<input name="normRealName" class="textInput required"
						value="${user.normRealName}" maxlength="20" style="width: 130px;" />
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="手机号" />
				</dt>
				<dd>
					<input name="userAccount" class="required phone" idName="userId" data-id="${user.userId}" remote="userManager/checkUserPhone.do"
						value="${user.userAccount}" maxlength="11"
						style="width: 130px;" /> <span class="info"></span>
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
					<bmtag:message messageKey="选择角色" />
				</dt>
				<dd>
				  	<c:forEach var="item" items="${roleList}">
						<input class="required" type="checkbox" name="roleId" value="${item.roleId}" 
							<c:forEach var="selectItem" items="${selectRoleList}">
								<c:if test="${item.roleId==selectItem.roleId}">checked</c:if>
							</c:forEach>
						/>${item.roleName}
				  	</c:forEach>  
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
				<input type="file" name="allMultiFile" class="file"  style="width:360px;  margin-left: 130px;"/>
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
						  <input id="unitInfo_createDate" name="normBirthday"  class="date"  style="width:130px"
					        type="text"  readonly="true" value="${user.normBirthday }"
						 onClick="WdatePicker({el:'unitInfo_createDate',minDate:'1970-01-01'})" >
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
					<select name="normCarCompanyId" ref="unbrandedit" class="select_Style" onchange="getCarTypeName(this)" style="width: 172px !important">
						<option value="">--请选择--</option>
						<c:forEach items="${brandList}" var="brand">
							<option value="${brand.pk_carCompany }" ${brand.pk_carCompany == user.normCarCompanyId ? 'selected="selected"' : ''}>${brand.carCompany_Name }</option>
						</c:forEach>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="车型" />
				</dt>
				<dd>
					<select name="normCarTypeId" id="unbrandedit" class="select_Style editUnbrandadd" style="width: 172px !important">
						<option value = "0">--请选择--</option>
						<c:forEach items="${carTypeList}" var="carType">
						<option value="${user.normCarTypeId }" ${carType.pkCarinfo == user.normCarTypeId?'selected="selected"':''}>${carType.carinfoStylename }</option>
						</c:forEach>
					</select>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="状态"/></dt>
				<dd>
					<select name="usinUserstatus" style="width: 136px;">
						<option value="0">全部</option>
						<option value="1" ${user.userStatus == '1'? 'selected="selected"' : ''}>初始</option>
						<option value="2" ${user.userStatus == '2'? 'selected="selected"' : ''}>冻结</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="身份证凭证" />
				</dt>
				<dd>
					<img alt="" src="${user.idCardimage}"  style="float: left;"
							width="100px" height="100px">
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<input type="file" name="idCardPic" class="file"  style="width:360px;margin-left: 130px;"/>
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
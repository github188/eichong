<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2 class="contentTitle">
	<bmtag:message messageKey="user.title.user.view" />
</h2>
<div class="pageContent">
	<div class="pageFormContent nowrap" layoutH="97">
		<dl>
				<dt>
					<bmtag:message messageKey="姓名" />
				</dt>
				<dd>
					${user.normRealName}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="手机号" />
				</dt>
				<dd>
					${user.userAccount}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="昵称" />
				</dt>
				<dd>
					${user.normName}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="拥有角色"/>
				</dt>
				<dd>
					<c:forEach var="selectItem" items="${selectRoleList}">
						${selectItem.roleName}&nbsp;&nbsp;&nbsp;&nbsp;
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
				<dt>
					<bmtag:message messageKey="性别" />
				</dt>
				<dd>
					<c:if test="${user.normSex==0}">男</c:if>
					<c:if test="${user.normSex==1}">女</c:if>
				<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="出生日期" />
				</dt>
				<dd>
					${user.normBirthday}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="电子邮件" />
				</dt>
				<dd>
					${user.normEmail}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="联系地址" />
				</dt>
				<dd>
					${user.normAddress}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="驾驶证号" />
				</dt>
				<dd>
					${user.normDrivingLicence}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="品牌" />
				</dt>
				<dd>
					<select disabled class="select_Style" style="width: 172px !important">
						<option value="0">--请选择--</option>
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
					<select disabled class="select_Style editUnbrandadd" style="width: 172px !important">
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
					<select name="usinUserstatus" disabled="disabled">
						<option value="0">全部</option>
						<option value="1" ${user.userStatus == '1'? 'selected="selected"' : ''}>正常 </option>
						<option value="2" ${user.userStatus == '2'? 'selected="selected"' : ''}>冻结</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="身份证复印件" />
				</dt>
				<dd>
					<img alt="" src="${user.idCardimage}"  style="float: left;"
							width="100px" height="100px">
					<span class="info"></span>
				</dd>
			</dl>
	</div>
	<div class="formBar">
		<ul>
			<li><bmtag:button messageKey="common.button.back" type="button"
					dwzClass="close" /></li>
		</ul>
	</div>
</div>
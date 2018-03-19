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
		<%-- <dl>
				<dt>
					<bmtag:message messageKey="企业名称"/>
				</dt>
				<dd>
					${tblPureBusiness.companyName }
					<span class="info"></span>
				</dd>
			</dl>			
			<dl>
				<dt>
					<bmtag:message messageKey="平台昵称"/>
				</dt>
				<dd>
					${tblPureBusiness.nickName }
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="企业地址"/>
				</dt>
				<dd>
					${tblPureBusiness.companyAddress }
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="企业邮箱"/>
				</dt>
				<dd>
					${tblPureBusiness.companyEmail }
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="邮编"/>
				</dt>
				<dd>
					${tblPureBusiness.postCode }
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="经营范围"/>
				</dt>
				<dd>
					${tblPureBusiness.scopeBusiness }
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="授权人姓名"/>
				</dt>
				<dd>
					${tblPureBusiness.authorizedName }
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="授权人联系电话"/>
				</dt>
				<dd>
					${tblPureBusiness.authorizedPhone }
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="授权人身份证" />
				</dt>
				<dd>
					<img alt="" src="${tblPureBusiness.authorizedCard}"  style="float: left;"
							width="100px" height="100px">
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="营业执照" />
				</dt>
				<dd>
					<img alt="" src="${tblPureBusiness.businessLicence}"  style="float: left;"
							width="100px" height="100px">
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="组织机构代码"/>
				</dt>
				<dd>
					${tblPureBusiness.organizationCode}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="税务登记证" />
				</dt>
				<dd>
					<img alt="" src="${tblPureBusiness.torontoHospital}"  style="float: left;"
							width="100px" height="100px">
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="授权证明" />
				</dt>
				<dd>
					<img alt="" src="${tblPureBusiness.authorization}"  style="float: left;"
							width="100px" height="100px">
					<span class="info"></span>
				</dd>
			</dl>
		 	<dl>
				<dt>
					<bmtag:message messageKey="注册时间"/>
				</dt>
				<dd>
					<fmt:formatDate value="${tblPureBusiness.createdate}" pattern="yyyy-MM-dd"/>				
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="邮寄地址"/></dt>
				<dd>
					${tblPureBusiness.mailingAddress }
					<span class="info"></span>
				</dd>
			</dl> --%>
	
			<dl>
				<dt>
					<bmtag:message messageKey="爱充网账号"/>
				</dt>
				<dd>
					${tblPureBusiness.userAccount}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="姓名"/>
				</dt>
				<dd>
					${tblPureBusiness.busiName}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="手机号"/>
				</dt>
				<dd>
					${tblPureBusiness.busiPhone}
					<span class="info"></span>
				</dd>
			</dl>
			<%-- <dl>
				<dt>
					<bmtag:message messageKey="登录密码"/>
				</dt>
				<dd>
					${tblPureBusiness.lovePassword}
					<span class="info"></span>
				</dd>
			</dl> --%>
             <dl>
				<dt>
					<bmtag:message messageKey="所属企业"/>
				</dt>
				<dd>
					${company.cpyCompanyname}
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
					<bmtag:message messageKey="状态"/>
				</dt>
				<dd>
					<c:choose>
						<c:when test="${tblPureBusiness.userStatus == '1'}">
							正常
						</c:when>
						<c:when test="${tblPureBusiness.userStatus == '2'}">
							禁用
						</c:when>
					</c:choose>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	var webroot = "${webroot}";

	function ajaxDoneCallback(json) {
	}

</script>
<h2 class="contentTitle">
	<bmtag:message messageKey="user.title.user.edit" />
</h2>
<div class="pageContent">
	<form method="post" action="userManager/upgradeUserList.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<input type="hidden" name="pkUserinfo" value="${user.pkUserinfo}" />
			<dl>
				<dt>
					<bmtag:message messageKey="姓名" />
				</dt>
				<dd>
					${user.usinFacticityname}
					<span class="info"></span>
				</dd>
			</dl>			
			<dl>
				<dt>
					<bmtag:message messageKey="头像" />
				</dt>
				<dd>
					<img alt="" src="${user.usinHeadimage}"  style="float: left;"
							width="100px" height="100px">
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="性别" />
				</dt>
				<dd>
					<input type="radio" value="0" name="usinSex" ${user.usinSex==
					0?'checked="checked" ':''}/>男 <input type="radio" value="1" name="sex"
					${user.usinSex== 1?'checked="checked" ':''}/>女 <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="出生日期" />
				</dt>
				<dd>
					<fmt:formatDate value="${user.usinBirthdate }" pattern="yyyy-MM-dd"/>
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="手机号" />
				</dt>
				<dd>
					${user.usinPhone}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="电子邮件" />
				</dt>
				<dd>
					${user.usinEmail}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="联系地址" />
				</dt>
				<dd>
					${user.usinUseraddress}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="驾驶证号" />
				</dt>
				<dd>
					${user.usinDrivinglicense}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="会员卡号" />
				</dt>
				<dd>
					${user.usinMembercode}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="IC卡号" />
				</dt>
				<dd>
					${user.usinIccode}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="品牌车型" />
				</dt>
				<dd>
					${user.carName}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="状态"/></dt>
				<dd>
					<select name="usinUserstatus">
						<option value="0">全部</option>
						<option value="1" ${user.usinUserstatus == '1'? 'selected="selected"' : ''}>正常 </option>
						<option value="2" ${user.usinUserstatus == '2'? 'selected="selected"' : ''}>冻结</option>
						<option value="3" ${user.usinUserstatus == '3'? 'selected="selected"' : ''}>商家升级审核中</option>
						<option value="4" ${user.usinUserstatus == '4'? 'selected="selected"' : ''}> 普通用户</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="授权人身份证"/></dt>
				<dd>
					<input type="file" name="IdCardPic" class="file"  style="width:360px;"/>
					<span class="info"></span>
				</dd>
			</dl>
			<div class="formBar">
				<ul>
					<li><bmtag:button messageKey="common.button.submit"
							type="submit" id="formSubmitter" /></li>
					<li><bmtag:button messageKey="common.button.back" type="button"
							dwzClass="close" /></li>
				</ul>
			</div>
		</div>
	</form>
</div>
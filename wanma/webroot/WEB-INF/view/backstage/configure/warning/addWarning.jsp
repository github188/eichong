<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<script type="text/javascript">
</script>
<h2 class="contentTitle">
	<bmtag:message messageKey="新增企业账户预警" />
</h2>
<div class="pageContent">
	<form method="post" action="warning/saveWarning.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			
			<dl>
				<dt style="width:120px;">选择公司</dt>
				<dd>
				 <select name="thwUserId" class="select_Style" style="width: 165px;">
								<c:forEach var="item" items="${companyType}">
							<option value="${item.thwUserId}"
										${item.thwUserId==item.thwUserId?'selected="selected"' : ''}>${item.thwBusiName}</option> 
								</c:forEach>
						</select>
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt style="width:120px;">预警金额（元）</dt>
				<dd>
					<input name="thwThreshold" class="textInput required"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"
						maxlength="50" style="width: 165px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt style="width:120px;">运营管理员手机号</dt>
				<dd>
					<input name="thwCellphone" class="textInput required" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"
						maxlength="50" style="width: 165px;"/> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt style="width:120px;">公司手机号</dt>
				<dd>
					<input name="thwCustomerPhone"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"
						maxlength="50" style="width: 165px;"/> <span class="info"></span>
				</dd>
			</dl>
		</div>
		
		<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit"
						type="submit" id="formSubmitter_warningAdd" />
				</li>
				<li><bmtag:button messageKey="common.button.back" type="button"
						dwzClass="close" />
				</li>
			</ul>
		</div>
	</form>
</div>

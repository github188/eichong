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
	<bmtag:message messageKey="电桩导入" />
</h2>
<div class="pageContent">
	<form method="post" action="electric/importElectricpile.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dt>
					<bmtag:message messageKey="导入文件" />
				</dt>
				<dd>
					<input type="file" name="file" /> 
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="模板下载" />
				</dt>
				<dd>
					<a href="<%=request.getContextPath()%>/upload/electric/demo.xlsx">点击下载</a>
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

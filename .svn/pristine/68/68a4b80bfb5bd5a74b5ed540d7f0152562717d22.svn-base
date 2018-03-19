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
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript">
function addCooperate(){
	
	alertMsg.confirm("确认增加活动后，系统会根据选择的奖品，在系统中生成优惠券。如，活动选择1000张，满10件2的现金券，系统会生成1000张优惠券，可在优惠券明细中查看。", {
		okCall: function(){
	  $("#chaForm").submit();
		}
	});
}
</script>
<head>

 </head>
<h2 class="contentTitle">新增联营合作商</h2>
<div class="pageContent">
	<form method="post" action="filter/saveCooperate.do" id="cooForm"
		class="required-validate" enctype="multipart/form-data" 
		onsubmit="return iframeCallback(this, navTabAjaxDone)"> 
		<input type="hidden" name="elpiPoweruser" value="3"/>
		<div class="pageFormContent nowrap" layoutH="97">
			
			<dl>
				<dt style="width:80px;">合作商名称</dt>
				<dd>
					 <select
							name="pkCompanyId" class="select_Style" style="width: 150px;">
							
								<c:forEach var="item" items="${Cooperate}">
									<option value="${item.pkCompanyId}"
										${item.pkCompanyId==item.pkCompanyId?'selected="selected"' : ''}>${item.companyName}</option>
								</c:forEach>
						</select>
					<span class="info"></span>
				</dd>
			</dl>
			
		</div>
		
		<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit" 
						type="submit" id="coo_formSubmitter" />
				</li>
				<li><bmtag:button messageKey="common.button.back" type="button"
						dwzClass="close" />
				</li>
			</ul>
		</div>
	</form>
</div>

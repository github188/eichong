<%@ page language="java"  import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript">
	var webroot = "${webroot}";

	function ajaxDoneCallback(json){
	}
	var pkCompanyid = $("#pkCompanyid").val();
	getCpyCompanyNumber(pkCompanyid)
	
	function changeCompany(pkCompanyid) {
		getCpyCompanyNumber(pkCompanyid)
	}	
	function getCpyCompanyNumber(pkCompanyid) {
		$.ajax({
			type : 'post',
			url : basepath+"/admin/offlineCharge/getCpyCompanyNumber.do",
			dataType : "json",
			data:{
				pkCompanyid:pkCompanyid
			},
			success : function(data) {
				var cpyCompanyNumber = data;
				$("#cpyCompanyNumber").html(cpyCompanyNumber);
				$("#cpyCompanyNumber2").val(cpyCompanyNumber);
			}
		});	
	}
	function offlineCharge_add(){
		 	var cpyNum = $("#cpyNum").val();
		 	if(cpyNum<0||cpyNum==0){
		 		alertMsg.error("请输入正确的离线充电次数")
		 	}else if(cpyNum>100){
		 		alertMsg.error("最大离线充电次数不能超过100")
		 	}else{
		 		$("#offlineCharge_add").submit();
		 	}

		}
</script>
<h2 class="contentTitle">
	<bmtag:message messageKey="新增用户标示" />
</h2>
<div class="pageContent">
<form method="post" action="offlineCharge/OfflineChargeAdd.do" class="pageForm required-validate"  id="offlineCharge_add"
	enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone)">
	<div class="pageFormContent nowrap" layoutH="97">
		<dl>
			<dt><bmtag:message messageKey="公司名称"/></dt>
			<dd>
				<select name="pkCompanyid" class="select_Style" id="pkCompanyid" onchange="changeCompany(this.value)">
					<c:forEach var="item" items="${companyList}">
						<option value="${item.pkCompanyid}">${item.cpyCompanyname}</option>
					</c:forEach>
				</select> <span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt><bmtag:message messageKey="公司标识"/></dt>
			<dd>
				<input name="cpyCompanyNumber" type="hidden"  id="cpyCompanyNumber2"/>
				<span  id="cpyCompanyNumber"></span>
				<span class="info"></span>
			</dd>
		</dl>
		<dl>
			<dt>
				<bmtag:message messageKey="最大离线充电次数" />
			</dt>
			<dd>
				<input name="cpyNum" class="required number textInput"  id="cpyNum" />
				<span class="info"></span>
			</dd>
		</dl>
	</div>
	<div class="formBar" >
			<ul>
				<li><bmtag:button messageKey="common.button.submit" onclick="offlineCharge_add()"
						type="button" id="formSubmitter" /></li>
				<li><bmtag:button messageKey="common.button.back"  type="button" dwzClass="close"/></li>
			</ul>
	</div>
</form>
</div>
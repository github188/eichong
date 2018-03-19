<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<script type="text/javascript">
	var webroot = "${webroot}";
	
	$("[name='formSubmitter']").each(function(){
			$(this).click(function(event){
				var form = $("#"+$(this).attr('data-form-name'));
				$(form).submit();
			});
		}
	)

	function submitHomePage(form) {
		return iframeCallback(form, navTabAjaxDone);
	}
	
	

</script> 
<h2 class="contentTitle">
	<bmtag:message messageKey="user.title.user.edit" />
</h2>
<div class="pageContent">
<div class="pageFormContent nowrap" layoutH="97">
	<form id="form1" method="post" action="advertise/modifyHomePage.do" data-fileId="form1File"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return submitHomePage(this)">
		<input type="hidden" name="homepageId" value="${homePage1.homepageId}" />
		<input type="hidden" name="homePageName" value="${homePage1.homePageName}" />
		<input type="hidden" name="homePageSequence" value="${homePage1.homePageSequence}" />
		<table>
		<tr>
			<td height="200px" width="80px">轮播图${homePage1.homepageId}</td>
			<td width="200px" height="200px">
				<div><img src="${homePage1.homePageImage}"  style="float: left;" width="200px" height="200px" onerror="this.src='<%=request.getContextPath()%>/res/bluemobi/img/default.png'"></div>
				<input id="form1File" type="file" name="listImage" class="file"  style="width:260px;margin-top:25px" />
				</td>		
		<td height="200px" width="40px">链接:</td><td width="180px"><input type="text" name="homePageUrl"  value="${homePage1.homePageUrl}"/></td>
		<td>
			<input type="button" data-form-name="form1" name="formSubmitter" value="提交"/>
			<br/>&nbsp;&nbsp;<br/>&nbsp;&nbsp;
		<bmtag:link href="advertise/deleteHomePage.do?homepageId=${homePage1.homepageId}"
				target="ajaxTodo" altKey="common.msg.delete.confirm" messageKey="common.icon.delete" dwzClass="delete"/></td>
		</tr>
		<tr><td colspan="4"><hr></td></tr>
		</table>
	</form>
	<form id="form2" method="post" action="advertise/modifyHomePage.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return submitHomePage(this)">
		<input type="hidden" name="homepageId" value="${homePage2.homepageId}" />
		<input type="hidden" name="homePageName" value="${homePage2.homePageName}" />
		<input type="hidden" name="homePageSequence" value="${homePage2.homePageSequence}" />
		<table>
		<tr>
			<td height="200px" width="80px">轮播图${homePage2.homepageId}</td>
			<td width="200px" height="200px">
				<div><img src="${homePage2.homePageImage}"  style="float: left;" width="200px" height="200px" onerror="this.src='<%=request.getContextPath()%>/res/bluemobi/img/default.png'"></div>
				<input type="file" name="listImage" class="file"  style="width:260px;margin-top:25px" />
				</td>		
		<td height="200px" width="40px">链接:</td><td width="180px"><input type="text" name="homePageUrl"  value="${homePage2.homePageUrl}"/></td>
		<td><input type="button" data-form-name="form2" name="formSubmitter" value="提交"/><br/>&nbsp;&nbsp;<br/>&nbsp;&nbsp;
		<bmtag:link href="advertise/deleteHomePage.do?homepageId=${homePage2.homepageId}"
				target="ajaxTodo" altKey="common.msg.delete.confirm" messageKey="common.icon.delete" dwzClass="delete"/>
		</td>
		</tr>
		<tr><td colspan="4"><hr></td></tr>
		</table>
	</form>
	<form id="form3" method="post" action="advertise/modifyHomePage.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return submitHomePage(this)">
		<input type="hidden" name="homepageId" value="${homePage3.homepageId}" />
		<input type="hidden" name="homePageName" value="${homePage3.homePageName}" />
		<input type="hidden" name="homePageSequence" value="${homePage3.homePageSequence}" />
		<table>
		<tr>
			<td height="200px" width="80px">轮播图${homePage3.homepageId}</td>
			<td width="200px" height="200px">
				<div><img src="${homePage3.homePageImage}"  style="float: left;" width="200px" height="200px" onerror="this.src='<%=request.getContextPath()%>/res/bluemobi/img/default.png'"></div>
				<input type="file" name="listImage" class="file"  style="width:260px;margin-top:25px" />
				</td>		
		<td height="200px" width="40px">链接:</td><td width="180px"><input type="text" name="homePageUrl"  value="${homePage3.homePageUrl}"/></td>
		<td><input type="button" data-form-name="form3" name="formSubmitter" value="提交"/><br/>&nbsp;&nbsp;<br/>&nbsp;&nbsp;
		<bmtag:link href="advertise/deleteHomePage.do?homepageId=${homePage3.homepageId}"
				target="ajaxTodo" altKey="common.msg.delete.confirm" messageKey="common.icon.delete" dwzClass="delete"/>
		</td>
		</tr>
		<tr><td colspan="4"><hr></td></tr>
		</table>
	</form>
	<form id="form4" method="post" action="advertise/modifyHomePage.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return submitHomePage(this)">
		<input type="hidden" name="homepageId" value="${homePage4.homepageId}" />
		<input type="hidden" name="homePageName" value="${homePage4.homePageName}" />
		<input type="hidden" name="homePageSequence" value="${homePage4.homePageSequence}" />
		<table>
		<tr>
			<td height="200px" width="80px">轮播图${homePage4.homepageId}</td>
			<td width="200px" height="200px">
				<div id="preview"><img alt="" src="${homePage4.homePageImage}"  style="float: left;" width="200px" height="200px" onerror="this.src='<%=request.getContextPath()%>/res/bluemobi/img/default.png'"></div>
				<input type="file" name="listImage" class="file"  style="width:260px;margin-top:25px" />
				</td>		
		<td height="200px" width="40px">链接:</td><td width="180px"><input type="text" name="homePageUrl"  value="${homePage4.homePageUrl}"/></td>
		<td><input type="button" data-form-name="form4" name="formSubmitter" value="提交"/><br/>&nbsp;&nbsp;<br/>&nbsp;&nbsp;
		<bmtag:link href="advertise/deleteHomePage.do?homepageId=${homePage4.homepageId}"
				target="ajaxTodo" altKey="common.msg.delete.confirm" messageKey="common.icon.delete" dwzClass="delete"/>
		</td>
		</tr>
		<tr><td colspan="4"><hr></td></tr>
		</table>
	</form>
	<form  id="form5" method="post" action="advertise/modifyHomePage.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return submitHomePage(this)">
		<input type="hidden" name="homepageId" value="${homePage5.homepageId}" />
		<input type="hidden" name="homePageName" value="${homePage5.homePageName}" />
		<input type="hidden" name="homePageSequence" value="${homePage5.homePageSequence}" />
		<table>
		<tr>
			<td height="200px" width="80px">轮播图${homePage5.homepageId}</td>
			<td width="200px" height="200px">"
				<div><img src="${homePage5.homePageImage}"  style="float: left;" width="200px" height="200px" onerror="this.src='<%=request.getContextPath()%>/res/bluemobi/img/default.png'"></div>
				<input type="file" name="listImage" class="file"  style="width:260px;margin-top:25px" />
				</td>		
		<td height="200px" width="40px">链接:</td><td width="180px"><input type="text" name="homePageUrl"  value="${homePage5.homePageUrl}"/></td>
		<td><input type="button" data-form-name="form5" name="formSubmitter" value="提交"/><br/>&nbsp;&nbsp;<br/>&nbsp;&nbsp;
		<bmtag:link href="advertise/deleteHomePage.do?homepageId=${homePage5.homepageId}"
				target="ajaxTodo" altKey="common.msg.delete.confirm" messageKey="common.icon.delete" dwzClass="delete"/>
		</td>
		</tr>
		<tr><td colspan="4"><hr></td></tr>
		</table>
	</form>
	</div>
</div>
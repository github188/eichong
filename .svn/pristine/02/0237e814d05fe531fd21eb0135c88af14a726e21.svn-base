<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="jstltag"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<script type="text/javascript">
	var webroot = "${webroot}";
	var menuId = "${menuModel.menuId}";
	var createUser = "${menuModel.createUser}";
	$(function() {
		if (menuId == "") {
			disableItems("init");
		} else if (menuId != "" && createUser == "") {
			disableItems("new");
		} else {
			disableItems("edit");
		}
	});
	function ajaxDoneCallback(json) {
	}

	function disableItems(type) {
		if ("init" == type) {
			$("input").attr("readonly", true);
			$("textarea").attr("readonly", true);
			$("#addMenu").attr("disabled", true);
            disabledButton($("#addMenu_B"));
			$("#deleteMenu").attr("disabled", true);
            disabledButton($("#deleteMenu_B"));
            disabledButtonLink($("#menuCopyLink"));
            disabledLink($("#addMenuRoleLink"));
            disabledLink($("#delMenuRoleLink"));
            disabledLink($("#addMenuPostLink"));
            disabledLink($("#delMenuPostLink"));
            disabledLink($("#addMenuDeptLink"));
            disabledLink($("#delMenuDeptLink"));
		} else if ("new" == type) {
            disabledButtonLink($("#menuCopyLink"));
			$("#deleteMenu").attr("disabled", true);
            disabledButton($("#deleteMenu_B"));
            disabledButtonLink($("#menuAddLink"));
		} else if ("edit" == type) {
			$("#menuId").attr("readonly", true);
			$("#menuId").removeAttr("remote");
		}
	}

	function onAddClick() {
		var isCheckOK = false;
		document.menuForm.action = "menu/saveMenu.do";
		isCheckOK = document.menuForm.onsubmit();

		if (undefined == isCheckOK) {
			document.menuForm.submit();
		}
	}

	function onModifyClick() {
		var isCheckOK = false;
		document.menuForm.action = "menu/modifyMenu.do";
		isCheckOK = document.menuForm.onsubmit();
		if (undefined == isCheckOK) {
			document.menuForm.submit();
		}
	}

	function onDeleteClick() {
		var isCheckOK = false;
		document.menuForm.action = "menu/removeMenu.do";
		isCheckOK = document.menuForm.onsubmit();

		if (undefined == isCheckOK) {
			document.menuForm.submit();
		}
	}
</script>
<style type="text/css">
ul.rightTools {
	float: right;
	display: block;
}

ul.centerTools {
	float: left;
	display: block;
	margin-left: 220px;
}

ul.centerTools li {
	float: left;
	display: block;
	margin-left: 220px;
}

ul.rightTools li {
	float: left;
	display: block;
	margin-left: 5px
}
</style>
<div class="pageHeader" style="border: 1px #B8D0D6 solid">
	<div class="panel" defH="25">
		<h1>
			<bmtag:message messageKey="menu.title.menu.new" />
		</h1>
		<div>
		<c:if test="${isSuperUser==true}">
		  <ul class="rightTools">
				<li><bmtag:link messageKey="common.button.copy"
						href="menu/copyMenu.do?menuId=${menuModel.menuId}" target="ajax"
						rel="menuBox" dwzClass="button" id="menuCopyLink" /></li>
				<li><bmtag:link messageKey="common.button.new"
						href="menu/newMenu.do?menuId=${menuModel.menuId}" target="ajax"
						rel="menuBox" dwzClass="button" id="menuAddLink" /></li>
				<li><bmtag:button messageKey="common.button.delete"
						type="button" id="deleteMenu" onclick="onDeleteClick();" /></li>
			</ul> 
			</c:if>
		</div>
	</div>

	<div class="pageFormContent nowrap" layoutH="97">
		<form method="post" action="menu/saveMenu.do" id="menuForm"
			name="menuForm" class="pageForm required-validate"
			enctype="multipart/form-data"
			onsubmit="return iframeCallback(this, navTabAjaxDone)">
			
			<div class="tabs">
				<div class="tabsHeader">
					<div class="tabsHeaderContent">
						<ul>
						<c:if test="${isSuperUser==true}">
							    <li><a href="javascript:;"><span><bmtag:message
											messageKey="common.tab.title.base" /></span></a></li>   </c:if>
							<li><a href="javascript:;" disabled="false" ><span><bmtag:message
											messageKey="menu.tab.role_users" /></span></a></li>
						<c:if test="${isSuperUser==true}">
							  <%-- <li><a href="javascript:;"><span><bmtag:message
											messageKey="menu.tab.department_users" /></span></a></li>
							<li><a href="javascript:;"><span><bmtag:message
											messageKey="menu.tab.post_users" /></span></a></li>   --%> </c:if>
						</ul>
					</div>
				</div>
				<div class="tabsContent">
				<c:if test="${isSuperUser==true}">
					  <div id="menuBase" class="unitBox" layoutH="234" 
							style="overflow: auto;">
						<%@include file="menuBaseInfo.jsp"%>
					</div>		 	</c:if>
					<div id="menuRole" class="unitBox">
						<%@include file="menuRole.jsp"%>
					</div>
					<c:if test="${isSuperUser==true}">
					<%--  <div id="menuDept" class="unitBox" >
						<%@include file="menuDepartment.jsp"%>
					</div> --%>
					<%-- <div id="menuPost" class="unitBox" >
						<%@include file="menuPost.jsp"%>
					</div> --%> </c:if>
					<c:if test="${isSuperUser==false}">
					<%@include file="menuBaseInfo.jsp"%>
					</c:if>
				</div>
				<div class="tabsFooter">
					<div class="tabsFooterContent"></div>
				</div>
			</div>
			
			<br/>
			<ul class="centerTools">
				<li><jstltag:if
						test="${menuModel.createUser==null ||menuModel.createUser==''}">
						<bmtag:button messageKey="common.button.save" type="button"
							id="addMenu" onclick="onAddClick();" />
					</jstltag:if> <jstltag:if
						test="${menuModel.createUser!=null && menuModel.createUser != ''}">
						<bmtag:button messageKey="common.button.update" type="button"
							id="modifyMenu" onclick="onModifyClick();" />
					</jstltag:if></li>
			</ul>
		</form>
	</div>
</div>
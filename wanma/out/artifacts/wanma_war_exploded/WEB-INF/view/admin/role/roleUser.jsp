<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<script type="text/javascript">

	var webroot = "${webroot}";
	function ajaxDoneCallback(json) {
	}
	var explorer =getBrowserInfo();
	 if (explorer.browser.indexOf("firefox") >= 0) {
		var divWidth=$("#roleUser").width()-2;
		$("#roleUserTable").css("width",divWidth+"px");
	} 
	
	function getBrowserInfo(){
		var userAgent = navigator.userAgent,   
		rMsie = /(msie\s|trident.*rv:)([\w.]+)/,   
		rFirefox = /(firefox)\/([\w.]+)/,   
		rOpera = /(opera).+version\/([\w.]+)/,   
		rChrome = /(chrome)\/([\w.]+)/,   
		rSafari = /version\/([\w.]+).*(safari)/;  
		var browser;  
		var version;  
		var ua = userAgent.toLowerCase();  
		  var match = rMsie.exec(ua);  
		  if(match != null){  
		    return { browser : "ie", version : match[2] || "0" };  
		  }  
		  var match = rFirefox.exec(ua);  
		  if (match != null) {  
		    return { browser : match[1] || "", version : match[2] || "0" };  
		  }  
		  var match = rOpera.exec(ua);  
		  if (match != null) {  
		    return { browser : match[1] || "", version : match[2] || "0" };  
		  }  
		  var match = rChrome.exec(ua);  
		  if (match != null) {  
		    return { browser : match[1] || "", version : match[2] || "0" };  
		  }  
		  var match = rSafari.exec(ua);  
		  if (match != null) {  
		    return { browser : match[2] || "", version : match[1] || "0" };  
		  }  
		  if (match != null) {  
		    return { browser : "", version : "0" };  
		  }  
		return null;
	}
</script>

<div class="pageContent"
	style="border-left: 1px #B8D0D6 solid; border-right: 1px #B8D0D6 solid">
	<div class="panelBar">
		<ul class="toolBar">
			<c:choose>
			  <c:when test="${loginUser.userLevel==3}"><!-- 纯商家 -->
				  <li><bmtag:link href="common/userSelectList.do?backRel=roleUserList&processType=role&userLevel=3&userId=${loginUser.userId}" target="dialog"
						rel="selDeptUser" messageKey="common.icon.new"
						dwzClass="add" id="addRoleUserLink"/></li>
			  </c:when>
			   <c:when test="${roleModel.roleName=='个体商家'}"><!-- 个体商家 -->
					  <li><bmtag:link href="common/userSelectList.do?backRel=roleUserList&processType=role&userLevel=5&userStatus=3" target="dialog"
							rel="selDeptUser" messageKey="common.icon.new"
							dwzClass="add" id="addRoleUserLink"/></li>
				  </c:when>
				  <c:when test="${roleModel.roleName=='纯商家'}"><!-- 纯商家 -->
					  <li><bmtag:link href="common/userSelectList.do?backRel=roleUserList&processType=role&userLevel=3" target="dialog"
							rel="selDeptUser" messageKey="common.icon.new"
							dwzClass="add" id="addRoleUserLink"/></li>
				  </c:when>
				  
				   <c:when test="${roleModel.roleName=='超级管理员'}"><!-- 管理员 -->
					  <li><bmtag:link href="common/userSelectList.do?backRel=roleUserList&processType=role&userLevel=1" target="dialog"
							rel="selDeptUser" messageKey="common.icon.new"
							dwzClass="add" id="addRoleUserLink"/></li>
				  </c:when>
				 
				  <c:otherwise>
					  <li><bmtag:link href="common/userSelectList.do?backRel=roleUserList&processType=role&userLevel=2" target="dialog"
							rel="selDeptUser" messageKey="common.icon.new"
							dwzClass="add" id="addRoleUserLink"/></li>
				  </c:otherwise>
			</c:choose>
			<li><bmtag:link
					href="role/removeRoleUser.do?pageNum=${pager.pageNum }&userId={userId}&userLevel=${loginUser.userLevel}"
					target="ajax" rel="roleUserList"  id="delRoleUserLink"
					altKey="common.msg.delete.confirm"
					messageKey="common.icon.delete" dwzClass="delete" /></li>
		</ul>
	</div>
	<div  id="roleUserList">
	<table id="roleUserTable" class="table" width="99%"  layoutH="310" >
		<thead>
			<tr>
				<%-- <th align="center"><bmtag:message messageKey="common.label.index" /></th> --%>
				<th align="center" ><bmtag:message messageKey="账户" /></th>
				<th align="center" ><bmtag:message messageKey="账户归属" /></th>
				<th align="center"><bmtag:message messageKey="账户级别"/></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList}" var="user" varStatus="status">
				<tr target="userId" rel="${user.userId }" align="center">
					<%-- <td align="center">${ status.index + 1}</td> --%>
					<td align="center">${user.userId }</td>
					<td align="center">${user.userAccount }</td>
					<td align="center">
					<c:choose>
						<c:when test="${user.userLevel == 1}">超级管理员</c:when>
						<c:when test="${user.userLevel == 2}">普通管理员</c:when>
						<c:when test="${user.userLevel == 3}">纯商家用户</c:when>
						<c:when test="${user.userLevel == 5}">个体商家用户</c:when>
					</c:choose>
				</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
	<div class="panelBar">
	</div>
</div>

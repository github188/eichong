<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
function dialogAjaxDone(json){
var forwardurl =json.forwardUrl;
forwardurl = forwardurl;
if (json.statusCode == 200) {
      window.location.href="<%=request.getContextPath()%>/admin/index.do";
}
if (json.statusCode == 300) {
      	alertMsg.error(json.message);
}

  
 
}
</script>
<div class="pageContent">
	
	<form method="post" action="${webroot }/toSimplelogin.do" class="pageForm" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>用户名：</label>
				<input  type="text" name="username" size="32" class="required"/>
			</div>
			<div class="unit">
				<label>密码</label>
				<input  type="password" name="passwd" onpaste="return false" size="32" class="required"/>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">登录</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div></li>
			</ul>
		</div>
	</form>
	
</div>


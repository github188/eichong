<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 <%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>爱充网后台管理系统</title>
<style>
body {
	background: #727272;
	font-family:"微软雅黑"；
	margin: 0;
	padding: 0;
}
.DivBox {
	width: 100%;
	height: 340px;
	position: absolute;
	left: 0%;
	top: 50%;
	margin: -170px 0 0 0;
	background: url(${webroot }/res/dwz/themes/default/images/divbg.png) repeat-x;
}
.DivMain {
	margin: 0 auto;
	width: 900px;
	height: 340px;
}
.DivMainLeft {
	height: 340px;
	width: 420px;
	background: url(${webroot }/res/dwz/themes/default/images/imglogo.png) left center no-repeat;
	float: left;
}
.DivMainRight {
	height: 340px;
	width: 460px;
	background: url(${webroot }/res/dwz/themes/default/images/mainbgline.png) 1px center no-repeat;
	float: left;
}
.denglu,.denglu dt,.denglu dd{ list-style:none; margin:0; padding:0; font-size:14px; color:#FFF; height:30px; line-height:30px;}
.denglu{ margin-top:70px; margin-left:30px;}
.denglu dd{ margin-top:10px;}
.denglu dt{ margin-top:20px;}
.denglu dt.w460,.denglu dd.w460{ width:460px;}
.denglu dt span,.denglu dd span{ display:block; width:80px; text-align:right; float:left; margin-right:10px; font-weight:bold;}
.denglu dd input{ vertical-align:middle; width:260px; height:24px; float:left;}
.denglu dd input.w120{width:120px;}
a.yanzhengma{ display:block; margin:0; padding:0; width:80px; float:left; margin-left:10px;}
a.yanzhengma img{ border:0px;}
.denglu dd span.huoqu a{ margin-left:10px; display:block; width:100px; text-align:center; color:#333; background:#dfdfdf; text-decoration:none;}
.denglu dd span.huoqu a:hover{ background:#4a4a4a; color:#FFF; }
.denglu dt input{ width:80px; height:36px; margin-right:10px; font-size:18px; line-height:32px;}
.denglu h3{ display:block; height:20px; line-height:20px; margin-top:-30px; margin-left:90px; text-align:left; color:#F00;}
.denglu h4{ list-style:none; margin:0; padding:0; font-weight:normal; font-size:14px; margin-top:20px; padding-left:90px;}
.denglu h4 a{ color:#FFF; text-decoration:none;}

</style>


<script src="<%=basePath%>/static/js/jquery.min.js" type="text/javascript"></script>
<script src="<%=basePath%>/static/lib/validator/jquery.validator.js"></script>
<script src="<%=basePath%>/static/lib/jquery.MD5/jquery.md5.js"></script>

<script type="text/javascript">

  $(function(){
	   reloadCode();
	   $("#loginUser").click(function(){//保存用户
		   var form = document.forms[0];
			var v=$.md5($("#password").val());
			var v=$.md5(v+$("#username").val())+generateMixed(1);
			$("#password1").val(v);
		    $("#password").attr("disabled", "true");
			form.action = "${pageContext.request.contextPath}/tologin.do";
			form.submit();
		   });
	});
  var chars = ['0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
  function generateMixed(n) {
       var res = "";
       for(var i = 0; i < n ; i ++) {
           var id = Math.ceil(Math.random()*35);
           res += chars[id];
       }
       return res;
  }
  	function reloadCode(){
  		
  		var time = new Date().getTime();
  		var img = "<%=basePath%>/web/user/getValidCode.do?t="+time;
  		//alert(img);
  		$("#codeImg").attr("src",img);
  	}
</script>
</head>

<body>
	<form method="post" method="post">
	<div class="DivBox">
	  <div class="DivMain">
	    <div class="DivMainLeft"> </div>
	    <div class="DivMainRight">
	      <dl class="denglu">
	      	<h3><font color="red">${erMessage}</font></h3>
	        <dd class="w460">
	        <span>用户名：</span>
	        	<input type="text" name="username" value="${username}" maxlength="32" id="username"/>
	        </dd>
	        <dd class="w460" ><span>密码：</span>
	        	<input type="password" name="password" minlength="6" maxlength="20" onpaste="return false" id="password"/>
			<input type="hidden" name="passwd" id="password1"/>
	        </dd>
	        <dd class="w460" ><span>验证码：</span>
	        	<input  class="w120" name="code" maxlength="4" type="text" />
	        	&nbsp;<%-- <%=basePath%>/web/user/getValidCode.do --%>
	        	<img id="codeImg" src="" onclick="reloadCode()"
	        		style="cursor: pointer;" title="看不清，重换一张" width="90" height="30"></img>
	        
	        </dd>
	        <dt class="w460"><span>&nbsp;&nbsp;</span>
		        <input id="loginUser" type="submit" value="登录" />
		        <input type="reset" value="重置" />
	        </dt>
	        <h4><a href="reset.do" >忘记密码</a></h4>
	      </dl>
	    </div>
	  </div>
	</div>
	</form>
</body>
</html>
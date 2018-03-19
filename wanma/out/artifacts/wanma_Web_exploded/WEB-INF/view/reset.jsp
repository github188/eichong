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
<title>爱充后台管理密码找回</title>
<style>
body{
	font-family:"微软雅黑";
margin:0;
padding:0;
width:100%;
height:100%;
background-color:#CCC;
}
.divBox{
position:absolute;
top:50%;
left:50%;
margin-top:-280px;
margin-left:-500px;
width:1000px;
height:560px;
background-color:#fff;
}
.divBox h1{ margin:0; padding:0; padding-left:20px; font-size:24px; font-weight:normal; height:60px; line-height:60px; background-color:#ff8800; color:#fff; }
.divBox h2{ margin:0 auto; margin-top:50px; padding:0; width:912px; height:50px; font-weight:normal; font-size:18px; line-height:50px;}

.bz_a span,.bz_b span,.bz_c span{ display:block; width:304px; float:left; text-align:center;}
.bz_a{ background:url(/wanma/static/images/img/zb1.png) no-repeat 0px 0px;}
.bz_b{ background:url(/wanma/static/images/img/zb1.png) no-repeat 0px -70px;}
.bz_c{ background:url(/wanma/static/images/img/zb1.png) no-repeat 0px -140px;}
.divBox ul,.divBox ul li{ margin:0; padding:0; list-style:none;}
.divBox ul{ width:400px; margin:0 auto; margin-top:50px;}
.divBox ul li{ height:30px; line-height:30px; margin-bottom:30px;}
.divBox ul li span{ display:block; width:100px; height:30px; float:left;  text-align:center; margin-right:20px; text-align:right;}
.divBox ul li input{ float:left; list-style:none; width:250px; height:24px;}
.divBox ul li input.submit{ width:100px; height:30px; }
.divBox ul li b{ float:left;}
.divBox ul li u{ display:block; height:20px; text-align:center; font-size:14px; line-height:20px; width:80px; background:#CCC; float:left; margin-top:5px;margin-left:10px; text-decoration:none;}
.divBox ul li u a{ color:#000; text-decoration:none;}
.divBox p{ text-align:center; margin-top:100px;}
.divBox p a{ color:#ff8800;}
</style>

</head>

<body>
<div class="divBox">
    <h1 class="MainTitle">爱充管理系统密码找回</h1>
    
    <div id="so">
    <h2 class="bz_a">
      <span>第一步：输入用户名</span>
      <span>第二步：账号验证、重置密码</span>
      <span>第三步：重置成功</span>
    </h2>
    <ul>
      <li><span>输入用户名</span><input id="userAccount" name="userAccount" type="text" maxlength="32"></li>
      <li><span>&nbsp;&nbsp;</span><input class="submit" type="button" id="userAccountSubmit" value="确定" /></li>
    </ul>
   </div>
    <!--第二步 -->
     <div id="st" style="display:none">
  <h2 class="bz_b">
      <span>第一步：输入用户名</span>
      <span>第二步：账号验证、重置密码</span>
      <span>第三步：重置成功</span>
    </h2>
  <ul>
	<form class="validForm" name="resetForm" id="resetForm">
	<input type="hidden" id="userAccountHidden" value="" />
	<input type="hidden" id="userAccountDiv" name="userAccount" value="" />
	<input type="hidden" id="userLevelDiv" value="" />
    <li><span>手机号码：</span><input class="w280denglu" id="resetPhone" name="phone" type="hidden" value="" readonly><b id="resetPhoneDiv">153****9990</b><u><a class="huoqu" href="javascript://" id="btn-message2">获取验证码</a></u></li>
    <li><span>输入验证码</span>
    <input class="w100denglu" name="msgCode" type="text"></li>
    <li><span>设置密码</span>
    <input id="userPassword" name="userPassword" type="password" minlength="6" maxlength="20"></li>
    <li><span>确认密码</span>
    <input id="rePassword" name="rePassword" type="password" minlength="6" maxlength="20"></li>
    <li><span>&nbsp;&nbsp;</span><input class="submit" type="button" id="resetSubmit"value="提交" /></li>
   </form>
  </ul>
</div>
   <div id="sc" style="display:none">
  <h2 class="bz_c">
      <span>第一步：输入用户名</span>
      <span>第二步：账号验证、重置密码</span>
      <span>第三步：重置成功</span>
    </h2>
  <p><img src="/wanma/static/images/img/ok.png" width="58" height="57" /> <br />
重置成功，<a href="login.do">立即登录</a>。</p>
 </div>
  </div>

</body>
<script>
var basepath = "<%=basePath%>";
</script>
<script src="static/js/jquery.min.js" type="text/javascript"></script>
<script src="static/lib/jquery.MD5/jquery.md5.js" type="text/javascript"></script>
<script src="static/js/common/common.js" type="text/javascript"></script>
<script src="static/js/index/reset.js" type="text/javascript"></script>
<script src="static/js/validate.js" type="text/javascript"></script>
</html>

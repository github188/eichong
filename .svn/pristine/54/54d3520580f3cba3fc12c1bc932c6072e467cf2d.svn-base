<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
	String ipPort = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort();
%>
<!-- start header -->
<div class="NavWarp">
  <div class="Nav">
    <div class="Logo"></div>
    <div class="NavMu">
      <ul class="70w">
        <li id="index" 				  class="ab abcd"><a href="<%=path%>/index.html">首页</a></li>
        <li id="packageCase" 		  class="abcd"><a href="<%=path%>/case.html">方案案例</a></li>
        <li id="electricDistribution" class="abcd"><a href="<%=path%>/electric/search.do">电桩分布</a></li>
        <li id="huodongMenu"		  class="abcd"><a href="<%=path%>/activity.html">活动中心</a></li>
        <li id="hangyeMenu"   	  class="abcd"><a href="<%=path%>/company.html">行业动态</a></li>
      </ul>
      <!--登陆&注册-->
      <ul class="Login 30w offline" style="display:none;">
        <li class="abc" id="login"><a>登录</a></li>
        <li class="abc" id="regist"><a>注册</a></li>
      </ul>      
      <!--登陆后出现个人中心-->
      <ul class="online"   style="display:none;">
        <li class="abcd"  id="userCenter"><a href="<%=path%>/mycenter.do">个人中心</a></li>
      </ul>      
      <ul class="HasLogged online" style="display:none;">
        <li class="Nickname" id="userAccount"></li>
        <li class="Logout"><a href="#" class="logoutAll">注销</a></li>
      </ul>
     
    </div>
  </div>
</div><div style="clear:both;"></div>
<!-- 导航结束 -->

<div class="LogBox" id="loginBox" style="display: none;">
<form class="validForm" name="loginForm" id="loginForm">
    <dl class="LogMain">
      <dt>
      <a class="Close" id="closeLogin"></a>用户登录
      <!-- <a class="shangjiaLink" href="/wanma/">企业商家入口</a> -->
      </dt>
      <dd class="ddTop"><span class="W180Left">登录账号</span>
      <span class="W280Left"><input class="w280denglu" id="loginPhone"  name="userAccount" type="text" value="" placeholder="请输入手机号码"></span></dd>
      <dd><span class="W180Left">输入密码:</span><span class="W280Left"><input class="w280denglu" name="userPassword" type="password" value=""></span></dd>
      <dd><span class="W180Left">输入图片验证码:</span><span class="W100Left"><input class="w100denglu"  name="code" type="text"></span>
	  <a class="imageCodeHuoqu" id="validateCodeLogin">获取验证码</a>
	  <a style="font-size:14px;" class="huan changeCode" data-id="validateCodeLogin">换一张</a>	
	  </dd>
	  <dd><input name="rememberMe" type="checkbox"><span style="font-size:12px;">记住密码</span></dd> 
      <dd  class="ddfooter"><a class="tijiao" id="loginSubmit">登录</a></dd>
      <dd><span class="wangji"><a style="margin-left:10px;" id="reset">忘记密码？</a></span>
      <span class="lijizhuce"><a style="margin-left:100px;" id="regist2">没有账号？现在去注册</a></span></dd>   
    </dl><div class="clear"> </div>
</form>    
</div>
<div  class="LogBox" id="registBox" style="display: none;">
<form id="registForm">
	<input class="D200"  name="usinRegistetype" type="hidden" value="2">
    <dl class="LogMain">
      <dt><a class="Close" id="closeRegist"></a>用户注册</dt>
      <dd class="ddTop"><span class="W180Left">设置昵称</span><span class="W280Left"><input class="w280denglu" id="registName" name="normName" type="text"></span></dd>
      <dd><span class="W180Left">设置密码</span><span class="W280Left"><input class="w280denglu" id="registPassword" name="userPassword" type="password"></span></dd>
      <dd><span class="W180Left">再次确认密码</span><span class="W280Left"><input class="w280denglu" id="registRePassword" name="rePassword" type="password"></span></dd>
      <dd><span class="W180Left">输入图片验证码</span><span class="W100Left"><input id="registYz"  class="w100denglu" name="code" type="text" /></span>
		<a  class="imageCodeHuoqu" id="validateCodeRegist">获取验证码</a>
		<a style="font-size:14px;" class="changeCode huan" data-id="validateCodeRegist">换一张</a>
		</dd>
      <dd><span class="W180Left">输入手机号码</span><span class="W280Left"><input class="w280denglu" id="registPhone" name="userAccount" type="text"></span></dd>
      <dd><span class="W180Left">输入手机验证码</span><span class="W100Left"><input id="registPhoneYz"  class="w100denglu" name="msgCode" type="text"></span>
	  <a  class="huoqu" id="btn-message">获取验证码</a></dd>
      <dd class="ddfooter"><a class="tijiao" id="registSubmit">提交注册</a></dd>   
    </dl>
</form>    
</div>
<div class="LogBox" id="resetBox" style="display: none;">
<form class="validForm" name="resetForm" id="resetForm">
	<input type="hidden" id="userAccountHidden" value="${user.userAccount}" />	
    <dl class="LogMain">
      <dt><a class="Close" id="closeReset"></a><t id="resetTitle">密码找回</t></dt>
      <dd class="ddTop">
	      <span class="W180Left">输入手机号码</span>
	      <span class="W280Left"><input class="w280denglu" id="resetPhone" name="userAccount" type="text" value=""></span>
	  </dd>
      <dd><span class="W180Left">输入手机验证码</span>
      	  <span class="W100Left"><input class="w100denglu" name="msgCode" type="text"></span>
		<a class="huoqu"  id="btn-message2">获取验证码</a>
	  </dd>
      <dd><span class="W180Left" >设置新密码</span><span class="W280Left"><input class="w280denglu" name="userPassword" type="password" value=""></span></dd>
      <dd><span class="W180Left" >再次确认新密码</span><span class="W280Left"><input class="w280denglu" name="rePassword" type="password" value=""></span></dd>
      <dd class="ddfooter"><a class="tijiao" id="resetSubmit">提交</a></dd>   
    </dl>
</form>    
</div>
		
<div class="information label " ></div> 
<script type="text/javascript">
	var basepath="<%=basePath%>";
	var frontVersion='${frontVersion}';
	var this_tag = "";
</script>
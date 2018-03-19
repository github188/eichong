
<%
	/* *
	 *功能：支付宝手机网站支付接口调试入口页面
	 *版本：3.3
	 *日期：2012-08-17
	 *说明：
	 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
	 */
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>支付宝手机网站支付接口</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
* {
	margin: 0;
	padding: 0;
}

ul, ol {
	list-style: none;
}

body {
	font-family: "Helvetica Neue", Helvetica, Arial, "Lucida Grande",
		sans-serif;
}

.hidden {
	display: none;
}

.new-btn-login-sp {
	padding: 1px;
	display: inline-block;
	th: 75%;
}

.new-btn-login {
	background-color: #02aaf1;
	color: #FFFFFF;
	font-weight: bold;
	border: none;
	th: 100%;
	height: 30px;
	border-radius: 5px;
	font-size: 16px;
}

#main {
	th: 100%;
	margin: 0 auto;
	font-size: 14px;
}

.red-star {
	color: #f00;
	th: 10px;
	display: inline-block;
}

.null-star {
	color: #fff;
}

.content {
	margin-top: 5px;
}

.content dt {
	th: 100px;
	display: inline-block;
	float: left;
	margin-left: 20px;
	color: #666;
	font-size: 13px;
	margin-top: 8px;
}

.content dd {
	margin-left: 120px;
	margin-bottom: 5px;
}

.content dd input {
	th: 85%;
	height: 28px;
	border: 0;
	-webkit-border-radius: 0;
	-webkit-appearance: none;
}

#foot {
	margin-top: 10px;
	position: absolute;
	bottom: 15px;
	th: 100%;
}

.foot-ul {
	th: 100%;
}

.foot-ul li {
	th: 100%;
	text-align: center;
	color: #666;
}

.note-help {
	color: #999999;
	font-size: 12px;
	line-height: 130%;
	margin-top: 5px;
	th: 100%;
	display: block;
}

#btn-dd {
	margin: 20px;
	text-align: center;
}

.foot-ul {
	th: 100%;
}

.one_line {
	display: block;
	height: 1px;
	border: 0;
	border-top: 1px solid #eeeeee;
	th: 100%;
	margin-left: 20px;
}

.am-header {
	display: -webkit-box;
	display: -ms-flexbox;
	display: box;
	th: 100%;
	position: relative;
	padding: 7px 0;
	-webkit-box-sizing: border-box;
	-ms-box-sizing: border-box;
	box-sizing: border-box;
	background: #1D222D;
	height: 50px;
	text-align: center;
	-webkit-box-pack: center;
	-ms-flex-pack: center;
	box-pack: center;
	-webkit-box-align: center;
	-ms-flex-align: center;
	box-align: center;
}

.am-header h1 {
	-webkit-box-flex: 1;
	-ms-flex: 1;
	box-flex: 1;
	line-height: 18px;
	text-align: center;
	font-size: 18px;
	font-weight: 300;
	color: #fff;
}
</style>
</head>
<body text=#000000 bgColor="#ffffff" leftMargin=0 topMargin=4>
	<header class="am-header">
		<h1>支付宝手机网站支付接口快速通道</h1>
	</header>
	<div id="main">
		<form name=alipayment action="alipay/deposit" method=post>
			<div id="body" style="clear: left">
				<dt>付款金额 ：</dt>
				<dd>
					<input name="total_fee" id="total_fee" value="0.01"/>
				</dd>
				<hr class="one_line">
				<dt>用户ID：</dt>
				<dd>
					<input name="userId" id="body" value="6" />
				</dd>
				<hr class="one_line">
				<dt></dt>
				<dd id="btn-dd">
					<span class="new-btn-login-sp">
						<button class="new-btn-login" type="submit"
							style="text-align: center;">确 认</button>
					</span> <span class="note-help">如果您点击“确认”按钮，即表示您同意该次的执行操作。</span>
				</dd>
				</dl>
			</div>
		</form>
		<div id="foot">
			<ul class="foot-ul">
				<li>吴彬川版权所有 违者必究~~~</li>
			</ul>
		</div>
	</div>
</body>
</html>
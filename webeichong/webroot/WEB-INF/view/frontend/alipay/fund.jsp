<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>www.eichong.com</title>
<meta content="www.eichong.com" name="keywords" />
<meta content="www.eichong.com" name="description" />
</head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<script src="<%=path%>/static/js/webjs/jquery-1.7.1.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function(){
    $("#amount").focus();
    $("#doSubmit").click(function(){
        form1.submit();
    });
});
</script>
<body>
 
    <form id="form1" action= "<%=basePath%>/alipay/deposit.do" method="post" target="_blank">  
                <table cellpadding="10">  
                    <tr>  
                        <td>充值测试</td>  
                        <td class="balance" id="userBalance"></td>  
                    </tr>  
                    <tr>  
                        <td><i class="zfb"></i></td>  
                        <td style="padding-bottom: 0px;">亲爱的<span  
                            class="suppliment_user" id="suppliment_user"></span>,您可以使用支付宝充值积善分，请填写以下信息  
                        </td>  
                    </tr>  
                    <tr>  
                        <td></td>  
                        <td>
                                金额<input type="text" name="amount" id="amount">
                        </td>  
                    </tr>  
   
                    <tr>  
                        <td></td>  
                        <td><a href="javascript:void(0);" id="doSubmit" class="blank_btn">确认</a></td>  
                    </tr>  
                </table>  
    </form>  
</body>
</html>
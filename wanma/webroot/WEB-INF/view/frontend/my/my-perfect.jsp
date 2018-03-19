<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>完善个人资料</title>

    <%--common css--%>
    <jsp:include page="../common/header-css.jsp"/>

    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/register.css">

    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/profile.css">
</head>
<body>

<!--//header -->
<jsp:include page="../common/header.jsp"/>

<div class="content">
    <div class="content-head">
        <a>首页 > </a>
        <a>注册 > </a>
        <a>完善个人资料</a>
    </div>
    <div class="cm-con-bm clearfix">
        <form method="post" id="update-info-form" enctype="multipart/form-data">
            <input name="pkUserinfo" type="hidden" value="${userPk}"/>
            <div class="pic-left">
                <div class="portrait tm">
                    <div id="loadimg" style="position: relative;display: inline-block;width: 172px;height: 172px;margin: 30px auto 0 !important;">
                    </div>
                    <div class="file_btn tm">
                        <span class="f18 tx">上传头像</span>
                        <input class="file size" type="file" id="file" name="titleMultiFile"/>
                        <input name="usinHeadimage" data-rule="length[0~500];" type="hidden"/>
                        <span style="left: 200px;" class="error f12 fc-orange msg-box" for="usinHeadimage"></span>
                    </div>
                </div>
                <img src="<%=request.getContextPath()%>/static/images/img/login-left.png"/>
            </div>
            <div class="content-right">
                <div class="login-detail">
                    <div class="mt10 clearfix">
                        <p class="fl tm title">真实姓名</p>

                        <p class="wrap fl">
                            <input placeholder="输入您的真实姓名" name="usinFacticityname" data-rule="真实姓名:required;length[1~20];" />
                            <span class="error f12 fc-orange msg-box" for="usinFacticityname"></span>
                        </p>
                    </div>

                    <div class="mt10 clearfix">
                        <p class="fl tm title">性 别</p>

                        <div class="fl sex">
                            <input class="radio inline" type="radio" data-rule="checked" checked="checked" name="usinSex" value="0" id="man">
                            <label for="man"><span class="inline fc-gray">男</span></label>
                            <input class="radio inline ml10" type="radio" name="usinSex" value="1" id="woman">
                            <label for="woman"><span class="radio inline fc-gray">女</span></label>
                        </div>
                    </div>

                    <div class="mt10 clearfix">
                        <p class="fl tm title">出生日期</p>
                        <p class="wrap fl">
                            <input onClick="WdatePicker()" readonly="readonly" placeholder="输入您的出生日期" data-rule="日期:date;" name="usinBirthdate"/>
                            <span class="error f12 fc-orange msg-box" for="usinBirthdate"></span>
                        </p>
                    </div>

                    <div class="mt10 clearfix">
                        <p class="fl tm title">会员卡号</p>

                        <p class="wrap fl">
                            <input placeholder="输入您的会员卡号" name="usinMembercode" data-rule="length[1~20];"/>
                            <span class="error f12 fc-orange msg-box" for="usinMembercode"></span>
                        </p>
                    </div>

                    <div class="mt10 clearfix">
                        <p class="fl tm title">IC卡号</p>

                        <p class="wrap fl">
                            <input placeholder="输入您的IC卡号" name="usinIccode" data-rule="length[1~20];"/>
                            <span class="error f12 fc-orange msg-box" for="usinIccode"></span>
                        </p>
                    </div>

                    <div class="mt10 clearfix">
                        <p class="fl tm title">手机号码</p>

                        <p class="wrap fl">
                            <input id="phone" readonly="readonly"/>
                            <span class="error f12 fc-orange msg-box"></span>
                        </p>
                    </div>

                    <div class="mt10 clearfix">
                        <p class="fl tm title">电子邮箱</p>

                        <p class="wrap fl">
                            <input placeholder="输入您的电子邮箱"data-rule="email" name="usinEmail"/>
                            <span class="error f12 fc-orange msg-box" for="usinEmail"></span>
                        </p>
                    </div>

                    <div class="mt10 clearfix">
                        <p class="fl tm title">驾驶证号</p>

                        <p class="wrap fl">
                            <input placeholder="输入您的驾驶证号" name="usinPlatenumber" data-rule="length[1~20];"/>
                            <span class="error f12 fc-orange msg-box" for="usinPlatenumber"></span>
                        </p>
                    </div>

                    <div class="mt10 clearfix">
                        <p class="fl tm title">汽车品牌</p>
                        <p class="wrap fl">
                            <span class="select-arrow"></span>
                            <select id="data-list" class="select fc-gray"></select>
                            <span class="error f12 fc-orange"></span>
                        </p>
                    </div>

                    <div class="mt10 clearfix">
                        <p class="fl tm title">汽车型号</p>
                        <p class="wrap fl">
                            <span class="select-arrow"></span>
                            <select name="usinAcura" id="car-model" class="select fc-gray"></select>
                            <span class="error f12 fc-orange"></span>
                        </p>
                    </div>

                    <p class="btn-register wrap">
                        <a id="submitBtn" class="zc f18">注册完成</a>
                    </p>
                </div>
            </div>
        </form>
    </div>
</div>

<jsp:include page="../common/footer.jsp"/>
<jsp:include page="../common/validator.jsp"/>
<jsp:include page="../common/my97.jsp"/>

<script type="text/html" id="data-list-tmpl">
    <!--[for(i=0;i<list.length;i++){]-->
            <option value="<!--[=list[i].pkParaconfig]-->"><!--[=list[i].paraName]--></option>
    <!--[}]-->
</script>

<script type="text/html" id="car-model-tmpl">
    <!--[for(i=0;i<list.length;i++){]-->
    <option value="<!--[=list[i].pkParaconfig]-->"><!--[=list[i].paraName]--></option>
    <!--[}]-->
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/lib/ajaxupload/ajaxupload.3.5.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/mycenter/my-perfect.js"></script>
</body>
</html>
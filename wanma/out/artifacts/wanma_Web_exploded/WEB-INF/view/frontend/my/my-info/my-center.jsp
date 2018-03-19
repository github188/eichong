<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>爱充网-个人中心</title>
    <%--custom css--%>
    <jsp:include page="../../common/header-css.jsp"/>
    <link rel="stylesheet" href="<%=basePath%>/static/css/personalcenter.css">
    <link rel="stylesheet" href="<%=basePath%>/static/css/member.css">
</head>
<body>

<!--//header -->
<jsp:include page="../../common/header.jsp"/>

<!--//section-->
<div class="content">
    <div class="content-head"><a>首页 > </a> <a>个人中心主页</a></div>
    <div class="cont_box">

		<!--//menu -->
		<jsp:include page="../my-menu.jsp"/>

        <!--右侧-->
        <div class="mem_right fl" id="detail-data">
            <div class="memLeft fl">
                <div class="mem_head">
                <c:choose>
                    <c:when test="${fn:containsIgnoreCase(webUser.userImage,'http://')}">
                        <img src="${webUser.userImage}" alt="" style="width: 100%;">
                    </c:when>
                    <c:otherwise>
                        <img src="<%=basePath%>${webUser.userImage}" alt="" style="width: 100%;">
                    </c:otherwise>
                </c:choose>
                </div>
                <p class="mem_name">${webUser.userRealName}</p>
                <p><span class="yellow">${webUser.userType=="2" ?"商户用户":"普通用户"}</span><span class="fz">（身份）</span></p>
                <c:if test="${webUser.userType=='1'}">
                    <p class="update" id="center_update"><a href="javascript:void(0);">一键升为商家</a></p>
                </c:if>

            </div>
            <div class="memRight fl">
                <div class="dash">
                    <p>
                        <span class="p_l">真实姓名：</span>
                        <span class="p_r">${webUser.userRealName}</span>
                    </p>
                    <p>
                        <span class="p_l">性别：</span>
                        <span class="p_r">${webUser.userSex=="1" ?"女":"男"}</span>
                    </p>
                    <p>
                        <span class="p_l">出生日期：</span>
                        <span class="p_r">${webUser.userBrithy}</span>
                    </p>
                    <p>
                        <span class="p_l">I C卡号：</span>
                        <span class="p_r">${webUser.userIcNo}</span>
                    </p>
                    <p>
                        <span class="p_l">手机号码：</span>
                        <span class="p_r">${webUser.userTel}</span>
                    </p>
                    <p>
                        <span class="p_l">电子邮箱：</span>
                        <span class="p_r">${webUser.userMail}</span>
                    </p>
                </div>
                <div class="mt20">
                    <p>
                        <span class="p_l">驾驶证号：</span>
                        <span class="p_r">${webUser.userDriveNo}</span>
                    </p>
                    <p>
                        <span class="p_l">品牌汽车：</span>
                        <span class="p_r">${webUser.userCarType}</span>
                    </p>
                    <p>
                        <span class="p_l">汽车型号：</span>
                        <span class="p_r">${webUser.userCarType}</span>
                    </p>
                </div>
            </div>
        </div>

    </div>

</div>

<!--//footer-->
<jsp:include page="../../common/footer.jsp"/>

<%--validator plugin--%>
<jsp:include page="../../common/validator.jsp"/>

<%--custom js--%>
<script type="text/javascript" src="<%=basePath%>/static/js/personalcenter.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/mycenter/my-center.js"></script>
</body>
</html>
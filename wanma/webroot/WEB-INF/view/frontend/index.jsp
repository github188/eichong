<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>爱充网</title>
    <%--common css--%>
    <jsp:include page="common/header-css.jsp"/>
    <link href="<%=request.getContextPath()%>/static/css/index.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/static/lib/sliderbox/css/jquery.slideBox.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<!--//header -->
<jsp:include page="common/header.jsp"/>

<div id="banner" class="slideBox">
    <ul class="items" id="data-list">
        <li><img src="<%=request.getContextPath()%>/static/images/img/toppic.jpg"></li>
        <li><img src="<%=request.getContextPath()%>/static/images/img/toppic2.jpg"></li>
        <li><img src="<%=request.getContextPath()%>/static/images/img/toppic3.jpg"></li>
    </ul>
</div>
<div class="content">
    <div class="list-tool">
        <ul>
            <li>
                <img src="<%=request.getContextPath()%>/static/images/img/one1.png" style="width: 104px;">

                <p class="font-big">新能源商城</p>

                <p class="font-small">电动汽车·电动自行车·充电设施</p>
            </li>
            <li><img src="<%=request.getContextPath()%>/static/images/img/two.png"/>

                <p class="font-big">智能电桩（安装）</p>

                <p class="font-small">私人·企业·大型充充电点</p>
            </li>
            <li><img src="<%=request.getContextPath()%>/static/images/img/three.png"/>

                <p class="font-big">智能电桩（运营）</p>

                <p class="font-small">电动汽车·电动自行车·充电设施</p>
            </li>
            <li><img src="<%=request.getContextPath()%>/static/images/img/four.png"/>

                <p class="font-big">便民充电</p>

                <p class="font-small">充值·查询·预约 导航·充电</p>
            </li>
        </ul>
    </div>
    <div class="weixin">
        <img src="<%=request.getContextPath()%>/static/images/img/weixin.png"/>
    </div>
    <div class="content-foot">
        <span class="leftBtn"></span>
        <ul style="padding: 0px;">
            <li class="con-foot-li "><img src="<%=request.getContextPath()%>/static/images/img/foot1.png"></li>
            <li class="con-foot-li"><img src="<%=request.getContextPath()%>/static/images/img/foot2.png"></li>
            <li class="con-foot-li"><img src="<%=request.getContextPath()%>/static/images/img/foot3.png"></li>
            <li class="con-foot-li"><img src="<%=request.getContextPath()%>/static/images/img/foot4.png"></li>
            <li class="con-foot-li"><img src="<%=request.getContextPath()%>/static/images/img/foot5.png"></li>
        </ul>
        <span class="rightBtn"></span>
    </div>
</div>

<!--//footer-->
<jsp:include page="common/footer.jsp"/>
<script type="text/html" id="data-list-tmpl">
<!--[for(i=0;i<list.length;i++){]-->
<li><a href="" title=""><img src="<!--[=$absImg(list[i].)]-->"></a></li>
<!--[}]-->
</script>
<script src="<%=request.getContextPath()%>/static/js/index.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/static/lib/sliderbox/js/jquery.slideBox.js" type="text/javascript"></script>

</body>
</html>

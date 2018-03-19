<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>选择城市</title>
    <%--common css--%>
    <jsp:include page="common/header-css.jsp"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/selectCity.css">

</head>
<body>
<!--//header -->
<jsp:include page="common/header.jsp"/>

<div class="content">
    <div class="padding">
        <ul>
            <li class="clearfix">
                <span class="con-l f14 fc-gray">猜你想要进：</span>
                <span class="con-r f18 fc-gray">杭州站 >></span>
            </li>
            <li class="clearfix">
                <span class="con-l f14 fc-orange">热门城市：</span>
                <span class="con-r f14 fc-gray">
                    <a href="#">上海</a>
                </span>
            </li>
            <li class="clearfix">
                <span class="con-l f14 fc-orange">按省份选择：</span>
					<span class="con-r f14  fc-gray">
						<select name="" id="province"></select>
						<select class="ml10" name="" id="city"></select>
					</span>
            </li>
            <li class="clearfix">
                <span class="con-l f14 fc-orange">搜索城市：</span>
					<span class="con-r f14  fc-gray">
						<input class="search" id="searchKey" type="text" placeholder="请输入中文/拼音">
					</span>
            </li>
        </ul>
        <p class="fc-orange f14">按首字母查找:</p>
        <ul class="letter" id="data-list"></ul>
    </div>

</div>
<div class="clear"></div>
<jsp:include page="common/footer.jsp"/>

<script id="province-tmpl" type="text/html">
    <option value="">请选择省份</option>
    <!--[for(i=0;i<list.length;i++){]-->
        <option value="<!--[=list[i].provinceCode]-->"><!--[=list[i].provinceName]--></option>
    <!--[}]-->
</script>
<script id="city-tmpl" type="text/html">
    <option value="">请选择城市</option>
    <!--[for(i=0;i<list.length;i++){]-->
    <option value="<!--[=list[i].cityCode]-->"><!--[=list[i].cityName]--></option>
    <!--[}]-->
</script>
<%--省市列表模板--%>
<script type="text/html" id="data-list-tmpl">
    <!--[for(var key in list){]-->
    <li class="clearfix f14 fc-gray">
        <!--[for(var letter in list[key]){]-->
        <span class="con-l f16 fc-orange"><!--[=letter]--></span>
            <!--[for(i=0;i<list[key][letter].length;i++){]-->
            <a href="javascript:void(0);"><!--[=list[key][letter][i].cityName]--></a>
            <!--[}]-->
        <!--[}]-->
    </li>
    <!--[}]-->
</script>
<script src="<%=request.getContextPath()%>/static/js/other/choose-city.js" type="text/javascript"></script>
</body>
</html>
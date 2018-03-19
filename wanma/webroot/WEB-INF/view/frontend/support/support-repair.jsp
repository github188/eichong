<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>设备维修</title>
    <%--custom css--%>
    <jsp:include page="../common/header-css.jsp"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/static/css/opinion_s.css">

</head>
<body>
<jsp:include page="../common/header.jsp"/>

<div class="content">
    <div class="content-head">
        <a>首页 > </a>
        <a>服务支持 > </a>
        <a>设备报修</a>
    </div>
    <div class="btn-wrap" id="data-list">
        <button>电桩故障</button>
        <button>充电异常</button>
        <button>结构损坏</button>
        <button>键盘失灵</button>
        <button>显示模糊</button>
        <button class="current">其他</button>
    </div>
    <textarea class="area mt20" name="eqreContent" cols="30" rows="10"
              placeholder="请输入您的反馈意见，我们将为您提供更好的服务（500字以内）..."></textarea>

    <p class="tm mt20">
        <button class="submit-btn">提交</button>
    </p>

</div>

<jsp:include page="../common/footer.jsp"/>
<script type="text/html" id="data-list-tmpl">
    <!--[for(i=0;i<list.length;i++){]-->
            <!--[if(i==0){]-->
            <button data-id="<!--[=list[i].pkParaconfig]-->" data-type="<!--[=list[i].paraType]-->" class="current"><!--[=list[i].paraName]--></button>
            <!--[}else{]-->
            <button data-id="<!--[=list[i].pkParaconfig]-->" data-type="<!--[=list[i].paraType]-->"><!--[=list[i].paraName]--></button>
            <!--[}]-->
    <!--[}]-->
</script>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/suggest/repair.js"></script>
</body>
</html>
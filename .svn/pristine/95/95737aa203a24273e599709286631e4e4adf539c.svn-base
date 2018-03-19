<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="page" id="page-dom"></div>
<%--
    !required parameter
    totalPage //总页数
    cpage //当前页
    pageStart //页码循环开始
    pageEnd //页码循环结束
--%>
<script type="text/html" id="page-tmpl">
  <!--[if(totalPage!=0){]-->
    <!--[if(pageEnd < totalPage || cPage < totalPage){]-->
        <span class="sp next">下一页</span>
    <!--[}]-->
  <ul class="clearfix ul-pages">

    <!--[if(cPage>4&&cPage<=totalPage){]-->
        <li data-page-num="<!--[=i]-->">1</li>
        <li>...</li>
    <!--[}else{]-->
          <!--[if(cPage != pageStart&&totalPage>4&&cPage<=totalPage){]-->
              <li data-page-num="<!--[=1]-->">1</li>
          <!--[}]-->
    <!--[}]-->

    <!--[for(i=pageStart;i<pageEnd;i++){]-->
        <!--[if(i==cPage){]-->
            <li class="current"><!--[=i]--></li>
        <!--[}else{]-->
            <li data-page-num="<!--[=i]-->"><!--[=i]--></li>
        <!--[}]-->
    <!--[}]-->

    <!--[if(totalPage-cPage >4){]-->
        <li>...</li>
        <li data-page-num="<!--[=totalPage]-->"><!--[=totalPage]--></li>
    <!--[}else{]-->
      <!--[if(cPage == totalPage){]-->
        <li class="current"><!--[=cPage]--></li>
      <!--[}else{]-->
        <li data-page-num="<!--[=totalPage]-->"><!--[=totalPage]--></li>
      <!--[}]-->
    <!--[}]-->

  </ul>
      <!--[if(cPage != pageStart&&cPage<=totalPage){]-->
        <span class="sp pre">上一页</span>
      <!--[}]-->
  <!--[}]-->
</script>
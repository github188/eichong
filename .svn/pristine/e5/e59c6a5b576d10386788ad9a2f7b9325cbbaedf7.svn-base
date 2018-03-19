<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="page" id="page-dom" ></div>
<%--
    !required parameter
    totalPage //总页数
    cpage //当前页
    pageStart //页码循环开始
    pageEnd //页码循环结束
--%>
<script type="text/html" id="page-tmpl">
  <!--[if(totalPage!=0){]-->
  <ul class="clearfix ul-pages">
	<!--[if(cPage==1){]-->
        <li class="fanyeDisable" disabled="disabled"><a>首页</a></li>
		<li class="fanyeDisable" disabled="disabled"><a>上一页</a></li>
    <!--[}else{]-->
        <li class="fanyeON" data-page-num="<!--[=1]-->"><a href="javascript://">首页</a></li>
		<li class="fanyeON" data-page-num="<!--[=cPage-1]-->"><a href="javascript://">上一页</a></li>
    <!--[}]-->
 	
    <!--[if(cPage>4&&cPage<=totalPage){]-->
        <li class="fanyeNum" data-page-num="<!--[=i]-->">1</li>
        <li>...</li>
    <!--[}else{]-->
          <!--[if(cPage != pageStart&&cPage>4&&cPage<=totalPage){]-->
              <li class="fanyeNum" data-page-num="<!--[=1]-->">1</li>
          <!--[}]-->
    <!--[}]-->

    <!--[for(i=pageStart;i<pageEnd;i++){]-->
        <!--[if(i==cPage){]-->
            <li class="current"><!--[=i]--></li>
        <!--[}else{]-->
            <li class="fanyeNum" data-page-num="<!--[=i]-->"><!--[=i]--></li>
        <!--[}]-->
    <!--[}]-->

    <!--[if(totalPage-cPage >4){]-->
        <li>...</li>
        <li class="fanyeNum" data-page-num="<!--[=totalPage]-->"><!--[=totalPage]--></li>
    <!--[}else{]-->
      <!--[if(cPage == totalPage){]-->
        <li class="current"><!--[=cPage]--></li>
      <!--[}else{]-->
        <li class="fanyeNum" data-page-num="<!--[=totalPage]-->"><!--[=totalPage]--></li>
      <!--[}]-->
    <!--[}]-->

	<!--[if(cPage==totalPage){]-->
		<li class="fanyeDisable" disabled="disabled"><a>下一页</a></li>
		<li class="fanyeDisable" disabled="disabled"><a>末页</a></li>
    <!--[}else{]-->
		<li class="fanyeON" data-page-num="<!--[=cPage+1]-->"><a href="javascript://">下一页</a></li>
		<li class="fanyeON" data-page-num="<!--[=totalPage]-->"><a href="javascript://">末页</a></li>
    <!--[}]-->
  </ul>
  <!--[}]-->

</script>
<script type="text/javascript">
	var page={
			pageFunction:null,
			params:{
				pageSize:10,
				pageNum:1
			}
	};
	function initPage(data,pageFun){
		page={
			pageFunction:pageFun,
			params:{
				pageSize:data.count,
				pageNum:data.currentPage
			}
		}
		var pageFor = data.pageFor || 'page-tmpl';
        var pageEle = data.pageEle || '#page-dom';
		 //页码渲染
        if ($('#' + pageFor).length) {
            var html = template.render(pageFor, {
                totalPage: data.totalPage,
                pageStart: data.pageStart,
                pageEnd: data.pageEnd,
                cPage: data.currentPage
            });
            $(pageEle).html(html).show();
        }
	}
</script>
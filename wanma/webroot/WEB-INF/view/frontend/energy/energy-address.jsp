<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <title>设置收货地址</title>
    <link href="<%=request.getContextPath()%>/static/css/base.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/static/css/common.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/static/css/address.css" rel="stylesheet"/>
    <link href="<%=request.getContextPath()%>/static/lib/sliderbox/css/jquery.slideBox.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/static/js/jquery.min.js"></script>
    <script src="<%=request.getContextPath()%>/static/js/common.js" type="text/javascript"></script>
    <script src="<%=request.getContextPath()%>/static/lib/sliderbox/js/jquery.slideBox.js" type="text/javascript"></script>
</head>
<body>
<!------头部-------->
<jsp:include page="../common/header.jsp"/>

<!--评价详情内容-->
<div class="content">
    <!-------导航-------->
    <div class="content-head">
        <a>首页 > </a> <a>新能源商城</a>
    </div>

    <!--中间内容-->
    <div class="cont_box" id="data-list">
        <!-- <div class="default_addr">
            <p class="default_tit">默认地址：</p>
            <div class="addr_info">
                <div class="check_box fl">
                    <span class="def_check checked"></span>
                </div>
                <div class="user_info fl">
                    <p><span class="gray">收货人： </span>李宏伟   13599762382 </p>
                    <p><span class="gray">收货地址：</span>上海市虹口区广纪路766号 </p>
                    <p class="yellow">（默认收货地址与安装地址一致）</p>
                </div>
                <div class="add_icon fl">
                    <a class="a_icon del"></a>
                    <a class="a_icon edit"></a>
                </div>
            </div>
        </div>

        <div class="other_addr">
            <p class="other_tit">其他收货地址：</p>
            <ul>
                <li>
                    <div class="addrDet">
                        <div class="check_box fl ">
                            <span class="def_check"></span>
                        </div>
                        <div class="user_info user_info_2 fl">
                            <p><span class="gray">收货人： </span>李宏伟   13599762382 </p>
                            <p><span class="gray">收货地址：</span>上海市虹口区广纪路766号 </p>
                        </div>
                        <div class="set_defalut fl"><a>设为默认地址</a></div>
                        <div class="add_icon add_icon_2 fl">
                            <a class="a_icon del"></a>
                            <a class="a_icon edit"></a>
                        </div>
                    </div>
                </li>
                <li>
                    <div class="addrDet">
                        <div class="check_box fl ">
                            <span class="def_check"></span>
                        </div>
                        <div class="user_info user_info_2 fl">
                            <p><span class="gray">收货人： </span>李宏伟   13599762382 </p>
                            <p><span class="gray">收货地址：</span>上海市虹口区广纪路766号 </p>
                        </div>
                        <div class="set_defalut fl"><a>设为默认地址</a></div>
                        <div class="add_icon add_icon_2 fl">
                            <a class="a_icon del"></a>
                            <a class="a_icon edit"></a>
                        </div>
                    </div>
                </li>
            </ul>
        </div> -->
        <!-- <div class="type_a">
            <a class="type_btn sure">确认</a>
            <a class="type_btn cancal">新增收货地址</a>
        </div> -->
    </div>
		<div class="type_a">
            <a class="type_btn sure">确认</a>
            <a class="type_btn cancal">新增收货地址</a>
        </div>
</div>

<jsp:include page="../common/footer.jsp" />

<script type="text/html" id="data-list-tmpl">
	<!--[for(i=0;i<list.length;i++){]-->
		<!--[if(list[i].pradIsDefault==1){]-->
			<div class="default_addr">
            	<p class="default_tit">默认地址：</p>
            	<div class="addr_info">
                	<div class="check_box fl">
                    	<span class="def_check checked"></span>
               		</div>
                	<div class="user_info fl">
                    	<p><span class="gray">收货人： </span><!--[=list[i].pradUsername]-->   <!--[=list[i].pradPhonenumber]--> </p>
                    	<p><span class="gray">收货地址：</span><!--[=list[i].pradAddress]--> </p>
                    	<p class="yellow">（默认收货地址与安装地址一致）</p>
                	</div>
					<input type="hidden" class="pkUseraddress" name="pkUseraddress" value="<!--[=list[i].pkUseraddress]-->"/>
                	<div class="add_icon fl">
                    	<a class="a_icon del"></a>
                    	<a class="a_icon edit"></a>
                	</div>
            	</div>
        	</div>
		<!--[}]-->
	<!--[}]-->
		
        	<div class="other_addr">
            	<p class="other_tit">其他收货地址：</p>
				<!--[for(i=0;i<list.length;i++){]-->
					<!--[if(list[i].pradIsDefault!=1){]-->
            			<ul>
                			<li>
                    			<div class="addrDet">
                        			<div class="check_box fl ">
                            			<span class="def_check"></span>
                        			</div>
                        			<div class="user_info user_info_2 fl">
                            			<p><span class="gray">收货人： </span><!--[=list[i].pradUsername]-->   <!--[=list[i].pradPhonenumber]--> </p>
                            			<p><span class="gray">收货地址：</span><!--[=list[i].pradAddress]--> </p>
                        			</div>
                        			<div class="set_defalut fl"><a>设为默认地址</a></div>
									<input type="hidden" class="pkUseraddress" name="pkUseraddress" value="<!--[=list[i].pkUseraddress]-->"/>
                        			<div class="add_icon add_icon_2 fl">
                            			<a class="a_icon del"></a>
                            			<a class="a_icon edit"></a>
                        			</div>
                    			</div>
                			</li>
            			</ul>
				<!--[}]-->
					<!--[}]-->
        	</div>
	
</script>

<!-- <script type="text/javascript">
    $(".other_addr ul li:last-child").css("border-bottom","0");
    $(".addr_info .check_box .def_check").click(function(){
       $(this).toggleClass("checked");
    });
    $(".other_addr ul li .addrDet .check_box .def_check").click(function(){
        if($(this).hasClass("checked")){
            $(this).removeClass("checked");
        }else{
            $(this).addClass("checked").parent().parent().parent().siblings().find(".def_check").removeClass("checked");
        }

    });
</script> -->

<script src="<%=request.getContextPath()%>/static/js/energy/readdress.js" type="text/javascript"></script>
</body>
</html>
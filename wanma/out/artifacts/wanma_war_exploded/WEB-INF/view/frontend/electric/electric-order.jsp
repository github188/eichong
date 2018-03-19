<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>预约</title>
    <%--custom css--%>
    <jsp:include page="../common/header-css.jsp"/>
    <link rel="stylesheet" href="<%=basePath%>/static/css/order.css">
    <link rel="stylesheet" href="<%=basePath%>/static/lib/sliderbox/css/jquery.slideBox.css">
    <style type="text/css">
		.clock {
		    position: relative;
		    width: 300px;
		    height: 260px;
		    margin: 20px auto;
		}
		.cursor{
		    position: absolute;
		    width: 188px;
		    height: 188px;
		    top: 36px;
		    left: 52px;
		}
    </style>
</head>
<body>

<!--//header -->
<jsp:include page="../common/header.jsp"/>

<!--//section-->
<div class="content">
    <!-------导航-------->
    <div class="content-head">
        <a>首页 > </a> 
        <a>电桩查找 > </a>
        <a>电桩详情> </a>
        <a>电桩预约</a>
    </div>
    <!--中间内容-->
    <div class="cont_box">
        <div class="order_cont" id="detail-data">
        </div>
    </div>
</div>

<!--//footer-->
<jsp:include page="../common/footer.jsp"/>

<%--validator plugin--%>
<jsp:include page="../common/validator.jsp"/>

<%--custom js--%>
<script type="text/javascript" src="<%=basePath%>/static/lib/sliderbox/js/jquery.slideBox.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/electric/electric-order.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/jquery.rotate.js"></script>
<script type="text/javascript">
    $(".eva_detail ul li:last-child").css("border-bottom","0");
</script>
<script type="text/html" id="detail-data-tmpl">
 <div class="eva_all">
			<!--[=ePHeElectricpileHeadName]-->当前状态: <span class="state">
				<!--[=$mateArray(ePHeElectricpileHeadState, 1)]--></span></div>
            <div class="contInfo">
                <div class="fl time_box">
                    <div class="clock_info">
                        <p class="time_tit tc">累计预约时间</p>
                		<p class="tc">
        					<div class="clock">
								<img src="<%=basePath%>/static/images/img/order/clock_1.png"/>
            					<div class="cursor">
									<img id="rotate-image" src="<%=basePath%>/static/images/img/order/cursor.png"/>
            					</div>
       						</div>
						</p>
                    </div>
                    <div class="time_info">
                        <div class="time_tit tc">预约时间设置</div>
                        <div class="time_list rel">
                            <div class="datelist_box rel">
                                <ul>
								<!--[for(i=0;i<4;i++){]-->
                                    <li>
                                        <p class="step_img">
                                            <img data-time="<!--[=i * 30]-->" src="<%=basePath%>/static/images/img/order/step_1.png"/>
                                            <p class="<!--[=i % 2 == 0 ? 'bottom' : 'top']-->_abs"><!--[=i * 0.5]-->小时</p>
                                        </p>
                                    </li>
     						 	<!--[}]-->
                                </ul>
                            </div>
                            <a class="return_left abs"></a>
                            <a class="return_right abs"></a>
                        </div>
                    </div>
                    <div class="big_btn">
                        <a href="javascript:void(0);" id="pay_detail">确定</a>
                    </div>
                </div>

                <div class="order_right fr">
                    <div class="radius_info">
                        <div class="rd_tit">
                            	当前预约费用
                        </div>
                        <div class="rd_price" id="order_price"></div>
                    </div>
                    <div class="radius_info mt">
                        <div class="rd_tit">
                            	当前时间
                        </div>
                        <div class="rd_price" id="now_time"></div>
                    </div>
                    <div class="radius_info mt">
                        <div class="rd_tit">
                               	  用户须知
                        </div>
                        <div class="rd_price">
                            <p>用户预约之后，可以买断从
                             	   当前时间开始，到用户本人
                             	   预计到达充电地点这段时间
                              	  ，保证用户到了后可以顺利
                              	  充到电！用户提前（预约买
                             	   断时间以30分钟为最小单位
                               	 、最大预约6小时）</p>
                        </div>
                    </div>
                </div>
            </div>
</script>
</body>
</html>
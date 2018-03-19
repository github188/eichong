<%@page import="com.sun.xml.internal.ws.util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="no">
<meta name="apple-mobile-web-app-status-bar-style" content="no">
<meta name="format-detection" content="telephone=no">

<title></title>

<link href="static/cssweb/appShare.css" rel="stylesheet" type="text/css">
</head>
<%
	String lng = "";
	String lat = "";
	String name = "";
	String type = "";
	String service = "";
	String addr = "";
	String web = request.getParameter("web");
	if(null != web){
		String[] webs = web.split("\\|");
		lng = webs[0];
		lat = webs[1];
		name = webs[2];
		addr = webs[3];
		type = webs[4];
		service = webs[5];
	}else{
		lng = request.getParameter("lng");
		lat = request.getParameter("lat");
		name = request.getParameter("name");
		type = request.getParameter("type");
		service = request.getParameter("service");
		addr = request.getParameter("addr");
	}
	
%>
<body class="eichong">
	<header class="m-banner m-banner-x1">
    <a class="download m-banner-x1--download">
      <div class="app-logo"></div>
      <div class="body">
        <h1 class="app-title"></h1>
        <p class="app-desc"></p>
      </div>
    </a>
    
  </header>
  

  <section class="eichong--main charger eichong--plug">
    <div class="charger--map">
      <img src="http://restapi.amap.com/v3/staticmap?scale=2&location=<%=lng %>,<%=lat %>&zoom=13&markers=-1,<%if(type.equals("1")){
    	  		out.print("http://www.eichong.com/app/static/imgAppShare/icon-super.png");
            }else if(type.equals("2")){
            	out.print("http://www.eichong.com/app/static/imgAppShare/btn_stilt.png");
            }else if(type.equals("3")){
            	out.print("http://www.eichong.com/app/static/imgAppShare/btn_bike.png");
           }%>,0:<%=lng %>,<%=lat %>&key=db7565fdde02dfb8ea56c49438ee2f3d">
    </div>
    <div class="info">
      <div class="info--item info--header">
        <h2 class="title"><%=name %></h2>
        <div class="type">
          <span>
          	<%if(type.equals("1")){ %>
            <img src="static/imgAppShare/img-station.png">充电站
            <%}else if(type.equals("2")){ %>
            <img src="static/imgAppShare/img-stilt.png">充电桩
            <%}else if(type.equals("3")){ %>
            <img src="static/imgAppShare/img-bike.png">自行车充电点
            <%} %>
          </span>
          <span>
            <img src="static/imgAppShare/img-money.png"><%=service %>元/度</span>

          <!-- 车主尊享 -->
                    </div>

      </div>

      <div class="info--item info--addr"><%=addr %></div>

          </div>

	  <!--begin of comments-->
    
        </section>

  <footer class="eichong--footer eichong--plug-footer">
    <div class="button fixed">
      <a href="app.html" class="btn">
        下载爱充网手机客户端
        <img src="static/imgAppShare/icon-arrow_more.png">
      </a>
    </div>
  </footer>
</body>
</html>
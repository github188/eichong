<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>电桩查找</title>
<jsp:include page="../common/header-css.jsp" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<style type="text/css">
.amap-marker-label {
	border: 0px solid #000;
	font-weight: bold;
	font-size: 18px;
	font-family: "Arial";
	padding: 0px;
}

.marker-label {
	border: 0px solid #fff;
	position: absolute;
	z-index: 2;
	background-color: #fff;
	white-space: nowrap;
	cursor: default;
	border: 0px solid #000;
	font-weight: bold;
	font-size: 18px;
	font-family: "Arial";
	padding: 0px;
	line-height: 14px;
}
</style>
</head>
<body>
<div id="wrapper"> 
	<!--//header -->
	<jsp:include page="../common/header2.jsp" />
    <div class="Column">
	  <div class="ChannelContent">
	    <ul class="Channel">
	      <li class="ChannelNav" data-type="map"><a class="show" >地图显示</a></li>
	      <li class="ChannelNav" data-type="table" ><a class="unshow">列表显示</a></li>
	      <li class="ChannelNav2 floatR mar_0" ><a id="search" class="search" >电桩搜索</a></li>
	      <li class="Search"> 
	        <!-- <select>
	          <option>地址</option>
	          <option>站/桩名</option>
	        </select> -->
	      <input id="address" class="search" name="" type="text" placeholder="名称或地址"/></li>
	    </ul>
	  </div>
  	  <div style="clear:both;"></div>
	  <div class="ChannelContent">
		    <ul class="shaixuanWarp">
		      <li class="shaixuan"><a href="#"><img src="<%=basePath%>/static/images/img/shaixuan.jpg" width="103" height="24" alt="  " /></a>
			      <div class="shaixuanShow">
			        <dl class="row-top">
			          <dd>国标接口充电点<input class="searchCondition" name="powerInterface" type="checkbox" value="7" /></dd>
			          <dd>快充点<input  name="chargingMode" type="checkbox" value="5" /></dd>
			          <dd>慢充点<input  name="chargingMode" type="checkbox" value="14" /></dd>
			          <dd>适合爱车充电点<input class="searchCondition" id="suitable" name="suitable" type="checkbox" value="1" /></dd>
			          <dd>空闲中的充电点<input class="searchCondition" id="headState" name="headState" type="checkbox" value="0" /></dd>
			          <dd>支持预约充电点<input class="searchCondition" id="commStatus" name="commStatus" type="checkbox" value="1" /></dd>
			        </dl>
			        <%-- <dl class="row-below">
			          <dd><img src="<%=basePath%>/static/images/img/zhushi.jpg" width="755" height="40" alt=" " /></dd>
			        </dl> --%>        
			      </div>
		      </li>   
		    </ul>
	  </div>
	</div>	
      
      <!--------筛选结束--------->
      
      <div class="map" id="allMap">
      </div>
      <!--列表内容-->
	  <div class="pile" id="table" style="background:#fff;">
		  <div class="ChannelContent" id="data-list"></div>
		  <div style="clear:both;"></div>
		  <div style="width:1000px; margin:0 auto;padding-bottom:100px;">
		  	<div class="floatL fanye" id="pageEle"></div>
		  </div>
      </div>
    </div>
</div>    
<div id="weixin_share" style="display:none;position:absolute;top:800px;left:0;width:200px; height:200px;z-index:9999;background:white;"></div>  
<!-- end content -->
<!--//footer-->
<div style="clear:both;"></div>
<jsp:include page="../common/footer2.jsp" />
<script type="text/javascript">
	var appShareUrl="${appShareUrl}";
	this_tag="electricDistribution";
</script>
<jsp:include page="../common/footer-js.jsp" />
<link href="<%=basePath%>/static/lib/share/css/style.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="<%=basePath%>/static/lib/share/js/share.js"></script>
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.3&key=5482098780018a97976fbb4f52252595"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/jquery.qrcode.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/electric/electric-search.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/common/page.js" ></script>
<script type="text/html" id="data-list-tmpl">
<!--[for(i = 0;i < list.length;i++){]-->
	<div class="<!--[if(i%2==0){]-->floatL<!--[}else{]-->floatR<!--[}]--> list">
	<dl>
        <dt>
		<a href="<%=basePath%>/web/<!--[if(list[i].electricType == 1){]-->electricpile<!--[}else{]-->powerstation<!--[}]-->/detail.do?eid=<!--[=list[i].electricId]-->" target="_blank" title="<!--[=list[i].electricName]-->">
			<img src="<%=basePath%>/static/images/img/ic-2.png" width="43" height="43" alt="  " />
		</a>
		</dt>
		<dd class="zhuangtai">
			<!--[if(list[i].electricType == 1){]-->
			<!--[}else{]-->
				共有充电桩 <span><!--[=list[i].electricPileSum]--></span> 个
			<!--[}]-->
		</dd>
        <dd class="P-Name">
          <h3 class="zhuang_title"><a href="<%=basePath%>/web/<!--[if(list[i].electricType == 1){]-->electricpile<!--[}else{]-->powerstation<!--[}]-->/detail.do?eid=<!--[=list[i].electricId]-->" target="_blank" title="<!--[=list[i].electricName]-->"><!--[=$ellipsis(list[i].electricName, 12)]--></a></h3>
        </dd>
        <dd class="add"> 	
          <span class="zhuang_roud">地址：<!--[=list[i].electricAddress]--></span>
        </dd>
      </dl>
	</div>
<!--[}]-->


</script>
</body>
</html>

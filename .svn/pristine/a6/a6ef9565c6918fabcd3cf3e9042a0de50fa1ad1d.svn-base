<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>电桩详情</title>
<jsp:include page="../common/header-css.jsp" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<link type="text/css" rel="stylesheet" href="<%=basePath%>/static/lib/score/css/application.css">
<link rel="stylesheet" href="<%=basePath%>/static/lib/jquery-ui/css/jquery-ui.min.css" />
<link type="text/css" rel="stylesheet" href="<%=basePath%>/static/lib/cssScore/css/style.css">
<link href="<%=basePath%>/static/lib/share/css/style.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/static/css/style.css" rel="stylesheet" type="text/css" /><!--全局-->
<script src="http://qzonestyle.gtimg.cn/qzone/app/qzlike/qzopensl.js#jsdate=20111201" charset="utf-8"></script>
</head>
<body>
	<!--//header -->
	<jsp:include page="../common/header2.jsp" />
<!-- start content -->
<input id="electricPileId" tip-value="1" class="tip" type="hidden" name="prCoProductId" value="${pile.pkElectricPile}"> 
<input id ="zhuangxingpingshu" type="hidden" value ="${score}">
<input id ="myCenterLinkPkCommentId" data-rowNum="${linkCommentsRowNum}" type="hidden" value="${commentId}">
<!-- start content -->
<!--详情内容-->
<div class="detail">
  <!--内容头部-->
  
  <div class="ChannelContent">
    <dl class="detailHeader">
      <dt style="position: relative;"><!--单一桩体，非站点<span class="biaoshi_zhuang"></span>--><span class="biaoshi_zhuang"></span>${pile.electricPileName}<span id='fenxiangDiv' class="fenxiang"><a href="#" id='shareOpenDiv'></a><div id='shareTxtDiv'>分享</div>
     </span>
     <div id='bshare-custom_div' data-info="${pile.elpiLongitude},${pile.elpiLatitude},2,${pile.electricPileAdress}" class="bshare-custom1" style="display:none"><a title="分享到QQ空间"  class="bshare-qzone" ></a><a title="分享到新浪微博" class="bshare-sinaminiblog"></a><a title="分享到微信" class="bshare-weixin"></a></div>
      <!--<span class="shoucang yishoucang"><a href="#"></a>已收藏</span>-->
      <span class="shoucang"><a class="collect_submit" data-id="${pile.pkElectricPile}" data-type="1" data-value="${pile.isCollect}"></a><div id='shouchangDiv'><c:if test="${pile.isCollect=='0'}">收藏</c:if><c:if test="${pile.isCollect != '0'}">已收藏</c:if></div></span></dt>
      <dd>地址：${pile.electricPileAdress}</dd>
      <dd class="shuxing">
        <div class="shuxingWarp">
          <p>
             联网桩体<strong>${pile.electricPileConnStatus}</strong>
          </p>
        </div>
        <div class="shuxingWarp">
          <p>
             空闲枪头<strong>${kongxianCount}</strong>
          </p>
        </div>
        <div class="shuxingWarp">
          <p>
             服务费<strong>${pile.raInServiceCharge}元/度</strong>
          </p>
        </div>
        <div class="shuxingWarp FengZhi">
          <p>
             <a href="#"><span>电费<strong>查看费率</strong></span></a>
          </p>
          <!--浮动的尖峰平谷汇率-->
          <div class="JFPG">
            <ul class="JFPG_BOX">
            	${fengzhiHtml}
            </ul>
          </div>
          <!--汇率结束-->
        </div>        
        <div class="shuxingWarp">
          <p>
             综合评分<strong><c:if test="${pile.electricPileCommentStar=='0'}">5.0</c:if>
            <c:if test="${pile.electricPileCommentStar!='0'}">${pile.electricPileCommentStar}</c:if>分</strong>
          </p>
        </div>
        <div class="shuxingWarp">
          <p>
             开放时间<strong><c:if test="${pile.elPiOnlineTime != ''}">${pile.elPiOnlineTime}</c:if><c:if test="${pile.elPiOnlineTime == ''}">24</c:if>小时</strong>
          </p>
        </div>        
        <div class="shuxingWarp">
          <p>
             人工服务<strong>${pile.electricPileTell}</strong>
          </p>
        </div>
      </dd>
    </dl>
  </div>
  <div style="clear:both;"></div>
  
  <div class="ChannelContent">
    <div class="zhuangone">
      <dl>
        <dd class="zhuangUp">
          <div class="zhuangOneL">
            <dl class="zhuangSide">
              <dd><p class="zhuangSideTitle">充电方式</p><p class="zhuangSideContent"><strong><c:if test="${pile.electricPileChargingMode=='5'}">快充</c:if>
            	<c:if test="${pile.electricPileChargingMode=='14'}">慢充</c:if></strong></p></dd>
              <dd class="zhuangSideCenter"><p class="zhuangSideTitle">桩体接口</p><p class="zhuangSideContent"><strong><c:if test="${pile.electricPowerInterface=='7'}">国标</c:if>
            	<c:if test="${pile.electricPowerInterface=='19'}">美标</c:if>
            	<c:if test="${pile.electricPowerInterface=='20'}">欧标</c:if></strong></p></dd>
              <dd><p class="zhuangSideTitle">额定功率</p><p class="zhuangSideContent"><strong>${pile.electricPowerSize}</strong></p></dd>
            </dl>
          </div>
          <div class="zhuangC_one">
            <dl class="Sequence">
              <dt><img src="<%=path%>/static/images/img/icon-<c:if test="${pile.electricPileState=='10'}">3</c:if><c:if test="${pile.electricPileState!='10'}">4</c:if>.png" width="50" height="50" alt="  " /></dt>
              <dd class="fontS48">01<span>#</span></dd>
              
              <c:if test="${pile.electricPileState=='10'}"><dd class="fontS36 Black"><b>普通桩</b></dd></c:if>
	                <c:if test="${pile.electricPileState=='15'}"><dd class="fontS36"><b>智能桩</b></dd></c:if>
              <dd class="fontS24 <c:if test="${pile.electricPileConnStatus=='0'}">Red</c:if>"><c:if test="${pile.electricPileConnStatus=='0'}">断开</c:if><c:if test="${pile.electricPileConnStatus=='1'}">连接中</c:if></dd>
            </dl>          
          </div>
          
          <c:if test="${pile.pileHeadList.size() <= 3}">
          <div class="zhuangR">
            <dl class="zhuangSide">
	          <c:forEach var="head" items="${pile.pileHeadList}" varStatus="status"  >
	          		<c:if test="${pile.electricPileConnStatus=='0'}">
						<c:if test="${head.pileHeadState==null}"><dd></dd></c:if>
						<c:if test="${head.pileHeadState!=null}"><dd><span>${head.pileHeadName}<br /><strong class="Black">未知状态</strong></span></dd></c:if>
	          		</c:if>
	          		<c:if test="${pile.electricPileConnStatus !='0'}">
	          		
						<c:if test="${head.pileHeadState=='0'}">
							<c:if test="${pile.electricPileState=='10'}">
								<dd class="kongxianzhuang"><span>${head.pileHeadName}<br />
					                   <strong class="Green">空闲</strong></span>
								</dd>
				          	</c:if>
				          	<c:if test="${pile.electricPileState!='10'}">
								<dd class="zhuangRightOther kongxianzhuang showErweima"><a href="javascript://"><span>${head.pileHeadName}<br />
					                   <strong class="Green">空闲</strong><br />手机扫码预约</span></a>
								</dd>
				          	</c:if>
						</c:if>
						<c:if test="${head.pileHeadState=='3'}"> <dd><span>${head.pileHeadName}<br /><strong class="Blue">预约中</strong></span></dd></c:if>
						<c:if test="${head.pileHeadState=='6'}"><dd><span>${head.pileHeadName}<br /><strong class="Orange">充电中</strong></span></dd></c:if>
						<c:if test="${head.pileHeadState=='9'}"><dd><span>${head.pileHeadName}<br /><strong class="Black">停用中</strong></span></dd></c:if>
						<c:if test="${head.pileHeadState==null}"><dd></dd></c:if>
	          		</c:if>
			   </c:forEach> 
            </dl>        
          </div> 
          </c:if>
          
           <c:if test="${pile.pileHeadList.size() > 3}">
          <div class="zhuangOneR P_fanye" data-id="scrollList${pile.pkElectricPile}" data-on="on${pile.pkElectricPile}" data-off="off${pile.pkElectricPile}">
           		<p class="P_fanye_a" id="on${pile.pkElectricPile}"><a ><img src="<%=path%>/static/images/img/h1-img01-on.png" width="20" height="12" /></a></p>
	            <p class="P_fanye_b" id="off${pile.pkElectricPile}"><a ><img src="<%=path%>/static/images/img/h1-img02-on.png" width="20" height="12" /></a></p>
	            <dl class="zhuangSide scrollList" id="scrollList${pile.pkElectricPile}" style="height:250px;">
	          <c:forEach var="head" items="${pile.pileHeadList}" varStatus="status"  >
	          		<c:if test="${pile.electricPileConnStatus=='0'}">
						<c:if test="${head.pileHeadState==null}"><dd class="zhuangRightOther"><span><strong></strong></span></dd></c:if>
						<c:if test="${head.pileHeadState!=null}"><dd class="zhuangRightOther"><span>${head.pileHeadName}<br /><strong class="Black">未知状态</strong></span></dd></c:if>
	          		</c:if>
	          		
	          		<c:if test="${pile.electricPileConnStatus !='0'}">
	          		
						<c:if test="${head.pileHeadState=='0'}">
							<c:if test="${pile.electricPileState=='10'}">
								<dd class="zhuangRightOther kongxianzhuang"><span>${head.pileHeadName}<br />
					                   <strong class="Green">空闲</strong></span>
								</dd>
				          	</c:if>
				          	<c:if test="${pile.electricPileState!='10'}">
								<dd class="zhuangRightOther kongxianzhuang showErweima"><a href="javascript://"><span>${head.pileHeadName}<br />
					                   <strong class="Green">空闲</strong><br />手机扫码预约</span></a>
								</dd>
				          	</c:if>
						</c:if>
						<c:if test="${head.pileHeadState=='3'}"> <dd class="zhuangRightOther"><span>${head.pileHeadName}<br /><strong class="Blue">预约中</strong></span></dd></c:if>
						<c:if test="${head.pileHeadState=='6'}"><dd class="zhuangRightOther"><span>${head.pileHeadName}<br /><strong class="Orange">充电中</strong></span></dd></c:if>
						<c:if test="${head.pileHeadState=='9'}"><dd class="zhuangRightOther"><span>${head.pileHeadName}<br /><strong class="Black">停用中</strong></span></dd></c:if>
	          		</c:if>
			   </c:forEach> 
            </dl>        
          </div>
          </c:if>       
        </dd>
      </dl>
    </div>
  </div>
  <div style="clear:both;"></div>  
  
   
  <!--图片展示、评分、评论-->
  <div class="ChannelContent pad_bot50">
    <dl class="imgShow">
      <dt>图片展示</dt>
      <dd><img src="${pile.electricPileImage}" width="300" height="200" alt="  " /></dd>
      <dd></dd>
      <dd></dd>
      <div style="clear:both;"></div>  
    </dl>
    
    
    
    <dl class="pingfen">
      <dt>评分</dt>
      <dd id="function-demo2" class="target-demo fen-main"></dd>
      <input id="zhuangxingpingfenshu" tip-value=""   type="hidden" /> 
      <dd><input name="button" type="button" id="zhuangxingping-tijiao" value="提交" /></dd>
    </dl>
    <dl class="pinglun">
      <dt><span id="allComment"></span>评论</dt>
      <dd>
        <div class="BoxInput">
          <ul>
            <li class="IconStyle"><a href="javascript://"><img src="<%=path%>/static/images/img/face.png" width="27" height="27" alt=" " /></a></li>
            <li class="InputOn"><textarea id='pingfen-neirong' onclick="clearTxt(this)" cols="" rows="" maxlength="200">我也说两句</textarea></li>
            <li class="user"><input id='zhuangpingfen-tijiao' name="button" type="button" value="提交" /><span class="userNav"><img id="userImgDiv" class="userImg" src="${myImage}" width="27" height="27" alt="" style="display:none"/><t id='userNameDiv' class="userName">${userName}</t></span>
            </li>
          </ul>
        </div>
      </dd>
      
      <dd> <!--当前评论-->  
      
        <!--一条评论开始-->
        <div id='tag'>
        	     
       	</div> 
        <!--一条评论结束-->    <!--翻页-->
        	<div class="floatL fanye" id="pageEle"></div><div style="clear:both;"></div>          
      </dd>
      
    </dl>
    
  </div>
  
  
  <div style="clear:both;"></div> 
     
</div>
<!-- end content -->
<div class="LoginBox" id="yuyueWindow" style="display:none;">
<form id="bespForm" method="post" >
<input type="hidden" id="bespElectricpileid" name="bespElectricpileid" />
<input type="hidden" id="bespBespoketime" name="bespBespoketime"/>
<input type="hidden" id="bespElectricpilehead" name="bespElectricpilehead" />
<input type="hidden" id="bespUserinfo" name="bespUserinfo"/>
<input type="hidden" id="bespResepaymentcode" name="bespResepaymentcode" />
<input type="hidden" id="bespFrozenamt" name="bespFrozenamt"/>
<input type="hidden" id="bespBespokeprice" name="bespBespokeprice"/>
<input type="hidden" id="bespBeginTime" name="bespBeginTime"/>
<input type="hidden" id="bespEndTime" name="bespEndTime"/>
</form>
    <dl class="LoginBoxDengLu">
      <dt><a id="closeYuyue">×</a>预约</dt>
      <h5 class="yy-A"><span id="pileHeadName"></span>状态：<span id="pileHeadStatus"></span></h5>
      <h5 class="yy-B yy">当前时间：<span id="showTime">00:00:00</span></h5>
      <h5 class="yy-B yy">设置预约时间，最多可预约<span>6</span>小时</h5>
      <h5 class="yy-C yy">
		  <div id="jslider"></div>
	  </h5>
      <h5 class="yy-D yy">当前预约<span id="yuyueTime" style="display:inline-block;width:25px;">0</span>小时,费用<span id="yuyuePrice" style="display:inline-block;width:50px;">0</span>元</h5>
      <dd><input id="submitBesp" class="sub" type="button" name="type" value="提交预约"></dd>
      <h5 class="yy-E yy">预约须知</h5>
      <h5 class="yy-F">用户预约之后，可以买断从当前时间开始，到用户本人预计到达充电地点这段时间，保证用户到了后可以顺利充到电...用户提前（预约买断时间以30分钟为最小单位、最大预约6小时）</h5>
    </dl><div class="clear"> </div>
</div>
<!--//footer-->
<jsp:include page="../common/footer2.jsp" />
<jsp:include page="../common/footer-js.jsp" />
<script type="text/javascript" src="<%=basePath%>/static/lib/jquery-ui/js/jquery-ui.min.js?<%=Math.random()%>"></script>
<script type="text/javascript" src="<%=basePath%>/static/lib/share/js/share.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/jquery.qrcode.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/electric/electric-pile.js?<%=Math.random()%>"></script>
<script type="text/javascript" src="<%=basePath%>/static/lib/score/js/jquery.raty.min.js"></script>
<script src="<%=basePath%>/static/js/common/page.js" type="text/javascript"></script>

<script type="text/javascript" src="<%=basePath%>/static/js/scrollText.js" ></script>
<script type="text/javascript">
var appShareUrl="${appShareUrl}";

$(document).ready(function() {
	$(".P_fanye").each(function(){
		 new ScrollText($(this).attr("data-id"),$(this).attr("data-on"),$(this).attr("data-off"),false,50,false);
	});
	$('body').on('mouseenter', '.showErweima', function (e) {
		var X = $(this).offset().left-116;
		var Y = $(this).offset().top;
		var html='<div id="showErweima" class="erweima" style="top:'+Y+'px;left:'+X+'px;"></div>';
		$("body").append(html);
		var epId=$("#electricPileId").val();
		$(".erweima").qrcode({
			width:'100',
			height:'100',
			correctLevel:0, 
			text:"http://www.eichong.com/app/check_app_in.jsp?et=1&c=&d="+epId
		});
	});
	$('body').on('mouseleave', '.showErweima', function (e) {
		$("#showErweima").remove();
		
	});
});
</script>
</body>
</html>

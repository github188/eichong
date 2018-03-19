<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<link href="<%=basePath%>/static/css/electric_pile.css" rel="stylesheet" type="text/css"/>


	<!--//header -->
<div class="pageContent" layoutH="1">	
<div class="Column" >
<div class="detail" >
  <!--内容头部-->
  
  <div class="ChannelContent">
    <dl class="detailHeader">
      <dt style="position: relative;"><!--单一桩体，非站点<span class="biaoshi_zhuang"></span>--><span class="biaoshi_zhan"></span>${station.powerStationName}
     </dt>
      <dd>地址：${station.powerStationAddress}</dd>
      <dd class="shuxing">
        <div class="shuxingWarp">
          <p>
			联网桩体<strong>${linkingCount}</strong>
          </p>
        </div>
        <div class="shuxingWarp">
          <p>
             空闲枪头<strong>${kongxianCount}</strong>
          </p>
        </div>
        <div class="shuxingWarp">
          <p>
             服务费<strong>${raIn_ServiceCharge}元/度</strong>
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
             综合评分<strong><c:if test="${station.powerCommentStar=='0'}">5</c:if>
            <c:if test="${station.powerCommentStar!='0'}">${station.powerCommentStar}</c:if>分</strong>
          </p>
        </div>
        <div class="shuxingWarp">
          <p>
 			充电电量<strong>${station.totalChargeDl}度</strong>
          </p>
        </div>
        <div class="shuxingWarp">
          <p>
             	充电时间<strong>${station.totalChargeTime}小时</strong>
          </p>
        </div>
        <div class="shuxingWarp">
          <p>
 			充电费用<strong>${station.totalChargeAmt}元</strong>
          </p>
        </div>
        <div class="shuxingWarp">
          <p>
             开放时间<strong><c:if test="${station.poStOnlineTime != ''}">${station.poStOnlineTime}</c:if><c:if test="${station.poStOnlineTime == ''}">24</c:if>小时</strong>
          </p>
        </div>        
        <div class="shuxingWarp">
          <p>
             人工服务<strong>${station.powerStationTell}</strong>
          </p>
        </div>
      </dd>
    </dl>
  </div>
  <div style="clear:both;"></div>
  <!--桩（一行两个）-->
   <c:forEach var="pile" items="${station.powerElectricpileList}" varStatus="status"  >
  <div class="ChannelContent">
    <div class="<c:if test="${status.index%2==0}">floatL zhuang</c:if><c:if test="${status.index%2==1}">floatR zhuang</c:if>">
      <dl>
        <dd class="zhuangUp">
          <div class="zhuangL">
            <dl class="zhuangSide">
               <dd><p class="zhuangSideTitle">充电方式</p><p class="zhuangSideContent"><strong><c:if test="${pile.electricPileChargingMode=='5'}">快充</c:if>
            	<c:if test="${pile.electricPileChargingMode=='14'}">慢充</c:if></strong></p></dd>
              <dd class="zhuangSideCenter"><p class="zhuangSideTitle">桩体接口</p><p class="zhuangSideContent"><strong><c:if test="${pile.electricPowerInterface=='7'}">国标</c:if>
            	<c:if test="${pile.electricPowerInterface=='19'}">美标</c:if>
            	<c:if test="${pile.electricPowerInterface=='20'}">欧标</c:if></strong></p></dd>
              <dd><p class="zhuangSideTitle">额定功率</p><p class="zhuangSideContent"><strong>${pile.electricPowerSize}</strong></p></dd>
            </dl>
          </div>
          <div class="zhuangC">
            <dl class="Sequence">
              <dt><img src="<%=path%>/static/images/img/icon-<c:if test="${pile.electricPileState=='10'}">3</c:if><c:if test="${pile.electricPileState!='10'}">4</c:if>.png" width="50" height="50" alt="  " /></dt>
              <dd class="fontS48">0${status.index+1}<span>#</span></dd>
              
              <c:if test="${pile.electricPileState=='10'}"><dd class="fontS36 Black"><b>普通桩</b></dd></c:if>
	                <c:if test="${pile.electricPileState=='15'}"><dd class="fontS36"><b>智能桩</b></dd></c:if>
              <dd class="fontS24 <c:if test="${pile.electricPileConnStatus=='0'}">Red</c:if>"><c:if test="${pile.electricPileConnStatus=='0'}">断开</c:if><c:if test="${pile.electricPileConnStatus=='1'}">连接中</c:if></dd>
               <dd class="shishixiangqing">
	              <a onclick="openCurrentDetailTab(this)" 
	              ref="<%=path%>/admin/electricCurrent/getElectricPileDetail.do?eid=${pile.elictricPicId}">
	              	进入实时详情
	              </a>
              </dd>	
            </dl>          
          </div>
          
    	<c:if test="${pile.pileHeadList.size() <= 3}">
          <div class="zhuangR">
            <dl class="zhuangSide">
	          <c:forEach var="head" items="${pile.pileHeadList}" varStatus="status"  >
	          		<c:if test="${pile.electricPileConnStatus=='0'}">
						<c:if test="${head.pileHeadState==null}"><dd class="<c:if test="${status.index==1}">zhuangSideCenter</c:if>"><span><strong></strong></span></dd></c:if>
						<c:if test="${head.pileHeadState!=null}"><dd class="<c:if test="${status.index==1}">zhuangSideCenter</c:if>"><span>${head.pileHeadName}<br /><strong class="Black">未知状态</strong></span></dd></c:if>
	          		</c:if>          		
	          		<c:if test="${pile.electricPileConnStatus !='0'}">
						<c:if test="${head.pileHeadState=='0'}"> 
			          		<c:if test="${pile.electricPileState=='10'}">
								<dd class="kongxianzhuang <c:if test="${status.index==1}">zhuangSideCenter</c:if>"><span>${head.pileHeadName}<br />
					                   <strong class="Green">空闲</strong></span>
			            		</dd>
			          		</c:if>
			          		<c:if test="${pile.electricPileState!='10'}">
								<dd class="zhuangRightOther kongxianzhuang showErweima"><a href="javascript://"><span>${head.pileHeadName}<br />
						                   <strong class="Green">空闲</strong><br />手机扫码预约</span></a>
						            </dd>
			          		</c:if>
						</c:if>
						<c:if test="${head.pileHeadState=='3'}"> <dd class="<c:if test="${status.index==1}">zhuangSideCenter</c:if>"><span>${head.pileHeadName}<br /><strong class="Blue">预约中</strong></span></dd></c:if>
						<c:if test="${head.pileHeadState=='6'}"><dd class="<c:if test="${status.index==1}">zhuangSideCenter</c:if>"><span>${head.pileHeadName}<br /><strong class="Orange">充电中</strong></span></dd></c:if>
						<c:if test="${head.pileHeadState=='9'}"><dd class="<c:if test="${status.index==1}">zhuangSideCenter</c:if>"><span>${head.pileHeadName}<br /><strong class="Black">停用中</strong></span></dd></c:if>
						<c:if test="${head.pileHeadState==null}"><dd class="<c:if test="${status.index==1}">zhuangSideCenter</c:if>"><span><strong></strong></span></dd></c:if>
	          		</c:if>
			   </c:forEach> 
            </dl>          
          </div>
    	</c:if> 
          <c:if test="${pile.pileHeadList.size() > 3}">
	          <div class="zhuangR P_fanye" data-id="scrollList${pile.elictricPicId}" data-on="on${pile.elictricPicId}" data-off="off${pile.elictricPicId}">
	            <p class="P_fanye_a" id="on${pile.elictricPicId}"><a ><img src="<%=path%>/static/images/img/h1-img01-on.png" width="20" height="12" /></a></p>
	            <p class="P_fanye_b" id="off${pile.elictricPicId}"><a ><img src="<%=path%>/static/images/img/h1-img02-on.png" width="20" height="12" /></a></p>
	            <dl class="zhuangSide scrollList" id="scrollList${pile.elictricPicId}" style="height:250px;">
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
	</c:forEach>
  <div style="clear:both;"></div>  
  
   
</div>
</div>	
</div>
  
<script type="text/javascript" src="<%=basePath%>/static/js/electric/electricPile.js?<%=Math.random()%>"></script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<link href="<%=basePath%>/static/css/styleshifan.css" rel="stylesheet"
	type="text/css" />
<div class="pageContent" layoutH="1">
	<!--详情内容-->
	<div class="detail">
		<!--内容头部-->
		<div class="ChannelContent">
			<dl class="detailHeader">
				<dt>
					<!--单一桩体，非站点<span class="biaoshi_zhuang"></span>-->${pile.electricPileName}
					<span class="UsernameMein" id="epCode" data-code="${pile.electricPileNo }">（充电桩编号：${pile.electricPileNo }#）</span>
				</dt>
				<dd>地址：${pile.electricPileAdress }</dd>

				<dd class="shuxing2">
					<div class="shuxingWarp2">充电电量（度）</div>
					<div class="shuxingWarp2">充电时间（小时）</div>
					<div class="shuxingWarp2">充电费用（元）</div>
					<div class="shuxingWarp2">故障次数</div>
					<div class="shuxingWarp2">电桩状态</div>
					<div class="shuxingWarp2">操作</div>

					<div class="shuxingWarp3">
						<span class="yellow">${pile.totalChargeDl }</span>
					</div>
					<div class="shuxingWarp3">
						<span class="yellow">${pile.totalChargeTime }</span>
					</div>
					<div class="shuxingWarp3">
						<span class="yellow">${pile.totalChargeAmt }</span>
					</div>
					<div class="shuxingWarp3">
						<span class="Red">${pile.faultAmount!=null?pile.faultAmount:0}</span>
					</div>
					<div class="shuxingWarp3">
						<span class="Green">启用中</span>
					</div>
					<div class="shuxingWarp3">
						<a class="stop" href="#"></a>
					</div>
				</dd>


				<div style="margin-top: 40px; height: 40px;">
					<c:forEach var="head" items="${headList}" varStatus="status">
						<div data-id="${head.pkElectricpilehead}"
							class="qiangtou 
							${status.index==0?'qiangtouSelect':'qiangtouUnselect'}">
							<a href="#" onclick="changeSelectClass(this);loadHeadDetailInfo()">${head.epheElectricpileheadname}</a>
						</div>
					</c:forEach>
				</div>



				<dd class="shuxing2">
					<div class=" shuxingWarp2">充电机状态</div>
					<div class=" shuxingWarp2">充电电量（度）</div>
					<div class=" shuxingWarp2">充电时间（小时）</div>
					<div class=" shuxingWarp2">充电费用（元）</div>
					<div class=" shuxingWarp2">电池组最低温度（℃）</div>
					<div class=" shuxingWarp2">电池组最高温度（℃）</div>

					<div class=" shuxingWarp3">
						<span class="Green" id="epheElectricpileheadstateDiv"></span>
					</div>
					<div class=" shuxingWarp3">
						<span class="yellow" id="totalChargeDlDiv"></span>
					</div>
					<div class=" shuxingWarp3">
						<span class="yellow" id="totalChargeTimeDiv"></span>
					</div>
					<div class=" shuxingWarp3">
						<span class="yellow" id="totalChargeAmtDiv"></span>
					</div>
					<div class=" shuxingWarp3">
						<span class="yellow" id="lowTemperatureDiv"></span>
					</div>
					<div class=" shuxingWarp3">
						<span class="yellow" id="highTemperatureDiv"></span>
					</div>
				</dd>
				<dd>
					<div id="voltageDiv" class="chartDiv" style="width:490px; height:200px; float:left; background:#fff; border: 1px solid #ccc; margin-top:20px;"></div>
					<div id="currentDiv" class="chartDiv" style="width:490px; height:200px; float:right; background:#fff; border: 1px solid #ccc; margin-top:20px;"></div>
					<div id="socDiv" style="width: 490px; height: 200px; float: left; background: #fff;border: 1px solid #ccc; margin-top: 20px;">
					
						<div style="width: 300px; height: 10px; float: left; background: #fff; margin-top: 10px;margin-left: 5px;font-size:20px;color:#1E90FF;font-family:黑体;">实时电池容量（%）</div>'
							<div id='allLine' style="border-radius:0px 15px 15px 0px;width: 230px; height: 100px; float: left; background: #E0EEEE; margin-top: 30px;margin-left: 130px;">
							</div>
						<div id="percent" style="width: 200px; height: 10px; float: left; background: #fff; margin-top: 10px;margin-left:170px;font-size:20px;color:#1E90FF;font-family:黑体;">电池容量（0%）</div>
					
					</div>
					<div id="centered" style="width:490px; height:200px; float:right; background:#fff; margin-top:20px;border: 1px solid #ccc;">
					<div style="width: 300px; height: 10px; float: left; background: #fff; margin-top: 10px;margin-left: 5px;font-size:20px;color:#1E90FF;font-family:黑体;">电池组平均温度（℃）</div>
				          <div id="mometer" style="left:187px">
			    		     <div id="hot" ></div>   		     
			    		     	<div ><img style="margin-top: 100px;margin-left: -15px;"  src="<%=basePath%>/static/images/wenduji/glassBottom.png"></div>    		     
					    		<span id="detailElectric">
							        <div id="Hgheader">0℃</div>
							        <div id="Hg"></div>
					   		    </span>
						  </div>
						  <input type="hidden"  name="detailTemperature" type="text"  id="num"/>
						  <input type="hidden"  name="" type="button" id="Risk" value="查看度数" />
					</div> 

				</dd>
			</dl>
		</div>



		<div style="clear: both;"></div>
	</div>
</div>

<script type="text/javascript"
	src="<%=basePath%>/static/js/electric/electric-current-chart.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/static/js/electric/electric-current.js"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/electric/electric-temperature.js"></script>
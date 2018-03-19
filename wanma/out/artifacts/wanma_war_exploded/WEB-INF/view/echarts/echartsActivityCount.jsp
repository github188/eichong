<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script src="<%=basePath%>/res/echarts/js/echarts.js"></script>
<style>
	.pie{width:34%;height:780px;float:left;}
	.topPie{width:100%;height:390px;}
	.downPie{width:100%;height:390px;}
	.lineChart{width:64%;height:650px;float:right;margin-top:100px;margin-left:-200px;}
	.pageContent{height:780px}
	#avtivityName{ text-align:center;font-size:18px}
</style>
<body>
<div class="pageHeader">
	<form id="pagerForm" >
		<div class="searchBar" style="height:60px">
			<table class="searchContent">
				<tbody>
					<tr>
						<td><label><bmtag:message
									messageKey="活动类型" /> </label>
						</td>
						<td><select name="actType" class="select_Style" onchange="getSelect(this,'echartPkActivity')" id="covaStutas">
								<option value="2" ${userCoupon.actType=="2"
									? 'selected="selected"' : ''}>渠道活动</option>
								<option value="1" ${userCoupon.actType==1
									? 'selected="selected"' : ''}>用户活动</option>
									
						</select>
						</td>
						<td><label><bmtag:message
									messageKey="活动名称" /> </label>
						</td>
						<td><select  name="pkActivity"  id="echartPkActivity" style="width:130px;">
							<option value="">-请选择-</option>
							<c:forEach items="${activityList}" var="activity">
								<option value="${activity.pkActivity}" ${activity.pkActivity==userCoupon.pkActivity ? 'selected="selected"' : ''}>${activity.actActivityname}</option>
							</c:forEach>
						</select>
						<td align="right"><input id="echartFormSubmitter" type="button" value="查询"/>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="pie" id="pie">
		<h2 id="avtivityName"></h2>
		<div class="topPie" id="topPie" ></div>
		<div class="downPie" id="downPie" ></div>
	</div>
	<div class="lineChart" id="line">
	</div>
</div>
</body>
<script type="text/javascript">
function getSelect(obj,selectId){
	 if(obj.value==""){
		return ;
	} 
	 $.ajax({
			type:'POST', 
			url:basepath+"/admin/userManager/getActivityList.do", 
			data: {state:obj.value},
			dataType:'json',
			cache: false,
			success: function(datas) {
				if(datas.status==100){
					var data=datas.data;
					var content='<option value="">-请选择-</option>';
					for(var i=0;i<data.length;i++){
						content+='<option value="'+data[i].pkActivity+'">'+data[i].actActivityname+'</option>';
					}
					 $("#"+selectId).html(content);
				}else{
					alertMsg.error(datas.msg);
				}
          }
		});
}
navTab.getCurrentPanel().find("#echartFormSubmitter").click(function(){
	var activityName=$("#echartPkActivity option:selected").html();
	var activityId=$("#echartPkActivity option:selected").val();
	if(activityId){
		$("#avtivityName").html(activityName+"活动统计");
		drowecharts();
	}else{
		require([ 
			        'echarts', 
			        'echarts/theme/macarons',
					'echarts/chart/bar',
			        'echarts/chart/line',
			        'echarts/chart/pie',
			
			], function(echarts, theme) {
				topPie = echarts.init(document.getElementById('topPie'), theme);
				downPie = echarts.init(document.getElementById('downPie'), theme);
				line = echarts.init(document.getElementById('line'), theme);
				$("#avtivityName").html("");
				topPie.clear();
				downPie.clear();
				line.clear();
			});
	}
	
})

var dataStyle = {
	    normal: {
	        label: {show:false},
	        labelLine: {show:false}
	    }
	};
	var placeHolderStyle = {
	    normal : {
	        color: 'rgba(0,0,0,0)',
	        label: {show:false},
	        labelLine: {show:false}
	    },
	    emphasis : {
	        color: 'rgba(0,0,0,0)'
	    }
	};
	optionPie = {
	    tooltip : {
	        show: true,
	        formatter: "{a} <br/>{b} "
	    },
	    legend: {
	        orient : 'vertical',
	        x : document.getElementById('pie').offsetWidth/1.48,
	        y : 150,
	        itemGap:12,
	        data:['68%的人表示过的不错','29%的人表示生活压力很大','3%的人表示“我姓曾”']
	    },
	    series : [
	        {
	            type:'pie',
	            clockWise:false,
	            radius : [125, 150],
	            itemStyle : dataStyle,
	            data:[
	                {
	                    value:68,
	                    name:'68%的人表示过的不错'
	                },
	                {
	                    value:32,
	                    name:'',
	                    itemStyle : placeHolderStyle
	                }
	            ]
	        },
	        {
	            type:'pie',
	            clockWise:false,
	            radius : [100, 125],
	            itemStyle : dataStyle,
	            data:[
	                {
	                    value:29, 
	                    name:'29%的人表示生活压力很大'
	                },
	                {
	                    value:71,
	                    name:'',
	                    itemStyle : placeHolderStyle
	                }
	            ]
	        },
	        {
	            type:'pie',
	            clockWise:false,
	            radius : [75, 100],
	            itemStyle : dataStyle,
	            data:[
	                {
	                    value:3, 
	                    name:'3%的人表示“我姓曾”'
	                }
	            ]
	        }
	    ]
	};

	optionLine = {
		    legend: {
		        data:['发行数量','使用数量']
		    },
		  
		    calculable : true,
		    tooltip : {
		        trigger: 'axis'
		    },
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		        	  axisLabel : {
		                formatter: '{value} ',
			           // interval :0
		            },
		            data : [],
		        }
		    ],
		    yAxis : [
		        {
		          	name :"数量(张)",
		            type : 'value',
		            axisLine : {onZero: false, show:true}
		        }
		    ],
		    series : [
		        {
		            name:'使用数量',
		            type:'line',
		            smooth:true,
		            itemStyle: {
		                normal: {
		                    lineStyle: {
		                        shadowColor : 'rgba(0,0,0,0.4)'
		                    }
		                }
		            },
		            data:['1','2','3','4']
		        },
		      {
		            name:'发行数量',
		            type:'line',
		            smooth:true,
		            itemStyle: {
		                normal: {
		                    lineStyle: {
		                        shadowColor : 'rgba(0,0,0,0.4)'
		                    }
		                }
		            },
		            data:['10','20','30','40']
		        }
		    ]
		};
		                    
	
	
require.config({
	paths : {
		echarts : '<%=basePath%>/res/echarts/js',
	}
});
function drowecharts() {
	require([ 
	        'echarts', 
	        'echarts/theme/macarons',
			'echarts/chart/bar',
	        'echarts/chart/line',
	        'echarts/chart/pie',
	
	], function(echarts, theme) {
		topPie = echarts.init(document.getElementById('topPie'), theme);
		downPie = echarts.init(document.getElementById('downPie'), theme);
		line = echarts.init(document.getElementById('line'), theme);
		initChart();
	});
}
function initChart() {
	if (topPie) {
		topPie.clear();
		downPie.clear();
		line.clear();
	}
	var ajaxUrl = "<%=basePath%>admin/activity/getEchartCount.do";
	var actType = $("#covaStutas").val();
	var pkActivity = $("#echartPkActivity").val();
	//获取饼图数据
	$.ajax({
		type : 'POST',
		url : ajaxUrl,
		data:{
			actType:actType,
			pkActivity:pkActivity
		},
		dataType : "json",
		cache : false,
		success : function(json) {
			drowTopPie(json.pie);
			drowDownPie(json.pie);
			drowSplitDate(json.line);
		}
	});
	
}	
	
	function drowTopPie(json){
		var option=optionPie;
		option.legend.data[0]="发行优惠劵 "+json.allCouponCount+"张";
		option.legend.data[1]="使用优惠劵 "+json.usedCouponCount+"张";
		if(json.allCouponCount!=0){
			option.legend.data[2]="优惠劵使用率"+(json.usedCouponCount/json.allCouponCount*100).toFixed(2)+"%";
		}else{
			option.legend.data[2]="优惠劵使用率0%"
		}
		option.series[2].data[0].value=json.allCouponCount;
		option.series[2].data[0].name="发行优惠劵 "+json.allCouponCount+"张";
		option.series[1].data[0].value=json.usedCouponCount;
		option.series[1].data[0].name="使用优惠劵 "+json.usedCouponCount+"张";
		option.series[1].data[1].value=json.allCouponCount-json.usedCouponCount;
		option.series[1].data[1].name="未使用优惠劵 "+(json.allCouponCount-json.usedCouponCount)+"张";
		option.series[0].data[0].value=(json.usedCouponCount/json.allCouponCount*100).toFixed(2);
		option.series[0].data[0].name="优惠劵使用率"+(json.usedCouponCount/json.allCouponCount*100).toFixed(2)+"%";
		option.series[0].data[1].value=((json.allCouponCount-json.usedCouponCount)/json.allCouponCount*100).toFixed(2);
		option.series[0].data[1].name="优惠劵未使用率"+((json.allCouponCount-json.usedCouponCount)/json.allCouponCount*100).toFixed(2)+"%";
		topPie.setOption(option);
		
	}
	
	function drowDownPie(json){
		var option=optionPie;
		option.legend.data[0]="发行优惠劵金额 "+json.allCouponValue+"元";
		option.legend.data[1]="使用优惠劵金额 "+json.usedCouponValue+"元";
		if(json.allCouponCount!=0){
			option.legend.data[2]="优惠劵金额使用率"+(json.usedCouponValue/json.allCouponValue*100).toFixed(2)+"%";
		}else{
			option.legend.data[2]="优惠劵金额使用率0%"
		}
		option.series[2].data[0].value=json.allCouponCount;
		option.series[2].data[0].name="发行优惠劵金额 "+json.allCouponValue+"元";
		option.series[1].data[0].value=json.usedCouponCount;
		option.series[1].data[0].name="使用优惠劵金额 "+json.usedCouponValue+"元";
		option.series[1].data[1].value=json.allCouponValue-json.usedCouponValue;
		option.series[1].data[1].name="未使用优惠劵金额 "+(json.allCouponValue-json.usedCouponValue)+"元";
		option.series[0].data[0].value=(json.usedCouponValue/json.allCouponValue*100).toFixed(2);
		option.series[0].data[0].name="优惠劵金额使用率"+(json.usedCouponValue/json.allCouponValue*100).toFixed(2)+"%";
		option.series[0].data[1].value=((json.allCouponValue-json.usedCouponValue)/json.allCouponValue*100).toFixed(2);
		option.series[0].data[1].name="优惠劵金额未使用率"+((json.allCouponValue-json.usedCouponValue)/json.allCouponValue*100).toFixed(2)+"%";
		downPie.setOption(option);
		
	}
	
	function drowSplitDate(json){
		//alert(JSON.stringify(json))
		var option=optionLine;
		option.xAxis[0].data=json.date;
		option.series[0].data=json.val1;
		option.series[1].data=json.val0;
		line.setOption(option);
	}	
	
	
	
	
	
	
	
</script>
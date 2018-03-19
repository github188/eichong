$(function() {
	$(".mainTab span").on("click", function() {
		$(this).addClass('active').siblings().removeClass('active');
		var index = $(this).index();
		$(".mainTabBlock").eq(index).show().siblings().hide();
	})
})
userRegisteCount();
function userRegisteCount(){
	$.ajax({
		type:"get",
		url: basePath + userRegisteCountUrl,
		dataType : 'json',
		success : function(datas) {
			var req=datas.data;
			$("#todayRegisterCount").html(req.v1);			
			$("#yesterdayRegisterCount").html(req.v2);			
			$("#totalRegisterCount").html(req.v3);			
		}
	});
}
//七天之内的==========
var myChart;
var forMonthChart;
var latestDataObj;
var UserRegisteForMonthObj;
initUserRegisteLatest();
userRegisteLatest();
//每月注册数==========
initUserRegisteForMonth();
userRegisteForMonth();
function userRegisteLatest(){
	$.ajax({
		type:"get",
		url: basePath + userRegisteLatestUrl,
		dataType : 'json',
		success : function(datas) {
			var req=datas.data;
			latestDataObj.legend.data=req.legend;
			for(var i=0;i<req.columns.length;i++){
				latestDataObj.xAxis[0].data[i]=req.columns[i];
			}
			for(var j=0;j<req.legend.length;j++){
				for(var key in req){
					if(key==req.legend[j]){
						var obj={
							name:key,
							type:"line",
							data:req[key]
						};
						latestDataObj.series[j]=obj;
					}
				}
			}
			myChart.setOption(latestDataObj);
		}
	});
}

function initUserRegisteLatest(ec) {
	myChart = echarts.init(document.getElementById('latestData'));
	latestDataObj = {
		title: {
			text:'最近7天注册',
			x:'center',
			y:'top',
			textStyle: {
				color: '#333',
				fontStyle: 'normal',
				fontWeight: 'normal',
				fontFamily: '微软雅黑',
				fontSize: 14
			}
		},
		color:['#2ec7c9','#ff8900','#b7a3df','#6cbce9'],
		tooltip: {
			trigger: 'axis',
			axisPointer:{
			    type: 'line',
			    lineStyle: {
			        color: '#cccccc',
			        width: 1,
			        type: 'dashed'
			    }
			} 
		},
		legend: {
			bottom:2,
			data: []
		},
		grid: {
			show:false,
			left: '0%',
			right: '0%',
			bottom: '10%',
			containLabel: true
		},
		xAxis: [{
			type: 'category',
			boundaryGap: true,
			data: [],
			axisLine:{
            	show:false
           },
           splitLine:{ 
                show:false
           },
           axisTick:{
		        show:false
		   }
		}],
		yAxis: [{
			type: 'value',
			minInterval:1,
	        axisLine:{
	        	show:false,
	        },
	        axisTick:{
		        show:false
		    }
		}],
		series: []
	};
	
	return latestDataObj;
}
function initUserRegisteForMonth(ec) {
	forMonthChart = echarts.init(document.getElementById('mounthData'));
	userRegisteForMonthObj = {
		title: {
			text:'每月注册',
			x:'center',
			y:'top',
			textStyle: {
				color: '#333',
				fontStyle: 'normal',
				fontWeight: 'normal',
				fontFamily: '微软雅黑',
				fontSize: 14
			}
		},
		color:['#2ec7c9','#ff8900','#b7a3df','#6cbce9'],
		tooltip: {
			trigger: 'axis',
			axisPointer:{
			    type: 'line',
			    lineStyle: {
			        color: '#cccccc',
			        width: 1,
			        type: 'dashed'
			    }
			} 
		},
		legend: {
			bottom:2,
			data: [],
		},
		toolbox: {},
		grid: {
			show:false,
			left: '0%',
			right: '0%',
			bottom: '10%',
			containLabel: true
		},
		xAxis: [{
			type: 'category',
			boundaryGap: true,
			data: [],
			axisLine:{
            	show:false
           },
           splitLine:{ 
                show:false
           },
           axisTick:{
		        show:false
		   }
			
		}],
		yAxis: [{
			type: 'value',
			axisLine:{
	        	show:false,
	        },
	        axisTick:{
		        show:false
		    }
		}],
		series: []
	};
	return userRegisteForMonthObj;
}
function userRegisteForMonth(){
	$.ajax({
		type:"get",
		url: basePath + userRegisteForMonthUrl,
		dataType : 'json',
		success : function(datas) {
			var req=datas.data;
			userRegisteForMonthObj.legend.data=req.legend;
			for(var i=0;i<req.columns.length;i++){
				userRegisteForMonthObj.xAxis[0].data[i]=req.columns[i];
			}
			for(var j=0;j<req.legend.length;j++){
				for(var key in req){
					if(key==req.legend[j]){
						var obj={
							name:key,
							type:"bar",
							data:req[key],
							stack:'总量',
							itemStyle: {
					            normal: {
					                barBorderRadius: 5
					            },
					            emphasis: {
					                barBorderRadius: 5
					            }
					       },
						};
						userRegisteForMonthObj.series[j]=obj;
					}
				}
			}
			forMonthChart.setOption(userRegisteForMonthObj);
		}
	});
}


initTable("","appRegisterListPage",userRegisteListUrl,userRegisteListCallback);
function userRegisteListCallback(req){
	var data=req.data;
	var pageNum=req.pager.pageNum;
	var listTr="";
	for(var i=0;i<data.length;i++){
		listTr+='<tr><td>'
				+new Date(data[i].v1).format("yyyy-MM-dd")+'</td><td>'
				+data[i].v2+'</td><td>'
				+data[i].v3+'</td></tr>';
	}
	$("#appRegisterTbody").html(listTr);
}
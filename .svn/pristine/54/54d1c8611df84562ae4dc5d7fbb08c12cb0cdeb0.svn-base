$(function(){
	$(".mainTab span").on("click",function(){
		$(this).addClass('active').siblings().removeClass('active');
		var index=$(this).index();
		$(".mainTabBlock").eq(index).show().siblings().hide();
	})
})
function historyData(){
	$.ajax({
		type:"post",
		url: basePath + historyDataUrl,
		data:{
			startDate:$("#startDateStatisticHistory").val(),
			endDate:$("#endDateStatisticHistory").val()
		},
		dataType : 'json',
		success : function(datas) {
			var req=datas.data;
			$("#payValue").html(req.v1);			
			$("#payCount").html(req.v2);			
			$("#chargeValue").html(req.v3);			
			$("#chargeCount").html(req.v4);			
		}
	});
}

function historyDataListCallback(req){
	var data=req.data;
	var listTr="";
	for(var i=0;i<data.length;i++){
		listTr+='<tr><td>'
				+new Date(data[i].v1).format("yyyy-MM-dd")+'</td><td>'
				+data[i].v2+'</td><td>'
				+data[i].v3+'</td><td>'
				+data[i].v4+'</td><td>'
				+data[i].v5+'</td><td>'
				+data[i].v6+'</td><td>'
				+data[i].v7+'</td></tr>';
	}
	$("#historyDataTbody").html(listTr);
}

//充值金额&次数==============================================================
var historyDataChart;
initPayTimes();
function initPayTimes(){
	historyDataChart = echarts.init(document.getElementById('payTimes'));
	historyDataObj = {
		title: {
			text:'充值',
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
			bottom:0,
			data: []
		},
		toolbox: {},
		grid: {
			show:false,
			left: '2%',
			right: '2%',
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
//			name:'充值金额',
			position:'left',
			axisLine:{
	        	show:false,
	        },
	        axisTick:{
		        show:false
		    }
		},
		{
			type: 'value',
//			name:'充值次数',
			position:'right',
			axisLine:{
	        	show:false,
	        },
	        axisTick:{
		        show:false
		    }
		}],
		series: []
	};
	return historyDataObj;
}
function PayTimes(){
	$.ajax({
		type:"get",
		url: basePath + historyChargeDataForDayUrl,
		data:{
			startDate:$("#startDateStatisticHistory").val(),
			endDate:$("#endDateStatisticHistory").val()
		},
		dataType : 'json',
		success : function(datas) {
			if (datas.status != 100) {
				initPayTimes();
				return;
			}
			var req=datas.data;
			historyDataObj.legend.data=req.legend;
			for(var i=0;i<req.columns.length;i++){
				historyDataObj.xAxis[0].data[i]=req.columns[i];
			}
			for(var j=0;j<req.legend.length;j++){
				for(var key in req){
					if(key==req.legend[j]){
						var obj={
							name:key,
							type:"line",
							data:req[key],
							yAxisIndex:j,
							itemStyle: {
					            normal: {
					                barBorderRadius: 5
					            },
					            emphasis: {
					                barBorderRadius: 5
					            }
					       },
						};
						historyDataObj.series[j]=obj;
					}
					
				}
			}
			historyDataChart.setOption(historyDataObj);
		}
	});
}


//充电金额&次数==================================================
var historyChargeDataForDayChart;
initchargeTimes();
function initchargeTimes(){
	historyChargeDataForDayChart = echarts.init(document.getElementById('chargeTimes'));
	historyChargeDataForDayObj = {
		title: {
			text:'充电',
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
			left: '2%',
			right: '2%',
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
//			name:'充电金额',
			position:'left',
			axisLine:{
	        	show:false,
	        },
	        axisTick:{
		        show:false
		    }
		},
		{
			type: 'value',
//			name:'充值次数',
			position:'right',
			axisLine:{
	        	show:false,
	        },
	        axisTick:{
		        show:false
		    }
		}],
		series: []
	};
	return historyChargeDataForDayObj;
}
function chargeTimes(){
	$.ajax({
		type:"get",
		url: basePath + historyDataForDayUrl,
		data:{
			startDate:$("#startDateStatisticHistory").val(),
			endDate:$("#endDateStatisticHistory").val()
		},
		dataType : 'json',
		success : function(datas) {
			if (datas.status != 100) {
				initchargeTimes();
				return;
			}
			var req=datas.data;
			historyChargeDataForDayObj.legend.data=req.legend;
			for(var i=0;i<req.columns.length;i++){
				historyChargeDataForDayObj.xAxis[0].data[i]=req.columns[i];
			}
			for(var j=0;j<req.legend.length;j++){
				for(var key in req){
					if(key==req.legend[j]){
						var obj={
							name:key,
							type:"line",
							data:req[key],
							yAxisIndex:j,
							itemStyle: {
					            normal: {
					                barBorderRadius: 5
					            },
					            emphasis: {
					                barBorderRadius: 5
					            }
					       },
						};
						historyChargeDataForDayObj.series[j]=obj;
					}
					
				}
			}
			historyChargeDataForDayChart.setOption(historyChargeDataForDayObj);
		}
	});
}
loadHistory();
function loadHistory(){
	historyData();
	PayTimes();
	chargeTimes();
	initTable("statisticHistoryForm","historyDataPage",historyDataListUrl,historyDataListCallback);
}
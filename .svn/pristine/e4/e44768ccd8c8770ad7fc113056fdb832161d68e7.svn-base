realTimeDate();
function realTimeDate(){
	$.ajax({
		type:"get",
		url: basePath + realtimeDataUrl,
		dataType : 'json',
		success : function(datas) {
			if(datas.status!=100){
				return;
			}
			var req=datas.data;
			$("#payValue").html(req.v1);			
			$("#payCount").html(req.v2);			
			$("#chargeValue").html(req.v3);			
			$("#chargeCount").html(req.v4);			
		}
	});
}
realTimeDateListCallback();
function realTimeDateListCallback(){
	$.ajax({
		type:"get",
		url: basePath + realtimeDataListUrl,
		dataType : 'json',
		success : function(datas) {
			var data=datas.data;
			var listTr="";
			for(var i=0;i<data.length;i++){
				listTr+='<tr><td>'
						+data[i].v1+'</td><td>'
						+data[i].v2+'</td><td>'
						+data[i].v3+'</td><td>'
						+data[i].v4+'</td><td>'
						+data[i].v5+'</td></tr>';
			}
			$("#realTimeTbody").html(listTr);	
		}
	});
	
}
initRealtimeDataForHour();
realtimeDataForHour();
var realtimeDataChart;
function initRealtimeDataForHour(ec) {
	realtimeDataChart = echarts.init(document.getElementById('realtimeData'));
	realtimeDataChartObj = {
		title: {
			text:'充电（实时）',
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
			bottom:'0',
			data: [],
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
	return realtimeDataChartObj;
}
function realtimeDataForHour(){
	$.ajax({
		type:"get",
		url: basePath + realtimeDataForHourUrl,
		dataType : 'json',
		success : function(datas) {
			if (datas.status != 100) {
				layer.alert(datas.msg, {
					offset : '100px',
					time : 2000,
					closeBtn : 2
				});
				return;
			}
			var req=datas.data;
			realtimeDataChartObj.legend.data=req.legend;
			for(var i=0;i<req.columns.length;i++){
				realtimeDataChartObj.xAxis[0].data[i]=req.columns[i];
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
						realtimeDataChartObj.series[j]=obj;
					}
					
				}
			}
			realtimeDataChart.setOption(realtimeDataChartObj);
		}
	});
}
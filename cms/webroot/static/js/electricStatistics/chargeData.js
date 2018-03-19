$(function(){
	$(".mainTab span").on("click",function(){
		$(this).addClass('active').siblings().removeClass('active');
		var index=$(this).index();
		$(".mainTabBlock").eq(index).show().siblings().hide();
	})
})
loadProvince("chargeData");
//充电统计四个数据=================================
chargeData();
function chargeData(){
	$.ajax({
		type:"get",
		url: basePath + chargeDataUrl,
		data:{
			provinceCode:$("#chargeDataProvince").val(),
			cityCode:$("#chargeDataCity").val()
		},
		dataType : 'json',
		success : function(datas) {
			var req=datas.data;
			$("#v1").html(req.v1);			
			$("#v2").html(req.v2);			
			$("#v3").html(req.v3);			
			$("#v4").html(req.v4);			
		}
	});
}
//列表详细==========================================
//initTable("statisticChargeDataForm","chargeDataPage",chargeDataDetailUrl,chargeDataCallback);
function chargeDataCallback(req){
	var data=req.data;
	var pageNum=req.pager.pageNum;
	var listTr="";
	for(var i=0;i<data.length;i++){
		listTr+='<tr><td>'
				+new Date(data[i].time).format("yyyy-MM-dd")+'</td><td>'
				+data[i].v1+'</td><td>'
				+data[i].v2+'</td><td>'
				+data[i].v3+'</td><td>'
				+data[i].v4+'</td></tr>';
	}
	$("#chargeDataTbody").html(listTr);
}
//充电====
var chargeCount;
var pieChart;
var chargeCountObj;
var pieChartOption;
initChargeCount();
ajaxChargeCount();
initPieChart();
ajaxPieChart();
function initChargeCount(){
	chargeCount = echarts.init(document.getElementById('chargeCount'));
	chargeCountObj = {
		title: {
			text:'充电度数',
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
			left: '0%',
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
		}],
		series: []
	};
	
	return chargeCountObj;
}
function ajaxChargeCount(){
	$.ajax({
		type:"get",
		url: basePath + chargeDataLineUrl,
		dataType : 'json',
		data:{
			provinceCode:$("#chargeDataProvince").val(),
			cityCode:$("#chargeDataCity").val()
		},
		success : function(datas) {
			var req=datas.data;
			chargeCountObj.legend.data=req.legend;
			for(var i=0;i<req.columns.length;i++){
				chargeCountObj.xAxis[0].data[i]=req.columns[i];
			}
			for(var key in req){
				if(key==req.legend){
					var obj={
						name:req.legend,
						type:"line",
						data:req[key]
					};
					chargeCountObj.series[0]=obj;
				}
			}
			chargeCount.setOption(chargeCountObj);
		}
	});
}
//占比====
function initPieChart(){
	pieChart = echarts.init(document.getElementById('pieChart'));
	pieChartOption={
		    title : {},
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        left: 'center',
		        data: []
		    },
		    series : []
	};
	return pieChartOption;
}
function ajaxPieChart(){
	$.ajax({
		type:"get",
		url: basePath + chargeDataPieUrl,
		dataType : 'json',
		data:{
			provinceCode:$("#chargeDataProvince").val(),
			cityCode:$("#chargeDataCity").val()
		},
		success : function(datas) {
			var req=datas.data;
			pieChartOption.legend.data=req.legend;
			var obj={
				name:"交直流充电占比",
				type:"pie",
				data:[],
				radius : '55%',
				center: ['50%', '60%'],
				itemStyle: {
			        normal: {
			            barBorderRadius: 5
			        },
			        emphasis: {
			            barBorderRadius: 5
			        }
			   }
			};
			for(var j=0;j<req.legend.length;j++){
				for(var key in req){
					if(key==req.legend[j]){
						obj.data[j]={value:req[key],name:key};
					}
				}
			}
			pieChartOption.series[0]=obj;
			pieChart.setOption(pieChartOption);
		}
	});
}
chargeDataSearch();
function chargeDataSearch(){
	chargeData();
	ajaxChargeCount();
	ajaxPieChart();
	initTable("statisticChargeDataForm","chargeDataPage",chargeDataDetailUrl,chargeDataCallback);
}

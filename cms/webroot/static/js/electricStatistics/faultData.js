loadProvince("faultData");
//基础10个故障=================================
faultData();
function faultData(){
	$.ajax({
		type:"get",
		url: basePath + faultDataCountUrl,
		data:{
			provinceCode:$("#faultDataProvince").val(),
			cityCode:$("#faultDataCity").val()
		},
		dataType : 'json',
		success : function(datas) {
			var req=datas.data;
			$("#v1").html(req.v1);			
			$("#v2").html(req.v2);			
			$("#v3").html(req.v3);			
			$("#v4").html(req.v4);			
			$("#v5").html(req.v5);			
			$("#v6").html(req.v6);			
			$("#v7").html(req.v7);			
			$("#v8").html(req.v8);			
			$("#v9").html(req.v9);			
			$("#v10").html(req.v10);			
		}
	});
}
//列表详细==========================================
//initTable("statisticFaultDataForm","faultDataPage",faultDataDetailUrl,faultDataDetailCallback);
function faultDataDetailCallback(req){
	var data=req.data;
	var pageNum=req.pager.pageNum;
	var listTr="";
	for(var i=0;i<data.length;i++){
		listTr+='<tr><td>'
				+new Date(data[i].time).format("yyyy-MM-dd hh:mm:ss")+'</td><td>'
				+data[i].v1+'</td><td>'
				+data[i].v2+'</td><td>'
				+data[i].v3+'</td></tr>';
	}
	$("#faultDataTbody").html(listTr);
}
//饼状图=============================================
var pieChart;
var pieChartOption;
initPieChart();
ajaxPieChart();
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
		url: basePath + faultDataPieUrl,
		dataType : 'json',
		data:{
			provinceCode:$("#faultDataProvince").val(),
			cityCode:$("#faultDataCity").val()
		},
		success : function(datas) {
			var req=datas.data;
			pieChartOption.legend.data=req.legend;
			var obj={
				name:"故障占比",
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
faultDataSearch();
function faultDataSearch(){
	faultData();
	ajaxPieChart();
	initTable("statisticFaultDataForm","faultDataPage",faultDataDetailUrl,faultDataDetailCallback);
}

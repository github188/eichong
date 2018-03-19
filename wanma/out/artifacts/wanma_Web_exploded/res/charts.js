(function($) {
	$.extend($.fn, {
		//基本饼图
		//参数： title			标题 
		//		subTitle		子标题
		//		dataLabelEnable 是否显示标签
		//		hoverLabel		悬浮标签名称
		//		dataUrl			数据URL
        basicPieChart: function(conf) {
        	// Radialize the colors
        	$this = $(this);
        	if(conf.dataUrl){
        		$.ajax({
        			url: conf.dataUrl, 
        			data: {}, 
        			type: 'POST',
        			async: false,
        			success: function(data){
	        			$this.highcharts({
	                        chart: {
	                            plotBackgroundColor: null,
	                            plotBorderWidth: null,
	                            plotShadow: false
	                        },
	                        title: {
	                            text: conf.title||'Default Title' //设置标题，默认为Default Title
	                        },
	                        subtitle: {
	                        	text: conf.subTitle||'Default Title' //设置标题，默认为Default Title
	                        },
	                        tooltip: {
	                    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>' 
	                        },
	                        plotOptions: {
	                            pie: {
	                                allowPointSelect: true,
	                                cursor: 'pointer',
	                                events: {
	                                	click: conf.clickHandler
	                                },
	                                dataLabels: {
	                                    enabled: conf.dataLabelEnable, //是否显示数据标签
	                                    color: '#000000',
	                                    connectorColor: '#000000',
	                                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
	                                },
	                                showInLegend: true
	                            }
	                        },
	                        series: [{
	                            type: 'pie',
	                            name: conf.hoverLabel, 			//数据标签
	                            data: eval("("+data+")")        //数据，例如[{name:'test',y:0},{name:'test',y:0}]
	                        }]
	                    });
        			}
        		});
        	}
        	//end if
        },
        //		基本线图
        //		title			标题 
        //		subTitle		子标题
		//		xAxis			X轴标签
        //		yAxis			Y轴标签
        //		hoverSuffix		悬浮数据单位
		//		hoverLabel		悬浮标签名称
		//		dataUrl			数据		
        basicLineChart: function(conf){
        	$this = $(this);
        	if(conf.dataUrl){
        		$.ajax({
        			url: conf.dataUrl, 
        			data: {}, 
        			type: 'POST',
        			async: false,
        			success: function(data){
	        			$this.highcharts({
	        	            title: {
	        	                text: conf.title||'Default Title'
	        	            },
	        	            subtitle: {
	        	                text: conf.subTitle||'Default Title'
	        	            },
	        	            xAxis: {
	        	                categories: conf.xAxis
	        	            },
	        	            yAxis: {
	        	                title: {
	        	                    text: conf.yAxis
	        	                },
	        	                plotLines: [{
	        	                    value: 0,
	        	                    width: 1,
	        	                    color: '#808080'
	        	                }]
	        	            },
	        	            tooltip: {
	        	                valueSuffix: conf.hoverSuffix
	        	            },
	        	            legend: {
	        	                layout: 'horizontal',
	        	                align: 'center',
	        	                borderWidth: 0
	        	            },
	        	            series: eval("("+data+")")
	        	        });
	        		}
        		})
        	}//end if
        },
        //		基本柱图
        //		title			标题 
        //		subTitle		子标题
		//		xAxis			X轴标签
        //		yAxis			Y轴标签
        //		hoverSuffix		悬浮数据单位
		//		hoverLabel		悬浮标签名称
		//		dataUrl			数据	
        basicColumnChart: function(conf){
        	$this = $(this);
        	if(conf.dataUrl){
        		$.ajax({
        			url: conf.dataUrl, 
        			data: {}, 
        			type: 'POST',
        			async: false,
        			success: function(data){
	        			$this.highcharts({
	        				chart: {
	        	                type: 'column'
	        	            },
	        	            title: {
	        	                text: conf.title||'Default Title'
	        	            },
	        	            subtitle: {
	        	                text: conf.subTitle||'Default Title'
	        	            },
	        	            xAxis: {
	        	                categories: conf.xAxis
	        	            },
	        	            yAxis: {
	        	                title: {
	        	                    text: conf.yAxis
	        	                },
	        	                plotLines: [{
	        	                    value: 0,
	        	                    width: 1,
	        	                    color: '#808080'
	        	                }]
	        	            },
	        	            tooltip: {
	        	                valueSuffix: conf.hoverSuffix
	        	            },
	        	            legend: {
	        	                layout: 'horizontal',
	        	                align: 'center',
	        	                borderWidth: 0
	        	            },
	        	            series: eval("("+data+")")
	        	        });
	        		}
        		})
        	}//end if
        }
	});
})(jQuery);

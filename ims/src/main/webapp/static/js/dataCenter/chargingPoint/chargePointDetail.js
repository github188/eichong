/**
 * Created by Administrator on 2018/2/28.
 */


$(function(){
    clearInterval(timer);
    var timer = setInterval(function(){
        var headId = window.localStorage.getItem('headId');
        var epCode = window.localStorage.getItem('epCode');
        toLoadReadTime(headId,epCode);
        getDetailStaticData(epCode);
    },2000*60);

    //枪口输出
    var volOption = {
        backgroundColor: '#f7f7f7',//背景色
        title: {
            text: '',
            textStyle: {
                fontSize: 12,
            },
            left: 'center'

        },
        series: [
            {
                name: '',
                type: 'gauge',
                splitNumber: 5,
                detail: {
                    formatter: '{value}',
                    textStyle: {
                        color: 'auto',
                        fontSize : 18
                    }
                },
                data: [{value: 0, name: ''}]
            }
        ]
    };
    var currentOption = {
        backgroundColor: '#f7f7f7',//背景色
        title: {
            text: '',
            textStyle: {
                fontSize: 12
            },
            left: 'center'

        },
        series: [
            {
                name: '',
                type: 'gauge',
                splitNumber: 5,
                detail: {
                    formatter: '{value}',
                    textStyle: {
                        color: 'auto',
                        fontSize : 18
                    }
                },
                data: [{value: 0, name: ''}]
            }
        ]
    };
    var allChargeOption = {
        backgroundColor: '#f7f7f7',//背景色
        title: {
            text: '',
            textStyle: {
                fontSize: 12
            },
            left: 'center'

        },
        series: [
            {
                name: '',
                type: 'gauge',
                splitNumber: 5,
                detail: {
                    formatter: '{value}',
                    textStyle: {
                        color: 'auto',
                        fontSize : 18
                    }
                },
                data: [{value: 0, name: ''}]
            }
        ]
    };
    var powerRateOption = {
        backgroundColor: '#f7f7f7',//背景色
        title: {
            text: '',
            textStyle: {
                fontSize: 12
            },
            left: 'center'

        },
        series: [
            {
                name: '',
                type: 'gauge',
                splitNumber: 5,
                detail: {
                    formatter: '{value}',
                    textStyle: {
                        color: 'auto',
                        fontSize : 18
                    }
                },
                data: [{value: 0, name: ''}]
            }
        ]
    };
    var voltageChart;
    var currentChart;
    var powerRateChart;
    var allChargeChart;

    //输出电流
    var outputCurrentChart;
    var outputCurrentObj;
    initOutputCurrent();

    //输出功率
    var outputPowerChart;
    var outputPowerObj;
    //initOutputPower();

    //输出电压
    var outputVoltageChart;
    var outputVoltageObj;
    //initOutputVoltage();

    //输出电压
    var temperatureChart;
    var temperatureObj;
    //initTemperature();

    //三项电流
    var threePhaseCurrentChart;
    var threePhaseCurrentObj;
    //initThreePhaseCurrent();

    //三项电压
    var threePhaseVoltageChart;
    var threePhaseVoltageObj;
    //initThreePhaseVoltage()


    //输出枪口折线
    //输出电流
    function initOutputCurrent(ec){
        outputCurrentChart = echarts.init(document.getElementById('outputCurrent'));
        outputCurrentChart.resize();
        outputCurrentObj = {
            title: {
                text: '',
                x: 'center',
                y: 'top',
                textStyle: {
                    color: '#333',
                    fontStyle: 'normal',
                    fontWeight: 'normal',
                    fontFamily: '微软雅黑',
                    fontSize: 14
                }
            },
            color: ['#ff7d00', '#ff8900', '#b7a3df', '#6cbce9'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'line',
                    lineStyle: {
                        color: '#cccccc',
                        width: 1,
                        type: 'dashed'
                    }
                }
            },
            legend: {
                bottom: 2,
                data: []
            },
            grid: {
                show: false,
                left: '50',
                right: '40',
                bottom: '10%',
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                show:false,
                boundaryGap: true,
                data: [],
                axisLine: {
                    show: false
                },
                splitLine: {
                    show: false
                },
                axisTick: {
                    show: false
                }
            }],
            yAxis: [{
                type: 'value',
                show:false,
                minInterval: 1,
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                }
            }],
            series: [{
                data: [],
                type: 'line',
                smooth: true
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        return outputCurrentObj;
    }

    //输出功率
    function initOutputPower(ec){
        outputPowerChart = echarts.init(document.getElementById('outputPower'));
        outputPowerChart.resize();
        outputPowerObj = {
            title: {
                text: '',
                x: 'center',
                y: 'top',
                textStyle: {
                    color: '#333',
                    fontStyle: 'normal',
                    fontWeight: 'normal',
                    fontFamily: '微软雅黑',
                    fontSize: 14
                }
            },
            color: ['#ff7d00', '#ff8900', '#b7a3df', '#6cbce9'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'line',
                    lineStyle: {
                        color: '#cccccc',
                        width: 1,
                        type: 'dashed'
                    }
                }
            },
            legend: {
                bottom: 2,
                data: []
            },
            grid: {
                show: false,
                left: '50',
                right: '40',
                bottom: '10%',
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                show:false,
                boundaryGap: true,
                data: [],
                axisLine: {
                    show: false
                },
                splitLine: {
                    show: false
                },
                axisTick: {
                    show: false
                }
            }],
            yAxis: [{
                type: 'value',
                show:false,
                minInterval: 1,
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                }
            }],
            series: [{
                data: [],
                type: 'line',
                smooth: true
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        return outputPowerObj;
    }

    //输出电压
    function initOutputVoltage(ec){
        outputVoltageChart = echarts.init(document.getElementById('outputVoltage'));
        outputVoltageChart.resize();
        outputVoltageObj = {
            title: {
                text: '',
                x: 'center',
                y: 'top',
                textStyle: {
                    color: '#333',
                    fontStyle: 'normal',
                    fontWeight: 'normal',
                    fontFamily: '微软雅黑',
                    fontSize: 14
                }
            },
            color: ['#ff7d00', '#ff8900', '#b7a3df', '#6cbce9'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'line',
                    lineStyle: {
                        color: '#cccccc',
                        width: 1,
                        type: 'dashed'
                    }
                }
            },
            legend: {
                bottom: 2,
                data: []
            },
            grid: {
                show: false,
                left: '50',
                right: '40',
                bottom: '10%',
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                show:false,
                boundaryGap: true,
                data: [],
                axisLine: {
                    show: false
                },
                splitLine: {
                    show: false
                },
                axisTick: {
                    show: false
                }
            }],
            yAxis: [{
                type: 'value',
                show:false,
                minInterval: 1,
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                }
            }],
            series: [{
                data: [],
                type: 'line',
                smooth: true
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        return outputVoltageObj;
    }

    //输出温度
    function initTemperature(ec){
        temperatureChart = echarts.init(document.getElementById('temperature'));
        temperatureChart.resize();
        temperatureObj = {
            title: {
                text: '',
                x: 'center',
                y: 'top',
                textStyle: {
                    color: '#333',
                    fontStyle: 'normal',
                    fontWeight: 'normal',
                    fontFamily: '微软雅黑',
                    fontSize: 14
                }
            },
            color: ['#ff7d00', '#ff8900', '#b7a3df', '#6cbce9'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'line',
                    lineStyle: {
                        color: '#cccccc',
                        width: 1,
                        type: 'dashed'
                    }
                }
            },
            legend: {
                bottom: 2,
                data: []
            },
            grid: {
                show: false,
                left: '50',
                right: '40',
                bottom: '10%',
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                show:false,
                boundaryGap: true,
                data: [],
                axisLine: {
                    show: false
                },
                splitLine: {
                    show: false
                },
                axisTick: {
                    show: false
                }
            }],
            yAxis: [{
                type: 'value',
                show:false,
                minInterval: 1,
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                }
            }],
            series: [{
                data: [],
                type: 'line',
                smooth: true
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        return temperatureObj;
    }

    //三项电流
    function initThreePhaseCurrent(ec){
        threePhaseCurrentChart = echarts.init(document.getElementById('threePhaseCurrent'));
        threePhaseCurrentChart.resize();
        threePhaseCurrentObj = {
            title: {
                text: '',
                x: 'center',
                y: 'top',
                textStyle: {
                    color: '#333',
                    fontStyle: 'normal',
                    fontWeight: 'normal',
                    fontFamily: '微软雅黑',
                    fontSize: 14
                }
            },
            color: ['#ff7d00', '#ff8900', '#b7a3df', '#6cbce9'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'line',
                    lineStyle: {
                        color: '#cccccc',
                        width: 1,
                        type: 'dashed'
                    }
                }
            },
            legend: {
                bottom: 2,
                data: []
            },
            grid: {
                show: false,
                left: '50',
                right: '40',
                bottom: '10%',
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                show:false,
                boundaryGap: true,
                data: [],
                axisLine: {
                    show: false
                },
                splitLine: {
                    show: false
                },
                axisTick: {
                    show: false
                }
            }],
            yAxis: [{
                type: 'value',
                show:false,
                minInterval: 1,
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                }
            }],
            series: [{
                data: [],
                type: 'line',
                smooth: true
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        return threePhaseCurrentObj;
    }

    //三项电压
    function initThreePhaseVoltage(ec){
        threePhaseVoltageChart = echarts.init(document.getElementById('threePhaseVoltage'));
        threePhaseVoltageChart.resize();
        threePhaseVoltageObj = {
            title: {
                text: '',
                x: 'center',
                y: 'top',
                textStyle: {
                    color: '#333',
                    fontStyle: 'normal',
                    fontWeight: 'normal',
                    fontFamily: '微软雅黑',
                    fontSize: 14
                }
            },
            color: ['#ff7d00', '#ff8900', '#b7a3df', '#6cbce9'],
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'line',
                    lineStyle: {
                        color: '#cccccc',
                        width: 1,
                        type: 'dashed'
                    }
                }
            },
            legend: {
                bottom: 2,
                data: []
            },
            grid: {
                show: false,
                left: '50',
                right: '40',
                bottom: '10%',
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                show:false,
                boundaryGap: true,
                data: [],
                axisLine: {
                    show: false
                },
                splitLine: {
                    show: false
                },
                axisTick: {
                    show: false
                }
            }],
            yAxis: [{
                type: 'value',
                show:false,
                minInterval: 1,
                axisLine: {
                    show: false
                },
                axisTick: {
                    show: false
                }
            }],
            series: [{
                data: [],
                type: 'line',
                smooth: true
            }]
        };
        // 使用刚指定的配置项和数据显示图表。
        return threePhaseVoltageObj;
    }

    toLoadStationInfo();
    function toLoadStationInfo(){
        var powerstationId = window.localStorage.getItem('powerstationId');
        if(powerstationId){
            toLoadStationHeadInfo(powerstationId,1);
        }
    }
    function toLoadStationHeadInfo(powerStationId,page){//充电点首页左侧
        $.ajax({
            type: "post",
            url: basePath + getPowerStationPileHeadDetailUrl,
            async: false,
            data: {
                powerStationId: powerStationId,
                pageNo:page
            },
            success: function (req) {
                if( req.success==true ){
                    var data = req.dataObject;
                    if (req.pager) {
                        initStationHeadPage(req.pager,powerStationId);
                    }
                    var powerStationName=data[0].powerStationName;
                    $('.powerStationName').html(powerStationName);
                    var stationHeadLi='';
                    for(var i=0;i<data.length;i++){
                        var chargingModeHtml='';
                        if(data[i].chargingMode==5){
                            chargingModeHtml='直流';
                        }else if(data[i].chargingMode==14){
                            chargingModeHtml='交流';
                        }
                        var headStatusHtml='';
                        if(data[i].headStatus==0){
                            headStatusHtml='空闲';
                        }else if(data[i].headStatus==6){
                            headStatusHtml='充电';
                        }else if(data[i].headStatus==9){
                            headStatusHtml='故障';
                        }else if(data[i].headStatus==17){
                            headStatusHtml='等待';
                        }
                        if(data[i].headStatus==9){
                            stationHeadLi+='<li class="stationHeadLi faultTips row" data-option="'+ data[i].headId +'" data-headNum="'+data[i].headNum+'" data-epCode="'+data[i].epCode+'" style="margin: 0;">'
                                + '<span class="headNumCon col-sm-4 col-md-4 col-xs-4">'+'<span class="gun">'+data[i].pileNum+'#'+'</span>'+'枪口'+ data[i].headNum +'</span>'
                                + '<span class="chargingModeCon col-sm-4 col-md-4 col-xs-4">'+ chargingModeHtml+data[i].powerSize +'</span>'
                                + '<span class="headStatusCon col-sm-4 col-md-4 col-xs-4 state'+data[i].headStatus+' " data-option="'+data[i].headStatus+'">'+'<i class="circle"></i>'+ headStatusHtml +'</span></li>'
                        }else{
                            stationHeadLi+='<li class="stationHeadLi row" data-option="'+ data[i].headId +'" data-epCode="'+data[i].epCode+'" style="margin: 0;">'
                                + '<span class="headNumCon col-sm-4 col-md-4 col-xs-4">'+'<span class="gun">'+data[i].pileNum+'#'+'</span>'+'枪口'+ data[i].headNum +'</span>'
                                + '<span class="chargingModeCon col-sm-4 col-md-4 col-xs-4">'+ chargingModeHtml+data[i].powerSize +'</span>'
                                + '<span class="headStatusCon col-sm-4 col-md-4 col-xs-4 state'+data[i].headStatus+' " data-option="'+data[i].headStatus+'">'+'<i class="circle"></i>'+ headStatusHtml +'</span></li>'
                        }
                    }
                    $('#stationHeadUl').html(stationHeadLi);
                }else if (req.status == 9001) {
                    layer.closeAll();
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '160px'],//宽高
                        content: '会话超时，请重新登陆！',
                        btn: ["确定"],
                        yes:function(index,layero){
                            layer.closeAll();
                            window.top.location.href = basePath + toLoginUrl;
                        },
                        cancel:function(index,layero){
                            layer.closeAll();
                            window.top.location.href = basePath + toLoginUrl;
                        }
                    });

                }else {
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '160px'],//宽高
                        content: req.msg,
                        time: 3000,
                        btn: ["确定"]
                    });
                }
            }
        });
    }
    //分页
    function initStationHeadPage(pager,powerStationId) {
        var options = {
            containerClass: "pagination",
            currentPage: pager.pageNo,
            numberOfPages: 5,
            totalPages: pager.pageTotal,
            totalCount: pager.total,
            pageUrl: function (type, page){
                return null;
            },
            onPageClicked: function (event, originalEvent, type, page) {
                toLoadStationHeadInfo(powerStationId,page);
            }
        };
        $('#stationDetailPage').bootstrapPaginator(options);
    }

    //点击充电点列表获取数据
    $("body").off("click", ".stationHeadLi").on("click", ".stationHeadLi", function() {
        var headId =$(this).attr('data-option');
        var epCode = $(this).attr('data-epCode');
        window.localStorage.setItem('headId', headId);
        window.localStorage.setItem('epCode', epCode);
        var headId = window.localStorage.getItem('headId');
        var epCode = window.localStorage.getItem('epCode');
        toLoadReadTime(headId,epCode);
    });


    //电桩基本信息
    toLoadElectricPileData(headId,epCode);
    function toLoadElectricPileData(headId,epCode){
        $.ajax({
            type: "post",
            url: '',
            async: false,
            data: {
                headId: headId,
                epCode:epCode
            },
            success: function (req) {
                if( req.success==true ){
                    var data = req.dataObject;

                }
            }
        })
    }

    //基本信息
    var headId = window.localStorage.getItem('headId');
    var epCode = window.localStorage.getItem('epCode');
    var headNum = window.localStorage.getItem('headNum');
    //console.log(headNum);
    toLoadReadTime(headNum,epCode);
    function toLoadReadTime(headNum,epCode){
        $.ajax({
            type: "post",
            url: basePath + getHeadRealTimeInfoUrl,
            async: false,
            data: {
                headId: headNum,
                epCode:epCode
            },
            success: function (req) {
                if( req.success==true ){
                    var data = req.dataObject;
                    //车辆信息
                    $('.vin').html(data.vin);//vin码
                    $('.batteryType').html(data.batteryType);//电池类型
                    $('.batterycapacity').html(data.batterycapacity);//电池容量

                    //充电订单信息
                    $('.chargeCode').html(data.chargeCode);//订单编号
                    $('.beginChargeTime').html(data.beginChargeTime);//开始充电时间
                    $('.userAccount').html(data.userAccount);//用户账号
                    $('.rateInfoId').html(data.rateInfoId);//费率id
                    $('.tradeType').html(data.tradeType);//账户类型
                    $('.chargeFrozenAmt').html(data.userAccount);//预冻金额
                    $('.cardNumber').html(data.cardNumber);//卡号

                    //电费&服务费
                    $('.raIn_TipTimeTariffPrice').html(data.rateInfo.raIn_TipTimeTariffPrice);
                    $('.raIn_TipTimeTariffMoney').html(data.rateInfo.raIn_TipTimeTariffMoney);
                    $('.raIn_PeakElectricityPrice').html(data.rateInfo.raIn_PeakElectricityPrice);
                    $('.raIn_PeakElectricityMoney').html(data.rateInfo.raIn_PeakElectricityMoney);
                    $('.raIn_UsualPrice').html(data.rateInfo.raIn_UsualPrice);
                    $('.raIn_UsualMoney').html(data.rateInfo.raIn_UsualMoney);
                    $('.raIn_ValleyTimePrice').html(data.rateInfo.raIn_ValleyTimePrice);
                    $('.raIn_ValleyTimeMoney').html(data.rateInfo.raIn_ValleyTimeMoney);

                    //电桩实时信息
                    $('.powerHighestTemperature').html(data.powerHighestTemperature);//电源单体最高温度
                    $('.chargeTime').html(data.chargeTime);//本次已充电
                    $('.restTime').html(data.restTime);//剩余充电时间
                    $('.isError').html(data.isError);//有无故障
                    $('.errorType').html(data.errorType);//故障类型

                    //车辆实时信息
                    $('.beginSoc').html(data.soc);//开始充电SOC
                    $('.carTotalVoltage ').html(data.carTotalVoltage );//车端总电压
                    $('.bpHighestVoltage').html(data.bpHighestVoltage);//单体最高电压和组号
                    $('.bpHighestTemperature').html(data.bpHighestTemperature);//单体电池最高温度
                    $('.bpLowestTemperature').html(data.bpLowestTemperature);//单体电池最低温度

                    //输出电流、输出电压、输出功率、输出总电量刻度表
                    var voltageValue = parseFloat(data.voltageValue).toFixed(2);
                    var currentValue = parseFloat(data.currentValue).toFixed(2);
                    var presentChargeValue = parseFloat(data.presentChargeValue).toFixed(2);
                    var powerRateValue = parseFloat(parseFloat(voltageValue) * parseFloat(currentValue)/1000).toFixed(2);
                    drawVoltageChart(voltageValue);
                    drawCurrentChart(currentValue);
                    drawAllChargeChart(presentChargeValue);
                    drawPowerRateChart(powerRateValue);

                    //输出枪口折线
                    //输出枪口折线切换
                    $(".mainTab span").on("click", function () {
                        $(this).addClass('active').siblings().removeClass('active');
                        var index = $(this).index();
                        $(".mainTabBlock").eq(index).show().siblings().hide();
                        var spanText=$(this).text();

                        if(spanText=="输出电流"){
                            outputCurrentObj =  initOutputCurrent();
                            //输出电
                            outputCurrentObj.series[0].data = data.outputCurrent.value;
                            outputCurrentChart.clear();
                            outputCurrentChart.setOption(outputCurrentObj);

                        }else if(spanText=="输出功率"){
                            outputPowerObj = initOutputPower();
                            //输出功率
                            //console.log(outputPowerObj);
                            outputPowerObj.series[0].data = data.outputPower.value;
                            outputPowerChart.clear();
                            outputPowerChart.setOption(outputPowerObj);
                        }else if(spanText=="输出电压"){
                            outputVoltageObj =initOutputVoltage();
                            //输出电压
                            outputVoltageObj.series[0].data = data.outputVoltage.value;
                            outputVoltageChart.clear();
                            outputVoltageChart.setOption(outputVoltageObj);
                        }else if(spanText=="输出温度"){
                           temperatureObj= initTemperature();
                            //输出温度
                            temperatureObj.series[0].data = data.temperature.value;
                            temperatureChart.clear();
                            temperatureChart.setOption(temperatureObj);
                        }else if(spanText=="三项电流"){
                            threePhaseCurrentObj=initThreePhaseCurrent();
                            //三项电流
                            threePhaseCurrentObj.series[0].data = data.threePhaseCurrent.value;
                            threePhaseCurrentChart.clear();
                            threePhaseCurrentChart.setOption(threePhaseCurrentObj);
                        }else if(spanText=="三项电压"){
                            threePhaseVoltageObj = initThreePhaseVoltage();
                            //三项电压
                            threePhaseVoltageObj.series[0].data = data.threePhaseVoltage.value;
                            threePhaseVoltageChart.clear();
                            threePhaseVoltageChart.setOption(threePhaseVoltageObj);
                        }
                        //toLoadReadTime(headId,epCode);

                    })
                    //初始化加载
                    $(".mainTab span:eq(0)").click();

                }else{
                    currentValue=0;
                    voltageValue=0;
                    presentChargeValue=0;
                    powerRateValue=0;
                    drawVoltageChart(voltageValue);
                    drawCurrentChart(currentValue);
                    drawAllChargeChart(presentChargeValue);
                    drawPowerRateChart(powerRateValue);
                }
            }
        })
    }

    //桩体基本信息
    getDetailStaticData(epCode);
    function getDetailStaticData(epCode){
        $.ajax({
            type: "post",
            url: basePath + getDetailStaticDataUrl,
            async: false,
            data: {
                epCode: epCode
            },
            success: function (req) {
                if (req.success == true) {
                    var data = req.dataObject;
                    if(data.chargeMode == 5 ){
                        $('.chargeMode').html('直流')
                    }else if(data.chargeMode == 14){
                        $('.chargeMode').html('交流')
                    }
                    $('.epCodeMa').html(data.epCode);
                    $('.headNum').html(data.headNum);
                    $('.power').html(data.power);
                    $('.pileMaker').html(data.pileMaker);
                    $('.creatDate').html(data.creatDate);

                    if(data.type == 11){
                        $('.type').html('一体式');
                    }else if(data.type == 1){
                        $('.type').html('落地式');
                    }else if(data.type == 2){
                        $('.type').html('壁挂式');
                    }else if(data.type == 12){
                        $('.type').html('分体式');
                    }

                    if(data.picImgList == null){
                        $('.zhuang').attr('src','../../../images/electric_pile.png');
                    }
                }
            }
        })
    }

    //初始化电压表
    function drawVoltageChart(vol) {
        voltageChart = echarts.init(document.getElementById('voltageDiv'), 'macarons');
        voltageChart.resize();
        if (voltageChart) {
            voltageChart.clear();
        }
        var series = volOption.series[0];
        series.name = '电压';
        series.min = 0;
        series.max = 850;
        series.data[0].value = vol;
        volOption.title.text = '输出电压(V)';
        voltageChart.setOption(volOption);

    }
    //初始化电流表
    function drawCurrentChart(currentValue) {
        currentChart = echarts.init(document.getElementById('currentDiv'), 'macarons');
        currentChart.resize();
        if (currentChart) {
            currentChart.clear();
        }
        var series = currentOption.series[0];
        series.name = '电流';
        series.min = 0;
        series.max = 125;
        series.data[0].value = currentValue;
        currentOption.title.text = '输出电流(A)';
        currentChart.setOption(currentOption);
    }
    //初始化总电量表
    function drawAllChargeChart(presentChargeValue) {
        allChargeChart = echarts.init(document.getElementById('allChargeDiv'), 'macarons');
        allChargeChart.resize();

        if (allChargeChart) {
            allChargeChart.clear();
        }
        var series = allChargeOption.series[0];
        series.name = '输出总电量';
        series.min = 0;
        series.max = 500;
        series.data[0].value = presentChargeValue;
        allChargeOption.title.text = '输出总电量(KWh)';
        allChargeChart.setOption(allChargeOption);
    }
    //初始化功率表
    function drawPowerRateChart(powerRateValue) {
        powerRateChart = echarts.init(document.getElementById('powerRateDiv'), 'macarons');
        powerRateChart.resize();
        if (powerRateChart) {
            powerRateChart.clear();
        }
        var series = powerRateOption.series[0];
        series.name = '输出功率（KW）';
        series.min = 0;
        series.max = 1000;
        series.data[0].value = powerRateValue;
        powerRateOption.title.text = '输出功率（KW）';
        powerRateChart.setOption(powerRateOption);
    }





})





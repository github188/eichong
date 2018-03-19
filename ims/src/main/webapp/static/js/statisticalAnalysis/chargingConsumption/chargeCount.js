$(function(){
    $(".spanBtn").on("click",function(){
        $(this).addClass("activeBtn").siblings().removeClass('activeBtn');
        var btnIndex = $(this).index();
        if(btnIndex==0){
            $('#cpyBlock').css('display','block');
            $('#stationBlock').css('display','none');
            cpyChargeSearch();
            //cpyChargeValueChart.resize();
            picResize();
            $("#cpyTab span").eq(0).addClass('active').siblings().removeClass('active');
            $(".cpyChart").eq(0).show().siblings().hide();
            var spanText=$(this).text();
            if(spanText=="充电电量"){
                initCpyChargeValueObj();
                cpyChargeValue();
            }else if(spanText=="充电次数"){
                initCpyChargeTimesObj();
                cpyChargeTimes();
            }else if(spanText=="充电金额"){
                initCpyChargeMoneyObj();
                cpyChargeMoney();
            }
        }else if(btnIndex==1){
            $('#cpyBlock').css('display','none');
            $('#stationBlock').css('display','block');
            stationChargeSearch();
            //stationChargeValueChart.resize();
            picResize();
            $("#stationTab span").eq(0).addClass('active').siblings().removeClass('active');
            $(".stationChart").eq(0).show().siblings().hide();
            var spanText=$(this).text();
            if(spanText=="充电电量"){
                initStationChargeValueObj();
                stationChargeValue();
            }else if(spanText=="充电次数"){
                initStationChargeTimesObj();
                stationChargeTimes();
            }else if(spanText=="充电金额"){
                initStationChargeMoneyObj();
                stationChargeMoney();
            }
        }
    })
    initInputSearch();
    setTimeout('toLoadAllSearch()',100);
})
//时间的默认值
function initInputSearch(){
    var nowTime =new Date().getTime();
    var etTime = nowTime-3600*24*1000;
    var stTime = nowTime-3600*24*1000*7;
    var et =new Date(etTime).format("yyyy-MM-dd");
    var st =new Date(stTime).format("yyyy-MM-dd");
    $('#datePicker').val(st+' - '+et);
}

$("#cpyTab span").on("click", function () {
    $(this).addClass('active').siblings().removeClass('active');
    var index = $(this).index();
    $(".cpyChart").eq(index).show().siblings().hide();
    var spanText=$(this).text();
    if(spanText=="充电电量"){
        initCpyChargeValueObj();
        cpyChargeValue();
    }else if(spanText=="充电次数"){
        initCpyChargeTimesObj();
        cpyChargeTimes();
    }else if(spanText=="充电金额"){
        initCpyChargeMoneyObj();
        cpyChargeMoney();
    }
})
$("#stationTab span").on("click", function () {
    $(this).addClass('active').siblings().removeClass('active');
    var index = $(this).index();
    $(".stationChart").eq(index).show().siblings().hide();
    var spanText=$(this).text();
    if(spanText=="充电电量"){
        initStationChargeValueObj();
        stationChargeValue();
    }else if(spanText=="充电次数"){
        initStationChargeTimesObj();
        stationChargeTimes();
    }else if(spanText=="充电金额"){
        initStationChargeMoneyObj();
        stationChargeMoney();
    }

})

function toLoadAllSearch(){
    $('#cpyFirstTag').show().addClass('active').siblings().removeClass('active');
    $('#cpyChargeValueData').show().siblings().hide();
    if($("#cpyCharge").hasClass("activeBtn")){
        setTimeout('cpyChargeSearch()', 200);
    }
    $('#stationFirstTag').show().addClass('active').siblings().removeClass('active');
    $('#stationChargeValueData').show().siblings().hide();
    if($("#powerStation").hasClass("activeBtn")){
        setTimeout('stationChargeSearch()', 300);
    }
}
function cpyChargeSearch() {
    var datePickerValue=$('#datePicker').val();
    if(datePickerValue==''){
        initInputSearch();
        toGiveHiddenInput();
        initTable("chargeCountForm", "chargeCpyPage", getReportChargeDetailUrl, cpyChargeListCallback);
    }else{
        toGiveHiddenInput();
        initTable("chargeCountForm", "chargeCpyPage", getReportChargeDetailUrl, cpyChargeListCallback);
    }

}
function stationChargeSearch(){
    var datePickerValue=$('#datePicker').val();
    if(datePickerValue==''){
        initInputSearch();
        toGiveHiddenInput();
        initTable("chargeCountForm", "chargeStationPage", getPowerStationChargeDataDetailUrl, stationChargeListCallback);
        initStationChargeValueObj();
    }else{
        toGiveHiddenInput();
        initTable("chargeCountForm", "chargeStationPage", getPowerStationChargeDataDetailUrl, stationChargeListCallback);
        initStationChargeValueObj();
    }

}

function cpyChargeListCallback(req) {
    //console.log("11111")
    var data = req.dataObject;
    //var pageNum = req.pager.pageNo;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        key = data[i];
        listTr += '<tr><td>' + key['rowNo']
            + '</td><td>' + key['cpyName']
            + '</td><td>' + key['A']
            + '</td><td>' + key['C']
            + '</td><td>' + key['D']
            + '</td></tr>';
    }
    $("#myTbogy").html(listTr);
    initCpyChargeValueObj();
    cpyChargeValue();
}
function stationChargeListCallback(req) {
    var data = req.dataObject;
    var pageNum = req.pager.pageNo;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        key = data[i];
        listTr += '<tr>'
            + '<td>' + (i+1+(pageNum-1)*20)
            + '</td><td>' + key['powerStationName']
            + '</td><td>' + key['A']
            + '</td><td>' + key['C']
            + '</td><td>' + key['D']
            + '</td></tr>';
    }
    $("#myTbody").html(listTr);

    stationChargeValue();
}

//初始化值
function toGiveHiddenInput() {
    getDateValue();
}
//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        toLoadAllSearch();
    }
});
//日期选择
laydate.render({
    elem: '#datePicker'
    , range: true,
    theme: '#ff7d00'
    //, min: -60,
    ,max: -1//0 代表今天 -1代表昨天，-2代表前天，以此类推
    ,change: function(value, date, endDate){

    }
});
function getDateValue() {
    var dateValue = $('#datePicker').val();
    if (dateValue == "") {
        $('input[name=startGmtCreate]').val('');
        $('input[name=endGmtCreate]').val('');
    } else {
        var dateValue = $('#datePicker').val();
        var startGmtCreate = dateValue.substring(0, 10);
        var endGmtCreate = dateValue.substring(13, 23);
        $('input[name=startGmtCreate]').val(startGmtCreate);
        $('input[name=endGmtCreate]').val(endGmtCreate);
    }
}

//充电度数======渠道
var cpyChargeValueChart;
var cpyChargeValueObj;
//充电次数
var cpyChargeTimesChart;
var cpyChargeTimesObj;
//充电金额
var cpyChargeMoneyChart;
var cpyChargeMoneyObj;


//充电电量======充电点
var stationChargeValueChart;
var stationChargeValueObj;
//initStationChargeValueObj();
//充电次数
var stationChargeTimesChart;
var stationChargeTimesObj;
//充电金额
var stationChargeMoneyChart;
var stationChargeMoneyObj;

//充电度数渲染====渠道
function cpyChargeValue() {
    toGiveHiddenInput();
    var index = layer.load(1);
    $.ajax({
        type: "POST",
        url: basePath + getReportChargeDataUrl,
        dataType: 'json',
        data: {
            startGmtCreate: $('input[name=startGmtCreate]').val(),
            endGmtCreate: $('input[name=endGmtCreate]').val(),
            type: 'A'
        },
        success: function (datas) {
            layer.close(index);
            var req=datas.dataObject;
            cpyChargeValueObj.legend.data=req.legend;
            for(var i=0;i<req.columns.length;i++){
                cpyChargeValueObj.xAxis[0].data[i]=req.columns[i];
            }
            for(var j=0;j<req.legend.length;j++){
                for(var key in req){
                    if(key==req.legend[j]){
                        var obj={
                            name:key,
                            type:"bar",
                            barMaxWidth:40,//最大宽度
                            itemStyle: {
                                normal: {
                                    //柱形图圆角，初始化效果
                                    barBorderRadius:[4, 4, 4, 4]
                                }
                            },
                            data:req[key]
                        };
                        cpyChargeValueObj.series[j]=obj;
                    }
                }
            }
            cpyChargeValueChart.clear();
            cpyChargeValueChart.resize();
            cpyChargeValueChart.setOption(cpyChargeValueObj);
        }
    });
}
//充电次数渲染
function cpyChargeTimes() {
    toGiveHiddenInput();
    var index = layer.load(1);
    $.ajax({
        type: "POST",
        url: basePath + getReportChargeDataUrl,
        dataType: 'json',
        data: {
            startGmtCreate: $('input[name=startGmtCreate]').val(),
            endGmtCreate: $('input[name=endGmtCreate]').val(),
            type: 'C'
        },
        success: function (datas) {
            layer.close(index);
            var req=datas.dataObject;
            cpyChargeTimesObj.legend.data=req.legend;
            for(var i=0;i<req.columns.length;i++){
                cpyChargeTimesObj.xAxis[0].data[i]=req.columns[i];
            }
            for(var j=0;j<req.legend.length;j++){
                for(var key in req){
                    if(key==req.legend[j]){
                        var obj={
                            name:key,
                            type:"bar",
                            barMaxWidth:40,//最大宽度
                            itemStyle: {
                                normal: {
                                    //柱形图圆角，初始化效果
                                    barBorderRadius:[4, 4, 4, 4]
                                }
                            },
                            data:req[key]
                        };
                        cpyChargeTimesObj.series[j]=obj;
                    }
                }
            }
            cpyChargeTimesChart.clear();
            cpyChargeTimesChart.setOption(cpyChargeTimesObj);
        }
    });
}
//充电金额渲染
function cpyChargeMoney() {
    toGiveHiddenInput();
    var index = layer.load(1);
    $.ajax({
        type: "POST",
        url: basePath + getReportChargeDataUrl,
        dataType: 'json',
        data: {
            startGmtCreate: $('input[name=startGmtCreate]').val(),
            endGmtCreate: $('input[name=endGmtCreate]').val(),
            type: 'D'
        },
        success: function (datas) {
            layer.close(index);
            var req=datas.dataObject;
            cpyChargeMoneyObj.legend.data=req.legend;
            for(var i=0;i<req.columns.length;i++){
                cpyChargeMoneyObj.xAxis[0].data[i]=req.columns[i];
            }
            for(var j=0;j<req.legend.length;j++){
                for(var key in req){
                    if(key==req.legend[j]){
                        var obj={
                            name:key,
                            type:"bar",
                            barMaxWidth:40,//最大宽度
                            itemStyle: {
                                normal: {
                                    //柱形图圆角，初始化效果
                                    barBorderRadius:[4, 4, 4, 4]
                                }
                            },
                            data:req[key]
                        };
                        cpyChargeMoneyObj.series[j]=obj;
                    }
                }
            }
            cpyChargeMoneyChart.clear();
            cpyChargeMoneyChart.setOption(cpyChargeMoneyObj);
        }
    });
}

//充电电量渲染======充电点
function stationChargeValue() {
    toGiveHiddenInput();
    var index = layer.load(1);
    $.ajax({
        type: "POST",
        url: basePath + getPowerStationChargeDataUrl,
        dataType: 'json',
        data: {
            startGmtCreate: $('input[name=startGmtCreate]').val(),
            endGmtCreate: $('input[name=endGmtCreate]').val(),
            type: 'A'
        },
        success: function (datas) {
            layer.close(index)
            var req=datas.dataObject;
            stationChargeValueObj.legend.data=req.legend;
            for(var i=0;i<req.columns.length;i++){
                stationChargeValueObj.xAxis[0].data[i]=req.columns[i];
            }
            for(var j=0;j<req.legend.length;j++){
                for(var key in req){
                    if(key==req.legend[j]){
                        var obj={
                            name:key,
                            type:"bar",
                            barMaxWidth:40,//最大宽度
                            itemStyle: {
                                normal: {
                                    //柱形图圆角，初始化效果
                                    barBorderRadius:[4, 4, 4, 4]
                                }
                            },
                            data:req[key]
                        };
                        stationChargeValueObj.series[j]=obj;
                    }
                }
            }
            stationChargeValueChart.clear();
            stationChargeValueChart.resize();
            stationChargeValueChart.setOption(stationChargeValueObj);
        }
    });
}
//充电次数渲染======充电点
function stationChargeTimes() {
    toGiveHiddenInput();
    var index = layer.load(1);
    $.ajax({
        type: "POST",
        url: basePath + getPowerStationChargeDataUrl,
        dataType: 'json',
        data: {
            startGmtCreate: $('input[name=startGmtCreate]').val(),
            endGmtCreate: $('input[name=endGmtCreate]').val(),
            type: 'C'
        },
        success: function (datas) {
            layer.close(index);
            var req=datas.dataObject;
            stationChargeTimesObj.legend.data=req.legend;
            for(var i=0;i<req.columns.length;i++){
                stationChargeTimesObj.xAxis[0].data[i]=req.columns[i];
            }
            for(var j=0;j<req.legend.length;j++){
                for(var key in req){
                    if(key==req.legend[j]){
                        var obj={
                            name:key,
                            type:"bar",
                            barMaxWidth:40,//最大宽度
                            itemStyle: {
                                normal: {
                                    //柱形图圆角，初始化效果
                                    barBorderRadius:[4, 4, 4, 4]
                                }
                            },
                            data:req[key]
                        };
                        stationChargeTimesObj.series[j]=obj;
                    }
                }
            }
            stationChargeTimesChart.clear();
            stationChargeTimesChart.setOption(stationChargeTimesObj);
        }
    });
}
//充电金额渲染======充电点
function stationChargeMoney() {
    toGiveHiddenInput();
    var index = layer.load(1);
    $.ajax({
        type: "POST",
        url: basePath + getPowerStationChargeDataUrl,
        dataType: 'json',
        data: {
            startGmtCreate: $('input[name=startGmtCreate]').val(),
            endGmtCreate: $('input[name=endGmtCreate]').val(),
            type: 'D'
        },
        success: function (datas) {
            layer.close(index);
            var req=datas.dataObject;
            stationChargeMoneyObj.legend.data=req.legend;
            for(var i=0;i<req.columns.length;i++){
                stationChargeMoneyObj.xAxis[0].data[i]=req.columns[i];
            }
            for(var j=0;j<req.legend.length;j++){
                for(var key in req){
                    if(key==req.legend[j]){
                        var obj={
                            name:key,
                            type:"bar",
                            barMaxWidth:40,//最大宽度
                            itemStyle: {
                                normal: {
                                    //柱形图圆角，初始化效果
                                    barBorderRadius:[4, 4, 4, 4]
                                }
                            },
                            data:req[key]
                        };
                        stationChargeMoneyObj.series[j]=obj;
                    }
                }
            }
            stationChargeMoneyChart.clear();
            stationChargeMoneyChart.setOption(stationChargeMoneyObj);
        }
    });
}

//初始化充电电量
function initCpyChargeValueObj(ec) {
    cpyChargeValueChart = echarts.init(document.getElementById('cpyChargeValueData'));
    cpyChargeValueChart.resize();
    cpyChargeValueObj = {
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
        color: ['#ff7d00'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow',
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
            bottom: '20%',
            containLabel: true
        },
        xAxis: [{
            type: 'category',
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
            },
            axisLabel:{
                interval: 0,
                formatter:newline
            }
        }],
        yAxis: [{
            type: 'value',
            minInterval: 1,
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            }
        }],
        series: []
    };
    return cpyChargeValueObj;
}


var newline=function(params){
    //alert(params)
    if(params){
        //var params =cpyChargeValueObj.xAxis[0].data;
        var newParamsName = "";// 最终拼接成的字符串
        var paramsNameNumber = params.length;// 实际标签的个数
        var provideNumber = 5;// 每行能显示的字的个数
        var rowNumber = Math.ceil(paramsNameNumber / provideNumber);// 换行的话，需要显示几行，向上取整
        /**
         * 判断标签的个数是否大于规定的个数， 如果大于，则进行换行处理 如果不大于，即等于或小于，就返回原标签
         */
        // 条件等同于rowNumber>1
        if (paramsNameNumber > provideNumber) {
            /** 循环每一行,p表示行 */
            for (var p = 0; p < rowNumber; p++) {
                var tempStr = "";// 表示每一次截取的字符串
                var start = p * provideNumber;// 开始截取的位置
                var end = start + provideNumber;// 结束截取的位置
                // 此处特殊处理最后一行的索引值
                if (p == rowNumber - 1) {
                    // 最后一次不换行
                    tempStr = params.substring(start, paramsNameNumber);
                } else {
                    // 每一次拼接字符串并换行
                    tempStr = params.substring(start, end) + "\n";
                }
                newParamsName += tempStr;// 最终拼成的字符串
            }

        } else {
            // 将旧标签的值赋给新标签
            newParamsName = params;
        }
        //将最终的字符串返回
        return newParamsName
    }
}



//初始化充电次数
function initCpyChargeTimesObj(ec) {
    cpyChargeTimesChart = echarts.init(document.getElementById('cpyChargeTimesData'));
    cpyChargeTimesChart.resize();
    cpyChargeTimesObj = {
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
        color: ['#ff7d00'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow',
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
            },
            axisLabel:{
                interval: 0,
                formatter:newline
            }
        }],
        yAxis: [{
            type: 'value',
            minInterval: 1,
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            }
        }],
        series: []
    };

    return cpyChargeTimesObj;
}
//初始化充电金额
function initCpyChargeMoneyObj(ec) {
    cpyChargeMoneyChart = echarts.init(document.getElementById('cpyChargeMoneyData'));
    cpyChargeMoneyChart.resize();
    cpyChargeMoneyObj = {
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
        color: ['#ff7d00'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow',
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
            },
            axisLabel:{
                show:true,
                interval: 0,
                formatter:newline
            }
        }],
        yAxis: [{
            type: 'value',
            minInterval: 1,
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            }
        }],
        series: []
    };

    return cpyChargeMoneyObj;
}

//初始化充电电量=====充电点
function initStationChargeValueObj(ec) {
    stationChargeValueChart = echarts.init(document.getElementById('stationChargeValueData'));
    stationChargeValueChart.resize();
    stationChargeValueObj = {
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
        color: ['#ff7d00'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow',
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
            //x: 40,
            //x2: 100,
            //y2: 150,
        },
        xAxis: [{
            type: 'category',
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
            },
            axisLabel:{
                interval: 0,
                formatter:newline
            }
        }],
        yAxis: [{
            type: 'value',
            minInterval: 1,
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            }
        }],
        series: []
    };
    return stationChargeValueObj;
}
//初始化充电次数=====充电点
function initStationChargeTimesObj(ec) {
    stationChargeTimesChart = echarts.init(document.getElementById('stationChargeTimesData'));
    stationChargeTimesChart.resize();
    stationChargeTimesObj = {
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
        color: ['#ff7d00'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow',
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
            },
            axisLabel:{
                show:true,
                interval: 0,
                formatter:newline
            }
        }],
        yAxis: [{
            type: 'value',
            minInterval: 1,
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            }
        }],
        series: []
    };

    return stationChargeTimesObj;
}
//初始化充电金额=====充电点
function initStationChargeMoneyObj(ec) {
    stationChargeMoneyChart = echarts.init(document.getElementById('stationChargeMoneyData'));
    stationChargeMoneyChart.resize();
    stationChargeMoneyObj = {
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
        color: ['#ff7d00'],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow',
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
            },
            axisLabel:{
                show:true,
                interval: 0,
                formatter:newline
            }
        }],
        yAxis: [{
            type: 'value',
            minInterval: 1,
            axisLine: {
                show: false
            },
            axisTick: {
                show: false
            }
        }],
        series: []
    };

    return stationChargeMoneyObj;
}

window.onresize=function(){
    picResize();
}
function picResize(){

    var cpyBlockData=$('#cpyBlock').css('display');
    var stationBlockData=$('#stationBlock').css('display');
    newline();
    if(cpyBlockData=='block'){
        //cpyChargeValueChart.resize();
        var cpyChargeValueData=$('#cpyChargeValueData').css('display');
        var cpyChargeTimesData=$('#cpyChargeTimesData').css('display');
        var cpyChargeMoneyData=$('#cpyChargeMoneyData').css('display');
        if(cpyChargeValueData=='block'){
            cpyChargeValueChart.resize();
            return;
        }
        if(cpyChargeTimesData=='block'){
            cpyChargeTimesChart.resize();
            return;
        }
        if(cpyChargeMoneyData=='block'){
            cpyChargeMoneyChart.resize();
            return;
        }
    }

    if(stationBlockData=='block'){
        //stationChargeValueChart.resize();
        var stationChargeValueData=$('#stationChargeValueData').css('display');
        var stationChargeTimesData=$('#stationChargeTimesData').css('display');
        var stationChargeMoneyData=$('#stationChargeMoneyData').css('display');
        if(stationChargeValueData=='block'){
            stationChargeValueChart.resize();
            return;
        }
        if(stationChargeTimesData=='block'){
            stationChargeTimesChart.resize();
            return;
        }
        if(stationChargeMoneyData=='block'){
            stationChargeMoneyChart.resize();
            return;
        }
    }
}
$(function () {
    $(".mainTab span").on("click", function () {
        $(this).addClass('active').siblings().removeClass('active');
        var index = $(this).index();
        $(".mainTabBlock").eq(index).show().siblings().hide();
        var spanText=$(this).text();
        if(spanText=="充电度数"){
            initcChargeValueObj();
            chargeValue();
        }else if(spanText=="充电次数"){
            initChargeTimesObj();
            chargeTimes();
        }else if(spanText=="充电金额"){
            initChargeMoneyObj();
            chargeMoney();
        }
    })
    initInputSearch();
    toLoadProvince('', '#cpyProvinceCode', '.cpyProvinceUl', 'toUnbindEvent');
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
function toLoadAllSearch(){
    $('#firstTag').show().addClass('active').siblings().removeClass('active');
    $('#chargeValueData').show().siblings().hide();
    setTimeout('cpyChargeSearch()', 100);
}
function cpyChargeSearch() {
    var datePickerValue=$('#datePicker').val();
    if(datePickerValue==''){
        initInputSearch();
        toGiveHiddenInput();
        initTable("cpyChargeForm", "cpyChargePage", getReportCpyDetailUrl, cpyChargeListCallback);
    }else{
        toGiveHiddenInput();
        initTable("cpyChargeForm", "cpyChargePage", getReportCpyDetailUrl, cpyChargeListCallback);
    }

}
function cpyChargeListCallback(req) {
    var data = req.dataObject;
    //var pageNum = req.pager.pageNo;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        key = data[i];
        listTr += '<tr>'
        + '<td>' + key['time']
        + '</td><td>' + key['A']
        + '</td><td>' + key['B']
        + '</td><td>' + key['C']
        + '</td><td>' + key['D']
        + '</td></tr>';
    }
    $("#myTbogy").html(listTr);
    countReportCpy();
}
function toUnbindEvent() {
    $('.cpyProvinceBlock').unbind();
    $('.cypCityBlock').unbind();
    $('.cpyCompanyBlock').unbind();
    selectModel();
}
toUnbindEvent();
//初始化值
function toGiveHiddenInput() {
    var cpyCompanyNameValue = $('#cpyCompanyName').attr('data-cpyNumber');
    if (cpyCompanyNameValue == "") {
        $('input[name=cpyNumber]').val('');
    } else {
        $('input[name=cpyNumber]').val(cpyCompanyNameValue);
    }
    getDateValue();

}
//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        toLoadAllSearch();
    }
});
//思路：先加载省，省的li点击，加载对应的市，市点击加载对应的公司，点击公司加载对应等级
$('.cpyProvinceUl').on("click", "li", function () {
    $('#cypCityCode').html('请选择');
    $('.cypCityUl').html('');

    $('#cpyCompanyName').html('请选择');
    $('.cpyCompanyUl').html('');
    $('#cpyCompanyName').attr('data-cpyNumber', '');
    $('input[name=cpyNumber]').val('');
    var provinceCodeId = $(this).attr('data-option');
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');
    if (flag == "") {
        $('#cypCityCode').html('请选择');
        $('.cypCityUl').html('');
        $('input[name=cpyNumber]').val('');
        $('#cpyCompanyName').html('请选择');
        $('.cpyCompanyUl').html('');
        $('#cpyCompanyName').attr('data-cpyNumber', '');
    } else {
        toLoadCity(provinceCodeId, '', '#cypCityCode', '.cypCityUl', 'toUnbindEvent');
    }
});
$('.cypCityUl').on("click", "li", function () {
    $('#cpyCompanyName').html('请选择');
    $('.cpyCompanyUl').html('');
    $('#cpyCompanyName').attr('data-cpyNumber', '');
    $('input[name=cpyNumber]').val('');
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');
    var cypCityCodeValue = $('#cypCityCode').attr('data-value');
    var cpyProvinceCodeValue = $('#cpyProvinceCode').attr('data-value');
    if (flag == "") {
        $('#cpyCompanyName').html('请选择');
        $('.cpyCompanyUl').html('');
        $('#cpyCompanyName').attr('data-cpyNumber', '');
        $('input[name=cpyNumber]').val('');
    } else {
        toLoadComponyName(cypCityCodeValue, cpyProvinceCodeValue);
    }
});
function toLoadComponyName(cypCityCodeValue, cpyProvinceCodeValue, cpyId) {
    var cypCompanyLi = "";
    var cpyObject = {
        provinceCode: cpyProvinceCodeValue,
        cityCode: cypCityCodeValue
    };
    if (JSON.stringify(cpyObject) == "{}") {
        $('#cpyCompanyName').html("请选择");
    } else {
        toAjaxCompany(cpyObject, cpyId);
    }
}
function toAjaxCompany(cpyObject, cpyId) {
    $.ajax({
        type: "post",
        url: basePath + getCompanyListUrl,
        async: true,
        data: cpyObject,
        success: function (data) {
            if (data.success == true) {
                var dataModule = data.dataObject;
                if (cpyId == '') {
                    var cypCompanyLi = '<li data-option="" data-cpyNumber="">请选择</li>';
                    for (var i = 0; i < dataModule.length; i++) {
                        cypCompanyLi += '<li data-option="' + dataModule[i].cpyId + '" data-cpyNumber="' + dataModule[i].cpyNumber + '">' + dataModule[i].cpyName + '</li>';
                    }
                } else {
                    var cypCompanyLi = '<li data-option="" data-cpyNumber="">请选择</li>';
                    for (var i = 0; i < dataModule.length; i++) {
                        if (dataModule[i].cpyId == cpyId) {
                            $('#cpyCompanyName').html(dataModule[i].cpyId);
                            cypCompanyLi += '<li data-option="' + dataModule[i].cpyId + '" data-cpyNumber="' + dataModule[i].cpyNumber + '" class="data-selected">' + dataModule[i].cpyName + '</li>';
                        } else {
                            cypCompanyLi += '<li data-option="' + dataModule[i].cpyId + '" data-cpyNumber="' + dataModule[i].cpyNumber + '">' + dataModule[i].cpyName + '</li>';
                        }

                    }
                }
                $('.cpyCompanyUl').html(cypCompanyLi);
                toUnbindEvent();
            }
        }
    });
}
$('.cpyCompanyUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option')).attr('data-cpyNumber', $(this).attr('data-cpyNumber'));
    var cpyNumber=$(this).attr('data-cpyNumber');
    if(cpyNumber==''){
        $('input[name=cpyNumber]').val('');
    }
});
//日期选择
laydate.render({
    elem: '#datePicker'
    , range: true,
    theme: '#ff7d00'
    //, min: -60,
    ,max: -1//0 代表今天 -1代表昨天，-2代表前天，以此类推
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
//
function countReportCpy() {
    toGiveHiddenInput();
    $.ajax({
        type: "post",
        url: basePath + countReportCpyUrl,
        dataType: 'json',
        data: {
            cpyNumber: $('input[name=cpyNumber]').val(),
            startGmtCreate: $('input[name=startGmtCreate]').val(),
            endGmtCreate: $('input[name=endGmtCreate]').val()
        },
        success: function (datas) {
            var data = datas.dataObject;
            $('#A').html(data['A']);
            $('#B').html(data['B']);
            $('#C').html(data['C']);
            $('#D').html(data['D']);
            chargeValue();
        }
    });
}

//充电度数==========
var chargeValueChart;
var chargeValueObj;
initcChargeValueObj();
//充电次数
var chargeTimesChart;
var chargeTimesObj;
//充电金额
var chargeMoneyChart;
var chargeMoneyObj;
//充电度数渲染
function chargeValue() {
    toGiveHiddenInput();
    var index = layer.load(1);
    $.ajax({
        type: "POST",
        url: basePath + getReportCpyDataUrl,
        dataType: 'json',
        data: {
            cpyNumber: $('input[name=cpyNumber]').val(),
            startGmtCreate: $('input[name=startGmtCreate]').val(),
            endGmtCreate: $('input[name=endGmtCreate]').val(),
            type: 'A'
        },
        success: function (datas) {
            layer.close(index);
            var req=datas.dataObject;
            chargeValueObj.legend.data=req.legend;
            for(var i=0;i<req.columns.length;i++){
                chargeValueObj.xAxis[0].data[i]=req.columns[i];
            }
            for(var j=0;j<req.legend.length;j++){
                for(var key in req){
                    if(key==req.legend[j]){
                        var obj={
                            name:key,
                            type:"line",
                            data:req[key]
                        };
                        chargeValueObj.series[j]=obj;
                    }
                }
            }
            chargeValueChart.clear();
            chargeValueChart.setOption(chargeValueObj);
        }
    });
}
//充电次数渲染
function chargeTimes() {
    toGiveHiddenInput();
    var index = layer.load(1);
    $.ajax({
        type: "POST",
        url: basePath + getReportCpyDataUrl,
        dataType: 'json',
        data: {
            cpyNumber: $('input[name=cpyNumber]').val(),
            startGmtCreate: $('input[name=startGmtCreate]').val(),
            endGmtCreate: $('input[name=endGmtCreate]').val(),
            type: 'C'
        },
        success: function (datas) {
            layer.close(index);
            var req=datas.dataObject;
            chargeTimesObj.legend.data=req.legend;
            for(var i=0;i<req.columns.length;i++){
                chargeTimesObj.xAxis[0].data[i]=req.columns[i];
            }
            for(var j=0;j<req.legend.length;j++){
                for(var key in req){
                    if(key==req.legend[j]){
                        var obj={
                            name:key,
                            type:"line",
                            data:req[key]
                        };
                        chargeTimesObj.series[j]=obj;
                    }
                }
            }
            chargeTimesChart.clear();
            chargeTimesChart.setOption(chargeTimesObj);
        }
    });
}
//充电金额渲染
function chargeMoney() {
    toGiveHiddenInput();
    var index = layer.load(1);
    $.ajax({
        type: "POST",
        url: basePath + getReportCpyDataUrl,
        dataType: 'json',
        data: {
            cpyNumber: $('input[name=cpyNumber]').val(),
            startGmtCreate: $('input[name=startGmtCreate]').val(),
            endGmtCreate: $('input[name=endGmtCreate]').val(),
            type: 'D'
        },
        success: function (datas) {
            layer.close(index);
            var req=datas.dataObject;
            chargeMoneyObj.legend.data=req.legend;
            for(var i=0;i<req.columns.length;i++){
                chargeMoneyObj.xAxis[0].data[i]=req.columns[i];
            }
            for(var j=0;j<req.legend.length;j++){
                for(var key in req){
                    if(key==req.legend[j]){
                        var obj={
                            name:key,
                            type:"line",
                            data:req[key]
                        };
                        chargeMoneyObj.series[j]=obj;
                    }
                }
            }
            chargeMoneyChart.clear();
            chargeMoneyChart.setOption(chargeMoneyObj);
        }
    });
}
function initcChargeValueObj(ec) {
    chargeValueChart = echarts.init(document.getElementById('chargeValueData'));
    chargeValueChart.resize();
    chargeValueObj = {
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
        color: ['#2ec7c9', '#ff8900', '#b7a3df', '#6cbce9'],
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
            left: '0%',
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

    return chargeValueObj;
}
//初始化充电次数
function initChargeTimesObj(ec) {
    chargeTimesChart = echarts.init(document.getElementById('chargeTimesData'));
    chargeTimesChart.resize();
    chargeTimesObj = {
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
        color: ['#2ec7c9', '#ff8900', '#b7a3df', '#6cbce9'],
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
            left: '0%',
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

    return chargeTimesObj;
}
//初始化充电金额
function initChargeMoneyObj(ec) {
    chargeMoneyChart = echarts.init(document.getElementById('chargeMoneyData'));
    chargeMoneyChart.resize();
    chargeMoneyObj = {
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
        color: ['#2ec7c9', '#ff8900', '#b7a3df', '#6cbce9'],
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
            left: '0%',
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

    return chargeMoneyObj;
}

window.onresize=function(){
    chargeValueChart.resize();
    var chargeValueData=$('#chargeValueData').css('display');
    var chargeTimesData=$('#chargeTimesData').css('display');
    var chargeMoneyData=$('#chargeMoneyData').css('display');
    if(chargeValueData=='block'){
        chargeValueChart.resize();
        return;
    }
    if(chargeTimesData=='block'){
        chargeTimesChart.resize();
        return;
    }
    if(chargeMoneyData=='block'){
        chargeMoneyChart.resize();
        return;
    }

}

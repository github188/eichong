$(function () {
    $(".mainTab span").on("click", function () {
        $(this).addClass('active').siblings().removeClass('active');
        var index = $(this).index();
        $(".mainTabBlock").eq(index).show().siblings().hide();
        var spanText=$(this).text();
        if(spanText=="充电电费"){
            initcChargeValueObj();
            chargeValue();
        }else if(spanText=="充电服务费"){
           /* initChargeTimesObj();
            chargeTimes();*/
        }
    })
    initDatePicker();
    toLoadProvince('', '#cpyProvinceCode', '.cpyProvinceUl', 'toUnbindEvent');
    setTimeout('toLoadAllSearch()',100);
})
//时间的默认值
function initDatePicker(){
    //获取当前月份
    var date=new Date;
    var year=date.getFullYear();
    var month=date.getMonth();//这里不+1，从上个月开始
    if(month==0){
        month="0"+(month+1);
    }else if(month<10){
        month="0"+month;
    }else{
        month=month;
    }
    var et = (year.toString()+'-'+month.toString());
    var fMounth=month-6;
    var fYear='';
    var fMonth='';
    if(fMounth==0){
        fMonth=12;
        fYear=year-1;
        st=(fYear.toString()+'-'+fMonth.toString());
    }
    if(fMounth<0){
        fMonth=12+fMounth;
        if(fMonth>=10){
            fMonth=fMonth;
        }else{
            fMonth='0'+fMonth;
        }
        fYear=year-1;
        st=(fYear.toString()+'-'+fMonth.toString());
    }else{
        fYear=year;
        fMonth='0'+fMounth;
        st=(fYear.toString()+'-'+fMonth.toString());
    }
    //获取当前的前6个月
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
        initDatePicker();
        toGiveHiddenInput();
        initTable("chargeProportionForm", "chargeProportionPage", getReportChargeMoneyDetailUrl, cpyChargeListCallback);
    }else{
        toGiveHiddenInput();
        initTable("chargeProportionForm", "chargeProportionPage", getReportChargeMoneyDetailUrl, cpyChargeListCallback);
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
    , range: true
    ,type: 'month'
    ,theme: '#ff7d00'
});

function getDateValue() {
    var dateValue = $('#datePicker').val();
    if (dateValue == "") {
        $('input[name=startGmtCreateY]').val('');
        $('input[name=endGmtCreateY]').val('');
    } else {
        var dateValue = $('#datePicker').val();
        var startGmtCreate = dateValue.substring(0, 7);
        var endGmtCreate = dateValue.substring(10, 20);
        $('input[name=startGmtCreateY]').val(startGmtCreate);
        $('input[name=endGmtCreateY]').val(endGmtCreate);
    }

}
//
function countReportCpy() {
    toGiveHiddenInput();
    $.ajax({
        type: "post",
        url: basePath + countReportChargeMoneyUrl,
        dataType: 'json',
        data: {
            cpyNumber: $('input[name=cpyNumber]').val(),
            startGmtCreateY: $('input[name=startGmtCreateY]').val(),
            endGmtCreateY: $('input[name=endGmtCreateY]').val()
        },
        success: function (datas) {
            var data = datas.dataObject;
            $('#A').html(data['A']);
            $('#B').html(data['B']);
            $('#C').html(data['C']);
            chargeValue();
        }
    });
}

//充电电费==========
var chargeValueChart;
var chargeValueObj;
initcChargeValueObj();

function chargeValue() {
    toGiveHiddenInput();
    var index = layer.load(1);
    $.ajax({
        type: "POST",
        url: basePath + getReportChargeMoneyDataUrl,
        dataType: 'json',
        data: {
            cpyNumber: $('input[name=cpyNumber]').val(),
            startGmtCreateY: $('input[name=startGmtCreateY]').val(),
            endGmtCreateY: $('input[name=endGmtCreateY]').val(),
            type: 'B'
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
                            name: key,
                            type: 'bar',
                            stack: '总量',
                            data: req[key],
                            itemStyle: {
                                normal: {
                                    //柱形图圆角，初始化效果
                                    barBorderRadius:[4, 4, 4, 4]
                                }
                            }
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
    }

    return chargeValueObj;
}


window.onresize=function(){
    chargeValueChart.resize();
}

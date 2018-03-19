initInputSearch();
setTimeout('AppRechargeSearch()',100);

//时间的默认值
function initInputSearch(){
    var nowTime =new Date().getTime();
    var etTime = nowTime-3600*24*1000;
    var stTime = nowTime-3600*24*1000*7;
    var et =new Date(etTime).format("yyyy-MM-dd");
    var st =new Date(stTime).format("yyyy-MM-dd");
    $('#datePicker').val(st+' - '+et);
}
function AppRechargeSearch() {
    var datePickerValue=$('#datePicker').val();
    if(datePickerValue==''){
        initInputSearch();
        toGiveHiddenInput();
        initTable("AppRechargeForm", "AppRechargePage", appRechargeDataListUrl, AppRechargeListCallback);
    }else{
        toGiveHiddenInput();
        initTable("AppRechargeForm", "AppRechargePage", appRechargeDataListUrl, AppRechargeListCallback);
    }

}
function AppRechargeListCallback(req) {
    var data = req.dataObject;
    //var pageNum = req.pager.pageNo;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        key = data[i];
        listTr += '<tr>'
            + '<td>' + key['time']
            + '</td><td>' + key['WxPay']
            + '</td><td>' + key['WxPayNum']
            + '</td><td>' + key['AliPay']
            + '</td><td>' + key['AliPayNum']
            + '</td></tr>';
    }
    $("#myTbogy").html(listTr);
    countReportAppRecharge();
}
//初始化值
function toGiveHiddenInput() {
    getDateValue();
}
//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        AppRechargeSearch();
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
        $('input[name=beginTime]').val('');
        $('input[name=endTime]').val('');
    } else {
        var dateValue = $('#datePicker').val();
        var startGmtCreate = dateValue.substring(0, 10);
        var endGmtCreate = dateValue.substring(13, 23);
        $('input[name=beginTime]').val(startGmtCreate);
        $('input[name=endTime]').val(endGmtCreate);
    }

}
//加载文字数据
function countReportAppRecharge() {
    toGiveHiddenInput();
    $.ajax({
        type: "post",
        url: basePath + appRechargeDataCountUrl,
        dataType: 'json',
        data: {
            beginTime: $('input[name=beginTime]').val(),
            endTime: $('input[name=endTime]').val()
        },
        success: function (datas) {
            var data = datas.dataObject;
            $('#countYesterdayMoney').html(data.countYesterdayMoney);
            $('#countTotalMoney').html(data.countTotalMoney);
            $('#countYesterday').html(data.countYesterday);
            $('#countTotal').html(data.countTotal);
            chargeValue();
        }
    });
}

//充值金额==========
var chargeValueChart;
var chargeValueObj;
initRechargeMoneyValueObj();

//充值金额渲染
function chargeValue() {
    toGiveHiddenInput();
    var index = layer.load(1);
    $.ajax({
        type: "POST",
        url: basePath + appRechargeDataLineUrl,
        dataType: 'json',
        data: {
            beginTime: $('input[name=beginTime]').val(),
            endTime: $('input[name=endTime]').val()
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

function initRechargeMoneyValueObj(ec) {
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

window.onresize=function(){
    chargeValueChart.resize();
}
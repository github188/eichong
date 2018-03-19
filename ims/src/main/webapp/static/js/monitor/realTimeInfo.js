$(function () {
    $('.mainTabContent').load('realTimeTable.html');
    $(".mainTab span").on("click", function () {
        $(this).addClass('active').siblings().removeClass('active');
        var target = $(this).attr('data-flag');
        $('.mainTabContent').load(target);
    })
    //加载表格
    setTimeout(function () {
        var headId = window.localStorage.getItem('monitor_headId');
        getRunTimeData(headId);
    }, 3200);
})
$('body').on('click', '.goBack', function () {
    var powerStationId = window.localStorage.getItem('monitor_powerStationId');
    window.location.href = "monitorIndex.html?num=1&monitor_powerStationId=" + powerStationId;
});

function getRunTimeData(headId) {
    var index = layer.load(1);
    $.ajax({
        type: "post",
        //url: basePath + getRunTimeDataUrl,
        url:'http://wanma.eichong.com/coffee/manage/monitor/getRunTimeData.do',
        async: true,
        data: {
            pkHeadId: headId
        },
        success: function (data) {
            layer.closeAll();
            if (data.success == true) {
                var data = data.dataObject;
                window.localStorage.setItem('monitor_epType', data.epType);
                window.localStorage.setItem('monitor_epCode', data.epCode);
                var epType = data.epType; //5-直流  14-交流
                var epCode = data.epCode;
                popEditChargeDataTbody(epType, epCode);
            }
        }
    });
}

//根据交直流加载不同表头
function popEditChargeDataTbody(epType, epCode) {
    var epType = epType;//5-直流  14-交流
    var chargeDataTheadHtml = '';
    if (epType == 5) {
        chargeDataTheadHtml = '<tr><th>时间</th><th>电表读数</th><th>已充度数（度）</th>'
        + '<th>已消费金额（元）</th><th>电池电量</th></tr>';
    } else if (epType == 14) {
        chargeDataTheadHtml = '<tr><th>时间</th><th>电表读数</th><th>已充度数（度）</th>'
        + '<th>已消费金额（元）</th></tr>';
    }
    $('#popChargeDataThead').html(chargeDataTheadHtml);
    toRenderPopChargeTable(epType, epCode);
}
//根据交直流加载不同表体
function toRenderPopChargeTable(epType, epCode) {
    var headId = window.localStorage.getItem('monitor_headId');
    $.ajax({
        type: "post",
        url: basePath + getChargingDataUrl,
        async: true,
        data: {
            headId: headId,
            epType: epType,
            epCode: epCode
        },
        success: function (data) {
            var chargeDataThbodyHtml = '';
            if (data.success == true) {
                var data = data.dataObject;
                var chargeData = data.data;
                if (chargeData == null || chargeData == '') {
                    return;
                } else {
                    for (var i = 0; i < chargeData.length; i++) {
                        var chargeDataTime = chargeData[i].ts;
                        if (epType == 5) {
                            chargeDataThbodyHtml += '<tr><td>'
                            + new Date(chargeDataTime).format("hh:MM:ss") + '</td><td>'
                            + chargeData[i].allChargeValue + '</td><td>'
                            + chargeData[i].presentChargeValue + '</td><td>'
                            + chargeData[i].presentChargeFee + '</td><td>'
                            + chargeData[i].soc + '</td></tr>';
                        } else if (epType == 14) {
                            chargeDataThbodyHtml += '<tr><td>'
                            + new Date(chargeDataTime).format("hh:MM:ss") + '</td><td>'
                            + chargeData[i].allChargeValue + '</td><td>'
                            + chargeData[i].presentChargeValue + '</td><td>'
                            + chargeData[i].presentChargeFee + '</td></tr>';
                        }
                    }
                    $('#popChargeDataTbody').html(chargeDataThbodyHtml);
                }

            }
        }
    });
}

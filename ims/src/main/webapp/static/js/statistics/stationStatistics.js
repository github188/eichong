//获取站点充电统计的menuId
var getMenuMap = window.localStorage.getItem('menuMap');
var getCurruntMap = JSON.parse(getMenuMap);
var menuId = getCurruntMap['站点充电统计'];
$(function(){
    ctrlMenu(menuId);
    //setTimeout('statisticsListSearch()', 190);
    setTimeout("toLoadProvince('', '#provinceCode', '.provinceUl', 'toUnbindEvent')", 100);
})
function ctrlMenu(menuId) {
    $.ajax({
        type: "post",
        url: basePath + getSelfButtonTreeUrl,
        async: true,
        data: {
            menuId: menuId
        },
        success: function (req) {
            var data = req.dataObject;
            //console.log(data);
            if(data==null){
                return;
            }
            if (data.length == 0) {
                return;
            } else {
                for (var i = 0; i < data.length; i++) {
                    var contents = data[i].contents;
                    if (contents.indexOf('显示') > -1) {
                        $('#showSet').show();
                    }
                    if (contents.indexOf('导出') > -1) {
                        $('#dataExport').show();
                    }

                }
            }


        }
    });
}
//去加载表格的数据
function statisticsListSearch() {
    toGiveHiddenInput();
    initTable("statisticsListForm", "statisticsListPage", statisticsPowerStationChargeOrderUrl, statisticsListCallback);
}
//下拉选项
toUnbindEvent();
function toUnbindEvent() {
    $('.provinceBlock').unbind();
    $('.cityBlock').unbind();
    selectModel();
}
//表格数据==========================================================
function statisticsListCallback(req) {
    var data = req.dataObject;
    var pageNum = req.pager.pageNo;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        var totalMoney=0;
        var numberMoney=0;
        if(data[i].totalElectricMoney || data[i].totalServiceMoney){
            var numberMoney = parseFloat(data[i].totalElectricMoney) + parseFloat(data[i].totalServiceMoney);
            totalMoney=parseFloat(numberMoney).toFixed(4)
        }else{
            totalMoney='';
        }
        var totalElectric=0;
        if(data[i].totalElectricCharge){
            totalElectric=parseFloat(data[i].totalElectricCharge).toFixed(2)
        }else{
            totalElectric='';
        }
        var totalCost=0;
        if(data[i].totalElectricCharge){
            totalCost=parseFloat(data[i].totalElectricMoney).toFixed(4)
        }else{
            totalCost='';
        }
        var totalServiceCost=0;
        if(data[i].totalServiceMoney){
            totalServiceCost=parseFloat(data[i].totalServiceMoney).toFixed(4)
        }else{
            totalServiceCost='';
        }
        var totalDiscounts=0;
        if(data[i].totalfavMoney){
            totalDiscounts=parseFloat(data[i].totalfavMoney).toFixed(4)
        }else{
            totalDiscounts='';
        }
        var JPower=0;
        if(data[i].JPower){
            JPower=parseFloat(data[i].JPower).toFixed(2)
        }else{
            JPower='';
        }
        var FPower=0;
        if(data[i].FPower){
            FPower=parseFloat(data[i].FPower).toFixed(2)
        }else{
            FPower='';
        }
        var PPower=0;
        if(data[i].PPower){
            PPower=parseFloat(data[i].PPower).toFixed(2)
        }else{
            PPower='';
        }
        var GPower=0;
        if(data[i].GPower){
            GPower=parseFloat(data[i].GPower).toFixed(2)
        }else{
            GPower='';
        }
        var JMoney=0;
        if(data[i].JMoney){
            JMoney=parseFloat(data[i].JMoney).toFixed(4)
        }else{
            JMoney='';
        }
        var FMoney=0;
        if(data[i].FMoney){
            FMoney=parseFloat(data[i].FMoney).toFixed(4)
        }else{
            FMoney='';
        }
        var PMoney=0;
        if(data[i].PMoney){
            PMoney=parseFloat(data[i].PMoney).toFixed(4)
        }else{
            PMoney='';
        }
        var GMoney=0;
        if(data[i].GMoney){
            GMoney=parseFloat(data[i].GMoney).toFixed(4)
        }else{
            GMoney='';
        }
        var JServiceMoney=0;
        if(data[i].JServiceMoney){
            JServiceMoney=parseFloat(data[i].JServiceMoney).toFixed(4)
        }else{
            JServiceMoney='';
        }
        var FServiceMoney=0;
        if(data[i].FServiceMoney){
            FServiceMoney=parseFloat(data[i].FServiceMoney).toFixed(4)
        }else{
            FServiceMoney='';
        }
        var PServiceMoney=0;
        if(data[i].PServiceMoney){
            PServiceMoney=parseFloat(data[i].PServiceMoney).toFixed(4)
        }else{
            PServiceMoney='';
        }
        var GServiceMoney=0;
        if(data[i].GServiceMoney){
            GServiceMoney=parseFloat(data[i].GServiceMoney).toFixed(4)
        }else{
            GServiceMoney='';
        }
        var favTotalMoney=0;
        if(data[i].favMoney || data[i].favServiceMoney ){
            favTotalMoney=(parseFloat( data[i].favMoney)+ parseFloat(data[i].favServiceMoney )).toFixed(4)
        }else{
            favTotalMoney='';
        }
        var favMoney=0;
        if(data[i].favMoney ){
            favMoney=parseFloat(data[i].favMoney).toFixed(4)
        }else{
            favMoney='';
        }
        var favServiceMoney=0;
        if(data[i].favServiceMoney ){
            favServiceMoney=parseFloat(data[i].favServiceMoney).toFixed(4)
        }else{
            favServiceMoney='';
        }

        listTr += '<tr>'
            + '<td class="statisticsList_date">' + data[i].serviceTime
            + '</td><td class="statisticsList_station">' + data[i].powerStationName
            + '</td><td class="statisticsList_city">' + data[i].city
            + '</td><td class="statisticsList_totalMoney">' + totalMoney
            + '</td><td class="statisticsList_totalElectric">' + totalElectric
            + '</td><td class="statisticsList_totalCost">' + totalCost
            + '</td><td class="statisticsList_totalServiceCost">' + totalServiceCost
            + '</td><td class="statisticsList_totalDiscounts">' + totalDiscounts
            + '</td><td class="statisticsList_electricJFPG">' + JPower
            + '</td><td class="statisticsList_electricJFPG">' + FPower
            + '</td><td class="statisticsList_electricJFPG">' + PPower
            + '</td><td class="statisticsList_electricJFPG">' + GPower
            + '</td><td class="statisticsList_costJFPG">' + JMoney
            + '</td><td class="statisticsList_costJFPG">' + FMoney
            + '</td><td class="statisticsList_costJFPG">' + PMoney
            + '</td><td class="statisticsList_costJFPG">' + GMoney
            + '</td><td class="statisticsList_serviceJFPG">'+ JServiceMoney
            + '</td><td class="statisticsList_serviceJFPG">'+ FServiceMoney
            + '</td><td class="statisticsList_serviceJFPG">'+ PServiceMoney
            + '</td><td class="statisticsList_serviceJFPG">'+ GServiceMoney
            + '</td><td class="statisticsList_favTotalMoney">'+ favTotalMoney
            + '</td><td class="statisticsList_favMoney">'+ favMoney
            + '</td><td class="statisticsList_favServiceMoney">'+ favServiceMoney
            + '</td></tr>';
    }
    $("#myTbogy").html(listTr);
    var arr = ['statisticsList_date', 'statisticsList_station', 'statisticsList_city', 'statisticsList_company','statisticsList_payType',
        'statisticsList_totalMoney', 'statisticsList_totalElectric', 'statisticsList_totalCost', 'statisticsList_totalServiceCost',
        'statisticsList_totalDiscounts', 'statisticsList_electricJFPG', 'statisticsList_costJFPG', 'statisticsList_serviceJFPG',
        'statisticsList_favTotalMoney','statisticsList_favMoney','statisticsList_favServiceMoney'];
    toGetLocalStorageInfo(arr);
}

//数据导出	==========================================
$('#dataExport').on("click", function () {
    toGiveHiddenInput();
    var obj = {
        provinceCode: $('input[name=provinceCode]').val(),
        cityCode: $('input[name=cityCode]').val(),
        powerStationName: $('input[name=powerStationName]').val(),
        startServiceTime:$('input[name=startServiceTime]').val(),
        endServiceTime: $('input[name=endServiceTime]').val()
    };
    window.location.href = basePath + exportStatisticsPowerStationChargeOrderUrl + '?provinceCode='
        + obj.provinceCode + '&cityCode='
        + obj.cityCode + '&powerStationName='
        + obj.powerStationName + '&startServiceTime='
        + obj.startServiceTime + '&endServiceTime='
        + obj.endServiceTime;
});
//查询条件部分========================================
function toGiveHiddenInput() {
    var provinceCodeValue = $('#provinceCode').attr('data-value');
    var cityCodeValue = $('#cityCode').attr('data-value');
    var powerStationNameValue = $('#powerStationName').val();
    if(!provinceCodeValue){
        provinceCodeValue='';
    }
    if(!cityCodeValue){
        cityCodeValue='';
    }
    if (provinceCodeValue == "") {
        $('input[name=provinceCode]').val('');
    } else {
        $('input[name=provinceCode]').val(provinceCodeValue);
    }
    if (cityCodeValue == "") {
        $('input[name=cityCode]').val('');
    } else {
        $('input[name=cityCode]').val(cityCodeValue);
    }
    if (powerStationNameValue == "") {
        $('input[name=powerStationName]').val('');
    } else {
        $('input[name=powerStationName]').val(powerStationNameValue);
    }

    getDateValue();
}
//加载省市区
//渠道筛选部分=============
//setTimeout("toLoadProvince('','#provinceCode','.provinceUl','toUnbindEvent')", 250);
$('.provinceUl').on("click", "li", function () {
    $('#cityCode').attr('data-value', '');
    $('#cityCode').html('请选择市');
    $('input[name=cityCode]').val('');
    var provinceCodeId = $(this).attr('data-option');
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');
    if (flag == "") {
        $('#provinceCode').html('请选择省');
        $('#provinceCode').attr('data-value', '');
        $('#cityCode').html('请选择市');
        $('#cityCode').attr('data-value', '');
        $('.cityUl').html('');
        $('input[name=cityCode]').val('');
        $('#companyCode').attr('data-value', '');
        $('#companyCode').html('请选择公司');
        $('input[name=cpyNumber]').val('');
    } else {
        toLoadCity(provinceCodeId, '', '#cityCode', '.cityUl', 'toUnbindEvent');
    }
})
$('.cityUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});

//日期选择
laydate.render({
    elem: '#datePicker'
    , range: true,
    theme: '#ff7d00',
    type:'month'
});

function getDateValue() {
    var dateValue = $('#datePicker').val();
    if (dateValue == "") {
        $('input[name=startServiceTime]').val('');
        $('input[name=endServiceTime]').val('');
    } else {
        var dateValue = $('#datePicker').val();
        var startGmtCreate = dateValue.substring(0, 7);
        var endGmtCreate = dateValue.substring(10, 17);
        $('input[name=startServiceTime]').val(startGmtCreate);
        $('input[name=endServiceTime]').val(endGmtCreate);
    }
}

//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        statisticsListSearch();
    }
});

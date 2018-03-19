
$(function () {
    toUnbindEvent();
    toLoadProvince('', '#stationProvinceCode', '.stationProvinceUl', 'toUnbindEvent');
    setTimeout("ccuFaultInfoSearch()", 100);
})
function toUnbindEvent() {
    $('.cFReFaultCauseBlock').unbind();
    $('.stationProvinceBlock').unbind();
    $('.stationCityBlock').unbind();
    $('.stationAreaBlock').unbind();
    $('.powerStationIdBlock').unbind();
    selectModel();
}
//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        ccuFaultInfoSearch();
    }
});
//去加载表格的数据
function ccuFaultInfoSearch() {
    toGiveHiddenInput();
    initTable("ccuFaultInfoForm", "ccuFaultInfoListPage", getChargingFaultRecordListUrl, getCcuFaultInfoListCallback);
}
function getCcuFaultInfoListCallback(req){
    var data = req.dataObject;
    var pageNum = req.pager.pageNo;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        listTr += '<tr><td>'+(i + 1 + (pageNum - 1) * 100)+'</td><td><span>' + data[i].cFReChargingSarttime
            + '</span></td><td>' + data[i].cFReFaultCause
            + '</td><td>' + data[i].cFReUsingMachineCode
            + '</td><td>' + data[i].powerStationName
            + '</td><td>' + data[i].epNum
            + '</td><td>' + data[i].cFReEphNo
            + '</td><td>' + data[i].cFReTransactionNumber
            + '</td></tr>';
    }
    $("#myTbogy").html(listTr);
}
function toGiveHiddenInput(){
    var stationProvinceCode = $('#stationProvinceCode').attr('data-value');
    var stationCityCode = $('#stationCityCode').attr('data-value');
    var stationAreaCode = $('#stationAreaCode').attr('data-value');

    if (stationProvinceCode == '') {
        $('input[name=provinceCode]').val('');
    } else {
        $('input[name=provinceCode]').val(stationProvinceCode);
    }
    if (stationCityCode == '') {
        $('input[name=cityCode]').val('');
    } else {
        $('input[name=cityCode]').val(stationCityCode);
    }
    if (stationAreaCode == '') {
        $('input[name=areaCode]').val('');
    } else {
        $('input[name=areaCode]').val(stationAreaCode);
    }

}
//获取充电点=================================================
$('.stationProvinceUl').on("click", "li", function () {
    $('#stationCityCode').html('请选择市');
    $('#stationCityCode').attr('data-value', '');
    $('.stationCityUl').html('');
    $('#stationAreaCode').html('请选择区');
    $('#stationAreaCode').attr('data-value', '');
    $('.stationAreaUl').html('');
    $('#powerStationName').val('');
    $('#powerStationId').val('');
    $('.powerStationNameUl').css('display','none');
    $('.powerStationNameUl').html('');
    var provinceCodeId = $(this).attr('data-option');
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');
    if (flag == "") {
        $('#stationCityCode').html('请选择市');
        $('#stationCityCode').attr('data-value', '');
        $('.stationCityUl').html('');
        $('#stationAreaCode').html('请选择区');
        $('#stationAreaCode').attr('data-value', '');
        $('.stationAreaUl').html('');
        $('#powerStationName').val('');
        $('#powerStationId').val('');
        $('.powerStationNameUl').css('display','none');
        $('.powerStationNameUl').html('');
    } else {
        toLoadCity(provinceCodeId, '', '#stationCityCode', '.stationCityUl', 'toUnbindEvent');
    }
});
$('.stationCityUl').on("click", "li", function () {
    $('#stationAreaCode').html('请选择区');
    $('#stationAreaCode').attr('data-value', '');
    $('.stationAreaUl').html('');
    $('#powerStationName').val('');
    $('#powerStationId').val('');
    $('.powerStationNameUl').css('display','none');
    $('.powerStationNameUl').html('');
    var cityCodeId = $(this).attr('data-option');
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');
    if (flag == "") {
        $('#stationAreaCode').html('请选择区');
        $('#stationAreaCode').attr('data-value', '');
        $('.stationAreaUl').html('');
        $('#powerStationName').val('');
        $('#powerStationId').val('');
        $('.powerStationNameUl').css('display','none');
        $('.powerStationNameUl').html('');
    }else {
        toLoadArea(cityCodeId, '', '#stationAreaCode', '.stationAreaUl', 'toUnbindEvent');
    }
});
$('.stationAreaUl').on("click", "li", function () {
    $('#powerStationName').val('');
    $('#powerStationId').val('');
    $('.powerStationNameUl').css('display','none');
    $('.powerStationNameUl').html('');
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');
    if (flag == "") {
        $('#powerStationName').val('');
        $('#powerStationId').val('');
        $('.powerStationNameUl').css('display','none');
        $('.powerStationNameUl').html('');
    }
});

var last;
$("#powerStationName").keyup(function(event){
    last = event.timeStamp;
    setTimeout(function(){
        if(last-event.timeStamp==0){
            var stationProvinceCodeValue = $('#stationProvinceCode').attr('data-value');
            var stationCityCodeValue = $('#stationCityCode').attr('data-value');
            var powerStationName=$('#powerStationName').val();
            if(stationCityCodeValue==''){
                $('#powerStationName').val('');
                $('#powerStationId').val('');
            }else{
                if(powerStationName==''){
                    $('.powerStationNameUl').css('display','none');
                    $('.powerStationNameUl').html('');
                    $('#powerStationId').val('');
                    return;
                }else{
                    $('.powerStationNameUl').css('display','block');
                    var cpyObject = {
                        provinceCode: stationProvinceCodeValue,
                        cityCode: stationCityCodeValue,
                        powerstationName:powerStationName
                    };
                    if (JSON.stringify(cpyObject) == "{}") {
                        $('#powerStationName').val("");
                        $('#powerStationId').val("");
                    } else {
                        toAjaxPowserStation(cpyObject);
                    }
                }
            }
        }
    },1000)
});
$('#powerStationName').on('onpropertychange input',function(){
    var length=$(this).val().length;
    if(length==0){
        $('#powerStationId').val('');
    }
})
function toAjaxPowserStation(cpyObject) {
    $.ajax({
        type: "post",
        url: basePath + getPowerStationListUrl,
        async: true,
        data: cpyObject,
        success: function (data) {
            if (data.success == true) {
                var dataModule = data.dataObject;
                var powerStationLi = '<li data-option="">请选择</li>';
                for (var i = 0; i < dataModule.length; i++) {
                    powerStationLi += '<li data-option="' + dataModule[i].id + '">' + dataModule[i].name + '</li>';
                }
                $('.powerStationNameUl').html(powerStationLi);
            }
        }
    });
}
$('body').off('click','.powerStationNameUl li').on('click','.powerStationNameUl li',function(){
    $('#powerStationName').val('');
    $('#powerStationId').val('');
    var id=$(this).attr('data-option');
    var name=$(this).html();
    $('#powerStationName').val(name);
    $('#powerStationId').val(id);
    $('.powerStationNameUl').css('display','none');
    $('.powerStationNameUl').html('');
})

$('.cFReFaultCauseUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
})
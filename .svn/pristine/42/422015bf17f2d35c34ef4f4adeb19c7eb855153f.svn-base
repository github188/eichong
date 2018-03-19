//获取电桩升级列表的menuId
var getMenuMap = window.localStorage.getItem('menuMap');
var getCurruntMap = JSON.parse(getMenuMap);
var menuId = getCurruntMap['电桩升级列表'];
$(function () {
    ctrlMenu(menuId);
    //去加载表格的数据
    setTimeout("getElectricUpdateList()", 100);
    //加载省市
    setTimeout("toLoadProvince('', '#provinceCodeHtml', '.cpyProvinceUl', 'toUnbindEvent')", 200);
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
            if (data.length == 0) {
                return;
            } else {
                for (var i = 0; i < data.length; i++) {
                    var contents = data[i].contents;
                    if (contents.indexOf('升级') > -1) {
                        $('#upDateBtn').show();
                    }
                }
            }


        }
    });
}


function toUnbindEvent() {
    $('.cpyProvinceBlock').unbind();
    $('.cypCityBlock').unbind();
    $('.cpyAreaBlock').unbind();
    $('.commStatusBlock').unbind();
    selectModel();
}
//toUnbindEvent();

function getElectricUpdateList() {
    toGiveHiddenInput();
    initTable("electricUpdateListForm", "electricUpdatePage", electricUpdateListUrl, electricUpdateListCallback);
}
function electricUpdateListCallback(req) {
    var data = req.dataObject;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        var firmwareNumber=data[i].firmwareNumber;
        if(firmwareNumber==undefined){
            firmwareNumber='';
        }
        var firmwareVersion=data[i].firmwareVersion;
        if(firmwareVersion==undefined){
            firmwareVersion='';
        }
        var ugFirmwareNumber=data[i].ugFirmwareNumber;
        if(ugFirmwareNumber==undefined){
            ugFirmwareNumber='';
        }
        var ugFirmwareVersion=data[i].ugFirmwareVersion;
        if(ugFirmwareVersion==undefined){
            ugFirmwareVersion='';
        }
        var ev_Updatedate=data[i].ev_Updatedate;
        if(ev_Updatedate==undefined){
            ev_Updatedate='';
        }
        var tsTypeSpan=data[i].tsTypeSpan;
        if(tsTypeSpan==undefined){
            tsTypeSpan='';
        }
        var powerName=data[i].powerName;
        if(powerName==undefined){
            powerName='';
        }
        var commStatus=data[i].commStatus;
        var commStatusHtml;
        if(commStatus==0){
            commStatusHtml='未连接';
        }else if(commStatus==1){
            commStatusHtml='已连接';
        }
        listTr += '<tr><td><input type="checkbox" name="ids" class="selectedBox" value="' + data[i].pkElectricPile + '" data-pkTypeSpanId="' + data[i].pkTypeSpanId +'"'+'/>'
        + '</td><td>' + data[i].code
        + '</td><td>' + data[i].epNum+'号桩'
        + '</td><td>' + tsTypeSpan
        + '</td><td>' + commStatusHtml
        + '</td><td>' + powerName
        + '</td><td>' + firmwareNumber
        + '</td><td>' + firmwareVersion
        + '</td><td>' + ugFirmwareNumber
        + '</td><td>' + ugFirmwareVersion
        + '</td><td>' + ev_Updatedate
        + '</td></tr>';
    }
    $("#myTbogy").html(listTr);
}
//查询条件部分=========================
function toGiveHiddenInput() {
    var provinceCodeHtmlValue = $('#provinceCodeHtml').attr('data-value');
    var cityCodeHtmlValue = $('#cityCodeHtml').attr('data-value');
    var areaCodeHtmlValue = $('#areaCodeHtml').attr('data-value');
    var commStatusHtmlValue = $('#commStatusHtml').attr('data-value');

    var electricPileCodeValue = $('input[name=electricPileCode]').val();
    var powerStationNameValue = $('input[name=powerStationName]').val();
    var productModelValue = $('input[name=productModel]').val();
    if (provinceCodeHtmlValue == '') {
        $('input[name=provinceCode]').val('');
    } else {
        $('input[name=provinceCode]').val(provinceCodeHtmlValue);
    }
    if (cityCodeHtmlValue == '') {
        $('input[name=cityCode]').val('');
    } else {
        $('input[name=cityCode]').val(cityCodeHtmlValue);
    }
    if (areaCodeHtmlValue == '') {
        $('input[name=areaCode]').val('');
    } else {
        $('input[name=areaCode]').val(areaCodeHtmlValue);
    }
    if (commStatusHtmlValue == '') {
        $('input[name=commStatus]').val('');
    } else {
        $('input[name=commStatus]').val(commStatusHtmlValue);
    }
    if (electricPileCodeValue == "") {
        $('input[name=electricPileCode]').val('');
    } else {
        $('input[name=electricPileCode]').val(electricPileCodeValue);
    }
    if (powerStationNameValue == "") {
        $('input[name=powerStationName]').val('');
    } else {
        $('input[name=powerStationName]').val(powerStationNameValue);
    }
    if (productModelValue == "") {
        $('input[name=productModel]').val('');
    } else {
        $('input[name=productModel]').val(productModelValue);
    }
}


//点击省加载市
$('.cpyProvinceUl').on("click", "li", function () {
    $('#cityCodeHtml').html('请选择');
    $('.cypCityUl').html('');
    $('#cityCodeHtml').attr('data-value', '');
    $('#areaCodeHtml').html('请选择');
    $('.areaUl').html('');
    $('#areaCodeHtml').attr('data-value', '');
    var provinceCodeId = $(this).attr('data-option');
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');
    if (flag == "") {
        $('#cityCodeHtml').html('请选择');
        $('.cypCityUl').html('');
        $('#cityCodeHtml').attr('data-value', '');
        $('#areaCodeHtml').html('请选择');
        $('.areaUl').html('');
        $('#areaCodeHtml').attr('data-value', '');
    } else {
        toLoadCity(provinceCodeId, '', '#cityCodeHtml', '.cypCityUl', 'toUnbindEvent');
    }
})
//点击市加载区
$('.cypCityUl').on("click", "li", function () {
    $('#areaCodeHtml').html('请选择');
    $('.areaUl').html('');
    $('#areaCodeHtml').attr('data-value', '');
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');
    var cityCodeId = $(this).attr('data-option');
    if (flag == "") {
        $('#areaCodeHtml').html('请选择');
        $('.areaUl').html('');
        $('#areaCodeHtml').attr('data-value', '');
    } else {
        toLoadArea(cityCodeId, '', '#areaCodeHtml', '.areaUl', 'toUnbindEvent');

    }
})
//点击区获取值
$('.areaUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
})
//连接状态
$('.commStatusUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
})
//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        getElectricUpdateList();
    }
});
//点击升级
$('body').on('click','#upDateBtn',function(){
    var pkElectricpiles = '',
        pkTypeSpanIds='';
    $('input[name=ids]').each(function () {
        var flag = $(this).prop('checked');
        if (flag == true) {
            pkElectricpiles += $(this).attr('value') + ',';
            pkTypeSpanIds += $(this).attr('data-pkTypeSpanId') + ',';
        }
    });

    if(!pkElectricpiles){
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '160px'],//宽高
            content: '请指定要升级的电桩',
            time: 3000,
            btn: ["确定"]
        });
    }else{
        pkElectricpiles = pkElectricpiles.substring(0, pkElectricpiles.length - 1);
        pkTypeSpanIds = pkTypeSpanIds.substring(0, pkTypeSpanIds.length - 1);
        var pkTypeSpan=pkTypeSpanIds.split(',');
        for(var i=1;i<pkTypeSpan.length;i++){
            if(pkTypeSpan[i-1]!=pkTypeSpan[i]){
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '160px'],//宽高
                    content: '请指定相同产品型号的电桩',
                    time: 3000,
                    btn: ["确定"]
                });
                return false;
            }
        }
        var pkTypeSpan=pkTypeSpan[0];//去获取要升级的bom清单，渲染到页面，渲染成功，弹出清单框
        getBomListByTypeSpanId(pkTypeSpan,pkElectricpiles);

    }

})
function getBomListByTypeSpanId(pkTypeSpan,pkElectricpiles){
    layer.closeAll();
    $('.errorTip').html('');
    loadBOMList(pkTypeSpan);
    layer.open({
        type: 1,
        offset: '100px',
        title: ["选择软硬件升级清单", "font-size:12px;text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['860px', '400px'],//宽高
        content: $('.bindPile'),
        btn: ["确定", "取消"],
        yes: function (index, layero) {
            //确定去升级
            toUpdataEV(pkTypeSpan,pkElectricpiles);

        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
}
//Bom渲染列表
function loadBOMList(pkTypeSpan){
    $.ajax({
        type: "post",
        url: basePath + getBomListByTypeSpanIdUrl,
        async: true,
        data: {
            pkTypeSpanId: pkTypeSpan
        },
        success: function (data) {
            if (data.success == true) {
                var data = data.dataObject;
                var listTr = "";
                for (var i = 0; i < data.length; i++) {
                    //是否强制
                    var blForceUpdate=data[i].blForceUpdate;
                    var blForceUpdateHtml='';
                    if(blForceUpdate==0){
                        blForceUpdateHtml='不强制';
                    }else if(blForceUpdate==1){
                        blForceUpdateHtml='强制';
                    }
                    //硬件类型
                    var blHardwareType=data[i].blHardwareType;
                    var blHardwareTypeHtml='';
                    if(blHardwareType==1){
                        blHardwareTypeHtml='计费单元';
                    }else if(blHardwareType==2){
                        blHardwareTypeHtml='主控单元';
                    }else if(blHardwareType==3){
                        blHardwareTypeHtml='显示屏';
                    }else if(blHardwareType==3){
                        blHardwareTypeHtml='通讯模块';
                    }
                    listTr += '<tr><td><input type="checkbox" name="pkBomListIds" class="selectedBox" value="' + data[i].pkBomListId + '"/></td>'
                    + '<td>' + (i + 1) + '</td>'
                    + '<td><span>' + blHardwareTypeHtml
                    + '</span></td><td>' + data[i].blHardwareNumber
                    + '</td><td>' + data[i].blHardwareVersion
                    + '</td><td>' + data[i].blFirmwareNumber
                    + '</td><td>' + data[i].blFirmwareVersion
                    + '</td><td>' + blForceUpdateHtml
                    + '</td></tr>';

                }
                $("#BomTbody").html(listTr);
            }
        }
    });
}
//单选复选框
$('body').on('click', 'input[name=pkBomListIds]', function () {
    var flag = $(this).prop('checked');
    if (flag == true) {
        $(this).parents('td').parents('tr').siblings().find('input[name=pkBomListIds]').prop('checked', false).attr('disabled', true);
    } else {
        $(this).parents('td').parents('tr').siblings().find('input[name=pkBomListIds]').attr('disabled', false);
    }

})
//确定去升级
function  toUpdataEV(pkTypeSpan,pkElectricpiles){
    var pkBomListIds = '';
    $('input[name=pkBomListIds]').each(function () {
        var flag = $(this).prop('checked');
        if (flag == true) {
            pkBomListIds += $(this).attr('value') + ',';
        }
    });
    if(!pkBomListIds){
        $('.errorTip').html('请选择软硬件清单');
        return false;
    }
    else{
        pkBomListIds = pkBomListIds.substring(0, pkBomListIds.length - 1);
        $('.errorTip').html('');
        layer.closeAll();
        $.ajax({
            type: "post",
            url: basePath + updateEpVisionUrl,
            async: true,
            data: {
                pkElectricpiles:pkElectricpiles,
                pkTypeSpanId: pkTypeSpan,
                pkBomListId:pkBomListIds
            },
            success: function (data) {
                if (data.success == true) {
                    layer.closeAll();
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '160px'],//宽高
                        content: data.msg,
                        btn: ["确定"],
                        yes:function(index,layero){
                            window.location.reload();
                        },
                        cancel: function (index, layero) {
                            layer.closeAll();
                            window.location.reload();
                        }
                    });
                }else{
                    layer.closeAll();
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '160px'],//宽高
                        content: data.msg,
                        btn: ["确定"]
                    });
                }
            }
        });

    }
}
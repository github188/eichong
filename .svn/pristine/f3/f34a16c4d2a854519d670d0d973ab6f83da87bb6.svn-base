$('#addGobackLock').off('click').on('click', function () {
    window.location.href = 'parkingLock_list.html';
})
function toUnbindEvent() {
    $('.lockScopeBlock').unbind();
    $('.lockStutasBlock').unbind();
    $('.cpyAreaBlock').unbind();
    $('.cpyCityBlock').unbind();
    $('.cpyProvinceBlock').unbind();
    $('.powerStationProvinceBlock').unbind();
    $('.powerStationCityBlock').unbind();
    $('.powerStationAreaBlock').unbind();
    selectModel();
}
toUnbindEvent();
$('.supplierLockUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});
//点击省市区
//加载省市
setTimeout("toLoadProvince('', '#provinceCodeHtml', '.provinceUl', 'toUnbindEvent')", 100);
//点击省加载市
$('.provinceUl').on("click", "li", function () {
    $('#cityCodeHtml').html('请选择').attr('data-value', '');
    $('#areaCodeHtml').html('请选择').attr('data-value', '');
    $('.areaUl').html('');
    $('.cityUl').html('');
    var provinceCodeId = $(this).attr('data-option');
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');
    if (flag == "") {
        $('#cityCodeHtml').html('请选择').attr('data-value', '');
        $('#areaCodeHtml').html('请选择').attr('data-value', '');
        $('.areaUl').html('');
        $('.cityUl').html('');
        $('#ps_provinceCode').val('');
        $('#ps_cityCode').val('');
        $('#ps_areaCode').val('');
    } else {
        toLoadCity(provinceCodeId, '', '#cityCodeHtml', '.cityUl', 'toUnbindEvent');
        $('#ps_provinceCode').val(provinceCodeId);
        $('#ps_cityCode').val('');
        $('#ps_areaCode').val('');
    }
})
//点击市加载区
$('.cityUl').on("click", "li", function () {
    $('#areaCodeHtml').html('请选择').attr('data-value', '');
    $('.areaUl').html('');
    var cityCodeId = $(this).attr('data-option');
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');

    if (flag == "") {
        $('#areaCodeHtml').html('请选择').attr('data-value', '');
        $('.areaUl').html('');
        $('#ps_cityCode').val('');
        $('#ps_areaCode').val('');
    } else {
        $('.areaUl').html('');
        toLoadArea(cityCodeId, '', '#areaCodeHtml', '.areaUl', 'toUnbindEvent');
        $('#ps_cityCode').val(cityCodeId);
        $('#ps_areaCode').val('');
    }
})
$('.ps_provinceUl').on("click", "li", function () {
    $('#powerStationCityHtml').html('请选择').attr('data-value', '');
    $('.ps_cityUl').html('');
    $('#powerStationAreaHtml').html('请选择').attr('data-value', '');
    $('.ps_areaUl').html('');
    var provinceCodeId = $(this).attr('data-option');
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');
    if (flag == "") {
        $('#powerStationCityHtml').html('请选择').attr('data-value', '');
        $('.ps_cityUl').html('');
        $('#powerStationAreaHtml').html('请选择').attr('data-value', '');
        $('.ps_areaUl').html('');
    } else {
        toLoadCity(provinceCodeId, '', '#powerStationCityHtml', '.ps_cityUl', 'toUnbindEvent');

    }
})
//点击市加载区
$('.ps_cityUl').on("click", "li", function () {
    $('#powerStationAreaHtml').html('请选择').attr('data-value', '');
    $('.ps_areaUl').html('');
    var cityCodeId = $(this).attr('data-option');
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');

    if (flag == "") {
        $('#powerStationAreaHtml').html('请选择').attr('data-value', '');
        $('.ps_areaUl').html('');
    } else {
        $('.ps_areaUl').html('');
        toLoadArea(cityCodeId, '', '#powerStationAreaHtml', '.ps_areaUl', 'toUnbindEvent');

    }
})
//点击区获取值
$('.areaUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');
    if (flag == "") {
        $('#areaCodeHtml').html('请选择').attr('data-value', '');
        $('.areaUl').html('');
        $('#ps_areaCode').val('');
    } else {
        $('#ps_areaCode').val(flag);
    }
})
$('.ps_areaUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
})
//点击范围选择 需要判断0 1 2
//点击更换充电点
$('#replacePowerStation').off('click').on('click', function () {
    sharePCAcode();
    powerStationListSearch();
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["绑定充电点", "font-size:12px;text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['860px', '400px'],//宽高
        content: $('#bindParkingLock'),
        cancel: function (index, layero) {
            layer.closeAll();
            $('#bindParkingLock').hide();
        }
    });
})
function sharePCAcode(){
    $('#powerStationProvinceHtml').attr('data-value','').html('省');
    $('#powerStationCityHtml').attr('data-value','').html('市');
    $('#powerStationAreaHtml').attr('data-value','').html('区');
    var provinceCode=$('#ps_provinceCode').val();
    var cityCode=$('#ps_cityCode').val();
    var areaCode=$('#ps_areaCode').val();
    toLoadProvince(provinceCode, '#powerStationProvinceHtml', '.ps_provinceUl', 'toUnbindEvent');
    toLoadCity(provinceCode, cityCode, '#powerStationCityHtml', '.ps_cityUl', 'toUnbindEvent');
    toLoadArea(cityCode, areaCode, '#powerStationAreaHtml', '.ps_areaUl', 'toUnbindEvent');
    $('#powerStationProvinceHtml').attr('data-value',provinceCode);
    $('#powerStationCityHtml').attr('data-value',cityCode);
    $('#powerStationAreaHtml').attr('data-value',areaCode);
}
//加载充电点列表
function powerStationListSearch() {
    getHideInput();
    initTable("powerstationListForm", "powerstationListPage", getPowerStationListPageUrl, getPowerStationListCallback);
}
function getHideInput() {
    var powerStationProvinceHtmlValue = $('#powerStationProvinceHtml').attr('data-value');
    var powerStationCityHtmlValue = $('#powerStationCityHtml').attr('data-value');
    var powerStationAreaHtmlValue = $('#powerStationAreaHtml').attr('data-value');

    if (powerStationProvinceHtmlValue == '') {
        $('#ps_provinceCode').val('');
    } else {
        $('#ps_provinceCode').val(powerStationProvinceHtmlValue);
    }
    if (powerStationCityHtmlValue == '') {
        $('#ps_cityCode').val('');
    } else {
        $('#ps_cityCode').val(powerStationCityHtmlValue);
    }
    if (powerStationAreaHtmlValue == '') {
        $('#ps_areaCode').val('');
    } else {
        $('#ps_areaCode').val(powerStationAreaHtmlValue);
    }
}
function getPowerStationListCallback(req) {
    var data = req.dataObject;
    var pageNum = req.pager.pageNo;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        listTr += '<tr><td><input type="checkbox" name="powerstations" powerstationname="'
            + data[i].powerstationName
            + '" class="selectedBox" value="'
            + data[i].powerstationId + '"/></td>'
            + '<td>' + data[i].powerstationName
            + '</td><td>' + data[i].address
            + '</td></tr>';
    }
    $("#bindPowerStationTbody").html(listTr);
}
//powerstations
//复选框按钮单选
$('body').on('click', 'input[name=powerstations]', function () {
    var flag = $(this).prop('checked');
    if (flag == true) {
        $(this).parents('td').parents('tr').siblings().find('input[name=powerstations]').prop('checked', false).attr('disabled', true);
    } else {
        $(this).parents('td').parents('tr').siblings().find('input[name=powerstations]').attr('disabled', false);
    }

})
//点击弹框确定，选择充电点
//点击确定绑定
$('body').off('click', '.sureBindBtn').on('click', '.sureBindBtn', function () {
    layer.closeAll();
    $('#bindParkingLock').hide();
    toChangePowerStationIdUrl();
})
function toChangePowerStationIdUrl() {
    var powerstationIds = '';
    var powerStationNames = '';
    $('input[name=powerstations]').each(function () {
        var flag = $(this).prop('checked');
        if (flag == true) {
            powerstationIds += $(this).attr('value') + ',';
            powerStationNames += $(this).attr('powerstationname') + ',';

        }
    });
    powerstationIds = powerstationIds.substring(0, powerstationIds.length - 1);
    powerStationNames = powerStationNames.substring(0, powerStationNames.length - 1);
    if (!powerstationIds) {
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '160px'],//宽高
            content: '请选择充电点',
            time: 3000,
            btn: ["确定"]
        });
        return false;
    } else {
        $('#lock_powerStationName').val(powerStationNames);
        $('#powerStationId').val(powerstationIds);
        $('#powerStationTip').html('');
    }
}
//一系列校验
//校验城市
function testCity() {
    var areaCodeHtmlValue = $('#areaCodeHtml').attr('data-value');
    if (areaCodeHtmlValue == '') {
        $('#cpyCityTip').html('请选择准确的城市');
        return false;
    } else {
        $('#cpyCityTip').html('');
        return true;
    }
}
function testAddressAndKey() {
    var address = $('#address').val();
    var platformLockKey = $('#platformLockKey').val();
    var powerStationId = $('#powerStationId').val();
    if (!powerStationId) {
        $('#powerStationTip').html('请选择充电点');
        return false;
    }else{
        $('#powerStationTip').html('');
        if (!address) {
            $('#addressLockTip').html('请填写地址');
            return false;
        }else{
            $('#addressLockTip').html('');
            if(!platformLockKey) {
                $('#platformLockKeyTip').html('请填写对接key');
                return false;
            }else {
                $('#platformLockKeyTip').html('');
                return true;
            }
        }
    }


}
//点击保存
function saveParkingLock() {
    if (testCity() && testAddressAndKey()) {
        var powerStationId = $('#powerStationId').val();
        var address = $('#address').val();
        var platformLockKey = $('#platformLockKey').val();
        var parkingLockPlatform = $('#parkingLockPlatformHtml').attr('data-value');
        var provinceCodeHtmlValue = $('#provinceCodeHtml').attr('data-value');
        var cityCodeHtmlValue = $('#cityCodeHtml').attr('data-value');
        var areaCodeHtmlValue = $('#areaCodeHtml').attr('data-value');
        var obj = {
            parkingLockPlatform:parkingLockPlatform,
            powerStationId:powerStationId,
            provinceCode:provinceCodeHtmlValue,
            cityCode:cityCodeHtmlValue,
            areaCode:areaCodeHtmlValue,
            address:address,
            platformLockKey:platformLockKey
        };
        $.ajax({
            type: "post",
            url: basePath + addParkingLockUrl,
            async: true,
            data: obj,
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
                        yes: function (index, layero) {
                            layer.closeAll();
                            window.location.href = 'parkingLock_list.html';
                        },
                        cancel: function (index, layero) {
                            layer.closeAll();
                            window.location.href = 'parkingLock_list.html';
                        }

                    });

                } else if (data.status == 9001) {
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
                        yes: function (index, layero) {
                            layer.closeAll();
                            window.top.location.href = basePath + toLoginUrl;
                        },
                        cancel: function (index, layero) {
                            layer.closeAll();
                            window.top.location.href = basePath + toLoginUrl;
                        }
                    });

                } else {
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '160px'],//宽高
                        content: data.msg,
                        btn: ["确定"],
                        time: 3000
                    });
                }
            }
        });
    }
}



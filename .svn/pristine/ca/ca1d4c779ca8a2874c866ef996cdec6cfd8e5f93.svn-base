//获取电桩参数设置的menuId
var getMenuMap = window.localStorage.getItem('menuMap');
var getCurruntMap = JSON.parse(getMenuMap);
var menuId = getCurruntMap['电桩参数设置'];
$(function () {
    ctrlMenu(menuId);
    loadHour();
    loadMinute();
    //去加载表格的数据
    setTimeout("getElectricParamList()", 100);
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
                    if (contents.indexOf('设置') > -1) {
                        $('#setBtn').show();
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
    selectModel();
}
//toUnbindEvent();

function getElectricParamList() {
    toGiveHiddenInput();
    initTable("electricParamListForm", "electricParamPage", electricParamListUrl, electricParamListCallback);
}
function electricParamListCallback(req) {
    var data = req.dataObject;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        var gmtModified = data[i].gmtModified;
        if (gmtModified == null) {
            gmtModified = '';
        } else {
            gmtModified = new Date(gmtModified.time).format("yyyy-MM-dd");
        }
        listTr += '<tr><td><input type="checkbox" name="id" class="selectedBox" value="' + data[i].electricPileCode + '"/>'
        + '</td><td>' + data[i].electricPileCode
        + '</td><td>' + data[i].epNum
        + '</td><td>' + data[i].powerStationName
        + '</td><td>' + data[i].chargeMode
        + '</td><td>' + data[i].commonStatus
        + '</td><td>' + data[i].offlineNum
        + '</td><td>' + data[i].time
        + '</td><td>' + data[i].socValue
        + '</td><td>' + data[i].issuedStatus
        + '</td><td>' + gmtModified
        + '</td></tr>';
    }
    $("#myTbogy").html(listTr);
}
//查询条件部分=========================
function toGiveHiddenInput() {
    var provinceCodeHtmlValue = $('#provinceCodeHtml').attr('data-value');
    var cityCodeHtmlValue = $('#cityCodeHtml').attr('data-value');
    var areaCodeHtmlValue = $('#areaCodeHtml').attr('data-value');

    var electricPileCodeValue = $('input[name=electricPileCode]').val();
    var powerStationNameValue = $('input[name=powerStationName]').val();
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
//时间设置时的加载

function loadHour() {
    var html = '';
    $('#hour').html('');
    for (var i = 0; i < 10; i++) {
        html += '<option value="' + i + '">0' + i + '</option>';
    }
    for (var i=10;i< 24; i++) {
        html += '<option value="' + i + '">' + i + '</option>';
    }
    $('#hour').html(html);
}
function loadMinute() {
    var html = '';
    $('#minute').html('');
    for (var i = 0; i < 10; i++) {
        html += '<option value="' + i + '">0' + i + '</option>';
    }
    for (var i = 10; i < 60; i++) {
        html += '<option value="' + i + '">' + i + '</option>';
    }
    $('#minute').html(html);
}
//设置按钮

$('body').off('click', '#setBtn').on('click', '#setBtn', function () {
    var ids = '';
    $('input[name=id]').each(function () {
        var flag = $(this).prop('checked');
        if (flag == true) {
            ids += $(this).attr('value') + ',';
        }
    });
    if (!ids) {
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '160px'],//宽高
            content: '请指定要设置的电桩',
            time: 3000,
            btn: ["确定"]
        });
    } else {
        ids = ids = ids.substring(0, ids.length - 1);
        layer.closeAll();
        layer.open({
            type: 1,
            offset: '100px',
            title: ["设置", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['340px', '320px'],//宽高
            content: $('.setBlock'),
            btn: ["确定", "取消"],
            yes: function (index, layero) {
                setBlockCallBack(ids);

            },
            cancel: function (index, layero) {
                layer.closeAll();
            }
        });
    }

})

//提交设置
function setBlockCallBack(ids) {
    if(testOfflineNum()){
        var status = $('input[name=status]:checked').val();
        var socValue = $('input[name=socValue]:checked').val();
        var hour = $('#hour').val();
        var minute = $('#minute').val();
        var offlineNum = $('input[name=offlineNum]').val();
        layer.closeAll();
        if(hour<10){
            hour = '0'+hour;
        }
        if(minute<10){
            minute = '0'+minute;
        }
        var obj = {
            code: ids,
            status: status,
            hour: hour,
            minute: minute,
            socValue: socValue,
            offlineNum: offlineNum
        };
        $.ajax({
            type: "post",
            url: basePath + setElectricParamUrl,
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
                        content: '设置成功',
                        btn: ["确定"],
                        yes: function (index, layero) {
                            layer.closeAll();
                            window.location.reload();
                        },
                        cancel: function (index, layero) {
                            layer.closeAll();
                            window.location.reload();
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
                        time: 3000,
                        btn: ["确定"]
                    });
                }
            }

        })

    }


}
//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        getElectricParamList();
    }
});
//校验离线次数
function testOfflineNum(){
    var offlineNum = $('input[name=offlineNum]').val();
    var reg=/(^[0-9]$|^10$)/;
    if(!offlineNum){
        $('.errorTip').show().html('离线次数不能为空');
        $('input[name=offlineNum]').focus();
        return false;
    }
    if(!reg.test(offlineNum)){
        $('.errorTip').show().html('离线次数必须为0-10之间纯数字,最大设置10次');
        $('input[name=offlineNum]').focus();
        return false;
    }
    return true;
}
//处理定时充电逻辑
$('body').off('click','input[name=status]').on('click','input[name=status]',function(){
    var flag=$(this).val();
    if(flag==1){
        $('#hour').attr('disabled',false);
        $('#minute').attr('disabled',false);
    }else{
        $('#hour').attr('disabled',true);
        $('#minute').attr('disabled',true);
    }
})
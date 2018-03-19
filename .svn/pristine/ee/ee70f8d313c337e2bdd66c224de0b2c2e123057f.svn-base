//获取地锁信息列表的menuId
var getMenuMap = window.localStorage.getItem('menuMap');
var getCurruntMap = JSON.parse(getMenuMap);
var menuId = getCurruntMap['地锁信息列表'];
$(function () {
    ctrlMenu(menuId);
    //去加载表格的数据
    setTimeout("parkingLockSearch()", 100);
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
                    if (contents.indexOf('新建') > -1) {
                        $('#addParkingLock').show();
                    }
                    if (contents.indexOf('绑定充电点') > -1) {
                        $('#bindPowerstation').show();
                    }
                    if (contents.indexOf('导入') > -1) {
                        $('#importParkingLockInfo').show();
                    }
                    if (contents.indexOf('手动降锁') > -1) {
                        $('#parkingLockDown').show();
                    }
                    if (contents.indexOf('手动升锁') > -1) {
                        $('#parkingLockUp').show();
                    }
                }
            }
        }
    });
}


function parkingLockSearch() {
    toGiveHiddenInput();
    initTable("parkingLockListForm", "parkingLockListPage", getParkingLockListUrl, getParkingLockListCallback);
}
function getParkingLockListCallback(req) {
    var data = req.dataObject;
    var pageNum = req.pager.pageNo;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        listTr += '<tr><td><input type="checkbox" name="id" class="selectedBox" provinceCode="'
            + data[i].provinceCode + '" cityCode="' + data[i].cityCode
            + '" areaCode="' + data[i].areaCode + '"  value="' + data[i].id
            + '"/></td><td><span class="code" data-id="'
            + data[i].id + '"  onclick="toParkingLockDetail(this)">'
            + data[i].code + '</span>'
            + '</td><td>' + data[i].chStatus
            + '</td><td>' + data[i].platformName
            + '</td><td>' + data[i].powerStationName
            + '</td><td>' + data[i].address
            + '</td></tr>';
    }
    $("#myTbogy").html(listTr);
}
//点击桩编号，进去桩详情
function toParkingLockDetail(obj) {
    var id = $(obj).attr('data-id');
    window.location.href = 'parkingLock_detail.html?id=' + id;
}
function toUnbindEvent() {
    $('.cpyProvinceBlock').unbind();
    $('.cypCityBlock').unbind();
    $('.cpyAreaBlock').unbind();
    $('.parkingStatusBlock').unbind();
    $('.powerStationProvinceBlock').unbind();
    $('.powerStationCityBlock').unbind();
    $('.powerStationAreaBlock').unbind();
    selectModel();
}
function toGiveHiddenInput() {
    var parkingStatusValue = $('#parkingStatus').attr('data-value');
    var parkingLockPlatformHtml = $('#parkingLockPlatformHtml').attr('data-value');
    var provinceCodeHtmlValue = $('#provinceCodeHtml').attr('data-value');
    var cityCodeHtmlValue = $('#cityCodeHtml').attr('data-value');
    var areaCodeHtmlValue = $('#areaCodeHtml').attr('data-value');


    if (provinceCodeHtmlValue == '') {
        $('#lock_provinceCode').val('');
    } else {
        $('#lock_provinceCode').val(provinceCodeHtmlValue);
    }
    if (cityCodeHtmlValue == '') {
        $('#lock_cityCode').val('');
    } else {
        $('#lock_cityCode').val(cityCodeHtmlValue);
    }
    if (areaCodeHtmlValue == '') {
        $('#lock_areaCode').val('');
    } else {
        $('#lock_areaCode').val(areaCodeHtmlValue);
    }
    if (parkingStatusValue == '') {
        $('input[name=status]').val('');
    } else {
        $('input[name=status]').val(parkingStatusValue);
    }
    if (parkingLockPlatformHtml == '') {
        $('input[name=parkingLockPlatform]').val('');
    } else {
        $('input[name=parkingLockPlatform]').val(parkingLockPlatformHtml);
    }

}
//加载省市
setTimeout("toLoadProvince('', '#provinceCodeHtml', '.cpyProvinceUl', 'toUnbindEvent')", 100);
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
    var cityCodeId = $(this).attr('data-option');
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');

    if (flag == "") {
        $('#areaCodeHtml').html('请选择');
        $('.areaUl').html('');
        $('#areaCodeHtml').attr('data-value', '');
    } else {
        $('.areaUl').html('');
        toLoadArea(cityCodeId, '', '#areaCodeHtml', '.areaUl', 'toUnbindEvent');
    }
})
//弹框省市区加载
//点击省加载市
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
$('.areaUl,.ps_areaUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
})

$('.parkingLockPlatformBlockUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});
$('.parkingStatusUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});
//新增按钮
$('body').on('click', '#addParkingLock', function () {
    window.location.href = 'add_parkingLock.html';
})

//手动降锁按钮
$("body").off("click", "#parkingLockDown").on("click", "#parkingLockDown", function () {
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["确认", "font-size:12px;text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '180px'],//宽高
        content: '！！请再次确定是否有必要手动降锁！！仅在用户投诉、故障处理、系统测试时操作，请慎重',
        btn: ["确定降锁", "取消"],
        yes: function (index, layero) {
            layer.closeAll();
            toParkingLockDown();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
});
//去降锁
function toParkingLockDown() {
    var ids = '';
    $('input[name=id]').each(function () {
        var flag = $(this).prop('checked');
        if (flag == true) {
            ids += $(this).attr('value') + ',';
        }
    });
    ids = ids.substring(0, ids.length - 1);
    if (!ids) {
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '160px'],//宽高
            content: '请指定表单元素',
            time: 3000,
            btn: ["确定"]
        });
    } else {
        $.ajax({
            type: "post",
            url: basePath + operatingParkingLockUrl,
            async: true,
            data: {
                parkingLockId: ids,
                operating: 0//降锁 0 升锁1
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
                        btn: ["确定", "取消"]
                    });
                }
            }
        });
    }
}
//去升锁
$("body").off("click", "#parkingLockUp").on("click", "#parkingLockUp", function () {
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["确认", "font-size:12px;text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '180px'],//宽高
        content: '！！请再次确定是否有必要手动升锁！！仅在用户投诉、故障处理、系统测试时操作，请慎重',
        btn: ["确定升锁", "取消"],
        yes: function (index, layero) {
            layer.closeAll();
            toParkingLockUp();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
});
function toParkingLockUp() {
    var ids = '';
    $('input[name=id]').each(function () {
        var flag = $(this).prop('checked');
        if (flag == true) {
            ids += $(this).attr('value') + ',';
        }
    });
    ids = ids.substring(0, ids.length - 1);
    if (!ids) {
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '160px'],//宽高
            content: '请指定表单元素',
            time: 3000,
            btn: ["确定"]
        });
    } else {
        $.ajax({
            type: "post",
            url: basePath + operatingParkingLockUrl,
            async: true,
            data: {
                parkingLockId: ids,
                operating: 1//降锁 0 升锁1
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
                        btn: ["确定", "取消"]
                    });
                }
            }
        });
    }
}
//复选框按钮单选
$('body').on('click', 'input[name=id]', function () {
    var flag = $(this).prop('checked');
    if (flag == true) {
        $(this).parents('td').parents('tr').siblings().find('input[name=id]').prop('checked', false).attr('disabled', true);
    } else {
        $(this).parents('td').parents('tr').siblings().find('input[name=id]').attr('disabled', false);
    }

})
//弹框复选框限制

//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        parkingLockSearch();
    }
});
//导入
$("#importParkingLockInfo").on("click", function () {
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["地锁导入", "font-size:12px;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '270px'],//宽高
        content: $("#fileInputContainer"),
        yes: function (index, layero) {
            layer.closeAll();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
})
//下载模版
$('body').off('click', '#downloadXlsx').on('click', '#downloadXlsx', function () {
    window.location.href = basePath + '/upload/parkingLockDemo.xlsx';
})
$('body').off('click', '.quitBtn').on('click', '.quitBtn', function () {
    layer.closeAll(); //关闭loading
})
//导入电桩
$('#importLockfile').change(function () {
    var fileName = $('#importLockfile').val();
    $('#importLockText').html(fileName);
})
$('#sureImport').on('click', function () {
    var importLockText = $('#importLockText').html();
    if (importLockText == '') {
        return;
    }
    importElectric();
})
function importElectric() {
    var formData = new FormData();
    formData.append("file", $('#importLockfile')[0].files[0]);
    $.ajax({
        url: basePath + importLockUrl,
        type: 'POST',
        data: formData,
        async: true,
        cache: false,
        contentType: false,
        processData: false,
        success: function (returndata) {
            layer.closeAll(); //关闭loading
            if (returndata.success == true) {
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '160px'],//宽高
                    content: returndata.msg,
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
                if (returndata.status == 9001) {
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        btn: ["确定"],
                        area: ['310px', '160px'],//宽高
                        content: returndata.msg,
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
                        area: ['310px', '180px'],//宽高
                        content: returndata.msg,
                        btn: ["确定"],
                        time: 3000

                    });
                }

            }

        }
    });
}
//绑定电桩
$('#bindPowerstation').on("click", function () {
    var lockIds = '';
    var provinceCode='';
    var cityCode='';
    var areaCode='';
    $('input[name=id]').each(function () {
        var flag = $(this).prop('checked');
        if (flag == true) {
            lockIds += $(this).attr('value') + ',';
            provinceCode += $(this).attr('provinceCode') + ',';
            cityCode += $(this).attr('cityCode') + ',';
            areaCode += $(this).attr('areaCode') + ',';
        }
    });
    lockIds = lockIds.substring(0, lockIds.length - 1);
    provinceCode = provinceCode.substring(0, provinceCode.length - 1);
    cityCode = cityCode.substring(0, cityCode.length - 1);
    areaCode = areaCode.substring(0, areaCode.length - 1);
    if (!lockIds) {
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '160px'],//宽高
            content: '请选择地锁',
            time: 3000,
            btn: ["确定"]
        });
        return false;
    } else {
        window.localStorage.setItem('parkingLockId', lockIds);//存下地锁id
        filterPowerStation(provinceCode,cityCode,areaCode);//根据地锁过滤充电点
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

    }

})
function filterPowerStation(provinceCode,cityCode,areaCode){
    window.localStorage.setItem('pL_provinceCode', provinceCode);//存下地锁provinceCode
    window.localStorage.setItem('pL_cityCode', cityCode);//存下地锁cityCode
    window.localStorage.setItem('pL_areaCode', areaCode);//存下地锁areaCode

    $('#powerStationProvinceHtml').attr('data-value',provinceCode);
    $('#powerStationCityHtml').attr('data-value',cityCode);
    $('#powerStationAreaHtml').attr('data-value',areaCode);

    toLoadProvince(provinceCode, '#powerStationProvinceHtml', '.ps_provinceUl', 'toUnbindEvent');
    toLoadCity(provinceCode, cityCode, '#powerStationCityHtml', '.ps_cityUl', 'toUnbindEvent');
    toLoadArea(cityCode, areaCode, '#powerStationAreaHtml', '.ps_areaUl', 'toUnbindEvent');
}
//点击确定绑定
$('body').off('click', '.sureBindBtn').on('click', '.sureBindBtn', function () {
    layer.closeAll();
    $('#bindParkingLock').hide();
    toBatchBindPowerStationIdUrl();
})
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
        listTr += '<tr><td><input type="checkbox" name="powerstations" class="selectedBox" value="' + data[i].powerstationId + '"/></td>'
            + '<td>' + data[i].powerstationName
            + '</td><td>' + data[i].address
            + '</td></tr>';
    }
    $("#bindPowerStationTbody").html(listTr);
}
//复选框按钮单选
$('body').on('click', 'input[name=powerstations]', function () {
    var flag = $(this).prop('checked');
    if (flag == true) {
        $(this).parents('td').parents('tr').siblings().find('input[name=powerstations]').prop('checked', false).attr('disabled', true);
    } else {
        $(this).parents('td').parents('tr').siblings().find('input[name=powerstations]').attr('disabled', false);
    }

})
//确定绑定vin码
function toBatchBindPowerStationIdUrl() {
    var powerstationIds = '';
    $('input[name=powerstations]').each(function () {
        var flag = $(this).prop('checked');
        if (flag == true) {
            powerstationIds += $(this).attr('value') + ',';
        }
    });
    powerstationIds = powerstationIds.substring(0, powerstationIds.length - 1);
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
        var lockIds = window.localStorage.getItem('parkingLockId');
        $.ajax({
            type: "post",
            url: basePath + batchBindPowerStationIdUrl,
            async: true,
            data: {
                parkingLockIds: lockIds,
                powerStationId: powerstationIds
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
        });
    }
}
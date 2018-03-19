function toUnbindEvent() {
    $('.chargingMethodBlock').unbind();
    $('.electricPowerBlock').unbind();
    $('.ownerShipBlock').unbind();
    $('.provinceBlock').unbind();
    $('.cityBlock').unbind();
    $('.areaBlock').unbind();
    $('.simNameBlock').unbind();
    $('.chStatusBlock').unbind();
    $('.typeBlock').unbind();
    $('.rateInformationIdBlock').unbind();
    $('.pileMakerBlock').unbind();
    $('.productModelBlock').unbind();
    $('.ele_cpyProvinceBlock').unbind();
    $('.ele_cypCityBlock').unbind();
    $('.ele_cpyCompanyBlock').unbind();
    selectModel();
}
toUnbindEvent();
var selectMap = {
    1: 'electricPileType',
    3: 'electricChargeMode',
    4: 'electricPower'
}
initSelects(selectMap);
//功率选择
$('#electricPower').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});
//充电方式选择
$('#electricChargeMode').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});
//电桩类型
$('#electricPileType').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});
//电桩制造厂商选择
setTimeout('toPileMarkerList()', 200);
$('.pileMarkerUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});
//产品型号选择 下拉列表需要动态加载？？未完成//////
$('#productModelUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});

//运营商设置
$('.simNameUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .html($(this).text());
});
toLoadProvince('', '#province', '.provinceUl', 'toUnbindEvent');
//点击省加载市
$('.provinceUl').on("click", "li", function () {
    $('#city').html('请选择');
    $('.cityUl').html('');
    $('#city').attr('data-value', '');
    $('#areaHtml').html('请选择');
    $('.areaUl').html('');
    $('#areaHtml').attr('data-value', '');
    var provinceCodeId = $(this).attr('data-option');
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');
    if (flag == "") {
        $('#city').html('请选择');
        $('.cityUl').html('');
        $('#city').attr('data-value', '');
        $('#areaHtml').html('请选择');
        $('.areaUl').html('');
        $('#areaHtml').attr('data-value', '');
    } else {
        toLoadCity(provinceCodeId, '', '#city', '.cityUl', 'toUnbindEvent');
    }
})

//点击市加载区
$('.cityUl').on("click", "li", function () {
    $('#areaHtml').html('请选择');
    $('.areaUl').html('');
    $('#areaHtml').attr('data-value', '');
    var cityCodeId = $(this).attr('data-option');
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');
    if (flag == "") {
        $('#areaHtml').html('请选择');
        $('.areaUl').html('');
        $('#areaHtml').attr('data-value', '');
    } else {
        toLoadArea(cityCodeId, '', '#areaHtml', '.areaUl', 'toUnbindEvent');
    }
})

//点击区获取值
$('.areaUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
})
//电桩编号的提示及校验
$('#num').on('focus', function () {
    layer.tips('请输入编号，0~9999！', '#num', {
        tips: 4
    });
})
$('#num').on('blur', function () {
    var code = $('#num').val();
    if (pileNum($('#num')) == false) {
        $('#num').val('');
        layer.tips('请输入正确的编号，0~9999！', '#num', {
            tips: 4
        });
    }
  /*  else {
        $.ajax({
            type: "post",
            url: basePath + checkCodeUrl,
            async: true,
            data: {
                code: code
            },
            success: function (data) {
                if(data.success == true){
                    var data=data.dataObject;
                    if(data==false){
                        $('#num').val('');
                        layer.tips('序号重复，请重新输入！', '#num', {
                            tips: 4
                        });
                        return false;
                    }
                    return true;
                }
                else {
                    layer.closeAll();
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '160px'],//宽高
                        content: data.msg,
                        time: 2000,
                        btn: ["确定"]
                    });

                }
            }
        });
    }*/
})
//接口标准的提示及校验
$('#interfaceStandard').on('focus', function () {
    layer.tips('请输入最多30个字符！', '#interfaceStandard', {
        tips: 4
    });
})
$('#interfaceStandard').on('blur', function () {
    if (lengthTest($('#interfaceStandard'), 30) == false) {
        $('#interfaceStandard').val('');
        layer.tips('请重新输入最多30个字符！', '#interfaceStandard', {
            tips: 4
        });
    }
})
//最大电流校验
$('#maxElectricity').on('focus', function () {
    layer.tips('请输入整数！', '#maxElectricity', {
        tips: 4
    });
})
$('#maxElectricity').on('blur', function () {
    if (isInteger($('#maxElectricity')) == false) {
        $('#maxElectricity').val('');
        layer.tips('充电电流，请输入整数！', '#maxElectricity', {
            tips: 4
        });
    }
})
//最大电压校验
$('#maxVoltage').on('focus', function () {
    layer.tips('请输入整数！', '#maxVoltage', {
        tips: 4
    });
})
$('#maxVoltage').on('blur', function () {
    if (isInteger($('#maxVoltage')) == false) {
        $('#maxVoltage').val('');
        layer.tips('充电电流，请输入整数！', '#maxVoltage', {
            tips: 4
        });
    }
})
//枪头数量校验
$('#muzzleNumber').on('focus', function () {
    layer.tips('请输入整数！', '#muzzleNumber', {
        tips: 4
    });
})
$('#muzzleNumber').on('blur', function () {
    if (isInteger($('#muzzleNumber')) == false) {
        $('#muzzleNumber').val('');
        layer.tips('枪头数量，请输入整数！', '#muzzleNumber', {
            tips: 4
        });
    }
})
//具体地址校验
$('#address').on('focus', function () {
    layer.tips('请输入电桩地址，最多50个字符！', '#address', {
        tips: 4
    });
})
$('#address').on('blur', function () {
    if (lengthTest($('#address'), 50) == false) {
        $('#address').val('');
        layer.tips('请重新输入电桩地址，最多50个字符！', '#address', {
            tips: 4
        });
    }
})
//经度校验
$('#longitude').on('focus', function () {
    layer.tips('请输入经度，保留小数点后六位', '#longitude', {
        tips: 4
    });
})
$('#longitude').on('blur', function () {
    if (longitudeTest($('#longitude')) == false) {
        $('#longitude').val('');
        layer.tips('请重新输入经度，保留小数点后六位！', '#longitude', {
            tips: 4
        });
    }
})
//纬度校验
$('#latitude').on('focus', function () {
    layer.tips('请输入经度，保留小数点后六位', '#latitude', {
        tips: 4
    });
})
$('#latitude').on('blur', function () {
    if (latitudeTest($('#latitude')) == false) {
        $('#latitude').val('');
        layer.tips('请重新输入经度，保留小数点后六位！', '#latitude', {
            tips: 4
        });
    }
})
//点击新增，新增电桩
function toAddElectricPile() {
    var numValue = $('#num').val();
    var interfaceStandardValue = $('#interfaceStandard').val();
    var maxElectricityValue = $('#maxElectricity').val();
    var maxVoltageValue = $('#maxVoltage').val();
    var muzzleNumberValue = $('#muzzleNumber').val();
    var addressValue = $('#address').val();
    var longitudeValue = $('#longitude').val();
    var latitudeValue = $('#latitude').val();
    if (!numValue) {
        $('#num').focus();
        return false;
    }
    if (!interfaceStandardValue) {
        $('#interfaceStandard').focus();
        return false;
    }
    if (!maxElectricityValue) {
        $('#maxElectricity').focus();
        return false;
    }
    if (!maxVoltageValue) {
        $('#maxVoltage').focus();
        return false;
    }
    if (!muzzleNumberValue) {
        $('#muzzleNumber').focus();
        return false;
    }
    if (!addressValue) {
        $('#address').focus();
        return false;
    }
    if (!longitudeValue) {
        $('#longitude').focus();
        return false;
    }
    if (!latitudeValue) {
        $('#latitude').focus();
        return false;
    }
    if (dropDownList($('#chargingMethod')) == false) {
        layer.tips('请选择充电方式！', '#chargingMethod', {
            tips: 4
        });
        return false;
    }
    if (dropDownList($('#chPower')) == false) {
        layer.tips('请选择功率！', '#chPower', {
            tips: 4
        });
        return false;
    }
    if (dropDownList($('#type')) == false) {
        layer.tips('请选择电桩类型！', '#type', {
            tips: 4
        });
        return false;
    }
    if (dropDownList($('#pileMaker')) == false) {
        layer.tips('请选择制造商！', '#pileMaker', {
            tips: 4
        });
        return false;
    }
    if (dropDownList($('#productModel')) == false) {
        layer.tips('请选择产品型号！', '#productModel', {
            tips: 4
        });
        return false;
    }
    if (dropDownList($('#province')) == false) {
        layer.tips('请选择省！', '#province', {
            tips: 4
        });
        return false;
    }
    if (dropDownList($('#city')) == false) {
        layer.tips('请选择市！', '#city', {
            tips: 4
        });
        return false;
    }
    if (dropDownList($('#areaHtml')) == false) {
        layer.tips('请选择区！', '#areaHtml', {
            tips: 4
        });
        return false;
    }
    if (dropDownList($('#ele_cpyCompanyName')) == false) {
        layer.tips('请选择资产归属！', '#ele_cpyCompanyName', {
            tips: 4
        });
        return false;
    }
    var obj = {
        num: $('#num').val(),
        chargingMethod: $('#chargingMethod').attr('data-value'),
        power: $('#chPower').attr('data-value'),
        typeId: $('#type').attr('data-value'),
        interfaceStandard: $('#interfaceStandard').val(),
        pileMakerId: $('#pileMaker').attr('data-value'),
        productModelId: $('#productModel').attr('data-value'),
        maxVoltage: $('#maxVoltage').val(),
        maxElectricity: $('#maxElectricity').val(),
        provinceCode: $('#province').attr('data-value'),
        cityCode: $('#city').attr('data-value'),
        areaCode: $('#areaHtml').attr('data-value'),
        address: $('#address').val(),
        longitude: $('#longitude').val(),
        latitude: $('#latitude').val(),
        muzzleNumber: $('#muzzleNumber').val(),
        simName: $('#simName').html(),
        simMac: $('#simMac').val(),
        remark: $('#remark').val(),
        companyId:$('#ele_cpyCompanyName').attr('data-value'),
        companyNumber:$('#ele_cpyCompanyName').attr('data-cpyNumber')
    };
    var index=layer.load(1);
    $.ajax({
        type: "post",
        url: basePath + addElectricUrl,
        async: true,
        data: obj,
        success: function (data) {
            layer.closeAll();
            if (data.success == true) {
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
                        window.location.href = "electricPile_list.html";
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        window.location.href = "electricPile_list.html";
                    }
                });

            } else if(data.status == 9001) {
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
                    yes:function(index,layero){
                        layer.closeAll();
                        window.top.location.href = basePath + toLoginUrl;
                    },
                    cancel:function(index,layero){
                        layer.closeAll();
                        window.top.location.href = basePath + toLoginUrl;
                    }
                });

            }else {
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
getTypeSpanList();
function getTypeSpanList() {
    $.ajax({
        type: "post",
        url: basePath + getTypeSpanForListUrl,
        async: true,
        success: function (data) {
            var getTypeSpanHtml = '<li data-option="" >请选择</li>';
            var data = data.dataObject;
            for (var i = 0; i < data.length; i++) {
                getTypeSpanHtml += '<li data-option="' + data[i].pkTypeSpanId + '">' + data[i].tsTypeSpan + '</li>';
            }
            $('#productModelUl').html(getTypeSpanHtml);
        }

    })
}

//返回电桩列表页面
$('body').on('click', '#toPileList', function () {
    window.history.back();
})
//资产归属的新增
toLoadProvince('', '#el_cpyProvinceCode', '.el_cpyProvinceUl', 'toUnbindEvent');
$('.el_cpyProvinceUl').on("click", "li", function () {
    var provinceCodeId = $(this).attr('data-option');
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');
    if (flag == "") {
        $('#ele_cypCityCode').html('请选择');
        $('.ele_cypCityUl').html('');
        $('#ele_cypCityCode').attr('data-value', '');
        $('#ele_cpyCompanyName').html('请选择');
        $('.ele_cpyCompanyUl').html('');
        $('#ele_cpyCompanyName').attr('data-value', '');
    } else {
        toLoadCity(provinceCodeId, '', '#ele_cypCityCode', '.ele_cypCityUl', 'toUnbindEvent');
    }
});
$('.ele_cypCityUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');
    var cypCityCodeValue = $('#ele_cypCityCode').attr('data-value');
    var cpyProvinceCodeValue = $('#ele_cpyProvinceCode').attr('data-value');
    if (flag == "") {
        $('#ele_cpyCompanyName').html('请选择');
        $('.ele_cpyCompanyUl').html('');
        $('#ele_cpyCompanyName').attr('data-value', '');
    } else {
        toLoadComponyName(cpyProvinceCodeValue,cypCityCodeValue);
    }
});

$('.ele_cpyCompanyUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-cpyNumber', $(this).attr('data-cpyNumber'));
});
function toLoadComponyName(cpyProvinceCodeValue,cypCityCodeValue , cpyId) {
    var cpyObject = {
        provinceCode: cpyProvinceCodeValue,
        cpyCity: cypCityCodeValue
    };
    if (JSON.stringify(cpyObject) == "{}") {
        $('#ele_cpyCompanyName').html("请选择");
    } else {
        toAjaxBindCompany(cpyObject,cypCityCodeValue, cpyId);
    }
}
function toAjaxBindCompany(cpyObject, cpyId){
    $.ajax({
        type: "post",
        url: basePath + initCompanyListUrl,
        async: true,
        data: cpyObject,
        success: function (data) {
            if (data.success == true) {
                toLoadCompany(data,cpyId);//加载公司
            }
        }
    });
}
function toLoadCompany(data,cpyId){
    var dataModule = data.dataObject;
    if (cpyId == '') {
        var cypCompanyLi = '<li data-option="">请选择</li>';
        for (var i = 0; i < dataModule.length; i++) {
            cypCompanyLi += '<li data-option="' + dataModule[i].cpyId + '" data-cpyNumber="' + dataModule[i].cpyNumber + '">' + dataModule[i].cpyName + '</li>';
        }
    } else {
        var cypCompanyLi = '<li data-option="">请选择</li>';
        for (var i = 0; i < dataModule.length; i++) {
            if (dataModule[i].cpyId == cpyId) {
                $('#ele_cpyCompanyName').html(dataModule[i].cpyId);
                cypCompanyLi += '<li data-option="' + dataModule[i].cpyId + '" data-cpyNumber="' + dataModule[i].cpyNumber + '" class="data-selected">' + dataModule[i].cpyName + '</li>';
            } else {
                cypCompanyLi += '<li data-option="' + dataModule[i].cpyId + '" data-cpyNumber="' + dataModule[i].cpyNumber + '">' + dataModule[i].cpyName + '</li>';
            }

        }
    }
    $('.ele_cpyCompanyUl').html(cypCompanyLi);
    toUnbindEvent();
}
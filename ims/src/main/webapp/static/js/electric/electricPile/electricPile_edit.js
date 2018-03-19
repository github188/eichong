var index=layer.load(1);
var electricPileId = getUrlParam('electricPileId');
function toUnbindEvent() {
    $('.electricPowerBlock').unbind();
    $('.provinceBlock').unbind();
    $('.cityBlock').unbind();
    $('.areaBlock').unbind();
    $('.chStatusBlock').unbind();
    $('.typeBlock').unbind();
    selectModel();
}
toUnbindEvent();
//获取单个电桩详细，并且展示到对应框上面
getElectricDetail();
function getElectricDetail() {
    $.ajax({
        type: "post",
        url: basePath + getElectricDetailUrl,
        async: true,
        data: {
            electricPileId: electricPileId
        },
        success: function (data) {
            layer.closeAll();
            $('#code').html(data.code);
            $('#chargingMethod').html(data.chChargingMethod);
            $('#chargingMethod').attr('data-value', data.chargingMethod);
            $('#chPower').html(data.chPower);
            $('#chPower').attr('data-value', data.power);

            $('#ownerShip').html(data.company);

            $('#productModel').html(data.productModel);

            $('#company').html(data.company);

            $('#province').html(data.province);
            $('#province').attr('data-value', data.provinceCode);


            $('#city').html(data.city);
            $('#city').attr('data-value', data.cityCode);

            $('#areaHtml').html(data.area);
            $('#areaHtml').attr('data-value', data.areaCode);

            window.localStorage.setItem('ele_provinceCode', data.provinceCode);
            window.localStorage.setItem('ele_cityCode', data.cityCode);
            window.localStorage.setItem('ele_areaCode', data.areaCode);
            //等待确定
            $('#simName').html(data.simName);

            $('#remark').val(data.remark);

            $('#num').val(data.num);

            $('#chStatus').html(data.chStatus);

            $('#type').html(data.type);
            $('#type').attr('data-value', data.typeId);

            $('#pileMaker').html(data.pileMaker);
            //未确认是否能修改 高
            $('#rateInformationId').val(data.rateInformationId);
            $('#muzzleNumber').val(data.muzzleNumber);
            $('#maxVoltage').val(data.maxVoltage);
            $('#maxElectricity').val(data.maxElectricity);


            $('#longitude').val(data.longitude);
            $('#latitude').val(data.latitude);
            $('#interfaceStandard').val(data.interfaceStandard);
            $('#address').val(data.address);
            $('#simMac').val(data.simMac);


            var ele_provinceCode = window.localStorage.getItem('ele_provinceCode');
            var ele_cityCode = window.localStorage.getItem('ele_cityCode');
            var ele_areaCode = window.localStorage.getItem('ele_areaCode');
            //console.log(provinceCode + "::" + cityCode)
            if (ele_provinceCode == null) {
                ele_provinceCode = '';
            }
            if (ele_cityCode == null) {
                ele_cityCode = '';
            }
            if (ele_areaCode == null) {
                ele_areaCode = '';
            }
            //去加载城市的省
            toLoadProvince(ele_provinceCode, '#province', '.provinceUl', 'toUnbindEvent');
            toLoadCity(ele_provinceCode,ele_cityCode,'#city',  '.cityUl', 'toUnbindEvent');
            toLoadArea(ele_cityCode, ele_areaCode, '#areaHtml', '.areaUl', 'toUnbindEvent');

        }
    });

}

//配置每一个下拉框拉接口，并处理加载选项到点击事件
/*
 3资产归属
 11默认费率 未写接口*/
var selectMap = {
    1: 'electricPileType',
    4: 'electricPower'
}
initSelects(selectMap);
//功率选择
$('#electricPower').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});
//电桩类型
$('#electricPileType').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});
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
        $('.areaUl').html('');
        toLoadArea(cityCodeId, '', '#areaHtml', '.areaUl', 'toUnbindEvent');
    }
})

//点击区获取值
$('.areaUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
})

//返回按钮返回到电桩详细
$('body').on('click', '#toPileDetail', function () {
    window.location.href = 'electricPile_list.html';
    //window.location.href = 'electricPile_detail.html?electricPileId=' + electricPileId;
})

//电桩编号的提示及校验
$('#num').on('focus', function () {
    layer.tips('请输入编号数字！', '#num', {
        tips: 4
    });
})
$('#num').on('blur', function () {
    if (isInteger($('#num')) == false) {
        $('#num').val('');
        layer.tips('请输入正确的编号，整数！', '#num', {
            tips: 4
        });
    }
})
//费率
/*$('#rateInformationId').on('focus', function () {
    layer.tips('请输入默认费率，大于零的整数！', '#rateInformationId', {
        tips: 4
    });
})
$('#rateInformationId').on('blur', function () {
    if (isInteger($('#rateInformationId')) == false) {
        $('#rateInformationId').val('');
        layer.tips('请输入默认费率，大于零的整数！', '#rateInformationId', {
            tips: 4
        });
    }else if($('#rateInformationId').val()==0){
        $('#rateInformationId').val('');
        layer.tips('请输入默认费率，大于零的整数！', '#rateInformationId', {
            tips: 4
        });
    }
})*/


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
    if (lengthTest($('#address'), 30) == false) {
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
//编辑保存按钮
function toModifyElectricPile() {
    var numValue = $('#num').val();
    var rateInformationIdValue = $('#rateInformationId').val();
    var interfaceStandardValue = $('#interfaceStandard').val();
    var muzzleNumberValue = $('#muzzleNumber').val();
    var addressValue = $('#address').val();
    var longitudeValue = $('#longitude').val();
    var latitudeValue = $('#latitude').val();
    var maxVoltage=$('#maxVoltage').val();
    var maxElectricity=$('#maxElectricity').val();
    if (!numValue) {
        $('#num').focus();
        return false;
    }

    if (!interfaceStandardValue) {
        $('#interfaceStandard').focus();
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
    if (!rateInformationIdValue) {
        $('#rateInformationId').val(0);
    }
    var obj = {
        id: electricPileId,
        num: $('#num').val(),
        chargingMethod: $('#chargingMethod').attr('data-value'),
        power: $('#chPower').attr('data-value'),
        typeId: $('#type').attr('data-value'),
        interfaceStandard: $('#interfaceStandard').val(),
        rateInformationId: $('#rateInformationId').val(),
        provinceCode: $('#province').attr('data-value'),
        cityCode: $('#city').attr('data-value'),
        areaCode: $('#areaHtml').attr('data-value'),
        address: $('#address').val(),
        longitude: $('#longitude').val(),
        latitude: $('#latitude').val(),
        muzzleNumber: $('#muzzleNumber').val(),
        simName: $('#simName').val(),
        simMac: $('#simMac').val(),
        remark: $('#remark').val(),
        maxVoltage:maxVoltage,
        maxElectricity:maxElectricity
    };
    $.ajax({
        type: "post",
        url: basePath + modifyElectricUrl,
        async: false,
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
                        window.location.href = "electricPile_list.html";
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        window.location.href = "electricPile_list.html";
                    }
                });

            }else if(data.status == 9001) {
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

            } else {
                layer.closeAll();
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '160px'],//宽高
                    content: data.msg,
                    //time: 2000,
                    btn: ["确定"]
                });
            }
        }

    });

}
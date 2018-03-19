var powerstationId = getUrlParam('powerstationId');
function toUnbindEvent() {
    $('.cpyProvinceBlock').unbind();
    $('.cypCityBlock').unbind();
    $('.cpyAreaBlock').unbind();
    $('.rateInfoBlock').unbind();
    $('.electricPileTypeBlock').unbind();
    $('.electricChargeModeBlock').unbind();
    $('.electricPowerBlock').unbind();
    $('.pileMakerBlock').unbind();
    selectModel();
}


getPowerStation();
function getPowerStation() {
    var index = layer.load(1);
    $.ajax({
        type: "post",
        url: basePath + getPowerStationByIdUrl,
        async: true,
        data: {
            powerstationId: powerstationId
        },
        success: function (data) {
            layer.closeAll();
            if (data.success == true) {
                var data = data.dataObject;
                var powerstationName = data.powerstationName;
                $('#powerstationName').val(powerstationName);

                $('input[name=provinceCode]').val(data.provinceCode);
                $('input[name=cityCode]').val(data.cityCode);
                $('input[name=areaCode]').val(data.areaCode);

                var provinceName = data.provinceName;
                $('#province').html(provinceName).attr('data-value', data.provinceCode);

                var cityName = data.cityName;
                $('#city').html(cityName).attr('data-value', data.cityCode);

                var areaName = data.areaName;
                $('#areaHtml').html(areaName).attr('data-value', data.areaCode);
                //
                window.localStorage.setItem('power_provinceCode', data.provinceCode);
                window.localStorage.setItem('power_cityCode', data.cityCode);
                window.localStorage.setItem('power_areaCode', data.areaCode);
                var latitude = data.latitude;
                $('#latitude').val(latitude);

                var phone = data.phone;
                $('#phone').val(phone);

                var parkingFee = data.parkingFee;
                $('#parkingFee').val(parkingFee);

                var rateInfoId = data.rateInfoId;
                $('#rateInfoId').val(rateInfoId);

                var address = data.address;
                $('#address').val(address);

                var longitude = data.longitude;
                $('#longitude').val(longitude);

                var onlineTime = data.onlineTime;
                $('#onlineTime').val(onlineTime);

                for (var j = 0; j < data.picImgList.length; j++) {
                    var src = data.picImgList[j];
                    $('#picDiv').append("<div class='imgContainer'><img src='" + src + "'><p onclick='delUrl(this)' class='imgDelete'>删除</p></div>");
                }
                var power_provinceCode = window.localStorage.getItem('power_provinceCode');
                var power_cityCode = window.localStorage.getItem('power_cityCode');
                var power_areaCode = window.localStorage.getItem('power_areaCode');
                //console.log(provinceCode + "::" + cityCode)
                if (power_provinceCode == null) {
                    power_provinceCode = '';
                }
                if (power_cityCode == null) {
                    power_cityCode = '';
                }
                if (power_areaCode == null) {
                    power_areaCode = '';
                }
                //去加载城市的省
                toLoadProvince(power_provinceCode, '#province', '.provinceUl', 'toUnbindEvent');
                toLoadCity(power_provinceCode, power_cityCode, '#city', '.cityUl', 'toUnbindEvent');
                toLoadArea(power_cityCode, power_areaCode, '#areaHtml', '.areaUl', 'toUnbindEvent');
            }
        }
    });
}
//点击省加载市
$('.provinceUl').on("click", "li", function () {
    $('#city').html('请选择');
    $('.cityUl').html('');
    $('#city').attr('data-value', '');
    $('#areaHtml').html('请选择');
    $('.areaUl').html('');
    $('#areaHtml').attr('data-value', '');
    var provinceCodeId = $(this).attr('data-option');
    $('input[name=provinceCode]').val(provinceCodeId);
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
var picArr = [];
function delUrl(obj) {
    var url = $(obj).parent().find('img').attr('src');
    $(obj).parent().remove();
    picArr.push(url);
}

//点击市加载区
$('.cityUl').on("click", "li", function () {
    $('#areaHtml').html('请选择');
    $('.areaUl').html('');
    $('#areaHtml').attr('data-value', '');
    var cityCodeId = $(this).attr('data-option');
    $('input[name=cityCode]').val(cityCodeId);
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
    var areaCodeId = $(this).attr('data-option');
    $('input[name=areaCode]').val(areaCodeId);
})
$('body').off('click', '#toPowerStationDetail').on('click', '#toPowerStationDetail', function () {
    window.location.href = 'powerStation_list.html';
    //window.location.href = 'powerStation_detail.html?powerstationId=' + powerstationId;
})
//加载对应的充电点下面的电桩
toLoadElectricPils();
function toLoadElectricPils() {
    $.ajax({
        type: "post",
        url: basePath + getAllElectricPileListUrl,
        async: true,
        data: {
            powerStationId: powerstationId,
            flag: 1
        },
        success: function (data) {
            //alert(JSON.stringify(data));
            if (data.success == true) {
                var data = data.dataObject;
                var listTr = "";
                //var readonly = "";
                for (var i = 0; i < data.length; i++) {
                    //var chStatus = data[i].chStatus;
                    //if (chStatus == '已解绑') {
                    //    readonly = 'disabled';
                    //} else {
                    //    readonly = '';
                    //}
                    listTr += '<tr><td><input type="checkbox" data-flag="0" ' + ' data-rateInformationId="' + data[i].rateInformationId + '" name="ids" class="selectedBox" value="' + data[i].id + '"/></td>'
                        + '<td>' + (i + 1) + '</td>'
                        + '<td><span>' + data[i].code
                        + '</span></td><td>' + data[i].num
                        + '</td><td>' + data[i].chStatus
                        + '</td><td>' + data[i].chChargingMethod
                        + '</td><td>' + data[i].chPower
                        + '</td><td>' + data[i].type
                        + '</td><td class="rateInformationIds"><input value="' + data[i].rateInformationId
                        + '"/></td><td>' + data[i].productModel
                        + '</td></tr>';

                }
                $("#electricPile").html(listTr);
            }
        }
    });
}

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
//绑定电桩
$("body").on("click", "#selectPileBtn", function () {
    $(".selAllElectric").attr("checked", false);
    getElectricPileList();
    layer.closeAll();
    layer.open({
        type: 1,
        title: '选择电桩',
        btn: ["确定"],
        shadeClose: true,
        closeBtn: 1,//点击遮罩关闭层
        area: ['860px', '400px'],
        content: $("#bindPile"),
        yes: function () {
            $(".selAllElectric").attr("checked", false);
            layer.closeAll();
            selectElectricCallback();

        }
    });
})
//加载绑定电桩部分电数据
//去加载表格的数据

function getElectricPileList() {
    getHiddenInputValue();
    initTable("BindElectricForm", "BindElectricPage", electricListUrl, bindElectricListCallback);
}
function bindElectricListCallback(req) {
    var data = req.dataObject;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        var dateTime = data[i].gmtCreate.time;
        listTr += '<tr><td><input type="checkbox" name="electricIds" class="selectedElectric" value="' + data[i].id
            + '" code="' + data[i].code + '" num="' + data[i].num
            + '" chStatus="' + data[i].chStatus
            + '" chChargingMethod="' + data[i].chChargingMethod
            + '"  data-type="' + data[i].type
            + '"  chPower="' + data[i].chPower
            + '"  productModel="' + data[i].productModel
            + '"  rateInformationId="' + data[i].rateInformationId
            + '"/></td><td class="electricPileList_electricPileCode"><span data-id="' + data[i].id + '">' + data[i].code
            + '</span></td><td>' + data[i].num
            + '</td><td>' + data[i].chChargingMethod
            + '</td><td>' + data[i].chPower
            + '</td><td>' + data[i].muzzleNumber
            + '</td><td>' + data[i].type
            + '</td><td>' + data[i].pileMaker
            + '</td><td>' + data[i].address
            + '</td><td>' + new Date(dateTime).format("yyyy-MM-dd")
            + '</td></tr>';

    }
    $("#bindPileTbody").html(listTr);

}
function getHiddenInputValue() {
    var powerHtmlValue = $('#powerHtml').attr('data-value');//功率
    var chargingMethodHtmlValue = $('#chargingMethodHtml').attr('data-value');//充电方式
    var pileMakerIdHtmlValue = $('#pileMakerIdHtml').attr('data-value'); //制造商
    var typeIdHtmlValue = $('#typeIdHtml').attr('data-value'); //制造商
    var provinceValue = $('#province').attr('data-value'); //省份code
    var cityValue = $('#city').attr('data-value'); //城市code
    var areaHtmlValue = $('#areaHtml').attr('data-value'); //区code

    var codeValue = $('input[name=code]').val();
    if (codeValue == "") {
        codeValue = $('input[name=code]').val('');
    } else {
        $('input[name=code]').val();
    }

    if (powerHtmlValue == "") {
        $('input[name=power]').val('');
    } else {
        $('input[name=power]').val(powerHtmlValue);
    }
    if (chargingMethodHtmlValue == "") {
        $('input[name=chargingMethod]').val('');
    } else {
        $('input[name=chargingMethod]').val(chargingMethodHtmlValue);
    }
    if (pileMakerIdHtmlValue == "") {
        $('input[name=pileMakerId]').val('');
    } else {
        $('input[name=pileMakerId]').val(pileMakerIdHtmlValue);
    }
    if (typeIdHtmlValue == "") {
        $('input[name=typeId]').val('');
    } else {
        $('input[name=typeId]').val(typeIdHtmlValue);
    }

    $('input[name=provinceCode]').val(provinceValue);
    $('input[name=cityCode]').val(cityValue);
    $('input[name=areaCode]').val(areaHtmlValue);
}
//点击全部选择电桩，选择对应电桩
// 全选和反选样式
$("body").on('click', ".selAllElectric", function () {
    $(".selectedElectric").prop("checked", $(this).is(':checked'));
})
//点击绑定往后面添加绑定后的电桩

function selectElectricCallback() {
    var i = parseInt($("#electricPile tr").length);
    var listTr = "";
    var arr = [];
    var b = 0;
    $('input[name="electricIds"]:checked').each(function () {
        listTr += '<tr><td>'
            + '<input type="checkBox" name="ids" class="selectedBox" data-flag="1" data-rateInformationId="' + $(this).attr("rateInformationId") + '"  value="' + $(this).val() + '" /></td><td>'
            + (++i) + '</td><td>'
            + $(this).attr("code") + '</td><td>'
            + $(this).attr("num") + '</td><td>'
            + $(this).attr("chStatus") + '</td><td>'
            + $(this).attr("chChargingMethod") + '</td><td>'
            + $(this).attr("chPower") + '</td><td>'
            + $(this).attr("data-type") + '</td><td class="rateInformationIds readonlyRateInformation"><input value="'
            + $(this).attr("rateInformationId") + '"></td><td>'
            + $(this).attr("productModel") + '</td></tr>';
        arr[b] = $(this).val();
        b++;
    });
    $("#electricPile").append(listTr);
}

//充电点名称校验
$('#powerstationName').on('blur', function () {
    layer.tips('请输入充电点名称，最多30个字符！', '#powerstationName', {
        tips: 4
    });
})
$('#powerstationName').on('blur', function () {
    if (lengthTest($('#powerstationName'), 30) == false) {
        $('#powerstationName').val('');
        layer.tips('请重新输入充电点名称，最多30个字符！', '#powerstationName', {
            tips: 4
        });
    }
})
//费率校验
$('#rateInfoId').on('focus', function () {
    layer.tips('请输入默认费率，大于零的整数！', '#rateInfoId', {
        tips: 4
    });
})
$('#rateInfoId').on('blur', function () {
    if (isInteger($('#rateInfoId')) == false) {
        $('#rateInfoId').val('');
        layer.tips('请输入默认费率，大于零的整数！', '#rateInfoId', {
            tips: 4
        });
    } else if ($('#rateInfoId').val() == 0) {
        $('#rateInfoId').val('');
        layer.tips('请输入默认费率，大于零的整数！', '#rateInfoId', {
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


//充电点编辑
var imgSrc = []; //图片路径
var imgFile = []; //文件流
var imgName = []; //图片名字
//选择图片
function imgUpload(obj) {
    var oInput = '#' + obj.inputId;
    var imgBox = '#' + obj.imgBox;
    var btn = '#' + obj.buttonId;
    $(oInput).on("change", function () {
        var fileImg = $(oInput)[0];
        var fileList = fileImg.files;
        for (var i = 0; i < fileList.length; i++) {
            var imgSrcI = getObjectURL(fileList[i]);
            imgName.push(fileList[i].name);
            imgSrc.push(imgSrcI);
            imgFile.push(fileList[i]);
        }
        addNewContent(imgBox);
    })
    $(btn).on('click', function () {
        if (!limitNum(obj.num)) {
            layer.closeAll();
            layer.open({
                type: 1,
                offset: '100px',
                title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                shadeClose: false,
                closeBtn: 1,
                area: ['310px', '180px'],//宽高
                content: '最多只能传6张图片',
                time: 2000,
                btn: ["确定"]
            });
            return false;
        }
        //用formDate对象上传
        var fd = new FormData($('#upBox')[0]);
        for (var i = 0; i < imgFile.length; i++) {
            fd.append(obj.data, imgFile[i]);
        }
        submitPicture(obj.upUrl, fd);
    })
}
//图片展示
function addNewContent(obj) {
    $(imgBox).html("");
    for (var a = 0; a < imgSrc.length; a++) {
        var oldBox = $(obj).html();
        $(obj).html(oldBox + '<div class="imgContainer"><img title=' + imgName[a] + ' alt=' + imgName[a] + ' src=' + imgSrc[a] + '><p onclick="removeImg(this,' + a + ')" class="imgDelete">删除</p></div>');
    }
}
//删除
function removeImg(obj, index) {
    imgSrc.splice(index, 1);
    imgFile.splice(index, 1);
    imgName.splice(index, 1);
    var boxId = "#" + $(obj).parent('.imgContainer').parent().attr("id");
    addNewContent(boxId);
}
//限制图片个数
function limitNum(num) {
    if (!num) {
        return true;
    } else if (imgFile.length > num) {
        return false;
    } else {
        return true;
    }
}

function submintPicUrl() {
    var picList = '';
    for (var i = 0; i < picArr.length; i++) {
        picList += picArr[i] + ';';
    }
    picList = picList.substring(0, picList.length - 1);
    return picList;
}
//电桩infos要求json格式
function getInfos() {
    var json = "[";
    var arr = [];
    var flag = false;
    if ($('input[name=ids]').length == 0) {
        json += "[";
    } else {
        $('input[name=ids]').each(function () {
            var id = $(this).attr('value');
            var rateInfoId = $(this).parent().siblings().last().prev().find('input').val();
            if (rateInfoId == '') {
                rateInfoId = 0;
            }
            if (arr.length == 0) {
                json += "{\"id\":" + "\"" + id
                    + "\"" + ",\"rateInfoId\":" + "\""
                    + rateInfoId + "\"},";
                arr.push(id);
            } else {
                for (var i = 0; i < arr.length; i++) {
                    if (arr[i] == id) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    arr.push(id);
                    json += "{\"id\":" + "\"" + id
                        + "\"" + ",\"rateInfoId\":" + "\""
                        + rateInfoId + "\"},";
                }
            }

        });
    }
    json = json.substring(0, json.length - 1);
    json += "]";
    return json;
}
//对象数组去重

//上传(将文件流数组传到后台)
function submitPicture(url, data) {
    var picList = submintPicUrl();
    var infosJson = getInfos();
    //console.log(infosJson)
    data.append("listImgUrl", picList);
    data.append("infos", infosJson);
    data.append("powerstationId", powerstationId);

    var powerstationNameValue = $('#powerstationName').val();
    var rateInfoIdValue = $('#rateInfoId').val();
    var addressValue = $('#address').val();
    var longitudeValue = $('#longitude').val();
    var latitudeValue = $('#latitude').val();
    if ($('#province').attr('data-value') == '') {
        layer.tips('请选择省！', '#province', {
            tips: 4
        });
        return false;
    }
    if ($('#city').attr('data-value') == '') {
        layer.tips('请选择市！', '#city', {
            tips: 4
        });
        return false;
    }
    if ($('#areaHtml').attr('data-value') == '') {
        layer.tips('请选择区！', '#areaHtml', {
            tips: 4
        });
        return false;
    }
    if (!powerstationNameValue) {
        $('#powerstationName').focus();
        return false;
    }
    if (!rateInfoIdValue) {
        $('#rateInfoId').focus();
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
    if (url && data) {
        var index = layer.load(1);
        $.ajax({
            type: "post",
            url: url,
            async: true,
            data: data,
            processData: false,
            contentType: false,
            success: function (returndata) {
                layer.closeAll();
                if (returndata.success == true) {
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '180px'],//宽高
                        content: returndata.msg,
                        btn: ["确定"],
                        yes: function (index, layero) {
                            window.location.href = 'powerStation_list.html';
                        },
                        cancel: function (index, layero) {
                            layer.closeAll();
                            window.location.href = 'powerStation_list.html';
                        }
                    });
                } else if (returndata.status == 9001) {
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
                    layer.closeAll();
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '180px'],//宽高
                        content: returndata.msg,
                        //time:2000,
                        btn: ["确定"]
                    });
                }

            }
        });
    }
}
//图片灯箱=======图片全屏展示
/*onclick="imgDisplay(this)"
 function imgDisplay(obj) {
 var src = $(obj).attr("src");
 var imgHtml = '<div style="width: 100%;height: 100vh;overflow: auto;background: rgba(0,0,0,0.5);text-align: center;position: fixed;top: 0;left: 0;z-index: 1000;"><img src=' + src + ' style="margin-top: 100px;width: 70%;margin-bottom: 100px;"/><p style="font-size: 50px;position: fixed;top: 30px;right: 30px;color: white;cursor: pointer;" onclick="closePicture(this)">×</p></div>'
 $('body').append(imgHtml);
 }*/
//关闭
function closePicture(obj) {
    $(obj).parent("div").remove();
}

//图片预览路径
function getObjectURL(file) {
    var url = null;
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}

imgUpload({
    inputId: 'file', //input框id
    imgBox: 'imgBox', //图片容器id
    buttonId: 'saveBtn', //提交按钮id
    upUrl: basePath + modifyPowerStationUrl, //提交地址
    data: 'file', //参数名
    num: "6" //上传个数
})

//解绑全选
$("body").on('click', "#selectPileCheck", function () {
    $('#electricPile').find('input[data-flag=0]').each(function () {
        var disabled = $(this).prop('disabled');
        if (disabled == false) {
            $(this).prop("checked", $(this).is(':checked'));
        } else {
            $(this).prop("checked", false);
        }
    })

})
//解绑
$('body').off('click', '#unBindBtn').on('click', '#unBindBtn', function () {
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["确认", "font-size:12px;text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '160px'],//宽高
        content: '是否解绑？',
        btn: ["确定"],
        yes: function (index, layero) {
            layer.closeAll();
            toUnBindElectric();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }


    })
})
function toUnBindElectric() {
    var electricPileIds = "";//传默认加载的电桩id
    $('#electricPile').find('input[data-flag=0]').each(function () {
        var flag = $(this).prop('checked');
        if (flag == true) {
            electricPileIds += $(this).attr('value') + ',';
        }
    })
    electricPileIds = electricPileIds.substring(0, electricPileIds.length - 1);
    if (!electricPileIds) {
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '160px'],//宽高
            content: '请选择已经绑定的电桩',
            time: 3000,
            btn: ["确定"]
        });
        return false;
    }
    else {
        $.ajax({
            type: "post",
            url: basePath + unBindUrl,
            async: true,
            data: {
                electricPileIds: electricPileIds,
                bindType: 0//充电点0 集中器1
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
                        content: '解绑成功',
                        btn: ["确定"],
                        yes: function (index, layero) {
                            layer.closeAll();
                            $('.selAll').prop('checked', false);
                            window.location.href = "powerStation_list.html";
                        },
                        cancel: function (index, layero) {
                            layer.closeAll();
                            $('.selAll').prop('checked', false);
                            window.location.href = "powerStation_list.html";
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
                        time: 3000,
                        btn: ["确定"]
                    });
                }
            }
        });
    }

}



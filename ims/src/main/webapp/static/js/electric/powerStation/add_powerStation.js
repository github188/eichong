var imgSrc = []; //图片路径
var imgFile = []; //文件流
var imgName = []; //图片名字
//选择图片
function imgUpload(obj) {
    var oInput = '#' + obj.inputId;
    var imgBox = '#' + obj.imgBox;
    var btn = '#' + obj.buttonId;
    $(oInput).on("change", function() {
        var fileImg = $(oInput)[0];
        var fileList = fileImg.files;
        for(var i = 0; i < fileList.length; i++) {
            var imgSrcI = getObjectURL(fileList[i]);
            imgName.push(fileList[i].name);
            imgSrc.push(imgSrcI);
            imgFile.push(fileList[i]);
        }
        addNewContent(imgBox);
    })
    $(btn).on('click', function() {
        if(!limitNum(obj.num)){
            layer.closeAll();
            layer.open({
                type: 1,
                offset: '100px',
                title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                shadeClose: false,
                closeBtn: 1,
                area: ['310px', '180px'],//宽高
                content: '最多只能传6张图片',
                time:2000,
                btn: ["确定"]
            });
            return false;
        }
        //用formDate对象上传
        var fd = new FormData($('#upBox')[0]);
        for(var i=0;i<imgFile.length;i++){
            fd.append(obj.data,imgFile[i]);
        }
        submitPicture(obj.upUrl, fd);
    })
}
//图片展示
function addNewContent(obj) {
    $(imgBox).html("");
    for(var a = 0; a < imgSrc.length; a++) {
        var oldBox = $(obj).html();
        $(obj).html(oldBox + '<div class="imgContainer"><img title=' + imgName[a] + ' alt=' + imgName[a] + ' src=' + imgSrc[a] + ' ><p onclick="removeImg(this,' + a + ')" class="imgDelete">删除</p></div>');
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
function limitNum(num){
    if(!num){
        return true;
    }else if(imgFile.length>num){
        return false;
    }else{
        return true;
    }
}

//上传(将文件流数组传到后台)
function submitPicture(url,data) {
    var powerstationNameValue = $('#powerstationName').val();
    var rateInfoIdValue = $('#rateInfoId').val();
    var addressValue = $('#address').val();
    var longitudeValue = $('#longitude').val();
    var latitudeValue = $('#latitude').val();
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
    var index=layer.load(1);
    if(url&&data){
        $.ajax({
            type: "post",
            url: url,
            async: true,
            data: data,
            processData: false,
            contentType: false,
            success: function(returndata) {
                if(returndata.success==true){
                    layer.closeAll();
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '180px'],//宽高
                        content: returndata.msg,
                        btn: ["确定"],
                        yes:function(index,layero){
                            window.location.href='powerStation_list.html';
                        },
                        cancel: function (index, layero) {
                            layer.closeAll();
                            window.location.href='powerStation_list.html';
                        }
                    });
                }else if(returndata.status == 9001) {
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

                }else{
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
//图片灯箱(图片全屏展示49 addNewContent() )
//onclick="imgDisplay(this)"
/*function imgDisplay(obj) {
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
    if(window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if(window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if(window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}

imgUpload({
    inputId: 'file', //input框id
    imgBox: 'imgBox', //图片容器id
    buttonId: 'addPowerStation', //提交按钮id
    upUrl: basePath + addPowerStationUrl, //提交地址
    data: 'file', //参数名
    num: "6" //上传个数
})
function toUnbindEvent() {
    $('.cpyProvinceBlock').unbind();
    $('.cypCityBlock').unbind();
    $('.cpyAreaBlock').unbind();
    selectModel();
}
toUnbindEvent();
//点击返回
$('body').off('click', '#toPowerStationList').on('click', '#toPowerStationList', function () {
    //window.location.href = 'powerStation_list.html';
    window.history.back();
})
//默认进来加载省的数据
setTimeout("toLoadProvince('', '#province', '.provinceUl', 'toUnbindEvent')", 100);
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
    var areaId = $(this).attr('data-option');
    $('input[name=areaCode]').val(areaId);
})
//充电点名称校验
$('#powerstationName').on('focus', function () {
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
//默认费率校验
$('#rateInfoId').on('focus', function () {
    layer.tips('请输入默认费率，大于零的整数！', '#rateInfoId', {
        tips: 4
    });
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
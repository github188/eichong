function toUnbindEvent() {
    $('.provinceBlock').unbind();
    $('.cityBlock').unbind();
    $('.companyBlock').unbind();
    $('.levelBlock').unbind();
    $('.statusBlock').unbind();
    $('.sexBlock').unbind();
    $('.accountBlock').unbind();
    selectModel();
}
toLoadProvince('', '#provinceCode', '.provinceUl', 'toUnbindEvent');
setTimeout(function () {
    toLoadProvince('', '#cpyProvinceCode', '.cpyProvinceUl', 'toUnbindEvent');
}, 200);

//新建用户账号校验
$('body').off('blur', '#userAccount').on('blur', '#userAccount', function () {
    if (testAccountNum()) {
        return true;
    }
});

function testAccountNum() {
    $('#accountNumTip').html('');
    var cpyNum = $('#cpyCompanyName').attr('data-cpynumber');
    var fileNameHtml=$('.fileName').html();
    if (!cpyNum) {
        $('#cpyNumTip').html('请先选择渠道');
        return false;
    }
    //判断文件val时候存在
    if (!fileNameHtml) {
        $('#accountNumTip').html('账号不能为空');
        return false;
    }
    return true;
}

function testLevel() {
    var levelId = $('#levelId').attr('data-value');
    if (levelId == '') {
        $('#levelTip').html('等级不能为空！');
        return false;
    } else {
        $('#levelTip').html('');
        return true;
    }
    return true;
}



//点击省加载市
$('.cpyProvinceUl').on("click", "li", function () {
    $('#cypCityCode').html('请选择');
    $('.cypCityUl').html('');
    $('#cypCityCode').attr('data-value', '');
    $('#cpyCompanyName').html('请选择');
    $('#cpyCompanyName').attr('data-value', '');
    $('.cpyCompanyUl').html('');
    $('#levelId').html("请选择");
    $('#levelId').attr('data-value', '');
    $('.levelUl').html('');
    $('#companyMethod').val('');
    $('#paymentRule').val('');
    $('#tradeType').html('').attr('data-value', '');
    var provinceCodeId = $(this).attr('data-option');
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');
    if (flag == "") {
        $('#cypCityCode').html('请选择');
        $('.cypCityUl').html('');
        $('#cypCityCode').attr('data-value', '');
        $('#cpyCompanyName').html('请选择');
        $('#cpyCompanyName').attr('data-value', '');
        $('.cpyCompanyUl').html('');
        $('#levelId').html("请选择");
        $('#levelId').attr('data-value', '');
        $('.levelUl').html('');
        $('#companyMethod').val('');
        $('#paymentRule').val('');
        $('#tradeType').html('').attr('data-value', '');
    } else {
        toLoadCity(provinceCodeId, '', '#cypCityCode', '.cypCityUl', 'toUnbindEvent');
    }
});

$('.cypCityUl').on("click", "li", function () {
    $('#cpyCompanyName').html('请选择');
    $('.cpyCompanyUl').html('');
    $('#cpyCompanyName').attr('data-value', '');
    $('#levelId').html("请选择");
    $('.levelUl').html('');
    $('#levelId').attr('data-value', '');
    $('#companyMethod').val('');
    $('#paymentRule').val('');
    $('#tradeType').html('').attr('data-value', '');
    //$('#saveBtn').removeAttr('onclick').addClass('saveBtn').removeClass('saveBtnStyle');
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');
    var cypCityCodeValue = $('#cypCityCode').attr('data-value');
    var cpyProvinceCodeValue = $('#cpyProvinceCode').attr('data-value');

    if (flag == "") {
        $('#cpyCompanyName').html('请选择');
        $('.cpyCompanyUl').html('');
        $('#cpyCompanyName').attr('data-value', '');
        $('#levelId').html("请选择");
        $('.levelUl').html('');
        $('#levelId').attr('data-value', '');
        $('#companyMethod').val('');
        $('#paymentRule').val('');
        $('#tradeType').html('').attr('data-value', '');
    } else {
        toLoadComponyName(cypCityCodeValue, cpyProvinceCodeValue);
    }
});

function toLoadComponyName(cypCityCodeValue, cpyProvinceCodeValue) {
    var cypCompanyLi = "";
    var cpyObject = {
        provinceCode: cpyProvinceCodeValue,
        cityCode: cypCityCodeValue
    };
    if (JSON.stringify(cpyObject) == "{}") {
        $('#cpyCompanyName').html("请选择");
    } else {
        toAjaxCompany(cpyObject);
    }
}

function toAjaxCompany(cpyObject) {
    $.ajax({
        type: "post",
        url: basePath + getCompanyListUrl,
        async: true,
        data: cpyObject,
        success: function (data) {
            if (data.success == true) {
                var dataModule = data.dataObject;
                var cypCompanyLi = '<li data-option="">请选择</li>';
                for (var i = 0; i < dataModule.length; i++) {
                    cypCompanyLi += '<li data-option="' + dataModule[i].cpyId + '" data-cpyNumber="' + dataModule[i].cpyNumber + '" data-tradeType="' + dataModule[i].tradeType + '">' + dataModule[i].cpyName + '</li>';
                }
                $('.cpyCompanyUl').html(cypCompanyLi);
                toUnbindEvent();
            }
        }
    });
}

$('.cpyCompanyUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option')).attr('data-cpyNumber', $(this).attr('data-cpyNumber'));
    var cpyId = $(this).attr('data-option');
    var tradeType = $(this).attr('data-tradeType');//拿到当前公司的资金账户类型
    window.localStorage.setItem('tradeType', tradeType);
    //为了区别是爱充还是其他渠道公司的一个标识
    var cpyNumber = $(this).attr('data-cpyNumber');
    window.localStorage.setItem('cpyCompanyName', cpyNumber);
    if (cpyId == "") {
        $('#levelId').html("请选择");
        $('.levelUl').html('');
        $('#levelId').attr('data-value', '');
        $('#companyMethod').val('');
        $('#paymentRule').val('');
        $('#tradeType').html('').attr('data-value', '');
        $('#saveBtn').removeAttr('onclick').addClass('saveBtn').removeClass('saveBtnStyle');
    } else {
        toLoadCompnayFinMethod(cpyId);
        $('#cpyNumTip').html('');
        $('#levelId').html("请选择");
        $('#levelId').attr('data-value', '');
        toLoadLevelName(cpyId);
    }
});
//	去加载等级
function toLoadLevelName(cpyId) {
    $.ajax({
        type: "post",
        url: basePath + getLevelList,
        async: true,
        data: {
            cpyId: cpyId
        },
        success: function (data) {
            if (data.success == true) {
                var data = data.dataObject;
                var levelLi = '<li data-option="">请选择</li>';
                for (var i = 0; i < data.length; i++) {
                    levelLi += '<li data-option="' + data[i].levelId + '">' + data[i].levelName + '</li>';
                }
                $('.levelUl').html(levelLi);
                toUnbindEvent();
            }
        }
    });
}
$('.levelUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
})
//加载资金账户类型
function toLoadTradType() {
    $('#tradeType').html('储值账户').attr('data-value', 2);
    var html = '<li data-option="2" class="seleced">储值账户</li><li data-option="1">信用账户</li>';
    $('.tradeTypeUl').html(html);
}
//去加载公司付费策略
function toLoadCompnayFinMethod(cpyId) {
    $.ajax({
        type: "post",
        url: basePath + getFinAccountConfigRela4CpyUrl,
        async: true,
        data: {
            cpyId: cpyId
        },
        success: function (data) {
            var data = data.dataObject;
            if (data.length == 0) {
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:center"],
                    shadeClose: false,
                    closeBtn: 2,
                    area: ['310px', '160px'],//宽高
                    content: '无对应的公司付费策略',
                    time: 2000,
                    btn: ["确定"]
                });
                $('#companyMethod').val('');
                $('#paymentRule').val('');
                $('#tradeType').html('').attr('data-value', '');
                return;
            } else {
                for (var i = 0; i < data.length; i++) {
                    var paymentRuleName = data[0].paymentRuleName;
                    $('#companyMethod').val(paymentRuleName);
                    var tradeType = window.localStorage.getItem('tradeType');
                    var paymentRule = data[0].paymentRule;
                    $('#paymentRule').val(paymentRule);
                    if (tradeType == 0) {
                        $('#tradeType').html('').attr('data-value', '');
                        $('.tradeTypeUl').html('');
                        return;
                    }
                    else {
                        if (paymentRule == 1 || paymentRule == 3) {
                            $('#tradeType').attr('data-value', tradeType);
                            if (tradeType == 1) {
                                $('#tradeType').html('信用账户').attr('data-value', tradeType);
                                $('.tradeTypeUl').html('');
                            } else if (tradeType == 2) {
                                $('#tradeType').html('储值账户').attr('data-value', tradeType);
                                $('.tradeTypeUl').html('');
                            }
                        } else if (paymentRule == 2) {
                            toLoadTradType();
                        } else if (paymentRule == 0) {
                            layer.open({
                                type: 1,
                                offset: '100px',
                                title: ["提示", "font-size:12px;text-align:center"],
                                shadeClose: false,
                                closeBtn: 2,
                                area: ['310px', '160px'],//宽高
                                content: '该公司无法创建用户',
                                time: 2000,
                                btn: ["确定"]
                            });
                        }
                    }

                }
            }

        }
    });
}
//用户状态
$('.userStatusUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
})
//资金账户类型
$('.tradeTypeUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
})



//刚一进页面就要执行的所有验证
$('#goback').on('click', function () {
    window.location.href = 'user_list.html';
});

//将数据提交到后台处理
function toSaveUserInfo() {
    if (testAccountNum() && testLevel()) {
        lastSaveInfo();
    }
}
function lastSaveInfo(){
    var formData = new FormData();
    var fileName = $('#file').val();
    var cpyCompanyName = $('#cpyCompanyName').attr('data-value');
    var levelId = $('#levelId').attr('data-value');
    var userStatus = $('#userStatus').attr('data-value');
    var tradeType = $('#tradeType').attr('data-value');
    var cpyNumber = window.localStorage.getItem('cpyCompanyName');

    formData.append('cpyId',cpyCompanyName);
    formData.append('levelId',levelId);
    formData.append('userStatus',userStatus);
    formData.append('tradeType',tradeType);
    formData.append('cpyNumber',cpyNumber);
    formData.append("file", $('#file')[0].files[0]);
    commonLoad(formData);
}

function commonLoad(formData){
    var index =layer.load(1);
    $.ajax({
        url: basePath + addUserCompanyBatchUrl,
        type: 'POST',
        data: formData,
        async: true,
        cache: false,
        contentType: false,
        processData: false,
        success: function (returndata) {
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
                        window.location.href='user_list.html';
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        window.location.href='user_list.html';
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
                    time:2000,
                    btn: ["确定"]
                });
            }
        }
    });
}
$('#file').change(function(){
    $('.fileName').html($(this).val());
})
//下载模版
$('body').off('click','#downloadXlsx').on('click','#downloadXlsx',function(){
    window.location.href = basePath + '/upload/batchAddUser_demo.xlsx';
})
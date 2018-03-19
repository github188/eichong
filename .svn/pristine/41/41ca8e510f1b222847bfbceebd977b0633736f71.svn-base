var cpyId = getUrlParam('cpyId');
var cpyNumber = getUrlParam('cpyNumber');
var cpyName = getUrlParam('cpyName');
var accountId = getUrlParam('accountId');
var savingAccountId = getUrlParam('savingAccountId');
//数据权限控制
//获取公司主页的menuId
var getMenuMap = window.localStorage.getItem('menuMap');
var getCurruntMap = JSON.parse(getMenuMap);
var menuId = getCurruntMap['公司主页'];
var index = layer.load(1);
$(function () {
    ctrlMenu(menuId);
    //加载公司的基本信息
    setTimeout('getCompanyBasicInfo()', 100);
    //公司用户板块的内容    ////////////////////////////////////////////////
    setTimeout('getCompanyUserInfo()', 150);
    //大账户板块内容
    setTimeout('getFinAccountBalance4CpyInfo()', 300);
    //黑白名单板块内容 （...）
    setTimeout('getUserBlackWhite4CpyInfo()', 400);
    setTimeout('getFinAccountConfig4CpyInfo()', 650);
    setTimeout('companyIndexChargeRelaInfo()', 500);
    //管辖的公司板块内容
    setTimeout('getCompanyByMasterInfo()', 700);
    //充电卡内容.
    setTimeout('getCardForCompanyInfo()', 800);
    //订单信息板块内容
    setTimeout('getHomeCpyOrderInfo()', 350);
    //等级板块内容////
    setTimeout('getLevelByCpyIdInfo()', 250);
    setTimeout('getRateUniqueRelaGroupInfo()', 450);
    //管理员账户板块内容
    setTimeout('getUserAdminByCpyIdInfo()', 550);
    //收款账户
    setTimeout('getReceiptAccount()', 900);
})
function ctrlMenu(menuId) {
    $.ajax({
        type: "post",
        url: basePath + getSelfButtonTreeUrl,
        async: true,
        data: {
            menuId: menuId
        },
        success: function (data) {
            if (data.success == true) {
                var data = data.dataObject;
                if (data == null) {
                    return;
                }
                if (data.length == 0) {
                    return;
                } else {
                    for (var i = 0; i < data.length; i++) {
                        var contents = data[i].contents;
                        if (contents.indexOf('概览管理') > -1) {
                            $('#companyInfoId').show();
                        }
                        if (contents.indexOf('用户管理') > -1) {
                            $('#companyUserId').show();
                        }
                        if (contents.indexOf('充电卡管理') > -1) {
                            $('#getCardForCompanyId').show();
                        }
                        if (contents.indexOf('大账户管理') > -1) {
                            $('#bigAccountId').show();
                        }
                        if (contents.indexOf('订单信息管理') > -1) {
                            $('#getHomeCpyOrderId').show();
                        }
                        if (contents.indexOf('黑白名单管理') > -1) {
                            $('#blackAndWhiteListId').show();
                        }
                        if (contents.indexOf('等级管理') > -1) {
                            $('#getLevelByCpyId').show();
                        }
                        if (contents.indexOf('充电范围管理') > -1) {
                            $('#companyChargeRelaId').show();
                        }
                        if (contents.indexOf('充电费率管理') > -1) {
                            $('#chargeRatesId').show();
                        }
                        if (contents.indexOf('管辖公司管理') > -1) {
                            $('#companyByMasterId').show();
                        }
                        if (contents.indexOf('管理员账号管理') > -1) {
                            $('#getUserAdminByCpyId').show();
                        }
                        if (contents.indexOf('账务关系管理') > -1) {
                            $('#companyFinanceId').show();
                        }
                        if (contents.indexOf('盗刷校验管理') > -1) {
                            $('#isValidTextId').show();
                        }
                        if (contents.indexOf('付费策略管理') > -1) {
                            $('#payForStrategy').show();
                        }
                        if (contents.indexOf('第三方API对接密钥') > -1) {
                            $('#api').show();
                        }
                        if (contents.indexOf('收款账户') > -1) {
                            $('#receiptAccount').show();
                        }
                    }
                }
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
                layer.closeAll();
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '160px'],//宽高
                    content: '系统出错',
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
            }
        }
    });
}


function getCompanyBasicInfo() {
    $.ajax({
        type: "post",
        url: basePath + companyHomeUrl,
        async: true,
        data: {
            cpyId: cpyId
        },
        success: function (req) {
            var data = req.dataObject;
            var cpyName = data.cpyName;//公司名称
            var cpyNumber = data.cpyNumber;//公司标识
            var cpyCity = data.cpyCity;//地区
            var cpyStatusText = data.cpyStatusText;//禁用状态
            var cpyParentName = data.cpyParentName;//上级公司
            var slaveCpyName = data.slaveCpyName;//管理单位
            var cpyStatus = data.cpyStatus;//公司状态
            var isValidText = data.isValidText;//是否开启盗刷校验
            var isValidValue = '';
            if (isValidText == '是') {
                $('#openIsValid').css('display', 'block');
                $('#closeIsValid').css('display', 'none');
                isValidValue = 1;
            } else if (isValidText == '否') {
                $('#openIsValid').css('display', 'none');
                $('#closeIsValid').css('display', 'block');
                isValidValue = 0;
            }
            $('#companyNameInfo').html(cpyName);
            $('#companyStatusInfo').html(cpyStatusText);
            $('#cpyCity').html(cpyCity);
            $('#cpyNumber').html(cpyNumber);
            $('#cpyParentIdInfo').html(cpyParentName);
            $('#slaveParentIdInfo').html(slaveCpyName);
            if (cpyStatus == 1) {
                $('#disableCompanyBtn').css('display', 'inline-block');
            } else if (cpyStatus == 0) {
                $('#disableCompanyBtn').css('display', 'none');
            }
            var setChargeInfo = window.localStorage;
            setChargeInfo.setItem("remCompanyName", cpyName);
            setChargeInfo.setItem("isValidValue", isValidValue);
        }
    });
}
//点击修改盗刷校验状态
$("body").off("click", "#isValidCloseBtn").on("click", "#isValidCloseBtn", function () {
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["关闭盗刷校验", "font-size:12px;text-align:center;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '180px'],//宽高
        content: '关闭盗刷校验之后，VIN码将不会限制是否可以充电',
        btn: ["确定", "取消"],
        yes: function (index, layero) {
            layer.closeAll();
            toCloseIsValid();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
})
function toCloseIsValid() {
    var isValid = 0;
    $.ajax({
        type: "post",
        url: basePath + modifyCompanyUrl,
        async: true,
        data: {
            isValid: isValid,
            cpyId: cpyId
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
                    content: '修改成功',
                    btn: ["确定"],
                    yes: function (index, layero) {
                        layer.closeAll();
                        getCompanyBasicInfo()
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        getCompanyBasicInfo()
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
$("body").off("click", "#isValidOpenBtn").on("click", "#isValidOpenBtn", function () {
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["开启盗刷校验", "font-size:12px;text-align:center;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '180px'],//宽高
        content: '开启盗刷校验后，用户车辆的VIN不再VIN码列表中，车辆不能充电',
        btn: ["确定", "取消"],
        yes: function (index, layero) {
            layer.closeAll();
            toOpenIsValid();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
})
function toOpenIsValid() {
    var isValid = 1;
    $.ajax({
        type: "post",
        url: basePath + modifyCompanyUrl,
        async: true,
        data: {
            isValid: isValid,
            cpyId: cpyId
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
                    content: '修改成功',
                    btn: ["确定"],
                    yes: function (index, layero) {
                        layer.closeAll();
                        getCompanyBasicInfo()
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        getCompanyBasicInfo()
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
//编辑、更改公司的名称
$("body").off("click", "#companyEdit").on("click", "#companyEdit", function () {
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["确认", "font-size:12px;text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '180px'],//宽高
        content: $('.changeCompanyNewName'),
        btn: ["确定", "取消"],
        yes: function (index, layero) {
            layer.closeAll();
            toSubmitCompanyNewName();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
});
function toSubmitCompanyNewName() {
    var cpyNewName = $('#cpyNewNameInput').val();
    var setChargeInfo = window.localStorage;
    var isValid = setChargeInfo.getItem("isValidValue");
    $.ajax({
        type: "post",
        url: basePath + modifyCompanyUrl,
        async: true,
        data: {
            cpyName: cpyNewName,
            cpyId: cpyId,
            isValid: isValid
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
                    content: '修改成功',
                    btn: ["确定"],
                    yes: function (index, layero) {
                        layer.closeAll();
                        getCompanyBasicInfo()
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        getCompanyBasicInfo()
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
//解禁
$("body").off("click", "#disableCompanyBtn").on("click", "#disableCompanyBtn", function () {
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["确认", "font-size:12px;text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '180px'],//宽高
        content: $('.abledCompany'),
        btn: ["确定", "取消"],
        yes: function (index, layero) {
            layer.closeAll();
            toAbledCompany();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
});
function toAbledCompany() {
    $.ajax({
        type: "post",
        url: basePath + disableCompanyUrl,
        async: true,
        data: {
            type: 0,
            cpyId: cpyId
        },
        success: function (data) {
            if (data.success == true) {
                getCompanyBasicInfo();
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

function getCompanyUserInfo() {
    $.ajax({
        type: "post",
        url: basePath + companyIndexUserUrl,
        async: true,
        data: {
            cpyId: cpyId
        },
        success: function (req) {
            var data = req.dataObject;
            var indexUserLi = '';
            for (var i = 1; i < data.length; i++) {
                indexUserLi += '<li><span>' + data[i].levelName + '</span><span>' + data[i].count + '</span></li>';
            }
            $('#indexUserUl').html(indexUserLi);
        }
    });
    newTab(".toUserBtn", "用户列表");
    var aTag = '<a class="toUserBtn" onclick="return false;"  href="' + basePath + '/static/html/userList/user_list.html?cpyId=' + cpyId + '&cpyName=' + cpyName + '"><li class="baseInfoBtn">查看</li></a>';
    $('#userListBtn').html(aTag);
}

function getFinAccountBalance4CpyInfo() {
    $.ajax({
        type: "post",
        url: basePath + getFinAccountBalance4CpyUrl,
        async: true,
        data: {
            accountId: accountId
        },
        success: function (req) {
            var data = req.dataObject;
            var accountStatusName = data.accountStatus;
            var a = '';
            if (accountStatusName == 1) {
                a = '正常';
            } else if (accountStatusName == 2) {
                a = '冻结';
            } else if (accountStatusName == 3) {
                a = '删除';
            }
            var tradeTypeName = data.tradeType;
            var b = '';
            if (tradeTypeName == 1) {
                b = '信用';
            } else if (tradeTypeName == 2) {
                b = '储蓄';
            }
            $('#accountStatus').html(a);
            $('#tradeType').html(b);
            if (data.tradeType == 1) {
                $('#accountBalanceLi').hide()
                $('#accountPresentLi').hide();
            } else if (data.tradeType == 2) {
                $('#accountBalanceLi').show();
                $('#accountPresentLi').show();
            }
            var accountBalance = data.accountBalance;//充值余额
            var accountPresent = data.accountPresent;//赠送余额
            var accountWarn = data.accountWarn;//额度
            var accountMoney = parseFloat(accountBalance) + parseFloat(accountPresent);
            //var accountStatusName = data.accountStatusName;//状态：1，正常，2.冻结，3.删除
            //var tradeTypeName = data.tradeTypeName;//类型： 1.信用，2.储蓄
            $('#accountMoney').html(accountMoney);
            $('#accountBalance').html(accountBalance);
            $('#accountPresent').html(accountPresent);
            $('#accountWarn').html(accountWarn);
            $('#totalAccount').html(accountBalance);

        }
    });
    //资金账户
    newTab(".toScanBtn", "资金账户列表");
    var aTag = '<a class="toScanBtn" onclick="return false;"  href="' + basePath + '/static/html/finAccount/finAccount_list.html?accountId=' + accountId + '"><li class="baseInfoBtn">查看</li></a>';
    $('#finAccountSkanBtn').prepend(aTag);
}
//点击预警金额，设置预警金额
$('#relieveCardFrozen').on("click", function () {
    layer.closeAll();
    layer.open({
        type: 1,
        title: '大账户预警金额',
        btn: ["确定", "取消"],
        shadeClose: true,
        closeBtn: 1,//点击遮罩关闭层
        area: ['320px', '200px'],
        content: $(".warningLayer"),
        yes: function () {
            warningTest($('#warningInput'));
            if (warningTest($('#warningInput')) == true) {
                layer.closeAll();
                setWarningAccount();
            }
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
})
function warningTest(obj) {
    var objValue = $(obj).val();
    var reg = /^[0-9][\d]*$/;
    $('#warningTip').html('');
    if (!objValue) {
        $(obj).focus();
        $('#warningTip').html('请输入数字！');
        return false;
    } else if (!reg.test(objValue)) {
        $(obj).focus();
        $('#warningTip').html('请输入数字！');
        return false;
    }
    return true;
}

function setWarningAccount() {
    var accountWarn = $('#warningInput').val();
    $.ajax({
        type: "post",
        url: basePath + modifyFinAccountWarnUrl,
        async: true,
        data: {
            accountId: accountId,
            accountWarn: accountWarn
        },
        success: function (req) {
            if (req.success == true) {
                layer.closeAll();
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '180px'],//宽高
                    content: "保存成功",
                    time: 2000,
                    btn: ["确定"]
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

function getUserBlackWhite4CpyInfo() {
    $.ajax({
        type: "post",
        url: basePath + getUserBlackWhite4CpyUrl,
        async: true,
        data: {
            cpyId: cpyId
        },
        success: function (req) {
            var data = req.dataObject;
            var blackWhiteUserLi = '';
            if (data.length == 0) {
                blackWhiteUserLi = '<li><span>白名单记录</span><span>0条</span></li><li><span>黑名单记录</span><span>0条</span></li>';
            }
            if (data.length > 0) {
                for (var i = 0; i < data.length; i++) {
                    if (data[i].type == 0) {
                        blackWhiteUserLi += '<li><span>白名单记录</span><span><t>' + data[i].counts + '</t>条</span></li>';
                    }
                    if (data[i].type == 1) {
                        blackWhiteUserLi += '<li><span>黑名单记录</span><span><t>' + data[i].counts + '</t>条</span></li>';
                    }
                }
            }
            $('#blackWhiteUserUl').html(blackWhiteUserLi);
        }
    });
    //黑白名单的设置
    var setBlackWhite = '<a class="heibai" onclick="return false" href="' + basePath + '/static/html/blackWhiteList/blackWhite_list.html?cpyId=' + cpyId + '&cpyName=' + cpyName + '"><li class="baseInfoBtn">设置</li></a>';
    $('#heibai').html(setBlackWhite);
    newTab(".heibai", '黑白名单列表');
}
/*$('#setBlackWhite').on('click',function(){
 var cpyName = $('#companyNameInfo').html();
 window.location.href= "../blackWhiteList/blackWhite_list.html?cpyId="+ cpyId+"&cpyName="+cpyName;
 })*/
//充电范围板块内容
$('#getStationAndPile').on('click', function () {
    window.location.href = "company_setChargeRela.html?cpyId=" + cpyId;
});

function companyIndexChargeRelaInfo() {
    $.ajax({
        type: "post",
        url: basePath + companyIndexChargeRelaUrl,
        async: true,
        data: {
            cpyId: cpyId
        },
        success: function (req) {
            var data = req.dataObject;
            var site = data[0];//电站数
            var stake = data[1];//电桩数
            $('#site').html(site);
            $('#stake').html(stake);
            var setChargeInfo = window.localStorage;
            setChargeInfo.setItem("remSiteNumber", site);
            setChargeInfo.setItem("remPileNumber", stake);

        }
    });
}

function getCompanyByMasterInfo() {
    $.ajax({
        type: "post",
        url: basePath + getCompanyByMasterUrl,
        async: true,
        data: {
            cpyId: cpyId
        },
        success: function (req) {
            var data = req.dataObject;
            //var cpyName = data.cpyName;//公司名称
            var cpyNameLi = '';
            for (var i = 0; i < data.length; i++) {
                cpyNameLi += '<li>' + data[i].cpyName + '</li>';
            }
            $('#cpyNameUl').html(cpyNameLi);
        }
    });
    //查看和新建
    var serchCompany = '<a class="serchMaster" onclick="return false" href="' + basePath + '/static/html/companyList/company_list.html?cpyId=' + cpyId + '&cpyName=' + cpyName + '"><li class="baseInfoBtn">查看</li></a>'
        + '<div class="marginLeft"></div>'
        + '<a class="setNewMaster" onclick="return false" href="' + basePath + '/static/html/companyList/add_company.html?cpyId=' + cpyId + '&cpyName=' + cpyName + '"><li class="baseInfoBtn">新建</li></a>';
    $('#cpyMaster').html(serchCompany);
    newTab(".serchMaster", '公司列表');
    newTab(".setNewMaster", '新增公司');
}
//账务关系
newTab(".toScanFinRelaBtn", "账务关系列表");
var finRelaScanTag = '<a class="toScanFinRelaBtn" onclick="return false;"  href="' + basePath + '/static/html/finAccountRelation/finAccountRelation_list.html?accountId=' + accountId + '"><li class="baseInfoBtn">查看</li></a>';
$('#finRelaSkanBtn').html(finRelaScanTag);

function getCardForCompanyInfo() {
    $.ajax({
        type: "post",
        url: basePath + getCardForCompanyUrl,
        async: true,
        data: {
            ucCpyId: cpyId
        },
        success: function (req) {
            var data = req.dataObject;
            var lossCardNum = data.lossCardNum;//已挂失卡
            var totalCardNum = data.totalCardNum;//卡数量
            $('#lossCardNum').html(lossCardNum);
            $('#totalCardNum').html(totalCardNum);
        }
    });
    newTab(".toCardList", "卡列表");
    var cardTag = '<a class="toCardList" onclick="return false;"  href="' + basePath + '/static/html/cardList/card_list.html?cpyId=' + cpyId + '&cpyName=' + cpyName + '"><li class="baseInfoBtn">查看</li></a>';
    $('#toCardBtn').html(cardTag);
}

//第三方api密钥
setTimeout('getKeytNum()', 900);
function getKeytNum() {
    $.ajax({
        type: "post",
        url: basePath + getPartnerListUrl,
        async: true,
        data: {
            partnerKey: cpyNumber
        },
        success: function (req) {
            var data = req.dataObject;
            var partnerId = '';
            var partnerToken = '';
            for (var i = 0; i < data.length; i++) {
                partnerId = data[i].partnerId;
                partnerToken = data[i].partnerToken;
            }
            $('#keytNum').html(partnerToken);
            var setPartnerId = window.localStorage;
            setPartnerId.setItem("partnerId", partnerId);

            var keytNumHtml = $('#keytNum').html();
            if (!keytNumHtml) {
                $('.setBtn').css('display', 'block');
                $('.deleteBtn').css('display', 'none');
            } else {
                $('.setBtn').css('display', 'none');
                $('.deleteBtn').css('display', 'block');
            }
        }
    });
}
$("body").off("click", ".setBtn").on("click", ".setBtn", function () {
    setTimeout('toAddPartner()', 200);
    $('.setBtn').css('display', 'none');
    $('.deleteBtn').css('display', 'block');
});
function toAddPartner() {
    var setPaymentRule = window.localStorage;
    var paymentRuleValue = setPaymentRule.getItem("paymentRule");
    $.ajax({
        type: "post",
        url: basePath + addPartnerUrl,
        async: true,
        data: {
            partnerName: cpyName,
            partnerKey: cpyNumber,
            paymod: paymentRuleValue
        },
        success: function (req) {
            getKeytNum();
        }
    });
}
$("body").off("click", ".deleteBtn").on("click", ".deleteBtn", function () {
    setTimeout('toDeletePartner()', 200);
    $('.setBtn').css('display', 'block');
    $('.deleteBtn').css('display', 'none');
});
function toDeletePartner() {
    var setPartnerId = window.localStorage;
    var partnerIdValue = setPartnerId.getItem("partnerId");
    $.ajax({
        type: "post",
        url: basePath + removePartnerUrl,
        async: true,
        data: {
            partnerId: partnerIdValue,
            partnerKey: cpyNumber
        },
        success: function (req) {
            getKeytNum();
        }
    });
}

function getHomeCpyOrderInfo() {
    $.ajax({
        type: "post",
        url: basePath + getHomeCpyOrderUrl,
        async: true,
        data: {
            cpyNumber: cpyNumber
        },
        success: function (req) {
            var data = req.dataObject;
            for (var key in data) {
                if (key == 1) {
                    $('#cumulativeOrder').html(data[key]);
                }
                if (key == 2) {
                    $('#cumulative').html(data[key]);
                }
                if (key == 3) {
                    $('#cumulativePower').html(data[key]);
                }
                if (key == 4) {
                    $('#todayOrder').html(data[key]);
                }
            }
        }
    });
    newTab(".toScanOrderBtn", "订单列表");
    var orderScanTag = '<a class="toScanOrderBtn" onclick="return false;"  href="' + basePath + '/static/html/order/order_list.html?cpyNumber=' + cpyNumber + '&cpyName=' + cpyName + '"><li class="baseInfoBtn">查看</li></a>';
    $('#orderSkanBtn').html(orderScanTag);
}

function getLevelByCpyIdInfo() {
    $.ajax({
        type: "post",
        url: basePath + getLevelByCpyIdUrl,
        async: true,
        data: {
            cpyId: cpyId
        },
        success: function (req) {
            var data = req.dataObject;
            var levelNameLi = '';
            for (var i = 0; i < data.length; i++) {
                levelNameLi += '<li>' + data[i].levelName + '</li>';
            }
            $('#levelNameId').html(levelNameLi);
        }
    });
    newTab(".toLevelList", "等级列表");
    var newLevelTag = '<a class="toLevelList" onclick="return false;"  href="' + basePath + '/static/html/level/level.html?cpyId=' + cpyId + '&cpyName=' + cpyName + '"><li class="baseInfoBtn">设置</li></a>';
    $('#setLevel').html(newLevelTag);
}
//充电费率板块内容
$('#setChargeRate').on('click', function () {
    window.location.href = "company_setChargeRate.html?cpyId=" + cpyId;
});

function getRateUniqueRelaGroupInfo() {
    $.ajax({
        type: "post",
        url: basePath + getRateUniqueRelaGroupUrl,
        async: true,
        data: {
            cpyId: cpyId
        },
        success: function (req) {
            var data = req.dataObject;
            var levelLi = '';
            for (var i = 0; i < data.length; i++) {
                levelLi += '<li><span>' + data[i].levelName + '</span><span>' + data[i].rateinfoId + '</span></li>';
            }
            $('#leaveUl').html(levelLi);
        }
    });
}

function getUserAdminByCpyIdInfo() {
    $.ajax({
        type: "post",
        url: basePath + getUserAdminByCpyIdUrl,
        async: true,
        data: {
            cpyId: cpyId
        },
        success: function (req) {
            var data = req.dataObject;
            var administerLi = '';
            for (var i = 0; i < data.length; i++) {
                //administerLi += '<li><p><t>管理员账号：</t><span>' + data[i].adminName + '</span></p></li>';
                administerLi += '<li><p>管理员账号：</p><p><span>' + data[i].adminName + '</span></p></li>';
            }
            $('#administerUl').html(administerLi);
        }
    });
    newTab(".toNewAdmin", "管理员列表");
    var newAdminTag = '<a class="toNewAdmin" onclick="return false;"  href="' + basePath + '/static/html/admin/add_admin.html?cpyId=' + cpyId + '"><li class="baseInfoBtn">新建</li></a>';
    $('#toNewAdminBtn').html(newAdminTag);
}
//盗刷校验内容
setTimeout('toSetVIN()', 900);
function toSetVIN() {
    newTab(".toVinHtml", "VIN码列表");
    var newVinTag = '<a class="toVinHtml" onclick="return false;"  href="' + basePath + '/static/html/carVinList/carVin_list.html?cpyId=' + cpyId + '&cpyName=' + cpyName + '"><li class="baseInfoBtn">设置</li></a>';
    $('#toVinId').html(newVinTag);
    layer.closeAll();
}
//付费策略

function getFinAccountConfig4CpyInfo() {
    $.ajax({
        type: "post",
        url: basePath + getFinAccountConfigRela4CpyUrl,
        async: true,
        data: {
            cpyId: cpyId
        },
        success: function (req) {
            var data = req.dataObject;
            var paymentRuleNameLi = '';
            for (var i = 0; i < data.length; i++) {
                var paymentRule = data[i].paymentRule;
                var setPaymentRule = window.localStorage;
                setPaymentRule.setItem("paymentRule", paymentRule);
                paymentRuleNameLi += '<li><span>' + data[i].billAccountName + '</span><span>' + data[i].paymentRuleName + '</span></li>';
                if (data[i].paymentRuleName == "大账户为个人配资") {
                    $('#toAssign').css("display", "inline-block");
                    $('#allAssign').css("display", "inline-block");
                }
            }
            $('#paymentRuleNameUl').html(paymentRuleNameLi);
        }
    });
}
//点击指定账户等额配资
$("body").off("click", "#allAssign").on("click", "#allAssign", function () {
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: '指定账户等额配资',
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '338px'],//宽高
        content: $(".toImportFile"),
        btn: ["确定", "取消"],
        yes: function () {
            importTest();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
})
function importTest() {
    var fileText = $('#fileText').html();
    var allocationAmount = $('#allocationAmount').val();
    var reg = /^\d+(\.\d{1,2})?$/;//正整数
    if (!allocationAmount) {
        $('.accountMoneyTip').html('请输入每个账户的分配资金！');
        $('#allocationAmount').focus();
        return false;
    }
    if (allocationAmount && !reg.test(allocationAmount)) {
        $('.accountMoneyTip').html('分配资金不能为负数,小数点后最多两位');
        $('#allocationAmount').focus();
        return false;
    } else {
        $('.accountMoneyTip').hide().html('');
        if (fileText == '请选择文件') {
            $('.fileInputValue').html('请选择上传的文件！').css('color', '#ff0000');
            return false;
        } else {
            $('.fileInputValue').css('color', '#666666');
            confirmEqualAllocation(allocationAmount);
        }
    }

}
//点击确定提交吗？
function confirmEqualAllocation(allocationAmount) {
    var allocationAmount = allocationAmount;
    var formData = new FormData();
    formData.append('cpyId', cpyId);
    formData.append('allocationAmount', allocationAmount);
    formData.append("file", $('#file')[0].files[0]);
    $.ajax({
        url: basePath + confirmEqualAllocationUrl,
        type: 'POST',
        data: formData,
        async: true,
        cache: false,
        contentType: false,
        processData: false,
        success: function (returndata) {
            if (returndata.success == true) {
                var data = returndata.dataObject;
                var userAccountCount = data.userAccountCount;
                var allocationAmountSure = data.allocationAmount;
                var accountNO = data.accountNO;
                layer.closeAll();
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '170px'],//宽高
                    content: '资金帐户' + accountNO + '将划出金额(或额度)' + allocationAmountSure + '元，' + userAccountCount + '个资金账户将划入金额(或额度)共' + allocationAmountSure + '元',
                    btn: ["确定","取消"],
                    yes: function (index, layero) {
                        layer.closeAll();
                        //最终提交文件
                        submitFile(allocationAmount);
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
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
                    content: returndata.msg,
                    btn: ["确定"],
                    time: 3000
                });
            }

        }
    });
}
function submitFile(allocationAmount) {
    var allocationAmount = allocationAmount;
    var formData = new FormData();
    formData.append('cpyId', cpyId);
    formData.append('allocationAmount', allocationAmount);
    formData.append("file", $('#file')[0].files[0]);

    $.ajax({
        url: basePath + equalAllocationUrl,
        type: 'POST',
        data: formData,
        async: true,
        cache: false,
        contentType: false,
        processData: false,
        success: function (returndata) {
            if (returndata.success == true) {
                layer.closeAll();
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
                layer.closeAll();
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '160px'],//宽高
                    content: returndata.msg,
                    btn: ["确定"],
                    time: 3000
                });
            }

        }
    });
}
$('#file').change(function () {
    var fileName = $('#file').val();
    $('#fileText').html(fileName).css('color', '#666666');
})
//下载模版
$('body').off('click', '#downloadXlsx').on('click', '#downloadXlsx', function () {
    window.location.href = basePath + '/upload/allocationAmount_demo.xlsx';
})
//点击账户单独分配时
$("body").off("click", "#toAssign").on("click", "#toAssign", function () {
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: '单独为小账户配资',
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '290px'],//宽高
        content: $(".toAssign"),
        btn: ["确定", "取消"],
        yes: function () {
            sAccountFundsTest($('#sAccountFunds'));
            sAccountQuotaTest($('#sAccountQuota'));
            if (sAccountFundsTest($('#sAccountFunds')) == true && sAccountQuotaTest($('#sAccountQuota')) == true) {
                layer.closeAll();
                toAssign();
            }
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
})
function toAssign() {
    var accountNO = $('#sAccountFunds').val();
    var allocationAmount = $('#sAccountQuota').val();
    $.ajax({
        type: "post",
        url: basePath + separateAllocationUrl,
        async: true,
        data: {
            cpyId: cpyId,
            allocationAmount: allocationAmount,
            accountNO: accountNO
        },
        success: function (req) {
            if (req.success == true) {
                layer.closeAll();
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 2,
                    area: ['320px', '180px'],//宽高
                    content: "设置成功",
                    time: 2000,
                    btn: ["确定"]
                });
            } else {
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 2,
                    area: ['310px', '160px'],//宽高
                    content: req.msg,
                    time: 3000,
                    btn: ["确定"]
                });
            }
        }
    });
}
function sAccountFundsTest(obj) {
    var objValue = $(obj).val();
    var reg = /^(FA)+[0-9][\d]*$/;
    $('#sAccountFundsTip').html('');
    if (!objValue) {
        $(obj).focus();
        $('#sAccountFundsTip').html('请输入小账户资金账号！');
        return false;
    } else if (!reg.test(objValue)) {
        $(obj).focus();
        $('#sAccountFundsTip').html('输入有误，请重新输入小账户资金账号！');
        return false;
    }
    return true;
}
function sAccountQuotaTest(obj) {
    var objValue = $(obj).val();
    var reg = /^[0-9][\d]*$/;
    $('#sAccountQuotaTip').html('');
    if (!objValue) {
        $(obj).focus();
        $('#sAccountQuotaTip').html('请输入数字！');
        return false;
    } else if (!reg.test(objValue)) {
        $(obj).focus();
        $('#sAccountQuotaTip').html('请输入数字！');
        return false;
    }
    return true;
}
function getReceiptAccount() {
    $.ajax({
        type: "post",
        url: basePath + getFinAccountBalance4CpyUrl,
        async: true,
        data: {
            accountId: savingAccountId
        },
        success: function (req) {
            var data = req.dataObject;
            var accountStatusName = data.accountStatus;
            var a = '';
            if (accountStatusName == 1) {
                a = '正常';
            } else if (accountStatusName == 2) {
                a = '冻结';
            } else if (accountStatusName == 3) {
                a = '删除';
            }
            var tradeTypeName = data.tradeType;
            var b = '';
            if (tradeTypeName == 1) {
                b = '信用';
            } else if (tradeTypeName == 2) {
                b = '储蓄';
            }
            $('#accountStatusName').html(a);
            $('#tradeTypeName').html(b);
            $('#savingAccount').html(data.accountBalance);

        }
    });
}
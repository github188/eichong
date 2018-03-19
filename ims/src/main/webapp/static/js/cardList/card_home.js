var ucId = getUrlParam('ucId');
var ucUserId = getUrlParam('ucUserId');
var ucCpyId = getUrlParam('ucCpyId');

//加载用户基本信息展示  目前返回的是空
//获取用户主页的menuId
var getMenuMap = window.localStorage.getItem('menuMap');
var getCurruntMap = JSON.parse(getMenuMap);
var homeMenuId = getCurruntMap['卡主页'];
var index = layer.load(1);
$(function () {
    ctrlMenuBtn(homeMenuId);
    setTimeout('getCardBasicInfo()', 100);
    //加载用户信息
    setTimeout('getCardIndexUser()', 200);
    //资金账户模块开始
    setTimeout('getFinAccountUser()', 300);
    //资金账目关系
    //setTimeout('getFinAccountRelation4User()', 400);//不要了
    //用户账单
    setTimeout("getUserAccount()", 500);
    setTimeout("toGetOrderDetail()", 600);
    setTimeout("baseBtnSkan()", 700);
    setTimeout('toBlackWhiteList()', 350);
    setTimeout('getVinInfoByUser()',900);
})

//控制用户主页按钮权限
function ctrlMenuBtn(homeMenuId) {
    $.ajax({
        type: "post",
        url: basePath + getSelfButtonTreeUrl,
        async: true,
        data: {
            menuId: homeMenuId
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
                        if (contents.indexOf('概览') > -1) {
                            $('#ctrCardBaseInfo').show();
                        }
                        if (contents.indexOf('状态') > -1) {
                            $('#statusBlock').show();
                        }
                        if (contents.indexOf('卡账单') > -1) {
                            $('#cardBill').show();
                        }
                        if (contents.indexOf('用户信息') > -1) {
                            $('#userInfoManagement').show();
                        }
                        if (contents.indexOf('资金账户') > -1) {
                            $('#finAccountBlock').show();
                        }
                        if (contents.indexOf('订单信息') > -1) {
                            $('#dingdanBlock').show();
                        }
                        if (contents.indexOf('账务关系') > -1) {
                            $('#finRelationBlock').show();
                        }
                        if (contents.indexOf('安全管理') > -1) {
                            $('#safetyBlock').show();
                        }
                        if (contents.indexOf('车辆信息') > -1) {
                            $('#carInfo').show();
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

function getCardBasicInfo() {
    $.ajax({
        type: "post",
        url: basePath + getCardBasicInfoUrl,
        async: true,
        data: {
            ucId: ucId
        },
        success: function (data) {
            var data = data.dataObject;
            var ucExternalCardNumber = data.ucExternalCardNumber;
            var ucInternalCardNumber = data.ucInternalCardNumber;
            var ucStatus = data.ucStatus;
            var ucCpyId = data.ucCpyId;
            var newUserId = data.newUserId;//grayBtn
            var levelName = data.levelName;
            var accountId = data.accountId;
            window.localStorage.setItem('ucCpyId', ucCpyId);
            window.localStorage.setItem('accountId_finAccount', accountId);
            var cpyCompanyname = data.cpyCompanyname;
            //卡级别未写
            var ucStatusHTML = "";
            if (ucStatus == 0) {
                ucStatusHTML = "正常";
            }
            if (ucStatus == 1) {
                ucStatusHTML = "挂失";
            }
            if (ucStatus == 2) {
                ucStatusHTML = "冻结";
            }
            if (ucStatus == 3) {
                ucStatusHTML = "注销";
            }
            if (newUserId == 0 && ucCpyId > 0) {
                $('#setLevelBtn_card').show();
            } else {
                $('#setLevelBtn_card').hide();
                $('#bindVinCode').addClass('grayBtn').removeClass('baseInfoBtn');
            }
            $('#ucExternalCardNumber').html(ucExternalCardNumber);
            $('#ucInternalCardNumber').html(ucInternalCardNumber);
            $('#cpyCompanyname').html(cpyCompanyname);
            $('#ucStatusHTML').html(ucStatusHTML);
            $('#levelName').html(levelName);
        }
    });
}
//状态管理
//去冻结
$('body').off('click', '.cardFrozen').on('click', '.cardFrozen', function () {
    toShowComfirm(this, cardFrozenUrl);
})
//去解冻
$('body').off('click', '.relieveCardFrozen').on('click', '.relieveCardFrozen', function () {
    toShowComfirm(this, relieveCardFrozenUrl);
})
//去挂失
$('body').off('click', '.cardLoss').on('click', '.cardLoss', function () {
    toShowComfirm(this, cardLossUrl);
})
//去解挂
$('body').off('click', '.relieveCardLoss').on('click', '.relieveCardLoss', function () {
    toShowComfirm(this, relieveCardLossUrl);
})
//去注销
$('body').off('click', '.cardCancel').on('click', '.cardCancel', function () {
    toShowComfirm(this, cardCancelUrl);
})
function toShowComfirm(obj, carStatusUrl) {
    var spanText = $(obj).text();
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["卡" + spanText, "font-size:12px;text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '160px'],//宽高
        content: '确定进行卡' + spanText + '吗？',
        btn: ["确定", "取消"],
        yes: function (index, layero) {
            layer.closeAll();
            toChangeStatus(carStatusUrl, spanText);
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
}
function toChangeStatus(carStatusUrl, spanText) {
    $.ajax({
        type: "post",
        url: basePath + carStatusUrl,
        data: {
            ucId: ucId
        },
        async: true,
        success: function (data) {
            if (data.success == true) {
                layer.closeAll();
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: [spanText, "font-size:12px;text-align:center"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '160px'],//宽高
                    content: spanText + '成功',
                    btn: ["确定"],
                    yes: function (index, layero) {
                        layer.closeAll();
                        getCardBasicInfo();
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        getCardBasicInfo();
                    }

                });

            } else if (data.success == false) {
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
    })
}
//安全密码修改
//设置支付密码
$('#setPayPassword').on('click', function () {
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["重置支付密码", "font-size:12px;text-align:center"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '200px'],//宽高
        content: '重置之后，支付密码为123456',
        btn: ["确定", "取消"],
        yes: function (index, layero) {
            layer.closeAll();
            toSetPayPassword();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
})
function toSetPayPassword() {
    $.ajax({
        type: "post",
        url: basePath + resetCardPasswordUrl,
        async: true,
        data: {
            ucId: ucId
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
                    time: 2000,
                    btn: ["确定"]
                });
            }
        }
    });
}
function getCardIndexUser() {
    $.ajax({
        type: "post",
        url: basePath + getCardIndexUserUrl,
        async: true,
        data: {
            userId: ucUserId
        },
        success: function (data) {
            if (data.success == true) {
                var data = data.dataObject;
                var userAccount = data.userAccount;
                window.localStorage.setItem('userAccount_finAccount', userAccount);
                $('.userInfoAccount').show();
                var a = '<a  class="userHome" onclick="return false;"  href="' + basePath + '/static/html/userList/user_home.html?userId=' + ucUserId + '&cpyType=' + data.cpyType + '&cpyId=' + data.cpyId + '">' + data.userAccount + '</a>';
                $('#userAccount').html(a);
            }
            else {
                $('.userInfoAccount').hide();
            }
        }
    });
}
newTab('.userHome', '用户主页');

//黑白名单跳转
function toBlackWhiteList() {
    var getUserAccount = window.localStorage;
    var userAccount = getUserAccount.getItem("userAccount_finAccount");
    var setBlackWhite = '<a class="heibai" onclick="return false" href="' + basePath + '/static/html/blackWhiteList/blackWhite_list.html?userAccount=' + userAccount + '"><span class="openDisable">设置</span></a>';
    $('#setTd').append(setBlackWhite);
    newTab(".heibai", '黑白名单列表');
}
function getFinAccountUser() {
    $.ajax({
        type: "post",
        url: basePath + getFinAccount4CardUrl,
        async: true,
        data: {
            ucId: ucId
        },
        success: function (data) {
            if (data.success == true) {
                var data = data.dataObject;
                for (var i = 0; i < data.length; i++) {
                    var accountNO = data[0].accountNO;
                    var accountBalance = data[0].accountBalance;
                    var tradeType = data[0].tradeType;
                    var accountStatus = data[0].accountStatus;
                    var accountWarn = data[0].accountWarn;
                    var accountPresent = data[0].accountPresent;
                    var accountId = data[0].accountId;
                    var payRule = data[0].payRule;
                    var accountAll = (parseFloat(accountPresent) + parseFloat(accountBalance)).toFixed(2);
                }
                $('#accountNO').html(accountNO);
                $('#accountBalance').html(accountBalance);
                $('#refundMoney').html(accountBalance);
                $('#accountWarn').html(accountWarn);
                $('#accountPresent').html(accountPresent);
                $('#accountAll').html(accountAll);
                $('input[name=accountId]').val(accountId);

                var tradeTypeHtml = '';
                if (tradeType == 1) {
                    tradeTypeHtml = '信用账户';//后付费
                    $('#accountBalanceLi').hide();
                    $('#accountPresentLi').hide();
                    $('#accountRefundBtn').hide();
                }
                if (tradeType == 2) {
                    tradeTypeHtml = '储蓄账户';//预付费
                    $('#accountBalanceLi').show();
                    $('#accountPresentLi').show();
                    if (payRule == 2) {
                        $('#accountRefundBtn').show();
                    } else {
                        $('#accountRefundBtn').hide();
                    }
                }
                $('#tradeType').html(tradeTypeHtml);
                var accountStatusHtml = '';
                if (accountStatus == 1) {
                    accountStatusHtml = '正常';
                }
                if (accountStatus == 2) {
                    accountStatusHtml = '冻结';
                }
                if (accountStatus == 3) {
                    accountStatusHtml = '删除';
                }
                $('#accountStatus').html(accountStatusHtml);
            }
        }
    });
}

/*function getFinAccountRelation4User() {
    $.ajax({
        type: "post",
        url: basePath + getFinAccountRelation4CardUrl,
        async: true,
        data: {
            ucId: ucId
        },
        success: function (data) {
            if (data.success == true) {
                var data = data.dataObject;
                for (var i = 0; i < data.length; i++) {
                    var accountNumber = data[0].accountNO;
                    var billAccountName = data[0].billAccountName;
                }
                $('#accountNumber').html(accountNumber);
                $('#billAccountName').html(billAccountName);
            }
        }
    });
}*/
//等级设置

//设置用户等级
$('#setLevelBtn_card').on('click', function () {
    toLoadLevelListCard();
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["设置用户等级", "font-size:12px;text-align:center"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '180px'],//宽高
        content: $('.setUserLevel_card'),
        btn: ["确定", "取消"],
        yes: function (index, layero) {
            layer.closeAll();
            toSetLevelCard();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
})
function toLoadLevelListCard() {
    $.ajax({
        type: "post",
        url: basePath + getLevelList,
        async: true,
        data: {
            cpyId: ucCpyId
        },
        success: function (data) {
            var levelHtml = ' <option value="">请选择</option>';
            var data = data.dataObject;
            for (var i = 0; i < data.length; i++) {
                levelHtml += '<option value="' + data[i].levelId + '">' + data[i].levelName + '</option>';
            }
            $('#setUserLevel_card').html(levelHtml);
        }

    })
}
//修改保存等级设置
function toSetLevelCard() {
    var levelId = $('#setUserLevel_card').val();
    if (levelId == "") {
        layer.closeAll();
        return;
    }
    $.ajax({
        type: "post",
        url: basePath + modifyUserLevelUrl,
        async: true,
        data: {
            levelId: levelId,
            userId: ucUserId
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
                    time: 2000,
                    btn: ["确定"]
                });
                getCardBasicInfo();
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
                    time: 2000,
                    btn: ["确定"]
                });
            }
        }

    })
}
//新绑用户
$('#bindUser').on("click", function () {
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["绑用户", "font-size:12px;text-align:center"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '180px'],//宽高
        content: $('.newBindUser'),
        btn: ["确定", "取消"],
        yes: function (index, layero) {
            layer.closeAll();
            toBindUser();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
})
function toBindUser() {
    $.ajax({
        type: "post",
        url: basePath + cardBindUserUrl,
        async: true,
        data: {
            ucId: ucId,
            userAccount: $('input[name=userAccount]').val()
        },
        success: function (data) {
            if (data.success == true) {
                layer.closeAll();
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ['绑定用户', "font-size:12px;text-align:center"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '160px'],//宽高
                    content: '绑用户成功',
                    time: 2000,
                    btn: ["确定"],
                    yes: function (index, layero) {
                        layer.closeAll();
                    }
                });
                getCardIndexUser();
                $('input[name=userAccount]').val('');
            } else if (data.success == false) {
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
                $('input[name=userAccount]').val('');
            }
        }
    });
}
function getUserAccount() {
    $.ajax({
        type: "post",
        url: basePath + getCardAccountUrl,
        async: true,
        data: {
            cardId: ucId
        },
        success: function (data) {
            if (data.success == true) {
                var data = data.dataObject;
                var chongzhi = data.chongzhi;
                var dongjie = data.dongjie;
                var xiaofei = data.xiaofei;
                var youhui = data.youhui;
                var yue = data.yue;
                $('#chongzhi').html(chongzhi);
                $('#dongjie').html(dongjie);
                $('#xiaofei').html(xiaofei);
                $('#youhui').html(youhui);
                $('#yue').html(yue);
            }
        }
    });
}

//去获取卡用户的订单详情
function toGetOrderDetail() {
    $.ajax({
        type: "post",
        url: basePath + getHomeUserOrderUrl,
        async: true,
        data: {
            userId: ucUserId
        },
        success: function (data) {
            if (data.success == true) {
                var data = data.dataObject;
                for (var key in data) {
                    if (key == 1) {
                        $('#chargeAll').html(data[key]);
                    }

                    if (key == 3) {
                        $('#orderAll').html(data[key]);
                    }
                    if (key == 4) {
                        $('#orderToday').html(data[key]);
                    }
                }
            }
        }
    });
}

//查看按钮的操作
function baseBtnSkan() {
    //资金账户
    newTab(".toScanBtn", "资金账户列表");
    var accountId = window.localStorage.getItem('accountId_finAccount');
    var aTag = '<a class="toScanBtn" onclick="return false;"  href="' + basePath + '/static/html/finAccount/finAccount_list.html?accountId=' + accountId + '">查看</a>';
    $('#finAccountSkanBtn').html(aTag);

    //订单详情
    newTab(".toScanOrderBtn", "订单列表");
    var userAccount = window.localStorage.getItem('userAccount_finAccount');
    var orderScanTag = '<a class="toScanOrderBtn" onclick="return false;"  href="' + basePath + '/static/html/order/order_list.html?userAccount=' + userAccount + '">查看</a>';
    $('#orderSkanBtn').html(orderScanTag);

    //账务关系
    newTab(".toScanFinRelaBtn", "账务关系列表");
    var finRelaScanTag = '<a class="toScanFinRelaBtn" onclick="return false;"  href="' + basePath + '/static/html/finAccountRelation/finAccountRelation_list.html?userAccount=' + userAccount + '">查看</a>';
    $('#finRelaSkanBtn').html(finRelaScanTag);
    layer.closeAll();
}
//资金账户退款
$('body').off('click','#toRefund').on('click', '#toRefund', function () {
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["退款", "font-size:12px;text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '248px'],//宽高
        content: $('.accountRefundBlock'),
        btn: ["确定", "取消"],
        yes: function (index, layero) {
            toRefundMoney();
        },
        cancel: function (index, layero) {
            layer.closeAll();
            $('input[name=accountId]').val('');
        }
    });
})
//发起退款
function toRefundMoney() {
    if (testAccountBalance()) {
        var accountId = $('input[name=accountId]').val();
        var accountBalance = $('input[name=refundBlance]').val();
        $.ajax({
            type: "post",
            url: basePath + accountRefundUrl,
            async: true,
            data: {
                accountId: accountId,
                accountBalance: accountBalance,
                userId:ucUserId
            },
            success: function (data) {
                layer.closeAll();
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
                            getFinAccountUser();
                            getUserAccount();
                            $('input[name=accountId]').val('');
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
                        area: ['310px', '200px'],//宽高
                        content: data.msg,
                        time: 2000,
                        btn: ["确定"]
                    });
                }
            }

        })
    }

}
//校验退款余额
function testAccountBalance() {
    var refundBlance = $('input[name=refundBlance]').val();
    var reg = /^\d+(\.\d{1,2})?$/;//正整数
    var refundMoney = $('#refundMoney').html();//充值余额
    if (!refundBlance) {
        $('#refundTip').html('请输入退款金额');
        return false;
    } else {
        if (!reg.test(refundBlance)) {
            $('#refundTip').html('退款金额不能为负数,小数点后最多两位');
            return false;
        } else if (parseFloat(refundBlance) > parseFloat(refundMoney)) {
            $('#refundTip').html('退款金额不能大于充值余额');
            return false;
        } else if (parseFloat(refundBlance) == 0) {
            $('#refundTip').html('请重新输入退款金额');
            return false;
        }else {
            $('#refundTip').html('');
            return true;
        }

    }
}
//一进来展示默认的vin列表
function getVinInfoByUser() {
    $.ajax({
        type: "post",
        url: basePath + getVinInfoByUserUrl,
        async: true,
        data: {
            userId: ucUserId
        },
        success: function (data) {
            if (data.success == true) {
                var data = data.dataObject;
                var html = '';
                for (var i = 0; i < data.length; i++) {
                    var cvLicenseNumberHtml=data[i].cvLicenseNumber;
                    html += '<li><span>'
                        + data[i].cvVinCode + '</span><span>'
                        + cvLicenseNumberHtml + '</span><span class="orange unBindVIN" data-pkId="' + data[i].pkId + '">解绑</span></li>'
                }
                $('.carInfoList').html(html);

            }
        }
    });
}
//vin码绑定
$('#bindVinCode').on("click", function () {
    var flag=$(this).attr('class');
    if(flag=='grayBtn'){
        return false;
    }else{
        vinListSearch();
        layer.closeAll();
        layer.open({
            type: 1,
            offset: '100px',
            title: ["新绑VIN", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['860px', '400px'],//宽高
            content: $('.bindVinBlock'),
            cancel: function (index, layero) {
                layer.closeAll();
                $('.bindVinBlock').hide();
            }
        });
    }

})
//点击确定绑定
$('body').off('click','.sureBtn').on('click','.sureBtn',function(){
    layer.closeAll();
    $('.bindVinBlock').hide();
    toBindVINcode();
})
//加载对应渠道的vin码
function vinListSearch(){
    getHideInput();
    initTable("carVinListForm", "carVinListPage", getCarVinRelaUrl, carVinListCallback);
}
function getHideInput(){
    var cvVinCodeValue = $('input[name=cvVinCode]').val();
    $('input[name=cpyId]').val(ucCpyId);//加载相同渠道的vin码
    if (cvVinCodeValue == "") {
        $('input[name=cvVinCode]').val('');
    } else {
        $('input[name=cvVinCode]').val(cvVinCodeValue);
    }
}
function carVinListCallback(req){
    var data = req.dataObject;
    var pageNum = req.pager.pageNo;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        listTr += '<tr><td><input type="checkbox" name="ids" class="selectedBox" value="' + data[i].pkId + '"/></td>'
            + '<td class="vinList_cvVinCode">'+data[i].cvVinCode
            + '</td><td class="vinList_cpyName">' + data[i].cpyName
            + '</td><td class="vinList_cvLicenseNumber">' + data[i].cvLicenseNumber
            + '</td></tr>';
    }
    $("#bindVinCodeTbody").html(listTr);
}
//确定绑定vin码
function toBindVINcode(){
    var vinIds = '';
    $('input[name=ids]').each(function () {
        var flag = $(this).prop('checked');
        if (flag == true) {
            vinIds += $(this).attr('value') + ',';
        }
    });
    vinIds = vinIds.substring(0, vinIds.length - 1);
    if (!vinIds) {
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
            url: basePath + bindVinCodeForUserUrl,
            async: true,
            data: {
                userId: ucUserId,
                vinIds:vinIds
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
                            getVinInfoByUser();
                        },
                        cancel: function (index, layero) {
                            layer.closeAll();
                            getVinInfoByUser();
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
//vin码全选和选择操作
$("body").on('click', ".selAllVinCode", function () {
    $(".selectedBox").prop("checked", $(this).is(':checked'));
})
//单独解绑vin
$('body').off('click','.unBindVIN').on('click','.unBindVIN',function(){
    toUnbindComfirm(this, unBindUserVinRelaByIdUrl);
})
function toUnbindComfirm(obj,unBindUserVinRelaByIdUrl){
    var pkId = $(obj).attr('data-pkId');
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '160px'],//宽高
        content: '确定解绑该VIN码吗？',
        btn: ["确定", "取消"],
        yes: function (index, layero) {
            layer.closeAll();
            toUnbindVINcode(pkId, unBindUserVinRelaByIdUrl);
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
}
function  toUnbindVINcode(pkId, unBindUserVinRelaByIdUrl){
    $.ajax({
        type: "post",
        url: basePath + unBindUserVinRelaByIdUrl,
        data: {
            pkId: pkId
        },
        async: true,
        success: function (data) {
            if (data.success == true) {
                layer.closeAll();
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["解绑VIN", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '160px'],//宽高
                    content: data.msg,
                    time: 2000,
                    btn: ["确定"],
                    yes: function (index, layero) {
                        layer.closeAll();
                    }
                });
                getVinInfoByUser();

            } else if (data.success == false) {
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
    })
}

//监听回车键,组织表单刷新
function ClearSubmit(e) {
    if (e.keyCode == 13) {
        return false;
    }
}
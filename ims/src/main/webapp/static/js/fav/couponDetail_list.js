//获取现金券明细的menuId
var getMenuMap = window.localStorage.getItem('menuMap');
var getCurruntMap = JSON.parse(getMenuMap);
var menuId = getCurruntMap['现金券明细'];
$(function () {
    ctrlMenu(menuId);
    setTimeout("getCouponVarietyForList()", 100);
    setTimeout("toLoadActivityList(2)", 200);//默认进来加载渠道活动
    setTimeout("getIssuerList()", 300);
    setTimeout('getPerInfo()', 400);
})
function getPerInfo() {
    var userAccount = getUrlParam('userAccount');
    //去加载表格的数据
    if (userAccount) {
        $('input[name=userPhone]').val(userAccount);
        couponDetailListSearch();
    }
    else {
        $('input[name=userPhone]').val('');
        couponDetailListSearch();
    }
}
function ctrlMenu(menuId) {
    $.ajax({
        type: "post",
        url: basePath + getSelfButtonTreeUrl,
        async: true,
        data: {
            menuId: menuId
        },
        success: function (data) {
            var data = data.dataObject;
            if (data == null) {
                return;
            }
            if (data == null) {
                return;
            }
            if (data.length == 0) {
                return;
            } else {
                for (var i = 0; i < data.length; i++) {
                    var contents = data[i].contents;
                    if (contents.indexOf('显示') > -1) {
                        $('#showSet').show();
                    }
                    if (contents.indexOf('导出') > -1) {
                        $('#dataExport').show();
                    }
                    if (contents.indexOf('删除') > -1) {
                        $('#deleteBtn').show();
                    }

                }
            }
        }
    });
}

function toUnbindEvent() {
    $('.activeTypeBlock').unbind();
    $('.activeBlock').unbind();
    $('.cpstatesBlock').unbind();
    $('.couponvarietyBlock').unbind();
    $('.covaScopeBlock').unbind();
    $('.cityCodeBlock').unbind();
    $('.issuerBlock').unbind();
    selectModel();
}
toUnbindEvent();
//去加载表格的数据

function couponDetailListSearch() {
    toGiveHiddenInput();
    initTable("couponDetailForm", "couponDetailPage", getCouponListUrl, getCouponListCallback);
}
function getCouponListCallback(req) {
    var data = req.dataObject;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        var cpstatesHtml = '';
        if (data[i].cpstates == 1) {
            cpstatesHtml = '未兑换未过期';
        }
        if (data[i].cpstates == 2) {
            cpstatesHtml = '未兑换已过期';
        }
        if (data[i].cpstates == 3) {
            cpstatesHtml = '已兑换已使用';
        }
        if (data[i].cpstates == 4) {
            cpstatesHtml = '已兑换未使用未过期';
        }
        if (data[i].cpstates == 5) {
            cpstatesHtml = '已兑换未使用已过期';
        }

        listTr += '<tr><td><input type="checkbox" name="ids" class="selectedBox" value="' + data[i].pkCoupon + '"/></td>'
        + '<td class="couponDetail_couponvariety">' + data[i].covaActivityname
        + '<td class="couponDetail_covaCityScope">' + data[i].couponCityScope
        + '<td class="couponDetail_cpstates">' + cpstatesHtml
        + '</td><td class="couponDetail_actActivityname">' + data[i].actActivityname
        + '</td><td class="couponDetail_userPhone">' + data[i].userPhone
        + '</td><td class="couponDetail_cpCouponcode">' + data[i].cpCouponcode
        + '</td><td class="couponDetail_issuer">' + data[i].cpyName
        + '</td><td class="couponDetail_getcouponvariety">' + data[i].cpBegindate
        + '</td><td class="couponDetail_cpUpdatedate">' + data[i].cpUpdatedate
        + '</td><td class="couponDetail_cpBegindate">' + data[i].cpBegindate
        + '</td><td class="couponDetail_cpEnddate">' + data[i].cpEnddate
        + '</td></tr>';

    }
    $("#myTbogy").html(listTr);
    var arr = ['couponDetail_couponvariety', 'couponDetail_covaCityScope', 'couponDetail_cpstates', 'couponDetail_actActivityname', 'couponDetail_userPhone',
        'couponDetail_cpCouponcode','couponDetail_issuer', 'couponDetail_getcouponvariety', 'couponDetail_cpUpdatedate',
        'couponDetail_cpBegindate', 'couponDetail_cpEnddate'];
    toGetLocalStorageInfo(arr);
}
function toGiveHiddenInput() {
    var activeTypeValue = $('#activeType').attr('data-value');
    var activeValue = $('#active').attr('data-value');
    var cpLimitationHtmlValue = $('#cpLimitationHtml').attr('data-value');
    var cpstatesHtmlValue = $('#cpstatesHtml').attr('data-value');
    var couponvarietyHtmlValue = $('#couponvarietyHtml').attr('data-value');
    var covaScopeHtmlValue = $('#covaScopeHtml').attr('data-value');
    var cityCodeHtmlValue = $('#cityCodeHtml').attr('data-value');
    var cpyIdValue = $('#cpyId').attr('data-value');

    if (activeTypeValue == "") {
        $('input[name=cpActivitytype]').val('');
    } else {
        $('input[name=cpActivitytype]').val(activeTypeValue);
    }
    if (activeValue == "") {
        $('input[name=pkActivity]').val('');
    } else {
        $('input[name=pkActivity]').val(activeValue);
    }
    if (covaScopeHtmlValue == "") {
        $('input[name=covaScope]').val('');
    } else {
        $('input[name=covaScope]').val(covaScopeHtmlValue);
    }
    if (cityCodeHtmlValue == "") {
        $('input[name=cityCode]').val('');
    } else {
        $('input[name=cityCode]').val(cityCodeHtmlValue);
    }
    if (cpstatesHtmlValue == "") {
        $('input[name=cpstates]').val('');
    } else {
        $('input[name=cpstates]').val(cpstatesHtmlValue);
    }
    if (couponvarietyHtmlValue == "") {
        $('input[name=pkCouponvariety]').val('');
    } else {
        $('input[name=pkCouponvariety]').val(couponvarietyHtmlValue);
    }
    if (cpyIdValue == "") {
        $('input[name=cpyId]').val('');
    } else {
        $('input[name=cpyId]').val(cpyIdValue);
    }
}
//活动类型
$('.activeTypeUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');
    if (flag == '') {
        $('#active').html('全部').attr('data-value', '');
        $('#cpyId').html('请选择').attr('data-value', '');
        $('.activeUl').html('');
        $('.issuerUl').html('');
        toLoadActivityList('');
        getIssuerList('');
    } else {
        $('#active').html('全部').attr('data-value', '');
        $('#cpyId').html('请选择').attr('data-value', '');
        $('.activeUl').html('');
        $('.issuerUl').html('');
        toLoadActivityList(flag);
        getIssuerList(flag);
    }

});
$('.covaScopeUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');
    if (flag == 1) {
        toLoadActivityCity();
    } else {
        $('#cityCodeHtml').attr('data-value', '').html('请选择');
        $('.cityCodeUl').html('');
    }
});
$('.cityCodeUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});
//活动
$('.activeUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});
//去加载活动的城市
function toLoadActivityCity() {
    $.ajax({
        type: "post",
        url: basePath + getCityScopeUrl,
        async: true,
        success: function (data) {
            var dataModule = data.dataObject;
            var activityCityLi = '<li data-option="" class="data-selected">请选择</li>';
            for (var i = 0; i < dataModule.length; i++) {
                var data = dataModule[i];
                for (var key in data) {
                    activityCityLi += '<li data-option="' + key + '">' + data[key] + '</li>';
                }

            }
            $('.cityCodeUl').html(activityCityLi);
            toUnbindEvent();
        }
    });
}
//状态
$('.cpstatesUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});
//现金券品种
$('.couponvarietyUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});
//加载活动列表
function toLoadActivityList(flag) {
    var flag = flag;
    var activeLi = '';
    $.ajax({
        type: "post",
        url: basePath + getActivityForListUrl,
        async: true,
        data: {
            cpActivitytype: flag == undefined ? '' : flag
        },
        success: function (data) {
            if (data.success == true) {
                var dataModule = data.dataObject;
                activeLi = '<li data-option="">请选择活动</li>';
                for (var i = 0; i < dataModule.length; i++) {
                    activeLi += '<li data-option="' + dataModule[i].pkActivity + '">' + dataModule[i].actActivityname + '</li>';
                }
                $('.activeUl').html(activeLi);
                toUnbindEvent();
            }

        }
    });
}
//现金券列表
function getCouponVarietyForList() {
    $.ajax({
        type: "post",
        url: basePath + getCouponVarietyForListUrl,
        async: true,
        success: function (data) {
            if (data.success == true) {
                var dataModule = data.dataObject;
                var couponVarietyLi = '<li data-option="">请选择</li>';
                for (var i = 0; i < dataModule.length; i++) {
                    couponVarietyLi += '<li data-option="' + dataModule[i].pkCouponVariety + '">' + dataModule[i].covaActivityName + '</li>';
                }
                $('.couponvarietyUl').html(couponVarietyLi);
                toUnbindEvent();
            }

        }
    });
}

//数据导出	=====================
$('#dataExport').on("click", function () {
    toGiveHiddenInput();
    var obj = {
        cpCouponcode: $('input[name=cpCouponcode]').val(),
        cpActivitytype: $('input[name=cpActivitytype]').val(),
        pkActivity: $('input[name=pkActivity]').val(),
        userPhone: $('input[name=userPhone]').val(),
        actScope: $('input[name=actScope]').val(),
        cityCode: $('input[name=cityCode]').val(),
        cpstates: $('input[name=cpstates]').val(),
        cpyId: $('input[name=cpyId]').val(),
        pkCouponvariety: $('input[name=pkCouponvariety]').val()
    };
    window.location.href = basePath + exportCouponUrl + '?cpCouponcode='
    + obj.cpCouponcode + '&cpActivitytype='
    + obj.cpActivitytype + '&pkActivity='
    + obj.pkActivity + '&userPhone='
    + obj.userPhone + '&actScope='
    + obj.actScope + '&cpstates='
    + obj.cpstates + '&pkCouponvariety='
    + obj.pkCouponvariety + '&cpyId='
    + obj.cpyId + '&cityCode='
    + obj.cityCode;
});

//删除优惠券
$("body").off("click", "#deleteBtn").on("click", "#deleteBtn", function () {
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["确认", "font-size:12px;text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '180px'],//宽高
        content: '确定删除优惠券吗？',
        btn: ["确定", "取消"],
        yes: function (index, layero) {
            layer.closeAll();
            toDeleteCoupon();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
});
function toDeleteCoupon() {
    var ids = '';
    $('input[name=ids]').each(function () {
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
            content: '请指定表单元素',
            time: 3000,
            btn: ["确定"]
        });
    } else {
        $.ajax({
            type: "post",
            url: basePath + removeCouponUrl,
            async: true,
            data: {
                ids: ids
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
                        content: '删除成功',
                        time: 3000,
                        btn: ["确定"],
                        yes: function (index, layero) {
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
//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        couponDetailListSearch();
    }
});
//发行方
$('.issuerUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});
//加载发行方 (修改...)
function getIssuerList(flag) {
    var flag = flag;
    var issuerLi = '';
    $.ajax({
        type: "post",
        url: basePath + getCpyForListUrl,
        async: true,
        data: {
            cpActivitytype: flag == undefined ? '' : flag
        },
        success: function (data) {
            if (data.success == true) {
                var dataModule = data.dataObject;
                issuerLi = '<li data-option="">请选择</li>';
                for (var i = 0; i < dataModule.length; i++) {
                    issuerLi += '<li data-option="' + dataModule[i].pkCompanyId + '">' + dataModule[i].cpyCompanyName + '</li>';
                }
                $('.issuerUl').html(issuerLi);
                toUnbindEvent();
            }

        }
    });
}

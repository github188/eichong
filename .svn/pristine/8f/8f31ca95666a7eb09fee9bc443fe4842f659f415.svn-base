//获取现金券品种的menuId
var getMenuMap = window.localStorage.getItem('menuMap');
var getCurruntMap = JSON.parse(getMenuMap);
var menuId = getCurruntMap['现金券品种'];
$(function () {
    ctrlMenu(menuId);
    setTimeout("couponVarietyListSearch()", 200);
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
            var data = data.dataObject;
            if(data==null){
                return;
            }
            if(data==null){
                return;
            }
            if (data.length == 0) {
                return;
            } else {
                for (var i = 0; i < data.length; i++) {
                    var contents = data[i].contents;
                    if (contents.indexOf('新建') > -1) {
                        $('#addCouponBtn').show();
                    }
                }
            }
        }
    });
}
function toUnbindEvent() {
    $('.covaScopeBlock').unbind();
    $('.cityCodeBlock').unbind();
    $('.covaStutasBlock').unbind();
    selectModel();
}
toUnbindEvent();

function couponVarietyListSearch() {
    toGiveHiddenInput();
    initTable("couponVarietyForm", "couponVarietyPage", getCouponVarietyListUrl, couponVarietyListCallback);
}
function couponVarietyListCallback(req) {
    var data = req.dataObject;
    var pageNum = req.pager.pageNo;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        var covaStutasHtml = '';
        if (data[i].covaStutas == 0) {
            covaStutasHtml = '已上架';
        }
        if (data[i].covaStutas == 1) {
            covaStutasHtml = '已下架';
        }
        var covaRemark=data[i].covaRemark;
        covaRemarkHtml=covaRemark.substring(0,20);
        listTr += '<tr><td><input type="checkbox" name="id" class="selectedBox" value="' + data[i].pkCouponVariety + '"/></td>'
        + '<td>' + data[i].pkCouponVariety
        //+ '<td>' + (i + 1 + (pageNum - 1) * 20)
        + '</td><td class="toDetail" data-flag="' + data[i].pkCouponVariety + '">' + data[i].covaActivityName
        + '</td><td>' + data[i].covaCityScope
        + '</td><td>' + data[i].covaCouponValue
        + '</td><td>满' + data[i].covaCouponCondition + '元可使用'
        + '</td><td>' + covaStutasHtml
        + '</td><td>' + data[i].covaCreatedate
        + '</td><td><a class="gay" title="' + data[i].covaRemark
        + '">' + covaRemarkHtml + '</a></td></tr>';
    }
    $("#myTbogy").html(listTr);
}
function toGiveHiddenInput() {
    var covaScopeHtmlValue = $('#covaScopeHtml').attr('data-value');
    var cityCodeHtmlValue = $('#cityCodeHtml').attr('data-value');
    var covaStutasValue = $('#covaStutasHtml').attr('data-value');
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
    if (covaStutasValue == "") {
        $('input[name=covaStutas]').val('');
    } else {
        $('input[name=covaStutas]').val(covaStutasValue);
    }
}
$('.covaScopeUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag=$(this).attr('data-option');
    if(flag==1){
        toLoadActivityCity();
    }else{
        $('#cityCodeHtml').attr('data-value','').html('请选择');
        $('.cityCodeUl').html('');
    }
});
$('.cityCodeUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});
$('.covaStutasUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});
//去加载活动的城市
function toLoadActivityCity(){
    $.ajax({
        type: "post",
        url: basePath + getCityScopeUrl,
        async: true,
        success: function (data) {
            var dataModule = data.dataObject;
            var activityCityLi = '<li data-option="" class="data-selected">请选择</li>';
            for (var i = 0; i < dataModule.length; i++) {
                var data=dataModule[i];
                for(var key in data){
                    activityCityLi += '<li data-option="' + key + '">' + data[key] + '</li>';
                }

            }
            $('.cityCodeUl').html(activityCityLi);
            toUnbindEvent();
        }
    });
}
//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        couponVarietyListSearch();
    }
});
//新建优惠券品种
$('#addCouponBtn').off('click').on('click', function () {
    window.location.href = 'add_couponVariety.html';
})
//点击去详情页面
$('body').off('click', '.toDetail').on('click', '.toDetail', function () {
    var pkCouponVariety = $(this).attr('data-flag');
    window.location.href = 'couponVariety_detail.html?pkCouponVariety=' + pkCouponVariety;
})
//初始查询
$(function(){
    integralActivityListSearch();
});

//去加载表格的数据

function integralActivityListSearch(obj) {
    //if(obj != 1&&obj != 0&&obj != 2){
    //    $('.isWholeBtn').css({'background-color':'#fff','color':'#666666'});
    //}
    toGiveHiddenInput();
    initTable("intergralActivityListForm", "integralActivityPage", getIntegralActivityListUrl, intergralActivityList);
}

function intergralActivityList(req) {
    var data = req.dataObject;
    var listTr = "";
    var state = "",
        area = "";
    for (var i = 0; i < data.length; i++) {
        var start_day = data[i].startDate;
        var end_day = data[i].endDate;
        var pk_id = data[i].pkId;
        //时间判断
        if (start_day == null) {
            start_day = '';
        } else {
            start_day = new Date(data[i].startDate.time).format("yyyy-MM-dd");
        }
        if (end_day == null) {
            end_day = '';
        } else {
            end_day = new Date(data[i].endDate.time).format("yyyy-MM-dd");
        }
        //状态判断
        if (data[i].activityStatus == 0) {
            state = '开启';
        } else if (data[i].activityStatus == 1) {
            state = '关闭';
        }
        //地址判断
        if (data[i].isWhole == 1 || data[i].isWhole == null) {
            area = '全国';
        } else {
            area = data[i].addressName;
        }

        listTr += '<tr data-id="'+pk_id+'" data-param="'+data[i].integralRulesId+'"><td><input class="selectedBox" type="checkbox" name="" value=""></td><td><a class="userHome " onclick="return false;">' + data[i].activityName + '</a></td><td>' + area + '</td><td>' + data[i].directionName + '</td><td>' + state + '</td><td>' + start_day + '</td><td>' + end_day + '<td>' + data[i].userAccount + '</td></tr>';
    }

    $("#myTbogy").html(listTr);
}
//查询条件部分=========================

function toGiveHiddenInput() {
    //var provinceCodeValue = $('#provinceCode').attr('data-value');
    //var cityCodeValue = $('#cityCode').attr('data-value');
    var activityStatusValue = $('#activityStatus').attr('data-value');
    var pkIdValue = $('#pkId').attr('data-value');
    if (pkIdValue == "") {
        $('input[name=pkId]').val('');
    } else {
        $('input[name=pkId]').val(pkIdValue);
    }
    if (activityStatusValue == "") {
        $('input[name=activityStatus]').val('');
    } else {
        $('input[name=activityStatus]').val(activityStatusValue);
    }
   // var isWhole = $('input[name=isWhole]').val();
   // if (provinceCodeValue == "") {
   //     $('input[name=provinceId]').val('');
   // } else {
   //     $('input[name=provinceId]').val(provinceCodeValue);
   // }
   // if (cityCodeValue == "") {
   //     $('input[name=cityId]').val('');
   // } else {
   //     $('input[name=cityId]').val(cityCodeValue);
   // }
   // if(isWhole == ''){
   //     $('input[name=isWhole]').val('');
   // }else if(provinceCodeValue != '' || cityCodeValue != ''){
   //     $('input[name=isWhole]').val('0');
   // }else if(provinceCodeValue == '' && cityCodeValue == ''){
   //     $('input[name=isWhole]').val('1');
   // }
}

//---------------------地区查询------------------------------
//toLoadProvince('', '#provinceCode', '.provinceUl', 'toUnbindEvent'); //key
toUnbindEvent()
function toUnbindEvent() {
    $('.provinceBlock').unbind();
    $('.cityBlock').unbind();
    $('.userStatusBlock').unbind();
    $('.cpyProvinceBlock').unbind();
    $('.cypCityBlock').unbind();
    $('.userStatusBlock').unbind();
    $('.cpyCompanyBlock').unbind();
    $('.levelBlock').unbind();
    $('.tagBlock').unbind();
    $('.activityStatusUl').unbind();
    $('.pkIdUl').unbind();
    selectModel();
}
//抓取省
//$('.provinceUl').on("click", "li", function() {
//    $('#cityCode').html('请选择');
//    $('#cityCode').attr('data-value', '');
//    $('.cityUl').html('');
//    $('input[name=cityCode]').val('');
//    var provinceCodeId = $(this).attr('data-option');
//    $(this).parent().siblings('div.model-select-text').text($(this).text()).attr('data-value', $(this).attr('data-option'));
//    var flag = $(this).attr('data-option');
//    if (flag == "") {
//        $('#cityCode').html('请选择');
//        $('#cityCode').attr('data-value', '');
//        $('.cityUl').html('');
//        $('input[name=cityCode]').val('');
//    } else {
//        toLoadCity(provinceCodeId, '', '#cityCode', '.cityUl', 'toUnbindEvent');
//    }
//});
////抓取市
//$('.cityUl').on("click", "li", function() {
//    $(this).parent().siblings('div.model-select-text').text($(this).text()).attr('data-value', $(this).attr('data-option'));
//});
//-----------------------------------------------------------------------
$('.activityStatusUl').on("click", "li", function() {
    $(this).parent().siblings('div.model-select-text').text($(this).text()).attr('data-value', $(this).attr('data-option'));
});
$('.pkIdUl').on("click", "li", function() {
    $(this).parent().siblings('div.model-select-text').text($(this).text()).attr('data-value', $(this).attr('data-option'));
});
//全国按钮

//function integralActivityListWholeCountry(){
//    $('#provinceCode').attr('data-value','');
//    $('#cityCode').attr('data-value','');
//    $('#cityCode').text('请选择');
//    $('.cityUl li').remove();
//    var isWholeData = $('input[name="isWhole"]').val();
//    if(isWholeData == ''){
//        $('input[name="isWhole"]').val('1');
//        $('.isWholeBtn').css({'background-color':'#FF7D00','color':'#fff'});
//        $('#provinceCode').text('全国');
//        integralActivityListSearch(1);
//    }else if(isWholeData == '1'){
//        $('input[name="isWhole"]').val('');
//        $('.isWholeBtn').css({'background-color':'#fff','color':'#666666'});
//        $('#provinceCode').text('请选择');
//        integralActivityListSearch(0);
//    }else if(isWholeData == '0'){
//        $('input[name="isWhole"]').val('0');
//        $('.isWholeBtn').css({'background-color':'#FF7D00','color':'#fff'});
//        $('#provinceCode').text('全国');
//        integralActivityListSearch(2);
//    }
//}

//按钮跳转
$(function() {
    var link_one = 'static/html/integral/integralActivityList/integralActivity_RechargeGift.html',
        link_two = 'static/html/integral/integralActivityList/integralActivity_RechargeConsumption.html',
        link_three = 'static/html/integral/integralActivityList/integralActivity_dailyCollection.html',
        link_four = 'static/html/integral/integralActivityList/integralActivity_EditProfile.html',
        link_five = 'static/html/integral/integralActivityList/integralActivity_Share.html',
        link_six = 'static/html/integral/integralActivityList/integralActivity_exchange.html',
        link_seven = 'static/html/integral/integralActivityList/integralActivity_festival.html',
        link_eight = 'static/html/integral/integralActivityList/integralActivity_birthday.html';
    $('.integralActivity_RechargeGift').attr('href', link_one);
    $('.integralActivity_RechargeConsumption').attr('href', link_two);
    $('.integralActivity_dailyCollection').attr('href', link_three);
    $('.integralActivity_EditProfile').attr('href', link_four);
    $('.integralActivity_Share').attr('href', link_five);
    $('.integralActivity_Exchange').attr('href', link_six);
    $('.integralActivity_festival').attr('href', link_seven);
    $('.integralActivity_birthday').attr('href', link_eight);

    newTab(".integralActivity_RechargeGift", "充值赠送-新增");
    newTab(".integralActivity_RechargeConsumption", "消费赠送-新增");
    newTab(".integralActivity_dailyCollection", "每日领取-新增");
    newTab(".integralActivity_EditProfile", "修改资料赠送-新增");
    newTab(".integralActivity_Share", "分享赠送-新增");
    newTab(".integralActivity_Exchange", "积分兑换-新增");
    newTab(".integralActivity_festival", "节假日-新增");
    newTab(".integralActivity_birthday", "生日赠送-新增");

});

//活动跳转
$('body').on('click','.userHome',function(){
    var page='';
    var pk = $(this).parent().parent().attr('data-id');
    var ruleId = $(this).parent().parent().attr('data-param');
    switch (pk)
    {
        case '1':
            page = 'integralActivity_RechargeGift_ed';
            window.location.href= page+'.html?integralRulesId='+ruleId;//充值赠送-编辑
            break;
        case '2':
            page = 'integralActivity_RechargeConsumption_ed';
            window.location.href= page+'.html?integralRulesId='+ruleId;//充电消费
            break;
        case '3':
            page = 'integralActivity_dailyCollection_ed';
            window.location.href= page+'.html?integralRulesId='+ruleId;//每日领取
            break;
        case '4':
            page = 'integralActivity_EditProfile_ed';
            window.location.href= page+'.html?integralRulesId='+ruleId;//修改资料
            break;
        case '5':
            page = 'integralActivity_Share_ed';
            window.location.href= page+'.html?integralRulesId='+ruleId;//充电消费-分享是否赠送
            break;
        case '6':
            page = 'integralActivity_exchange_ed';
            window.location.href= page+'.html?integralRulesId='+ruleId;//积分兑换
            break;
        case '8':
            page = 'integralActivity_festival_ed';
            window.location.href= page+'.html?integralRulesId='+ruleId;//节假日
            break;
        case '9':
            page = 'integralActivity_birthday_ed';
            window.location.href= page+'.html?integralRulesId='+ruleId;//生日
            break;
    }
});

//监听回车键
$(document).keyup(function(event) {
    if (event.keyCode == 13) {
        integralActivityListSearch();
    }
});
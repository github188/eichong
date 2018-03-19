toUnbindEvent();
function toUnbindEvent() {
    $('.actActivityruleBlock').unbind();
    $('.prizeListBlock').unbind();
    $('.prizeTypeBlock').unbind();
    $('.actScopeBlock').unbind();
    $('.cityCodeBlock').unbind();

    $('.provinceBlock').unbind();
    $('.cityBlock').unbind();
    $('.areaCodeBlock').unbind();
    $('.actStationBlock').unbind();
    $('.actChanneltypeBlock').unbind();
    selectModel();
}
//加载页面时默认的奖品类型
setTimeout('getIssuerList()',100);
setTimeout('toLoadDefaultPrizaType()',200);
function toLoadDefaultPrizaType(){
    //var actActivityruleValue = $('#actActivityrule').attr('data-value');
    //if (actActivityruleValue ==1) {
        toLoadprizeType();
    //}else if (actActivityruleValue ==7) {
    //    toLoadprizeType();
    //}
}
//规则
var prizeListArr = [];
var textHtmlArr = [];
$('.actActivityruleUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    $('#actBegindate_f1').val('');
    $('#actEnddate_f1').val('');
    $('#actCouponEnddate_f1').val('');
    $('#beginTime').val('');
    $('#actTopMoney').val('');
    $('#singelMoneyR').val('');
    $('#singelMoneyC').val('');
    $('#provinceCode').attr('data-value','');
    $('#provinceCode').html('选择省');
    $('.provinceUl').html('');
    $('#cityCode').attr('data-value','');
    $('#cityCode').html('选择市');
    $('.cityUl').html('');
    $('#areaCode').attr('data-value','');
    $('#areaCode').html('选择区');
    $('.areaUl').html('');
    $('#pkPowerstation').attr('data-value','');
    $('#pkPowerstation').html('选择充电点');
    $('.pkPowerstationUl').html('');

    $('#prizeType').attr('data-value','');
    $('#prizeType').html('请选择');
    $('.prizeTypeUl').html('');
    $('#prizeNumber').val('');

    prizeListArr = [];
    textHtmlArr = [];
    $('#prizeListUl').html('');
    toShowPrize();
    var actActivityruleValue = $('#actActivityrule').attr('data-value');
    //if (actActivityruleValue <= 4 && actActivityruleValue>1) {
    //    $('#startDate').css('display', 'block');
    //    $('#endDate').css('display', 'block');
    //    $('#stopDate').css('display', 'block');
    //    $('#stopTime').css('display', 'none');
    //    $('#rechargeSend').css('display', 'none');
    //    $('#consumeSend').css('display', 'none');
    //    $('#specificUser').css('display', 'none');
    //}
    if (actActivityruleValue ==1) {
        $('#startDate').css('display', 'block');
        $('#endDate').css('display', 'block');
        $('#stopDate').css('display', 'block');
        $('#stopTime').css('display', 'none');
        $('#rechargeSend').css('display', 'none');
        $('#consumeSend').css('display', 'none');
        $('#specificUser').css('display', 'none');
        $('#actScopeBlock').css('display','block');
        $('#actScope').html('全国活动');
        $('#actScope').attr('data-value','0');
        $('.actScopeUl').html('<li data-option="0" class="seleced">全国活动</li>');
        toLoadprizeType();
    }
    if (actActivityruleValue==5) {
        $('#startDate').css('display', 'block');
        $('#endDate').css('display', 'block');
        $('#stopDate').css('display', 'block');
        $('#stopTime').css('display', 'none');
        $('#rechargeSend').css('display', 'block');
        $('#consumeSend').css('display', 'none');
        $('#specificUser').css('display', 'none');
        $('#actScopeBlock').css('display','block');
        $('#actScope').html('全国活动');
        $('#actScope').attr('data-value','0');
        $('.actScopeUl').html('<li data-option="0" class="seleced">全国活动</li><li data-option="1">城市活动</li>');
        var actScopeValue=$('#actScope').attr('data-value');
        if(actScopeValue==0){
            toLoadprizeType();
        }
    }
    if (actActivityruleValue == 6) {
        $('#startDate').css('display', 'block');
        $('#endDate').css('display', 'block');
        $('#stopDate').css('display', 'block');
        $('#stopTime').css('display', 'none');
        $('#rechargeSend').css('display', 'none');
        $('#consumeSend').css('display', 'block');
        $('#specificUser').css('display', 'none');
        $('#actScopeBlock').css('display','block');
        $('#actScope').html('全国活动');
        $('#actScope').attr('data-value','0');
        $('.actScopeUl').html('<li data-option="0" class="seleced">全国活动</li><li data-option="1">城市活动</li><li data-option="2">站点活动</li>');
        var actScopeValue=$('#actScope').attr('data-value');
        if(actScopeValue==0){
            toLoadprizeType();
        }
    }
    if (actActivityruleValue == 7) {
        $('#startDate').css('display', 'none');
        $('#endDate').css('display', 'none');
        $('#stopDate').css('display', 'none');
        $('#stopTime').css('display', 'block');
        $('#rechargeSend').css('display', 'none');
        $('#consumeSend').css('display', 'none');
        $('#specificUser').css('display', 'block');
        $('#actScopeBlock').css('display','none');
        $('#actScope').html('全国活动');
        $('#actScope').attr('data-value','');
        $('.actScopeUl').html('');
        toLoadprizeType();
        //add_File();
    }
    toLoadRange();
})
//活动范围
$('.actScopeUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    $('#provinceCode').attr('data-value','');
    $('#provinceCode').html('选择省');
    $('.provinceUl').html('');
    $('#cityCode').attr('data-value','');
    $('#cityCode').html('选择市');
    $('.cityUl').html('');
    $('#areaCode').attr('data-value','');
    $('#areaCode').html('选择区');
    $('.areaUl').html('');
    $('#pkPowerstation').attr('data-value','');
    $('#pkPowerstation').html('选择充电点');
    $('.pkPowerstationUl').html('');
    $('#prizeType').attr('data-value','');
    $('#prizeType').html('请选择');
    $('.prizeTypeUl').html('');
    $('#prizeNumber').val('');
    var actScopeValue=$('#actScope').attr('data-value');
    if(actScopeValue==0){
        toLoadprizeType();
    }
    toLoadRange();
})
//根据活动范围和活动类型 加载省市区站点下拉列表
function toLoadRange(){
    var actActivityruleValue = $('#actActivityrule').attr('data-value');
    var actScopeValue = $('#actScope').attr('data-value');
    if(actActivityruleValue==1){
        if(actScopeValue==1){
            $('#registerSendRange').css('display','block');
            $('#consumptionSendRange').css('display','none');
        }else{
            $('#registerSendRange').css('display','none');
            $('#consumptionSendRange').css('display','none');
        }
    }
    if(actActivityruleValue==5){
        if(actScopeValue==1){
            $('#registerSendRange').css('display','none');
            $('#consumptionSendRange').css('display','block');
            $('#province').css('display','block');
            $('#city').css('display','block');
            $('#station').css('display','none');
            toLoadProvince('','#provinceCode','.provinceUl','toUnbindEvent');
        }else if(actScopeValue==0){
            $('#registerSendRange').css('display','none');
            $('#consumptionSendRange').css('display','none');
        }
    }
    if(actActivityruleValue==6){
        if(actScopeValue==1){
            $('#registerSendRange').css('display','none');
            $('#consumptionSendRange').css('display','block');
            $('#province').css('display','block');
            $('#city').css('display','block');
            $('#station').css('display','none');
            toLoadProvince('','#provinceCode','.provinceUl','toUnbindEvent');
        }else if(actScopeValue==2){
            $('#registerSendRange').css('display','none');
            $('#consumptionSendRange').css('display','block');
            $('#province').css('display','block');
            $('#city').css('display','block');
            $('#station').css('display','block');
            toLoadProvince('','#provinceCode','.provinceUl','toUnbindEvent');
        }else if(actScopeValue==0){
            $('#registerSendRange').css('display','none');
            $('#consumptionSendRange').css('display','none');
        }
    }
    if( actActivityruleValue==7){
        $('#registerSendRange').css('display','none');
        $('#consumptionSendRange').css('display','none');
    }
}
//城市筛选部分=======
$('.provinceUl').on("click", "li", function () {
    $('#cityCode').attr('data-value', '');
    $('#cityCode').html('选择市');
    $('input[name=cityCode]').val('');
    $('#areaCode').attr('data-value', '');
    $('#areaCode').html('选择区');
    $('#pkPowerstation').attr('data-value', '');
    $('#pkPowerstation').html('选择充电点');
    $('input[name=pkPowerstation]').val('');

    var provinceCodeId = $(this).attr('data-option');
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');
    if (flag == "") {
        $('#provinceCode').html('请选择');
        $('#provinceCode').attr('data-value', '');
        $('#cityCode').html('选择市');
        $('#cityCode').attr('data-value', '');
        $('.cityUl').html('');
        $('input[name=cityCode]').val('');
        $('#areaCode').attr('data-value', '');
        $('#areaCode').html('选择区');
        $('.areaUl').html('');
        $('#pkPowerstation').attr('data-value', '');
        $('#pkPowerstation').html('选择充电点');
        $('.pkPowerstationUl').html('');
        $('input[name=pkPowerstation]').val('');
    } else {
        toLoadCity(provinceCodeId, '', '#cityCode', '.cityUl', 'toUnbindEvent');
    }
});
$('.cityUl').on("click", "li", function () {
    $('#areaCode').attr('data-value', '');
    $('#areaCode').html('选择区');
    $('#pkPowerstation').attr('data-value', '');
    $('#pkPowerstation').html('选择充电点');
    $('input[name=pkPowerstation]').val('');

    var cityCodeId = $(this).attr('data-option');
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));

    var flag = $(this).attr('data-option');
    if (flag == "") {
        $('#cityCode').html('选择市');
        $('#cityCode').attr('data-value', '');
        $('#areaCode').attr('data-value', '');
        $('#areaCode').html('选择区');
        $('.areaUl').html('');
        $('#pkPowerstation').attr('data-value', '');
        $('#pkPowerstation').html('选择充电点');
        $('.pkPowerstationUl').html('');
        $('input[name=pkPowerstation]').val('');
    } else {
        toLoadArea(cityCodeId, '', '#areaCode', '.areaUl', 'toUnbindEvent');
    }
    var actScopeValue =$('#actScope').attr('data-value');
    if(actScopeValue==1){
        toLoadprizeType();
    }
});
$('.areaUl').on("click", "li", function () {
    $('#pkPowerstation').attr('data-value', '');
    $('#pkPowerstation').html('选择充电点');
    $('input[name=pkPowerstation]').val('');

    var areaCodeId = $(this).attr('data-option');
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));

    var flag = $(this).attr('data-option');
    if (flag == "") {
        $('#areaCode').attr('data-value', '');
        $('#areaCode').html('选择区');
        $('#pkPowerstation').attr('data-value', '');
        $('#pkPowerstation').html('选择充电点');
        $('.pkPowerstationUl').html('');
        $('input[name=pkPowerstation]').val('');
    } else {
        //toLoadPowerStationList(areaCode, pkPowerstationId, powerstationHtml, powerstationUl, toUnbindEventName)
        toLoadPowerStationList(areaCodeId, '', '#pkPowerstation', '.pkPowerstationUl', 'toUnbindEvent');
    }
});
$('.pkPowerstationUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    toLoadprizeType();

});

$("body").off("mouseover", ".actScopeUl li").on("mouseover", ".actScopeUl li", function(){
    $(this).parent().find('li').removeClass('seleced');
    $(this).addClass('seleced');
});
$("body").off("mouseover", ".cityCodeUl li").on("mouseover", ".cityCodeUl li", function(){
    $(this).parent().find('li').removeClass('seleced');
    $(this).addClass('seleced');
});
//奖品种类
$('.prizeListUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
})
//现金券类型
$('.prizeTypeUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    $('#prizeNameTip').html('');
})
//现金券类型加载
//setTimeout('toLoadprizeType()', 300)
function toLoadprizeType() {
    $('#prizeType').attr('data-value','');
    $('#prizeType').html('请选择');
    $('.prizeTypeUl').html('');
    $('#prizeNumber').val('');
    var actScopeValue=$('#actScope').attr('data-value');
    var provinceCodeValue=$('#provinceCode').attr('data-value');
    var cityCodeValue=$('#cityCode').attr('data-value');
    var areaCodeValue=$('#areaCode').attr('data-value');
    var pkPowerstationValue=$('#pkPowerstation').attr('data-value');
    $.ajax({
        type: "post",
        url: basePath + getCouponVarietyForListUrl,
        async: true,
        data:{
            covaScope :actScopeValue,
            provinceCode:provinceCodeValue,
            cityCode:cityCodeValue,
            areaCode:areaCodeValue,
            pkPowerstation:pkPowerstationValue
        },
        success: function (data) {
            if (data.success == true) {
                var data = data.dataObject;
                var toLoadCpyParentIdLi = '<li data-option="" class="data-selected">请选择</li>';
                for (var i = 0; i < data.length; i++) {
                    toLoadCpyParentIdLi += '<li data-option="' + data[i].pkCouponVariety + '">' + data[i].covaActivityName + '</li>';
                }
                $('.prizeTypeUl').html(toLoadCpyParentIdLi);
                toUnbindEvent();
            }
        }
    });
}
//新增活动奖品
$('#addPrizeList').on('click', function () {
    var actActivityruleValue = $('#actActivityrule').attr('data-value');
    if(actActivityruleValue==1 || actActivityruleValue==7){
        $('#prizeNameTip').html('');
        var aaa = $('#prizeList').attr('data-value');
        var prize = '';
        if (aaa == 1) {
            prize = '现金券';
        }
        var prizeNameTest = $('#prizeType').attr('data-value');
        if (!prizeNameTest) {
            $('#prizeNameTip').html('请选择奖品种类');
            return false;
        }
        var pkCouponVariety = $('#prizeType').attr('data-value');
        for (var i = 0; i < prizeListArr.length; i++) {
            if (pkCouponVariety == prizeListArr[i].pkCouponVariety) {
                layer.closeAll();
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '180px'],//宽高
                    content: "同一种现金券品种只能添加一次！",
                    btn: ["确定"]
                });
                return false;
            }
        }
        var prizeName = $('#prizeType').html();
        var prizeNum = $('#prizeNumber').val() / 1;
        var reg=/^[0-9][\d]*$/;
        if (!prizeNum) {
            $('#prizeNameTip').html('请选择奖品数量');
            $('#prizeNumber').focus();
            return false;
        } else if (!reg.test(prizeNum)) {
            $('#prizeNameTip').html('输入有误，请重新选择奖品数量');
            $('#prizeNumber').focus();
            return false;
        } else if (prizeNum==0) {
            $('#prizeNameTip').html('输入有误，请重新选择奖品数量');
            $('#prizeNumber').focus();
            return false;
        }
        var liList = '<li><p>' + prize + prizeName + '，' + prizeNum + '张</p><span class="deleteBtn">删除</span></li>';
        $('#prizeListUl').prepend(liList);
        var prizeJson = {
            singelMoney:'',
            actsType: aaa,
            pkCouponVariety: pkCouponVariety,
            num: prizeNum
        }
        var textHtmlJson = {
            singelMoney:'',
            actsType: prize,
            pkCouponVariety: prizeName,
            num: prizeNum
        }
        prizeListArr.push(prizeJson);
        textHtmlArr.push(textHtmlJson);
        toShowPrize();
    }
    if(actActivityruleValue==5){
        $('#prizeNameTip').html('');
        var aaa = $('#prizeList').attr('data-value');
        var prize = '';
        if (aaa == 1) {
            prize = '现金券';
        }
        var prizeNameTest = $('#prizeType').attr('data-value');
        if (!prizeNameTest) {
            $('#prizeNameTip').html('请选择奖品种类');
            return false;
        }
        var pkCouponVariety = $('#prizeType').attr('data-value');
        var chongzhiMoney = $('#singelMoneyR').val();
        for (var i = 0; i < prizeListArr.length; i++) {
            if (pkCouponVariety == prizeListArr[i].pkCouponVariety && chongzhiMoney==prizeListArr[i].singelMoney) {
                layer.closeAll();
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '180px'],//宽高
                    content: "同一种现金券品种只能添加一次！",
                    btn: ["确定"]
                });
                return false;
            }
        }
        var prizeName = $('#prizeType').html();
        var prizeNum = $('#prizeNumber').val() / 1;
        var reg=/^[0-9][\d]*$/;
        if (!prizeNum) {
            $('#prizeNameTip').html('请选择奖品数量');
            $('#prizeNumber').focus();
            return false;
        } else if (!reg.test(prizeNum)) {
            $('#prizeNameTip').html('输入有误，请重新选择奖品数量');
            $('#prizeNumber').focus();
            return false;
        } else if (prizeNum==0) {
            $('#prizeNameTip').html('输入有误，请重新选择奖品数量');
            $('#prizeNumber').focus();
            return false;
        }
        var liList = '<li><p>'+'单笔充值满'+ chongzhiMoney +'就送：'+ prize + prizeName + '，' + prizeNum + '张</p><span class="deleteBtn">删除</span></li>';
        $('#prizeListUl').prepend(liList);
        var prizeJson = {
            singelMoney:chongzhiMoney,
            actsType: aaa,
            pkCouponVariety: pkCouponVariety,
            num: prizeNum
        }
        var textHtmlJson = {
            singelMoney:chongzhiMoney,
            actsType: prize,
            pkCouponVariety: prizeName,
            num: prizeNum
        }
        prizeListArr.push(prizeJson);
        textHtmlArr.push(textHtmlJson);
        toShowPrize();
    }
    if(actActivityruleValue==6){
        $('#prizeNameTip').html('');
        var aaa = $('#prizeList').attr('data-value');
        var prize = '';
        if (aaa == 1) {
            prize = '现金券';
        }
        var prizeNameTest = $('#prizeType').attr('data-value');
        if (!prizeNameTest) {
            $('#prizeNameTip').html('请选择奖品种类');
            return false;
        }
        var pkCouponVariety = $('#prizeType').attr('data-value');
        var xiaofeiMoney = $('#singelMoneyC').val();
        for (var i = 0; i < prizeListArr.length; i++) {
            if (pkCouponVariety == prizeListArr[i].pkCouponVariety && xiaofeiMoney==prizeListArr[i].singelMoney) {
                layer.closeAll();
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '180px'],//宽高
                    content: "同一种现金券品种只能添加一次！",
                    btn: ["确定"]
                });
                return false;
            }
        }
        var prizeName = $('#prizeType').html();
        var prizeNum = $('#prizeNumber').val() / 1;
        var reg=/^[0-9][\d]*$/;
        if (!prizeNum) {
            $('#prizeNameTip').html('请选择奖品数量');
            $('#prizeNumber').focus();
            return false;
        } else if (!reg.test(prizeNum)) {
            $('#prizeNameTip').html('输入有误，请重新选择奖品数量');
            $('#prizeNumber').focus();
            return false;
        } else if (prizeNum==0) {
            $('#prizeNameTip').html('输入有误，请重新选择奖品数量');
            $('#prizeNumber').focus();
            return false;
        }
        var liList = '<li><p>'+'单笔消费满'+ xiaofeiMoney +'就送：'+ prize + prizeName + '，' + prizeNum + '张</p><span class="deleteBtn">删除</span></li>';
        $('#prizeListUl').prepend(liList);
        var prizeJson = {
            singelMoney:xiaofeiMoney,
            actsType: aaa,
            pkCouponVariety: pkCouponVariety,
            num: prizeNum
        }
        var textHtmlJson = {
            singelMoney:xiaofeiMoney,
            actsType: prize,
            pkCouponVariety: prizeName,
            num: prizeNum
        }
        prizeListArr.push(prizeJson);
        textHtmlArr.push(textHtmlJson);
        toShowPrize();
    }

})
//删除活动奖品
$("body").off("click", ".deleteBtn").on("click", ".deleteBtn", function () {
    var aaa = $(this).parent();
    var i = aaa.index();
    $('#prizeListUl').find(aaa).remove();
    var lilength = $('#prizeListUl').children().length;
    prizeListArr.splice((lilength - i), 1);
    textHtmlArr.splice((lilength - i), 1);
    toShowPrize();
})
//文字显示
function toShowPrize() {
    $('#prizeShowTit').html('');
    if (textHtmlArr.length) {
        var ruleType = $('#actActivityrule').html();
        $('#prizeShowTit').html(ruleType);
    }
    $('#prizeShowCon').html('');
    var listtext = '';
    var actActivityruleValue = $('#actActivityrule').attr('data-value');
    if(actActivityruleValue == 1||actActivityruleValue == 7){
        for (var i = 0; i < textHtmlArr.length; i++) {
            listtext += textHtmlArr[i].actsType + textHtmlArr[i].pkCouponVariety + '，' + textHtmlArr[i].num + '张；';
        }
        $('#prizeShowCon').append(listtext);
    }
    if (actActivityruleValue == 5) {
        for (var i = 0; i < textHtmlArr.length; i++) {
            listtext +=  '单笔充值满'+textHtmlArr[i].singelMoney+'就送：'+textHtmlArr[i].actsType + textHtmlArr[i].pkCouponVariety + '，' + textHtmlArr[i].num + '张；';
        }
        $('#prizeShowCon').append(listtext);
    }
    if (actActivityruleValue == 6) {
        for (var i = 0; i < textHtmlArr.length; i++) {
            listtext +=  '单笔消费满'+textHtmlArr[i].singelMoney+'就送：'+textHtmlArr[i].actsType + textHtmlArr[i].pkCouponVariety + '，' + textHtmlArr[i].num + '张；';
        }
        $('#prizeShowCon').append(listtext);
    }
}
$('#singelMoneyR').on('blur',function(){
    toShowPrize();
})
$('#singelMoneyC').on('blur',function(){
    toShowPrize();
})

//返回
$('#goCancel').on('click', function () {
    window.location.href = "activity_list.html";
})
//保存
function saveInfo(){
    var actActivityname = $('#actActivityname').val();
    if (!actActivityname) {
        $('#actActivitynameTip').html('活动名称不能为空，请输入活动名称');
        $('#actActivityname').focus();
        return false;
    }else if(actActivityname.length>50){
        $('#actActivitynameTip').html('活动名称过长，最多50个字');
        $('#actActivityname').focus();
        return false;
    }
    var actChanneltype =  $('#actChanneltype').attr('data-value');
    if (!actChanneltype) {
        $('#actChanneltypeTip').html('优惠券发行方不能为空，请选择输入优惠券发行方');
        return false;
    }
    var actBegindates = $('input[name=actBegindates]').val();
    var actEnddates = $('input[name=actEnddates]').val();
    var actCouponEndDates = $('input[name=actCouponEndDates]').val();
    if (actEnddates < actBegindates) {
        layer.closeAll();
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '180px'],//宽高
            content: "活动结束时间不能小于活动开始时间！",
            btn: ["确定"]
        });
        return false;
    }
    if (actCouponEndDates < actEnddates) {
        layer.closeAll();
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '180px'],//宽高
            content: "优惠券使用结束时间不能小于活动结束时间！",
            btn: ["确定"]
        });
        return false;
    }
    if (prizeListArr.length == 0) {
        layer.closeAll();
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '180px'],//宽高
            content: "活动奖品不能为空，请添加活动奖品内容！",
            btn: ["确定"]
        });
        return false;
    }
    var actActivityruleValue = $('#actActivityrule').attr('data-value');
    if (actActivityruleValue != 7) {
        $('#actBegindatesTip').html('');
        $('#actEnddatesTip').html('');
        $('#actCouponEndDatesTip').html('');
        if (!actBegindates) {
            $('#actBegindatesTip').html('活动开始时间不能为空，请选择活动开始时间！');
            $('#actBegindate_f1').focus();
            return false;
        }
        if (!actEnddates) {
            $('#actEnddatesTip').html('活动结束时间不能为空，请选择活动结束时间！');
            $('#actEnddate_f1').focus();
            return false;
        }
        if (!actCouponEndDates) {
            $('#actCouponEndDatesTip').html('优惠券结束时间不能为空，请选择优惠券使用结束时间！');
            $('#actCouponEnddate_f1').focus();
            return false;
        }
    }
    if (actActivityruleValue == 5) {
        for(var i=0;i<prizeListArr.length;i++){
            for(var j=i+1;j<prizeListArr.length;j++){
                if(prizeListArr[i].singelMoney==prizeListArr[j].singelMoney){
                    $('#singelMoneyRTip').html('同一种单笔充值满送不能有多种奖品！');
                    return false;
                }
            }
        }
        $('#actTopMoneyTip').html('');
        $('#singelMoneyRTip').html('');
        var reg=/^[0-9][\d]*$/;
        var actTopMoneyValue = $('#actTopMoney').val();
        if (!actTopMoneyValue) {
            $('#actTopMoneyTip').html('单人最高充值金额不能为空！');
            $('#actTopMoney').focus();
            return false;
        } else if (!reg.test(actTopMoneyValue)) {
            $('#actTopMoneyTip').html('输入有误，请重新输入单人最高充值金额！');
            $('#actTopMoney').focus();
            return false;
        } else if (actTopMoneyValue==0) {
            $('#actTopMoneyTip').html('输入有误，请重新输入单人最高充值金额！');
            $('#actTopMoney').focus();
            return false;
        }
        var singelMoneyRValue = $('#singelMoneyR').val();
        if (!singelMoneyRValue) {
            $('#singelMoneyRTip').html('单笔充值金额满送条件不能为空！');
            $('#singelMoneyR').focus();
            return false;
        } else if (!reg.test(singelMoneyRValue)) {
            $('#singelMoneyRTip').html('输入有误，请重新输入单笔充值满送金额！');
            $('#singelMoneyR').focus();
            return false;
        } else if (singelMoneyRValue==0) {
            $('#singelMoneyRTip').html('输入有误，请重新输入单笔充值满送金额！');
            $('#singelMoneyR').focus();
            return false;
        }
    }
    if (actActivityruleValue == 6) {
        for(var i=0;i<prizeListArr.length;i++){
            for(var j=i+1;j<prizeListArr.length;j++){
                if(prizeListArr[i].singelMoney==prizeListArr[j].singelMoney){
                    $('#singelMoneyCTip').html('同一种单笔消费满送不能有多种奖品！');
                    return false;
                }
            }
        }
        $('#singelMoneyCTip').html('');
        var reg=/^[0-9][\d]*$/;
        var singelMoneyCValue = $('#singelMoneyC').val();
        if (!singelMoneyCValue) {
            $('#singelMoneyCTip').html('单笔消费金额满送条件不能为空！');
            $('#singelMoneyC').focus();
            return false;
        } else if (!reg.test(singelMoneyCValue)) {
            $('#singelMoneyCTip').html('输入有误，请重新输入单笔消费满送金额！');
            $('#singelMoneyC').focus();
            return false;
        } else if (singelMoneyCValue==0) {
            $('#singelMoneyCTip').html('输入有误，请重新输入单笔消费满送金额！');
            $('#singelMoneyC').focus();
            return false;
        }
    }
    if(actActivityruleValue == 7) {
        $('#beginTimeTip').html('');
        $('#fileNameTip').html('');
        var fileName = $('#file').val();
        var actStopDate = $('#beginTime').val();
        if (!actStopDate) {
            $('#beginTimeTip').html('优惠券结束时间不能为空，请选择优惠券使用结束时间！');
            $('#beginTime').focus();
            return false;
        }
        if(!fileName){
            $('#fileNameTip').html('请选择需要上传的文件！');
            return false;
        }
    }
    if (actActivityruleValue <= 4) {
        setForm();
    }
    if (actActivityruleValue == 5) {
        setForm2();
    }
    if (actActivityruleValue == 6) {
        setForm3();
    }
    if (actActivityruleValue == 7) {
        setForm4();
    }
}
function setForm(){
    var formData = new FormData();
    var a=$('#actActivityrule').attr('data-value');
    var prizeList = JSON.stringify(prizeListArr);
    var actActivityname=$('input[name=actActivityname]').val();
    var actRemark=$('input[name=actRemark]').val();
    var startDate=$('input[name=actBegindates]').val();
    var endDate=$('input[name=actEnddates]').val();
    var actCouponEndDates=$('input[name=actCouponEndDates]').val();
    var actActivityruleValue=$('#actActivityrule').attr('data-value');
    var actScopeValue=$('#actScope').attr('data-value');
    var provinceCodeValue=$('#provinceCode').attr('data-value');
    var cityCodeValue=$('#cityCode').attr('data-value');
    var areaCodeValue=$('#areaCode').attr('data-value');
    var pkPowerstationValue=$('#pkPowerstation').attr('data-value');
    var actChanneltypeValue=$('#actChanneltype').attr('data-value');

    formData.append('actActivityrule',a);
    formData.append('prizeList',prizeList);
    formData.append('actRemark',actRemark);
    formData.append('actActivityname',actActivityname);
    formData.append('actBegindates',startDate);
    formData.append('actEnddates',endDate);
    formData.append('actCouponEndDates',actCouponEndDates);
    formData.append('actTopMoney','');
    formData.append('actActivityrule',actActivityruleValue);
    formData.append('actScope',actScopeValue);
    formData.append('provinceCode',provinceCodeValue);
    formData.append('cityCode',cityCodeValue);
    formData.append('areaCode',areaCodeValue);
    formData.append('pkPowerstation',pkPowerstationValue);
    formData.append('actChanneltype',actChanneltypeValue);
    commonLoad(formData);
}
function setForm2(){
    var formData = new FormData();
    var a=$('#actActivityrule').attr('data-value');
    var prizeList = JSON.stringify(prizeListArr);
    var actActivityname=$('input[name=actActivityname]').val();
    var actRemark=$('input[name=actRemark]').val();
    var startDate=$('input[name=actBegindates]').val();
    var endDate=$('input[name=actEnddates]').val();
    var actCouponEndDates=$('input[name=actCouponEndDates]').val();
    var actTopMoney=$('input[name=actTopMoney]').val();
    var actActivityruleValue=$('#actActivityrule').attr('data-value');
    var actScopeValue=$('#actScope').attr('data-value');
    var provinceCodeValue=$('#provinceCode').attr('data-value');
    var cityCodeValue=$('#cityCode').attr('data-value');
    var areaCodeValue=$('#areaCode').attr('data-value');
    var pkPowerstationValue=$('#pkPowerstation').attr('data-value');
    var actChanneltypeValue=$('#actChanneltype').attr('data-value');

    formData.append('actActivityrule',a);
    formData.append('prizeList',prizeList);
    formData.append('actRemark',actRemark);
    formData.append('actActivityname',actActivityname);
    formData.append('actBegindates',startDate);
    formData.append('actEnddates',endDate);
    formData.append('actCouponEndDates',actCouponEndDates);
    formData.append('actTopMoney',actTopMoney);
    formData.append('actActivityrule',actActivityruleValue);
    formData.append('actScope',actScopeValue);
    formData.append('provinceCode',provinceCodeValue);
    formData.append('cityCode',cityCodeValue);
    formData.append('areaCode',areaCodeValue);
    formData.append('pkPowerstation',pkPowerstationValue);
    formData.append('actChanneltype',actChanneltypeValue);
    commonLoad(formData);

}
function setForm3(){
    var formData = new FormData();
    var a=$('#actActivityrule').attr('data-value');
    var prizeList = JSON.stringify(prizeListArr);
    var actActivityname=$('input[name=actActivityname]').val();
    var actRemark=$('input[name=actRemark]').val();
    var startDate=$('input[name=actBegindates]').val();
    var endDate=$('input[name=actEnddates]').val();
    var actCouponEndDates=$('input[name=actCouponEndDates]').val();
    var actTopMoney=$('input[name=actTopMoney]').val();
    var actActivityruleValue=$('#actActivityrule').attr('data-value');
    var actScopeValue=$('#actScope').attr('data-value');
    var provinceCodeValue=$('#provinceCode').attr('data-value');
    var cityCodeValue=$('#cityCode').attr('data-value');
    var areaCodeValue=$('#areaCode').attr('data-value');
    var pkPowerstationValue=$('#pkPowerstation').attr('data-value');
    var actChanneltypeValue=$('#actChanneltype').attr('data-value');

    formData.append('actActivityrule',a);
    formData.append('prizeList',prizeList);
    formData.append('actRemark',actRemark);
    formData.append('actActivityname',actActivityname);
    formData.append('actBegindates',startDate);
    formData.append('actEnddates',endDate);
    formData.append('actCouponEndDates',actCouponEndDates);
    formData.append('actTopMoney','');
    formData.append('actActivityrule',actActivityruleValue);
    formData.append('actScope',actScopeValue);
    formData.append('provinceCode',provinceCodeValue);
    formData.append('cityCode',cityCodeValue);
    formData.append('areaCode',areaCodeValue);
    formData.append('pkPowerstation',pkPowerstationValue);
    formData.append('actChanneltype',actChanneltypeValue);
    commonLoad(formData);
}
function setForm4(){
    var formData = new FormData();
    var a=$('#actActivityrule').attr('data-value');
    var prizeList = JSON.stringify(prizeListArr);
    var actActivityname=$('input[name=actActivityname]').val();
    var actRemark=$('input[name=actRemark]').val();
    var startDate=$('input[name=actBegindates]').val();
    var endDate=$('input[name=actEnddates]').val();
    var actCouponEndDates=$('#beginTime').val();
    var actTopMoney=$('input[name=actTopMoney]').val();
    var singelMoney=$('input[name=singelMoney]').val();
    var actActivityruleValue=$('#actActivityrule').attr('data-value');
    var actScopeValue=$('#actScope').attr('data-value');
    var provinceCodeValue=$('#provinceCode').attr('data-value');
    var cityCodeValue=$('#cityCode').attr('data-value');
    var areaCodeValue=$('#areaCode').attr('data-value');
    var pkPowerstationValue=$('#pkPowerstation').attr('data-value');
    var actChanneltypeValue=$('#actChanneltype').attr('data-value');

    formData.append('actActivityrule',actActivityruleValue);
    formData.append('prizeList',prizeList);
    formData.append('actRemark',actRemark);
    formData.append('actActivityname',actActivityname);
    formData.append('actBegindates','');
    formData.append('actEnddates','');
    formData.append('actCouponEndDates',actCouponEndDates);
    formData.append('actTopMoney','');

    formData.append("file", $('#file')[0].files[0]);
    formData.append('actScope',actScopeValue);
    formData.append('provinceCode',provinceCodeValue);
    formData.append('cityCode',cityCodeValue);
    formData.append('areaCode',areaCodeValue);
    formData.append('pkPowerstation',pkPowerstationValue);
    formData.append('actChanneltype',actChanneltypeValue);
    commonLoad(formData);
}
function commonLoad(formData){
    $.ajax({
        url: basePath + addUserActivityUrl,
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
                        window.location.href='activity_list.html';
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        window.location.href='activity_list.html';
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
                    time:2000,
                    btn: ["确定"]
                });
            }

        }
    });
}
$('#file').change(function(){
    $('#fileListUl').html($(this).val());
})

//发行方
$('.actChanneltypeUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});
//加载发行方 (修改...)
function getIssuerList() {
    var issuerLi = '';
    $.ajax({
        type: "post",
        url: basePath + initInvestAndEichongCompanyListUrl,
        async: true,
        success: function (data) {
            if (data.success == true) {
                var dataModule = data.dataObject;
                issuerLi = '<li data-option="" class="seleced">请选择</li>';
                for (var i = 0; i < dataModule.length; i++) {
                    issuerLi += '<li data-option="' + dataModule[i].cpyId + '">' + dataModule[i].cpyName + '</li>';
                }
                $('.actChanneltypeUl').html(issuerLi);
                toUnbindEvent();
            }

        }
    });
}

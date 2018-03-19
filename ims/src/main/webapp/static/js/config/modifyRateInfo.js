var rateInfoId = getUrlParam('rateInfoId');
getRateInfoDetail();
//将数据提交到后台处理
function getRateInfoDetail() {
    $.ajax({
        type: "post",
        url: basePath + getRateInfoUrl,
        async: true,
        data: {
            pk_RateInformation: rateInfoId
        },
        success: function (data) {
            data = data.dataObject;
            if(data.raIn_type==3){
                //$('#raIn_ServiceCharge').attr('readonly','readonly');
                //$('#uniformPrice').removeAttr('readonly');
                $('#raIn_ServiceChargeLi').hide();
                $('#uniformPriceLi').show();
                $('#rightDiv').show();
            }else{
                //$('#uniformPrice').attr('readonly','readonly');
                //$('#raIn_ServiceCharge').removeAttr('readonly');
                $('#raIn_ServiceChargeLi').show();
                $('#uniformPriceLi').hide();
                $('#rightDiv').hide();
            }
            $('#raIn_Name').val(data.raIn_Name);
            $('#raIn_ServiceCharge').val(data.raIn_ServiceCharge);
            $('#raIn_TipTimeTariffPrice').val(data.raIn_TipTimeTariffPrice);
            $('#raIn_PeakElectricityPrice').val(data.raIn_PeakElectricityPrice);
            $('#raIn_UsualPrice').val(data.raIn_UsualPrice);
            $('#raIn_ValleyTimePrice').val(data.raIn_ValleyTimePrice);
            $('input[value=' + data.raIn_type + ']').attr('checked', true);
            $('#uniformPrice').val(data.uniformPrice);
            $('#raIn_TipTimeTariffMoney').val(data.raIn_TipTimeTariffMoney);
            $('#raIn_PeakElectricityMoney').val(data.raIn_PeakElectricityMoney);
            $('#raIn_UsualMoney').val(data.raIn_UsualMoney);
            $('#raIn_ValleyTimeMoney').val(data.raIn_ValleyTimeMoney);
            toTestRateInfo();

            var rainQuantumdate = JSON.parse(data.raIn_QuantumDate);
            $.each(rainQuantumdate.data, function (i, item) {
                var data = {
                    sh: parseInt(item.st / 60),
                    sm: item.st % 60,
                    eh: parseInt(item.et / 60),
                    em: item.et % 60,
                    mark: item.mark
                };
                timeListAddRow("rateinfoEditTbody", data);
            })
        }
    });
}
var tempHour = 0;
var tempMinute = 0;
function cleanRow(tableId) {
    tempHour = 0;
    tempMinute = 0;
    $("#" + tableId).html("");
}
function timeListAddRow(tableId, data) {
    //第一行复选框禁用
    var tempType = "";
    var selected = "";
    var indexList = $("#" + tableId + " tr").length;
    var disabled = indexList == 0 ? 'disabled="disabled"' : "";
    var typeOptions = "";
    var shOptions = "", smOptions = "", ehOptions = "", emOptions = "";
    var type = "", sh = 0, sm = 0, eh = 0, em = 0;

    if (data) {
        type = data.mark;
        sh = data.sh;
        sm = data.sm;
        eh = data.eh;
        em = data.em;
    }
    var typeArray = new Array("尖", "峰", "平", "谷");
    for (var i = 1; i <= 4; i++) {
        if (type == i) {
            selected = "selected";
        } else {
            selected = "";
        }
        typeOptions += '<option value="' + i + '" ' + selected + '>' + typeArray[i - 1] + '</option>';
    }
    for (var i = 0; i <= 24; i++) {
        if ((tempHour != 0 && tempHour == i) || sh == i) {
            selected = "selected";
        } else {
            selected = "";
        }
        shOptions += '<option value="' + i + '" ' + selected + '>' + i + '</option>';
    }
    for (var i = 0; i <= 24; i++) {
        if (eh == i) {
            selected = "selected";
        } else {
            selected = "";
        }
        ehOptions += '<option value="' + i + '" ' + selected + '>' + i + '</option>';
    }
    for (var i = 0; i < 60; i++) {
        if ((tempMinute != 0 && tempMinute == i) || sm == i) {
            selected = "selected";
        } else {
            selected = "";
        }
        smOptions += '<option value="' + i + '" ' + selected + '>' + i + '</option>';
    }
    for (var i = 0; i < 60; i++) {
        if (em == i) {
            selected = "selected";
        } else {
            selected = "";
        }
        emOptions += '<option value="' + i + '" ' + selected + '>' + i + '</option>';
    }
    var html = '<tr><td class="smallWidth"><input ' + disabled + ' type="checkbox" name="test" class="selectPer" value=""/></td>'
        + '<td><select name="mark">' + typeOptions + '</select></td>'
        + '<td><select name="starth">' + shOptions + '</select><span>时</span>'
        + '<select name="startm">' + smOptions + '</select><span>分</span></td>'
        + '<td><select name="endh" onchange="setHour(this)">' + ehOptions + '</select><span>时</span>'
        + '<select name="endm" onchange="setMinute(this)">' + emOptions + '</select><span>分</span></td></tr>';
    $("#" + tableId).append(html);
}

function setHour(obj) {
    tempHour = obj.value;
}
function setMinute(obj) {
    tempMinute = obj.value;
}

function formatTimeListToJSON(tableId) {
    var json = "{\"data\":[";
    $("#" + tableId + " tr").each(function (i) {
        var mark = $(this).find("select[name='mark']").val();
        var starth = $(this).find("select[name='starth']").val();
        var startm = $(this).find("select[name='startm']").val();
        var endh = $(this).find("select[name='endh']").val();
        var endm = $(this).find("select[name='endm']").val();
        var st = parseInt(starth) * 60 + parseInt(startm);
        var et = parseInt(endh) * 60 + parseInt(endm);
        json += "{\"st\":" + "\""+st + "\""+ ",\"et\":"+ "\"" + et + "\""+ ",\"mark\":" + "\"" + mark + "\"},";
    })
    json = json.substring(0, json.length - 1);
    json += "]}";
    return json;
}

$("#timeListEditBtn").off("click").on("click", function () {
    $(".rateInfoEditTipText").html("").hide();
    timeListAddRow("rateinfoEditTbody");
})

$("#timeListEditDeleteBtn").off("click").on("click", function () {
    timeListEditDelete();
})
function timeListEditDelete() {
    if ($("#rateinfoEditTbody tr").length == 1) {
        $(".rateInfoEditTipText").html("第一行不能删除").show();
        return;
    }
    if ($("#rateinfoEditTbody tr").length > 1) {
        if ($("#rateinfoEditTbody tr").length == $("input[name='rainSelect']:checked").length) {
            //			alert($("input[name='test']:checked").length);
            $("#rateinfoEditTbody tr:not(:first)").remove();
            $(".rateInfoEditTipText").html("第一行不能删除").show();
        } else {
            $("input[name='test']:checked", "#rateinfoEditTbody").each(function () { // 遍历选中的checkbox
                n = $(this).parents("tr").index() + 1; // 获取checkbox所在行的顺序，多加1行是因为标题
                $("table#rateInfoEditTable").find("tr:eq(" + n + ")").remove();
            });
        }

    }
    return true;
}
function testTimeEdit() {
    var firstEndH = "";
    var firstEndM = "";
    var firstEndHT = "";
    var firstEndMT = "";
    var secondStartH = "";
    var secondStartM = "";
    var boo = 1;
    $("#rateinfoEditTbody").find("tr").each(function (index) {
        //遍历每行的每个select
        //	alert($("#rateinfoTbody tr").length)
        $("select", this).each(function (cindex) {
            var name = $(this).attr("name");
            //		alert(cindex);
            //将每行的开始时间存放起来
            if (name == "starth") {
                secondStartH = $(this).val();
            } else if (name == "startm") {
                secondStartM = $(this).val();
            } else if (name == "endh") {
                firstEndH = $(this).val();
            } else if (name == "endm") {
                firstEndM = $(this).val();
            }
        });

        //第一行的开始时间必须从0点开始
        if (index == 0) {
            if (secondStartH != 0 || secondStartM != 0) {
                //			alert("费率的起始时间必须从每天0点开始，请重新设置");
                $(".rateInfoEditTipText").html("费率的起始时间必须从每天0点开始，请重新设置").show();
                boo = 0;
                return false;
            }
        }
        if ((parseInt(secondStartH) * 60 + parseInt(secondStartM)) >= (parseInt(firstEndH) * 60 + parseInt(firstEndM))) {
            //alert(((secondStartH * 60 + secondStartM) >= (firstEndH * 60 + firstEndM)) + "||" + (secondStartH * 60 + secondStartM) + "--" + (firstEndH * 60 + firstEndM));
            //		alert("时间段设置--同行的结束时间必须大于开始时间，请重新设置");
            $(".rateInfoEditTipText").html("时间段设置--同行的结束时间必须大于开始时间，请重新设置").show();
            boo = 0;
            return false;
        }
        //在有多行的时候再开始判断
        if (index > 0) {
            //alert(secondStartH + ":" + secondStartM + "--" + firstEndHT + ":" + firstEndMT);
            //上一次的结束时间不等于这一次的开始时间的话给出提示
            if (secondStartH != firstEndHT || secondStartM != firstEndMT) {
                //			alert("时间段设置--上一行的结束时间必需与下一行的开始时间相一致，请重新设置");
                $(".rateInfoEditTipText").html("时间段设置--上一行的结束时间必需与下一行的开始时间相一致，请重新设置").show();
                boo = 0;
                return false;
            }
            firstEndHT = "";
            firstEndMT = "";
        }
        //将本行最后的时间保存下来，用来与下一行的开始时间比对
        firstEndHT = firstEndH;
        firstEndMT = firstEndM;
        //费率的结束时间必须是每天24点
    });
    if (firstEndH != "24") {
        //	alert("费率的最终结束时间必须为每天的24点，请重新设置");
        $(".rateInfoEditTipText").html("费率的最终结束时间必须为每天的24点，请重新设置").show();
        boo = 0;
        return false;
    }
    if (boo == 0) {
        returnValue = false;
        return false;
    } else
        return true;
}
//新建名称
$('body').off('blur', '#raIn_Name').on('blur', '#raIn_Name', function () {
    var raInNameValue=$('#raIn_Name').val();
    var reg=/^[a-zA-Z0-9\u4e00-\u9fa5]+$/;
    if(!raInNameValue){
        $('#raIn_Name').val('');
        layer.tips('费率名称不能为空，请输入正确的费率名称,最多30字符！', '#raIn_Name', {
            tips: 4
        });
    }else if(!reg.test(raInNameValue)){
        $('#raIn_Name').val('');
        layer.tips('请输入正确的费率名称,可以由数字、字母或中文组成,最多30字符！', '#raIn_Name', {
            tips: 4
        });
    }
    if(raInNameValue.length>30){
        $('#raIn_Name').val('');
        layer.tips('请输入正确的费率名称,可以由数字、字母或中文组成,最多30字符！', '#raIn_Name', {
            tips: 4
        });
    }
});
//校验统一价的位数
$('body').off('focus', '#uniformPrice').on('focus', '#uniformPrice', function () {
    $('#raIn_TipTimeTariffPrice').val('');
    $('#raIn_PeakElectricityPrice').val('');
    $('#raIn_UsualPrice').val('');
    $('#raIn_ValleyTimePrice').val('');
    $('#raIn_TipTimeTariffMoney').val('');
    $('#raIn_PeakElectricityMoney').val('');
    $('#raIn_UsualMoney').val('');
    $('#raIn_ValleyTimeMoney').val('');
})
$('body').off('blur', '#uniformPrice').on('blur', '#uniformPrice', function () {
    var uniformPriceValue=$('#uniformPrice').val();
    var reg=/^([0-9]{0,})+\.[0-9]{4}$/;
    if(uniformPriceValue){
        if(!reg.test(uniformPriceValue)){
            $('#uniformPrice').val('');
            layer.tips('请输入正确的统一价,保留小数点后四位！', '#uniformPrice', {
                tips: 4
            });
        }else if(parseFloat(uniformPriceValue)>=100){
            $('#uniformPrice').val('');
            layer.tips('请输入正确的统一价,最大不能超过100！', '#uniformPrice', {
                tips: 4
            });
        }
        $('#raIn_TipTimeTariffMoney').attr('readonly','readonly');
        $('#raIn_PeakElectricityMoney').attr('readonly','readonly');
        $('#raIn_UsualMoney').attr('readonly','readonly');
        $('#raIn_ValleyTimeMoney').attr('readonly','readonly');
    }else if(!uniformPriceValue){
        $('#raIn_TipTimeTariffMoney').removeAttr('readonly');
        $('#raIn_PeakElectricityMoney').removeAttr('readonly');
        $('#raIn_UsualMoney').removeAttr('readonly');
        $('#raIn_ValleyTimeMoney').removeAttr('readonly');
        testJFPGMoney($('#raIn_TipTimeTariffMoney'),'#raIn_TipTimeTariffMoney');
        testJFPGMoney($('#raIn_PeakElectricityMoney'),'#raIn_PeakElectricityMoney');
        testJFPGMoney($('#raIn_UsualMoney'),'#raIn_UsualMoney');
        testJFPGMoney($('#raIn_ValleyTimeMoney'),'#raIn_ValleyTimeMoney');
    }
});
//输入校验
testInput();
function testInput(){
    var typeValue = $("input[name='raIn_type']:checked").val();
    if(typeValue==3){
        testJFPGPrice($('#raIn_TipTimeTariffPrice'),'#raIn_TipTimeTariffPrice',$('#raIn_TipTimeTariffMoney'));
        testJFPGPrice($('#raIn_PeakElectricityPrice'),'#raIn_PeakElectricityPrice',$('#raIn_PeakElectricityMoney'));
        testJFPGPrice($('#raIn_UsualPrice'),'#raIn_UsualPrice',$('#raIn_UsualMoney'));
        testJFPGPrice($('#raIn_ValleyTimePrice'),'#raIn_ValleyTimePrice',$('#raIn_ValleyTimeMoney'));
    }
}
//位数选择
$('body').off('click',"input[name='raIn_type']").on('click',"input[name='raIn_type']",function(){
    $('#raIn_ServiceCharge').val('');
    $('#raIn_TipTimeTariffPrice').val('');
    $('#raIn_PeakElectricityPrice').val('');
    $('#raIn_UsualPrice').val('');
    $('#raIn_ValleyTimePrice').val('');
    $('#raIn_TipTimeTariffMoney').val('');
    $('#raIn_PeakElectricityMoney').val('');
    $('#raIn_UsualMoney').val('');
    $('#raIn_ValleyTimeMoney').val('');
    toTestRateInfo();
})

//校验费率内容
function toTestRateInfo(){
    var typeValue = $("input[name='raIn_type']:checked").val();
    if(typeValue==3){
        $('#raIn_ServiceChargeLi').hide();
        $('#uniformPriceLi').show();
        $('#rightDiv').show();
        $('body').off('blur', '#uniformPrice').on('blur', '#uniformPrice', function () {
            var uniformPriceValue=$('#uniformPrice').val();
            var reg=/^([0-9]{0,})+\.[0-9]{4}$/;
            if(uniformPriceValue){
                if(!reg.test(uniformPriceValue)){
                    $('#uniformPrice').val('');
                    layer.tips('请输入正确的统一价,保留小数点后四位！', '#uniformPrice', {
                        tips: 4
                    });
                }else if(parseFloat(uniformPriceValue)>=100){
                    $('#uniformPrice').val('');
                    layer.tips('请输入正确的统一价,最大不能超过100！', '#uniformPrice', {
                        tips: 4
                    });
                }
                $('#raIn_TipTimeTariffMoney').attr('readonly','readonly');
                $('#raIn_PeakElectricityMoney').attr('readonly','readonly');
                $('#raIn_UsualMoney').attr('readonly','readonly');
                $('#raIn_ValleyTimeMoney').attr('readonly','readonly');
                testJFPGPrice($('#raIn_TipTimeTariffPrice'),'#raIn_TipTimeTariffPrice',$('#raIn_TipTimeTariffMoney'));
                testJFPGPrice($('#raIn_PeakElectricityPrice'),'#raIn_PeakElectricityPrice',$('#raIn_PeakElectricityMoney'));
                testJFPGPrice($('#raIn_UsualPrice'),'#raIn_UsualPrice',$('#raIn_UsualMoney'));
                testJFPGPrice($('#raIn_ValleyTimePrice'),'#raIn_ValleyTimePrice',$('#raIn_ValleyTimeMoney'));
            }else if(!uniformPriceValue){
                $('#raIn_TipTimeTariffMoney').removeAttr('readonly');
                $('#raIn_PeakElectricityMoney').removeAttr('readonly');
                $('#raIn_UsualMoney').removeAttr('readonly');
                $('#raIn_ValleyTimeMoney').removeAttr('readonly');
                testJFPGMoney($('#raIn_TipTimeTariffMoney'),'#raIn_TipTimeTariffMoney');
                testJFPGMoney($('#raIn_PeakElectricityMoney'),'#raIn_PeakElectricityMoney');
                testJFPGMoney($('#raIn_UsualMoney'),'#raIn_UsualMoney');
                testJFPGMoney($('#raIn_ValleyTimeMoney'),'#raIn_ValleyTimeMoney');
            }
        });
    }
    if(typeValue==1){
        $('#raIn_ServiceChargeLi').show();
        $('#uniformPriceLi').hide();
        $('#rightDiv').hide();
        testJFPGPriceNumTwo($('#raIn_ServiceCharge'),'#raIn_ServiceCharge');
        testJFPGPriceNumTwo($('#raIn_TipTimeTariffPrice'),'#raIn_TipTimeTariffPrice');
        testJFPGPriceNumTwo($('#raIn_PeakElectricityPrice'),'#raIn_PeakElectricityPrice');
        testJFPGPriceNumTwo($('#raIn_UsualPrice'),'#raIn_UsualPrice');
        testJFPGPriceNumTwo($('#raIn_ValleyTimePrice'),'#raIn_ValleyTimePrice');
    }
    if(typeValue==2){
        $('#raIn_ServiceChargeLi').show();
        $('#uniformPriceLi').hide();
        $('#rightDiv').hide();
        testJFPGPriceNumFour($('#raIn_ServiceCharge'),'#raIn_ServiceCharge');
        testJFPGPriceNumFour($('#raIn_TipTimeTariffPrice'),'#raIn_TipTimeTariffPrice');
        testJFPGPriceNumFour($('#raIn_PeakElectricityPrice'),'#raIn_PeakElectricityPrice');
        testJFPGPriceNumFour($('#raIn_UsualPrice'),'#raIn_UsualPrice');
        testJFPGPriceNumFour($('#raIn_ValleyTimePrice'),'#raIn_ValleyTimePrice');
    }
}

//选择新账户后的尖峰平谷电价
function testJFPGPrice (obj,ids,obj2){
    obj.off('blur').on('blur',function(){
        var objValue = obj.val();
        var reg=/^([0-9]{0,})+\.[0-9]{4}$/;
        if(!objValue){
            obj.val('');
            layer.tips('请输入正确的价格,保留小数点后四位！', ids, {
                tips: 4
            });
        }
        if(!reg.test(objValue)){
            obj.val('');
            layer.tips('请输入正确的价格,保留小数点后四位！', ids, {
                tips: 4
            });
        }
        if(objValue>=100){
            obj.val('');
            layer.tips('请输入正确的价格,最大不能超过100！', ids, {
                tips: 4
            });
        }
        var uniformPriceValue=$('#uniformPrice').val();
        if(parseFloat(uniformPriceValue)-parseFloat(obj.val())<0){
            layer.tips('当时段电价不能大于统一价', obj, {
                tips: 4
            });
            obj.val('');
            obj.focus();
            return false;
        }else{
            var a = parseFloat(uniformPriceValue - obj.val()).toFixed(4);
            obj2.val(a);
            return true;
        }


    })
}
//新账户的尖峰平谷服务费
function testJFPGMoney (obj,ids){
    obj.off('blur').on('blur',function(){
        var objValue = obj.val();
        var re=/^([0-9]{0,})+\.[0-9]{4}$/;
        if(!objValue){
            obj.val('');
            layer.tips('请输入服务费,保留小数点后四位！', ids, {
                tips: 4
            });
        }
        if(!re.test(objValue)){
            obj.val('');
            layer.tips('请输入正确的服务费,保留小数点后四位！', ids, {
                tips: 4
            });
        }
        if(objValue>=100){
            obj.val('');
            layer.tips('请输入正确的服务费,最大不能超过100！', ids, {
                tips: 4
            });
        }
    })
}
//2位的尖峰平谷电价
function testJFPGPriceNumTwo (obj,ids){
    obj.off('blur').on('blur',function(){
        var objaValue = obj.val();
        var regt=/^([0-9]{0,})+\.[0-9]{2}$/;
        if(!objaValue){
            obj.val('').focus();
            layer.tips('请输入价格,保留小数点后两位！', ids, {
                tips: 4
            });
            return false;
        }
        if(!regt.test(objaValue)){
            obj.val('').focus();
            layer.tips('请输入正确的价格,保留小数点后两位！', ids, {
                tips: 4
            });
            return false;
        }
        if(objaValue>=100){
            obj.val('').focus();
            layer.tips('请输入正确的价格,最大不能超过100！', ids, {
                tips: 4
            });
            return false;
        }
    })
    return true;
}
//4位的尖峰平谷电价
function testJFPGPriceNumFour (obj,ids){
    obj.off('blur').on('blur',function(){
        var objValue = obj.val();
        var regf=/^([0-9]{0,})+\.[0-9]{4}$/;
        if(!objValue){
            obj.val('');
            layer.tips('请输入价格,保留小数点后四位！', ids, {
                tips: 4
            });
        }
        if(!regf.test(objValue)){
            obj.val('');
            layer.tips('请输入正确的价格,保留小数点后四位！', ids, {
                tips: 4
            });
        }
        if(objValue>=100){
            obj.val('');
            layer.tips('请输入正确的价格,最大不能超过100！', ids, {
                tips: 4
            });
        }
    })
}
//将数据提交到后台处理
function saveRateInfo() {
    var raIn_Name = $('#raIn_Name').val();
    var raIn_ServiceCharge = $('#raIn_ServiceCharge').val();
    var raIn_TipTimeTariffPrice = $('#raIn_TipTimeTariffPrice').val();
    var raIn_PeakElectricityPrice = $('#raIn_PeakElectricityPrice').val();
    var raIn_UsualPrice = $('#raIn_UsualPrice').val();
    var raIn_ValleyTimePrice = $('#raIn_ValleyTimePrice').val();
    var raIn_type = $("input[name='raIn_type']:checked").val();
    var uniformPrice = $('#uniformPrice').val();
    var raIn_TipTimeTariffMoney = $('#raIn_TipTimeTariffMoney').val();
    var raIn_PeakElectricityMoney = $('#raIn_PeakElectricityMoney').val();
    var raIn_UsualMoney = $('#raIn_UsualMoney').val();
    var raIn_ValleyTimeMoney = $('#raIn_ValleyTimeMoney').val();
    var raIn_QuantumDate = formatTimeListToJSON('rateinfoEditTbody');
    var et = JSON.parse(raIn_QuantumDate);
    var data = et.data;
    var flag=false;
    for (var i = 0; i < data.length; i++) {
        if (data[0].et > 1440) {
            layer.closeAll();
            flag=true;
            layer.open({
                type: 1,
                offset: '100px',
                title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                shadeClose: false,
                closeBtn: 1,
                area: ['310px', '160px'],//宽高
                content: '时间不能超过24时',
                time:3000,
                btn: ["确定"]

            });
        }
    }
    if(flag==true){
        return;
    }
    var obj = {
        pk_RateInformation: rateInfoId,
        raIn_Name: raIn_Name,
        raIn_TipTimeTariffPrice: raIn_TipTimeTariffPrice,
        raIn_PeakElectricityPrice: raIn_PeakElectricityPrice,
        raIn_UsualPrice: raIn_UsualPrice,
        raIn_ValleyTimePrice: raIn_ValleyTimePrice,
        raIn_type: raIn_type,
        uniformPrice: uniformPrice,
        raIn_ServiceCharge:raIn_ServiceCharge,
        raIn_TipTimeTariffMoney: raIn_TipTimeTariffMoney,
        raIn_PeakElectricityMoney: raIn_PeakElectricityMoney,
        raIn_UsualMoney: raIn_UsualMoney,
        raIn_ValleyTimeMoney: raIn_ValleyTimeMoney,
        raIn_QuantumDate: raIn_QuantumDate
    };
    var typeValue = $("input[name='raIn_type']:checked").val();
    if(!raIn_Name){
        $('#raIn_Name').focus();
        layer.tips('请输入费率名称', '#raIn_Name', {
            tips: 4
        });
        return false;
    }
    if(!raIn_TipTimeTariffPrice){
        $('#raIn_TipTimeTariffPrice').focus();
        return false;
    }
    if(!raIn_PeakElectricityPrice){
        $('#raIn_PeakElectricityPrice').focus();
        return false;
    }
    if(!raIn_UsualPrice){
        $('#raIn_UsualPrice').focus();
        return false;
    }
    if(!raIn_ValleyTimePrice){
        $('#raIn_ValleyTimePrice').focus();
        return false;
    }
    if(typeValue==3){
        if(!raIn_TipTimeTariffMoney){
            $('#raIn_TipTimeTariffMoney').focus();
            return false;
        }
        if(!raIn_PeakElectricityMoney){
            $('#raIn_PeakElectricityMoney').focus();
            return false;
        }
        if(!raIn_UsualMoney){
            $('#raIn_UsualMoney').focus();
            return false;
        }
        if(!raIn_ValleyTimeMoney){
            $('#raIn_ValleyTimeMoney').focus();
            return false;
        }
    }else{
        if(!raIn_ServiceCharge){
            $('#raIn_ServiceCharge').focus();
            return false;
        }
    }
    toSave(obj);
}
function toSave (obj){
    if(testTimeEdit()){
        $.ajax({
            type: "post",
            url: basePath + modifyRateInfoUrl,
            async: true,
            data: obj,
            success: function (data) {
                if (data.success == true) {
                    window.location.href = 'rateInfo.html';
                } else if(data.status == 9001) {
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

                }else {
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
//返回列表页
$('body').on('click', '#goback', function () {
    //window.location.href = 'rateInfoDetail.html?rateInfoId=' + rateInfoId;
    window.location.href = 'rateInfo.html';
});
/**
 * Created by Administrator on 2017/10/27 0027.
 */
var loadArr,
    special_check = 0;
//初始渲染
$(function() {
    var zNodes = '',
        treeObj = '';
    //-------------------
    var data = {
        pkId: 2,
        integralRulesId: getUrlParam('integralRulesId')
    };
    $.ajax({
        url: basePath + getIntegralActivityAndRulesListUrl,
        type: "post",
        dataType: 'json',
        data: data,
        success: function(data) {
            //预处理选中的地区
            if(data.dataObject[0].isWhole == 1){
                $('input[name="isWhole"]').attr("checked", 'checked').val('1');
                $('#treeDemo').css('display','none');
            }
            getZNodes();
            var load = '';
            for(var k=0;k<(data.dataObject).length;k++){
                load = data.dataObject[k].provinceId+','+data.dataObject[k].cityId+','+data.dataObject[k].powerStationId+','+data.dataObject[k].electricPileId;
                loadArr += load + ';';
            }
            loadArr = loadArr.substring(0, loadArr.length - 1);
            //定义
            var checkedData = data.dataObject[0];
            //状态
            if (checkedData.activityStatus == 0) {
                $('input[name="state"]:eq(0)').attr("checked", 'checked');
            } else if (checkedData.activityStatus == 1) {
                $('input[name="state"]:eq(1)').attr("checked", 'checked');
                $('#datePicker,input[name="weight"],input[name="RechargeThreshold"],input[name="RechargeChoice"],input[name="fixed-score"],input[name="proportion"],input[name="rechargeAmount"],input[name="money"],input[name="isWhole"]').attr("disabled", true);
            }
            //规则权重
            if (checkedData.highestPriority == 1) {
                $('input[name="weight"]').attr("checked", 'checked');
                $('input[name="weight"]').val(1);
            } else {
                $('input[name="weight"]').val(0);
            }
            //阈值设置
            $('input[name="RechargeThreshold"]').val(checkedData.minValue);
            //时间选择
            var startday = new Date(checkedData.startDate.time).format("yyyy-MM-dd"),
                endday = new Date(checkedData.endDate.time).format("yyyy-MM-dd");
            $('#datePicker').val(startday + ' - ' + endday);
            //固定分值和按比例判断
            if (checkedData.fixedIntegralValue != 0) {
                $('input[name="RechargeChoice"]:eq(0)').attr("checked", 'checked');
                $('input[name="RechargeChoice"]:eq(1)').attr("checked", false);
                $('input[name="fixed-score"]').val(checkedData.fixedIntegralValue);
            } else {
                $('input[name="RechargeChoice"]:eq(1)').attr("checked", 'checked');
                $('input[name="RechargeChoice"]:eq(0)').attr("checked", false);
                $('input[name="proportion"]').val(checkedData.ratioIntegralValue);
            }
            //抽奖机会
            if (checkedData.isChoice == 0) {
                $('input[name="rechargeAmount"]').attr("checked", 'checked').val(0);
            } else if (checkedData.isChoice == 1) {
                $('input[name="rechargeAmount"]').attr("checked", false).val(1);
            }
            if ($('input[name="rechargeAmount"]').is(":checked") == true) {
                $('input[name="money"]').attr('disabled', false);
                $('input[name="rechargeAmount"]').val('0');
            } else {
                $('input[name="money"]').attr('disabled', true);
                $('input[name="rechargeAmount"]').val('1');
            }
            //金额
            if (checkedData.choiceMoney != '') {
                $('input[name="money"]').val(checkedData.choiceMoney);
            } else {
                $('input[name="money"]').val('');
            }
            //积分上限
            $('input[name="recharge-max"]').val(checkedData.limitIntegral);
        }
    });
    //--------------------
    $('#saveBtn').bind("click", foo);
});

//日期选择
laydate.render({
    elem: '#datePicker',
    range: true,
    theme: '#ff7d00'
    /* ,min: -90,
     max:0//0 代表今天 -1代表昨天，-2代表前天，以此类推*/
});

//规则权重
$('input[name="weight"]').click(function() {
    $(this).val('1');
});

//错误提示
function layerCase(msg){
    layer.open({
        type: 1,
        offset: '100px',
        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '160px'],
        //宽高
        content: msg,
        time: 0,
        btn: ["确定"],
        btn1: function(index, layero) {
            layer.closeAll();
        },
        cancel: function(index, layero) {
            layer.closeAll();
        }
    });
}

//充值赠送
$('.rechargeAmount').click(function() {
    if ($('input[name="rechargeAmount"]').is(":checked") == true) {
        $('input[name="money"]').attr('disabled', false);
        $('input[name="rechargeAmount"]').val('0');
    } else {
        $('input[name="money"]').attr('disabled', true);
        $('input[name="rechargeAmount"]').val('1');
    }
});

//分值比例切换
$('input[name="RechargeChoice"]').on('click',function(){
    if($(this).val() == 1){
        $('input[name="proportion"]').val('1');
    }else if($(this).val() == 2){
        $('input[name="fixed-score"]').val('');
    }
});

//返回按钮
$('body').off('click', '#toPowerStationList').on('click', '#toPowerStationList', function() {
    window.history.back();
});

//关闭选项
$('input[name="state"]').on('click', function() {
    var target = $(this).val();
    if (target == '1') {
        $('#datePicker,input[name="weight"],input[name="RechargeThreshold"],input[name="RechargeChoice"],input[name="fixed-score"],input[name="proportion"],input[name="rechargeAmount"],input[name="money"],input[name="isWhole"],input[name="recharge-max"]').attr("disabled", true);
    } else {
        $('#datePicker,input[name="weight"],input[name="RechargeThreshold"],input[name="RechargeChoice"],input[name="fixed-score"],input[name="proportion"],input[name="rechargeAmount"],input[name="money"],input[name="recharge-max"]').attr("disabled", false);
        $('#saveBtn').bind("click", foo);
        if ($('input[name="rechargeAmount"]').is(":checked") == true) {
            $('input[name="money"]').attr('disabled', false);
        } else {
            $('input[name="money"]').attr('disabled', true);
        }
    }
});

//限制数字和两位小数
function num(obj) {
    obj.value = obj.value.replace(/[^\d.]/g, ""); //清除"数字"和"."以外的字符
    obj.value = obj.value.replace(/^\./g, ""); //验证第一个字符是数字
    obj.value = obj.value.replace(/\.{2,}/g, "."); //只保留第一个, 清除多余的
    obj.value = obj.value.replace(".", "$#$").replace(/\./g, "").replace("$#$", ".");
    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/, '$1$2.$3'); //只能输入两个小数
}

//按比例提示
function ProportionaAvice(obj) {
    if (obj.value != 1) {
        layerCase('比例变化会导致积分贬值或增值，请谨慎操作。推荐为1：1');
    }
}

//阈值提示
function ThresholdAdvice(obj) {
    if (obj.value <= 0) {
        layerCase('<1的金额会引发恶意刷单、损伤充电桩，请重新输入');
        $('input[name="RechargeThreshold"]').val('1');
    } else if (obj.value >= 10) {
        layerCase('过高的消费金额阈值会减少用户获取的积分，降低用户体验，请谨慎设置');
    }
}

//---------------------地区查询------------------------------
toLoadProvince('', '#provinceCode', '.provinceUl', 'toUnbindEvent'); //key

function toUnbindEvent() {
    $('.provinceBlock,.cityBlock,.userStatusBlock,.cpyProvinceBlock,.cypCityBlock,.userStatusBlock').unbind();
    $('.cpyCompanyBlock,.levelBlock,.tagBlock').unbind();
    selectModel();
}

//抓取省
$('.provinceUl').on("click", "li", function() {
    $('#cityCode').html('请选择');
    $('#cityCode').attr('data-value', '');
    $('.cityUl').html('');
    $('input[name=cityCode]').val('');
    var provinceCodeId = $(this).attr('data-option');
    $(this).parent().siblings('div.model-select-text').text($(this).text()).attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');
    if (flag == "") {
        $('#cityCode').html('请选择');
        $('#cityCode').attr('data-value', '');
        $('.cityUl').html('');
        $('input[name=cityCode]').val('');
    } else {
        toLoadCity(provinceCodeId, '', '#cityCode', '.cityUl', 'toUnbindEvent');
    }
});

//抓取市
$('.cityUl').on("click", "li", function() {
    $(this).parent().siblings('div.model-select-text').text($(this).text()).attr('data-value', $(this).attr('data-option'));
});
//-----------------------------------------------------------------------

//固定分值，抽奖机会取整
$(document).on({
    keyup: function() {
        if (this.value.length == 1) {
            this.value = this.value.replace(/[^1-9]/g, '')
        } else {
            this.value = this.value.replace(/\D/g, '')
        }
    },
    afterpaste: function() {
        if (this.value.length == 1) {
            this.value = this.value.replace(/[^1-9]/g, '0')
        } else {
            this.value = this.value.replace(/\D/g, '')
        }
    },
    blur: function() {
        switch (this.name) {
            case 'fixed-score':
                if (this.value < 100) {
                    layerCase('<100的减少用户获取的积分，降低用户体验，请谨慎设置');
                } else if (this.value > 100) {
                    layerCase('用户消费时获取太多积分，可能导致损失，请谨慎操作');
                }
                break;
            case 'money':
                if (this.value < 25) {
                    layerCase('数值过小，用户消费时可能会获取多次抽奖机会，请谨慎操作');
                } else if (this.value > 25) {
                    layerCase('数值过大，用户消费时可能无法获得抽奖机会，请谨慎操作');
                }
                break;
            case 'recharge-max':
                if(this.value == '') {
                    layerCase('单笔订单积分上限不能为空');
                    $('input[name="recharge-max"]').val('1');
                } else if (this.value < 100) {
                    layerCase('数值过小，会减少用户获取的积分，降低用户体验，请谨慎操作');
                } else if (this.value > 100) {
                    layerCase('数值过大，用户会获取过多积分，可能导致损失，请谨慎操作');
                }
                break;
        }

    }
}, 'input[name="fixed-score"],input[name="money"],input[name="recharge-max"]');

//定义数据类型
var setting = {
    async: {
        enable: true
    },
    check: {
        enable: true,
        chkboxType: {
            "Y": "ps",
            "N": "ps"
        }
    },
    data: {
        simpleData: {
            enable: true
        },
        check:{
            chkDisabledInherit: true,
            enable:true
        },
        key: {
            checked: "isSelected",
            children: "list",
            name: "name",
            title: "id"
        }
    },
    view:{
        dblClickExpand: false,
        selectedMulti: false
    },
    callback: {
        onCheck: zTreeOnCheck
    }
};

function zTreeOnCheck(event, treeId, treeNode) {
    special_check = 1;

    var treeObj = $.fn.zTree.getZTreeObj("treeDemo"),
        nodes = (treeObj.getCheckedNodes(true));
    var ff = [];
    for(var i=0;i<nodes.length;i++){
        ff.push(nodes[i].level)
    }
    //获取第四层
    function filter(node) {
        return (node.level == 3 && node.remark != 'undefined' && node.isSelected == true);
    }
    var forthArr = treeObj.getNodesByFilter(filter),
        pd_arr = '';
    for(var j=0;j<forthArr.length;j++){
        pd_arr += forthArr[j].remark+';';
        //PileArr.push(forthArr[j].remark)
    }
    pd_arr = pd_arr.substring(0, pd_arr.length - 1);
    //存在的数量
    var map = new Array();
    for(var i = 0; i < ff.length; i++){
        var ai = ff[i];
        if(!map[ai]){
            map[ai] = 1;
        }else{
            map[ai]++;
        }
    }
    //if(map[0] > 1){
    //    treeObj.checkNode(treeNode, false, true);
    //    layerCase('无法选择多个省');
    //    return false;
    //}
    return pd_arr;
};

function getZNodes() {
    $.ajax({
        type: "post",
        url: basePath + integralGetStationAndPileUrl,
        async: true,
        data: {
            pkId: 2,
            integralRulesId: getUrlParam('integralRulesId')
        },
        success: function(req) {
            zNodes = req.dataObject.list;
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        }
    });
}

//站点选择
$('input[name="isWhole"]').on('click',function(){
    if($(this).is(':checked') == true){
        $(this).val('1');
        $('#treeDemo').css('display','none');
    }else{
        $(this).val('0');
        $('#treeDemo').css('display','block');
    }
});

//提交数据
var foo = function(){
    //判断初始省市站桩
    if(special_check == 0){
        var pile_arr = loadArr;
    }else if(special_check == 1){
        var pile_arr = zTreeOnCheck();
    }
    //电桩编号
    var day = $('#datePicker').val(),
        recharge_choice = $('input[name="RechargeChoice"]:radio:checked').val(); //判断固定分值和按比例的条件
    var data_one = 2,
    //活动名称Id
        data_two = $('input[name="state"]:radio:checked').val(),
    //活动状态
        data_three = '消费赠送',
    //活动名称
        data_seven = $('input[name="weight"]').val(),
    //最高优先级
        data_eight = getUrlParam('integralRulesId'),
    //活动规则Id
        data_nine = 0,
    //积分变化方向
        data_ten = $('input[name="RechargeThreshold"]').val(),
    //充电消费满足最小金额
        data_eleven = $('input[name="fixed-score"]').val(),
    //固定分值
        data_twelve = $('input[name="proportion"]').val(),
    //比例分值
        data_thirteen = $('input[name="rechargeAmount"]').val(),
    //按照消费金额赠送抽奖机会
        data_forteen = $('input[name="money"]').val(),
    //每消费多少金额赠送一次抽奖机会
        data_fifiteen = slice_date(day)[0],
    //活动开始时间
        data_sixteen = slice_date(day)[1],
    //活动结束时间
        data_seventeen = $('input[name="recharge-max"]').val();
    //单笔订单积分上限

    //判断固定分值和按比例
    if (recharge_choice == 1) {
        var data = '';
        //判断全国
        if($('input[name="isWhole"]').val() == 0){
            if (data_ten == '') {
                layerCase('阈值不能为空');
                return false;
            }else if(day == ''){
                layerCase('活动时间不能为空');
                return false;
            }else if(data_eleven == ''){
                layerCase('固定分值不能为空');
                return false;
            }else if(pile_arr == ''){
                layerCase('请选择站点');
                return false;
            }
            data = {
                'pkId': data_one,
                'activityStatus': data_two,
                'activityName': data_three,
                'highestPriority': data_seven,
                'integralRulesId': data_eight,
                'direction': data_nine,
                'minValue': data_ten,
                'fixedIntegralValue': data_eleven,
                'isChoice': data_thirteen,
                'choiceMoney': data_forteen,
                'strStartDate': data_fifiteen,
                'strEndDate': data_sixteen,
                'electricPileIds': pile_arr,
                'limitIntegral':data_seventeen,
                'isWhole': 0
            }
        }else{
            if (data_ten == '') {
                layerCase('阈值不能为空');
                return false;
            }else if(day == ''){
                layerCase('活动时间不能为空');
                return false;
            }else if(data_eleven == ''){
                layerCase('固定分值不能为空');
                return false;
            }
            data = {
                'pkId': data_one,
                'activityStatus': data_two,
                'activityName': data_three,
                'highestPriority': data_seven,
                'integralRulesId': data_eight,
                'direction': data_nine,
                'minValue': data_ten,
                'fixedIntegralValue': data_eleven,
                'isChoice': data_thirteen,
                'choiceMoney': data_forteen,
                'strStartDate': data_fifiteen,
                'strEndDate': data_sixteen,
                'limitIntegral':data_seventeen,
                'isWhole': 1
            }
        }

        $.ajax({
            url: basePath + modifyIntegralActivityUrl,
            type: "post",
            dataType: 'json',
            data: data,
            success: function(data) {
                if (data.status == 1000) {
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '160px'],
                        //宽高
                        content: '提交成功',
                        btn: ["确定"],
                        yes: function(index, layero) {
                            layer.closeAll();
                            window.history.back();
                        },
                        cancel: function(index, layero) {
                            layer.closeAll();
                            window.history.back();
                        }
                    });
                } else if (data.status == 2001){
                    layer.closeAll();
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '160px'],
                        //宽高
                        content: data.msg,
                        btn: ["确定"],
                        yes: function(index, layero) {
                            layer.closeAll();
                        },
                        cancel: function(index, layero) {
                            layer.closeAll();
                        }
                    });
                    return false;
                } else if (data.status == 9001) {
                    layer.closeAll();
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '160px'],
                        //宽高
                        content: '会话超时，请重新登陆！',
                        btn: ["确定"],
                        yes: function(index, layero) {
                            layer.closeAll();
                            window.top.location.href = basePath + toLoginUrl;
                        },
                        cancel: function(index, layero) {
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
                        area: ['310px', '160px'],
                        //宽高
                        content: '系统出错',
                        btn: ["确定"],
                        yes: function(index, layero) {
                            layer.closeAll();
                            window.top.location.href = basePath + toLoginUrl;
                        },
                        cancel: function(index, layero) {
                            layer.closeAll();
                            window.top.location.href = basePath + toLoginUrl;
                        }
                    });
                }
            }
        })
    } else if (recharge_choice == 2) {
        //判断全国
        if($('input[name="isWhole"]').val() == 0){
            if (data_ten == '') {
                layerCase('阈值不能为空');
                return false;
            }else if(day == ''){
                layerCase('活动时间不能为空');
                return false;
            }else if(data_twelve == ''){
                layerCase('比例分值');
                return false;
            } else if(pile_arr == ''){
                layerCase('请选择站点');
                return false;
            }
            data = {
                'pkId': data_one,
                'activityStatus': data_two,
                'activityName': data_three,
                'highestPriority': data_seven,
                'integralRulesId': data_eight,
                'direction': data_nine,
                'minValue': data_ten,
                'ratioIntegralValue': data_twelve,
                'isChoice': data_thirteen,
                'choiceMoney': data_forteen,
                'strStartDate': data_fifiteen,
                'strEndDate': data_sixteen,
                'electricPileIds': pile_arr,
                'limitIntegral':data_seventeen,
                'isWhole': 0
            }
        }else{
            if (data_ten == '') {
                layerCase('阈值不能为空');
                return false;
            }else if(day == ''){
                layerCase('活动时间不能为空');
                return false;
            }else if(data_twelve == ''){
                layerCase('比例分值');
                return false;
            }

            data = {
                'pkId': data_one,
                'activityStatus': data_two,
                'activityName': data_three,
                'highestPriority': data_seven,
                'integralRulesId': data_eight,
                'direction': data_nine,
                'minValue': data_ten,
                'ratioIntegralValue': data_twelve,
                'isChoice': data_thirteen,
                'choiceMoney': data_forteen,
                'strStartDate': data_fifiteen,
                'strEndDate': data_sixteen,
                'limitIntegral':data_seventeen,
                'isWhole': 1
            }
        }
        $.ajax({
            url: basePath + modifyIntegralActivityUrl,
            type: "post",
            dataType: 'json',
            data: data,
            success: function(data) {
                if (data.status == 1000) {
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '160px'],
                        //宽高
                        content: '提交成功',
                        btn: ["确定"],
                        yes: function(index, layero) {
                            layer.closeAll();
                            //window.location.reload();
                            window.history.back();
                        },
                        cancel: function(index, layero) {
                            layer.closeAll();
                            //window.location.reload();
                            window.history.back();
                        }
                    });
                } else if (data.status == 2001){
                    layer.closeAll();
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '160px'],
                        //宽高
                        content: data.msg,
                        btn: ["确定"],
                        yes: function(index, layero) {
                            layer.closeAll();
                        },
                        cancel: function(index, layero) {
                            layer.closeAll();
                        }
                    });
                    return false;
                } else if (data.status == 9001) {
                    layer.closeAll();
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '160px'],
                        //宽高
                        content: '会话超时，请重新登陆！',
                        btn: ["确定"],
                        yes: function(index, layero) {
                            layer.closeAll();
                            window.top.location.href = basePath + toLoginUrl;
                        },
                        cancel: function(index, layero) {
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
                        area: ['310px', '160px'],
                        //宽高
                        content: '系统出错',
                        btn: ["确定"],
                        yes: function(index, layero) {
                            layer.closeAll();
                            window.top.location.href = basePath + toLoginUrl;
                        },
                        cancel: function(index, layero) {
                            layer.closeAll();
                            window.top.location.href = basePath + toLoginUrl;
                        }
                    });
                }
            }
        })
    }else{
        layerCase('请设置充电消费分享是否赠送积分、抽奖机会');
        return false;
    }
};

//监听回车键
$(document).keyup(function(event) {
    if (event.keyCode == 13) {
        foo();
    }
});
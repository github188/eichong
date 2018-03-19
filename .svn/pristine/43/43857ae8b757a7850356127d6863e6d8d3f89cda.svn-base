/**
 * Created by chenjie on 2017/10/25 0025.
 */
//编辑初始渲染
$(function() {
    var data = {
        pkId: 3,
        integralRulesId: getUrlParam('integralRulesId')
    };
    $.ajax({
        url: basePath + getIntegralActivityAndRulesListUrl,
        type: "post",
        dataType: 'json',
        data: data,
        success: function(data) {
            var checkedData = data.dataObject[0];
            //状态
            if (checkedData.activityStatus == 0) {
                $('input[name="state"]:eq(0)').attr("checked", 'checked');
            } else if (checkedData.activityStatus == 1) {
                $('input[name="state"]:eq(1)').attr("checked", 'checked');
                $('input[name="dailyCollecction"]').attr("disabled", true);
                $('input[name="fixed-score"]').attr('disabled', true);
                $('#datePicker').attr("disabled", true);
            }
            //时间
            var startday = new Date(checkedData.startDate.time).format("yyyy-MM-dd"),
                endday = new Date(checkedData.endDate.time).format("yyyy-MM-dd");
            $('#datePicker').val(startday + ' - ' + endday);
            //固定分值
            $('input[name="fixed-score"]').val(checkedData.fixedIntegralValue)
        }
    });
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

//开启关闭按钮
$('.radio-inline').on('click', function() {
    var target = $(this).attr('state');
    if (target == '2') {
        $('input[name="dailyCollecction"]').attr("disabled", true);
        $('input[name="fixed-score"]').attr('disabled', true);
        $('#datePicker').attr("disabled", true);
    } else {
        $('input[name="dailyCollecction"]').attr("disabled", false);
        $('input[name="fixed-score"]').attr('disabled', false);
        $('#saveBtn').bind("click", foo);
        $('#datePicker').attr("disabled", false);
    }
});

//返回按钮
$('body').off('click', '#toPowerStationList').on('click', '#toPowerStationList', function() {
    window.history.back();
});

//固定分值取整提醒
$('input[name="fixed-score"]').on({
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
        if (this.value >= 100) {
            layer.open({
                type: 1,
                offset: '100px',
                title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                shadeClose: false,
                closeBtn: 1,
                area: ['310px', '160px'],
                //宽高
                content: '过高的分值可能会导致损失，请注意操作。',
                time: 0,
                btn: ["确定", "取消"],
                btn1: function(index, layero) {
                    layer.closeAll();
                },
                btn2: function(index, layero) {
                    $('input[name="fixed-score"]').val('');
                    layer.closeAll();
                },
                cancel: function(index, layero) {
                    $('input[name="fixed-score"]').val('');
                    layer.closeAll();
                }
            })
        }
    }
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
            $('input[name="fixed-score"]').val('');
            layer.closeAll();
        }
    });
}
//数据提交
var foo = function() {
    var data_one = $('input:radio:checked').val(),
    //状态
        data_two = 3,
    //活动名称
        data_three = 0,
    //积分方法
        day = $('#datePicker').val(),
    //时间
        data_five = $('input[name = "fixed-score"]').val(); //固定分值
    var start_date = slice_date(day)[0],
        end_day = slice_date(day)[1];
    if (data_two == '') {
        layerCase('活动名称不能为空');
        return false;
    }else if(day == ''){
        layerCase('活动时间不能为空');
        return false;
    }else if(data_five == ''){
        layerCase('固定分值不能为空');
        return false;
    }

    var data = {
        'pkId': data_two,
        'activityStatus': data_one,
        'activityName': '每日领取',
        'direction': data_three,
        'fixedIntegralValue': data_five,
        'strStartDate': start_date,
        'strEndDate': end_day,
        'integralRulesId': getUrlParam('integralRulesId')
    };
    $.ajax({
        url: basePath + modifyIntegralActivityUrl,
        type: "post",
        dataType: 'json',
        data: data,
        success: function(data) {
            if (data.success) {
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

            } else if (data.status == 2001) {
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
};

//监听回车键
$(document).keyup(function(event) {
    if (event.keyCode == 13) {
        foo();
    }
});

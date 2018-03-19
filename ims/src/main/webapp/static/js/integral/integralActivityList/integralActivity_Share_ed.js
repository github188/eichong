/**
 * Created by Administrator on 2017/10/27 0027.
 */
$(function() {
    var data = {
        pkId: 5,
        integralRulesId: getUrlParam('integralRulesId')
    };
    $.ajax({
        url: basePath + getIntegralActivityAndRulesListUrl,
        type: "post",
        dataType: 'json',
        data: data,
        success: function(data) {
            var checkedData = data.dataObject[0];
            //时间选择
            var startday = new Date(checkedData.startDate.time).format("yyyy-MM-dd"),
                endday = new Date(checkedData.endDate.time).format("yyyy-MM-dd");
            $('#datePicker').val(startday + ' - ' + endday);
            //两种赠送方式选择
            if (checkedData.isShareChoice == 0) {
                $('input[name="share-choice"]').attr("checked", false);
            } else if (checkedData.isShareChoice == 1) {
                $('input[name="share-choice"]:eq(0)').attr("checked", 'checked');
                $('input[name="share-choice"]:eq(1)').attr("checked", false);
                $('input[name="fixed-score-input"]').attr('disabled', false);
                $('input[name="fixed-score"]').attr('disabled', false);
            } else if (checkedData.isShareChoice == 2) {
                $('input[name="share-choice"]:eq(0)').attr("checked", false);
                $('input[name="share-choice"]:eq(1)').attr("checked", 'checked');
                $('input[name="fixed-score-input"]').attr('disabled', false);
                $('input[name="fixed-score"]').attr('disabled', false);
            }
            //状态
            if (checkedData.activityStatus == 0) {
                $('input[name="state"]:eq(0)').attr("checked", 'checked');
            } else if (checkedData.activityStatus == 1) {
                $('input[name="state"]:eq(1)').attr("checked", 'checked');
                $('#datePicker,input[name="share-choice"],input[name="fixed-score-input"],input[name="fixed-score"],input[name="lottery-chance"]').attr("disabled", true);
            }
            //固定分值
            if (checkedData.shareIntegralValue != 0) {
                $('input[name="fixed-score-input"]').attr("checked", 'checked');
                $('input[name="fixed-score"]').val(checkedData.shareIntegralValue);
            }
            //赠送一次抽奖机会
            if (checkedData.shareChoice == 0) {
                $('input[name="lottery-chance"]').attr("checked", 'checked');
                $('input[name="lottery-chance"]').val(0);
            } else {
                $('input[name="lottery-chance"]').attr("checked", false);
                $('input[name="lottery-chance"]').val(1);
            }
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

//时间戳转日期
function formatDate(now) {
    var year = now.getFullYear();
    var month = now.getMonth() + 1;
    var date = now.getDate();
    var hour = now.getHours();
    var minute = now.getMinutes();
    var second = now.getSeconds();
    return year + "-" + month + "-" + date;
}

//返回按钮
$('body').off('click', '#toPowerStationList').on('click', '#toPowerStationList', function() {
    window.history.back();
});

//关闭选项
$('input[name="state"]').on('click', function() {
    var target = $(this).parent().attr('state');
    if (target == '2') {
        $('#datePicker,input[name="share-choice"],input[name="fixed-score-input"],' +
            'input[name="fixed-score"],input[name="lottery-chance"]').attr("disabled", true);
    } else {
        $('#datePicker,input[name="share-choice"],input[name="lottery-chance"]').attr("disabled", false);
        $('#saveBtn').bind("click", foo);
    }
});

//分值取整提醒
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
                content: '用户消费时获取太多积分，可能导致损失，请谨慎操作',
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
        } else if (this.value < 100 && this.value != '') {
            layer.open({
                type: 1,
                offset: '100px',
                title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                shadeClose: false,
                closeBtn: 1,
                area: ['310px', '160px'],
                //宽高
                content: '<100的减少用户获取的积分，降低用户体验，请谨慎设置',
                time: 0,
                btn: ["确定"],
                btn1: function(index, layero) {
                    $('input[name="fixed-score"]').val('100');
                    layer.closeAll();
                },
                cancel: function(index, layero) {
                    $('input[name="fixed-score"]').val('100');
                    layer.closeAll();
                }
            })
        } else if (this.value == '') {
            return true;
        }
    }
})

//首次和每次分享
$('input[name="share-choice"]').on('click', function() {
    $('input[name="fixed-score-input"]').attr('disabled', false);
    $('input[name="fixed-score"]').attr('disabled', false);
})
//
//是否赠送抽奖机会
$('input[name="lottery-chance"]').on('click', function() {
    if ($(this).is(':checked') == true) {
        $(this).val('0');
    } else if ($(this).is(':checked') == false) {
        $(this).val('1');
    }
});

//数据提交
var foo = function() {
    var choice = '',
        day = $('#datePicker').val();
    var shareChoice = $('input[name="share-choice"]:radio:checked').val();
    //充电消费分享赠送积分、抽奖机会
    if (shareChoice == 1) {
        choice = 1;
    } else if (shareChoice == 2) {
        choice = 2;
    } else {
        choice = 0;
    }

    if (choice == 0) {
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '160px'],
            //宽高
            content: '请设置充电消费分享是否赠送积分、抽奖机会',
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
        return false;
    }

    var data_one = 5,
    //活动id
        data_two = $('input[name="state"]:radio:checked').val(),
    //活动状态
        data_three = '分享赠送',
    //活动名称
        data_four = getUrlParam('integralRulesId'),
    //活动规则Id
        data_five = choice,
    //充电消费分享赠送积分、抽奖机会
        data_six = $('input[name="fixed-score"]').val(),
    //分享成功后赠送积分值
        data_seven = $('input[name="lottery-chance"]').val(),
    //分享成功后赠送一次抽奖机会
        data_eight = slice_date(day)[0],
    //活动开始日期
        data_nine = slice_date(day)[1]; //活动结束日期

    if (day == '') {
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '160px'],
            //宽高
            content: '活动时间不能为空',
            time: 0,
            btn: ["确定"],
            btn1: function(index, layero) {
                layer.closeAll();
            },
            cancel: function(index, layero) {
                layer.closeAll();
            }
        });
        return false;
    }else if(data_six == ''){
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '160px'],
            //宽高
            content: '固定分值不能为空',
            time: 0,
            btn: ["确定"],
            btn1: function(index, layero) {
                layer.closeAll();
            },
            cancel: function(index, layero) {
                layer.closeAll();
            }
        });
        return false;
    }

    var data = {
        'pkId': data_one,
        'activityStatus': data_two,
        'activityName': data_three,
        'integralRulesId': data_four,
        'isShareChoice': data_five,
        'shareIntegralValue': data_six,
        'shareChoice': data_seven,
        'strStartDate': data_eight,
        'strEndDate': data_nine
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
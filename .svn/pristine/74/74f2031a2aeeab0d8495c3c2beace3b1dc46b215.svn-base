/**
 * Created by Administrator on 2017/10/26 0026.
 */
$(function() {
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

//关闭选项
$('.radio-inline').on('click', function() {
    var target = $(this).attr('state');
    if (target == '2') {
        $('input[name="fixed-score"]').attr('disabled', true);
        $('#saveBtn').unbind("click", foo);
        $('#datePicker').attr("disabled", true);
    } else {
        $('input[name="fixed-score"]').attr('disabled', false);
        $('#saveBtn').bind("click", foo);
        $('#datePicker').attr("disabled", false);
    }
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
                content: '超过100会导致损失和漏洞，请重新设置',
                time: 0,
                btn: ["确定"],
                btn1: function(index, layero) {
                    $('#saveBtn').unbind("click", foo);
                    $('#datePicker').attr("disabled", true);
                    layer.closeAll();
                }
            })
        } else if (this.value < 100) {
            $('#saveBtn').bind("click", foo);
            $('#datePicker').attr("disabled", false);
        }
    }
})

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
    })
}
//数据提交
var foo = function() {
    var data_one = $('input:radio:checked').val(),
    //状态
        data_two = 4,
    //活动名称
        data_three = 0,
    //积分方法
        day = $('#datePicker').val(),
    //时间
        data_five = $('input[name = "fixed-score"]').val();
    //固定分值
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
        'activityName': '修改资料赠送',
        'direction': data_three,
        'fixedIntegralValue': data_five,
        'strStartDate': start_date,
        'strEndDate': end_day
    };
    $.ajax({
        url: basePath + addIntegralActivityUrl,
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
                        iframeClose();
                    },
                    cancel: function(index, layero) {
                        layer.closeAll();
                        iframeClose();
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
/**
 * Created by Administrator on 2017/11/28 0028.
 */
$(document).ready(function() {
    $('#submitBtn').bind("click", submitPassword);
});
//提交
var submitPassword = function() {
    var ar = $('.newPassword').val(),
        br = $('.surePassword').val();
    var data = {
        'userId': JSON.parse(localStorage.getItem("loginUser")).userId,
        'password': br
    };
    if (ar.length == '' || br.length == '') {
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '160px'],
            //宽高
            content: '请完整填写密码',
            time: 0,
            btn: ["确定"],
            btn1: function(index, layero) {
                layer.closeAll();
            },
            cancel: function(index, layero) {
                layer.closeAll();
            }
        })
    } else if (ar == br) {
        $.ajax({
            url: basePath + modifyLoginPasswordUrl,
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
                            window.location.reload();
                        },
                        cancel: function(index, layero) {
                            layer.closeAll();
                            window.location.reload();
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
    }
};

//新密码
$('.newPassword').bind('input propertychange', function() {
    var len = $(this).val();
    var errorOne = '<div class="col-sm-3 queryParam newError">*请至少填写6位密码</div>';
    var errorTwo = '<div class="col-sm-3 queryParam newError">*密码中不能有空格</div>';
    $('.newError').remove();
    if (len.length < 6) {
        $(this).parent().after(errorOne);
        $('#submitBtn').unbind("click", submitPassword);
    } else if (len.indexOf(" ") != -1) {
        $(this).parent().after(errorTwo);
        $('#submitBtn').unbind("click", submitPassword);
    } else {
        $('#submitBtn').bind("click", submitPassword);
    }
});
//确认新密码
$('.surePassword').bind('input propertychange', function() {
    var drr = $('.newPassword').val(),
        len = $(this).val();
    var errorOne = '<div class="col-sm-3 queryParam sureError">*请至少填写6位密码</div>';
    var errorTwo = '<div class="col-sm-3 queryParam sureError">*密码中不能有空格</div>';
    var errorThree = '<div class="col-sm-3 queryParam sureError">*两次密码不相同</div>';
    $('.sureError').remove();
    if (len.length < 6) {
        $(this).parent().after(errorOne);
        $('#submitBtn').unbind("click", submitPassword);
    } else if (len.indexOf(" ") != -1) {
        $(this).parent().after(errorTwo);
        $('#submitBtn').unbind("click", submitPassword);
    } else if (drr != len) {
        $(this).parent().after(errorThree);
        $('#submitBtn').unbind("click", submitPassword);
    } else {
        $('#submitBtn').bind("click", submitPassword);
    }
});
//重置按钮
$('#cancelBtn').on('click', function() {
    $('.newPassword,.surePassword').val('');
});
/**
 * Created by Kael on 2015/3/20.
 */
$(document).ready(function(){

    /**
     * 重置密码
     */
    $('#reset-form').on('valid.form',function(e,f){
        UserService.resetPwd(f);
    });

    /**
     * 短信发送
     */
    $('#btn-message').bind('click', validPhone);

    //手机号码校验
    function validPhone(e) {
        e.preventDefault();
        //异步回调结果
        $('input[name="usinPhone"]').isValid(function (v) {
            if (!v) {$(this).focus();return}
            Ajax.custom({
                url: config.ISendMsg,
                data: {
                    phone: $('input[name="usinPhone"]').val()
                }
            }, function (result) {
                if (!result)return;
                if (result.code != "OK") {
                    alert(result.msg);
                    return;
                }
                changeBtnState($('#btn-message'));
                alert("短信发送成功!");
            });
        });
    }

    /**
     * 倒计时
     */
    function changeBtnState(obj) {
        //移除事件，避免重复发送短信
        obj.unbind('click');
        var second = 60, text = obj.text();
        var timer = setInterval(function () {
            obj.text(text + '(' + (second--) + ')');
            if (second <= 0) {
                obj.bind('click', validPhone).text(text);
                clearInterval(timer);
            }
        }, 1000);
    }
});
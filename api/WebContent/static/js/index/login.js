/**
 * Created by Aaron on 2015/3/20.
 */

(function(){
    //记住密码，回填密码，账号
    $('input[name="rememberMe"]').prop('checked', false);
    $('input[name="usinPhone"]').val('');
    $('input[name="usInPassword"]').val('');
    var account = UserService.isRememberPwd();
    //如果记住密码
    if (account) {
        $('input[name="rememberMe"]').prop('checked', true);
        $('input[name="usinPhone"]').val(account.uname || '');
        $('input[name="usInPassword"]').val(account.pwd || '');
    }
})();
$(document).ready(function () {

    /**
     * 登录
     */
    $('#login-form').on('valid.form', function (e, f) {
        UserService.login(f);
    });

    /**
     * 验证码
     */
    $('#check-img').click(function(){
        $(this).attr('src',config.IValidCode+'?r='+Math.random());
    });

    /**
     * 记住密码
     */
    $('input[name="rememberMe"]').click(function(){
        if(!$(this).prop('checked')){
            Cookie.remove(Cookie.REMEMBER);
            $('input[name="usInPassword"]').val('');
        }
    });

    /**
     * 忘记密码
     */
    $('#reset-jump').click(function(){
        location.href = config.PReset;
    });

    /**
     * 免费注册
     */
    $('#regist-jump').click(function(){
        location.href = config.PRegist;
    });
    
});
/**
 * Created by Kael on 2015/3/20.
 */
$(document).ready(function () {
    /**
     * 注册
     */
    $('#regist-form').on('valid.form', function (e, f) {
        UserService.register(f);
    });

    /**
     * 验证码
     */
    $('#check-img').click(function () {
        $(this).attr('src', config.IValidCode + '?r=' + Math.random());
    });


    /**
     * 短信发送
     */
    $('body').on('click', '#btn-message',validPhone);

    //手机号码校验
    function validPhone(e) {
    	if($('#btn-message').hasClass("invalid"))return;
        e.preventDefault();
        var  phone=$("#registPhone").val();
        if(!/^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/i.test(phone)){
          showInfo("请输入正确的手机号码","error");
        }else{
        	$.ajax({
		         type: 'POST',
		         url: basepath+"/web/user/validPhone.do" ,
		         dataType: "json",
		         data:{userAccount:phone,checkPhoneExistFlag:1},
		         contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		         success: function(data){
		        	 if(data.code=="OK"){
		        		 $('#btn-message').addClass("invalid");
	                     changeBtnState($('#btn-message'));
	                     //alert("短信发送成功!");
	                     showInfo("短信发送成功!","success");
		        		 Ajax.custom({
		                     url: config.ISendMsg,
		                     data: {
		                         phone: $("#registPhone").val()
		                     }
		                 }, function (result) {
		                     if (!result)return;
		                     if (result.code != "OK") {
		                    	 //showInfo(result.msg,"error",3000);
		                         return;
		                     }
		                 });
		        	 }else{
		        		 showInfo(data.msg,"error");
		        	 }
		         },
		         error: function(XMLHttpRequest, textStatus, errorThrown) {
		             //alert(XMLHttpRequest.status);
		            // alert(XMLHttpRequest.readyState);
		            // alert(textStatus);
		         }
		    });
        }
//        //异步回调结果
//        $("#registPhone").isValid(function (v) {
//        	alert(1234);
//            if (!v) {$(this).focus();return}
//            Ajax.custom({
//                url: config.ISendMsg,
//                data: {
//                    phone: $("#registPhone").val()
//                }
//            }, function (result) {
//                if (!result)return;
//                if (result.code != "OK") {
//                    alert(result.msg);
//                    return;
//                }
//                changeBtnState($('#btn-message'));
//                alert("短信发送成功!");
//            });
//        });
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
            	obj.removeClass("invalid");
                obj.text(text);
                clearInterval(timer);
            }
        }, 1000);
    }
});
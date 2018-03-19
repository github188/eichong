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
    $('body').on('click', '#btn-message2',validPhone2);
    //手机号码校验
    function validPhone2(e) {
    	if($('#btn-message2').hasClass("invalid"))return;
        e.preventDefault();
        var  phone=$("#resetPhone").val();
        if(!/^(13[0-9]|17[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i.test(phone)){
          showInfo("请输入正确的手机号码","error");
        }else if($("#userAccountHidden").val()!=""&&phone!=$("#userAccountHidden").val()){
        		showInfo("验证手机不正确","error");
        }else{
        	$.ajax({
		         type: 'POST',
		         url: basepath+"/web/user/validPhone.do" ,
		         dataType: "json",
		         data:{userAccount:phone},
		         contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		         success: function(data){	
		        	 if(data.code=="OK"){
		        		 $('#btn-message2').addClass("invalid");
		        		 changeBtnState2($('#btn-message2'));
	                     showInfo("短信发送成功!","success");
		        		 Ajax.custom({
		                     url: config.ISendMsg,
		                     data: {
		                         phone: $("#resetPhone").val()
		                     }
		                 }, function (result) {
		                     if (!result)return;
		                     if (result.code != "OK") {
		                    	// showInfo(result.msg,"error",3000);
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
    }  
//    //手机号码校验
//    function validPhone(e) {
//        e.preventDefault();
//        //异步回调结果
//        $('input[name="usinPhone"]').isValid(function (v) {
//            if (!v) {$(this).focus();return}
//            Ajax.custom({
//                url: config.ISendMsg,
//                data: {
//                    phone: $('input[name="usinPhone"]').val()
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
//    }

    /**
     * 倒计时
     */
    function changeBtnState2(obj) {
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
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
    $('body').on('click', '#userAccountSubmit',getPhoneByAccount);
    $('body').on('click', '#btn-message2',validPhone2);
	$('body').on('click', '#resetSubmit', function (e) {
		if($('#userPassword').val() != $('#rePassword').val()){
			alert("两次密码输入不一致，请重新输入");
			return;
		}
		var array=[
					{
						name:"phone",
						rule:{
								"required":{msg:"手机号码不能为空"},
								"mobile":{msg:"手机号码有误"}
							}
					},
					{
						name:"userPassword",
						rule:{
								"required":{msg:"密码不能为空"},
								"password":{msg:"密码输入有误"},
								"password":{msg:"密码请输入6~20位字符串"},
								"minLength":{param:6,msg:"密码长度最小为6"},
								"maxLength":{param:20,msg:"密码长度最大为20"}
							}
					},
					{
						name:"rePassword",
						rule:{
								"required":{msg:"重复密码不能为空"},
								"password":{msg:"重复密码输入有误"},
								"password":{msg:"重复密码请输入6~20位字符串"},
								"minLength":{param:6,msg:"重复密码长度最小为6"},
								"maxLength":{param:20,msg:"重复密码长度最大为20"}
							}
					},
					{
						name:"msgCode",
						rule:{  "required":{msg:"手机验证码不能为空"},
								"numberOrLetter":{msg:"手机验证码有误"},
								"eqLength":{param:4,msg:"验证码长度为4位"}
								}
					}
					
				];
		
				if($("#userLevelDiv").val() == '5'){
					array[1].rule = {
							"required":{msg:"密码不能为空"},
							"password":{msg:"密码输入有误"},
							"number":{msg:"密码请输入6~8位数字"},
							"minLength":{param:6,msg:"密码长度最小为6"},
							"maxLength":{param:8,msg:"密码长度最大为8"}
					}
					array[2].rule = {
							"required":{msg:"重复密码不能为空"},
							"password":{msg:"重复密码输入有误"},
							"number":{msg:"重复密码请输入6~8位数字"},
							"minLength":{param:6,msg:"重复密码长度最小为6"},
							"maxLength":{param:8,msg:"重复密码长度最大为8"}
					}
					
					$("#userPassword").attr("maxlength",'8')
					$("#rePassword").attr("maxlength",'8')
				}
				var res=validateForm("resetForm",array);
				if(!res.result){
					alert(res.msg);
					return;
				}
		var obj=$("#resetForm").serializeObject();
		$.ajax({
	         type: 'POST',
	         url: basepath+"/web/user/resetPasswrod.do",
	         dataType: "json",
	         data:$.param(obj),
	         contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	         success: function(data){
	        	 if(data.statusCode == "200"){
	        		 $("#st").hide();
	        		 $("#sc").show();
	        	 }else{
	        		 alert(data.message);
	        	 }
	         },
	         error: function(XMLHttpRequest, textStatus, errorThrown) {
	         }
	    });
	});
	
    //获取手机号
    function getPhoneByAccount(e) {
    	$.ajax({
	         type: 'POST',
	         url: basepath+"/web/user/getPhoneByUserAccount.do" ,
	         dataType: "json",
	         data:{userAccount:$("#userAccount").val()},
	         contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	         success: function(data){
	        	 if(data.statusCode == "300"){
	        		 alert(data.message);
	        		 return;
	        	 }
	        	 var dataArray = data.rel.split(",");
        		 $("#so").hide();
        		 $("#st").show();
        		 $("#userAccountDiv").val(dataArray[1])
        		 $("#userLevelDiv").val(dataArray[2])
        		 var phone = dataArray[0];
        		 $("#resetPhone").val(phone)
        		 $("#resetPhoneDiv").html(phone.replace(phone.substring(3,phone.length-4),"****"))
	         },
	         error: function(XMLHttpRequest, textStatus, errorThrown) {
	        	 
	         }
	    });
    }
	
	
	
    //手机号码校验
    function validPhone2(e) {
    	if($('#btn-message2').hasClass("invalid"))return;
        e.preventDefault();
        var  phone=$("#resetPhone").val();
        if(!/^(13[0-9]|17[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i.test(phone)){
        	alert("请输入正确的手机号码");
        }else if($("#userAccountHidden").val()!=""&&phone!=$("#userAccountHidden").val()){
        	alert("验证手机不正确");
        }else{
        	$.ajax({
		         type: 'POST',
		         url: basepath+"/web/user/validPhone.do" ,
		         dataType: "json",
		         data:{phone:phone},
		         contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		         success: function(data){	
		        	 if(data.statusCode == "200"){
		        		 $('#btn-message2').addClass("invalid");
		             	 $.ajax({
		     		         type: 'POST',
		     		         url:basepath+"/web/user/sendMsg.do" ,
		     		         dataType: "json",
		     		         data:{phone: $("#resetPhone").val()},
		     		         contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		     		         success: function(data){
		     		        	 if(data.statusCode == "200"){
					        		 alert(data.message);
					        		 changeBtnState2($('#btn-message2'));
		     		        	 }
			     		     },
		     		         error: function(XMLHttpRequest, textStatus, errorThrown) {
		     		        	 
		     		         }
		     		    });
		        	 }else{
		        		 alert(data.message);
		        	 }
		         },
		         error: function(XMLHttpRequest, textStatus, errorThrown) {
		        	 
		         }
		    });
        }
    } 

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

    /**
     * 倒计时
     */
    function changeBtnState2(obj) {
        //移除事件，避免重复发送短信
        obj.unbind('click');
        var second = 60;
        var text = obj.text();
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
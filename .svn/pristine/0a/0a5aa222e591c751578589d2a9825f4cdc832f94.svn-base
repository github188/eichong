$(document).ready(function() {
		$.fn.serializeObject = function() {
			var o = {};
			var a = this.serializeArray();
			$.each(a, function() {
				if (o[this.name] !== undefined) {
					if (!o[this.name].push) {
						o[this.name] = [ o[this.name] ];
					}
					o[this.name].push(this.value || '');
				} else {
					o[this.name] = this.value || '';
				}
			});
			return o;
		};
		UserService.showUser();
		//登录
		$('body').on('click', '#login', function (e) {
			hideBox();
			$("#loginBox").show();
			$("#loginForm")[0].reset();
			showZZ();
			$("#validateCodeLogin").click();
		});
		$('body').on('click', '#closeLogin', function (e) {
			$("#loginBox").hide();
			closeZZ();	
		});
		//注册
		$('body').on('click', '#regist', function (e) {
			hideBox();
			$("#registBox").show();
			$("#registForm")[0].reset();
			showZZ();
			$("#validateCodeRegist").click();
		});
		//注册
		$('body').on('click', '#regist2', function (e) {
			hideBox();
			$("#registBox").show();
			$("#registForm")[0].reset();
			showZZ();
			$("#validateCodeRegist").click();
		});
		$('body').on('click', '#closeRegist', function (e) {
			$("#registBox").hide();
			closeZZ();	
		});
		//密码找回
		$('body').on('click', '#reset', function (e) {
			hideBox();
			$("#resetForm")[0].reset();
			$("#resetBox").show();
			$("#resetTitle").html("密码找回");
			showZZ();
			$("#validateCodeReset").click();
		});
		//修改密码
		$('body').on('click', '#modifyPwd', function (e) {
			$(window).scrollTop(0);
			hideBox();
			$("#resetBox").show();
			$("#resetTitle").html("密码修改");
			showZZ();
			$("#validateCodeReset").click();
		});
		$('body').on('click', '#closeReset', function (e) {
			$("#resetBox").hide();
			closeZZ();
		});
		//注销
		$('body').on('click', '.logoutAll', function (e) {
			 if(confirm("确定要注销吗？")){
				 UserService.removeUser();
			 }
			
		});
		$('body').on('click', '.changeCode', function (e) {
			var id=$(this).attr("data-id");
			$("#"+id).click();
		});
		$('body').on('click', '.imageCodeHuoqu', function (e) {
			var imageHtml='<img src="'+basepath+'/web/user/getValidCode.do?'+ Math.random()+ '" style="margin:0;width:80px;height:36px;">';
			$(this).html(imageHtml);
		});
		$('body').on('click', '#loginSubmit', function (e) {
			var array=[
				{
					name:"userAccount",
					rule:{"mobile":{msg:"手机号码有误"}}
				},
				{
					name:"userPassword",
					rule:{
						"password":{msg:"密码输入有误"},
						"number":{msg:"密码请输入6~8位数字"},
						"minLength":{param:6,msg:"密码长度最小为6"},
						"maxLength":{param:8,msg:"密码长度最大为8"}
						}
				},
				{
					name:"code",
					rule:{
							"numberOrLetter":{msg:"验证码有误"},
							"eqLength":{param:4,msg:"验证码长度有误"}
							}
				}     
				
			];
			var res=validateForm("loginForm",array);
			if(!res.result){
				showInfo(res.msg,"error");
				 $("#validateCodeLogin").click();
				return;
			}
			var obj=$("#loginForm").serializeObject();
			obj.userPassword= $.md5(obj.userPassword).toLowerCase();
			$.ajax({
		         type: 'POST',
		         url: basepath+"/web/user/login.do" ,
		         dataType: "json",
		         data:$.param(obj),
		         contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		         success: function(data){
		        	 if(data.code=="OK"){
		        		 hideBox();
		        		 closeZZ();
		        		 UserService.login2(data.data);
		        		 UserService.showUser();
		        	 }else{
		        		 showInfo(data.msg,"error",3000);
		        		 $("#validateCodeLogin").click();
		        	 }
		         },
		         error: function(XMLHttpRequest, textStatus, errorThrown) {
		         }
		    });
		});
		$('body').on('click', '#registSubmit', function (e) {
			var array=[
						{
							name:"userAccount",
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
									"number":{msg:"密码请输入6~8位数字"},
									"minLength":{param:6,msg:"密码长度最小为6"},
									"maxLength":{param:8,msg:"密码长度最大为8"}
								}
						},
						{
							name:"rePassword",
							rule:{
									"required":{msg:"重复密码不能为空"},
									"password":{msg:"重复密码输入有误"},
									"number":{msg:"重复密码请输入6~8位数字"},
									"minLength":{param:6,msg:"重复密码长度最小为6"},
									"maxLength":{param:8,msg:"重复密码长度最大为8"}
								}
						},
						{
							name:"msgCode",
							rule:{  "required":{msg:"手机验证码不能为空"},
									"numberOrLetter":{msg:"手机验证码有误"},
									"eqLength":{param:6,msg:"手机验证码长度为6位"}
									}
						},    
						{
							name:"code",
							rule:{  "required":{msg:"图片验证码不能为空"},
									"numberOrLetter":{msg:"图片验证码有误"},
									"eqLength":{param:4,msg:"图片验证码长度有误"}
									}
						},
						{
							name:"normName",
							rule:{	
									"required":{msg:"昵称不能为空"},
									/*"numberOrLetter":{msg:"昵称输入有误"},*/
									"maxLength":{param:6,msg:"昵称长度最大为6"}
									}
						} 
						
					];
					var res=validateForm("registForm",array);
					if(!res.result){
						showInfo(res.msg,"error");
						$("#validateCodeRegist").click();
						return;
					}
			
			var obj=$("#registForm").serializeObject();
			obj.userPassword= $.md5(obj.userPassword).toLowerCase();
			obj.rePassword=$.md5(obj.rePassword).toLowerCase();
			$.ajax({
		         type: 'POST',
		         url: basepath+"/web/user/regist.do" ,
		         dataType: "json",
		         data:$.param(obj),
		         success: function(data){
		        	 if(data.code=="OK"){
		        		 hideBox();
		        		 closeZZ();
		        		 UserService.login2(data.data);
		        		 UserService.showUser();
		        	 }else{
		        		 showInfo(data.msg,"error");
		        		 $("#validateCodeRegist").click();
		        	 }
		         },
		         error: function(XMLHttpRequest, textStatus, errorThrown) {
		         }
		    });
		});
		$('body').on('click', '#resetSubmit', function (e) {
			var array=[
						{
							name:"userAccount",
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
									"number":{msg:"密码请输入6~8位数字"},
									"minLength":{param:6,msg:"密码长度最小为6"},
									"maxLength":{param:8,msg:"密码长度最大为8"}
								}
						},
						{
							name:"rePassword",
							rule:{
									"required":{msg:"重复密码不能为空"},
									"password":{msg:"重复密码输入有误"},
									"number":{msg:"重复密码请输入6~8位数字"},
									"minLength":{param:6,msg:"重复密码长度最小为6"},
									"maxLength":{param:8,msg:"重复密码长度最大为8"}
								}
						},
						{
							name:"msgCode",
							rule:{  "required":{msg:"手机验证码不能为空"},
									"numberOrLetter":{msg:"手机验证码有误"},
									"eqLength":{param:6,msg:"验证码长度为6位"}
									}
						}
						
					];
					var res=validateForm("resetForm",array);
					if(!res.result){
						showInfo(res.msg,"error");
						return;
					}
			var obj=$("#resetForm").serializeObject();
			obj.userPassword= $.md5(obj.userPassword).toLowerCase();
			$.ajax({
		         type: 'POST',
		         url: basepath+"/web/user/resetPasswrod.do" ,
		         dataType: "json",
		         data:$.param(obj),
		         contentType: "application/x-www-form-urlencoded; charset=UTF-8",
		         success: function(data){
		        	 if(data.code=="OK"){
		        		 hideBox();
		        		 closeZZ();
		        		 showInfo(data.msg);
		        		 UserService.removeUser();
		        		 UserService.showUser();
		        		 $("#login").click();
		        	 }else{
		        		 showInfo(data.msg,"error",1000);
		        	 }
		         },
		         error: function(XMLHttpRequest, textStatus, errorThrown) {
		         }
		    });
		});
		
		//分页点击
	    $('body').on('click', '.page li[data-page-num]', function (e) {
	        e.preventDefault();
	        if (typeof page.pageFunction == 'function') {
	        	page.params.pageNum=$(this).attr('data-page-num');
	        	page.pageFunction();
	        }
	    });


	    //上一页,下一页
	    $('body').on('click', '.page .pre,.page .next', function () {
	        if (typeof page.pageFunction == 'function') {
	            var cPage = parseInt($('.page .ul-pages').find('.current').text());
                $(this).is($('.page .pre')) ? cPage -= 1 : cPage += 1;
                page.params.pageNum = cPage;
                page.pageFunction();
	        }
	    });
	    
	    $(window).scroll(function() {
	    	 if($("#zz").length>0){
	    		 $(window).scrollTop(0);
	    	 }
	    });
	    
	});
	

	function hideBox(){
		$(".LogBox").hide();
	}
	
	function showZZ(){
		$('<div class="zz"></div>').appendTo("body");
		$(".zz").css("height",$(document).height());
	}
	function closeZZ(){
		$(".zz").remove();
	}
	
	function showInfo(info, type, time) {
		if (type) {
	        $(".information").addClass("label-"+type);
	    }
		$(".information").html(info).removeClass("infohide");
	    time=time?time:3000;
	    setTimeout(hideInfo, time);
	}
	function hideInfo() {
		$(".information").html("");
	    if ($(".information").css("opacity") != 1)return;
	    $(".information").addClass("infohide");
	    if ($(".information").hasClass("label-error")) {
	        $(".information").removeClass("label-error");
	    }
	    if ($(".information").hasClass("label-warn")) {
	        $(".information").removeClass("label-warn");
	    }
	    if ($(".information").hasClass("label-success")) {
	        $(".information").removeClass("label-success");
	    }
	}
	
	$(window).on('scroll',function(){
		var st = $(document).scrollTop();
		if( st>0 ){
			if( $('#main-container').length != 0  ){
				var w = $(window).width(),mw = $('#main-container').width();
				if( (w-mw)/2 > 70 )
					$('#go-top').css({'left':(w-mw)/2+mw+20});
				else{
					$('#go-top').css({'left':'auto'});
				}
			}
			$('#go-top').fadeIn(function(){
				$(this).removeClass('dn');
			});
		}else{
			$('#go-top').fadeOut(function(){
				$(this).addClass('dn');
			});
		}	
	});
	
	//跳到顶端
	$("body").on("click",'#go-top .go',function(){
		$('html,body').animate({'scrollTop':0},500);
	});
	
	//键盘回车事件
	$("body").on("keydown",function(e){
		var curKey = e.which; 
		if(curKey == 13){ 
			if($("#registBox").is(":visible")){
				$("#registSubmit").click();
			}else if($("#loginBox").is(":visible")){
				$("#loginSubmit").click();
			}else if($("#resetBox").is(":visible")){
				$("#resetSubmit").click();
			}
		} 
	});

	$('#go-top .uc-2vm').hover(function(){
		$('#go-top .uc-2vm-pop').removeClass('dn');
	},function(){
		$('#go-top .uc-2vm-pop').addClass('dn');
	});
	//选中菜单
	if(this_tag=="index"){
		$("#"+this_tag).addClass("select");
	}else{
		$("#"+this_tag).addClass("select2");
	}
$(document)
		.ready(
				function() {
					$.fn.serializeObject = function() {
						var c = {};
						var b = this.serializeArray();
						$.each(b, function() {
							if (c[this.name] !== undefined) {
								if (!c[this.name].push) {
									c[this.name] = [ c[this.name] ]
								}
								c[this.name].push(this.value || "")
							} else {
								c[this.name] = this.value || ""
							}
						});
						return c
					};
					UserService.showUser();
					$("#login").click(function() {
						hideBox();
						$("#loginBox").show();
						$("#loginForm")[0].reset();
						showZZ();
						$("#validateCodeLogin").click()
					});
					$("#closeLogin").click(function() {
						$("#loginBox").hide();
						closeZZ()
					});
					$(".regist").click(function() {
						hideBox();
						$("#registBox").show();
						$("#registForm")[0].reset();
						showZZ();
						$("#validateCodeRegist").click()
					});
					$("#closeRegist").click(function() {
						$("#registBox").hide();
						closeZZ()
					});
					$("#reset").click(function() {
						hideBox();
						$("#resetForm")[0].reset();
						$("#resetBox").show();
						$("#resetTitle").html("密码找回");
						showZZ();
						$("#validateCodeReset").click()
					});
					$("#modifyPwd").click(function() {
						$(window).scrollTop(0);
						hideBox();
						$("#resetBox").show();
						$("#resetTitle").html("密码修改");
						showZZ();
						$("#validateCodeReset").click()
					});
					$("#closeReset").click(function() {
						$("#resetBox").hide();
						closeZZ()
					});
					$("#logout").click(function() {
						if (confirm("确定要注销吗？")) {
							UserService.removeUser();
							window.location.href = basepath + "/index.do"
						}
					});
					$(".changeCode").click(function() {
						var a = $(this).attr("data-id");
						$("#" + a).click()
					});
					$(".huoqu")
							.click(
									function() {
										var a = '<img src="'
												+ basepath
												+ "/web/user/getValidCode.do?"
												+ Math.random()
												+ '" style="margin:0;width:80px;height:40px;">';
										$(this).html(a)
									});
					$("#loginSubmit")
							.click(
									function() {
										var c = [ {
											name : "usinPhone",
											rule : {
												mobile : {
													msg : "手机号码有误"
												}
											}
										}, {
											name : "usinPassword",
											rule : {
												password : {
													msg : "密码输入有误"
												},
												number : {
													msg : "密码请输入6~8位数字"
												},
												minLength : {
													param : 6,
													msg : "密码长度最小为6"
												},
												maxLength : {
													param : 8,
													msg : "密码长度最大为8"
												}
											}
										}, {
											name : "code",
											rule : {
												numberOrLetter : {
													msg : "验证码有误"
												},
												eqLength : {
													param : 4,
													msg : "验证码长度有误"
												}
											}
										} ];
										var a = validateForm("loginForm", c);
										if (!a.result) {
											showInfo(a.msg, "error");
											return
										}
										var b = $("#loginForm")
												.serializeObject();
										b.usinPassword = $.md5(b.usinPassword)
												.toLowerCase();
										$
												.ajax({
													type : "POST",
													url : basepath
															+ "/web/user/login.do",
													dataType : "json",
													data : $.param(b),
													contentType : "application/x-www-form-urlencoded; charset=UTF-8",
													success : function(d) {
														if (d.code == "OK") {
															hideBox();
															closeZZ();
															UserService
																	.login2(d.data);
															UserService
																	.showUser()
														} else {
															showInfo(d.msg,
																	"error",
																	3000)
														}
													},
													error : function(d, f, e) {
													}
												})
									});
					$("#registSubmit").click(function() {
						var c = [ {
							name : "usinPhone",
							rule : {
								required : {
									msg : "手机号码不能为空"
								},
								mobile : {
									msg : "手机号码有误"
								}
							}
						}, {
							name : "usinPassword",
							rule : {
								required : {
									msg : "密码不能为空"
								},
								password : {
									msg : "密码输入有误"
								},
								number : {
									msg : "密码请输入6~8位数字"
								},
								minLength : {
									param : 6,
									msg : "密码长度最小为6"
								},
								maxLength : {
									param : 8,
									msg : "密码长度最大为8"
								}
							}
						}, {
							name : "rePassword",
							rule : {
								required : {
									msg : "重复密码不能为空"
								},
								password : {
									msg : "重复密码输入有误"
								},
								number : {
									msg : "重复密码请输入6~8位数字"
								},
								minLength : {
									param : 6,
									msg : "重复密码长度最小为6"
								},
								maxLength : {
									param : 8,
									msg : "重复密码长度最大为8"
								}
							}
						}, {
							name : "msgCode",
							rule : {
								required : {
									msg : "手机验证码不能为空"
								},
								numberOrLetter : {
									msg : "手机验证码有误"
								},
								eqLength : {
									param : 6,
									msg : "验证码长度为6位"
								}
							}
						}, {
							name : "code",
							rule : {
								required : {
									msg : "验证码不能为空"
								},
								numberOrLetter : {
									msg : "验证码有误"
								},
								eqLength : {
									param : 4,
									msg : "验证码长度有误"
								}
							}
						}, {
							name : "usinUsername",
							rule : {
								numberOrLetter : {
									msg : "昵称输入有误"
								},
								maxLength : {
									param : 6,
									msg : "昵称长度最大为6"
								}
							}
						} ];
						var a = validateForm("registForm", c);
						if (!a.result) {
							showInfo(a.msg, "error");
							return
						}
						var b = $("#registForm").serializeObject();
						b.usinPassword = $.md5(b.usinPassword).toLowerCase();
						b.rePassword = $.md5(b.rePassword).toLowerCase();
						$.ajax({
							type : "POST",
							url : basepath + "/web/user/regist.do",
							dataType : "json",
							data : $.param(b),
							success : function(d) {
								if (d.code == "OK") {
									hideBox();
									closeZZ();
									UserService.saveUser(b);
									UserService.showUser()
								} else {
									showInfo(d.msg, "error")
								}
							},
							error : function(d, f, e) {
							}
						})
					});
					$("#resetSubmit")
							.click(
									function() {
										var c = [ {
											name : "usinPhone",
											rule : {
												required : {
													msg : "手机号码不能为空"
												},
												mobile : {
													msg : "手机号码有误"
												}
											}
										}, {
											name : "usinPassword",
											rule : {
												required : {
													msg : "密码不能为空"
												},
												password : {
													msg : "密码输入有误"
												},
												number : {
													msg : "密码请输入6~8位数字"
												},
												minLength : {
													param : 6,
													msg : "密码长度最小为6"
												},
												maxLength : {
													param : 8,
													msg : "密码长度最大为8"
												}
											}
										}, {
											name : "rePassword",
											rule : {
												required : {
													msg : "重复密码不能为空"
												},
												password : {
													msg : "重复密码输入有误"
												},
												number : {
													msg : "密码请输入6~8位数字"
												},
												minLength : {
													param : 6,
													msg : "重复密码长度最小为6"
												},
												maxLength : {
													param : 8,
													msg : "密码长度最大为8"
												}
											}
										}, {
											name : "msgCode",
											rule : {
												required : {
													msg : "手机验证码不能为空"
												},
												numberOrLetter : {
													msg : "手机验证码有误"
												},
												eqLength : {
													param : 6,
													msg : "验证码长度为6位"
												}
											}
										} ];
										var a = validateForm("resetForm", c);
										if (!a.result) {
											showInfo(a.msg, "error");
											return
										}
										var b = $("#resetForm")
												.serializeObject();
										b.usinPassword = $.md5(b.usinPassword)
												.toLowerCase();
										$
												.ajax({
													type : "POST",
													url : basepath
															+ "/web/user/resetPasswrod.do",
													dataType : "json",
													data : $.param(b),
													contentType : "application/x-www-form-urlencoded; charset=UTF-8",
													success : function(d) {
														if (d.code == "OK") {
															hideBox();
															closeZZ();
															showInfo(d.msg);
															UserService
																	.removeUser();
															UserService
																	.showUser();
															$("#login").click()
														} else {
															showInfo(d.msg,
																	"error",
																	1000)
														}
													},
													error : function(d, f, e) {
													}
												})
									});
					$("body").on(
							"click",
							".page li[data-page-num]",
							function(a) {
								a.preventDefault();
								if (typeof page.pageFunction == "function") {
									page.params.pageNum = $(this).attr(
											"data-page-num");
									page.pageFunction()
								}
							});
					$("body").on(
							"click",
							".page .pre,.page .next",
							function() {
								if (typeof page.pageFunction == "function") {
									var a = parseInt($(".page .ul-pages").find(
											".current").text());
									$(this).is($(".page .pre")) ? a -= 1
											: a += 1;
									page.params.pageNum = a;
									page.pageFunction()
								}
							});
					$(window).scroll(function() {
						if ($("#zz").length > 0) {
							$(window).scrollTop(0)
						}
					})
				});
function hideBox() {
	$(".LogBox").hide()
}
function showZZ() {
	$('<div class="zz"></div>').appendTo("body");
	$(".zz").css("height", $(document).height())
}
function closeZZ() {
	$(".zz").remove()
}
function showInfo(c, a, b) {
	if (a) {
		$(".information").addClass("label-" + a)
	}
	$(".information").removeClass("infohide").html(c);
	b = b ? b : 3000;
	setTimeout(hideInfo, b)
}
function hideInfo() {
	if ($(".information").css("opacity") != 1) {
		return
	}
	$(".information").addClass("infohide");
	if ($(".information").hasClass("label-error")) {
		$(".information").removeClass("label-error")
	}
	if ($(".information").hasClass("label-warn")) {
		$(".information").removeClass("label-warn")
	}
}
$(window).on("scroll", function() {
	var b = $(document).scrollTop();
	if (b > 0) {
		if ($("#main-container").length != 0) {
			var a = $(window).width(), c = $("#main-container").width();
			if ((a - c) / 2 > 70) {
				$("#go-top").css({
					left : (a - c) / 2 + c + 20
				})
			} else {
				$("#go-top").css({
					left : "auto"
				})
			}
		}
		$("#go-top").fadeIn(function() {
			$(this).removeClass("dn")
		})
	} else {
		$("#go-top").fadeOut(function() {
			$(this).addClass("dn")
		})
	}
});
$("body").on("click", "#go-top .go", function() {
	$("html,body").animate({
		scrollTop : 0
	}, 500)
});
$("#go-top .uc-2vm").hover(function() {
	$("#go-top .uc-2vm-pop").removeClass("dn")
}, function() {
	$("#go-top .uc-2vm-pop").addClass("dn")
});
// JavaScript Document
$(function() {
	$(".content .nav li").click(function() {
		$(this).addClass("current").siblings().removeClass("current");
		var index = $(".content .nav li").index(this);
		$(".content .info-details").eq(index).show().siblings().hide();
		return false;
	});
})
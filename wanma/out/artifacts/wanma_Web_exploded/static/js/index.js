/**
 * Created by George on 1/7/15.
 */
$(function () {
    var flag = false;
    var sliderUl = $(".content-foot ul");
    var liLength = sliderUl.find("li").length;
    sliderUl.width(liLength * 389);
    $(".content-foot .leftBtn").click(function () {
        if (parseInt(sliderUl.css("left")) < 0 && flag==false) {
            flag = true;

            $(".content-foot ul").animate({"left": "+=389px"}, function () {
                flag = false;
            });
        }
    });
    $(".content-foot .rightBtn").click(function () {
        if (parseInt(sliderUl.css("left")) > (liLength - 3) * -389 && flag==false) {
            flag = true;
            $(".content-foot ul").animate({"left": "-=389px"},function(){
                flag = false;
            });
        }
    });


    //Ajax.pageRequest({
    //    url:config.
    //});

    /**
     * 轮播初始化
     */
    $(function () {
        var width = parseInt($(window).width());
        var height = width / 3.2;
        $('#banner').slideBox({'width': width, 'height': height,'hideClickBar':false,'clickBarRadius':10});
    });
});
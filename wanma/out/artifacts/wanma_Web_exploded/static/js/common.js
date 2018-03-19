/**
 * Created by George on 1/6/15.
 */
$(function () {
    $(".header .support").click(function () {
        $(this).find("ul").toggle();
    });
    $('.sn-select-trigger ,.sn-select-content').hover(function () {
        $('.sn-select-content').show();
    }, function () {
        $('.sn-select-content').hide();
    });
    $('.sn-select-content li').click(function (event) {
        var text = $(this).html();
        $('.sn-select-trigger').html(text);
    });

    /**
     * 选择城市
     */
    $('#choose-city').click(function () {
        location.href = config.PChooseCity;
    });

    /**
     * 登录页面
     */
    $('#login-page').click(function(){
        location.href = config.PLogin;
    });

    /**
     * 注册页面
     */
    $('#regist-page').click(function(){
        location.href = config.PRegist;
    });
    
    /**
     * 导航跳转
     * @type {*[]}
     */
    var hrefArr = [config.PIndex, config.PElectricSearch, config.PEnergyIndex, config.PAppDownload/*, config.PSupportHelp*/, config.PSupportRepair, config.PSupportSuggest];
    $('.header .nav a').each(function (i, o) {
        if (i != ($('.header .nav a').length - 1)) {
            $(this).bind('click', function () {
                location.href = hrefArr[i];
            })
        }
    });
    
    /**
     * 菜单跳转
     * @type {*[]}
     */
    var menuArr = [config.PMyCenter, config.PMyPurse, config.PMyOrder, config.PMyIntegral, config.PMyBill, config.PMyCollect, config.PMyEvaluate, config.PMyFootprint, config.PReset];
    $('.menu ul a').each(function (i, o) {
        if (i != ($('.menu ul a').length)) {
            $(this).bind('click', function () {
            	window.location.href = menuArr[i];
            })
        }
    });

    /**
     * 收藏
     */
    $('body').on('click','.dh-collect',function(e){
        e.preventDefault();
        UserService.collect($(this).attr('data-id'));
    });

    /**
     * 退出
     */
    $('body').on('click','.logout',function(e){
        e.preventDefault();
        UserService.logout();
    });

    //分页点击
    $('body').on('click', '.page li[data-page-num]', function (e) {
        e.preventDefault();
        if (typeof config.pageRequest == 'function') {
            config.begin = $(this).attr('data-page-num');
            config.pageRequest();
        }
    });


    //上一页,下一页
    $('body').on('click', '.page .pre,.page .next', function () {
        if (typeof config.pageRequest == 'function') {
            var cPage = parseInt($('.page .ul-pages').find('.current').text());
            if (typeof config.pageRequest == 'function') {
                $(this).is($('.page .pre')) ? cPage -= 1 : cPage += 1;
                config.begin = cPage;
                config.pageRequest();
            }
        }
    });

    /**
     * 个人中心
     */
    $('#my-center').click(function(e){
        e.preventDefault();
        location.href = config.PMyCenter;
    });

    /**
     * 获取当前城市
     */
    $('#currentCity').text(UserService.getCity().currentCity);

    /**
     * 搜电桩
     */
    $('#search-pile').click(function(e){
        var data_name = $('#searchName').val();
        location.href = config.PElectricResult + '?n=' + data_name;
    });

});
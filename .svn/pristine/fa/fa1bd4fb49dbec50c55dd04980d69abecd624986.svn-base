//退出系统
$('body').off('click','#loginOut').on('click','#loginOut',function(){
    var loginUser=window.localStorage.getItem('loginUser');
    window.localStorage.setItem('loginUser','');
    window.top.location.href = basePath + toLogoutUrl;
})
$('body').off('click','#controlSlide').on('click','#controlSlide',function(){
    var miniNavbar=$('body').hasClass('mini-navbar');
    if(miniNavbar==false){
        $('body').removeClass('mini-navbar');
        var $elements = $('.firstText');
        $elements.each(function() {
            var $this = $(this);
            if($this.hasClass('selfChoice')){
                $this.find('ul').show(300).siblings().find('ul').hide(300);
            }
        });
    }else{
        $('body').addClass('mini-navbar');
        $('.firstText').find('ul').hide();
    }
})
$('body').on('click', '.firstText>a', function () {
    var miniNavbar=$('body').hasClass('mini-navbar');
    if(miniNavbar==true){
        $(this).off('click');
        return;
    }else{
        var flag=$(this).next('ul').is(':hidden');
        if(flag==true){
            $(this).parent().css({'borderLeft':'4px solid #32b16c'});
            $(this).parent().siblings().css({'borderLeft':'none'});
            $(this).next('ul').show(300).parent().siblings().find('ul').hide(300);
            $(this).parent().addClass('selfChoice');
            $(this).parent().siblings().removeClass('selfChoice');
            $(this).find('span.fa').addClass('rotate');
            $(this).parent().siblings().find('span.fa').removeClass('rotate');
        }else{
            $(this).parent().css('borderLeft','none');
            $(this).find('span.fa').removeClass('rotate');
            $(this).next('ul').hide(300)
        }
    }

})
$(function(){
    // 加载菜单部分
    loadMenu();
    function loadMenu() {
        $.ajax({
            type: "post",
            url: basePath + getSelfMeunTreeUrl,
            async: true,
            dataType: "json",
            success: function (req) {
                if (req.success == true) {
                    //alert(JSON.stringify(req)) //${ctx}
                    getMenuMap(req);
                    setTimeout(function () {
                        var data = req.dataObject;
                        var html = "";
                        for (var i = 0; i < data.length; i++) {
                            if (i == 0) {
                                html += '<li class="firstText selfChoice" style="border-left: 4px solid #32b16c"><a href="javascript:void(0);"><i><img src="'+ basePath + data[i].imageUrl + '"/></i>'
                                + '<span class="nav-label">' + data[i].contents
                                + '</span><span class="fa arrow rotate"></span></a><ul class="nav nav-second-level">';
                            }
                            else {
                                html += '<li class="firstText"><a href="javascript:void(0);"><i><img src="'+ basePath + data[i].imageUrl + '"/></i>'
                                + '<span class="nav-label">' + data[i].contents
                                + '</span><span class="fa arrow"></span></a><ul class="nav nav-second-level" style="display: none;">';
                            }
                            for (var j = 0; j < data[i].menuList.length; j++) {
                                var ctrStr = data[i].menuList[j].contents;
                                if (ctrStr.indexOf('主页') > -1) {
                                    html += '<li style="display: none;"><a class="J_menuItem" href="' + basePath + data[i].menuList[j].url + '?menuId='
                                    + data[i].menuList[j].menuId + '">' + data[i].menuList[j].contents + '</a></li>';
                                } else {
                                    html += '<li><a class="J_menuItem" href="' + basePath + data[i].menuList[j].url + '?menuId='
                                    + data[i].menuList[j].menuId + '">' + data[i].menuList[j].contents + '</a></li>';
                                }

                            }
                            html += '</ul></li>';
                        }
                        $(".nav-header").after(html);
                    }, 200)
                }
            }
        });
    }
    function getMenuMap(req) {
        var data = req.dataObject;
        var menuMap = {};
        for (var i = 0; i < data.length; i++) {
            var menuList = data[i].menuList;
            for (var j = 0; j < menuList.length; j++) {
                menuMap[menuList[j].contents] = menuList[j].menuId;
            }
        }
        var menuMap = JSON.stringify(menuMap);
        window.localStorage.setItem('menuMap', menuMap);
    }
})
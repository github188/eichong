/* 首页通栏幻灯 */$(function () {
    var $con = $('#gg'), 
    $box = $con.find('#ggBox'), 
    $btns = $con.find('#ggBtns'),
    intervalTime=3000,
    i = 0, 
    autoChange = function () {
    	var size=$btns.children("a").length;
        i += 1;
        if (i == size) { i = 0; }
        $btns.find('a:eq(' + i + ')').addClass('ggOn').siblings().removeClass('ggOn');
        var curr = $box.find('a:eq(' + i + ')'), prev = curr.siblings();
        prev.css('z-index', 2);
        curr.css('z-index', 3).animate({
            'opacity': 1
        }, 150, function () {
            prev.css({
                'z-index': 1, 'opacity': 0.1
            });
        });
    }, 
    loop = setInterval(autoChange, intervalTime);
    $con.hover(function () {
        clearInterval(loop);
    }, function () {
        loop = setInterval(autoChange, intervalTime);
    });
    $('body').on('click', '#ggBtns a', function (e) {
        i = $(this).index() - 1;
        autoChange();
    });
});
window.onload = function () {
    var menuArr =
        ['/my/info', '/my/wallet', '/my/book', config.PMyIntegral,
            '/my/order', '/my/collect', '/my/comment', config.PMyFootprint];
    $.each(menuArr, function (idx, o) {
        if (location.href.indexOf(menuArr[idx]) != -1) {
            $('#centerMenu li a').removeClass('current').eq(idx).addClass('current');return;
        }
    });
};

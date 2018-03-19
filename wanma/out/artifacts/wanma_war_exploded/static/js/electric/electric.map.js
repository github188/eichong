var myMap = new BMap.Map("map"),
    mapWforGPS = new BMapLib.MapWrapper(myMap, BMapLib.COORD_TYPE_GPS),
    geo,timer, count = 0;

//init map
myMap.centerAndZoom('上海', 12);
myMap.enableScrollWheelZoom();

/**
 * tab 切换
 */
$(".search-title li").click(function () {
    $(this).addClass("current").siblings("li").removeClass("current");
    var liindex = $(this).index();
    $(".toggle").eq(liindex).show().siblings(".none").hide();
});


//获取电桩数据
Ajax.custom({
    url: createCommonInterfaceUrl('electricPileMap/getElectricPileMapList')
}, function (res) {
    if (!res || !res.data)return;
    var data = res.data || [];
    for (var i = 0; i < data.length; i++) {
        //添加gps坐标mkr
        mapWforGPS.addOverlay(new BMap.Marker(new BMap.Point(data[i].Longitude, data[i].Latitude)));
    }
});


function getGeo(callback) {
    if (!navigator.geolocation) {
        log('您的浏览器不支持定位功能!');
        return;
    }
    function navigatorGeo() {
        navigator.geolocation.getCurrentPosition(function (pos) {
            log('latitude:' + pos.coords.latitude + ',longitude:' + pos.coords.longitude);
            if (pos || count >= 3) {
                clearTimeout(timer);
                geo = pos.coords;
                if ($.isFunction(callback)) callback(geo);
            }
        }, function (error) {
            log('定位失败:' + error);
        });
    }

    //定位
    navigatorGeo();

    //失败后尝试再次定位
    timer = setTimeout(navigatorGeo, 1000);
}

getGeo();




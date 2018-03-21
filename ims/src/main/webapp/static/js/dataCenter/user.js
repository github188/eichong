$(function(){
    setTimeout(function(){
        loadshishiData();
        var intervalIdloadRunTime;
        function loadshishiData(){
            realData();
            clearInterval(intervalIdloadRunTime);
            intervalIdloadRunTime=setInterval('realData()',1000*60);
        }
    },1000*60);
    map.setFeatures(['bg','road','building']);//去掉地图默认标注
})

function realData(){
    var provinceCode = window.localStorage.getItem('provinceCode');
    var cityCode = window.localStorage.getItem('cityCode');
    var powerstationId = window.localStorage.getItem('powerstationId');
    var cpyId = window.localStorage.getItem('cpyId')?undefined:'';
    getElectricPileMap(provinceCode,cityCode,cpyId,3);
}

var map = new AMap.Map('container', {
    resizeEnable: true,
    zoom:5,
    zooms:[5,14],//5-9 9-11 12-14
    mapStyle: 'amap://styles/blue',
    doubleClickZoom:false,
    center: [114.31620010268132, 30.58108412692075]
});

// 百度地图的开发者秘钥
var token = 'fHrNQj6DHTjZtfTvfqbsuvTzKc5V9SBl';
var url = 'http://api.map.baidu.com/geocoder/v2/?output=json&ak=' + token + '&address=';
var chartData = [];
var mapData;
var markers = [];

//请求所有省级数据获取到所有返回城市到经纬度(第一级)
function getElectricPileMap(provinceCode,cityCode,cpyId,type) {
    if(map.getZoom() < 12){
        toLoadPoint(provinceCode,cityCode,cpyId,type);
    }else if(map.getZoom() >= 12){
        toLoadPointRepeat(provinceCode,cityCode,cpyId,type);
    }
}

var _onZoomEnd = function(e) {//地图层级变化
    provinceCode = window.localStorage.getItem('provinceCode');
    cityCode = window.localStorage.getItem('cityCode');
    powerstationId = window.localStorage.getItem('powerstationId');
    cpyId = window.localStorage.getItem('cpyId');
    //console.log(cpyId);
    if(map.getZoom()==5){
        var centerA = [114.31620010268132, 30.58108412692075];
        map.setCenter(centerA);
        clearLocalStorageCon();
        toLoadPoint('','',cpyId,3)
    }else if(map.getZoom()==10){
        if(provinceCode == '' ){
            return;
        }else{
            toLoadPoint(provinceCode,'',cpyId,3)
        }
    }
}
AMap.event.addListener(map, 'zoomend', _onZoomEnd);//监听地图zoom变化


//重置缓存
function clearLocalStorageCon(){
    window.localStorage.setItem('cityCode', '');
    window.localStorage.setItem('provinceCode', '');
    window.localStorage.setItem('powerstationId', '');
}
//获取到数据渲染地图
function drawMap(place){
    var createMarker = function(data){
        centerPoint=[];
        centerPoint.push(data);
        var div = document.createElement('div');
        div.className = 'circle';
        //根据电量的大小画不同大小的圆
        if(map.getZoom() >=5 && map.getZoom() < 9){
            if(data.userCount >0 && data.userCount <= 50){
                var size = 16;
                div.style.backgroundColor = '#ff7d00';
                div.style.width = size + 'px';
                div.style.height = size + 'px';
                div.style.borderRadius = size/2 + 'px';
                div.style.opacity = 0.8;
            }else if(data.userCount > 50 && data.userCount <= 200){
                var size = 26;
                div.style.backgroundColor = '#ff7d00';
                div.style.width = size + 'px';
                div.style.height = size + 'px';
                div.style.borderRadius = size/2 + 'px';
                div.style.opacity = 0.8;
            }else if(data.userCount > 200){
                var size = 36;
                div.style.backgroundColor = '#ff7d00';
                div.style.width = size + 'px';
                div.style.height = size + 'px';
                div.style.borderRadius = size/2 + 'px';
                div.style.opacity = 0.8;
            }
        }else if(map.getZoom() >=9 && map.getZoom() < 12){
            if(data.userCount >0 && data.userCount <= 20){
                var size = 16;
                div.style.backgroundColor = '#ff7d00';
                div.style.width = size + 'px';
                div.style.height = size + 'px';
                div.style.borderRadius = size/2 + 'px';
                div.style.opacity = 0.8;
            }else if(data.userCount > 20 && data.userCount <= 100){
                var size = 26;
                div.style.backgroundColor = '#ff7d00';
                div.style.width = size + 'px';
                div.style.height = size + 'px';
                div.style.borderRadius = size/2 + 'px';
                div.style.opacity = 0.8;
            }else if(data.userCount > 100){
                var size = 36;
                div.style.backgroundColor = '#ff7d00';
                div.style.width = size + 'px';
                div.style.height = size + 'px';
                div.style.borderRadius = size/2 + 'px';
                div.style.opacity = 0.8;
            }
        }else{
            if(data.userCount >0 && data.userCount <= 20){
                var size = 16;
                div.style.backgroundColor = '#ff7d00';
                div.style.width = size + 'px';
                div.style.height = size + 'px';
                div.style.borderRadius = size/2 + 'px';
                div.style.opacity = 0.8;
            }else if(data.userCount > 20 && data.userCount <= 100){
                var size = 26;
                div.style.backgroundColor = '#ff7d00';
                div.style.width = size + 'px';
                div.style.height = size + 'px';
                div.style.borderRadius = size/2 + 'px';
                div.style.opacity = 0.8;
            }else if(data.userCount > 100){
                var size = 36;
                div.style.backgroundColor = '#ff7d00';
                div.style.width = size + 'px';
                div.style.height = size + 'px';
                div.style.borderRadius = size/2 + 'px';
                div.style.opacity = 0.8;
            }
        }

        var marker = new AMap.Marker({
            content: div,
            animation:'',
            title: data.name + '  用户数量:'+data.userCount,
            position:data.value,
            offset: new AMap.Pixel(-24, -35),
            extData:{
                name:data.name,
                center:data.value,
                provinceCode:data.provinceCode || window.localStorage.getItem('provinceCode'),
                cityCode:data.cityCode || window.localStorage.getItem('cityCode'),
                powerstationId:data.powerstationId || window.localStorage.getItem('powerstationId'),
                cpyId:window.localStorage.getItem('cpyId') || ''
            }
        });
        marker.content = data.name + '<br/>' + '用户数量：'+data.userCount;
        marker.on('mouseover', markerClick);
        marker.emit('mouseout', {target: marker});
        marker.setMap(map);
        function markerClick(e) {
            infoWindow.setContent(e.target.content);
            infoWindow.open(map, e.target.getPosition());
        }
        marker.off('click').on('click',markerdblClick);
        //点击进入下一层
        function markerdblClick(e){
            var provinceCode = e.target.G.extData.provinceCode;
            var cityCode = e.target.G.extData.cityCode;
            var powerstationId = e.target.G.extData.powerstationId;
            var cpyId = e.target.G.extData.cpyId;
            window.localStorage.setItem('cityCode', cityCode);
            window.localStorage.setItem('provinceCode', provinceCode);
            window.localStorage.setItem('powerstationId', powerstationId);
            window.localStorage.setItem('cpyId', cpyId);
            if(provinceCode && cityCode=='' && powerstationId== ''){//进入第二层
                map.clearMap();
                //进入第二级图层
                map.setZoom(9);
                toLoadPoint(provinceCode,'',cpyId,3);
                //删除第一级信息窗
                $('.amap-info-content').parent().remove();
            }
            if(provinceCode &&cityCode && powerstationId== ''){//进入第三层
                map.clearMap();
                map.setZoom(12);
                toLoadPointRepeat(provinceCode,cityCode,cpyId,3);
                $('.amap-info-content').parent().remove();
            }
            if(provinceCode &&cityCode && powerstationId != ''){//跳出地图
                window.parent.location.href = 'chargingPoint/chargePointHomePage.html?powerstationId='+powerstationId;
            }
        }
    }
    var infoWindow = new AMap.InfoWindow({offset: new AMap.Pixel(0, -30),class:infoWindow});
    var chartDataLast = chartData[chartData.length - 1];
    var marker = createMarker(chartDataLast);
    markers.push(marker);
    //设置地图的中心点
    var center = centerPoint[0].value;
    map.setCenter(center);
}

//加载第一二级
function toLoadPoint(provinceCode,cityCode,cpyId,type){
    map.clearMap();
    $('.chargiePoint').hide();
    $('#chargeRecord').hide();
    $('#userRank').hide();
    $('.historicalData').show();
    $('.realTimeData').show();
    toLoadPointData(provinceCode,cityCode,cpyId,type);
}
//加载第三级
function toLoadPointRepeat(provinceCode,cityCode,cpyId,type){
    map.clearMap();
    $('.historicalData').hide();
    $('.realTimeData').hide();
    $('.chargiePoint').show();
    $('#userRank').show();
    $('#chargeRecord').show();
    toLoadPointData(provinceCode,cityCode,cpyId,type);
    getUserStartChargeRecord(provinceCode,cityCode,cpyId,type);
    getUserChargeRank(provinceCode,cityCode,cpyId,type);
}
//输入地点名返回数据
function toLoadPointData(provinceCode,cityCode,cpyId,type){
    $.ajax({
        type: "post",
        url: basePath + getMapDataUrl,
        async: false,
        data:{
            provinceCode:provinceCode,
            cityCode:cityCode,
            cpyId:cpyId,
            type:type
        },
        success: function (req) {
            mapData = req.dataObject;
            exceptionHandle(mapData);
            //判断data参数是否为空，三种情况
            for (var i = 0; i < mapData.length; i++) {
                var place = mapData[i].provinceName || mapData[i].cityName || mapData[i].powerstationName;
                var provinceCode = mapData[i].provinceCode;
                var cityCode = mapData[i].cityCode;
                var powerstationId = mapData[i].powerstationId;
                var userCount = mapData[i].userCount;
                if (place) {
                    (function (place, provinceCode, cityCode, powerstationId, userCount) {
                        $.getJSON(url + place + '&callback=?', function (res) {
                            var loc;
                            //console.log('hou'+place);
                            if (res.status === 0) {
                                loc = res.result.location;
                                chartData.push({
                                    name: place,
                                    value: [loc.lng, loc.lat],
                                    provinceCode: provinceCode,
                                    cityCode:cityCode,
                                    powerstationId:powerstationId,
                                    userCount: userCount
                                });
                                drawMap(place);
                            } else {
                                layer.closeAll();
                                layer.open({
                                    type: 1,
                                    offset: '100px',
                                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                                    shadeClose: false,
                                    closeBtn: 1,
                                    area: ['310px', '160px'],//宽高
                                    content: '高德没有找到地址信息',
                                    time: 3000,
                                    btn: ["确定"]
                                });
                            }
                        })
                    }(place, provinceCode,cityCode,powerstationId, userCount));
                }
            }
        }
    })
    toLoadHistoryData(provinceCode,cityCode,cpyId,type);
}
//加载历史数据
function toLoadHistoryData(provinceCode,cityCode,cpyId,type){
    var index=layer.load(1);
    $.ajax({
        type: "post",
        url: basePath + getHistoryDataUrl,
        async: false,
        data: {
            type:type,
            provinceCode:provinceCode,
            cityCode:cityCode,
            cpyId:cpyId
        },
        success: function (req) {
            layer.close(index);
            var data = req.dataObject;
            exceptionHandle(data);
            $('.userNomalCount').html(data.userNomalCount);
            $('.consumAmount').html(data.consumAmount);
            $('.accumulativeCharge').html(data.chargeCount);
            $('#cityChargeCount').html(data.chargeCount);
        }
    });
    toLoadRealTimeData(provinceCode,cityCode,cpyId,type);
}

//加载实时数据
function toLoadRealTimeData(provinceCode,cityCode,cpyId,type){
    //var index=layer.load(1);
    $.ajax({
        type: "post",
        url: basePath + getChargeRealTimeDateUrl,
        async: false,
        data: {
            type:type,
            provinceCode:provinceCode,
            cityCode:cityCode,
            cpyId:cpyId
        },
        success: function (req) {
            //layer.close(index);
            removeLoading();
            //var data = req.dataObject;
            exceptionHandle(req);
            $('.chargeCount').html(req.chargeCount);
            $('.chargeUser').html(req.chargeUser);
            $('.newUser').html(req.newUser);
            $('#newChargeCount').html(req.chargeCount);
        }
    });
}

function timestampToTime(timestamp) {
    var date = new Date(timestamp);
    Y = date.getFullYear() + '-';
    M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    D = date.getDate() + ' ';
    h = date.getHours() + ':';
    m = date.getMinutes() + ':';
    s = date.getSeconds();
    return Y+M+D+h+m+s;
}

//用户维度开始充电记录列表
function getUserStartChargeRecord(provinceCode,cityCode,cpyId,type){
    var cpyId = window.localStorage.getItem('cpyId');
    var index=layer.load(1);
    $.ajax({
        type: "post",
        url: basePath + getUserStartChargeRecordUrl,
        async: false,
        data: {
            cityCode:cityCode//330100
        },
        success: function (req) {
            layer.close(index);
            var data = req.dataObject;
            exceptionHandle(data);
            var beginLi = '';
            for(var i = 0; i < data.length; i ++ ){
                timestampToTime(data[i].time);
                var timeData = timestampToTime(data[i].time);
                //console.log(timeData)
                beginLi += '<li>'+
                    '<i>'+data[i].chargeBegintime+'</i>'+
                     '<i>用户</i>'+
                    '<i>'+data[i].userId+'</i>'+
                    '<i>'+data[i].powerStationName+'</i>'+
                    '<i>开始充电</i>'+
                    '</li>'
            }
            $('.beginData1').html(beginLi);

            //广告文字向上滚动
            $("#chargeAmountTwo").myScroll({
                speed:60, //数值越大，速度越慢
                rowHeight:36 //li的高度
            });

        }
    });
}

//排行榜
function getUserChargeRank(provinceCode,cityCode,cpyId,type){
    var index=layer.load(1);
    $.ajax({
        type: "post",
        url: basePath + getUserChargeRankUrl,
        async: false,
        data: {
            provinceCode:provinceCode,
            cityCode:cityCode
        },
        success: function (req) {
            layer.close(index);
            var data = req.dataObject;
            exceptionHandle(data);
            var rankLi = '';
            for(var i = 0; i < data.length; i ++ ){
                //console.log(i);
                 rankLi += '<tr>'+
                    '<td class="span1">'+(i+1)+'</td>'+
                    '<td class="span2 userAccount1">'+data[i].userAccount+'</td>'+
                    '<td class="span3 chargeCount1">'+data[i].chargeCount+'</td>'+
                    '<td class="span4 chargeMoney1">'+data[i].chargeMoney+'</td>'+
                    '</tr>'
                $('#rankBox').html(rankLi);
            }

        }
    });
}



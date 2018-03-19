
$(function(){
    setTimeout(function(){
        loadshishiData();
        var intervalIdloadRunTime;
        function loadshishiData(){
            realData();
            clearInterval(intervalIdloadRunTime);
            intervalIdloadRunTime=setInterval('realData()',2000*60);
        }
    },2000*60);
    map.setFeatures(['bg','road','building']);//去掉地图默认标注
});
function realData(){
    var provinceCode = window.localStorage.getItem('provinceCode');
    var cityCode = window.localStorage.getItem('cityCode');
    var powerstationId = window.localStorage.getItem('powerstationId');
    var cpyId = window.localStorage.getItem('cpyId')?undefined:'';
    getElectricPileMap(provinceCode,cityCode,cpyId,1);
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
        //console.log(2);
        //toLoadPointData(provinceCode,cityCode,cpyId,type);
        toLoadPoint(provinceCode,cityCode,cpyId,type);
    }else if(map.getZoom() >= 12){
        toLoadPointRepeat(provinceCode,cityCode,cpyId,type);
    }

}

var _onZoomEnd = function(e) {//地图层级变化
    getLocalStorage();//获取缓存
    var cpyId = window.localStorage.getItem('cpyId');
    if(map.getZoom()==5){
        var centerA = [114.31620010268132, 30.58108412692075];
        map.setCenter(centerA);
        clearLocalStorageCon();
        toLoadPoint('','',cpyId,1);
    }else if(map.getZoom()==10){
        if(provinceCode == '' ){
            return;
        }else{
            toLoadPoint(provinceCode,'',cpyId,1)
        }
    }
}
AMap.event.addListener(map, 'zoomend', _onZoomEnd);//监听地图zoom变化


//重置缓存
function clearLocalStorageCon(){
    window.localStorage.setItem('cityCode', '');
    window.localStorage.setItem('provinceCode', '');
    window.localStorage.setItem('powerstationId', '');
    //window.localStorage.setItem('cpyId', '');
}

//获取缓存
function getLocalStorage(){
    provinceCode = window.localStorage.getItem('provinceCode');
    cityCode = window.localStorage.getItem('cityCode');
    powerstationId = window.localStorage.getItem('powerstationId');
    cpyId = window.localStorage.getItem('cpyId')?undefined:'';
}

//获取到数据渲染地图
function drawMap(place){
    var createMarker = function(data){
        centerPoint=[];
        centerPoint.push(data);
        var div = document.createElement('div');
        div.className = 'circle';
        //根据电量的大小画不同大小的圆
        if(data.chargeCount >0 && data.chargeCount <= 10000){
            var size = 16;
            div.style.backgroundColor = '#ff7d00';
            div.style.width = size + 'px';
            div.style.height = size + 'px';
            div.style.borderRadius = size/2 + 'px';
            div.style.opacity = 0.8;
        }else if(data.chargeCount > 10000 && data.chargeCount < 30000){
            var size = 26;
            div.style.backgroundColor = '#ff7d00';
            div.style.width = size + 'px';
            div.style.height = size + 'px';
            div.style.borderRadius = size/2 + 'px';
            div.style.opacity = 0.8;
        }else if(data.chargeCount >= 30000){
            var size = 36;
            div.style.backgroundColor = '#ff7d00';
            div.style.width = size + 'px';
            div.style.height = size + 'px';
            div.style.borderRadius = size/2 + 'px';
            div.style.opacity = 0.8;
        }
        var marker = new AMap.Marker({
            content: div,
            animation:'',
            title: data.name + '  充电总数量:'+data.chargeCount,
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
        marker.content = data.name + '<br/>' + '充电数量：'+data.chargeCount;
        marker.on('click', markerClick);
        marker.emit('mouseout', {target: marker});
        marker.setMap(map);
        function markerClick(e) {
            infoWindow.setContent(e.target.content);
            infoWindow.open(map, e.target.getPosition());
        }
        marker.off('dblclick').on('dblclick',markerdblClick);
        //双击进入下一层
        function markerdblClick(e){//鼠标双击事件
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
                toLoadPoint(provinceCode,'',cpyId,1);
                //删除第一级信息窗
                $('.amap-info-content').parent().remove();
            }
            if(provinceCode &&cityCode && powerstationId== ''){//进入第三层
                map.clearMap();
                map.setZoom(12);
                toLoadPointRepeat(provinceCode,cityCode,cpyId,1);
                $('.amap-info-content').parent().remove();
            }
            if(provinceCode &&cityCode && powerstationId != ''){//跳出地图
                window.parent.location.href = 'chargingPoint/chargePointHomePage.html?powerstationId='+powerstationId;
            }
        }
    }
    //信息窗
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
    $('.chargeAmountOne').hide();
    $('.chargeAmountTwo').hide();
    $('.historicalData').show();
    $('.realTimeData').show();
    toLoadPointData(provinceCode,cityCode,cpyId,type);
    //console.log(4);
}
//加载第三级
function toLoadPointRepeat(provinceCode,cityCode,cpyId,type){
    map.clearMap();
    $('.historicalData').hide();
    $('.realTimeData').hide();
    $('.chargiePoint').show();
    $('.chargeAmountOne').show();
    $('.chargeAmountTwo').show();
    toLoadPointData(provinceCode,cityCode,cpyId,type);
    getChargePowerCurve(provinceCode,cityCode,cpyId,type);
    getChargeCount5DaysData(provinceCode,cityCode,cpyId,type);
}
//输入地点名返回数据
function toLoadPointData(provinceCode,cityCode,cpyId,type){
    //console.log(cpyId);
    var index=layer.load(1);
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
            layer.close(index);
            mapData = req.dataObject;
            for (var i = 0; i < mapData.length; i++) {
                var place = mapData[i].provinceName || mapData[i].cityName || mapData[i].powerstationName;
                var provinceCode = mapData[i].provinceCode;
                var cityCode = mapData[i].cityCode;
                var powerstationId = mapData[i].powerstationId;
                var chargeCount = mapData[i].chargeCount;
                if (place) {
                    (function (place, provinceCode, cityCode, powerstationId, chargeCount) {
                        $.getJSON(url + place + '&callback=?', function (res) {
                            var loc;
                            //place进入的先后顺序不同，所以地图返回的中心点的经纬度不同
                            //console.log('hou'+place);
                            if (res.status === 0) {
                                loc = res.result.location;
                                chartData.push({
                                    name: place,
                                    value: [loc.lng, loc.lat],
                                    provinceCode: provinceCode,
                                    cityCode:cityCode,
                                    powerstationId:powerstationId,
                                    chargeCount: chargeCount
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
                    }(place, provinceCode,cityCode,powerstationId, chargeCount));
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
            $('.chargeCount1').html(data.chargeCount);
            $('.chargeTime1').html(data.chargeTime);
            $('.orderCount1').html(data.orderCount);
            $('#cityChargeCount').html(data.chargeCount);
        }
    });
    toLoadRealTimeData(provinceCode,cityCode,cpyId,type);
}

//加载实时数据
function toLoadRealTimeData(provinceCode,cityCode,cpyId,type){
    var index=layer.load(1);
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
            layer.close(index);
            var data = req.dataObject;
            $('.chargeCount').html(data.chargeCount);
            $('.orderCount').html(data.orderCount);
            $('.errorCount').html(data.errorCount);
            $('#newChargeCount').html(data.chargeCount);
            $('#chargeMoney').html(data.chargeMoney);

        }
    });
}


var chargeAmountOne;
function getChargePowerCurve(provinceCode,cityCode,cpyId,type){//充电电量
    var cpyId = window.localStorage.getItem('cpyId');
    var index=layer.load(1);
    $.ajax({
        type: "post",
        url: basePath + getChargePowerCurveUrl,
        async: false,
        data: {
            cityCode:cityCode,//110100
            cpyId:cpyId//295
        },
        success: function (req) {
            layer.close(index);
            var data = req.dataObject;
            var myChartTwo = echarts.init(document.getElementById('chargeAmountOne'));
            chargeAmountOne = {
                backgroundColor: '#383838',
                title: {
                    text: '实时充电电量曲线',
                    x: '20px',
                    y: 'top',
                    textStyle: {
                        color: '#fff',
                        fontStyle: 'normal',
                        fontWeight: 'normal',
                        fontFamily: '微软雅黑',
                        fontSize: 14
                    }
                },
                color: ['#ff7d00', '#ff8900', '#b7a3df', '#6cbce9'],
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'line',
                        lineStyle: {
                            color: '#cccccc',
                            width: 1,
                            type: 'dashed'
                        }
                    }
                },
                legend: {
                    bottom: 2,
                    data: []
                },
                grid: {
                    show: false,
                    left: '50',
                    right: '40',
                    bottom: '10%',
                    containLabel: true
                },
                xAxis: {
                    type: 'category',
                    show:true,
                    boundaryGap: true,
                    textStyle: {
                        color: '#fff'
                    },
                    data: [],
                    axisLine:{
                        lineStyle:{
                            color:'#fff',
                            width:2,//这里是为了突出显示加上的
                        }
                    },
                    axisLabel: {
                        show: true,
                        textStyle: {
                            color: '#fff'
                        }
                    },
                    splitLine: {
                        show: false
                    },
                    axisTick: {
                        show: true
                    }
                },
                yAxis: [{
                    type: 'value',
                    show:false,
                    minInterval: 1,
                    axisLine: {
                        show: false
                    },
                    axisTick: {
                        show: false
                    }
                }],
                series: [{
                    data: [],
                    type: 'line',
                    smooth: true
                }]
            };
            // 使用刚指定的配置项和数据显示图表。
            chargeAmountOne.xAxis.data = data.time;
            for(var i = 0; i < chargeAmountOne.series.length; i ++){
                chargeAmountOne.series[i].data = data.value;
            }
            myChartTwo.setOption(chargeAmountOne);

        }
    });
}


//加载近五日充电量
var chargeAmountTwo;
function getChargeCount5DaysData(provinceCode,cityCode,cpyId,type){
    var cpyId = window.localStorage.getItem('cpyId');
    var index=layer.load(1);
    $.ajax({
        type: "post",
        url: basePath + getChargeCount5DaysUrl,
        async: false,
        data: {
            provinceCode:provinceCode,
            cityCode:cityCode,
            cpyId:cpyId
        },
        success: function (req) {
            layer.close(index);
            var data = req.dataObject;
            var myChartTwo = echarts.init(document.getElementById('chargeAmountTwo'));
            chargeAmountTwo = {
                backgroundColor: '#383838',
                title: {
                    text: '近五日电量',
                    x: '20px',
                    y: 'top',
                    textStyle: {
                        color: '#fff',
                        fontStyle: 'normal',
                        fontWeight: 'normal',
                        fontFamily: '微软雅黑',
                        fontSize: 12
                    }
                },
                tooltip: {

                },
                color: ['#ff7d00', '#ff8900', '#b7a3df', '#6cbce9'],
                xAxis: {
                    type : 'category',
                    data: [],
                    splitLine:{
                        show:false
                    },
                    axisLine: {show: false},
                    axisTick:{
                        show:false
                    },
                    axisLabel: {
                        show: true,
                        interval: 0,
                        color:'#fff',
                        textStyle: {
                            color: '#fff'
                        }
                    }
                },
                yAxis: {
                    show:false
                },
                series: [{
                    type: 'bar',
                    data: []
                }]
            };

            chargeAmountTwo.xAxis.data = data.time;
            for(var i = 0; i < chargeAmountTwo.series.length; i ++){
                chargeAmountTwo.series[i].data = data.value;
            }
            // 使用刚指定的配置项和数据显示图表。
            myChartTwo.setOption(chargeAmountTwo);
        }
    });
}


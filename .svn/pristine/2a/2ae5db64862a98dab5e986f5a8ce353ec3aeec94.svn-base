// 百度地图的开发者秘钥
var token = 'fHrNQj6DHTjZtfTvfqbsuvTzKc5V9SBl';
//var token = 'wGpSoa5hAbbdjpkRQLVwMSPEGLFYxDpp';
var url = 'http://api.map.baidu.com/geocoder/v2/?output=json&ak=' + token + '&address=';
var myChart = echarts.init(document.getElementById('mapChart'));
var chartData = [];
var mapData;
$(function(){
    getElectricPileMap();
    setTimeout('getCountReportCity()',300);
})
//请求所有数据获取到所有返回城市到经纬度
function getElectricPileMap() {
    $.ajax({
        type: "post",
        url: basePath + getElectricPileMapUrl,
        async: true,
        success: function (req) {
            if (req.success == true) {
                mapData = req.dataObject;
                for (var i = 0; i < mapData.length; i++) {
                    var place = mapData[i].cityName;
                    var cityCode = mapData[i].cityCode;
                    var electricPileNum = mapData[i].electricPileNum;
                    var powerStationNum = mapData[i].powerStationNum;
                    if (place) {
                        !function (place, cityCode, electricPileNum, powerStationNum) {
                            $.getJSON(url + place + '&callback=?', function (res) {
                                var loc;
                                if (res.status === 0) {
                                    loc = res.result.location;
                                    chartData.push({
                                        name: place,
                                        value: [loc.lng, loc.lat],
                                        cityCode: cityCode,
                                        electricPileNum: electricPileNum,
                                        powerStationNum: powerStationNum
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
                                        content: '百度没有找到地址信息',
                                        time: 3000,
                                        btn: ["确定"]
                                    });
                                }
                            })
                        }(place, cityCode, electricPileNum, powerStationNum);
                    }
                }

            } else if (req.status == 9001) {
                layer.closeAll();
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '160px'],//宽高
                    content: '会话超时，请重新登陆！',
                    btn: ["确定"],
                    yes: function (index, layero) {
                        layer.closeAll();
                        window.top.location.href = basePath + toLoginUrl;
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        window.top.location.href = basePath + toLoginUrl;
                    }
                });

            } else {
                layer.closeAll();
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '160px'],//宽高
                    content: '系统出错',
                    btn: ["确定"],
                    yes: function (index, layero) {
                        layer.closeAll();
                        window.top.location.href = basePath + toLoginUrl;
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        window.top.location.href = basePath + toLoginUrl;
                    }
                });
            }
        }
    });

}
function drawMap(name) {
    var option = {
        backgroundColor: '#404a59',
        title: {
            text: '实时监控',
            left: 20,
            textStyle: {
                color: '#fff'
            }
        },  //浮层设置
        tooltip: {
            trigger: 'item',
            formatter: function (params, ticket, callback) {
                var $pna = params.name;
                var mapData = params.data;
                var cityCode = mapData.cityCode;
                window.localStorage.setItem('monitor_cityCode', cityCode);
                var cityName = mapData.name;
                window.localStorage.setItem('monitor_cityName', cityName);
                var areaCode='';
                var areaName='请选择';
                window.localStorage.setItem('monitor_areaCode', areaCode);
                window.localStorage.setItem('monitor_areaName', areaName);
                var powerStationName='';
                var powerStationId='';
                window.localStorage.setItem('monitor_powerStationName', powerStationName);
                window.localStorage.setItem('monitor_powerStationId', powerStationId);
                var res = "";
                if (mapData.name == $pna) {
                    res = '<div>' + mapData.name + ' 电桩:' + mapData.electricPileNum + ' 充电点:' + mapData.powerStationNum + '</div>';//设置自定义数据的模板，这里的模板是文字
                }
                return res;
            }
        },
        toolbox: {
            show: true
        },
        geo: {
            map: 'china',
            label: {
                emphasis: {
                    show: false
                }
            },
            left: 20,
            roam: true,
            itemStyle: {
                normal: {
                    areaColor: '#323c48',
                    borderColor: '#111'
                },
                emphasis: {
                    areaColor: '#2a333d'
                }
            }
        },
        series: [{
            name: '充电数量',
            type: 'scatter',
            coordinateSystem: 'geo',
            data: chartData,
            symbolSize: function (val) {
                return val[1] / 4;
            },
            label: {
                normal: {
                    formatter: '{b}',
                    position: 'right',
                    show: false
                },
                emphasis: {
                    show: false
                }
            },
            itemStyle: {
                normal: {
                    color: '#ddb926'
                }
            }
        }]
    }
    myChart.setOption(option)
}

//处理根据城市模糊查询
$('body').off('keyup').on('keyup', '#cityName', function () {
    setTimeout('getCityListByName()',500);
    $('.searchUl').css('display','block');

})

function getCityListByName(){
    var cityName = $('#cityName').val();
    var reg=/^[\u4E00-\u9FA5]+$/;
    if(cityName==''){
        $('.searchUl').html('');
        $('.searchUl').css('display','none');
        $('.entryMap').css('display','none');
    }else{
       if(reg.test(cityName)){
           $.ajax({
               type: "post",
               url: basePath + getCityListByNameUrl,
               async: true,
               data: {
                   cityName:cityName
               },
               success: function (data) {
                   if (data.success == true) {
                       var data = data.dataObject;
                       var cityNameLi = '';
                       for (var i = 0; i < data.length; i++) {
                           cityNameLi += '<li data-option="' + data[i].cityId + '">' + data[i].cityName + '</li>';
                       }
                       $('.searchUl').html(cityNameLi);
                   }else if (data.status == 9001) {
                       layer.closeAll();
                       layer.open({
                           type: 1,
                           offset: '100px',
                           title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                           shadeClose: false,
                           closeBtn: 1,
                           area: ['310px', '160px'],//宽高
                           content: '会话超时，请重新登陆！',
                           btn: ["确定"],
                           yes: function (index, layero) {
                               layer.closeAll();
                               window.top.location.href = basePath + toLoginUrl;
                           },
                           cancel: function (index, layero) {
                               layer.closeAll();
                               window.top.location.href = basePath + toLoginUrl;
                           }
                       });

                   } else {
                       /*layer.closeAll();
                        layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '160px'],//宽高
                        content:data.msg,
                        btn: ["确定"]
                        });*/
                   }
               }
           });
       }
    }

}
//点击模糊查询的城市，对input赋值
$('body').off('click','.searchUl li').on('click','.searchUl li',function(){
    $('.searchUl').css('display','none');
    var cityCode=$(this).attr('data-option');
    window.localStorage.setItem('monitor_cityCode',cityCode);
    var cityName=$(this).text();
    window.localStorage.setItem('monitor_cityName',cityName);
    var areaCode='';
    var areaName='请选择';
    window.localStorage.setItem('monitor_areaCode', areaCode);
    window.localStorage.setItem('monitor_areaName', areaName);
    var powerStationName='';
    var powerStationId='';
    window.localStorage.setItem('monitor_powerStationName', powerStationName);
    window.localStorage.setItem('monitor_powerStationId', powerStationId);
    $('input[name=cityName]').val(cityCode);
    $('#cityName').val(cityName);
    getCountReportCity(cityCode);
})
function getCountReportCity(){
    var cityCode=$('input[name=cityName]').val();
    if(cityCode==''){
        $('.entryMap').css('display','none');
    }else{
        $('.entryMap').css('display','block');
    }
    $.ajax({
        type: "post",
        url: basePath + monitorCountReportCityUrl,
        async: true,
        data: {
            cityCode:cityCode
        },
        success: function (data) {
            if (data.success == true) {
                var data=data.dataObject;
                for(var k in  data){
                    if(k=='A'){
                        var countCharge=data[k];
                        $('#countCharge').html(countCharge);
                    }
                    if(k=='B'){
                        var countTime=data[k];
                        $('#countTime').html(countTime);

                    }
                    if(k=='C'){
                        var countTimes=data[k];
                        $('#countTimes').html(countTimes);
                    }
                }
            }
        }
    });
}
//点击搜索按钮
$('body').off('click','.searchBtn').on('click','.searchBtn',function(){
    getCountReportCity();
})
//处理地图点击事件
myChart.on('click', function () {
    var cityCode = window.localStorage.getItem('monitor_cityCode');
    var cityName = window.localStorage.getItem('monitor_cityName');
    window.location.href = "monitorIndex.html?cityCode=" + cityCode;
})
//进入充电点地图按钮
$('body').off('click','#entryMapBtn').on('click','#entryMapBtn',function(){
    var cityCode = window.localStorage.getItem('monitor_cityCode');
    var cityName = window.localStorage.getItem('monitor_cityName');
    window.location.href = "monitorIndex.html?cityCode=" + cityCode;
})
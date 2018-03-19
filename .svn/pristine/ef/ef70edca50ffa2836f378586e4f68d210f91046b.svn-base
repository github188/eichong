$(function(){
    toLoadStationInfo();
    //加载所有城市
    setTimeout('toLoadAllCity()',200);
});
function toLoadStationInfo(){
    var cityCode = window.localStorage.getItem('monitor_cityCode');
    var cityName = window.localStorage.getItem('monitor_cityName');
    if(!cityName){
        cityName='请选择';
    }
    $('#cityCode').attr('data-value',cityCode);
    $('#cityCode').html(cityName);
    var areaCode = window.localStorage.getItem('monitor_areaCode');
    var areaName = window.localStorage.getItem('monitor_areaName');
    var powerStationName = window.localStorage.getItem('monitor_powerStationName');
    var num = getUrlParam('num');
    var powerStationIdValue = getUrlParam('monitor_powerStationId');
    if(!areaCode){
        areaCode='';
    }
    if(!areaName){
        areaName='请选择';
    }
    if(powerStationIdValue){
        toLoadStationHeadInfo(powerStationIdValue,1);
    }
    if(!powerStationName){
        powerStationName='';
    }
    $('#areaCode').attr('data-value',areaCode);
    $('#areaCode').html(areaName);
    $('#powerStationName').val(powerStationName);
    if(num==1){
        $('#stationSearch').css('display','none');
        $('#stationDetail').css('display','block');
    }
}
//下拉选项
toUnbindEvent();
function toUnbindEvent() {
    $('.cityBlock').unbind();
    $('.areaBlock').unbind();
    selectModel();
}

function toLoadAllCity(){
    $.ajax({
        type: "post",
        url: basePath + getCityListByNameUrl,
        async: true,
        data:{
            cityName:''
        },
        success: function (req) {
            var cityCode = window.localStorage.getItem('monitor_cityCode');
            toLoadArea(cityCode, '', '#areaCode', '.areaUl', 'toUnbindEvent');
            if( req.success==true ){
                var data = req.dataObject;
                var cityLi ='';
                for(var i=0;i<data.length;i++){
                    cityLi += '<li data-option="' + data[i].cityId + '" class="">' + data[i].cityName + '</li>';
                }
                $('.cityUl').html(cityLi);
            }else if (req.status == 9001){
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
                    yes:function(index,layero){
                        layer.closeAll();
                        window.top.location.href = basePath + toLoginUrl;
                    },
                    cancel:function(index,layero){
                        layer.closeAll();
                        window.top.location.href = basePath + toLoginUrl;
                    }
                });

            }else {
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '160px'],//宽高
                    content: req.msg,
                    time: 3000,
                    btn: ["确定"]
                });
            }
        }

    });
    setTimeout('stationListSearch()',200);
}
$("body").off("mouseover", ".cityUl li").on("mouseover", ".cityUl li", function(){
    $(this).parent().find('li').removeClass('seleced');
    $(this).addClass('seleced');
});
//加载市、区
$('.cityUl').on("click", "li", function () {
    $('#areaCode').html('请选择');
    $('.areaUl').html('');
    $('#areaCode').attr('data-value', '');
    var cityCode = $(this).attr('data-option');
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');
    if (flag == "") {
        $('#areaCode').html('请选择');
        $('.areaUl').html('');
        $('#areaCode').attr('data-value', '');
    } else {
        $('.areaUl').html('');
        toLoadArea(cityCode, '', '#areaCode', '.areaUl', 'toUnbindEvent');
    }
    var cityCode=$(this).attr('data-option');
    var cityName=$(this).html();
    var areaCode='';
    var areaName='请选择';
    window.localStorage.setItem('monitor_cityCode', cityCode);
    window.localStorage.setItem('monitor_cityName', cityName);
    window.localStorage.setItem('monitor_areaCode', areaCode);
    window.localStorage.setItem('monitor_areaName', areaName);
    stationListSearch();
});

//点击区获取值
$('.areaUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var areaCode=$(this).attr('data-option');
    var areaName=$(this).html();
    window.localStorage.setItem('monitor_areaCode', areaCode);
    window.localStorage.setItem('monitor_areaName', areaName);
    stationListSearch();
});

//加载左侧列表
function stationListSearch() {
    toGiveHiddenInput();
    initStation("stationListForm", "stationListPage", getPowerStationPageUrl, stationListCallback);
    setTimeout('toLoadErrorPile()',500);
}
function stationListCallback(req){
    var data = req.dataObject;
    var stationLi='';
    for(var i=0;i<data.length;i++){
        if(data[i].directNum && !data[i].communicationNum){
            stationLi +='<li class="stationLi"><p class="stationName"><t class="powerStationName" data-option="'+ data[i].powerStationId +'" data-num="1">'+ data[i].powerStationName +'</t></p>'
                + '<p class="stationAddress"><t class="addressHtml">地址：</t><t class="addressCon">'+ data[i].address +'</t></p>'
                + '<span class="stationNum">交流：'+ data[i].directNum +'</span></li>'
        }else if(data[i].communicationNum && !data[i].directNum){
            stationLi +='<li class="stationLi"><p class="stationName"><t class="powerStationName" data-option="'+ data[i].powerStationId +'" data-num="1">'+ data[i].powerStationName +'</t></p>'
                + '<p class="stationAddress"><t class="addressHtml">地址：</t><t class="addressCon">'+ data[i].address +'</t></p>'
                + '<span class="stationNum">直流：'+ data[i].communicationNum +'</span></li>'
        }else if(!data[i].directNum && !data[i].communicationNum){
            stationLi +='<li class="stationLi"><p class="stationName"><t class="powerStationName" data-option="'+ data[i].powerStationId +'" data-num="0">'+ data[i].powerStationName +'</t></p>'
                + '<p class="stationAddress"><t class="addressHtml">地址：</t><t class="addressCon">'+ data[i].address +'</t></p></li>'
        }else{
            stationLi +='<li class="stationLi"><p class="stationName"><t class="powerStationName" data-option="'+ data[i].powerStationId +'" data-num="1">'+ data[i].powerStationName +'</t></p>'
                + '<p class="stationAddress"><t class="addressHtml">地址：</t><t class="addressCon">'+ data[i].address +'</t></p>'
                + '<span class="stationNum">直流：'+ data[i].communicationNum +'</span><span class="stationNum">交流：'+ data[i].directNum +'</span></li>'
        }
    }
    $('#stationListUl').css('display','block');
    $('#stationListUl').html(stationLi);
    searchStation();
}
function initStation(formId, pageId, url, callback, pager) {
    if (!pager) {
        pager = {
            pageNo: 1
        };
    }
    var data = $("#" + formId).serializeObject();
    data.page = pager.pageNo;
    var index = layer.load(1);
    $.ajax({
        url: basePath + url,
        type: "post",
        dataType: 'json',
        data: data,
        success: function (datas) {
            if (typeof callback == "function") {
                if (datas.success == true) {
                    layer.close(index);
                    callback(datas);
                    if (datas.pager) {
                        initPage(datas.pager, formId, pageId, url, callback);
                    }
                } else if (datas.status == 9001) {
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
                        yes:function(index,layero){
                            layer.closeAll();
                            window.top.location.href = basePath + toLoginUrl;
                        },
                        cancel:function(index,layero){
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
                        yes:function(index,layero){
                            layer.closeAll();
                            window.top.location.href = basePath + toLoginUrl;
                        },
                        cancel:function(index,layero){
                            layer.closeAll();
                            window.top.location.href = basePath + toLoginUrl;
                        }
                    });
                }
            }
        }
    });
}
function toGiveHiddenInput(){
    var powerStationNameValue = $('#powerStationName').val();
    var cityCodeValue = $('#cityCode').attr('data-value');
    var areaCodeValue = $('#areaCode').attr('data-value');

    if (powerStationNameValue == "") {
        $('input[name=powerStationName]').val('');
    } else {
        $('input[name=powerStationName]').val(powerStationNameValue);
    }
    if (cityCodeValue == "") {
        $('input[name=cityCode]').val('');
    } else {
        $('input[name=cityCode]').val(cityCodeValue);
    }
    if (areaCodeValue == "") {
        $('input[name=areaCode]').val('');
    } else {
        $('input[name=areaCode]').val(areaCodeValue);
    }
}

//加载高德地图，描绘充电点位置
var map = new AMap.Map('container',{
    resizeEnable: true,
    zoom:12
});

var markers = [];
function searchStation(){
    markers = [];
    map.clearMap();
    var icon = new AMap.Icon({
        image : basePath + '/static/img/monitorStation.png',
        size : new AMap.Size(30,30)
    });
    var cityCode = $('#cityCode').attr('data-value');
    var areaCode = $('#areaCode').attr('data-value');
    var powerStationName=$('#powerStationName').val()
    $.ajax({
        type: "post",
        url: basePath + getPowerStationMapUrl,
        async: true,
        data: {
            cityCode: cityCode,
            areaCode: areaCode,
            powerStationName: powerStationName
        },
        success: function (req) {
            if( req.success==true ){
                var data = req.dataObject;
                for(var i=0;i<data.length;i++){
                    marker = new AMap.Marker({
                        icon:icon,
                        position: [data[i].longitude,data[i].latitude],
                        title: data[i].powerStationName,
                        map: map,
                        extData:{
                            powerStationName:data[i].powerStationName,
                            powerStationId:data[i].powerStationId,
                            address:data[i].address
                        },
                        clickable:true
                    });
                    markers.push(marker);
                    map.setCenter([parseFloat(data[0].longitude),parseFloat(data[0].latitude)]);
                    AMap.event.addListener(marker, 'click', _onClick);
                }
            }else if (req.status == 9001) {
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
                    yes:function(index,layero){
                        layer.closeAll();
                        window.top.location.href = basePath + toLoginUrl;
                    },
                    cancel:function(index,layero){
                        layer.closeAll();
                        window.top.location.href = basePath + toLoginUrl;
                    }
                });
            }else {
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '160px'],//宽高
                    content: req.msg,
                    time: 3000,
                    btn: ["确定"]
                });
            }
        }
    });
}
var _onZoomEnd = function(e) {
    var icon = new AMap.Icon({
        image : basePath + '/static/img/monitorStation.png',
        size : new AMap.Size(30,30)
    });
    var arr=[];
    if (map.getZoom() < 8) {
        var cityCode = $('#cityCode').attr('data-value');
        var totalNumValue=0;
        $.ajax({
            type: "post",
            url: basePath + getPowerStationMapUrl,
            async: true,
            data: {
                cityCode: cityCode,
                areaCode: '',
                powerStationName:''
            },
            success: function (req) {
                if( req.success==true ){
                    var data = req.dataObject;
                    window.localStorage.setItem('totalNum',data.length);
                    totalNumValue = data.length;
                }else if (req.status == 9001) {
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
                        yes:function(index,layero){
                            layer.closeAll();
                            window.top.location.href = basePath + toLoginUrl;
                        },
                        cancel:function(index,layero){
                            layer.closeAll();
                            window.top.location.href = basePath + toLoginUrl;
                        }
                    });

                }else {
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '160px'],//宽高
                        content: req.msg,
                        //content: '是这个吗',
                        time: 3000,
                        btn: ["确定"]
                    });
                }
            }
        });
        markersA=markers.slice(0,1);
        var totalNum = totalNumValue;
        var cityName =  $('#cityCode').html();
        var longitude = markers[0].G.position.lng;
        var latitude = markers[0].G.position.lat;
        for (var i = 0; i < markers.length; i += 1){
            map.remove(markers[i]);
            markers.pop;
        }
        arr.push(markers[0]);
        markers=[];
        markers=arr;
        window.localStorage.setItem('monitor_arr', arr);
        markerA = new AMap.Marker({
            icon:icon,
            position: [longitude,latitude],
            map: map,
            clickable:true
        });
        map.setCenter([parseFloat(longitude),parseFloat(latitude)]);
        AMap.event.addListener(markerA, 'click', _onClickA);

    }else if(map.getZoom() >= 8 && markers.length==1 ){
        setTimeout('searchStation()',300);
    }
}
AMap.event.addListener(map, 'zoomend', _onZoomEnd);

var _onClick = function(e){
    var powerStationName = e.target.G.extData.powerStationName;
    var powerStationId = e.target.G.extData.powerStationId;
    var address = e.target.G.extData.address;
    var longitude = e.target.G.position.lng;
    var latitude = e.target.G.position.lat;
    var kongxianNum=0 ;
    var guzhangNum=0 ;
    var chargingNum=0 ;
    $.ajax({
        type: "post",
        url: basePath + getPowerStationPileHeadNumUrl,
        async: true,
        data: {
            powerStationId: powerStationId
        },
        success: function (req) {
            if( req.success==true ){
                var data = req.dataObject;
                for(i=0;i<data.length;i++){
                    if(data[i].headNumStatus==0){
                        kongxianNum = data[i].num;
                    }else if(data[i].headNumStatus==6){
                        chargingNum = data[i].num;
                    }else if(data[i].headNumStatus==9){
                        guzhangNum = data[i].num;
                    }
                }
                var info = [];
                info.push("<div><div style=\"padding:0px 0px 0px 4px;\"><b class='powerStationNameHtml'>"+powerStationName+"</b>");
                info.push("地址 :"+address);
                info.push("<span>空闲 :"+kongxianNum+"；</span><span>充电："+chargingNum+"；</span><span class='faultNum'>故障："+guzhangNum+"</span></div></div>");
                infoWindow = new AMap.InfoWindow({
                    content: info.join("<br/>"), //使用默认信息窗体框样式，显示信息内容
                    offset: new AMap.Pixel(8, -30)//-113, -140
                });
                infoWindow.open(map, [longitude,latitude]);
            }else if (req.status == 9001) {
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
                    yes:function(index,layero){
                        layer.closeAll();
                        window.top.location.href = basePath + toLoginUrl;
                    },
                    cancel:function(index,layero){
                        layer.closeAll();
                        window.top.location.href = basePath + toLoginUrl;
                    }
                });

            }else {
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '160px'],//宽高
                    content: req.msg,
                    time: 3000,
                    btn: ["确定"]
                });
            }
        }
    });
};

var _onClickA = function(e){
    var totalNum = window.localStorage.getItem('totalNum');
    var cityName =  $('#cityCode').html();
    var longitude = markers[0].G.position.lng;
    var latitude = markers[0].G.position.lat;

    var info = [];
    info.push("<div><div style=\"padding:0px 0px 0px 4px;\"><b class='powerStationNameHtml'>"+cityName+"</b>");
    info.push("充电点数量 :"+totalNum+"</div></div>");
    infoWindow = new AMap.InfoWindow({
        content: info.join("<br/>"), //使用默认信息窗体框样式，显示信息内容
        offset: new AMap.Pixel(8, -30)//-113, -140
    });
    infoWindow.open(map, [longitude,latitude]);

};
//点击查询
$("body").off("click", "#query").on("click", "#query", function(){
    var powerStationName =$('#powerStationName').val();
    window.localStorage.setItem('monitor_powerStationName', powerStationName);
    stationListSearch();
});
//返回
$("body").off("click", "#backBtn").on("click", "#backBtn", function(){
    window.location.href = "electric_map.html";
});

//点击li显示充电点详情
$("body").off("click", ".powerStationName").on("click", ".powerStationName", function(){
    var powerStationId =$(this).attr('data-option');
    var num = $(this).attr('data-num');
    window.localStorage.setItem('monitor_powerStationId', powerStationId);
    if(num==0){
        layer.closeAll();
        layer.open({
            type: 1,
            offset: '100px',
            title: ["确认", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '180px'],//宽高
            content: '该充电点下没有电桩',
            cancel: function (index, layero) {
                layer.closeAll();
            }
        });
    }else if(num==1){
        var page=1;
        $('#stationSearch').css('display','none');
        $('#stationDetail').css('display','block');
        toLoadStationHeadInfo(powerStationId,page);
    }
});

function toLoadStationHeadInfo(powerStationId,page){
    $.ajax({
        type: "post",
        url: basePath + getPowerStationPileHeadDetailUrl,
        async: true,
        data: {
            powerStationId: powerStationId,
            pageNo:page
        },
        success: function (req) {
            if( req.success==true ){
                var data = req.dataObject;
                if (req.pager) {
                    initStationHeadPage(req.pager,powerStationId);
                }
                var powerStationName=data[0].powerStationName;
                var address=data[0].address;
                var phone=data[0].phone;
                $('#backListBtn').html('<返回“'+ powerStationName +'”的搜索结果 ');
                $('#stationNameTitle').html(  powerStationName );
                $('#stationDetailAddress').html('地址：'+ address );
                $('#phoneNum').html('电话：'+ phone );
                var stationHeadLi='';
                for(var i=0;i<data.length;i++){
                    var chargingModeHtml='';
                    if(data[i].chargingMode==5){
                        chargingModeHtml='直流';
                    }else if(data[i].chargingMode==14){
                        chargingModeHtml='交流';
                    }
                    var headStatusHtml='';
                    if(data[i].headStatus==0){
                        headStatusHtml='空闲';
                    }else if(data[i].headStatus==6){
                        headStatusHtml='充电';
                    }else if(data[i].headStatus==9){
                        headStatusHtml='故障';
                    }else if(data[i].headStatus==17){
                        headStatusHtml='等待';
                    }
                    if(data[i].headStatus==9){
                        stationHeadLi+='<li class="stationHeadLi faultTips row" data-option="'+ data[i].headId +'" style="margin: 0;">'
                            + '<span class="headNumCon col-sm-4 col-xs-4">'+ data[i].pileNum +'号桩枪口'+ data[i].headNum +'</span>'
                            + '<span class="chargingModeCon col-sm-4 col-xs-4">'+ chargingModeHtml+data[i].powerSize +'</span>'
                            + '<span class="headStatusCon col-sm-4 col-xs-4">'+ headStatusHtml +'</span></li>'
                    }else{
                        stationHeadLi+='<li class="stationHeadLi row" data-option="'+ data[i].headId +'" style="margin: 0;">'
                            + '<span class="headNumCon col-sm-4 col-xs-4">'+ data[i].pileNum +'号桩枪口'+ data[i].headNum +'</span>'
                            + '<span class="chargingModeCon col-sm-4 col-xs-4">'+ chargingModeHtml+data[i].powerSize +'</span>'
                            + '<span class="headStatusCon col-sm-4 col-xs-4">'+ headStatusHtml +'</span></li>'
                    }
                }
                $('#stationHeadUl').html(stationHeadLi);
            }else if (req.status == 9001) {
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
                    yes:function(index,layero){
                        layer.closeAll();
                        window.top.location.href = basePath + toLoginUrl;
                    },
                    cancel:function(index,layero){
                        layer.closeAll();
                        window.top.location.href = basePath + toLoginUrl;
                    }
                });

            }else {
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '160px'],//宽高
                    content: req.msg,
                    time: 3000,
                    btn: ["确定"]
                });
            }
        }
    });
}

//分页
function initStationHeadPage(pager,powerStationId) {
    var options = {
        containerClass: "pagination",
        currentPage: pager.pageNo,
        numberOfPages: 5,
        totalPages: pager.pageTotal,
        totalCount: pager.total,
        pageUrl: function (type, page){
            return null;
        },
        onPageClicked: function (event, originalEvent, type, page) {
            toLoadStationHeadInfo(powerStationId,page);
        }
    };
    $('#stationDetailPage').bootstrapPaginator(options);
}
//点击返回充电点列表查询
$("body").off("click", "#backListBtn").on("click", "#backListBtn", function() {
    $('#stationSearch').css('display', 'block');
    $('#stationDetail').css('display', 'none');
});
//点击进实时数据
$("body").off("click", ".stationHeadLi").on("click", ".stationHeadLi", function() {
    var headId =$(this).attr('data-option');
    window.localStorage.setItem('monitor_headId', headId);
    window.location.href = "realTimeInfo.html?headId=" + headId;
});

function toLoadErrorPile(){
    $.ajax({
        type: "post",
        url: basePath + getNowErrorPileUrl,
        async: true,
        success: function (req) {
            if( req.success==true ){
                var liNum=0;
                setInterval(function(){
                    var num=(liNum % (data.length));
                    liNum++;
                    $('#newsTit li').removeClass('hiddenLi');
                    $('#newsTit li').addClass('errorLi');
                    $('#newsTit li').fadeOut(10);
                    $('#newsTit li').eq(num).fadeIn(500);
                },3000);
                var data = req.dataObject.data;
                if(data.length>0){
                    var errorLi='提示：';
                    for(var i=0;i<data.length;i++){
                        var time = new Date(data[i].ts).format("yyyy-MM-dd hh:mm");
                        if (data[i].status == 1 || data[i].status >= 30) {
                            errorLi +='<li class="hiddenLi">'+ time +'，'+ data[i].epCode +'号桩发生故障</li>';
                        }
                    }
                    $('#newsTit').html(errorLi);
                }
            }else if (req.status == 9001){
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
                    yes:function(index,layero){
                        layer.closeAll();
                        window.top.location.href = basePath + toLoginUrl;
                    },
                    cancel:function(index,layero){
                        layer.closeAll();
                        window.top.location.href = basePath + toLoginUrl;
                    }
                });

            }else {
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '160px'],//宽高
                    content: req.msg,
                    time: 3000,
                    btn: ["确定"]
                });
            }
        }

    });
}
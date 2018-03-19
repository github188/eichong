
$(function(){
    clearInterval(timer);
    var timer = setInterval(function(){
        getPowerStationPileHeadOnlineStateOne();
        getParkingLockState();
        getPowerStationSwiper();
        getPowerStationHistoryData();
    },2000*60);

    selectModel();
    toLoadStationInfo();
    function toLoadStationInfo(){
        var powerstationId = window.localStorage.getItem('powerstationId');
        if(powerstationId){
            toLoadStationHeadInfo(powerstationId,1);
        }
    }

    function toLoadStationHeadInfo(powerStationId,page){//充电点首页左侧
        $.ajax({
            type: "post",
            url: basePath + getPowerStationPileHeadDetailUrl,
            async: false,
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
                    $('.powerStationName').html(powerStationName);
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
                            stationHeadLi+='<li class="stationHeadLi faultTips row" data-option="'+ data[i].headId +'" data-headNum="'+data[i].headNum+'" data-epCode="'+data[i].epCode+'" style="margin: 0;">'
                                + '<span class="headNumCon col-sm-4 col-md-4 col-xs-4">'+'<span class="gun">'+data[i].pileNum+'#'+'</span>'+'枪口'+ data[i].headNum +'</span>'
                                + '<span class="chargingModeCon col-sm-4 col-md-4 col-xs-4">'+ chargingModeHtml+data[i].powerSize +'</span>'
                                + '<span class="headStatusCon col-sm-4 col-md-4 col-xs-4 state'+data[i].headStatus+' " data-option="'+data[i].headStatus+'">'+'<i class="circle"></i>'+ headStatusHtml +'</span></li>'
                        }else{
                            stationHeadLi+='<li class="stationHeadLi row" data-option="'+ data[i].headId +'" data-headNum="'+data[i].headNum+'" data-epCode="'+data[i].epCode+'" style="margin: 0;">'
                                + '<span class="headNumCon col-sm-4 col-md-4 col-xs-4">'+'<span class="gun">'+data[i].pileNum+'#'+'</span>'+'枪口'+ data[i].headNum +'</span>'
                                + '<span class="chargingModeCon col-sm-4 col-md-4 col-xs-4">'+ chargingModeHtml+data[i].powerSize +'</span>'
                                + '<span class="headStatusCon col-sm-4 col-md-4 col-xs-4 state'+data[i].headStatus+' " data-option="'+data[i].headStatus+'">'+'<i class="circle"></i>'+ headStatusHtml +'</span></li>'
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

    //电桩/枪口在线状态
    getPowerStationPileHeadOnlineStateOne();
    function getPowerStationPileHeadOnlineStateOne(){//电桩
        var powerstationId = window.localStorage.getItem('powerstationId');
        $.ajax({
            type: "post",
            url: basePath + getPowerStationPileHeadOnlineStateUrl,
            async: false,
            data: {
                powerStationId: powerstationId,
                type:1
            },
            success: function (req) {
                if( req.success==true ){
                    var data = req.dataObject;
                    $('.chargingCount').html(data.chargingCount);
                    $('.freeCount').html(data.freeCount);
                    $('.errorCount').html(data.errorCount);
                    $('.disconnectCount').html(data.disconnectCount);
                }
            }
        })
    }

    //电桩/枪口在线状态
    getPowerStationPileHeadOnlineStateTwo();
    function getPowerStationPileHeadOnlineStateTwo(){//枪口
        var powerstationId = window.localStorage.getItem('powerstationId');
        $.ajax({
            type: "post",
            url: basePath + getPowerStationPileHeadOnlineStateUrl,
            async: false,
            data: {
                powerStationId: powerstationId,
                type:2
            },
            success: function (req) {
                if( req.success==true ){
                    var data = req.dataObject;
                    $('.chargingCountTwo').html(data.chargingCount);
                    $('.freeCountTwo').html(data.freeCount);
                    $('.errorCountTwo').html(data.errorCount);
                    $('.disconnectCountTwo').html(data.disconnectCount);
                }
            }
        })
    }

    //电桩在线状态切换
    $('.muzzle').click(function(){
        $('.chargeUl2').show();
        $('.chargeUl1').hide();
        $(this).addClass('active');
        $('.electricPile').removeClass('active');
    });
    $('.electricPile').click(function(){
        $('.chargeUl2').hide();
        $('.chargeUl1').show();
        $(this).addClass('active');
        $('.muzzle').removeClass('active');
    });


    getParkingLockState();
    //地锁状态
    function getParkingLockState(){
        var powerstationId = window.localStorage.getItem('powerstationId');
        $.ajax({
            type: "post",
            url: basePath + getParkingLockStateUrl,
            async: false,
            data: {
                powerStationId: powerstationId
            },
            success: function (req) {
                if( req.success==true ){
                    var data = req.dataObject;
                    $('.usingCount').html(data.usingCount);
                    $('.nomalCount').html(data.nomalCount);
                    $('.errorCount').html(data.errorCount);
                }
            }
        })
    }


    getPowerStationSwiper();
    //充电点历史信息swiper
    function getPowerStationSwiper(){//地锁
        var powerstationId = window.localStorage.getItem('powerstationId');
        $.ajax({
            type: "post",
            url: basePath + getPowerStationPicListUrl,
            async: false,
            data: {
                powerStationId: powerstationId
            },
            success: function (req) {
                if( req.success==true ){
                    var data = req.dataObject;
                    var swiperLi = '';
                    if(data != ''){
                        for(var i = 0; i < data.length; i ++){
                            //console.log(data[i])
                            swiperLi += '<div class="swiper-slide">'+
                                '<img src="'+data[i]+'" alt=""/>'+
                                '</div>'
                        }
                        $('.swiper-wrapper').html(swiperLi);
                        swiper();
                    }else{
                        $('.piPhoto').remove();
                        $('.swiperPhoto').remove();
                    }

                }
            }
        })
    }
    //充电点历史信息轮播图
    function swiper(){
        var mySwiper = new Swiper ('.swiper-container', {
            slidesPerView: 5,
            spaceBetween: 30,
            freeMode: true,
            loop: false,
            loopFillGroupWithBlank: true,
            pagination: {
                el: '.swiper-pagination',
                clickable: true,
            },
            // 如果需要前进后退按钮
            navigation: {
                nextEl: '.swiper-button-next',
                prevEl: '.swiper-button-prev',
            },
            observer:true,
            observeParents:true
        })
    }

    //充电点照片下的历史数据
    getPowerStationHistoryData();
    function getPowerStationHistoryData(){
        var powerstationId = window.localStorage.getItem('powerstationId');
        $.ajax({
            type: "post",
            url: basePath + getPowerStationHistoryDataUrl,
            async: false,
            data: {
                powerStationId: powerstationId
            },
            success: function (req) {
                if( req.success==true ){
                    var data = req.dataObject;
                    //allCountChargeCount 累计充电量
                    //allCountOrderCount 历史充电订单
                    //epError 电桩故障
                    //lastMonthChargeCount 上月充电量
                    //lastMonthOrderCount 上月充电订单
                    //orderError 异常订单
                    //yesterdayChargeCount 昨日充电量
                    //yesterdayOrderCount 昨日充电订单
                    $('.allCountChargeCount').html(data.allCountChargeCount);
                    $('.allCountOrderCount').html(data.allCountOrderCount);
                    $('.epError').html(data.epError);
                    $('.lastMonthChargeCount').html(data.lastMonthChargeCount);
                    $('.lastMonthOrderCount').html(data.lastMonthOrderCount);
                    $('.orderError').html(data.orderError);
                    $('.yesterdayChargeCount').html(data.yesterdayChargeCount);
                    $('.yesterdayOrderCount').html(data.yesterdayOrderCount);
                }
            }
        })
    }

    //点击进入充电点详情
    $("body").off("click", ".stationHeadLi").on("click", ".stationHeadLi", function() {
        var headId =$(this).attr('data-option');
        var epCode = $(this).attr('data-epCode');
        var headNum = $(this).attr('data-headNum');
        window.localStorage.setItem('headId', headId);
        window.localStorage.setItem('epCode', epCode);
        window.localStorage.setItem('headNum', headNum);
        window.location.href = "chargePointDetail.html";
    });





































    //toLoadStationHeadList();
    function toLoadStationHeadList(){//充电点首页左边 列表
        var powerstationId = window.localStorage.getItem("powerstationId");
        $.ajax({
            type: "post",
            url:basePath + getPowerStationPileHeadDetailUrl,
            async: false,
            data: {
                powerstationId:powerstationId
            },
            success: function (req) {
                var data = req.dataObject;
                var stationHeadLi='';
                for(var i = 0; i < data.length;i++){
                    var headStatusHtml='';
                    if(data[i].state==1){
                        headStatusHtml='空闲';
                    }else if(data[i].state==2){
                        headStatusHtml='充电';
                    }else if(data[i].state==3){
                        headStatusHtml='故障';
                    }
                    stationHeadLi+='<li class="stationHeadLi faultTips row" data-option="'+data[i].id+'" style="margin: 0;">'
                        + '<span class="headNumCon col-sm-4 col-md-4 col-xs-4">'+'<img class="gun" src="'+data[i].img+'" alt=""/>'+ data[i].muzzle +'</span>'
                        + '<span class="chargingModeCon col-sm-4 col-md-4 col-xs-4">'+data[i].direct +'</span>'
                        + '<span class="headStatusCon col-sm-4 col-md-4 col-xs-4 state'+data[i].state+' " data-option="'+data[i].state+'">'+'<i class="circle"></i>'+ headStatusHtml +'</span></li>'

                }
                $('#stationHeadUl').html(stationHeadLi);
            }
        })
    }

    function toLoadStation(){
        $.ajax({
            type: "post",
            url:"../../../json/dataCenter/chargePointHomePage.json",
            async: true,
            data: {

            },
            success: function (req) {
                var data = req.dataObject1;
                var stationHeadLi='';
                for(var i = 0; i < data.length;i++){
                    var headStatusHtml='';
                    if(data[i].state==1){
                        headStatusHtml='空闲';
                    }else if(data[i].state==2){
                        headStatusHtml='充电';
                    }else if(data[i].state==3){
                        headStatusHtml='故障';
                    }
                    stationHeadLi+='<li class="stationHeadLi faultTips row" data-option="'+data[i].id+'" style="margin: 0;">'
                        + '<span class="headNumCon col-sm-4 col-md-4 col-xs-4">'+'<img class="gun" src="'+data[i].img+'" alt=""/>'+ data[i].muzzle +'</span>'
                        + '<span class="chargingModeCon col-sm-4 col-md-4 col-xs-4">'+data[i].direct +'</span>'
                        + '<span class="headStatusCon col-sm-4 col-md-4 col-xs-4 state'+data[i].state+' " data-option="'+data[i].state+'">'+'<i class="circle"></i>'+ headStatusHtml +'</span></li>'

                }
                //$('#stationHeadUl').html(stationHeadLi);

                var data = req.dataObject2;
                //console.log(data)
                var chargeLi='';
                for(var i = 0; i < data.length;i++){
                    chargeLi += '<li class="chargingState col-xs-3 col-xs-12 lineState'+data[i].state+'">'+'<img src="'+data[i].img+'" alt=""/>'+data[i].charging+'</li>'
                }
                $('#chargeUl').html(chargeLi);

                var data = req.dataObject3;
                var lockLi = '';
                for(var i = 0; i < data.length; i++){
                    lockLi += '<li class="chargingState col-xs-3 col-xs-12 lineState'+data[i].state+'">'+'<img src="'+data[i].img+'" alt=""/>'+data[i].charging+'</li>'
                }
                $("#lockStateUl").html(lockLi);
                $('#carStateUl').html(lockLi);
                $('#carStateUl1').html(lockLi);
                $('#chargeUl1').html(lockLi);
                var data = req.dataObject4;
                var orderLi = '';
                for(var i = 0; i < data.length; i ++){
                    orderLi += '<li class="row" style="border-bottom:1px solid #c9c9c9;height:auto;margin:0;font-size:12px;padding:12px 20px;">'+
                                    '<span class="left">'+data[i].order+'</span>'+
                                    '<span class="right">'+data[i].num+'</span>'+
                                '</li>'
                }
                $('#orderUl').html(orderLi);

                //轮播图
                var data = req.dataObject7;
                var swiperLi = '';
                //console.log(data)
                for(var i = 0; i < data.length; i ++){
                    swiperLi += '<div class="swiper-slide">'+
                        '<img src="'+data[i].img+'" alt=""/>'+
                        '</div>'
                }
                $('.swiper-wrapper').html(swiperLi);
                swiper();
            }
        })

    }



    //视频监控摄像头
    $('.cameraUl').on("click", "li", function () {
        $(this).parent().siblings('div.model-select-text').text($(this).text())
            .attr('data-value', $(this).attr('data-option'));
    })



})
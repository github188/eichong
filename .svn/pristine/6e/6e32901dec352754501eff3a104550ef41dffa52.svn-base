getTotal();
function getTotal(){
    var index=layer.load(1);
    $.ajax({
        type: "post",
        url: basePath + doIntegralStatisticsUrl,
        async: true,
        success: function (data) {
            if(data.success){
                layer.closeAll();
                var data = data.dataObject;
                var totalIntegrals=data.totalIntegrals;
                var usedIntegrals=data.usedIntegrals;
                var availableIntegrals=data.availableIntegrals;
                $('#totalIntegrals').html(totalIntegrals);
                $('#usedIntegrals').html(usedIntegrals);
                $('#availableIntegrals').html(availableIntegrals);
                //充值
                var zzjf=data.zzjf;
                var zzrs=data.zzrs;
                var czcs=data.czcs;
                $('#zzjf').html(zzjf);
                $('#zzrs').html(zzrs);
                $('#czcs').html(czcs);

                //消费送出
                var xfjf=data.xfjf;
                var xfrs=data.xfrs;
                var xfcs=data.xfcs;
                $('#xfjf').html(xfjf);
                $('#xfrs').html(xfrs);
                $('#xfcs').html(xfcs);

                //每日领取
                var lqjf=data.lqjf;
                var lqrs=data.lqrs;
                var lqcs=data.lqcs;
                $('#lqjf').html(lqjf);
                $('#lqrs').html(lqrs);
                $('#lqcs').html(lqcs);

                //资料修改
                var xgjljf=data.xgjljf;
                var xgjlrs=data.xgjlrs;
                $('#xgjljf').html(xgjljf);
                $('#xgjlrs').html(xgjlrs);

                //分享
                var fxjf=data.fxjf;
                var fxrs=data.fxrs;
                $('#fxjf').html(fxjf);
                $('#fxrs').html(fxrs);

                //积分兑换
                var jfdhjf=data.jfdhjf;
                var jfdhrs=data.jfdhrs;
                var jfdhcs=data.jfdhcs;
                $('#jfdhjf').html(jfdhjf);
                $('#jfdhrs').html(jfdhrs);
                $('#jfdhcs').html(jfdhcs);

                //积分抽奖
                var jfcjjf=data.jfcjjf;
                var jfcjrs=data.jfcjrs;
                var jfcjcs=data.jfcjcs;
                $('#jfcjjf').html(jfcjjf);
                $('#jfcjrs').html(jfcjrs);
                $('#jfcjcs').html(jfcjcs);

                //节假日
                var jjrjf=data.jjrjf;
                var jjrrs=data.jjrrs;
                var jjrcs=data.jjrcs;
                $('#jjrjf').html(jjrjf);
                $('#jjrrs').html(jjrrs);
                $('#jjrcs').html(jjrcs);

                //生日
                var srjf=data.srjf;
                var sjrs=data.sjrs;
                var sjcs=data.sjcs;
                $('#srjf').html(srjf);
                $('#sjrs').html(sjrs);
                $('#sjcs').html(sjcs);
            }

        }

    })
}
/*layer.open({
 type:2,
 time:5
 })*/

wxloadStart();
setTimeout("wxloadStop()",3000);
setTimeout('getWxChargeStop()',3000);
initWxJs();
function initWxJs() {
    $.ajax({
        type: "get",
        url: basePath + config,
        async: true,
        dataType: "json",
        data: {
            page: location.href.split('#')[0]
        },
        success: function (datas) {
        	var datas=JSON.parse(datas);
            if (datas.status == 100) {
                var req = datas.data;
                var signature = req.signature;
                var timestamp = req.timestamp;
                var nonceStr = req.nonceStr;
              
                wx.config({
                    //  debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
                    appId: 'wxb26a9c1ed4b0686b',
                    timestamp: timestamp, // 必填，生成签名的时间戳
                    nonceStr: nonceStr, // 必填，生成签名的随机串
                    signature: signature, // 必填，签名
                    jsApiList: ["getLocation", "chooseImage", "hideOptionMenu", 'checkJsApi', 'chooseWXPay','closeWindow']
                    // 必填，需要使用的JS接口列表
                });
                // 通过ready接口处理成功验证
                wx.ready(function () {
                    //  alert("初始化成功！");
                });
                wx.error(function () {
                    //alert("初始化失败！");
                });

            } else {
                layer.closeAll();
                layer.open({
                    content: datas.msg,
                    style: 'border:none; background-color:#cccccc; color:#000;font-size:36/@r;',
                    btn: ['我知道了'],
                    shadeClose: false,
                    anim: 'up',
                    fixed: true
                });
            }
        }
    });

}

// 获取信息========================
function getWxChargeStop() {
    var tC = localStorage.getItem('tC');
    $('#timeChargeF').html(tC);
    var power = localStorage.getItem('powerF');
    $('#powerF').html(parseFloat(power).toFixed(2) + 'kW');
    var feeTotal = localStorage.getItem('feeTotalF');
    $('#feeTotalF').html(parseFloat(feeTotal).toFixed(2) + '元');
    var feeBack = localStorage.getItem('feeBack');
    $('#feeBack').html(parseFloat(feeBack).toFixed(2) + '元');

}
$('#haveDone').on('click', function () {
	wx.closeWindow();
})

function toPage(url) {
    return window.location.href = url;
}

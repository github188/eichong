var code = getUrlParam("code");
var getOpenId = localStorage.getItem("openId");
getAccount();
function getAccount() {
    $.ajax({
        type: "get",
        url: basePath + getWxAccount,
        async: true,
        dataType: "json",
        data: {
            code: code,
            openId: (!getOpenId) ? "" : getOpenId
        },
        success: function (datas) {
        	var datas=JSON.parse(datas);
            if (datas.status == 100) {
                var req = datas.data;
                // alert(JSON.stringify(req))
                var openId = req.openId;
                var accountArray = req.account;
                localStorage.setItem("openId", openId);
                var html = "";
                for (var i = 0; i < accountArray.length; i++) {
                    html += '<div class="mainContent" onclick="toDetail(this);" data-val="' + accountArray[i].month + '">'
                        + '<div class="month"><div class="monthNum"><span class="font28">'
                        + accountArray[i].month + '</span><span class="font26">查看明细</span></div></div> <ul><li>消费&nbsp;&nbsp;<span>'
                        + accountArray[i].monetary + '</span>元</li><li>充电&nbsp;&nbsp;<span>'
                        + accountArray[i].chargeDegree + '</span>&nbsp;&nbsp;KWh(度)</li></ul></div>';
                }
                $('#mainBlock').html(html);
            }
        }
    });
}

function toDetail(dom) {
    var reMonth = $(dom).attr('data-val');
    localStorage.setItem("reMonth", reMonth);
    toPage("accountDetail.html");
}
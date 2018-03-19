var getOpenId = localStorage.getItem("openId");
var month = localStorage.getItem("reMonth");
getAccountDetail();
function getAccountDetail() {
    $.ajax({
        type: "get",
        url: basePath + getWxAccountDetail,
        async: true,
        dataType: "json",
        data: {
            month: month,
            openId: getOpenId
        },
        success: function (datas) {
        	var datas=JSON.parse(datas);
            if (datas.status == 100) {
                var req = datas.data;
                // alert(JSON.stringify(req))
                var html = "";
                for (var i = 0; i < req.length; i++) {
                    html += '<li><div><span>'
                        + req[i].endChargeTime.substr(5, 5) + '</span>&nbsp;&nbsp;<span class="font28">'
                        + req[i].endChargeTime.substr(11, 5) + '</span></div><div>充电消费</div><div>'
                        + ((parseFloat(req[i].ChargeMoney) + parseFloat(req[i].ServiceMoney)).toFixed(2)) + '</div></li>'
                }
                $('#detailList').html(html);
            }
        }
    });
}
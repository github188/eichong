var rateInfoId = getUrlParam('rateInfoId');
//获取费率主页的menuId
var getMenuMap = window.localStorage.getItem('menuMap');
var getCurruntMap = JSON.parse(getMenuMap);
var menuId = getCurruntMap['费率主页'];
$(function () {
    ctrlMenu(menuId);
    setTimeout("getRateInfoDetail()", 100);
})
function ctrlMenu(menuId) {
    $.ajax({
        type: "post",
        url: basePath + getSelfButtonTreeUrl,
        async: true,
        data: {
            menuId: menuId
        },
        success: function (req) {
            if(req.success==true){
                var data = req.dataObject;
                if (data == null) {
                    return;
                }
                if(data.length==0){
                    return;
                }
                else {
                    for (var i = 0; i < data.length; i++) {
                        var contents = data[i].contents;
                        if (contents.indexOf('编辑') > -1) {
                            $('#editBtn').show();
                        }
                    }
                }
            }else if(req.status == 9001) {
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

            }
        }
    });
}

function getRateInfoDetail() {
    $.ajax({
        type: "post",
        url: basePath + getRateInfoUrl,
        async: true,
        data: {
            pk_RateInformation: rateInfoId
        },
        success: function (data) {
            data = data.dataObject;
            $('#raIn_ServiceCharge').html(data.raIn_ServiceCharge);
            $('#raIn_Name').html(data.raIn_Name);
            $('#raIn_TipTimeTariffPrice').html(data.raIn_TipTimeTariffPrice);
            $('#raIn_PeakElectricityPrice').html(data.raIn_PeakElectricityPrice);
            $('#raIn_UsualPrice').html(data.raIn_UsualPrice);
            $('#raIn_ValleyTimePrice').html(data.raIn_ValleyTimePrice);
            $('input[value=' + data.raIn_type + ']').attr('checked', true);
            $('#uniformPrice').html(data.uniformPrice);
            $('#raIn_TipTimeTariffMoney').html(data.raIn_TipTimeTariffMoney);
            $('#raIn_PeakElectricityMoney').html(data.raIn_PeakElectricityMoney);
            $('#raIn_UsualMoney').html(data.raIn_UsualMoney);
            $('#raIn_ValleyTimeMoney').html(data.raIn_ValleyTimeMoney);

            var table = JSON.parse(data.raIn_QuantumDate).data;
            var mark = {'1': '尖', '2': '峰', '3': '平', '4': '谷'};
            var listTr = "";
            for (var i = 0; i < table.length; i++) {
                listTr += '<tr>'
                + '<td>' + mark[table[i].mark]
                + '</td><td>' + parseInt(table[i].st / 60) + '时' + table[i].st % 60 + '分'
                + '</td><td>' + parseInt(table[i].et / 60) + '时' + table[i].et % 60 + '分'
                + '</td></tr>';
            }
            $("#myTbogy").html(listTr);
        }
    });
}

function toModifyRateInfo() {
    window.location.href = "modifyRateInfo.html?rateInfoId=" + rateInfoId;
}

//返回列表页
$('#goback').on('click', function () {
    window.location.href = 'rateInfo.html';
});
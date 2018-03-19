$(function(){
    setTimeout('getFinBillAccount()',100);
})

function getFinBillAccount() {
    $.ajax({
        type: "post",
        url: basePath + getFinBillAccountUrl,
        async: true,
        success: function (data) {
            if (data.success == true) {
                var data = data.dataObject;
                var listTr = "";
                for (var i = 0; i < data.length; i++) {
                    listTr += '<tr><td>' + data[i].billAccountCode
                    + '</td><td>' + data[i].billAccountName
                    + '</td></tr>';
                }
                $("#myTbody").html(listTr);
            }else if(data.status == 9001) {
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
//下拉列表
toUnbindEvent();
function toUnbindEvent() {
    $('.areaCodeBlock').unbind();
    selectModel();
}
//保存新增集中器信息
$("body").off("click", "#saveBtn").on("click", "#saveBtn", function () {
    var concentratorName = $('#concentratorName').val();
    var obj = {
        concentratorName: concentratorName
    };
    var nameLength = lengthTest($('#concentratorName'), 30);
    if (nameLength == false) {
        layer.tips('请输入正确的名称，最多30字符！', '#concentratorName', {
            tips: 4
        });
        return false;
    }
    $.ajax({
        type: "post",
        url: basePath + addConcentratorUrl,
        async: true,
        data: obj,
        success: function (data) {
            if (data.success == true) {
                layer.closeAll();
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '180px'],//宽高
                    content: "保存成功",
                    btn: ["确定"],
                    yes: function (index, layero) {
                        window.location.href = 'concentrator_list.html';
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        window.location.href = 'concentrator_list.html';
                    }
                });
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

            } else {
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '160px'],//宽高
                    content: data.msg,
                    time: 3000,
                    btn: ["确定"]
                });
            }
        }
    });

})


$('#toPowerStationDetail').on('click', function () {
    //window.location.href = 'concentrator_list.html';
    window.history.back();
})
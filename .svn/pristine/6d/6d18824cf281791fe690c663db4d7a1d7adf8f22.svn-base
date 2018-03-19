//新建电桩制造商校验
$('#makerName').on('focus',function(){
    layer.tips('电桩制造商名称最多50个汉字', '#makerName', {
        tips: 4
    });
})
$('body').off('blur', '#makerName').on('blur', '#makerName', function () {
    $('#makerNameTip').html('');
    var makerNameValue =$('#makerName').val();
    var reg= /^[\u4E00-\u9FA5]+$/;
    if(!makerNameValue){
        $('#makerNameTip').html('请输入电桩制造商名称');
        $('#makerName').val('');
    }else if(!reg.test(makerNameValue)){
        $('#makerNameTip').html('电桩制造商名称只能输入汉字，请重新输入');
        $('#makerName').val('');
    }else if(makerNameValue.length>50){
        $('#makerNameTip').html('电桩制造商名称最多50个字，请重新输入');
        $('#makerName').val('');
    }

});

$('#makerRemark').on('focus',function(){
    layer.tips('电桩制造商标识为两位数字', '#makerRemark', {
        tips: 4
    });
})
$('body').off('blur', '#makerRemark').on('blur', '#makerRemark', function () {
    $('#makerRemarkTip').html('');
    var makerRemarkValue = $('#makerRemark').val();
    var reg=/^[0-9]{2}$/;
    if(!makerRemarkValue){
        $('#makerRemarkTip').html('请输入电桩制造商标识');
    }else if(!reg.test(makerRemarkValue)){
        $('#makerRemarkTip').html('制造商标识有误，请重新输入');
        $('#makerRemark').val('');
    }
});

//将数据提交到后台处理
function savePileMaker() {
    var makerName = $('#makerName').val();
    var makerRemark = $('#makerRemark').val();
    if (!makerName){
        $('#makerNameTip').html('请输入电桩制造商名称');
        $('#makerName').focus();
        return;
    }
    if (!makerRemark){
        $('#makerRemarkTip').html('请输入电桩制造商标识');
        $('#makerRemark').focus();
        return;
    }

    var obj = {
        makerName: makerName,
        makerRemark: makerRemark
    };
    $.ajax({
        type: "post",
        url: basePath + addPileMakerUrl,
        async: true,
        data: obj,
        success: function (data) {
            if (data.success == true) {
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '160px'],//宽高
                    content: data.msg,
                    btn: ["确定"],
                    yes:function(index,layero){
                        window.location.href = 'pileMaker.html';
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        window.location.href = 'pileMaker.html';
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
}

$('#goback').on('click', function () {
    window.location.href = 'pileMaker.html';
});

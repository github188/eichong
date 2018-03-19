//新建电动车品牌名称校验
$('body').off('blur', '#name').on('blur', '#name', function () {
    checkName($('#name').val());
});

$('body').off('blur', '#remark').on('blur', '#remark', function () {
    checkRemark($('#remark').val());
});

function checkName(name) {
    if (!name) {
        $('#nameTip').html('电动车品牌名称不能为空');
        return false;
    }

    $('#nameTip').html('');
    return true;
}

function checkRemark(remark) {
    if (!remark) {
        $('#remarkTip').html('制造商标识不能为空');
        return false;
    }

    $('#remarkTip').html('');
    return true;
}

//将数据提交到后台处理
function saveCarCompany() {
    var name = $('#name').val();
    var remark = $('#remark').val();

    if (!checkName(name) || !checkRemark(remark)) {
        return;
    }

    var obj = {
        name: name,
        remark: remark
    };
    $.ajax({
        type: "post",
        url: basePath + addCarCompanyUrl,
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
                        window.location.href = 'carCompany.html';
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        window.location.href = 'carCompany.html';
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
    window.location.href = 'carCompany.html';
});

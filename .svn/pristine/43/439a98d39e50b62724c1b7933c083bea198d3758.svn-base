//新建标签类型校验
$('body').off('blur', '#name').on('blur', '#name', function () {
    checkName($('#name').val());
});

function checkName(name) {
    if (!name) {
        $('#nameTip').html('标签名称不能为空');
        return false;
    }

    if (name.length > 50) {
        $('#nameTip').html('标签名称最多50个汉字');
        return false;
    }

    $('#nameTip').html('');
    return true;
}

function checkType(type) {
    if (!type) {
        $('#typeTip').html('标签类型不能为空');
        return false;
    }

    $('#typeTip').html('');
    return true;
}

//将数据提交到后台处理
function saveTag() {
    var type = $('#type').attr('data-value');
    var name = $('#name').val();

    if (!checkType(type) || !checkName(name)) {
        return;
    }

    var obj = {
        type: type,
        name: name
    };
    $.ajax({
        type: "post",
        url: basePath + addTagUrl,
        async: true,
        data: obj,
        success: function (data) {
            if (data.success == true) {
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:center"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '160px'],//宽高
                    content: '新建成功',
                    btn: ["确定"],
                    yes: function (index, layero) {
                        window.location.href = 'tag.html';
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        window.location.href = 'tag.html';
                    }
                });
            } else if (data.status == 9001) {
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
                    yes: function (index, layero) {
                        layer.closeAll();
                        window.top.location.href = basePath + toLoginUrl;
                    },
                    cancel: function (index, layero) {
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
                    btn: ["确定", "取消"]
                });
            }
        }
    });
}

$('#goback').on('click', function () {
    window.location.href = 'tag.html';
});

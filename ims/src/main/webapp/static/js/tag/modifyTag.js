var tagId = getUrlParam('tagId');

setTimeout("loadTag()", 100);
function loadTag() {
    $.ajax({
        type: "post",
        url: basePath + getTagUrl,
        async: true,
        data: {
            tagId: tagId
        },
        success: function (data) {
            $('#name').val(data.dataObject.name);
            $('#tagId').val(data.dataObject.id);

            var type = data.dataObject.type;
            $('#type').attr('data-value', type);
            switch (type) {
                case 0:
                    $('#type').html('用户标签');
                    break;
                default:
                    $('#type').html('用户标签');
                    break;
            }
        }
    });
}

//修改电桩制造商校验
$('body').off('blur', '#name').on('blur', '#name', function () {
    checkName($('#name').val());
});

function checkName(name) {
    if (!name) {
        $('#nameTip').html('标签名称不能为空');
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
function modifyTag() {
    var type = $('#type').attr('data-value');
    var name = $('#name').val();
    var tagId = $('#tagId').val();
    if (!checkType(type) || !checkName(name)) {
        return;
    }

    var obj = {
        id: tagId,
        type: type,
        name: name
    };
    $.ajax({
        type: "post",
        url: basePath + modifyTagUrl,
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
                    content: '保存成功',
                    btn: ["确定"],
                    yes:function(index,layero){
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

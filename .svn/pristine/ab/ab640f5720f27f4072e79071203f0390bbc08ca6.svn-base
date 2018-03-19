//获取角色列表的menuId
var getMenuMap = window.localStorage.getItem('menuMap');
var getCurruntMap = JSON.parse(getMenuMap);
var menuId = getCurruntMap['角色列表'];
$(function () {
    ctrlMenu(menuId);
    //去加载表格的数据
    setTimeout("roleListSearch()", 100);

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
            var data = req.dataObject;
            if(data==null){
                return;
            }
            if (data.length == 0) {
                return;
            } else {
                for (var i = 0; i < data.length; i++) {
                    var contents = data[i].contents;
                    if (contents.indexOf('新建') > -1) {
                        $('#addRole').show();
                    }
                    if (contents.indexOf('删除') > -1) {
                        $('#deleteRole').show();
                    }
                }
            }


        }
    });
}

function roleListSearch() {
    initTable("roleListForm", "roleListPage", roleListUrl, roleListCallback);
}
function roleListCallback(req) {
    var data = req.dataObject;
    var pageNum = req.pager.pageNo;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        listTr += '<tr><td><input type="checkbox" name="id" class="selectedBox" value="' + data[i].roleId + '"/></td><td><span class="toEditRole" data-roleId="' + data[i].roleId + '">' + data[i].roleName
        + '</span></td></tr>';
    }
    $("#myTbody").html(listTr);
}

//跳转到新建角色
$('body').off('click', '#addRole').on('click', '#addRole', function () {
    window.location.href = 'add_role.html';
})
$('body').off('click', '.toEditRole').on('click', '.toEditRole', function () {
    var roleId = $(this).attr('data-roleId');
    window.location.href = 'role_edit.html?roleId=' + roleId;
})
//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        roleListSearch();
    }
});

//点击删除，只能单个删除
$('body').off('click', '#deleteRole').on('click', '#deleteRole', function () {
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["确认", "font-size:12px;text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '180px'],//宽高
        content: $('.disabledContent'),
        btn: ["确定", "取消"],
        yes: function (index, layero) {
            layer.closeAll();
            toDeleteRole();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
});
function toDeleteRole() {
    var ids = '';
    $('input[name=id]').each(function () {
        var flag = $(this).prop('checked');
        if (flag == true) {
            ids += $(this).attr('value');
        }
    });
    if (!ids) {
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '160px'],//宽高
            content: '请指定表单元素',
            time: 3000,
            btn: ["确定"]
        });
    } else {
        $.ajax({
            type: "post",
            url: basePath + removeRoleUrl,
            async: true,
            data: {
                roleId: ids
            },
            success: function (data) {
                if (data.success == true) {
                    layer.closeAll();
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '160px'],//宽高
                        content: '删除成功',
                        btn: ["确定"],
                        yes: function (index, layero) {
                            layer.closeAll();
                            window.location.reload();
                        },
                        cancel: function (index, layero) {
                            layer.closeAll();
                            window.location.reload();
                        }
                    });

                } else {
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:center"],
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
}
$('body').on('click', 'input[name=id]', function () {
    var flag = $(this).prop('checked');
    if (flag == true) {
        $(this).parents('td').parents('tr').siblings().find('input[name=id]').prop('checked', false).attr('disabled', true);
    } else {
        $(this).parents('td').parents('tr').siblings().find('input[name=id]').attr('disabled', false);
    }

})
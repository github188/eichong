//获取首页故障通知的menuId
var getMenuMap = window.localStorage.getItem('menuMap');
var getCurruntMap = JSON.parse(getMenuMap);
var menuId = getCurruntMap['功能按钮发布'];
$(function(){
    setTimeout('appBtnListSearch()', 150);
    ctrlMenu(menuId);
})
function ctrlMenu(menuId) {
    $.ajax({
        type: "post",
        url: basePath + getSelfButtonTreeUrl,
        async: true,
        data: {
            menuId: menuId
        },
        success: function (data) {
            var data = data.dataObject;
            if (data == null) {
                return;
            }
            if (data == null) {
                return;
            }
            if (data.length == 0) {
                return;
            } else {
                for (var i = 0; i < data.length; i++) {
                    var contents = data[i].contents;
                    if (contents.indexOf('新建') > -1) {
                        $('#addList').show();
                    }
                    if (contents.indexOf('编辑') > -1) {
                        $('#editList').show();
                    }
                    if (contents.indexOf('删除') > -1) {
                        $('#delList').show();
                    }
                    if (contents.indexOf('下架') > -1) {
                        $('#closeList').show();
                    }
                    if (contents.indexOf('排序') > -1) {
                        $('#editBtnSort').show();
                    }
                }
            }
        }
    });
}

//去加载表格的数据
function appBtnListSearch() {
    initTable("AppBtnListForm", "appBtnListPage", getAppButtonListUrl, appBtnListCallback);
}
//表格数据
function appBtnListCallback(req) {
    var data = req.dataObject;
    var pageNum = req.pager.pageNo;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        var timeaaa=data[i].buttonUpdatedate.time;
        var newsInfoStatusName='';
        var buttonStatusValue =data[i].buttonStatus;
        if(buttonStatusValue==1){
            newsInfoStatusName='开启';
        }else if(buttonStatusValue==2){
            newsInfoStatusName='删除';
        }else if(buttonStatusValue==3){
            newsInfoStatusName='下架关闭';
        }

        listTr += '<tr><td><input type="checkbox" name="ids" class="selectedBox" value="' + data[i].pkButtonId + '"/></td>'
            + '<td ><input type="text" class="btnSortInput" data-id="'+ data[i].pkButtonId +'" data-type="'+ data[i].buttonStatus +'" data-option="'+ data[i].buttonSort +'" value="'+ data[i].buttonSort +'" />'
            + '</td><td ><a title="预览" onclick="pictureShow('+"'"+data[i].buttonPicUrl+"'"+')">缩略图</a>'
            + '</td><td >' + data[i].buttonName
            + '</td><td >' + new Date(timeaaa).format("yyyy-MM-dd hh:mm:ss")
            + '</td><td >' + newsInfoStatusName
            + '</td><td >' + data[i].buttonDesc
            + '</td></tr>';
    }
    $("#appBtnTb").html(listTr);
    $(".btnSortInput").each(function(){
        var type = $(this).attr("data-type");
        if(type==3){
            $(this).hide();
        }
    });
}
//点击查看图片
function pictureShow(picUrl){
    $('.pic').attr('src',picUrl);
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["", "font-size:12px;text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['360px', '380px'],//宽高
        content: $('.pictureShow')
    });
}

//播放顺序
$('body').off('blur', '.btnSortInput').on('blur', '.btnSortInput', function () {
    var newBannerSort = $(this).val();
    var reg=/(^1{0,1}[1-9]{1}$)|(^10$)|(^20$)/;
    if(!newBannerSort){
        layer.closeAll();
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '180px'],//宽高
            content: "播放顺序输入有误，请确认后重新输入1-20的整数",
            btn: ["确定"]
        });
        $(this).val('');
        return false;
    }else if(!reg.test(newBannerSort)){
        layer.closeAll();
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '180px'],//宽高
            content: "播放顺序输入有误，请确认后重新输入1-20的整数",
            btn: ["确定"]
        });
        $(this).val('');
        return false;
    }
});

$("#editBtnSort").click(function(){
    var btnList = new Array();
    var i = 0;
    var flag = 0;
    $(".btnSortInput").each(function(){
        var newBtnSort = $(this).val();
        var type = $(this).attr("data-type");
        if(newBtnSort==0){
            layer.closeAll();
            layer.open({
                type: 1,
                offset: '100px',
                title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                shadeClose: false,
                closeBtn: 1,
                area: ['310px', '180px'],//宽高
                content: "播放顺序不能为0",
                btn: ["确定"],
                yes:function (index,layero){
                    layer.closeAll();
                    $(this).val('');
                },
                cancel: function (index, layero) {
                    layer.closeAll();
                    $(this).val('');
                }
            });
            flag=1;
        }
        if(type!=3){
            btnList[i]=newBtnSort;
            i++;
        }
    });
    if(flag==1){
        return;
    }
    if(isRepeat(btnList)==true){
        layer.closeAll();
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '180px'],//宽高
            content: "播放循序重复，请重新确认！",
            btn: ["确定"]
        });
        return;
    }else {
        $(".btnSortInput").each(function(){
            var newBtnSort = $(this).val();
            var oldBtnSort =$(this).attr("data-option");
            var pkButtonId =$(this).attr("data-id");
            var type = $(this).attr("data-type");
            if(newBtnSort!==oldBtnSort){
                changeSortAjax(pkButtonId,newBtnSort);
            }else{
                window.location.href = 'AppBtnList.html';
            }
        });
    }
});
function changeSortAjax(a,b){
    $.ajax({
        type: "post",
        url: basePath + editButtonOrderUrl,
        async: false,
        data: {
            pkButtonId:a,
            newButtonSort:b
        },
        success: function (data) {
            if (data.success == false) {
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
                    yes:function (index,layero){
                        window.location.href = 'AppBtnList.html';
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        window.location.href = 'AppBtnList.html';
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

//判断开启的功能按钮播放顺序是否有重复
function isRepeat(arr){
    var hash = {};
    for(var i in arr) {
        if(hash[arr[i]])
            return true;
        hash[arr[i]] = true;
    }
    return false;
}

//点击新建
$('#addList').on('click', function () {
    window.location.href = "add_AppBtn.html";
});

//点击编辑
$('#editList').on('click', function () {
    var ids = '';
    $('input[name=ids]').each(function () {
        var flag = $(this).prop('checked');
        if (flag == true) {
            ids = $(this).attr('value');
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
    }else {
        var timestamp = Date.parse(new Date());
        window.location.href = "AppBtnEdit.html?pkButtonId="+ids+"&_="+timestamp;
    }

});

//点击下架
$("body").off("click", "#closeList").on("click", "#closeList", function () {
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["确认", "font-size:12px;text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '180px'],//宽高
        content: "确认下架该条消息？",
        btn: ["确定", "取消"],
        yes: function (index, layero) {
            layer.closeAll();
            toSubmitCloseAppIndex();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
});
function toSubmitCloseAppIndex() {
    var ids = '';
    $('input[name=ids]').each(function () {
        var flag = $(this).prop('checked');
        if (flag == true) {
            ids = $(this).attr('value');
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
            url: basePath + downAppButtonUrl,
            async: true,
            data: {
                pkButtonId:ids
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
                        content: '下架成功',
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
}

//点击删除
$("body").off("click", "#delList").on("click", "#delList", function () {
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["确认", "font-size:12px;text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '180px'],//宽高
        content: "确认删除该条消息？",
        btn: ["确定", "取消"],
        yes: function (index, layero) {
            layer.closeAll();
            toSubmitDeleteAppIndex();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
});
function toSubmitDeleteAppIndex() {
    var ids = '';
    $('input[name=ids]').each(function () {
        var flag = $(this).prop('checked');
        if (flag == true) {
            ids = $(this).attr('value');
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
            url: basePath + deleteAppButtonUrl,
            async: true,
            data: {
                pkButtonId: ids
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
                        },
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
}
//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        appBtnListSearch();
    }
});
//单个禁用
$('body').on('click', 'input[name=ids]', function () {
    var flag = $(this).prop('checked');
    if (flag == true) {
        $(this).parents('td').parents('tr').siblings().find('input[name=ids]').prop('checked', false).attr('disabled', true);
    } else {
        $(this).parents('td').parents('tr').siblings().find('input[name=ids]').attr('disabled', false);
    }

})
//获取首页故障通知的menuId
var getMenuMap = window.localStorage.getItem('menuMap');
var getCurruntMap = JSON.parse(getMenuMap);
var menuId = getCurruntMap['闪屏发布'];
$(function(){
    setTimeout('appFlashListSearch()', 150);
    toUnbindEvent();
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
                    if (contents.indexOf('下架') > -1) {
                        $('#closeList').show();
                    }
                }
            }
        }
    });
}

//去加载表格的数据
function appFlashListSearch() {
    toGiveHiddenInput();
    initTable("AppFlashListForm", "appFlashListPage", getAdvertisementListUrl, appFlashListCallback);
}
//下拉选项
function toUnbindEvent(){
    $('.advStsBlock').unbind();
    selectModel();
}
//状态选择
$('.advStsUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});
//表格数据
function appFlashListCallback(req) {
    var data = req.dataObject;
    var pageNum = req.pager.pageNo;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        var advStsName='';
        if(data[i].advSts==0){
            advStsName='未开始';
        }else if(data[i].advSts==1){
            advStsName='进行中';
        }else if(data[i].advSts==2){
            advStsName='已结束';
        }

        listTr += '<tr><td><input type="checkbox" name="ids" class="selectedBox" data-value="'+ data[i].advSts +'" value="' + data[i].pkAdId + '"/></td>'
            + '<td >' + data[i].adName
            + '<td >' + advStsName
            + '<td >' + new Date(data[i].beginAdTimeDate.time).format("yyyy-MM-dd hh:mm:ss")
            + '<td >' + new Date(data[i].endAdTimeDate.time).format("yyyy-MM-dd hh:mm:ss")
            + '<td >' + data[i].adDesc
            + '<td >' + data[i].adURL
            + '<td >' + new Date(data[i].adCreatedate.time).format("yyyy-MM-dd hh:mm:ss")
            + '<td >' + data[i].userAccount
            + '</td><td ><a title="预览" onclick="pictureShow('+"'"+data[i].advPicUrl+"'"+')">缩略图</a>'
            + '</td></tr>';
    }
    $("#myCompanyTb").html(listTr);
}
newTab(".newsInfo", "资讯详细");
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
//点击新建
$('#addList').on('click', function () {
    window.location.href = "add_AppFlash.html";
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
            toSubmitCloseAppInterpolation();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
});
function toSubmitCloseAppInterpolation() {
    var ids = '';
    var advSts='';
    $('input[name=ids]').each(function () {
        var flag = $(this).prop('checked');
        if (flag == true) {
            ids = $(this).attr('value');
            advSts=$(this).attr('data-value');
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
    } else if( advSts==2 ){
        layer.open({
            type: 1,
            offset: '100px',
            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
            shadeClose: false,
            closeBtn: 1,
            area: ['310px', '160px'],//宽高
            content: '当前闪屏信息已下架，请确认',
            time: 3000,
            btn: ["确定"]
        });
    } else {
        $.ajax({
            type: "post",
            url: basePath + downAdvertisementUrl,
            async: true,
            data: {
                pkAdId:ids
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

//查询条件部分=========================
function toGiveHiddenInput() {
    var advStsValue = $('#advSts').attr('data-value');
    var adTypeValue = $('#adType').val();
    if (advStsValue == "") {
        $('input[name=advSts]').val('');
    } else {
        $('input[name=advSts]').val(advStsValue);
    }
    if (adTypeValue == "") {
        $('input[name=adType]').val('');
    } else {
        $('input[name=adType]').val(adTypeValue);
    }
}

//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        appFlashListSearch();
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

});

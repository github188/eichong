//获取黑白名单列表的menuId
var getMenuMap = window.localStorage.getItem('menuMap');
var getCurruntMap = JSON.parse(getMenuMap);
var menuId = getCurruntMap['安卓强制升级'];
$(function(){
    ctrlMenu(menuId);
    //去加载表格的数据
    setTimeout('androidUpdateListSearch()', 150);
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
                        $('#addAndroidUpdate').show();
                    }
                    if (contents.indexOf('删除') > -1) {
                        $('#deleteAndroidUpdate').show();
                    }

                }
            }


        }
    });
}

function androidUpdateListSearch() {
    toGiveHiddenInput();
    initTable("androidUpdateListForm", "androidUpdateListPage", getVersionListUrl, androidUpdateListCallback);
}
//下拉选项
toUnbindEvent();
function toUnbindEvent() {
    $('.versTypeBlock').unbind();
    $('.isAutoUpdateBlock').unbind();
    selectModel();
}
//app类型
$('.versTypeUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
})
//是否强制
$('.isAutoUpdateUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
})
//表格数据
function androidUpdateListCallback(req) {
    var data = req.dataObject;

    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        var isAutoUpdateHtml='';
        var versTypeHtml='';
        var versCreatedate = data[i].versCreatedate;
        var versCreatedateHtml='';
        if(data[i].versCreatedate==null){
            versCreatedateHtml='';
        }else{
            var versCreatedate = data[i].versCreatedate.time;
            versCreatedateHtml=new Date(versCreatedate).format("yyyy-MM-dd hh:mm:ss");
        }

        if(data[i].isAutoUpdate==1){
            isAutoUpdateHtml='是';
        }else{
            isAutoUpdateHtml='否';
        }
        if(data[i].versType==1){
            versTypeHtml='爱充APP';
        }else if(data[i].versType==3){
            versTypeHtml='政企APP';
        }else if(data[i].versType==2){
            versTypeHtml='iOS';
        }

        listTr += '<tr><td><input type="checkbox" name="ids" class="selectedBox" value="' + data[i].pkVersion + '"/></td>'
        + '<td>'+versTypeHtml
        + '</td><td>' + data[i].versNumber
        + '</td><td>' + data[i].versUrl
        + '</td><td>' + data[i].versRemark
        + '</td><td>' + isAutoUpdateHtml
        + '</td><td>' + versCreatedateHtml
        + '</td></tr>';
    }
    $("#androidUpdateTbody").html(listTr);
}

//点击删除
$("body").off("click", "#deleteAndroidUpdate").on("click", "#deleteAndroidUpdate", function () {
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '180px'],//宽高
        content: "删除升级后：已经升级的客户端无法改变，未升级的客户端不会收到升级推送",
        btn: ["确定", "取消"],
        yes: function (index, layero) {
            layer.closeAll();
            toSubmitDisAbleUpdate();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
});
function toSubmitDisAbleUpdate() {
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
            url: basePath + delVersionUrl,
            async: true,
            data: {
                versionIds: ids
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
    var versTypeValue = $('#versTypeHtml').attr('data-value');
    var isAutoUpdateValue = $('#isAutoUpdateHtml').attr('data-value');
    if (versTypeValue == "") {
        $('input[name=versType]').val('');
    } else {
        $('input[name=versType]').val(versTypeValue);
    }
    if (isAutoUpdateValue == "") {
        $('input[name=isAutoUpdate]').val('');
    } else {
        $('input[name=isAutoUpdate]').val(isAutoUpdateValue);
    }

}


//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        androidUpdateListSearch();
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
$('#addAndroidUpdate').on('click', function () {
    window.location.href = "add_androidUpdate.html";
});
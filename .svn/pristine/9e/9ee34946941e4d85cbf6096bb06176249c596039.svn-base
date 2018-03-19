/**
 * Created by Administrator on 2017/9/7.
 */
//获取渠道活动的menuId
var getMenuMap = window.localStorage.getItem('menuMap');
var getCurruntMap = JSON.parse(getMenuMap);
var menuId = getCurruntMap['渠道活动'];

$(function(){
    ctrlMenu(menuId);
    //去加载表格的数据
    setTimeout('getIssuerList()', 150);
    setTimeout('activityListSearch()', 250);
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
                    if (contents.indexOf('终止') > -1) {
                        $('#endActivity').show();
                    }
                    if (contents.indexOf('新建') > -1) {
                        $('#addActivity').show();
                    }


                }
            }


        }
    });
}


function activityListSearch() {
    toGiveHiddenInput();
    initTable("activityForm", "avtivityListPage", getActivityListUrl, activityListCallback);
}
//下拉选项
toUnbindEvent();
function toUnbindEvent() {
    $('.statusTypeBlock').unbind();
    $('.actChanneltypeBlock').unbind();
    selectModel();
}
//表格数据
function activityListCallback(req) {
    var data = req.dataObject;
    var pageNum = req.pager.pageNo;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        var startTime = data[i].actBegindate.time;
        var actEnddate = data[i].actEnddate.time;
        var actStatusHtml='';
        if(data[i].actStatus==0){
            actStatusHtml='未开始';
        }
        if(data[i].actStatus==1){
            actStatusHtml='终止';
        }
        if(data[i].actStatus==2){
            actStatusHtml='进行中';
        }
        if(data[i].actStatus==3){
            actStatusHtml='已结束';
        }
        var actRemark=data[i].actRemark;
        actRemarkHtml=actRemark.substring(0,10);
        listTr += '<tr><td><input type="checkbox" name="ids" class="selectedBox" value="' + data[i].pkActivity + '"/></td>'
            + '<td class="activityList_number">'+ (i+1+(pageNum-1)*20)
            + '</td><td class="activityList_name">'+data[i].actActivityname
            + '</td><td class="activityList_status">' + actStatusHtml
            + '</td><td class="activityList_cpyCompanyName">' + data[i].cpyCompanyName
            + '</td><td class="activityList_prizes">' + data[i].prizeName
            + '</td><td class="activityList_startTime">' + new Date(startTime).format("yyyy-MM-dd")
            + '</td><td class="activityList_endTime">' + new Date(actEnddate).format("yyyy-MM-dd")
            + '</td><td class="activityList_remark"><a class="gay" title="'+data[i].actRemark+'">' + actRemarkHtml
            + '</a></td></tr>';
    }
    $("#myCompanyTb").html(listTr);
}
//新增活动
$('#addActivity').on('click', function () {
    window.location.href = "add_cpyActivity.html";
});
//状态
$('.statusUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
})
//渠道
$('.actChanneltypeUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
})
//点击终止
$("body").off("click", "#endActivity").on("click", "#endActivity", function () {
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["确认", "font-size:12px;text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['390px', '240px'],//宽高
        content: $('.endActivityLayer'),
        btn: ["确定", "取消"],
        yes: function (index, layero) {
            layer.closeAll();
            toEndActivity();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
});
function toEndActivity() {
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
            url: basePath + stopActivityUrl,//终止活动url
            async: true,
            data: {
                pkActivity: ids,
                actType:2
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
                        content: '设置终止活动成功',
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

function toLoadCpy() {
    $.ajax({
        type: "post",
        url: basePath + getCompanyListUrl,
        async: true,
        data: {
            provinceCode: '',
            cityCode: ''
        },
        success: function (data) {
            if (data.success == true) {
                var dataModule = data.dataObject;
                var cypCompanyLi = '<li data-option="">请选择</li>';
                for (var i = 0; i < dataModule.length; i++) {
                    cypCompanyLi += '<li data-option="' + dataModule[i].cpyId + '" data-cpyNumber="' + dataModule[i].cpyNumber + '">' + dataModule[i].cpyName + '</li>';
                }
                $('.actChanneltypeUl').html(cypCompanyLi);
                toUnbindEvent();
            }
        }
    });
}
//查询条件部分=========================
function toGiveHiddenInput() {
    var statusType = $('#statusType').attr('data-value');
    var actChanneltype = $('#actChanneltype').attr('data-value');
    if (statusType == "") {
        $('input[name=actStatus]').val('');
    } else {
        $('input[name=actStatus]').val(statusType);
    }
    if (actChanneltype == "") {
        $('input[name=actChanneltype]').val('');
    } else {
        $('input[name=actChanneltype]').val(actChanneltype);
    }
}

//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        activityListSearch();
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
//发行方
$('.actChanneltypeUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});
//加载发行方 (修改...)
function getIssuerList() {
    var issuerLi = '';
    $.ajax({
        type: "post",
        url: basePath + getCpyForListUrl,
        async: true,
        data: {
            actType : 2
        },
        success: function (data) {
            if (data.success == true) {
                var dataModule = data.dataObject;
                issuerLi = '<li data-option="" class="seleced">请选择</li>';
                for (var i = 0; i < dataModule.length; i++) {
                    issuerLi += '<li data-option="' + dataModule[i].pkCompanyId + '">' + dataModule[i].cpyCompanyName + '</li>';
                }
                $('.actChanneltypeUl').html(issuerLi);
                toUnbindEvent();
            }

        }
    });
}

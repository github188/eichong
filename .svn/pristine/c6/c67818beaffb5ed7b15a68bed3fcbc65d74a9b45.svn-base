//获取电卡申请列表的menuId
var getMenuMap = window.localStorage.getItem('menuMap');
var getCurruntMap = JSON.parse(getMenuMap);
var menuId = getCurruntMap['电卡申请列表'];
$(function () {
    ctrlMenu(menuId);
    setTimeout("cardApplicationListSearch()", 200);
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
            if(data==null){
                return;
            }
            if (data.length == 0) {
                return;
            } else {
                for (var i = 0; i < data.length; i++) {
                    var contents = data[i].contents;
                    if (contents.indexOf('申请驳回') > -1) {
                        $('#rejectionApplyBtn').show();
                    }
                }
            }
        }
    });
}
function toUnbindEvent() {
    $('.statusBlock').unbind();
    selectModel();
}
toUnbindEvent();

function cardApplicationListSearch() {
    toGiveHiddenInput();
    initTable("cardApplicationForm", "cardApplicationPage", getCardApplicationFormListUrl, cardApplicationListCallback);
}
function cardApplicationListCallback(req) {
    var data = req.dataObject;
    var pageNum = req.pager.pageNo;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        var statusHtml='';
        if(data[i].status==0){
            statusHtml='申请中';
        }
        if(data[i].status==1){
            statusHtml='申请成功';
        }
        if(data[i].status==2){
            statusHtml='申请失败';
        }
        var gmtCreateTime=data[i].gmtCreate.time;
        listTr += '<tr><td><input type="checkbox" name="id" class="selectedBox" value="' + data[i].id + '"/></td>'
        + '<td>' + (i + 1 + (pageNum - 1) * 20)
        + '</td><td>' + data[i].userAccount
        + '</td><td>' + data[i].cpyName
        + '</td><td>' + data[i].cpyNumber
        + '</td><td>' + data[i].realName
        + '</td><td>' + data[i].phone
        + '</td><td>' + data[i].address
        + '</td><td>' +new Date(gmtCreateTime).format("yyyy-MM-dd")
        + '</td><td>' + statusHtml
        + '</td></tr>';
    }
    $("#myTbogy").html(listTr);
}
function toGiveHiddenInput() {
    var statusHtmlValue = $('#statusHtml').attr('data-value');
    if (statusHtmlValue == "") {
        $('input[name=status]').val('');
    } else {
        $('input[name=status]').val(statusHtmlValue);
    }
    getDateValue();
}
$('.statusUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});

//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        cardApplicationListSearch();
    }
});
//日期选择
laydate.render({
    elem: '#datePicker'
    , range: true,
    theme: '#ff7d00'
});

function getDateValue() {
    var dateValue = $('#datePicker').val();
    if (dateValue == "") {
        $('input[name=applicationStartTime]').val('');
        $('input[name=applicationEndTime]').val('');
    } else {
        var dateValue = $('#datePicker').val();
        var applicationStartTime = dateValue.substring(0, 10);
        var applicationEndTime = dateValue.substring(13, 23);
        $('input[name=applicationStartTime]').val(applicationStartTime);
        $('input[name=applicationEndTime]').val(applicationEndTime);
    }

}
//申请驳回按钮
$("body").off("click", "#rejectionApplyBtn").on("click", "#rejectionApplyBtn", function () {
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["确认", "font-size:12px;text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '180px'],//宽高
        content: '确定申请驳回吗？',
        btn: ["确定", "取消"],
        yes: function (index, layero) {
            layer.closeAll();
            toRejectionApply();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
});
function toRejectionApply() {
    var ids = '';
    $('input[name=id]').each(function () {
        var flag = $(this).prop('checked');
        if (flag == true) {
            ids += $(this).attr('value') + ',';
        }
    });
    ids = ids.substring(0, ids.length - 1);
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
            btn: ["确定", "取消"]
        });
    } else {
        $.ajax({
            type: "post",
            url: basePath + rejectionApplyUrl,
            async: true,
            data: {
                cardApplicationFormIds: ids
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
                        content: '驳回成功',
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
                        btn: ["确定", "取消"]
                    });
                }
            }
        });
    }
}
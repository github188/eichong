//获取用户列表的menuId
var getMenuMap = window.localStorage.getItem('menuMap');
var getCurruntMap = JSON.parse(getMenuMap);
var menuId = getCurruntMap['意见反馈列表'];
$(function(){
    ctrlMenu(menuId);
    feedbackListSearch();
    //下拉选项
    toUnbindEvent();
})
//去加载表格的数据
function feedbackListSearch() {
    toGiveHiddenInput();
    initTable("feedbackListForm", "feedbackListPage", getFeedBackListUrl, feedbackListCallback);
}

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
                    if (contents.indexOf('处理') > -1) {
                        $('#handing').show();
                    }
                }
            }


        }
    });
}

function toUnbindEvent() {
    $('.feedbackStatusBlock').unbind();
    selectModel();
}
//表格数据
function feedbackListCallback(req) {
    var data = req.dataObject;
    var pageNum = req.pager.pageNo;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        var statusHtml='';
        if(data[i].status==0){
            statusHtml='未处理';
        }else if(data[i].status==1){
            statusHtml='处理中';
        }else if(data[i].status==2){
            statusHtml='已处理';
        }
        listTr += '<tr><td><input type="checkbox" name="ids" class="selectedBox" value="' + data[i].pkFeedBack + '"/></td>'
            + '</td><td>' + data[i].userAccount
            + '</td><td>' + data[i].createDate
            + '</td><td>' + data[i].content
            + '</td><td>' + statusHtml
            + '</td><td>' + data[i].reason
            + '</td><td>' + data[i].replyUserAccount
            + '</td></tr>';
    }
    $("#myTbogy").html(listTr);
}

//点击处理
$("body").off("click", "#handing").on("click", "#handing", function () {
    toHanding();
});
function toHanding(){
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
            url: basePath + getFeedBackByIdUrl,
            async: true,
            data: {
                pkFeedBack: ids
            },
            success: function (req) {
                if (req.success == true) {
                    var data = req.dataObject;
                    var statusHtml='';
                    if(data.status==0){
                        statusHtml='未处理';
                    }else if(data.status==1){
                        statusHtml='处理中';
                    }else if(data.status==2){
                        statusHtml='已处理';
                    }
                    $('#accountNumber').val(data.userAccount);
                    $('#connectPon').val(data.userName);
                    $('#time').val(data.createDate);
                    $('#feadbackText').html(data.content);
                    $('#feedBackCon').html(data.reason);
                    $('#handStatus').html(statusHtml);
                    $('#handStatus').attr('data-value',data.status);
                    layer.closeAll();
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '460px'],//宽高
                        content: $('.tohanding'),
                        btn: ["确定"],
                        yes: function (index, layero) {
                            toSubmitHanding(ids);
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
                        content: req.msg,
                        time: 3000,
                        btn: ["确定"]
                    });
                }
            }
        });
    }
}
function toSubmitHanding(ids){
    var statusValue=$('#handStatus').attr('data-value');
    var reasonHtml=$('#feedBackCon').val();
    var isPush='';
    var flag = $('#massageTip').prop('checked');
    if(flag == true){
        isPush=1
    }else if(flag == false){
        isPush=0
    }
    $.ajax({
        type: "post",
        url: basePath + editFeedBackUrl,
        async: false,
        data: {
            pkFeedBack: ids,
            status:statusValue,
            reason:reasonHtml,
            isPush:isPush
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
                    area: ['310px', '460px'],//宽高
                    content: '修改成功',
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
//状态
$('.statusUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
})
//查询部分初始化
function toGiveHiddenInput() {
    var userAccountValue = $('#userAccount').val();
    var statusValue = $('#status').attr('data-value');
    if (userAccountValue == '') {
        $('input[name=userAccount]').val('');
    } else {
        $('input[name=userAccount]').val(userAccountValue);
    }
    if (statusValue == "") {
        $('input[name=status]').val('');
    } else {
        $('input[name=status]').val(statusValue);
    }
    getDateValue();
}

//日期选择
laydate.render({
    elem: '#datePicker'
    , range: true,
    theme: '#ff7d00'
    /* ,min: -90,
     max:0//0 代表今天 -1代表昨天，-2代表前天，以此类推*/
});
function getDateValue() {
    var dateValue = $('#datePicker').val();
    if (dateValue == "") {
        $('input[name=startDate]').val('');
        $('input[name=endDate]').val('');
    } else {
        var dateValue = $('#datePicker').val();
        var startGmtCreate = dateValue.substring(0, 10);
        var endGmtCreate = dateValue.substring(13, 23);
        $('input[name=startDate]').val(startGmtCreate);
        $('input[name=endDate]').val(endGmtCreate);
    }
}

//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        feedbackListSearch();
    }
});
//单个选择
$('body').on('click', 'input[name=ids]', function () {
    var flag = $(this).prop('checked');
    if (flag == true) {
        $(this).parents('td').parents('tr').siblings().find('input[name=ids]').prop('checked', false).attr('disabled', true);
    } else {
        $(this).parents('td').parents('tr').siblings().find('input[name=ids]').attr('disabled', false);
    }

})

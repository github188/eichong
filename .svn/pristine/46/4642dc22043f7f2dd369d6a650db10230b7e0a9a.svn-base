/**
 * Created by linzhijing on 2018/3/2.
 */
$(function () {
    setTimeout("getAppLoginRecordListSearch()", 50);
})
function toUnbindEvent() {
    $('.deviceTypeBlock').unbind();
    selectModel();
}
toUnbindEvent();
//去加载表格的数据

function getAppLoginRecordListSearch() {
    toGiveHiddenInput();
    initTable("appLoginRecordListForm", "appLoginRecordListPage", getAppLoginRecordListUrl, getAppLoginRecordListCallback);
}
function getAppLoginRecordListCallback(req) {
    var data = req.dataObject;
    var pageNum = req.pager.pageNo;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        var deviceTypeHtml=data[i].deviceType;
        var deviceTypeName='';
        if(deviceTypeHtml==1){
            deviceTypeName='android';
        }else{
            deviceTypeName='ios';
        }
        listTr += '<tr>'
            + '<td>' + (i + 1 + (pageNum - 1) * 20)
            + '</td><td>' + data[i].createdate
            + '</td><td>' + deviceTypeName
            + '</td><td>' + data[i].registrationID
            + '</td><td>' + data[i].userAccount
            + '</td><td>' + data[i].ipAddress
            + '</td><td>' + data[i].longitude
            + '</td><td>' + data[i].latitude
            + '</td><td>' + (data[i].provinceName) + (data[i].cityName)
            + '</td></tr>';

    }
    $("#appLoginRecordTbody").html(listTr);
}
function toGiveHiddenInput() {
    var deviceTypeHtmlValue = $('#deviceTypeHtml').attr('data-value');
    if (deviceTypeHtmlValue == "") {
        $('input[name=deviceType]').val('');
    } else {
        $('input[name=deviceType]').val(deviceTypeHtmlValue);
    }
    getDateValue();
}
$('.deviceTypeUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});

//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        getAppLoginRecordListSearch();
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
        $('input[name=createdateBegin]').val('');
        $('input[name=createdateEnd]').val('');
    } else {
        var dateValue = $('#datePicker').val();
        var createdateBegin = dateValue.substring(0, 10);
        var createdateEnd = dateValue.substring(13, 23);
        $('input[name=createdateBegin]').val(createdateBegin);
        $('input[name=createdateEnd]').val(createdateEnd);
    }

}

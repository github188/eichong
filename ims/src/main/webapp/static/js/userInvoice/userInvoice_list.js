//下拉选项
toUnbindEvent();
function toUnbindEvent() {
    $('.ivInvoiceStatusBlock').unbind();
    selectModel();
}
//去加载表格的数据
setTimeout('userInvoiceListSearch()', 190);
function userInvoiceListSearch() {
    toGiveHiddenInput();
    initTable("userInvoiceListForm", "userInvoiceListPage", getFinAppInvoiceListUrl, userInvoiceListCallback);
}

//表格数据
function userInvoiceListCallback(req) {
    var data = req.dataObject;
    var pageNum = req.pager.pageNo;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        var ivPayFreightName = '';
        if(data[i].ivPayFreight==0){
            ivPayFreightName='账户余额支付';
        }else if(data[i].ivPayFreight==1){
            ivPayFreightName='支付宝支付';
        }else if(data[i].ivPayFreight==2){
            ivPayFreightName='微信支付';
        }else if(data[i].ivPayFreight==3){
            ivPayFreightName='银联支付 ';
        }else if(data[i].ivPayFreight==4){
            ivPayFreightName='货到付款';
        }
        var ivReceipTypeName = '';
        if(data[i].ivReceipType==0){
            ivReceipTypeName='普通发票';
        }else if(data[i].ivReceipType==1){
            ivReceipTypeName='增值税专用发票';
        }
        var ivInvoiceStatusName = '';
        if(data[i].ivInvoiceStatus==0){
            ivInvoiceStatusName='已申请（待开票）';
        }else if(data[i].ivInvoiceStatus==1){
            ivInvoiceStatusName='已开票 ';
        }else if(data[i].ivInvoiceStatus==2){
            ivInvoiceStatusName='已申请，未支付邮费';
        }else if(data[i].ivInvoiceStatus==3){
            ivInvoiceStatusName='已拒绝';
        }
        listTr += '<tr><td><input type="checkbox" value="'+data[i].pkInvoice+'" /></td><td>' + (i+1+(pageNum-1)*20)
            + '</td><td><a class="invoiceDetail" onclick="return false" href="' + basePath + '/static/html/userInvoice/userInvoice_detail.html?pkInvoice=' + data[i].pkInvoice +'">' + data[i].userAccount + '</a>'
            + '</t></td><td>' + data[i].ivCreatedate
            + '</td><td>' + data[i].discountChangMoney
            + '</td><td>' + data[i].discountServiceMoney
            + '</td><td>' + data[i].ivFreightAmount
            + '</td><td>' + ivPayFreightName
            + '</td><td>' + ivInvoiceStatusName
            + '</td><td>' + ivReceipTypeName
            + '</td><td>' + data[i].ivUpdatedate
            + '</td></tr>';
    }
    $("#myTbogy").html(listTr);
}
//点击跳转
newTab(".invoiceDetail", "个人开票详情");
//查询条件部分=========================
function toGiveHiddenInput() {
    var userAccountValue = $('#userAccount').val();
    var ivInvoiceStatusValue = $('#ivInvoiceStatus').attr('data-value');
    if (userAccountValue == "") {
        $('input[name=userAccount]').val('');
    } else {
        $('input[name=userAccount]').val(userAccountValue);
    }
    if (ivInvoiceStatusValue == "") {
        $('input[name=ivInvoiceStatus]').val('');
    } else {
        $('input[name=ivInvoiceStatus]').val(ivInvoiceStatusValue);
    }
    getDateValue();
    getTimeValue();
}

//日期选择
laydate.render({
    elem: '#datePicker'
    , range: true,
    theme: '#ff7d00'
});
function getDateValue() {
    var dateValue = $('#datePicker').val();
    if (dateValue == "") {
        $('input[name=ivCreatedateBegin]').val('');
        $('input[name=ivCreatedateEnd]').val('');
    } else {
        var dateValue = $('#datePicker').val();
        var startGmtCreate = dateValue.substring(0, 10);
        var endGmtCreate = dateValue.substring(13, 23);
        $('input[name=ivCreatedateBegin]').val(startGmtCreate);
        $('input[name=ivCreatedateEnd]').val(endGmtCreate);
    }

}
//日期选择
laydate.render({
    elem: '#timePicker'
    , range: true,
    theme: '#ff7d00'
});
function getTimeValue() {
    var dateValue = $('#timePicker').val();
    if (dateValue == "") {
        $('input[name=ivUpdatedateBegin]').val('');
        $('input[name=ivUpdatedateEnd]').val('');
    } else {
        var dateValue = $('#timePicker').val();
        var startGmtCreate = dateValue.substring(0, 10);
        var endGmtCreate = dateValue.substring(13, 23);
        $('input[name=ivUpdatedateBegin]').val(startGmtCreate);
        $('input[name=ivUpdatedateEnd]').val(endGmtCreate);
    }

}

//状态筛选部分
$('.ivInvoiceStatusUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
})


//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        userInvoiceListSearch();
    }
});


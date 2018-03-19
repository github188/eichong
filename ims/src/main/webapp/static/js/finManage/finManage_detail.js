
//去加载表格的数据
setTimeout('divideListSearch()', 150);
function divideListSearch() {
    toGiveHiddenInput();
    initTable("divideListForm", "divideListPage", getFinAccountSplitDetailsListUrl, divideListCallback);
}
toUnbindEvent();
function toUnbindEvent() {
    $('.orderStatusBlock').unbind();
    selectModel();
}
//表格数据
function divideListCallback(req) {
    var data = req.dataObject;
    var pageNum = req.pager.pageNo;
    var listTr = "";
    for(var i=0;i<data.length;i++){
        var internalDa = JSON.parse(data[i].internalData);
            if(internalDa[0].serviceChargeSplitAmt  == 'null'){
                internalDa[0].serviceChargeSplitAmt  = (0).toFixed(1);
            }
            if(internalDa[0].couponSplitAmt == 'null'){
                internalDa[0].couponSplitAmt = (0).toFixed(1);
            }
            if(internalDa[0].electricityChargeSplitAmt == 'null'){
                internalDa[0].electricityChargeSplitAmt = (0).toFixed(1);
            }
            if(internalDa[0].electricPowerSplitAmt == 'null'){
                internalDa[0].electricPowerSplitAmt = (0).toFixed(1);
            }
        listTr += '<tr><td rowspan="'+internalDa.length+'"><input type="checkbox" name="ids" class="selectedBox" value="' + data[0].pkId + '"/>'
            + '</td><td>' + internalDa[0].cpyName + '</a>'
            + '</td><td >' + internalDa[0].serviceChargeSplitAmt
            + '</td><td >' + internalDa[0].electricityChargeSplitAmt
            + '</td><td >' + internalDa[0].electricPowerSplitAmt
            + '</td><td >' + internalDa[0].couponSplitAmt
            + '</td><td rowspan="'+internalDa.length+'">' + data[i].chargingOrderCode
            + '</td><td rowspan="'+internalDa.length+'">' + data[i].quantityElectricity
            + '</td><td rowspan="'+internalDa.length+'">' + data[i].totalMoney
            + '</td><td rowspan="'+internalDa.length+'">' + data[i].chargeMoney
            + '</td><td rowspan="'+internalDa.length+'">' + data[i].serviceMoney
            + '</td><td rowspan="'+internalDa.length+'">' + data[i].orderStatusName
            + '</td><td rowspan="'+internalDa.length+'">' + data[i].endChargeTime
            + '</td></tr>';
        for(var j=1;j<internalDa.length;j++){
            if(internalDa[j].serviceChargeSplitAmt  == 'null'){
                internalDa[j].serviceChargeSplitAmt  = (0).toFixed(1);
            }
            if(internalDa[j].couponSplitAmt == 'null'){
                internalDa[j].couponSplitAmt = (0).toFixed(1);
            }
            if(internalDa[j].electricityChargeSplitAmt == 'null'){
                internalDa[j].electricityChargeSplitAmt = (0).toFixed(1);
            }
            if(internalDa[j].electricPowerSplitAmt == 'null'){
                internalDa[j].electricPowerSplitAmt = (0).toFixed(1);
            }
            listTr += '<tr>' +
                '<td>'+internalDa[j].cpyName+'</td>' +
                '<td>'+internalDa[j].serviceChargeSplitAmt+'</td>' +
                '<td>'+internalDa[j].electricityChargeSplitAmt+'</td>' +
                '<td>'+internalDa[j].electricPowerSplitAmt+'</td>' +
                '<td>'+internalDa[j].couponSplitAmt+'</td></tr>'
        }
    }
    $("#myCompanyTb").html(listTr);
}
//状态
$('.orderStatusUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});
//查询条件
function toGiveHiddenInput() {
    var chargingOrderStatus = $('#chargingOrderStatus').attr('data-value');
    if (chargingOrderStatus == "") {
        $('input[name=chargingOrderStatus]').val('');
    } else {
        $('input[name=chargingOrderStatus]').val(chargingOrderStatus);
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
        divideListSearch();
    }
});
//单个编辑
$('body').on('click', 'input[name=ids]', function () {
    var flag = $(this).prop('checked');
    if (flag == true) {
        $(this).parents('td').parents('tr').siblings().find('input[name=ids]').prop('checked', false).attr('disabled', true);
    } else {
        $(this).parents('td').parents('tr').siblings().find('input[name=ids]').attr('disabled', false);
    }

})

$('#cpyName').on('onpropertychange input',function(){
    var length=$(this).val().length;
    if(length==0){
        $('input[name="cpyId"]').val('');
    }
});

var last;
$("#cpyName").keyup(function(event){
    $('input[name="cpyId"]').val('');
    last = event.timeStamp;
    setTimeout(function(){
        if(last-event.timeStamp==0){
            var cpyName=$('#cpyName').val();
            if(cpyName.length<1){
                $('#cpyId').val('');
                $('.cpyNameUl').css('display','none');
                $('.cpyNameUl').html('');
                return;
            }else{
                $('.cpyNameUl').css('display','block');
                var cpyObject = {
                    cpyName:cpyName
                };
                if (JSON.stringify(cpyObject) == "{}") {
                    $('#cpyName').val('');
                    $('#cpyId').val('');
                } else {
                    toAjaxCompany(cpyObject);
                }
            }
        }
    },500)
});

function toAjaxCompany(cpyObject) {
    $.ajax({
        type: "post",
        url: basePath + getCompanyListByCpyNameUrl,
        async: false,
        data: cpyObject,
        success: function (data) {
            if (data.success == true) {
                var dataModule = data.dataObject;
                var cpyLi = '<li data-option="">请选择</li>';
                for (var i = 0; i < dataModule.length; i++) {
                    cpyLi += '<li data-option="' + dataModule[i].cpyId + '">' + dataModule[i].cpyName + '</li>';
                }
                $('.cpyNameUl').html(cpyLi);
            }
        }
    });
}
$('body').off('click','.cpyNameUl li').on('click','.cpyNameUl li',function(){
    $('#cpyName').val('');
    $('#cpyId').val('');
    var id=$(this).attr('data-option');
    var name=$(this).html();
    $('#cpyName').val(name);
    $('#cpyId').val(id);
    $('.cpyNameUl').css('display','none');
    $('.cpyNameUl').html('');
})

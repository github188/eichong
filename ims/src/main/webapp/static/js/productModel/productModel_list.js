//获取产品型号的menuId
var getMenuMap = window.localStorage.getItem('menuMap');
var getCurruntMap = JSON.parse(getMenuMap);
var menuId = getCurruntMap['产品型号'];

$(function(){
    ctrlMenu(menuId);
    //去加载表格的数据
    setTimeout('productModelListSearch()', 150);
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
            //console.log(data);
            if (data.length == 0) {
                return;
            } else {
                for (var i = 0; i < data.length; i++) {
                    var contents = data[i].contents;
                    if (contents.indexOf('新建') > -1) {
                        $('#addList').show();
                    }
                }
            }
        }
    });
}
toGiveHiddenInput();

function productModelListSearch() {
    toGiveHiddenInput();
    initTable("productModelListForm", "productModelListPage", getTypeSpanListUrl, productModelListCallback);
}
//表格数据
function productModelListCallback(req) {
    var data = req.dataObject;
    var pageNum = req.pager.pageNo;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        listTr += '<tr><td><input type="checkbox" name="ids" class="selectedBox" value="' + data[i].pkTypeSpanId + '"/></td>'
            + '<td class="tsList_number">'+ (i+1+(pageNum-1)*20)
            + '</td><td class="tsList_tsTypeSpan"><t class="toDetailHtml" data-value="'+data[i].pkTypeSpanId+'">'+data[i].tsTypeSpan
            + '</t></td><td class="tsList_tsProductNumber">' + data[i].tsProductNumber
            + '</td><td class="tsList_tsModelName">' + data[i].tsModelName
            + '</td><td class="tsList_tsRemarks">' + data[i].tsRemarks
            + '</td></tr>';
    }
    $("#myCompanyTb").html(listTr);
}
$('body').off('click','.toDetailHtml').on('click','.toDetailHtml',function(){
    var pkTypeSpanId = $(this).attr('data-value');
    window.location.href = 'productModel_detail.html?pkTypeSpanId=' + pkTypeSpanId;
})
//新建黑白名单
$("body").off("click", "#addList").on("click", "#addList", function () {
    window.location.href = "add_productModel.html";
})
//查询条件部分=========================
function toGiveHiddenInput() {
    var tsTypeSpanValue = $('#tsTypeSpan').val();
    if (tsTypeSpanValue == "") {
        $('input[name=tsTypeSpan]').val('');
    } else {
        $('input[name=tsTypeSpan]').val(tsTypeSpanValue);
    }
}
//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        productModelListSearch();
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
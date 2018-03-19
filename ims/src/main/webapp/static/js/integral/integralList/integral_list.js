/*//获取积分列表的menuId
 var getMenuMap = window.localStorage.getItem('menuMap');
 var getCurruntMap = JSON.parse(getMenuMap);
 var menuId = getCurruntMap['积分列表'];*/
$(function () {
    setTimeout('integralListSearch()', 100);
})

function integralListSearch() {
    initTable("integralListForm", "integralListPage", getIntegralListUrl, integralListCallback);
}
function integralListCallback(req) {
    var data = req.dataObject;
    var pageNum = req.pager.pageNo;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        listTr += '<tr><td><input type="checkbox" name="id" class="selectedBox" value="' + data[i].pkId + '"/>'
        + '</td><td>' + (i + 1 + (pageNum - 1) * 20)
        + '</td><td><a class="integralDetail" onclick="return false;" href="' + basePath + '/static/html/integral/integralList/integral_detail.html?integralId=' + data[i].pkId +'&availableIntegrals=' + data[i].availableIntegrals + '&normName=' + data[i].normName+ '&userAccount=' + data[i].userAccount+ '">' + data[i].availableIntegrals + '</a>'
        + '</td><td>' + data[i].userAccount
        + '</td><td>' + data[i].normName
        + '</td><td>' + data[i].totalIntegrals
        + '</td><td>' + data[i].usedIntegrals
        + '</td></tr>';

    }
    $("#myTbogy").html(listTr);
}
newTab(".integralDetail", "积分详情");
//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        integralListSearch();
    }
});

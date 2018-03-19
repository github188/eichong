$(function(){
    var integralId=getUrlParam('integralId');
    getIntegralDetailsListSearch(integralId);
    setTimeout('getDetail()',100);
})
function getDetail(){
    //获取基础数据
    var availableIntegrals=getUrlParam('availableIntegrals');
    var normName=getUrlParam('normName');
    var userAccount=getUrlParam('userAccount');
    $('#availableIntegrals').html(availableIntegrals)
    $('#normName').html(normName)
    $('#userAccount').html(userAccount)
}
function getIntegralDetailsListSearch(integralId) {
    initTable("", "integralDetailsPage", getIntegralDetailsListUrl + '?integralId=' + integralId, getIntegralDetailsListCallback);
}
function getIntegralDetailsListCallback(req) {
    var data = req.dataObject;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        var integralDate = data[i].integralDate.time;
        listTr += '<tr><td>' + data[i].integralValue
        + '</td><td>' + data[i].directionName
        + '</td><td>' + data[i].activityName
        + '</td><td>' + data[i].moneyInvolved
        + '</td><td>' + new Date(integralDate).format("yyyy-MM-dd hh:mm")
        + '</td></tr>';
    }
    $("#myTbody").html(listTr);
}
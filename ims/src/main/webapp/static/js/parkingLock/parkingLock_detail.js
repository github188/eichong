function editUnBind() {
}//什么也不做，详情要移除绑定事件
var parkingLockId = getUrlParam('id');
getDetailById(parkingLockId);
function getDetailById(parkingLockId) {
    $.ajax({
        type: "post",
        url: basePath + getParkingLockUrl,
        async: true,
        data: {
            parkingLockId: parkingLockId
        },
        success: function (data) {
            var data = data.dataObject;
            var code = data.code;
            $('input[name=code]').val(code);

            var powerStationId = data.powerStationId;
            $('input[name=powerStationId]').val(powerStationId);
            $('#powerStationName').attr('data-powerStationId', powerStationId);

            var powerStationName = data.powerStationName;
            $('#powerStationName').attr('data-powerStationName',powerStationName);

            var aTag = '<a class="toScanBtn" onclick="return false;"  href="' + basePath + '/static/html/electric/powerStation/powerStation_detail.html?powerstationId=' + powerStationId + '">'+powerStationName+'</a>';
            $('#powerStationName').append(aTag);//自动生成a标签

            var address = data.address;
            $('input[name=address]').val(address);

            var platformLockKey = data.platformLockKey;
            $('input[name=platformLockKey]').val(platformLockKey);



            var parkingLockPlatform = data.parkingLockPlatform;
            if (parkingLockPlatform == 0) {
                $('#parkingLockPlatformHtml').attr('data-value', 0).html('电喵');
            }
            if (parkingLockPlatform == 1) {
                $('#parkingLockPlatformHtml').attr('data-value', 1).html('慧泊金');
            }

            var status = data.status;
            if (status == 0) {
                $('#parkingStatusHtml').attr('data-value', 0).html('正常');
            }
            if (status == 1) {
                $('#parkingStatusHtml').attr('data-value', 1).html('故障');
            }
            if (status == 2) {
                $('#parkingStatusHtml').attr('data-value', 2).html('正在使用');
            }
        }
    });
}
$('#goback').off('click').on('click', function () {
    window.location.href = 'parkingLock_list.html';
})

$('#saveBtn').off('click').on('click', function () {
    window.location.href = 'packingLock_edit.html?id=' + parkingLockId;
})
function removeBindEvent() {
    $('.lockScopeBlock').unbind();
    $('.lockStutasBlock').unbind();
    selectModel();
}
newTab(".toScanBtn", "充电点列表");
//点击充电点名称
/*
$('#powerStationName').off('click').on('click', function () {
    var powerStationNameId = $(this).attr('data-powerStationId');
    var powerStationName = $(this).attr('data-powerStationName');


})*/

var powerstationId = getUrlParam('powerstationId');
//获取充电点主页的menuId
var getMenuMap = window.localStorage.getItem('menuMap');
var getCurruntMap = JSON.parse(getMenuMap);
var menuId = getCurruntMap['充电点主页'];
$(function () {
    ctrlMenu(menuId);
    setTimeout("getPowerStation()", 100);
    //加载对应的充电点下面的电桩
    setTimeout("toLoadElectricPils()", 200);
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
            if (data.length == 0) {
                return;
            } else {
                for (var i = 0; i < data.length; i++) {
                    var contents = data[i].contents;
                    if (contents.indexOf('编辑') > -1) {
                        $('#editBtn').show();
                    }
                }
            }


        }
    });
}


function getPowerStation() {
    $.ajax({
        type: "post",
        url: basePath + getPowerStationByIdUrl,
        async: true,
        data: {
            powerstationId: powerstationId
        },
        success: function (data) {
            //alert(JSON.stringify(data));
            if (data.success == true) {
                var data = data.dataObject;
                var powerstationName = data.powerstationName;
                $('#powerstationName').html(powerstationName);

                var provinceName = data.provinceName;
                $('#provinceName').html(provinceName);

                var cityName = data.cityName;
                $('#cityName').html(cityName);

                var areaName = data.areaName;
                $('#areaName').html(areaName);
                //
                var latitude = data.latitude;
                $('#latitude').html(latitude);

                var phone = data.phone;
                $('#phone').html(phone);

                var parkingFee = data.parkingFee;
                $('#parkingFee').html(parkingFee);

                var rateInfoId = data.rateInfoId;

                window.localStorage.setItem('rateInformationId',rateInfoId);
                toRateList();

                var address = data.address;
                $('#address').html(address);

                var longitude = data.longitude;
                $('#longitude').html(longitude);

                var onlineTime = data.onlineTime;
                $('#onlineTime').html(onlineTime);

                for (var j=0;j<data.picImgList.length;j++){
                    var src=data.picImgList[j];
                    $('#picDiv').append('<div class="imgContainer"><img src=' + src + '></div>');
                }
            }
        }
    });
}

function toLoadElectricPils() {
    var index = layer.load(1);
    $.ajax({
        type: "post",
        url: basePath + getAllElectricPileListUrl,
        async: true,
        data: {
            powerStationId: powerstationId,
            flag: 1
        },
        success: function (data) {
            //alert(JSON.stringify(data));
            if (data.success == true) {
                layer.close(index);
                var data = data.dataObject;
                var listTr = "";
                for (var i = 0; i < data.length; i++) {
                    listTr += '<tr><td>' + (i + 1) + '</td>'
                    + '<td><span>' + data[i].code
                    + '</span></td><td>' + data[i].num
                    + '</td><td>' + data[i].chStatus
                    + '</td><td>' + data[i].chChargingMethod
                    + '</td><td>' + data[i].chPower
                    + '</td><td>' + data[i].type
                    + '</td><td>' + data[i].rateInformationId
                    + '</td><td>' + data[i].productModel
                    + '</td></tr>';
                }
                $("#electricPile").html(listTr);
            }
        }
    });
}
$('body').off('click', '#toPowerStationList').on('click', '#toPowerStationList', function () {
    window.location.href = 'powerStation_list.html';
})

$('body').off('click', '#editBtn').on('click', '#editBtn', function () {
    window.location.href = 'powerStation_edit.html?powerstationId=' + powerstationId;
})
//点击费率跳转到费率列表
function toRateList(){
    //费率值
    newTab(".toScanFinRelaBtn", "费率列表");
    var rateInformationId=window.localStorage.getItem('rateInformationId');
    var rateInformationIdTag='<a class="toScanFinRelaBtn" onclick="return false;"  href="'+basePath+'/static/html/config/rateInfo.html?rateInformationId='+rateInformationId+'">'+rateInformationId+'</a>';
    $('#rateInfoId').html(rateInformationIdTag);
}
function editUnBind(){}//什么也不做，详情要移除绑定事件
var pkCouponVariety = getUrlParam('pkCouponVariety');
getDetailById(pkCouponVariety);
function getDetailById(pkCouponVariety) {
    $.ajax({
        type: "post",
        url: basePath + getCouponVarietyInfoUrl,
        async: true,
        data: {
            pkCouponVariety: pkCouponVariety
        },
        success: function (data) {
            var data = data.dataObject;
            var covaActivityName = data.covaActivityName;
            $('input[name=covaActivityName]').val(covaActivityName);

            var covaCouponValue = data.covaCouponValue;
            $('input[name=covaCouponValue]').val(covaCouponValue);

            var covaCouponCondition = data.covaCouponCondition;
            $('input[name=covaCouponCondition]').val(covaCouponCondition);

            var covaRemark = data.covaRemark;
            $('input[name=covaRemark]').val(covaRemark);

            var covaLabel = data.covaLabel;
            $('input[name=covaLabel]').val(covaLabel);

            var covaScope = data.covaScope;
            if (covaScope == 0) {
                $('#covaScopeHtml').attr('data-value', 0).html('全国通用');
            }
            if (covaScope == 1) {
                $('#covaScopeHtml').attr('data-value', 1).html('城市通用');
                var provinceCode = data.provinceCode;
                var cityCode = data.cityCode;
                //去加载城市的省
                toLoadProvince(provinceCode, '#provinceCodeHtml', '.cpyProvinceUl', 'editUnBind');
                toLoadCity(provinceCode, cityCode, '#cityCodeHtml', '.cypCityUl', 'editUnBind');
                $('#provinceCodeHtml').attr('data-value',provinceCode);
                $('#cityCodeHtml').attr('data-value',cityCode);
                $('.ctrAllChoice').show();
                $('.cityChoice').show();
                $('.powerStationChoice').hide();
            }
            if (covaScope == 2) {
                $('#covaScopeHtml').attr('data-value', 2).html('站点用');
                var provinceCode = data.provinceCode;
                var cityCode = data.cityCode;
                var areaCode = data.areaCode;
                var pkPowerstation = data.pkPowerstation;
                //去加载城市的省

                toLoadProvince(provinceCode, '#provinceCodeHtml', '.cpyProvinceUl', 'editUnBind');
                toLoadCity(provinceCode, cityCode, '#cityCodeHtml', '.cypCityUl', 'editUnBind');
                toLoadArea(cityCode, areaCode, '#areaCodeHtml', '.areaUl', 'editUnBind');
                toLoadPowerStationList(areaCode, pkPowerstation, '#powerstationHtml', '.powerstationUl', 'editUnBind');
                $('#provinceCodeHtml').attr('data-value',provinceCode);
                $('#cityCodeHtml').attr('data-value',cityCode);
                $('#areaCodeHtml').attr('data-value',areaCode);
                $('#powerstationHtml').attr('data-value',pkPowerstation);
                $('.ctrAllChoice').show();
                $('.cityChoice').show();
                $('.powerStationChoice').show();
            }
            var covaStutas = data.covaStutas;
            if (covaStutas == 0) {
                $('#covaStutasHtml').attr('data-value', 0).html('上架');
            }
            if (covaStutas == 1) {
                $('#covaStutasHtml').attr('data-value', 1).html('下架');
            }
        }
    });
}
$('#goback').off('click').on('click', function () {
    window.location.href = 'couponVariety_list.html';
})
function editCouponVariety() {
    window.location.href = 'couponVariety_edit.html?pkCouponVariety=' + pkCouponVariety;
}
function removeBindEvent() {
    $('.cpyProvinceBlock').unbind();
    $('.cypCityBlock').unbind();
    $('.cpyAreaBlock').unbind();
    $('.powerstationBlock').unbind();
    selectModel();
}
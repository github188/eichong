//获取VIM码列表的menuId
var getMenuMap = window.localStorage.getItem('menuMap');
var getCurruntMap = JSON.parse(getMenuMap);
var menuId = getCurruntMap['VIN码列表'];

$(function(){
    ctrlMenu(menuId);
    //去加载表格的数据
    setTimeout('blackWhiteListSearch()', 150);
})

var cpyId = getUrlParam('cpyId');
var cpyName = getUrlParam('cpyName');
$('#companyCode').html(cpyName);
$('#companyCode').attr('data-value',cpyId);
toGiveHiddenInput();

function blackWhiteListSearch() {
    toGiveHiddenInput();
    initTable("carVinListForm", "carVinListPage", getCarVinRelaUrl, carVinListCallback);
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
                    if (contents.indexOf('导入') > -1) {
                        $('#importCardVin').show();
                    }
                    if (contents.indexOf('新建') > -1) {
                        $('#addCardVin').show();
                    }
                    if (contents.indexOf('删除') > -1) {
                        $('#deleteListBtn').show();
                    }

                }
            }


        }
    });
}
//下拉选项
toUnbindEvent();
function toUnbindEvent() {
    $('.provinceBlock').unbind();
    $('.cityBlock').unbind();
    $('.cpyNameBlock').unbind();
    $('.vinCompanyBlock').unbind();
    selectModel();
}
//表格数据
function carVinListCallback(req) {
    var data = req.dataObject;
    var pageNum = req.pager.pageNo;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        listTr += '<tr><td><input type="checkbox" name="ids" class="selectedBox" value="' + data[i].pkId + '"/></td>'
            + '<td class="vinList_cvVinCode">'+data[i].cvVinCode
            + '</td><td class="vinList_cpyName">' + data[i].cpyName
            + '</td><td class="vinList_cvLicenseNumber">' + data[i].cvLicenseNumber
            + '</td></tr>';
    }
    $("#myCompanyTb").html(listTr);
}

//点击导入------------------------------------------------------------------------------待补充
$("body").off("click", "#importCardVin").on("click", "#importCardVin", function(){
    layer.closeAll();
    toLoadCpyName();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["导入VIN码盗刷校验", "text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '330px'],//宽高
        content: $(".toImportFile"),
        btn: ["确定", "取消"],
        yes: function () {
            importTest();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
})
function importTest(){
    $('.cpyTips').html('');
    $('#fileTip').html('');
    var aaa = $('#vinCompany').attr('data-value');
    var fileName = $('#file').val();

    if(!aaa){
        $('.cpyTips').html('请选择渠道公司！');
        return false;
    }
    if(!fileName){
        $('#fileTip').html('请选择上传的文件！');
        return false;
    }
    setVinForm()
}
//渠道
function toLoadCpyName(){
    $.ajax({
        type: "post",
        url: basePath + getCompanyListUrl,
        async: true,
        data: {
            provinceCode: '',
            cityCode: ''
        },
        success: function (data) {
            if (data.success == true) {
                var dataModule = data.dataObject;
                var cypCompanyLi = '<li data-option="">请选择</li>';
                for (var i = 0; i < dataModule.length; i++) {
                    cypCompanyLi += '<li data-option="' + dataModule[i].cpyId + '" data-cpyNumber="' + dataModule[i].cpyNumber + '">' + dataModule[i].cpyName + '</li>';
                }
                $('.vinCompanyUl').html(cypCompanyLi);
                toUnbindEvent();
            }
        }
    });
}
$('.vinCompanyUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-cpyNumber'));
})
function setVinForm(){
    var formData = new FormData();
    var cpyNumber=$('#vinCompany').attr('data-value');

    formData.append('cpyNumber',cpyNumber);
    formData.append("file", $('#file')[0].files[0]);
    $.ajax({
        url: basePath + importCarVinRelaUrl,
        type: 'POST',
        data: formData,
        async: true,
        cache: false,
        contentType: false,
        processData: false,
        success: function (returndata) {
            if(returndata.success==true){
                layer.closeAll();
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '280px'],//宽高
                    content: returndata.msg,
                    btn: ["确定"],
                    yes:function(index,layero){
                        window.location.reload();
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        window.location.reload();
                    }

                });
            }else{
                layer.closeAll();
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '180px'],//宽高
                    content: returndata.msg,
                    btn: ["确定"],
                    yes:function(index,layero){
                        window.location.reload();
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        window.location.reload();
                    }
                });
            }

        }
    });
}
$('#file').change(function(){
    var fileName = $('#file').val();
    $('#fileText').html(fileName);
})
//下载模版
$('body').off('click','#downloadXlsx').on('click','#downloadXlsx',function(){
    window.location.href = basePath + '/upload/vin_demo.xlsx';
})
//新建VIN列表
$('#cpyNumberInput').on('blur',function(){
    var objValue = $('#cpyNumberInput').val();
    var reg = /^[0-9]{4}$/;
    $('#cpyNumberInputTip').html('');
    if (!objValue) {
        $('#cpyNumberInput').focus();
        $('#cpyNumberInputTip').html('请输入4位数字！');
        return false;
    } else if (!reg.test(objValue)) {
        $('#cpyNumberInput').focus();
        $('#cpyNumberInputTip').html('请输入4位数字！');
        return false;
    }
})
//校验VIN码格式，规则？？？
/*$('#carVinCodeInput').on('blur',function(){
    var objValue = $('#carVinCodeInput').val();
    var reg = /^[0-9][\d]*$/;
    $('#carVinCodeInputTip').html('');
    if (!objValue) {
        $('#carVinCodeInput').focus();
        $('#carVinCodeInputTip').html('请输入数字！');
        return false;
    } else if (!reg.test(objValue)) {
        $('#carVinCodeInput').focus();
        $('#carVinCodeInputTip').html('请输入数字！');
        return false;
    }
})*/
$("body").off("click", "#addCardVin").on("click", "#addCardVin", function () {
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["新建VIN码盗刷校验", "text-align:center"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '240px'],//宽高
        content: $(".toAddCarVinList"),
        btn: ["确定", "取消"],
        yes: function () {
            addTest();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
})
function addTest(){
    if(!$('#cpyNumberInput').val()){
        $('#cpyNumberInput').focus();
        $('#cpyNumberInputTip').html('请输入4位数字！');
        return false;
    }
    if(!$('#carVinCodeInput').val()){
        $('#carVinCodeInput').focus();
        $('#carVinCodeInputTip').html('请输入VIN码！');
        return false;
    }
    if($('#cpyNumberInputTip').html()){
        $('#cpyNumberInput').focus();
        return false;
    }
    toAddCarVinList();
    layer.closeAll();
}
function toAddCarVinList(){
    var cpyNumber = $('#cpyNumberInput').val();
    var cvVinCode = $('#carVinCodeInput').val();
    $.ajax({
        type: "post",
        url: basePath + addCarVinRelaUrl,
        async: true,
        data: {
            cpyNumber: cpyNumber,
            cvVinCode: cvVinCode
        },
        success: function (req) {
            if (req.success == true) {
                layer.closeAll();
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['320px', '180px'],//宽高
                    content: "设置成功",
                    btn: ["确定"],
                    yes:function(index,layero){
                        window.location.reload();
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        window.location.reload();
                    }
                });
                //toGiveHiddenInput();
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
//点击删除
$("body").off("click", "#deleteListBtn").on("click", "#deleteListBtn", function () {
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '180px'],//宽高
        content: "确认删除该记录？",
        btn: ["确定", "取消"],
        yes: function (index, layero) {
            layer.closeAll();
            toSubmitDeleteVinList();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
});
function toSubmitDeleteVinList() {
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
            url: basePath + removeCarVinRelaUrl,
            async: true,
            data: {
                pkId: ids
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
                        area: ['310px', '160px'],//宽高
                        content: '删除成功',
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
}
//查询条件部分=========================
function toGiveHiddenInput() {
    var cvVinCodeValue = $('#cvVinCode').val();
    var cpyIdValue = $('#companyCode').attr('data-value');
    if (cvVinCodeValue == "") {
        $('input[name=cvVinCode]').val('');
    } else {
        $('input[name=cvVinCode]').val(cvVinCodeValue);
    }
    if (cpyIdValue == "") {
        $('input[name=cpyId]').val('');
    } else {
        $('input[name=cpyId]').val(cpyIdValue);
    }
}
//渠道筛选部分=============
setTimeout("toLoadProvince('','#provinceCode','.provinceUl','toUnbindEvent')", 250);
$('.provinceUl').on("click", "li", function () {
    $('#cityCode').attr('data-value', '');
    $('#cityCode').html('请选择市');
    $('input[name=cityCode]').val('');
    $('#companyCode').attr('data-value', '');
    $('#companyCode').html('请选择公司');
    $('input[name=cpyId]').val('');
    $('.cpyIdUl').html('');
    var provinceCodeId = $(this).attr('data-option');
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');
    if (flag == "") {
        $('#provinceCode').html('请选择省');
        $('#provinceCode').attr('data-value', '');
        $('#cityCode').html('请选择市');
        $('#cityCode').attr('data-value', '');
        $('.cityUl').html('');
        $('input[name=cityCode]').val('');
        $('#companyCode').attr('data-value', '');
        $('#companyCode').html('请选择公司');
        $('input[name=cpyId]').val('');
        $('.cpyIdUl').html('');
    } else {
        toLoadCity(provinceCodeId, '', '#cityCode', '.cityUl', 'toUnbindEvent');
    }
})
$('.cityUl').on("click", "li", function () {
    $('#companyCode').attr('data-value', '');
    $('#companyCode').html('请选择公司');
    $('input[name=cpyId]').val('');
    $('.cpyIdUl').html('');
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var flag = $(this).attr('data-option');
    var cypCityCodeValue = $('#cityCode').attr('data-value');
    var cpyProvinceCodeValue = $('#provinceCode').attr('data-value');
    if (flag == "") {
        $('#companyCode').attr('data-value', '');
        $('#companyCode').html('请选择公司');
        $('input[name=cpyId]').val('');
        $('.cpyIdUl').html('');
    } else {
        toLoadComponyName(cypCityCodeValue, cpyProvinceCodeValue);
    }
});
function toLoadComponyName(cypCityCodeValue, cpyProvinceCodeValue) {
    var cypCompanyLi = "";
    var cpyObject = {
        provinceCode: cpyProvinceCodeValue,
        cityCode: cypCityCodeValue
    };
    if (JSON.stringify(cpyObject) == "{}") {
        $('#companyCode').html("请选择公司");
    } else {
        toAjaxCompany(cpyObject);
    }
}
function toAjaxCompany(cpyObject) {
    $.ajax({
        type: "post",
        url: basePath + getCompanyListUrl,
        async: true,
        data: cpyObject,
        success: function (data) {
            if (data.success == true) {
                var dataModule = data.dataObject;
                var cypCompanyLi = '<li data-option="">请选择公司</li>';
                for (var i = 0; i < dataModule.length; i++) {
                    cypCompanyLi += '<li data-option="' + dataModule[i].cpyId + '" data-cpyNumber="' + dataModule[i].cpyNumber + '">' + dataModule[i].cpyName + '</li>';
                }
                $('.cpyIdUl').html(cypCompanyLi);
                toUnbindEvent();
            }
        }
    });
}
$('.cpyIdUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
})
//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        blackWhiteListSearch();
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

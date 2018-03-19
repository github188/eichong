//获取分成列表的menuId
var getMenuMap = window.localStorage.getItem('menuMap');
var getCurruntMap = JSON.parse(getMenuMap);
var menuId = getCurruntMap['分账配置列表'];
//监听回车键
$(document).keyup(function (event) {
    if (event.keyCode == 13) {
        divideListSearch();
    }
});
$(function(){
    ctrlMenu(menuId);
    //去加载表格的数据
    setTimeout('divideListSearch()', 150);
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
            if (data.length == 0) {
                return;
            } else {
                for (var i = 0; i < data.length; i++) {
                    var contents = data[i].contents;
                    if (contents.indexOf('新建') > -1) {
                        $('#addFinManage').show();
                    }
                    if (contents.indexOf('编辑') > -1) {
                        $('#editFinManageBtn').show();
                    }

                }
            }


        }
    });
}

function divideListSearch() {
    initTable("divideListForm", "divideListPage", getFinAccountSplitConfigListUrl, divideListCallback);
}
//表格数据
function divideListCallback(req) {
    var data = req.dataObject;
    var pageNum = req.pager.pageNo;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        listTr += '<tr><td><input type="checkbox" name="ids" class="selectedBox" value="' + data[i].cpyId + '" data-option="'+data[i].pkId+'" />'
            + '</td><td >' + (i + 1 + (pageNum - 1) * 20) + '</a>'
            + '</td><td >' + data[i].splitModeName
            + '</td><td >' + data[i].cpyName
            + '</td><td >' + data[i].strCpyName
            + '</td><td >' + data[i].strServicesRatio
            + '</td><td >' + data[i].strElectricityRatio
            + '</td><td >' + data[i].strPrice
            + '</td><td><a class="finManageEdit" href="' + basePath + '/static/html/finManage/finManage_configuration.html?status=2&pkId='+data[i].pkId+'&cpyId=' + data[i].cpyId + '">详细</a>'
            + '</td></tr>';
    }
    $("#myCompanyTb").html(listTr);

}

//模糊查询相关

//处理根据城市模糊查询
var last;
$('#cpyName').keyup(function(event){
    last = event.timeStamp;
    setTimeout(function(){
        if(last-event.timeStamp==0)
        {
            getCityListByName();
            $('.searchUl').css('display','block');
            $('input[name="cpyId"]').val('');
        }
    },500)
});
//$('body').off('keyup','#cpyName').on('keyup', '#cpyName', function () {
//    setTimeout('getCityListByName()',500);
//    $('.searchUl').css('display','block');
//    $('input[name="cpyId"]').val('');
//});

$('#cpyName').on('onpropertychange input',function(){
    var length=$(this).val().length;
    if(length==0){
        $('input[name="cpyId"]').val('');
    }
});

function getCityListByName(){
    var companyName = $('#cpyName').val();
    var reg=/^[\u4E00-\u9FA5]+$/;
    if(companyName==''){
        $('.searchUl').html('');
        $('.searchUl').css('display','none');
        $('input[name="cpyId"]').val('');
    }else{
            $.ajax({
                type: "post",
                url: basePath + getCompanyListByCpyNameUrl,
                async: true,
                data: {
                    cpyName:companyName
                },
                success: function (data) {
                    if (data.success == true) {
                        var data = data.dataObject;
                        var cityNameLi = '';
                        if(data.length>0){
                            $('.searchUl').css('display', 'block');
                        }else{
                            $('.searchUl').css('display', 'none');
                        }
                        for (var i = 0; i < data.length; i++) {
                            cityNameLi += '<li data-option="' + data[i].cpyId + '">' + data[i].cpyName + '</li>';
                        }
                        $('.searchUl').html(cityNameLi);
                    }else if (data.status == 9001) {
                        layer.closeAll();
                        layer.open({
                            type: 1,
                            offset: '100px',
                            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                            shadeClose: false,
                            closeBtn: 1,
                            area: ['310px', '160px'],//宽高
                            content: '会话超时，请重新登陆！',
                            btn: ["确定"],
                            yes: function (index, layero) {
                                layer.closeAll();
                                window.top.location.href = basePath + toLoginUrl;
                            },
                            cancel: function (index, layero) {
                                layer.closeAll();
                                window.top.location.href = basePath + toLoginUrl;
                            }
                        });
                    }
                }
            });
    }
}

//点击模糊查询的城市，对input赋值
$('body').off('click','.searchUl li').on('click','.searchUl li',function(){
    $('.searchUl').css('display','none');
    var cpyId=$(this).attr('data-option');
    var cpyName=$(this).text();
    $('.cpyId').val(cpyId);
    $('#cpyName').val(cpyName);
});

//------------------------------------------------------------

//新增公司
$('#addFinManage').on('click', function () {
    window.location.href = "finManage_configuration.html?status=0&pkId=";
});

//点击编辑
$("body").off("click", "#editFinManageBtn").on("click", "#editFinManageBtn", function () {
    layer.closeAll();
    toEditFinManage();
});
function toEditFinManage(){
    var ids = '',cds = '';
    $('input[name=ids]').each(function () {
        var flag = $(this).prop('checked');
        if (flag == true) {
            ids = $(this).attr('value');
            cds = $(this).attr('data-option');
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
        window.location.href = "finManage_configuration.html?status=1&pkId="+cds+"&cpyId="+ids;
    }
}

//单个编辑
$('body').on('click', 'input[name=ids]', function () {
    var flag = $(this).prop('checked');
    if (flag == true) {
        $(this).parents('td').parents('tr').siblings().find('input[name=ids]').prop('checked', false).attr('disabled', true);
    } else {
        $(this).parents('td').parents('tr').siblings().find('input[name=ids]').attr('disabled', false);
    }

})
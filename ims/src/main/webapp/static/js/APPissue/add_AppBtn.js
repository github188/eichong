toUnbindEvent();
function toUnbindEvent() {
    $('.buttonActionBlock').unbind();
    selectModel();
}
//内部动作选择
$('.buttonActionUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
})
//名称
$('#buttonName').on('blur',function(){
    $('#buttonNameTip').html('');
    var buttonName = $(this).val();
    if(!buttonName){
        $('#buttonNameTip').html('请输入名称，1-10个字');
        $('#buttonName').val('');
        $('#buttonName').focus();
    }else if(buttonName.length>10){
        $('#buttonNameTip').html('请重新输入名称，1-10个字');
        $('#buttonName').val('');
        $('#buttonName').focus();
    }
});

//操作  内部操作时 ============加载内部操作列表(请求接口？？？？？？)，外部操作时清除列表
var buttonTypeValue=$('input:radio[name="buttonType"]:checked').val();
if(!buttonTypeValue){
    $('#buttonUrl').attr('readonly','readonly');
}
$('input:radio[name="buttonType"]').on('click', function () {
    var buttonTypeValue=$('input:radio[name="buttonType"]:checked').val();
    var buttonActionLi = '<li data-option="0" class="seleced">请选择</li>'
        + '<li data-option="1">扫码充电</li>'
        + '<li data-option="2">地图找桩</li>'
        + '<li data-option="3">我的充电</li>'
        + '<li data-option="4">余额充值</li>';
    if(buttonTypeValue==1){
        $('#buttonUrl').attr('readonly','readonly');
        $('#buttonUrl').val('');
        $('.buttonActionUl').html(buttonActionLi);
        $('.buttonActionUl').on('mouseover','li',function(){
            $(this).addClass('seleced');
            $(this).siblings('li').removeClass('seleced');
        })
    }else if(buttonTypeValue==2){
        $('#buttonUrl').removeAttr('readonly');
        $('.buttonActionUl').html('');
        $('#buttonAction').attr('data-value','0');
        $('#buttonAction').html('请选择');
    }
});
//链接
$('#buttonUrl').on('blur',function(){
    $('#buttonUrlTip').html('');
    var buttonUrl = $(this).val();
    var reg=/^(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&:/~\+#]*[\w\-\@?^=%&/~\+#])?$/;

    if(!buttonUrl){
        $('#buttonUrlTip').html('请输入链接');
        $('#buttonUrl').val('');
    }else if(!reg.test(buttonUrl)){
        $('#buttonUrlTip').html('链接有误，请重新输入正确的链接');
        $('#buttonUrl').val('');
    }
})
$('#buttonUrl').on('focus',function(){
    layer.tips('能识别带有http、https的网址', '#buttonUrl', {
        tips: 4
    });
})
//返回
$('#goCancel').on('click', function (){
    window.location.href = "AppBtnList.html";
});
//保存
function saveInfo(){
    $('#fileNameTip').html('');
    $('#buttonNameTip').html('');
    $('#buttonTypeTip').html('');
    $('#buttonStatusTip').html('');
    $('#buttonDescTip').html('');
    var fileName = $('#file').val();
    var buttonNameValue = $('#buttonName').val();
    var buttonTypeValue=$('input:radio[name="buttonType"]:checked').val();
    var buttonStatusValue=$('input:radio[name="buttonStatus"]:checked').val();
    var buttonDescValue = $('#buttonDesc').val();
    if(!fileName){
        $('#fileNameTip').html('请选择需要上传的文件！');
        return false;
    }
    if(!buttonNameValue){
        $('#buttonNameTip').html('请重新输入名称，1-10个字');
        return false;
    }else if(buttonNameValue.length>10){
        $('#buttonNameTip').html('名称过长，最多10个字');
        return false;
    }
    if(!buttonTypeValue){
        $('#buttonTypeTip').html('请选择操作类型');
        return false;
    }else if(buttonTypeValue==1){
        var buttonActionValue = $('#buttonAction').attr('data-value');
        if(!buttonActionValue){
            $('#buttonTypeTip').html('请选择内部操作类型');
            return false;
        }
    }else if(buttonTypeValue==2){
        var outOperationInputValue = $('#buttonUrl').val();
        if(!outOperationInputValue){
            $('#buttonTypeTip').html('请填写外部操作类型');
            return false;
        }
    }
    if(!buttonDescValue){
        $('#buttonDescTip').html('请填写备注内容');
        return false;
    }
    toSetForm()
}
//需要上传参数的名字？？
function toSetForm(){
    var formData = new FormData();
    var buttonNameValue = $('#buttonName').val();
    var buttonTypeValue=$('input:radio[name="buttonType"]:checked').val();
    var buttonStatusValue=$('input:radio[name="buttonStatus"]:checked').val();
    var type;
    if(buttonTypeValue==1){
        $('#buttonUrl').val('');
        type = $('#buttonAction').attr('data-value');
    }else if(buttonTypeValue==2){
        $('#buttonAction').attr('data-value','');
        $('#buttonAction').html('');
        type = 0;
    }


    var buttonUrlValue = $('#buttonUrl').val();
    var buttonDescValue = $('#buttonDesc').val();

    formData.append('buttonName',buttonNameValue);
    formData.append('buttonType',buttonTypeValue);
    formData.append('buttonAction',type);
    formData.append('buttonUrl',buttonUrlValue);
    formData.append('buttonStatus',buttonStatusValue);
    formData.append('buttonDesc',buttonDescValue);
    formData.append("file", $('#file')[0].files[0]);
    commonLoad(formData);
}
function commonLoad(formData){
    var index =layer.load(1);
    $.ajax({
        url: basePath + addAppButtonUrl,
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
                    area: ['310px', '180px'],//宽高
                    content: "保存成功！",
                    btn: ["确定"],
                    yes:function(index,layero){
                        layer.closeAll();
                        window.location.href='AppBtnList.html';
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        window.location.href='AppBtnList.html';
                    }
                });
            }else if(data.status == 9001) {
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
                    yes:function(index,layero){
                        layer.closeAll();
                        window.top.location.href = basePath + toLoginUrl;
                    },
                    cancel:function(index,layero){
                        layer.closeAll();
                        window.top.location.href = basePath + toLoginUrl;
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
                    time:2000,
                    btn: ["确定"]
                });
            }

        }
    });
}
$('#file').change(function(){
    $('#fileListUl').html($(this).val());
})
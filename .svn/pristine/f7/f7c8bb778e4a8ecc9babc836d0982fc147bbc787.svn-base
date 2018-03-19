toUnbindEvent();
function toUnbindEvent() {
    $('.adLocationBlock').unbind();
    $('.adIsGotoBlock').unbind();
    selectModel();
}
//插屏位置
$('.adLocationUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
});
//是否点击跳转
$('.adIsGotoUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var adIsGotoValue = $('#adIsGoto').attr('data-value');
    if(adIsGotoValue==1){
        $('#kedianji').css('display','block');
    }else if(adIsGotoValue==0){
        $('#kedianji').css('display','none');
    }
});

//链接
$('#adURL').on('blur',function(){
    $('#adURLTip').html('');
    var adURL = $(this).val();
    var reg=/^(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&:/~\+#]*[\w\-\@?^=%&/~\+#])?$/;

    if(!adURL){
        $('#adURLTip').html('请输入链接');
        $('#adURL').val('');
    }else if(!reg.test(adURL)){
        $('#adURLTip').html('链接有误，请重新输入正确的链接');
        $('#adURL').val('');
    }
})
$('#adURL').on('focus',function(){
    layer.tips('能识别带有http、https的网址', '#adURL', {
        tips: 4
    });
});

//返回
$('#goCancel').on('click', function () {
    window.location.href = "AppInterpolationList.html";
});

//保存
function saveInfo(){
    $('#adNameTip').html('');
    $('#fileNameTip').html('');
    $('#adIsGotoTip').html('');
    $('#beginAdTimeTip').html('');
    $('#endAdTimeTip').html('');
    var adNameValue = $('#adName').val();
    var fileName = $('#file').val();
    var adIsGotoValue = $('#adIsGoto').attr('data-value');
    var beginAdTimeValue = $('input[name=beginAdTime]').val();
    var endAdTimeValue = $('input[name=endAdTime]').val();
    if(!adNameValue){
        $('#adNameTip').html('请输入名称');
        return false;
    }
    if(!fileName){
        $('#fileNameTip').html('请选择需要上传的文件');
        return false;
    }
    if(adIsGotoValue==1){
        $('#adDescTip').html('');
        $('#adURLTip').html('');
        $('#picListFileNameTip').html('');
        var adDescValue = $('#adDesc').val();
        var adURLValue = $('#adURL').val();
        var picListFileValue = $('#picListFile').val();
        if(!adDescValue){
            $('#adDescTip').html('请填写说明');
            return false;
        }
        if(!adURLValue){
            $('#adURLTip').html('请填写Url');
            return false;
        }
        if(!picListFileValue){
            $('#picListFileNameTip').html('请选择要上传的动态列表图片');
            return false;
        }
    }
    if(!beginAdTimeValue){
        $('#beginAdTimeTip').html('请选择开始时间');
        return false;
    }
    if(!endAdTimeValue){
        $('#endAdTimeTip').html('请选择结束时间');
        return false;
    }
    toSetForm()
}
//需要上传参数的名字？？
function toSetForm(){
    var formData = new FormData();
    var fileName = $('#file').val();
    var adNameValue = $('#adName').val();
    var adLocationValue = $('#adLocation').attr('data-value');
    var adIsGotoNum = $('#adIsGoto').attr('data-value');
    var adDescValue = $('#adDesc').val();
    var adURLValue = $('#adURL').val();
    var beginAdTimeValue = $('input[name=beginAdTime]').val();
    var endAdTimeValue = $('input[name=endAdTime]').val();
    if(adIsGotoNum==0){
        formData.append('adName',adNameValue);
        formData.append('adLocation',adLocationValue);
        formData.append('adIsGoto',adIsGotoNum);
        formData.append('beginAdTime',beginAdTimeValue);
        formData.append('endAdTime',endAdTimeValue);
        formData.append("file", $('#file')[0].files[0]);
        formData.append("adType", '2');
        commonLoad(formData);
    }else if(adIsGotoNum==1){
        formData.append('adName',adNameValue);
        formData.append('adLocation',adLocationValue);
        formData.append('adIsGoto',adIsGotoNum);
        formData.append('adDesc',adDescValue);
        formData.append('adURL',adURLValue);
        formData.append('beginAdTime',beginAdTimeValue);
        formData.append('endAdTime',endAdTimeValue);
        formData.append("file", $('#file')[0].files[0]);
        formData.append("picListFile", $('#picListFile')[0].files[0]);
        formData.append("adType", '2');
        commonLoad(formData);
    }

}
function commonLoad(formData){
    var index =layer.load(1);
    $.ajax({
        url: basePath + addAdvertisementUrl,
        type: 'POST',
        data: formData,
        async: false,
        cache: false,
        contentType: false,
        processData: false,
        beforeSend:function(){
            index =layer.load(1);
        },
        success: function (returndata) {
            layer.close(index);
            if(returndata.success==true){
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
                        window.location.href='AppInterpolationList.html';
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        window.location.href='AppInterpolationList.html';
                    }
                });
            } else if (returndata.status == 9001) {
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
});
$('#picListFile').change(function(){
    $('#picfileListUl').html($(this).val());
});
$(document).ready(function(){
    $('#file').change(function(){
        var fileName = $('#file').val();
        $('input[name=fileName]').val(fileName);
    })
//点击确定添加
    $('#sureAdd').on('click',function(){
        var checkFlag=$('#readFileName').val();//若前面的文件没有选择，则什么也不做。
        if(!checkFlag){
            return;
        }
        companyChargeRelaImport();
    });
    function companyChargeRelaImport(){
        var formData = new FormData();
        formData.append("file", $('#file')[0].files[0]);
        $.ajax({
            url: basePath + companyChargeRelaImportUrl,
            type: 'POST',
            data: formData,
            async: true,
            cache: false,
            contentType: false,
            processData: false,
            success: function (returndata) {
                layer.closeAll(); //关闭loading
                if(returndata.success==true){
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '160px'],//宽高
                        content: returndata.msg,
                        btn: ["确定"],
                        yes:function(index,layero){
                            layer.closeAll();
                            window.location.reload();
                        },
                        cancel:function(index,layero){
                            layer.closeAll();
                            window.location.reload();
                        }
                    });
                }else{
                    if(returndata.status == 9001){
                        layer.open({
                            type: 1,
                            offset: '100px',
                            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                            shadeClose: false,
                            closeBtn: 1,
                            btn: ["确定"],
                            area: ['310px', '160px'],//宽高
                            content: returndata.msg,
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

            }
        });
    }
//下载模版	=====================
    $('#downElectricBtn').on("click", function () {
        window.location.href = basePath + '/upload/powerstationCompany_demo.xlsx';
    });
})
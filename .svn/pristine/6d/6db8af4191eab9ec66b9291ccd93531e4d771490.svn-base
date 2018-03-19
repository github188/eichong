//导入电桩importElectricBtn importElectricUrl
layui.use('upload', function () {
    var upload = layui.upload;
    //执行实例
    var uploadInst = upload.render({
        elem: '#importElectricBtn'
        , url: basePath + companyChargeRelaImportUrl //上传接口
        , accept: 'file'
        , exts: 'xlsx|xls'
        , auto: false
        , bindAction: '#sureAdd'
        , before: function (obj) {
            layer.load(1); //上传loading
        }
        ,choose: function(obj){
            //将每次选择的文件追加到文件队列
            var files = obj.pushFile();

            //预读本地文件，如果是多文件，则会遍历。(不支持ie8/9)
            obj.preview(function(index, file, result){
                //console.log(index); //得到文件索引
                $('input[name=fileName]').val(file.name);
               // console.log(result); //得到文件base64编码，比如图片
            });
        }
        , done: function (res, index, upload) {
            if (res.success == true) {
                layer.closeAll(); //关闭loading
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    area: ['310px', '160px'],//宽高
                    content: '导入成功',
                    time: 2000,
                    btn: ["确定"]
                });
            } else if (res.status == 9001) {
                layer.closeAll();
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    btn: ["确定"],
                    area: ['310px', '160px'],//宽高
                    content: res.msg,
                    yes: function (index, layero) {
                        layer.closeAll();
                        window.top.location.href = basePath + toLoginUrl;
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        window.top.location.href = basePath + toLoginUrl;
                    }
                });
            } else {
                layer.closeAll();
                layer.open({
                    type: 1,
                    offset: '100px',
                    title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                    shadeClose: false,
                    closeBtn: 1,
                    btn: ["确定"],
                    area: ['310px', '160px'],//宽高
                    content: res.msg,
                    yes: function (index, layero) {
                        layer.closeAll();
                    }
                });
            }
        }
    });
});

//下载模版	=====================
$('#downElectricBtn').on("click", function () {
    window.location.href = basePath + '/upload/充电点_公司对应表.xlsx';
});
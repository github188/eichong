var pkTypeSpanId = getUrlParam('pkTypeSpanId');
//下拉列表
toUnbindEvent();
function toUnbindEvent() {
    $('.blTypeBlock').unbind();
    selectModel();
}
$('.typeUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
})
toLoadpkTypeSpanInfo();
function toLoadpkTypeSpanInfo (){
    $.ajax({
        type: "post",
        url: basePath + getTypeSpanByIdUrl,
        async: true,
        data: {
            pkTypeSpanId: pkTypeSpanId
        },
        success: function (req) {
            var data = req.dataObject;

            var tsTypeSpan = data.tsTypeSpan;//内部
            var tsModelName = data.tsModelName;//名称
            var tsProductNumber = data.tsProductNumber;//外部
            var tsRemarks = data.tsRemarks;//备注
            var elPiMaker = data.elPiMakerName;//制造商
            var elPiPowerSize = data.elPiPowerSizeName ;//功率
            var elPiType = data.elPiTypeName ;//电桩类型
            var elPiPowerNumber = data.elPiPowerNumber;//枪头数量
            //充电方式
            var elPiChargingMode='';
            if(data.elPiChargingMode==0){
                elPiChargingMode='';
            }else if(data.elPiChargingMode==5){
                elPiChargingMode='直流充电桩'
            }else if(data.elPiChargingMode==14){
                elPiChargingMode='交流充电桩'
            }

            $('#tsTypeSpan').html(tsTypeSpan);
            $('#tsModelName').html(tsModelName);
            $('#tsProductNumber').html(tsProductNumber);
            $('#tsRemarks').html(tsRemarks);
            $('#elPiMaker').html(elPiMaker);
            $('#elPiPowerSize').html(elPiPowerSize);
            $('#elPiChargingMode').html(elPiChargingMode);
            $('#elPiType').html(elPiType);
            $('#elPiPowerNumber').html(elPiPowerNumber);

            $('#tsTypeSpanEdit').html(tsTypeSpan);
            $('#tsModelNameEdit').val(tsModelName);
            $('#tsProductNumberEdit').val(tsProductNumber);
            $('#tsRemarksEdit').val(tsRemarks);
            $('#elPiMakerEdit').html(elPiMaker);
            $('#elPiMakerEditInput').val(data.elPiMaker);
            $('#elPiPowerSizeEdit').html(elPiPowerSize);
            $('#elPiPowerSizeEditInput').val(data.elPiPowerSize);
            $('#elPiChargingModeEdit').html(elPiChargingMode);
            $('#elPiChargingModeEditImput').val(data.elPiChargingMode);
            $('#elPiTypeEdit').html(elPiType);
            $('#elPiTypeEditInput').val(data.elPiType);
            $('#elPiPowerNumberEdit').html(elPiPowerNumber);

            var tabLi = '';
            for(var i=0;i<data.bomList.length;i++){
                var blHardwareTypeName='';
                if(data.bomList[i].blHardwareType==1){
                    blHardwareTypeName='计费单元'
                }else if(data.bomList[i].blHardwareType==2){
                    blHardwareTypeName='主控单元'
                }else if(data.bomList[i].blHardwareType==3){
                    blHardwareTypeName='显示屏'
                }else if(data.bomList[i].blHardwareType==4){
                    blHardwareTypeName='通讯模块'
                }
                var blForceUpdateName ='';
                if(data.bomList[i].blForceUpdate==0){
                    blForceUpdateName='不强制'
                }else if(data.bomList[i].blForceUpdate==1){
                    blForceUpdateName='强制更新'
                }
                tabLi += '<tr><td class="tab-num">'+ (i+1)
                    +'</td><td class="tab-type">'+blHardwareTypeName
                    +'</td><td class="tab-blHardwareNumber">'+data.bomList[i].blHardwareNumber
                    +'</td><td class="tab-blHardwareVersion">'+data.bomList[i].blHardwareVersion
                    +'</td><td class="tab-blFirmwareNumber">'+data.bomList[i].blFirmwareNumber
                    +'</td><td class="tab-blFirmwareVersion">'+data.bomList[i].blFirmwareVersion
                    +'</td><td class="tab-blFileMd5">'+data.bomList[i].blFileMd5
                    +'</td><td class="tab-blForceUpdate">'+ blForceUpdateName
                    +'</td></tr>'
            }
            $('#addList').html(tabLi)

        }
    });
}
$('body').off('click','#addBtn').on('click','#addBtn',function(){
    layer.closeAll();
    layer.open({
        type: 1,
        title: ["新增软硬件", "font-size:12px;text-align:center"],
        btn: ["确定", "取消"],
        shadeClose: false,
        closeBtn: 1,//点击遮罩关闭层
        area: ['320px', '380px'],
        content: $(".addBomList"),
        yes: function () {
            addBomList();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
})
//硬件型号
$('body').off('blur','#blHardwareNumberInput').on('blur','#blHardwareNumberInput',function(){
    var blHardwareNumber = $('#blHardwareNumberInput').val();
    var reg=/^([a-z]|[A-Z]|[0-9]|(\-)|(\+))*$/;
    if(!blHardwareNumber){
        layer.tips('请输入硬件型号！', '#blHardwareNumberInput', {
            tips: 4
        });
    }else if(!reg.test(blHardwareNumber)){
        layer.tips('输入有误,请重新输入硬件型号,数字、-、英文区分大小写,长度最多20位！', '#blHardwareNumberInput', {
            tips: 4
        });
        $('#blHardwareNumberInput').val('');
    }else if(blHardwareNumber.length>20){
        layer.tips('输入长度过长,请重新输入硬件型号,数字、-、英文区分大小写,长度最多20位！', '#blHardwareNumberInput', {
            tips: 4
        });
        $('#blHardwareNumberInput').val('');
    }
})
//硬件版本号
$('body').off('blur','#blHardwareVersionInput').on('blur','#blHardwareVersionInput',function(){
    var blHardwareVersion = $('#blHardwareVersionInput').val();
    var reg = /^[0-9a-zA-Z\.\u0000-\u00FF]+$/;
    if(!blHardwareVersion){
        layer.tips('请输入硬件版本号！', '#blHardwareVersionInput', {
            tips: 4
        });
    }else if(!reg.test(blHardwareVersion)){
        layer.tips('输入有误,请重新输入硬件版本号,数字、英文、小数点、半角,长度最多20位！', '#blHardwareVersionInput', {
            tips: 4
        });
        $('#blHardwareVersionInput').val('');
    }else if(blHardwareVersion.length>20){
        layer.tips('输入长度过长,请重新输入硬件版本号,数字、英文、小数点、半角,长度最多20位！', '#blHardwareVersionInput', {
            tips: 4
        });
        $('#blHardwareVersionInput').val('');
    }
})
//固件编号
$('body').off('blur','#blFirmwareNumberInput').on('blur','#blFirmwareNumberInput',function(){
    var blFirmwareNumber = $('#blFirmwareNumberInput').val();
    var reg=/^([a-z]|[A-Z]|[0-9]|(\-)|(\+))*$/;
    if(!blFirmwareNumber){
        layer.tips('请输入固件编号！', '#blFirmwareNumberInput', {
            tips: 4
        });
    }else if(!reg.test(blFirmwareNumber)){
        layer.tips('输入有误,请重新输入固件编号,数字、英文区分大小写,长度最多20位！', '#blFirmwareNumberInput', {
            tips: 4
        });
        $('#blFirmwareNumberInput').val('');
    }else if(blFirmwareNumber.length>20){
        layer.tips('输入长度过长,请重新输入固件编号,数字、英文区分大小写,长度最多20位！', '#blFirmwareNumberInput', {
            tips: 4
        });
        $('#blFirmwareNumberInput').val('');
    }
})
//固件版本号
$('body').off('blur','#blFirmwareVersionInput').on('blur','#blFirmwareVersionInput',function(){
    var blFirmwareVersion = $('#blFirmwareVersionInput').val();
    var reg = /^[0-9a-zA-Z\.\u0000-\u00FF]+$/;
    if(!blFirmwareVersion){
        layer.tips('请输入硬件版本号！', '#blFirmwareVersionInput', {
            tips: 4
        });
    }else if(!reg.test(blFirmwareVersion)){
        layer.tips('输入有误,请重新输入硬件版本号,数字、英文、小数点、半角,长度最多20位！', '#blFirmwareVersionInput', {
            tips: 4
        });
        $('#blFirmwareVersionInput').val('');
    }else if(blFirmwareVersion.length>20){
        layer.tips('输入长度过长,请重新输入硬件版本号,数字、英文、小数点、半角,长度最多20位！', '#blFirmwareVersionInput', {
            tips: 4
        });
        $('#blFirmwareVersionInput').val('');
    }
})
function addBomList(){
    var blHardwareNumber = $('#blHardwareNumberInput').val();
    var blHardwareVersion = $('#blHardwareVersionInput').val();
    var blFirmwareNumber = $('#blFirmwareNumberInput').val();
    var blFirmwareVersion = $('#blFirmwareVersionInput').val();
    var blFileMd5 = $('#blFileMd5Input').val();
    var blForceUpdate = $("input[name='blForceUpdateSpan']:checked").val();
    var blHardwareType = $('#blType').attr('data-value');
    if(!blHardwareType){
        layer.tips('请选择硬件类型！', '#blType', {
            tips: 4
        });
        return false;
    }
    if(!blHardwareNumber){
        layer.tips('请输入硬件型号！', '#blHardwareNumberInput', {
            tips: 4
        });
        $('#blHardwareNumberInput').focus();
        return false;
    }
    if(!blHardwareVersion){
        layer.tips('请输入硬件版本号！', '#blHardwareVersionInput', {
            tips: 4
        });
        $('#blHardwareVersionInput').focus();
        return false;
    }
    if(!blFirmwareNumber){
        layer.tips('请输入固件编号！', '#blFirmwareNumberInput', {
            tips: 4
        });
        $('#blFirmwareNumberInput').focus();
        return false;
    }
    if(!blFirmwareVersion){
        layer.tips('请输入固件版本号！', '#blFirmwareVersionInput', {
            tips: 4
        });
        $('#blFirmwareVersionInput').focus();
        return false;
    }
    if(!blFileMd5){
        layer.tips('请输入MD5值！', '#blFileMd5Input', {
            tips: 4
        });
        $('#blFileMd5Input').focus();
        return false;
    }
    $.ajax({
        type: "post",
        url: basePath + insertBomListUrl,
        async: true,
        data: {
            blHardwareType:blHardwareType,
            blHardwareNumber:blHardwareNumber,
            blHardwareVersion:blHardwareVersion,
            blFirmwareNumber:blFirmwareNumber,
            blFirmwareVersion:blFirmwareVersion,
            blFileMd5:blFileMd5,
            blForceUpdate:blForceUpdate,
            pkTypeSpanId:pkTypeSpanId
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
                    content: "保存成功",
                    btn: ["确定"],
                    yes:function(){
                        layer.closeAll();
                        toLoadpkTypeSpanInfo();
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        toLoadpkTypeSpanInfo();
                    }
                });
            } else if(data.status == 9001) {
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

            }else {
                layer.closeAll();
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
//编辑
$('body').off('click','#editBtn').on('click','#editBtn',function(){
    $('#editBtn').css('display','none');
    $('#detailBlock').css('display','none');
    $('#addBtn').css('display','none');
    $('#toProductModelList').css('display','none');
    $('#saveBtn').css('display','inline-block');
    $('#editBlock').css('display','block');
    $('#backBtn').css('display','inline-block');
})
//编辑保存
$('body').off('click','#saveBtn').on('click','#saveBtn',function(){
    var tsTypeSpan =$('#tsTypeSpanEdit').html();
    var tsModelName =$('#tsModelNameEdit').val();
    var tsProductNumber =$('#tsProductNumberEdit').val();
    var tsRemarks =$('#tsRemarksEdit').val();
    var elPiPowerNumber =$('#elPiPowerNumberEdit').html();
    var elPiChargingMode =$('#elPiChargingModeEditInput').val();
    var elPiPowerSize =$('#elPiPowerSizeEditInput').val();
    var elPiMaker =$('#elPiMakerEditInput').val();
    var elPiType =$('#elPiTypeEditInput').val();
    $.ajax({
        type: "post",
        url: basePath + updateTypeSpanUrl,
        async: true,
        data: {
            tsTypeSpan:tsTypeSpan,
            tsModelName:tsModelName,
            tsProductNumber:tsProductNumber,
            tsRemarks:tsRemarks,
            elPiPowerNumber:elPiPowerNumber,
            elPiChargingMode:elPiChargingMode,
            elPiPowerSize:elPiPowerSize,
            elPiMaker:elPiMaker,
            elPiType:elPiType,
            pkTypeSpanId:pkTypeSpanId
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
                    content: "保存成功",
                    btn: ["确定"],
                    yes:function(){
                        layer.closeAll();
                        window.location.href = "productModel_list.html";
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        window.location.href = "productModel_list.html";
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
                    area: ['310px', '160px'],//宽高
                    content: data.msg,
                    time: 3000,
                    btn: ["确定"]
                });
            }
        }
    });
})
//返回
$('body').off('click','#toProductModelList').on('click','#toProductModelList',function(){
    window.location.href = "productModel_list.html";
})
//编辑页返回
$('body').off('click','#backBtn').on('click','#backBtn',function(){
    $('#saveBtn').css('display','none');
    $('#editBlock').css('display','none');
    $('#backBtn').css('display','none');
    $('#editBtn').css('display','inline-block');
    $('#detailBlock').css('display','block');
    $('#addBtn').css('display','inline-block');
    $('#toProductModelList').css('display','inline-block');
})
var pkInvoice = getUrlParam('pkInvoice');
getInvoiceBasicInfo();
function getInvoiceBasicInfo() {
    $.ajax({
        type: "post",
        url: basePath + getFinInvoiceByIdUrl,
        async: true,
        data: {
            pkInvoice: pkInvoice
        },
        success: function (req) {
            var data = req.dataObject;
            var userAccount = data.userAccount;//申请人
            var ivCreatedate = data.ivCreatedate;//申请时间
            var ivCreatedateName = ivCreatedate.substring(0,19);
            var ivInvoiceStatus = data.ivInvoiceStatus;//发票申请状态 0-待开票，1-已开票，2-已申请，未支付邮费，3.拒绝开票
            var ivInvoiceType = data.ivInvoiceType;//开票类型：0-个人开票 ，1-公司开票
            var ivCompanyName = data.ivCompanyName;//公司名称
            var ivTaxpayerNumber = data.ivTaxpayerNumber;//纳税识别号
            var ivCompanyAddress = data.ivCompanyAddress;//营业地址
            var ivPhoneNumber = data.ivPhoneNumber;//电话
            var ivBankName = data.ivBankName;//公司开户行
            var ivBankAccount = data.ivBankAccount;//银行账户
            var ivPayFreight = data.ivPayFreight;//收件方式  支付类型：0-账户余额支付， 1-支付宝支付，2-微信支付，3-银联支付 ,4-货到付款
            var ivRecipients = data.ivRecipients;//收件人
            var ivRecipientsNumber = data.ivRecipientsNumber;//联系电话
            var ivFreightAmount = data.ivFreightAmount;//邮费
            var ivConsigneeAddress = data.ivConsigneeAddress;//收件地址
            var discountChangMoney = data.discountChangMoney;//优惠后电费
            var discountServiceMoney = data.discountServiceMoney;//优惠后服务费
            var ivReceipType = data.ivReceipType;//发票类型 0-普通发票，1-增值税专用发票
            var ivUpdatedate = data.ivUpdatedate;//开票时间
            var ivInvoiceNumber = data.ivInvoiceNumber;//发票号码
            var ivInvoiceStatusName = '';
            if(ivInvoiceStatus==0){
                ivInvoiceStatusName='待开票';
                $('#waitInvoice').show();
                $('#invoiceSuccess').hide();
                $('#refuseInvoice').hide();
                $('#refuseCancelBtn').hide();
                $('#confirmCancelBtn').hide();
                $('#refuseBtn').show();
                $('#confirmBtn').show();
            }else if(ivInvoiceStatus==1){
                ivInvoiceStatusName='已开票';
                $('#waitInvoice').hide();
                $('#invoiceSuccess').show();
                $('#refuseInvoice').hide();
            }else if(ivInvoiceStatus==2){
                ivInvoiceStatusName='已申请，未支付邮费';
                $('#waitInvoice').show();
                $('#invoiceSuccess').hide();
                $('#refuseInvoice').hide();
                $('#refuseCancelBtn').hide();
                $('#confirmCancelBtn').hide();
                $('#refuseBtn').show();
                $('#confirmBtn').show();
            }else if(ivInvoiceStatus==3){
                ivInvoiceStatusName='拒绝开票';
                $('#waitInvoice').hide();
                $('#invoiceSuccess').hide();
                $('#refuseInvoice').show();
                //$('#ivRefuseRejectionHtml').html(data[i].ivRefuseRejection)
            }
            var ivInvoiceTypeName='';
            if(ivInvoiceType==0){
                ivInvoiceTypeName='个人开票';
                $('.userInfoUl').show();
                $('.cpyInfoUl').hide();
            }else if(ivInvoiceType==1){
                ivInvoiceTypeName='公司开票';
                $('.userInfoUl').hide();
                $('.cpyInfoUl').show();
            }
            var ivPayFreightName='';
            if(ivPayFreight==4){
                ivPayFreightName='货到付款';
                $('.ivFreightAmountLi').hide();
            }else{
                ivPayFreightName='已付款';
                $('.ivFreightAmountLi').show();
            }
            var ivReceipTypeName ='';
            if(data.ivReceipType==0){
                ivReceipTypeName='普通发票';
            }else if(data.ivReceipType==1){
                ivReceipTypeName='增值税专用发票';
            }
            $('#userAccount').html(userAccount);
            $('#ivCreatedate').html(ivCreatedateName);
            $('#ivInvoiceStatus').html(ivInvoiceStatusName);
            $('#ivInvoiceType').html(ivInvoiceTypeName);
            $('#ivInvoiceType').attr('data-value',ivInvoiceTypeName);
            $('#ivCompanyName').val(ivCompanyName);
            $('#ivTaxpayerNumber').val(ivTaxpayerNumber);
            $('#ivCompanyAddress').val(ivCompanyAddress);
            $('#ivPhoneNumber').val(ivPhoneNumber);
            $('#ivBankName').val(ivBankName);
            $('#ivBankAccount').val(ivBankAccount);
            $('#ivPayFreight').html(ivPayFreightName);
            $('#ivRecipients').val(ivRecipients);
            $('#ivRecipientsNumber').val(ivRecipientsNumber);
            $('#ivFreightAmount').html(ivFreightAmount);
            $('#ivConsigneeAddress').val(ivConsigneeAddress);

            $('#ivCompanyNameSpan').html(ivCompanyName);
            $('#ivTaxpayerNumberSpan').html(ivTaxpayerNumber);
            $('#ivCompanyAddressSpan').html(ivCompanyAddress);
            $('#ivPhoneNumberSpan').html(ivPhoneNumber);
            $('#ivBankNameSpan').html(ivBankName);
            $('#ivBankAccountSpan').html(ivBankAccount);
            $('#ivPayFreightSpan').html(ivPayFreightName);
            $('#ivRecipientsSpan').html(ivRecipients);
            $('#ivRecipientsNumberSpan').html(ivRecipientsNumber);
            $('#ivFreightAmountSpan').html(ivFreightAmount);
            $('#ivConsigneeAddressSpan').html(ivConsigneeAddress);
            $('#userAccountNameSpan').html(ivCompanyName);

            $('#discountChangMoney').html(discountChangMoney);
            $('#discountServiceMoney').html(discountServiceMoney);
            $('#icStatus').html(ivInvoiceStatusName);
            $('#ivInvoiceNumber').val(ivInvoiceNumber);
            $('#userAccountName').val(ivCompanyName);
            $('#invoiceType').html(ivReceipTypeName);
            $('#invoiceNum').html(data.ivInvoiceNumber);
            $('#ivRefuseRejectionHtml').html(data.ivRefuseRejection);
            $(":radio[name='invoiceType'][value='" + ivReceipType + "']").prop("checked", "checked");

        }
    });
}
//下拉
toUnbindEvent();
function toUnbindEvent() {
    $('.payTypeBlock').unbind();
    selectModel();
}
$('.ivInvoiceTypeUl').on("click", "li", function () {
    $(this).parent().siblings('div.model-select-text').text($(this).text())
        .attr('data-value', $(this).attr('data-option'));
    var typeNum = $('#ivInvoiceType').attr('data-value');
    if(typeNum==0){
        $('.userInfoUl').show();
        $('.cpyInfoUl').hide();
    }else if(typeNum==1){
        $('.userInfoUl').hide();
        $('.cpyInfoUl').show();
    }
})
//编辑
$('body').off('click','#cpyEditBtn').on('click','#cpyEditBtn',function(){
    $('#saveUl').show();
    $('#editUl').hide();
})
//保存
$('body').off('click','#cpySaveBtn').on('click','#cpySaveBtn',function(){
    $('#editUl').show();
    $('#saveUl').hide();
    var typeValue = $('#ivInvoiceType').attr('data-value');
    if(typeValue=='个人开票'){
        toSaveInvoiceUserInfo();
    }else if(typeValue=='公司开票'){
        toSaveInvoiceCpyInfo();
    }
    if(typeValue==0){
        toSaveInvoiceUserInfo();
    }else if(typeValue==1){
        toSaveInvoiceCpyInfo();
    }
})
function toSaveInvoiceUserInfo(){
    var ivCompanyNameValue =$('#ivCompanyName').val();
    var ivTaxpayerNumberValue =$('#ivTaxpayerNumber').val();
    var ivCompanyAddressValue =$('#ivCompanyAddress').val();
    var ivPhoneNumberValue =$('#ivPhoneNumber').val();
    var ivBankNameValue =$('#ivBankName').val();
    var ivBankAccountValue =$('#ivBankAccount').val();
    var ivConsigneeAddressValue =$('#ivConsigneeAddress').val();
    var ivRecipientsValue =$('#ivRecipients').val();
    var ivRecipientsNumberValue =$('#ivRecipientsNumber').val();
    var ivUserNameValue =$('#userAccountName').val();
    $.ajax({
        type: "post",
        url: basePath + modifyFinInvoiceUrl,
        async: true,
        data: {
            ivCompanyName: ivUserNameValue,
            ivTaxpayerNumber: ivTaxpayerNumberValue,
            ivCompanyAddress: ivCompanyAddressValue,
            ivPhoneNumber: ivPhoneNumberValue,
            ivBankName: ivBankNameValue,
            ivBankAccount: ivBankAccountValue,
            ivConsigneeAddress: ivConsigneeAddressValue,
            ivRecipients: ivRecipientsValue,
            ivRecipientsNumber: ivRecipientsNumberValue,
            pkInvoice:pkInvoice
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
                    content: '修改成功',
                    btn: ["确定"],
                    yes: function (index, layero) {
                        layer.closeAll();
                        getInvoiceBasicInfo()
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        getInvoiceBasicInfo()
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
function toSaveInvoiceCpyInfo(){
    var ivCompanyNameValue =$('#ivCompanyName').val();
    var ivTaxpayerNumberValue =$('#ivTaxpayerNumber').val();
    var ivCompanyAddressValue =$('#ivCompanyAddress').val();
    var ivPhoneNumberValue =$('#ivPhoneNumber').val();
    var ivBankNameValue =$('#ivBankName').val();
    var ivBankAccountValue =$('#ivBankAccount').val();
    var ivConsigneeAddressValue =$('#ivConsigneeAddress').val();
    var ivRecipientsValue =$('#ivRecipients').val();
    var ivRecipientsNumberValue =$('#ivRecipientsNumber').val();
    var ivUserNameValue =$('#userAccountName').val();
    $.ajax({
        type: "post",
        url: basePath + modifyFinInvoiceUrl,
        async: true,
        data: {
            ivCompanyName: ivCompanyNameValue,
            ivTaxpayerNumber: ivTaxpayerNumberValue,
            ivCompanyAddress: ivCompanyAddressValue,
            ivPhoneNumber: ivPhoneNumberValue,
            ivBankName: ivBankNameValue,
            ivBankAccount: ivBankAccountValue,
            ivConsigneeAddress: ivConsigneeAddressValue,
            ivRecipients: ivRecipientsValue,
            ivRecipientsNumber: ivRecipientsNumberValue,
            pkInvoice:pkInvoice
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
                    content: '修改成功',
                    btn: ["确定"],
                    yes: function (index, layero) {
                        layer.closeAll();
                        getInvoiceBasicInfo()
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        getInvoiceBasicInfo()
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
//确定开票
$('body').off('click','#confirmBtn').on('click','#confirmBtn',function(){
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["确认", "font-size:12px;text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '180px'],//宽高
        content: "确定开票吗？",
        btn: ["确定", "取消"],
        yes: function (index, layero) {
            layer.closeAll();
            toConfirmInvoice();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
})
function toConfirmInvoice(){
    var ivReceipType =$("input[name='invoiceType']:checked").val();
    var ivInvoiceNumber =$('#ivInvoiceNumber').val();
    if(!ivInvoiceNumber){
        layer.tips('请输入发票号码，长度不超过50位！', '#ivInvoiceNumber', {
            tips: 4
        });
        return false;
    }
    if(ivInvoiceNumber.length>50){
        layer.tips('发票号码不能超过50位！', '#ivInvoiceNumber', {
            tips: 4
        });
        return false;
    }
    var ivReceipTypeName ='';
    if(ivReceipType==0){
        ivReceipTypeName='普通发票';
    }else if(ivReceipType==1){
        ivReceipTypeName='增值税专用发票';
    }
    $('#invoiceType').html(ivReceipTypeName);
    $('#invoiceNum').html(ivInvoiceNumber);
    $.ajax({
        type: "post",
        url: basePath + confirmInvoiceUrl,
        async: true,
        data: {
            ivReceipType: ivReceipType,
            ivInvoiceNumber: ivInvoiceNumber,
            pkInvoice:pkInvoice
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
                    content: '开票成功',
                    btn: ["确定"],
                    yes: function (index, layero) {
                        layer.closeAll();
                        $('#invoiceSuccess').show();
                        $('#waitInvoice').hide();
                        $('#refuseInvoice').hide();
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        $('#invoiceSuccess').show();
                        $('#waitInvoice').hide();
                        $('#refuseInvoice').hide();
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
//拒绝开票
$('body').off('click','#refuseBtn').on('click','#refuseBtn',function(){
    layer.closeAll();
    layer.open({
        type: 1,
        offset: '100px',
        title: ["拒绝开票", "font-size:12px;text-align:center;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '220px'],//宽高
        content: $('.ivRefuseRejectionBlock'),
        btn: ["确定", "取消"],
        yes: function (index, layero) {
            layer.closeAll();
            toRefuseInvoice();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
})
function toRefuseInvoice(){
    $('.tips').html("");
    var ivRefuseRejection =$("#ivRefuseRejectionTxt").val();
    if(ivRefuseRejection.length>200){
        $('.tips').html("拒绝理由不能超过200字");
        return false;
    }
    $('#ivRefuseRejectionHtml').html(ivRefuseRejection);
    $.ajax({
        type: "post",
        url: basePath + refuseInvoiceUrl,
        async: true,
        data: {
            ivRefuseRejection: ivRefuseRejection,
            pkInvoice:pkInvoice
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
                    content: '已拒绝开票',
                    btn: ["确定"],
                    yes: function (index, layero) {
                        layer.closeAll();
                        $('#refuseInvoice').show();
                        $('#invoiceSuccess').hide();
                        $('#waitInvoice').hide();
                    },
                    cancel: function (index, layero) {
                        layer.closeAll();
                        $('#refuseInvoice').show();
                        $('#invoiceSuccess').hide();
                        $('#waitInvoice').hide();
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
//点击修改(已开票)
$('body').off('click','#resetBtn').on('click','#resetBtn',function(){
    $('#waitInvoice').show();
    $('#refuseInvoice').hide();
    $('#invoiceSuccess').hide();
    $('#icStatus').html('已开票');
    $('#confirmCancelBtn').show();
    $('#confirmBtn').show();
    $('#refuseBtn').hide();
    $('#refuseCancelBtn').hide();

})
//点击修改(已拒绝)
$('body').off('click','#resetBtnRefuse').on('click','#resetBtnRefuse',function(){
    $('#waitInvoice').show();
    $('#refuseInvoice').hide();
    $('#invoiceSuccess').hide();
    $('#icStatus').html('已拒绝开票');
    $('#refuseCancelBtn').show();
    $('#refuseBtn').hide();
    //$('#refuseBtn').addClass('saveBtn');
    //$('#refuseBtn').removeClass('cancelBtn');
    $('#confirmBtn').show();
    $('#confirmCancelBtn').hide();

})

//取消修改(已开票取消)
$('body').off('click','#confirmCancelBtn').on('click','#confirmCancelBtn',function(){
    $('#waitInvoice').hide();
    $('#refuseInvoice').hide();
    $('#invoiceSuccess').show();
})
//取消修改(已拒绝取消)
$('body').off('click','#refuseCancelBtn').on('click','#refuseCancelBtn',function(){
    $('#waitInvoice').hide();
    $('#invoiceSuccess').hide();
    $('#refuseInvoice').show();
})
$('body').off('click','#lookBtn').on('click','#lookBtn',function(){
    layer.closeAll();
    listInfo(pkInvoice);
    layer.open({
        type: 1,
        offset: '100px',
        title: ["查看开票订单", "font-size:12px;text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['860px', '400px'],//宽高
        content: $('.bindPile'),
        btn: ["确定", "取消"],
        yes: function (index, layero) {
            layer.closeAll();
            toRefuseInvoice();
        },
        cancel: function (index, layero) {
            layer.closeAll();
        }
    });
})
function listInfo(pkInvoice){
    $('input[name=invoiceId]').val(pkInvoice);
    initTable("invoiceListForm", "userInvoiceListPage", getOrderInvoiceDetailUrl, getOrderInvoiceCallback);
}
function getOrderInvoiceCallback(req){
    var data = req.dataObject;
    var pageNum = req.pager.pageNo;
    var listTr = "";
    for (var i = 0; i < data.length; i++) {
        var orderStatusName ='';
        if(data[i].orderStatus==1){
            orderStatusName='待支付';
        }else if(data[i].orderStatus==2){
            orderStatusName='支付成功 ';
        }else if(data[i].orderStatus==3){
            orderStatusName='完成操作';
        }else if(data[i].orderStatus==4){
            orderStatusName='异常订单';
        }
        listTr += '<tr><td><input type="checkbox" name="ids" class="selectedBox" value="' + data[i].orderCode +'"/>'
            + '</td><td>' + (i+1+(pageNum-1)*20)
            + '</td><td>' + data[i].orderCode
            + '</td><td>' + data[i].startChargeTime +'-'+ data[i].endChargeTime
            + '</td><td>' + data[i].totalElectricMoney
            + '</td><td>' + data[i].totalServiceMoney
            + '</td><td>' + data[i].favMoney
            + '</td><td>' + data[i].favServiceMoney
            + '</td><td>' + data[i].electricPileCode
            + '</td><td>' + data[i].powerstationName
            + '</td><td>' + orderStatusName
            + '</td></tr>';
    }
    $("#invoiceListTab").html(listTr);
}

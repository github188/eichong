/**
 * Created by Administrator on 2017/11/28 0028.
 */

//返回按钮
$('body').off('click', '#cancelBtn').on('click', '#cancelBtn', function() {
    window.history.back();
});

$(document).ready(function() {
    var status= getUrlParam('status');//查看状态
    var cpyId= getUrlParam('cpyId');//查询条件
    var pkId= getUrlParam('pkId');//公司id
    if(status == 1){
        //状态——编辑
        $('#changeBtn').css('display','block');
        $('#submitBtn,#searchBtn,.add-company').css('display','none');
        $('.searchZnode').attr('disabled',true);
        $.ajax({
            type: "post",
            url: basePath + getFinAccountSplitConfigListUrl,
            async: true,
            data: {
                cpyId: cpyId,
                pkId: pkId
            },
            success:function(data){
                var str = data.dataObject[0];
                var company_data = (JSON.parse(str.splitRules)).data,//公司对象
                    mainComName = str.cpyName,//资产公司名称
                    company_name = (str.strCpyName).split('，');//分账公司名称
                if(str.splitMode == 0){
                    $('input[name="state"]:eq(0)').attr("checked", 'checked');
                    $('input[name="state"]:eq(1)').attr("disabled",true);
                }else if(str.splitMode == 1){
                    $('input[name="state"]:eq(0)').attr("disabled",true);
                    $('input[name="state"]:eq(1)').attr("checked", 'checked');
                    $('.PropAccount').css('display','none');
                    $('.SplitAccount').css('display','block');
                }
                addCompany(company_data,company_name,mainComName);
                $('.SplitAccount .userAccountInput').val((str.strPrice).split('，')[0]);
                var zArr = new Array(cpyId,pkId,str.splitRules);
                getZNodes(zArr);
                $('#changeBtn').unbind('click').bind('click',{paramName: modifyFinAccountSplitConfigUrl,paramId: pkId,paramData:str.electricPileIds}, submitAjax)
            }
        })
    }else if(status == 2){
        //状态——详细
        $.ajax({
            type: "post",
            url: basePath + getFinAccountSplitConfigListUrl,
            async: true,
            data: {
                cpyId: cpyId,
                pkId: pkId
            },
            success:function(data){
                var str = data.dataObject[0];
                var company_data = (JSON.parse(str.splitRules)).data,//公司对象
                    mainComName = str.cpyName,//资产公司名称
                    company_name = (str.strCpyName).split('，');//分账公司名称
                if(str.splitMode == 0){
                    $('input[name="state"]:eq(0)').attr("checked", 'checked');
                }else if(str.splitMode == 1){
                    $('input[name="state"]:eq(1)').attr("checked", 'checked');
                    $('.PropAccount').css('display','none');
                    $('.SplitAccount').css('display','block');
                }
                addCompany(company_data,company_name,mainComName);
                $('.SplitAccount .userAccountInput').val((str.strPrice).split('，')[0]);
                var zArr = new Array(cpyId,pkId,str.splitRules);
                getZNodes(zArr);

                $('#submitBtn,.link-company').css('display','none');
                $('#cancelBtn').css('margin','3% 0 0 15%');
                $('.add-company').remove();
                $('input[name="state"],.userAccountInput').attr('disabled',true);
            }
        })
    }else if(status == 0){
        //状态——新增
        $('#submitBtn').bind('click',{paramName: addFinAccountSplitConfigUrl,paramId: pkId,paramData:''}, submitAjax)
    }


});

//基础layui提示
function CommonCaution(data){
    layer.open({
        type: 1,
        offset: '100px',
        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
        shadeClose: false,
        closeBtn: 1,
        area: ['310px', '160px'],
        //宽高
        content: data,
        btn: ["确定"],
        yes: function(index, layero) {
            layer.closeAll();
        },
        cancel: function(index, layero) {
            layer.closeAll();
        }
    });
}

//对比两个数组,取不同的地方
function compareArray(a,b){
    var crr = new Array();
    for(var i=0;i<a.length;i++){
        var stra = a[i];
        var count = 0;
        for(var j=0;j<b.length;j++){
            var strb = b[j]
            if (stra==strb) {
                count++;
            }
        }
        if (count == 0) { //表示数组1的这个值没有重复的，放到返回列表中
            crr.push(stra);
        }
    }
    return crr;
}

function sameArray(a,b){
    var crr = new Array();
    for(var i=0;i<a.length;i++){
        var stra = a[i];
        var count = 0;
        for(var j=0;j<b.length;j++){
            var strb = b[j]
            if (stra==strb) {
                count++;
            }
        }
        if (count > 0) { //表示数组1的这个值没有重复的，放到返回列表中
            crr.push(stra);
        }
    }
    return crr
}

//限制两位小数,保留两位小数
/**
 * 实时动态强制更改用户录入
 **/
function amount(th){
    var regStrs = [
        ['^0(\\d+)$', '$1'], //禁止录入整数部分两位以上，但首位为0
        ['[^\\d\\.]+$', ''], //禁止录入任何非数字和点
        ['\\.(\\d?)\\.+', '.$1'], //禁止录入两个以上的点
        ['^(\\d+\\.\\d{2}).+', '$1'] //禁止录入小数点后两位以上
    ];
    for(var i=0; i<regStrs.length; i++){
        var reg = new RegExp(regStrs[i][0]);
        th.value = th.value.replace(reg, regStrs[i][1]);
    }
}

/**
 * 录入完成后，输入模式失去焦点后对录入进行判断并强制更改，并对小数点进行0补全
 * arg1 inputObject
 **/
function overFormat(th){
    var v = th.value;
    if(v === ''){
        v = '0.00';
    }else if(v === '0'){
        v = '0.00';
    }else if(v === '0.'){
        v = '0.00';
    }else if(/^0+\d+\.?\d*.*$/.test(v)){
        v = v.replace(/^0+(\d+\.?\d*).*$/, '$1');
        v = inp.getRightPriceFormat(v).val;
    }else if(/^0\.\d$/.test(v)){
        v = v + '0';
    }else if(!/^\d+\.\d{2}$/.test(v)){
        if(/^\d+\.\d{2}.+/.test(v)){
            v = v.replace(/^(\d+\.\d{2}).*$/, '$1');
        }else if(/^\d+$/.test(v)){
            v = v + '.00';
        }else if(/^\d+\.$/.test(v)){
            v = v + '00';
        }else if(/^\d+\.\d$/.test(v)){
            v = v + '0';
        }else if(/^[^\d]+\d+\.?\d*$/.test(v)){
            v = v.replace(/^[^\d]+(\d+\.?\d*)$/, '$1');
        }else if(/\d+/.test(v)){
            v = v.replace(/^[^\d]*(\d+\.?\d*).*$/, '$1');
            ty = false;
        }else if(/^0+\d+\.?\d*$/.test(v)){
            v = v.replace(/^0+(\d+\.?\d*)$/, '$1');
            ty = false;
        }else{
            v = '0.00';
        }
    }
    th.value = v;
}

//定义数据类型
var setting = {
    check: {
        enable: true,
        chkboxType: {
            "Y": "ps",
            "N": "ps"
        }
    },
    data: {
        simpleData: {
            enable: true
        },
        check: {
            chkDisabledInherit: true,
            enable: true
        },
        key: {
            checked: "isSelected",
            children: "list",
            name: "name",
            title: "id"
        }
    },
    callback: {
        onCheck: zTreeOnCheck
    }

};

//树勾选监听

function zTreeOnCheck(event, treeId, treeNode) {
    var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
    var nodeChecked = treeObj.getCheckedNodes(true);
    var nodeArr = new Array();
        for (var i = 0; i < nodeChecked.length; i++) {
            if(nodeChecked[i].isParent == false){
                nodeArr.push(nodeChecked[i].id)
            }
        }
    return nodeArr;
}

//加载树结构

function getZNodes(obj) {
    //新增
    var zData = {cpyId: obj[0]};
    //详细和编辑
    if(obj.length>1){
        zData = {
            cpyId : obj[0],
            pkId : obj[1],
            splitRules : obj[2]
        }
    }
    $.ajax({
        type: "post",
        url: basePath + finGetStationAndPileUrl,
        async: true,
        data: zData,
        success: function(req) {
            zNodes = req.dataObject.list;
            if(zNodes.length == 0){
                CommonCaution('该资产公司下没有可维护的电桩')
                return false
            }
            $.fn.zTree.init($("#treeDemo"), setting, zNodes);
            treeObj = $.fn.zTree.getZTreeObj("treeDemo");
        }
    });
}

//分账配置radio判断
$('input[name="state"]').on('click', function() {
    var state = $(this).val();
    if (state == 0) {
        $('.PropAccount,.add-company').show();
        $('.SplitAccount').hide();
    } else if (state == 1) {
        $('.PropAccount,.add-company').hide();
        $('.SplitAccount').show();
        $('.link-company').click();
    }
});

//添加公司

function addCompany(obj,elem,mainElem) {
    var len = $('.electric-company .form-group').length;
    var prop_one = $('.PropAccount .form-group:eq(1)'), //服务费
        prop_two = $('.PropAccount .form-group:eq(2)'); //电费
    var newCompany = '',newService = '',newElectric = '';
    if (len > 3) {
        CommonCaution('最多可添加2个分成公司');
        return false;
    }

    if(typeof (obj) != 'undefined'){
        //资产公司对应
        $('.searchZnode').val(mainElem).attr('data-option',obj[0].cpyId);
        prop_one.find('.userAccount').eq(0).find('input').val(obj[0].servicesRatio);
        prop_two.find('.userAccount').eq(0).find('input').val(obj[0].electricityRatio);
        //初始存在的第一个分账公司
        $('.electric-company .form-group:eq(1)').find('input').val(elem[0]).attr('data-option',obj[1].cpyId);
        prop_one.find('.userAccount').eq(1).find('input').val(obj[1].servicesRatio);
        prop_two.find('.userAccount').eq(1).find('input').val(obj[1].electricityRatio);
        //新增对应
        for(var i=0;i<elem.length-1;i++){
            newCompany = '<div class="form-group col-xs-10"><div class="col-sm-3 queryParam">选择分账公司' + (len+i) + '</div>' + ' <div class="userAccount queryParam"> ' + '<input type="text" data-option='+obj[i+2].cpyId+' name="" class="userAccountInput cityName" placeholder="输入公司名称" value='+elem[i+1]+'></div>' + '<div class="col-sm-2 userAccount queryParam">' + '<a href="javascript:void(0);" class="link-company" onclick="removeCompany(this);">删除</a></div></div>';
            newService = '<div class="col-sm-1 queryParam userAccount"><input type="text" name="" class="userAccountInput" placeholder="分账公司' + len + '" value='+obj[i+2].servicesRatio+'></div>';
            newElectric = '<div class="col-sm-1 queryParam userAccount"><input type="text" name="" class="userAccountInput" placeholder="分账公司' + len + '" value='+obj[i+2].electricityRatio+'></div>';
            $('.electric-company').append(newCompany);
            prop_one.append(newService);
            prop_two.append(newElectric);
        }
    }else{
        newCompany = '<div class="form-group col-xs-10"><div class="col-sm-3 queryParam">选择分账公司' + len + '</div>' + ' <div class="userAccount queryParam"> ' + '<input type="text" name="" class="userAccountInput cityName" placeholder="输入公司名称"></div>' + '<div class="col-sm-2 userAccount queryParam">' + '<a href="javascript:void(0);" class="link-company" onclick="removeCompany(this);">删除</a></div></div>';
        newService = '<div class="col-sm-1 queryParam userAccount">' + '<input type="text" name="" class="userAccountInput" placeholder="分账公司' + len + '"></div>';
        newElectric = '<div class="col-sm-1 queryParam userAccount">' + '<input type="text" name="" class="userAccountInput" placeholder="分账公司' + len + '"></div>';
        $('.electric-company').append(newCompany);
        prop_one.append(newService);
        prop_two.append(newElectric);
    }
}

//移除公司
function removeCompany(obj) {
    var elem = obj.parentNode.parentNode;
    var index = $('.electric-company .form-group').index(elem) + 1;
    obj.parentNode.parentNode.parentNode.removeChild(elem);
    $('.serviceFee div:eq(' + index + '),.electricFee div:eq(' + index + ')').remove();
}

//input分值取整提醒
$(document).on({
    keyup: function() {
        if (this.value.length == 1) {
            this.value = this.value.replace(/[^0-9]/g, '')
        } else {
            this.value = this.value.replace(/\D/g, '')
        }
        //大于100不可提交
        var mixNum = '',
            eleNum = '';
        $('.serviceFee .userAccountInput').each(function() {
            mixNum = Number(mixNum) + Number($(this).val());
        });
        $('.electricFee .userAccountInput').each(function() {
            eleNum = Number(eleNum) + Number($(this).val());
        });
        if (mixNum > 100 || eleNum > 100) {
            this.value = '';
        }

    },
    afterpaste: function() {
        if (this.value.length == 1) {
            this.value = this.value.replace(/[^1-9]/g, '0')
        } else {
            this.value = this.value.replace(/\D/g, '')
        }
    }
}, '.serviceFee .userAccountInput,.electricFee .userAccountInput');

//电桩查询按钮
$('#searchBtn').on('click',function(){
    var searchData = $('.searchZnode').attr('data-option');
    if(typeof (searchData) != 'undefined'){
        $('#treeDemo').html('');
        getZNodes(new Array(searchData));
    }else{
        CommonCaution('请先选择电桩资产公司')
    }
});

//[-----------------------模糊查询相关---------------------------------

//处理根据城市模糊查询
var last;
$('.cityName').keyup(function(event){
    var cityname = $(this);
    if(cityname.hasClass('searchZnode') == true){
        $('.searchUl,#treeDemo').html('');
    }
    cityname.attr('data-option','');
    $(this).after($('.cityQueryBlock'));
    last = event.timeStamp;
    setTimeout(function(){
        if(last-event.timeStamp==0)
        {
            getCityListByName(cityname)
        }
    },500)
});
//$('body').off('keyup').on('keyup', '.cityName', function() {
//    var cityname = $(this);
//    if(cityname.hasClass('searchZnode') == true){
//        $('.searchUl,#treeDemo').html('');
//    }
//    cityname.attr('data-option','');
//    $(this).after($('.cityQueryBlock'));
//    setTimeout(getCityListByName(cityname), 2000);
//
//});

function getCityListByName(obj) {
    var cpyName = obj.val();
    var reg = /^[\u4E00-\u9FA5]+$/;
    if (cpyName == '') {
        $('.searchUl').html('');
        $('.cityQueryBlock').css('display','none');
    } else {
            $.ajax({
                type: "post",
                url: basePath + getCompanyListByCpyNameUrl,
                async: true,
                data: {
                    cpyName: cpyName
                },
                success: function(data) {
                    if (data.status == 1000) {
                        var data = data.dataObject;
                        //有数据的时候才显示下拉框
                        if(data.length>0){
                            $('.cityQueryBlock').css('display', 'block');
                        }else{
                            $('.cityQueryBlock').css('display', 'none');
                        }
                        var cityNameLi = '';
                        for (var i = 0; i < data.length; i++) {
                            cityNameLi += '<li data-option="' + data[i].cpyId + '">' + data[i].cpyName + '</li>';
                        }
                        $('.searchUl').html(cityNameLi);
                    } else if (data.status == 9001) {
                        layer.closeAll();
                        layer.open({
                            type: 1,
                            offset: '100px',
                            title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                            shadeClose: false,
                            closeBtn: 1,
                            area: ['310px', '160px'],
                            //宽高
                            content: '会话超时，请重新登陆！',
                            btn: ["确定"],
                            yes: function(index, layero) {
                                layer.closeAll();
                                window.top.location.href = basePath + toLoginUrl;
                            },
                            cancel: function(index, layero) {
                                layer.closeAll();
                                window.top.location.href = basePath + toLoginUrl;
                            }
                        });
                    }
                }
            });
    }
}
//检测数组重复,已经存在的公司
function repeatArr(obj, elem) {
    var ary = obj;
    var nary = ary.sort();
    for (var i = 0; i < ary.length; i++) {
        if (nary[i] == nary[i + 1]) {
            layer.open({
                type: 1,
                offset: '100px',
                title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                shadeClose: false,
                closeBtn: 1,
                area: ['310px', '160px'],
                //宽高
                content: '不可选择已存在的分账公司，请重新选择',
                btn: ["确定"],
                yes: function(index, layero) {
                    layer.closeAll();
                    elem.val('').attr('data-option', '');
                },
                cancel: function(index, layero) {
                    layer.closeAll();
                    elem.val('').attr('data-option', '');
                }
            });
        }
    }
}
//点击模糊查询的城市，对input赋值
$('body').off('click', '.searchUl li').on('click', '.searchUl li', function() {
    var cityCode = $(this).attr('data-option'),
    //公司cpyid
        cityName = $(this).text(); //公司名
    var aimAt = $(this).parents('.userAccount').find('.cityName'); //目标input框
    var len = $('.electric-company .form-group').length,
        newArr = new Array();
    aimAt.attr('data-option', cityCode).val(cityName); //input赋值
    $('.cityQueryBlock').css('display', 'none'); //模块隐藏
    $('#cancelBtn').after($('.cityQueryBlock')); //避免删除，模块移动至底部
    //组装已存在的cpyid数组
    for (var i = 0; i < len; i++) {
        var arr_child = $('.electric-company .form-group:eq(' + i + ')').find('input').attr('data-option');
        if (typeof(arr_child) == 'undefined') {
            return false;
        }
        newArr.push(arr_child);
    }
    repeatArr(newArr, aimAt); //判断重复公司名
});

//---------------------------------------------------------------------------------------

//数据提交
var submitAjax = function(ajaxAddress) {
    if(ajaxAddress.data.paramData != ''){
        var firArray = (ajaxAddress.data.paramData).split(',');//原有的电桩数组
        var secArray = zTreeOnCheck();//经过修改的电桩数组
    }

    var state = $('input[name="state"]:radio:checked').val(); //分账配置
    var arr = new Array();
    if (state == 0) {
        //服务费&电费
        for (var i = 0; i < $('.electric-company .form-group').length; i++) {
            var cpyId = $('.electric-company .form-group:eq(' + i + ')').find('input').attr('data-option'),
                servicesRatio = $('.serviceFee div:eq(' + (i + 1) + ')').find('input').val(),
                electricityRatio = $('.electricFee div:eq(' + (i + 1) + ')').find('input').val();
            //
            if (typeof(cpyId) == 'undefined') {
                CommonCaution('请完整填写分账对象');
                return false;
            } else if (servicesRatio == '') {
                CommonCaution('请完整填写分账比例——服务费');
                return false;
            } else if (electricityRatio == '') {
                CommonCaution('请完整填写分账比例——电费');
                return false;
            }
            var arr_child = {"cpyId":"" + cpyId +"","servicesRatio":"" + servicesRatio + "","electricityRatio":"" + electricityRatio + ""};
            arr.push(JSON.stringify(arr_child))
        }
        var splitRules = '{"data": ['+arr+']}';
        //比例不等于100则提示
        var serNum = '',
            eleNum = '';
        $('.serviceFee .userAccountInput').each(function() {
            serNum = Number(serNum) + Number($(this).val());
        });
        $('.electricFee .userAccountInput').each(function(){
            eleNum = Number(eleNum) + Number($(this).val());
        });
        if (serNum != 100) {
            CommonCaution('请填写正确的分成比例（服务费）');
            return false;
        }else if(eleNum != 100){
            CommonCaution('请填写正确的分成比例（电费）');
            return false;
        }
        //电桩判断
        if($('#treeDemo').html() == ''){
            CommonCaution('请先查询并至少选择一个选定的资产归属公司的电桩');
            return false;
        }else if (zTreeOnCheck().length == 0) {
            CommonCaution('请至少选择一个选定的资产归属公司的电桩');
            return false;
        }
        //提交的数据
        if(ajaxAddress.data.paramId == ''){
            var data = {
                'splitMode': state,
                'cpyId': $('.electric-company .form-group:eq(0)').find('input').attr('data-option'),
                'splitRules': splitRules,
                'electricPileIds': zTreeOnCheck().toString()
            };
        }else{
            var data = {
                'splitMode': state,
                'cpyId': $('.electric-company .form-group:eq(0)').find('input').attr('data-option'),
                'splitRules': splitRules,
                'addElectricPileIds': compareArray(secArray,firArray).toString(),
                'delElectricPileIds': compareArray(firArray,secArray).toString(),
                'updElectricPileIds': sameArray(firArray,secArray).toString(),
                'pkId':ajaxAddress.data.paramId
            };
        }

        $.ajax({
            type: "post",
            url: basePath + ajaxAddress.data.paramName,
            async: true,
            data: data,
            success:function(data){
                if(data.success){
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '160px'],
                        //宽高
                        content: '操作成功',
                        btn: ["确定"],
                        yes: function(index, layero) {
                            layer.closeAll();
                            window.location.href="finManage_list.html";
                        },
                        cancel: function(index, layero) {
                            layer.closeAll();
                            window.location.href="finManage_list.html";
                        }
                    });
                }else if(data.status == 2001){
                    layer.closeAll();
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '160px'],
                        //宽高
                        content: data.msg,
                        btn: ["确定"],
                        yes: function(index, layero) {
                            layer.closeAll();
                        },
                        cancel: function(index, layero) {
                            layer.closeAll();
                        }
                    });
                    return false;
                } else if (data.status == 9001) {
                    layer.closeAll();
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '160px'],
                        //宽高
                        content: '会话超时，请重新登陆！',
                        btn: ["确定"],
                        yes: function(index, layero) {
                            layer.closeAll();
                            window.top.location.href = basePath + toLoginUrl;
                        },
                        cancel: function(index, layero) {
                            layer.closeAll();
                            window.top.location.href = basePath + toLoginUrl;
                        }
                    });

                }
            }
        })
    } else if (state == 1) {
        //电量&单价
        for (var i = 0; i < $('.electric-company .form-group').length; i++) {
            var cpyId = $('.electric-company .form-group:eq(' + i + ')').find('input').attr('data-option'),
                price = $('.SplitAccount').find('input').val();
            //
            if (typeof(cpyId) == 'undefined') {
                CommonCaution('请完整填写分账对象');
                return false;
            }else if(price == 0){
                CommonCaution('分账价格不可为0');
                return false;
            }
            var arr_child ={"cpyId":"" + cpyId +"","price":"" + price + ""};
            arr.push(JSON.stringify(arr_child));
        }
        var splitRules = '{"data": ['+arr+']}';
        //电桩判断
        if($('#treeDemo').html() == ''){
            CommonCaution('请先查询并至少选择一个选定的资产归属公司的电桩');
            return false;
        }else if (zTreeOnCheck().length == 0) {
            CommonCaution('请至少选择一个选定的资产归属公司的电桩');
            return false;
        }
        //提交的数据
        if(ajaxAddress.data.paramId == ''){
            var data = {
                'splitMode': state,
                'cpyId': $('.electric-company .form-group:eq(0)').find('input').attr('data-option'),
                'splitRules': splitRules,
                'electricPileIds': zTreeOnCheck().toString()
            };
        }else{
            var data = {
                'splitMode': state,
                'cpyId': $('.electric-company .form-group:eq(0)').find('input').attr('data-option'),
                'splitRules': splitRules,
                'addElectricPileIds': compareArray(secArray,firArray).toString(),
                'delElectricPileIds': compareArray(firArray,secArray).toString(),
                'updElectricPileIds': sameArray(firArray,secArray).toString(),
                'pkId':ajaxAddress.data.paramId
            };
        }

        $.ajax({
            type: "post",
            url: basePath + ajaxAddress.data.paramName,
            async: true,
            data: data,
            success:function(data){
                if(data.success){
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '160px'],
                        //宽高
                        content: '操作成功',
                        btn: ["确定"],
                        yes: function(index, layero) {
                            layer.closeAll();
                            window.location.href="finManage_list.html";
                        },
                        cancel: function(index, layero) {
                            layer.closeAll();
                            window.location.href="finManage_list.html";
                        }
                    });
                }else if(data.status == 2001){
                    layer.closeAll();
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '160px'],
                        //宽高
                        content: data.msg,
                        btn: ["确定"],
                        yes: function(index, layero) {
                            layer.closeAll();
                        },
                        cancel: function(index, layero) {
                            layer.closeAll();
                        }
                    });
                    return false;
                } else if (data.status == 9001) {
                    layer.closeAll();
                    layer.open({
                        type: 1,
                        offset: '100px',
                        title: ["提示", "font-size:12px;text-align:left;padding-left:20px;"],
                        shadeClose: false,
                        closeBtn: 1,
                        area: ['310px', '160px'],
                        //宽高
                        content: '会话超时，请重新登陆！',
                        btn: ["确定"],
                        yes: function(index, layero) {
                            layer.closeAll();
                            window.top.location.href = basePath + toLoginUrl;
                        },
                        cancel: function(index, layero) {
                            layer.closeAll();
                            window.top.location.href = basePath + toLoginUrl;
                        }
                    });

                }
            }
        })
    }

};
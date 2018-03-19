/**
 * Created by Administrator on 2017/8/22.
 */
//校验电桩编号、、新增电桩编号(4位数字)
function pileNum(obj){
    var objValue=$(obj).val();
    var reg=/^[0-9]{1,4}$/;
    if(!objValue){
        $(obj).val('');
        return false;
    }else if(!reg.test(objValue)){
        $(obj).val('');
        return false;
    }
    return true;
}
//校验电桩编码、、（16位数字）
function pileCodeNum(obj){
    var objValue=$(obj).val();
    var reg=/^[0-9]{16}$/;
    if(!objValue){
        $(obj).val('');
        return false;
    }else if(!reg.test(objValue)){
        $(obj).val('');
        return false;
    }
    return true;
}
//校验下拉列表(非空校验)
function dropDownList(obj) {
    var objValue = $(obj).attr("data-value");
    if(!objValue){
        return false;
    }
    return true;
}
//校验经度(0.000000-180.000000 )
function longitudeTest(obj){
    var objValue=$(obj).val();
    var reg=/^([0-9]{0,3})+\.[0-9]{6}$/;
    if(!objValue){
        $(obj).val('');
        return false;
    }
    if(!reg.test(objValue)){
        $(obj).val('');
        return false;
    }else if(objValue>180){
        $(obj).val('');
        return false;
    }
    return true;
}
//校验纬度(0.000000-90.000000 )
function latitudeTest(obj){
    var objValue=$(obj).val();
    var reg=/^([0-9]{0,2})+\.[0-9]{6}$/;
    if(!objValue){
        $(obj).val('');
        return false;
    }
    if(!reg.test(objValue)){
        $(obj).val('');
        return false;
    }else if(objValue>90){
        $(obj).val('');
        return false;
    }
    return true;
}
//校验最大输出电压、电流、枪头数量(纯数字 )
function isInteger(obj){
    var objValue=$(obj).val();
    var reg=/^[0-9][\d]*$/;
    if(!objValue){
        $(obj).val('');
        return false;
    }else if(!reg.test(objValue)){
        $(obj).val('');
        return false;
    }
    return true;
}
//非空校验
function emptyTest(obj){
    var objValue=$(obj).val();
    if(!objValue){
        $(obj).val('');
        return false;
    }
    return true;
}
//校验sim编码 （字母和数字）
function simMacTest(obj){
    var objValue=$(obj).val();
    var reg=/^([a-z]|[A-Z]|[0-9])*$/;
    if(!objValue){
        $(obj).val('');
        return false;
    }else if(!reg.test(objValue)){
        $(obj).val('');
        return false;
    }
    return true;
}
//校验名称的 ( 输入限制长度num )
function lengthTest(obj,num){
    var objValue=$(obj).val();
    if(!objValue){
        $(obj).val('');
        return false;
    }else if(objValue.length>num){
        $(obj).val('');
        return false;
    }
    return true;
}

//校验联系方式(充电点)
/*function siteName(obj){
    var objValue=$(obj).val();
    //var reg=/^([0-9a-zA-Z_]|[/u4e00-/u9fa5]){1,}$/;
    var reg=/(^[0-9]{3,4}\-[0-9]{7}$)|(^[0-9]{7}$)|(^0{0,1}13[0-9]{9}$)|(^1[3|4|5|8]{1}[0-9]{9}$)/;
    if(!objValue){
        $(obj).focus();
        return false;
    }else if(!reg.test(objValue)){
        $(obj).focus();
        return false;
    }
    return true;
}*/

//电桩编辑部分验证公司标示必须为正整数（包括0）obj是dom对象，tipClass是提示框
function testNumber(obj,tipClass){
	var objValue=$(obj).val();
	var reg=/^[0-9][\d]*$/; // /^[1-9][\d]*$/
	if(!reg.test(objValue)){
		$(tipClass).show().html("公司标识必须为整数");
		$(obj).focus();
		$(obj).val("");
		return false;
	}else{
		return true;
	}
}
// 集中器新增的情况  验证表单的值是否为空的情况，obj是dom对象，tipClass是提示框
function testNull(obj,tipClass){
	var objValue=$(obj).val();
	if(objValue==""){
		var thisContent=$(obj).siblings().html();
		$(tipClass).show().html(thisContent+"不能为空");
		$(obj).focus();
		return false;
	}else{
		$(tipClass).hide().html("");
		return true;
	}
}

//中文、数字、字母或组合，不允许有特殊字符，可以有" -" 不能超过20位  var reg=/^[a-zA-Z0-9-\u4e00-\u9fa5]+$/;
function isBrandName(obj,tipClass){
	var objValue=$(obj).val();
	var reg=/^[a-zA-Z0-9-\u4e00-\u9fa5]+$/;
	var thisContent=$(obj).siblings().html();
	if(!reg.test(objValue)){
		$(tipClass).show().html("请填写正确的"+thisContent+",可以有'-',中文、数字、字母或组合");
		$(obj).focus();
		return false;
	}
	if(objValue.length>20){
		$(tipClass).show().html(thisContent+"长度不能超过20位！");
		$(obj).focus();
		return false;
	}
	return true;
	
}

//车型名称：中文、数字、字母或组合var reg=/^[a-zA-Z0-9\u4e00-\u9fa5]+$/;
function isModelName(obj,tipClass){
	var objValue=$(obj).val();
	var reg=/^[a-zA-Z0-9\u4e00-\u9fa5\s]+$/;
	var thisContent=$(obj).siblings().html();
	if(!reg.test(objValue)){
		$(tipClass).show().html("请填写正确的"+thisContent+",可以有中文、数字、字母或组合");
		$(obj).focus();
		return false;
	}
	if(objValue.length>20){
		$(tipClass).show().html(thisContent+"长度不能超过20位！");
		$(obj).focus();
		return false;
	}
	return true;
}
//验证是否为整数数字 
function isInteger(obj,tipClass){
	var objValue=$(obj).val();
	var reg=/^[0-9]*$/;
	var thisContent=$(obj).siblings().html();
	if(!reg.test(objValue)){
		$(tipClass).show().html(thisContent+"必须为整数数字！");
		$(obj).focus();
		return false;
	}
	return true;
}
//验证是否为纯数字
function isNumeric(obj,tipClass){
	var value=$(obj).val();
    if( value != null && value.length>0 && isNaN(value) == false){  
        return true;  
    }  
    else{  
    	var thisContent=$(obj).siblings().html();
    	$(tipClass).show().html(thisContent+"必须为纯数字！");
		$(obj).focus();
        return false;  
    }  
}
//判断输入框的value不能大于10
function maxValue(obj,tipClass){
	var value=$(obj).val();
	if(value>10){
		var thisContent=$(obj).siblings().html();
    	$(tipClass).show().html(thisContent+"不能大于10.00！");
		$(obj).focus();
		$(obj).val("");
        return false;  
	}else{
		return true;
	}
}

//判断bom清单里面的内容是否为空 整理数组避免多个&&
function testArray(obj,classTip){
	var list=$(obj+" input[type='text']");
	var success = false;
	list.each(function(index){
		if(list[index].value==""){
			var thisContent=$(list[index]).siblings().html();
			$(classTip).show().html(thisContent+"不能为空");
			list[index].focus();
			success = false;
			return false;
		}
		else{
			$(classTip).hide().html("");
			success = true;
		}
	})
	return success;
}
//验证select
function testSelect(obj,classTip){
	var list=$(obj+" select");
	var success = false;
	list.each(function(index){
		if(list[index].value==""){
			var thisContent=$(list[index]).siblings().html();
			$(classTip).show().html(thisContent+"不能为空");
			list[index].focus();
			success = false;
			return false;
		}
		else{
			$(classTip).hide().html("");
			success = true;
		}
	})
	return success;
}

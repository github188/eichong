//选择三级城市的代码=====================================================================
/*(function($, doc) {
		$.init();
		$.ready(function() {
			var cityPicker3 = new $.PopPicker({
					layer: 3
				});
				cityPicker3.setData(cityData3);
				var showCityPickerButton = doc.getElementById('showCityPicker3');
				var cityResult3 = doc.getElementById('cityResult3');
				var provinceId = doc.getElementById('provinceId');
				var cityId = doc.getElementById('cityId');
				var areaId = doc.getElementById('areaId');
				showCityPickerButton.addEventListener('tap', function(event) {
					cityPicker3.show(function(items) {
						cityResult3.innerText = (items[0] || {}).text + " " + (items[1] || {}).text + " " + (items[2] || {}).text;
						provinceId.value=(items[0] || {}).value ;
						cityId.value= (items[1] || {}).value;
						areaId.value=(items[2] || {}).value;
						//返回 false 可以阻止选择框的关闭
						//return false;
					});
				}, false);
		});
	})(mui, document);	
*/
//事件监听浏览器兼容===========================================================================
function attEvent(obj,type,fn){
	if(obj.addEventListener){
		obj.addEventListener(type,fn,false)
		
		}
	else if(obj.attachEvent){
		obj.attachEvent('on'+type,fn)
		
		}
	
	}
function removeEvent(obj,type,fn){
	if(obj.removeEventListener){
		obj.removeEventListener(type,fn,false)
		}
	else if(obj.detachEvent){
		obj.detachEvent('on'+type,fn)
		}
	
	}	
function getTarget(ev){
	if(ev.target){
	return ev.target
		}
	else if(window.event.srcElement){
	return window.event.srcElement
		}
}

//判断=======================================================================
/*attEvent(window,'load',function(){
		attEvent(people,'blur',peopleTest);//联系人
		attEvent(telNumber,'blur',phoneTest);//电话号码
		attEvent(detailInfo,'blur',detailAddr);//详细信息
})
*/
//验证联系人======联系人姓名,长度最大30==============================================================		
	
		function peopleTest(){
			var people=document.getElementById("people");
			var peopleValue=people.value;
			var re=/^.*[`!@#$%^&*()=+|\}\[\]{'\";://?.>,<·~！@#￥%……&*（）——+=|、】｝【｛‘“；：、？。》，《]+.*$/;
			if(re.test(people.value)){
				//	telTip.innerHTML="您的账号格式不对";
					mui.toast("对不起，联系人格式不正确，请重新输入！");
					people.focus();
					return false;
			}else if(peopleValue.length>30){
				people.focus();
				return false;
				mui.toast("联系人的长度不能大于30,请重新输入");
			}else if(peopleValue.length==0){
				mui.toast("联系人不能为空");
				people.focus();
				return false;
			}
			return true;
		}
//验证电话号码======联系人电话,长度最大15,匹配规则：11位手机号 | 3/4位区号-7/8位电话号==========================================================
		
		//var telTip=document.getElementById("telTip");
		
		function phoneTest(){
			var telNumber=document.getElementById("telNumber");
			var telNumberValue=telNumber.value;
			var rePhone=/^1[3|4|5|8]{1}[0-9]{9}$/;//手机号验证
			var reTel=/^(0\d{2,3}-)?\d{7,8}$/;//电话验证
			if(!telNumberValue){
				telNumber.focus();
				mui.toast("请输入手机号码");
				return false;
			}
			if(!rePhone.test(telNumberValue)&&!reTel.test(telNumberValue)){
					telNumber.focus();
					mui.toast("您的手机号码格式不对,请重新填写");
					return false;
				}
			else if(telNumberValue.length>15){
				
				mui.toast("手机号码不能超过15位");
				telNumber.focus();
				return false;
			}
			return true;	
		}
//验证省份代码===========================================================================
//验证市代码===========================================================================
//验证区份代码===========================================================================
		function areaTest(){
			var cityId=document.getElementById("cityId").value;
			var provinceId=document.getElementById("provinceId").value;
			var areaId=document.getElementById("areaId").value;
			if(!(provinceId&&cityId&&areaId)){
				mui.toast("请选择建桩地区");
				return false;
			}
			return true;	
		}


//验证具体地址=长度255=========验证详细地址验证=========================================================
		function addrTest(){
			var detailInfo=document.getElementById("detailInfo");
			var detailInfoValue=detailInfo.value;
			var re=/^.*[`!@#$%^&*()=+|\}\[\]{'\";://?.>,<·~！@#￥%……&*（）——+=|、】｝【｛‘“；：、？。》，《]+.*$/;
			if(!detailInfoValue){
				mui.toast("请输入详细地址");
				detailInfo.focus();
				return false;
			}
			if(re.test(detailInfoValue)){
					mui.toast("对不起，详细地址格式不正确，请重新输入！");
					detailInfo.focus();
					return false;
					
				}
			else if(detailInfoValue.length>255){
				mui.toast("填写详细地址，长度不能大于255,请重新输入");
				detailInfo.focus();
				return false;
				
			}
			return true;
		}		
//总验证，若上面条件全部符合，就点击下一页，页面跳转============================================================

//下一步按钮=====================================================
//	页面传值========================================================================
var userId=getUrlParam("userId");
if(!userId){
	userId=-1;
}
/*var aepOrigin=getUrlParam("aepOrigin");
var token=getUrlParam("token");
var pageFlag=true;*/
/*$.ajax({
	  url: getRootPath()+"/itf/applyep/valid",
	  type:"post",
	  dataType: 'json',
	  data: {
		  token:token,
		  aepOrigin:aepOrigin
	  },
	  success: function(datas){
	  	if(datas.status!=100){
	  		pageFlag=false;
	  		//mui.toast("页面参数有误！");
	  		window.location.href="error.html";
	  	}
	  }
	});*/
$("#rightApply").click(function(){
	if(!(userId&&pageFlag)){
		mui.toast("来源信息有误!");
		return;
	}
	//申请类型的值
	var typeInfo=$("#typeInfo").val();
	//分类的值
	var classInfo=$("#classInfo").val();
	//联系人的值        escape()和unescape()方法
	var people= escape($("#people").val());
	//电话
	var telNumber=$("#telNumber").val();
	//建桩地区 escape
	var provinceId=$("#provinceId").val();
	var cityId=$("#cityId").val();
	var areaId=$("#areaId").val();
	//详细信息
	var detailInfo=escape($("#detailInfo").val());
	
	if(peopleTest()&&phoneTest()&&areaTest()&&addrTest()){
		var url="builderInfo.html?typeInfo="+typeInfo+"&classInfo="+classInfo+"&people="+people
		+"&telNumber="+telNumber+"&provinceId="+provinceId+"&cityId="+cityId+"&areaId="+areaId+"&detailInfo="+detailInfo
		+"&userId="+userId+"&aepOrigin="+aepOrigin+"&token="+token;
		window.location.href=url;
	}
})
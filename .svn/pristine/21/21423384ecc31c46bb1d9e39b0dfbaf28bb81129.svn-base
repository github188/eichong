//checkUserLogin();
var user=getUserInfo();
var userId=user.pkUserinfo;
var userAccount=user.usinPhone;
var userAccountbalance =user.usinAccountbalance;
var bespElectricpileid=getSValue("elictricPileId");
//alert(bespElectricpileid)
var bespElectricpilehead=getSValue("pileHeadId");
var pileHeadName=getSValue("pileHeadName");
var raIn_ReservationRate=getSValue("raIn_ReservationRate");//预约单价
raIn_ReservationRate=raIn_ReservationRate?(parseFloat(raIn_ReservationRate)*60).toFixed(2):7.2;
var pkBespoke=getSValue("pkBespoke");//续约ID，无值为预约
var bespBespoketime=getSValue("bespBespoketime");//预约时间
bespBespoketime=bespBespoketime?parseFloat(bespBespoketime):0;
var bespTime=parseFloat(bespBespoketime);


//		显示枪头名称
$("#pileHeadNameText").html(pileHeadName);
//		费率单价
var unitPrice=getUrlParam("unitPrice");
//		显示用户余额
$("#accountBalance").html(userAccountbalance);
//		获取到的电桩id
//var bespElectricpileid=getUrlParam("elictricPicId");
//		获取到的枪头id
//var bespElectricpilehead=getUrlParam("pileHeadId");
if(bespTime>0){
	$("#inline-range").attr("min",bespTime);
	$(".yuyueDiv").html("续约");
}

mui.init({
		swipeBack:true //启用右滑关闭功能
	});
//监听input事件，获取range的value值，也可以直接element.value获取该range的值
var rangeList = document.querySelectorAll('input[type="range"]');
for(var i=0,len=rangeList.length;i<len;i++){
    rangeList[i].addEventListener('input',function(){
        if(this.id.indexOf('field')>=0){
            document.getElementById(this.id+'-input').value = this.value;
        }else{
//	            	alert(0)
        	document.getElementById("importTip").style.display="none";
        	document.getElementById("importTipTime").style.display="block";
            document.getElementById(this.id+'-val').innerHTML = (this.value-bespTime);
            document.getElementById("orderFree").innerHTML = parseFloat((this.value-bespTime)*raIn_ReservationRate).toFixed(2);
            if(this.value==0){
            	document.getElementById("importTip").style.display="block";
            	document.getElementById("importTipTime").style.display="none";
            	document.getElementById("orderBtn").style.background="#CCCCCC";
            	document.getElementById("myModal").style.display="none";
            	
            }else if(this.value>0){
            	document.getElementById("orderBtn").style.background="#FF7D00";
                }
            }
           

        });
    }
function toOrder(){
	var orderFreeValue=document.getElementById("orderFree").innerHTML;
if(orderFreeValue==0){
	mui.toast("请选择预约时间");
		return;
	}	
	toPay();
}
function plusReady(){
}
if(window.plus){
	plusReady();
}else{
	document.addEventListener("plusready",plusReady,false);
}
function finishPayment(){
	$("#payPassValue").blur();
	var payPassword=$("#payPassValue").val();
	loadStart();
var url="/itf/userinfo/checkPayPwd";
$("#payModal").modal('hide');
$.ajax({
	type:"get",
	url:getRootPath()+url,
	async:true,
	dataType:"json",
	data:{
		uid:userId,
		phone:userAccount,
		pwd:payPassword 
	},
	success:function(req){
		
//				alert(JSON.stringify(req));
		if(req.status==100){
			var bespBespoketime=parseFloat($("#inline-range-val").html())*60;
			var beginTime=new Date();
			var endTime=new Date(beginTime.getTime()+bespBespoketime*60000);
			var bespBespoketimes=beginTime.format("hh:mm:ss")+"至"+endTime.format("hh:mm:ss");
			var bespResepaymentcode=userAccount+beginTime.getTime();
			var bespFrozenamt=parseFloat($("#orderFree").html()).toFixed(2);
			$.ajax({
				type:"get",
				url:getRootPath()+"/itf/electric/bespoke",
				async:true,
				dataType:"json",
				data:{
					pkBespoke:pkBespoke,
					bespElectricpileid:bespElectricpileid,
					bespBespoketime:bespBespoketime, 
					bespBespoketimes:bespBespoketimes,
					bespElectricpilehead:bespElectricpilehead,
					bespUserinfo:userId,
					bespResepaymentcode:bespResepaymentcode,
					bespFrozenamt:bespFrozenamt,
					bespBespokeprice:raIn_ReservationRate,
					bespBeginTime:beginTime.format("yyyy-MM-dd hh:mm:ss"),
					bespEndTime:endTime.format("yyyy-MM-dd hh:mm:ss"),
					did:deviceId,
					org:1000,
					payMod:1
				},
				success:function(req){
					if(req.status==100){
						loadStop();
						mui.toast(req.msg);
						setSValue("pkBespoke",req.data);
						setSValue("bespBespoketime",bespTime+parseFloat($("#inline-range-val").html()));
						refreshUserAB();
						toPage("order_detail.html");
						}else{
							$("#waitLoad").hide();
							mui.toast(req.msg);
						}
						
					}
				});
			}else{
				loadStop();
				mui.toast(req.msg);
			}
			
		}
	});
}
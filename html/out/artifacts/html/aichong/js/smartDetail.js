var user=getUserInfo();
var userId=user?user.pkUserinfo:0;
var userAccount=user?user.usinPhone:"";

var powerStationId=getSValue("powerStationId");
var epId=powerStationId;
setSValue("epId",epId);
var deviceType=2;
setSValue("deviceType",deviceType);
var bespElectricpileid="";
//var pkUserinfo=getUrlParam("userId");
var elPi_RelevancePowerStation=getUrlParam("elPi_RelevancePowerStation");
//alert(powerStationId)
var detailInfoUrl="/itf/electric/powerStationDetail";
var arr=null;
$.ajax({
		type:"get",
		url:getRootPath()+detailInfoUrl,
		async:true,
		dataType:"json",
		data:{
//			powerStationId:elPi_RelevancePowerStation,
//			pkUserinfo:userId,
			powerStationId:powerStationId,
			pkUserinfo:userId
		},
		success:function(req){
//			alert(JSON.stringify(req));
			if(req.status==100){
				var data=req.data;
				powerStationId=data[0].powerStationId;
				
				var pileGroup="";
				$(".collectBtn").attr("data-id",data[0].powerStationId);
				if(data[0].isCollect){
					$(".collectBtn").addClass("isCollect").attr("data-collectId",data[0].isCollect);
				}
				//alert(data[0].powerStationState);
					arr=req.data[0].powerElectricpileList;
//					点击纠错跳转纠错页面=============================
					$("body").on("click",".checkError",function(){
						toPage("checkError.html?powerStationId="+data[0].powerStationId);
					});
//					
					Specific(data[0]);
					for(var j=0;j<arr.length;j++){
						drawPile(arr[j],j);
	//				bespElectricpileid=data[0].bespElectricpileid;
					}
					//alert(pileGroup)
			}
		}
	});
//点击收藏添加收藏==============================
$(".collectBtn").on("click",function(){
	var powerStationId=$(this).attr("data-id");
	var collectId=$(this).attr("data-collectId");
	if($(this).hasClass("isCollect")){
		$(this).removeClass("isCollect");
		var collectUrl="/itf/collect/delete";
		$.ajax({
			type:"get",
			url:getRootPath()+collectUrl,
			async:true,
			dataType:"json",
			data:{
				userId:userId,
				favoriteType:2,
				userCollectId:collectId
			},
			success:function(request){
				if(request.status==100){
					//alert(request.msg);
					mui.toast("取消成功");
					}
					
				}
		});
		
	}else{
		$(this).addClass("isCollect");
		var collectBtn=$(".collectBtn");
		var powerStationId=collectBtn.attr("data-id");
		var collectId=collectBtn.attr("data-collectId");
		var isCollect=collectBtn.hasClass("isCollect");
	   		var collectUrl="/itf/collect/add";
			$.ajax({
				type:"get",
				url:getRootPath()+collectUrl,
				async:true,
				dataType:"json",
				data:{
					userId:userId,
					favoriteType:2,
					favoriteTypeId:powerStationId
				},
				success:function(request){
					if(request.status==100){
							mui.toast("收藏成功");
						}
						
					}
			});
		
	}	
});	

//window.onbeforeunload = function(e){
////	mui.back=function(){
//	var collectBtn=$(".collectBtn");
//	var powerStationId=collectBtn.attr("data-id");
//	var collectId=collectBtn.attr("data-collectId");
//	var isCollect=collectBtn.hasClass("isCollect");
// 	if((!collectId)&&isCollect){//收藏
// 		var collectUrl="/itf/collect/add";
//		$.ajax({
//			type:"get",
//			url:getRootPath()+collectUrl,
//			async:true,
//			dataType:"json",
//			data:{
//				userId:userId,
//				favoriteType:2,
//				favoriteTypeId:powerStationId
//			},
//			success:function(request){
//				if(request.status==100){
//					
//					}
//					
//				}
//		});
// 	}else if((!isCollect)&&collectId){//取消收藏
// 		var collectUrl="/itf/collect/delete";
//		$.ajax({
//			type:"get",
//			url:getRootPath()+collectUrl,
//			async:true,
//			dataType:"json",
//			data:{
//				userId:userId,
//				favoriteType:2,
//				userCollectId:collectId
//			},
//			success:function(request){
//				if(request.status==100){
//					//alert(request.msg);
//					}
//					
//				}
//		});
// 	}
//}
	
//画桩按钮列表=============================================================	
function drawPile(pile,index){
	//把费率ID设置到session中
	setSValue("rateId",pile.rateId);
	var elpiChargingmode=pile.elpiChargingmode==14?"慢":"快";
	var html='<li onclick="showPileInfo(this,'+index+','+pile.elictricPicId+')"'
		+'" data-elpiPowersize="'+pile.elpiPowersize
		+'" data-elpiPowerinterface="'+pile.elpiPowerinterface
		+'" data-ownerCompany="'+pile.ownerCompany
		+'" ><span>'+pile.ep_num+'号桩</span>&nbsp;&nbsp;'
			+'<span class="elpiChargingmode">'+elpiChargingmode+'</span></li>';
	$(".pileGroup").append(html);
	showPileInfo("li:eq(0)",0);
}
//显示动态列表的头部=============================================================
function showPileInfo(obj,index,elictricPileId){
	$(obj).addClass("active");
	$(obj).siblings().removeClass("active");
//			功率================================
	var elpiPowersize=$(obj).attr("data-elpiPowersize");
	$("#elpiPowersize").html(elpiPowersize);
//			接口方式================================
	var elpiPowerinterface=$(obj).attr("data-elpiPowerinterface");
	if(elpiPowerinterface==7){
		$("#elpiPowerinterface").html("国标");
	}else if(elpiPowerinterface==19){
		$("#elpiPowerinterface").html("美标");
	}else{
		$("#elpiPowerinterface").html("欧标");
	}
//		  	电桩所属==================================
	var ownerCompany=$(obj).attr("data-ownerCompany");
	if(ownerCompany==1){
		$("#ownerCompany").html("爱充网");
		showInterfaceIcon("ownerCompanyAiChong");
	}else if(ownerCompany==2){
		$("#ownerCompany").html("国网");
		showInterfaceIcon("ownerCompanyState");
	}else if(ownerCompany==3){
		$("#ownerCompany").html("特斯拉");
		showInterfaceIcon("ownerCompanyImg");
	}
	else{
		$("#ownerCompany").html("其他");
		showInterfaceIcon("ownerCompanyOther");
	}
	showPileHeadList(index,elictricPileId);
}
	
function showInterfaceIcon(iconClass){
	$("#ownerCompanyImg").removeClass("ownerCompanyAiChong");
	$("#ownerCompanyImg").removeClass("ownerCompanyState");
	$("#ownerCompanyImg").removeClass("ownerCompanyImg");
	$("#ownerCompanyImg").removeClass("ownerCompanyOther");
	$("#ownerCompanyImg").addClass(iconClass);
}
//	动态添加列表部分=========================================================
function  showPileHeadList(index,elictricPileId){
	setSValue("raIn_ReservationRate",arr[index].raIn_ReservationRate);
//	alert(elictricPileId);
//	alert(JSON.stringify(arr[index]))
	//toPage("bespoke.html?pileHeadId="+pileHeadId);
	var arrPile=arr[index].pileHeadList;
//	电桩连接状态
	var comm_status=arr[index].comm_status;
//	alert(comm_status);
	var dynamicRow='';
	var line1="";
	var line2="";
	var line3="";
	var line4="";
	
	var pileHeadState=0;
	var pileHeadId=0;
	var str1="",str2="",str3="";
	var appointClass="",headStateClass="";
	for(var m=0;m<arrPile.length;m++){
		pileHeadState=arrPile[m].pileHeadState;
		//alert(pileHeadState)
		pileHeadId=arrPile[m].pileHeadId;
		pileHeadName=arrPile[m].pileHeadName;
		
		gunpointName();
		raIn_ReservationRate=arrPile[m].raIn_ReservationRate;
//		alert(arrPile.length);
		if(comm_status==1){
			if(pileHeadState==0){
				str1="空闲中";
				str2="<a href='##' class='Ybtn' data-id='"+pileHeadId+"' data-pileHeadName='"+pileHeadName+"' data-bespElectricpileid='"+elictricPileId+"'>预约</a>";
				appointClass="isAppoint";
				headStateClass="pileHeadState1";
				line1+='<div class="dynamicRow"><div class="col-xs-3 gunpoint">'+pileHeadName
				+'</div><div class="col-xs-3 gunpoint parkNum">'+'车位号:'+arrPile[m].parkNum
				+'</div><div class="col-xs-3 gunpoint '+headStateClass+'">'+str1
				+'</div><div class="col-xs-3 gunpoint '+appointClass+'">'+ str2 
				+'</div></div>';
				
			}else if(pileHeadState==3){
				str1="预约中";
				str2="预约";
				appointClass="noAppoint";
				headStateClass="pileHeadStateGray";
				line2+='<div class="dynamicRow"><div class="col-xs-3 gunpoint">'+pileHeadName
				+'</div><div class="col-xs-3 gunpoint parkNum">'+'车位号:'+arrPile[m].parkNum
				+'</div><div class="col-xs-3 gunpoint '+headStateClass+'">'+str1
				+'</div><div class="col-xs-3 gunpoint '+appointClass+'">'+ str2 
				+'</div></div>';
			}
			else if(pileHeadState==6){
				str1="充电中";
				str2="预约";
				appointClass="noAppoint";
				headStateClass="pileHeadStateGray";
				line3+='<div class="dynamicRow"><div class="col-xs-3 gunpoint">'+pileHeadName
				+'</div><div class="col-xs-3 gunpoint parkNum">'+'车位号:'+arrPile[m].parkNum
				+'</div><div class="col-xs-3 gunpoint '+headStateClass+'">'+str1
				+'</div><div class="col-xs-3 gunpoint '+appointClass+'">'+ str2 
				+'</div></div>';
			}
			else if(pileHeadState==9){
				str1="不在线";
				str2="预约";
				appointClass="noAppoint";
				headStateClass="pileHeadStateGray";
				line4+='<div class="dynamicRow"><div class="col-xs-3 gunpoint">'+pileHeadName
				+'</div><div class="col-xs-3 gunpoint parkNum">'+'车位号:'+arrPile[m].parkNum
				+'</div><div class="col-xs-3 gunpoint '+headStateClass+'">'+str1
				+'</div><div class="col-xs-3 gunpoint '+appointClass+'">'+ str2 
				+'</div></div>';
			}
			
		}else if(comm_status==0){
			if(pileHeadState==0){
				str1="不在线";
				str2="<a href='##' class='Ybtn' data-id='"+pileHeadId+"' data-pileHeadName='"+pileHeadName+"' data-bespElectricpileid='"+elictricPileId+"'>预约</a>";
				appointClass="noAppoint";
				headStateClass="pileHeadStateGray";
				line1+='<div class="dynamicRow"><div class="col-xs-3 gunpoint">'+pileHeadName
				+'</div><div class="col-xs-3 gunpoint parkNum">'+'车位号:'+arrPile[m].parkNum
				+'</div><div class="col-xs-3 gunpoint '+headStateClass+'">'+str1
				+'</div><div class="col-xs-3 gunpoint '+appointClass+'">'+ str2 
				+'</div></div>';
				
			}else if(pileHeadState==3){
				str1="不在线";
				str2="预约";
				appointClass="noAppoint";
				headStateClass="pileHeadStateGray";
				line2+='<div class="dynamicRow"><div class="col-xs-3 gunpoint">'+pileHeadName
				+'</div><div class="col-xs-3 gunpoint parkNum">'+'车位号:'+arrPile[m].parkNum
				+'</div><div class="col-xs-3 gunpoint '+headStateClass+'">'+str1
				+'</div><div class="col-xs-3 gunpoint '+appointClass+'">'+ str2 
				+'</div></div>';
			}
			else if(pileHeadState==6){
				str1="不在线";
				str2="预约";
				appointClass="noAppoint";
				headStateClass="pileHeadStateGray";
				line3+='<div class="dynamicRow"><div class="col-xs-3 gunpoint">'+pileHeadName
				+'</div><div class="col-xs-3 gunpoint parkNum">'+'车位号:'+arrPile[m].parkNum
				+'</div><div class="col-xs-3 gunpoint '+headStateClass+'">'+str1
				+'</div><div class="col-xs-3 gunpoint '+appointClass+'">'+ str2 
				+'</div></div>';
			}
			else if(pileHeadState==9){
				str1="不在线";
				str2="预约";
				appointClass="noAppoint";
				headStateClass="pileHeadStateGray";
				line4+='<div class="dynamicRow"><div class="col-xs-3 gunpoint">'+pileHeadName
				+'</div><div class="col-xs-3 gunpoint parkNum">'+'车位号:'+arrPile[m].parkNum
				+'</div><div class="col-xs-3 gunpoint '+headStateClass+'">'+str1
				+'</div><div class="col-xs-3 gunpoint '+appointClass+'">'+ str2 
				+'</div></div>';
			}
		}
		
	}
	dynamicRow=line1+line2+line3+line4;
	$(".dynamicList").html(dynamicRow);	
}
//枪口名称==========================
function gunpointName(){
	if(pileHeadName=="1号枪头"){
		pileHeadName="枪口A";
	}else if(pileHeadName=="2号枪头"){
		pileHeadName="枪口B";
	}else if(pileHeadName=="3号枪头"){
		pileHeadName="枪口C";
	}
}
//	点击预约按钮，跳转下一个页面=====================================
$("body").on("click",".Ybtn",function(){
	var pileHeadId=$(this).attr("data-id");
	var pileHeadName=$(this).attr("data-pileHeadName");
	var elictricPileId=$(this).attr("data-bespElectricpileid");
	setSValue("pileHeadId",pileHeadId);
	setSValue("pileHeadName",pileHeadName);
	setSValue("elictricPileId",elictricPileId);
	setSValue("pkBespoke","");
	setSValue("bespBespoketime","");
//	alert(elictricPileId);
	toPage("orderPage.html");
});

//细节具体数据处理===============================================================
function Specific(index){
		var pile=index.powerElectricpileList[0];
		$("#unitDegree").html(parseFloat(pile.currentRate)+parseFloat(pile.raIn_ServiceCharge));
		$(".powerStationName").html(index.powerStationName);
//		$("#distance1").html(data[0].distance);
		var distance1=parseInt(index.distance/1000).toFixed(2);
		if(distance1>15){
			$("#distance1").html("未知");
		}else{
			$("#distance1").html(getSValue("distance")+'km');
		}
		$("#zlHeadNum").html(index.zlHeadNum);
		$("#zlFreeHeadNum").html(index.zlFreeHeadNum);
		$("#jlHeadNum").html(index.jlHeadNum);
		$("#jlFreeHeadNum").html(index.jlFreeHeadNum);
		$(".powerStationAddress").html(index.powerStationAddress);
		$("#onlineTime").html(index.onlineTime);
		$("#powerStationTell").html(index.powerStationTell);
		$("#powerCommentStar").html(parseInt(index.powerCommentStar).toFixed(1));
		$("#powerCommentSum").html(index.powerCommentSum);
		setSValue("powerCommentSum",index.powerCommentSum);
		
//若不在线状态下，普通详情处理==========================================
		if(index.powerStationState==10){
			$(".freeCount").hide();
			$(".dynamicList").hide();
		}else if(index.powerStationState){
			$(".freeCount").show();
		}
//处理加载回来的图片=================================
		//$(".unitDegree").html(data[i].powerStationAddress);
		var str=index.powerStationImage; //这是一字符串 
		var strs= new Array(); //定义一数组 
		var strs=str.split(","); //字符分割 
		var newArry=new Array();
		//alert(strs[1]);
		for(var i=0;i<strs.length;i++){
			var a=strs[i].lastIndexOf(".");
			//alert(a);
			var b=strs[i].substring("0",a-1);
			b=b+"_130X100.jpg";
			//alert(b);
			newArry.push(b);
			//alert(newArry.push(strs[i]))
		$(".powerStationImageS").attr("src",newArry[2]);
		}
		
		//alert(index)
}
function toMapIndex(){
	toPage("map_index.html");
}

//去导航==================================
function toNavigate(){
	toPage("navigation.html");
}
function toPowerCommentList(){
   var powerCommentSum=getSValue("powerCommentSum");
	if(powerCommentSum>0){
		toPage("powerStation_evaluateList.html");
	}else{
		return;
	}
}

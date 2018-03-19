var arr=null;
var electricPileId=getSValue("electricPileId");
//alert(electricPileId)
var epId=electricPileId;
setSValue("epId",epId);
var deviceType=1;
setSValue("deviceType",deviceType);
//var electricPileId="13407";
//var pk_ElectricPile=getSValue("pk_ElectricPile");
//var userId=getUrlParam("userId");
var user=getUserInfo();
var userId=user.pkUserinfo;
var userAccount=user.usinPhone;
var detailInfoUrl="/itf/electric/electricPileDetail";
$.ajax({
		type:"get",
		url:getRootPath()+detailInfoUrl,
		async:true,
		dataType:"json",
		data:{
			electricPileId:electricPileId,
//			electricPileId:pk_ElectricPile,
			pkUserinfo:userId,
			longitude:121.360305,
			latitude:31.202912
		},
		success:function(req){
			//alert(JSON.stringify(req));
			if(req.status==100){
				var data=req.data;
				//alert(JSON.stringify(data));
				var pileGroup="";
				$(".collectBtn").attr("data-id",electricPileId);
				if(data[0].isCollect){
					$(".collectBtn").addClass("isCollect").attr("data-collectId",data[0].isCollect);
				}
			//	alert(data[0].electricPowerSize);
					Specific(data[0]);
					arr=req.data[0];
					showHead(arr);
					//alert(JSON.stringify(arr));
					drawPile(data[0],0);
//					点击纠错跳转纠错页面=============================
					$("body").on("click",".checkError",function(){
						toPage("checkError.html");
//						toPage("checkError.html?electricPileId="+electricPileId);
					});
//					
			}
			
		}
});


//点击收藏添加收藏==============================
$(".collectBtn").on("click",function(){
	var electricPileId=$(this).attr("data-id");
	var collectId=$(this).attr("data-collectId");
	var collectBtn=$(".collectBtn");
	var isCollect=collectBtn.hasClass("isCollect");
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
				favoriteType:1,
				userCollectId:collectId
			},
			success:function(request){
				//alert(JSON.stringify(request));
				if(request.status==100){
//						alert(request.msg);
						mui.toast("取消收藏成功");
					}
					
				}
		});
		
	}else{
		$(this).addClass("isCollect");
		var collectUrl="/itf/collect/add";
		$.ajax({
			type:"get",
			url:getRootPath()+collectUrl,
			async:true,
			dataType:"json",
			data:{
				userId:userId,
				favoriteType:1,
				favoriteTypeId:electricPileId
			},
			success:function(request){
				//alert(JSON.stringify(request));
				if(request.status==100){
						mui.toast("恭喜您，收藏成功");
					}
					
				}
		});
	}	
});	

//window.onbeforeunload = function(){
//	var collectBtn=$(".collectBtn");
//	var electricPileId=collectBtn.attr("data-id");
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
//				favoriteType:1,
//				favoriteTypeId:electricPileId
//			},
//			success:function(request){
//				//alert(JSON.stringify(request));
//				if(request.status==100){
//	//						alert(request.msg);
//						mui.toast("恭喜您，收藏成功");
//						alert(collectId);
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
//				favoriteType:1,
//				userCollectId:collectId
//			},
//			success:function(request){
//				//alert(JSON.stringify(request));
//				if(request.status==100){
////						alert(request.msg);
//						mui.toast("取消收藏成功");
//					}
//					
//				}
//		});
// 	}
//}

//画桩按钮列表=============================================================	
	function drawPile(pile,index){
		setSValue("rateId",pile.rateId);
		var electricPileChargingMode=pile.electricPileChargingMode==14?"慢充":"快充";
		var html='<li onclick="showPileInfo(this,'+index+')" data-val = "'+pile.electricPileId
			+'" data-electricPowerSize="'+pile.electricPowerSize
			+'" data-electricPowerInterface="'+pile.electricPowerInterface
			+'" data-ownerCompany="'+pile.ownerCompany
			+'"><span class="electricPileChargingMode">'+electricPileChargingMode+'</span></li>';
		$(".pileGroup").append(html);
		showPileInfo("li:eq(0)",0);
		
	}
//显示动态列表的头部=============================================================
	function showPileInfo(obj,index){
		$(obj).addClass("active");
		$(obj).siblings().removeClass("active");
		showHead(arr);
		showPileHeadList(index);
	}
	function showHead(arr){
//			功率================================
		var electricPowerSize=$(".elpiPowersize").attr("data-electricPowerSize");
		
		$("#electricPowerSize").html(arr.electricPowerSize);
		
//			接口方式================================
		var electricPowerInterface=$(".elpiPowerinterface").attr("data-electricPowerInterface");
		$("#electricPowerInterface").html(arr.electricPowerInterface);
		
		if(electricPowerInterface==7){
			$("#electricPowerInterface").html("国际");
		}else if(electricPowerInterface==19){
			$("#electricPowerInterface").html("美标");
		}else{
			$("#electricPowerInterface").html("欧标");
		}
//		  	电桩所属==================================
		var ownerCompany=$("#ownerCompany").attr("data-ownerCompany");
		$("#ownerCompany").html(arr.ownerCompany);
		var ownerCompany=arr.ownerCompany;
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
			showInterfaceIcon("");
		}
	}
	function showInterfaceIcon(iconClass){
		$("#ownerCompanyImg").removeClass("ownerCompanyAiChong");
		$("#ownerCompanyImg").removeClass("ownerCompanyState");
		$("#ownerCompanyImg").removeClass("ownerCompanyImg");
		$("#ownerCompanyImg").addClass(iconClass);
	}
//	动态添加列表部分=========================================================
	function  showPileHeadList(index){
		//alert(JSON.stringify(arr[index]))
		//toPage("bespoke.html?pileHeadId="+pileHeadId);
//		setSValue("raIn_ReservationRate",arr[index].raIn_ReservationRate);
		var arrPile=arr.pileHeadList;
		var comm_status=arr.comm_status;
//		alert(comm_status);
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
			pileHeadId=arrPile[m].pileHeadId;
			pileHeadName=arrPile[m].pileHeadName;
			gunpointName();
			raIn_ReservationRate=arrPile[m].raIn_ReservationRate;
//			检查电桩状态 是否连接
			if(comm_status==1){
				if(pileHeadState==0){
					str1="空闲中";
					str2="<a href='##' class='Ybtn' data-id='"+pileHeadId+"' data-pileHeadName='"+pileHeadName+"'>预约</a>";
					appointClass="isAppoint";
					headStateClass="pileHeadState1";
					line1='<div class="dynamicRow"><div class="col-xs-3 gunpoint">'+pileHeadName
					+'</div><div class="col-xs-3 gunpoint parkNum">'+'车位号:'+arrPile[m].parkNum
					+'</div><div class="col-xs-3 gunpoint '+headStateClass+'">'+str1
					+'</div><div class="col-xs-3 gunpoint '+appointClass+'">'+ str2 
					+'</div></div>';
				}else if(pileHeadState==3){
					str1="预约中";
					str2="预约";
					appointClass="noAppoint";
					headStateClass="pileHeadStateGray";
					line2='<div class="dynamicRow"><div class="col-xs-3 gunpoint">'+pileHeadName
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
					line3='<div class="dynamicRow"><div class="col-xs-3 gunpoint">'+pileHeadName
					+'</div><div class="col-xs-3 gunpoint parkNum">'+'车位号:'+arrPile[m].parkNum
					+'</div><div class="col-xs-3 gunpoint '+headStateClass+'">'+str1
					+'</div><div class="col-xs-3 gunpoint '+appointClass+'">'+ str2 
					+'</div></div>';
				}
				else if(pileHeadState==9){
					str1="停电中";
					str2="预约";
					appointClass="noAppoint";
					headStateClass="pileHeadStateGray";
					line4='<div class="dynamicRow"><div class="col-xs-3 gunpoint">'+pileHeadName
					+'</div><div class="col-xs-3 gunpoint parkNum">'+'车位号:'+arrPile[m].parkNum
					+'</div><div class="col-xs-3 gunpoint '+headStateClass+'">'+str1
					+'</div><div class="col-xs-3 gunpoint '+appointClass+'">'+ str2 
					+'</div></div>';
				}
			}else if(comm_status==0){
				if(pileHeadState==0){
					str1="不在线";
					str2="<a href='##' class='Ybtn' data-id='"+pileHeadId+"' data-pileHeadName='"+pileHeadName+"'>预约</a>";
					appointClass="noAppoint";
					headStateClass="pileHeadStateGray";
					line1='<div class="dynamicRow"><div class="col-xs-3 gunpoint">'+pileHeadName
					+'</div><div class="col-xs-3 gunpoint parkNum">'+'车位号:'+arrPile[m].parkNum
					+'</div><div class="col-xs-3 gunpoint '+headStateClass+'">'+str1
					+'</div><div class="col-xs-3 gunpoint '+appointClass+'">'+ str2 
					+'</div></div>';
				}else if(pileHeadState==3){
					str1="不在线";
					str2="预约";
					appointClass="noAppoint";
					headStateClass="pileHeadStateGray";
					line2='<div class="dynamicRow"><div class="col-xs-3 gunpoint">'+pileHeadName
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
					line3='<div class="dynamicRow"><div class="col-xs-3 gunpoint">'+pileHeadName
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
					line4='<div class="dynamicRow"><div class="col-xs-3 gunpoint">'+pileHeadName
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
//	枪口状态======
	function gunpointName(){
		if(pileHeadName=="1号枪头"){
			pileHeadName="枪口A";
		}else if(pileHeadName=="2号枪头"){
			pileHeadName="枪口B";
		}else if(pileHeadName=="3号枪头"){
			pileHeadName="枪口C";
		}
	}
	$("body").on("click",".Ybtn",function(){
		var pileHeadId=$(this).attr("data-id");
		var pileHeadName=$(this).attr("data-pileHeadName");
		
		setSValue("pileHeadId",pileHeadId);
		setSValue("pileHeadName",pileHeadName);
		setSValue("elictricPileId",electricPileId);
		setSValue("pkBespoke","");
		setSValue("bespBespoketime","");
		toPage("orderPage.html");
	});
	
function Specific(num){
	$("#electricPileName").html(num.electricPileName);
//	$("#distance1").html(data[0].distance);
	var distance=parseInt(num.distance/1000).toFixed(2);
//	if(distance>15){
//		$("#distance").html("未知");
//	}else{
		$("#distance").html(getSValue("distance"));
//		$("#distance").html(distance);
//	}
	//====================判断提示按钮显示的状态=======================================
	var electricPileChargingMode=num.electricPileChargingMode;
	if(electricPileChargingMode==14){
		$(".text").html("慢充");
		$(".typeNum").attr("id","jlHeadNum");
		$("#jlHeadNum").html(num.jlHeadNum);
		$(".FreeHeadNum").attr("id","jlFreeHeadNum");
		$("#jlFreeHeadNum").html(num.jlFreeHeadNum);
		//alert(data[0].jlHeadNum)
	}else{
		$(".text").html("快充");
		$(".typeNum").attr("id","zlHeadNum");
		$("#zlHeadNum").html(num.zlHeadNum);
		$(".FreeHeadNum").attr("id","zlFreeHeadNum");
		$("#zlFreeHeadNum").html(num.zlFreeHeadNum);
	}
//===============================================================================					
		$("#electricPileAdress").html(num.electricPileAdress);
		
		$("#onlineTime").html(num.onlineTime);
		$("#electricPileTell").html(num.electricPileTell);
		
		$("#electricPileCommentStar").html(parseInt(num.electricPileCommentStar).toFixed(1));
		$("#electricPileCommentSum").html(num.electricPileCommentSum);
		setSValue("electricPileCommentSum",num.electricPileCommentSum);
//若不在线状态下，普通详情处理==========================================
		if(num.electricPileState==10){
			$(".freeCount").hide();
		}else if(num.electricPileState){
			$(".freeCount").show();
		}		
		//alert(JSON.stringify(arr));
//		showPileInfo(data[0],0);
		//alert(pileGroup)
		
		//处理加载回来的图片=================================
		
		
		var str=num.electricPileImage; //这是一字符串 
		var strs= new Array(); //定义一数组 
		var strs=str.split(",");
		var newArry=new Array();
		//alert(strs[1]);
		for(var i=0;i<strs.length;i++){
			var a=strs[i].lastIndexOf(".");
			//alert(a);
			var b=strs[i].substring("0",a-1);
			b=b+"_130X100.jpg";
			//alert(b);
			newArry.push(b);
			$(".powerStationImageS").attr("src",newArry[1]);
		
		}
	
		
}
//返回按钮去首页
function toMapIndex(){
	toPage("map_index.html");
}
//去导航==================================
function toNavigate(){
	toPage("navigation.html");
}
//去列表
function toPowerCommentList(){
   var powerCommentSum=getSValue("powerCommentSum");
	if(powerCommentSum>0){
		toPage("powerStation_evaluateList.html");
	}else{
		return;
	}
}
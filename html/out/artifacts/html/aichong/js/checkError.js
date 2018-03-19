checkUserLogin();
var user=getUserInfo();
var userId=user.pkUserinfo;
var userAccount=user.usinPhone;


var epId=getSValue("epId");
var deviceType=getSValue("deviceType");

var eqreWarrantytypeid=null;
$(".errorList").on("click","li",function(){
	var flag=$(this).hasClass("addBg");
	$("#detailTip").hide();	
	clearBgStyle();
	if(flag){
		eqreWarrantytypeid=null;
		
	}else{
		$(this).addClass("addBg");
		eqreWarrantytypeid=$(this).attr("data-val");
		//alert(eqreWarrantytypeid);
	}
	
})
function clearBgStyle(){
	$(".errorList li").removeClass("addBg");
}
var url="/itf/electric/paraconfig";
$.ajax({
	type:"get",
	url:getRootPath()+url,
	async:true,
	dataType:"json",
	data:{
		cpId:29
	},
	success:function(req){
		//alert(JSON.stringify(req));
		if(req.status==100){
			//alert(req.data);
			var data=req.data;
			var html="";
			for(var i=0;i<data.length;i++){
				 
				 if(data[i].coCo_Content=="其他"){
				 	 html+='<li data-val="'+data[i].pk_ConfigContent+'" class="other">'+data[i].coCo_Content+'</li>';
				 }else{
				 	html+='<li data-val="'+data[i].pk_ConfigContent+'">'+data[i].coCo_Content+'</li>';
				 }
			}
			$(".errorList").html(html);
			//mui.toast("提交成功");
		}
		
	}
});

$("#errorList").on("click",".other",function(){
	var flag=$(this).hasClass("addBg");
	if(flag){
		$("#detailTip").show();
	}else{
		$("#detailTip").hide();		
	}
})
$("#submitBtn").click(function(){
	//alert(userId+"::"+eqreWarrantytypeid+"::"+epId+"::"+deviceType+"::"+textDetail)
	var textDetail=$(".textDetail").val();
	
	var repairUrl="/itf/electric/addRepair";
	$.ajax({
			type:"get",
			url:getRootPath()+repairUrl,
			async:true,
			dataType:"json",
			data:{
				eqreUserid:userId,
				eqreWarrantytypeid:eqreWarrantytypeid,
				epId:epId,
				deviceType:deviceType,
				eqreContent:textDetail
			},
			success:function(req){
//				alert(JSON.stringify(req));
				if(req.status==100){
					//alert(req.msg);
//					alert(textDetail);
//					mui.toast("提交成功");
					$("#myModal").modal('show');
				}
				
			}
		});
})
$("#certain").on("click",function(){
	$("#myModal").modal('hide');
	toHistory();
})

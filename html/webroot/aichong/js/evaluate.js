var epId=getSValue("PK_ElectricPile");
var user=getUserInfo();
var userId=user.pkUserinfo;

var starList=$("#starList");
var starLi=$("#starList li");
var result=$("#result");
var starValue="";
var textDetail="";
starLi.click(function(){
	var index=$(this).index();
	var firstActiveFlag=$(this).hasClass("active")&&$("#result").val()==1;
	//alert(firstActiveFlag);
	//$(this).addClass("active").prevAll().addClass("active").nextAll().removeClass("active");
	$(this).addClass("active").prevAll().addClass("active");
	$(this).nextAll().removeClass("active");
	if(!firstActiveFlag){
		$("#result").val(index+1);
	}else{
		$("#result").val(0);
		$(this).removeClass("active");
	}
	starValue=$("#result").val();//拿到的几颗星的值
	
})
//alert(starValue);
textDetail=$(".textDetail").val();
function evaluate(){
	var url="/itf/electric/epStar";
	$.ajax({
		type:"get",
		url:getRootPath()+url,
		async:true,
		dataType:"json",
		data:{
			epId:epId,
			uId:userId,
//			uName:54,
			epStar:$("#result").val()
		},
		success:function(req){
			//alert(JSON.stringify(req));
			if(req.status==100){
				$("#myModal").modal('show');//alert(req.data);
			}
			
			}
		});
		var Url="/itf/electric/addEpComment";
		$.ajax({
				type:"get",
				url:getRootPath()+Url,
				async:true,
				dataType:"json",
				data:{
					epId:epId,
					uId:userId,
					uName:54,
					pcId:0,
					epContent:textDetail,
					type:1
				},
				success:function(req){
		//				alert(JSON.stringify(req));
					if(req.status==100){
//						alert(req.msg);
						$("#myModal").modal('show');
					}
					
				}
			});
}
$("#submitBtn").click(function(){
	textDetail=$(".textDetail").val();
	starValue=$("#result").val();//拿到的几颗星的值
	if(starValue!=0&&textDetail){
		evaluate();
	}else if(starValue==""&&textDetail==""){
		alert("星评和文字评论均为空，不能提交");
	}else if(starValue&&textDetail==""){
		alert("请输入评论");
	}else if(starValue==""&&textDetail){
		alert("请选择星评");
	}
})
//点击确定之后回到首页======
$("#certain").on("click",function(){
	toPage("map_index.html");
})




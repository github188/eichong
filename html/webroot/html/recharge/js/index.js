init();
function init() {
	
	$.ajax({
		url : getRootPath() + "/itf/charge/init",
		type : "get",
		dataType : 'json',
		success : function(datas) {
			if (datas.status == 100) {
				$("#mainDiv").show();
			}else{
				$("body").html("");
				parent.layer.alert(datas.msg);
			}
		}

	});
}

$("#submitButton").click(function(){
	var code=$("#code").val();
	if(!code){
		parent.layer.alert("你想做啥嘞");
		return;
	}
	$.ajax({
		url : getRootPath() + "/itf/charge/valid",
		type : "get",
		data:{
			code:code
		},
		dataType : 'json',
		success : function(datas) {
			if (datas.status == 100) {
				setSValue("code",code);
				window.location.href = "main.html";
			}else{
				parent.layer.alert(datas.msg);
			}
		}

	});
	
});

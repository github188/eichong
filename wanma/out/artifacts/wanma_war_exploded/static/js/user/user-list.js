//获取车型
	function getCarTypeName(obj,selectId){
		if(obj.value==""){
			return;
		}
		 $.ajax({
				type:'POST', 
				url:basepath+"/admin/carinfo/searchCarinfoList.do", 
				data: {companyId:obj.value},
				dataType:'json',
				cache: false,
				success: function(datas) {
					if(datas.status==100){
						var data=datas.data;
						var content='<option value="">--请选择车型--</option>';
						for(var i=0;i<data.length;i++){
							content+='<option value="'+data[i].pkCarinfo+'">'+data[i].carinfoStylename+'</option>';
						}
						 $("#"+selectId).html(content);
					}else{
						alertMsg.error(datas.msg);
					}
					
	            }
			});
	}
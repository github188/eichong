var userImgUrl;
var myName;
var myCenterLinkPkCommentId = $("#myCenterLinkPkCommentId").val();
$(document).ready(function() { 	
	 var tip=$(".tip").attr("tip-value");
	 if(tip==2){
		 if(myCenterLinkPkCommentId != ''){
			 //如果是个人中心链接过来，定位评论信息
			 var linkCommentsRowNum = parseInt($("#myCenterLinkPkCommentId").attr("data-rowNum"));
			 loadStationComment(Math.ceil(linkCommentsRowNum/10) ,10);
		 }else{
			 loadStationComment(1,10);
		 }
		 zhanxingping();
	 }else if(tip==1){
		 if(myCenterLinkPkCommentId != ''){
			 //如果是个人中心链接过来，定位评论信息
			 var linkCommentsRowNum = parseInt($("#myCenterLinkPkCommentId").attr("data-rowNum"));
			 loadPileComment(Math.ceil(linkCommentsRowNum/10) ,10);
		 }else{
		     loadPileComment(1,10);
		 }
	     zhuangxingping();
     }
	 userImgUrl = $('#userImgDiv').attr('src')
	 myName = $('#userNameDiv').html();
 }); 
	
	$(".yuyue").click(function(){
		var userid=UserService.getUserId();
		if(!userid){
	        $("#login").click();
	        return;
	    }else{
	    	//用户ID
	    	$("#bespUserinfo").val(userid);
	    	//流水号
	    	$("#bespResepaymentcode").val(""+userid+parseInt(new Date().getTime()/1000));
	    }
		var obj=this;
		var dataPileId=$(this).parent().attr("data-pile-id");
		var dataId=$(this).parent().attr("data-id");
		var dataName=$(this).parent().attr("data-name");
		$("#bespElectricpileid").val(dataPileId);
		$("#bespElectricpilehead").val(dataId);
		$.ajax({
	        type: 'POST',
	        url: config.appInterfaceServer+"/web/common/sendRequest.do",
	        dataType: "json",
	        data:{
	        	requestType:1,
	        	pkElectricpilehead:dataId,
	        	pkElectricPile:dataPileId
	        	},
	        success: function(datas){ 
	       	 if(datas.status=="100"){
	       		var data=datas.data;
	       		var dataStatus=data.ePHeElectricpileHeadState;
	    		if(dataStatus=="0"){
	    			$("#pileHeadStatus").html("空闲中");
	    			$(obj).parent().attr("data-rate",data.unitPrice);
	    			$("#bespBespokeprice").val(data.unitPrice);
	    		}else{
	    			showInfo("正在使用中，无法预约！","error");
	    			return;
	    		}
	    		initSlider(obj);
	    		$("#pileHeadName").html(dataName);
	    		//showInfo("预约"+dataId);
	    		//弹窗
	    		$("#yuyueWindow").show();
	    		showZZ();
	    		//刷新时间
	    		refreshTime();
	    		setInterval(refreshTime,1000); //每隔一秒执行一次 
	       	 }else{
	       		 showInfo(datas.msg,"error");
	       	 }
	       	 
	        }
	   });
		
	});
	$("#closeYuyue").click(function(){
		$("#yuyueWindow").hide();
		closeZZ();
	});
	
	$(".tongji_ke").click(function(){
		var dataId=$(this).attr("data-id");
		showInfo("该功能暂未实现:充电统计"+dataId,"error");
	});
	
	
	$("#submitBesp").click(function(){
		var date=new Date()
		var bespBeginTime=DateUtil.dateToStr(date);
		$("#bespBeginTime").val(bespBeginTime);
		var bespTime=$("#yuyueTime").html();
		if(bespTime=="0"){
			showInfo("预约时间不能为0","error");
			return;
		}
		$("#bespBespoketime").val(bespTime*60);
		var endTime=new Date(date.getTime()+bespTime*3600000);
		var bespEndTime=DateUtil.dateToStr(endTime);
		$("#bespEndTime").val(bespEndTime);
		//预约单价
		var unitPrice=$("#bespBespokeprice").val();
		$("#bespBespokeprice").val(unitPrice);
		//冻结资金
		var price=$( "#yuyuePrice" ).html();
		$("#bespFrozenamt").val(price);
		var obj=$("#bespForm").serializeObject();
		obj.requestType=2;
		//请求预约操作
		$.ajax({
	        type: 'post',
	        url: config.appInterfaceServer+"/web/common/sendRequest.do?requestType=2",
	        data:$.param(obj),
	        dataType: "json",
	        success: function(data){     	
	       	 if(data.status=="100"){
	       		 showInfo(data.msg);
	       		 $("#yuyueWindow").hide();
	    		 closeZZ();
	    		 location.href=location.href;
	       	 }else{
	       		 showInfo(data.msg,"error");
	       	 }
	        }
	   });
	});
	
	$('#fenxiangDiv').mouseover(function(){
		$("#bshare-custom_div").show();
	})
	
	$('#fenxiangDiv').mouseout(function(){
		$("#bshare-custom_div").hide();
	})
	
	$('#bshare-custom_div').mouseover(function(){
		$("#bshare-custom_div").show();
	})
	
	$('#bshare-custom_div').mouseout(function(){
		$("#bshare-custom_div").hide();
	})
	
	/**
	 * 新增收藏
	 */
	$('.collect_submit').click(function(){
		if($(this).attr("data-value")>0){
			showInfo("已收藏","warn",1000);
			return;
		}
		var userid=UserService.getUserId();
		if(!userid){
	        $("#login").click();
	        return;
	    }
	    Ajax.custom({
	        url:config.ICollect,
	        data:{
	        	favoriteType:$(this).attr("data-type"),
	        	favoriteTypeId : $(this).attr("data-id"),
	        	userId : userid
	        },
            type: 'POST'
	    },function(res){
	    	if(res.code=="OK"){
	    		showInfo("收藏成功");
	    		$("#shouchangDiv").html('已收藏');
	    		//$("#collectImg").attr("src",basepath+"/static/images/prettyPhoto/shoucang_h.jpg");
	    		//$(".collect_submit").attr("data-value",1);
	    	}else{
	    		showInfo(res.msg,"error");
	    	}
	    	
	    });
	});
	var initSlider=function(obj){
		//预约单价(小时)
		var reservationRate=parseFloat($("#bespBespokeprice").val())*60;
		$("#jslider").slider({
			range: "max",
			min: 0,
			max: 12,
			value: 0,
			slide: function( event, ui ) {
				var time=parseInt(ui.value)/2;
				var price=(time*reservationRate).toFixed(2);
				$("#yuyueTime").html(time);
				$( "#yuyuePrice").html(price);
			}
		});
		$( "#yuyueTime" ).html(0);
		$( "#yuyuePrice" ).html(0);
	}	
	//取得系统当前时间 
	var refreshTime=function(){ 
		var myDate = new Date(); 
		var date = myDate.toLocaleDateString(); 
		var hours = myDate.getHours()<10?"0"+myDate.getHours():myDate.getHours();  
		var minutes = myDate.getMinutes()<10?"0"+myDate.getMinutes():myDate.getMinutes(); 
		var seconds = myDate.getSeconds()<10?"0"+myDate.getSeconds():myDate.getSeconds(); 
		$("#showTime").html(hours+":"+minutes+":"+seconds); //将值赋给div 
	}
	  
	  //加载电站评论
	  function loadStationComment(pageNum,pageSize){
			var powerStationId = $("#powerStationId").val();
		  	$.ajax({
		        type: 'POST',
		        url: basepath+"/web/psComment/findPsComments.do?prCoProductId="+powerStationId,
		        dataType: "json",
		        data:initPageParams(pageNum,pageSize),
		        success: function(datas){
		       	 if(datas.pager.code=="OK"){
		       		 var data=datas.pager.data;
		       		 var replyData = datas.replyData;
		       		 var comments ="";
		       		 var k=0;
		       		 for(var i =0;i<data.length;i++){
		       			 var replyComment = "";
		       			 var commentId=data[i].pk_PsComment;
		       			 for(var j=k;j<replyData.length;j++){
		       				 if(replyData[j].up_commentId == commentId){
		       					//获取回复信息
		       					replyComment +='<div id="centerLinkReplyDiv'+replyData[j].pk_PsComment+'" class="PLleft PL-HF"><img src="'+replyData[j].userImage+'" onerror="javascript:this.src=\''+basepath+'/static/images/user/2.png\'" width="80" height="80" /></div>'+
		       					'<div class="PLright PL-HFRight">'+
		       			        '<dl class="PL">'+
		       			        '<dt>'+(replyData[j].psc_UserName?replyData[j].psc_UserName:'爱充网用户')+'</dt>'+
		       			        '<dd class="PLContent">'+replyData[j].psc_Content+'</dd>'+
		       			        '<dd><span class="time">'+new Date(parseInt(replyData[j].psc_Createdate)).toLocaleString()+'</span></dd>'+
		       			        '</dl>'+
		       			        '</div> <div style="clear:both;"></div>';
		       					k = j;
		       				 }else{
		       					 continue;
		       				 }
		       			 }
		       			 
		       			//回复对话框
		       			var userImageHtml = "";
		       			if(userImgUrl != ""){
		       				userImageHtml = '<img class="userImg" src="'+userImgUrl+'" width="27" height="27" alt=" " />'
		       			}else{
		       				userImageHtml = '<img class="userImg" style="display:none" width="27" height="27" alt=" " />'
		       			}
		       			var commentsDiv = '<div id="zhuangreplyDiv'+commentId+'" data_reply_count="'+data[i].reply_count+'" class="BoxInput" style="display:none"><ul>'+
		                '<li class="IconStyle"><a href="javascript://"><img src="'+basepath+'/static/images/img/face.png" width="27" height="27" alt=" " /></a></li>'+
		                '<li class="InputOn"><textarea maxlength="200" id="reply-neirong'+commentId+'" onclick="clearTxt(this)" cols="" rows="">我也说两句</textarea></li>'+
		                '<li class="user"><input name="button" onclick=saveStationReply('+commentId+','+data[i].psc_PsId+') type="button" value="提交" /><input name="button" onclick="closeStationReplyWindow('+commentId+')" type="button" value="关闭" />'+
		                '<span class="userNav">'+userImageHtml+'<t class="userName">'+myName+'</t></span></li>'+
		                '</ul></div>';
		       			
		       			//评论内容
				       	 comments +='<div class="PLleft"><img src="'+data[i].userImage+'" onerror="javascript:this.src=\''+basepath+'/static/images/user/2.png\'" width="80" height="80" /></div>'+
			       			'<div class="PLright">'+
			       	        '<dl class="PL">'+
			       	        '<dt>'+(data[i].psc_UserName?data[i].psc_UserName:'爱充网用户')+'</dt>'+
			       	        '<dd class="PLContent">'+data[i].psc_Content+'</dd>'+
			       	        '<dd><span class="time">'+new Date(parseInt(data[i].psc_Createdate)).toLocaleString()+
							'</span><span id="praiseDiv'+commentId+'" class="dianzhan"><a href="javascript://" onclick=stationPraise('+commentId+','+data[i].praise_count+')></a>点赞('+data[i].praise_count+') </span><span id="replyDiv'+commentId+'"  class="huifu"><a href="javascript://" onclick="showStationReplyWindow('+commentId+')"></a> 回复('+data[i].reply_count+')</span></dd>'+
			       	        '</dl>'+
			       	        '</div> <div style="clear:both;"></div>'+commentsDiv+'<div id="replyDetailDiv'+commentId+'">'+replyComment+'</div>';
				       	 
		       		 }
		       		$("#allComment").html('（共有'+datas.pager.countData+'条评论）');
		       		$("#tag").html(comments);
		       		$("#page-dom").show();		       		
		       		initPage(datas.pager,"loadStationComment");
		       		
		       		//个人中心链接定位
		       		if(myCenterLinkPkCommentId != ''){
			       		moveToCommentPosition();
		       		}
		       		
			       	//URL重构
		       	    initTwoDicodeImg();
		       	 }else{
		       		 alert(datas.pager.msg);
		       	 }
		       	 
		        }
		   });
			  
		  }
	  
	  function showStationReplyWindow(commentId){
		  $('#zhuangreplyDiv'+commentId).show();
	  }
	  
	  function closeStationReplyWindow(commentId){
		  $('#zhuangreplyDiv'+commentId).hide();
		  $('#reply-neirong'+commentId).val('')
	  }
	  
	  //站评论点赞
	  var praiseFlag=0;
	  function stationPraise(commentId,praiseCount){
		  var data_user = UserService.getUserId();
		  if(!data_user){
		        $("#login").click();
		        return;
		    }
		  //控制重复点赞
		  if(praiseFlag != commentId){
			  praiseFlag = commentId;
	    	   var newPraiseCount=praiseCount+1;
			  $.ajax({
			       type: 'POST',
			       url: basepath+"/web/psComment/praise.do",
			       dataType: "json",
			       data:{
			        	"userId":data_user,
			        	"commentId":commentId,
			        	"praiseCount":newPraiseCount
			       },
			       success: function(datas){
			    	   if(datas.code == 'FAILED'){
			    		   showInfo("您已经点过赞了，亲！","warn");
			    	   }else{
				    	   $("#praiseDiv"+commentId).html('<a href="javascript://" onclick=stationPraise('+commentId+','+newPraiseCount+')></a>点赞('+newPraiseCount+')')
			    	   }
			       }
			  });
		  }else{
			  showInfo('您已经点过赞了，亲！')
		  }
	  }
	  
	  //保存站评论回复
	  function saveStationReply(commentId,psId){
		  var user=UserService.getUser();
		  if(!user){
		        $("#login").click();
		        return;
		    }
		  var reply_count = parseInt($('#zhuangreplyDiv'+commentId).attr('data_reply_count'));
		  var newReply_count = reply_count+1;
		  var psContent = $('#reply-neirong'+commentId).val();
		  if(psContent == "" || psContent == "我也说两句"){
			  showInfo("评论内容不能为空","warn");
 			  return;
		  }
		  psContent =  psContent.replace('<','《').replace('>','》')
		  $.ajax({
		       type: 'POST',
		       url: basepath+"/web/psComment/insertReply.do",
		       dataType: "json",
		       data:{
		        	"uId":user.userId,
		        	"uName":user.normName,
		        	"prCoProductId":psId,
		        	"upCommentId":commentId,
		        	"replyCount":newReply_count,
		        	"psContent":psContent
		       },
		       success: function(datas){			        
		        	if(datas.code=="OK"){
				    	   var newContent = datas.data;
				    	   $("#replyDiv"+commentId).html('<a href="javascript://" onclick="showStationReplyWindow('+commentId+')"></a>回复('+newReply_count+')')
				    	   $('#reply-neirong'+commentId).val("")
				    	   //回复对话框，点击事件回复数量参数变更
				    	   $('#zhuangreplyDiv'+commentId).attr('data_reply_count',newReply_count);
				    	   
		  			       //动态增加回复信息
				    	   userImgUrl = $('#userImgDiv').attr('src')
				    	   $('#replyDetailDiv'+commentId).prepend('<div class="PLleft PL-HF"><img src="'+userImgUrl+'" onerror="javascript:this.src=\''+basepath+'/static/images/user/2.png\'" width="80" height="80" /></div>'+
				  					'<div class="PLright PL-HFRight">'+
				  			        '<dl class="PL">'+
				  			        '<dt>'+user.normName+'</dt>'+
				  			        '<dd class="PLContent">'+newContent+'</dd>'+
				  			        '<dd><span class="time">'+new Date().toLocaleString()+'</span></dd>'+
				  			        '</dl>'+
				  			        '</div> <div style="clear:both;"></div>');			        	
		        	}else{
		        		showInfo(datas.msg,"error");
		        	}
		       }
		  });
	  }
	  
	  
	  function clearTxt(this_e){
		  $(this_e).html('')
	  }
	  
	  $("#zhanpingfen-tijiao").click(function(){
		  var user=UserService.getUser();
		  if(!user){
		        $("#login").click();
		        return;
		    }
		  var  powerStationContent = $("#pingfen-neirong").val().trim();
		  if(powerStationContent=="" || powerStationContent=="我也说两句"){
			  showInfo("评论内容不能为空","warn");
			  return;
		  }
		  powerStationContent =  powerStationContent.replace('<','《').replace('>','》')
		  var  powerStationId = $("#powerStationId").val();
		  	$.ajax({
		        type: 'post',
		        url: basepath+"/web/psComment/insertPsCommnet.do",
		        dataType: "json",
		        data:{
		          psc_PsId:powerStationId,
		          psc_Content:powerStationContent,
		  		  psc_UserId:user.userId,
		  		  psc_UserName:user.normName
		        },
		        success: function(datas){
		        	if(datas.code=="OK"){
		        	$("#pingfen-neirong").val('');
		  		  	addNewStationComment(user,datas.data);		        
	        	}else{
	        		showInfo(datas.msg,"error");
	        	} 		       	        	
		        }
		   });
	  });

	  //动态增加站主评论
	  function addNewStationComment(user,data){
		  var datas = data.split("|||");
		   userImgUrl = $('#userImgDiv').attr('src')
		   var commentsDiv = '<div id="zhuangreplyDiv'+datas[1]+'" data_reply_count="0" class="BoxInput" style="display:none"><ul>'+
		                '<li class="IconStyle"><a href="javascript://"><img src="'+basepath+'/static/images/img/face.png" width="27" height="27" alt=" " /></a></li>'+
		                '<li class="InputOn"><textarea maxlength="200" id="reply-neirong'+datas[1]+'" onclick="clearTxt(this)" cols="" rows="">我也说两句</textarea></li>'+
		                '<li class="user"><input name="button" onclick=saveStationReply('+datas[1]+','+datas[2]+') type="button" value="提交" /><input name="button" onclick="closeStationReplyWindow('+datas[1]+')" type="button" value="关闭" />'+
		                '<span class="userNav"><img class="userImg" src="'+userImgUrl+'" width="27" height="27" alt=" " /><t class="userName">'+user.normName+'</t></span></li>'+
		                '</ul></div>';
		   $("#tag").prepend('<div class="PLleft"><img src="'+userImgUrl+'" onerror="javascript:this.src=\''+basepath+'/static/images/user/2.png\'" width="80" height="80" /></div>'+
	       			'<div class="PLright">'+
	       	        '<dl class="PL">'+
	       	        '<dt>'+user.normName+'</dt>'+
	       	        '<dd class="PLContent">'+datas[0]+'</dd>'+
	       	        '<dd><span class="time">'+new Date().toLocaleString()+
					'</span><span id="praiseDiv'+datas[1]+'" class="dianzhan"><a href="javascript://" onclick=stationPraise('+datas[1]+',0)></a>点赞(0) </span><span id="replyDiv'+datas[1]+'"  class="huifu"><a href="javascript://" onclick="showStationReplyWindow('+datas[1]+')"></a> 回复(0)</span></dd>'+
	       	        '</dl>'+
	       	        '</div> <div style="clear:both;"></div>'+commentsDiv+'<div id="replyDetailDiv'+datas[1]+'"></div>')
	     	 var allComment = $("#allComment").html();
	     	 var allCommentTxtlength = allComment.length;
	     	 var oldCount = allComment.substring(3,allCommentTxtlength-4);
			 $("#allComment").html('（共有'+(parseInt(oldCount)+1)+'条评论）');
	  }
	  
	  //电桩评论加载
	  function loadPileComment(pageNum,pageSize){
				var electricPileId = $("#electricPileId").val();
			  	$.ajax({
			        type: 'POST',
			        url: basepath+"/web/epComment/findEpComments.do?prCoProductId="+electricPileId,
			        dataType: "json",
			        data:initPageParams(pageNum,pageSize),
			        success: function(datas){
				       	 if(datas.pager.code=="OK"){
				       		 var data=datas.pager.data;
				       		 var replyData = datas.replyData;
				       		 var comments ="";
				       		 var k=0;
				       		 for(var i =0;i<data.length;i++){
				       			 var replyComment = "";
				       			 var commentId=data[i].pk_EpcComment;
				       			 for(var j=k;j<replyData.length;j++){
				       				 if(replyData[j].up_commentId == commentId){
				       					//生成回复信息
				       					replyComment +='<div id="centerLinkReplyDiv'+replyData[j].pk_EpcComment+'" class="PLleft PL-HF"><img src="'+replyData[j].userImage+'" onerror="javascript:this.src=\''+basepath+'/static/images/user/2.png\'" width="80" height="80" /></div>'+
				       					'<div class="PLright PL-HFRight">'+
				       			        '<dl class="PL">'+
				       			        '<dt>'+(replyData[j].epc_UserName?replyData[j].epc_UserName:'爱充网用户')+'</dt>'+
				       			        '<dd class="PLContent">'+replyData[j].epc_Content+'</dd>'+
				       			        '<dd><span class="time">'+new Date(parseInt(replyData[j].epc_Createdate)).toLocaleString()+'</span></dd>'+
				       			        '</dl>'+
				       			        '</div> <div style="clear:both;"></div>';
				       					k = j;
				       				 }else{
				       					 continue;
				       				 }
				       			 }
				       			 
				       			//回复对话框
					       		var userImageHtml = "";
				       			if(userImgUrl != ""){
				       				userImageHtml = '<img class="userImg" src="'+userImgUrl+'" width="27" height="27" alt=" " />'
				       			}else{
				       				userImageHtml = '<img class="userImg" style="display:none" width="27" height="27" alt=" " />'
				       			}
				       			var commentsDiv = '<div id="zhuangreplyDiv'+commentId+'" data_reply_count="'+data[i].reply_count+'" class="BoxInput" style="display:none"><ul>'+
				                '<li class="IconStyle"><img src="'+basepath+'/static/images/img/face.png" width="27" height="27" alt=" " /></a></li>'+
				                '<li class="InputOn"><textarea maxlength="200" id="reply-neirong'+commentId+'" onclick="clearTxt(this)" cols="" rows="">我也说两句</textarea></li>'+
				                '<li class="user"><input name="button" onclick="saveEpReply('+commentId+','+data[i].epc_EpId+')" type="button" value="提交" /><input name="button" onclick="closeStationReplyWindow('+commentId+')" type="button" value="关闭" />'+
				                '<span class="userNav">'+userImageHtml+'<t class="userName">'+myName+'</t></span></li>'+
				                '</ul></div>';
				       			 
				       			 //评论内容
						       	 comments +='<div class="PLleft"><img src="'+data[i].userImage+'" onerror="javascript:this.src=\''+basepath+'/static/images/user/2.png\'" width="80" height="80" /></div>'+
					       			'<div class="PLright">'+
					       	        '<dl class="PL">'+
					       	        '<dt>'+(data[i].epc_UserName?data[i].epc_UserName:'爱充网用户')+'</dt>'+
					       	        '<dd class="PLContent">'+data[i].epc_Content+'</dd>'+
					       	        '<dd><span class="time">'+new Date(parseInt(data[i].epc_Createdate)).toLocaleString()+
									'</span><span id="praiseDiv'+commentId+'" class="dianzhan"><a href="javascript://" onclick=eLPraise('+commentId+','+data[i].praise_count+')></a>点赞('+data[i].praise_count+') </span><span id="replyDiv'+commentId+'"  class="huifu"><a href="javascript://" onclick="showStationReplyWindow('+commentId+')"></a> 回复('+data[i].reply_count+')</span></dd>'+
					       	        '</dl>'+
					       	        '</div> <div style="clear:both;"></div>'+commentsDiv+'<div id="replyDetailDiv'+commentId+'">'+replyComment+'</div>';
				       		 }
				       		$("#allComment").html('（共有'+datas.pager.countData+'条评论）');
				       		$("#tag").html(comments);
				       		$("#page-dom").show();		       		
				       		initPage(datas.pager,"loadPileComment");
				       		
				       		//个人中心链接定位
				       		if(myCenterLinkPkCommentId != ''){
					       		moveToCommentPosition();
				       		}
				       		
				       		//URL重构
					       	initTwoDicodeImg();
				       	 }else{
				       		 alert(datas.pager.msg);
				       	 }
			       	 
			        }
			   });
				  
			  }
	  
	  //桩点赞
	  var eLPraiseFlag=0;
	  function eLPraise(commentId,praiseCount){
		  var data_user = UserService.getUserId();
		  if(!data_user){
		        $("#login").click();
		        return;
		    }
		  //控制重复点赞
		  if(eLPraiseFlag != commentId){
			  eLPraiseFlag = commentId;
	    	   var newPraiseCount=praiseCount+1;
			  $.ajax({
			       type: 'POST',
			       url: basepath+"/web/epComment/praise.do",
			       dataType: "json",
			       data:{
			        	"userId":data_user,
			        	"commentId":commentId,
			        	"praiseCount":newPraiseCount
			       },
			       success: function(datas){
			    	   if(datas.code == 'FAILED'){
			    		   showInfo("您已经点过赞了，亲！","warn");
			    	   }else{
				    	   $("#praiseDiv"+commentId).html('<a href="javascript://" onclick=eLPraise('+commentId+','+newPraiseCount+')></a>点赞('+newPraiseCount+')')
			    	   }
			       }
			  });
		  }else{
			  showInfo('您已经点过赞了，亲！')
		  }
	  }

	  
	  //保存桩评论回复
	  function saveEpReply(commentId,epId){
		  var user=UserService.getUser();
		  if(!user){
		        $("#login").click();
		        return;
		    }
		  var reply_count = parseInt($('#zhuangreplyDiv'+commentId).attr('data_reply_count'));
		  var newReply_count = reply_count+1;
		  var epContent = $('#reply-neirong'+commentId).val().trim();
		  if(epContent == "" || epContent == "我也说两句"){
			  showInfo("评论内容不能为空","warn");
 			  return;
		  }
		  epContent =  epContent.replace('<','《').replace('>','》')
		  $.ajax({
		       type: 'POST',
		       url: basepath+"/web/epComment/insertReply.do",
		       dataType: "json",
		       data:{
		        	"uId":user.userId,
		        	"uName":user.normName,
		        	"epId":epId,
		        	"upCommentId":commentId,
		        	"replyCount":newReply_count,
		        	"epContent":epContent
		       },
		       success: function(datas){
		    	   if(datas.code=="OK"){
			    	   var newContent = datas.data;
			    	   $("#replyDiv"+commentId).html('<a href="javascript://" onclick="showStationReplyWindow('+commentId+')"></a>回复('+newReply_count+')')
			    	   $('#reply-neirong'+commentId).val("")
			    	   
			    	   //回复对话框点击事件，回复数量对话框参数变更
				       $('#zhuangreplyDiv'+commentId).attr('data_reply_count',newReply_count);
			    	   
			    	   //动态增加回复信息
			    	   userImgUrl = $('#userImgDiv').attr('src')
			    	   $('#replyDetailDiv'+commentId).prepend('<div class="PLleft PL-HF"><img src="'+userImgUrl+'" onerror="javascript:this.src=\''+basepath+'/static/images/user/2.png\'" width="80" height="80" /></div>'+
			  					'<div class="PLright PL-HFRight">'+
			  			        '<dl class="PL">'+
			  			        '<dt>'+user.normName+'</dt>'+
			  			        '<dd class="PLContent">'+newContent+'</dd>'+
			  			        '<dd><span class="time">'+new Date().toLocaleString()+'</span></dd>'+
			  			        '</dl>'+
			  			        '</div> <div style="clear:both;"></div>');
		    	   }else{
		    		   showInfo(datas.msg,"error");
		    	   }
		       }
		  });
	  }
	  
		  $("#zhuangpingfen-tijiao").click(function(){
			  var user=UserService.getUser();
				if(!user){
			        $("#login").click();
			        return;
			    }
			  var  electricPileContent = $("#pingfen-neirong").val().trim();
			  if(electricPileContent=="" || electricPileContent == "我也说两句"){
				  showInfo("评论内容不能为空","warn");
				  return;
			  };
			  electricPileContent =  electricPileContent.replace('<','《').replace('>','》')
			  var  electricPileId = $("#electricPileId").val();
			  	$.ajax({
			        type: 'POST',
			        url: basepath+"/web/epComment/insertEpCommnet.do",
			        dataType: "json",
			        data:{
			        	epc_EpId:electricPileId,
			        	epc_Content:electricPileContent,
			        	epc_UserId:user.userId,
			        	epc_UserName:user.normName
					  },
			        success: function(datas){			        
			        	if(datas.code=="OK"){
			        		$(".liuyan").remove();
			        		$("#pingfen-neirong").val('');
			        		addNewEpComment(user, datas.data);				        	
			        	}else{
			        		showInfo(datas.msg,"error");
			        	}			        				        	
			        }
			   });
		  });

		  //动态增加桩主评论
		  function addNewEpComment(user,data){
			   var datas=data.split("|||")
	    	   userImgUrl = $('#userImgDiv').attr('src')
	    	   var commentsDiv = '<div id="zhuangreplyDiv'+datas[1]+'" data_reply_count="0" class="BoxInput" style="display:none"><ul>'+
				                '<li class="IconStyle"><img src="'+basepath+'/static/images/img/face.png" width="27" height="27" alt=" " /></a></li>'+
				                '<li class="InputOn"><textarea maxlength="200" id="reply-neirong'+datas[1]+'" onclick="clearTxt(this)" cols="" rows="">我也说两句</textarea></li>'+
				                '<li class="user"><input name="button" onclick="saveEpReply('+datas[1]+','+datas[2]+')" type="button" value="提交" /><input name="button" onclick="closeStationReplyWindow('+datas[1]+')" type="button" value="关闭" />'+
				                '<span class="userNav"><img class="userImg" src="'+userImgUrl+'" width="27" height="27" alt=" " /><t class="userName">'+user.normName+'</t></span></li>'+
				                '</ul></div>';
			   $("#tag").prepend('<div class="PLleft"><img src="'+userImgUrl+'" onerror="javascript:this.src=\''+basepath+'/static/images/user/2.png\'" width="80" height="80" /></div>'+
		       			'<div class="PLright">'+
		       	        '<dl class="PL">'+
		       	        '<dt>'+user.normName+'</dt>'+
		       	        '<dd class="PLContent">'+datas[0]+'</dd>'+
		       	        '<dd><span class="time">'+new Date().toLocaleString()+
						'</span><span id="praiseDiv'+datas[1]+'" class="dianzhan"><a href="javascript://" onclick=eLPraise('+datas[1]+',0)></a>点赞(0) </span><span id="replyDiv'+datas[1]+'"  class="huifu"><a href="javascript://" onclick="showStationReplyWindow('+datas[1]+')"></a> 回复(0)</span></dd>'+
		       	        '</dl>'+
		       	        '</div> <div style="clear:both;"></div>'+commentsDiv+'<div id="replyDetailDiv'+datas[1]+'"></div>')
		   	    var allComment = $("#allComment").html();
			    var allCommentTxtlength = allComment.length;
			    var oldCount = allComment.substring(3,allCommentTxtlength-4);
			    $("#allComment").html('（共有'+(parseInt(oldCount)+1)+'条评论）');
		  }
		  
		  $("#zhanxingping-tijiao").click(function(){
	      	  var user=UserService.getUser();
	  			if(!user){
	  		        $("#login").click();
	  		        return;
	  		    }
			  if(parseInt($("#zhanxingpingshu").val())>0){
				  showInfo("您已经评分!","warn");
				  return;
			  }
	  			
	  		  var  powerStationId = $("#powerStationId").val();	 
	  		  var  score=$("#zhanxingpingfenshu").attr('tip-value');
	  		  if(score==0){
	  			showInfo("请选择评分!","warn");
	  			return;
	  		  }
	  		  	$.ajax({
	  		        type: 'post',
	  		        url: basepath+"/web/psStar/insertPsStar.do",
	  		        dataType: "json",
	  		        data:{
	  		        	psStar:score,
	  		        	psId:powerStationId,
	  		        	uId:user.userId,
	  		        	uName:user.normName
					  },
	  		        success: function(datas){	  		        
	  		        	showInfo("评分完成，谢谢您!");
	  		        	$("#zhanxingpingshu").val(score);
	  		        }
	  		   });
	        });	
		  $("#zhuangxingping-tijiao").click(function(){	      
	      	  var userid=UserService.getUserId();
	  			if(!userid){
	  		        $("#login").click();
	  		        return;
	  		    }	
	  		 if(parseInt($("#zhuangxingpingshu").val())>0){
					  showInfo("您已经评分!","warn");
					  return;
				  }
	  		  var  electricPileId = $("#electricPileId").val();	 
	  		  var  score=$("#zhuangxingpingfenshu").attr('tip-value');	
	  		if(score==0){
	  			showInfo("请选择评分!","warn");
	  			return;
	  		  }
	  		  	$.ajax({
	  		        type: 'post',
	  		        url: basepath+"/web/epStar/insertEpStar.do",
	  		        dataType: "json",
	  		        data:{
	  		        	epStar:score,
	  		        	epId:electricPileId
					  },
	  		        success: function(datas){	  		        
	  		        	showInfo("评分完成，谢谢您!");
	  		        	$("#zhuangxingpingshu").val(score);
	  		        }
	  		   });
	        });			  
	   function zhanxingping() {  
		   var scoreValue =  $("#zhanxingpingshu").val();
		     $.fn.raty.defaults.path = 'img';  		    
		  	  $('#function-demo1').raty({		  		  
		  	  	  number: 5,//多少个星星设置
		  		  score: scoreValue,//初始值是设置
		  		  targetType: 'number',//类型选择，number是数字值，hint，是设置的数组值
		          path      : basepath+'/static/lib/score/img',
	              cancelOff : 'cancel-off-big.png',
		          cancelOn  : 'cancel-on-big.png', 	         
		          size      : 24,
		          starHalf  : 'star-half-big.png',
		          starOff   : 'star-off-big.png',
		          starOn    : 'star-on-big.png',
		          //target    : '#function-hint1',
		          cancel    : false,
		          targetKeep: true,
		          precision : false,//是否包含小数
		          click: function(score, evt) {
		        	  $("#zhanxingpingfenshu").attr('tip-value',score);		       		        		        	 		         
		          }
		        }); 
		      };
		      function zhuangxingping() {		
		    	  var scoreValue =  $("#zhuangxingpingshu").val();
				     $.fn.raty.defaults.path = 'img';  					   
				  	  $('#function-demo2').raty({		  		  
				  	  	  number: 5,//多少个星星设置
				  		  score: scoreValue,//初始值是设置
				  		  targetType: 'number',//类型选择，number是数字值，hint，是设置的数组值
				          path      : basepath+'/static/lib/score/img',
			              cancelOff : 'cancel-off-big.png',
				          cancelOn  : 'cancel-on-big.png', 	         
				          size      : 24,
				          starHalf  : 'star-half-big.png',
				          starOff   : 'star-off-big.png',
				          starOn    : 'star-on-big.png',
				          //target    : '#function-hint2',
				          cancel    : false,
				          targetKeep: true,
				          precision : false,//是否包含小数
				          click: function(score, evt) {
				        	  $("#zhuangxingpingfenshu").attr('tip-value',score);		       		        		        	 		         
				          }
				        }); 
				      };
/*  function getPsCommentStar(){
	  
		 //  page.params.psId=powerStationId;	
	  	$.ajax({
	        type: 'POST',
	        url: basepath+"/web/psStar/getPsCommentStar.do",
	        dataType: "json",
	        data:{psId:powerStationId},
	        success: function(datas){		        	
	        	if(datas==""){
	        		 $("#zhanxingpingfenshu").attr('score-value',0);
	        	}else{
	        		$("#zhanxingpingfenshu").attr('score-value',datas.pss_CommentStar);
	        		alert($("#zhanxingpingfenshu").val('score-value'));
	        	}
	        				        		     	        		        				        	
	        }
	   });
	  
  }*/
				      
 String.prototype.trim = function(){
			  return this.replace(/(^\s*)|(\s*$)/g, "");
				    	};
				    	
function initTwoDicodeImg() {
	var datas = $("#bshare-custom_div").attr("data-info").split(",");
		var obj={
		   lng: datas[0],  
		   lat: datas[1],
		   type:datas[2],
		   name:"",
		   addr:datas[3],
		   service:1.0
		}
		var params=obj.lng
     			 +"|"+obj.lat
     			 +"|"+obj.name
     			 +"|"+obj.addr
     			 +"|"+obj.type
     			 +"|"+obj.service;
     share_config.share_url=basepath+"/reurl_act_appShare_ip_"+appShareUrl+"_web_"+encodeURIComponent(params)+".shtml";
 }

function moveToCommentPosition(){
    var scroll_offset = $("#centerLinkReplyDiv"+myCenterLinkPkCommentId).offset();  //得到pos这个div层的offset，包含两个值，top和left
    $("body,html").animate({
    	scrollTop:scroll_offset.top  //让body的scrollTop等于pos的top，就实现了滚动
     },0);
}
		   

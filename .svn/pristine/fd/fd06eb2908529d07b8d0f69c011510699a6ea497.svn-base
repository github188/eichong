/**
 * Created by Kael on 2015/3/20.
 */
(function () {
	$(".UserLeftDdNav").click(function(){
		var dataId=$(this).attr("data-id");
		if(dataId!="modifyPwd"){			
			$(this).siblings().removeClass("UserLeftDd");
			$(this).addClass("UserLeftDd");
			$(".UserRightWarp").hide();
			$(".UserRightWarpTwo").hide();
			$("#"+dataId).show();
			$("#pageEle").hide();
		}
		if(dataId=="myMsg"){
		   	$("#systemMessage").parent().siblings().children().addClass("select");
		   	$("#systemMessage").removeClass("select").addClass("noSelect");
			loadSystemMessages(1,10);
			$("#pageEle").show(); 
		}else if(dataId=="comment"){
			 loadComments(1,10);
			 $("#pageEle").show();
		}else if(dataId=="wallet"){
			loadWallets(1,10);
			$("#pageEle").show();
		}else if(dataId=="besp"){
			loadBespokes(1,10);
		}else if(dataId=="feedBack"){
			/*loadFeedbacks();
			$("#pageEle").show();*/
		}		
	});
	
	$(".UserLeftTitle").click(function(){
		$(".UserRightWarp").hide();
		$(".UserRightWarpTwo").hide();
		$(this).siblings().removeClass("UserLeftDd");		
		$("#maininfo").show();
		loadMsgTitles();
		loadUserLeftTitles();
		$("#pageEle").hide();
	});
	var lb=$("#lb").val();
	if(lb!=""){
		$(".UserLeftDdNav[data-id="+lb+"]").click();
	}else{
		$(".UserLeftTitle").click();
	}
	$("#feedBackBtn").click(function(){
		$.ajax({
		         type: 'POST',
		         url: basepath+"/web/other/addTblFeedBack.do" ,
		         dataType: "json",
		         data:$("#feedBackForm").serialize(),
		         success: function(data){
		        	 if(data.code=="OK"){
		        		 showInfo(data.msg);
		        		 $("#feedBackForm")[0].reset();
		        	 }else{
		        		 showInfo(data.msg,"error");
		        	 }
		         }
		    });
	});
	
	$("#chargeButton").click(function(){
		var chargeType=$("input[name='chargeType']:checked").val();
		if(chargeType==1){
			$("#chargeForm")[0].submit();
		}else{
			alert("亲，暂时只支持支付宝充值哦。")
		}
	});


	//点击钱包页面的我要充值详情
    $('body').on('click', '.recharge', function (e) {
        e.preventDefault();
        $("#pageEle").hide();
        $(".UserRightWarp").hide();
        $(".UserRightWarpTwo").hide();
        $("#recharge").show();
	});
	
		
    
/*	//点击显示消息详情
    $('body').on('click', '.myMsg', function (e) {
        e.preventDefault(); 
        var dataId=$(this).attr("data-id");
        $.ajax({
	         type: 'POST',
	         url: basepath+"/web/usermessage/getUsermessageById.do" ,
	         dataType: "json",
	         data:{id:dataId},
	         success: function(data){
	        	 alert()
	        	 $("#msgTitle").html(data.title);
	        	 $("#msgTime").html(new Date(parseInt(data.editTime.time)).toLocaleString());
	        	 $("#msgFrom").html(data.fromUsername);
	        	 $("#msgContent").html(data.content);	        	
	        	 showMsgOrDetail("msgDetailDiv"); 
	         }
	    });
        //修改未读信息数
        var unreadMsg=parseInt($("#unreadMsg").html());
        var status=$(this).attr("data-status");
        if(status==0){
        	if(unreadMsg>0){
	           	 $("#unreadMsg").html(unreadMsg-1);
	           	 $(".myMsg[data-id="+dataId+"]").parent().addClass("zhihui");        		
        	}
        	
        }
            
    });*/
    
    
	//点击个人中心的消息详情
   $('body').on('click', '.myMsgTitle', function (e) {
        e.preventDefault(); 
        var dataId=$(this).attr("data-id");
        $.ajax({
	         type: 'POST',
	         url: basepath+"/web/usermessage/getUsermessageById.do" ,
	         dataType: "json",
	         data:{id:dataId},
	         success: function(data){	
	        	 $("#msgTitleTitle").html(data.title);
	        	 $("#msgTimeTitle").html(new Date(parseInt(data.editTime.time)).toLocaleString());
	        	 $("#msgFromTitle").html(data.fromUsername);
	        	 $("#msgContentTitle").html(data.content);	        	
	        	 showMsgOrDetail("msgDetailDivTitle"); 
	        	 
	         }
	    });
       //修改未读信息数
        /*var unreadMsg=parseInt($("#unreadMsg").html());*/
        var status=$(this).attr("data-status");
        if(status==0){
        	/*if(unreadMsg>0){*/
        		 /*$("#unreadMsg").html(unreadMsg-1);*/
            	 $(".myMsg[data-id="+dataId+"]").parent().addClass("zhihui");       		
        	/*}*/       
        }
              
    });
	
    $("#backToMsg").click(function(){
    	showMsgOrDetail("myMsgDiv");
    	$("#pageEle").show();
    });
    
   $("#backToTitle").click(function(){
    	$(".UserLeftTitle").click();
    });
   
    
	
/*	function loadMessages(){
		$.ajax({
	         type: 'POST',
	         url: basepath+"/web/usermessage/getMessages.do",
	         dataType: "json",
	         data:page.params,
	         success: function(datas){
	        	 if(datas.code=="OK"){
	        		 var data=datas.data;
	        		 var content="";
	        		 var unreadMsg=0;
	        		 var liClass="TextOne";	        	
	        		 for(var i=0;i<data.length;i++){
	        			 if(data[i].status==0){
	        				 unreadMsg++;
	        				 liClass=liClass;
	        			 }else{
	        				 liClass=liClass+" zhihui";
	        			 }
	        			 content+= '<dd class="'+liClass+'"><span>'+new Date(data[i].createTime).toLocaleString()+'</span><a class="myMsg" data-id="'+data[i].id+'" data-status="'+data[i].status+'">'+data[i].title+'</a></dd>';	        			 
	        		 }
	        		 $("#systemMessage").html(content);
	        		 $("#unreadMsg").html(unreadMsg);
	        		 showMsgOrDetail("myMsgDiv");
	        		 initPage(datas,loadMessages);
	        	 }else{
	        		 alert(datas.msg);
	        	 }
	        	 
	         }
	    });
	}*/
	
	function showSystemMsgOrDetail(elId){		

		$(".ModuleTwo").hide();
		$("#maininfo").hide();	
		$(".systemMsgInfo").hide();
		$("#"+elId).show();
	}
	
	function showMsgOrDetail(elId){
		$("#pageEle").hide();
		$(".ModuleTwo").hide();
		$("#maininfo").hide();	
		$(".myMsgInfo").hide();
		$("#"+elId).show();
	}
   //我的消息TAB切换
   $(".msgTab").click(function(){    	
   	$(this).parent().siblings().children().addClass("select");
   	$(this).removeClass("select").addClass("noSelect");
/*   	var sysMsg = $("#sysMsg");
   	var perMsg = $("#perMsg");
   	var perFeedback = $("#perFeedback"); */ 
   	$("#pageEle").hide();
   	if(this.id=="systemMessage"){
/*   		$("#messageAllTab").append(sysMsg).append(perMsg).append(perFeedback);*/
   		$("#personMessage1").hide();
   		$("#personFeedback1").hide();
   		$("#systemMessage1").hide();
   		$("#systemMessage1").show();   
   		loadSystemMessage2(1,10);  		
   	}else if(this.id=="personMessage"){
   	/*	$("#messageAllTab").append(perMsg).append(sysMsg).append(perFeedback);*/  		
   		$("#personMessage1").hide();
   		$("#personFeedback1").hide();
   		$("#systemMessage1").hide();
   		$("#personMessage1").show();
   		loadPersonMessage2(1,5); 
   	}else if(this.id=="personFeedback"){
/*   		$("#messageAllTab").append(perFeedback).append(sysMsg).append(perMsg);*/
   		$("#personMessage1").hide();
   		$("#personFeedback1").hide();
   		$("#systemMessage1").hide();
   		$("#personFeedback1").show();  
   		loadFeedback2(1,5);
   	
   	}
 /*  	if($(this).attr("data-value")=="consumeType"){
   		$("#consumeType").show();
   	}else{
   		$("#consumeType").hide();
   	}
   	loadWallets();*/
   
   });

	function loadSystemMessages(){	
		var type=$(".msgTab.noSelect").attr("id");
/*		var sysMsg = $("#sysMsg");
    	var perMsg = $("#perMsg");
    	var perFeedback = $("#perFeedback"); */   		    	   	    	    	   	        	    	   	    	
    	//获取系统消息
    	if(type=="systemMessage"){
    		/*$("#messageAllTab").append(sysMsg).append(perMsg).append(perFeedback);*/
    		$("#personMessage1").hide();
    		$("#personFeedback1").hide();
    		$("#systemMessage1").hide();
    		$("#systemMessage1").show();    		
    		loadSystemMessage2(1,10);
    	}			
	}
	
	

	
	
	
	
	//点击显示消息详情
    $('body').on('click', '.systemMsgInfos', function (e) {
        e.preventDefault();
        var dataId=$(this).attr("data-id"); 
        $.ajax({
	         type: 'POST',
	         url: basepath+"/web/usermessage/getUsermessageById.do" ,
	         dataType: "json",
	         data:{id:dataId},
	         success: function(data){	  
	        	 $("#systemMsgTitle").html(data.title);		        	
	        	 $("#systemMsgTime").html(new Date(parseInt(data.editTime.time)).toLocaleString());
	        	 $("#systemMsgFrom").html(data.fromUsername);
	        	 $("#systemMsgContent").html(data.content);
	     		 $("#pageEle").hide();
	         }
	    });
        //修改未读信息数
       /* var unreadSystemMsg=parseInt($("#unreadSystemMsg").html());*/
        var status=$(this).attr("data-status");
        if(status==0){        	        	
        	/* $("#unreadSystemMsg").html(unreadSystemMsg-1);*/
        	 $(".systemMsgInfos[data-id="+dataId+"]").parent().addClass("zhihui");
        }
        showSystemMsgOrDetail("systemMsgDetailDiv");
    });
    
    $("#systemBackToMsg").click(function(){
    	/*showSystemMsgOrDetail("myMsgDiv");*/
    	/*$("a.changeColor").attr("style","'color:#999'");
    	$("span.changeColor").attr("style","'color:#999'");*/
    	$("#changeColor").click();
    	/*$("#pageEle").show();*/
    });
	
  function  loadComments(pageNum,pageSize){
  	$.ajax({
        type: 'POST',
        url: basepath+"/web/user/getCommentsByUserId.do" ,
        dataType: "json",
        data:initPageParams(pageNum,pageSize),
        success: function(datas){
       	 if(datas.code=="OK"){
       		 var data=datas.data;
       		 var content="";	        		
       		 for(var i=0;i<data.length;i++){
       			var connect = data[i].type==1?basepath+'/web/electricpile/detail.do?eid='+data[i].epc_epId:basepath+'/web/powerstation/detail.do?eid='+data[i].epc_epId;
       			/* content+='<dd><h1><u><a href="#"  >'+data[i].epc_Content+'</a></u></h1>'+
					 '<p>'+new Date(parseInt(data[i].epc_Createdate)).toLocaleString()+' '+data[i].type+' '+data[i].epc_epId+'<a class="yyxq-quxiao deleteComment" href="#" data-id="'+data[i].id+'" data-type="'+data[i].type+'">删除评论</a><a target="_blank" class="yyxq-chakan checkComment" href="'+connect+'" data-id="'+data[i].id+'">查看</a></p>'+
					 '</dd>';*/
       			content+= '<dl class="shoucang">'+
			              '<dt>'+data[i].epc_Content+'</dt>'+
			               '<dd>'+new Date(parseInt(data[i].epc_Createdate)).toLocaleString()+' '+data[i].type+' '+data[i].epc_epId+'<a class="yyxq-quxiao deleteComment"   data-id="'+data[i].id+'" data-type="'+data[i].type+'">删除评论</a><a target="_blank" class="yyxq-chakan checkComment" href="'+connect+'" data-id="'+data[i].id+'">查看</a></dd>'+
			               '</dl>';
       		 }
       		 $("#comments").html(content);	        		
       		 showCmtOrDetail();
       		 initPage(datas,"loadComments");
       	 }else{
       		 alert(datas.msg);
       	 }
        }
   });
	  
  }
	function showCmtOrDetail(){	
		$("#pageEle").show();
	}
	
	
	/*	
	//点击显示评论详情
    $('body').on('click', '.checkComment', function (e) {
        e.preventDefault();
        var dataId=$(this).attr("data-id");
        
        $.ajax({
	         type: 'POST',
	         url: basepath+"/web/user/getCommentById.do" ,
	         dataType: "json",
	         data:{id:dataId},
	         success: function(data){
        	 alert(JSON3.stringify(data));
	        	 $("#msgTitle").html(data.title);
	        	 $("#msgTime").html(new Date(parseInt(data.createTime.time)).toLocaleString());
	        	 $("#msgFrom").html(data.fromUsername);
	        	 $("#msgContent").html(data.content);
	         }
	    });
    });*/
    //点击删除评论
    $('body').on('click', '.deleteComment', function (e) {
        e.preventDefault();
        var dataId=$(this).attr("data-id");
        var dataType=$(this).attr("data-type");
        if(confirm("您确定要删除吗？")){
        $.ajax({
	         type: 'POST',
	         url: basepath+"/web/user/removeMyCommentN.do" ,
	         dataType: "json",
	         data:{cId:dataId,type:dataType},
	         success: function(data){
        	 loadComments(1,10)
	        /*	 $("#msgTitle").html(data.title);
	        	 $("#msgTime").html(new Date(parseInt(data.createTime.time)).toLocaleString());
	        	 $("#msgFrom").html(data.fromUsername);
	        	 $("#msgContent").html(data.content);*/
	         }
	    });}
    });
    
//	var viewFiles = document.getElementById("file1");
//    var viewImg = document.getElementById("fileImg1");
//    var viewFiles2 = document.getElementById("file2");
//    var viewImg2 = document.getElementById("fileImg2");
//    var viewFiles3 = document.getElementById("file3");
//    var viewImg3 = document.getElementById("fileImg3");
//    var viewFiles4 = document.getElementById("file4");
//    var viewImg4 = document.getElementById("fileImg4");
//    var viewFiles5 = document.getElementById("file5");
//    var viewImg5 = document.getElementById("fileImg5");
    var viewFiles6 = document.getElementById("file6");
    var viewImg6 = document.getElementById("fileImg6");
    function viewFile (file,img) {
        //通过file.size可以取得图片大小
        var reader = new FileReader();
        reader.onload = function( evt ){
        	//alert(viewImg.src);
        	img.src = evt.target.result;
        }
        reader.readAsDataURL(file);
    }
//    viewFiles.addEventListener("change", function () {
//        //通过 this.files 取到 FileList ，这里只有一个
//        viewFile(this.files[0],viewImg);
//    }, false);
//    viewFiles2.addEventListener("change", function () {
//        viewFile(this.files[0],viewImg2);
//    }, false);
//    viewFiles3.addEventListener("change", function () {
//        viewFile(this.files[0],viewImg3);
//    }, false);
//    viewFiles4.addEventListener("change", function () {
//        viewFile(this.files[0],viewImg4);
//    }, false);
//    viewFiles5.addEventListener("change", function () {
//        viewFile(this.files[0],viewImg5);
//    }, false);
    viewFiles6.addEventListener("change", function () {
        viewFile(this.files[0],viewImg6);
    }, false);
	
	var options = {  
        beforeSubmit:  showRequest,  //提交前处理 
        success:       showResponse,  //处理完成 
//        resetForm: true,  
        dataType:  'json'  
    };  
  
    $('#pileShareBtn').click(function() {
    	var obj=$("#pileShareForm").serializeObject();
		var parameter_note="";
		if(!obj.address){
			showInfo("请选择电桩的地址","error");
			return;
		}
		if(!obj.maker){
			showInfo("请选择制造厂商","error");
			return;
		}
		if(!obj.chargingMode){
			showInfo("请选择充电方式","error");
			return;
		}
		if(!obj.powerInterface){
			showInfo("请选择接口方式","error");
			return;
		}
		if(!obj.powerSize){
			showInfo("请选择额定功率","error");
			return;
		}
		var parameter_note="制造厂商:"+obj.maker+",充电方式:"+obj.chargingMode
							+",接口方式:"+obj.powerInterface+",额定功率:"+obj.powerSize;
		$("#parameter_note").val(parameter_note);
        $("#pileShareForm").ajaxSubmit(options);  
    });  
    
    function showRequest(formData, jqForm, options) { 
	    return true;  
	}  
  
	function showResponse(responseText, statusText)  {  
		var data=responseText;
		if(data.code=="OK"){
   		 showInfo(data.msg);
   		 $("#pileShareForm").reset();
   	 }else{
   		 showInfo(data.msg,"error");
   	 }	
	}
	
	$("input[name=usinFacticityname]").blur(function(){
		if($.trim($("input[name=usinFacticityname]").val()).length>20){
			showInfo("真实姓名过长","error");
			$("input[name=usinFacticityname]").focus();
			return;			
		}		
	})
	
	
	$("input[name=normEmail]").blur(function(){
		var myRegex = /@.*\.[a-z]{2,6}/;
	      var email = $("input[name=normEmail]").val();
	      email = email.replace(/^ | $/g,"");
	      email = email.replace(/^\.*|\.*$/g,"");
	      email = email.toLowerCase();
	      //验证电子邮件的有效性
        if (email&&!myRegex.test(email))
		{
      	showInfo("请输入正确的E-MAIL!","error");
      	$("input[name=normEmail]").focus();
			return;
			}		
	})
	
	$("input[name=normAddress]").blur(function(){
		if($.trim($("input[name=normAddress]").val()).length>60){
			showInfo("家庭地址过长","error");
			$("input[name=normAddress]").focus();
			return;
		}		
	})
	
	
	
	
	$("input[name=normDrivingLicence]").blur(function(){
		var card = $("input[name=normDrivingLicence]").val();
		card = $.trim(card);
		if(card)
		{
		   // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
		   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
		   if(reg.test(card) === false)
		   {
			   showInfo("驾驶证输入不合法","error");
			   $("input[name=normDrivingLicence]").focus();
		       return;
		   }
		}		
	})
	
    $("#modifyUserBtn").click(function(){
    	var obj=$("#modifyUserForm").serializeObject();
    	
    	if($.trim($("input[name=normRealName]").val()).length>20){
			showInfo("真实姓名过长","error");
			$("input[name=normRealName]").focus();
			return;			
		}
   	   	
    	var myRegex = /@.*\.[a-z]{2,6}/;
	      var email = $("input[name=normEmail]").val();
	      email = email.replace(/^ | $/g,"");
	      email = email.replace(/^\.*|\.*$/g,"");
	      email = email.toLowerCase();
	      //验证电子邮件的有效性
      if (email&&!myRegex.test(email))
		{
    	showInfo("请输入正确的E-MAIL!","error");
    	$("input[name=normEmail]").focus();
			return;
			}	
    	
      if($.trim($("input[name=normAddress]").val()).length>60){
			showInfo("家庭地址过长","error");
			$("input[name=normAddress]").focus();
			return;
		}
      
      
      if($.trim($("input[name=normIdCard]").val()).length>49){
			showInfo("IC卡号过长","error");
			$("input[name=normIdCard]").focus();
			return;
		}
      
    	var card = $("input[name=normDrivingLicence]").val();
		card = $.trim(card);
		if(card)
		{
		   // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
		   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
		   if(reg.test(card) === false)
		   {
			   showInfo("驾驶证输入不合法","error");
			   $("input[name=normDrivingLicence]").focus();
		       return;
		   }
		}
		 
/*		if(!obj.usinPhone){
			showInfo("请输入手机号码","error");
			return;
		}*/
				         
	/*	if(!obj.normEmail){
			showInfo("请输入电子邮箱","error");
			return;
		}*/
		
		 
		    				
		$("#modifyUserForm").ajaxSubmit({
			dataType:  'json'  ,
	        success: function(data){
	    		if(data.code=="OK"){
    			 UserService.login2(data.data);
        		 UserService.showUser();
	       		 //$("#usinHeadimage").attr("src",data.data.usinHeadimage);   
/*	       		 var src10 = $("#fileImg6").attr("src");
	       		 $("#fileImg10").attr("src",src10);*/
	       		 /*window.location.reload();*/
	       		
	       		//setTimeout(function(){location.href=basepath+"/mycenter.do?lb=modifyUser";},500);
	       		showInfo("操作成功");
	       	 }else{
	       		 showInfo(data.msg,"error");
	       	 }	
	        }
		});
	});
    
    /**
     * 一键升级
     */
    $('#center_update').click(function () {
        Ajax.custom({
            url: config.IUpdateMyCenter,
            data: {userId: UserService.getUserId()},
            type: 'POST'
        }, function (res) {
            if(!res||res.code != 'OK')return;
            showInfo("提交成功,审核中...");
        });
    });
    //车品牌类型加载
    $.ajax({
	    type: 'POST',
	    url:basepath+ "/web/paraconfig/findCarCompanyList.do",
	    dataType: "json",
	    success: function(data){	
	    			var arr1 = data.data;	
	    			var html="<option value='0'>请选择</option>";
			   		 for(var i =0;i<arr1.length;i++){	 
			   			 html+='<option value="'+arr1[i].pk_carCompany+'">'+arr1[i].carCompany_Name+'</option>';			   		
			   		 };			   		 
			   		 $("#qcpp").html(html);
			   		 var qcpp=$("#qcpp").attr("data-value");			   		
			   		 if(qcpp){
			   			$("#qcpp").val(qcpp);
			   			getModels(qcpp,true);
			   		 }else{
			   			 $("#ppcx").html("<option value='0'>请选择</option>");
			   			 return;
			   		 }			   		
			   		//alert();
  	    		}		 
	});
	//选项切换
    $('body').on('change', '#qcpp', function (e) {  
        e.preventDefault();
        getModels($(this).val());
    });
     
	function getModels(carCompanyId,select){
		$.ajax({
		    type: 'POST',
		    url:basepath+ "/web/paraconfig/findCarinfoList.do",
		    dataType: "json",
		    data:{carinfoCompanyId:carCompanyId},	 
		    success: function(data){
		    	if(carCompanyId!=0){
		    		var arr2 = data.data;	
			    	var html = "";
				   		 for(var i =0;i<arr2.length;i++){
				   			 html+='<option value="'+arr2[i].pkCarinfo+'">'+arr2[i].carinfoStylename+'</option>';
				   		 };  
				   		 $("#ppcx").html(html);
				   		 var ppcx=$("#ppcx").attr("data-value")
				   		 if(ppcx&&select){
				   			$("#ppcx").val(ppcx);
				   		 }		    		
		    	}else{
		    		var html = "<option value='0'>请选择</option>";			   		 
			   		$("#ppcx").html(html);		    		
		    		return;		    		
		    	}		    	
	  	    }		 
		});
	}
    
    //我的钱包TAB切换
    $(".walletTab").click(function(){    	
    	$(this).parent().siblings().children().addClass("unselect").removeClass("select");
    	$(this).removeClass("unselect").addClass("select");
    	if($(this).attr("data-value")=="consumeType"){
    		$("#consumeType").show();
    	}else{
    		$("#consumeType").hide();
    	}
    	loadWallets(1,10);
    });
    
    

    
    $(".collect-cancel").click(function(){
    	if(confirm("确定要取消收藏吗？")){
    		$.ajax({
    		    type: 'POST',
    		    url:basepath+ "/web/usercollect/removeFavoritesList.do",
    		    dataType: "json",
    		    data:{userCollectId:$(this).attr("data-id")},	 
    		    success: function(data){
    		    	if(data&&data.code == 'OK'){
    		    		location.href=basepath+"/mycenter.do?lb=collect";
    		    	}else{
    		    		showInfo(data.msg,"error");
    		    	}
    	            
    	  	    }		 
    		});
    	}
    });
    

	/*$('body').on('click', '.yyxq-xuyuyue', function (e) {
		var userid=UserService.getUserId();
		if(!userid){
	        $("#login").click();
	        return;
	    }else{
	    	//用户ID
	    	$("#bespUserinfo").val(userid);
	    }
		var bespFrozenamt= parseFloat($("#bespFrozenamt").val());
		var bespBespokeprice=parseFloat($("#bespBespokeprice").val());
		var time=bespFrozenamt/(bespBespokeprice*60);
		if(time>=6){
			showInfo("预约时间不能大于6个小时","warn");
			return;
		}
		$("#xuyueTime").html(6-time);
		//弹窗
		$("#yuyueWindow").show();
		initSlider();
		showZZ();
		//刷新时间
		refreshTime();
		setInterval(refreshTime,1000); //每隔一秒执行一次 
		
	});
	$("#closeYuyue").click(function(){
		$("#yuyueWindow").hide();
		closeZZ();
	});
	$('body').on('click', '.yuyue-cancel', function (e) {
    	if(confirm("确定要取消预约吗？")){
    		$.ajax({
    		    type: 'POST',
    		    url:config.appInterfaceServer+"/web/common/sendRequest.do",
    		    dataType: "json",
    		    data:{
    		    	requestType:4,
    		    	pkBespoke:$("#pkBespoke").val(),
    		    	bespElectricpilehead:$("#bespElectricpilehead").val(),
    		    	uId:UserService.getUserId()
    		    	},	 
    		    success: function(data){
    		    	if(data&&data.status == '100'){
    		    		showInfo(data.msg);
    		    		 location.href=basepath+"/mycenter.do?lb=besp";
    		    	}else{
    		    		showInfo(data.msg,"error");
    		    	}
    	            
    	  	    }		 
    		});
    	}
    });
	
	var initSlider=function(){
		//预约单价(小时)
		var reservationRate=parseFloat($("#bespBespokeprice").val())*60;
		var xuyueTime=parseFloat($("#xuyueTime").html())*2;
		$("#jslider").slider({
			range: "max",
			min: 0,
			max: xuyueTime,
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
	}	*/
	//取得系统当前时间 
/*	var refreshTime=function(){ 
		var myDate = new Date(); 
		var date = myDate.toLocaleDateString(); 
		var hours = myDate.getHours()<10?"0"+myDate.getHours():myDate.getHours();  
		var minutes = myDate.getMinutes()<10?"0"+myDate.getMinutes():myDate.getMinutes(); 
		var seconds = myDate.getSeconds()<10?"0"+myDate.getSeconds():myDate.getSeconds(); 
		$("#showTime").html(hours+":"+minutes+":"+seconds); //将值赋给div 
	} */
	
/*	$("#submitBesp").click(function(){
		var bespTime=$("#yuyueTime").html();
		if(bespTime=="0"){
			showInfo("续约时间不能为0","error");
			return;
		}
		$("#bespBespoketime").val(bespTime*60);
		var endTime=DateUtil.strToDate($("#bespEndTime").val());
		endTime=new Date(endTime.getTime()+bespTime*3600000);
		var bespEndTime=DateUtil.dateToStr(endTime);
		$("#bespEndTime").val(bespEndTime);
		//预约单价
		var unitPrice=$("#bespBespokeprice").val();
		$("#bespBespokeprice").val(unitPrice);
		//冻结资金
		var price=$( "#yuyuePrice" ).html();
		$("#bespFrozenamt").val(price);
		//alert($("#bespForm").serialize());
		//请求预约操作
		var obj=$("#bespForm").serializeObject();
		obj.requestType=2;
		$.ajax({
	        type: 'post',
	        url: config.appInterfaceServer+"/web/common/sendRequest.do",
	        data:$.param(obj),
	        dataType: "json",
	        async:true,
	        success: function(data){     	
	       	 if(data.status=="100"){
	       		 showInfo(data.msg);
	       		 $("#yuyueWindow").hide();
	    		 closeZZ();
	    		 setTimeout(reload,1000);
	    		
	       	 }else{
	       		 showInfo(data.msg,"error");
	       	 }
	        }
	   });
	});*/
})();

/*function reload(){
	 location.href=basepath+"/mycenter.do?lb=besp";
}*/



function loadWallets(pageNum,pageSize){
	var beginTime=$("#walletStartDate").val();
	var endTime=$("#walletEndDate").val();
	var endDate=DateUtil.strFormatToDate(endTime,'yyyy-MM-dd');
	endDate=DateUtil.dateAdd('d', 1, endDate);
	endTime=DateUtil.dateToStr(endDate,"yyyy-MM-dd");
	var type=$(".walletTab.select").attr("data-value");
	var walletType=0;
	if(type=="consumeType"){
		walletType=$("#consumeType").val();
	}else{
		walletType=5;
	}
	$.ajax({
         type: 'POST',
         url: basepath+"/web/user/findMyWallet.do?beginTime="+beginTime+"&endTime="+endTime+"&walletType="+walletType,
         dataType: "json",
         data:initPageParams(pageNum,pageSize),
         success: function(datas){
        	 if(datas.code=="OK"){
        		 if(datas.data.length>0){
            		 var data=datas.data;
            		 var content="";
            		 var recordType="";   
            		 var divClass="";
            		 for(var i=0;i<data.length;i++){
            			 divClass=i%2==0?"TablesOne":"TablesTwo";
            			 switch(parseInt(data[i].recordTitle)){
    	        		 	case 1:
    	        		 		recordType="充电消费";
    	        		 		break;
    	        		 	case 2:
    	        		 		recordType="预约消费";
    	        		 		break;
    	        		 	case 3:
    	        		 		recordType="购物消费";
    	        		 		break;
    	        		 	case 4:
    	        		 		recordType="充值消费";
    	        		 		break;
    	        		 	case 5:
    	        		 		recordType="收益";
    	        		 		break;	
    	        		 	default:
    	        		 		break;
    	        		 }       			
            			 content+='<dd class="'+divClass+'"><span class="fenleiC">'+data[i].recordMoney+'</span><span class="fenleiB">'+new Date(data[i].recordTime).toLocaleString()+'</span><span class="fenleiA">'+recordType+'</span></dd>';      			
            		 } 
            		 //$(content).appendTo($("#walletDiv"));
            		 $("#walletDiv").html(content);
            		 initPage(datas,"loadWallets");
        		 }else{
        			 content = '<dd class = "TextTwo"><strong style="color:red; text-align:center;">暂   无   记   录</strong></dd>';		
        			 $("#walletDiv").html(content);
        			 $("#pageEle").hide();
        		 }        		       
        	 }else{
        		 showMsg(datas.msg,"error");
        	 }
        	 
         }
    });
}


/*function  loadBespokes(){
	var userid=UserService.getUserId();
	if(!userid){
        $("#login").click();
        return;
    }
  	$.ajax({
        type: 'POST',
        url: config.appInterfaceServer+"/web/common/sendRequest.do" ,
        dataType: "json",
        data:{
	        	bespUserInfo:userid,
	        	requestType:3
        	},
        success: function(datas){
       	 if(datas.status=="100"){
       		 var data=datas.data;
       		// $("#aaaa").html(JSON3.stringify(data));
    		 var content="";
    		 var currentContent="<dt>当前预约</dt>"
    		 var finishContent="<dt>已过期预约</dt>";
    		 for(var i=0;i<data.length;i++){
    			 var beginTime=DateUtil.dateToStr(new Date(data[i].bespBeginTime),"yyyy-MM-dd HH:mm");
    			 var endTime=new Date(data[i].bespEndTime);
    			 var endHours = endTime.getHours()<10?"0"+endTime.getHours():endTime.getHours();  
    			 var endMinutes = endTime.getMinutes()<10?"0"+endTime.getMinutes():endTime.getMinutes(); 
    			 //1：取消预约 2：预约结束(完成) 3：续预约中 4：预约中 5:预约确认中 6：预约失败
    			 var bespBespokestatus="";
    			 switch(parseInt(data[i].bespBespokestatus)){
    			 	case -1:
    			 		bespBespokestatus="结束充电";
	     		 		break;
	     		 	case 1:
	     		 		bespBespokestatus="取消预约";
	     		 		break;
	     		 	case 2:
	     		 		bespBespokestatus="预约结束";
	     		 		break;
	     		 	case 3:
	     		 		bespBespokestatus="续预约中";
	     		 		break;
	     		 	case 4:
	     		 		bespBespokestatus="预约中";
	     		 		break;
	     		 	case 5:
	     		 		bespBespokestatus="预约确认中";
	     		 		break;
	     		 	case 6:
	     		 		bespBespokestatus="预约失败";
	     		 		break;		
	     		 	default:
	     		 		break;
	     		 }
    			 var status=parseInt(data[i].bespBespokestatus);
    			 if(status=="-1"||(status!="3"&&status!="4")){
    				 finishContent+= '<dd><h1><span>'+beginTime+'</span>桩体状态：<u>'+bespBespokestatus+'</u>预约号：<u>'+data[i].bespResepaymentcode+'</u></h1>'
				    +'<p>您已预约买断了<span>'+beginTime+'</span>至<span>'+endHours+':'+endMinutes+'</span>时间段 '
				    +'预约桩体编号<span>'+data[i].elPiElectricPileCode+'</span>。'
					+'当前已消费金额为<span>'+data[i].bespFrozenamt+'</span>元。';;
    			 }else{
    				 $("#pkBespoke").val(data[i].pkBespoke);
    				 $("#bespElectricpileid").val(data[i].pk_ElectricPile);
    				 $("#bespElectricpilehead").val(data[i].bespElectricpilehead);
    				 $("#pileHeadName").html(data[i].elPi_ElectricPileAddress);
    				 $("#bespBespokeprice").val(data[i].bespBespokeprice);
    				 $("#bespFrozenamt").val(data[i].bespFrozenamt);
    				 $("#bespBespokeprice").val(data[i].bespBespokeprice);
    				 var beginTime2=DateUtil.dateToStr(new Date(data[i].bespBeginTime),"yyyy-MM-dd HH:mm:ss");
    				 $("#bespBeginTime").val(beginTime2);
    				 $("#bespEndTime").val(DateUtil.dateToStr(endTime));
    				 currentContent+='<dd><h1><span>'+beginTime+'</span>桩体状态：<u>'+bespBespokestatus+'</u>预约号：<u>'+data[i].bespResepaymentcode+'</u></h1>'
				    +'<p>您已预约买断了<span>'+beginTime+'</span>至<span>'+endHours+':'+endMinutes+'</span>时间段 <br />'
				    +'预约桩体编号<span>'+data[i].elPiElectricPileCode+'</span>。<br />'
					+'当前已冻结金额为<span>'+data[i].bespFrozenamt+'</span>元。'
    				+'<a class="yyxq-quxiao yuyue-cancel" data-id="'+data[i].pkBespoke+'">取消预约</a>'
    				+'<a class="yyxq-xuyuyue" data-id="'+data[i].pkBespoke+'">续预约</a></p>';
    			 }
    		 }
    		 $(".bespCurrent").html(currentContent);
    		 $(".bespFinish").html(finishContent);
       	 }else{
       		 alert(datas.msg);
       	 }
        }
   });
}*/
//反馈列表
function  loadFeedbacks(){
/*	var userid=UserService.getUserId();
	if(!userid){
        $("#login").click();
        return;
    }
	$.ajax({
        type: 'POST',
        url: basepath+"/web/feedback/getFeedbackList.do?febaUserid="+userid ,
        dataType: "json",
        data:page.params,
        success:function(datas){
        	if(datas.code=="OK"){
            	var data=datas.data;
            	var html="" ; 
           	for(var i = 0;i<data.length;i++){
           		html+='<dl class="shoucang">'+
	              '<dt>'+data[i].feBa_Content+'</dt>'+
	               '<dd>'+new Date(parseInt(data[i].feBa_Createdate)).toLocaleString()+'</dd>'+
	               '</dl>';          		        		
            	}
              $("#loadfeedbacks").html(html);
        	  initPage(datas,loadFeedbacks);
        	}else{
        		alert(datas.msg);
        	}       
        }
	})*/
}

function  loadUserLeftTitles(pageNum,pageSize){	
	$.ajax({
        type: 'POST',
        url: basepath+"/web/user/findMyWallet.do",
        dataType: "json",
        data:initPageParams(pageNum,pageSize),
        success: function(datas){            	
       	 if(datas.code=="OK"){
       		 var data=datas.data;
       		 var content="";
       		 var recordType="";
       		 if(data.length==0){
    			 content = '<dd class = "TextTwo"><strong style="color:red; text-align:center;">暂   无   记   录</strong></dd>';
       		 }else{
         		 for(var i=0;i<2;i++){
            			
           			 switch(parseInt(data[i].recordTitle)){
    	        		 	case 1:
    	        		 		recordType="充电消费";
    	        		 		break;
    	        		 	case 2:
    	        		 		recordType="预约消费";
    	        		 		break;
    	        		 	case 3:
    	        		 		recordType="购物消费";
    	        		 		break;
    	        		 	case 4:
    	        		 		recordType="充值消费";
    	        		 		break;
    	        		 	case 5:
    	        		 		recordType="收益";
    	        		 		break;	
    	        		 	default:
    	        		 		break;
    	        		 }
           			
           			 content+='<dd class="TablesOne"><span class="fenleiC">'+data[i].recordMoney+'</span><span class="fenleiB">'+new Date(data[i].recordTime).toLocaleString()+'</span><span class="fenleiA">'+recordType+'</span></dd>';  
           		 } 
       		 } 
       		 $("#walletDivTitle").html(content);       	
       	 }else{
       		 showMsg(datas.msg,"error");
       	 }
       	 
        }
   });
	
}
 function loadMsgTitles(pageNum,pageSize){
		$.ajax({
	         type: 'POST',
	         url: basepath+"/web/usermessage/getSystemMessages.do",
	         dataType: "json",
	         data:initPageParams(pageNum,pageSize),
	         success: function(datas){
	        	 if(datas.code=="OK"){
	        		 var data=datas.data;
	        		 var content="";
	        		 var content1="";
	        		 var content2="";      		 
	        		 if(data.length==0){
	        			 content = '<dd class = "TextTwo"><strong style="color:red; text-align:center;">暂   无   消   息</strong></dd>';
	        			 }else{
	        			 for(var i=0;i<2;i++){
		        			 if(data[i].status==0){
			        			 content1+= '<dd  class="TextOne"><span style="color:#666;">'+new Date(data[i].createtime).toLocaleString()+'</span><a class="myMsgTitle" data-id="'+data[i].id+'" data-status="'+data[i].status+'">'+data[i].title+'</a></dd>';	        			 		        		     
		        			 }else{		        						        				 
				        	     content2+= '<dd  class="TextOne zhihui"><span style="color:#999;">'+new Date(data[i].createtime).toLocaleString()+'</span><a style="color:#999;" class="myMsgTitle" data-id="'+data[i].id+'" data-status="'+data[i].status+'">'+data[i].title+'</a></dd>';	        			 		        		     

		        			 }
		        			 content = content1+content2;
		        				 }
	        		 }        		
	        		 $("#msgLiTitle").html(content);
	        		/* $("#unreadMsg").html(unreadMsg);*/
	        		 /*showMsgOrDetail("myMsgDiv");*/
	        	 }else{
	        		 alert(datas.msg);
	        	 }
	        	 
	         }
	    });
	 
 }

 
 function loadFeedback2(pageNum,pageSize){
 	//获取反馈列表
		$.ajax({
	         type: 'POST',
	         url: basepath+"/web/feedback/getFeedbackList.do",
	         dataType: "json",
	         data:initPageParams(pageNum,pageSize),
	         success: function(datas){	        
	        	 if(datas.code=="OK"){
	        		 var data=datas.data;
	        		 var content="";	        		
	        		 var content1 = "";	    
	        		 if(data.length>0){
	        			 for(var i=0;i<data.length;i++){
		        			 if(data[i].feBa_Status=='0'){	        				 
		        				 content1 = '<li><span class="sixinTime">'+new Date(data[i].feBa_Createdate).toLocaleString()+'<span class="sixinJieguo Red"><b>未受理</b></span></span>'+	       				 
			        		          '<span class="sixinUserName"><b>用户反馈</b></span>'+
			        		          '<span class="sixinText">'+data[i].feBa_Content+'</span></li>'		        		         
		        			 }else if(data[i].feBa_Status=='1'){
		        				 content1 = '<li><span class="sixinTime">'+new Date(data[i].feBa_Createdate).toLocaleString()+'<span class="sixinJieguo Green"><b>处理中</b></span></span>'+	       				 
		        		          '<span class="sixinUserName"><b>用户反馈</b></span>'+
		        		          '<span class="sixinText">'+data[i].feBa_Content+'</span></li>'	
		        			 }else if(data[i].feBa_Status=='2'){
		        				 var reason = data[i].feBa_Reason;
		        				 if(data[i].feBa_Reason.length==0){
		        					 reason ='详情请联系400-085-0006';
		        				 }
		          				 content1 = '<li><span class="sixinTime">'+new Date(data[i].feBa_Createdate).toLocaleString()+'<span class="sixinJieguo"><b>已处理</b></span></span>'+	       				 
		        		          '<span class="sixinUserName"><b>用户反馈</b></span>'+
		        		          '<span class="sixinText">'+data[i].feBa_Content+'</span>'+
		        		          '<p class="sixinHD">'+
		        		          '<span class="sixinTime Red">爱充客服：充小小&nbsp;&nbsp;&nbsp;&nbsp;于'+new Date(data[i].feBa_Updatedate).toLocaleString()+'处理</span>'+
		        		          '<span class="sixinUserName Red"><b>处理结果</b>：</span>'+
		        		          '<span class="sixinText Red">'+reason+'</span></li>' 	        		          	        		          	        		         	        		          		        			        				 
		        			 }
		        			 content+=content1;	 
		        		 }	        			 
	        		 }else{
	        			 
	        			 content = '<li><span class="sixinUserName"><b>暂无反馈</b></span></li>';
	        			 $("#personMessage1").html(content);
	        		 }
	        		 
	        		 $("#personFeedback1").html(content);	
	        		 $("#pageEle").show();
	        		 initPage(datas,"loadFeedback2");	 	        		 
	        	 }else{
	        		 alert(datas.msg);
	        	 }
	        	 
	         }
	    });			
	}
 
 
	function loadSystemMessage2(pageNum,pageSize){
		$.ajax({
	         type: 'POST',
	         url: basepath+"/web/usermessage/getSystemMessages.do",
	         dataType: "json",
	         data:initPageParams(pageNum,pageSize),
	         success: function(datas){	        
	        	 if(datas.code=="OK"){
	        		 var data=datas.data;
	        		 var content="";
	        		 var content1="";
	        		 var content2="";
	        		/* var unreadSystemMsg=0;*/
	        		 for(var i=0;i<data.length;i++){
	        			 if(data[i].status==0){
	        				 content1+='<dd class="TextOne"><span style="color:#666;font-size:14px;font-weight:normal;">'+new Date(data[i].createtime).toLocaleString()+'</span>'
		        			 +'<a style="color:#333;" href="#" class="systemMsgInfos" data-id="'+data[i].id+'" data-status="'+data[i].status+'">'+data[i].title+'</a></dd>';
	        			 }else{
	        				 content2+='<dd class="TextOne zhihui"><span style="color:#999;">'+new Date(data[i].createtime).toLocaleString()+'</span>'
		        			 +'<a style="color:#999;" href="#" class="systemMsgInfos" data-id="'+data[i].id+'" data-status="'+data[i].status+'">'+data[i].title+'</a></dd>';	
	        			 } 	        		 
	        			 content=content1+content2;
	        		 }
	        		 $("#systemMessage1").html(content);
	        		/* $("#unreadSystemMsg").html(unreadSystemMsg);*/
	        		 showSystemMsgOrDetail("myMsgDiv");
	        		 $("#pageEle").show();
	        		 initPage(datas,"loadSystemMessage2");
	        	 }else{
	        		 alert(datas.msg);
	        	 }
	        	 
	         }
	    });
		
	}
	
	function loadPersonMessage2(pageNum,pageSize){
		$.ajax({
	         type: 'POST',
	         url: basepath+"/web/usermessage/getPersonMessages.do",
	         dataType: "json",
	         data:initPageParams(pageNum,pageSize),
	         success: function(datas){	        
	        	 if(datas.code=="OK"){
	        		 var data=datas.data;
	        		 var content="";
	        		 if(data.length>0){
	        			 for(var i=0;i<data.length;i++){
			        		 var connect = data[i].type==1?basepath+'/web/electricpile/detail.do?eid='+data[i].epc_EpId+'&&commentId='+data[i].pk_EpcComment:basepath+'/web/powerstation/detail.do?eid='+data[i].epc_EpId+'&&commentId='+data[i].pk_EpcComment+'&&type='+data[i].type;
			        		 var textType = data[i].type==1?'的桩':'的站';
		        		     content+='<li><span class="sixinTime">'+new Date(data[i].epc_Createdate).toLocaleString()+'</span><span class="sixinUserName"><b>'+data[i].epc_UserName+'</b>&nbsp;&nbsp;在&nbsp;&nbsp;<a  data-id="'+data[i].pk_EpcComment+' " href="'+connect+'">'+data[i].commentAddress+textType+'</a>&nbsp;&nbsp;中回复:</span><span class="sixinText" style="word-break: break-all;">'+data[i].epc_Content+'</span></li>'; 	        			 
		        		 }	        		 
		        		 $("#personMessage1").html(content);
		        		/* $("#unreadSystemMsg").html(unreadSystemMsg);*/
		        		 showPersonMessage1();
		        		 initPage(datas,"loadPersonMessage2");	        			 
	        		 }else{
	        			 content = '<li><span class="sixinUserName"><b>暂无私信</b></span></li>';
	        			 $("#personMessage1").html(content);
	        		 }
	        		 
	        	 }else{
	        		 alert(datas.msg);
	        	 }
	        	 
	         }
	    });
	}
	function showPersonMessage1(){	
		$("#pageEle").show();
	}
	
	
	function showSystemMsgOrDetail(elId){
		$("#pageEle").hide();
		$(".ModuleTwo").hide();
		$("#maininfo").hide();	
		$(".systemMsgInfo").hide();
		$("#"+elId).show();
	}
	

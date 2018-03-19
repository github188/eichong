/**
 * Created by Kael on 2015/3/20.
 */
$(document).ready(function () {
	$("#choosePartnerDiv").show();
	$("#chooseNext").click(function(){
		if($("input[name=choosePartner]:checked").val()==1){
			$("#choosePartnerDiv").hide();
			$("#companyApplyPartner").show();
		}
		if($("input[name=choosePartner]:checked").val()==2){
			$("#choosePartnerDiv").hide();
			$("#personApplyPartner").show();
		}
	});
	$("#companyApplySub").click(function(){
		
		if(!$.trim($("#apPaCompanyUserName").val())){
			showInfo("请填写联系人姓名","error");
			return;
		}
		if($.trim($("#apPaCompanyUserName").val()).length>=11){
			showInfo("联系人姓名必须小于11个字","error");
			return;
		}
		if(!$.trim($("#apPaCompanyUserPhone").val())){
			showInfo("请填写联系电话","error");
			return;
		}
		var sMobile = $.trim($("#apPaCompanyUserPhone").val());
			 if(!(/^([0-9]{3,4}-)?[0-9]{7,8}$/.test(sMobile) || /^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|17[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/.test(sMobile))){
				 showInfo("请正确填写电话格式","error")
				 $("#apPaCompanyUserPhone").focus();
				 return;
			 }
			 if($.trim($("#apPaCompanyName").val()).length>=50){
					showInfo("企业名称长度必须小于50","error");
					return;
				}

		  var myRegex = /@.*\.[a-z]{2,6}/;
	      var email = $("#apPaCompanyUserEmail").val();
	      email = email.replace(/^ | $/g,"");
	      email = email.replace(/^\.*|\.*$/g,"");
	      email = email.toLowerCase();
	      //验证电子邮件的有效性
	      if(email.length>0){
	    	  if (!myRegex.test(email))
				{
	    		  showInfo("请输入有效的E-MAIL!","error");
	       		$("#apPaCompanyUserEmail").focus();
				return;
				}  
	      }
         
		$.ajax({
	         type: 'POST',
	         url: "/admin/applyPartner/companyApplyInsert.do" ,
	         dataType: "json",
	         data:$("#companyApplyForm").serialize(),
	         success: function(data){		       
	        		 $("#companyApplyPartner").hide();
					$("#companyApplyRemind").show();
	        		
	         }
	    });	
	})
	
		var viewFiles = document.getElementById("file1");
	    var viewImg = document.getElementById("fileImg1");
	    var viewFiles2 = document.getElementById("file2");
	    var viewImg2 = document.getElementById("fileImg2");
	    var viewFiles3 = document.getElementById("file3");
	    var viewImg3 = document.getElementById("fileImg3");
	    var viewFiles4 = document.getElementById("file4");
	    var viewImg4 = document.getElementById("fileImg4");
	    function viewFile (file,img) {
	        //通过file.size可以取得图片大小
	        var reader = new FileReader();
	        reader.onload = function( evt ){
	        	//alert(evt.target.result);
	        	img.src = evt.target.result;
	        }
	        reader.readAsDataURL(file);
	    }
	    viewFiles.addEventListener("change", function () {
	        //通过 this.files 取到 FileList ，这里只有一个
	        //alert(this.files[0])
	        viewFile(this.files[0],viewImg);
	    }, false);
	    viewFiles2.addEventListener("change", function () {
	        viewFile(this.files[0],viewImg2);
	    }, false);
	    viewFiles3.addEventListener("change", function () {
	        viewFile(this.files[0],viewImg3);
	    }, false);
	    viewFiles4.addEventListener("change", function () {
	        viewFile(this.files[0],viewImg4);
	    }, false); 

	    
	    function showRequest(formData, jqForm, options) { 
		    return true;  
		}
		function showResponse(responseText, statusText)  {  
			var data=responseText;
			if(data.code=="OK"){		   		
	 		
			$("#personApplyPartner").hide();
    		$("#personApplyRemind").show();
	   		/*  $("#personApplyForm").reset(); */
	   	 }else{
	   		 showInfo(data.msg,"error");
	   	 }	
		}
		
		var options = {  
	        beforeSubmit:  showRequest,  //提交前处理 
	        success:       showResponse,  //处理完成 
	        /* resetForm: true,   */
	        dataType:  'json'  
	    };  
	 
	    $('#personApplySub').click(function() {
			if($("input[name='apPaAgreement']:checked").val()!=1){
				showInfo("请同意合作协议","error");			
				return;
			}
	    	var obj=$("#personApplyForm").serializeObject();
			if($.trim(obj.apPaServiceQQ)){
				if($.trim(obj.apPaServiceQQ).length>=13){
					showInfo("客服QQ必须小于13个字","error");
					return;
				} 
				if(isNaN(parseInt($("input[name=apPaServiceQQ]").val()))){
					showInfo("客服QQ只能是数字","error");
					return;
				};
			}
			if(!$.trim(obj.apPaUserName)){
				showInfo("请填写联系人姓名","error");
				return;
			}
			if($.trim(obj.apPaUserName).length>=11){
				showInfo("联系人姓名必须小于11个字","error");
				return;
			} 
			if(!$.trim(obj.apPaUserPhone)){
				showInfo("请填写联系人电话","error");
				return;
			}
			var personMobile = $.trim(obj.apPaUserPhone);
			 if(!(/^([0-9]{3,4}-)?[0-9]{7,8}$/.test(personMobile) || /^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|17[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/.test(personMobile))){
				 showInfo("请正确填写电话格式","error")
				 $("#apPaCompanyUserPhone").focus();
				 return;
			 }
			 if($.trim($("input[name=apPaPointName]").val()).length>15){
					showInfo("站点名称长度必须小于15","error");
					return;
				}
			 
			
			if(isNaN(parseInt(obj.apPaUserPhone))){
				showInfo("请正确填写电话格式","error");
				return;
			};
			if(!(($.trim($("input[name=apPaProvinceAddress]").val()))&&($.trim($("input[name=apPaCityAddress]").val()))&&($.trim($("input[name=apPaAreaAddress]").val()))&&($.trim($("input[name=apPaStreetAddress]").val()))&&($.trim($("input[name=apPaRoomNumber]").val())))){
				showInfo("请填写完整地址","error");
				return;
			}
			if($.trim($("input[name=apPaProvinceAddress]").val()).length>5){
				showInfo("省份名字数必须小于6","error");
				return;
			}
			if($.trim($("input[name=apPaCityAddress]").val()).length>7){
				showInfo("城市名字数必须小于8","error");
				return;
			}
			if($.trim($("input[name=apPaAreaAddress]").val()).length>7){
				showInfo("地区名字数必须小于8","error");
				return;
			}
			if($.trim($("input[name=apPaStreetAddress]").val()).length>9){
				showInfo("街道名字数必须小于10","error");
				return;
			}
			if($.trim($("input[name=apPaRoomNumber]").val()).length>19){
				showInfo("车位具体位置名字数必须小于20","error");
				return;
			}
	    	$("#apPaStationAddr").val($("#apPaProvinceAddress").val()+"省"+$("#apPaCityAddress").val()+"市"+$("#apPaAreaAddress").val()+"区"+$("#apPaStreetAddress").val()+"街道"+$("#apPaRoomNumber").val());
			if(!$.trim(obj.apPaStationType)){
				showInfo("请选择接站点类型","error");
				return;
			}
			if(!$.trim(obj.apPaInstallation)){
				showInfo("请选择是否已安装电桩","error");
				return;
			}
			if(!$.trim($("input[name=file1]").val())){
				showInfo("请上传分享图片","error");
				return;
			}						
			if($("input[name=apPaStationType]:checked").val()==3){
				if($.trim($("input[name=apPaStationTypeText]")).length>=16){
					showInfo("站点类型字数必须小于16","error");
					return;
				};			
				$("input[name=apPaStationTypeText]").val($("#apPaStationTypeOther").val());
			}
			
			var arr1 = [];
            $('input[name="apPaPileNum"]:checked').each(function(){ 
            	arr1.push($(this).val());             
            })           			
				for(var i = 0;i<arr1.length;i++){					
					if(arr1[i]==1){	
						if(isNaN(parseInt($("#apPaAcPileNumText").val()))){
							showInfo("交流电桩数量请填写数字","error");
							return;
						};
						if($.trim($("input[name=apPaAcPileNumText]").val()).length>5){
							showInfo("交流电桩数量字数小于6","error");
							return;
						};
						$("input[name=apPaAcPileNum]").val($("#apPaAcPileNumText").val())
					}else if(arr1[i]==2){
						if(isNaN(parseInt($("#apPaDcPileNumText").val()))){
							showInfo("直流电桩数量请填写数字","error");
							return;
						};
						if($.trim($("input[name=apPaDcPileNumText]").val()).length>5){
							showInfo("交流电桩数量字数小于6","error");
							return;
						};
						$("input[name=apPaDcPileNum]").val($("#apPaDcPileNumText").val())
					}
				}			
			
			var arr2 = [];
            $('input[name="apPaOpenTime"]:checked').each(function(){ 
            	arr2.push($(this).val());             
            })            	
				for(var i = 0;i<arr2.length;i++){					
					if(arr2[i]==1){
						if($.trim($("input[name=apPaOpenTimeWorkDayText]").val()).length>24){
							showInfo("工作日时间段字数小于25","error");
							return;
						};
						$("input[name=apPaOpenTimeWorkDay]").val($("#apPaOpenTimeWorkDayText").val())
					}else if(arr2[i]==2){
						if($.trim($("input[name=apPaOpenTimeHolidayText]").val()).length>24){
							showInfo("节假日时间段字数小于25","error");
							return;
						};
						$("input[name=apPaOpenTimeHoliday]").val($("#apPaOpenTimeHolidayText").val())
					}
				}
            if($.trim($("input[name=apPaCarTypeMatch]").val()).length>15){
				showInfo("匹配车型长度必须小于15","error");
				return;
			}
			var arr = [];
            $('input[name="apPaChargingCost"]:checked').each(function(){ 
            	arr.push($(this).val());             
            })
				for(var i = 0;i<arr.length;i++){					
					if(arr[i]==1){
						if($.trim($("input[name=apPaChargingFeeText]").val()).length>6){
							showInfo("电费字数小于6","error");
							return;
						};
						$("input[name=apPaChargingFee]").val($("#apPaChargingFeeText").val())
					}else if(arr[i]==2){
						if($.trim($("input[name=apPaServiceFeeText]").val()).length>6){
							showInfo("服务费字数小于6","error");
							return;
						};
						$("input[name=apPaServiceFee]").val($("#apPaServiceFeeText").val())
					}else if(arr[i]==3){
						if($.trim($("input[name=apPaParkingFeeText]").val()).length>6){
							showInfo("停车费字数小于6","error");
							return;
						};
						$("input[name=apPaParkingFee]").val($("#apPaParkingFeeText").val())
					}
				}
															
	        $("#personApplyForm").ajaxSubmit(options);  
	    });
	    
	    
	    	  
});
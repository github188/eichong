var errorFlag=true;
function deleteFile(imageUrl,obj,referenceId){
		var fileName=imageUrl.substring(imageUrl.lastIndexOf("/")+1,imageUrl.length);
		var businessType=$("#businessType").val();
		$.ajax({
			type : 'post',
			url : basepath+"/common/deleteFile.do",
			dataType : "json",
			data:{
					businessType:businessType,
					referenceId:referenceId,
					fileName:fileName
			},
			success : function(data) {
				if (data.statusCode==200) {
					alertMsg.correct(data.message);
					$(obj).parent().remove();
				} else {
					alertMsg.error(data.message);
				}
			}
		});	
	}
function deleteFile_list(imageUrl,obj,referenceId){
	var fileName=imageUrl.substring(imageUrl.lastIndexOf("/")+1,imageUrl.length);
	var businessType=$("#splashListType").val();
	$.ajax({
		type : 'post',
		url : basepath+"/common/deleteFile.do",
		dataType : "json",
		data:{
				businessType:businessType,
				referenceId:referenceId,
				fileName:fileName
		},
		success : function(data) {
			if (data.statusCode==200) {
				alertMsg.correct(data.message);
				$(obj).parent().remove();
			} else {
				alertMsg.error(data.message);
			}
		}
	});	
}
	function electricFileUploadSuccess(file, data, response){
		var obj=JSON.parse(data);
		var dataId = obj.message;
		//alert(dataId);
		$("#fileId").val($("#fileId").val()+dataId+",");
		var url=obj.navTabId;
		$("#imageDD").prepend('<t><img  src="'+url+'" style="width: 150px; height: 150px;" />'
				+'<a href="javascript:void(0);" onclick="deleteFile(\''+url+'\',this,\''+dataId+'\')">删除</a></t>');
	}
	
	function splashUploadSuccess_list(file, data, response){
		var obj=JSON.parse(data);
		var dataId = obj.message;
		//alert(dataId);
		$("#fileId_list").val($("#fileId_list").val()+dataId+",");
		var url=obj.navTabId;
		$("#imageDD_list").prepend('<t><img  src="'+url+'" style="width: 150px; height: 150px;" />'
				+'<a href="javascript:void(0);" onclick="deleteFile_list(\''+url+'\',this,\''+dataId+'\')">删除</a></t>');
	}
	function electricFileUploadComplete(queueData) {
		var msg = "The total number of files uploaded: "+queueData.uploadsSuccessful+"<br/>"
		+ "The total number of errors while uploading: "+queueData.uploadsErrored+"<br/>"
		+ "The total number of bytes uploaded: "+queueData.queueBytesUploaded+"<br/>"
		+ "The average speed of all uploaded files: "+queueData.averageSpeed;
		if (queueData.uploadsErrored) {
			alertMsg.error(msg);
		} else {
			if(errorFlag){
				alertMsg.correct("文件上传成功");
			}
			
		}
	}
	
	function electricFileUploadStart(file) {
		errorFlag=true;
	    var fileSize=$("#imageDD t").length;
	    if(fileSize>=6){ 
	      this.cancelUpload(file.id); 
	      $('#' + file.id).remove();
	      alertMsg.error("只能发最多6个文件");
	      errorFlag=false;
	      return;
	    } 
	} 
	function splashUploadStart_list(file) {
		errorFlag=true;
	    var fileSize=$("#imageDD_list t").length;
	    if(fileSize>=6){ 
	      this.cancelUpload(file.id); 
	      $('#' + file.id).remove();
	      alertMsg.error("只能发最多6个文件");
	      errorFlag=false;
	      return;
	    } 
	} 
	function uploadify_onSelectError(file, errorCode, errorMsg) {
        var msgText = "上传失败\n";
        switch (errorCode) {
            case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
                //this.queueData.errorMsg = "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
                msgText += "每次最多上传 " + this.settings.queueSizeLimit + "个文件";
                break;
            case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:
                msgText += "文件大小超过限制( " + this.settings.fileSizeLimit + " )";
                break;
            case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:
                msgText += "文件大小为0";
                break;
            case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:
                msgText += "文件格式不正确，仅限 " + this.settings.fileTypeExts;
                break;
            default:
                msgText += "错误代码：" + errorCode + "\n" + errorMsg;
        }
        alertMsg.error(msgText);
    }
		 
	function uploadify_onUploadError(file, errorCode, errorMsg, errorString) {
	        // 手工取消不弹出提示
	        if (errorCode == SWFUpload.UPLOAD_ERROR.FILE_CANCELLED
	                || errorCode == SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED) {
	            return;
	        }
	        var msgText = "上传失败\n";
	        switch (errorCode) {
	            case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:
	                msgText += "HTTP 错误\n" + errorMsg;
	                break;
	            case SWFUpload.UPLOAD_ERROR.MISSING_UPLOAD_URL:
	                msgText += "上传文件丢失，请重新上传";
	                break;
	            case SWFUpload.UPLOAD_ERROR.IO_ERROR:
	                msgText += "IO错误";
	                break;
	            case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:
	                msgText += "安全性错误\n" + errorMsg;
	                break;
	            case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:
	                msgText += "每次最多上传 " + this.settings.uploadLimit + "个";
	                break;
	            case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:
	                msgText += errorMsg;
	                break;
	            case SWFUpload.UPLOAD_ERROR.SPECIFIED_FILE_ID_NOT_FOUND:
	                msgText += "找不到指定文件，请重新操作";
	                break;
	            case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:
	                msgText += "参数错误";
	                break;
	            default:
	                msgText += "文件:" + file.name + "\n错误码:" + errorCode + "\n"
	                        + errorMsg + "\n" + errorString;
	        }
	        alertMsg.error(msgText);
	    }

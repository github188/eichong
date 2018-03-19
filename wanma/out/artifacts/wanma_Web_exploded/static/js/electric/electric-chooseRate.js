function sendRate(obj){
		var electricRateChoose = $("#electricRateChoose").val();
		var ids = $("#chooseRateIds").val();
		ids = ids+":"+electricRateChoose;
		$("#chooseRateParamStr").val(ids);
		var token=getToken();
		$("#chooseRateT").val(token);
		var form=$("#chooseRateForm")[0];
		validateCallback(form, navTabAjaxDone);
}


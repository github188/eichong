$("body").on("change",".epNum",function(){
	var value=$(this).val();
	var count=0;
	$(".epNum").each(function(){
    	if($(this).val()==value){
    		count++;
    	}
  	});
	if(count>1){
		alertMsg.error("此电桩的编号重复，请修改。");
		$(this).val("");
	}
 });
 function selectPile(args){
	var content="";
	var length=args["pkElectricpile"].split(",").length;
	var pkElectricpileArr=args["pkElectricpile"].split(",");
	var elpiElectricPileCodeArr=args["elpiElectricPileCode"].split(",");
	var elpiElectricpilenameArr=args["elpiElectricpilename"].split(",");
	var elpiStateArr=args["elpiState"].split(",");
	var chargingModeNameArr=args["chargingModeName"].split(",");
	var powerNameArr=args["powerName"].split(",");
	var typeNameArr=args["typeName"].split(",");
	var powerSizeNameArr=args["powerSizeName"].split(",");
	var elPiRateInformationIdArr=args["rateInformationId"].split(",");
	var content="";
	$(".newTr").remove();
	var oldTrLength=$("#selectPileTable tr").length;
	var epNum=getMaxNum();
	for (var i=0;i<length;i++){
			content+='<tr class="newTr border-left" rel='+pkElectricpileArr[i]+' align="center">';
			content+='<td style="width: 5%">'+(oldTrLength+i+1)+'</td>';	 
			content+='<td style="width: 15%">'+(elpiElectricPileCodeArr[i]?elpiElectricPileCodeArr[i]:"")+'</td>';	 
			content+='<td style="width: 10%"><input type="hidden" name="pkElectricpile" value="'+pkElectricpileArr[i]+'" />'
			content+='<input type="hidden" name="index" value="'+(oldTrLength+i+1)+'" />'
			content+='<input type="hidden" name="oldEpNum" value="-1" />'
			content+='<input type="hidden" name="electricPileCode" value="'+elpiElectricPileCodeArr[i]+'" />'
			content+='<input class="epNum" type="text" name="epNum" value="'+(epNum+i+1)+'" size="1" style="float:none;" />号桩</td>';	 
			content+='<td style="width: 10%">'+elpiElectricpilenameArr[i]+'</td>';	 
			content+='<td style="width: 10%">'+elpiStateArr[i]+'</td>';	 
			content+='<td style="width: 10%">'+chargingModeNameArr[i]+'</td>';	 
			content+='<td style="width: 10%">'+powerNameArr[i]+'</td>';	 
			content+='<td style="width: 10%">'+typeNameArr[i]+'</td>';	 
			content+='<td style="width: 10%">'+powerSizeNameArr[i]+'</td>';
			content+='<td style="width: 5%"><input type="hidden" name="oldRateId" value="'+elPiRateInformationIdArr[i]+'"/><input type="text" name="rateId" value="'+elPiRateInformationIdArr[i]+'" size="1" style="float: none;" /></td>';
			content+='<td style="width: 5%"></td>';
			content+='</tr>';
	}
	$("#selectPileTable").append(content);
 }
 
 function getMaxNum(){
	 var epNum=0;
	 $(".epNum").each(function(){
		var value=parseInt($(this).val());
		 if(value>epNum){
			epNum=value;
		}
	});
	 return epNum;
 }
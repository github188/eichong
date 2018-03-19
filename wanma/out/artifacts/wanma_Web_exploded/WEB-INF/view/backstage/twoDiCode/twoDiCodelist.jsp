<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<script type="text/javascript">	

		function xiafa2(obj){
			
			var $this = $(obj);
			var targetType = $this.attr("targetType");
			var selectedIds = "paramStrs";
			var ids = getIds2("paramStrs", targetType);
			if (!ids) {
				alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
				return false;
			}else if(ids=="-1"){
				alertMsg.error("GATE服务器ID为0，请重新选择！");
				return false;
			}
			function doPost(){
				$.ajax({
					type:'POST', 
					url:$this.attr('url'), 
					data: {paramStrs:ids,requestType:1},
					dataType:'json',
					cache: false,
					success: function(data) {
		               if(data.statusCode==200){
		            	   alertMsg.correct(data.message);
		               }else{
		            	   alertMsg.error(data.message);
		               }
		            }
				});
				
			}	
			var title = $this.attr("title");
			alertMsg.confirm(title, {okCall: doPost});
			return false;
		}
		
		function getIds2(selectedIds, targetType){
			//data-value格式:电桩编码:枪口编号,电桩编码:枪口编号
			var ids = "";
			var  codeGateId="";
			var $box = targetType == "dialog" ? $.pdialog.getCurrent() : navTab.getCurrentPanel();
			$box.find("input:checked").filter("[name='"+selectedIds+"']").each(function(i){				
				var val = $(this).attr("value");
				//电桩在线且连接	
				  codeGateId = $(this).attr("codeGate-id");
				if(codeGateId<=0){
					ids = "-1";
					return false;
				}
					var arr=val.split(":");
					//电桩桩体编码存在
					if(arr[0]){
						var str=arr[0]+":"+arr[1];
						ids += i==0 ? str : ","+str;
					}				
			});
			return ids;
		}

	
   $("#TwoDiCodeselProvince").change(function(){
	  	 $("#TwoDiCodeselDistrict").html('<option value="">--请选择区/县--</option>');
		var provinceId=$(this).val();
		if(!provinceId){
			$("#TwoDiCodeselCity").html('<option value="">--请选择城市--</option>');
			return;
		}
		$.ajax({
			type:'POST', 
			url:basepath+"/admin/rateinfo/searchCityList.do", 
			data: {provinceId:provinceId},
			dataType:'json',
			cache: false,
			success: function(datas) {
				if(datas.status==100){
					var data=datas.data;
					var content='<option value="">--请选择城市--</option>';
					for(var i=0;i<data.length;i++){
						content+='<option value="'+data[i].CITY_ID+'">'+data[i].CITY_NAME+'</option>';
						
					}
					 $("#TwoDiCodeselCity").html(content);
				}else{
					alertMsg.error(datas.msg);
				}
				
           }
		});
	});
	
	$("#TwoDiCodeselCity").change(function(){
		
		var cityId=$(this).val();
		if(!cityId){
			$("#TwoDiCodeselDistrict").html('<option value="">--请选择区/县--</option>');
			return;
		}
		$.ajax({
			type:'POST', 
			url:basepath+"/admin/rateinfo/searchAreaList.do", 
			data: {cityId:cityId},
			dataType:'json',
			cache: false,
			success: function(datas) {
				if(datas.status==100){
					var data=datas.data;
					var content='<option value="">--请选择区/县--</option>';
					for(var i=0;i<data.length;i++){
						content+='<option value="'+data[i].area_id+'">'+data[i].area_name+'</option>';
					}
					 $("#TwoDiCodeselDistrict").html(content);
				}else{
					alertMsg.error(datas.msg);
				}
				
           }
		});
	});



	 function xiafaCallback(data){		 
			if(data.status==100){
	     	   alertMsg.correct(data.msg);
	        }else{
	     	   alertMsg.error(data.msg);
	        }
		}
	 
</script>
<div class="pageHeader">
	<form id="pagerForm" method="post" action="TwoDiCode/getTwoDiCodeList.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
			<input type="hidden" id="provinceId"  value="" />
			<input type="hidden" id="cityId"      value="" />
			<input type="hidden" id="countyId"    value="" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td>
							<label>区域选择</label>
						</td>
						<td colspan="2">
							<select  id="TwoDiCodeselProvince" name="elPi_OwnProvinceCode" class="required" style="float: none">
								<option value="">--请选择省份--</option>
								<c:forEach var="item" items="${provinceMap}">
									<option value="${item.key}"
										${item.key== elPi_OwnProvinceCode ? 'selected="selected"' : ''} >
										${item.value.PROVINCE_NAME}</option>
								</c:forEach>
							</select>
							<select id="TwoDiCodeselCity" name="elPi_OwnCityCode" class="required" style="float: none;">
								 <option value="">--请选择城市--</option>
								 <c:forEach var="item" items="${cityList}">
									<option value="${item.CITY_ID}"
										${item.CITY_ID==elPi_OwnCityCode  ? 'selected="selected"' : ''} >
										${item.CITY_NAME}</option>
								</c:forEach>
							</select>
							<select id="TwoDiCodeselDistrict" name="elPi_OwnCountyCode" class="required" name="startm" style="float: none;">
								<option value="">--请选择区/县--</option>
								<c:forEach var="item" items="${areaList}">
									<option value="${item.area_id}"
										${item.area_id==elPi_OwnCountyCode ? 'selected="selected"' : ''} >
										${item.area_name}</option>
								</c:forEach>
							</select>	
						</td>
						<td style="align: left"><label style="align: left"><bmtag:message
									messageKey="桩体地址" /> </label></td>
						<td><input name="electricPileAddress"
							value="${params.electricPileAddress}" /></td>		
						<td align="right"><bmtag:button
								messageKey="common.button.search" type="submit"
								id="formSubmitter" /></td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="delete"  rel="paramStrs"
				postType="string" title="确定要下发吗？" onclick="xiafa2(this)" 
				url="${webroot}/common/sendRequest.do"
				><span>下发二维码</span>
			</a>
			</li>
		</ul>
	</div>
	<table class="table"  width="100%" layoutH="117">
		<thead>
			<tr align="center">
			   <th width="10"><input type="checkbox" group="paramStrs" class="checkboxCtrl"></th>
			   	<th><bmtag:message messageKey="电桩编号"/></th>
				<th><bmtag:message messageKey="电桩名" /></th>
				<th><bmtag:message messageKey="电桩地址" /></th>
				<th><bmtag:message messageKey="充电枪编号" /></th>			
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${TwoDiCodeList}" var="TwoDiCode" varStatus="status">
				<tr target="id" rel="${TwoDiCode.elPi_ElectricPileCode}:${TwoDiCode.eph_num}" align="center">
				    <td><input  name="paramStrs"  codeGate-id="${TwoDiCode.elPi_GateId}"  value="${TwoDiCode.elPi_ElectricPileCode}:${TwoDiCode.ePHe_ElectricpileHeadId}" type="checkbox"></td>					
					<td>${TwoDiCode.elPi_ElectricPileCode}</td>
					<td>${TwoDiCode.elPi_ElectricPileName}</td>
					<td>${TwoDiCode.elPi_ElectricPileAddress}</td>
					<td>${TwoDiCode.ePHe_ElectricpileHeadId}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span> <!-- <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="4" ${pager.numPerPage==4?"selected":""}>4</option>
					<option value="20" ${pager.numPerPage==20?"selected":""}>20</option>
					<option value="100" ${pager.numPerPage==100?"selected":""}>100</option>
					<option value="200" ${pager.numPerPage==200?"selected":""}>200</option>
			</select>  -->共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>




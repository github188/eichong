<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript">
	var webroot = "${webroot}";
	var myDate = new Date();
	var array=new Array();
	function addNew(){
	
		var sel=document.getElementsByName("pkCouponVariety")[0];
		var pkCouponVariety= sel.options[sel.options.selectedIndex].value//你要的值
		var couponName= sel.options[sel.options.selectedIndex].innerHTML//你要的文本
		var prizeType = document.getElementById('prizeType').value;
	//	var pkCouponVariety = document.getElementById('pkCouponVariety').value;
		
		
		var num = document.getElementById('num').value;
		if(num==""){
			alertMsg.error("请输入奖品数量！");
			return;
		}
		for (var i=0;i<array.length;i++){
			
			if(array[i]==pkCouponVariety){
				alertMsg.error("同一种现金券品种只能添加一次！");
			    return;
			}
		}
		array.push(pkCouponVariety);
		
		if (prizeType="1"){
			prizeTypeName="现金券";
		}else if (prizeType="2"){
			prizeTypeName="折扣券";
		}
		
		num_name= ","+ num+"张";
		var zhanshi=prizeTypeName+couponName+num_name;
		var awardDiv = navTab.getCurrentPanel().find("#awardNum");
	    var oldNum = parseInt(awardDiv.val());
	    awardDiv.val(oldNum+1); 
		//设置list参数下标
	    var listIndex = oldNum
	    var row_obj = '<tr><td><label id="headList['+listIndex+'].ephNum" style="width: 150px;">'+zhanshi+'</label></td>'
	                     +'<td><input hidden="true" value='+prizeType+' name="headList['+listIndex+'].actsType" type="text"></td>'
		                 +'<td><input hidden="true" value='+num+' name="headList['+listIndex+'].num" type="text"></td>'
		                 +'<td><input hidden="true" value='+pkCouponVariety+' name="headList['+listIndex+'].pkCouponVariety" type="text"></td>'
					 +'<td><div class="button"><div class="buttonContent" style="width: 30px"><button type="button" onclick="removeTr(this,'+pkCouponVariety+')">删除</button></div></div></td></tr>';
	    var rowHeadDiv = navTab.getCurrentPanel().find("#row_head");
	    rowHeadDiv.prepend(row_obj); // 插入行 
	}
	
	/***删除当前行***/
	function removeTr(this_e,pkCouponVariety){
		
		array.remove(pkCouponVariety);
		//array.splice(pkCouponVariety,1);
		var awardDiv = navTab.getCurrentPanel().find("#awardNum");
		var rowHeadDiv = navTab.getCurrentPanel().find("#row_head");
	    var oldNum = parseInt(awardDiv.val());
	  /*   if(oldNum == 1){
	    	alertMsg.warn("最后一行不可删除！");
	    	return;
	    } */
	    var $thTr = $(this_e).parent().parent().parent().parent();
		$thTr.remove();
		var newNum = oldNum-1;
		awardDiv.val(newNum);
	    //重新设置行下标
		rowHeadDiv.find("tr").each(function(i){
			var $th = $(this);
			$th.attr("index-data",newNum-1-i);
		});
	    //重新设置list参数下标
	    rowHeadDiv.find(".required").each(function(i){
			var $th = $(this);
			var listIndex = $th.parent().parent().attr("index-data");
			var paramName = $th.attr("name");
			var needReplaceStr = paramName.substring(9,10);
			$th.attr("name",paramName.replace(needReplaceStr,listIndex));
		});
	}
	
	function getValue(ele) {
		var value = ele.value;
	}
	
	Array.prototype.indexOf = function(val) { for (var i = 0; i < this.length; i++) { if (this[i] == val) return i; } return -1; };
	Array.prototype.remove = function(val) { var index = this.indexOf(val); if (index > -1) { this.splice(index, 1); } };
	
	
	function formatJson(){
		var actBegindate_f1 = $("#actBegindate_f1").val();
		var actEnddate_f1 = $("#actEnddate_f1").val();
		var actCouponEnddate_f1 = $("#actCouponEnddate_f1").val();
		if(actCouponEnddate_f1<actEnddate_f1){
			alertMsg.error("到期时间不能小于结束时间！");
			return;
		}
		alertMsg.confirm("确认增加活动后，系统会根据选择的奖品，在系统中生成优惠券。如，活动选择1000张，满10件2的现金券，系统会生成1000张优惠券，可在优惠券明细中查看。", {
			okCall: function(){
		  $("#chaForm").submit();
			}
		});
	}
</script>
<head>

 <style type="text/css">
.divcss5{ border-left:10px solid #00F} ;
 </style>
 </head>
<h2 class="contentTitle">新增渠道活动</h2>
<div class="pageContent">
	<form method="post" action="activity/saveActivity.do" id="chaForm"
		class="required-validate" enctype="multipart/form-data" 
		onsubmit="return iframeCallback(this, navTabAjaxDone)"> 
		<input type="hidden" name="elpiPoweruser" value="3"/>
		<div class="pageFormContent nowrap" layoutH="97">
			<dl>
				<dt style="width:80px;">名称</dt>
				<dd>
					<input name="actActivityname" class="textInput required"
						maxlength="50" style="width: 165px;" remote="<%=request.getContextPath()%>/admin/activity/checkUnique.do?tName=tbl_activity&tProperty=act_ActivityName&property=actActivityname&pkTProperty=act_Type&pkTValue=2"/> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt style="width:80px;">渠道</dt>
				<dd>
					<%-- <select name="actChanneltype" class="select_Style" >
						<option value="1"
									${TblActivity.actChanneltype=='1'
									? 'selected' : ''}>吉利集团</option>
								<option value="2"
									${TblActivity.actChanneltype=='2'
									? 'selected' : ''}>车分享</option>
								<option value="3"
									${TblActivity.actChanneltype=='3'
									? 'selected' : ''}>爱充网</option>
								
					</select> --%>  <select
							name="actChanneltype" class="select_Style" style="width: 150px;">
							
								<c:forEach var="item" items="${ChannelType}">
									<option value="${item.pkCompanyId}"
										${item.pkCompanyId==TblCoupon.pkCompanyId?'selected="selected"' : ''}>${item.cpyCompanyName}</option>
								</c:forEach>
						</select>
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt style="width:80px;">开始时间</dt>
				<dd>
					 <input  class="textInput required" id="actBegindate_f1" name="actBegindates" value="${TblActivity.actBegindates}" class="date"  style="width:155px"
						 onClick="WdatePicker({el:'actBegindate_f1',minDate:'${nowday}',maxDate:'#F{$dp.$D(\'actEnddate_f1\')}'})"> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt style="width:80px;">结束时间</dt>
				<dd>
					<input  class="textInput required" id="actEnddate_f1" name="actEnddates" value="${TblActivity.actEnddates}" class="date"  style="width:155px"
			             onClick="WdatePicker({el:'actEnddate_f1',minDate:'#F{$dp.$D(\'actBegindate_f1\')}'})"><span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt style="width:80px;">到期时间</dt>
				<dd>
				<input class="textInput required" id="actCouponEnddate_f1" name="actCouponEndDates" value="${TblActivity.actCouponEndDates}" class="date"  style="width:155px"
			             onClick="WdatePicker({el:'actCouponEnddate_f1',minDate:'#F{$dp.$D(\'actEnddate_f1\')}'})"><span class="info"></span>
					
				</dd>
			</dl>
			<dl>
				<dt>
					<strong>选择奖品</strong>
				</dt>
			</dl>
			
			<dl>
			<dt style="width:30px;">&nbsp;</dt>
				<dd>
					 <select name="prizeType" id="prizeType"  class="select_Style" >
						<option value="1" >现金券</option>
					</select> <span class="info"></span> 
				</dd>
			</dl>
			<dl>
			<dt style="width:30px;">&nbsp;</dt>
				<dd>
					<select name="pkCouponVariety" id="pkCouponVariety" class="select_Style" onchange="getValue(this)">
							<c:forEach var="item" items="${couponType}">
									<option value="${item.pkCouponvariety}"
										${item.pkCouponvariety==TblCoupon.pkCouponvariety?'selected="selected"' : ''}>${item.covaActivityname}</option>
								</c:forEach>
								
								<%-- 	<c:forEach var="item" items="${chargeList}">
						</c:forEach> --%>
					</select> <span class="info"></span>
				</dd>
			</dl>
			<dl>
			<dt style="width:30px;">&nbsp;</dt>
				<dd>
					<input name="num"  id="num"class="textInput required" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"
						maxlength="20" style="width: 165px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
			<dd>
				<dt style="width:80px;">&nbsp;</dt>
					<button type="button" onclick="addNew()">新增</button>
				</dd>
			</dl>
			<dl><dd><input id="awardNum" name=awardNum 
						 max="4" value="0"hidden="true"/></dd></dl>
			<dl>
				<dt>
					<strong>已选择的奖品：</strong>
				</dt>
			</dl>
			<dl>
				<dt>
				<div>
					<table  width="250px;" >
						
						<tbody id="row_head">
							<!--  <tr>
								<td style="width:70%;">直流现金券10减2，5000张</td>
								<td style="width:30%;"><div class="button"><div class="buttonContent" style="width: 30px"><button type="button" onclick="removeTr(this)">删除</button></div></div></td>
							</tr> -->
						</tbody>
					</table>
					</div style="border:1px dashed #000;">
				</dt>
			</dl>
			<dl>
				<dt>
					<strong>备注</strong>
				</dt>
			</dl>
			<dl >
				<dt style="width:30px;">&nbsp;</dt>
			<dd>
				<textarea name="actRemark" rows="13" cols="70" id="textarea"></textarea>
					</dd>
			</dl>
		</div>
		
		<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit" onclick="formatJson()"
						type="button" id="formSubmitter" />
				</li>
				<li><bmtag:button messageKey="common.button.back" type="button"
						dwzClass="close" />
				</li>
			</ul>
		</div>
	</form>
</div>

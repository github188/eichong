<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<script type="text/javascript">
	var webroot = "${webroot}";
	function checkDetails() {
	}

</script>
<style type="text/css"> 
*{ margin:0; padding:0;}  
	dl{
		clear:both; 
		margin-bottom:5px;
		height:30px;
	}  
	dt{
	width:140px;
	padding:2px 5px;
	float:left;
	}
	dd{
		width:160px;
		padding:2px 5px;
		float:left;
	}  
	h1{
		clear:both;
		font-size:14px;
	} 
	.btn{
	    
		width: 60px;
	    height: 30px;
	    margin: 0;
	    padding: 0 0 5px 0;
	    border:0;
	    font-size: 12px;
	    font-weight: bold;
	    background: none;
	    cursor: pointer;
	} 	
</style>

<div class="pageContent">
	<form method="post" action="moneyRecord/getRecordRefund.do" 
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return navTabSearch(this);">
		<div>
			<dl>
				<dt><bmtag:message messageKey="用户账号" /></dt>
				<dd id="userAccount">${params.user_account }</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="真实姓名" /></dt>
				<dd>${params.usIn_FacticityName }</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="账户余额（元）" /></dt>
				<dd id="allAccount">${params.all_account}</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="退款金额（元）" /></dt>
				<dd><input id="refundAccount" name="account" maxlength="50" style="width: 160px;" 
				 onkeyup="this.value=/^\d+\.?\d{0,2}$/.test(this.value) ? this.value : ''" /></dd>
				<dd>				
				<div id="refundOver" style='color:#FF0000;position:absolute;display:none'>您输入的金额超出账户余额资金</div>
				</dd>
			</dl>
			<dl>
				<dt></dt>
				<dd><label style='color:#FF0000;' >* 退款前请确认账户及金额</label></dd>
			</dl>
			<dl>
				<dt></dt>
				 <dd>
					 <div class="button">
					 	<div class="buttonContent" >
					 		<input id="refundBtn" class="btn" value="确认退款"  type="button" disabled/>
						 </div>
					 </div>
				 </dd>
			</dl>
		</div>
	</form>
</div>
<script type="text/javascript">
$("input[name=account]").on("input",function(evt){
	  if($(this).val().trim().length){
	    
	    $("#refundBtn").removeAttr("disabled");
	  }else{
	    $("#refundBtn").prop("disabled","disabled");
	  }
	});
var allAccount ="";
var refundAccount="";
$('#refundBtn').click(function(){
	refundAccount = document.getElementById("refundAccount").value;
	allAccount = document.getElementById("allAccount").innerText;
	 if(parseFloat(refundAccount)>parseFloat(allAccount)){   
		$("#refundOver").show();
		return;
	}else{
		if(refundAccount > 0){
			var userAccount=document.getElementById("userAccount").innerText;
			alertMsg.confirm("请确认是否要退款"+refundAccount+"元?", {
				okCall: function(){
					$.ajax({
						type : 'post',
						url : basepath+ "/admin/moneyRecord/refundByUserAccount.do",
						dataType : "json",
						data :{
							userAccount:userAccount,
							refundAccount:refundAccount
						},
						success : function(json) {
							navTab.closeCurrentTab();
							navTab.reloadFlag("getMoneyRecordList");
							alertMsg.info('退款成功');
						}
					});
				}
			
			})
		}
	}
	
});


</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript">
	var webroot = "${webroot}";

	function ajaxDoneCallback(json) {
	}
	
	function getNowFormatDate(){
	    var day = new Date();
	    var Year = 0;
	    var Month = 0;
	    var Day = 0;
	    var CurrentDate = "";
	    Year= day.getFullYear();//支持IE和火狐浏览器.
	    Month= day.getMonth()+1;
	    Day = day.getDate();
	    CurrentDate += Year;
	    if (Month >= 10 ){
	     CurrentDate += Month;
	    }
	    else{
	     CurrentDate += "0" + Month;
	    }
	    if (Day >= 10 ){
	     CurrentDate += Day ;
	    }
	    else{
	     CurrentDate += "0" + Day ;
	    }
	    return CurrentDate;
	 }
</script>
<h2 class="contentTitle">
	<bmtag:message messageKey="编辑商品" />
</h2>
<div class="pageContent">
	<form method="post" action="business/modifyProduct.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="97">
			<input type="hidden" name="pkProduct" value="${tblProduct.pkProduct}" />
			<dl>
				<dt><bmtag:message messageKey="商品名称"/></dt>
				<dd>
					<input name="prodProductname" value="${tblProduct.prodProductname}" style="width: 130px;" class="required"/>
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="商品编号"/></dt>
				<dd>
					<input name="prodProductcode" value="${tblProduct.prodProductcode}" style="width: 130px;" class="required"/>
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="所属类目"/></dt>
				<dd>
					<select name="prodCategoryid" style="width:136px" class="required">
						<c:forEach items="${categoryList}" var="cate">
							<option value="${cate.pkCategory}" ${cate.caName == tblProduct.caName ? 'selected="selected"' : ''}>${cate.caName}</option>
						</c:forEach>
					</select>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="所属品牌"/></dt>
				<dd>
					<select name="prodBrand" style="width:136px" class="required">
						<c:forEach items="${markList}" var="mark">
							<option value="${mark.pkCarmaker}" ${mark.pkCarmaker==tblProduct.prodBrand? 'selected="selected"' : ''}>${mark.makerName}</option>
						</c:forEach>
					</select>
					<span class="info"></span>
				</dd>
				</dl>
			<dl>
				<dt><bmtag:message messageKey="成本价格"/></dt>
				<dd>
					<input name="prodProductprice" value="${tblProduct.prodProductprice }" style="width: 130px;" class="required"/>
					<span class="info"></span>
				</dd>
			</dl>
			 <dl>
				<dt><bmtag:message messageKey="折扣价格"/></dt>
				<dd>
					<input name="prodDiscountprice" value="${tblProduct.prodDiscountprice}" style="width: 130px;" />
					<span class="info"></span>
				</dd>
			</dl>
			  <dl>
				<dt><bmtag:message messageKey="折扣率"/></dt>
				<dd>
					<select name="prodProductdiscount" style="width:136px">
					  <option value="">请选择</option>
						<option value="70" ${ fn:substring(tblProduct.prodProductdiscount,0,2) == '70'?"selected":""}>70%</option>
						<option value="80" ${fn:substring(tblProduct.prodProductdiscount,0,2) == '80'?"selected":""}>80%</option>
						<option value="90" ${ fn:substring(tblProduct.prodProductdiscount,0,2) == '90'?"selected":""}>90%</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="市场价格"/></dt>
				<dd>
					<input name="prodMarketprice" value="${tblProduct.prodMarketprice }" style="width: 130px;" class="required"/>
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="库存数量"/></dt>
				<dd>
					<input name="prodStockquantity" value="${tblProduct.prodStockquantity}" style="width:130px;" class="required"/>
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="上架状态"/></dt>
				<dd>
					<select name="prodProductlsadd" style="width:136px">
						<option value="">--请选择--</option>
						<option value="1" ${tblProduct.prodProductlsadd == 1?"selected":""}>待上架</option>
						<option value="2" ${tblProduct.prodProductlsadd == 2?"selected":""}>已上架</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="基本参数"/></dt>
				<dd>
				<input name="prodParameters" value="${tblProduct.prodParameters}" style="width:130px;"/>
					
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="人工服务"/></dt>
				<dd>
				<input name="prodArtificialservice" value="${tblProduct.prodArtificialservice}" style="width:130px;"/>
					
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="列表图片" />
				</dt>
				<dd>
					<img alt="" src="${tblProduct.prodProductimage}" class="file required" style="float: left;"
							width="100px" height="100px">
					<span class="info"></span>
				</dd>
			</dl>
			<dl style="margin-left: 130px;">
				<bmtag:link href="business/deletePic.do?pkProduct=${tblProduct.pkProduct}"
				target="ajaxTodo" altKey="common.msg.delete.confirm" messageKey="common.icon.delete" dwzClass="delete"/>
				&nbsp;&nbsp;
				<input type="file" name="ListImage" class="file"  style="width:360px;"/>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="详情图片" />
				</dt>
				<dd>
					<img alt="" src="${tblProduct.prodDetailimage}" class="file required" style="float: left;"
							width="100px" height="100px">
					<span class="info"></span>
				</dd>
			</dl>
			<dl style="margin-left: 130px;">
				<bmtag:link href="business/deletePic.do?pkProduct=${tblProduct.pkProduct}"
				target="ajaxTodo" altKey="common.msg.delete.confirm" messageKey="common.icon.delete" dwzClass="delete"/>
				&nbsp;&nbsp;
				<input type="file" name="DetailImage" class="file" style="width:360px;"/>
			</dl>
			
			<dl>
			<dt><bmtag:message messageKey="充电方式"/></dt>
			<dd>
				<select name="prodChargingMode" style="width:136px">
					<c:forEach items="${chargeList}" var="item">
						<option value="${item.pkConfigcontent}" ${tblProduct.prodChargingMode == item.pkConfigcontent ? 'selected="selected"' : ''}>${item.cocoContent}</option>
					</c:forEach>
			</select>
			<span class="info"></span>
			</dd>
		</dl>
		
		<dl>
			<dt><bmtag:message messageKey="接口标准"/></dt>
			<dd>
				<select name="prodPowerInterface" style="width:136px">
					<c:forEach items="${connectorList}" var="item">
						<option value="${item.pkConfigcontent}" ${tblProduct.prodPowerInterface == item.pkConfigcontent ? 'selected="selected"' : ''}>${item.cocoContent}</option>
					</c:forEach>
			</select>
			<span class="info"></span>
			</dd>
		</dl>
		
		<dl>
			<dt><bmtag:message messageKey="电动车类型"/></dt>
			<dd>
				<select name="prodCarType" style="width:136px">
						<option value="0">--请选择--</option>
						<option value="1" ${tblProduct.prodCarType == 1 ? 'selected="selected"' : ''}>纯电动车</option>
						<option value="2" ${tblProduct.prodCarType == 2 ? 'selected="selected"' : ''}>插电式混合动力车</option>
			</select>
			<span class="info"></span>
			</dd>
		</dl>
		
		<dl>
			<dt><bmtag:message messageKey="政府补贴"/></dt>
			<dd>
				<select name="prodSubsidies" style="width:136px">
						<option value="0">--请选择--</option>
						<option value="1" ${tblProduct.prodSubsidies == 1 ? 'selected="selected"' : ''}>有</option>
						<option value="2" ${tblProduct.prodSubsidies == 2 ? 'selected="selected"' : ''}>无</option>
			</select>
			<span class="info"></span>
			</dd>
		</dl>
		
		<dl>
			<dt><bmtag:message messageKey="电池类型"/></dt>
			<dd>
				<select name="prodBattery" style="width:136px">
					<option value="0">--请选择--</option>
					<c:forEach items="${batteryList}" var="battery">
						<option value="${battery.pkConfigcontent}" ${tblProduct.prodBattery==battery.pkConfigcontent?'selected="selected"' : ''}>${battery.cocoContent}</option>
					</c:forEach>
			</select>
			<span class="info"></span>
			</dd>
		</dl>
		
		<dl>
			<dt><bmtag:message messageKey="续航里程"/></dt>
			<dd>
				<select name="prodOdometer" style="width:136px">
					<option value="0">--请选择--</option>
					<c:forEach items="${MaxOdometerList}" var="MaxOdometer">
						<option value="${MaxOdometer.carinfoMaxodometer}" ${tblProduct.prodOdometer==MaxOdometer.carinfoMaxodometer?'selected="selected"' : ''}>${MaxOdometer.carinfoMaxodometer}</option>
					</c:forEach>
			</select>
			<span class="info"></span>
			</dd>
		</dl>
		
		<dl>
			<dt><bmtag:message messageKey="充电时间"/></dt>
			<dd>
				<select name="prodChargingTime" style="width:136px">
					<option value="">--请选择--</option>
					<c:forEach items="${chargingTimeList}" var="chargingTime">
						<option value="${chargingTime.carinfoChargingTime}"${tblProduct.prodChargingTime==chargingTime.carinfoChargingTime?'selected="selected"' : ''}>${chargingTime.carinfoChargingTime}</option>
					</c:forEach>
			</select>
			<span class="info"></span>
			</dd>
		</dl>
			
		</div>
		<div class="formBar">
				<ul>
					<li><bmtag:button messageKey="common.button.submit"
							type="submit" id="formSubmitter" /></li>
					<li><bmtag:button messageKey="common.button.back" type="button"
							dwzClass="close" /></li>
				</ul>
		</div>
	</form>
</div>
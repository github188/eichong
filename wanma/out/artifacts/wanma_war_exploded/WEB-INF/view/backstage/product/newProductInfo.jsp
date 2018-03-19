<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<script type="text/javascript">
	var webroot = "${webroot}";

	function ajaxDoneCallback(json){
	}
	
</script>
<h2 class="contentTitle"><bmtag:message messageKey="新增商品"/></h2>
<div class="pageContent">
<form method="post" action="business/saveProduct.do" class="pageForm required-validate" 
	enctype="multipart/form-data" onsubmit="return iframeCallback(this, navTabAjaxDone)">
	<div class="pageFormContent nowrap" layoutH="97">
		<dl><dt><bmtag:message messageKey="商品名称"/></dt><dd>
			<input name="prodProductname" style="width:130px;" class="required"/>
			<span class="info"></span></dd></dl>
		<dl><dt><bmtag:message messageKey="商品编号"/></dt><dd>
			<input name="prodProductcode" style="width:130px;" class="required"/>
			<span class="info"></span></dd></dl>
		<dl><dt><bmtag:message messageKey="所属类目"/></dt><dd>
			<select name="prodCategoryid" style="width:136px" class="required">
				<c:forEach items="${categoryList}" var="cat">
						<option value="${cat.pkCategory}" ${cat.pkCategory == prodCategoryid ? 'selected="selected"' : ''}>${cat.caName}</option>
				</c:forEach>
			</select>
			<span class="info"></span></dd></dl>
		<dl><dt><bmtag:message messageKey="所属品牌"/></dt><dd>
			<select name="prodBrand" style="width:136px" class="required">
				<c:forEach items="${markList}" var="mark">
						<option value="${mark.pkCarmaker}">${mark.makerName}</option>
				</c:forEach>
			</select>
			<span class="info"></span></dd></dl>	
		<dl><dt><bmtag:message messageKey="成本价格"/></dt><dd>
			<input name="prodProductprice" style="width:130px;" class="required"/>
			<span class="info"></span></dd></dl>
		<dl><dt><bmtag:message messageKey="市场价格"/></dt><dd>
			<input name="prodMarketprice" maxlength="30" style="width:130px;" class="required"/>
			<span class="info"></span></dd></dl>
		<dl><dt><bmtag:message messageKey="库存数量"/></dt><dd>
			<input name="prodStockquantity" style="width:130px;" class="required"/>
			<span class="info"></span></dd></dl>
		<dl><dt><bmtag:message messageKey="上架状态"/></dt><dd>
			<select name="prodProductlsadd" style="width:136px" class="required">
				<option value="1" ${tblProduct.prodProductlsadd == 1?"selected":""}>待上架</option>
				<option value="2" ${tblProduct.prodProductlsadd == 2?"selected":""}>已上架</option>
			</select>
			<span class="info"></span></dd></dl>
		<dl><dt><bmtag:message messageKey="基本参数"/></dt><dd>
			<input name="prodParameters" maxlength="200" style="width:130px;"/>
			<span class="info"></span></dd></dl>
		<dl><dt><bmtag:message messageKey="人工服务"/></dt><dd>
			<input name="prodArtificialservice"  maxlength="200" style="width:130px;"/>
			<span class="info"></span></dd></dl>
			
		<dl>
			<dt><bmtag:message messageKey="列表图片"/></dt>
			<dd>
				<input type="file" name="ListImage" class="file"  style="width:360px;"/>
				<span class="info"></span>
			</dd>
		</dl>
		
		<dl>
			<dt><bmtag:message messageKey="详情图片"/></dt>
			<dd>
				<input type="file" name="DetailImage" class="file"  style="width:260px;"/>
				<span class="info"></span>
			</dd>
		</dl>
		
		<dl>
			<dt><bmtag:message messageKey="充电方式"/></dt>
			<dd>
				<select name="prodChargingMode" style="width:136px">
					<option value="0">--请选择--</option>
					<c:forEach items="${chargeList}" var="item">
						<option value="${item.pkConfigcontent}">${item.cocoContent}</option>
					</c:forEach>
			</select>
			<span class="info"></span>
			</dd>
		</dl>
		
		<dl>
			<dt><bmtag:message messageKey="接口标准"/></dt>
			<dd>
				<select name="prodPowerInterface" style="width:136px">
					<option value="0">--请选择--</option>
					<c:forEach items="${connectorList}" var="item">
						<option value="${item.pkConfigcontent}">${item.cocoContent}</option>
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
						<option value="1">纯电动车</option>
						<option value="2">插电式混合动力车</option>
			</select>
			<span class="info"></span>
			</dd>
		</dl>
		
		<dl>
			<dt><bmtag:message messageKey="政府补贴"/></dt>
			<dd>
				<select name="prodSubsidies" style="width:136px">
						<option value="0">--请选择--</option>
						<option value="1">有</option>
						<option value="2">无</option>
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
						<option value="${battery.pkConfigcontent}">${battery.cocoContent}</option>
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
						<option value="${MaxOdometer.carinfoMaxodometer}">${MaxOdometer.carinfoMaxodometer}</option>
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
						<option value="${chargingTime.carinfoChargingTime}">${chargingTime.carinfoChargingTime}</option>
					</c:forEach>
			</select>
			<span class="info"></span>
			</dd>
		</dl>
		
		
	</div>
	<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit" type="submit" id="formSubmitter"/></li>
				<li><bmtag:button messageKey="common.button.back"  type="button" dwzClass="close"/></li>
			</ul>
	</div>
</form>
</div>
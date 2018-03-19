<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2 class="contentTitle">
	<bmtag:message messageKey="查看商品" />
</h2>
<div class="pageContent">
	<div class="pageFormContent nowrap" layoutH="97">
		<input type="hidden" name="pkProduct" value="${tblProduct.pkProduct}" />
			<dl>
				<dt><bmtag:message messageKey="商品名称"/></dt>
				<dd>
					${tblProduct.prodProductname}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="商品编号"/></dt>
				<dd>
					${tblProduct.prodProductcode}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="所属类目"/></dt>
				<dd>
					${tblProduct.caName }
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="成本价格"/></dt>
				<dd>
					${tblProduct.prodProductprice }
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="市场价格"/></dt>
				<dd>
					${tblProduct.prodMarketprice }
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="库存数量"/></dt>
				<dd>
					${tblProduct.prodStockquantity}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="上架状态"/></dt>
				<dd>
					<select name="prodProductlsadd">
						<option value="">--请选择--</option>
						<option value="1" ${tblProduct.prodProductlsadd == 1?"selected":""}>待上架</option>
						<option value="2" ${tblProduct.prodProductlsadd == 2?"selected":""}>已上架</option>
					</select>
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="基本参数"/></dt>
				<dd>
					${tblProduct.prodParameters}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="人工服务"/></dt>
				<dd>
					${tblProduct.prodArtificialservice}
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
			<dl>
				<dt>
					<bmtag:message messageKey="详情图片" />
				</dt>
				<dd>
					<img alt="" src="${tblProduct.prodDetailimage}" class="file required" style="float: left;"
							width="150px" height="200px">
					<span class="info"></span>
				</dd>
			</dl>
	</div>
	<div class="formBar">
		<ul>
			<li><bmtag:button messageKey="common.button.back" type="button"
					dwzClass="close" /></li>
		</ul>
	</div>
</div>
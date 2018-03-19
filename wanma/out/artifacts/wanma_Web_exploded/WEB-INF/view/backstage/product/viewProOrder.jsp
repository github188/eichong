<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<h2 class="contentTitle">
	<bmtag:message messageKey="user.title.user.view" />
</h2>
<div class="pageContent">
	<div class="pageFormContent nowrap" layoutH="97">
		<input type="hidden" name="pkOrder" value="${tblOrder.pkOrder}" />
			<dl>
				<dt><bmtag:message messageKey="商品名称"/></dt>
				<dd>
					${tblOrder.prName}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="商品编号"/></dt>
				<dd>
					${tblOrder.prCode}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="商品单价"/></dt>
				<dd>
					${tblOrder.olPrice }
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="数量"/></dt>
				<dd>
					${tblOrder.olQuan }
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="总金额"/></dt>
				<dd>
					${tblOrder.olTotal }
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="订单编号"/></dt>
				<dd>
					${tblOrder.ordeCode}
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="订单状态"/></dt>
				<dd>
					<c:choose>
						<c:when test="${tblOrder.ordeStatus == '1'}">
							待支付
						</c:when>
						<c:when test="${tblOrder.ordeStatus == '2'}">
							支付成功
						</c:when>
					</c:choose>
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="订单类型"/></dt>
				<dd>
					<c:choose>
						<c:when test="${tblOrder.ordeTypeOrder == '1'}">
							支付宝
						</c:when>
						<c:when test="${tblOrder.ordeTypeOrder == '2'}">
							银联支付
						</c:when>
					</c:choose>
					<span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="成交时间"/></dt>
				<dd>
					<fmt:formatDate value="${tblOrder.ordeCreatedate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="收货人"/></dt>
				<dd>
					${tblOrder.ordeReceiveingname}
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="联系电话"/></dt>
				<dd>
					${tblOrder.ordeReceiveingcontact}
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="收货地址"/></dt>
				<dd>
					${tblOrder.ordeReceiveingaddress}
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="配送方式"/></dt>
				<dd>
					<c:choose>
						<c:when test="${tblOrder.ordeDeliveryway == '1'}">
							包邮
						</c:when>
						<c:when test="${tblOrder.ordeDeliveryway == '2'}">
							不包邮
						</c:when>
					</c:choose>
				</dd>
			</dl>
			<dl>
				<dt><bmtag:message messageKey="快递公司"/></dt>
				<dd>
					${tblOrder.ordeExpress}
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
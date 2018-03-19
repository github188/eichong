<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/electric/electric-chooseRate.js" />
<div class="pageContent">
<form id="chooseRateForm" method="post" action="${webroot}/common/sendRequest.do" class="pageForm required-validate" 
	 onsubmit="return validateCallback(this, navTabAjaxDone)">
	<div class="pageFormContent nowrap" layoutH="57">
	<input type="hidden" id="chooseRateIds"  value="${ids}"/>
	<input type="hidden" name="ids" value="${ids}"/>
	<input type="hidden" id="chooseRateParamStr" name="paramStrs" value=""/>
	<input type="hidden" id="chooseRateT" name="t" />
	<input type="hidden" name="requestType" value="2" />
	<input type="hidden" name="navTabId" value="electricChooseRate" />
	<input type="hidden" name="callbackType" value="closeCurrent"/>
		<tr>
				<td>
					<label>费率选择</label>
				</td>
				<td colspan="2">
							<select  id="electricRateChoose" name="pk_RateInformation" class="required" style="float: none">
								<option value="">--请选择费率--</option>
								<c:forEach var="item" items="${rateInfoList}">
									<option value="${item.pk_RateInformation}" >
									预约单价:${item.raIn_ReservationRate}  服务费:${item.raIn_ServiceCharge} 城市:${item.CITY_NAME}</option>
								</c:forEach>
							</select>	
				</td>
		</tr>
	</div>
	<div class="formBar">
			<ul>
			    <li><bmtag:button messageKey="费率下发" type="button" onclick="sendRate()" id="formSubmitter"/></li>
				<li><bmtag:button messageKey="common.button.back"  type="button" dwzClass="close"/></li>
			</ul>
	</div>
</form>
</div>


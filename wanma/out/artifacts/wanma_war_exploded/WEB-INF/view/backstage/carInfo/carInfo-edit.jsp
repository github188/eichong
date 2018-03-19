<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>

<div class="pageContent">
	<form method="post" action="carinfo/updateCarinfo.do" id="editForm"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		
		<input type="hidden" name="pkCarinfo" value="${carinfo.pkCarinfo}" />
		
		
		<div class="pageFormContent nowrap" layoutH="55">
			<dl>
				<dt>电动车类型名称：</dt>
				<dd>
					<input name="carinfoStylename" value="${carinfo.carinfoStylename}" type="text" style="width: 165px;" class="required special" size="25"
						maxlength="20" />
				</dd>
			</dl>
			<dl>
				<dt>电动车最大续航：</dt>
				<dd>
					<input name="carinfoMaxodometer" value="${carinfo.carinfoMaxodometer}" type="text" style="width: 165px;" class="required decimal" size="25"
						maxlength="7" />
				</dd>
			</dl>
			<dl>
				<dt>电动车电池容量：</dt>
				<dd>
					<input name="carinfoBatterycapacity" value="${carinfo.carinfoBatterycapacity}" type="text" style="width: 165px;" class="required decimal" size="25"
						maxlength="7" />
				</dd>
			</dl>
			<dl>
				<dt>电动车充电时间：</dt>
				<dd>
					<input id="carinfoChargingTime" name="carinfoChargingTime" type="text" style="width: 165px;" value="${carinfo.carinfoChargingTime}" class="required normal" size="25"
						maxlength="7" />
				</dd>
			</dl>
			<dl>
				<dt>电池类型：</dt>
				<select class="select_Style"  name="carinfoBatteryType">
						<option value = "0" ${carinfo.carinfoBatteryType==0?'selected="selected"' : ''}>其他</option>
						<option value = "1" ${carinfo.carinfoBatteryType==1?'selected="selected"' : ''}>铅酸电池</option>
						<option value = "2" ${carinfo.carinfoBatteryType==2?'selected="selected"' : ''}>胶体电池</option>
						<option value = "3" ${carinfo.carinfoBatteryType==3?'selected="selected"' : ''}>锂离子电池</option>
						<option value = "4" ${carinfo.carinfoBatteryType==4?'selected="selected"' : ''}>镍氢电池</option>
						<option value = "5" ${carinfo.carinfoBatteryType==5?'selected="selected"' : ''}>锌空电池</option>
						<option value = "6" ${carinfo.carinfoBatteryType==6?'selected="selected"' : ''}>铅晶蓄电池</option>
					</select>
			</dl>
			<dl>
				<dt>充电类型：</dt>
				<select class="select_Style"  name="carinfoChargingMode">						
							<option value="14" ${carinfo.carinfoChargingMode==14? 'selected="selected"' : ''}>交流</option>
							<option value="5" ${carinfo.carinfoChargingMode==5? 'selected="selected"' : ''}>直流</option>
							<option value="1" ${carinfo.carinfoChargingMode==1? 'selected="selected"' : ''}>交流和直流</option>													
				</select>
			</dl>
			<dl>
				<dt>接口标准：</dt>
				<select class="select_Style"  name="carinfoPowerInterface">						
							<option value="7" ${carinfo.carinfoPowerInterface==1?'selected="selected"' : ''}>国标</option>
							<option value="20" ${carinfo.carinfoPowerInterface==2?'selected="selected"' : ''}>欧标</option>
							<option value="19" ${carinfo.carinfoPowerInterface==3?'selected="selected"' : ''}>美标</option>													
				</select>
			</dl>
			<dl>
				<dt>电动车品牌名称：</dt>
				<select class="select_Style" name="carinfoCompanyId">
					<option value="">--请选择--</option>
						<c:forEach var="carCompany" items="${carCompanyList}">
							<option value="${carCompany.pk_carCompany}" ${carCompany.pk_carCompany==carinfo.carinfoCompanyId?'selected="selected"' : ''}>${carCompany.carCompany_Name}</option>
						</c:forEach>
					</select>
			</dl>
		</div>
		
		
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button id="addButton" type="submit">
								保存
							</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">
								取消
							</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
	
</div>

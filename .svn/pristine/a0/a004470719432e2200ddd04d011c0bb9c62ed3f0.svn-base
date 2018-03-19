<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>

<script type="text/javascript">
	function check(){
	var carinfoMaxodometer = $('#carinfoMaxodometer').val();
	alert(carinfoMaxodometer);
	}
</script>

<div class="pageContent">
	<form method="post" action="carinfo/insertCarinfo.do" id="addForm"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">

		<div class="pageFormContent nowrap" layoutH="55">
			
			<dl>
				<dt>电动车类型名称：</dt>
				<dd>
					<input name="carinfoStylename" type="text" style="width: 165px;" class="required special" size="25"
						maxlength="20" />
				</dd>
			</dl>
			<dl>
				<dt>电动车最大续航：</dt>
				<dd>
					<input id="carinfoMaxodometer" name="carinfoMaxodometer" type="text" style="width: 165px;" class="required decimal" size="25"
						maxlength="7" />
				</dd>
			</dl>
			<dl>
				<dt>电动车电池容量：</dt>
				<dd>
					<input id="carinfoBatterycapacity" name="carinfoBatterycapacity" type="text" style="width: 165px;" class="required decimal" size="25"
						maxlength="7" />
				</dd>
			</dl>
			<dl>
				<dt>电动车充电时间：</dt>
				<dd>
					<input id="carinfoChargingTime" name="carinfoChargingTime" type="text" style="width: 165px;" class="required normal" size="25"
						maxlength="7" />
				</dd>
			</dl>
			<%-- <dl>
				<dt>电池类型：</dt>
				<select class="combox"  name="carinfoBatteryType">
						<c:forEach var="battery" items="${batteryList}">
							<option value="${battery.pkConfigcontent}">${battery.cocoContent}</option>
						</c:forEach>
					</select>
			</dl> --%>
			<dl>
				<dt>电池类型：</dt>
				<select class="select_Style"  name="carinfoBatteryType">						
						<option value = "0" >其他</option>
						<option value = "1" >铅酸电池</option>
						<option value = "2" >胶体电池</option>
						<option value = "3" >锂离子电池</option>
						<option value = "4" >镍氢电池</option>
						<option value = "5" >锌空电池</option>
						<option value = "6" >铅晶蓄电池</option>					
					</select>
			</dl>
			<dl>
				<dt>充电类型：</dt>
				<select class="select_Style"  name="carinfoChargingMode">						
							<option value="14">交流</option>
							<option value="5">直流</option>
							<option value="1">交流和直流</option>													
				</select>
			</dl>
			<dl>
				<dt>接口标准：</dt>
				<select class="select_Style"  name="carinfoPowerInterface">						
							<option value="7">国标</option>
							<option value="20">欧标</option>
							<option value="19">美标</option>													
				</select>
			</dl>
			<dl>
				<dt>电动车品牌名称：</dt>
				<select class="select_Style"  name="carinfoCompanyId">
						<c:forEach var="carCompany" items="${carCompanyList}">
							<option value="${carCompany.pk_carCompany}">${carCompany.carCompany_Name}</option>
						</c:forEach>
				</select>
			</dl>
		</div>

		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button id="addButton" type="submit" >保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>

</div>

<%@page import="com.wanma.model.TblUser"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
</script>
<div class="pageHeader">
	<form id="pagerForm" method="post"
		action="electric/getExamineElectricPileList.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td><label><bmtag:message
									messageKey="electric.label.code" /> </label></td>
						<td><input name="elpiElectricpilecode"
							value="${tblElectricpile.elpiElectricpilecode}" /></td>
						<td style="align: left"><label style="align: left"><bmtag:message
									messageKey="electric.label.name" /> </label></td>
						<td><input name="elpiElectricpilename"
							value="${tblElectricpile.elpiElectricpilename}" /></td>

						<td><label><bmtag:message
									messageKey="electric.label.way" /> </label></td>
						<td><select name="elpiChargingmode" class="select_Style">
								<option value="">全部</option>
								<c:forEach var="item" items="${chargeList}">
									<option value="${item.pkConfigcontent}"
										${item.pkConfigcontent==
										tblElectricpile.elpiChargingmode ? 'selected="selected"' : ''} >
										${item.cocoContent}</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
			     <td style="align: left"><label style="align: left"><bmtag:message
									messageKey="electric.label.connector" /> </label></td>
						<td><select name=elpiPowerinterface class="select_Style">
								<option value="">全部</option>
								<c:forEach var="item" items="${connectorList}">
									<option value="${item.pkConfigcontent}"
										${item.pkConfigcontent==
										tblElectricpile.elpiPowerinterface ? 'selected="selected"' : ''} >
										${item.cocoContent}</option>

								</c:forEach>
						</select></td>
						<td><label><bmtag:message
									messageKey="electric.label.power" /> </label></td>
						<td><select name="elpiPowersize" class="select_Style">
								<option value="">全部</option>
								<c:forEach var="item" items="${powerList}">
									<option value="${item.pkConfigcontent}"
										${item.pkConfigcontent==
										tblElectricpile.elpiPowersize ? 'selected="selected"' : ''} >
										${item.cocoContent}</option>

								</c:forEach>
						</select></td>
						<td><label><bmtag:message
									messageKey="electric.label.type" /> </label></td>
						<td><select name="elpiType" class="select_Style">
								<option value="">全部</option>
								<c:forEach var="item" items="${typeList}">
									<option value="${item.pkConfigcontent}"
										${item.pkConfigcontent==
										tblElectricpile.elpiType ? 'selected="selected"' : ''} >
										${item.cocoContent}</option>

								</c:forEach>
						</select></td>

					</tr>
					<tr>
			
						<%-- <td style="align: left"><label style="align: left"><bmtag:message
									messageKey="electric.icon.muzzleSum" /> </label></td>
						<td><select name="elpiPowernumber" class="select_Style">
								<option value="">全部</option>
								<option value="1" ${tblElectricpile.elpiPowernumber==1
									? 'selected="selected"' : ''}>单枪头</option>
								<option value="2" ${tblElectricpile.elpiPowernumber==2
									? 'selected="selected"' : ''}>多枪头</option>
						</select></td> --%>
						<td><label><bmtag:message
									messageKey="electric.label.user" /> </label></td>
						<td><input name="elPiUserName"
							value="${tblElectricpile.elPiUserName}" /></td>

					</tr>
					<tr>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
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
			 <li>
				 <bmtag:link isAuth="true"  target="selectedTodo" href="electric/changeElectricPileState.do?changeType=examineOkPage"
				 	rel="ids" postType="string" altKey="确定通过吗？" dwzClass="edit" messageKey="通过" />
			 </li>
			 <li>
			 	<bmtag:link isAuth="true"  authUrl="electric/examineReasonUi.do"
				 	rel="ids" dwzClass="edit" messageKey="驳回"  onclick="checkSelect(this)"/>
			</li>
		</ul>
	</div>
	
	<table class="table" width="100%" layoutH="189">
		<thead>
			<tr align="center">
				<th><input type="checkbox" group="ids" class="checkboxCtrl" name="idsGroup">
				</th>
				<%--<th><bmtag:message messageKey="common.label.index" />
				</th>--%>
				<th><bmtag:message messageKey="electric.label.code" />
				</th>
				<th><bmtag:message messageKey="electric.label.name" />
				</th>
				<th><bmtag:message messageKey="electric.label.user" />
				</th>
				<th><bmtag:message messageKey="electric.label.state" />
				</th>
				<th><bmtag:message messageKey="electric.label.way" />
				</th>
				<th><bmtag:message messageKey="electric.label.connector" />
				</th>
				<th><bmtag:message messageKey="electric.label.type" />
				</th>
				<th><bmtag:message messageKey="electric.label.power" />
				</th>
				<th><bmtag:message messageKey="electric.label.location" />
				</th>
				<th><bmtag:message messageKey="electric.label.madein" />
				</th>
				<%-- <th><bmtag:message messageKey="electric.label.muzzleState" />
				</th> --%>
				<%-- <th><bmtag:message messageKey="连接状态"/></th> --%>
				<th><bmtag:message messageKey="所属区域" />
				</th>
				<%-- <th><bmtag:message messageKey="所属城市" />
				</th>
				<th><bmtag:message messageKey="所属区/县" />
				</th> --%>
				<th><bmtag:message messageKey="地区代码" />
				</th>
				<th><bmtag:message messageKey="费率id" /></th>
				<th><bmtag:message messageKey="gate_id" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${electricList}" var="electric" varStatus="status">
				<tr target="id" rel="${electric.elPi_ElectricPileCode}"
					align="center">
					<td><input name="ids"
						value="${electric.elPi_ElectricPileCode}" type="checkbox">
					</td>
					<%--<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>--%>
					<td>${electric.elPi_ElectricPileCode}</td>
					<td>${electric.elPi_ElectricPileName}</td>
					<td>${electric.elPi_UserName}</td>
					<td><c:if test="${electric.elPi_State ==0}">草稿</c:if> <c:if
							test="${electric.elPi_State ==5}">提交审核 </c:if> <c:if
							test="${electric.elPi_State ==12}">分享申请中 </c:if> <c:if
							test="${electric.elPi_State ==3}">已驳回 </c:if> <c:if
							test="${electric.elPi_State ==10}">专属 </c:if> <c:if
							test="${electric.elPi_State ==15}">分享</c:if></td>
					<td>${electric.chargingModeName}</td>
					<td>${electric.powerName}</td>
					<td>${electric.typeName}</td>
					<td>${electric.powerSizeName}</td>
					<td>
						${electric.elPi_ElectricPileAddress}
					<%-- <c:if test="${electric.elPi_Longitude!=null}">经度：${electric.elPi_Longitude}</c:if>
						<c:if test="${electric.elPi_Latitude!=null}">&nbsp;&nbsp;纬度：${electric.elPi_Latitude}</c:if> --%>
					</td>
					<td>
						${electric.elPi_Maker_Name}
					</td>
					<%-- <td>${electric.headSateDetail}</td> --%>
					<%-- <td>
						<c:if test="${electric.comm_status == 0}">未连接</c:if>
						<c:if test="${electric.comm_status == 1}">已连接</c:if>
					</td> --%>
					<td>${electric.elPi_OwnProvince}${electric.elPi_elPiOwnCity}${electric.elPi_elPiOwnCounty}</td>
					<td>${electric.elPi_OwnCountyCode}</td>
					<td>${electric.elPi_RateInformationId}</td>
					<td>${electric.elPi_GateId}</td>
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
			</select> --> 共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
<script type="text/javascript">
$(function() {
	var userIdForShow="<%=((TblUser)session.getAttribute("user")).getUserId()%>";
	
if(userIdForShow==8231){
	$(".pages").html("");
}
});
function checkSelect(obj){
	var $this = $(obj);
	var ids = getIds();
	if (ids.length==0) {
		alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
		return false;
	}else if(ids.length <0){
		alertMsg.error("只能选择一条信息！");
	}else{
		navTab.openTab("", basepath+"/admin/electric/examineReasonUi.do", { title:"驳回", fresh:false, data:{id:ids.join()}});
	}

}

function getIds(){
	//data-value格式:编码：上线：连接
	var ids = [];
	var $box =navTab.getCurrentPanel();
	$('input[name="ids"]:checked').each(function(){
		ids.push($(this).val());
	});
	if(ids == null || ids.length == 0){
		var trSelected = $box.find("tr.selected");
		var trSelectEdId = trSelected.attr('rel');
		if(trSelectEdId){
			ids.push(trSelectEdId);
		}
	} 
	return ids;
}
</script>

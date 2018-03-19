<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>
.dd_content {
	width: 180px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	course: hand;
}
</style>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
	
	function showContent(this_e) {
		alertMsg.info($(this_e).html());
	}
</script>
<div class="pageHeader">
<form id="pagerForm" method="post" action="device/deviceList.do" onsubmit="return navTabSearch(this);"> 
	<input type="hidden" name="status" value="${pager.status}"/>
	<input type="hidden" name="keywords" value="${pager.keywords}"/>
	<input type="hidden" name="pageNum" value="${pager.pageNum}"/>
	<input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
	<div class="searchBar">
		<table class="searchContent" >
			<tbody>
				<tr >
					<td>
			            <span>提交时间&nbsp;</span>			             
			              <input id="device_start_create_date" name="start_create_date" value="${tblEquipmentrepair.start_create_date }" class="date"  style="width:155px"
						 onClick="WdatePicker({el:'device_start_create_date',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'device_end_create_date\')}'})" >
			                                    至 <input id="device_end_create_date" name="end_create_date" value="${tblEquipmentrepair.end_create_date }" class="date"  style="width:155px"
			             onClick="WdatePicker({el:'device_end_create_date',minDate:'#F{$dp.$D(\'device_start_create_date\')}'})">                        
			                                    
		            </td>
					 <td>
					 
						<span>故障类型&nbsp;</span>
						<select name="eqreWarrantytypeid" style="align:left;width:162px">
							<option value="">全部故障</option>
						<c:forEach var="item" items="${param29}">
									<option value="${item.key}"
										${item.key == tblEquipmentrepair.eqreWarrantytypeid ? 'selected="selected"' : ''} >
										${item.value.coCo_Content}</option>
						</c:forEach>							
						</select>
						&nbsp;&nbsp;&nbsp;&nbsp;
										
						<span><input  style="align:right"  name="phone" value="${tblEquipmentrepair.phone}" placeholder="搜索用户账号" style="width:155px"/></span>
					</td>
					<td align="left">
						<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter"/>
						 <div class="button"><div class="buttonContent" style="width: 60px"><button type="button" onclick="exportSubmit('pagerForm','device/equipmentrepairExport.do')">导出excel</button></div></div>
					</td>
				</tr>
			 </tbody>
		</table>
	</div>
</form>
</div>
<div class="pageContent">
	<table class="table" width="100%" layoutH="87">
		<thead>
			<tr align="center">
				<th>序号</th>
				<!-- <th>用户ID</th> -->
				<th>提交人</th>
				<th>电桩编号</th>
				<th>桩/站名称</th>
				<th>桩/站地址</th>
				<th>所属公司</th>
				<th>提交时间</th>
				 <th>报修类型</th>
				<th>问题描述</th>
				<th>处理结果</th>						
				<th>处理状态</th>
				<th>操作</th>
	        </tr>
		</thead>
		<tbody>
		<c:forEach items="${equipList}" var="equip" varStatus="status">
			<tr target="pkEquipmentrepair" rel="${equip.pk_EquipmentRepair }" align="center">
				<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
				<%-- <td>${equip.userId }</td> --%>
				<td>${equip.userName }${equip.phone }</td>
				<td>${equip.elPi_ElectricPileCode}</td>
				<td>${equip.elPi_ElectricPileName }</td>
				<td>
				${equip.elPi_ElectricPileAddress }
				</td>
				<td>
				${equip.elPi_UserName }
				</td>
				<td>
					<fmt:formatDate value="${equip.eqRe_Createdate }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			<td>${equip.coCo_Content}</td>
				<td><div class='dd_content' onclick='showContent(this)'>${equip.eqRe_Content }</div></td>
			<td>${equip.deal_result}</td>
				<td>
					<c:choose>
						<c:when test="${equip.eqRe_WarrantyStatus == '1'}">
							未处理
						</c:when>
						<c:when test="${equip.eqRe_WarrantyStatus == '3'}">
							已处理
						</c:when>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${equip.eqRe_WarrantyStatus == '1'}">
							<a onclick="checkContent(this)" data-id="${equip.pk_EquipmentRepair}" href="Javascript://" style='color:#0099FF'>开始处理</a>
						</c:when>
					</c:choose>
				</td>
				
			
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>
		<!-- 	<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="4" ${pager.numPerPage == 4?"selected":""}>4</option> 
				<option value="20" ${pager.numPerPage == 20?"selected":""}>20</option> 
				<option value="100" ${pager.numPerPage == 100?"selected":""}>100</option> 
				<option value="200" ${pager.numPerPage == 200?"selected":""}>200</option> 
			</select> -->
			共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${pager.total}" numPerPage="${pager.numPerPage}" pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
<script type="text/javascript">
	function exportSubmit(formId,url){
		var form = navTab.getCurrentPanel().find("#"+formId);
		var oldUrl = form.attr("action");
		var oldSubmitMethod = form.attr("onsubmit");
		form.attr("onsubmit",null)
		form.attr("action",url);
		form.submit();
		form.attr("action",oldUrl);
		form.attr("onsubmit",oldSubmitMethod);
	}
	
	function checkContent(this_e){
		navTab.openTab("", basepath+"/admin/device/deviceDealUi.do", { title:"报修处理", fresh:false, data:{id:$(this_e).attr("data-id")}});
	}
</script>

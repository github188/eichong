<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<style>
.pCenterRed {
	color: red;
	margin-top: 5px;
}

.pCenterRedGreen {
	color: green;
	margin-top: 5px;
}
</style>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json) {
	}
</script>
<div class="pageHeader">
	<form id="pagerForm" method="post"
		action="concentrator/getConcentratorList.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td style="align: left"><label style="align: left"><bmtag:message
									messageKey="concentrator.label.name" /> </label></td>
						<td><input name="coctConcentratorName"
							value="${concentrator.coctConcentratorName}" /></td>
						<td><label><bmtag:message
									messageKey="concentrator.label.SIMMAC" /> </label></td>
						<td><input name="coctSIMMAC"
							value="${concentrator.coctSIMMAC}" /></td>
						<td><label><bmtag:message
									messageKey="concentrator.label.SIMTYPE" /> </label></td>
						<td><select name="coctSIMTYPE" class="select_Style">
								<option value="">全部</option>
								<option value="1"
									${concentrator.coctSIMTYPE==1
									? 'selected="selected"' : ''}>联通</option>
								<option value="2"
									${concentrator.coctSIMTYPE==2
									? 'selected="selected"' : ''}>电信</option>
								<option value="3"
									${concentrator.coctSIMTYPE==3
									? 'selected="selected"' : ''}>移动</option>
						</select></td>
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
			<li><bmtag:link isAuth="true" target="navTab"
					href="concentrator/addConcentratorUi.do" rel="concentratorAddPage"
					messageKey="common.icon.new" dwzClass="add"
					id="concentratorAddPage" /></li>
			<li><bmtag:link isAuth="true"
					authUrl="concentrator/editConcentratorUi.do?pkConcentratorID={id}"
					id="concentratorEditPage" rel="concentratorEditPage"
					messageKey="common.icon.edit" dwzClass="edit"
					onclick="checkSelect(this)" /></li>
			<li><bmtag:link isAuth="true" target="selectedTodo"
					href="concentrator/removeConcentrator.do" dwzClass="delete"
					rel="ids" postType="string" altKey="确定删除吗？" messageKey="批量删除" /></li>
			<li><bmtag:link id="offLineSubmit" isAuth="true"
					target="selectedTodo" href="concentrator/offLine.do"
					dwzClass="edit" rel="ids" postType="string" altKey="确定做离线操作吗？"
					messageKey="离线" /></li>
			<!--
		    <li>
		    	<bmtag:link isAuth="true" target="selectedTodo" href="concentrator/onLine.do"
		    	 	dwzClass="edit"  rel="ids" postType="string" altKey="确定做上线操作吗？"  messageKey="上线" />
	       </li>  -->
		</ul>
	</div>
	<table class="table" width="100%" layoutH="114">
		<thead>
			<tr>
				<th><input type="checkbox" group="ids" class="checkboxCtrl"></th>
				<th><bmtag:message messageKey="common.label.index" /></th>
				<th>集中器ID</th>
				<th>集中器名称</th>
				<th>SIM卡号</th>
				<th>SIM卡编码</th>
				<th>卡号来源</th>
				<th>所属用户</th>
				<th>状态</th>
				<th>创建时间</th>
				<th>修改时间</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${concentratorList}" var="concentrator"
				varStatus="status">
				<tr target="id" rel="${concentrator.pkConcentratorID}"
					data-state="${concentrator.coctState}" align="center"
					offLineSubmitFlag="${concentrator.coctState}"
					onclick="setNoSumbitInfo(this);">
					<td><input name="ids" value="${concentrator.pkConcentratorID}"
						type="checkbox" data-state="${concentrator.coctState}"></td>
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
					<td>${concentrator.pkConcentratorID}</td>
					<td>${concentrator.coctConcentratorName}</td>
					<td>${concentrator.coctSIMMAC}</td>
					<td>${concentrator.coctSIMCODE}</td>
					<td><c:if test="${concentrator.coctSIMTYPE ==1}">联通</c:if> <c:if
							test="${concentrator.coctSIMTYPE ==2}">电信</c:if> <c:if
							test="${concentrator.coctSIMTYPE ==3}">移动</c:if></td>
					<td>${concentrator.coctUserName}</td>
					<td><c:if test="${concentrator.coctState ==1}">
							<p class="pCenterRedGreen">上线</p>
						</c:if> <c:if test="${concentrator.coctState ==0}">
							<p class="pCenterRed">离线</p>
						</c:if></td>
					<td><fmt:formatDate value="${concentrator.coctCreatedate}"
							type="both" /></td>
					<td><fmt:formatDate value="${concentrator.coctUpdatedate}"
							type="both" /></td>
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
			</select>  -->共${pager.total }条, 共${pager.pageTotal}页
			</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
<script>
	/**
	 * 校验是否可编辑
	 */
	function checkSelect(obj) {
		var $this = $(obj);
		var ids = getIds();
		if (ids.length == 0) {
			alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
			return false;
		} else if (ids.length > 1) {
			alertMsg.error("只能选择一条信息！");
		} else {
			var idAndState = ids[0].split(',')
			if (idAndState[1] == 1) {
				alertMsg.error("上线状态不可编辑！");
			} else {
				navTab
						.openTab(
								"concentratorEditPage",
								basepath
										+ "/admin/concentrator/editConcentratorUi.do?pkConcentratorID="
										+ idAndState[0], {
									title : "编辑",
									fresh : false
								});
			}
		}

	}

	/**
	 * 获取选中的ID
	 */
	function getIds() {
		var $box = navTab.getCurrentPanel();
		var ids = [];
		$box.find("input:checked").filter("[name='ids']").each(function(i) {
			ids.push($(this).attr("value") + ',' + $(this).attr("data-state"));
		})
		if (ids == null || ids.length == 0) {
			var trSelected = $box.find("tr.selected");
			var trSelectEdId = trSelected.attr('rel');
			if (trSelectEdId) {
				ids.push(trSelectEdId + ',' + trSelected.attr("data-state"));
			}
		}
		return ids;
	}

	/**
	 * 按钮提交前作限制判断
	 */
	function setNoSumbitInfo(this_e) {
		var noSubmitInfo = $("#offLineSubmit").attr("noSubmit-info");
		if ($(this_e).attr("offLineSubmitFlag") == "0") {
			$("#offLineSubmit").attr("noSubmit-info", "请选择未离线的集中器")
		} else {
			$("#offLineSubmit").attr("noSubmit-info", "")
		}
	}
</script>

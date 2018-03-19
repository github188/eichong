<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
	function bohui1(obj){
		var $this = $(obj);
		var targetType = $this.attr("targetType");
		var ids = getIds("ids", targetType);
		//alert(ids);		
		if (!ids) {
			alertMsg.error($this.attr("warn") || DWZ.msg("alertSelectMsg"));
			return false;
		}else if(ids=="-1"){
			alertMsg.error("流程走完，不能驳回");
			return false;
		}
		
		navTab.openTab("applyCompanyPartner", basepath+"/admin/applyPartner/changeApplyPartnerReason.do", { title:"驳回&编辑原因", fresh:false, data:{applyPartnerId:ids}});

}
	
	function getIds(selectedIds, targetType){
		//data-value格式:编码：上线：连接
		var ids = "";
		var gateId="";
		var $box = targetType == "dialog" ? $.pdialog.getCurrent() : navTab.getCurrentPanel();
		$box.find("input:checked").filter("[name='"+selectedIds+"']").each(function(i){				
			if($(this).attr("status")==4){			
				ids="-1";
				return false;
			}	
			ids = $(this).attr("value");
		})
		if(!ids){
			var trSelectEdId = $box.find("tr.selected").attr('rel');
			if(trSelectEdId){
				ids += trSelectEdId;
			}
		}
		return ids;
	}
</script>
<div class="pageHeader">
<form id="pagerForm" method="post" action="applyPartner/applyPartnerList.do" onsubmit="return navTabSearch(this);"> 
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
			              <input id="applyPacreateDateStarte" name="createDateStarte" value="${tblApplyPartner.createDateStarte }" class="date"  style="width:155px"
						 onClick="WdatePicker({el:'applyPacreateDateStarte',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'applyPacreateDateEnd\')}'})" >
			                                    至 <input id="applyPacreateDateEnd" name="createDateEnd" value="${tblApplyPartner.createDateEnd }" class="date"  style="width:155px"
			             onClick="WdatePicker({el:'applyPacreateDateEnd',minDate:'#F{$dp.$D(\'applyPacreateDateStarte\')}'})">                        	                                    
		            </td>
					
				</tr>
				<tr >
<%-- 				<td>
			            <span>审批时间&nbsp;</span>			             
			              <input id="applyPaupdateDateStart" name="updateDateStart" value="${tblApplyPartner.updateDateStart }" class="date"  style="width:155px"
						 onClick="WdatePicker({el:'applyPaupdateDateStart',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'applyPaupdateDateEnd\')}'})" >
			                                    至 <input id="applyPaupdateDateEnd" name="updateDateEnd" value="${tblApplyPartner.updateDateEnd }" class="date"  style="width:155px"
			             onClick="WdatePicker({el:'applyPaupdateDateEnd',minDate:'#F{$dp.$D(\'applyPaupdateDateStart\')}'})">                        	                                    
		            </td> --%>
					 <td>
					 
						<span>审批状态&nbsp;&nbsp;</span>
						<select name="apPaProcessState" style="align:left;width:155px">
								<option value="">全部状态</option>
								<option value="1" ${tblApplyPartner.apPaProcessState == 1 ? 'selected="selected"' : ''} >提交申请</option>						   
						        <option value="2" ${tblApplyPartner.apPaProcessState == 2 ? 'selected="selected"' : ''} >信息审核</option>
						        <option value="3" ${tblApplyPartner.apPaProcessState == 3 ? 'selected="selected"' : ''} >合作洽谈</option>
						        <option value="4" ${tblApplyPartner.apPaProcessState == 4 ? 'selected="selected"' : ''} >达成合作</option>	 
						        <option value="10" ${tblApplyPartner.apPaProcessState == 10 ? 'selected="selected"' : ''} >申请驳回</option>						        									
						</select>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<span><input  style="align:right"  name="apPaUserPhone" value="${tblApplyPartner.apPaUserPhone}" placeholder="搜索联系人电话" style="width:155px"/></span>																
					</td>				
					<td align="left">
						<bmtag:button messageKey="common.button.search" type="submit" id="formSubmitter"/>
					</td>
				</tr>
				
			 </tbody>
		</table>
	</div>
</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
	<%-- 		<c:if test="${loginUser.userLevel!='1'}"> --%>

				<%-- <li><bmtag:link href="applyPartner/changeApplyPartnerReason.do?applyPartnerId={pkApplyPartner}"
						target="navTab" rel="编辑驳回原因"
						messageKey="驳回&编辑原因" dwzClass="edit" /></li> --%>
						
				<li><a class="icon"  rel="ids"
					postType="string"  onclick="bohui1(this)"
					title="驳回&编辑原因"><span>驳回&编辑原因 </span>
				</a></li>
						
						
			<%-- </c:if> --%>
			<li><bmtag:link
						href="applyPartner/deleteById.do?pageNum=${pager.pageNum }&applyPartnerId={pkApplyPartner}"
						target="ajaxTodo" altKey="common.msg.delete.confirm"
						messageKey="common.icon.delete" dwzClass="delete" /></li>
				<li><a class="delete" target="selectedTodo" rel="ids"
					postType="string" title="确定要批量删除吗？"
					href="<c:url value='/admin/applyPartner/deleteApplyPartners.do'/>"
					title="批量删除"><span>批量删除</span> </a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="136">
		<thead>
			<tr align="center">
				<th width="10"><input type="checkbox" group="ids"
					class="checkboxCtrl" />
				</th>
				<th>申请人姓名</th>
				<th>联系人电话</th>	
				<th>企业名称</th>				
				<th>联系人邮箱</th>				
				<th>合作模式</th>
				<th>申请时间</th>
				 <!-- <th>驳回原因</th>  -->
				<th>审批状态</th>								
				<th>审批操作</th>
	        </tr>
		</thead>
		<tbody>
		<c:forEach items="${applyPartnerList}" var="applyPa" varStatus="status">
			<tr target="pkApplyPartner" rel="${applyPa.pkApplyPartner }" align="center">
				<td><input name="ids" value="${applyPa.pkApplyPartner}" status = "${applyPa.apPaProcessState}" 
						type="checkbox">
				</td>										
				<td>${applyPa.apPaUserName }</td>
				<td>${applyPa.apPaUserPhone}</td>
				<td>
				<c:if test="${applyPa.apPaCompanyName != ''}">${applyPa.apPaCompanyName}</c:if>
				<c:if test="${applyPa.apPaCompanyName == ''}">用户未填</c:if>
				</td>				
				<td>
				<c:if test="${applyPa.apPaUserEmail != ''}">${applyPa.apPaUserEmail}</c:if>
				<c:if test="${applyPa.apPaUserEmail == ''}">用户未填</c:if>
				</td>
				<td>
				<c:if test="${applyPa.apPaCooperationMode == 1}">有地无桩</c:if>
				<c:if test="${applyPa.apPaCooperationMode == 2}">有桩，不会运营</c:if>	
				<c:if test="${applyPa.apPaCooperationMode == 3}">其他合作模式</c:if>	
				<c:if test="${applyPa.apPaCooperationMode == 0}">用户未填</c:if>					
				</td>	
				<td>
					<fmt:formatDate value="${applyPa.apPaCreatedate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
<%-- 				<td>
					<fmt:formatDate value="${applyPa.apPaUpdatedate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
<%-- 			    <td>
				    <c:choose>
							<c:when test="${applyPa.apPaProcessState != '10'}">
								未驳回
							</c:when>
							<c:when test="${applyPa.apPaDealReason == '' and applyPa.apPaProcessState =='10'}">
								未编辑原因
							</c:when>
							<c:when test="${applyPa.apPaDealReason != '' and applyPa.apPaProcessState =='10'}">
								${applyPa.apPaDealReason} 
							</c:when>
					</c:choose>
				</td> 	 --%>			
				<td>
					<c:choose>
						<c:when test="${applyPa.apPaProcessState == '1'}">
							提交申请阶段
						</c:when>
						<c:when test="${applyPa.apPaProcessState == '2'}">
							信息审核阶段
						</c:when>
						<c:when test="${applyPa.apPaProcessState == '3'}">
							合作洽谈阶段
						</c:when> 
						<c:when test="${applyPa.apPaProcessState == '4'}">
							达成合作成功
						</c:when>
						<c:when test="${applyPa.apPaProcessState == '10'}">
							已驳回
						</c:when>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${applyPa.apPaProcessState == '1'}">
							<a target="ajaxTodo" href="<c:url value='/admin/applyPartner/updateApplyPartnerState.do?pkApplyPartner=${applyPa.pkApplyPartner}&apPaProcessState=2'/>" title="确定通过该申请？" style='color:#0099FF'>通过申请</a>
						</c:when>
						<c:when test="${applyPa.apPaProcessState == '2'}">
							<a target="ajaxTodo" href="<c:url value='/admin/applyPartner/updateApplyPartnerState.do?pkApplyPartner=${applyPa.pkApplyPartner}&apPaProcessState=3'/>"  style='color:#0099FF'>完成信息审核</a>
						</c:when>
						<c:when test="${applyPa.apPaProcessState == '3'}">
							<a target="ajaxTodo" href="<c:url value='/admin/applyPartner/updateApplyPartnerState.do?pkApplyPartner=${applyPa.pkApplyPartner}&apPaProcessState=4'/>"  style='color:#0099FF'>完成合作洽谈</a>
						</c:when>
						<c:when test="${applyPa.apPaProcessState == '4'}">
							完成加入
						</c:when>
						<c:when test="${applyPa.apPaProcessState == '10'}">
							已结束
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

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
	var webroot = "${webroot}";
	function ajaxDoneCallback(json){
	}
	
	function bohui(obj){
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
		
		navTab.openTab("applyElecPile", basepath+"/admin/applyElecPile/changeApplyElecPileReason.do", { title:"驳回&编辑原因", fresh:false, data:{applyElecPileId:ids}});

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
<form id="pagerForm" method="post" action="applyElecPile/applyElecPileList.do" onsubmit="return navTabSearch(this);"> 
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
			              <input id="applyEpcreateDateStarte" name="createDateStarte" value="${tblApplyElecPile.createDateStarte}" class="date"  style="width:155px"
						 onClick="WdatePicker({el:'applyEpcreateDateStarte',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'applyEpcreateDateEnd\')}'})" >
			                                    至 <input id="applyEpcreateDateEnd" name="createDateEnd" value="${tblApplyElecPile.createDateEnd}" class="date"  style="width:155px"
			             onClick="WdatePicker({el:'applyEpcreateDateEnd',minDate:'#F{$dp.$D(\'applyEpcreateDateStarte\')}'})">                        	                                    
		            </td>
					 <td>
					 
						<span>点地址&nbsp;</span>
						<span><input  style="align:right"  name="apEpPointAddress" value="${tblApplyElecPile.apEpPointAddress}" placeholder="搜索充电点地址" style="width:162px"/></span>
						&nbsp;&nbsp;&nbsp;&nbsp;										
					</td>
				</tr>
				<tr >
				<%-- 	<td>
			            <span>审批时间&nbsp;</span>			             
			              <input id="applyEpupdateDateStart" name="updateDateStart" value="${tblApplyElecPile.updateDateStart }" class="date"  style="width:155px"
						 onClick="WdatePicker({el:'applyEpupdateDateStart',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'applyEpupdateDateEnd\')}'})" >
			                                    至 <input id="applyEpupdateDateEnd" name="updateDateEnd" value="${tblApplyElecPile.updateDateEnd }" class="date"  style="width:155px"
			             onClick="WdatePicker({el:'applyEpupdateDateEnd',minDate:'#F{$dp.$D(\'applyEpupdateDateStart\')}'})">                        	                                    
		            </td> --%>
					 <td>
					 
						<span>审批状态&nbsp;</span>
						<select name="apEpProcessState" style="align:left;width:162px">
								<option value="">全部状态</option>
								<option value="1" ${tblApplyElecPile.apEpProcessState == 1 ? 'selected="selected"' : ''} >提交申请</option>
						        <option value="2" ${tblApplyElecPile.apEpProcessState == 2 ? 'selected="selected"' : ''} >勘场施工</option>
						        <option value="3" ${tblApplyElecPile.apEpProcessState == 3 ? 'selected="selected"' : ''} >入网爱充</option>
						        <option value="4" ${tblApplyElecPile.apEpProcessState == 4 ? 'selected="selected"' : ''} >建站成功</option>
						        <option value="10" ${tblApplyElecPile.apEpProcessState == 10 ? 'selected="selected"' : ''} >申请驳回</option>						        									
						</select>
						&nbsp;&nbsp;&nbsp;&nbsp;
										
						<span><input  style="align:right"  name="apEpUserPhone" value="${tblApplyElecPile.apEpUserPhone}" placeholder="搜索联系人电话" style="width:155px"/></span>
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
				<li><bmtag:link href="applyElecPile/checkApplyElecPileDetail.do?applyElecPileId={pkApplyElecPile}"
						target="navTab" rel="查看详情"
						messageKey="查看详情" dwzClass="add" /></li>
				<%-- <li><bmtag:link href="applyElecPile/changeApplyElecPileReason.do?applyElecPileId={pkApplyElecPile}"
						target="navTab" rel="编辑驳回原因"
						messageKey="驳回&编辑原因" dwzClass="edit" /></li> --%>
						
					<li><a class="icon"  rel="ids"
						postType="string"  onclick="bohui(this)"
						title="驳回&编辑原因"><span>驳回&编辑原因 </span>
					</a></li>
						
			<%-- </c:if> --%>
			<li><bmtag:link
						href="applyElecPile/deleteById.do?pageNum=${pager.pageNum }&applyElecPileId={pkApplyElecPile}"
						target="ajaxTodo" altKey="common.msg.delete.confirm"
						messageKey="common.icon.delete" dwzClass="delete" /></li>
				<li><a class="delete" target="selectedTodo" rel="ids"
					postType="string" title="确定要批量删除吗？"
					href="<c:url value='/admin/applyElecPile/deleteApplyElecPiles.do'/>"
					title="批量删除"><span>批量删除</span> </a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="136">
		<thead>
			<tr align="center">
				<th width="10"><input type="checkbox" group="ids"
					class="checkboxCtrl" />
				</th>							
				<th>联系人姓名</th>
				<th>联系人电话</th>
				<th>建桩地址</th>
				<th>车辆品牌</th>
				<th>车辆型号</th>
				<th>物业性质</th>
				<th>车位位置</th>
				<th>是否开放</th>
				<th>申请时间</th>
				<!-- <th>审批时间</th> -->
				 <!-- <th>驳回原因</th>  -->
				<th>审批状态</th>								
				<th>审批操作</th>
	        </tr>
		</thead>
		<tbody>
		<c:forEach items="${applyElecPileList}" var="applyEp" varStatus="status">
			<tr target="pkApplyElecPile" rel="${applyEp.pkApplyElecPile }" align="center">				
				<td><input name="ids" value="${applyEp.pkApplyElecPile}" status = "${applyEp.apEpProcessState}" 
						type="checkbox">
					</td>
				<td>${applyEp.apEpUserName }</td>						
				<td>${applyEp.apEpUserPhone }</td>	
				<td>${applyEp.apEpPointAddress }</td>			
				<td>${applyEp.carCompanyName }</td>				
				<td>${applyEp.carinfoName}</td>	
				<td>
					<c:if test="${applyEp.apEpPropertyNature == 1}">零售</c:if>
				  	<c:if test="${applyEp.apEpPropertyNature == 2}">酒店</c:if>
				  	<c:if test="${applyEp.apEpPropertyNature == 3}">度假村</c:if>	
				  	<c:if test="${applyEp.apEpPropertyNature == 4}">娱乐场所</c:if>
				  	<c:if test="${applyEp.apEpPropertyNature == 5}">办公写字楼</c:if>
				  	<c:if test="${applyEp.apEpPropertyNature == 6}">住宅</c:if>
				  	<c:if test="${applyEp.apEpPropertyNature == 7}">汽车服务</c:if>
				  	<c:if test="${applyEp.apEpPropertyNature == 8}">综合体</c:if>
				  	<c:if test="${applyEp.apEpPropertyNature == 9}">市政</c:if>
				  	<c:if test="${applyEp.apEpPropertyNature == 10}">收费停车场</c:if>
				  	<c:if test="${applyEp.apEpPropertyNature == 11}">餐厅</c:if>				  	
				 </td>
				<td>
				<c:if test="${applyEp.apEpParkingPlace == 1}">室内</c:if>
				<c:if test="${applyEp.apEpParkingPlace == 2}">室外</c:if>
				</td>	
				<td>
				<c:if test="${applyEp.apEpOpenShare == 1}">是</c:if>
				<c:if test="${applyEp.apEpOpenShare == 2}">部分开放</c:if>
				<c:if test="${applyEp.apEpOpenShare == 3}">否</c:if>
				</td>				
				
				<td>
					<fmt:formatDate value="${applyEp.apEpCreatedate }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
			<%-- 	<td>
					<fmt:formatDate value="${applyEp.apEpUpdatedate }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td> --%>
<%-- 			    <td>
				    <c:choose>
							<c:when test="${applyEp.apEpProcessState != '10'}">
								未驳回
							</c:when>
							<c:when test="${applyEp.apEpDealReason == '' and applyEp.apEpProcessState =='10'}">
								未编辑原因
							</c:when>
							<c:when test="${applyEp.apEpDealReason != '' and applyEp.apEpProcessState =='10'}">
								${applyEp.apEpDealReason} 
							</c:when>
					</c:choose>
				</td> 	 --%>			
				<td>
					<c:choose>
						<c:when test="${applyEp.apEpProcessState == '1'}">
							提交申请
						</c:when>
						<c:when test="${applyEp.apEpProcessState == '2'}">
							勘场施工
						</c:when>
						<c:when test="${applyEp.apEpProcessState == '3'}">
							入网爱充
						</c:when>
						<c:when test="${applyEp.apEpProcessState == '4'}">
							建站成功
						</c:when>
						<c:when test="${applyEp.apEpProcessState == '10'}">
							已驳回
						</c:when>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${applyEp.apEpProcessState == '1'}">
							<a target="ajaxTodo" href="<c:url value='/admin/applyElecPile/updateApplyElecPileState.do?pkApplyElecPile=${applyEp.pkApplyElecPile}&apEpProcessState=2'/>" title="确定通过该申请？" style='color:#0099FF'>通过申请</a>
						</c:when>
						<c:when test="${applyEp.apEpProcessState == '2'}">
							<a target="ajaxTodo" href="<c:url value='/admin/applyElecPile/updateApplyElecPileState.do?pkApplyElecPile=${applyEp.pkApplyElecPile}&apEpProcessState=3'/>"  style='color:#0099FF'>完成勘场施工</a>
						</c:when>
						<c:when test="${applyEp.apEpProcessState == '3'}">
							<a target="ajaxTodo" href="<c:url value='/admin/applyElecPile/updateApplyElecPileState.do?pkApplyElecPile=${applyEp.pkApplyElecPile}&apEpProcessState=4'/>"  style='color:#0099FF'>完成入网爱充</a>
						</c:when>
						<c:when test="${applyEp.apEpProcessState == '4'}">
							完成加入						
						</c:when>
						<c:when test="${applyEp.apEpProcessState == '10'}">
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

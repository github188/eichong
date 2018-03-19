<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<style>
  .pCenterRed {
  	color:red;
  	margin-top:5px;
  }
  
  .pCenterRedGreen {
  	color:green;
  	margin-top:5px;
  }
  .title{
  	text-align:center;
  	font-size:16px;
  	
  }
  .one{
  	width:100%;
  	margin-top:40px;
  	text-align:center;
  	font-size:14px;
  }
  .myBtn{
  	display:inline-block;
  	width:80px;
  	height:30px;
  	border:1px solid #ccc;
  	text-align:center;
  	line-height:30px;
  	border-radius:8px;
  	cursor:pointer; 
  	color:#000;
  	background-color:#ebebeb;
  }
</style>
 <link href="<%=request.getContextPath()%>/static/css/bannerList.css" rel="stylesheet" />

<div class="pageHeader">
	<form id="pagerForm" method="post"
		action="simCard/simCardList.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> 
		<input type="hidden" name="keywords" value="${pager.keywords}" /> 
		<input type="hidden" id="pageNum" name="pageNum" value="${pager.pageNum}" /> 
		<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr> 
						<td><label><bmtag:message
									messageKey="sim卡编码" /> </label>
						</td>
						<td><input name="simCode"
							value="${simCard.simCode}" />
						</td>
						<td><label><bmtag:message
									messageKey="sim卡运营商" /> </label>
						</td>
						<td>
							<select name="simOperator"  class="select_Style">
								<option value="">全部</option>
								<option value="10" ${(simCard.simOperator == 46000 || simCard.simOperator == 46002 || simCard.simOperator == 46004 || simCard.simOperator == 46007 ||simCard.simOperator == 46020 || simCard.simOperator ==46060)?"selected":""}>
								移动</option>
								<option value="11" ${(simCard.simOperator == 46001 || simCard.simOperator == 46006 || simCard.simOperator == 46010)?"selected":""}>
								联通</option>
								<option value="12" ${(simCard.simOperator == 46003 || simCard.simOperator == 46005 || simCard.simOperator == 46011)?"selected":""}>
								电信</option>  
							</select>
						</td>
						<td><label><bmtag:message
									messageKey="桩体编码" /> </label>
						</td>
						<td><input name="elpiElectricpilecode"
							value="${simCard.elpiElectricpilecode}" />
						</td>
						<td style="align: left"><label style="align: left"><bmtag:message
									messageKey="充电点名称" /> </label>
						</td>
						<td><input name="postName"
							value="${simCard.postName}" />
						</td>
						
						<td align="right"><bmtag:button
							messageKey="common.button.search" type="submit" id="formSubmitter" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul>
			<li>
				<div class="myEdit" id="simUpdate">更新</div>
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="125">
		<thead>
			<tr align="center">
				<th><input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<th><bmtag:message messageKey="common.label.index" />
				</th> 
				<th><bmtag:message messageKey="sim卡编码" />
				</th>
				<th><bmtag:message messageKey="sim卡运营商" />
				</th>
				<th><bmtag:message messageKey="桩体编码" />
				</th>
				<th><bmtag:message messageKey="编号" />
				</th>
				<th><bmtag:message messageKey="充电点名称" />
				</th>
				<th><bmtag:message messageKey="连接状态" />
				</th>
				<th><bmtag:message messageKey="更新时间" />
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${simCardList}" var="sim" varStatus="status">
				<tr target="id" rel="${sim.pkId}" align="center">
					<td><input name="ids" value="" type="checkbox" class="checkbox" id="${sim.elpiElectricpilecode}" data-value="${sim.elpiElectricpilecode}" data-value1="${sim.commStatus}"></td>
					<td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td> 
					<td>
						<c:if  test="${sim.simCode == '0'}"></c:if>
						<c:if  test="${sim.simCode != '0'}">${sim.simCode}</c:if>
					</td>
					<td>
						<c:if test="${sim.simOperator == 46000 || sim.simOperator == 46002 || sim.simOperator == 46004 || sim.simOperator == 46007 || sim.simOperator == 46020 || sim.simOperator == 46060}">
						移动</c:if>
						<c:if test="${sim.simOperator == 46001 || sim.simOperator == 46006 || sim.simOperator == 46010}">
						联通</c:if>
						<c:if test="${sim.simOperator == 46003 || sim.simOperator == 46005 || sim.simOperator == 46011}">
						电信</c:if>
					</td>
					<td>${sim.elpiElectricpilecode}</td>
					<td>
						<c:if test="${sim.epNum == 0}"></c:if>
						<c:if test="${sim.epNum != 0}">${sim.epNum}号桩</c:if>
					</td>
					<td>${sim.postName}</td>
					<td>
						<c:if test="${sim.commStatus == 0}">
							<p class="pCenterRed">未连接</p>
						</c:if>
						<c:if test="${sim.commStatus == 1}">
							<p class="pCenterRedGreen">已连接
						</c:if>
					</td>
					<td>
						<c:if test="${sim.updateDate != null}"><fmt:formatDate value="${sim.updateDate }" pattern="yyyy-MM-dd HH:mm:ss"/></c:if>
						<c:if test="${sim.updateDate == null}"><fmt:formatDate value="${sim.createDate }" pattern="yyyy-MM-dd HH:mm:ss"/></c:if>
						
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>
<script type="text/javascript">
var elpiElectricpilecodes="";
var comms=1;
	$("#simUpdate").click(function(){
			$(".checkbox").each(function(){
				var elpiElectricpilecode =$(this).attr("data-value");
				var commStatus = $(this).attr("data-value1");
				if ($(this).attr('checked')) {
					elpiElectricpilecodes=elpiElectricpilecodes+elpiElectricpilecode+":";
					comms*=commStatus;
				}
				
			});
			
			if(elpiElectricpilecodes.length>0){
				
				if(comms==1){
					simUpdateAiax(elpiElectricpilecodes);
				}else{
					 alertMsg.error("电桩离线，无法下发更新命令！");
					 navTab.reloadFlag("simCardList");
				}
			}else{
				alertMsg.error("请选择至少一条记录！");
				return;
			}
	})
	
function simUpdateAiax(elpiElectricpilecodes){
	$.ajax({
		type : 'post',
		url : basepath+ "/admin/simCard/simUpdateUi.do",
		dataType : "json",
		data :{
			elpiElectricpilecode:elpiElectricpilecodes
		},
		success : function(datas) {
			if(datas.statusCode==200){
				/* alertMsg.info("sim信息更新成功！"); */
				navTab.reloadFlag("simCardList");				
				elpiElectricpilecodes.length=0;
			}else{
				alertMsg.error(data.message);
			} 
		}
	});
}
</script>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<script type="text/javascript">
function offline(id){
	$.ajax({
		type : 'post',
		url : basepath+"/admin/electric/changeElectricPileState.do",
		dataType : "json",
		data:{changeType:"offLinePage",ids:id},
		success : function(data) {
			if(data.statusCode==200){
				alertMsg.correct(data.message);
				navTab.reloadFlag("stationDetail");
			}else{
				alertMsg.error(data.message);
			}
		}
	});
}
</script>
<div class="pageContent">
	<form method="post" action=""
		class="pageForm required-validate" 
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="58">

			<dl>
				<dt>
					充电点名称
				</dt>
				<dd>
					${station.powerStationName}
				</dd>
			</dl>
			<dl>
				<dt>
					地址信息
				</dt>
				<dd>
					${station.powerStationAddress}
				</dd>
			</dl>
			<fieldset>
				<legend>电桩详情</legend>
				<table class="searchContent" >
						<tbody>
							<c:forEach var="pile" items="${station.powerElectricpileList}" varStatus="status"  >
							<tr>
								<td style="width:200px;height:20px;">${pile.elictricPicName}</td>
		                        <td>
									<c:if test="${pile.electricPileState ==10}">专属 </c:if>
									<c:if test="${pile.electricPileState ==15}">
										分享
										<a style="margin-left:20px;color:blue;cursor:pointer;" onclick="offline('${pile.elictricPicId}')">离线</a>
									</c:if>
								</td>
								<td></td>
								<td align="right"></td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
			</fieldset>
		</div>

		<div class="formBar">
			<ul>
				<%-- <li><bmtag:button messageKey="common.button.submit"
						type="submit" id="formSubmitter" />
				</li> --%>
				<li><bmtag:button messageKey="common.button.back" type="button"
						dwzClass="close" />
				</li>
			</ul>
		</div>
	</form>
</div>
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
<h2 class="contentTitle">
	新增集中器
</h2>
<div class="pageContent">
	<form method="post" action="concentrator/editConcentrator.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">

		<div class="pageFormContent nowrap" layoutH="97">

			<dl>
				<dt>
					集中器名称
				</dt>
				<dd><input id="pkConcentratorIDDiv" name="pkConcentratorID" type="hidden"value="${concentrator.pkConcentratorID}"/>
					<input name="coctConcentratorName" class="textInput required"
						value="${concentrator.coctConcentratorName}" style="width: 165px;" /> <span
						class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					SIM卡号
				</dt>
				<dd>
					<input name="coctSIMCODE" value="${concentrator.coctSIMCODE}" class="textInput phone required" maxlength="11"
						style="width: 165px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					SIM卡编码
				</dt>
				<dd>
					<input name="coctSIMMAC" class="textInput required" value="${concentrator.coctSIMMAC}"
						maxlength="20" style="width: 165px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					卡号来源
				</dt>
				<dd>		 
						<select name="coctSIMTYPE" class="select_Style required">
							<option value="">-请选择-</option>			
							<option value="1" ${concentrator.coctSIMTYPE==1
									? 'selected="selected"' : ''}>联通</option>
							<option value="2" ${concentrator.coctSIMTYPE==2
									? 'selected="selected"' : ''}>电信</option>
							<option value="3" ${concentrator.coctSIMTYPE==3
									? 'selected="selected"' : ''}>移动</option>
						</select>
						<span class="info"></span>	
				</dd>
			</dl>
			<dl>
				<dt>
					<bmtag:message messageKey="绑定电桩" />
				</dt>
				<dd>
					<input name="tblElectricpile.pkElectricpile" value="" type="hidden">
					<input name="tblElectricpile.elpiElectricpilename" type="text"
						readonly="true" /><a class="btnLook"
						href="../admin/concentrator/getConcentratorElectricPileList.do"
						lookupGroup="tblElectricpile">查找电桩</a> <span class="info">(请选择电桩)</span>
				</dd>
			</dl>
			<dl>已绑电桩列表
			</dl>
			<dl></dl>
			<dl>
				<table class="table" width="100%" layoutH="409">
					<thead>
						<tr>
							<!-- <th><input type="checkbox" group="ids" class="checkboxCtrl">
							</th> -->
							<th><bmtag:message messageKey="common.label.index" />
							</th>
							<th><bmtag:message messageKey="electric.label.code" />
							</th>
							<th><bmtag:message messageKey="electric.label.name" />
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
							<th><bmtag:message messageKey="操作" />
							</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${concentrator.pileList}" var="electric"
							varStatus="status">
							<tr class="bindTr" target="id" rel="${electric.pkElectricpile}" align="center">
								<td>${ status.index + 1 +
									pager.numPerPage*(pager.pageNum-1)}</td>
								<td>${electric.elpiElectricpilecode}</td>
								<td>${electric.elpiElectricpilename}</td>
								<td><c:if test="${electric.elctrcState ==0}">草稿</c:if> <c:if
										test="${electric.elctrcState ==5}">提交审核 </c:if> <c:if
										test="${electric.elctrcState ==10}">专属 </c:if> <c:if
										test="${electric.elctrcState ==15}">分享</c:if></td>
								<td>${electric.chargingMode}</td>
								<td>${electric.powerName}</td>
								<td>${electric.typeName}</td>
								<td>${electric.powerSizeName}</td>
								
								<td><a class="unbind" target="ajaxTodo"
									href="<c:url value='concentrator/unbindElectricPile.do?pkElectricpile=${electric.pkElectricpile}&pkConcentratorID=${electric.pkConcentratorID}'/>"
									title="确定要处理吗？">解绑</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</dl>
		</div>

		<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit"
						type="submit" id="formSubmitter" />
				</li>
				<li><bmtag:button messageKey="common.button.back" type="button"
						dwzClass="close" />
				</li>
			</ul>
		</div>
	</form>
</div>
<script>
	$(document).ready(function(){
		makeUnbindUrl();
	});
	
	//设置解绑URL
	function makeUnbindUrl(){
		var pkElectricpiles = "";//所有已绑定的电桩ID
		$('.bindTr').each(function(){
			pkElectricpiles += $(this).attr("rel")+","
		});
		var length = pkElectricpiles.length;
		pkElectricpiles = pkElectricpiles.substring(0, length-1)
		$('.unbind').each(function(){
			var unbindPkElectricpile = $(this).parent().parent().attr("rel");//需要解绑的电桩ID
			var reSortPkElectricpiles="";//不需要解绑的电桩ID组
			if(pkElectricpiles.indexOf(unbindPkElectricpile) == pkElectricpiles.length-unbindPkElectricpile.length){
				reSortPkElectricpiles = pkElectricpiles.replace(","+unbindPkElectricpile,"");
			}else{
				reSortPkElectricpiles = pkElectricpiles.replace(unbindPkElectricpile+",","");
			}
			if(unbindPkElectricpile == reSortPkElectricpiles){
				reSortPkElectricpiles = "";
			}
			$(this).attr("href","<c:url value='concentrator/unbindElectricPile.do?unbindPkElectricpile="+unbindPkElectricpile+"&reSortPkElectricpiles="+reSortPkElectricpiles+"&cId="+$("#pkConcentratorIDDiv").val()+"'/>");
		});
	}
</script>
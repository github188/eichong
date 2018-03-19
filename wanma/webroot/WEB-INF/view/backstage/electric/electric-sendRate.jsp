<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<link href="<%=request.getContextPath()%>/res/commen.css"
	rel="stylesheet" />
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/electric/electric-list.js" />
<div class="pageHeader">
	<form id="pagerForm" method="post"
		action="sendRate/getElectricPileList.do"
		onsubmit="return navTabSearch(this);">
		<input type="hidden" name="status" value="${pager.status}" /> <input
			type="hidden" name="keywords" value="${pager.keywords}" /> <input
			type="hidden" id="pageNum" name="pageNum" value="${pager.pageNum}" /> <input
			type="hidden" name="numPerPage" value="${pager.numPerPage}" />
		<div class="searchBar">
			<table class="searchContent">
				<tbody>
					<tr>
						<td><label><bmtag:message
									messageKey="electric.label.code" /> </label>
						</td>
						<td><input name="elpiElectricpilecode"
							value="${tblElectricpile.elpiElectricpilecode}" />
						</td>
						<td style="align: left"><label style="align: left"><bmtag:message
									messageKey="electric.label.name" /> </label>
						</td>
						<td><input name="elpiElectricpilename"
							value="${tblElectricpile.elpiElectricpilename}" />
						</td>

						<td><label><bmtag:message
									messageKey="electric.label.way" /> </label>
						</td>
						<td><select name="elpiChargingmode" class="select_Style">
								<option value="">全部</option>
								<c:forEach var="item" items="${chargeList}">
									<option value="${item.pkConfigcontent}"
										${item.pkConfigcontent==
										tblElectricpile.elpiChargingmode ? 'selected="selected"' : ''} >
										${item.cocoContent}</option>
								</c:forEach>
						</select>
						</td>
					</tr>
					<tr>
						<td><label><bmtag:message
									messageKey="electric.label.state" /> </label>
						</td>
						<td><select name="elpiState" class="select_Style">
								<option value="">全部</option>
								<%-- <option value="0" ${tblElectricpile.elpiState==0
									? 'selected="selected"' : ''}>草稿</option>
								<option value="5" ${tblElectricpile.elpiState==5
									? 'selected="selected"' : ''}>提交审核</option>
								<option value="3" ${tblElectricpile.elpiState==3
									? 'selected="selected"' : ''}>已驳回</option> --%>
								<option value="10" ${tblElectricpile.elpiState==10
									? 'selected="selected"' : ''}>专属</option>
								<option value="15" ${tblElectricpile.elpiState==15
									? 'selected="selected"' : ''}>分享</option>
						</select>
						</td>
						<td><label><bmtag:message
									messageKey="electric.label.power" /> </label>
						</td>
						<td><select name="elpiPowersize" class="select_Style">
								<option value="">全部</option>
								<c:forEach var="item" items="${powerList}">
									<option value="${item.pkConfigcontent}"
										${item.pkConfigcontent==
										tblElectricpile.elpiPowersize ? 'selected="selected"' : ''} >
										${item.cocoContent}</option>

								</c:forEach>
						</select>
						</td>
						<td><label><bmtag:message
									messageKey="electric.label.type" /> </label>
						</td>
						<td><select name="elpiType" class="select_Style">
								<option value="">全部</option>
								<c:forEach var="item" items="${typeList}">
									<option value="${item.pkConfigcontent}"
										${item.pkConfigcontent==
										tblElectricpile.elpiType ? 'selected="selected"' : ''} >
										${item.cocoContent}</option>

								</c:forEach>
						</select>
						</td>

					</tr>
					<tr>
						<td style="align: left"><label style="align: left"><bmtag:message
									messageKey="electric.label.connector" /> </label>
						</td>
						<td><select name=elpiPowerinterface class="select_Style">
								<option value="">全部</option>
								<c:forEach var="item" items="${connectorList}">
									<option value="${item.pkConfigcontent}"
										${item.pkConfigcontent==
										tblElectricpile.elpiPowerinterface ? 'selected="selected"' : ''} >
										${item.cocoContent}</option>

								</c:forEach>
						</select>
						</td>
						<td style="align: left"><label style="align: left"><bmtag:message
									messageKey="electric.icon.muzzleSum" /> </label>
						</td>
						<td><select name="elpiPowernumber" class="select_Style">
								<option value="0">全部</option>
								<option value="1" ${tblElectricpile.elpiPowernumber==1
									? 'selected="selected"' : ''}>单枪头</option>
								<option value="2" ${tblElectricpile.elpiPowernumber==2
									? 'selected="selected"' : ''}>多枪头</option>
						</select>
						</td>

						<c:choose>
							<c:when
								test="${loginUser.userLevel==1 || loginUser.userLevel ==2}">
								<td><label><bmtag:message
											messageKey="electric.label.user" /> </label>
								</td>
								<td><input name="elPiUserName"
									value="${tblElectricpile.elPiUserName}" />
								</td>
							</c:when>

						</c:choose>


					</tr>
					<tr>
						<td>
							<label>区域选择</label>
						</td>
						<td colspan="2">
							<select  id="electricSelProvince" name="elPiOwnProvinceCode" class="required" style="float: none;width:130px;">
								<option value="">--请选择省份--</option>
								<c:forEach var="item" items="${proviceMap}">
									<option value="${item.key}"
										${item.key== tblElectricpile.elPiOwnProvinceCode ? 'selected="selected"' : ''} >
										${item.value.PROVINCE_NAME}
									</option>
								</c:forEach>
							</select>
							<select id="electricSelCity" name="elPiOwnCityCode" class="required" style="float: none;width:130px;">
								 <option value="">--请选择城市--</option>
								 <c:forEach var="item" items="${cityList}">
									<option value="${item.CITY_ID}"
										${item.CITY_ID==tblElectricpile.elPiOwnCityCode ? 'selected="selected"' : ''} >
										${item.CITY_NAME}
									</option>
								</c:forEach>
							</select>
							<select id="electricSelDistrict" name="elPiOwnCountyCode" class="required"  style="float: none;width:130px;">
								<option value="">--请选择区/县--</option>
								<c:forEach var="item" items="${areaList}">
									<option value="${item.area_id}"
										${item.area_id==tblElectricpile.elPiOwnCountyCode ? 'selected="selected"' : ''} >
										${item.area_name}
									</option>
								</c:forEach>
							</select>	
						</td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td align="right"><bmtag:button
								messageKey="common.button.search" type="submit"
								id="formSubmitter" />
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
			<li><a class="icon"  rel="ids"
				postType="string"  onclick="xiafa(this)"
				title="费率选择"><span>费率选择 </span>
			</a></li>

		</ul>
	</div>
	<table class="table" width="100%" layoutH="209">
		<thead>
			<tr>
				<th><input type="checkbox" group="ids" class="checkboxCtrl">
				</th>
				<%-- <th><bmtag:message messageKey="common.label.index" />
				</th> --%>
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
				<%-- <th><bmtag:message messageKey="electric.label.carState" /> --%>
				</th>
				<%-- <th><bmtag:message messageKey="electric.label.rejectReason" />
				</th> --%>
				<th><bmtag:message messageKey="连接状态"/></th>
				<th><bmtag:message messageKey="所属省份" />
				</th>
				<th><bmtag:message messageKey="所属城市" />
				</th>
				<th><bmtag:message messageKey="所属区/县" />
				</th>
				<th><bmtag:message messageKey="地区代码" /></th>
				<th><bmtag:message messageKey="费率id" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${electricList}" var="electric" varStatus="status">
				<tr target="id" rel="${electric.pk_ElectricPile}" align="center">
					<td><input name="ids" value="${electric.pk_ElectricPile}" gate-id = "${electric.elPi_GateId}" city-id="${electric.elPi_OwnCityCode}" data-value="${electric.elPi_ElectricPileCode}:${electric.elPi_State}:${electric.comm_status}"
						type="checkbox">
					</td>
					<%-- <td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td> --%>
					<td>${electric.elPi_ElectricPileCode}</td>
					<td>${electric.elPi_ElectricPileName}</td>
					<td>${electric.elPi_UserName}</td>
					<td><c:if test="${electric.elPi_State ==0}">草稿</c:if> <c:if
							test="${electric.elPi_State ==5}">提交审核 </c:if> <c:if
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
						${elPi_Maker_Name}
					</td>
					<%-- <td>${electric.headSateDetail}</td> --%>
					<!-- <td></td> -->
					<%-- <td>${electric.elPi_RejectionReason}</td> --%>
					<td>
						<c:if test="${electric.comm_status == '0'}">未连接</c:if>
						<c:if test="${electric.comm_status == '1'}">已连接</c:if>
					</td>
					<td>${electric.elPi_OwnProvince}</td>
					<td>${electric.elPi_elPiOwnCity}</td>
					<td>${electric.elPi_elPiOwnCounty}</td>
					<td>${electric.elPi_OwnCountyCode}</td>
					<td>${electric.elPi_RateInformationId}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<!-- <span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value});pageChange()">
					<option value="4" ${pager.numPerPage==4?"selected":""}>4</option>
					<option value="20" ${pager.numPerPage==20?"selected":""}>20</option>
					<option value="100" ${pager.numPerPage==100?"selected":""}>100</option>
					<option value="200" ${pager.numPerPage==200?"selected":""}>200</option>
			</select>  -->共${pager.total }条, 共${pager.pageTotal}页</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>

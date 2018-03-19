<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>充电点信息</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/static/css/station/stationList.css"/>
	</head>
	<body>
		<div id="container1">
			<div class="box">
				<!--<div id="" class="AssetStatistics">
				</div>-->
				<div class="formList">
					<form id="powerStationListForm">
					<input type="text" name="postName" class="160width" placeholder="充电点名称">
					<select name="postStatus" id="stationState">
						<option disabled selected>充电点状态</option>
					</select>
					<select name="postOwnProvinceCode" id="powerStationListProvince" onchange="ProvinceChange(this)">
					</select>
					<select name="postOwnCityCode" id="powerStationListCity" onchange="cityChange(this)">
						<option disabled selected>请选择市</option>
					</select>
					<select name="postOwnCountyCode" id="powerStationListArea">
						<option disabled selected>请选择区</option>
					</select>
					
					<span class="check" onclick="powerStationListSearch()">查询</span>
					</form>
				</div>
				<div class="btnGroup">
					<a target="selectedTodo" rel="ids" href="/admin/station/stationRemove.do"  posttype="string" title="确定删除吗？"><span style="margin-left: 0;">删除</span></a>
					<a target="selectedTodo" rel="ids" href="/admin/station/stationOnLine.do"  posttype="string" title="确定分享吗？"><span>分享</span></a>
					<a target="selectedTodo" rel="ids" href="/admin/station/stationOffLine.do"  posttype="string" title="确定专属吗？"><span>专属</span></a>
					<span class="superLink fileExport" rel="powerStationListForm" href="/admin/station/stationExport.do" >导出EXCEL</span>
				</div>
			</div>
			<div class="box2 col-md-10 col-sm-8">
				<div class="padding30">
						<table class="myTable">
							<thead>
								<tr class="active">
									<th class="smallWidth"><input type="checkbox" name="" class="selAll" value=""/></th>
									<th class="smallWidth">序号</th>
									<th>充电点名称</th>
									<th>充电点状态</th>
									<th>电桩总数</th>
									<th>充电点地址</th>
									<th>停车费</th>
									<th>开放时间</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="powerStationListTbody">
							</tbody>
						</table>
						<div id="" class="" style="height: 40px; width: auto;">
							&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
				</div>
			</div>
			<div id="powerStationListPage" class="pagination col-md-10 col-sm-10">
			</div>
		</div>
	</body>
<script src="${ctx}/static/js/station/stationList.js" type="text/javascript" charset="utf-8"></script>
</html>
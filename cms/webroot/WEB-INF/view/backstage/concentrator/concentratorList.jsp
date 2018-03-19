<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>集中器信息</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/static/css/concentrator/concentratorList.css"/>
	</head>
	<body>
		<div id="container1">
			<div class="box">
				<div class="formList">
					<form id="concentratorListForm">
					<input type="text" name="coctConcentratorName" class="160width" placeholder="集中器名称/SIM卡号">
					<select name="coctSIMTYPE" id="serviceProvider">
						<option disabled selected>SIM卡运营商</option>
					</select>
					<select name="coctState" id="concentratorState">
						<option disabled selected>集中器状态</option>
					</select>
					
					<span class="check" onclick="concentratorListSearch()">查询</span>
					</form>
				</div>
				<div class="btnGroup">
					<span style="margin-left: 0;" id="concentratorAdd">新增</span>
					<a target="selectedTodo" rel="ids" href="/admin/concentrator/concentratorRemove.do"  posttype="string" title="确定删除吗？"><span>删除</span></a>
					<a target="selectedTodo" rel="ids" href="/admin/concentrator/concentratorOffLine.do"  posttype="string" title="确定离线吗？"><span>离线</span></a>
				</div>
			</div>
			<div class="box2 col-md-10 col-sm-8">
				<div class="padding30">
						<table class="myTable">
							<thead>
								<tr class="active">
									<th class="smallWidth"><input type="checkbox" name="" class="selAll" value=""/></th>
									<th class="smallWidth">序号</th>
									<th>集中器ID</th>
									<th>集中器名称</th>
									<th>SIM卡号</th>
									<th>SIM卡编码</th>
									<th>SIM卡运营商</th>
									<th>资产所有者</th>
									<th>状态</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="concentratorListTbody">
								
							</tbody>
						</table>
						<div id="" class="" style="height: 40px; width: auto;">
							&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
				</div>
			</div>
			<div id="concentratorListPage" class="pagination col-md-10 col-sm-10">

			</div>

		</div>
	</body>
<script src="${ctx}/static/js/concentrator/concentratorList.js" type="text/javascript" charset="utf-8"></script>
</html>
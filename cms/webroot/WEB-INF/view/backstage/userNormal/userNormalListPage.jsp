<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>注册用户</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/static/css/userNormal/userNormalList.css"/>
	</head>
	<body>
		<div id="container1">
			<div class="box">
				<div class="formList">
					<form id="listUserInfoForm">
						<input name="userAccount"  type="text" class="160width" placeholder="手机号">
							<div class="dataBlock">
								<input type="" name="startDate" id="startDateUserNormal" value="" placeholder="起始时间"
									onClick="WdatePicker({el:'startDateUserNormal',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'endDateUserNormal\')}'})"/>
								<span>至</span>
								<input type="" name="endDate" id="endDateUserNormal" value="" placeholder="截止时间"
									onClick="WdatePicker({el:'endDateUserNormal',minDate:'#F{$dp.$D(\'startDateUserNormal\')}'})" />
							</div>
						<select class="marginLeft10" name="normRegisteType" id="normRegisteType">
							<option disabled selected>注册渠道</option>
						</select>
						<span class="check marginLeft10" onclick="listUserInfoSearch()">查询</span>
					</form>
					<div class="btnGroup">
						<a target="selectedTodo" rel="ids" href="/admin/userNormal/frostUsers.do"  posttype="string" title="确定冻结吗？"><span style="margin-left: 0;">冻结</span></a>
						<a target="selectedTodo" rel="ids" href="/admin/userNormal/unFrostUsers.do"  posttype="string" title="确定解冻吗？"><span>解冻</span></a>
						<span class="superLink fileExport" rel="listUserInfoForm" href="/admin/userNormal/userNormalExport.do">导出EXCEL</span>
					</div>
				</div>
			</div>
			<div class="box2 col-md-10 col-sm-8">
				<div class="padding30">
					<table class="myTable listPayOrderTable">
						<thead>
							<tr class="active">
								<th class="smallWidth"><input type="checkbox" name="" class="selAll" value=""/></th>
								<th class="smallWidth">序号</th>
								<th>注册时间</th>
								<th>手机号</th>
								<th>用户姓名</th>
								<th>注册渠道</th>
								<th>邮箱</th>
								<th>联系地址</th>
								<th>品牌</th>
								<th>车型</th>
								<th>状态</th>
							</tr>
						</thead>
						<tbody id="listUserInfoTbody">
						</tbody>
					</table>
					<div class="" style="height: 40px; width: auto;">
						&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
				</div>
			</div>
			<div id="listUserInfoPage" class="pagination col-md-10 col-sm-10">
			</div>
		</div>
	</body>
<script src="${ctx}/static/js/userNormal/userNormalList.js" type="text/javascript" charset="utf-8"></script>
</html>
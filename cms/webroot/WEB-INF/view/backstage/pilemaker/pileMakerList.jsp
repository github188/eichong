<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../../base.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>电桩制造商</title>
		<link rel="stylesheet" type="text/css" href="${ctx}/static/css/config/pileMakerList.css"/>
	</head>
	<body>
		<div id="container1">
			<div class="box">
				<div class="btnGroup">
					<span style="margin-left: 0;" id="pileMakerAdd">新增</span>
					<a target="selectedTodo" rel="ids" href="/admin/pilemaker/pilemakerRemove.do"  posttype="string" title="确定删除吗？"><span>删除</span></a>
				</div>
			</div>
			<div class="box2 col-md-10 col-sm-8">
				<div class="padding30">
						<table class="myTable">
							<thead>
								<tr class="active">
									<th class="smallWidth"><input type="checkbox" name="" class="selAll"/></th>
									<th class="smallWidth">序号</th>
									<th>电桩制造商</th>
									<th>制造商标识</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody id="pileMakerListTbody">
							</tbody>
						</table>
						<div id="" class="" style="height: 40px; width: auto;">
							&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
				</div>
			</div>
			<div id="pileMakerListPage" class="pagination col-md-10 col-sm-10">
			</div>

		</div>
		<!--电桩制造商新增-->
		<div id="pileMakerAddContent" class="pileMakerAdd" style="display: none;">
			<form id="pilemakerAddForm" method="post"
				action="/admin/pilemaker/pilemakerSave.do" callback="refreshCurrent()">
			<div class="line">
				<span>制造商名称</span>
				<input  name="makerName" class="marginLeft30" id="carMarkerNameAdd"/>
			</div>
			<div class="line">
				<span>制造商标识</span>
				<input  name="makerRemark"  class="marginLeft30" id="carMarkerTagAdd"/>
			</div>
			<div class="pileMakerAddTextTip" style="display: none; color: #FF0000; margin-top: 6px;">
				
			</div>
			</form>
		</div>
		<!--电桩制造商编辑-->
		<div id="pileMakerEditContent" class="pileMakerAdd" style="display: none;">
			<form id="pilemakerEditForm" method="post"
				action="/admin/pilemaker/pilemakerModify.do" callback="refreshCurrent()">
				<input type="hidden" name="pkCarmaker" id="pkCarmaker"  />
			<div class="line">
				<span>制造商名称</span>
				<input name="makerName" id="makerName"  class="marginLeft30" id="carMarkerNameEdit"/>
			</div>
			<div class="line">
				<span>制造商标识</span>
				<input   name="makerRemark" id="makerRemark" class="marginLeft30" id="carMarkerTagEdit"/>
			</div>
			<div class="pileMakerEditTextTip" style="display: none; color: #FF0000; margin-top: 6px;">
				
			</div>
			</form>
		</div>
	</body>
<script src="${ctx}/static/js/config/pileMakerList.js" type="text/javascript" charset="utf-8"></script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
ul.rightTools {
	float: right;
	display: block;
}

ul.rightTools li {
	float: left;
	display: block;
	margin-left: 5px
}
</style>
</head>
<body>
	<div class="pageContent" style="padding: 5px">
		<div class="panel" defH="40">
			<h1>菜单基本信息</h1>
		</div>

		<div class="tabs">
			<div class="tabs">
				<div class="tabsContent">
					<div>

						<div layoutH="80%" class="accordion" fillSpace="sidebar"
							style="float: left; display: block; overflow: auto; width: 240px; border: solid 1px #CCC; line-height: 21px; background: #fff">
							<div class="accordionHeader">
								<h2>
									<span>Folder</span>菜单一览
								</h2>
							</div>
							<div id="menuTopBox">${menuTreeModel}</div>
						</div>

						<div id="menuBox" class="unitBox" style="margin-left: 246px;">
							<%@ include file="menu.jsp"%>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/common/include.inc.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<div class="pageContent">
	<form method="post" action="publishEp/updatePublishEp.do" id="editForm"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, navTabAjaxDone);">
		
		<input type="hidden" name="id" value="${publishEp.id}" />
		
		
		<div class="pageFormContent nowrap" layoutH="55">
			<dl>
				<dt>所在地址:</dt>
				<dd>
					${publishEp.address}
				</dd>
			</dl>
			<dl>
				<dt>经度：</dt>
				<dd>
					${publishEp.longitude}
				</dd>
			</dl>
			<dl>
				<dt>纬度:</dt>
				<dd>
					${publishEp.latitude}
				</dd>
			</dl>
			<dl>
				<dt>参数说明：</dt>
				<dd>
					${publishEp.parameter_note}
				</dd>
			</dl>
			<dl>
				<dt>其他信息：</dt>
				<dd>
					${publishEp.note}
				</dd>
			</dl>
			<dl>
				<dt>拍摄图片:</dt>
				<dd>
					<c:forEach items="${images}" var="img" varStatus="status">
						<img src="${path}/${img}" style="width:150px;height:150px;"/>
					</c:forEach>
				</dd>
			</dl>
		</div>
		
		
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button id="addButton" type="submit">
								处理
							</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">
								取消
							</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
	
</div>

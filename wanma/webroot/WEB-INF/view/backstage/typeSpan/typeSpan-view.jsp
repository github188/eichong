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
	查看产品型号
</h2>
<div class="pageContent">
	<form>
		<div class="pageFormContent nowrap" layoutH="97">

			<dl>
				<dt>
					产品型号
				</dt>
				<dd>
					${typeSpan.tsTypeSpan}
				</dd>
			</dl>
			<dl>
				<dt>
					名称
				</dt>
				<dd>
					${typeSpan.tsModelName}
				</dd>
			</dl>
			<dl>
				<dt>
					说明
				</dt>
				<dd>
					${typeSpan.tsFactTag}
				</dd>
			</dl>
			<dl>
				<dt>
					文件ID和名称
				</dt>
				<dd>		 
					${typeSpan.tsFileName}
				</dd>
			</dl>
			<dl>
				<dt>
					外部产品型号
				</dt>
				<dd>
					${typeSpan.tsProductNumber}
				</dd>
			</dl>
			<dl>
				<dt>
					备注
				</dt>
				<dd>
					${typeSpan.tsRemarks}
				</dd>
			</dl>
			
			<dl>
				<dt>
					<strong>添加BOM清单：</strong>
				</dt>
			</dl>
			<dl>
				<dt>
					<table>
						<thead>
							<tr height="30">
								<th align="left">硬件型号</th>
								<th align="left">硬件版本号</th>
								<th align="left">固件编号</th>
								<th align="left">固件版本号</th>
								<th align="left">应用程序MD5值</th>
								<th align="left" style="width: 100px">强制更新</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${bomList}" var="bom" varStatus="status">
								<tr>
									<td>
										<input disabled="disabled" type="text" value="${bom.blHardwareNumber}">
									</td>
									<td><input disabled="disabled" type="text" value="${bom.blHardwareVersion}"></td>
									<td><input disabled="disabled" type="text" value="${bom.blFirmwareNumber}"></td>
									<td><input disabled="disabled" type="text" value="${bom.blFirmwareVersion}"></td>
									<td><input disabled="disabled" type="text" value="${bom.blFileMd5}"></td>
									<td><div style="width: 80px;margin-left: 10px;">
										<input disabled="disabled"type="radio" <c:if test="${bom.blForceUpdate == '1'}">checked</c:if>>是
										<input disabled="disabled"type="radio" <c:if test="${bom.blForceUpdate == '0'}">checked</c:if>>否
										</div>
									</td>
									<td></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</dt>
			</dl>
		</div>

		<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.back" type="button"
						dwzClass="close" />
				</li>
			</ul>
		</div>
	</form>
</div>
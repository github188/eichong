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
	编辑产品型号
</h2>
<div class="pageContent">
	<form id="formDiv" method="post" action="product/changeTypeSpan.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">
		<input name="pkTypeSpanId" type="hidden" value="${typeSpan.pkTypeSpanId}">
		<div class="pageFormContent nowrap" layoutH="97">

			<dl>
				<dt>
					产品型号
				</dt>
				<dd>
					<input name="tsTypeSpan" class="textInput required"  readonly="readonly"
						value="${typeSpan.tsTypeSpan}" style="width: 165px;" /><b>（格式参考：ICPAC-16101）</b> <span
						class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					名称
				</dt>
				<dd>
					<input name="tsModelName" value="${typeSpan.tsModelName}" class="textInput required" maxlength="11"
						style="width: 165px;" /><b>（格式参考：爱充交流壁挂桩）</b> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					说明
				</dt>
				<dd>
					<input name="tsFactTag" class="textInput required" value="${typeSpan.tsFactTag}"
						maxlength="20" style="width: 165px;" /><b>（格式参考：16kw）</b> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					文件ID和名称
				</dt>
				<dd>		 
					<input name="tsFileName" class="textInput required" value="${typeSpan.tsFileName}"
						maxlength="50" style="width: 165px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					外部产品型号
				</dt>
				<dd>
					<input name="tsProductNumber" class="textInput" value="${typeSpan.tsProductNumber}"
						maxlength="20" style="width: 165px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					备注
				</dt>
				<dd>
					<textarea name="tsRemarks" class="textInput "
						maxlength="20" style="width: 165px;" >${typeSpan.tsRemarks}</textarea> <span class="info"></span>
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
								<th align="left"><div class="button"><div class="buttonContent" style="width: 30px"><button type="button" onclick="addNew()">新增</button></div></div></th>
							</tr>
						</thead>
						<tbody id="row_bom">
							<c:forEach items="${bomList}" var="bom" varStatus="status">
								<tr class="bomList">
									<td>
										<input class="rmIndex" name=".pkBomListId" type="hidden"  value="${bom.pkBomListId}">
										<input class="rmIndex" name=".pkTypeSpanId" type="hidden"  value="${typeSpan.pkTypeSpanId}">
										<input class="required rmIndex" name=".blHardwareNumber" readonly="readonly"  type="text" value="${bom.blHardwareNumber}">
									</td>
									<td><input class="required rmIndex" name=".blHardwareVersion" readonly="readonly" type="text" value="${bom.blHardwareVersion}"></td>
									<td><input class="required rmIndex" name=".blFirmwareNumber" readonly="readonly" type="text" value="${bom.blFirmwareNumber}"></td>
									<td><input class="required rmIndex" name=".blFirmwareVersion" readonly="readonly" type="text" value="${bom.blFirmwareVersion}"></td>
									<td><input class="required rmIndex" name=".blFileMd5" type="text" readonly="readonly"  minlength="32" maxlength="32" style="width:230px;" value="${bom.blFileMd5} "></td>
									<td><div style="width: 80px;margin-left: 10px;" readonly="readonly">
										<input class="required rmIndex" readonly="readonly" name="${status.index}.blForceUpdate" value="1" type="radio" <c:if test="${bom.blForceUpdate == '1'}">checked</c:if>>是
										<input class="required rmIndex" readonly="readonly" name="${status.index}.blForceUpdate" value="0" type="radio" <c:if test="${bom.blForceUpdate == '0'}">checked</c:if>>否
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
				<li><bmtag:button messageKey="common.button.submit" id="formSubmitter"
						type="button" onclick="doSubmit()"/>
				</li>
				<li><bmtag:button messageKey="common.button.back" type="button"
						dwzClass="close" />
				</li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/typeSpan/typeSpan.js" />
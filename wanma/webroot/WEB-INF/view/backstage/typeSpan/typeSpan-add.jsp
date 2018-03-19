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
	新增产品型号
</h2>
<div class="pageContent">
	<form id="formDiv" method="post" action="product/addTypeSpan.do"
		class="pageForm required-validate" enctype="multipart/form-data"
		onsubmit="return iframeCallback(this, navTabAjaxDone)">

		<div class="pageFormContent nowrap" layoutH="97">

			<dl>
				<dt>
					产品型号
				</dt>
				<dd>
					<input name="tsTypeSpan" class="textInput required"
						value="" style="width: 165px;" /><b>（格式参考：ICPAC-16101）</b><span
						class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					名称
				</dt>
				<dd>
					<input name="tsModelName" value="" class="textInput required" maxlength="11"
						style="width: 165px;" /><b>（格式参考：爱充交流壁挂桩）</b> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					说明
				</dt>
				<dd>
					<input name="tsFactTag" class="textInput required"
						maxlength="20" style="width: 165px;" /><b>（格式参考：16kw）</b> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					文件ID和名称
				</dt>
				<dd>		 
					<input name="tsFileName" class="textInput required"
						maxlength="50" style="width: 165px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					外部产品型号
				</dt>
				<dd>
					<input name="tsProductNumber" class="textInput"
						maxlength="20" style="width: 165px;" /> <span class="info"></span>
				</dd>
			</dl>
			<dl>
				<dt>
					备注
				</dt>
				<dd>
					<textarea name="tsRemarks" class="textInput "
						maxlength="20" style="width: 165px;" ></textarea> <span class="info"></span>
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
							<tr class="bomList">
								<td><input class="required rmIndex" name=".blHardwareNumber" type="text"></td>
								<td><input class="required rmIndex" name=".blHardwareVersion" type="text"></td>
								<td><input class="required rmIndex" name=".blFirmwareNumber" type="text"></td>
								<td><input class="required rmIndex" name=".blFirmwareVersion" type="text"></td>
								<td><input class="required rmIndex" name=".blFileMd5" type="text" minlength="32" maxlength="32" style="width:230px;"></td>
								<td><div style="width: 80px;margin-left: 10px;">
									<input data-type="radio" class="required radio rmIndex" name=".blForceUpdate" value="1" type="radio">是
									<input data-type="radio" class="required radio rmIndex" name=".blForceUpdate" value="0" type="radio" checked>否
									</div>
								</td>
								<td><div class="button"><div class="buttonContent" style="width: 30px"><button type="button" onclick="removeTr(this)">删除</button></div></div></td>
							</tr>
						</tbody>
					</table>
				</dt>
			</dl>
		</div>

		<div class="formBar">
			<ul>
				<li><bmtag:button messageKey="common.button.submit" id="formSubmitter"
						type="button" onclick="doSubmit()" />
				</li>
				<li><bmtag:button messageKey="common.button.back" type="button"
						dwzClass="close" />
				</li>
			</ul>
		</div>
	</form>
</div>
<script type="text/javascript" src="<%=request.getContextPath()%>/static/js/typeSpan/typeSpan.js" />
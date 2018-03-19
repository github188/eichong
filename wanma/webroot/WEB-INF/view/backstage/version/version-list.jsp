<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript">
function addVersion(){
	var $form=navTab.getCurrentPanel().find("#pagerForm");
	if (!$form.valid()) {
		return false;
	}else{
		$.ajax({
			type:'POST', 
			url:basepath+"/admin/version/insertVersion.do", 
			data: $form.serialize(),
			dataType:'json',
			cache: false,
			success: function(data) {
               if(data.statusCode==200){
            	   alertMsg.correct(data.message);
            	   navTab.reload(data.forwardUrl);
               }else{
            	   alertMsg.error(data.msg);
               }
            }
		});
	}
}
</script>
<div class="pageContent">
<form id="pagerForm" class="pageForm  required-validate" method="post" action="version/versionList.do" onsubmit="return navTabSearch(this);">
<input type="hidden" name="status" value="${pager.status}" /> 
<input type="hidden" name="keywords" value="${pager.keywords}" />
<input type="hidden" name="pageNum" value="${pager.pageNum}" /> 
<input type="hidden" name="numPerPage" value="${pager.numPerPage}" />	
	<div class="pageFormContent nowrap" >
			<dl>
				<dt>版本号：</dt>
				<dd>
					<input name="versNumber" type="text" class="required" size="25"
						maxlength="20" />
				</dd>
			</dl>
			<dl>
				<dt>版本类型：</dt>
				<dd>
					<select name="versType" class="required" style="width:182px;">
						<option value="1" >安卓</option>
						<option value="2" >IOS</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>版本链接：</dt>
				<dd>
					<input  name="versUrl" type="text" class="required" size="25"
						maxlength="200" />
				</dd>
			</dl>
			<dl>
				<dt>强制更新：</dt>
				<dd>
					<input type="radio" name="isAutoUpdate" value="0" checked/>否
					<input type="radio" name="isAutoUpdate" value="1" />是
				</dd>
			</dl>
			<dl>
				<dt>版本描述：</dt>
				<dd>
					<textarea name="versRemark" cols="40" rows="3"></textarea>
				</dd>
			</dl>
			<dl>
				<dt>&nbsp;</dt>
				<dd>
					<div class="button" > 
						<div class="buttonContent">
							<button id="formSubmitter" type="button" onclick="addVersion()">
								 新 增&nbsp;&nbsp;&nbsp;&nbsp;
							</button>
						</div>
					</div>
				</dd>
			</dl>
		</div>
</form>		
	<table class="table" width="100%" layoutH="260">
		<thead>
			<tr align="center">
				<th style="border-top:1px solid #d0d0d0;"><bmtag:message messageKey="版本号" /></th>
				<th style="border-top:1px solid #d0d0d0;"><bmtag:message messageKey="版本类型" /></th>
				<th style="border-top:1px solid #d0d0d0;"><bmtag:message messageKey="版本链接" /></th>
				<th style="border-top:1px solid #d0d0d0;"><bmtag:message messageKey="版本描述" /></th>
				<th style="border-top:1px solid #d0d0d0;"><bmtag:message messageKey="强制更新" /></th>
				<th style="border-top:1px solid #d0d0d0;"><bmtag:message messageKey="操作" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${versionList}" var="version" varStatus="status">
				<tr target="id" rel="${version.pkVersion}" align="center">
					<td>${version.versNumber}</td>
					<td>
						<c:if test="${version.versType==1}">
							安卓
						</c:if>
						<c:if test="${version.versType==2}">
							IOS
						</c:if>
					</td>
					<td>${version.versUrl}</td>
					<td>${version.versRemark}</td>
					<td>
						<c:if test="${version.isAutoUpdate==0}">
							否
						</c:if>
						<c:if test="${version.isAutoUpdate==1}">
							是
						</c:if>
					</td>
					<td><a class="delete"  target="ajaxTodo" title="确定要删除吗？" warn="请选择一条记录"
					 href="version/deleteById.do?pkVersion=${version.pkVersion}">删除</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
					<option value="4" ${pager.numPerPage == 4?"selected":""}>4</option>
					<option value="20" ${pager.numPerPage == 20?"selected":""}>20</option>
					<option value="100" ${pager.numPerPage == 100?"selected":""}>100</option>
					<option value="200" ${pager.numPerPage == 200?"selected":""}>200</option>
			</select> 共${pager.total }条, 共${pager.pageTotal}页
			</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
			pageNumShown="10" currentPage="${pager.pageNum}"></div>
	</div>
</div>

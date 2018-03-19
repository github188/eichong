<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page isELIgnored="false"%>
<div class="pageContent">
		<div layoutH="10"
			style="float: left; display: block; overflow: auto; width: 240px; border: solid 1px #CCC; line-height: 21px; background: #fff">
			<ul id="treeDemo" class="ztree" style="height:95%;"></ul>
		</div>

		<div id="relBox" style="margin-left: 246px;">
			
		</div>
</div>

<SCRIPT type="text/javascript">
	function zTreeLink(event, treeId, treeNode){
		var url = "electric/getElectricPileHeadList.do?"
	 	var zTreeA = $("#relBox");
		if(treeNode.isParent){
			if(treeNode.getParentNode()){
				url += "elPiOwnCityCode="+treeNode.id
			}else{
				url += "elPiOwnProvinceCode="+treeNode.id
			}
		}else{
			url += "elPiOwnCountyCode="+treeNode.id
		}
		
		zTreeA.loadUrl(url, {}, function(){
			zTreeA.find("[layoutH]").layoutH();
		});
	}
	
	var setting = {
		data : {
			simpleData : {
				enable : true
			}
		},
		callback: {
			onClick: zTreeLink
		}
	};

	var zNodes = ${areaTreeData};
	$(document).ready(
			function() {
				$.fn.zTree.init(navTab.getCurrentPanel().find("#treeDemo"),
						setting, zNodes);
			});
</SCRIPT>
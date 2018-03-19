<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page isELIgnored="false" %>
<div class="pageContent" >
			<div>
	
				<div layoutH="10" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
				   <ul id="treeDemo" class="ztree"></ul>
				</div>
				
				<div id="relBox" class="unitBox" style="margin-left:246px;">
					<!--#include virtual="list1.html" -->
				</div>
	
			</div>
			
	
</div>

<SCRIPT type="text/javascript">
		var setting = {
			callback: {
				onClick: zTreeOnClick
			},
			data: {
				key: {
                    title: "rel"
                },
				simpleData: {
					enable: true
				}
			}
		};

		var zNodes =[
			{ id:1, pId:0, name:"zTree Home", rel:"123123",open:true},
			{ id:2, pId:1, name:"zTree in Google", rel:"123123"},
			{ id:3, pId:2, name:"zTree in Iteye",rel:"123123"},
			{ id:4, pId:1, name:"Nothing...", rel:"123123"}
		];

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		function zTreeOnClick(event, treeId, treeNode) {
		    alert(treeNode.tId + ", " + treeNode.name);
		    var $rel = $("#relBox");
		    var url="menu/findMenuList.do";
		    $rel.loadUrl(url, {}, function(){
				$rel.find("[layoutH]").layoutH();
			});
		};
	</SCRIPT>
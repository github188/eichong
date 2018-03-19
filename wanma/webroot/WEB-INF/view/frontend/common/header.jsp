<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<div class="header">
    <div class="header-top">

        <c:choose>
            <c:when test="${empty userPk}">
                <p>
	              <span>
	                  <a id="login-page" href="javascript:void(0);">登录</a>
	              </span>
	                    <a>丨</a>
	              <span>
	                  <a id="regist-page" href="javascript:void(0);">注册</a>
	              </span>
                </p>
            </c:when>
	        <c:otherwise>
	            <p>
	            	<span class="user-wrap">
		            <span class="user-pic">
		                <c:choose>
		                    <c:when test="${fn:containsIgnoreCase(webUser.userImage,'http')}">
		                        <img src="${webUser.userImage}" alt="" style="width: 100%;">
		                    </c:when>
		                    <c:otherwise>
		                        <img src="<%=basePath%>${webUser.userImage}" alt="" style="width: 100%;">
		                    </c:otherwise>
		                </c:choose>
		            </span>
		            <span id="username">${webUser.userRealName}</span>
		                <span>欢迎你!</span>
		            </span>
		            <span>
		                <a href="#" id="my-center">个人中心</a>
		            </span>
		            <a>丨</a>
		            <span>
		                <a href="#" class="logout">注销</a>
		            </span>		
	            </p>
	            <input type="hidden" id="userId" value="${userPk}"/>
	        </c:otherwise>
        </c:choose>
    </div>
    <div class="header-content">
        <img src="<%=basePath%>/static/images/icon/logo.png"/>
        <ul class="nav">
            <li class="city">
                <span id="currentCity">杭州</span>
                <span class="select-city" id="choose-city" style="cursor: pointer;">[切换城市]</span>
            </li>
            <li>
                <a>首页</a>
            </li>
            <li>
                <a>电桩查找</a>
            </li>
            <li>
                <a>新能源商城</a>
            </li>
            <li class="download">
                <a>
                	App下载
                    <img src="<%=basePath%>/static/images/icon/download_B_icon.png"/>
                </a>
            </li>
            <li class="support">
             	服务支持
                <ul class="menu hidden">
                   <!--  <li><a>使用帮助</a></li> -->
                    <li><a>设备保修</a></li>
                    <li><a>意见反馈</a></li>
                </ul>
            </li>
            <li class="search">
                <div class="search-cat-btn">
		            <span class="sn-select-trigger">
		              	搜电桩 <!-- <i class="sn-arrow-left"></i> -->
		            </span>
                    <!-- <ul class="sn-select-content">
                        <li class="sn-select-item">搜电桩</li>
                        <li class="sn-select-item">搜充充电点</li>
                        <li class="sn-select-item">搜充电树</li>
                    </ul> -->
                </div>
                <input class="search-input" type="text" style="padding-left: 20px" id="searchName"/>
                <a class="search-btn" id="search-pile" href="javascript:void(0);">搜索</a>
            </li>
        </ul>
    </div>
</div>

<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/WEB-INF/mytag.tld" prefix="my" %>
<%@ taglib uri="/WEB-INF/bluemobi-tag.tld" prefix="bmtag" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="<%=request.getContextPath()%>/res/commen.css"
      rel="stylesheet"/>


<div class="pageHeader">
    <form id="pagerForm" method="post"
          action="offLine/list.do"
          onsubmit="return navTabSearch(this);">
        <input type="hidden" name="status" value="${pager.status}"/>
        <input type="hidden" name="keywords" value="${pager.keywords}"/>
        <input type="hidden" id="pageNum" name="pageNum" value="${pager.pageNum}"/>
        <input type="hidden" name="numPerPage" value="${pager.numPerPage}"/>
        <div class="searchBar">
            <table class="searchContent">
                <tbody>
                <tr>
                    <td><label><bmtag:message
                            messageKey="桩体编码"/> </label>
                    </td>
                    <td><input name="elPiElectricPileCode"
                               value="${tblOffLineInfo.elPiElectricPileCode}"/>
                    </td>
                    <td><span>断网时间</span> <input id="beginOfflineTime"
                                                 name="beginOfflineTime" placeholder="请选择"
                                                 value="${tblOffLineInfo.beginOfflineTime}" class="date"
                                                 onClick="WdatePicker({el:'beginOfflineTime',minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'endOfflineTime\')}'})">
                        <span>至</span><input
                                id="endOfflineTime" name="endOfflineTime"
                                value="${tblOffLineInfo.endOfflineTime}" class="date"
                                placeholder="请选择"
                                onClick="WdatePicker({el:'endOfflineTime',minDate:'#F{$dp.$D(\'beginOfflineTime\')}'})">
                    </td>

                    <td style="align: left"><label style="align: left"><bmtag:message
                            messageKey="充电点名称"/> </label>
                    </td>
                    <td><input name="elPiRelevancePowerStation"
                               value="${tblOffLineInfo.elPiRelevancePowerStation}"/>
                    </td>

                    <td align="right"><bmtag:button
                            messageKey="common.button.search" type="submit" id="formSubmitter"/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </form>
</div>
<div class="pageContent">

    <table class="table" width="100%" layoutH="125">
        <thead>
        <tr align="center">
            <th><bmtag:message messageKey="common.label.index"/>
            </th>
            <th><bmtag:message messageKey="电桩编号"/>
            </th>
            <th><bmtag:message messageKey="充电点名称"/>
            </th>
            <th><bmtag:message messageKey="断网时间"/>
            </th>
            <th><bmtag:message messageKey="恢复时间"/>
            </th>
            <th><bmtag:message messageKey="断网时长"/>
            </th>
            <th><bmtag:message messageKey="断网原因"/>
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${infos}" var="info" varStatus="status">
            <tr target="id" rel="${info.id}" align="center">

                <td>${ status.index + 1 + pager.numPerPage*(pager.pageNum-1)}</td>
                <td>${info.elPiElectricPileCode}</td>
                <td> ${info.elPiRelevancePowerStation} </td>

                <td>${info.beginOfflineTime}</td>
                <td>${info.endOfflineTime}</td>
                <td>${info.interval}分</td>
                <td>${info.type}</td>

            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="panelBar">
        <div class="pages">
            共${pager.total }条, 共${pager.pageTotal}页</span>
        </div>
        <div class="pagination" targetType="navTab"
             totalCount="${pager.total}" numPerPage="${pager.numPerPage}"
             pageNumShown="10" currentPage="${pager.pageNum}"></div>
    </div>
</div>
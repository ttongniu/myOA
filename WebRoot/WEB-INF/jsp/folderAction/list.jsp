<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>文件夹管理</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 文件夹管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td style="text-align: center;" >文件夹名称</td>
                <td style="text-align: center;" >备注说明</td>
                <td style="text-align: center;" >类型</td>
                <td style="text-align: center;" >创建者</td>
            	<td style="text-align: center;">创建时间</td>
                <td style="text-align: center;">相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="roleList">
        
        <s:iterator value="recordList">
			<tr class="TableDetail1 template">
				<td style="width: 15%;text-align: center;"> <IMG SRC="${pageContext.request.contextPath}/style/images/FileType/folder.gif"/>
				 <s:a action="file_list?id=%{id}"> &nbsp;${name}&nbsp; </s:a>  </td>
				<td style="width: 20%;text-align: center;">&nbsp;${description}&nbsp;</td>
				<c:if test="${type==0}">
                <td style="width: 5%;text-align: center;">&nbsp;私有&nbsp;</td>
                </c:if>
                <c:if test="${type==1}">
                <td style="width: 5%;text-align: center;">&nbsp;共享&nbsp;</td>
                </c:if>
				<td style="width: 10%;text-align: center;">&nbsp;${user.name}&nbsp;</td>
				<td style="width: 15%;text-align: center;">&nbsp;${createTime}&nbsp;</td>
				<%-- <td style="width: 25%"><s:radio name="type" disabled="true" list="#{'0':'私有', '1':'共享'}"></s:radio></td>		 --%>
				<td style="width: 25%;text-align: center;">
				   <%--  <s:if test="#session.user.hasPrivilegeByName('岗位删除')"> --%>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<s:a action="folder_delete?id=%{id}" onclick="return confirm('确定要删除吗？')">删除</s:a>
					<%-- </s:if> --%>
				    <%-- <s:if test="#session.user.hasPrivilegeByName('岗位修改')"> --%>
					&nbsp;&nbsp;&nbsp;<s:a action="folder_editUI?id=%{id}">修改</s:a>
					<c:if test="${type==1}">
					&nbsp;&nbsp;&nbsp;<s:a action="folder_setPrivate?id=%{id}">设为私有</s:a>
					</c:if>
					<c:if test="${type==0}">
					&nbsp;&nbsp;&nbsp;<s:a action="folder_setShare?id=%{id}">设为共享</s:a>
					</c:if>
					<%-- </s:if> --%>
					<%-- <s:if test="#session.user.hasPrivilegeByName('设置权限')"> --%>
					<%-- <s:a action="role_setPrivilegeUI?id=%{id}">设置权限</s:a> --%>
					<%-- </s:if> --%>
				</td>
			</tr>  
        </s:iterator>

        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
		<div id="TableTail_inside">
		      <%-- <s:if test="#session.user.hasPrivilegeByName('岗位添加')"> --%>
			<s:a action="folder_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
			  <%-- </s:if> --%>
        </div>
    </div>
    <!-- 页面信息 -->
    <%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
    <s:form action="folder_list"></s:form>
</div>
</body>
</html>

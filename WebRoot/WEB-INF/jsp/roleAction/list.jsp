<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>岗位列表</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 岗位管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td style="text-align: center;width: 30%">岗位名称</td>
                <td style="text-align: center;width: 30%">岗位说明</td>
                <td style="text-align: center;width: 40%">相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="roleList">
        
        <s:iterator value="recordList">
			<tr class="TableDetail1 template">
				<td style="text-align: center;width: 30%">${name}&nbsp;</td>
				<td style="text-align: center;width: 30%">${description}&nbsp;</td>
				<td style="text-align: center;width: 40%">
				   <%--  <s:if test="#session.user.hasPrivilegeByName('岗位删除')"> --%>
					<s:a action="role_delete?id=%{id}" onclick="return confirm('确定要删除吗？')">删除</s:a>
					<%-- </s:if> --%>
				    <%-- <s:if test="#session.user.hasPrivilegeByName('岗位修改')"> --%>
					<s:a action="role_editUI?id=%{id}">修改</s:a>
					<%-- </s:if> --%>
					<%-- <s:if test="#session.user.hasPrivilegeByName('设置权限')"> --%>
					<s:a action="role_setPrivilegeUI?id=%{id}">设置权限</s:a>
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
			<s:a action="role_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
			  <%-- </s:if> --%>
        </div>
    </div>
    <!-- 页面信息 -->
    <%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
    <s:form action="role_list"></s:form>
</div>
</body>
</html>

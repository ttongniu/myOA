<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>通讯录列表</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 通讯录管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="100px">姓名</td>
                <td width="50px">性别</td>
                <td width="100px">电话号码</td>
                <td width="150px">电子邮件</td>
                <td width="150px">部门名称</td>
                <td width="150px">家庭住址</td>
                <td width="150px">QQ</td>
                <td width="150px">备注说明</td>
                <td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="roleList">
        
        <s:iterator value="recordList">
			<tr class="TableDetail1 template">
				<td style="text-align: center;">&nbsp;${name}&nbsp;</td>
				<td style="text-align: center;">&nbsp;${gender}&nbsp;</td>
				<td style="text-align: center;">&nbsp;${phoneNumber}&nbsp;</td>
				<td style="text-align: center;">&nbsp;${email}&nbsp;</td>
				<td style="text-align: center;">&nbsp;${department.name}&nbsp;</td>
				<td style="text-align: center;">&nbsp;${address}&nbsp;</td>
				<td style="text-align: center;">&nbsp;${QQ}&nbsp;</td>
				<td style="text-align: center;">&nbsp;${description}&nbsp;</td>
				<td style="text-align: center;">
				   <%--  <s:if test="#session.user.hasPrivilegeByName('岗位删除')"> --%>
					<s:a action="addressBook_delete?id=%{id}" onclick="return confirm('确定要删除吗？')">删除</s:a>
					<%-- </s:if> --%>
				    <%-- <s:if test="#session.user.hasPrivilegeByName('岗位修改')"> --%>
					<s:a action="addressBook_editUI?id=%{id}">修改</s:a>
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
			<s:a action="addressBook_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
			  <%-- </s:if> --%>
        </div>
    </div>
    <!-- 页面信息 -->
    <%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
    <s:form action="addressBook_list"></s:form>
</div>
</body>
</html>

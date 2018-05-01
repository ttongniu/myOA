<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>名片夹列表</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
               <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 共享名片夹列表
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<div id="QueryArea">
	<div style="height: 30px">
		<s:form action="cardcase_listPrivate">
			<table border=0 cellspacing=3 cellpadding=5>
				<tr>
					<td>按名称查询：</td>
					<td> <s:textfield name="name" /></td>
					
					<td><a href=""><input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG"/></a></td>
				</tr>
			</table>
		</s:form>
	</div>
</div>
<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td style="text-align: center;" >文件夹名称</td>
                <td style="text-align: center;" >备注说明</td>
                <td style="text-align: center;" >创建者</td>
            	<td style="text-align: center;">创建时间</td>
            	<td style="text-align: center;">相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="roleList">
        <s:iterator value="recordList">
			<tr class="TableDetail1 template">
				<td style="width: 20%;text-align: center;">
				<IMG SRC="${pageContext.request.contextPath}/style/images/FileType/folder.gif"/>
				<s:a action="file_list?id=%{id}">&nbsp;${name}&nbsp;</s:a></td>
				<td style="width: 20%;text-align: center;">&nbsp;${description}&nbsp;</td>
				<td style="width: 20%;text-align: center;">&nbsp;${user.name}&nbsp;</td>
				<td style="width: 20%;text-align: center;">&nbsp;${createTime}&nbsp;</td>
			    <td style="width: 20%;text-align: center">
                 <s:a action="file_list?id=%{id}">查看文件夹</s:a>
                </td> 
				
			</tr>  
        </s:iterator>

        </tbody>
    </table>
    
  
    <!-- 页面信息 -->
    <%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
    <s:form action="folder_list"></s:form>
</div>
</body>
</html>

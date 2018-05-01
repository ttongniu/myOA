<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>文件列表</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 文件列表
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td style="text-align: center;" >文件名称</td>
                <td style="text-align: center;" >下载次数</td>
                <td style="text-align: center;" >文件格式</td>
                <td style="text-align: center;" >上传时间</td>
            	<td style="text-align: center;">文件描述</td>
                <td style="text-align: center;">相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="roleList">
        
        <s:iterator value="recordList">
			<tr class="TableDetail1 template">
				<td style="width: 15%;text-align: center;">&nbsp;${name}&nbsp;</td>
				<td style="width: 10%;text-align: center;">&nbsp;${downLoadNum}&nbsp;</td>
				<td style="width: 10%;text-align: center;">&nbsp;${format}&nbsp;</td>
				<td style="width: 15%;text-align: center;">&nbsp;${createTime}&nbsp;</td>
				<td style="width: 25%;text-align: center;">&nbsp;${description}&nbsp;</td>
				<td style="width: 20%;text-align: center;">
				
					<s:a action="file_download?id=%{id}">下载</s:a>
					&nbsp;&nbsp;&nbsp;
					<s:a action="file_delete?id=%{id}&folderId=%{#folder.id}" onclick="return confirm('确定要删除吗？')" >删除</s:a>
					&nbsp;&nbsp;&nbsp;
					<s:a action="file_editUI?id=%{id}&folderId=%{#folder.id}" >修改</s:a>
				</td>
			</tr>  
        </s:iterator>

        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
   <DIV ID="TableTail">
		<DIV ID="TableTail_inside">
			<TABLE BORDER="0" CELLSPACING="0" CELLPADDING="10" ALIGN="left">
                <TR>
					
					<TD><DIV CLASS="FuncBtn">
                            <DIV class=FuncBtnHead></DIV>
                            <DIV class=FuncBtnMemo><s:a action="file_addUI?folderId=%{#folder.id}">上传文件</s:a></DIV>
                            <DIV class=FuncBtnTail></DIV>
                        </DIV></TD>
                </TR>
			</TABLE>
		</DIV>
	</DIV>

    <!-- 页面信息 -->
    <%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
    <s:form action="file_list"></s:form>
</div>
</body>
</html>

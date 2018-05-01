<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>名片列表</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 名片列表
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
            	<td width="50px">姓名</td>
               <!--  <td width="50px">昵称</td> -->
                <td width="50px">英文名</td>
                <td width="50px">公司</td>
                <td width="100px">部门名称</td>
                <td width="50px">职业</td>
                <td width="100px">邮箱</td>
                <td width="100px">公司电话</td>
                <td width="50px">个人电话</td>
                 <!-- <td width="50px">QQ</td> -->
                <td width="100px">地址</td>
                <td width="50px">邮编</td>
              <!--   <td width="50px">备注说明</td> -->
                <td>相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="roleList">
        
        <s:iterator value="recordList">
			<tr class="TableDetail1 template">
				<td style="width: 6%">&nbsp;${name}&nbsp;</td>
				<%-- <td style="width: 6%">&nbsp;${nickName}&nbsp;</td> --%>
				<td style="width: 6%">&nbsp;${englishName}&nbsp;</td>
				<td style="width: 15%">&nbsp;${company}&nbsp;</td>
				<td style="width: 10%">&nbsp;${departmentName}&nbsp;</td>
				<td style="width: 10%">&nbsp;${vocation}&nbsp;</td>
				<td style="width: 8%">&nbsp;${email}&nbsp;</td>
				<td style="width: 10%">&nbsp;${telBusiness}&nbsp;</td>
				<td style="width: 10%">&nbsp;${telHome}&nbsp;</td>
				<%-- <td width="30px">&nbsp;${QQ}&nbsp;</td> --%>
				<td style="width: 10%">&nbsp;${address}&nbsp;</td>
				<td style="width: 6%">&nbsp;${zipCode}&nbsp;</td>
			
				<td style="width: 14%">
						&nbsp;<s:a action="card_delete?id=%{id}&cardcaseId=%{#cardcase.id}" onclick="return confirm('确定要删除吗？')">删除</s:a>
					    &nbsp;<s:a action="card_editUI?id=%{id}&cardcaseId=%{#cardcase.id}">修改</s:a>
				</td>
			</tr>  
        </s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
		<div id="TableTail_inside">
			<s:a action="card_addUI?cardcaseId=%{#cardcase.id}"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
        </div>
    </div>
    <!-- 页面信息 -->
    <%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
    <s:form action="card_list"></s:form>
</div>
</body>
</html>

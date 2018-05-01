<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>用户列表</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
     <script language="javascript" src="${pageContext.request.contextPath}/script/jquery-1.11.1.js"></script>
    <script type="text/javascript">                  
                   	 function Export(){
                     	if(confirm("确定要导出所有用户信息吗？")){
                     		window.location.href="user_exportUser.action";
                     		/*  alert("通讯录信息");
                     		 $.post("addressBook_exportBook.action", {},function (data) {
                                 	 if(data=="1"){
                                	  		alert("数据导出成功");
                                 	 }else {
                                 		 alert("数据导出失败");
                                 	 }
                                  },"json"); */
                     	}
                     };
	</script>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head"> 
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 用户管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>
<div id="QueryArea">
	<div style="height: 30px">
		<s:form action="user_list">
			<table border=0 cellspacing=3 cellpadding=5>
						<tr valign=bottom>
							<td>按条件查询：</td>
							<td>
                                                                                     姓名： <s:textfield name="name" />							
                        	 部门：<s:select name="departmentId" cssClass="SelectStyle"
                        		list="#departmentList" listKey="id" listValue="name"
                        		headerKey="" headerValue="==请选择部门=="
                        	/>  					
							<a href=""><input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG"/></a>
						    </td>	
						</tr>
					</table>
		</s:form>
	</div>
</div>



<s:form action="user_list">
<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
                <td width="15%">登录名</td>
                <td width="15%">姓名</td>
                <td width="15%">所属部门</td>
                <td width="15%">岗位</td>
                <td width="20%">备注</td>
                <td>相关操作</td>
            </tr>
        </thead>
        
        <!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="userList">
        
        <s:iterator value="recordList">
            <tr class="TableDetail1 template">
                <td style="text-align: center;">${loginName}&nbsp;</td>
                <td style="text-align: center;">${name}&nbsp;</td>
                <td style="text-align: center;">${department.name}&nbsp;</td>
                <td style="text-align: center;">
                	<s:iterator value="roles">
                		${name}&nbsp;&nbsp;
                	</s:iterator>
                </td>
                <td style="text-align: center;">${description}&nbsp;</td>
                <td style="text-align: center;">
                   <%--  <s:if test="#session.user.hasPrivilegeByName('用户删除')"> --%>
                	<s:a action="user_delete?id=%{id}" onclick="return delConfirm()">删除</s:a>
                	<%-- </s:if> --%>
                    <%-- <s:if test="#session.user.hasPrivilegeByName('用户修改')"> --%>
                    <s:a action="user_editUI?id=%{id}">修改</s:a>
                   <%--  </s:if> --%>
                   <%--  <s:if test="#session.user.hasPrivilegeByName('初始化密码')"> --%>
					<s:a action="user_initPassword?id=%{id}" onclick="return window.confirm('您确定要初始化密码为1234吗？')">初始化密码</s:a>
					<%-- </s:if> --%>
                </td>
            </tr>
        </s:iterator> 
            
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <%-- <div id="TableTail">
       <div id="TableTail_inside">
					<table border="0" cellspacing="0" cellpadding="0" height="100%" align="left">
						<tr valign=bottom>
							<td></td>
							<td>
                                                                                     姓名： <s:textfield name="name" />							
                        	 部门：<s:select name="departmentId" cssClass="SelectStyle"
                        		list="#departmentList" listKey="id" listValue="name"
                        		headerKey="" headerValue="==请选择部门=="
                        	/>                          
							<input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" align="ABSMIDDLE"/>
						    </td>	
						</tr>
					</table>
				</div>
			</div> --%>
        <div id="TableTail_inside">
             <%-- <s:if test="#session.user.hasPrivilegeByName('用户添加')"> --%>
            <s:a action="user_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
            <!-- <span><input type="button"  value="导出" id="export" onclick="Export();" /> &nbsp;&nbsp; -->
            <%--  <s:a action="user_exportUser"><img src="${pageContext.request.contextPath}/style/images/export.png" /></s:a> --%>
            <img alt="" src="${pageContext.request.contextPath}/style/images/export.png" onclick="Export();">
            <%--  </s:if> --%>
        </div>
    </div>
    </s:form>
    <!--分页  -->
       <%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
		<s:form action="user_list"></s:form>
</div>

</body>
</html>

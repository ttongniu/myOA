<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>部门列表</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<script type="text/javascript">                  
                   	 function Export(){
                     	if(confirm("确定要导出所有部门信息吗？")){
                     		window.location.href="department_exportDepartment.action";
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 部门管理
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
       
        <!-- 表头-->
        <thead>
            <tr align=center valign=middle id=TableTitle>
            	<td width="25%">部门名称</td>
				<td width="25%">上级部门名称</td>
				<td width="25%">职能说明</td>
				<td width="25%">相关操作</td>
            </tr>
        </thead>

		<!--显示数据列表-->
        <tbody id="TableData" class="dataContainer" datakey="departmentList">
        
        <s:iterator value="recordList">
			<tr class="TableDetail1 template">
				<td width="25%" style="text-align: center;"  ><s:a action="department_list?parentId=%{id}">${name}</s:a>&nbsp;</td>
				<td width="25%" style="text-align: center;">
				<c:if test="${ parent.name==null}"> 
				      无上级部门
				</c:if> 
				${parent.name}&nbsp;</td>
				<td width="25%" style="text-align: center;">${description}&nbsp;</td>
				<td width="25%" style="text-align: center;">
				   <%--  <s:if test="#session.user.hasPrivilegeByName('部门删除')"> --%>
					<s:a action="department_delete?id=%{id}&parentId=%{parent.id}" onclick="return window.confirm('这将删除所有的下级部门，您确定要删除吗？')">删除</s:a>
					<%-- </s:if> --%>
					<%-- <s:if test="#session.user.hasPrivilegeByName('部门修改')"> --%>
					<s:a action="department_editUI?id=%{id}">修改</s:a>
					<%-- </s:if> --%>
				</td>
			</tr>
		</s:iterator>	
			
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail">
        <div id="TableTail_inside">
           <%--  <s:if test="#session.user.hasPrivilegeByName('部门添加')"> --%>
            <s:a action="department_addUI?parentId=%{parentId}"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
          <%--   </s:if> --%>
			<s:a action="department_list?parentId=%{#parent.parent.id}"><img src="${pageContext.request.contextPath}/style/blue/images/button/ReturnToPrevLevel.png" /></s:a>
			<img alt="" src="${pageContext.request.contextPath}/style/images/export.png" onclick="Export();">
        </div>
    </div>
    <!-- 分页 -->
    <%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
   <s:form action="department_list"></s:form>
</div>

<!--说明-->	
<div id="Description"> 
	说明：<br />
	1，列表页面只显示一层的（同级的）部门数据，默认显示最顶级的部门列表。<br />
	2，点击部门名称，可以查看此部门相应的下级部门列表。<br />
	3，删除部门时，同时删除此部门的所有下级部门。
</div>

</body>
</html>

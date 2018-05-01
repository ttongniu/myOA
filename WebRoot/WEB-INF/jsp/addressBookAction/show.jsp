<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
    <title>通讯录列表</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script language="javascript" src="${pageContext.request.contextPath}/script/jquery-1.11.1.js"></script>
    <script type="text/javascript">                  
                   	 function Export(){
                     	if(confirm("确定要导出所有通讯录信息吗？")){
                     		window.location.href="addressBook_exportBook.action";
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

<style>
       span{
            width: 100%;
            text-align: center;
       }
</style>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 通讯录
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="QueryArea">
	<div style="height: 30px">
		<s:form action="addressBook_show">
			<table border=0 cellspacing=3 cellpadding=5>
						<tr valign=bottom>
							<td>按条件查询：</td>
							<td>
                                                                                     姓名： <s:textfield name="name" />							
                        	 部门：<s:select name="departmentId" cssClass="SelectStyle"
                        		list="#departmentList" listKey="id" listValue="name"
                        		headerKey="" headerValue="==请选择部门=="
                        	/>                          
                                                                                     电话：   <s:textfield name="phoneNumber" />							
							<a href=""><input type="image" src="${pageContext.request.contextPath}/style/blue/images/button/query.PNG"/></a>
						    </td>	
						</tr>
					</table>
		</s:form>
	</div>
</div>


<s:form action="addressBook_show">
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
			</tr>  
        </s:iterator>

        </tbody>
    </table>  
    <!-- 其他功能超链接 -->
    
   <%--  <div id="TableTail">
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
                                                                                     电话：   <s:textfield name="phoneNumber" />							
							<input type="IMAGE" src="${pageContext.request.contextPath}/style/blue/images/button/submit.PNG" align="ABSMIDDLE"/>
						    </td>	
						</tr>
					</table>
				</div>
			</div> --%>
			
		<div id="TableTail_inside">
		      <%-- <s:if test="#session.user.hasPrivilegeByName('岗位添加')">
			<s:a action="addressBook_addUI"><img src="${pageContext.request.contextPath}/style/images/createNew.png" /></s:a>
			  </s:if> --%>
			 
			 <span><input type="button" value="导出" id="export" onclick="Export();" /> &nbsp;&nbsp;
			  <input type="button" value="打印" id="print"  onclick="javascript:window.print()" /></span>
        </div>
    </div> 
    </s:form>
    <!-- 页面信息 -->
    <%@ include file="/WEB-INF/jsp/public/pageView.jspf" %>
    <s:form action="addressBook_show"></s:form>
</div>
</body>
</html>

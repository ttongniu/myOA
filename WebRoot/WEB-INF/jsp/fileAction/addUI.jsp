<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<HTML>
<HEAD>
<TITLE>文件属性</TITLE>
   <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script type="text/javascript">
    $(function(){
   	$("#upload").blur(function(){
    		
    		var name=$("#upload").val();
    		if(name==""){
    			$("#div1").text("不能为空！");
    			return;
    		}
    	});
    });
</script>
</HEAD>
<BODY>
 
<!-- 标题显示 -->
<DIV ID="Title_bar">
    <DIV ID="Title_bar_Head">
        <DIV ID="Title_Head"></DIV>
        <DIV ID="Title"><!--页面标题-->
            <IMG BORDER="0" WIDTH="13" HEIGHT="13" SRC="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 文件属性
        </DIV>
        <DIV ID="Title_End"></DIV>
    </DIV>
</DIV>
 
<!--显示表单内容-->
<DIV ID=MainArea>
      <s:form action="file_add?folderId=%{#folder.id}" enctype="multipart/form-data">
        <DIV CLASS="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 文件属性</DIV>  -->
        </DIV>
        
        <!-- 表单内容显示 -->
        
        <DIV CLASS="ItemBlockBorder">
            <DIV CLASS="ItemBlock">
                <TABLE CELLPADDING="0" CELLSPACING="0" CLASS="mainForm">
                    <TR>
                        <TD WIDTH="100">文件信息</TD>
                       <td><input type="file" name="upload" id="upload" class="InputStyle" style="width:450px;" />*
                       <span id="div1" style="color: red"></span>
                        </td>
                    </TR>
                    <TR>
                        <TD>文件描述</TD>
                        <TD><INPUT TYPE="text"  NAME="description" CLASS="TextareaStyle"/></TD>
                    </TR>
					
					<TR>
                        <TD>所属文件夹</TD>
                        <TD><INPUT TYPE="TEXT" NAME="parentId" READONLY VALUE="${folder.name}" CLASS="InputStyle" /></TD>
                    </TR>
					
                </TABLE>
            </DIV>
        </DIV>
        
        <!-- 表单操作 -->
        <DIV ID="InputDetailBar">
            <INPUT TYPE="image" SRC="${pageContext.request.contextPath}/style/images/save.png"/>
            <A HREF="javascript:history.go(-1);"><IMG SRC="${pageContext.request.contextPath}/style/images/goBack.png"/></A>
        </DIV>
   </s:form>
</DIV>
 
<DIV CLASS="Description">
	说明：<BR />
	1，选择的文件的名称，就是这个文件的显示名称，注意不要与本文件夹中已有的文件重名。<BR />
</DIV>
 
</BODY>
</HTML>

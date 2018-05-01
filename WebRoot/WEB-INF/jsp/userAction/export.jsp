<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<HTML>
<HEAD>
    <TITLE>导出</TITLE>
    <%@include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script language="javascript" type="text/javascript">
            var timer;
            //启动跳转的定时器
            function startTimes() {
                timer = window.setInterval(showSecondes,1000);
            }
 
            var i = 2;
            function showSecondes() {
                if (i > 0) {
                    i--;
                    document.getElementById("secondes").innerHTML = i;
                }
                else {
                    window.clearInterval(timer);
                    location.href = "javascript:history.go(-1);";
                }
            }
 
            //取消跳转
            function resetTimer() {
                if (timer != null && timer != undefined) {
                    window.clearInterval(timer);
                    location.href = "javascript:history.go(-1);";
                }
            }
        </script> 
    
</HEAD>   
<BODY  onload="startTimes();">
          
<DIV ID="Title_bar">
    <DIV ID="Title_bar_Head">
        <DIV ID="Title_Head"></DIV>
        <DIV ID="Title"><!--页面标题-->
            <IMG BORDER="0" WIDTH="13" HEIGHT="13" SRC="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 提示
        </DIV>
        <DIV ID="Title_End"></DIV>
    </DIV>
</DIV>


<!--显示表单内容-->
<DIV ID="MainArea">
		<DIV CLASS="ItemBlock_Title1">
        </DIV> 
        
        <DIV CLASS="ItemBlockBorder" STYLE="margin-left: 15px;">
            <DIV CLASS="ItemBlock" STYLE="text-align: center; font-size: 16px;">
                                                       恭喜你导出成功了！&nbsp;<span id="secondes">2</span>&nbsp;秒后将自动跳转，立即跳转请点击&nbsp;
				
            </DIV>
        </DIV>
        
        <!-- 操作 -->
        <DIV ID="InputDetailBar">
            <A HREF="javascript:resetTimer();"><IMG SRC="${pageContext.request.contextPath}/style/images/goBack.png"/></A>
        </DIV>
</DIV>

</BODY>
</HTML>

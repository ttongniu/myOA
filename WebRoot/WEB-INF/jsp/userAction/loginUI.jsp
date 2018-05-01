<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<title>NTT OA</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<link href="${pageContext.request.contextPath}/style/blue/login.css" type=text/css rel=stylesheet>
	<script charset="Shift_JIS" src="http://chabudai.sakura.ne.jp/blogparts/honehoneclock/honehone_clock_tr.js"></script>
	<script type="text/javascript">
		$(function(){
			document.forms[0].loginName.focus();			
		});
		
		// 在被嵌套时就刷新上级窗口
		if(window.parent != window){
			window.parent.location.reload(true);
		}
		function changeValidateCode(obj) { 
			alert("*************");
			//获取当前的时间作为参数，无具体意义 
			var timenow = new Date().getTime(); 
			//每次请求需要一个不同的参数，否则可能会返回同样的验证码 
			//这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。 
			obj.src="user_getRandomPictrue?id="+timenow; 
			//window.location.href="user_getRandomPictrue?d="+timenow;
			} 
		
	</script>
</head>

<body leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 class=PageBody >



<!-- 显示表单 -->
<s:form action="user_login" focusElement="loginNameInput">
    <div id="CenterAreaBg"> 
        <div id="CenterArea">
            <div id="LogoImg"><img border="0" src="${pageContext.request.contextPath}/style/blue/images/logo.png" /></div>
            <div id="LoginInfo">
                <table BORDER=0 CELLSPACING=0 CELLPADDING=0 width=100%>
                	<tr>
                		<td colspan="3"><!-- 显示错误 -->
							<font color="red"><s:fielderror/></font>
                		</td>
                	</tr>
                    <tr>
                        <td width=45 class="Subject"><img border="0" src="${pageContext.request.contextPath}/style/blue/images/login/userId.gif" /></td>
                        <td>
                        	<s:textfield name="loginName" size="20" tabindex="1" cssClass="TextField required" id="loginNameInput" />
                        </td>
                        <td rowspan="2" style="padding-left:10px;">
                        	<input type="image" tabindex="3" src="${pageContext.request.contextPath}/style/blue/images/login/userLogin_button.gif" />
                        </td>
                    </tr>
                    <tr>
                        <td class="Subject"><img border="0" src="${pageContext.request.contextPath}/style/blue/images/login/password.gif" /></td>
                        <td><s:password name="password" id="aa" size="20" tabindex="2" showPassword="false" cssClass="TextField required" /></td>
                    </tr>
                     <%-- <tr>
                         <td class="Subject"><img border="0" src="${pageContext.request.contextPath}/style/blue/images/login/password.gif" /></td>
                        <td><s:textfield name="random" id="random" cssClass="TextField required" /></td>
                        <td><img src="user_getRandomPictrue" onclick="changeValidateCode(this)" title="点击图片刷新验证码"/></td> 
                    </tr>  --%>
                </table>
            </div>
            <div id="CopyRight"><a href="javascript:void(0)">&copy; 2016 天道酬勤，勤能补拙。</a></div>
            
    </div>
    </s:form>
</body>

</html>


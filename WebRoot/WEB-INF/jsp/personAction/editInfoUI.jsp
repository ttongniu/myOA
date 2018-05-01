<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <title>个人信息</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<script type="text/javascript">
  $(function(){
	  $("#phoneNumber").blur(function(){
  		
  		var telephone=$("#phoneNumber").val();
  		if(telephone==""){
  			$("#div2").text("不能为空！");
  			return;
  		}
  		
  		var reg=/^1[0-9]{10}$/;
  		  var result=reg.test(telephone);
  		  if(!result){
  			  $("#div2").text("非法格式");
 			   return false;
  		  }
  		  $("#div2").text("格式正确！");
		      return true;
  	});
  	
  	

      $("#email").blur(function(){
  		
  		var email=$("#email").val();
  		if(email==""){
  			$("#div3").text("不能为空！");
  			return;
  		}
  		
  		var reg=/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
  		  var result=reg.test(email);
  		  if(!result){
  			  $("#div3").text("非法格式");
 			   return false;
  		  }
  		  $("#div3").text("格式正确！");
		      return true;
  	});
  	
  });
  function showResult() {
	  var phoneNumber=$("#phoneNumber").val(); 
	  var email=$("#email").val();
	  $.ajax({
          type: "post",
          url: "person_editInfo.action",
          data:{"phoneNumber":phoneNumber,"email":email},
          success: function (data) {
        	  alert(data);
          }
      });
     
  }
  
</script>
  </head>
  
  <body>
 
<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 个人信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>
 
<!--显示表单内容-->
<div id=MainArea>
        
     <!--显示表单内容-->
<div id="MainArea">

    <form >
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div  class="ItemBlock">
            <div align="right">
                <table  cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td width="100">登录名</td>
                        <td><s:textfield name="loginName" cssClass="InputStyle" readonly="true" /></td>
                    </tr>
                    <tr>
                        <td width="100">部门名称</td>
                        <td><s:textfield name="department.name" cssClass="InputStyle" readonly="true"/></td>
                    </tr>
                      <tr>
                        <td width="100">岗位</td>
                            <td cssClass="InputStyle">
                           
                         <c:forEach  varStatus="status" items="${user.roles }" var="role">
                            <c:choose>
                            <c:when test="${status.last }">
                                        ${role.name }    
                            </c:when>
                                     
                                <c:otherwise>
                                   ${role.name },
                                </c:otherwise>           
                            </c:choose>
                            </c:forEach>
                         </td>          
                    </tr>
                   
                    <tr>
                        <td width="100">姓名</td>
                        <td><s:textfield name="name" cssClass="InputStyle" readonly="true"/> </td>
                    </tr>
                    <tr>
                        <td width="100">电话号码</td>
                        <td><s:textfield name="phoneNumber" id="phoneNumber" cssClass="InputStyle" /> 
                         <span id="div2" style="color: red"></span>
                        </td>
                    </tr>
                    <tr>
                        <td width="100">Email</td>
                        <td><s:textfield name="email" id="email" cssClass="InputStyle"  /> 
                         <span id="div3" style="color: red"></span>
                        </td>
                    </tr>
                </table>
                </div>
            </div>
        </div>
        
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <!--  <input type="button" value="保存" onclick="showResult()"/> -->
            <img alt="" src="${pageContext.request.contextPath}/style/images/save.png"  onclick="showResult();"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </form>
</div>

 
</body>

</html>

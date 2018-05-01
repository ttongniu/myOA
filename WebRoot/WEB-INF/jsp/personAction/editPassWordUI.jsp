<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <title>修改密码</title>
	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
	<script language="javascript" src="${pageContext.request.contextPath}/script/jquery-1.11.1.js"></script>
  <script type="text/javascript">
  $(function(){
	  
	  $("#oldPassword").blur(function(){
         var passWord= $("#oldPassword").val();
         if(passWord==""){
        	 $("#div1").text("密码不能为空");
        	 return;
         } 
		  $.ajax({
              type: "post",
              url: "person_validatePassWord.action",
              data: "passWord=" +passWord,
              success: function (data) {
            	  $("#div1").text(data);
              }
          });
	   });
	  
	  $("#password2").blur(function(){
	         var passWord= $("#password").val();
	         var passWord2=$("#password2").val();
	         if(passWord!=passWord2){
	        	 $("#div2").text("密码不一致");
	        	 return;
	         }else{
	        	 $("#div2").text("");
	         } 
		   });
  });
  function showResult() {
	  var passWord= $("#password").val();
	  $.ajax({
          type: "post",
          url: "person_editPassWord.action",
          data: "passWord=" +passWord,
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 修改密码
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form id="PwdForm">
        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 修改密码 </DIV>  -->
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder"  style="text-align: center;">
            <div class="ItemBlock" style="text-align: center;">
                <table cellpadding="0" cellspacing="0" class="mainForm">
					<tr height="50">
						<td width="150">
							<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
							请输入原密码
						</td>
						<td><input type="password" id="oldPassword" name="oldPassword" class="InputStyle" /> *<span id="div1"></span></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
							请填写新密码
						</td>
						<td><input type="password" id="password" name="password" class="InputStyle" /> *</td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
							再次输入新密码
						</td>
						<td><input type="password" id="password2" name="password2" class="InputStyle" />&nbsp;&nbsp;<span id="div2"></span></td>
						<td></td>
					</tr>
                </table>
            </div>
        </div>
       
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <!-- <input type="button" value="保存" onclick="showResult();"/> -->
            <img alt="" src="${pageContext.request.contextPath}/style/images/save.png"  onclick="showResult();"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>

<div class="Description">
	验证规则：<br />
	1，旧密码不能为空。<br />
	2，新密码不能为空。<br />
	3，再次输入的密码要和新密码一致。<br />
</div>

</body>

</html>

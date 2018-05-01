<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<title>通讯录信息</title>
  	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
   	<script type="text/javascript">
    $(function(){
    	
    	$("#address").blur(function(){
    		
    		var name=$("#address").val();
    		if(name==""){
    			$("#div5").text("不能为空！");
    			return;
    		}
    		
    		
    	});
    	
         $("#QQ").blur(function(){
    		
    		var name=$("#QQ").val();
    		if(name==""){
    			$("#div6").text("不能为空！");
    			return;
    		}
    		var reg=/\d{5,10}$/;
  		  var result=reg.test(name);
  		  if(!result){
  			  $("#div6").text("非法格式");
 			   return false;
  		  }
  		  $("#div6").text("格式正确！");
		      return true;
    		
    	});
    	
          $("#name").blur(function(){
    		
    		var name=$("#name").val();
    		if(name==""){
    			$("#div4").text("不能为空！");
    			return;
    		}
    		
    		var reg=/[a-zA-Z-\u3E00-\u9FA5]{1,20}$/;
    		  var result=reg.test(name);
    		  if(!result){
    			  $("#div4").text("非法格式");
   			   return false;
    		  }
    		  $("#div4").text("格式正确！");
		      return true;
    	});
    	
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
    
</script>
</head>
<body> 

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 通讯录信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">

    <s:form action="addressBook_%{ id == null ? 'add' : 'edit' }">
    	<s:hidden name="id"></s:hidden>
    
        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 岗位信息 </DIV>  -->
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
                        <td width="100">姓名</td>
                        <td><s:textfield name="name" id="name" cssClass="InputStyle" /> *
                        <span id="div4" style="color: red">（20长度以内的汉字、字母的组合）</span>
                        </td>
                    </tr>
                      <tr>
                        <td width="100">性别</td>
                        <td><s:radio name="gender" list="{'男', '女'}"></s:radio></td>
                    </tr>
                      <tr>
                        <td width="100">电话号码</td>
                        <td><s:textfield name="phoneNumber" id="phoneNumber"  cssClass="InputStyle" /> *
                         <span id="div2" style="color: red"></span>
                        </td>
                       
                    </tr>
                      <tr>
                        <td width="100">电子邮件</td>
                        <td><s:textfield name="email"  id="email" cssClass="InputStyle" /> *
                         <span id="div3" style="color: red"></span>
                        </td>
                    </tr>
                      <tr>
                       <tr><td width="100">部门名称</td>
                        <td>
                        	<s:select name="departmentId" cssClass="SelectStyle"
                        		list="#departmentList" listKey="id" listValue="name"
                        		headerKey="" headerValue="==请选择部门=="
                        	/>*<span id="div0" style="color: red">部门必选</span>
                        </td>
                    </tr>
                    </tr>
                      <tr>
                        <td width="100">家庭住址</td>
                        <td><s:textfield name="address" id="address" cssClass="InputStyle" /> *
                        <span id="div5" style="color: red"></span>
                        </td>
                    </tr>
                      <tr>
                        <td width="100">QQ</td>
                        <td><s:textfield name="QQ" id="QQ" cssClass="InputStyle" />   *
                        <span id="div6" style="color: red"></span>
                       </td>
                    </tr>
                      
                    <tr>
                        <td>备注说明</td>
                        <td><s:textarea name="description" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>
        
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>

</body>
</html>


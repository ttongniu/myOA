<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
	<title>用户信息</title>
   	<%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
   	<script language="javascript" src="${pageContext.request.contextPath}/script/jquery-1.11.1.js"></script>
   	<script type="text/javascript">
    $(function(){
    	
    	$("#loginName").blur(function(){
    		var name=$("#loginName").val();
    		if(name==""){
    			$("#div1").text("不能为空！");
    			return;
    		}
    		  $.ajax({
    	          type: "post",
    	          url: "person_validateLoginName.action",
    	          data:{"loginName":name},
    	          success: function (data) {
    	           $("#div1").text(data);
    	           return;
    	          }
    	      }); 
    		var reg=/[a-zA-Z-\u3E00-\u9FA5]{1,20}$/;
    		  var result=reg.test(name);
    		  if(!result){
    			  $("#div1").text("非法格式");
   			   return false;
    		  }
    		  $("#div1").text("格式正确！");
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 用户信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>

    <s:form action="user_%{id == null ? 'add' : 'edit'}">
    	<s:hidden name="id"></s:hidden>
        
        <div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 用户信息 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr><td width="100">所属部门</td>
                        <td>
                        	<s:select name="departmentId" cssClass="SelectStyle"
                        		list="#departmentList" listKey="id" listValue="name"
                        		headerKey="" headerValue="==请选择部门=="
                        	/>*<span id="div0" style="color: red"> （部门必选）</span>
                        </td>
                    </tr>
                    <tr><td>登录名</td>
                        <td><s:textfield name="loginName"  id="loginName" cssClass="InputStyle"/> *
							 <span id="div1" style="color: red">（20长度以内的汉字、字母的组合）</span>
						</td>
						
                    </tr>
                    <tr><td>姓名</td>
                        <td><s:textfield name="name" id="name" cssClass="InputStyle"/> *
                        <span id="div4" style="color: red"> （20长度以内的汉字、字母的组合）</span>
                        </td>
                        
                    </tr>
					<tr><td>性别</td>
                        <td>
                        	<%--
							<s:radio name="gender" list="%{ #{'男':'男', '女':'女'} }"></s:radio>
							<s:radio name="gender" list="#{'男':'男', '女':'女'}"></s:radio>
                        	 --%>
							<s:radio name="gender" list="{'男', '女'}"></s:radio>

						</td>
                    </tr>
					<tr><td>联系电话</td>
                        <td><s:textfield name="phoneNumber" id="phoneNumber" cssClass="InputStyle"/>
                        <span id="div2" style="color: red"></span>
                        </td>
                    </tr>
                    <tr><td>E-mail</td>
                        <td><s:textfield name="email"  id="email" cssClass="InputStyle"/>
                         <span id="div3" style="color: red"></span>
                        </td>
                    </tr>
                    <tr><td>备注</td>
                        <td><s:textarea name="description" cssClass="TextareaStyle"></s:textarea></td>
                    </tr>
                </table>
            </div>
        </div>
        
		<div class="ItemBlock_Title1"><!-- 信息说明 --><div class="ItemBlock_Title1">
        	<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 岗位设置 </div> 
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>
						<td width="100">岗位</td>
                        <td>
                        	<s:select name="roleIds" cssClass="SelectStyle"
                        		multiple="true" size="10" 
                        		list="#roleList" listKey="id" listValue="name"
                        	/>
							按住Ctrl键可以多选或取消选择
                        </td>
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

<div class="Description">
	说明：<br />
	1，用户的登录名要唯一，在填写时要同时检测是否可用。<br />
	2，新建用户后，密码被初始化为"1234"。<br />
	3，密码在数据库中存储的是MD5摘要（不是存储明文密码）。<br />
	4，用户登录系统后可以使用“个人设置→修改密码”功能修改密码。<br />
	5，修改用户信息时，登录名不可修改。
</div>

</body>
</html>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<title>名片编辑</title>
    <%@ include file="/WEB-INF/jsp/public/commons.jspf" %>
    <script type="text/javascript">
    $(function(){
    	 $("#name").blur(function(){
     		
     		var name=$("#name").val();
     		if(name==""){
     			$("#div1").text("不能为空！");
     			return;
     		}
     		
     		var reg=/[a-zA-Z-\u3E00-\u9FA5]{1,20}$/;
     		  var result=reg.test(name);
     		  if(!result){
     			  $("#div1").text("非法格式");
    			   return false;
     		  }
     		  $("#div1").text("格式正确！");
 		      return true;
     	});
   	$("#englishName").blur(function(){
    		
    		var name=$("#englishName").val();
    		if(name==""){
    			$("#div2").text("不能为空！");
    			return;
    		}
    		$("#div2").text("");
    		
    	});
	$("#company").blur(function(){
		
		var name=$("#company").val();
		if(name==""){
			$("#div3").text("不能为空！");
			return;
		}
		$("#div3").text("");
	});
	$("#departmentName").blur(function(){
			
			var name=$("#departmentName").val();
			if(name==""){
				$("#div4").text("不能为空！");
				return;
			}
			$("#div4").text("");
		});
	$("#vocation").blur(function(){
		
		var name=$("#vocation").val();
		if(name==""){
			$("#div5").text("不能为空！");
			return;
		}
		$("#div5").text("");
	});
	 $("#email").blur(function(){
 		
 		var email=$("#email").val();
 		if(email==""){
 			$("#div6").text("不能为空！");
 			return;
 		}
 		
 		var reg=/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
 		  var result=reg.test(email);
 		  if(!result){
 			  $("#div6").text("非法格式");
			   return false;
 		  }
 		  $("#div6").text("格式正确！");
		      return true;
 	});
	
	$("#telBusiness").blur(function(){
		
		var telBusiness=$("#telBusiness").val();
		if(telBusiness==""){
			$("#div7").text("不能为空！");
			return;
		}
		var reg=/^\d{3}-\d{8}|\d{4}-\d{7}$/;
		  var result=reg.test(telBusiness);
		  if(!result){
			  $("#div7").text("非法格式");
			   return false;
		  }
		  $("#div7").text("格式正确！");
	      return true;
	});
	$("#telHome").blur(function(){
		
		var telephone=$("#telHome").val();
		if(telephone==""){
			$("#div8").text("不能为空！");
			return;
		}
		
		var reg=/^1[0-9]{10}$/;
		  var result=reg.test(telephone);
		  if(!result){
			  $("#div8").text("非法格式");
			   return false;
		  }
		  $("#div8").text("格式正确！");
	      return true;
	});
    	
	$("#address").blur(function(){
			
			var name=$("#address").val();
			if(name==""){
				$("#div9").text("不能为空！");
				return;
			}
			$("#div9").text("");
		});
		$("#zipCode").blur(function(){
				
				var zipCode=$("#zipCode").val();
				if(zipCode==""){
					$("#div10").text("不能为空！");
					return;
				}
				
				var reg=/^[1-9][0-9]{5}$/;
				  var result=reg.test(zipCode);
				  if(!result){
					  $("#div10").text("非法格式");
					   return false;
				  }
				  $("#div10").text("格式正确！");
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
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 名片编辑
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id="MainArea">

    <s:form action="card_%{ id == null ? 'add' : 'edit' }">
    	<s:hidden name="id"></s:hidden>
        <s:hidden name="cardcaseId"></s:hidden>
        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 岗位信息 </DIV>  -->
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock" >
            <div style="text-align: right;">
                <table  cellpadding="0" cellspacing="0" class="mainForm">
                    <tr>               
                   <td width="100"> 姓名:</td>
                   <td>  <s:textfield name="name" id="name" cssClass="InputStyle" /> *
                         <span id="div1" style="color: red"></span>
                   </td>
                    </tr>
                     <tr>
                        <td width="100">英文名</td>
                        <td><s:textfield name="englishName" id="englishName" cssClass="InputStyle" /> *
                        <span id="div2" style="color: red"></span>
                        </td>
                    </tr>
                     <tr>
                        <td width="100">公司</td>
                        <td><s:textfield name="company" id="company" cssClass="InputStyle" /> *
                         <span id="div3" style="color: red"></span>
                        </td>
                    </tr>
                     <tr>
                        <td width="100">部门名称</td>
                        <td><s:textfield name="departmentName" id="departmentName" cssClass="InputStyle" /> *
                        <span id="div4" style="color: red"></span>
                        </td>
                    </tr>
                     <tr>
                        <td width="100">职业</td>
                        <td><s:textfield name="vocation" id="vocation" cssClass="InputStyle" /> *
                        <span id="div5" style="color: red"></span>
                        </td>
                    </tr>
                     <tr>
                        <td width="100">邮箱</td>
                        <td><s:textfield name="email" id="email"  cssClass="InputStyle" /> *
                        <span id="div6" style="color: red"></span>
                        </td>
                    </tr>
                     <tr>
                        <td width="100">公司电话</td>
                        <td><s:textfield name="telBusiness" id="telBusiness"  cssClass="InputStyle" /> *
                        <span id="div7" style="color: red"></span>
                        </td>
                    </tr>
                     <tr>
                        <td width="100">个人电话</td>
                        <td><s:textfield name="telHome"  id="telHome"  cssClass="InputStyle" /> *
                        <span id="div8" style="color: red"></span>
                        </td>
                    </tr>
                     <tr>
                        <td width="100">地址</td>
                        <td><s:textfield name="address"   id="address"  cssClass="InputStyle" /> *
                        <span id="div9" style="color: red"></span>
                        </td>
                    </tr>
                     <tr>
                        <td width="100">邮编</td>
                        <td><s:textfield name="zipCode" id="zipCode"  cssClass="InputStyle" /> *
                        <span id="div10" style="color: red"></span>
                        </td>
                    </tr>
                </table>
                </div>
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


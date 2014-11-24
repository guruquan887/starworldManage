<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%
String msg="";
if(request.getAttribute("msg")!=null){
	msg=(String)request.getAttribute("msg");
}
%>
<head>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<script language="javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
function goto(grade,gradeExp,rebateLV){
 //var grade = document.getElementById("grade").value;
//alert(gradeExp+"&&&"+rebateLV)
 //alert(document.getElementById(gradeExp).value+"&&&&&&"+document.getElementById(rebateLV).value);
 var gradeExp = document.getElementById(gradeExp).value;
 var rebateLV = document.getElementById(rebateLV).value;
 document.location.href="<%=request.getContextPath()%>/system/UpdateGradeCS.do?grade="+grade+"&gradeExp="+gradeExp+"&rebateLV="+rebateLV;

}

function isNumber(oNum){

 if(!oNum) return false; //line:160
 if(oNum.value=="") return false;
 var vv = oNum.value.replace(/[^\d]/g,'');
 var strP=/^\d+$/;
  if (!isNaN(oNum.value)) {
        return true;
    } else {
        alert("请输入数字");
        oNum.value=vv;
 		oNum.focus();
 		return false; 
    }
}
</script>
<body>
<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/system/UpdateGradeCS.do">
  <table cellpadding="2" cellspacing="1" border="0" width="100%" align="center">
    <tr>
      <td height="25" colspan="7" class="menutop" style="text-align:center;">会员等级参数设定</td>
    </tr>
	 <tr align="center">
      <td width="11%" height="22" class="tdcenter"><strong>等级</strong></td>
      <td width="26%" class="tdcenter"><strong>经验值</strong></td>
      <td width="30%" class="tdcenter"><strong>反水利率</strong></td>
      <td width="33%" class="tdcenter"><strong>操作</strong></td>
    </tr>
	<% int i =1; %>
	<c:forEach var="user" items="${dto}">
	
    <tr	align="center">
      <td width="11%" height="22" class="tdcenter">${user.grade}</td>
      <td width="26%" class="tdcenter"><input name="gradeExp_<%=i%>" id="gradeExp_<%=i%>" onKeyUp="isNumber(this)" type="text" size="30" value="${user.gradeExp}" /></td>
      <td width="30%" class="tdcenter"><input name="rebateLV_<%=i%>" id="rebateLV_<%=i%>" onKeyUp="isNumber(this)" type="text" size="30" value="${user.rebateLV}" />
	  <input name="grade" id="grade" type="hidden" value="${user.grade}"></td>
      <td width="33%" class="tdcenter"><a style="cursor:pointer" onClick="goto(${user.grade},'gradeExp_<%=i%>','rebateLV_<%=i++%>')">修改</a></td>
    </tr>
	</c:forEach>
  </table>
</form>
</body>
</html>
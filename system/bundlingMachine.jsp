<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%@ page isELIgnored="false" %> 
<%
	String msg="";
	if(request.getAttribute("msg")!=null){
		msg=(String)request.getAttribute("msg");
	}

%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
function bangding()
{
		var machine = document.getElementById("machine").value;
		if(machine==""){
		alert("机器码不能为空");
		return false;
	}
		return true;
}

function tjSubmit()
{
	  form1.action="<%=request.getContextPath()%>/system/adminList.do?action=qxbundlingMachine";
	  form1.submit();
 }
</script>  
<body>
	<Div class="title">绑定电脑</Div>
	<form id="form1" name="form1" method="post" onsubmit="return bangding()" action="<%=request.getContextPath()%>/system/adminList.do?action=bundlingMachine">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
<tr	align="center">
      <td width="20%" height="22" class="tdright_new">登录的用户名：</td>
      <td width="80%" class="tdright">${username}</td>
    </tr>
    <tr	align="center">
      <td height="22" class="tdright_new">目前登录的机器码：</td>
      <td class="tdright"><input name="machine" id="machine" type="text" class="input_width2" value="${machine}" /></td>
    </tr>
	 <tr align="center">
      <td height="22" class="tdright_new">&nbsp;</td>
      <td class="tdright"><label>
        <input type="submit" class="input" name="Submit" value="绑定电脑"/>
     &nbsp; 
     <input type="button" class="input" name="Submit2" value="取消绑定" onclick="tjSubmit()"/>
      </label></td>
    </tr>
</table>
</form>
</body>
</html>
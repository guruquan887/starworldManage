<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%
	String msg="";
	if(request.getAttribute("msg")!=null){
	msg=request.getAttribute("msg").toString();
	}
	%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
       //弹出友好提示
var msg="<%=msg %>";
if(msg!='')alert(msg);
</script>
</head>
<body>
	<Div class="title">
	<span>  
	  <form id="form1" name="form1" method="post" action="">
	    <input name="Submit2" type="button" class="input"  value="代理报表" onclick="window.location.href='<%=request.getContextPath()%>/baob/bbtj.do?action=search'" />
	    <input name="Submit" type="button" class="input" value="会员报表" onclick="window.location.href='baobmember.html'" />
      </form>
	</span>
	报表管理—报表删除</Div>
    <form id="form2" name="form2" method="post" action="<%=request.getContextPath()%>/baob/bbtj.do?action=baobDel">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
        <tr>
          <td class="menutop">内容</td>
        </tr>
        <tr>
          <td class="tdcenter">从
            <label>
            <input name="startTime" id="startTime" type="text" onClick="WdatePicker()" value="" />
            至
            <input name="endTime" id="endTime" type="text" onClick="WdatePicker()" value="" />
            <input name="Submit3" type="submit" class="input" value="确定删除" />
          </label></td>
        </tr>
      </table>
</form>
</body>
</html>
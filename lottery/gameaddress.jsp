<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
String msg="";
if(request.getAttribute("msg")!=null){
	msg=(String)request.getAttribute("msg");
}
%>
<html>
<head>
<script language="javascript" src="../js/swfobject.js"/>
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>游戏列表</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
}
-->
</style>
<link href="../css/main.css" rel="stylesheet" type="text/css" /></head>
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
function goto(gameID,dataInter){
 var dataInter = document.getElementById(dataInter).value;
 //alert(dataInter);
 document.location.href="roomAddressList.do?action=changeAddress&gameID="+gameID+"&dataInter="+dataInter;
}

</script>
<body ondocumentready="">
  
<form id="form1" name="form1" method="post" action="">

  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
    <tr class="topbg" align="center">
	 
      <td height="25" colspan=7 class="menutop"><div style="width:90%; float:left; text-align:center; line-height:25px;">游戏房间地址管理</div></td>
    </tr>
    
  </table><br />
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
    <tr class="title">
      <td align="center" class="td1"><b>序号</b></td>
      <td align="center" class="td1"><strong>游戏类型ID</strong></td>
      <td align="center" class="td1"><b>游戏房间名称</b></td>
      <td align="center" class="td1"><b>游戏地址</b></td>
	  <td align="center" class="td1"><b>操作</b></td>
    </tr>
    <%int i=1; %>
		 <c:forEach var="r" items="${roomList}">
		<tr class="tdbg">
	      <td height="25" align="center"><%=i++ %></td>
	      <td height="25" align="center">${r.gameID}</td>
	      <td height="25" align="center">${r.displayName}</td>
	      <td height="25" width="100"><input name="dataInter_<%=i%>" size="80" id="dataInter_<%=i%>" type="text" value="${r.dataInter}"></td>
	      <td height="25" align="center"><a style="cursor:pointer" onClick="goto(${r.gameID},'dataInter_<%=i%>')">修改</a></td>
	    </tr>
	</c:forEach>
  </table>
</form>
</body>
</html>

<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
String msg="";
if(request.getAttribute("msg")!=null){
	msg=(String)request.getAttribute("msg");
}
String action="";
if(request.getParameter("action")!=null){
	action=request.getParameter("action");
}
String username = "";
String password = "";
if(session.getAttribute("username")!=null){
	username = (String)session.getAttribute("username");
}
if(session.getAttribute("password")!=null){
	password = (String)session.getAttribute("password");
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

function openrooms(gameID,gameopenclose){
		document.location.href="roomList.do?action=changeRoom&gameID="+gameID+"&gameopenclose="+gameopenclose;

		
	}
function closerooms(gameID,gameopenclose){
		document.location.href="roomList.do?action=changeRoom&gameID="+gameID+"&gameopenclose="+gameopenclose;
	}

</script>
<body ondocumentready="">
  
<form id="form1" name="form1" method="post" action="">

  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
    <tr class="topbg" align="center">
	 
      <td height="25" colspan=7 class="menutop"><div style="width:90%; float:left; text-align:center; line-height:25px;">游戏房间管理</div></td>
	  </tr>
	  <tr>
	   <td align="left" height="25" colspan=6 class="menutop1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="../lottery/roomList.do?action=roomList">游戏开启关闭状态</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="../lottery/roomGetData.do?action=roomList">游戏手动自动数据转换</a></td>
	   <td width="20%" align="right" class="menutop"> <input name="" type="button" onClick="sendFlex()" value=" 执 行 "></td>
    </tr>
    
  </table><br />
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
    <tr class="title">
      <td align="center" class="td1"><b>序号</b></td>
      <td align="center" class="td1"><strong>游戏类型ID</strong></td>
      <td align="center" class="td1"><b>游戏房间名称</b></td>
      <td align="center" class="td1"><b>游戏状态</b></td>
      <td align="center" class="td1"><b>操作</b></td>
    </tr>
    <%int i=1; %>
		 <c:forEach var="r" items="${roomList}">
		<tr class="tdbg">
	      <td height="25" align="center"><%=i++ %></td>
	      <td height="25" align="center">${r.gameID}</td>
	      <td height="25" align="center">${r.displayName}</td>
		  <c:if test="${r.gameopenclose==0}" var="true">	
	      <td height="25" align="center"><font color="#FF0000">${r.gameopencloseName}</font></td></c:if>
		  <c:if test="${r.gameopenclose==1}" var="true">	
	      <td height="25" align="center">${r.gameopencloseName}</td></c:if>
	      <td height="25" align="center">
	      	<c:if test="${r.gameopenclose==0}" var="true">	
                <a style="cursor:hand" href="roomList.do?action=changeRoom&gameID=${r.gameID}&gameopenclose=${r.gameopenclose}">关闭房间</a> </c:if> 
			<c:if test="${r.gameopenclose==1}" var="true">	
	      		<a style="cursor:hand" href="roomList.do?action=changeRoom&gameID=${r.gameID}&gameopenclose=${r.gameopenclose}" ><font color="#FF0000">开启房间</font></a> </c:if></td>
	    </tr>
	</c:forEach>
  </table>
<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
			id="Keno8Manager" width="0" height="0"
			codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
			<param name="movie" value="../Keno8Manager.swf" />
			<param name="quality" value="high" />
			<param name="bgcolor" value="#869ca7" />
			<param name="allowScriptAccess" value="always" />
			<embed src="../Keno8Manager.swf" id="Keno8Manager"
			 quality="high" bgcolor="#869ca7"
				width="0" height="0" name="Keno8Manager" align="middle"
				play="true"
				loop="false"
				quality="high"
				allowScriptAccess="always"
				type="application/x-shockwave-flash"
				pluginspage="http://www.adobe.com/go/getflashplayer">
			</embed>
	</object>
<%--	<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
			id="jifenKeno8Manager" width="0" height="0"
			codebase="http://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab">
			<param name="movie" value="../KenoJifenManager.swf" />
			<param name="quality" value="high" />
			<param name="bgcolor" value="#869ca7" />
			<param name="allowScriptAccess" value="always" />
//			<embed src="../KenoJifenManager.swf" id="jifenKeno8Manager"
//			 quality="high" bgcolor="#869ca7"
//				width="0" height="0" name="jifenKeno8Manager" align="middle"
//				play="true"
//				loop="false"
//				quality="high"
//				allowScriptAccess="always"
//				type="application/x-shockwave-flash"
//				pluginspage="http://www.adobe.com/go/getflashplayer">
//			</embed>
	</object>--%>
</form>
</body>
<script  language="javascript">
var username = '<%=username %>';
var password = '<%=password %>';
var cmd = "101";
function sendFlex(){
		document.getElementById("Keno8Manager").sendFlex(username,password,cmd);
		//document.getElementById("jifenKeno8Manager").sendFlex(username,password,cmd);
		alert("恭喜，操作成功!");
}

</script>

</html>

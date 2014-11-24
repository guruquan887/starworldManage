<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<% 
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires",0); 
%>
<%
	String betSerial = "";
	if(request.getParameter("betSerial")!=null){
		betSerial = request.getParameter("betSerial");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>即时注单</title>
<link href="../css/user.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="<%=request.getContextPath()%>/js/ajax.js"></script>
</head>
<script>
 function closeDiv(id){
	var objtable=document.getElementById(id); 
	objtable.parentNode.removeChild(objtable);
 }
function jumppage(value,betSerial,gameArea){
    var betSerial = document.getElementById("betSerial_").value;
	var gameArea = document.getElementById("gameArea_").value;
    var url="<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&curPage="+value+"&betSerial="+betSerial+"&gameArea="+gameArea;
	return url;
 }
function changepage(pageNo,betSerial,gameArea){
	var betSerial = document.getElementById("betSerial_").value;
	var gameArea = document.getElementById("gameArea_").value;
	var url="<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&curPage="+pageNo+"&betSerial="+betSerial+"&gameArea="+gameArea;
	return url;
 }
</script>
<body style="background:#fff;">
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
    <tr>
          <td height="25" colspan="9" class="menutop"><div style="width:90%; float:left; text-align:center; line-height:25px;">即时注单列表</div>
      </td>
    </tr>
</table>
 <table width="100%" border="0" cellspacing="0" cellpadding="0" >
 <tr><td colspan="4" class="t_center">
 	<div style="padding-left:10px;text-align:left">总计： &nbsp;<b><em>${totalGold}</em></b></div>
 </td></tr>
 </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="4" >
    <tr>
      <td width="25%" valign="top">
	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tables">
        <tr>
          <td width="40%" class="th">玩法</td>
          <td width="25%" class="th">笔数</td>
          <td class="th">金额</td>
        </tr>
		<c:forEach var="dto" items="${oneList}">
        <tr>
          <td class="thyellow">${dto.objectName}&nbsp;</td>
          <td class="t_center">${dto.bets}&nbsp;<c:if test="${dto.bets ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=${dto.gameArea}');">${dto.totalGold}</a>&nbsp;<c:if test="${dto.totalGold ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        </c:forEach>
      </table></td>
      <td width="25%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tables">
        <tr>
          <td width="40%" class="th">玩法</td>
          <td width="25%" class="th">笔数</td>
          <td class="th">金额</td>
        </tr>
        <c:forEach var="dto" items="${twoList}">
        <tr>
          <td class="thyellow">${dto.objectName}&nbsp;</td>
          <td class="t_center">${dto.bets}&nbsp;<c:if test="${dto.bets ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=${dto.gameArea}');">${dto.totalGold}</a>&nbsp;<c:if test="${dto.totalGold ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        </c:forEach>
      </table></td>
      <td width="25%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tables">
        <tr>
          <td width="40%" class="th">玩法</td>
          <td width="25%" class="th">笔数</td>
          <td class="th">金额</td>
        </tr>
        <c:forEach var="dto" items="${threeList}">
        <tr>
          <td class="thyellow">${dto.objectName}&nbsp;</td>
          <td class="t_center">${dto.bets}&nbsp;<c:if test="${dto.bets ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=${dto.gameArea}');">${dto.totalGold}</a>&nbsp;<c:if test="${dto.totalGold ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        </c:forEach>
      </table></td>
      <td width="25%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tables">
        <tr>
          <td width="40%" class="th">玩法</td>
          <td width="25%" class="th">笔数</td>
          <td class="th">金额</td>
        </tr>
        <c:forEach var="dto" items="${fourList}">
        <tr>
          <td class="thyellow">${dto.objectName}&nbsp;</td>
          <td class="t_center">${dto.bets}&nbsp;<c:if test="${dto.bets ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=${dto.gameArea}');">${dto.totalGold}</a>&nbsp;<c:if test="${dto.totalGold ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        </c:forEach>
      </table></td>
    </tr>
  </table>
  <div id="showDetail">
  </div>
</body>

</html>

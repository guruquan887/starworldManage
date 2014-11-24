<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<% 
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires",0); 
%> 
<%	
	int zC = 1;//全部
	int time = 30000;
	try{
		if(request.getParameter("time")!=null){
			time=Integer.parseInt(request.getParameter("time"));
		}
		if(request.getParameter("zc")!=null){
			zC=Integer.parseInt(request.getParameter("zc"));
		}
	}catch(Exception e){
		e.printStackTrace();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>即时注单</title>
<link href="../css/user.css" rel="stylesheet" type="text/css" />
</head>
<script>
function refresh(){
	var d = document.getElementById("dform");
	d.submit();
}   
setTimeout("refresh()",<%=request.getParameter("time")==null?"30000":request.getParameter("time")%>);
</script>
<body style="background:#fff;">
<form id="dform" name="dform" method="post" action="<%=request.getContextPath()%>/single/noteList.do?action=list">
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
    <tr>
          <td height="25" colspan="9" class="menutop">
		  <div style="float:left; text-align:right;line-height:25px; padding-top:3px;">
		  &nbsp;&nbsp;■热门号码&nbsp;&nbsp; </div>
		  <div style="width:80%; float:left; text-align:right; line-height:25px;padding-top:3px;">
		  &nbsp;&nbsp;占成<select name="zc" id="zc" onchange="dform.submit()"><option value="1">占成</option><option value="0">全部</option></select>
		  &nbsp;&nbsp;盘类<select><option>A盘</option><option>B盘</option></select>
		  &nbsp;&nbsp;排列方式<select><option>按金额</option><option>按笔数</option></select>
		  &nbsp;&nbsp;<select name="time" id="time" onchange="dform.submit()"><option value="10000"> 10 秒刷新 </option><option value="30000"> 30 秒刷新 </option><option value="60000"> 1 分钟刷新 </option><option value="180000"> 3 分钟刷新 </option></select>
		  </div>
		  <div style="float:right; text-align:right;line-height:25px; padding-top:3px;">
		  <input type="button" style="cursor:pointer;" onclick="dform.submit()" value="手动刷新>>" />
		  </div>
      </td>
    </tr>
</table>
 </form> 
  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="tables">
    <tr>
      <td width="12%" class="th">项目</td>
      <td width="6%" class="th">大</td>
      <td width="6%" class="th">小</td>
      <td width="6%" class="th">单</td>
	  <td width="6%" class="th">双</td>
      <td width="6%" class="th">庄</td>
	  <td width="6%" class="th">闲</td>
      <td width="6%" class="th">和</td>
	  <td width="6%" class="th">红</td>
      <td width="6%" class="th">黑</td>
	  <td width="6%" class="th">庄对</td>
	  <td width="6%" class="th">闲对</td>
      <td width="6%" class="th">号码</td>
	  <td width="6%" class="th">笔数</td>
      <td width="10%" class="th">金额</td>
    </tr>
	<c:forEach var="g" items="${gameList}">
	 <tr>
      <td class="thyellow"><a href="<%=request.getContextPath()%>/single/noteList.do?action=detailInfo&kid=${g.serverID}&betSerial=${g.betSerial}&zc=<%=zC%>">${g.objectName}</a>&nbsp;</td>
      <td class="t_center">${g.da}&nbsp;<c:if test="${g.da ==null}" var="true"><font color="#666666">-</font></c:if></td>
      <td class="t_center">${g.xiao}&nbsp;<c:if test="${g.xiao ==null}" var="true"><font color="#666666">-</font></c:if></td>
      <td class="t_center">${g.dan}&nbsp;<c:if test="${g.dan ==null}" var="true"><font color="#666666">-</font></c:if></td>
      <td class="t_center">${g.shuang}&nbsp;<c:if test="${g.shuang ==null}" var="true"><font color="#666666">-</font></c:if></td>
      <td class="t_center">${g.zhuang}&nbsp;<c:if test="${g.zhuang ==null}" var="true"><font color="#666666">-</font></c:if></td>
      <td class="t_center">${g.xian}&nbsp;<c:if test="${g.xian ==null}" var="true"><font color="#666666">-</font></c:if></td>
      <td class="t_center">${g.he}&nbsp;<c:if test="${g.he ==null}" var="true"><font color="#666666">-</font></c:if></td>
      <td class="t_center">${g.red}&nbsp;<c:if test="${g.red ==null}" var="true"><font color="#666666">-</font></c:if></td>
      <td class="t_center">${g.black}&nbsp;<c:if test="${g.black ==null}" var="true"><font color="#666666">-</font></c:if></td>
	  <td class="t_center">${g.zhuangDui}&nbsp;<c:if test="${g.zhuangDui ==null}" var="true"><font color="#666666">-</font></c:if></td>
      <td class="t_center">${g.xianDui}&nbsp;<c:if test="${g.xianDui ==null}" var="true"><font color="#666666">-</font></c:if></td>
      <td class="t_center">${g.number}&nbsp;<c:if test="${g.number ==null}" var="true"><font color="#666666">-</font></c:if></td>
      <td class="t_center">${g.bets}&nbsp;<c:if test="${g.bets ==null}" var="true"><font color="#666666">-</font></c:if></td>
      <td class="t_center">${g.amount}&nbsp;<c:if test="${g.amount ==null}" var="true"><font color="#666666">-</font></c:if></td>		  	  
    </tr>
	</c:forEach>
	
</table>
</body>
<script type="text/javascript">
var zc=document.getElementById("zc");
var index="<%=zC %>";
for(var i=0; i<zc.options.length; i++){
			if(zc.options[i].value==index){
				zc.options[i].selected=true;break;
	}
}
		
var ttime=document.getElementById("time");
var index2="<%=time %>";
for(var i=0; i<ttime.options.length; i++){
			if(ttime.options[i].value==index2){
				ttime.options[i].selected=true;break;
	}
}
</script> 
</html>

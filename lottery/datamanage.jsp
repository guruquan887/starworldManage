<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page isELIgnored="false" %> 
<%
	String time = "";
	String time1 = "";
	try{
		if(request.getAttribute("time2")!=null){
			time= String.valueOf(request.getAttribute("time2"));
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	if(request.getParameter("time")!=null){
			time1=request.getParameter("time");
		}
	String msg="";
	if(request.getAttribute("msg")!=null){
		msg=(String)request.getAttribute("msg");
	}

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>数据管理</title>
<link href="../css/user.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
}
-->
</style>
<link href="../css/AdminCss.css" rel="stylesheet" type="text/css" />
</head>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
function keno8delete()
{
    var time=document.all.time.options[document.all.time.selectedIndex].value;
	document.location.href="dataManage.do?action=deleteList&time="+time;
}
function beifen()
{
	document.location.href="dataManage.do?action=countList";
}
</script>  
<body>
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
    <tr>
          <td height="25" colspan="9" class="menutop"><div style="width:90%; float:left; text-align:center; line-height:25px;">数据管理列表</div>
      </td>
    </tr>
  </table>
  <form id="form1" name="form1" method="post" action="dataManage.do?action=deleteRecord">
<table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">  
</table>
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
    <tr class="tdcenter">
      <td height="30" align="center"><strong>操作</strong></td>
    </tr>
    <tr class="tdcenter">
      <td align="center">请选取删除时间范围 
        <select name="time" id="time" onchange="keno8delete()">
		<option value="30">1个月之前</OPTION>    
		<option value="60">2个月之前</OPTION>  
		<option value="90">3个月之前</OPTION>  
        </select>
		<input type="submit" name="button" value="执行删除操作"/>
       </td>
     </tr>
	 <br/>
    <tr class="tdcenter">
      <td align="center">点击将进行数据库备份操作<input type="button" name="button" value="进行数据库备份" onclick="beifen()"/></td>
	  </tr>
</table>
 <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
  <tr>
      <td  align="center" width="60%"><strong>备份文件名</strong></td>
      <td  align="center" width="20%"><strong>文件大小</strong></td>
      <td  align="center" width="20%"><strong>操作</strong></td>
  </tr>
<c:forEach var="file" items="${files}">
<tr class="tdcenter">
      <td  align="left">&nbsp;&nbsp;<strong>${file.fileName}</strong></td>
      <td  align="left">&nbsp;&nbsp;<strong>${file.fileSize}bytes</strong></td>
      <td  align="center"><a href="<%=request.getContextPath()%>/lottery/dataManage.do?action=delete&filename=${file.encodeFileName}">删除</a> <a href="<%=request.getContextPath()%>/download.do?action=download&filename=${file.encodeFileName}">下载</a></td>
  </tr>
 
</c:forEach>
 </table>
</form>
</body>
<script type="text/javascript">
var ttime=document.all.time;
var index2="<%=time1 %>";
for(var i=0; i<ttime.options.length; i++){
			if(ttime.options[i].value==index2){
				ttime.options[i].selected=true;break;
			}
		}
var msg="<%=msg %>";
if(msg!='')alert(msg);  
var time1 = '<%=time %>';
if(time1!='')alert("查找 "+time1+" 之前keno8数据库的数据");  
</script> 
</html>

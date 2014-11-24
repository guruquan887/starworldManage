<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<script language="javascript" type="text/javascript">
function datadelete()
{
    var time=document.all.time.options[document.all.time.selectedIndex].value;
	document.location.href="<%=request.getContextPath()%>/system/dataManage.do?action=deleteList&time="+time;
}
function beifen()
{
	document.location.href="<%=request.getContextPath()%>/system/dataManage.do?action=countList";
}
</script>  
<body>
	<Div class="title">数据库管理列表</Div>
		<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/system/dataManage.do?action=deleteRecord">
		  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
            <tr>
              <td width="180" class="menutop">项目</td>
              <td class="menutop">内容</td>
            </tr>
            <tr>
              <td class="tdright_new">请选取删除时间范围：</td>
              <td class="tdright"><label>
                <select name="time" id="time" onchange="datadelete()">
                  <option value="30">1个月之前</OPTION>    
				  <option value="60">2个月之前</OPTION>  
				  <option value="90">3个月之前</OPTION>  
                </select>
                <input name="Submit" type="submit" class="input" value="执行删除" />
              </label></td>
            </tr>
            <tr>
              <td class="tdright_new">点击将时行备份操作：</td>
              <td class="tdright"><input name="button" type="button" class="input" value="数据库备份" onclick="beifen()"/></td>
            </tr>
          </table>
         </form>
		 <% int i=0;%>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td width="60" class="menutop">序号</td>
            <td class="menutop">备份文件名</td>
            <td class="menutop">文件大小</td>
            <td class="menutop">操作</td>
          </tr>
		  
		  <c:forEach var="file" items="${files}">
		  <%i++;%>
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter"><%=i%></td>
            <td class="tdcenter">${file.fileName}</td>
            <td class="tdcenter">${file.fileSize}bytes</td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/system/dataManage.do?action=delete&filename=${file.encodeFileName}">删除</a> <a href="<%=request.getContextPath()%>/download.do?action=download&filename=${file.encodeFileName}">下载</a> </td>
          </tr>
		  
		  </c:forEach>
</table>
</body>
</html>
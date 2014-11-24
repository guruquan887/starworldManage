<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %> 
<%
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=10;
	try{
		if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("pageIndex")!=null){
			pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	recordIndex=(pageIndex-1)*pageSize+1;
	String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
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
function pubnews(newsid){
	document.location.href="<%=request.getContextPath()%>/web/pageoneChangeState.do?action=pub&newsid="+newsid;
}
function unpubnews(newsid){
	document.location.href="<%=request.getContextPath()%>/web/pageoneChangeState.do?action=unpub&newsid="+newsid;
}
</script>
<body>
	<Div class="title">单页管理</Div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td width="60" class="menutop">序号</td>
            <td class="menutop">单页</td>
            <td class="menutop">操作</td>
          </tr>
		  <c:forEach var="news" items="${newslist}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter"><%=recordIndex++ %></td>
            <td class="tdcenter">${news.newstitle}</td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/web/pageOnePreUpdate.do?id=${news.id}">编辑</a> / <c:if test="${news.state==0}" var="true">
      		<a style="cursor:hand" onClick="pubnews(${news.id})">发布</a>      	</c:if>
      	<c:if test="${news.state==1}" var="true">
      		<a style="cursor:hand" onClick="unpubnews(${news.id})">撤销</a>     </c:if></td>
          </tr>
		  </c:forEach>
        </table>
</body>
</html>
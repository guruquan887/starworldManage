<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", -10);
%>
<%
	if (session.getAttribute("USER") == null){
	out.print("<script>alert('对不起，您还没有登录本系统或者您已经超过登录时间!');top.location.href='../login.jsp';</script>");

}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML xmlns="http://www.w3.org/1999/xhtml">

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
  <body>
    <%
	session.invalidate();
//response.sendRedirect("login.jsp");
	//out.print("<script>  window.target='_parent';window.location.href='login.jsp';</script>");

	//java.io.PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<script>");
				out.println("parent.window.close();");
				out.println("window.open ('" + request.getContextPath()
						+ "/login.jsp','')");
				out.println("</script>");
				out.println("</html>");
	%>
  </body>
  <script language="javascript">
   //document.location.target='_parent._parent';
  //parent.frmright.location.target='_parent._parent';
  //document.location.href='login.jsp';
  </script>
</html>

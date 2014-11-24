package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("﻿<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd\">\r\n");
      out.write("<HTML xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<HEAD>\r\n");

String msg="";
if(request.getAttribute("msg")!=null){
	msg=(String)request.getAttribute("msg");
}

      out.write("\r\n");
      out.write("<TITLE>后台管理系统</TITLE>\r\n");
      out.write("<META http-equiv=Content-Type content=\"text/html; charset=utf-8\">\r\n");
      out.write("<STYLE type=text/css>\r\n");
      out.write("BODY {COLOR: white; font-size:12px;}\r\n");
      out.write("</STYLE>\r\n");
      out.write("<script language=\"javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/userweb.js\"></script>\r\n");
      out.write("<script language=\"javascript\">\r\n");
      out.write("var msg=\"");
      out.print(msg );
      out.write("\";\r\n");
      out.write("if(msg!='')alert(msg);\r\n");
      out.write("</script>\r\n");
      out.write("<META content=\"MSHTML 6.00.2900.6036\" name=GENERATOR>\r\n");
      out.write("<link href=\"css/main.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("</HEAD>\r\n");
      out.write("<BODY style=\"BACKGROUND: #0381ba\" >\r\n");
      out.write("<FORM name=theForm onSubmit=\"return validateLogin()\" action=\"login.do\" method=post>\r\n");
      out.write("<TABLE style=\"MARGIN-TOP: 100px\" cellSpacing=0 cellPadding=0 align=center>\r\n");
      out.write("  <TBODY>\r\n");
      out.write("  <TR>\r\n");
      out.write("    <TD><IMG height=256 alt=\"xc\" src=\"images/login.png\" width=175 \r\n");
      out.write("      border=0></TD>\r\n");
      out.write("    <TD style=\"PADDING-LEFT: 10px\">\r\n");
      out.write("      <TABLE align=\"left\">\r\n");
      out.write("        <TBODY>\r\n");
      out.write("        \t<TR>\r\n");
      out.write("          <TD width=\"72\" height=\"30\">管理员姓名：</TD>\r\n");
      out.write("          <TD width=\"176\"><INPUT name=\"username\" id=\"username\" size=\"18\" class=\"input_width2\"></TD></TR>\r\n");
      out.write("        \t<TR>\r\n");
      out.write("          <TD height=\"30\">管理员密码：</TD>\r\n");
      out.write("          <TD><INPUT name=\"password\" type=\"password\" id=\"password\" size=\"18\" class=\"input_width2\"></TD></TR>\r\n");
      out.write("          <tr><TD>&nbsp;</TD>\r\n");
      out.write("          <TD><INPUT name=\"提交\" type=\"submit\" class=\"input\" value=\"确定登录\"></TD></TR>\r\n");
      out.write("        </TABLE></TD></TR></TBODY></TABLE>\r\n");
      out.write("\r\n");
      out.write("</FORM>\r\n");
      out.write("\r\n");
      out.write("</BODY></HTML>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}

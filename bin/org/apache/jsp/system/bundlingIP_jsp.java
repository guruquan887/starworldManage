package org.apache.jsp.system;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class bundlingIP_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"", true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../yanzheng.jsp", out, false);
      out.write("\r\n");
      out.write(" \r\n");

	String msg="";
	if(request.getAttribute("msg")!=null){
		msg=(String)request.getAttribute("msg");
	}


      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>无标题文档</title>\r\n");
      out.write("<link href=\"../css/main.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("</head>\r\n");
      out.write("<script language=\"javascript\" type=\"text/javascript\">\r\n");
      out.write("var msg=\"");
      out.print(msg );
      out.write("\";\r\n");
      out.write("if(msg!='')alert(msg);\r\n");
      out.write("function bangding()\r\n");
      out.write("{\r\n");
      out.write("\t\tvar ip = document.getElementById(\"ip\").value;\r\n");
      out.write("\t\tif(ip==\"\"){\r\n");
      out.write("\t\talert(\"ip不能为空\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\t\treturn true;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function tjSubmit()\r\n");
      out.write("{\r\n");
      out.write("\t  form1.action=\"");
      out.print(request.getContextPath());
      out.write("/system/adminList.do?action=qxbundlingIP\";\r\n");
      out.write("\t  form1.submit();\r\n");
      out.write(" }\r\n");
      out.write("</script>  \r\n");
      out.write("<body>\r\n");
      out.write("\t<Div class=\"title\">绑定IP</Div>\r\n");
      out.write("\t<form id=\"form1\" name=\"form1\" method=\"post\" onsubmit=\"return bangding()\" action=\"");
      out.print(request.getContextPath());
      out.write("/system/adminList.do?action=bundlingIP\">\r\n");
      out.write("\t\t<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_margin\">\r\n");
      out.write("<tr\talign=\"center\">\r\n");
      out.write("      <td width=\"20%\" height=\"22\" class=\"tdright_new\">登录的用户名：</td>\r\n");
      out.write("      <td width=\"80%\" class=\"tdright\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${username}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr\talign=\"center\">\r\n");
      out.write("      <td height=\"22\" class=\"tdright_new\">目前登录的IP：</td>\r\n");
      out.write("      <td class=\"tdright\"><input name=\"ip\" id=\"ip\" type=\"text\" class=\"input_width2\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ip}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" /></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("\t <tr align=\"center\">\r\n");
      out.write("      <td height=\"22\" class=\"tdright_new\">&nbsp;</td>\r\n");
      out.write("      <td class=\"tdright\"><label>\r\n");
      out.write("        <input type=\"submit\" class=\"input\" name=\"Submit\" value=\"绑定IP\"/>\r\n");
      out.write("     &nbsp; \r\n");
      out.write("     <input type=\"button\" class=\"input\" name=\"Submit2\" value=\"取消绑定\" onclick=\"tjSubmit()\"/>\r\n");
      out.write("      </label></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("</table>\r\n");
      out.write("</form>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
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

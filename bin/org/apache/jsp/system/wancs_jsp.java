package org.apache.jsp.system;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class wancs_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../yanzheng.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

String msg="";
if(request.getAttribute("msg")!=null){
	msg=(String)request.getAttribute("msg");
}

      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<META http-equiv=Content-Type content=\"text/html; charset=utf-8\">\r\n");
      out.write("<title>无标题文档</title>\r\n");
      out.write("<link href=\"../css/main.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("</head>\r\n");
      out.write("<script language=\"javascript\">\r\n");
      out.write("var msg=\"");
      out.print(msg );
      out.write("\";\r\n");
      out.write("if(msg!='')alert(msg);\r\n");
      out.write("</script>\r\n");
      out.write("<body>\r\n");
      out.write("<form id=\"form1\" name=\"form1\" method=\"post\" action=\"");
      out.print(request.getContextPath());
      out.write("/system/systemset.do?action=update\">\r\n");
      out.write("  <table cellpadding=\"2\" cellspacing=\"1\" border=\"0\" width=\"100%\" align=\"center\">\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td height=\"25\" colspan=\"4\" class=\"menutop\" style=\"text-align:center;\">发布公告</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr\talign=\"center\">\r\n");
      out.write("      <td height=\"22\" class=\"tdleft\">网站标题：</td>\r\n");
      out.write("      <td class=\"tdright\"><input name=\"wwwName\" type=\"text\" size=\"50\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.wwwName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">&nbsp;</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr\talign=\"center\">\r\n");
      out.write("      <td width=\"20%\" height=\"22\" class=\"tdleft\">后台标题：</td>\r\n");
      out.write("      <td width=\"80%\" class=\"tdright\"><input name=\"siteName\" type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.siteName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr\talign=\"center\">\r\n");
      out.write("      <td height=\"22\" class=\"tdleft\">版权名称：</td>\r\n");
      out.write("      <td class=\"tdright\"><input name=\"copyRight\" id=\"copyRight\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.copyRight}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" type=\"text\"></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("\t    <tr\talign=\"center\">\r\n");
      out.write("      <td height=\"22\" class=\"tdleft\">网站公告：</td>\r\n");
      out.write("      <td class=\"tdright\"><input name=\"adsMsg\" id=\"adsMsg\" size=\"50\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.adsMsg}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" type=\"text\"></td>\r\n");
      out.write("    </tr>   \r\n");
      out.write("\t <tr align=\"center\">\r\n");
      out.write("      <td height=\"22\" class=\"tdleft\">大厅公告：</td>\r\n");
      out.write("      <td class=\"tdright\"><input name=\"gameMsg\" id=\"gameMsg\" size=\"50\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.gameMsg}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" type=\"text\"></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("\t\r\n");
      out.write("    <tr\talign=\"center\">\r\n");
      out.write("      <td height=\"22\" class=\"tdleft\">&nbsp;</td>\r\n");
      out.write("      <td class=\"tdright\"><label>\r\n");
      out.write("        <input type=\"submit\" class=\"input\" name=\"Submit\" value=\"提交\"/>\r\n");
      out.write("     &nbsp; \r\n");
      out.write("     <input type=\"reset\" class=\"input\" name=\"Submit2\" value=\"重置\"/>\r\n");
      out.write("\t <input type=\"hidden\" name=\"siteID\" value=\"1\" />\r\n");
      out.write("      </label></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("  </table>\r\n");
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

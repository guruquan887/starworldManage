package org.apache.jsp.gold;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class budan_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
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
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>无标题文档</title>\r\n");
      out.write("<link href=\"../css/main.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("</head>\r\n");
      out.write("<script language=\"javascript\">\r\n");
      out.write("function check(){\r\n");
      out.write("\r\n");
      out.write("  var accounts = document.form2.accounts.value;\r\n");
      out.write("  var r3_Amt = document.form2.r3_Amt.value;\r\n");
      out.write("  if(accounts==''){\r\n");
      out.write("      alert(\"请输入充值用户!\");\r\n");
      out.write("\t  return false;\r\n");
      out.write("  }\r\n");
      out.write("  if(r3_Amt==''){\r\n");
      out.write("      alert(\"请输入充值金额!\");\r\n");
      out.write("\t  return false;\r\n");
      out.write("  }\r\n");
      out.write("  return true;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("<body>\r\n");
      out.write("<Div class=\"title\"><span>  \r\n");
      out.write("\t  <form id=\"form1\" name=\"form1\" method=\"post\" action=\"\">\r\n");
      out.write("\t    <label>\r\n");
      out.write("\t    <input name=\"Submit\" type=\"button\" class=\"input\" value=\"取款\" onclick=\"window.location.href='");
      out.print(request.getContextPath());
      out.write("/gold/drawList.do?action=showDrawList'\" />\r\n");
      out.write("\t    <input name=\"Submit3\" type=\"button\" class=\"input\" value=\"存款\"  onclick=\"window.location.href='");
      out.print(request.getContextPath());
      out.write("/gold/yeepayList.do?action=showYeepayList'\" />\r\n");
      out.write("\t    </label>\r\n");
      out.write("\t  </form>\r\n");
      out.write("\t</span>网银管理—补单</Div>\r\n");
      out.write("    <form id=\"form2\" name=\"form2\" method=\"post\" onSubmit=\"return check()\" action=\"");
      out.print(request.getContextPath());
      out.write("/gold/yeepayList.do?action=addYeaapy\">\r\n");
      out.write("      <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_margin\">\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"120\" class=\"menutop\">项目</td>\r\n");
      out.write("          <td class=\"menutop\">内容</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">订单号：</td>\r\n");
      out.write("          <td class=\"tdlefts\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dh}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("<input type=\"hidden\" name=\"dh\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dh}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" ></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">充值用户：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><label>\r\n");
      out.write("            <input name=\"accounts\" type=\"text\" class=\"input_width2\" />\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">充值金额：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><label>\r\n");
      out.write("            <input name=\"r3_Amt\" type=\"text\" class=\"input_width2\" />\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">&nbsp;</td>\r\n");
      out.write("          <td class=\"tdright\"><label>\r\n");
      out.write("            <input name=\"Submit4\" type=\"submit\" class=\"input\" value=\"确定补单\" />\r\n");
      out.write("            <input name=\"Submit42\" type=\"button\" class=\"input\" value=\"取消补单\" onclick=\"window.location.href='");
      out.print(request.getContextPath());
      out.write("/gold/yeepayList.do?action=showYeepayList'\" />\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("      </table>\r\n");
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

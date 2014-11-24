package org.apache.jsp.baob;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class gameserachbbtj_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>输赢报表</title>\r\n");
      out.write("<link href=\"../css/main.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("<script language=\"javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/js.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<form id=\"form1\" name=\"form1\" method=\"post\" action=\"");
      out.print(request.getContextPath());
      out.write("/baob/bbtj.do?action=search\">\r\n");
      out.write("  <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"tables\">\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td height=\"25\" class=\"title\">输赢报表</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("  </table>\r\n");
      out.write("  <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" style=\"margin-top:5px;\">\r\n");
      out.write("    \r\n");
      out.write("    <tr>\r\n");
      out.write("      <td class=\"menutop\">项目</td>\r\n");
      out.write("      <td class=\"menutop\">内容</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td class=\"menutop\">查询种类：</td>\r\n");
      out.write("      <td class=\"tdright\"><label>\r\n");
      out.write("      <select name=\"typeName\">\r\n");
      out.write("\t  <option value=\"brg\">百人馆</option>\r\n");
      out.write("\t  <option value=\"jjg\">竞技馆</option>\r\n");
      out.write("      </select>\r\n");
      out.write("      </label></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td class=\"menutop\">时间区间：</td>\r\n");
      out.write("      <td class=\"tdright\">\r\n");
      out.write("        <input id=\"startTime\" name=\"startTime\" type=\"text\" value=\"\" onClick=\"setDay(this);\" size=\"10\" readonly=\"readonly\"/>&nbsp;00:00:00\r\n");
      out.write("        到\r\n");
      out.write("       <input id=\"endTime\" name=\"endTime\" value=\"\" type=\"text\" onClick=\"setDay(this);\" size=\"10\" readonly=\"readonly\"/>&nbsp;00:00:00\r\n");
      out.write("       <input name=\"checkTime\" id=\"checkTime\" type=\"radio\" value=\"today\"/>\r\n");
      out.write("       今日\r\n");
      out.write("\t  <input name=\"checkTime\" id=\"checkTime\" type=\"radio\" value=\"yestoday\" />\r\n");
      out.write("\t  昨日\r\n");
      out.write("\t  <input name=\"checkTime\" id=\"checkTime\" type=\"radio\"  value=\"cweek\" />\r\n");
      out.write("\t  本周\r\n");
      out.write("\t  <input name=\"checkTime\" id=\"checkTime\" type=\"radio\"  value=\"bweek\"/>\r\n");
      out.write("\t  上周\r\n");
      out.write("\t  <input name=\"checkTime\" id=\"checkTime\" type=\"radio\"  value=\"cmonth\"/>\r\n");
      out.write("\t  本月\r\n");
      out.write("\t  <input name=\"checkTime\" id=\"checkTime\" type=\"radio\" value=\"bmonth\" />\r\n");
      out.write("\t  上月\r\n");
      out.write("\t  <input name=\"checkTime\" id=\"checkTime\" type=\"radio\"  value=\"all\" />\r\n");
      out.write("\t  全部</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td class=\"menutop\">用户名称：</td>\r\n");
      out.write("      <td class=\"tdright\"><label>\r\n");
      out.write("        <input type=\"text\" name=\"termOne\" id=\"termOne\" />\r\n");
      out.write("      </label>\r\n");
      out.write("      <select name=\"selectOne\" class=\"input_width\" id=\"selectOne\">\r\n");
      out.write("\t      <option value=\"accounts\">用户名</option>\r\n");
      out.write("\t      <option value=\"gameID\">游戏ID</option>\r\n");
      out.write("        </select>可不输，不输为查询全部</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td class=\"menutop\">&nbsp;</td>\r\n");
      out.write("      <td class=\"tdright\"><label>\r\n");
      out.write("        <input type=\"submit\" class=\"input\" name=\"Submit\" value=\"提交\" />\r\n");
      out.write("        &nbsp;\r\n");
      out.write("        <input type=\"reset\" class=\"input\" name=\"Submit2\" value=\"重置\" />\r\n");
      out.write("      </label></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("  </table>\r\n");
      out.write("</form>\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("</html>\r\n");
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

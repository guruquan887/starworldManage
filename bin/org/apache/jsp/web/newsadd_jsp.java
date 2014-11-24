package org.apache.jsp.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class newsadd_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList(2);
    _jspx_dependants.add("/WEB-INF/struts-logic.tld");
    _jspx_dependants.add("/WEB-INF/struts-bean.tld");
  }

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
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>无标题文档</title>\r\n");
      out.write("<link href=\"../css/main.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("</head>\r\n");
      out.write("<script language=\"javascript\" type=\"text/javascript\">\r\n");
      out.write("function submitform(){\r\n");
      out.write("\tdocument.all.submithid.click();\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function newsaddcheck(){\r\n");
      out.write("\tvar newstitle = document.form1.newstitle.value;\r\n");
      out.write("\tvar content = document.form1.content.value;\r\n");
      out.write("\tif(newstitle==\"\"){\r\n");
      out.write("\t\t  alert(\"标题不能为空\");\r\n");
      out.write("\t\t  return false;\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\tif(content==\"\"){\r\n");
      out.write("\t\talert(\"内容不能为空\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"../FCKeditor/fckeditor.js\"></script>\r\n");
      out.write("<body>\r\n");
      out.write("\t<Div class=\"title\"><span>\r\n");
      out.write("\t  <label>\r\n");
      out.write("\t  <input name=\"Submit\" type=\"button\" class=\"input\" value=\"类别管理\" />\r\n");
      out.write("\t  </label>\r\n");
      out.write("\t</span>内容添加</Div>\r\n");
      out.write("    <form id=\"form1\" name=\"form1\" method=\"post\" onSubmit=\"return newsaddcheck()\" action=\"");
      out.print(request.getContextPath());
      out.write("/web/newsAdd.do\">\r\n");
      out.write("      <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_margin\">\r\n");
      out.write("\t  \r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"120\" class=\"menutop\">项目</td>\r\n");
      out.write("          <td class=\"menutop\">内容</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\t\t <tr>\r\n");
      out.write("          <td class=\"tdright_new\">新闻类别：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><label>\r\n");
      out.write("           <select name=\"classID\">\r\n");
      out.write("\t\t\t<option value=\"1\">新闻</option>\t\r\n");
      out.write("\t\t\t<option value=\"2\">公告</option>\t\r\n");
      out.write("        </select>\r\n");
      out.write("\t\t必选\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">新闻标题：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><label>\r\n");
      out.write("            <input name=\"newstitle\" type=\"text\" size=\"50\" style=\"width:400px\"/>\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">属性：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><label>\r\n");
      out.write("            <input type=\"checkbox\" name=\"in_OnTop\" value=\"1\" />\r\n");
      out.write("          置顶新闻\r\n");
      out.write("          <input type=\"checkbox\" name=\"in_OnTopAll\" value=\"1\" />\r\n");
      out.write("          总置顶新闻\r\n");
      out.write("          <input type=\"checkbox\" name=\"in_IsElite\" value=\"1\" />\r\n");
      out.write("          推荐新闻\r\n");
      out.write("          <input type=\"checkbox\" name=\"in_IsHot\" value=\"1\" />\r\n");
      out.write("          热门新闻\r\n");
      out.write("          <input type=\"checkbox\" name=\"in_IsLock\" value=\"1\" checked=\"checked\" />\r\n");
      out.write("          立即发布</label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">引用：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"in_LinkUrl\" id=\"in_LinkUrl\" type=\"text\" size=\"50\" style=\"width:400px\" value=\"http://\"/>\r\n");
      out.write("            <label>\r\n");
      out.write("            <input type=\"checkbox\" name=\"in_IsLinks\" id=\"in_IsLinks\" value=\"1\" />\r\n");
      out.write("            链接地址\r\n");
      out.write("            </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("       \r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">新闻内容：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><label>\r\n");
      out.write("            <textarea id=\"content\" name=\"content\"><br></textarea>\r\n");
      out.write("\t\t\t<script type=\"text/javascript\">  \r\n");
      out.write("\t\t\tvar oFCKeditor = new FCKeditor( 'content' ) ;     \r\n");
      out.write("\t\t\toFCKeditor.ToolbarSet = 'Default' ;   \r\n");
      out.write("\t\t\toFCKeditor.Width = '100%' ;   \r\n");
      out.write("\t\t\toFCKeditor.Height = '400' ;   \r\n");
      out.write("\t\t\toFCKeditor.Value = '' ;   \r\n");
      out.write("\t\t\toFCKeditor.ReplaceTextarea();     \r\n");
      out.write("\t\t\t</script>  \r\n");
      out.write("\t\t必填\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">&nbsp;</td>\r\n");
      out.write("          <td class=\"tdlefts\"><label>\r\n");
      out.write("            <input name=\"Submit2\" type=\"submit\" class=\"input\" value=\"确定添加\" />\r\n");
      out.write("         &nbsp; \r\n");
      out.write("         <input name=\"Submit22\" type=\"reset\" class=\"input\" value=\"重新填写\"/>\r\n");
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

package org.apache.jsp.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class gameadd_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("    <title>增加页面</title>\r\n");
      out.write("<link href=\"../css/main.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("  </head>\r\n");
      out.write("  <script language=\"javascript\" type=\"text/javascript\">\r\n");
      out.write("  function adaddcheck(){\r\n");
      out.write("\tvar gameName = document.form1.gameName.value;\r\n");
      out.write("\tvar content = document.form1.content.value;\r\n");
      out.write("\tvar gameUrl = document.form1.gameUrl.value;\r\n");
      out.write("\tvar photourl = document.form1.photourl.value;\r\n");
      out.write("\tif(gameName==\"\"){\r\n");
      out.write("\t\talert(\"游戏名称不能为空\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\tif(content==\"\"){\r\n");
      out.write("\talert(\"游戏简介不能为空\");\r\n");
      out.write("\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\tif(gameUrl==\"\"){\r\n");
      out.write("\talert(\"游戏链接不能为空\");\r\n");
      out.write("\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\tif(photourl==\"\"){\r\n");
      out.write("\talert(\"请上传游戏图片\");\r\n");
      out.write("\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\t\treturn true;\r\n");
      out.write("\t}\r\n");
      out.write("  </script>\r\n");
      out.write("  <script type=\"text/javascript\" src=\"../FCKeditor/fckeditor.js\"></script>\r\n");
      out.write("  <body>\r\n");
      out.write("  <form id=\"form1\" name=\"form1\" method=\"post\" onSubmit=\"return adaddcheck()\" action=\"");
      out.print(request.getContextPath());
      out.write("/web/gameList.do?action=gameAdd\">\r\n");
      out.write("  <p>&nbsp;</p>\r\n");
      out.write("  <p></p>\r\n");
      out.write("  <table width=\"800\" border=\"0\" align=\"center\" cellpadding=\"2\" cellspacing=\"1\" class=\"table_margin\">\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td height=\"22\" colspan=\"2\" class=\"title\">游戏管理：增加游戏</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("   <tr>\r\n");
      out.write("      <td class=\"tdright_new\">游戏类型</td>\r\n");
      out.write("      <td><select name=\"gameType\" size=\"1\" class=\"Select\">\r\n");
      out.write("         <option value=\"0\" selected=\"selected\">棋牌游戏</option>\r\n");
      out.write("       <option value=\"2\" selected=\"selected\">彩票游戏</option>\r\n");
      out.write("      </select>\r\n");
      out.write("      必选</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td class=\"tdright_new\">游戏名称</td>\r\n");
      out.write("      <td class=\"tdlefts\"><input name=\"gameName\" id=\"gameName\" type=\"text\" value=\"\"/>必填</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td class=\"tdright_new\">游戏简介</td>\r\n");
      out.write("      <td class=\"tdlefts\"><textarea id=\"content\" name=\"content\"></textarea>\r\n");
      out.write("\t  <script type=\"text/javascript\">  \r\n");
      out.write("\t\t\tvar oFCKeditor = new FCKeditor( 'content' ) ;     \r\n");
      out.write("\t\t\toFCKeditor.ToolbarSet = 'Default' ;   \r\n");
      out.write("\t\t\toFCKeditor.Width = '100%';   \r\n");
      out.write("\t\t\toFCKeditor.Height = '400';   \r\n");
      out.write("\t\t\toFCKeditor.Value = '' ;   \r\n");
      out.write("\t\t\toFCKeditor.ReplaceTextarea();     \r\n");
      out.write("\t\t\t</script>  \r\n");
      out.write("\t\t必填</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td class=\"tdright_new\">游戏链接</td>\r\n");
      out.write("      <td class=\"tdlefts\"><input name=\"gameUrl\" id=\"gameUrl\" type=\"text\" value=\"\" size=\"30\" /></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("     <td class=\"tdright_new\">\r\n");
      out.write("\t\t\t\t\t\t<div align=\"left\">上传照片\t\t\t\t\t\t</div>\t\t\t\t\t</td>\r\n");
      out.write("\t  <td class=\"tdlefts\">\r\n");
      out.write("\t\t\t\t\t\t<label>\r\n");
      out.write("\t\t\t\t\t\t\t<iframe name=\"uploadframe\" id=\"uploadframe\" width=100% height='120' src=\"");
      out.print(request.getContextPath());
      out.write("/web/FileUploadInner1.jsp\" frameborder=0 scrolling=no></iframe><input type=\"hidden\" name=\"photourl\" id=\"photourl\" value=\"\" />\r\n");
      out.write("\t\t\t\t\t\t</label>  \t\t\r\n");
      out.write("\t\t\t\t\t\t必选</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("  </table>\r\n");
      out.write("  <table width=\"800\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">\r\n");
      out.write("    <tr align=\"center\">\r\n");
      out.write("      <td><input type=\"submit\" name=\"Submit\" value=\"添加\" /> 　\r\n");
      out.write("        <input type=\"reset\" name=\"Submit2\" value=\"重置\" /></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("  </table>\r\n");
      out.write("</form>\r\n");
      out.write("  </body>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("function setphotourl1(url)\r\n");
      out.write("        {\r\n");
      out.write("\t\tdocument.all.photourl.value=url;\r\n");
      out.write("\t\t}\r\n");
      out.write("</script>\r\n");
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

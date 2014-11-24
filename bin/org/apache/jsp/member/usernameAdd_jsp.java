package org.apache.jsp.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class usernameAdd_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");

String regAccounts = "";
double winner = 0;
double brokerage = 0;
double taxRate = 0;
int spreaderID = 3;
int levelID = 1;
String accounts = "";
if(request.getParameter("accounts")!=null){
      accounts = request.getParameter("accounts");
}
if(request.getParameter("spreaderID")!=null){
	    spreaderID = Integer.parseInt(request.getParameter("spreaderID"));
	}
if(request.getParameter("levelID")!=null){
	    levelID = Integer.parseInt(request.getParameter("levelID"));
	}
if(request.getParameter("regAccounts")!=null){
   regAccounts = request.getParameter("regAccounts");
}
if(request.getParameter("winner")!=null){
   winner = Double.parseDouble(request.getParameter("winner"));
}
if(request.getParameter("brokerage")!=null){
   brokerage = Double.parseDouble(request.getParameter("brokerage"));
}
double sxwinner = 0;
if(request.getAttribute("sxwinner")!=null){
	   sxwinner = (Double)(request.getAttribute("sxwinner"));
   }
double sxbrokerage = 0;
if(request.getAttribute("sxbrokerage")!=null){
	   sxbrokerage = (Double)(request.getAttribute("sxbrokerage"));
   }
double sxtaxRate = 0;
if(request.getAttribute("sxtaxRate")!=null){
	   sxtaxRate = (Double)(request.getAttribute("sxtaxRate"));
   }
if(request.getParameter("taxRate")!=null){
   taxRate = Double.parseDouble(request.getParameter("taxRate"));
}
String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}

      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>无标题文档</title>\r\n");
      out.write("<link href=\"../css/main.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("</head>\r\n");
      out.write("<script type='text/javascript' src='");
      out.print(request.getContextPath());
      out.write("/js/jquery.js'></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath() );
      out.write("/js/jquery-latest.pack.js\"></script>\r\n");
      out.write("<body>\r\n");
      out.write("\t<Div class=\"title\"><span>\r\n");
      out.write("\t  <label>\r\n");
      out.write("\t  <input name=\"Submit3\" type=\"button\" class=\"input\" value=\"查看\" onclick=\"window.location.href='");
      out.print(request.getContextPath());
      out.write("/member/gameuserList.do?action=gameUserList'\" />\r\n");
      out.write("\t  </label>\r\n");
      out.write("\t</span>会员—新增</Div>\r\n");
      out.write("\t\r\n");
      out.write("    <form id=\"form1\" name=\"form1\" method=\"post\" action=\"");
      out.print(request.getContextPath());
      out.write("/member/gameuserList.do?action=addUser\">\r\n");
      out.write("      <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_margin\">\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"120\" class=\"menutop\">项目</td>\r\n");
      out.write("          <td class=\"menutop\">内容</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("          <td class=\"tdright_new\">所属：</td>\r\n");
      out.write("          <td class=\"tdlefts\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.accounts}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write('/');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.spreaderID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">用户名：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"type\" id=\"type\" type=\"hidden\" value=\"0\" /><input name=\"sxAccounts\" type=\"hidden\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.accounts}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" /><input name=\"accounts\" id=\"accounts\" onBlur=\"checkusername();\" type=\"text\" class=\"input_width2\" /><font color=\"#FF0000\"><span id=\"accountdiv\"></span></font></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">昵称：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><label><input name=\"spreaderID\" id=\"spreaderID\" type=\"hidden\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.spreaderID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("\t\t  <input name=\"levelID\" type=\"hidden\" value=\"");
      out.print(levelID);
      out.write("\" />\r\n");
      out.write("            <input name=\"regAccounts\" id=\"regAccounts\" type=\"text\" class=\"input_width2\" onBlur=\"checknickName();\"/><font color=\"#FF0000\"><span id=\"nickNamediv\"></span></font>\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">密码：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><label>\r\n");
      out.write("            <input name=\"password1\" id=\"password1\" type=\"password\" class=\"input_width2\" />\r\n");
      out.write("            <a href=\"#\"></a></label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">确认密码：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"password2\" id=\"password2\" type=\"password\" class=\"input_width2\" /></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">钱庄密码：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"bankPass\" id=\"bankPass\" type=\"password\" class=\"input_width2\" /></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">钱庄密码确认：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"bankPass2\" id=\"bankPass2\" type=\"password\" class=\"input_width2\" /></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("       <tr>\r\n");
      out.write("          <td class=\"tdright_new\">状态：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><label>\r\n");
      out.write("            <input name=\"isFreeze\" id=\"isFreeze\" type=\"radio\" value=\"0\" checked=\"checked\" />\r\n");
      out.write("            启用\r\n");
      out.write("            <input type=\"radio\" name=\"isFreeze\" id=\"isFreeze\" value=\"1\" />\r\n");
      out.write("停用 </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">&nbsp;</td>\r\n");
      out.write("          <td class=\"tdright\"><label>\r\n");
      out.write("            <input name=\"Submit\" type=\"submit\" class=\"input\" value=\"确定添加\" />\r\n");
      out.write("            <input name=\"Submit2\" type=\"reset\" class=\"input\" value=\"重新填写\" />\r\n");
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

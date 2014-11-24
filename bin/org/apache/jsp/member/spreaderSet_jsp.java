package org.apache.jsp.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class spreaderSet_jsp extends org.apache.jasper.runtime.HttpJspBase
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
if(request.getParameter("regAccounts")!=null){
   regAccounts = request.getParameter("regAccounts");
}
String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}
int type = 0;
if(request.getParameter("type")!=null){
	    type = Integer.parseInt(request.getParameter("type"));
	}
int spreaderID = 3;
int levelID = 0;
if(request.getParameter("spreaderID")!=null){
	    spreaderID = Integer.parseInt(request.getParameter("spreaderID"));
	}
	if(request.getParameter("levelID")!=null){
	    levelID = Integer.parseInt(request.getParameter("levelID"));
	}
String accounts = "";
if(request.getParameter("accounts")!=null){
   accounts = request.getParameter("accounts");
}
long insureScore = 0;
if(request.getAttribute("insureScore")!=null){
	insureScore = (Long)(request.getAttribute("insureScore"));
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
      out.write("<script language=\"javascript\">\r\n");
      out.write("var msg=\"");
      out.print(msg );
      out.write("\";\r\n");
      out.write("if(msg!='')alert(msg);\r\n");
      out.write("function isNumber(oNum) {\r\n");
      out.write(" if(!oNum) return false; //line:160\r\n");
      out.write(" if(oNum.value==\"\") return false;\r\n");
      out.write(" var vv = oNum.value.replace(/[^\\d]/g,'');\r\n");
      out.write(" var strP=/^\\d+$/;\r\n");
      out.write("  if (!isNaN(oNum.value)) {\r\n");
      out.write("        return true;\r\n");
      out.write("    } else {\r\n");
      out.write("        alert(\"请输入整数\");\r\n");
      out.write("        oNum.value=vv;\r\n");
      out.write(" \t\toNum.focus();\r\n");
      out.write(" \t\treturn false; \r\n");
      out.write("    }\r\n");
      out.write(" }\r\n");
      out.write(" \r\n");
      out.write("</script>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div class=\"tab clearfix\">\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li class=\"active\"><a href=\"");
      out.print(request.getContextPath());
      out.write("/member/gameuserList.do?action=spreaderSet\">推广管理</a></li>\r\n");
      out.write("\t\t\t<li><a href=\"");
      out.print(request.getContextPath());
      out.write("/member/spreaderUserList.jsp\">推广明细</a></li>\r\n");
      out.write("\t\t\t<li><a href=\"");
      out.print(request.getContextPath());
      out.write("/member/gameuserList.do?action=SpreaderUserList\">财务明细</a></li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\r\n");
      out.write("    <form id=\"form1\" name=\"form1\" method=\"post\" action=\"");
      out.print(request.getContextPath());
      out.write("/member/gameuserList.do?action=spreaderSetUpdate\" >\r\n");
      out.write("      <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_margin\">\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td colspan=\"2\" class=\"menutop\"><div align=\"left\">注册赠送</div></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"120\" class=\"tdright_new\">银子：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><label>\r\n");
      out.write("            <input type=\"text\" name=\"RegisterGrantScore\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.registerGrantScore}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td colspan=\"2\" class=\"menutop\"><div align=\"left\">游戏时长赠送（属于一次性赠送，推荐设置108000）</div></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("          <td width=\"120\" class=\"tdright_new\">时长（秒）：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><label>\r\n");
      out.write("            <input type=\"text\" name=\"PlayTimeCount\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.playTimeCount}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>（单位：秒）\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("          <td width=\"120\" class=\"tdright_new\">银子：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><label>\r\n");
      out.write("            <input type=\"text\" name=\"PlayTimeGrantScore\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.playTimeGrantScore}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("          <td colspan=\"2\" class=\"menutop\"><div align=\"left\">充值赠送</div></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("          <td width=\"120\" class=\"tdright_new\">比率：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><label>\r\n");
      out.write("            <input type=\"text\" name=\"FillGrantRate\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.fillGrantRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>（单位：%）\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\t\t        <tr>\r\n");
      out.write("          <td colspan=\"2\" class=\"menutop\"><div align=\"left\">结算赠送</div></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("          <td width=\"120\" class=\"tdright_new\">比率：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><label>\r\n");
      out.write("            <input type=\"text\" name=\"BalanceRate\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.balanceRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>（单位：%）\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("          <td width=\"120\" class=\"tdright_new\">最低值：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><label>\r\n");
      out.write("            <input type=\"text\" name=\"MinBalanceScore\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.minBalanceScore}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">&nbsp;</td>\r\n");
      out.write("          <td class=\"tdright\"><label>\r\n");
      out.write("            <input name=\"Submit\" type=\"submit\" class=\"input\" value=\"保存\" />\r\n");
      out.write("\t\t\t</label></td>\r\n");
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

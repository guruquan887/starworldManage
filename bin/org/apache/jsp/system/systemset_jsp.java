package org.apache.jsp.system;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class systemset_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("/system/NetCS.do?action=update\">\r\n");
      out.write("  <table cellpadding=\"2\" cellspacing=\"1\" border=\"0\" width=\"100%\" align=\"center\">\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td height=\"25\" colspan=\"4\" class=\"menutop\" style=\"text-align:left;\">参数设置</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td height=\"25\" colspan=\"4\" class=\"tdright\">  <A href=\"");
      out.print(request.getContextPath());
      out.write("/system/NetCS.do?action=preUpdate&systemParam=AllScoreIn57Game\">总游戏币</A> |  <A href=\"");
      out.print(request.getContextPath());
      out.write("/system/NetCS.do?action=preUpdate&systemParam=AllScoreIn57GameRest\">总剩余游戏币</A> | <A href=\"");
      out.print(request.getContextPath());
      out.write("/system/NetCS.do?action=preUpdate&systemParam=BankPrerequisite\">存取条件</A> | <A href=\"");
      out.print(request.getContextPath());
      out.write("/system/NetCS.do?action=preUpdate&systemParam=EnjoinInsure\">钱庄服务</A> | <A href=\"");
      out.print(request.getContextPath());
      out.write("/system/NetCS.do?action=preUpdate&systemParam=EnjoinLogon\">登录服务</A> | <A href=\"");
      out.print(request.getContextPath());
      out.write("/system/NetCS.do?action=preUpdate&systemParam=EnjoinRegister\">注册服务</A> | <A href=\"");
      out.print(request.getContextPath());
      out.write("/system/NetCS.do?action=preUpdate&systemParam=GrantIPCount\">注册IP限制</A> | <A href=\"");
      out.print(request.getContextPath());
      out.write("/system/NetCS.do?action=preUpdate&systemParam=GrantScoreCount\">注册赠送</A> | <A href=\"");
      out.print(request.getContextPath());
      out.write("/system/NetCS.do?action=preUpdate&systemParam=MedalRate\">奖牌返率</A> | <A href=\"");
      out.print(request.getContextPath());
      out.write("/system/NetCS.do?action=preUpdate&systemParam=RevenueRateTake\">取款税率</A> | <A href=\"");
      out.print(request.getContextPath());
      out.write("/system/NetCS.do?action=preUpdate&systemParam=RevenueRateTransfer\">转账税率</A> | <A href=\"");
      out.print(request.getContextPath());
      out.write("/system/NetCS.do?action=preUpdate&systemParam=TransferNullity\">转账是否收税</A> | <A href=\"");
      out.print(request.getContextPath());
      out.write("/system/NetCS.do?action=preUpdate&systemParam=TransferPrerequisite\">转账条件</A> | <A href=\"");
      out.print(request.getContextPath());
      out.write("/system/PreUpdateGradeCS.do\">会员退水利率设置</A> | <A href=\"");
      out.print(request.getContextPath());
      out.write("/system/Pre3DUpdateNetCS.do\">综合设置</A></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr\talign=\"center\">\r\n");
      out.write("      <td width=\"20%\" height=\"22\" class=\"tdleft\">键名：</td>\r\n");
      out.write("      <td width=\"80%\" class=\"tdright\">\r\n");
      out.write("      <input name=\"StatusName\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.statusName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" readonly=\"true\" /></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr\talign=\"center\">\r\n");
      out.write("      <td height=\"22\" class=\"tdleft\">键值：</td>\r\n");
      out.write("      <td class=\"tdright\"><input name=\"StatusValue\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.statusValue}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" /></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("\t <tr align=\"center\">\r\n");
      out.write("      <td height=\"22\" class=\"tdleft\">名称：</td>\r\n");
      out.write("      <td class=\"tdright\"><input name=\"StatusTip\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.statusTip}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" /></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("\t <tr align=\"center\">\r\n");
      out.write("\t   <td height=\"22\" class=\"tdleft\">备注：</td>\r\n");
      out.write("\t   <td class=\"tdright\"><textarea name=\"StatusString\" rows=\"3\" cols=\"50\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.statusString}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</textarea></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("\t <tr align=\"center\">\r\n");
      out.write("      <td height=\"22\" class=\"tdleft\">描述：</td>\r\n");
      out.write("      <td class=\"tdright\"><textarea name=\"StatusDescription\" rows=\"5\" cols=\"50\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.statusDescription}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</textarea></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("\t\r\n");
      out.write("    <tr\talign=\"center\">\r\n");
      out.write("      <td height=\"22\" class=\"tdleft\">&nbsp;</td>\r\n");
      out.write("      <td class=\"tdright\"><label>\r\n");
      out.write("        <input type=\"submit\" class=\"input\" name=\"Submit\" value=\"提交\"/>\r\n");
      out.write("     &nbsp; \r\n");
      out.write("     <input type=\"reset\" class=\"input\"  value=\"重置\"/>\r\n");
      out.write("\t <input type=\"hidden\" name=\"id\" value=\"1\" />\r\n");
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

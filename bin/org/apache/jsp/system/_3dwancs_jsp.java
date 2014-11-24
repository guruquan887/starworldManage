package org.apache.jsp.system;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class _3dwancs_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("/system/Update3DNetCS.do\">\r\n");
      out.write("  <table cellpadding=\"2\" cellspacing=\"1\" border=\"0\" width=\"100%\" align=\"center\">\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td height=\"25\" colspan=\"4\" class=\"menutop\" style=\"text-align:center;\">网站参数设定</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    ");
      out.write("\r\n");
      out.write("\t<!-- <tr\talign=\"center\">\r\n");
      out.write("      <td width=\"20%\" height=\"22\" class=\"tdleft\"><div align=\"right\">银子兑换：</div></td>\r\n");
      out.write("      <td width=\"80%\" class=\"tdright\"><label>\r\n");
      out.write("            <div align=\"left\">\r\n");
      out.write("              <input name=\"goldRateScore\" type=\"text\" size=\"12\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.goldRateScore}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("                    <span class=\"tdleft\"> 积分(1银子对兑的积分数)      </span></div>\r\n");
      out.write("      </label></td>\r\n");
      out.write("    </tr>-->\r\n");
      out.write("    <tr\talign=\"center\">\r\n");
      out.write("      <td height=\"22\" class=\"tdleft\"> <div align=\"right\">新用户注册：</div></td>\r\n");
      out.write("      <td class=\"tdright\"><label>\r\n");
      out.write("        <div align=\"left\">\r\n");
      out.write("         <!-- <input name=\"regScore\" type=\"text\" size=\"12\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.regScore}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("          <span class=\"tdleft\">积分-->\r\n");
      out.write("          <input name=\"regGold\" type=\"text\" size=\"12\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.regGold}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("          <span class=\"tdleft\">银子</span></div>\r\n");
      out.write("      </label></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("\t    <tr\talign=\"center\">\r\n");
      out.write("      <td height=\"22\" class=\"tdleft\"> <div align=\"right\">新用户注册：</div></td>\r\n");
      out.write("      <td class=\"tdright\"><label>\r\n");
      out.write("        <div align=\"left\">\r\n");
      out.write("         <!-- <input name=\"regScore\" type=\"text\" size=\"12\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.regScore}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("          <span class=\"tdleft\">积分-->\r\n");
      out.write("          <input name=\"regScore\" type=\"text\" size=\"12\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.regScore}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("          <span class=\"tdleft\">积分</span></div>\r\n");
      out.write("      </label></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr\talign=\"center\">\r\n");
      out.write("      <td height=\"22\" class=\"tdleft\"><div align=\"right\">网银充值设定：</div></td>\r\n");
      out.write("      <td class=\"tdright\"><div align=\"left\">\r\n");
      out.write("        <input name=\"yeepayRate\" type=\"text\" size=\"12\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.yeepayRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("        <span class=\"tdleft\">（1/元）银子</span></div></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr\talign=\"center\">\r\n");
      out.write("      <td height=\"22\" class=\"tdleft\">投注额的倍数：</td>\r\n");
      out.write("      <td class=\"tdright\"><label>\r\n");
      out.write("        <input type=\"text\" size=\"12\" name=\"beishu\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.beishu}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"><span class=\"tdleft\">达到存款额的倍数返还第一次存款的奖励</span>\r\n");
      out.write("      </label></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr\talign=\"center\">\r\n");
      out.write("      <td height=\"22\" class=\"tdleft\">第一次存款反水的利率：</td>\r\n");
      out.write("      <td class=\"tdright\"><input type=\"text\" size=\"12\" name=\"fanben\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.fanben}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"><span class=\"tdleft\">百分比&nbsp;&nbsp;例如：10.0 = 10%</span></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("   <!-- <tr\talign=\"center\">\r\n");
      out.write("      <td height=\"22\" class=\"tdleft\"><div align=\"right\">推广的下线所获得奖励：</div></td>\r\n");
      out.write("      <td class=\"tdright\"><div align=\"left\">\r\n");
      out.write("          <input name=\"onLineTimeGold\" type=\"text\" size=\"12\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.onLineTimeGold}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("        (万银子)\r\n");
      out.write("        /\r\n");
      out.write("          <input name=\"onLineTime\" type=\"text\" size=\"3\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.onLineTime}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("          小时</div></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr\talign=\"center\">\r\n");
      out.write("      <td height=\"22\" class=\"tdleft\"><div align=\"right\">推广的上线所获得奖励：</div></td>\r\n");
      out.write("      <td class=\"tdright\"><div align=\"left\">\r\n");
      out.write("        <input name=\"uponLineTimeGold\" type=\"text\" size=\"12\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.uponLineTimeGold}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("        (万银子)\r\n");
      out.write("        /\r\n");
      out.write("        <input name=\"onLineTime\" type=\"text\" size=\"3\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.onLineTime}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("      小时</div></td>\r\n");
      out.write("    </tr>-->\r\n");
      out.write("    <tr\talign=\"center\">\r\n");
      out.write("      <td height=\"22\" class=\"tdleft\"><div align=\"right\">上线充值提成：</div></td>\r\n");
      out.write("      <td class=\"tdright\"><label>\r\n");
      out.write("        <div align=\"left\">\r\n");
      out.write("          <input name=\"gameTax\" type=\"text\" size=\"12\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.gameTax}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/><span class=\"tdleft\">百分比&nbsp;&nbsp;例如：0.5 = 0.5%（推广的下线充值上线所获提成）</span></div>\r\n");
      out.write("      </label></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("   <!-- <tr\talign=\"center\">\r\n");
      out.write("      <td height=\"22\" class=\"tdleft\"><div align=\"right\">用户转账系统税收：</div></td>\r\n");
      out.write("      <td class=\"tdright\"><div align=\"left\">\r\n");
      out.write("\t  <input name=\"xtss\" type=\"text\" size=\"12\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.xtss}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("\t  <span class=\"tdleft\">百分比（用户转账的时候系统征收的税收）</span></div></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr\talign=\"center\">\r\n");
      out.write("      <td height=\"22\" class=\"tdleft\"><div align=\"right\">管理员邮箱：</div></td>\r\n");
      out.write("      <td class=\"tdright\"><div align=\"left\">\r\n");
      out.write("        <input name=\"adminEmail\" type=\"text\" size=\"30\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.adminEmail}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("      </div></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr\talign=\"center\">\r\n");
      out.write("      <td height=\"22\" class=\"tdleft\"><div align=\"right\">联系人：</div></td>\r\n");
      out.write("      <td class=\"tdright\"><div align=\"left\">\r\n");
      out.write("        <input name=\"linkMan\" type=\"text\" size=\"30\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.linkMan}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("      </div></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr\talign=\"center\">\r\n");
      out.write("      <td height=\"22\" class=\"tdleft\"><div align=\"right\">ICP 备案信息：</div></td>\r\n");
      out.write("      <td class=\"tdright\"><div align=\"left\">\r\n");
      out.write("        <input name=\"recordInfo\" type=\"text\" size=\"30\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.recordInfo}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/>\r\n");
      out.write("      </div></td>\r\n");
      out.write("    </tr>-->\r\n");
      out.write("    <tr\talign=\"center\">\r\n");
      out.write("      <td height=\"22\" class=\"tdleft\">&nbsp;</td>\r\n");
      out.write("      <td class=\"tdright\"><label>\r\n");
      out.write("        <input type=\"submit\" class=\"input\" name=\"Submit\" value=\"提交\"/>\r\n");
      out.write("     &nbsp; \r\n");
      out.write("     <input type=\"reset\" class=\"input\" name=\"Submit2\" value=\"重置\"/>\r\n");
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

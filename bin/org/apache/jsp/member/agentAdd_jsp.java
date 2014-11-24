package org.apache.jsp.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class agentAdd_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.release();
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
      out.write("function check(){\r\n");
      out.write("   if(document.getElementById(\"accounts\").value==\"\"){\r\n");
      out.write("       alert(\"请输入账号!\");\r\n");
      out.write("\t   return false;\r\n");
      out.write("   }\r\n");
      out.write("   if(document.getElementById(\"regAccounts\").value==\"\"){\r\n");
      out.write("       alert(\"请输入昵称!\");\r\n");
      out.write("\t   return false;\r\n");
      out.write("   }\r\n");
      out.write("   if(document.getElementById(\"password1\").value==\"\"){\r\n");
      out.write("       alert(\"请输入密码!\");\r\n");
      out.write("\t   return false;\r\n");
      out.write("   }\r\n");
      out.write("   if(document.getElementById(\"password2\").value==\"\"){\r\n");
      out.write("       alert(\"请输入确认密码!\");\r\n");
      out.write("\t   return false;\r\n");
      out.write("   }\r\n");
      out.write("   if(document.getElementById(\"password1\").value!=document.getElementById(\"password2\").value){\r\n");
      out.write("       alert(\"两次密码输入不正确!\");\r\n");
      out.write("\t   return false;\r\n");
      out.write("   }\r\n");
      out.write("   if(document.getElementById(\"winner\").value==\"\"){\r\n");
      out.write("       alert(\"请输入股份比例!\");\r\n");
      out.write("\t   return false;\r\n");
      out.write("   }\r\n");
      out.write("   if(document.getElementById(\"brokerage\").value==\"\"){\r\n");
      out.write("       alert(\"请输入佣金!\");\r\n");
      out.write("\t   return false;\r\n");
      out.write("   }\r\n");
      out.write("   if(document.getElementById(\"taxRate\").value==\"\"){\r\n");
      out.write("       alert(\"请输入扣税提成!\");\r\n");
      out.write("\t   return false;\r\n");
      out.write("   }\r\n");
      out.write("\r\n");
      out.write("var winner = document.getElementById(\"winner\").value;\r\n");
      out.write("var brokerage = document.getElementById(\"brokerage\").value;\r\n");
      out.write("var taxRate = document.getElementById(\"taxRate\").value;\r\n");
      out.write("if(parseInt(winner)>'");
      out.print(sxwinner);
      out.write("'){\r\n");
      out.write("   alert(\"股份占成超出上限!\");\r\n");
      out.write("   return false;\r\n");
      out.write("}\r\n");
      out.write("if(parseInt(brokerage)>'");
      out.print(sxbrokerage);
      out.write("'){\r\n");
      out.write("   alert(\"洗码佣金超出上限!\");\r\n");
      out.write("   return false;\r\n");
      out.write("}\r\n");
      out.write("if(parseFloat(taxRate)>'");
      out.print(sxtaxRate);
      out.write("'){\r\n");
      out.write("   alert(\"税收提成超出上限!\");\r\n");
      out.write("   return false;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function checkusername(){\r\n");
      out.write("var accounts=encodeURI(document.getElementById(\"accounts\").value);\r\n");
      out.write("if(accounts==0||accounts==\"\"){\r\n");
      out.write("  $(\"#accountdiv\").empty(); \r\n");
      out.write("  $(\"#accountdiv\").append(\"用户名不能为空！\"); \r\n");
      out.write("   }\r\n");
      out.write("else{\r\n");
      out.write("$(\"#accountdiv\").empty(); \r\n");
      out.write("jQuery.get(\"");
      out.print(request.getContextPath());
      out.write("/member/gameuserList.do?action=checkMemName&accounts=\"+accounts,null, function(data){\r\n");
      out.write("if(data==\"0\"){\r\n");
      out.write("$(\"#accountdiv\").append(\"恭喜，用户名可用！\"); \r\n");
      out.write("}\r\n");
      out.write("if(data==\"-1\"){\r\n");
      out.write("$(\"#accountdiv\").append(\"对不起，用户名不可用！\"); \r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("});\r\n");
      out.write("}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function checknickName(){\r\n");
      out.write("var regAccounts=encodeURI(document.getElementById(\"regAccounts\").value);\r\n");
      out.write("if(regAccounts==0||regAccounts==\"\"){\r\n");
      out.write("  $(\"#nickNamediv\").empty(); \r\n");
      out.write("  $(\"#nickNamediv\").append(\"昵称不能为空！\"); \r\n");
      out.write("   }\r\n");
      out.write("else{\r\n");
      out.write("$(\"#nickNamediv\").empty(); \r\n");
      out.write("jQuery.get(\"");
      out.print(request.getContextPath());
      out.write("/member/gameAgentList.do?action=checkNickName&regAccounts=\"+regAccounts,null, function(data){\r\n");
      out.write("if(data==\"0\"){\r\n");
      out.write("$(\"#nickNamediv\").append(\"恭喜，昵称可用！\"); \r\n");
      out.write("}\r\n");
      out.write("if(data==\"-1\"){\r\n");
      out.write("$(\"#nickNamediv\").append(\"对不起，昵称不可用！\"); \r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("});\r\n");
      out.write("}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div class=\"title\"><span>\r\n");
      out.write("\t  <label>\r\n");
      out.write("\t  <input name=\"Submit3\" type=\"button\" class=\"input\" value=\"查看代理\" onclick=\"window.location.href='");
      out.print(request.getContextPath());
      out.write("/member/gameAgentList.do?action=gameAgentList'\" />\r\n");
      out.write("\t  </label>\r\n");
      out.write("\t</span>");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      if (_jspx_meth_c_005fif_005f1(_jspx_page_context))
        return;
      if (_jspx_meth_c_005fif_005f2(_jspx_page_context))
        return;
      if (_jspx_meth_c_005fif_005f3(_jspx_page_context))
        return;
      out.write("—新增</div>\r\n");
      out.write("    <form id=\"form1\" name=\"form1\" method=\"post\" action=\"");
      out.print(request.getContextPath());
      out.write("/member/gameAgentList.do?action=addUser\" onsubmit=\"return check()\">\r\n");
      out.write("      <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_margin\">\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"120\" class=\"menutop\">项目</td>\r\n");
      out.write("          <td class=\"menutop\">内容</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">所属：</td>\r\n");
      out.write("          <td class=\"tdlefts\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.accounts}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">用户名：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"type\" id=\"type\" type=\"hidden\" value=\"1\" /><input name=\"sxAccounts\" type=\"hidden\" value=\"");
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
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">股份比例：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"winner\" id=\"winner\" type=\"text\" class=\"input_width2\" onkeyup=\"isNumber(this)\" value=\"");
      out.print(winner);
      out.write("\"/>%上限");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sxwinner}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("%</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">真人佣金：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"brokerage\" id=\"brokerage\" type=\"text\" class=\"input_width2\" onkeyup=\"isNumber(this)\" value=\"");
      out.print(brokerage);
      out.write("\"/>%上限");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sxbrokerage}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("%</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">扣税提成：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><label>\r\n");
      out.write("            <input name=\"taxRate\" id=\"taxRate\" type=\"text\" class=\"input_width2\" onkeyup=\"isNumber(this)\" value=\"");
      out.print(taxRate);
      out.write("\"/>%上限");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sxtaxRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("%\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">状态：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><label>\r\n");
      out.write("            <input name=\"isFreeze\" id=\"isFreeze\" type=\"radio\" value=\"0\" checked=\"checked\" />\r\n");
      out.write("            启用\r\n");
      out.write("            <input type=\"radio\" name=\"isFreeze\" id=\"isFreeze\" value=\"1\" />\r\n");
      out.write("停用 </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">&nbsp;</td>\r\n");
      out.write("          <td class=\"tdright\"><label>\r\n");
      out.write("            <input name=\"Submit\" type=\"submit\" class=\"input\" value=\"确定\" />\r\n");
      out.write("            <input name=\"Submit2\" type=\"reset\" class=\"input\" value=\"重置\" />\r\n");
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

  private boolean _jspx_meth_c_005fif_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /member/agentAdd.jsp(175,8) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${levelID==1}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /member/agentAdd.jsp(175,8) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setVar("true");
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('公');
        out.write('司');
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent(null);
    // /member/agentAdd.jsp(175,55) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${levelID==2}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /member/agentAdd.jsp(175,55) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setVar("true");
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('股');
        out.write('东');
        int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f2.setParent(null);
    // /member/agentAdd.jsp(175,102) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${levelID==3}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /member/agentAdd.jsp(175,102) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f2.setVar("true");
    int _jspx_eval_c_005fif_005f2 = _jspx_th_c_005fif_005f2.doStartTag();
    if (_jspx_eval_c_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('总');
        out.write('代');
        out.write('理');
        int evalDoAfterBody = _jspx_th_c_005fif_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f3 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f3.setParent(null);
    // /member/agentAdd.jsp(175,150) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f3.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${levelID==4}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /member/agentAdd.jsp(175,150) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f3.setVar("true");
    int _jspx_eval_c_005fif_005f3 = _jspx_th_c_005fif_005f3.doStartTag();
    if (_jspx_eval_c_005fif_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('代');
        out.write('理');
        int evalDoAfterBody = _jspx_th_c_005fif_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f3);
    return false;
  }
}

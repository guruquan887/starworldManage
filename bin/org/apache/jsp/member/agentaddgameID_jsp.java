package org.apache.jsp.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class agentaddgameID_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.release();
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
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" >\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

String gameIDSelect = "";
if(request.getParameter("gameIDSelect")!=null){
   gameIDSelect = request.getParameter("gameIDSelect");
   System.out.println("gameIDSelect:"+gameIDSelect);
}
String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}
int type=0;
	if(request.getParameter("type")!=null){
	    type = Integer.parseInt(request.getParameter("type"));
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
      out.write("<script type=\"text/javascript\">\r\n");
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
      out.write("\r\n");
      out.write(" function check(){\r\n");
      out.write("  \tvar reGameID = document.getElementById(\"in_ReGameID\").value;\r\n");
      out.write("    var in_Reason = document.getElementById(\"in_Reason\").value;\r\n");
      out.write("\tif(reGameID == \"\")\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\talert(\"赠送号码不能为空！\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\telse if(!IsPositiveInt64(reGameID))\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\talert(\"赠送号码输入非法！\");\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\telse if(parseInt(reGameID)>999999)\r\n");
      out.write("\t{\r\n");
      out.write("\t\talert(\"赠送号码输入非法！\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\telse if(strLen(reGameID)>10)\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\talert(\"赠送号码输入非法！\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\telse if(in_Reason==\"\"){\r\n");
      out.write("\t   alert(\"请输入赠送原因!\");\r\n");
      out.write("\t   return false;\r\n");
      out.write("\t}\r\n");
      out.write("\telse{\r\n");
      out.write("\treturn true;\r\n");
      out.write("\t}\r\n");
      out.write(" }\r\n");
      out.write(" \r\n");
      out.write("  function strLen(s) {\r\n");
      out.write("    var len=0;\r\n");
      out.write("    for(var i=0;i<s.length;i++){\r\n");
      out.write("        if(!ischinese(s.charAt(i))){\r\n");
      out.write("            len+=1;\r\n");
      out.write("        }else {\r\n");
      out.write("            len+=2;\r\n");
      out.write("        }\r\n");
      out.write("    }\r\n");
      out.write("     return len;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function ischinese(s){\r\n");
      out.write("    var ret=false;\r\n");
      out.write("    for(var i=0;i<s.length;i++){\r\n");
      out.write("        if(s.charCodeAt(i)>=256){\t\t\t\r\n");
      out.write("\t\t\tret=true;\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("    return ret;\r\n");
      out.write("}\r\n");
      out.write(" \r\n");
      out.write(" //判断给定的字符串是否是bigint类型\r\n");
      out.write("function IsPositiveInt64(str) {\r\n");
      out.write("    var reg = /^\\d+$/;\r\n");
      out.write("    var r = str.match(reg);\r\n");
      out.write("    if (r == null) {\r\n");
      out.write("        return false;\r\n");
      out.write("    }\r\n");
      out.write("    else if (r > 9223372036854775807) {\r\n");
      out.write("        return false;\r\n");
      out.write("    }\r\n");
      out.write("    return true;\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div class=\"title\"><span>\r\n");
      out.write("\t  <label>\r\n");
      out.write("\t  <input name=\"Submit3\" type=\"button\" class=\"input\" value=\"查看用户\" onclick=\"window.location.href='");
      out.print(request.getContextPath());
      out.write("/member/gameuserList.do?action=gameUserList&type=0'\" />\r\n");
      out.write("\t  </label>\r\n");
      out.write("\t</span>用户—赠送靓号</div>\r\n");
      out.write("    <form id=\"form1\" name=\"form1\" method=\"post\" action=\"");
      out.print(request.getContextPath());
      out.write("/member/gameuserList.do?action=zsGameID\" onsubmit=\"return check()\">\r\n");
      out.write("      <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_margin\">\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"120\" class=\"menutop\">项目</td>\r\n");
      out.write("          <td class=\"menutop\">内容</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"menutop\">用户名（gameID）：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><label>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.accounts}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write('(');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.gameID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write(")<input name=\"userID\" id=\"userID\" type=\"hidden\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.userID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"menutop\"><span class=\"listTdLeft\">赠送靓号ID</span>：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"type\" id=\"type\" type=\"hidden\" value=\"");
      out.print(type);
      out.write("\" /><input name=\"in_ReGameID\" id=\"in_ReGameID\" type=\"text\" class=\"input_width2\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${gameID1}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" onkeyup=\"isNumber(this)\" maxlength=\"11\" />\r\n");
      out.write("            <select name=\"gameIDSelect\" style=\"width:80px;\" onchange=\"document.form1.in_ReGameID.value = this.value\">\r\n");
      out.write("             ");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("            </select></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"menutop\"><span class=\"listTdLeft\">赠送原因：</span></td>\r\n");
      out.write("          <td class=\"tdlefts\"><label>\r\n");
      out.write("            <textarea name=\"in_Reason\" id=\"in_Reason\" cols=\"20\" rows=\"2\"></textarea><input name=\"type\" id=\"type\" type=\"hidden\" value=\"");
      out.print(type);
      out.write("\" />\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("      \r\n");
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
      out.write("<script language=\"javascript\">\r\n");
      out.write("var ttype1=document.all.gameIDSelect;\r\n");
      out.write("var index1=\"");
      out.print(gameIDSelect );
      out.write("\";\r\n");
      out.write("for(var i=0; i<ttype1.options.length; i++){\r\n");
      out.write("\tif(ttype1.options[i].value==index1){\r\n");
      out.write("\t\tttype1.options[i].selected=true;break;\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
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

  private boolean _jspx_meth_c_005fforEach_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /member/agentaddgameID.jsp(135,13) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("gameIDlist");
    // /member/agentaddgameID.jsp(135,13) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${gameIDlist}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("             <option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${gameIDlist.gameID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write('"');
          out.write('>');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${gameIDlist.gameID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</option>\r\n");
          out.write("             ");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }
}

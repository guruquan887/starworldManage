package org.apache.jsp.system;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class gradecs_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../yanzheng.jsp", out, false);
      out.write('\r');
      out.write('\n');

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
      out.write("function goto(grade,gradeExp,rebateLV){\r\n");
      out.write(" //var grade = document.getElementById(\"grade\").value;\r\n");
      out.write("//alert(gradeExp+\"&&&\"+rebateLV)\r\n");
      out.write(" //alert(document.getElementById(gradeExp).value+\"&&&&&&\"+document.getElementById(rebateLV).value);\r\n");
      out.write(" var gradeExp = document.getElementById(gradeExp).value;\r\n");
      out.write(" var rebateLV = document.getElementById(rebateLV).value;\r\n");
      out.write(" document.location.href=\"");
      out.print(request.getContextPath());
      out.write("/system/UpdateGradeCS.do?grade=\"+grade+\"&gradeExp=\"+gradeExp+\"&rebateLV=\"+rebateLV;\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function isNumber(oNum){\r\n");
      out.write("\r\n");
      out.write(" if(!oNum) return false; //line:160\r\n");
      out.write(" if(oNum.value==\"\") return false;\r\n");
      out.write(" var vv = oNum.value.replace(/[^\\d]/g,'');\r\n");
      out.write(" var strP=/^\\d+$/;\r\n");
      out.write("  if (!isNaN(oNum.value)) {\r\n");
      out.write("        return true;\r\n");
      out.write("    } else {\r\n");
      out.write("        alert(\"请输入数字\");\r\n");
      out.write("        oNum.value=vv;\r\n");
      out.write(" \t\toNum.focus();\r\n");
      out.write(" \t\treturn false; \r\n");
      out.write("    }\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<body>\r\n");
      out.write("<form id=\"form1\" name=\"form1\" method=\"post\" action=\"");
      out.print(request.getContextPath());
      out.write("/system/UpdateGradeCS.do\">\r\n");
      out.write("  <table cellpadding=\"2\" cellspacing=\"1\" border=\"0\" width=\"100%\" align=\"center\">\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td height=\"25\" colspan=\"7\" class=\"menutop\" style=\"text-align:center;\">会员等级参数设定</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("\t <tr align=\"center\">\r\n");
      out.write("      <td width=\"11%\" height=\"22\" class=\"tdcenter\"><strong>等级</strong></td>\r\n");
      out.write("      <td width=\"26%\" class=\"tdcenter\"><strong>经验值</strong></td>\r\n");
      out.write("      <td width=\"30%\" class=\"tdcenter\"><strong>反水利率</strong></td>\r\n");
      out.write("      <td width=\"33%\" class=\"tdcenter\"><strong>操作</strong></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("\t");
 int i =1; 
      out.write('\r');
      out.write('\n');
      out.write('	');
      //  c:forEach
      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
      _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f0.setParent(null);
      // /system/gradecs.jsp(59,1) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setVar("user");
      // /system/gradecs.jsp(59,1) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
      int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
        if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("\t\r\n");
            out.write("    <tr\talign=\"center\">\r\n");
            out.write("      <td width=\"11%\" height=\"22\" class=\"tdcenter\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.grade}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("      <td width=\"26%\" class=\"tdcenter\"><input name=\"gradeExp_");
            out.print(i);
            out.write("\" id=\"gradeExp_");
            out.print(i);
            out.write("\" onKeyUp=\"isNumber(this)\" type=\"text\" size=\"30\" value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.gradeExp}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\" /></td>\r\n");
            out.write("      <td width=\"30%\" class=\"tdcenter\"><input name=\"rebateLV_");
            out.print(i);
            out.write("\" id=\"rebateLV_");
            out.print(i);
            out.write("\" onKeyUp=\"isNumber(this)\" type=\"text\" size=\"30\" value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.rebateLV}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\" />\r\n");
            out.write("\t  <input name=\"grade\" id=\"grade\" type=\"hidden\" value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.grade}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\"></td>\r\n");
            out.write("      <td width=\"33%\" class=\"tdcenter\"><a style=\"cursor:pointer\" onClick=\"goto(");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.grade}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write(",'gradeExp_");
            out.print(i);
            out.write("','rebateLV_");
            out.print(i++);
            out.write("')\">修改</a></td>\r\n");
            out.write("    </tr>\r\n");
            out.write("\t");
            int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return;
        }
      } catch (Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_005fforEach_005f0.doFinally();
        _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
      }
      out.write("\r\n");
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

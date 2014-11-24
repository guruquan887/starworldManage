package org.apache.jsp.lottery;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class datamanage_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../yanzheng.jsp", out, false);
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write(" \r\n");

	String time = "";
	String time1 = "";
	try{
		if(request.getAttribute("time2")!=null){
			time= String.valueOf(request.getAttribute("time2"));
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	if(request.getParameter("time")!=null){
			time1=request.getParameter("time");
		}
	String msg="";
	if(request.getAttribute("msg")!=null){
		msg=(String)request.getAttribute("msg");
	}


      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>数据管理</title>\r\n");
      out.write("<link href=\"../css/user.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("<!--\r\n");
      out.write("body {\r\n");
      out.write("\tmargin-left: 0px;\r\n");
      out.write("\tmargin-top: 0px;\r\n");
      out.write("\tmargin-right: 0px;\r\n");
      out.write("}\r\n");
      out.write("-->\r\n");
      out.write("</style>\r\n");
      out.write("<link href=\"../css/AdminCss.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("</head>\r\n");
      out.write("<script language=\"javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/js.js\"></script>\r\n");
      out.write("<script language=\"javascript\" type=\"text/javascript\">\r\n");
      out.write("function keno8delete()\r\n");
      out.write("{\r\n");
      out.write("    var time=document.all.time.options[document.all.time.selectedIndex].value;\r\n");
      out.write("\tdocument.location.href=\"dataManage.do?action=deleteList&time=\"+time;\r\n");
      out.write("}\r\n");
      out.write("function beifen()\r\n");
      out.write("{\r\n");
      out.write("\tdocument.location.href=\"dataManage.do?action=countList\";\r\n");
      out.write("}\r\n");
      out.write("</script>  \r\n");
      out.write("<body>\r\n");
      out.write("  <table width=\"100%\" border=\"0\" cellpadding=\"2\" cellspacing=\"1\" class=\"border\">\r\n");
      out.write("    <tr>\r\n");
      out.write("          <td height=\"25\" colspan=\"9\" class=\"menutop\"><div style=\"width:90%; float:left; text-align:center; line-height:25px;\">数据管理列表</div>\r\n");
      out.write("      </td>\r\n");
      out.write("    </tr>\r\n");
      out.write("  </table>\r\n");
      out.write("  <form id=\"form1\" name=\"form1\" method=\"post\" action=\"dataManage.do?action=deleteRecord\">\r\n");
      out.write("<table width=\"100%\" border=\"0\" cellpadding=\"2\" cellspacing=\"1\" class=\"border\">  \r\n");
      out.write("</table>\r\n");
      out.write("  <table width=\"100%\" border=\"0\" cellpadding=\"2\" cellspacing=\"1\" class=\"border\">\r\n");
      out.write("    <tr class=\"tdcenter\">\r\n");
      out.write("      <td height=\"30\" align=\"center\"><strong>操作</strong></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr class=\"tdcenter\">\r\n");
      out.write("      <td align=\"center\">请选取删除时间范围 \r\n");
      out.write("        <select name=\"time\" id=\"time\" onchange=\"keno8delete()\">\r\n");
      out.write("\t\t<option value=\"30\">1个月之前</OPTION>    \r\n");
      out.write("\t\t<option value=\"60\">2个月之前</OPTION>  \r\n");
      out.write("\t\t<option value=\"90\">3个月之前</OPTION>  \r\n");
      out.write("        </select>\r\n");
      out.write("\t\t<input type=\"submit\" name=\"button\" value=\"执行删除操作\"/>\r\n");
      out.write("       </td>\r\n");
      out.write("     </tr>\r\n");
      out.write("\t <br/>\r\n");
      out.write("    <tr class=\"tdcenter\">\r\n");
      out.write("      <td align=\"center\">点击将进行数据库备份操作<input type=\"button\" name=\"button\" value=\"进行数据库备份\" onclick=\"beifen()\"/></td>\r\n");
      out.write("\t  </tr>\r\n");
      out.write("</table>\r\n");
      out.write(" <table width=\"100%\" border=\"0\" cellpadding=\"2\" cellspacing=\"1\" class=\"border\">\r\n");
      out.write("  <tr>\r\n");
      out.write("      <td  align=\"center\" width=\"60%\"><strong>备份文件名</strong></td>\r\n");
      out.write("      <td  align=\"center\" width=\"20%\"><strong>文件大小</strong></td>\r\n");
      out.write("      <td  align=\"center\" width=\"20%\"><strong>操作</strong></td>\r\n");
      out.write("  </tr>\r\n");
      //  c:forEach
      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
      _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f0.setParent(null);
      // /lottery/datamanage.jsp(89,0) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setVar("file");
      // /lottery/datamanage.jsp(89,0) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${files}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
      int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
        if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("<tr class=\"tdcenter\">\r\n");
            out.write("      <td  align=\"left\">&nbsp;&nbsp;<strong>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${file.fileName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</strong></td>\r\n");
            out.write("      <td  align=\"left\">&nbsp;&nbsp;<strong>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${file.fileSize}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("bytes</strong></td>\r\n");
            out.write("      <td  align=\"center\"><a href=\"");
            out.print(request.getContextPath());
            out.write("/lottery/dataManage.do?action=delete&filename=");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${file.encodeFileName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\">删除</a> <a href=\"");
            out.print(request.getContextPath());
            out.write("/download.do?action=download&filename=");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${file.encodeFileName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\">下载</a></td>\r\n");
            out.write("  </tr>\r\n");
            out.write(" \r\n");
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
      out.write(" </table>\r\n");
      out.write("</form>\r\n");
      out.write("</body>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var ttime=document.all.time;\r\n");
      out.write("var index2=\"");
      out.print(time1 );
      out.write("\";\r\n");
      out.write("for(var i=0; i<ttime.options.length; i++){\r\n");
      out.write("\t\t\tif(ttime.options[i].value==index2){\r\n");
      out.write("\t\t\t\tttime.options[i].selected=true;break;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("var msg=\"");
      out.print(msg );
      out.write("\";\r\n");
      out.write("if(msg!='')alert(msg);  \r\n");
      out.write("var time1 = '");
      out.print(time );
      out.write("';\r\n");
      out.write("if(time1!='')alert(\"查找 \"+time1+\" 之前keno8数据库的数据\");  \r\n");
      out.write("</script> \r\n");
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

package org.apache.jsp.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class notice_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fstep_005fend_005fbegin;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fstep_005fend_005fbegin = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.release();
    _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.release();
    _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fstep_005fend_005fbegin.release();
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
      out.write(" \r\n");

	int recordIndex=1;
	int pageIndex=1;
	int pageSize=10;
	int nullity=0;
	try{
		if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("pageIndex")!=null){
			pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
		}
		if(request.getParameter("nullity")!=null){
			nullity=Integer.parseInt(request.getParameter("nullity"));
		}
	}catch(Exception e){
		e.printStackTrace();
	}
	recordIndex=(pageIndex-1)*pageSize+1;
	String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}
	String termOne="";
	if(request.getParameter("termOne")!=null){
		termOne=request.getParameter("termOne");
		termOne = new String(termOne.getBytes("ISO_8859_1"),"UTF-8");
	}

      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>管理中心-弹出页面</title>\r\n");
      out.write("<link href=\"../css/main.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("</head>\r\n");
      out.write("<script language=\"javascript\" type=\"text/javascript\">\r\n");
      out.write("var msg=\"");
      out.print(msg );
      out.write("\";\r\n");
      out.write("if(msg!='')alert(msg);\r\n");
      out.write("var tone=encodeURI(\"");
      out.print(termOne );
      out.write("\");\r\n");
      out.write("\r\n");
      out.write("function checkAllBox(i){\r\n");
      out.write("\t\r\n");
      out.write("\t\tvar isChecked=(document.form2.checkAll.checked == true);\r\n");
      out.write("\t    var elements=document.form2.elements;\r\n");
      out.write("\t    var counter=elements.length;\r\n");
      out.write("\t    for(i=0;i<counter;i++){\r\n");
      out.write("\t\t\tvar element=elements[i];\r\n");
      out.write("\t\tif(element.type==\"checkbox\"){\r\n");
      out.write("\t\t\telement.checked=isChecked;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function jumppage(value)\r\n");
      out.write("   {\r\n");
      out.write("      window.location.href=\"");
      out.print(request.getContextPath());
      out.write("/web/GameMatchInfoList.do?action=noticeList&curPage=\"+value+\"&nullity=");
      out.print(nullity );
      out.write("&pageSize=");
      out.print(pageSize );
      out.write("&termOne=\"+tone;\r\n");
      out.write("   }\r\n");
      out.write("   \r\n");
      out.write("function changepage(pageNo){\r\n");
      out.write("\tdocument.location.href=\"");
      out.print(request.getContextPath());
      out.write("/web/GameMatchInfoList.do?action=noticeList&curPage=\"+pageNo+\"&termOne=\"+tone+\"&nullity=");
      out.print(nullity );
      out.write("&pageSize=");
      out.print(pageSize );
      out.write("\";\r\n");
      out.write("}\r\n");
      out.write("function undoCheck(){\r\n");
      out.write("\tvar flag10=0;\r\n");
      out.write("\tvar radio10=document.getElementsByName(\"checkbox\");\r\n");
      out.write("\tfor(var i=0;i<radio10.length;i++)\r\n");
      out.write(" \t{\r\n");
      out.write("    if(radio10.item(i).checked==true)\r\n");
      out.write("        {\r\n");
      out.write("        flag10=1;\r\n");
      out.write("           break;\r\n");
      out.write("        }\r\n");
      out.write(" }\r\n");
      out.write("  if(!flag10){\r\n");
      out.write("         alert(\"请选择撤销对象\");\r\n");
      out.write("         return false;\r\n");
      out.write("  }\r\n");
      out.write("   return true;\r\n");
      out.write("\t\t}\t\r\n");
      out.write("\r\n");
      out.write("function search(){\r\n");
      out.write("\tdocument.location.href=\"");
      out.print(request.getContextPath());
      out.write("/web/issueList.do?termOne=\"+encodeURI(document.all.termOne.value);\r\n");
      out.write("}\r\n");
      out.write("function tjSubmit(opId){\r\n");
      out.write("var flag10=0;\r\n");
      out.write("var radio10=document.getElementsByName(\"checkbox\");\r\n");
      out.write("   for(var i=0;i<radio10.length;i++)\r\n");
      out.write("   {\r\n");
      out.write("     if(radio10.item(i).checked==true)\r\n");
      out.write("        {\r\n");
      out.write("     flag10=1;\r\n");
      out.write("           break;\r\n");
      out.write("   \t\t}\r\n");
      out.write("   }\r\n");
      out.write("  if(!flag10){\r\n");
      out.write("         alert(\"请选择对象\");\r\n");
      out.write("         return false;\r\n");
      out.write("  \t\t\t}\r\n");
      out.write("else{\r\n");
      out.write("   if(opId==0){\r\n");
      out.write("      form2.action=\"");
      out.print(request.getContextPath());
      out.write("/web/issueDelete.do\";\r\n");
      out.write("\t  form2.submit();\r\n");
      out.write("   }\r\n");
      out.write("   else if(opId==1){\r\n");
      out.write("\t  form2.action=\"");
      out.print(request.getContextPath());
      out.write("/web/issueChangeState.do?action=pub\";\r\n");
      out.write("\t  form2.submit();  \r\n");
      out.write("   }\r\n");
      out.write("   else if(opId==2){\r\n");
      out.write("\t  form2.action=\"");
      out.print(request.getContextPath());
      out.write("/web/issueChangeState.do?action=unpub\";\r\n");
      out.write("\t  form2.submit();  \r\n");
      out.write("   }\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write("   }\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("<body>\r\n");
      out.write("\t<Div class=\"title\"><span>\r\n");
      out.write("\t  <form id=\"form1\" name=\"form1\" method=\"post\" action=\"");
      out.print(request.getContextPath());
      out.write("/web/issueList.do\">\r\n");
      out.write("        <label>\r\n");
      out.write("\t\t<select name=\"nullity\" id=\"nullity\" class=\"Select\" onchange=\"form1.submit()\" >\r\n");
      out.write("\t\t<option value=\"0\">正常</option>\r\n");
      out.write("\t\t<option value=\"1\">锁定</option>\r\n");
      out.write("        </select>\r\n");
      out.write("        <input type=\"text\" name=\"termOne\" id=\"termOne\" value=\"\" />\r\n");
      out.write("        <input name=\"Submit4\" type=\"button\" class=\"input\" value=\"搜索\" onClick=\"search()\"/>\r\n");
      out.write("        </label>\t  \r\n");
      out.write("\t\t");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t<label>\r\n");
      out.write("\t  <input name=\"Submit\" type=\"button\" class=\"input\" value=\"新增\" onclick=\"window.location.href='");
      out.print(request.getContextPath());
      out.write("/web/issueadd.jsp'\"/>\r\n");
      out.write("\t  </label>\r\n");
      out.write("\t  </form>\r\n");
      out.write("\t</span>弹出页面</Div>\r\n");
      out.write("\t   <form id=\"form2\" name=\"form2\" method=\"post\" action=\"\">\r\n");
      out.write("\t   <tr>\r\n");
      out.write("            <td colspan=\"24\" class=\"menutop\">\r\n");
      out.write("                <div align=\"left\">\r\n");
      out.write("                 <input type=\"button\" name=\"Submit3\" class=\"input\" value=\"删除\" onclick=\"tjSubmit(0)\" />\r\n");
      out.write("                  <input type=\"button\" name=\"Submit4\" class=\"input\" value=\"解冻\" onclick=\"tjSubmit(1)\" />\r\n");
      out.write("                  <input type=\"button\" name=\"Submit5\" class=\"input\" value=\"冻结\" onclick=\"tjSubmit(2)\" />\r\n");
      out.write("              </div>            </td>\r\n");
      out.write("         </tr>\r\n");
      out.write("\t\t<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_margin\">\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td width=\"60\" class=\"menutop\"><label>\r\n");
      out.write("              <input type=\"checkbox\" name=\"checkAll\" value=\"checkAll\" onClick=\"checkAllBox(0)\" />\r\n");
      out.write("            </label></td>\r\n");
      out.write("            <td width=\"60\" class=\"menutop\">标题</td>\r\n");
      out.write("            <td class=\"menutop\">窗口大小</td>\r\n");
      out.write("            <td class=\"menutop\">开始时间</td>\r\n");
      out.write("            <td class=\"menutop\">结束时间</td>\r\n");
      out.write("            <td class=\"menutop\">禁用状态</td>\r\n");
      out.write("          </tr>\r\n");
      out.write("\t\t  ");
      //  c:forEach
      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
      _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f1.setParent(null);
      // /web/notice.jsp(156,4) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f1.setVar("issue");
      // /web/notice.jsp(156,4) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f1.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${noticeList}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
      int[] _jspx_push_body_count_c_005fforEach_005f1 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f1 = _jspx_th_c_005fforEach_005f1.doStartTag();
        if (_jspx_eval_c_005fforEach_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("          <tr onmouseOver=\"this.className='trover'\" onmouseOut=\"this.className='trout'\">\r\n");
            out.write("            <td class=\"tdcenter\"><label>\r\n");
            out.write("              <input type=\"checkbox\" name=\"checkbox\" value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${issue.noticeID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\" />\r\n");
            out.write("            </label></td>\r\n");
            out.write("            <td onclick=\"window.location.href='");
            out.print(request.getContextPath());
            out.write("/web/issuePreUpdate.do?noticeID=");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${issue.noticeID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("'\" class=\"tdcenter\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${issue.subject}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("            <td onclick=\"window.location.href='");
            out.print(request.getContextPath());
            out.write("/web/issuePreUpdate.do?noticeID=");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${issue.noticeID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("'\" class=\"tdcenter\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${issue.width}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('X');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${issue.height}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("            <td onclick=\"window.location.href='");
            out.print(request.getContextPath());
            out.write("/web/issuePreUpdate.do?noticeID=");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${issue.noticeID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("'\" class=\"tdcenter\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${issue.startDate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("            <td onclick=\"window.location.href='");
            out.print(request.getContextPath());
            out.write("/web/issuePreUpdate.do?noticeID=");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${issue.noticeID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("'\" class=\"tdcenter\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${issue.overDate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("            <td onclick=\"window.location.href='");
            out.print(request.getContextPath());
            out.write("/web/issuePreUpdate.do?noticeID=");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${issue.noticeID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("'\" class=\"tdcenter\">");
            if (_jspx_meth_c_005fif_005f0(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
              return;
            if (_jspx_meth_c_005fif_005f1(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
              return;
            out.write("</td>\r\n");
            out.write("            </tr>\r\n");
            out.write("\t\t  ");
            int evalDoAfterBody = _jspx_th_c_005fforEach_005f1.doAfterBody();
            if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
              break;
          } while (true);
        }
        if (_jspx_th_c_005fforEach_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
          return;
        }
      } catch (Throwable _jspx_exception) {
        while (_jspx_push_body_count_c_005fforEach_005f1[0]-- > 0)
          out = _jspx_page_context.popBody();
        _jspx_th_c_005fforEach_005f1.doCatch(_jspx_exception);
      } finally {
        _jspx_th_c_005fforEach_005f1.doFinally();
        _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f1);
      }
      out.write("\r\n");
      out.write("          <tr>\r\n");
      out.write("                <td class=\"tdcenter\" colspan=\"27\" align=\"center\" valign=\"middle\" bgcolor=\"#FFFFFF\">\r\n");
      out.write("\t\t\t\t<font color=\"red\">");
      if (_jspx_meth_c_005fout_005f0(_jspx_page_context))
        return;
      out.write("</font></td>\r\n");
      out.write("       </tr>\r\n");
      out.write("            <td colspan=\"12\" class=\"tdright_new\">总记录:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalRecord}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write('条');
      out.write('/');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPage}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("页 \r\n");
      out.write("          ");
      if (_jspx_meth_c_005fif_005f2(_jspx_page_context))
        return;
      out.write("</td>\r\n");
      out.write("          </tr>\r\n");
      out.write("        </table>\r\n");
      out.write("</form>\r\n");
      out.write("</body>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var tcalc=document.getElementById(\"nullity\");\r\n");
      out.write("var index=\"");
      out.print(nullity );
      out.write("\";\r\n");
      out.write("for(var i=0; i<tcalc.options.length; i++){\r\n");
      out.write("\t\t\tif(tcalc.options[i].value==index){\r\n");
      out.write("\t\t\t\ttcalc.options[i].selected=true;break;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("var ttype3=document.all.termOne;\r\n");
      out.write("var index2=\"");
      out.print(termOne);
      out.write("\";\r\n");
      out.write("for(var i=0; i<ttype3.length; i++){\r\n");
      out.write("\tif(ttype3.value==index2){\r\n");
      out.write("\t\tttype3.selected=true;break;\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
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
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /web/notice.jsp(130,2) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("r1");
    // /web/notice.jsp(130,2) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${class}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems_005fnobody.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    // /web/notice.jsp(165,145) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${issue.nullity==0}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /web/notice.jsp(165,145) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setVar("true");
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('否');
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

  private boolean _jspx_meth_c_005fif_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    // /web/notice.jsp(165,197) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${issue.nullity==1}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /web/notice.jsp(165,197) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setVar("true");
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write('是');
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

  private boolean _jspx_meth_c_005fout_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f0.setParent(null);
    // /web/notice.jsp(170,22) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${returnInfo}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f0 = _jspx_th_c_005fout_005f0.doStartTag();
    if (_jspx_th_c_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
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
    // /web/notice.jsp(173,10) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPage>=0}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /web/notice.jsp(173,10) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f2.setVar("true");
    int _jspx_eval_c_005fif_005f2 = _jspx_th_c_005fif_005f2.doStartTag();
    if (_jspx_eval_c_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write(" \r\n");
        out.write("　<a onClick=\"changepage(1)\" style=\"cursor:hand\">首页</a> \r\n");
        out.write("\t<a onClick=\"changepage(");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.curPage-1}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write(")\" style=\"cursor:hand\">上页</a> \r\n");
        out.write("\t<select name=\"select\" onChange=\"jumppage(this.value);\">\r\n");
        out.write("          ");
        if (_jspx_meth_c_005fforEach_005f2(_jspx_th_c_005fif_005f2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("        </select> &nbsp;<a onClick=\"changepage(");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.curPage+1}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write(")\" style=\"cursor:hand\">下页</a> \r\n");
        out.write("        <a onClick=\"changepage(");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPage}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write(")\" style=\"cursor:hand\">末页</a>\t\t");
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

  private boolean _jspx_meth_c_005fforEach_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f2 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fstep_005fend_005fbegin.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f2);
    // /web/notice.jsp(177,10) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f2.setVar("i");
    // /web/notice.jsp(177,10) name = begin type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f2.setBegin(1);
    // /web/notice.jsp(177,10) name = end type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f2.setEnd(((java.lang.Integer) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPage }", java.lang.Integer.class, (PageContext)_jspx_page_context, null, false)).intValue());
    // /web/notice.jsp(177,10) name = step type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f2.setStep(1);
    int[] _jspx_push_body_count_c_005fforEach_005f2 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f2 = _jspx_th_c_005fforEach_005f2.doStartTag();
      if (_jspx_eval_c_005fforEach_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write(" <option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\" \r\n");
          out.write("              ");
          if (_jspx_meth_c_005fif_005f3(_jspx_th_c_005fforEach_005f2, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
            return true;
          out.write("\r\n");
          out.write("            >第");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("页\r\n");
          out.write("            </option>\r\n");
          out.write("          ");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f2.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f2[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f2.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f2.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fstep_005fend_005fbegin.reuse(_jspx_th_c_005fforEach_005f2);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f2, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f3 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f2);
    // /web/notice.jsp(178,14) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f3.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i==page.curPage}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /web/notice.jsp(178,14) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f3.setVar("true");
    int _jspx_eval_c_005fif_005f3 = _jspx_th_c_005fif_005f3.doStartTag();
    if (_jspx_eval_c_005fif_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("selected=\"selected\"");
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

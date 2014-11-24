package org.apache.jsp.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class display_005fin_005fimage_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("\r\n");

   String no = "";
   if(request.getParameter("no")!=null){
   		no = request.getParameter("no");
   }
   String msg ="";
   if(request.getAttribute("msg")!=null){
   		msg = request.getAttribute("msg").toString();
		//System.out.println("--------->>"+msg);
   }

      out.write("\r\n");
      out.write("<div style=\"text-align:center; width:50px; height:20px; font-size:14px; font-weight:bold; z-index:99999; display:block; background:#FFF; border:solid 1px #ccc; position:absolute; left:45%;margin:0px; padding:0px;\" id=\"showDiv_");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" onclick=\"closeDiv('showDiv_");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("')\">\r\n");
      out.write("<ul style=\"margin:0px; padding:0px;\">\r\n");
      out.write("\r\n");
      out.write("<li id=\"fm_");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" style=\" text-align:left; font-size:12px; color:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${css}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write(";font-weight:bold;list-style:none;padding-right:5px;padding-left:5px;padding-top:3px; padding-bottom:3px;\"><iframe src=\"\" scrolling=\"no\" frameborder=\"0\" id=\"FM_Id_");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" onLoad=\"{TuneHeight(false,'fm_");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("','FM_Id_");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("');}\" style=\"top:0px;left:0px;position:absolute;visibility:inherit;z-index:-1;\"></iframe>\r\n");
      out.write("<img src=\"");
      out.print(request.getContextPath());
      out.write("/incoming/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto.gamephoto2}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" style=\"vertical-align:middle\"/>\r\n");
      out.write("</li>\r\n");
      out.write("</ul>\r\n");
      out.write("</div> \r\n");
      out.write("\t  ");
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

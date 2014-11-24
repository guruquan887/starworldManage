package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import com.doowal.hk798.login.AuthorityMenuViewDTO;
import java.util.List;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html; charset=UTF-8");
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
if (session.getAttribute("USER") == null){
	out.print("<script>alert('对不起，您还没有登录本系统或者您已经超过登录时间!');  window.target='_parent';window.location='/login.jsp';</script>");
	return;

}

      out.write("\r\n");
      out.write("<!DOCTYPE HTML Public \"-//W3C//DTD HTML 4.0 Transitional//EN\">\r\n");
      out.write("<HTML xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<HEAD>\r\n");
      out.write("<TITLE>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${title}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</TITLE>\r\n");
      out.write("<META http-equiv=Content-Type content=\"text/html; charset=utf-8\">\r\n");
      out.write("<LINK href=\"css/style.css\" type=text/css rel=stylesheet>\r\n");
      out.write("<link href=\"css/admin.css\" type=\"text/css\" rel=\"stylesheet\">\r\n");
      out.write("<script language=\"javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/admin.js\"></script>\r\n");
      out.write("<SCRIPT>\r\n");
      out.write("var status = 1;\r\n");
      out.write("var Menus = new DvMenuCls;\r\n");
      out.write("document.onclick=Menus.Clear;\r\n");
      out.write("function switchSysBar(){\r\n");
      out.write("     if (1 == window.status){\r\n");
      out.write("\t\t  window.status = 0;\r\n");
      out.write("          switchPoint.innerHTML = '<img src=\"images/left.gif\">';\r\n");
      out.write("          document.all(\"frmTitle\").style.display=\"none\"\r\n");
      out.write("     }\r\n");
      out.write("     else{\r\n");
      out.write("\t\t  window.status = 1;\r\n");
      out.write("          switchPoint.innerHTML = '<img src=\"images/right.gif\">';\r\n");
      out.write("          document.all(\"frmTitle\").style.display=\"\"\r\n");
      out.write("     }\r\n");
      out.write("}\r\n");
      out.write("</SCRIPT>\r\n");
      out.write("<META content=\"MSHTML 6.00.2900.5726\" name=GENERATOR></HEAD>\r\n");
      out.write("<BODY style=\"MARGIN: 0px\">\r\n");
      out.write("<!--导航部分-->\r\n");
      out.write("<DIV class=top_table>\r\n");
      out.write("<DIV class=top_table_leftbg>\r\n");
      out.write("<DIV class=system_logo><IMG src=\"images/logo_up.gif\"></DIV>\r\n");
      out.write("<DIV class=menu>\r\n");
      out.write("<UL>\r\n");
      out.write("  ");


List<AuthorityMenuViewDTO> topmenu=(List)session.getAttribute("topmenu");
for (AuthorityMenuViewDTO topmenuitem: topmenu) {
	int topmenuid=topmenuitem.getId();
	out.println("<LI id=menu_"+topmenuid+" onmouseover=Menus.Show(this,0) onclick=getleftbar(this);>");
	out.println("<A href=\""+topmenuitem.getMenuPath()+"\" target=frmright>"+topmenuitem.getMenuName()+"</A>");
	out.println("<DIV class=menu_childs onmouseout=Menus.Hide(0);>");
	out.println("<UL>");
	List<AuthorityMenuViewDTO> submenu =(List)session.getAttribute("submenu"+topmenuitem.getMenuid());
	for (AuthorityMenuViewDTO submenuitem: submenu) {
		out.println("<LI><A href=\""+submenuitem.getMenuPath()+"\"  target=frmright>"+submenuitem.getMenuName()+"</A></LI>");
		if(submenuitem.getInsertdiv()){
			out.println("<DIV class=menuhr></DIV>");
		}
	}
	out.println("</UL>");
	out.println("</div>");
	out.println("<DIV class=menu_div><IMG style=\"VERTICAL-ALIGN: bottom\" src=\"images/menu01_right.gif\"></DIV>");
	out.println("</LI>");
}

      out.write("\r\n");
      out.write("    <!--<LI><A href=\"logout.jsp\" target=frmright>登出管理</A>-->\r\n");
      out.write("\t<!--<DIV class=menu_div><IMG style=\"VERTICAL-ALIGN: bottom\" src=\"images/menu01_right.gif\"></DIV>-->\r\n");
      out.write("  </UL>\r\n");
      out.write("</DIV></DIV></DIV>\r\n");
      out.write("<DIV style=\"BACKGROUND: #337abb; HEIGHT: 24px\"></DIV>\r\n");
      out.write("<!--导航部分结束-->\r\n");
      out.write("<TABLE style=\"BACKGROUND: #337abb\" height=\"92%\" cellSpacing=0 cellPadding=0 \r\n");
      out.write("width=\"100%\" border=0>\r\n");
      out.write("  <TBODY>\r\n");
      out.write("  <TR>\r\n");
      out.write("    <TD class=main_left id=frmTitle vAlign=top align=middle name=\"fmTitle\">\r\n");
      out.write("      <TABLE class=main_left_top cellSpacing=0 cellPadding=0 width=\"100%\" \r\n");
      out.write("      border=0>\r\n");
      out.write("        <TBODY>\r\n");
      out.write("        <TR height=32>\r\n");
      out.write("          <TD vAlign=top></TD>\r\n");
      out.write("          <TD class=main_left_title id=leftmenu_title>常用快捷功能</TD>\r\n");
      out.write("          <TD vAlign=top align=right></TD></TR></TBODY></TABLE><IFRAME \r\n");
      out.write("      class=left_iframe id=frmleft name=frmleft \r\n");
      out.write("      src=\"left.jsp\" frameBorder=0  \r\n");
      out.write("      allowTransparency></IFRAME>\r\n");
      out.write("      <TABLE cellSpacing=0 cellPadding=0 width=\"100%\" border=0>\r\n");
      out.write("        <TBODY>\r\n");
      out.write("        <TR height=32>\r\n");
      out.write("          <TD vAlign=top></TD>\r\n");
      out.write("          <TD vAlign=bottom align=middle></TD>\r\n");
      out.write("          <TD vAlign=top align=right></TD></TR></TBODY></TABLE></TD>\r\n");
      out.write("    <TD style=\"WIDTH: 10px\" bgColor=#337abb>\r\n");
      out.write("      <TABLE height=\"100%\" cellSpacing=0 cellPadding=0 border=0>\r\n");
      out.write("        <TBODY>\r\n");
      out.write("        <TR>\r\n");
      out.write("          <TD style=\"HEIGHT: 100%\" onclick=switchSysBar()><SPAN class=navPoint \r\n");
      out.write("            id=switchPoint title=关闭/打开左栏><IMG \r\n");
      out.write("            src=\"images/right.gif\"></SPAN> </TD></TR></TBODY></TABLE></TD>\r\n");
      out.write("    <TD vAlign=top width=\"100%\" bgColor=#337abb>\r\n");
      out.write("      <TABLE cellSpacing=0 cellPadding=0 width=\"100%\" bgColor=#c4d8ed \r\n");
      out.write("        border=0><TBODY>\r\n");
      out.write("        <TR height=32>\r\n");
      out.write("          <TD vAlign=top width=10 background=images/bg2.gif><IMG \r\n");
      out.write("            alt=\"\" src=\"images/teble_top_left.gif\"></TD>\r\n");
      out.write("          <TD width=28 background=images//bg2.gif><IMG alt=\"\" \r\n");
      out.write("            src=\"images/arrow.gif\" align=absMiddle></TD>\r\n");
      out.write("          <TD background=images/bg2.gif><SPAN \r\n");
      out.write("            style=\"FLOAT: left\">最新公告：</SPAN><SPAN style=\"FONT-WEIGHT: bold; FLOAT: left; WIDTH: 300px; COLOR: #c00\"><marquee scrollamount=\"2\">\r\n");
      out.write("          ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${title}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\r\n");
      out.write("          </marquee></SPAN></TD>\r\n");
      out.write("          <TD style=\"COLOR: #135294; TEXT-ALIGN: right\" \r\n");
      out.write("          background=images/bg2.gif>  <a href=\"#\" onClick=\"javascript:history.go(-1)\">返回</a> </TD>\r\n");
      out.write("          <TD vAlign=top align=right width=28 \r\n");
      out.write("          background=images/bg2.gif><IMG alt=\"\" \r\n");
      out.write("            src=\"images//teble_top_right.gif\"></TD>\r\n");
      out.write("          <TD align=right width=16 bgColor=#337abb></TD></TR></TBODY></TABLE>\r\n");
      out.write("\t\t  <IFRAME class=main_iframe id=frmright name=frmright src=\"");
      out.print(request.getContextPath() );
      out.write("/system/dateCount.do?method=usercount\" frameBorder=0 scrolling=yes></IFRAME>\r\n");
      out.write("      <TABLE style=\"BACKGROUND: #c4d8ed\" cellSpacing=0 cellPadding=0 \r\n");
      out.write("      width=\"100%\" border=0>\r\n");
      out.write("        <TBODY>\r\n");
      out.write("        <TR>\r\n");
      out.write("          <TD><IMG height=6 alt=\"\" \r\n");
      out.write("            src=\"images/teble_bottom_left.gif\" width=5></TD>\r\n");
      out.write("          <TD align=right><IMG height=6 alt=\"\" \r\n");
      out.write("            src=\"images/teble_bottom_right.gif\" width=5></TD>\r\n");
      out.write("          <TD align=right width=16 \r\n");
      out.write("  bgColor=#337abb></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE>\r\n");
      out.write("</BODY></HTML>\r\n");
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

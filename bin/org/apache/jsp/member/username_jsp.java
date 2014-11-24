package org.apache.jsp.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class username_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

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
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fstep_005fend_005fbegin = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
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
      out.write("\r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../yanzheng.jsp", out, false);
      out.write('\r');
      out.write('\n');

	int recordIndex=1;
	int pageIndex=1;
	int pageSize=10;
	int type = 0;
	if(request.getParameter("type")!=null){
	    type = Integer.parseInt(request.getParameter("type"));
	}
	String orderby="RegisterDate";
	if(request.getParameter("orderby")!=null){
		orderby=request.getParameter("orderby");
	}
	String username = "";
	if(request.getSession().getAttribute("username")!=null){
		username = (String)request.getSession().getAttribute("username");
	}
	try{
		if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("pageIndex")!=null){
			pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
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
	String selectOne="";
	if(request.getParameter("selectOne")!=null){
		selectOne=request.getParameter("selectOne");
	}
	String spreaderID="";
	if(request.getParameter("spreaderID")!=null){
		spreaderID=request.getParameter("spreaderID");
	}
	int type = 0;
if(request.getParameter("type")!=null){
	    type = Integer.parseInt(request.getParameter("type"));
	}

      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<script language=\"javascript\" type=\"text/javascript\">\r\n");
      out.write("var msg=\"");
      out.print(msg );
      out.write("\";\r\n");
      out.write("if(msg!='')alert(msg);\r\n");
      out.write("var tone=encodeURI(\"");
      out.print(termOne );
      out.write("\");\r\n");
      out.write("function jumppage(value)\r\n");
      out.write("   {\r\n");
      out.write("      window.location.href=\"");
      out.print(request.getContextPath());
      out.write("/member/gameuserList.do?action=gameUserList&curPage=\" +value+\"&pageSize=");
      out.print(pageSize );
      out.write("&orderby=");
      out.print(orderby );
      out.write("&termOne=");
      out.print(termOne);
      out.write("&selectOne=");
      out.print(selectOne );
      out.write("&spreaderID=");
      out.print(spreaderID);
      out.write("&type=");
      out.print(type);
      out.write("\";\r\n");
      out.write("   }\r\n");
      out.write("function deleteUser(gameUserId)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tdocument.location.href=\"gameuserDelete.do?gameUserID=\"+gameUserId+\"&type=1\";\r\n");
      out.write("\t}\r\n");
      out.write("function changepage(pageNo){\r\n");
      out.write("\tdocument.location.href=\"");
      out.print(request.getContextPath());
      out.write("/member/gameuserList.do?action=gameUserList&curPage=\"+pageNo+\"&termOne=\"+tone+\"&selectOne=");
      out.print(selectOne );
      out.write("&pageSize=");
      out.print(pageSize );
      out.write("\"+\"&orderby=");
      out.print(orderby );
      out.write("&spreaderID=");
      out.print(spreaderID);
      out.write("&type=");
      out.print(type);
      out.write("\";\r\n");
      out.write("}\r\n");
      out.write("function search(){\r\n");
      out.write("\tdocument.location.href=\"");
      out.print(request.getContextPath());
      out.write("/member/gameuserList.do?action=gameUserList&termOne=\"+encodeURI(document.all.termOne.value)\r\n");
      out.write("\t\t+\"&selectOne=\"+document.all.selectOne.options[document.all.selectOne.selectedIndex].value\r\n");
      out.write("\t\t+\"&orderby=\"+document.all.orderby.options[document.all.orderby.selectedIndex].value\r\n");
      out.write("\t\t+\"&spreaderID=");
      out.print(spreaderID);
      out.write("&type=");
      out.print(type);
      out.write("\";\r\n");
      out.write("}\r\n");
      out.write("function ejiaA1(o,a,b,c,d){\r\n");
      out.write("var t=document.getElementById(o).getElementsByTagName(\"tr\");\r\n");
      out.write("for(var i=0;i<t.length;i++){\r\n");
      out.write("t[i].style.backgroundColor=(t[i].sectionRowIndex%2==0)?a:b;\r\n");
      out.write("t[i].onclick=function(){\r\n");
      out.write("if(this.x!=\"1\"){\r\n");
      out.write("this.x=\"1\";\r\n");
      out.write("this.style.backgroundColor=d;\r\n");
      out.write("}else{\r\n");
      out.write("this.x=\"0\";\r\n");
      out.write("this.style.backgroundColor=(this.sectionRowIndex%2==0)?a:b;\r\n");
      out.write("}\r\n");
      out.write("}\r\n");
      out.write("t[i].onmouseover=function(){\r\n");
      out.write("if(this.x!=\"1\")this.style.backgroundColor=c;\r\n");
      out.write("}\r\n");
      out.write("t[i].onmouseout=function(){\r\n");
      out.write("if(this.x!=\"1\")this.style.backgroundColor=(this.sectionRowIndex%2==0)?a:b;\r\n");
      out.write("}\r\n");
      out.write("}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(" function getAll()\r\n");
      out.write("{\r\n");
      out.write("\tvar elements=document.form2.checkbox;\r\n");
      out.write("\tvar length=elements.length;\r\n");
      out.write("\tfor(var i=0;i<length;i++)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tvar element=elements[i];\r\n");
      out.write("\t\telement.checked=true;\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
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
      out.write("function delCheck(){\r\n");
      out.write("\t\tvar flag10=0;\r\n");
      out.write("         var radio10=document.getElementsByName(\"checkbox\");\r\n");
      out.write("         for(var i=0;i<radio10.length;i++)\r\n");
      out.write("   {\r\n");
      out.write("     if(radio10.item(i).checked==true)\r\n");
      out.write("        {\r\n");
      out.write("     flag10=1;\r\n");
      out.write("           break;\r\n");
      out.write("   }\r\n");
      out.write(" }\r\n");
      out.write("  if(!flag10){\r\n");
      out.write("         alert(\"请选择删除对象\");\r\n");
      out.write("         return false;\r\n");
      out.write("  }\r\n");
      out.write("   return true;\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>无标题文档</title>\r\n");
      out.write("<link href=\"");
      out.print(request.getContextPath());
      out.write("/css/main.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("</head>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("<!--\r\n");
      out.write("a:link { text-decoration: none}\r\n");
      out.write("a:active { text-decoration: none }\r\n");
      out.write("a:visited { text-decoration: none }\r\n");
      out.write("-->\r\n");
      out.write("</style>\r\n");
      out.write("<body>\r\n");
      out.write("\t<Div class=\"title\"><span>  \r\n");
      out.write("\t  <form id=\"form1\" name=\"form1\" method=\"post\" action=\"");
      out.print(request.getContextPath());
      out.write("/member/gameuserList.do?action=gameUserList\">\r\n");
      out.write("\t    <label>\r\n");
      out.write("\t    <input name=\"termOne\" type=\"text\" class=\"input_width2\" id=\"termOne\"/>\r\n");
      out.write("\t    <select name=\"selectOne\" class=\"input_width1\" id=\"selectOne\">\r\n");
      out.write("\t      <option value=\"accounts\">用户名</option>\r\n");
      out.write("\t      <option value=\"gameID\">游戏ID</option>\r\n");
      out.write("\t      <option value=\"tyAccounts\">停用账户</option>\r\n");
      out.write("\t\t  <option value=\"xxAccounts\">推广用户</option>\r\n");
      out.write("\t\t  <option value=\"interType\">内部账号</option>\r\n");
      out.write("        </select>\r\n");
      out.write("\t\t<select name=\"orderby\" class=\"input_width1\" id=\"orderby\">\r\n");
      out.write("          <option value=\"registerDate\">注册时间</option>\r\n");
      out.write("          <option value=\"totalScore\">银子</option>\r\n");
      out.write("          <option value=\"onLine\">在线</option>\r\n");
      out.write("        </select>\r\n");
      out.write("\t    <input name=\"Submit2\" type=\"button\" class=\"input\" value=\"搜索\" onClick=\"search()\"/>\r\n");
      out.write("\t    </label>\r\n");
      out.write("\t    ");
      out.write("\r\n");
      out.write("      </form>\r\n");
      out.write("\t</span>会员</Div>\r\n");
      out.write("\t<form id=\"form2\" name=\"form2\" method=\"post\" onsubmit=\"return delCheck()\" action=\"");
      out.print(request.getContextPath());
      out.write("/member/gameuserList.do?action=delAll\">\r\n");
      out.write("\t\t<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_margin\">\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td width=\"30\" class=\"menutop\"><input type=\"checkbox\" name=\"checkAll\" value=\"checkAll\" onClick=\"checkAllBox(0)\" /></td>\r\n");
      out.write("            <td width=\"30\" class=\"menutop\">序号</td>\r\n");
      out.write("            <td class=\"menutop\">ID</td>\r\n");
      out.write("            <td class=\"menutop\">用户名</td>\r\n");
      out.write("            <td class=\"menutop\">昵称</td>\r\n");
      out.write("            <td class=\"menutop\">真实姓名</td>\r\n");
      out.write("\t\t\t<td class=\"menutop\">号码</td>\r\n");
      out.write("            <td class=\"menutop\">银子</td>\r\n");
      out.write("            <td class=\"menutop\">下线</td>\r\n");
      out.write("            <td class=\"menutop\">税收</td>\r\n");
      out.write("            <td class=\"menutop\">胜率</td>\r\n");
      out.write("            <td class=\"menutop\">在线时间</td>\r\n");
      out.write("            <td class=\"menutop\">游戏时间</td>\r\n");
      out.write("            <td class=\"menutop\">级别</td>\r\n");
      out.write("            <td class=\"menutop\">积分</td>\r\n");
      out.write("            <td class=\"menutop\">注册日期</td>\r\n");
      out.write("            <td class=\"menutop\">登录IP</td>\r\n");
      out.write("            <td class=\"menutop\">状态</td>\r\n");
      out.write("            <td class=\"menutop\">账号状态</td>\r\n");
      out.write("            <td class=\"menutop\">操作</td>\r\n");
      out.write("          </tr>\r\n");
      out.write("\t\t  ");
      //  c:forEach
      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
      _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f0.setParent(null);
      // /member/username.jsp(197,4) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setVar("user");
      // /member/username.jsp(197,4) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userlist}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
      int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
        if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("          <tr onmouseOver=\"this.className='trover'\" onmouseOut=\"this.className='trout'\">\r\n");
            out.write("            <td class=\"tdcenter\"><input name=\"type\" id=\"type\" type=\"hidden\" value=\"");
            out.print(type);
            out.write("\" /><input type=\"checkbox\" id=\"checkbox\" value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.userID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\" name=\"checkbox\" /></td>\r\n");
            out.write("            <td class=\"tdcenter\">");
            out.print(recordIndex++);
            out.write("</td>\r\n");
            out.write("            <td class=\"tdcenter\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.gameID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("            <td class=\"tdcenter\"><a href=\"");
            out.print(request.getContextPath());
            out.write("/member/gameuserList.do?action=userLogs&userID=");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.userID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('"');
            out.write('>');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.accounts}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</a></td>\r\n");
            out.write("            <td class=\"tdcenter\">&nbsp;");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.regAccounts}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("            <td class=\"tdcenter\">&nbsp;");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.realName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("\t\t\t<td class=\"tdcenter\">&nbsp;");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.telphone}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("            <td class=\"tdcenter\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.totalScore}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("            <td class=\"tdcenter\"><a href=\"");
            out.print(request.getContextPath());
            out.write("/member/gameuserList.do?action=gameUserList&spreaderID=");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.userID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('"');
            out.write('>');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.number}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</a></td>\r\n");
            out.write("            <td class=\"tdcenter\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.tax}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("%</td>\r\n");
            out.write("            <td class=\"tdcenter\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.winRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("%</td>\r\n");
            out.write("            <td class=\"tdcenter\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.onLineTimeCount}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("            <td class=\"tdcenter\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.playTimeCount}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("            <td class=\"tdcenter\">");
            if (_jspx_meth_c_005fif_005f0(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
              return;
            if (_jspx_meth_c_005fif_005f1(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
              return;
            if (_jspx_meth_c_005fif_005f2(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
              return;
            if (_jspx_meth_c_005fif_005f3(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
              return;
            if (_jspx_meth_c_005fif_005f4(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
              return;
            out.write("</td>\r\n");
            out.write("            <td class=\"tdcenter\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.jifen}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("            <td class=\"tdcenter\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.registerDate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("            <td class=\"tdcenter\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.lastLogonIP}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("            <td class=\"tdcenter\"><span class=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.bgcss}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('"');
            out.write('>');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.onLineName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</span></td>\r\n");
            out.write("            <td class=\"tdcenter\">");
            //  c:if
            org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f5 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
            _jspx_th_c_005fif_005f5.setPageContext(_jspx_page_context);
            _jspx_th_c_005fif_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
            // /member/username.jsp(217,33) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
            _jspx_th_c_005fif_005f5.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.zhState==0}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
            // /member/username.jsp(217,33) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
            _jspx_th_c_005fif_005f5.setVar("true");
            int _jspx_eval_c_005fif_005f5 = _jspx_th_c_005fif_005f5.doStartTag();
            if (_jspx_eval_c_005fif_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              do {
                out.write("\r\n");
                out.write("              <a href=\"");
                out.print(request.getContextPath());
                out.write("/member/gameuserList.do?action=zhFrozen&userState=");
                out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.zhState}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                out.write("&userID=");
                out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.userID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                out.write("&curPage=");
                out.print(pageIndex);
                out.write("&type=0\"><font color=\"blue\">");
                out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.zhStateName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                out.write("</font></a>\r\n");
                out.write("            ");
                int evalDoAfterBody = _jspx_th_c_005fif_005f5.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
            }
            if (_jspx_th_c_005fif_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f5);
              return;
            }
            _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f5);
            //  c:if
            org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f6 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
            _jspx_th_c_005fif_005f6.setPageContext(_jspx_page_context);
            _jspx_th_c_005fif_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
            // /member/username.jsp(219,19) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
            _jspx_th_c_005fif_005f6.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.zhState==1}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
            // /member/username.jsp(219,19) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
            _jspx_th_c_005fif_005f6.setVar("true");
            int _jspx_eval_c_005fif_005f6 = _jspx_th_c_005fif_005f6.doStartTag();
            if (_jspx_eval_c_005fif_005f6 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              do {
                out.write("\r\n");
                out.write("              <a href=\"");
                out.print(request.getContextPath());
                out.write("/member/gameuserList.do?action=zhFrozen&userState=");
                out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.zhState}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                out.write("&userID=");
                out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.userID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                out.write("&curPage=");
                out.print(pageIndex);
                out.write("&type=0\"><font color=\"red\">");
                out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.zhStateName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                out.write("</font></a>\r\n");
                out.write("            ");
                int evalDoAfterBody = _jspx_th_c_005fif_005f6.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
            }
            if (_jspx_th_c_005fif_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f6);
              return;
            }
            _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f6);
            out.write("</td>\r\n");
            out.write("            <td class=\"tdcenter\">");
            out.write(" <a href=\"");
            out.print(request.getContextPath());
            out.write("/member/gameuserList.do?action=preUpdate&userID=");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.userID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("&curPage=");
            out.print(pageIndex);
            out.write("&selectOne=");
            out.print(selectOne);
            out.write("&type=2\">详情</a> / <a href=\"");
            out.print(request.getContextPath());
            out.write("/member/gameuserList.do?action=MemberPreCun&userID=");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.userID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\">存入</a> / <a href=\"");
            out.print(request.getContextPath());
            out.write("/member/gameuserList.do?action=MemberPreQu&userID=");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.userID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\">取出</a> ");
            //  c:if
            org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f7 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
            _jspx_th_c_005fif_005f7.setPageContext(_jspx_page_context);
            _jspx_th_c_005fif_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
            // /member/username.jsp(222,648) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
            _jspx_th_c_005fif_005f7.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roleId==1}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
            // /member/username.jsp(222,648) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
            _jspx_th_c_005fif_005f7.setVar("true");
            int _jspx_eval_c_005fif_005f7 = _jspx_th_c_005fif_005f7.doStartTag();
            if (_jspx_eval_c_005fif_005f7 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              do {
                out.write("/ <a href=\"");
                out.print(request.getContextPath());
                out.write("/member/gameuserList.do?action=deleteUser&userID=");
                out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.userID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                out.write("&curPage=");
                out.print(pageIndex);
                out.write("&selectOne=");
                out.print(selectOne);
                out.write("&type=2\">删除</a>");
                int evalDoAfterBody = _jspx_th_c_005fif_005f7.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
            }
            if (_jspx_th_c_005fif_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f7);
              return;
            }
            _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f7);
            out.write(' ');
            out.write('/');
            out.write(' ');
            //  c:if
            org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f8 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
            _jspx_th_c_005fif_005f8.setPageContext(_jspx_page_context);
            _jspx_th_c_005fif_005f8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
            // /member/username.jsp(222,861) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
            _jspx_th_c_005fif_005f8.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.userType==2}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
            // /member/username.jsp(222,861) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
            _jspx_th_c_005fif_005f8.setVar("true");
            int _jspx_eval_c_005fif_005f8 = _jspx_th_c_005fif_005f8.doStartTag();
            if (_jspx_eval_c_005fif_005f8 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              do {
                out.write("<a href=\"");
                out.print(request.getContextPath());
                out.write("/member/gameuserList.do?action=preauthRelation&userID=");
                out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.userID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                out.write("\">权限</a>");
                int evalDoAfterBody = _jspx_th_c_005fif_005f8.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
            }
            if (_jspx_th_c_005fif_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f8);
              return;
            }
            _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f8);
            out.write("</td>\r\n");
            out.write("          </tr>\r\n");
            out.write("\t\t  ");
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
      out.write("\t\t  ");
      if (_jspx_meth_c_005fif_005f9(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td colspan=\"11\" align=\"left\" class=\"tdlefts\"><input name=\"curPage\" type=\"hidden\" value=\"");
      out.print(pageIndex);
      out.write("\"/><input name=\"Submit3\" type=\"submit\" class=\"input\" value=\"删除\" />           </td>\r\n");
      out.write("            <td colspan=\"10\" class=\"tdright_new\">总记录:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalRecord}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write('条');
      out.write('/');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPage}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("页\r\n");
      out.write("        ");
      if (_jspx_meth_c_005fif_005f10(_jspx_page_context))
        return;
      out.write("</td>\r\n");
      out.write("          </tr>\r\n");
      out.write("        </table>\r\n");
      out.write("</form>\r\n");
      out.write("</body>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var ttype2=document.all.selectOne;\r\n");
      out.write("var index2=\"");
      out.print(selectOne );
      out.write("\";\r\n");
      out.write("for(var i=0; i<ttype2.options.length; i++){\r\n");
      out.write("\tif(ttype2.options[i].value==index2){\r\n");
      out.write("\t\tttype2.options[i].selected=true;break;\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
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
      out.write("var ttype=document.all.orderby;\r\n");
      out.write("var index=\"");
      out.print(orderby );
      out.write("\";\r\n");
      out.write("for(var i=0; i<ttype.options.length; i++){\r\n");
      out.write("\tif(ttype.options[i].value==index){\r\n");
      out.write("\t\tttype.options[i].selected=true;break;\r\n");
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

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /member/username.jsp(212,33) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.memberOrder==0}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /member/username.jsp(212,33) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setVar("true");
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.memberName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
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

  private boolean _jspx_meth_c_005fif_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /member/username.jsp(212,106) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.memberOrder==1}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /member/username.jsp(212,106) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setVar("true");
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<span class=\"colorred\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.memberName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</span>");
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

  private boolean _jspx_meth_c_005fif_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /member/username.jsp(212,209) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.memberOrder==2}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /member/username.jsp(212,209) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f2.setVar("true");
    int _jspx_eval_c_005fif_005f2 = _jspx_th_c_005fif_005f2.doStartTag();
    if (_jspx_eval_c_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<span class=\"colorblue\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.memberName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</span>");
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

  private boolean _jspx_meth_c_005fif_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f3 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /member/username.jsp(212,313) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f3.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.memberOrder==3}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /member/username.jsp(212,313) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f3.setVar("true");
    int _jspx_eval_c_005fif_005f3 = _jspx_th_c_005fif_005f3.doStartTag();
    if (_jspx_eval_c_005fif_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<span class=\"coloryellow\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.memberName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</span>");
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

  private boolean _jspx_meth_c_005fif_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f4 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /member/username.jsp(212,419) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f4.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.memberOrder==4}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /member/username.jsp(212,419) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f4.setVar("true");
    int _jspx_eval_c_005fif_005f4 = _jspx_th_c_005fif_005f4.doStartTag();
    if (_jspx_eval_c_005fif_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<span class=\"colorzs\">");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.memberName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("</span>");
        int evalDoAfterBody = _jspx_th_c_005fif_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f4);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f9(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f9 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f9.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f9.setParent(null);
    // /member/username.jsp(225,4) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f9.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${returnInfo!=''}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /member/username.jsp(225,4) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f9.setVar("true");
    int _jspx_eval_c_005fif_005f9 = _jspx_th_c_005fif_005f9.doStartTag();
    if (_jspx_eval_c_005fif_005f9 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t  <tr>\r\n");
        out.write("                <td class=\"tdcenter\" colspan=\"21\" align=\"center\" valign=\"middle\" bgcolor=\"#FFFFFF\">\r\n");
        out.write("\t\t\t\t<font color=\"red\">");
        if (_jspx_meth_c_005fout_005f0(_jspx_th_c_005fif_005f9, _jspx_page_context))
          return true;
        out.write("</font></td>\r\n");
        out.write("       </tr>\r\n");
        out.write("\t   ");
        int evalDoAfterBody = _jspx_th_c_005fif_005f9.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f9);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f9, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f9);
    // /member/username.jsp(228,22) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${returnInfo}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f0 = _jspx_th_c_005fout_005f0.doStartTag();
    if (_jspx_th_c_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f10(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f10 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f10.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f10.setParent(null);
    // /member/username.jsp(234,8) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f10.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPage>=0}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /member/username.jsp(234,8) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f10.setVar("true");
    int _jspx_eval_c_005fif_005f10 = _jspx_th_c_005fif_005f10.doStartTag();
    if (_jspx_eval_c_005fif_005f10 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write(" \r\n");
        out.write("　<a onClick=\"changepage(1)\" style=\"cursor:hand\">首页</a>\r\n");
        out.write("<a onClick=\"changepage(");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.curPage-1}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write(")\" style=\"cursor:hand\">上页</a>\r\n");
        out.write("<select name=\"select\" onChange=\"jumppage(this.value);\">\r\n");
        out.write("          ");
        if (_jspx_meth_c_005fforEach_005f1(_jspx_th_c_005fif_005f10, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("        </select>\r\n");
        out.write("        <a onClick=\"changepage(");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.curPage+1}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write(")\" style=\"cursor:hand\">下页</a>\r\n");
        out.write("        <a onClick=\"changepage(");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPage}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write(")\" style=\"cursor:hand\">末页</a>");
        int evalDoAfterBody = _jspx_th_c_005fif_005f10.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f10);
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f10, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fstep_005fend_005fbegin.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f10);
    // /member/username.jsp(238,10) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setVar("i");
    // /member/username.jsp(238,10) name = begin type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setBegin(1);
    // /member/username.jsp(238,10) name = end type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setEnd(((java.lang.Integer) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPage }", java.lang.Integer.class, (PageContext)_jspx_page_context, null, false)).intValue());
    // /member/username.jsp(238,10) name = step type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setStep(1);
    int[] _jspx_push_body_count_c_005fforEach_005f1 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f1 = _jspx_th_c_005fforEach_005f1.doStartTag();
      if (_jspx_eval_c_005fforEach_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write(" <option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\" \r\n");
          out.write("              ");
          if (_jspx_meth_c_005fif_005f11(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
            return true;
          out.write("\r\n");
          out.write("            >第");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("页\r\n");
          out.write("            </option>\r\n");
          out.write("          ");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f1.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fstep_005fend_005fbegin.reuse(_jspx_th_c_005fforEach_005f1);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f11(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f11 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f11.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    // /member/username.jsp(239,14) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f11.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i==page.curPage}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /member/username.jsp(239,14) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f11.setVar("true");
    int _jspx_eval_c_005fif_005f11 = _jspx_th_c_005fif_005f11.doStartTag();
    if (_jspx_eval_c_005fif_005f11 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("selected=\"selected\"");
        int evalDoAfterBody = _jspx_th_c_005fif_005f11.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f11);
    return false;
  }
}

package org.apache.jsp.bbtj;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class gameuserlistToday_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fstep_005fend_005fbegin;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fstep_005fend_005fbegin = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.release();
    _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.release();
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

      out.write("﻿\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write(" \r\n");

	int recordIndex=1;
	int pageIndex=1;
	int pageSize=10;
	String peroidnum = "";
	int num = 1000;
	String createTime = "";
	String checkTime = "";

	try{
		if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("pageIndex")!=null){
			pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
		}
		if(request.getParameter("peroidnum")!=null){
			peroidnum=request.getParameter("peroidnum");
		}

		if(request.getAttribute("checkTime")!=null){
		   checkTime =request.getAttribute("checkTime").toString();
		   System.out.println("checkTime:"+checkTime);
        }
		if(request.getAttribute("num")!=null){
			num=Integer.parseInt(request.getAttribute("num").toString());
		}
		if(request.getParameter("startTime")!=null){
			createTime=(String)request.getParameter("startTime");
			System.out.println("startTime:"+createTime);
		}
		//else {createTime=(String)request.getAttribute("startTime"); }
		recordIndex=(pageIndex-1)*pageSize+1;
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
		System.out.println("selectOne:"+selectOne);
	}
	String selectOrderBy="totalGoldXZ";
	if(request.getParameter("selectOrderBy")!=null){
		selectOrderBy= request.getParameter("selectOrderBy");
	}

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>即时注单管理</title>\r\n");
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
      out.write("var msg=\"");
      out.print(msg );
      out.write("\";\r\n");
      out.write("if(msg!='')alert(msg); \r\n");
      out.write("\r\n");
      out.write("//function   refresh()   {   \r\n");
      out.write("//  history.go(0);   \r\n");
      out.write("//  }   \r\n");
      out.write("//  var t = setTimeout(\"refresh()\",");
      out.print(request.getAttribute("time")==null?"60000":request.getAttribute("time"));
      out.write(");   \r\n");
      out.write("\r\n");
      out.write("//function queryrooms(){\r\n");
      out.write("//\t\t var roomId=document.all.roomId.options[document.all.roomId.selectedIndex].value;\r\n");
      out.write("//\t\t var roomId1=parseInt(roomId);\r\n");
      out.write("//\t\t if(roomId1==16||roomId1==17){\r\n");
      out.write("//\t\t     document.location.href=\"countList.do?action=countList&roomId=\"+roomId;\r\n");
      out.write("//\t\t }\r\n");
      out.write("//\t\t else{\r\n");
      out.write("//\t\t     document.location.href=\"countList.do?action=countList&roomId=\"+roomId;\r\n");
      out.write("//\t\t }\r\n");
      out.write("//}\r\n");
      out.write("\r\n");
      out.write("function submted(){\r\n");
      out.write("\tvar d1 = document.getElementById(\"startTime\").value;\r\n");
      out.write("    if(!checkTime()){\r\n");
      out.write("\tif (d1 == \"\"){\r\n");
      out.write("\t\talert(\"请选择查看日期\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}}\r\n");
      out.write("\treturn true;\r\n");
      out.write("}\r\n");
      out.write("function checkTime(){\r\n");
      out.write("    var radioo = document.getElementsByName(\"checkTime\");\r\n");
      out.write("\r\n");
      out.write("    for(var i=0;i<radioo.length;i++){\r\n");
      out.write("//      alert(radioo[i]);\r\n");
      out.write("//     alert(radioo[i].checked);\r\n");
      out.write("\t   if(radioo[i].checked){\r\n");
      out.write("//\t      alert(radioo[i].checked);\r\n");
      out.write("\t      return true;\r\n");
      out.write("\t\t  break;\r\n");
      out.write("\t   }   \r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write(" function loadDay(){\r\n");
      out.write("\tvar radioo = document.getElementsByName(\"checkTime\");\r\n");
      out.write("\tvar index1=\"");
      out.print(checkTime );
      out.write("\";\r\n");
      out.write("\t for(var i=0;i<radioo.length;i++){\r\n");
      out.write("//\t   alert(radioo[i].value);\r\n");
      out.write("\t\tif(radioo[i].value==index1){\r\n");
      out.write("//\t\t alert(radioo[i].checked);\r\n");
      out.write("\t\t\tradioo[i].checked=true;break;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("var tone=encodeURI(\"");
      out.print(termOne );
      out.write("\");\r\n");
      out.write("function jumppage(value)\r\n");
      out.write("   {\r\n");
      out.write("       window.location.href=\"bbtjOne.do?action=search&curPage=\" +value+\"&pageSize=");
      out.print(pageSize );
      out.write("&termOne=");
      out.print(termOne);
      out.write("&selectOne=");
      out.print(selectOne );
      out.write("&selectOrderBy=");
      out.print(selectOrderBy);
      out.write("&startTime=");
      out.print(createTime );
      out.write("&checkTime=");
      out.print(checkTime );
      out.write("\";\r\n");
      out.write("   }\r\n");
      out.write("function changepage(pageNo){\r\n");
      out.write("\tdocument.location.href=\"bbtjOne.do?action=search&curPage=\"+pageNo+\"&termOne=\"+tone\r\n");
      out.write("\t+\"&selectOne=");
      out.print(selectOne );
      out.write("&pageSize=");
      out.print(pageSize );
      out.write("&selectOrderBy=");
      out.print(selectOrderBy);
      out.write("&startTime=");
      out.print(createTime );
      out.write("&checkTime=");
      out.print(checkTime );
      out.write("\";\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<body onload=\"loadDay()\">\r\n");
      out.write("  <form id=\"form1\" name=\"form1\" method=\"post\" onsubmit=\"return submted()\" action=\"bbtjOne.do?action=search\">\r\n");
      out.write(" <table width=\"100%\" border=\"0\" cellpadding=\"2\" cellspacing=\"1\" class=\"border\">\r\n");
      out.write("     <tr class=\"tdright\">\r\n");
      out.write("      <td height=\"25\">\r\n");
      out.write("\t\t字段\r\n");
      out.write("        <input name=\"termOne\" type=\"text\" class=\"input\" id=\"termOne\" value=\"");
      out.print(termOne);
      out.write("\" size=\"15\" />\r\n");
      out.write("\t\t&nbsp;&nbsp;类型\r\n");
      out.write("         <select name=\"selectOne\" size=\"1\" class=\"Select\" id=\"selectOne\">\r\n");
      out.write("\t\t<option value=\"accounts\">按用户名</option>\r\n");
      out.write("\t\t<option value=\"userId\">按用户ID</option>\r\n");
      out.write("        </select>\r\n");
      out.write("\t\t&nbsp;&nbsp;排序\r\n");
      out.write("\t\t<select name=\"selectOrderBy\" size=\"1\" class=\"Select\" id=\"selectOrderBy\">\r\n");
      out.write("\t\t<option value=\"totalGoldXZ\">按投总额</option>\r\n");
      out.write("\t\t<option value=\"winlostMoney\">按输赢数</option>\r\n");
      out.write("\t\t<option value=\"score\">按用户银子</option>\r\n");
      out.write("\t\t<option value=\"userID\">按用户ID</option>\r\n");
      out.write("        </select>\r\n");
      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("       &nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t   <input type=\"hidden\" name=\"type\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${type}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("        <input type=\"submit\" name=\"Submit\" value=\"查询\" />\r\n");
      out.write("       </td>\r\n");
      out.write("     </tr>\r\n");
      out.write("</table>\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<form id=\"form2\" name=\"form2\" method=\"post\" action=\"userDel.do\">\r\n");
      out.write("  <table width=\"100%\" border=\"0\" cellpadding=\"2\" cellspacing=\"1\" class=\"border\">\r\n");
      out.write("        <tr>\r\n");
      out.write("        <td height=\"25\" colspan=\"12\" class=\"menutop\">当日投注用户</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("        <td colspan=\"3\" class=\"tdcenter\"><div align=\"right\"><strong>汇总:</strong></div></td>\r\n");
      out.write("\t\t<td class=\"tdcenter\">&nbsp;");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sumNo.totalGoldXZ}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("\t\t<td class=\"tdcenter\">&nbsp;");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sumNo.winlostMoney}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("        <td class=\"tdcenter\">&nbsp;</td>\r\n");
      out.write("        ");
      out.write("\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td class=\"tdcenter\" align=\"center\">序号</td>\r\n");
      out.write("      <td class=\"tdcenter\"><div align=\"center\">用户ID</div></td>\r\n");
      out.write("      <td class=\"tdcenter\"><div align=\"center\">用户名</div></td>\r\n");
      out.write("      <td class=\"tdcenter\"><div align=\"center\">投注总额</div></td>\r\n");
      out.write("\t  <td class=\"tdcenter\"><div align=\"center\">输赢总额</div></td>\r\n");
      out.write("      <td class=\"tdcenter\"><div align=\"center\">用户银子</div></td>\r\n");
      out.write("      ");
      out.write("\r\n");
      out.write("    </tr>\r\n");
      out.write("    ");
      //  c:forEach
      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
      _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f0.setParent(null);
      // /bbtj/gameuserlistToday.jsp(211,4) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setVar("user");
      // /bbtj/gameuserlistToday.jsp(211,4) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${userlist}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
      int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
        if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("      <tr class=\"tdcenter\">\r\n");
            out.write("        <td height=\"25\" align=\"center\">");
            out.print(recordIndex++ );
            out.write("</td>\r\n");
            out.write("        <td height=\"25\"><div align=\"center\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.userID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("&nbsp;</div></td>\r\n");
            out.write("        <td><div align=\"center\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.accounts}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("&nbsp;</div></td>\r\n");
            out.write("\t\t<td><div align=\"center\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.totalGoldXZ}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("&nbsp;</div></td>\r\n");
            out.write("\t\t<td><div align=\"center\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.winlostMoney}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("&nbsp;</div></td>\r\n");
            out.write("        <td><div align=\"center\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.score}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("&nbsp;</div></td>\r\n");
            out.write("        ");
            out.write("\r\n");
            out.write("      </tr>\r\n");
            out.write("    ");
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
      out.write("\t<tr><td colspan=\"12\" align=\"center\"><font color=\"red\">");
      if (_jspx_meth_c_005fout_005f0(_jspx_page_context))
        return;
      out.write("</font></td></tr>\r\n");
      out.write("  </table>\r\n");
      out.write("  <table width=\"100%\" border=\"0\" cellpadding=\"2\" cellspacing=\"1\">\r\n");
      out.write("    \r\n");
      out.write("    <tr class=\"tdcenter\" align=\"left\">\r\n");
      out.write("       <td colspan=\"4\">总记录:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalRecord}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write('条');
      out.write('/');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPage}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("页\r\n");
      out.write("        ");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("  </table>\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("<script>\r\n");
      out.write("\r\n");
      out.write("var radioo = document.getElementsByName(\"checkTime\");\r\n");
      out.write("var index1=\"");
      out.print(checkTime );
      out.write("\";\r\n");
      out.write(" for(var i=0;i<radioo.length;i++){\r\n");
      out.write(" //  alert(radioo[i].value);\r\n");
      out.write("\tif(radioo[i].value==index1){\r\n");
      out.write("//\t alert(radioo[i].checked);\r\n");
      out.write("\t\tradioo[i].checked=true;break;\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("var ttype2=document.all.selectOne;\r\n");
      out.write("var index22=\"");
      out.print(selectOne );
      out.write("\";\r\n");
      out.write("for(var i=0; i<ttype2.length; i++){\r\n");
      out.write("\tif(ttype2.options[i].value==index22){\r\n");
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
      out.write("var ttype4=document.all.startTime;\r\n");
      out.write("var index3=\"");
      out.print(createTime);
      out.write("\";\r\n");
      out.write("\tif(ttype3.value==index3){\r\n");
      out.write("\tttype3.selected=true;\r\n");
      out.write("}\r\n");
      out.write("var ttype5=document.all.selectOrderBy;\r\n");
      out.write("var index5=\"");
      out.print(selectOrderBy );
      out.write("\";\r\n");
      out.write("for(var i=0; i<ttype5.length; i++){\r\n");
      out.write("\tif(ttype5.options[i].value==index5){\r\n");
      out.write("\t\tttype5.options[i].selected=true;break;\r\n");
      out.write("\t}\r\n");
      out.write("}\t\t\r\n");
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

  private boolean _jspx_meth_c_005fout_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f0.setParent(null);
    // /bbtj/gameuserlistToday.jsp(224,55) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${returnInfo}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f0 = _jspx_th_c_005fout_005f0.doStartTag();
    if (_jspx_th_c_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /bbtj/gameuserlistToday.jsp(230,8) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPage>=0}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /bbtj/gameuserlistToday.jsp(230,8) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setVar("true");
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write(" \r\n");
        out.write("　<a onclick=\"changepage(1)\" style=\"cursor:hand\">首页</a>\r\n");
        out.write("<a onclick=\"changepage(");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.curPage-1}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write(")\" style=\"cursor:hand\">上页</a>\r\n");
        out.write("<select name=\"select\" onChange=\"jumppage(this.value);\">\r\n");
        out.write("          ");
        if (_jspx_meth_c_005fforEach_005f1(_jspx_th_c_005fif_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("        </select>\r\n");
        out.write("        <a onclick=\"changepage(");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.curPage+1}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write(")\" style=\"cursor:hand\">下页</a>\r\n");
        out.write("        <a onclick=\"changepage(");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPage}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write(")\" style=\"cursor:hand\">末页</a>");
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

  private boolean _jspx_meth_c_005fforEach_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fstep_005fend_005fbegin.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /bbtj/gameuserlistToday.jsp(234,10) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setVar("i");
    // /bbtj/gameuserlistToday.jsp(234,10) name = begin type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setBegin(1);
    // /bbtj/gameuserlistToday.jsp(234,10) name = end type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setEnd(((java.lang.Integer) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPage }", java.lang.Integer.class, (PageContext)_jspx_page_context, null, false)).intValue());
    // /bbtj/gameuserlistToday.jsp(234,10) name = step type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
          if (_jspx_meth_c_005fif_005f1(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
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

  private boolean _jspx_meth_c_005fif_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    // /bbtj/gameuserlistToday.jsp(235,14) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i==page.curPage}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /bbtj/gameuserlistToday.jsp(235,14) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setVar("true");
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("selected=\"selected\"");
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
}

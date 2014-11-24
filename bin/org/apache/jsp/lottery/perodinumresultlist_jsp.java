package org.apache.jsp.lottery;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class perodinumresultlist_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write('\r');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../yanzheng.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");

	int recordIndex=1;
	int pageIndex=1;
	int pageSize=30;
	int roomId = 1;
	int retroType = 1;
	String peroidnum = "";
	String pperoidnum="";
	int pero = 0;
	try{
		System.out.println(request.getAttribute("pageSize")+","+request.getAttribute("pageIndex"));
		
//		if(request.getAttribute("peroidnum")!=null){
//			peroidnum=(String)request.getAttribute("peroidnum");
//			pero = Integer.parseInt(peroidnum);
//		}
		if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("pageIndex")!=null){
			pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
		}
		if(request.getParameter("roomId")!=null){
			roomId=Integer.parseInt(request.getParameter("roomId"));
		}
		if(request.getParameter("retroType")!=null){
			retroType=Integer.parseInt(request.getParameter("retroType"));
		}
			if(request.getParameter("pperoidnum")!=null){
		pperoidnum=request.getParameter("pperoidnum");
		//peroidnum = new String(peroidnum.getBytes("ISO_8859_1"),"UTF-8");
	}
		}catch(Exception e){
			e.printStackTrace();
	}
	recordIndex=(pageIndex-1)*pageSize+1;
	String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>8kenok开奖补录</title>\r\n");
      out.write("<style type=\"text/css\">\r\n");
      out.write("<!--\r\n");
      out.write("body {\r\n");
      out.write("\tmargin-left: 0px;\r\n");
      out.write("\tmargin-top: 0px;\r\n");
      out.write("\tmargin-right: 0px;\r\n");
      out.write("}\r\n");
      out.write("-->\r\n");
      out.write("</style>\r\n");
      out.write("<link href=\"../css/user.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("<script type=\"text/javascript\" src=\"../js/check.js\" charset=\"UTF-8\" ></script>\r\n");
      out.write("<script language=\"javascript\" type=\"text/javascript\">\r\n");
      out.write("var msg=\"");
      out.print(msg );
      out.write("\";\r\n");
      out.write("if(msg!='')alert(msg);\r\n");
      out.write("function jumppage(value)\r\n");
      out.write("   {\r\n");
      out.write("         window.location.href=\"perodinumResult.do?action=resultList&curPage=\" +value+\"&pageSize=");
      out.print(pageSize );
      out.write("&roomId=");
      out.print(roomId );
      out.write("&pperoidnum=\"+document.getElementById(\"pperoidnum\").value;\r\n");
      out.write("   }\r\n");
      out.write("   \r\n");
      out.write("function search(){\r\n");
      out.write("\tdocument.location.href=\"perodinumResult.do?action=resultList&pperoidnum=\"+document.getElementById(\"pperoidnum\").value+\"&roomId=");
      out.print(roomId);
      out.write("\"\r\n");
      out.write("}\r\n");
      out.write("   \r\n");
      out.write("function changepage(pageNo){\r\n");
      out.write("\t     document.location.href=\"perodinumResult.do?action=resultList&curPage=\"+pageNo+\"&roomId=");
      out.print(roomId );
      out.write("&pperoidnum=\"+document.getElementById(\"pperoidnum\").value;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function queryrooms(){\r\n");
      out.write("\t\t var roomId=document.all.roomId.options[document.all.roomId.selectedIndex].value;\r\n");
      out.write("\t\t document.location.href=\"perodinumResult.do?action=resultList&roomId=\"+roomId+\"&pperoidnum=\"+document.getElementById(\"pperoidnum\").value;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function checkUpdateNum(){\r\n");
      out.write("var arr = new Array(20);\r\n");
      out.write("\r\n");
      out.write("for(var i =1 ;i<21;i++){\r\n");
      out.write("    var obj = document.getElementById(\"_nnum\"+i);\r\n");
      out.write("\tvar bjvalue = obj.value;\r\n");
      out.write("\tif(bjvalue==\"\"){\r\n");
      out.write("\t   alert(\"输入的号码不能为空!\");\r\n");
      out.write("\t   return false;\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tif(bjvalue<1||bjvalue>80){\r\n");
      out.write("\t   alert(\"请输入0-80之间的数字!\");\r\n");
      out.write("\t   return false;\r\n");
      out.write("\t}\r\n");
      out.write("\tarr[i] = bjvalue;\r\n");
      out.write("\t}\r\n");
      out.write("\tvar nary=arr.sort(); \r\n");
      out.write("\tfor(var i=0;i<nary.length-1;i++) \r\n");
      out.write("\t{\r\n");
      out.write("\tif (nary[i]==nary[i+1]) {\r\n");
      out.write("\t    alert(\"重复内容：\"+nary[i]);\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t\t} \r\n");
      out.write("\t}\r\n");
      out.write("\treturn true;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function checknum(){\r\n");
      out.write("var arr = new Array(20);\r\n");
      out.write("var peroidnum = document.getElementById(\"peroidnum\").value;\r\n");
      out.write("if(peroidnum==\"\"){\r\n");
      out.write("\t  alert(\"请选择期数!\");\r\n");
      out.write("\t  return false;\r\n");
      out.write("\t}\r\n");
      out.write("for(var i =1 ;i<21;i++){\r\n");
      out.write("    var obj = document.getElementById(\"_num\"+i);\r\n");
      out.write("\tvar bjvalue = obj.value;\r\n");
      out.write("\tif(bjvalue==\"\"){\r\n");
      out.write("\t   alert(\"输入的号码不能为空!\");\r\n");
      out.write("\t   return false;\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tif(bjvalue<1||bjvalue>80){\r\n");
      out.write("\t   alert(\"请输入0-80之间的数字!\");\r\n");
      out.write("\t   return false;\r\n");
      out.write("\t}\r\n");
      out.write("\tarr[i] = bjvalue;\r\n");
      out.write("\t}\r\n");
      out.write("\tvar nary=arr.sort(); \r\n");
      out.write("\tfor(var i=0;i<nary.length-1;i++) \r\n");
      out.write("\t{\r\n");
      out.write("\tif (nary[i]==nary[i+1]) {\r\n");
      out.write("\t    alert(\"重复内容：\"+nary[i]);\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t\t} \r\n");
      out.write("\t}\r\n");
      out.write("\treturn true;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function isNumber(oNum){\r\n");
      out.write(" if(!oNum) return false; //line:160\r\n");
      out.write(" if(oNum.value==\"\") return false;\r\n");
      out.write(" var vv = oNum.value.replace(/[^\\d]/g,'');\r\n");
      out.write(" var strP=/^\\d+$/;\r\n");
      out.write(" //alert(nn);\r\n");
      out.write("  if (!isNaN(oNum.value)) {\r\n");
      out.write("        return true;\r\n");
      out.write("    } \r\n");
      out.write("  else{\r\n");
      out.write("        alert(\"请输入0-80区间数字\");\r\n");
      out.write("        oNum.value=vv;\r\n");
      out.write(" \t\toNum.focus();\r\n");
      out.write(" \t\treturn false; \r\n");
      out.write("    }\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function modfun(h1,h2,h3,h4,h5,h6,h7,h8,h9,h10,h11,h12,h13,h14,h15,h16,h17,h18,h19,h20,qibie){\r\n");
      out.write("\r\n");
      out.write("\tfor(var i = 0;i<20;i++){\r\n");
      out.write("\t   document.getElementById(\"_nnum\"+(i+1)).value=arguments[i];\r\n");
      out.write("\t}\r\n");
      out.write("\tdocument.getElementById(\"m_table\").style.display='';\r\n");
      out.write("\tdocument.getElementById(\"a_table\").style.display='none';\r\n");
      out.write("\tdocument.getElementById(\"peroidnum1\").value=qibie;\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("<form id=\"form1\" name=\"form1\" method=\"post\" onsubmit=\"return checknum()\" action=\"perodinumResult.do?action=addResult&roomId=");
      out.print(roomId );
      out.write("\">\r\n");
      out.write("  <table width=\"100%\" id=\"a_table\" border=\"0\" cellpadding=\"2\" cellspacing=\"1\" class=\"border\" style=\"display:block\">\r\n");
      out.write("  <tr>\r\n");
      out.write("      <td width=\"19%\" height=\"25\" class=\"menutop\">\r\n");
      out.write("\t\t    开奖号码录入</td>\r\n");
      out.write("\t\t\t<td width=\"65%\" align=\"left\" class=\"menutop\">\r\n");
      out.write("\t\t\t01\r\n");
      out.write("\t\t    <input size=\"1\" style=\"width:25px\" id=\"_num1\" maxlength=\"2\" type=\"text\" name=\"num1\" onkeyup=\"isNumber(this)\"  />\r\n");
      out.write("\t\t\t02\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" id=\"_num2\" maxlength=\"2\" type=\"text\"  name=\"num2\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t03\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" id=\"_num3\" maxlength=\"2\" type=\"text\"  name=\"num3\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t04\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" id=\"_num4\" maxlength=\"2\" type=\"text\"  name=\"num4\" onkeyup=\"isNumber(this)\"/> \r\n");
      out.write("\t\t\t05\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" id=\"_num5\" maxlength=\"2\" type=\"text\"  name=\"num5\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t06\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" id=\"_num6\" maxlength=\"2\" type=\"text\"  name=\"num6\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t07\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" id=\"_num7\" maxlength=\"2\" type=\"text\"  name=\"num7\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t08\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" id=\"_num8\" maxlength=\"2\" type=\"text\"  name=\"num8\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t09\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" id=\"_num9\" maxlength=\"2\" type=\"text\"  name=\"num9\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t10\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" id=\"_num10\" maxlength=\"2\" type=\"text\"  name=\"num10\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t  <td width=\"16%\" class=\"menutop\"></td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t<td class=\"menutop\">期号为：<font color=\"#FF0000\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${peroidnum}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</font>\r\n");
      out.write("\t\t\t<input name=\"peroidnum\" type=\"hidden\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${peroidnum}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("\t\t\t<input name=\"retroType1\" type=\"hidden\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${retroType}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" /></td>\r\n");
      out.write("\t\t\t<td class=\"menutop\" align=\"left\">\r\n");
      out.write("\t\t\t11\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" id=\"_num11\" maxlength=\"2\" type=\"text\"  name=\"num11\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t12\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" id=\"_num12\" maxlength=\"2\" type=\"text\"   name=\"num12\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t13\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" id=\"_num13\" maxlength=\"2\" type=\"text\"  name=\"num13\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t14\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" id=\"_num14\" maxlength=\"2\" type=\"text\"  name=\"num14\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t15\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" id=\"_num15\" maxlength=\"2\" type=\"text\"  name=\"num15\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t16\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" id=\"_num16\" maxlength=\"2\" type=\"text\"  name=\"num16\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t17\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" id=\"_num17\" maxlength=\"2\" type=\"text\"  name=\"num17\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t18\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" id=\"_num18\" maxlength=\"2\" type=\"text\"  name=\"num18\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t19\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" id=\"_num19\" maxlength=\"2\" type=\"text\"  name=\"num19\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t20\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" id=\"_num20\" maxlength=\"2\" type=\"text\"  name=\"num20\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t\t  <td class=\"menutop1\" align=\"left\"><input type=\"submit\"  name=\"submit\" value=\"录入\"></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("  </table>\r\n");
      out.write("</form>\r\n");
      out.write("  <form id=\"form1\" name=\"form1\" method=\"post\" onsubmit=\"return checkUpdateNum()\" action=\"perodinumResult.do?action=updateResult&roomId=");
      out.print(roomId );
      out.write("\">\r\n");
      out.write("  <table width=\"100%\" id=\"m_table\" border=\"0\" cellpadding=\"2\" cellspacing=\"1\" class=\"border\" style=\"display:none\">\r\n");
      out.write("  <tr>\r\n");
      out.write("      <td width=\"19%\" height=\"25\" class=\"menutop\">\r\n");
      out.write("\t\t    开奖号码修改</td>\r\n");
      out.write("\t\t\t<td width=\"65%\" align=\"left\" class=\"menutop\">\r\n");
      out.write("\t\t\t01\r\n");
      out.write("\t\t    <input size=\"1\" style=\"width:25px\" maxlength=\"2\" type=\"text\" name=\"_nnum1\" onkeyup=\"isNumber(this)\"  />\r\n");
      out.write("\t\t\t02\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" maxlength=\"2\" type=\"text\"  name=\"_nnum2\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t03\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" maxlength=\"2\" type=\"text\"  name=\"_nnum3\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t04\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" maxlength=\"2\" type=\"text\"  name=\"_nnum4\" onkeyup=\"isNumber(this)\"/> \r\n");
      out.write("\t\t\t05\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\"  maxlength=\"2\" type=\"text\"  name=\"_nnum5\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t06\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" maxlength=\"2\" type=\"text\"  name=\"_nnum6\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t07\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" maxlength=\"2\" type=\"text\"  name=\"_nnum7\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t08\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" maxlength=\"2\" type=\"text\"  name=\"_nnum8\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t09\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" maxlength=\"2\" type=\"text\"  name=\"_nnum9\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t10\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" maxlength=\"2\" type=\"text\"  name=\"_nnum10\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t  <td width=\"16%\" class=\"menutop\"></td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t\t\t<td class=\"menutop\" align=\"center\">期号为：<input name=\"peroidnum1\" type=\"text\" id=\"peroidnum1\" style=\"background:#73b3d6; border: solid 1px #73b3d6; width:50px; color:#000; font-size:12px;\" maxlength=\"2\" border=\"0\" checked=\"checked\"/>\r\n");
      out.write("\t\t\t</font>\r\n");
      out.write("\t\t\t<input name=\"retroType11\" type=\"hidden\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${retroType}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" /></td>\r\n");
      out.write("\t\t\t<td class=\"menutop\" align=\"left\">\r\n");
      out.write("\t\t\t11\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" maxlength=\"2\" type=\"text\"  name=\"_nnum11\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t12\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" maxlength=\"2\" type=\"text\"   name=\"_nnum12\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t13\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" maxlength=\"2\" type=\"text\"  name=\"_nnum13\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t14\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" maxlength=\"2\" type=\"text\"  name=\"_nnum14\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t15\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" maxlength=\"2\" type=\"text\"  name=\"_nnum15\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t16\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" maxlength=\"2\" type=\"text\"  name=\"_nnum16\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t17\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" maxlength=\"2\" type=\"text\"  name=\"_nnum17\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t18\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" maxlength=\"2\" type=\"text\"  name=\"_nnum18\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t19\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" maxlength=\"2\" type=\"text\"  name=\"_nnum19\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t20\r\n");
      out.write("\t\t\t<input size=\"1\" style=\"width:25px\" maxlength=\"2\" type=\"text\"  name=\"_nnum20\" onkeyup=\"isNumber(this)\"/>\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t  </td>\r\n");
      out.write("\t\t\t  <td class=\"menutop1\" align=\"left\"><input type=\"submit\"  name=\"submit\" value=\"修改\"></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("  </table>\r\n");
      out.write("</form>\r\n");
      out.write("\t<form name=\"form2\" id=\"form2\" action=\"\"/>\r\n");
      out.write("\t<table border=\"0\" width=\"100%\" cellpadding=\"2\" cellspacing=\"1\" class=\"border\">\r\n");
      out.write("\t  <tr class=\"title\">\r\n");
      out.write("      <td colspan=\"33\">游戏列表\r\n");
      out.write("        \r\n");
      out.write("        <select name=\"roomId\" size=\"1\" class=\"Select\" id=\"roomId\" onChange=\"queryrooms()\">\r\n");
      out.write("           ");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("        </select>\r\n");
      out.write("\t\t 期数查询<input name=\"pperoidnum\" size=\"12\" type=\"text\" class=\"input\" id=\"pperoidnum\" value=\"");
      out.print(pperoidnum);
      out.write("\" />\r\n");
      out.write("\t\t<input type=\"button\" name=\"button\" value=\"查询\" onClick=\"search()\"/>\r\n");
      out.write("</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr class=\"tdcenter\">\r\n");
      out.write("      <td rowspan=\"2\" align=\"center\"><strong>序号</strong></td>\r\n");
      out.write("      <td rowspan=\"2\"><div align=\"center\"><strong>期号</strong></div></td>\r\n");
      out.write("      <td rowspan=\"2\"><div align=\"center\"><strong>开奖时间</strong></div></td>\r\n");
      out.write("      <td colspan=\"20\"><div align=\"center\"><strong>开奖号码</strong></div></td>\r\n");
      out.write("     \r\n");
      out.write("    <!--  <td width=\"82\" rowspan=\"2\"><strong>总和</strong></td>-->\r\n");
      out.write("      <td rowspan=\"2\"><strong>单双</strong></td>\r\n");
      out.write("      <td rowspan=\"2\"><strong>大小</strong></td>\r\n");
      out.write("      <td rowspan=\"2\"><strong>龙和虎</strong></td>\r\n");
      out.write("      <td rowspan=\"2\"><strong>操作</strong></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr class=\"tdcenter\">\r\n");
      out.write("      <td><strong>01</strong></td>\r\n");
      out.write("      <td><strong>02</strong></td>\r\n");
      out.write("\t  <td><strong>03</strong></td>\r\n");
      out.write("\t  <td><strong>04</strong></td>\r\n");
      out.write("\t  <td><strong>05</strong></td>\r\n");
      out.write("\t  <td><strong>06</strong></td>\r\n");
      out.write("\t  <td><strong>07</strong></td>\r\n");
      out.write("\t  <td><strong>08</strong></td>\r\n");
      out.write("\t  <td><strong>09</strong></td>\r\n");
      out.write("\t  <td><strong>10</strong></td>\r\n");
      out.write("\t  <td><strong>11</strong></td>\r\n");
      out.write("\t  <td><strong>12</strong></td>\r\n");
      out.write("\t  <td><strong>13</strong></td>\r\n");
      out.write("\t  <td><strong>14</strong></td>\r\n");
      out.write("\t  <td><strong>15</strong></td>\r\n");
      out.write("\t  <td><strong>16</strong></td>\r\n");
      out.write("\t  <td><strong>17</strong></td>\r\n");
      out.write("\t  <td><strong>18</strong></td>\r\n");
      out.write("\t  <td><strong>19</strong></td>\r\n");
      out.write("\t  <td><strong>20</strong></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    ");
      //  c:forEach
      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
      _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f1.setParent(null);
      // /lottery/perodinumresultlist.jsp(341,4) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f1.setVar("user");
      // /lottery/perodinumresultlist.jsp(341,4) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f1.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${preodinumList}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
      int[] _jspx_push_body_count_c_005fforEach_005f1 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f1 = _jspx_th_c_005fforEach_005f1.doStartTag();
        if (_jspx_eval_c_005fforEach_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("    <tr class=\"tdcenter\">\r\n");
            out.write("      <td height=\"25\" align=\"center\">");
            out.print(recordIndex++ );
            out.write("</td>\r\n");
            out.write("      <td height=\"25\" align=\"center\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.peroidnum}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\r\n");
            out.write("\t  <input id=\"peroidnum\" name=\"peroidnum\" type=\"hidden\" value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.peroidnum}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\" />\r\n");
            out.write("\t  <input id=\"awardnum1\" name=\"awardnum1\" type=\"hidden\" value=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\" /></td>\r\n");
            out.write("\r\n");
            out.write("      <td height=\"25\" align=\"center\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.createTime}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("      <td class=\"ball_widht_height\"  background=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.color[0]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\"><strong>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[0]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</strong></td>\r\n");
            out.write("    <td class=\"ball_widht_height\" background=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.color[1]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\"><strong>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[1]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</strong></td>\r\n");
            out.write("\t<td class=\"ball_widht_height\" background=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.color[2]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\"><strong>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[2]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</strong></td>\r\n");
            out.write("\t<td class=\"ball_widht_height\" background=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.color[3]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\"><strong>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[3]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</strong></td>\r\n");
            out.write("\t<td class=\"ball_widht_height\" background=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.color[4]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\"><strong>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[4]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</strong></td>\r\n");
            out.write("    <td class=\"ball_widht_height\" background=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.color[5]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\"><strong>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[5]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</strong></td>\r\n");
            out.write("\t<td class=\"ball_widht_height\" background=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.color[6]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\"><strong>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[6]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</strong></td>\r\n");
            out.write("\t<td class=\"ball_widht_height\" background=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.color[7]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\"><strong>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[7]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</strong></td>\r\n");
            out.write("\t<td class=\"ball_widht_height\" background=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.color[8]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\"><strong>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[8]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</strong></td>\r\n");
            out.write("\t<td class=\"ball_widht_height\" background=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.color[9]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\"><strong>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[9]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</strong></td>\r\n");
            out.write("\t<td class=\"ball_widht_height\" background=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.color[10]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\"><strong>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[10]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</strong></td>\r\n");
            out.write("\t<td class=\"ball_widht_height\" background=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.color[11]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\"><strong>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[11]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</strong></td>\r\n");
            out.write("\t<td class=\"ball_widht_height\" background=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.color[12]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\"><strong>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[12]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</strong></td>\r\n");
            out.write("\t<td class=\"ball_widht_height\" background=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.color[13]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\"><strong>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[13]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</strong></td>\r\n");
            out.write("\t<td class=\"ball_widht_height\" background=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.color[14]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\"><strong>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[14]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</strong></td>\r\n");
            out.write("\t<td class=\"ball_widht_height\" background=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.color[15]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\"><strong>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[15]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</strong></td>\r\n");
            out.write("\t<td class=\"ball_widht_height\" background=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.color[16]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\"><strong>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[16]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</strong></td>\r\n");
            out.write("\t<td class=\"ball_widht_height\" background=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.color[17]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\"><strong>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[17]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</strong></td>  \r\n");
            out.write("\t<td class=\"ball_widht_height\" background=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.color[18]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\"><strong>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[18]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</strong></td>\r\n");
            out.write("\t<td class=\"ball_widht_height\" background=\"");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.color[19]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\"><strong>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[19]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</strong></td>\r\n");
            out.write("      <td>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.singleName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("      <td>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.largeName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("      <td>");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.serpentName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("      <td><div align=\"center\">\r\n");
            out.write("\t   ");
            //  c:if
            org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
            _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
            _jspx_th_c_005fif_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
            // /lottery/perodinumresultlist.jsp(373,4) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
            _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.peroidnum!=peroidnum}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
            // /lottery/perodinumresultlist.jsp(373,4) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
            _jspx_th_c_005fif_005f0.setVar("true");
            int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
            if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              do {
                out.write("<a href=\"perodinumResult.do?action=resultList&roomId=");
                out.print(roomId);
                out.write("&retroType=");
                out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.retroType}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                out.write("&peroidnum=");
                out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.peroidnum}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                out.write("\" >选择</a>");
                int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
            }
            if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f0);
              return;
            }
            _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f0);
            out.write("\r\n");
            out.write("\t  ");
            if (_jspx_meth_c_005fif_005f1(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
              return;
            out.write("\r\n");
            out.write("\t ");
            out.write(" <a href=\"javascript:modfun('");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[0]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('\'');
            out.write(',');
            out.write('\'');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[1]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('\'');
            out.write(',');
            out.write('\'');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[2]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('\'');
            out.write(',');
            out.write('\'');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[3]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('\'');
            out.write(',');
            out.write('\'');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[4]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('\'');
            out.write(',');
            out.write('\'');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[5]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('\'');
            out.write(',');
            out.write('\'');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[6]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('\'');
            out.write(',');
            out.write('\'');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[7]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('\'');
            out.write(',');
            out.write('\'');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[8]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('\'');
            out.write(',');
            out.write('\'');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[9]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('\'');
            out.write(',');
            out.write('\'');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[10]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('\'');
            out.write(',');
            out.write('\'');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[11]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('\'');
            out.write(',');
            out.write('\'');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[12]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('\'');
            out.write(',');
            out.write('\'');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[13]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('\'');
            out.write(',');
            out.write('\'');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[14]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('\'');
            out.write(',');
            out.write('\'');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[15]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('\'');
            out.write(',');
            out.write('\'');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[16]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('\'');
            out.write(',');
            out.write('\'');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[17]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('\'');
            out.write(',');
            out.write('\'');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[18]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('\'');
            out.write(',');
            out.write('\'');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.awardnum1[19]}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write('\'');
            out.write(',');
            out.write('\'');
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.peroidnum}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("')\">修改</a>\r\n");
            out.write("\t  <a href=\"perodinumResult.do?action=jiesuanResult&roomId=");
            out.print(roomId);
            out.write("&peroidnum=");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.peroidnum}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("&retroType=");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.retroType}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\">结算</a>");
            out.write("\r\n");
            out.write("\t\t</div></td>\r\n");
            out.write("    </tr>\r\n");
            out.write("    ");
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
      out.write("\t<tr class=\"tdcenter\"><td colspan=\"30\" align=\"center\"><font color=\"red\">");
      if (_jspx_meth_c_005fout_005f0(_jspx_page_context))
        return;
      out.write("</font></td></tr>\r\n");
      out.write("</table>\r\n");
      out.write("  <table width=\"100%\" border=\"0\" cellpadding=\"2\" cellspacing=\"1\">\r\n");
      out.write("    <tr>\r\n");
      out.write("       <td colspan=\"4\">总记录:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalRecord}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write('条');
      out.write('/');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPage}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("页\r\n");
      out.write("            ");
      if (_jspx_meth_c_005fif_005f2(_jspx_page_context))
        return;
      out.write("</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("  </table>\r\n");
      out.write("</form>\r\n");
      out.write("</body>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("var tstate=document.all.roomId;\r\n");
      out.write("var index1=\"");
      out.print(roomId );
      out.write("\";\r\n");
      out.write("for(var i=0; i<tstate.options.length; i++){\r\n");
      out.write("\t\t\tif(tstate.options[i].value==index1){\r\n");
      out.write("\t\t\t\ttstate.options[i].selected=true;break;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("var ttype3=document.all.pperoidnum;\r\n");
      out.write("var index2=\"");
      out.print(pperoidnum);
      out.write("\";\r\n");
      out.write("for(var i=0; i<ttype3.length; i++){\r\n");
      out.write("\tif(ttype3.value==index2){\r\n");
      out.write("\t\tttype3.selected=true;break;\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("</script> \r\n");
      out.write("\r\n");
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

  private boolean _jspx_meth_c_005fforEach_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /lottery/perodinumresultlist.jsp(295,11) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("r");
    // /lottery/perodinumresultlist.jsp(295,11) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roomType}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t         <option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${r.roomId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write('"');
          out.write('>');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${r.displayName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</option>\t\r\n");
          out.write("\t    ");
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

  private boolean _jspx_meth_c_005fif_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    // /lottery/perodinumresultlist.jsp(374,3) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.peroidnum==peroidnum}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /lottery/perodinumresultlist.jsp(374,3) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setVar("true");
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<img src=\"../images/register_44.jpg\" />");
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
    // /lottery/perodinumresultlist.jsp(380,72) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
    // /lottery/perodinumresultlist.jsp(385,12) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPage>0}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /lottery/perodinumresultlist.jsp(385,12) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f2.setVar("true");
    int _jspx_eval_c_005fif_005f2 = _jspx_th_c_005fif_005f2.doStartTag();
    if (_jspx_eval_c_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("  \r\n");
        out.write("　<a onclick=\"changepage(1)\" style=\"cursor:hand\">首页</a>\r\n");
        out.write("<a onclick=\"changepage(");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.curPage-1}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write(")\" style=\"cursor:hand\">上页</a>\r\n");
        out.write("<select name=\"select\" onChange=\"jumppage(this.value);\">\r\n");
        out.write("          ");
        if (_jspx_meth_c_005fforEach_005f2(_jspx_th_c_005fif_005f2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("        </select>\r\n");
        out.write("        <a onclick=\"changepage(");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.curPage+1}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write(")\" style=\"cursor:hand\">下页</a>\r\n");
        out.write("        <a onclick=\"changepage(");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPage}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write(")\" style=\"cursor:hand\">末页</a>");
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
    // /lottery/perodinumresultlist.jsp(389,10) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f2.setVar("i");
    // /lottery/perodinumresultlist.jsp(389,10) name = begin type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f2.setBegin(1);
    // /lottery/perodinumresultlist.jsp(389,10) name = end type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f2.setEnd(((java.lang.Integer) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPage }", java.lang.Integer.class, (PageContext)_jspx_page_context, null, false)).intValue());
    // /lottery/perodinumresultlist.jsp(389,10) name = step type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
    // /lottery/perodinumresultlist.jsp(390,14) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f3.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i==page.curPage}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /lottery/perodinumresultlist.jsp(390,14) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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

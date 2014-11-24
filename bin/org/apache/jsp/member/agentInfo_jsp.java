package org.apache.jsp.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class agentInfo_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("\r\n");
      out.write("\r\n");

int curPage = 0;
   if(request.getParameter("curPage")!=null){
        curPage = Integer.parseInt(request.getParameter("curPage"));
   }
String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}
String selectOne="";
	if(request.getParameter("selectOne")!=null){
		selectOne=request.getParameter("selectOne");
	}
int type = 1;
   if(request.getParameter("type")!=null){
        type = Integer.parseInt(request.getParameter("type"));
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
int spreaderID = 3;
if(request.getParameter("spreaderID")!=null){
	    spreaderID = Integer.parseInt(request.getParameter("spreaderID"));
	}

String accounts = "";
if(request.getParameter("accounts")!=null){
   accounts = request.getParameter("accounts");
}

      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>无标题文档</title>\r\n");
      out.write("<link href=\"../css/main.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("<script language=\"javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/userweb.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script src=\"../js/jquery.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script language=\"javascript\" src=\"../js/ajax.js\"></script>\r\n");
      out.write("<script language=\"javascript\">\r\n");
      out.write("var msg=\"");
      out.print(msg );
      out.write("\";\r\n");
      out.write("if(msg!='')alert(msg);\r\n");
      out.write("function checkbox()\r\n");
      out.write("{\r\n");
      out.write("var str=document.getElementsByName(\"limit\");\r\n");
      out.write("var objarray=str.length;\r\n");
      out.write("var chestr=\"\";\r\n");
      out.write("for (i=0;i<objarray;i++)\r\n");
      out.write("{\r\n");
      out.write("  if(str[i].checked == true)\r\n");
      out.write("  {\r\n");
      out.write("   chestr+=str[i].value+\",\";\r\n");
      out.write("  }\r\n");
      out.write("}\r\n");
      out.write("if(chestr == \"\")\r\n");
      out.write("{\r\n");
      out.write("  //alert(\"请选择权限!\");\r\n");
      out.write("}\r\n");
      out.write("else\r\n");
      out.write("{\r\n");
      out.write("  //alert(\"您现择的是：\"+chestr);\r\n");
      out.write("}\r\n");
      out.write("}\r\n");
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
      out.write("function SelectAllTable(flag,tableID)\r\n");
      out.write("{\r\n");
      out.write("    var m_list_table =  window.document.getElementById(tableID);\r\n");
      out.write("    var m_list_checkbox = GelTags(\"input\", m_list_table);\r\n");
      out.write("    for (var i = m_list_checkbox.length - 1; i >= 0; i--) {\r\n");
      out.write("        m_list_checkbox[i].checked = flag;\r\n");
      out.write("    }\r\n");
      out.write("}\r\n");
      out.write("function GelTags(tag, ob) {\r\n");
      out.write("    return (ob || gd).getElementsByTagName(tag);\r\n");
      out.write("}\r\n");
      out.write("function check(){\r\n");
      out.write("var taxRate = document.getElementById(\"taxRate\").value;\r\n");
      out.write("var brokerage = document.getElementById(\"brokerage\").value;\r\n");
      out.write("var winner = document.getElementById(\"winner\").value;\r\n");
      out.write("if(parseFloat(taxRate)>'");
      out.print(sxtaxRate);
      out.write("'){\r\n");
      out.write("   alert(\"税收提成超出上限!\");\r\n");
      out.write("   return false;\r\n");
      out.write("}\r\n");
      out.write("if(parseFloat(brokerage)>'");
      out.print(sxbrokerage);
      out.write("'){\r\n");
      out.write("   alert(\"洗码佣金超出上限!\");\r\n");
      out.write("   return false;\r\n");
      out.write("}\r\n");
      out.write("if(parseFloat(winner)>'");
      out.print(sxwinner);
      out.write("'){\r\n");
      out.write("   alert(\"股份占成超出上限!\");\r\n");
      out.write("   return false;\r\n");
      out.write("}\r\n");
      out.write("if (CheckNull(document.forms['form2'].nickName, '昵称不能为空!')){\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\tform2.submit();\r\n");
      out.write("\r\n");
      out.write("return true;\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("$(document).ready(function() {\r\n");
      out.write("\tSetContantFirst();\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("$(document).ready(function() {\r\n");
      out.write("$(\"#alls\").click(function() {\t\r\n");
      out.write("$(\"#form1 input\").each(function(i) { //(\"input[type='checkbox']\")\r\n");
      out.write("\tvar id = this.id.replace(\"menu\",'');\r\n");
      out.write("\tif(this.checked == false){\r\n");
      out.write("\t\tchangeState2(id);\r\n");
      out.write("\t\t//this.checked = true;\r\n");
      out.write("\t}\r\n");
      out.write("});\r\n");
      out.write("});\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("$(document).ready(function() {\r\n");
      out.write("$(\"#allno\").click(function() {\t\r\n");
      out.write("$(\"#form1 input\").each(function(i) {\r\n");
      out.write("\tvar id = this.id.replace(\"menu\",'');\r\n");
      out.write("\tif(this.checked == true){\r\n");
      out.write("\t\tchangeState1(id);\r\n");
      out.write("\t\t//this.checked = false;\r\n");
      out.write("\t}else{\r\n");
      out.write("\t\tchangeState2(id);\r\n");
      out.write("\t\t//this.checked = true;\r\n");
      out.write("\t}\r\n");
      out.write("});\r\n");
      out.write("});\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("function changeQx(id){\r\n");
      out.write("\tvar tid = \"td\"+id;\r\n");
      out.write("\tvar t=document.getElementById(tid).getElementsByTagName(\"input\");\r\n");
      out.write("\tfor(var i=0;i<t.length;i++){\r\n");
      out.write("\t\t//alert(t[i].checked+\"--\"+t[i].id);\r\n");
      out.write("\t\tvar id = t[i].id.replace(\"menu\",'');\r\n");
      out.write("\t\tif(t[i].checked == true){\r\n");
      out.write("\t\t    \r\n");
      out.write("\t\t\tchangeState1(id); //删除\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse{\r\n");
      out.write("\t\t\tchangeState2(id); //添加\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("//页面加载时  显示第一级的所有选项\r\n");
      out.write("function SetContantFirst()\r\n");
      out.write("{\r\n");
      out.write("\tvar fid = 0;\r\n");
      out.write("\tvar uid = parseInt(document.getElementById(\"proxyID\").value);\r\n");
      out.write("\tAgentAuthorityDwr.SetContantFirst(parseInt(fid),uid,function(data) {\r\n");
      out.write("\t\tdwr.util.setValue(\"level1\", data,{ escapeHtml:false });\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("//加载二级选项\r\n");
      out.write("function SetContantSecond(fid,open)\r\n");
      out.write("{\r\n");
      out.write("\tvar uid = parseInt(document.getElementById(\"proxyID\").value);\r\n");
      out.write("\tif(document.getElementById(\"level2-\"+fid).innerHTML!=''){\r\n");
      out.write("\t\tdwr.util.setValue(\"level2-\"+fid,'',{ escapeHtml:false });\r\n");
      out.write("\t\tdocument.getElementById(\"level2-\"+fid).style.display=\"none\";\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\tAgentAuthorityDwr.SetContantSecond(parseInt(fid),uid,function(data) {\r\n");
      out.write("\t\tif(data!='')\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\tdocument.getElementById(\"level2-\"+fid).style.display=\"\";\r\n");
      out.write("\t\t\tdwr.util.setValue(\"level2-\"+fid, data,{ escapeHtml:false });\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("//加载三级选项\r\n");
      out.write("function SetContantThird(fid,step)\r\n");
      out.write("{\r\n");
      out.write("\tvar uid = parseInt(document.getElementById(\"proxyID\").value);\r\n");
      out.write("\t//能级管理选项显示\r\n");
      out.write("\tAgentAuthorityDwr.SetContantThird(parseInt(fid),uid,function(data) {\r\n");
      out.write("\t\tif(data!='')\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\tdwr.util.setValue(\"level3-\"+fid, data,{ escapeHtml:false });\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("//加载四级选项\r\n");
      out.write("function SetContantFourth(fid,step)\r\n");
      out.write("{\r\n");
      out.write("\tvar uid = parseInt(document.getElementById(\"proxyID\").value);\r\n");
      out.write("\t//能级管理选项显示\r\n");
      out.write("\tAgentAuthorityDwr.SetContantFourth(parseInt(fid),uid,function(data) {\r\n");
      out.write("\t\tif(data!='')\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\tdwr.util.setValue(\"level4-\"+fid, data,{ escapeHtml:false });\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("//加载四级选项\r\n");
      out.write("function SetContantFifth(fid,step)\r\n");
      out.write("{\r\n");
      out.write("\tvar uid = parseInt(document.getElementById(\"proxyID\").value);\r\n");
      out.write("\t//能级管理选项显示\r\n");
      out.write("\tAgentAuthorityDwr.SetContantFifth(parseInt(fid),uid,function(data) {\r\n");
      out.write("\t\tif(data!='')\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\tdwr.util.setValue(\"level5-\"+fid, data,{ escapeHtml:false });\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("function changeSelectState(id,cdname)\r\n");
      out.write("{\r\n");
      out.write("\tvar uid = parseInt(document.getElementById(\"proxyID\").value);\r\n");
      out.write("\tif(document.getElementById(\"menu\"+id).checked==true)\r\n");
      out.write("\t{\r\n");
      out.write("\t\t//删除选中的权限和级联的权限\r\n");
      out.write("\t\tAgentAuthorityDwr.GetAuthority(id,uid,function(data) {\r\n");
      out.write("\t\t\tvar\tidArr = new Array();  \r\n");
      out.write("\t\t\tidArr = data.split(\"-\");\r\n");
      out.write("\t\t\tfor(var i=0;i<idArr.length;i++)\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tif(document.getElementById(\"menu\"+idArr[i])!=null)\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\tdocument.getElementById(\"menu\"+idArr[i]).checked=true;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t//alert(\"\\\"\"+cdname+\"\\\" 添加成功！\");\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}else\r\n");
      out.write("\t{\r\n");
      out.write("\t\t//删除选中的权限和级联的权限\r\n");
      out.write("\t\tAgentAuthorityDwr.ReleaseAuthority(id,uid,function(data) {\r\n");
      out.write("\t\t\tvar\tidArr = new Array();  \r\n");
      out.write("\t\t\tidArr = data.split(\"-\");\r\n");
      out.write("\t\t\tfor(var i=0;i<idArr.length;i++)\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tif(document.getElementById(\"menu\"+idArr[i])!=null)\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\tdocument.getElementById(\"menu\"+idArr[i]).checked=false;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t//alert(\"\\\"\"+cdname+\"\\\" 取消成功！\");\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function changeState1(id){\r\n");
      out.write("\t\tvar uid = parseInt(document.getElementById(\"proxyID\").value);\r\n");
      out.write("\t\t//删除选中的权限和级联的权限\r\n");
      out.write("\t\tAgentAuthorityDwr.ReleaseAuthority(id,uid,function(data) {\r\n");
      out.write("\t\t\tvar\tidArr = new Array();  \r\n");
      out.write("\t\t\tidArr = data.split(\"-\");\r\n");
      out.write("\t\t\tfor(var i=0;i<idArr.length;i++)\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tif(document.getElementById(\"menu\"+idArr[i])!=null)\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t   //alert(document.getElementById(\"menu\"+idArr[i]).checked+\",11,\"+idArr[i]);\r\n");
      out.write("\t\t\t\t\tdocument.getElementById(\"menu\"+idArr[i]).checked=false;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("function changeState2(id){\r\n");
      out.write("\t\tvar uid = parseInt(document.getElementById(\"proxyID\").value);\r\n");
      out.write("\t\tAgentAuthorityDwr.GetAuthority(id,uid,function(data) {\r\n");
      out.write("\t\t\tvar\tidArr = new Array();  \r\n");
      out.write("\t\t\tidArr = data.split(\"-\");\r\n");
      out.write("\t\t\tfor(var i=0;i<idArr.length;i++)\r\n");
      out.write("\t\t\t{\r\n");
      out.write("\t\t\t\tif(document.getElementById(\"menu\"+idArr[i])!=null)\r\n");
      out.write("\t\t\t\t{\r\n");
      out.write("\t\t\t\t\t//alert(document.getElementById(\"menu\"+idArr[i]).checked+\",22,\"+idArr[i]);\r\n");
      out.write("\t\t\t\t\tdocument.getElementById(\"menu\"+idArr[i]).checked=true;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t});\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("//表单提交判断\r\n");
      out.write("function isBlank(str) {\r\n");
      out.write("\tvar blankFlag = true;\r\n");
      out.write("\tif (str.length == 0) \r\n");
      out.write("\t\treturn true;\r\n");
      out.write("\tfor (var i = 0; i < str.length; i++) {\r\n");
      out.write("\t\tif ((str.charAt(i) != \"\") && (str.charAt(i) != \" \")) {\r\n");
      out.write("\t\t\tblankFlag = false;\r\n");
      out.write("\t\t\tbreak;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\treturn blankFlag;\r\n");
      out.write("}\r\n");
      out.write("function CheckNull(theField, fieldName) {\t\r\n");
      out.write("\tif(isBlank(theField.value)){\r\n");
      out.write("\t\talert(fieldName);\r\n");
      out.write("\t\ttheField.focus();\r\n");
      out.write("\treturn 1;\r\n");
      out.write("\t}\r\n");
      out.write("\treturn 0;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("function save(){\r\n");
      out.write("\tif (CheckNull(document.forms['form2'].nickName, '昵称不能为空!')){\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\tform2.submit();\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<Div class=\"title\"><span>\r\n");
      out.write("\t  <label> <input name=\"Submit3\" type=\"button\" class=\"input\" value=\"查看账户\" onclick=\"window.location.href='");
      out.print(request.getContextPath());
      out.write("/member/gameAgentList.do?action=gameAgentList'\" /> </label>\r\n");
      out.write("\t</span>详情</Div>\r\n");
      out.write("      <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_margin\">\r\n");
      out.write("\t   <form id=\"form2\" name=\"form2\" method=\"post\" action=\"");
      out.print(request.getContextPath());
      out.write("/member/gameAgentList.do?action=updateAgent\">\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"120\" class=\"menutop\">项目</td>\r\n");
      out.write("          <td class=\"menutop\">内容</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        \r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">用户名：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input type=\"hidden\" id=\"proxyID\" name=\"proxyID\" class=\"input_width2\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.proxyID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("\t\t  <input name=\"proxyAccounts\" type=\"hidden\" class=\"input_width2\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.proxyAccounts}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("\t\t  ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.proxyAccounts}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">游戏ID：</td>\r\n");
      out.write("          <td class=\"tdlefts\">&nbsp;");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${dto2.gameID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">用户昵称：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"nickName\" id=\"nickName\" type=\"text\" class=\"input_width3\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.nickName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" /></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\t\t    <tr>\r\n");
      out.write("          <td class=\"tdright_new\">用户密码：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"password\" id=\"password\" type=\"password\" class=\"input_width3\" value=\"\" />&nbsp;留空不修改</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\t\t    <tr>\r\n");
      out.write("\t\t      <td class=\"tdright_new\">确认密码：</td>\r\n");
      out.write("\t\t      <td class=\"tdlefts\"><input name=\"password2\" id=\"password2\" type=\"password\" class=\"input_width3\" value=\"\" />\r\n");
      out.write("\t          留空不修改</td>\r\n");
      out.write("         </tr>\r\n");
      out.write("\t\t    <tr>\r\n");
      out.write("\t\t      <td class=\"tdright_new\">钱庄密码：</td>\r\n");
      out.write("\t\t      <td class=\"tdlefts\"><input name=\"bankPass\" id=\"bankPass\" type=\"password\" class=\"input_width3\" value=\"\" />\r\n");
      out.write("\t          留空不修改</td>\r\n");
      out.write("         </tr>\r\n");
      out.write("\t\t    <tr>\r\n");
      out.write("\t\t      <td class=\"tdright_new\">钱庄确认密码：</td>\r\n");
      out.write("\t\t      <td class=\"tdlefts\"><input name=\"bankPass2\" id=\"bankPass2\" type=\"password\" class=\"input_width3\" value=\"\" />\r\n");
      out.write("\t          留空不修改</td>\r\n");
      out.write("         </tr>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t  <td class=\"tdright_new\">银子：</td>\r\n");
      out.write("\t\t  <td class=\"tdlefts\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.gold}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</td>\r\n");
      out.write("\t    </tr>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t <tr>\r\n");
      out.write("          <td class=\"tdright_new\">扣税提成：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><label>\r\n");
      out.write("              <input type=\"text\" name=\"taxRate\" id=\"taxRate\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.taxRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />&nbsp;&nbsp;上限");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sxtaxRate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">股份比例：</td>\r\n");
      out.write("          <td class=\"tdlefts\">\r\n");
      out.write("            <label>\r\n");
      out.write("          <input type=\"text\" name=\"winner\" id=\"winner\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.winner}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />&nbsp;&nbsp;上限");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sxwinner}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">真人佣金：</td>\r\n");
      out.write("          <td class=\"tdlefts\">\r\n");
      out.write("            <label>\r\n");
      out.write("          <input type=\"text\" name=\"brokerage\" id=\"brokerage\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.brokerage}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />&nbsp;&nbsp;上限");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sxbrokerage}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t<td class=\"tdright_new\"></td>\r\n");
      out.write("\t<td class=\"tdlefts\" colspan=\"3\">      \r\n");
      out.write("\t<input name=\"back\" type=\"button\" class=\"input\" id=\"back\" onClick=\"check()\" value=\"确定修改\" /></td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("\t</form>\r\n");
      out.write("      </table>\r\n");
      out.write("\r\n");
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

package org.apache.jsp.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class authRelation_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

String msg="";
int userID = 0;
if(request.getAttribute("msg")!=null){
	msg=request.getAttribute("msg").toString();
}
if(request.getParameter("userID")!= null){
	userID = Integer.parseInt(request.getParameter("userID"));
}
int spreaderID = 3;
int levelID = 0;
if(request.getParameter("spreaderID")!=null){
	    spreaderID = Integer.parseInt(request.getParameter("spreaderID"));
	}
	if(request.getParameter("levelID")!=null){
	    levelID = Integer.parseInt(request.getParameter("levelID"));
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
      out.write("<script type='text/javascript' src='");
      out.print(request.getContextPath());
      out.write("/dwr/engine.js'> </script>\r\n");
      out.write("<script type='text/javascript' src='");
      out.print(request.getContextPath());
      out.write("/dwr/util.js'> </script>\r\n");
      out.write("<script type='text/javascript' src='");
      out.print(request.getContextPath());
      out.write("/dwr/interface/AgentAuthorityDwr.js'> </script>\r\n");
      out.write("<script src=\"../js/jquery.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script language=\"javascript\" src=\"../js/ajax.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<script language=\"javascript\" type=\"text/javascript\">\r\n");
      out.write(" var msg=\"");
      out.print(msg );
      out.write("\";\r\n");
      out.write(" if(msg!='')alert(msg);\r\n");
      out.write("\r\n");
      out.write("$(document).ready(function() {\r\n");
      out.write("\tSetContantFirst();\r\n");
      out.write("});\r\n");
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
      out.write("//页面加载时  显示第一级的所有选项\r\n");
      out.write("function SetContantFirst()\r\n");
      out.write("{\r\n");
      out.write("\tvar fid = 0;\r\n");
      out.write("\tvar uid = parseInt(document.getElementById(\"userid\").value);\r\n");
      out.write("\tAgentAuthorityDwr.SetContantFirst(parseInt(fid),uid,function(data) {\r\n");
      out.write("\t\tdwr.util.setValue(\"level1\", data,{ escapeHtml:false });\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
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
      out.write("function changeSelectState(id,cdname)\r\n");
      out.write("{\r\n");
      out.write("\tvar uid = parseInt(document.getElementById(\"userid\").value);\r\n");
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
      out.write("\t\tvar uid = parseInt(document.getElementById(\"userid\").value);\r\n");
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
      out.write("\t\tvar uid = parseInt(document.getElementById(\"userid\").value);\r\n");
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
      out.write("function save(){\r\n");
      out.write("\r\n");
      out.write("\tform2.submit();\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\t<div class=\"title\">权限</Div>\r\n");
      out.write("\t<form id=\"form2\" name=\"form2\" method=\"post\" action=\"");
      out.print(request.getContextPath());
      out.write("/member/gameAgentList.do?action=updateauthRelation\" onsubmit=\"return confirm('確定?');\">\r\n");
      out.write("<input name=\"spreaderID\" type=\"hidden\" value=\"");
      out.print(spreaderID);
      out.write("\" /><input name=\"levelID\" type=\"hidden\" value=\"");
      out.print(levelID);
      out.write("\" /><input name=\"sxaccounts\" type=\"hidden\" value=\"");
      out.print(accounts);
      out.write("\" />\r\n");
      out.write("<input type=\"hidden\" name=\"type\" value=\"1\" />\r\n");
      out.write("</form>\r\n");
      out.write("\t\t<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_margin\">\r\n");
      out.write("          <tr align=\"center\">\r\n");
      out.write("            <td class=\"tdlefts\">权限名</td>\r\n");
      out.write("          </tr>\r\n");
      out.write("\t \t<td colspan=\"3\" ><div id=\"level1\"></div></td>\r\n");
      out.write("        </table>\r\n");
      out.write("\t\t<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_margin\">\r\n");
      out.write("\t\t    <tr><td height=\"5\"></td></tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("                <td align=\"center\"><input type=\"hidden\" name=\"userid\" id=\"userid\" value=\"");
      out.print(userID);
      out.write("\" /><input name=\"back\" type=\"button\" class=\"input\" id=\"back\" onClick=\"save()\" value=\"确定修改\" /><input type=\"button\" name=\"alls\" id=\"alls\" class=\"input\" value=\"全选\" />\r\n");
      out.write("                  <input  type=\"button\" name=\"allno\" id=\"allno\" class=\"input\" value=\"反选\" />\r\n");
      out.write("\t\t\t\t");
      out.write("</td>\r\n");
      out.write("            </tr>\r\n");
      out.write("\t  </table>\r\n");
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

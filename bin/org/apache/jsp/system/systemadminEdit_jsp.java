package org.apache.jsp.system;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class systemadminEdit_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.release();
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
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../yanzheng.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>无标题文档</title>\r\n");
      out.write("<link href=\"../css/main.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("\r\n");
      out.write("<script type='text/javascript' src='");
      out.print(request.getContextPath());
      out.write("/dwr/engine.js'> </script>\r\n");
      out.write("<script type='text/javascript' src='");
      out.print(request.getContextPath());
      out.write("/dwr/util.js'> </script>\r\n");
      out.write("<script type='text/javascript' src='");
      out.print(request.getContextPath());
      out.write("/dwr/interface/AuthorityDwr.js'> </script>\r\n");
      out.write("<script src=\"../js/jquery.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script language=\"javascript\" src=\"../js/ajax.js\"></script>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");

   int userid = 0;
	if(request.getParameter("id")!= null){
		userid = Integer.parseInt(request.getParameter("id"));
	}
	String msg="";
	if(request.getAttribute("msg")!=null){
		msg= request.getAttribute("msg").toString();
	}
	

      out.write("\r\n");
      out.write("<script language=\"javascript\" type=\"text/javascript\">\r\n");
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
      out.write("\r\n");
      out.write("function CheckNull(theField, fieldName) {\t\r\n");
      out.write("\tif(isBlank(theField.value)){\r\n");
      out.write("\t\talert(fieldName);\r\n");
      out.write("\t\ttheField.focus();\r\n");
      out.write("\treturn 1;\r\n");
      out.write("\t}\r\n");
      out.write("\treturn 0;\r\n");
      out.write("}\r\n");
      out.write("function save(){\r\n");
      out.write("\t\r\n");
      out.write("\tif (CheckNull(document.forms['form2'].password, '密码不能为空!')){\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\tform2.submit();\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write("\r\n");
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
      out.write("       //弹出友好提示\r\n");
      out.write("       var msg=\"");
      out.print(msg );
      out.write("\";\r\n");
      out.write("       if(msg!='')alert(msg);\r\n");
      out.write("\r\n");
      out.write("//页面加载时  显示第一级的所有选项\r\n");
      out.write("function SetContantFirst()\r\n");
      out.write("{\r\n");
      out.write("\tvar fid = 0;\r\n");
      out.write("\tvar uid = parseInt(document.getElementById(\"userid\").value);\r\n");
      out.write("\tAuthorityDwr.SetContantFirst(parseInt(fid),uid,function(data) {\r\n");
      out.write("\t\tdwr.util.setValue(\"level1\", data,{ escapeHtml:false });\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("//加载二级选项\r\n");
      out.write("function SetContantSecond(fid,open)\r\n");
      out.write("{\r\n");
      out.write("\tvar uid = parseInt(document.getElementById(\"userid\").value);\r\n");
      out.write("\tif(document.getElementById(\"level2-\"+fid).innerHTML!=''){\r\n");
      out.write("\t\tdwr.util.setValue(\"level2-\"+fid,'',{ escapeHtml:false });\r\n");
      out.write("\t\tdocument.getElementById(\"level2-\"+fid).style.display=\"none\";\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\tAuthorityDwr.SetContantSecond(parseInt(fid),uid,function(data) {\r\n");
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
      out.write("\tvar uid = parseInt(document.getElementById(\"userid\").value);\r\n");
      out.write("\t//能级管理选项显示\r\n");
      out.write("\tAuthorityDwr.SetContantThird(parseInt(fid),uid,function(data) {\r\n");
      out.write("\t\tif(data!='')\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\tdwr.util.setValue(\"level3-\"+fid, data,{ escapeHtml:false });\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("//加载四级选项\r\n");
      out.write("function SetContantFourth(fid,step)\r\n");
      out.write("{\r\n");
      out.write("\tvar uid = parseInt(document.getElementById(\"userid\").value);\r\n");
      out.write("\t//能级管理选项显示\r\n");
      out.write("\tAuthorityDwr.SetContantFourth(parseInt(fid),uid,function(data) {\r\n");
      out.write("\t\tif(data!='')\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\tdwr.util.setValue(\"level4-\"+fid, data,{ escapeHtml:false });\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("//加载四级选项\r\n");
      out.write("function SetContantFifth(fid,step)\r\n");
      out.write("{\r\n");
      out.write("\tvar uid = parseInt(document.getElementById(\"userid\").value);\r\n");
      out.write("\t//能级管理选项显示\r\n");
      out.write("\tAuthorityDwr.SetContantFifth(parseInt(fid),uid,function(data) {\r\n");
      out.write("\t\tif(data!='')\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\tdwr.util.setValue(\"level5-\"+fid, data,{ escapeHtml:false });\r\n");
      out.write("\t\t}\r\n");
      out.write("\t});\r\n");
      out.write("}\r\n");
      out.write("function changeSelectState(id,cdname)\r\n");
      out.write("{\r\n");
      out.write("\tvar uid = parseInt(document.getElementById(\"userid\").value);\r\n");
      out.write("\tif(document.getElementById(\"menu\"+id).checked==true)\r\n");
      out.write("\t{\r\n");
      out.write("\t\t//删除选中的权限和级联的权限\r\n");
      out.write("\t\tAuthorityDwr.GetAuthority(id,uid,function(data) {\r\n");
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
      out.write("\t\tAuthorityDwr.ReleaseAuthority(id,uid,function(data) {\r\n");
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
      out.write("\t\tAuthorityDwr.ReleaseAuthority(id,uid,function(data) {\r\n");
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
      out.write("\t\tAuthorityDwr.GetAuthority(id,uid,function(data) {\r\n");
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
      out.write("function save(){\r\n");
      out.write("\t\r\n");
      out.write("\tif (CheckNull(document.getElementById(\"password\"), '密码不能为空!')){\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\tif(document.getElementById(\"password\").value!=document.getElementById(\"password1\").value){\r\n");
      out.write("\t    alert(\"两次密码输入不一致，请重新输入\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\tform2.submit();\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("<body>\r\n");
      out.write("\t<Div class=\"title\">账户管理—编辑</Div>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("      <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_margin\">\r\n");
      out.write("\t  <form id=\"form2\" name=\"form2\" method=\"post\" action=\"");
      out.print(request.getContextPath());
      out.write("/system/adminList.do?action=update\">\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"120\" class=\"menutop\">项目</td>\r\n");
      out.write("          <td class=\"menutop\">内容</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">用户名：</td>\r\n");
      out.write("          <td class=\"tdright\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${ADMIN.userName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("<input type=\"hidden\" name=\"id\" value=\"");
      out.print(userid);
      out.write("\" /></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">密码：</td>\r\n");
      out.write("          <td class=\"tdright\"><label>\r\n");
      out.write("            <input name=\"password\" id=\"password\" type=\"text\" class=\"input_width2\" value=\"\" />\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("          <td class=\"tdright_new\">确认密码：</td>\r\n");
      out.write("          <td class=\"tdright\"><label>\r\n");
      out.write("            <input name=\"password1\" id=\"password1\" type=\"text\" class=\"input_width2\" value=\"\" />\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("      </table>\r\n");
      out.write("\r\n");
      out.write("<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_margin\">\r\n");
      out.write("    <tr>\r\n");
      out.write("      <td colspan=\"2\">\r\n");
      out.write("\t  ");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("</td>\r\n");
      out.write("    </tr>\r\n");
      out.write("\t<tr>\r\n");
      out.write("\t<td></td>\r\n");
      out.write("\t<td class=\"tdright\"><label><input type=\"hidden\" id=\"userid\" name=\"userid\" value=\"");
      out.print(userid );
      out.write("\">           \r\n");
      out.write("\t<input name=\"back\" type=\"button\" class=\"input\" id=\"back\" onClick=\"save()\" value=\"确定修改\" />\r\n");
      out.write("                   ");
      if (_jspx_meth_c_005fif_005f1(_jspx_page_context))
        return;
      out.write("</label></td>\r\n");
      out.write("\t</tr>\r\n");
      out.write("</table>\r\n");
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

  private boolean _jspx_meth_c_005fif_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /system/systemadminEdit.jsp(312,3) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${USER.roleId == 1}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /system/systemadminEdit.jsp(312,3) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setVar("true");
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<div id=\"level1\"></div>\r\n");
        out.write("\t  ");
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

  private boolean _jspx_meth_c_005fif_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent(null);
    // /system/systemadminEdit.jsp(319,19) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${USER.roleId == 1}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /system/systemadminEdit.jsp(319,19) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setVar("true");
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<input type=\"button\" name=\"alls\" id=\"alls\" class=\"input\" value=\"全选\" />\r\n");
        out.write("                  <input  type=\"button\" name=\"allno\" id=\"allno\" class=\"input\" value=\"反选\" />");
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

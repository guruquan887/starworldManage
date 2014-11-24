package org.apache.jsp.game;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class createMachine_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("  ");

	//判断是否已登陆，返回登陆页面
	if (session.getAttribute("USER") == null){
	out.print("<script>alert('对不起，您还没有登录本系统或者您已经超过登录时间!');  window.target='_parent';window.location.href='../login.jsp';</script>");
	return;
	}

	String msg="";
	if(request.getAttribute("msg")!=null){
		msg=(String)request.getAttribute("msg");
	}
   
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("function CheckNull(theField, fieldName) {\t\r\n");
      out.write("\tif(isBlank(theField.value)){\r\n");
      out.write("\t\talert(fieldName);\r\n");
      out.write("\t\ttheField.focus();\r\n");
      out.write("\t\treturn 1;\r\n");
      out.write("\t}\r\n");
      out.write("\treturn 0;\r\n");
      out.write("}\r\n");
      out.write("function isNumber(oNum){\r\n");
      out.write("\r\n");
      out.write(" if(!oNum) return false; //line:160\r\n");
      out.write(" if(oNum.value==\"\") return false;\r\n");
      out.write(" var vv = oNum.value.replace(/[^\\d]/g,'');\r\n");
      out.write(" var strP=/^\\d+$/;\r\n");
      out.write("  if (!isNaN(oNum.value)) {\r\n");
      out.write("        return true;\r\n");
      out.write("    } else {\r\n");
      out.write("        alert(\"请输入数字\");\r\n");
      out.write("        oNum.value=vv;\r\n");
      out.write(" \t\toNum.focus();\r\n");
      out.write(" \t\treturn false; \r\n");
      out.write("    }\r\n");
      out.write("}\r\n");
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
      out.write("function IsIp(str)\r\n");
      out.write("{\r\n");
      out.write("    var reg = /^((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)$/;\r\n");
      out.write("    var r = str.match(reg);\r\n");
      out.write("    if (r == null) {\r\n");
      out.write("        return false;\r\n");
      out.write("    }\r\n");
      out.write("    return true;\r\n");
      out.write("}\r\n");
      out.write("        function CheckFormInfo()\r\n");
      out.write("        {\r\n");
      out.write("            var information = document.myFormInfo.Information.value;\r\n");
      out.write("            var dBAddr = document.myFormInfo.DBAddr.value;\r\n");
      out.write("            var dBPort = document.myFormInfo.DBPort.value;\r\n");
      out.write("            var dBUser = document.myFormInfo.DBUser.value;\r\n");
      out.write("            var dBPassword = document.myFormInfo.DBPassword.value;\r\n");
      out.write("            var re = /^(-?[1-9][0-9]*|0)$/; \r\n");
      out.write("            if(information.trim()==\"\")\r\n");
      out.write("            {\r\n");
      out.write("                alert(\"机器名称不能为空！\");\r\n");
      out.write("                return false;\r\n");
      out.write("            }\r\n");
      out.write("            if(dBAddr.trim()==\"\")\r\n");
      out.write("            {\r\n");
      out.write("                alert(\"地址不能为空！\");\r\n");
      out.write("                return false;\r\n");
      out.write("            }\r\n");
      out.write("            else\r\n");
      out.write("            {\r\n");
      out.write("                if(!IsIp(dBAddr))\r\n");
      out.write("                {\r\n");
      out.write("                    alert(\"地址输入非法！\");\r\n");
      out.write("                    return false;\r\n");
      out.write("                }\r\n");
      out.write("            }\r\n");
      out.write("            if(dBPort.trim()==\"\")\r\n");
      out.write("            {\r\n");
      out.write("                alert(\"端口不能为空！\");\r\n");
      out.write("                return false;\r\n");
      out.write("            }\r\n");
      out.write("            else\r\n");
      out.write("            {\r\n");
      out.write("                if (!re.test(dBPort.trim()))\r\n");
      out.write("                {\r\n");
      out.write("                    alert(\"端口必须为正整数！\");\r\n");
      out.write("                    return false;\r\n");
      out.write("                }\r\n");
      out.write("                else\r\n");
      out.write("                {\r\n");
      out.write("                    if(dBPort.trim()<0)\r\n");
      out.write("                    {\r\n");
      out.write("                        alert(\"端口必须为正整数！\");\r\n");
      out.write("                        return false;\r\n");
      out.write("                    }\r\n");
      out.write("                }\r\n");
      out.write("            }\r\n");
      out.write("            if(dBUser.trim()==\"\")\r\n");
      out.write("            {\r\n");
      out.write("                alert(\"用户名不能为空！\");\r\n");
      out.write("                return false;\r\n");
      out.write("            }\r\n");
      out.write("            if(dBPassword.trim()==\"\")\r\n");
      out.write("            {\r\n");
      out.write("                alert(\"密码不能为空！\");\r\n");
      out.write("                return false;\r\n");
      out.write("            }\r\n");
      out.write("        }\r\n");
      out.write("\t</script>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>无标题文档</title>\r\n");
      out.write("<link href=\"../css/main.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<Div class=\"title\">新建机器</Div>\r\n");
      out.write("\t\r\n");
      out.write("    <form id=\"myFormInfo\" name=\"myFormInfo\" method=\"post\" onsubmit=\"return CheckFormInfo()\" action=\"");
      out.print(request.getContextPath() );
      out.write("/game/gameSystem.do?action=addMachine\">\r\n");
      out.write("      <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_margin\">\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"120\" class=\"menutop\">项目</td>\r\n");
      out.write("          <td class=\"menutop\">内容</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">机器名称：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><label>\r\n");
      out.write("          <input name=\"Information\" id=\"Information\" type=\"text\" maxlength=\"10\" class=\"input_width2\" />\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">地址：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><label>\r\n");
      out.write("            <input name=\"DBAddr\" id=\"DBAddr\" type=\"text\" maxlength=\"10\" class=\"input_width2\" />\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">端口：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"DBPort\" onKeyUp=\"isNumber(this)\" id=\"DBPort\" maxlength=\"14\" type=\"text\" class=\"input_width2\" /></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">用户名：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"DBUser\" id=\"DBUser\" maxlength=\"14\" type=\"text\" class=\"input_width2\" /></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">密码：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"DBPassword\" id=\"DBPassword\" maxlength=\"14\" type=\"text\" class=\"input_width2\" /></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">机器码：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"MachineID\" id=\"MachineID\" maxlength=\"14\" type=\"text\" class=\"input_width2\" /></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">&nbsp;</td>\r\n");
      out.write("          <td class=\"tdright\"><label>\r\n");
      out.write("            <input name=\"Submit\" type=\"submit\" class=\"input\" value=\"确定添加\" />\r\n");
      out.write("            <input name=\"Submit2\" type=\"reset\" class=\"input\" value=\"重新填写\" />\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("      </table>\r\n");
      out.write("</form>\r\n");
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

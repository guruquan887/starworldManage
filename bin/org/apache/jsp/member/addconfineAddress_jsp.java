package org.apache.jsp.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class addconfineAddress_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");

String [] ids = {};
String ss = "";
if(request.getParameterValues("checkbox")!=null){
   ids = request.getParameterValues("checkbox");
   for(int i=0;i<ids.length;i++){
	   ss += ids[i]+",";
	}
   System.out.println("造型成字符串的ss:"+ss);
}
String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}

      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>无标题文档</title>\r\n");
      out.write("<link href=\"../css/main.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("</head>\r\n");
      out.write("<script language=\"javascript\" src=\"");
      out.print(request.getContextPath());
      out.write("/js/js.js\"></script>\r\n");
      out.write("<script type='text/javascript' src='");
      out.print(request.getContextPath());
      out.write("/js/jquery.js'></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(request.getContextPath() );
      out.write("/js/jquery-latest.pack.js\"></script>\r\n");
      out.write("<script language=\"javascript\">\r\n");
      out.write("var msg=\"");
      out.print(msg );
      out.write("\";\r\n");
      out.write("if(msg!='')alert(msg);\r\n");
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
      out.write(" \r\n");
      out.write(" function isDate(str) \r\n");
      out.write("{\r\n");
      out.write("    var r = str.match(/^(\\d{1,4})(-|\\/)(\\d{1,2})\\2(\\d{1,2})$/);\r\n");
      out.write("    if (r == null) return false;\r\n");
      out.write("    var d = new Date(r[1], r[3] - 1, r[4]);\r\n");
      out.write("    return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4]);\r\n");
      out.write("}\r\n");
      out.write(" \r\n");
      out.write("        function CheckFormInfo()\r\n");
      out.write("        {\r\n");
      out.write("            var addrString = document.getElementById(\"addrString\").value;\r\n");
      out.write("            var enjoinOverDate = document.getElementById(\"in_enjoinOverDate\").value;\r\n");
      out.write("            if(addrString==\"\")\r\n");
      out.write("            {\r\n");
      out.write("                alert(\"地址不能为空！\");\r\n");
      out.write("                return false;\r\n");
      out.write("            } \r\n");
      out.write("            if(addrString.length()>15)\r\n");
      out.write("            {\r\n");
      out.write("                alert(\"地址字符长度不能超过31个字符！\");\r\n");
      out.write("                return false;\r\n");
      out.write("            }      \r\n");
      out.write("            if(enjoinOverDate!=\"\"&&!isDate(enjoinOverDate))\r\n");
      out.write("            {\r\n");
      out.write("                alert(\"日期格式不正确！\");\r\n");
      out.write("                return false;\r\n");
      out.write("            }                      \r\n");
      out.write("        }\r\n");
      out.write("</script>\r\n");
      out.write("<body>\r\n");
      out.write("\t<div class=\"title\"><span>\r\n");
      out.write("\t</span>新增—限制地址</div>\r\n");
      out.write("    <form id=\"myFormInfo\" name=\"myFormInfo\" method=\"post\" action=\"");
      out.print(request.getContextPath());
      out.write("/member/ConfineList.do?action=addconfineAddress\" onsubmit=\"return CheckFormInfo()\">\r\n");
      out.write("      <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_margin\">\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"120\" class=\"menutop\">项目</td>\r\n");
      out.write("          <td class=\"menutop\">内容</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"menutop\">地址：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"addrString\" id=\"addrString\" type=\"text\" /></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"menutop\">用户登录：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"in_EnjoinLogon\" id=\"in_EnjoinLogon\" type=\"radio\" value=\"0\" checked=\"checked\"/><label for=\"in_EnjoinLogon0\">正常</label>\r\n");
      out.write("                    <input name=\"in_EnjoinLogon\" id=\"in_EnjoinLogon\" type=\"radio\" value=\"1\"/><label for=\"in_EnjoinLogon1\">冻结</label>                                                  \r\n");
      out.write("\t            </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("          <td class=\"menutop\">用户注册：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"in_EnjoinRegister\" id=\"in_EnjoinRegister\" type=\"radio\" value=\"0\" checked=\"checked\"/><label for=\"in_EnjoinLogon0\">正常</label>\r\n");
      out.write("                    <input name=\"in_EnjoinRegister\" id=\"in_EnjoinRegister\" type=\"radio\" value=\"1\"/><label for=\"in_EnjoinLogon1\">冻结</label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("         <tr>\r\n");
      out.write("          <td class=\"menutop\">失效時間：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"in_enjoinOverDate\" id=\"in_enjoinOverDate\" onclick=\"setDay(this);\"  type=\"text\" />   警告：  不填写默认永久限制   </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\t\t <tr>\r\n");
      out.write("\t            <td class=\"menutop\">备注：</td>\r\n");
      out.write("\t            <td class=\"tdlefts\">\r\n");
      out.write("\t                <input name=\"in_CollectNote\" id=\"in_CollectNote\" type=\"text\" class=\"text\" maxlength=\"32\" style=\"width:500px;\" value=\"\" />    \r\n");
      out.write("\t            </td>\r\n");
      out.write("\t        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">&nbsp;</td>\r\n");
      out.write("          <td class=\"tdright\"><label>\r\n");
      out.write("            <input name=\"Submit\" type=\"submit\" class=\"input\" value=\"确定\" />\r\n");
      out.write("            <input name=\"Submit2\" type=\"reset\" class=\"input\" value=\"重置\" />\r\n");
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

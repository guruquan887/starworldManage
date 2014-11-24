package org.apache.jsp.shoping;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class FileUploadInner1_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=GBK");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');

String msg="";
if(request.getAttribute("message")!=null)
	msg=request.getAttribute("message").toString();
String photourl="";
if(request.getAttribute("photourl")!=null){
	photourl=(String)request.getAttribute("photourl");
}
if(request.getParameter("purl")!=null){
	String tmp=request.getParameter("purl");
	if(tmp.length()>0)
		photourl=tmp;
}
System.out.println(photourl);

      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"/css/appform.css\">\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\">\r\n");
      out.write("<script language=\"javascript\">\r\n");
      out.write("var mymsg=\"");
      out.print(msg);
      out.write("\";\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("function checkValidInt(text)\r\n");
      out.write("{\r\n");
      out.write("\tvar len = text.length;\r\n");
      out.write("\tvar valid = true;\r\n");
      out.write("\tvar dot = false;\r\n");
      out.write("\tvar reg = \"0123456789\";\r\n");
      out.write("\tif(len < 1 || len > 9)\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\tfor(var i = 0; i < len; i++)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tvar ch = text.charAt(i);\r\n");
      out.write("\t\tvar j;\r\n");
      out.write("\t\tfor(j = 0; j < reg.length; j++)\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t\tif(ch == reg.charAt(j))\r\n");
      out.write("\t\t\t\tbreak;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(j == reg.length)\r\n");
      out.write("\t\t\treturn false;\t\t\t\t\r\n");
      out.write("\t}\r\n");
      out.write("\treturn true;\r\n");
      out.write("}\r\n");
      out.write("function upfile(){\r\n");
      out.write("\t//alert(document.all.photofile+\",\"+document.all.photofile.value);\r\n");
      out.write("\tif(document.all.photofile!=\"\"){\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tdocument.all.uploadForm.submit();\r\n");
      out.write("\t\tuploadBegin();\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("}\r\n");
      out.write("function uploadBegin(){\r\n");
      out.write("theFeats = \"height=360,width=370,location=no,menubar=no,resizable=no,scrollbars=no,status=no,toolbar=no\";\r\n");
      out.write("\tstrAppVersion = navigator.appVersion;\r\n");
      out.write("    if (strAppVersion.indexOf('MSIE') != -1 && strAppVersion.substr(strAppVersion.indexOf('MSIE')+5,1) > 4)\r\n");
      out.write("\t{\r\n");
      out.write("\t\twinstyle = \"dialogWidth=312px; dialogHeight:220px; center:yes\";\r\n");
      out.write("\t\twindow.showModelessDialog(\"");
      out.print(request.getContextPath() );
      out.write("/jsp/upload/progressbar.jsp\",window,winstyle) ;\r\n");
      out.write("\t}\r\n");
      out.write("\treturn true;\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
if(photourl.length()==0){ 
      out.write("\r\n");
      out.write("\r\n");
      out.write("<table border=\"0\" width=\"100%\" >\r\n");
      out.write("<form action=\"");
      out.print(request.getContextPath() );
      out.write("/shoping/uploadphoto.do?no=1\" method=\"post\"\r\n");
      out.write("\t\t\t\tenctype=\"multipart/form-data\" name=\"uploadForm\" id=\"uploadForm\">\r\n");
      out.write("<tr>\r\n");
      out.write("<td align=\"left\"><input type=\"file\" name=\"photofile\" id=\"photofile\" style=\"width:300px\">\r\n");
      out.write("<input name=\"button\" type=\"button\" class=\"button\" onClick=\"upfile();\" value=\" 上 传 \">\r\n");
      out.write("\r\n");
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("<tr style=\"display:none\">\r\n");
      out.write("<td>\r\n");
      out.write("<input type=\"hidden\" name=\"articleid\" id=\"articleid\" value=\"\">\r\n");
      out.write("</td>\r\n");
      out.write("</tr>\r\n");
      out.write("</table>\r\n");
}else{ 
      out.write("\r\n");
      out.write("<script language=\"javascript\">\r\n");
      out.write("parent.setphotourl1(\"");
      out.print(photourl);
      out.write("\");\r\n");
      out.write("</script>\r\n");
      out.write("<a href=\"");
      out.print(request.getContextPath());
      out.write("/incoming/");
      out.print(photourl );
      out.write("\" target=\"_blank\"><img src=\"");
      out.print(request.getContextPath());
      out.write("/incoming/");
      out.print(photourl );
      out.write("\" width=\"116\" height=\"76\" border=\"0\"></a>\r\n");
      out.write("<a href=\"../gameshop/FileUploadInner1.jsp\"><strong>重新上传</strong></a>\r\n");
} 
      out.write("\r\n");
      out.write("<script language=\"javascript\">\r\n");
      out.write("\r\n");
      out.write("if(mymsg!=\"\"){\r\n");
      out.write("\talert(mymsg);\r\n");
      out.write("\t\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
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

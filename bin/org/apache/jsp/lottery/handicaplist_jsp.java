package org.apache.jsp.lottery;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class handicaplist_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fstep_005fend_005fbegin;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fstep_005fend_005fbegin = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.release();
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

      out.write('\r');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../yanzheng.jsp", out, false);
      out.write("\r\n");
      out.write("\r\n");
      out.write(" \r\n");

	int recordIndex=1;
	int pageIndex=1;
	int pageSize=10;
	int roomId = 1;
	String pperoidnum="";
	try{
	String userName = "";
	String password = "";
        if(request.getSession().getAttribute("username")!=null){
            userName = (String)request.getSession().getAttribute("username");
  		}
		if(request.getSession().getAttribute("password")!=null){
            password = (String)request.getSession().getAttribute("password");
  		}
		if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("pageIndex")!=null){
			pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
		}
		if(request.getParameter("roomId")!=null){
			roomId=Integer.parseInt(request.getParameter("roomId"));
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
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>盘口设定管理</title>\r\n");
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
      out.write("<script language=\"javascript\" src=\"../js/swfobject.js\" type=\"text/javascript\"></script>\r\n");
      out.write("<script language=\"javascript\" type=\"text/javascript\">\r\n");
      out.write("var msg=\"");
      out.print(msg );
      out.write("\";\r\n");
      out.write("if(msg!='')alert(msg);\r\n");
      out.write("var tone=encodeURI(\"");
      out.print(pperoidnum );
      out.write("\");\r\n");
      out.write("var myDate = null;\r\n");
      out.write("myDate =  new Date();\r\n");
      out.write("//var year = myDate.getYear()+'-'+(myDate.getMonth()+1)+'-'+(myDate.getDate())+;\r\n");
      out.write("\r\n");
      out.write("function jumppage(value)\r\n");
      out.write("   {\r\n");
      out.write("      window.location.href=\"handicapList.do?action=handicapList&curPage=\"+value+\"&roomId=");
      out.print(roomId);
      out.write("&pperoidnum=\"+document.getElementById(\"pperoidnum\").value;\r\n");
      out.write("   }\r\n");
      out.write("   \r\n");
      out.write("function changepage(pageNo){\r\n");
      out.write("\t     document.location.href=\"handicapList.do?action=handicapList&curPage=\"+pageNo+\"&roomId=");
      out.print(roomId );
      out.write("&pperoidnum=\"+document.getElementById(\"pperoidnum\").value;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function queryrooms(){\r\n");
      out.write("\t\t var roomId=document.all.roomId.options[document.all.roomId.selectedIndex].value;\r\n");
      out.write("\t\t document.location.href=\"handicapList.do?action=handicapList&roomId=\"+roomId+\"&pperoidnum=\"+document.getElementById(\"pperoidnum\").value;\r\n");
      out.write("}\r\n");
      out.write("function search(){\r\n");
      out.write("\tdocument.location.href=\"handicapList.do?action=handicapList&pperoidnum=\"+document.getElementById(\"pperoidnum\").value+\"&roomId=");
      out.print(roomId);
      out.write("\"\r\n");
      out.write("}\r\n");
      out.write("function checknum(){\r\n");
      out.write("var arr = new Array(6);\r\n");
      out.write("var arropen = new Array(6);\r\n");
      out.write("for(var i =1 ;i<7;i++){\r\n");
      out.write("    var obj = document.getElementById(\"_time\"+i);\r\n");
      out.write("\tvar bjvalue = obj.value;\r\n");
      out.write("\tif(bjvalue==\"\"){\r\n");
      out.write("\t   alert(\"请输入时间!\");\r\n");
      out.write("\t   return false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tarr[i]=bjvalue;\r\n");
      out.write("\t}\r\n");
      out.write("for(var j =1 ;j<7;j++){\r\n");
      out.write("    var openobj = document.getElementById(\"_opentime\"+j);\r\n");
      out.write("\tvar openobjvalue = openobj.value;\r\n");
      out.write("\tif(openobjvalue==\"\"){\r\n");
      out.write("\t   alert(\"请输入时间!\");\r\n");
      out.write("\t   return false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tarropen[j]=openobjvalue;\r\n");
      out.write("\t}\r\n");
      out.write("\tif(arr[2]>12||arr[2]<1){\r\n");
      out.write("\t    alert(\"月份不合法!\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t\t}\t\r\n");
      out.write("\tif(arr[3]>31||arr[3]<1){\r\n");
      out.write("\t\talert(\"日期不合法!\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\tif(arr[4]>24||arr[4]<0){\r\n");
      out.write("\t\talert(\"小时不合法!\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\tif(arr[5]>59||arr[5]<0){\r\n");
      out.write("\t\talert(\"分钟不合法!\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\tif(arr[6]>59||arr[6]<0){\r\n");
      out.write("\t\talert(\"秒数不合法!\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\tif(arropen[2]>12||arropen[2]<1){\r\n");
      out.write("\t    alert(\"月份不合法!\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t\t}\t\r\n");
      out.write("\tif(arropen[3]>31||arropen[3]<1){\r\n");
      out.write("\t\talert(\"日期不合法!\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\tif(arropen[4]>24||arropen[4]<0){\r\n");
      out.write("\t\talert(\"小时不合法!\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\tif(arropen[5]>59||arropen[5]<0){\r\n");
      out.write("\t\talert(\"分钟不合法!\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\tif(arropen[6]>59||arropen[6]<0){\r\n");
      out.write("\t\talert(\"秒数不合法!\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\tfor(var k=1;k<7;k++){\r\n");
      out.write("\t\tif(arr[k]>arropen[k]){\r\n");
      out.write("\t    alert(\"开奖的时间要大于开盘时间\");\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse {\r\n");
      out.write("\t\treturn true;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t}\r\n");
      out.write("\treturn true;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function isNumber(oNum){\r\n");
      out.write(" if(!oNum) return false; //line:160\r\n");
      out.write(" if(oNum.value==\"\") return false;\r\n");
      out.write(" var vv = oNum.value.replace(/[^\\d]/g,'');\r\n");
      out.write(" var strP=/^\\d+$/;\r\n");
      out.write("  if (!isNaN(oNum.value)) {\r\n");
      out.write("        return true;\r\n");
      out.write("    } \r\n");
      out.write("  else{\r\n");
      out.write("        alert(\"请输入数字\");\r\n");
      out.write("        oNum.value=vv;\r\n");
      out.write(" \t\toNum.focus();\r\n");
      out.write(" \t\treturn false; \r\n");
      out.write("    }\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("  \r\n");
      out.write("  <form id=\"form1\" name=\"form1\" method=\"post\" onSubmit=\"return checknum()\" action=\"handicapList.do?action=addhandicap&roomId=");
      out.print(roomId );
      out.write("\">\r\n");
      out.write("    <table width=\"100%\" border=\"0\" cellpadding=\"2\" cellspacing=\"1\" class=\"border\">\r\n");
      out.write("    <tr>\r\n");
      out.write("        <td width=\"9%\" height=\"25\" class=\"menutop\">期数        </td>\r\n");
      out.write("\t\t<td width=\"38%\" height=\"25\" class=\"menutop\">开盘时间        </td>\r\n");
      out.write("\t\t<td width=\"40%\" class=\"menutop\">开奖时间</td>\r\n");
      out.write("\t\t<td width=\"13%\"class=\"menutop\">功能        </td>\r\n");
      out.write("    </tr>\r\n");
      out.write("    <tr class=\"tdcenter\">\r\n");
      out.write("      <td height=\"25\"><font color=\"#FF0000\"><input size=\"10\" name=\"peroidnum\" type=\"text\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${peroidnum}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></font></td>\r\n");
      out.write("      <td height=\"25\"><input size=\"1\" id=\"_time1\" style=\"width:35px\"  maxlength=\"4\" onKeyUp=\"isNumber(this)\"  name=\"year\" type=\"text\">\r\n");
      out.write("      年&nbsp;<strong>-</strong>&nbsp;\r\n");
      out.write("\t  <input size=\"1\" id=\"_time2\" style=\"width:25px\" onKeyUp=\"isNumber(this)\" maxlength=\"2\" name=\"month\" type=\"text\">\r\n");
      out.write("\t  月&nbsp;<strong>-</strong>&nbsp;\r\n");
      out.write("\t  <input size=\"1\" id=\"_time3\" style=\"width:25px\" onKeyUp=\"isNumber(this)\" maxlength=\"2\" name=\"day\" type=\"text\">日&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t  <input size=\"1\" id=\"_time4\" style=\"width:25px\" onKeyUp=\"isNumber(this)\" maxlength=\"2\" name=\"hour\" type=\"text\">时&nbsp;<strong>:</strong>&nbsp;\r\n");
      out.write("\t  <input size=\"1\" id=\"_time5\" style=\"width:25px\" onKeyUp=\"isNumber(this)\" maxlength=\"2\" name=\"minute\" type=\"text\">分&nbsp;<strong>:</strong>&nbsp;\r\n");
      out.write("\t  <input size=\"1\" id=\"_time6\" style=\"width:25px\" onKeyUp=\"isNumber(this)\" maxlength=\"2\" name=\"second\" type=\"text\">秒</td>\r\n");
      out.write("     <td><input style=\"width:35px\" id=\"_opentime1\" maxlength=\"4\" onKeyUp=\"isNumber(this)\" size=\"1\" name=\"year1\" type=\"text\">\r\n");
      out.write("     年&nbsp;<strong>-</strong>&nbsp;\r\n");
      out.write("\t  <input size=\"1\" id=\"_opentime2\" style=\"width:25px\" onKeyUp=\"isNumber(this)\" maxlength=\"2\" name=\"month1\" type=\"text\">\r\n");
      out.write("\t  月&nbsp;<strong>-</strong>&nbsp;\r\n");
      out.write("\t  <input size=\"1\" id=\"_opentime3\" style=\"width:25px\" onKeyUp=\"isNumber(this)\" maxlength=\"2\" name=\"day1\" type=\"text\">\r\n");
      out.write("\t  日&nbsp;&nbsp;&nbsp;\r\n");
      out.write("\t  <input size=\"1\" id=\"_opentime4\" style=\"width:25px\" onKeyUp=\"isNumber(this)\" maxlength=\"2\" name=\"hour1\" type=\"text\">\r\n");
      out.write("\t  &nbsp;时<strong>:</strong>&nbsp;\r\n");
      out.write("\t  <input size=\"1\" id=\"_opentime5\" style=\"width:25px\" onKeyUp=\"isNumber(this)\" maxlength=\"2\" name=\"minute1\" type=\"text\">\r\n");
      out.write("\t  &nbsp;分<strong>:</strong>&nbsp;\r\n");
      out.write("\t  <input size=\"1\" id=\"_opentime6\" style=\"width:25px\" onKeyUp=\"isNumber(this)\" maxlength=\"2\" name=\"second1\" type=\"text\">\r\n");
      out.write("\t  秒</td>\r\n");
      out.write("      <td>\r\n");
      out.write("\t <label>\r\n");
      out.write("      <input type=\"submit\" name=\"Submit\" value=\"添加\">\r\n");
      out.write("      </label></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("\t<tr> <td colspan=\"4\" align=\"center\">(录入时开盘时间与开奖时间至少相隔3分钟)</td></tr>\r\n");
      out.write("  </table>\r\n");
      out.write("  </form>\r\n");
      out.write("  <table width=\"100%\" border=\"0\" cellpadding=\"2\" cellspacing=\"1\" class=\"border\">\r\n");
      out.write("    <tr>\r\n");
      out.write("          <td height=\"25\" colspan=\"9\" class=\"menutop\"><div style=\"width:90%; float:left; text-align:center; line-height:25px;\">盘口设定列表</div>\r\n");
      out.write("      </td>\r\n");
      out.write("    </tr>\r\n");
      out.write("  </table>\r\n");
      out.write("  <form id=\"form2\" name=\"form2\" method=\"post\" action=\"handicapList.do?action=handicapList\">\r\n");
      out.write(" <table width=\"100%\" border=\"0\" cellpadding=\"2\" cellspacing=\"1\" class=\"border\">\r\n");
      out.write("     <tr class=\"tdright\">\r\n");
      out.write("      <td height=\"25\">盘口类型 \r\n");
      out.write("        <select name=\"roomId\" id=\"roomId\" onChange=\"queryrooms()\">\r\n");
      out.write("      ");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("        </select>\r\n");
      out.write("\t\t期数查询<input name=\"pperoidnum\" size=\"12\" type=\"text\" class=\"input\" id=\"pperoidnum\" value=\"");
      out.print(pperoidnum);
      out.write("\" />\r\n");
      out.write("\t\t<input type=\"button\" name=\"button\" value=\"查询\" onClick=\"search()\"/>\r\n");
      out.write("        </td>\r\n");
      out.write("     </tr>\r\n");
      out.write("</table>\r\n");
      out.write("</form>\r\n");
      out.write("  <table width=\"100%\" border=\"0\" cellpadding=\"2\" cellspacing=\"1\" class=\"border\">\r\n");
      out.write("    <tr class=\"tdcenter\">\r\n");
      out.write("      <td align=\"center\"><strong>序号</strong></td>\r\n");
      out.write("      <td align=\"center\"><strong>盘口期数</strong></td>\r\n");
      out.write("      <td align=\"center\"><strong>开奖时间</strong></td>\r\n");
      out.write("      <td align=\"center\"><strong>功能</strong></td>\r\n");
      out.write("    </tr>\r\n");
      out.write("   \r\n");
      out.write("    ");
      //  c:forEach
      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
      _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f1.setParent(null);
      // /lottery/handicaplist.jsp(245,4) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f1.setVar("handicap");
      // /lottery/handicaplist.jsp(245,4) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
            out.write("      <td align=\"center\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${handicap.peroidnum}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("      <td height=\"25\" align=\"center\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${handicap.openHandTime}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("      <td height=\"25\" align=\"center\">");
            if (_jspx_meth_c_005fif_005f0(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
              return;
            //  c:if
            org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
            _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
            _jspx_th_c_005fif_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
            // /lottery/handicaplist.jsp(250,126) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
            _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${handicap.lotteryType=='1'}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
            // /lottery/handicaplist.jsp(250,126) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
            _jspx_th_c_005fif_005f1.setVar("true");
            int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
            if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
              do {
                out.write("<font color=\"#FF0000\">等待开奖<a href=\"");
                out.print(request.getContextPath() );
                out.write("/lottery/perodinumResult.do?action=resultList&peroidnum=");
                out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${handicap.peroidnum}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                out.write("&roomId=");
                out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${handicap.roomId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                out.write("&retroType=");
                out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${handicap.retroType}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
                out.write("\">(号码录入)</a></font>");
                int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
                if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
                  break;
              } while (true);
            }
            if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
              _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f1);
              return;
            }
            _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.reuse(_jspx_th_c_005fif_005f1);
            if (_jspx_meth_c_005fif_005f2(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
              return;
            out.write("&nbsp;&nbsp;\r\n");
            out.write("      \t<a href=\"handicapList.do?action=delete&peroidnum=");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${handicap.peroidnum}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("&roomId=");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${handicap.roomId}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\" onClick=\"{if(confirm('删除后该期数后将被永久删除！\\n\\n确定删除吗?')){return true;}return false;}\">删除</a>\t\t    </td>\r\n");
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
      out.write("</table>\r\n");
      out.write("  <table width=\"100%\" border=\"0\" cellpadding=\"2\" cellspacing=\"1\">\r\n");
      out.write("\t  <tr class=\"tdcenter\">\r\n");
      out.write("       <td colspan=\"4\">总记录:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalRecord}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write('条');
      out.write('/');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPage}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("页 \r\n");
      out.write("\t     ");
      if (_jspx_meth_c_005fif_005f3(_jspx_page_context))
        return;
      out.write("</td>\r\n");
      out.write("</td>\r\n");
      out.write("    </tr>  \r\n");
      out.write("  </table>\r\n");
      out.write("  \r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("<div id=\"swfcontent\" ></div>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
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
    // /lottery/handicaplist.jsp(227,6) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("r");
    // /lottery/handicaplist.jsp(227,6) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${rooms}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
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
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${r.roomName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</option>\t\r\n");
          out.write("\t ");
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

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    // /lottery/handicaplist.jsp(250,37) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${handicap.retroType==0}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /lottery/handicaplist.jsp(250,37) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setVar("true");
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<font color=\"#FF0000\">已开奖</font>");
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

  private boolean _jspx_meth_c_005fif_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    // /lottery/handicaplist.jsp(250,406) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${handicap.lotteryType=='2'}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /lottery/handicaplist.jsp(250,406) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f2.setVar("true");
    int _jspx_eval_c_005fif_005f2 = _jspx_th_c_005fif_005f2.doStartTag();
    if (_jspx_eval_c_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<font color=\"#FF0000\">等待结算</font>");
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

  private boolean _jspx_meth_c_005fif_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f3 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f3.setParent(null);
    // /lottery/handicaplist.jsp(258,6) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f3.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPage>0}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /lottery/handicaplist.jsp(258,6) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f3.setVar("true");
    int _jspx_eval_c_005fif_005f3 = _jspx_th_c_005fif_005f3.doStartTag();
    if (_jspx_eval_c_005fif_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write(" \r\n");
        out.write("        <select name=\"select\" onChange=\"jumppage(this.value);\">\r\n");
        out.write("          ");
        if (_jspx_meth_c_005fforEach_005f2(_jspx_th_c_005fif_005f3, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("        </select> \r\n");
        out.write("　  <a onClick=\"changepage(1)\" style=\"cursor:hand\">首页</a> \r\n");
        out.write("\t<a onClick=\"changepage(");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.curPage-1}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write(")\" style=\"cursor:hand\">上页</a> \r\n");
        out.write("\t&nbsp;<a onClick=\"changepage(");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.curPage+1}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write(")\" style=\"cursor:hand\">下页</a> \r\n");
        out.write("        <a onClick=\"changepage(");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPage}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write(")\" style=\"cursor:hand\">末页</a>");
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

  private boolean _jspx_meth_c_005fforEach_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f3, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f2 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fstep_005fend_005fbegin.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f3);
    // /lottery/handicaplist.jsp(260,10) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f2.setVar("i");
    // /lottery/handicaplist.jsp(260,10) name = begin type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f2.setBegin(1);
    // /lottery/handicaplist.jsp(260,10) name = end type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f2.setEnd(((java.lang.Integer) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPage }", java.lang.Integer.class, (PageContext)_jspx_page_context, null, false)).intValue());
    // /lottery/handicaplist.jsp(260,10) name = step type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
          if (_jspx_meth_c_005fif_005f4(_jspx_th_c_005fforEach_005f2, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
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

  private boolean _jspx_meth_c_005fif_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f2, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f4 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f2);
    // /lottery/handicaplist.jsp(261,14) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f4.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i==page.curPage}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /lottery/handicaplist.jsp(261,14) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f4.setVar("true");
    int _jspx_eval_c_005fif_005f4 = _jspx_th_c_005fif_005f4.doStartTag();
    if (_jspx_eval_c_005fif_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("selected=\"selected\"");
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
}

package org.apache.jsp.gold;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class card_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fstep_005fend_005fbegin;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fstep_005fend_005fbegin = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.release();
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.release();
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
      out.write("    \r\n");
      out.write(" \r\n");

int recordIndex=1;
	int pageIndex=1;
	int pageSize=30;
	int state=1;
	try{
		
		
		if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("pageIndex")!=null){
			pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
		}
		if(request.getParameter("state")!=null){
			state=Integer.parseInt(request.getParameter("state"));
		}
		}catch(Exception e){
			e.printStackTrace();
	}
	recordIndex=(pageIndex-1)*pageSize+1;
	String termOne="";
	if(request.getParameter("termOne")!=null){
		termOne=request.getParameter("termOne");
		termOne = new String(termOne.getBytes("ISO_8859_1"),"UTF-8");
	}
	String selectOne="";
	if(request.getParameter("selectOne")!=null){
		selectOne=request.getParameter("selectOne");
	}
	String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}

      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write(" var msg=\"");
      out.print(msg );
      out.write("\";\r\n");
      out.write(" if(msg!='')alert(msg);\r\n");
      out.write(" var tone=encodeURI(\"");
      out.print(termOne );
      out.write("\");\r\n");
      out.write(" function getAll()\r\n");
      out.write("{\r\n");
      out.write("\tvar elements=document.form1.checkbox;\r\n");
      out.write("\tvar length=elements.length;\r\n");
      out.write("\tfor(var i=0;i<length;i++)\r\n");
      out.write("\t{\r\n");
      out.write("\t\tvar element=elements[i];\r\n");
      out.write("\t\telement.checked=true;\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function checkAllBox(i){\r\n");
      out.write("\t\r\n");
      out.write("\t\tvar isChecked=(document.form1.checkAll.checked == true);\r\n");
      out.write("\t    var elements=document.form1.elements;\r\n");
      out.write("\t    var counter=elements.length;\r\n");
      out.write("\t    for(i=0;i<counter;i++){\r\n");
      out.write("\t\t\tvar element=elements[i];\r\n");
      out.write("\t\tif(element.type==\"checkbox\"){\r\n");
      out.write("\t\t\telement.checked=isChecked;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("function jumppage(value)\r\n");
      out.write("   {\r\n");
      out.write("      window.location.href=\"");
      out.print(request.getContextPath());
      out.write("/gold/cardList.do?action=list&curPage=\" +value+\"&termOne=");
      out.print(termOne );
      out.write("&selectOne=");
      out.print(selectOne );
      out.write("&state=");
      out.print(state);
      out.write("\";\r\n");
      out.write("   }\r\n");
      out.write("function changepage(pageNo){\r\n");
      out.write("\tdocument.location.href=\"");
      out.print(request.getContextPath());
      out.write("/gold/cardList.do?action=list&curPage=\"+pageNo+\"&pageSize=");
      out.print(pageSize );
      out.write("&termOne=");
      out.print(termOne );
      out.write("&selectOne=");
      out.print(selectOne );
      out.write("&state=");
      out.print(state);
      out.write("\";\r\n");
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
      out.write("function querystate()\r\n");
      out.write("\t{\r\n");
      out.write("\t\tvar state=document.getElementById(\"state\").options[document.getElementById(\"state\").selectedIndex].value;\r\n");
      out.write("\t\tdocument.location.href=\"");
      out.print(request.getContextPath());
      out.write("/gold/cardList.do?action=list&state=\"+state;\r\n");
      out.write("\t}\r\n");
      out.write("function search(){\r\n");
      out.write("\tdocument.location.href=\"");
      out.print(request.getContextPath());
      out.write("/gold/cardList.do?action=list&termOne=\"+encodeURI(document.all.termOne.value)\r\n");
      out.write("\t\t+\"&selectOne=\"+document.all.selectOne.options[document.all.selectOne.selectedIndex].value+\"&state=\"+document.all.state.options[document.all.state.selectedIndex].value;\r\n");
      out.write("\r\n");
      out.write("}\r\n");
      out.write("function addBeizhu(){\r\n");
      out.write("   var beizhu = document.getElementById(\"beizhu\").value;\r\n");
      out.write("   var boxes = document.getElementsByName(\"checkbox\");   \r\n");
      out.write("   var checkbox = new Array();   \r\n");
      out.write("\t//alert(checkbox);\r\n");
      out.write("    for(var i = 0;i<boxes.length;i++)   \r\n");
      out.write("    {   \r\n");
      out.write("    if(boxes[i].checked)   \r\n");
      out.write("    {   \r\n");
      out.write("        checkbox[i] = boxes[i].value;\r\n");
      out.write("\t\t//alert(checkbox[i]);\r\n");
      out.write("    }   \r\n");
      out.write("}\r\n");
      out.write("//alert(checkbox);\r\n");
      out.write("   document.location.href=\"");
      out.print(request.getContextPath());
      out.write("/gold/cardList.do?action=addBeizhu&checkboxss=\"+checkbox+\"&beizhu=\"+encodeURI(beizhu);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("function excel(){\r\n");
      out.write("   var boxes = document.getElementsByName(\"checkbox\");   \r\n");
      out.write("   var checkbox = new Array();  \r\n");
      out.write("   var j = 0; \r\n");
      out.write("    for(var i = 0;i<boxes.length;i++)   \r\n");
      out.write("    {   \r\n");
      out.write("    if(boxes[i].checked==true)   \r\n");
      out.write("    {    \r\n");
      out.write("        checkbox[j] = boxes[i].value;\r\n");
      out.write("\t\tj++;\r\n");
      out.write("\t\t//alert(j);\r\n");
      out.write("    }   \r\n");
      out.write("}\r\n");
      out.write("   document.location.href=\"");
      out.print(request.getContextPath());
      out.write("/gold/cardList.do?action=excel&checkboxss=\"+checkbox;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("function open1(m){\r\n");
      out.write("   var boxes = document.getElementsByName(\"checkbox\");   \r\n");
      out.write("   var checkbox = new Array();  \r\n");
      out.write("   var j = 0; \r\n");
      out.write("    for(var i = 0;i<boxes.length;i++)   \r\n");
      out.write("    {   \r\n");
      out.write("    if(boxes[i].checked==true)   \r\n");
      out.write("    {    \r\n");
      out.write("        checkbox[j] = boxes[i].value;\r\n");
      out.write("\t\tj++;\r\n");
      out.write("    }   \r\n");
      out.write("\t}\r\n");
      out.write("\tdocument.getElementById(\"checkboxss\").value=checkbox;\r\n");
      out.write("\t//alert(document.getElementById(\"checkboxss\").value);\r\n");
      out.write("\tvar ss = \"opendiv\"+m;\r\n");
      out.write("\tdocument.getElementById(ss).style.display=\"block\";\t\r\n");
      out.write("\t}\r\n");
      out.write("function close1(m){\r\n");
      out.write("\tvar ss = \"opendiv\"+m;\r\n");
      out.write("\tdocument.getElementById(ss).style.display=\"none\";\t\r\n");
      out.write("\t}\r\n");
      out.write("function DownloadCard(buildID)\r\n");
      out.write("{\r\n");
      out.write("\tif(!confirm(\"确定要执行选定的操作吗？\"))\r\n");
      out.write("\t{\r\n");
      out.write("\t\treturn;\r\n");
      out.write("\t}\r\n");
      out.write("\telse\r\n");
      out.write("\t{\r\n");
      out.write("\t\tdocument.form1.action = \"");
      out.print(request.getContextPath());
      out.write("/gold/cardList.do?action=excel&buildID=\"+buildID;\r\n");
      out.write("\t\tdocument.form1.submit();\r\n");
      out.write("\t}\r\n");
      out.write("}\r\n");
      out.write("</script>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>无标题文档</title>\r\n");
      out.write("<link href=\"../css/main.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<!--备注开始-->\r\n");
      out.write("<div class=\"layer\" id=\"opendiv1\" name=\"opendiv1\" style=\"display:none\">\r\n");
      out.write("  <form id=\"form2\" name=\"form2\" method=\"post\" action=\"");
      out.print(request.getContextPath());
      out.write("/gold/cardList.do?action=addBeizhu\">\r\n");
      out.write(" \r\n");
      out.write("\t\t<div class=\"menu\"><span class=\"span\" onclick=\"close1(1)\" onmouseover=\"this.className='span1'\" onmouseout=\"this.className='span'\">&nbsp;</span>添加备注</div>\r\n");
      out.write("\t\t<div class=\"content\">\r\n");
      out.write("\t\t\t<ul>\r\n");
      out.write("\t\t\t\t<li class=\"lileft\">备注：</li>\r\n");
      out.write("\t\t\t\t<li class=\"liright\">\r\n");
      out.write("\t\t\t\t  <label>\r\n");
      out.write("\t\t\t\t  <textarea name=\"beizhu\" cols=\"25\" rows=\"3\"></textarea><input name=\"checkboxss\" id=\"checkboxss\" type=\"hidden\" value=\"\" />\r\n");
      out.write("\t\t\t\t  </label>\r\n");
      out.write("\t\t\t\t</li>\r\n");
      out.write("\t\t\t\t<div  style=\" clear:both\"></div>\r\n");
      out.write("\t\t\t</ul>\r\n");
      out.write("  </div>\r\n");
      out.write("  <div class=\"down\">\r\n");
      out.write("    <label>\r\n");
      out.write("    <input name=\"Submit4\" type=\"submit\" class=\"input\" value=\"确定\" />\r\n");
      out.write("    </label>\r\n");
      out.write("\t <label>\r\n");
      out.write("    <input name=\"Submit4\" type=\"reset\" class=\"input\" value=\"重填\" />\r\n");
      out.write("    </label>\r\n");
      out.write("  </div> \r\n");
      out.write("  </form>\r\n");
      out.write("</div>\r\n");
      out.write("<!--备注结束-->\r\n");
      out.write("\t<Div class=\"title\"><span>  \r\n");
      out.write("\t  <form id=\"form2\" name=\"form2\" method=\"post\" action=\"\">\r\n");
      out.write("\t    <label>\r\n");
      out.write("\t    <input name=\"termOne\" type=\"text\" class=\"input_width2\"  />\r\n");
      out.write("\t    <select name=\"selectOne\" size=\"1\" class=\"Select\" id=\"selectOne\">\r\n");
      out.write("\t\t<option value=\"jsUser\">按用户</option>\r\n");
      out.write("        </select>\r\n");
      out.write("\t\t<select name=\"state\" id=\"state\" onChange=\"querystate()\">\r\n");
      out.write("\t\t<option value=\"1\">未充值</option>\r\n");
      out.write("      \t<option value=\"0\">已充值</option>\r\n");
      out.write("      </select>\r\n");
      out.write("\t    <input name=\"Submit2\" type=\"button\" class=\"input\" value=\"搜索\" onclick=\"search()\"/>\r\n");
      out.write("\t    </label>\r\n");
      out.write("\t    ");
      //  c:if
      org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
      _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f0.setParent(null);
      // /gold/card.jsp(216,5) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${roleId==1}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
      // /gold/card.jsp(216,5) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f0.setVar("true");
      int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
      if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write(" <input name=\"Submit\" type=\"button\" class=\"input\" value=\"生成点卡\" onclick=\"window.location.href='");
          out.print(request.getContextPath());
          out.write("/gold/cardList.do?action=preCreate'\" />");
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
      out.write("      </form>\r\n");
      out.write("\t</span>充值卡管理</Div>\r\n");
      out.write("\t<form id=\"form1\" name=\"form1\" method=\"post\" onsubmit=\"return delCheck()\" action=\"");
      out.print(request.getContextPath());
      out.write("/gold/cardList.do?action=delete\">\r\n");
      out.write("\t\t<table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_margin\">\r\n");
      out.write("          <tr>\r\n");
      out.write("            <td width=\"30\" class=\"menutop\">生产批次</td>\r\n");
      out.write("            <td class=\"menutop\">生成日期</td>\r\n");
      out.write("            <td class=\"menutop\">管理员</td>\r\n");
      out.write("            <td class=\"menutop\">销售商</td>\r\n");
      out.write("            <td class=\"menutop\">实卡名称</td>\r\n");
      out.write("            <td class=\"menutop\">实卡数量</td>\r\n");
      out.write("            <td class=\"menutop\">实卡价格</td>\r\n");
      out.write("            <td class=\"menutop\">总金额</td>\r\n");
      out.write("            <td class=\"menutop\">赠送银子</td>\r\n");
      out.write("            <td class=\"menutop\">地址</td>\r\n");
      out.write("            <td class=\"menutop\">导出次数</td>\r\n");
      out.write("\t\t\t<td class=\"menutop\">备注</td>\r\n");
      out.write("            <td class=\"menutop\">管理</td>\r\n");
      out.write("          </tr>\r\n");
      out.write("\t\t  ");
      //  c:forEach
      org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
      _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fforEach_005f0.setParent(null);
      // /gold/card.jsp(236,4) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setVar("card");
      // /gold/card.jsp(236,4) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cardlist}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
      int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
      try {
        int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
        if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
          do {
            out.write("\r\n");
            out.write("          <tr  onmouseOver=\"this.className='trover'\" onmouseOut=\"this.className='trout'\">\r\n");
            out.write("            <td class=\"tdcenter\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.buildID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("            <td class=\"tdcenter\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.buildDate}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("            <td class=\"tdcenter\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.adminName }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("            <td class=\"tdcenter\">&nbsp;");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.salesPerson}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("            <td class=\"tdcenter\">&nbsp;");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.cardName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("            <td class=\"tdcenter\">&nbsp;");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.buildCount }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("            <td class=\"tdcenter\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.cardPrice}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("            <td class=\"tdcenter\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.cardTotalPrice }", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("            <td class=\"tdcenter\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.cardGold}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("            <td class=\"tdcenter\">");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.buildAddr}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("            <td class=\"tdcenter\">&nbsp;");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.downloadCount}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("\t\t\t<td class=\"tdcenter\">&nbsp;");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.noteInfo}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("</td>\r\n");
            out.write("            <td class=\"tdcenter\">&nbsp;<a href=\"javascript:void(0)\" onclick=\"DownloadCard(");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.buildID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write(")\">实卡导出</a> / <a href=\"");
            out.print(request.getContextPath());
            out.write("/gold/cardList.do?action=cardInfo&buildID=");
            out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${card.buildID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
            out.write("\">实卡信息</a></td>\r\n");
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
      out.write("          <tr>\r\n");
      out.write("            <td colspan=\"14\" class=\"tdright_new\">总记录:");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalRecord}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write('条');
      out.write('/');
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPage}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("页\r\n");
      out.write("        ");
      if (_jspx_meth_c_005fif_005f1(_jspx_page_context))
        return;
      out.write("</td>\r\n");
      out.write("          </tr>\r\n");
      out.write("        </table>\r\n");
      out.write("</form>\r\n");
      out.write("</body>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\r\n");
      out.write("var tstate=document.all.state;\r\n");
      out.write("var index1=\"");
      out.print(state );
      out.write("\";\r\n");
      out.write("//alert(index1);\r\n");
      out.write("for(var i=0; i<tstate.options.length; i++){\r\n");
      out.write("\t\t\tif(tstate.options[i].value==index1){\r\n");
      out.write("\t\t\t\ttstate.options[i].selected=true;break;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
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

  private boolean _jspx_meth_c_005fif_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent(null);
    // /gold/card.jsp(255,8) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPage>=0}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /gold/card.jsp(255,8) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setVar("true");
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write(" \r\n");
        out.write("　<a onClick=\"changepage(1)\" style=\"cursor:hand\">首页</a>\r\n");
        out.write("<a onClick=\"changepage(");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.curPage-1}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write(")\" style=\"cursor:hand\">上页</a>\r\n");
        out.write("<select name=\"select\" onChange=\"jumppage(this.value);\">\r\n");
        out.write("          ");
        if (_jspx_meth_c_005fforEach_005f1(_jspx_th_c_005fif_005f1, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("        </select>\r\n");
        out.write("        <a onClick=\"changepage(");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.curPage+1}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write(")\" style=\"cursor:hand\">下页</a>\r\n");
        out.write("        <a onClick=\"changepage(");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPage}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write(")\" style=\"cursor:hand\">末页</a>");
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

  private boolean _jspx_meth_c_005fforEach_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f1, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fstep_005fend_005fbegin.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f1);
    // /gold/card.jsp(259,10) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setVar("i");
    // /gold/card.jsp(259,10) name = begin type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setBegin(1);
    // /gold/card.jsp(259,10) name = end type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setEnd(((java.lang.Integer) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${page.totalPage }", java.lang.Integer.class, (PageContext)_jspx_page_context, null, false)).intValue());
    // /gold/card.jsp(259,10) name = step type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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
          if (_jspx_meth_c_005fif_005f2(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
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

  private boolean _jspx_meth_c_005fif_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    // /gold/card.jsp(260,14) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i==page.curPage}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /gold/card.jsp(260,14) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f2.setVar("true");
    int _jspx_eval_c_005fif_005f2 = _jspx_th_c_005fif_005f2.doStartTag();
    if (_jspx_eval_c_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("selected=\"selected\"");
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
}

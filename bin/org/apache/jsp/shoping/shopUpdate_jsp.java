package org.apache.jsp.shoping;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class shopUpdate_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.release();
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
      response.setContentType("text/html;charset=utf-8");
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

String msg="";
	if(request.getAttribute("msg")!=null){
	msg=request.getAttribute("msg").toString();
}

      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>无标题文档</title>\r\n");
      out.write("<link href=\"../css/main.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("<script type=\"text/javascript\" src=\"../FCKeditor/fckeditor.js\"></script>\r\n");
      out.write("<script language=\"javascript\" type=\"text/javascript\">\r\n");
      out.write("  var msg=\"");
      out.print(msg );
      out.write("\";\r\n");
      out.write("  if(msg!='')alert(msg);\r\n");
      out.write("  \r\n");
      out.write("function check(){\r\n");
      out.write("  var price_gold = document.form1.price_gold.value;\r\n");
      out.write("  var vipScore = document.form1.vipScore.value;\r\n");
      out.write("  \r\n");
      out.write("  if(isNaN(price_gold)||price_gold==''){\r\n");
      out.write("     alert('清空请填0');\r\n");
      out.write("     return false;\r\n");
      out.write("  }\r\n");
      out.write("  if(isNaN(vipScore)||vipScore==''){\r\n");
      out.write("     alert('清空请填0');\r\n");
      out.write("\t return false;\r\n");
      out.write("  }\r\n");
      out.write("  return true;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
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
      out.write("\r\n");
      out.write("function gameshopaddcheck(){\r\n");
      out.write("    var typeId = document.form1.typeId.value;\r\n");
      out.write("\tvar cateName = document.form1.mallName.value;\r\n");
      out.write("\tvar price1 = document.form1.price_gold.value;\r\n");
      out.write("\tvar price2 = document.form1.price_score.value;\r\n");
      out.write("\tvar count = document.form1.count.value;\r\n");
      out.write("\tvar image = document.form1.photourl1.value;\r\n");
      out.write("\r\n");
      out.write("\tif(typeId==0){\r\n");
      out.write("\t  alert(\"请选择类型\");\r\n");
      out.write("\t  return false;\r\n");
      out.write("\t}\r\n");
      out.write("\tif(cateName==\"\"){\r\n");
      out.write("\t  alert(\"名称不能为空\");\r\n");
      out.write("\t  return false;\r\n");
      out.write("\t}\r\n");
      out.write("\tif(price1==\"\"){\r\n");
      out.write("\t  alert(\"请输入普通价格\");\r\n");
      out.write("\t  return false;\r\n");
      out.write("\t}\r\n");
      out.write("\tif(price2==\"\"){\r\n");
      out.write("\t  alert(\"请输入会员价格\");\r\n");
      out.write("\t  return false;\r\n");
      out.write("\t}\r\n");
      out.write("\tif(count==\"\"){\r\n");
      out.write("\t  alert(\"请输入商品数量\");\r\n");
      out.write("\t}\r\n");
      out.write("\tif(image==\"\"){\r\n");
      out.write("\t  alert(\"请上传图片\");\r\n");
      out.write("\t  return false;\r\n");
      out.write("\t}\r\n");
      out.write("  }\r\n");
      out.write("  </script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<Div class=\"title\">商品修改</Div>\r\n");
      out.write("\t\t<form id=\"form1\" name=\"form1\" method=\"post\" onSubmit=\"return gameshopaddcheck()\" action=\"");
      out.print(request.getContextPath());
      out.write("/shoping/gameshopList.do?action=updateMall\">\r\n");
      out.write("\t\t<input name=\"id\" type=\"hidden\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${gameshop.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" />\r\n");
      out.write("\t\t  <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_margin\">\r\n");
      out.write("            <tr>\r\n");
      out.write("              <td width=\"120\" class=\"menutop\">项目</td>\r\n");
      out.write("              <td class=\"menutop\">内容</td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr >\r\n");
      out.write("              <td class=\"tdright_new\">商品类型：</td>\r\n");
      out.write("              <td class=\"tdlefts\"><select name=\"typeId\" id=\"typeId\" size=\"1\" class=\"Select\">\r\n");
      out.write("         <option value=\"0\">---选择类型---</option>\r\n");
      out.write("         ");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("       </select></td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("              <td class=\"tdright_new\">商品名称：</td>\r\n");
      out.write("              <td class=\"tdlefts\"><input type=\"text\" name=\"mallName\" id=\"mallName\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${gameshop.mallName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("              <td class=\"tdright_new\">普通价格：</td>\r\n");
      out.write("              <td class=\"tdlefts\"><input type=\"text\" name=\"price_gold\" id=\"price_gold\" onkeyup=\"isNumber(this)\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${gameshop.price_gold}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("              <td class=\"tdright_new\">会员价格：</td>\r\n");
      out.write("              <td class=\"tdlefts\"><input type=\"text\" name=\"vipPrice\" id=\"vipPrice\" onkeyup=\"isNumber(this)\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${gameshop.vipPrice}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("              <td class=\"tdright_new\">商品数量：</td>\r\n");
      out.write("              <td class=\"tdlefts\"><input type=\"text\" name=\"count\" id=\"count\" onkeyup=\"isNumber(this)\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${gameshop.count}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("              <td class=\"tdright_new\">上传照片：</td>\r\n");
      out.write("              <td class=\"tdlefts\"><iframe name=\"uploadframe\" id=\"uploadframe\" width=100% height='120' src=\"");
      out.print(request.getContextPath());
      out.write("/shoping/FileUploadInner1.jsp\" frameborder=0 scrolling=no></iframe><input type=\"hidden\" name=\"photourl\" id=\"photourl\" /></td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("              <td class=\"tdright_new\">商品描述：</td>\r\n");
      out.write("              <td class=\"tdlefts\"><textarea name=\"cateNote\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${gameshop.descript}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</textarea>\r\n");
      out.write("\t\t\t  <script type=\"text/javascript\">  \r\n");
      out.write("\t\t\t\tvar oFCKeditor = new FCKeditor( 'cateNote' ) ;   \r\n");
      out.write("\t\t\t\t//oFCKeditor.BasePath = 'FCKeditor/' ;   \r\n");
      out.write("\t\t\t\toFCKeditor.ToolbarSet = 'Default' ;   \r\n");
      out.write("\t\t\t\toFCKeditor.Width = '100%' ;   \r\n");
      out.write("\t\t\t\toFCKeditor.Height = '400' ;   \r\n");
      out.write("\t\t\t\toFCKeditor.Value = '' ;   \r\n");
      out.write("\t\t\t\toFCKeditor.ReplaceTextarea();    \r\n");
      out.write("\t\t\t\t//oFCKeditor.Create() ;   \r\n");
      out.write("\t\t\t  </script>  </td>\r\n");
      out.write("            </tr>\r\n");
      out.write("            <tr>\r\n");
      out.write("              <td class=\"tdright_new\">&nbsp;</td>\r\n");
      out.write("              <td class=\"tdlefts\"><label>\r\n");
      out.write("                <input name=\"Submit\" type=\"submit\" class=\"input\" value=\"确定修改\" />\r\n");
      out.write("                <input name=\"Submit2\" type=\"reset\" class=\"input\" value=\"重新填写\" />\r\n");
      out.write("              </label></td>\r\n");
      out.write("            </tr>\r\n");
      out.write("          </table>\r\n");
      out.write("</form>\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("function setphotourl1(url)\r\n");
      out.write("        {\r\n");
      out.write("\t\tdocument.all.photourl.value=url;\r\n");
      out.write("\t\t}\r\n");
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

  private boolean _jspx_meth_c_005fforEach_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /shoping/shopUpdate.jsp(98,9) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("gsType");
    // /shoping/shopUpdate.jsp(98,9) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${typelist}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("           <option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${gsType.typeID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write('"');
          out.write(' ');
          if (_jspx_meth_c_005fif_005f0(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write('>');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${gsType.typeName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</option>\r\n");
          out.write("         ");
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

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_005fvar_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /shoping/shopUpdate.jsp(99,44) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${gsType.typeID==gameshop.typeID}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    // /shoping/shopUpdate.jsp(99,44) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setVar("true");
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write(" selected=\"selected\"");
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
}

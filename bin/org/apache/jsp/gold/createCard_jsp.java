package org.apache.jsp.gold;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class createCard_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.release();
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
      out.write("\r\n");
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
      out.write(" function GetCardInfo(obj)\r\n");
      out.write("        {\r\n");
      out.write("            document.myFormInfo.in_CardGold.value = obj.split(',')[1];\r\n");
      out.write("            document.myFormInfo.in_MemberDays.value = obj.split(',')[2];\r\n");
      out.write("            document.myFormInfo.in_CardPrice.value = obj.split(',')[3]\r\n");
      out.write("        }\r\n");
      out.write("        \r\n");
      out.write("        function CheckFormInfo()\r\n");
      out.write("        {\r\n");
      out.write("            var count = document.myFormInfo.in_Count.value.trim();\r\n");
      out.write("            var cardGold = document.myFormInfo.in_CardGold.value.trim();\r\n");
      out.write("            var memberDays = document.myFormInfo.in_MemberDays.value.trim();\r\n");
      out.write("            var cardPrefix = document.myFormInfo.in_CardPrefix.value.trim();\r\n");
      out.write("            var cardLength = document.myFormInfo.in_CardLength.value.trim();\r\n");
      out.write("            var pwdLength= document.myFormInfo.in_PwdLength.value.trim();\r\n");
      out.write("            var validDate = document.myFormInfo.in_ValidDate.value.trim();\r\n");
      out.write("            if(count==\"\"||IsPositiveInt(count)==false)\r\n");
      out.write("            {\r\n");
      out.write("                alert(\"生成数量不能为空或非数字字符或者超过允许大小！\");\r\n");
      out.write("                return false;\r\n");
      out.write("            }   \r\n");
      out.write("            else\r\n");
      out.write("            {\r\n");
      out.write("                if(count==0)\r\n");
      out.write("                {\r\n");
      out.write("                    alert(\"生成数量必须大于0！\");\r\n");
      out.write("                    return false;\r\n");
      out.write("                }\r\n");
      out.write("                else if(count>5000)\r\n");
      out.write("                {\r\n");
      out.write("                    alert(\"生成数量不可以超过5000！\");\r\n");
      out.write("                    return false;\r\n");
      out.write("                }\r\n");
      out.write("            }         \r\n");
      out.write("            if(cardGold==\"\"||IsPositiveInt(cardGold)==false)\r\n");
      out.write("            {\r\n");
      out.write("                alert(\"赠送银子不能为空或非数字字符或者超过允许大小！\");\r\n");
      out.write("                return false;\r\n");
      out.write("            }\r\n");
      out.write("            if(memberDays==\"\"||IsPositiveInt(memberDays)==false)\r\n");
      out.write("            {\r\n");
      out.write("                alert(\"会员天数不能为空或非数字字符或者超过允许大小！\");\r\n");
      out.write("                return false;\r\n");
      out.write("            }  \r\n");
      out.write("            if(validDate!=\"\"&&!IsPositiveInt(validDate))\r\n");
      out.write("            {\r\n");
      out.write("                alert(\"有效天数输入非法或者超过允许大小！\");\r\n");
      out.write("                return false;\r\n");
      out.write("            }       \r\n");
      out.write("            if(cardLength==\"\"||IsPositiveInt(cardLength)==false)\r\n");
      out.write("            {\r\n");
      out.write("                alert(\"卡号长度不能为空或非数字字符或者超过允许大小！\");\r\n");
      out.write("                return false;\r\n");
      out.write("            }\r\n");
      out.write("            else\r\n");
      out.write("            {\r\n");
      out.write("                if(cardLength<15)\r\n");
      out.write("                {\r\n");
      out.write("                    alert(\"卡号长度长度必须大于等于15小于32！\");\r\n");
      out.write("                    return false;\r\n");
      out.write("                }\r\n");
      out.write("                if(cardLength>31)\r\n");
      out.write("                {\r\n");
      out.write("                    alert(\"卡号长度长度必须大于等于15小于32！\");\r\n");
      out.write("                    return false;\r\n");
      out.write("                }\r\n");
      out.write("            }\r\n");
      out.write("            if(cardPrefix.length>1)\r\n");
      out.write("            {\r\n");
      out.write("                alert(\"开始字符只能是一个！\");\r\n");
      out.write("                return false;\r\n");
      out.write("            }\r\n");
      out.write("            if(pwdLength==\"\"||IsPositiveInt(pwdLength)==false)\r\n");
      out.write("            {\r\n");
      out.write("                alert(\"密码长度不能为空或非数字字符！\");\r\n");
      out.write("                return false;\r\n");
      out.write("            }\r\n");
      out.write("            else\r\n");
      out.write("            {\r\n");
      out.write("                if(pwdLength<8)\r\n");
      out.write("                {\r\n");
      out.write("                    alert(\"密码长度必须大于等于8！\");\r\n");
      out.write("                    return false;\r\n");
      out.write("                }\r\n");
      out.write("                if(pwdLength>32)\r\n");
      out.write("                {\r\n");
      out.write("                    alert(\"密码长度必须大于等于8小于33！\");\r\n");
      out.write("                    return false;\r\n");
      out.write("                }                \r\n");
      out.write("            }\r\n");
      out.write("        }\r\n");
      out.write("\t</script>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>无标题文档</title>\r\n");
      out.write("<link href=\"../css/main.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<Div class=\"title\">生成点卡</Div>\r\n");
      out.write("\t\r\n");
      out.write("    <form id=\"myFormInfo\" name=\"myFormInfo\" method=\"post\" action=\"");
      out.print(request.getContextPath() );
      out.write("/gold/cardList.do?action=create\">\r\n");
      out.write("      <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_margin\">\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"120\" class=\"menutop\">项目</td>\r\n");
      out.write("          <td class=\"menutop\">内容</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">会员卡类别：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><label>\r\n");
      out.write("            <select name=\"in_CardTypeID\" id=\"in_CardTypeID\" class=\"input_width2\" onchange=\"GetCardInfo(this.value)\">\r\n");
      out.write("\t\t\t");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("          </select>\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">生成数量：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><label>\r\n");
      out.write("            <input name=\"in_Count\" id=\"in_Count\" onKeyUp=\"isNumber(this)\" type=\"text\" maxlength=\"10\" class=\"input_width2\" />\r\n");
      out.write("          </label></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">赠送银子：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"in_CardGold\" onKeyUp=\"isNumber(this)\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cardGold}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" id=\"in_CardGold\" maxlength=\"14\" type=\"text\" class=\"input_width2\" />\r\n");
      out.write("\t\t  <input name=\"in_CardPrice\" id=\"in_CardPrice\" type=\"hidden\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cardPrice}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" /> </td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">会员天数：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"in_MemberDays\" onKeyUp=\"isNumber(this)\" id=\"in_MemberDays\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${memberDays}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" maxlength=\"14\" type=\"text\" class=\"input_width2\" /></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">使用范围：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><select name=\"in_UseRange\" style=\"width:157px;\">\r\n");
      out.write("\t                <option value=\"0\">全部用户</option>\r\n");
      out.write("\t                <option value=\"1\">新注册用户</option>\r\n");
      out.write("\t                <option value=\"2\">第一次充值用户</option>\r\n");
      out.write("\t                </select></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">销售商：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"in_SalesPerson\" id=\"in_SalesPerson\" maxlength=\"14\" type=\"text\" class=\"input_width2\" /></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">有效天数：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"in_ValidDate\" onkeyup=\"isNumber(this)\" id=\"in_ValidDate\" maxlength=\"14\" type=\"text\" class=\"input_width2\" /></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">备注：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"in_NoteInfo\" width=\"500px\" id=\"in_NoteInfo\" value=\"管理员【");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${AdminName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("】 于 ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${date}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write(" 生成\" maxlength=\"14\" type=\"text\" /></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"menutop\">卡号规则</td>\r\n");
      out.write("          <td class=\"tdlefts\">&nbsp;</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">开始字符：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"in_CardPrefix\" onkeyup=\"isNumber(this)\" id=\"in_CardPrefix\" value=\"P\" maxlength=\"14\" type=\"text\" class=\"input_width2\" />开始字符可为空</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\t\t <tr>\r\n");
      out.write("          <td class=\"tdright_new\">卡号长度：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"in_CardLength\" onkeyup=\"isNumber(this)\" id=\"in_CardLength\" value=\"15\" maxlength=\"14\" type=\"text\" class=\"input_width2\" />卡号长度必须大于等于15小于32</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("          <td class=\"menutop\">卡密规则</td>\r\n");
      out.write("          <td class=\"tdlefts\">&nbsp;</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">密码字符类型：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input type=\"checkbox\" id=\"in_PasswdType1\" value=\"1\" name=\"in_PasswdType\" checked=\"checked\"/>数字<input type=\"checkbox\" id=\"in_PasswdType2\" value=\"2\" name=\"in_PasswdType\" />含小写字母<input type=\"checkbox\" id=\"in_PasswdType3\" value=\"4\" name=\"in_PasswdType\" />含大写字母</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("\t\t <tr>\r\n");
      out.write("          <td class=\"tdright_new\">密码长度：</td>\r\n");
      out.write("          <td class=\"tdlefts\"><input name=\"in_PwdLength\" onkeyup=\"isNumber(this)\" id=\"in_PwdLength\" value=\"8\" maxlength=\"14\" type=\"text\" class=\"input_width2\" />密码长度必须大于等于8小于33</td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td class=\"tdright_new\">&nbsp;</td>\r\n");
      out.write("          <td class=\"tdright\"><label>\r\n");
      out.write("            <input name=\"Submit\" type=\"submit\" class=\"input\" value=\"生成会员卡\" />\r\n");
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

  private boolean _jspx_meth_c_005fforEach_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /gold/createCard.jsp(164,3) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("type");
    // /gold/createCard.jsp(164,3) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${listtype}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("              <option value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${type.cardTypeID}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write(',');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${type.cardGold}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write(',');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${type.memberDays}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write(',');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${type.cardPrice}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write('"');
          out.write('>');
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${type.cardName}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</option>\r\n");
          out.write("\t\t\t");
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
}

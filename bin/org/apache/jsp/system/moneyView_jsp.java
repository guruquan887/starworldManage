package org.apache.jsp.system;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.*;

public final class moneyView_jsp extends org.apache.jasper.runtime.HttpJspBase
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

	int recordIndex=1;
	int pageIndex=1;
	int pageSize=30;
	String orderby="RegisterDate";
	if(request.getParameter("orderby")!=null){
		orderby=request.getParameter("orderby");
	}
	try{
		if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("pageIndex")!=null){
			pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
		}
		}catch(Exception e){
			e.printStackTrace();
	}
	recordIndex=(pageIndex-1)*pageSize+1;
	String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}

	String startTime="";
	if(request.getParameter("startTime")!=null){
		startTime=request.getParameter("startTime");
	}
	String endTime="";
	if(request.getParameter("endTime")!=null){
		endTime=request.getParameter("endTime");
	}
	int proxyID = 0;
	if(request.getParameter("userID")!=null){
		proxyID=Integer.parseInt(request.getParameter("userID"));
	}
	

      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("<title>无标题文档</title>\r\n");
      out.write("<link href=\"../css/main.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div class=\"title\" style=\"margin:20px 0 0;\">系统统计</Div>\r\n");
      out.write("    <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_margin\">\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td height=\"28\" class=\"menutop\">系统</td>\r\n");
      out.write("        <td class=\"menutop\">充值</td>\r\n");
      out.write("        <td class=\"menutop\">损耗（赠送）</td>\r\n");
      out.write("        <td class=\"menutop\">机器人输赢</td>\r\n");
      out.write("        <td class=\"menutop\">佣金收入</td>\r\n");
      out.write("        <td class=\"menutop\">税收收入</td>\r\n");
      out.write("        <td class=\"menutop\">新增代理</td>\r\n");
      out.write("        <td class=\"menutop\">新增会员</td>\r\n");
      out.write("        <td class=\"menutop\">游戏总银子</td>\r\n");
      out.write("        <td class=\"menutop\">钱庄总银子</td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td class=\"tdcenter\">今日</td>\r\n");
      out.write("        <td class=\"tdcenter\">1000</td>\r\n");
      out.write("        <td class=\"tdcenter\">-2500</td>\r\n");
      out.write("        <td class=\"tdcenter\">10000</td>\r\n");
      out.write("        <td class=\"tdcenter\">30000</td>\r\n");
      out.write("        <td class=\"tdcenter\">30000</td>\r\n");
      out.write("        <td class=\"tdcenter\">10</td>\r\n");
      out.write("        <td class=\"tdcenter\">10</td>\r\n");
      out.write("        <td class=\"tdcenter\">452130214578</td>\r\n");
      out.write("        <td class=\"tdcenter\">965874526352</td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td class=\"tdcenter\">20214-09-04</td>\r\n");
      out.write("        <td class=\"tdcenter\">1000</td>\r\n");
      out.write("        <td class=\"tdcenter\">-2500</td>\r\n");
      out.write("        <td class=\"tdcenter\">10000</td>\r\n");
      out.write("        <td class=\"tdcenter\">30000</td>\r\n");
      out.write("        <td class=\"tdcenter\">30000</td>\r\n");
      out.write("        <td class=\"tdcenter\">10</td>\r\n");
      out.write("        <td class=\"tdcenter\">10</td>\r\n");
      out.write("        <td class=\"tdcenter\">452130214578</td>\r\n");
      out.write("        <td class=\"tdcenter\">965874526352</td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr style=\"font-weight:bold;\">\r\n");
      out.write("        <td class=\"tdcenter\">总计</td>\r\n");
      out.write("        <td class=\"tdcenter\">1000</td>\r\n");
      out.write("        <td class=\"tdcenter\">-2500</td>\r\n");
      out.write("        <td class=\"tdcenter\">10000</td>\r\n");
      out.write("        <td class=\"tdcenter\">30000</td>\r\n");
      out.write("        <td class=\"tdcenter\">30000</td>\r\n");
      out.write("        <td class=\"tdcenter\">10</td>\r\n");
      out.write("        <td class=\"tdcenter\">10</td>\r\n");
      out.write("        <td class=\"tdcenter\">452130214578</td>\r\n");
      out.write("        <td class=\"tdcenter\">965874526352</td>\r\n");
      out.write("      </tr>\r\n");
      out.write("    </table>\r\n");
      out.write("    <div class=\"title\" style=\"margin:20px 0 0;\">百人游戏</Div>\r\n");
      out.write("    <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_margin\">\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td height=\"28\" class=\"menutop\">百人馆</td>\r\n");
      out.write("        <td class=\"menutop\">百家乐</td>\r\n");
      out.write("        <td class=\"menutop\">百人二张</td>\r\n");
      out.write("        <td class=\"menutop\">百人牛牛</td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td class=\"tdcenter\">今日</td>\r\n");
      out.write("        <td class=\"tdcenter\">1000</td>\r\n");
      out.write("        <td class=\"tdcenter\">-2500</td>\r\n");
      out.write("        <td class=\"tdcenter\">10000</td>\r\n");
      out.write("\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td class=\"tdcenter\">20214-09-04</td>\r\n");
      out.write("        <td class=\"tdcenter\">1000</td>\r\n");
      out.write("        <td class=\"tdcenter\">-2500</td>\r\n");
      out.write("        <td class=\"tdcenter\">10000</td>\r\n");
      out.write("\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr style=\"font-weight:bold;\">\r\n");
      out.write("        <td class=\"tdcenter\">总计</td>\r\n");
      out.write("        <td class=\"tdcenter\">1000</td>\r\n");
      out.write("        <td class=\"tdcenter\">-2500</td>\r\n");
      out.write("        <td class=\"tdcenter\">10000</td>\r\n");
      out.write("\r\n");
      out.write("      </tr>\r\n");
      out.write("    </table>\r\n");
      out.write("    \r\n");
      out.write("    <div class=\"title\" style=\"margin:20px 0 0;\">竞技游戏</Div>\r\n");
      out.write("    <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_margin\">\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td height=\"28\" class=\"menutop\">竞技馆</td>\r\n");
      out.write("        <td class=\"menutop\">德州</td>\r\n");
      out.write("        <td class=\"menutop\">梭哈</td>\r\n");
      out.write("        <td class=\"menutop\">牛牛</td>\r\n");
      out.write("        <td class=\"menutop\">双扣</td>\r\n");
      out.write("        <td class=\"menutop\">斗地主</td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td class=\"tdcenter\">今日</td>\r\n");
      out.write("        <td class=\"tdcenter\">1000</td>\r\n");
      out.write("        <td class=\"tdcenter\">-2500</td>\r\n");
      out.write("        <td class=\"tdcenter\">10000</td>\r\n");
      out.write("        <td class=\"tdcenter\">-2500</td>\r\n");
      out.write("        <td class=\"tdcenter\">10000</td>\r\n");
      out.write("\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td class=\"tdcenter\">20214-09-04</td>\r\n");
      out.write("        <td class=\"tdcenter\">1000</td>\r\n");
      out.write("        <td class=\"tdcenter\">-2500</td>\r\n");
      out.write("        <td class=\"tdcenter\">10000</td>\r\n");
      out.write("        <td class=\"tdcenter\">-2500</td>\r\n");
      out.write("        <td class=\"tdcenter\">10000</td>\r\n");
      out.write("\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr style=\"font-weight:bold;\">\r\n");
      out.write("        <td class=\"tdcenter\">总计</td>\r\n");
      out.write("        <td class=\"tdcenter\">1000</td>\r\n");
      out.write("        <td class=\"tdcenter\">-2500</td>\r\n");
      out.write("        <td class=\"tdcenter\">10000</td>\r\n");
      out.write("        <td class=\"tdcenter\">-2500</td>\r\n");
      out.write("        <td class=\"tdcenter\">10000</td>\r\n");
      out.write("\r\n");
      out.write("      </tr>\r\n");
      out.write("    </table>\r\n");
      out.write("    \r\n");
      out.write("    <div class=\"title\" style=\"margin:20px 0 0;\">彩票游戏</Div>\r\n");
      out.write("    <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"table_margin\">\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td height=\"28\" class=\"menutop\">彩票馆</td>\r\n");
      out.write("        <td class=\"menutop\">北京快乐吧</td>\r\n");
      out.write("        <td class=\"menutop\">双色球</td>\r\n");
      out.write("        <td class=\"menutop\">深圳快乐吧</td>\r\n");
      out.write("        <td class=\"menutop\">上海时时彩</td>\r\n");
      out.write("        <td class=\"menutop\">重庆时时彩</td>\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td class=\"tdcenter\">今日</td>\r\n");
      out.write("        <td class=\"tdcenter\">1000</td>\r\n");
      out.write("        <td class=\"tdcenter\">-2500</td>\r\n");
      out.write("        <td class=\"tdcenter\">10000</td>\r\n");
      out.write("        <td class=\"tdcenter\">-2500</td>\r\n");
      out.write("        <td class=\"tdcenter\">10000</td>\r\n");
      out.write("\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr>\r\n");
      out.write("        <td class=\"tdcenter\">20214-09-04</td>\r\n");
      out.write("        <td class=\"tdcenter\">1000</td>\r\n");
      out.write("        <td class=\"tdcenter\">-2500</td>\r\n");
      out.write("        <td class=\"tdcenter\">10000</td>\r\n");
      out.write("        <td class=\"tdcenter\">-2500</td>\r\n");
      out.write("        <td class=\"tdcenter\">10000</td>\r\n");
      out.write("\r\n");
      out.write("      </tr>\r\n");
      out.write("      <tr style=\"font-weight:bold;\">\r\n");
      out.write("        <td class=\"tdcenter\">总计</td>\r\n");
      out.write("        <td class=\"tdcenter\">1000</td>\r\n");
      out.write("        <td class=\"tdcenter\">-2500</td>\r\n");
      out.write("        <td class=\"tdcenter\">10000</td>\r\n");
      out.write("        <td class=\"tdcenter\">-2500</td>\r\n");
      out.write("        <td class=\"tdcenter\">10000</td>\r\n");
      out.write("\r\n");
      out.write("      </tr>\r\n");
      out.write("    </table>\r\n");
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

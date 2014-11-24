<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.doowal.hk798.login.AuthorityMenuViewDTO"%>
<%@page import="java.util.List"%>
<%if (session.getAttribute("USER") == null){
	out.print("<script>alert('对不起，您还没有登录本系统或者您已经超过登录时间!');  window.target='_parent';window.location='/login.jsp';</script>");
	return;

}
%>
<!DOCTYPE HTML Public "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<TITLE>${title}</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<LINK href="css/style.css" type=text/css rel=stylesheet>
<link href="css/admin.css" type="text/css" rel="stylesheet">
<script language="javascript" src="<%=request.getContextPath()%>/js/admin.js"></script>
<SCRIPT>
var status = 1;
var Menus = new DvMenuCls;
document.onclick=Menus.Clear;
function switchSysBar(){
     if (1 == window.status){
		  window.status = 0;
          switchPoint.innerHTML = '<img src="images/left.gif">';
          document.all("frmTitle").style.display="none"
     }
     else{
		  window.status = 1;
          switchPoint.innerHTML = '<img src="images/right.gif">';
          document.all("frmTitle").style.display=""
     }
}
</SCRIPT>
<META content="MSHTML 6.00.2900.5726" name=GENERATOR></HEAD>
<BODY style="MARGIN: 0px">
<!--导航部分-->
<DIV class=top_table>
<DIV class=top_table_leftbg>
<DIV class=system_logo><IMG src="images/logo_up.gif"></DIV>
<DIV class=menu>
<UL>
  <%

List<AuthorityMenuViewDTO> topmenu=(List)session.getAttribute("topmenu");
for (AuthorityMenuViewDTO topmenuitem: topmenu) {
	int topmenuid=topmenuitem.getId();
	out.println("<LI id=menu_"+topmenuid+" onmouseover=Menus.Show(this,0) onclick=getleftbar(this);>");
	out.println("<A href=\""+topmenuitem.getMenuPath()+"\" target=frmright>"+topmenuitem.getMenuName()+"</A>");
	out.println("<DIV class=menu_childs onmouseout=Menus.Hide(0);>");
	out.println("<UL>");
	List<AuthorityMenuViewDTO> submenu =(List)session.getAttribute("submenu"+topmenuitem.getMenuid());
	for (AuthorityMenuViewDTO submenuitem: submenu) {
		out.println("<LI><A href=\""+submenuitem.getMenuPath()+"\"  target=frmright>"+submenuitem.getMenuName()+"</A></LI>");
		if(submenuitem.getInsertdiv()){
			out.println("<DIV class=menuhr></DIV>");
		}
	}
	out.println("</UL>");
	out.println("</div>");
	out.println("<DIV class=menu_div><IMG style=\"VERTICAL-ALIGN: bottom\" src=\"images/menu01_right.gif\"></DIV>");
	out.println("</LI>");
}
%>
    <!--<LI><A href="logout.jsp" target=frmright>登出管理</A>-->
	<!--<DIV class=menu_div><IMG style="VERTICAL-ALIGN: bottom" src="images/menu01_right.gif"></DIV>-->
  </UL>
</DIV></DIV></DIV>
<DIV style="BACKGROUND: #337abb; HEIGHT: 24px"></DIV>
<!--导航部分结束-->
<TABLE style="BACKGROUND: #337abb" height="92%" cellSpacing=0 cellPadding=0 
width="100%" border=0>
  <TBODY>
  <TR>
    <TD class=main_left id=frmTitle vAlign=top align=middle name="fmTitle">
      <TABLE class=main_left_top cellSpacing=0 cellPadding=0 width="100%" 
      border=0>
        <TBODY>
        <TR height=32>
          <TD vAlign=top></TD>
          <TD class=main_left_title id=leftmenu_title>常用快捷功能</TD>
          <TD vAlign=top align=right></TD></TR></TBODY></TABLE><IFRAME 
      class=left_iframe id=frmleft name=frmleft 
      src="left.jsp" frameBorder=0  
      allowTransparency></IFRAME>
      <TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
        <TBODY>
        <TR height=32>
          <TD vAlign=top></TD>
          <TD vAlign=bottom align=middle></TD>
          <TD vAlign=top align=right></TD></TR></TBODY></TABLE></TD>
    <TD style="WIDTH: 10px" bgColor=#337abb>
      <TABLE height="100%" cellSpacing=0 cellPadding=0 border=0>
        <TBODY>
        <TR>
          <TD style="HEIGHT: 100%" onclick=switchSysBar()><SPAN class=navPoint 
            id=switchPoint title=关闭/打开左栏><IMG 
            src="images/right.gif"></SPAN> </TD></TR></TBODY></TABLE></TD>
    <TD vAlign=top width="100%" bgColor=#337abb>
      <TABLE cellSpacing=0 cellPadding=0 width="100%" bgColor=#c4d8ed 
        border=0><TBODY>
        <TR height=32>
          <TD vAlign=top width=10 background=images/bg2.gif><IMG 
            alt="" src="images/teble_top_left.gif"></TD>
          <TD width=28 background=images//bg2.gif><IMG alt="" 
            src="images/arrow.gif" align=absMiddle></TD>
          <TD background=images/bg2.gif><SPAN 
            style="FLOAT: left">最新公告：</SPAN><SPAN style="FONT-WEIGHT: bold; FLOAT: left; WIDTH: 300px; COLOR: #c00"><marquee scrollamount="2">
          ${title}
          </marquee></SPAN></TD>
          <TD style="COLOR: #135294; TEXT-ALIGN: right" 
          background=images/bg2.gif>  <a href="#" onClick="javascript:history.go(-1)">返回</a> </TD>
          <TD vAlign=top align=right width=28 
          background=images/bg2.gif><IMG alt="" 
            src="images//teble_top_right.gif"></TD>
          <!--<TD align=right width=16 bgColor=#337abb></TD>--></TR></TBODY></TABLE>
		  <IFRAME class=main_iframe id=frmright name=frmright src="<%=request.getContextPath() %>/system/dateCount.do?method=usercount" frameBorder=0 scrolling=yes></IFRAME>
      <TABLE style="BACKGROUND: #c4d8ed" cellSpacing=0 cellPadding=0 
      width="100%" border=0>
        <TBODY>
        <TR>
          <TD><IMG height=6 alt="" 
            src="images/teble_bottom_left.gif" width=5></TD>
          <TD align=right><IMG height=6 alt="" 
            src="images/teble_bottom_right.gif" width=5></TD>
          <!--<TD align=right width=16 
  bgColor=#337abb></TD>--></TR></TBODY></TABLE></TD>
	<TD style="WIDTH: 10px;" bgColor=#337abb>&nbsp;</TD></TR></TBODY></TABLE>
</BODY></HTML>

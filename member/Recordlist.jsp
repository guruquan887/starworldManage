<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%

int userID = 0;
if(request.getParameter("userID")!=null){
	    userID = Integer.parseInt(request.getParameter("userID"));
	}

	int recordIndex=1;
	int pageIndex=1;
	int pageSize=10;

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
	String termOne="";
	if(request.getParameter("termOne")!=null){
		termOne=request.getParameter("termOne");
		termOne = new String(termOne.getBytes("ISO_8859_1"),"UTF-8");
	}
	String selectOne="";
	if(request.getParameter("selectOne")!=null){
		selectOne=request.getParameter("selectOne");
	}
	String startTime="";
	if(request.getParameter("startTime")!=null){
		startTime=request.getParameter("startTime");
	}
	String endTime="";
	if(request.getParameter("endTime")!=null){
		endTime=request.getParameter("endTime");
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<style type="text/css">
<!--
a:link { text-decoration: none}
a:active { text-decoration: none }
a:visited { text-decoration: none }
-->
</style>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">

var msg="<%=msg %>";
if(msg!='')alert(msg);
var tone=encodeURI("<%=termOne %>");
function jumppage(value)
   {
      window.location.href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gamebankRecord&curPage=" +value+"&pageSize=<%=pageSize %>&termOne=<%=termOne%>&selectOne=<%=selectOne %>"+"&startTime="+document.all.startTime.value+"&endTime="+document.all.endTime.value;
   }

function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gamebankRecord&curPage="+pageNo+"&termOne="+tone+"&selectOne=<%=selectOne %>&pageSize=<%=pageSize %>"+"&startTime="+document.all.startTime.value+"&endTime="+document.all.endTime.value;
}
function search(){
	document.location.href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gamebankRecord&termOne="+encodeURI(document.all.termOne.value)
		+"&selectOne="+document.all.selectOne.options[document.all.selectOne.selectedIndex].value
		+"&pageSize="+document.all.pageSize.options[document.all.pageSize.selectedIndex].value+"&startTime="+document.all.startTime.value+"&endTime="+document.all.endTime.value;
}
</script>
<body>
<Div class="title">
  <div align="left"><span></span>财富信息记录</div>
</Div>
	<div class="tab clearfix">
		<ul>
			<li><a href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gamebankRecord&userID=<%=userID%>">钱庄记录</a></li>
			<li><a href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gameUserInOutRecord&userID=<%=userID%>">进出记录</a></li>
			<li><a href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gameUserDrawRecord&userID=<%=userID%>">游戏记录</a></li>
			<li><a href="<%=request.getContextPath()%>/gold/cardList.do?action=cardRecord&userID=<%=userID%>">充值记录</a></li>
		</ul>
</div>
</body>
<script type="text/javascript">
var ttype2=document.all.selectOne;
var index2="<%=selectOne %>";
for(var i=0; i<ttype2.options.length; i++){
	if(ttype2.options[i].value==index2){
		ttype2.options[i].selected=true;break;
	}
}

var ttype3=document.all.termOne;
var index3="<%=termOne%>";
for(var i=0; i<ttype3.length; i++){
	if(ttype3.value==index3){
		ttype3.selected=true;break;
	}
}

var ttype1=document.all.pageSize;
var index1="<%=pageSize %>";
for(var i=0; i<ttype1.options.length; i++){
	if(ttype1.options[i].value==index1){
		ttype1.options[i].selected=true;break;
	}
}


</script>
</html>
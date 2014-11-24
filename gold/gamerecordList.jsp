<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=10;
	int userID = 0;
	String orderby="generateTime";
	if(request.getParameter("orderby")!=null){
		orderby=request.getParameter("orderby");
	}
	if(request.getParameter("userID")!=null){
		userID = Integer.parseInt(request.getParameter("userID"));
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
	String termOne="";
	if(request.getParameter("termOne")!=null){
		termOne=request.getParameter("termOne");
		termOne = new String(termOne.getBytes("ISO_8859_1"),"UTF-8");
	}
	String selectOne="";
	if(request.getParameter("selectOne")!=null){
		selectOne=request.getParameter("selectOne");
	}
	
%>
<head>
<script language="javascript" type="text/javascript">

function jumppage(value)
   {
      window.location.href="<%=request.getContextPath()%>/gold/gamegoldList.do?action=gamerecordList&curPage=" +value+"&pageSize=<%=pageSize %>&userID=<%=userID%>";
   }
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/gold/gamegoldList.do?action=gamerecordList&curPage="+pageNo+"&pageSize=<%=pageSize %>&userID=<%=userID%>";
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<Div class="title">输赢记录&nbsp; 用户名：${dto.accounts}，游戏ID：${dto.gameID}</Div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td colspan="9" class="tdright">胜局数：<span class="colorred">${dto.winCount}</span>，输局数：<span class="colorblue">${dto.lostCount}</span>，和局数：<span class="colorgreen">${dto.drawCount}</span>，逃局数：${dto.fleeCount}，胜率：<span class="colorred">：${dto.wingl}</span>，逃率：${dto.fleegl}</td>
          </tr>
          <tr>
            <td width="30" class="menutop">序号</td>
            <td class="menutop">游戏ID</td>
            <td class="menutop">用户名</td>
            <td class="menutop">游戏类别</td>
            <td class="menutop">房间类别</td>
            <td class="menutop">盘前银两</td>
            <td class="menutop">盘后银两</td>
            <td class="menutop">输赢</td>
            <td class="menutop">时间</td>
          </tr>
		  <c:forEach var="user" items="${userlist}">
          <tr>
            <td class="tdcenter"><%=recordIndex++ %></td>
            <td class="tdcenter">${user.gameID}</td>
            <td class="tdcenter">${user.accounts}</td>
            <td class="tdcenter">${user.kindName}</td>
            <td class="tdcenter">${user.roomName}</td>
            <td class="tdcenter">${user.firstGold}</td>
            <td class="tdcenter">${user.nextGold}</td>
            <td class="tdcenter">${user.goldRecord}</td>
            <td class="tdcenter">${user.generateTime}</td>
          </tr>
		  </c:forEach>
          <tr>
            <td colspan="9" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
        <c:if test="${page.totalPage>=0}"  var="true"> 
　<a onClick="changepage(1)" style="cursor:hand">首页</a>
<a onClick="changepage(${page.curPage-1})" style="cursor:hand">上页</a>
<select name="select" onChange="jumppage(this.value);">
          <c:forEach var="i" begin="1" end="${page.totalPage }" step="1"> <option value="${i}" 
              <c:if test="${i==page.curPage}" var="true">selected="selected"</c:if>
            >第${i}页
            </option>
          </c:forEach>
        </select>
        <a onClick="changepage(${page.curPage+1})" style="cursor:hand">下页</a>
        <a onClick="changepage(${page.totalPage})" style="cursor:hand">末页</a></c:if></td>
          </tr>
        </table>
</body>
</html>
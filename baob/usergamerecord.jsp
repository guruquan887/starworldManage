<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=30;
	int userID = 0;
	String orderby="RegisterDate";
	if(request.getParameter("orderby")!=null){
		orderby=request.getParameter("orderby");
	}
	try{
		if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("userID")!=null){
			userID=Integer.parseInt(request.getAttribute("userID").toString());
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
	String accounts="";
	if(request.getAttribute("accounts")!=null){
		accounts=request.getAttribute("accounts").toString();
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
	if(request.getParameter("userID")!=null){
		userID=Integer.parseInt(request.getParameter("userID"));
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=accounts%></title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
</head>
<script language="javascript" type="text/javascript">
var tone=encodeURI("<%=termOne %>");
function jumpEnter(e,newpageNo) {
	// 响应回车
	var key = window.event ? e.keyCode : e.which;
	if (key == 13) {
		//alert('123123123');
		jumppage(newpageNo);
	}
}

function jumppage(value)
   {
      window.location.href="<%=request.getContextPath()%>/baob/gameUserBBTJ.do?action=usergameRecord&curPage=" +value+"&startTime=<%=startTime%>&endTime=<%=endTime%>";
   }
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/baob/gameUserBBTJ.do?action=usergameRecord&curPage="+pageNo+"&userID=<%=userID%>&startTime=<%=startTime%>&endTime=<%=endTime%>";
}
function search(){
var startTime = document.all.startTime.value;
var endTime = document.all.endTime.value;
if(startTime!=""){
     if(endTime==""){
	     alert("请选择结束时间");
		 return false;
	 }
}

	document.location.href="<%=request.getContextPath()%>/baob/gameUserBBTJ.do?action=usergameRecord&userID=<%=userID%>&startTime="+document.all.startTime.value
		+"&endTime="+document.all.endTime.value;
}
</script>
<body>
	<Div class="title">
		<span>  
	  <form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/baob/gameUserBBTJ.do?action=usergameRecord">
	    <input name="startTime" onClick="WdatePicker()" type="text" value="" size="15" />
至
<input name="endTime" type="text" onClick="WdatePicker()" value="" size="15" />
<input name="Submit2" type="button" class="input" value="搜索" onClick="search()"/>
      </form>
	</span>
	游戏记录:<%=accounts%></Div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td width="60" class="menutop">序号</td>
            <td class="menutop">局数ID</td>
            <td class="menutop">椅子ID</td>
            <td class="menutop">输赢</td>
            <td class="menutop">游戏税收</td>
            <td class="menutop">奖牌数目</td>
            <td class="menutop">游戏时长</td>
            <td class="menutop">插入时间</td>
          </tr>
		  <c:forEach var="user" items="${usergameRecord}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter"><%=recordIndex++%></td>
            <td class="tdcenter">${user.drawID}</td>
            <td class="tdcenter">${user.chairID}</td>
            <td class="tdcenter">${user.score}</td>
            <td class="tdcenter">${user.revenue}</td>
            <td class="tdcenter">${user.userMedal}</td>
            <td class="tdcenter">${user.playTimeCount}</td>
            <td class="tdcenter">${user.insertTime}</td>
          </tr>
		  </c:forEach>
          <tr onmouseover="this.className='trover'" onmouseout="this.className='trout'">
            <td colspan="8" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
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
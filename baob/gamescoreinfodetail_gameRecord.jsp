<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=30;
    String createTimeStart = "";
	String createTimeEnd = "";
	String checkTime = "";
	int userID = 0;
	try{
		System.out.println(request.getAttribute("pageSize")+","+request.getAttribute("pageIndex"));
		if(request.getParameter("userID")!=null){
		    userID = Integer.parseInt(request.getParameter("userID"));
			System.out.println("userID:"+userID);
		}
		if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("pageIndex")!=null){
			pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
		}
		if(request.getParameter("startTime")!=null){
			createTimeStart= request.getParameter("startTime");
			System.out.println("createTimeStart:"+createTimeStart);
		}
		if(request.getParameter("endTime")!=null){
			createTimeEnd= request.getParameter("endTime");
			System.out.println("createTimeEnd:"+createTimeEnd);
		}
		if(request.getAttribute("checkTime")!=null){
		   checkTime =request.getAttribute("checkTime").toString();
		   System.out.println("checkTime:"+checkTime);
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
	int selectRoomType = 0;
	if(request.getParameter("selectRoomType")!=null){
		selectRoomType= Integer.parseInt(request.getParameter("selectRoomType"));
	}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>输赢详细记录</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
}
-->
</style>
<link href="../css/user.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/js/bbtj.js"></script>
<script language="javascript" type="text/javascript">

var tone=encodeURI("<%=termOne %>");
function jumppage(value)
   {
       window.location.href="gamescoreInfoDetail.do?action=date&curPage="+value+"&userID=<%=userID%>";
   }
var hash ={checkTime:'<%=checkTime %>'};
</script></head>

<body onload="loadDay(hash)">


  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
  <form id="form1" name="form1" method="post" onsubmit="return submted()" action="gamescoreInfoDetail.do?action=date">
  <input type="hidden"  value="<%=userID %>" name="userID"/>
    <tr class="menutop">
      <td colspan="17" ><div align="center">真人-输赢记录列表</div>
	    <hr />
      <div align="right">用户账号:&nbsp;${userlist[0].accounts}&nbsp;&nbsp;&nbsp;
	  (<input size="10" id="startTime" type="text" onClick="WdatePicker()"  name="startTime" value="${startTime}<%=createTimeStart %>" readonly/>
		 ><span title="例：开始‘2010-06-06’，结束‘2010-06-07’；    取到的数据的日期为：‘2010-06-06 00:00:00’至‘2010-06-07 23:59:59’" style="cursor:pointer">数据日期</span>&lt;     
		 <input size="10" id="endTime" type="text" onClick="WdatePicker()"  name="endTime" value="${endTime}<%=createTimeEnd %>" readonly/> 
		)
		<input type="button" name="reSet" onclick="reSetDate()" value="清空日期" />
	    &nbsp;&nbsp;
		<input id="checkTime"  type="radio" value="all" name="checkTime" />
        全部
	    <input id="checkTime"  type="radio" value="bweek" name="checkTime" />
        上周
        <input id="checkTime"  type="radio" value="cweek" name="checkTime" />
        本周
       <input id="checkTime"  type="radio" value="bmonth" name="checkTime" />
        上月份
        <input id="checkTime"  type="radio" value="cmonth" name="checkTime" />
       本月份
	   <input type="submit" name="Submit" value="查询" />
	   &nbsp;&nbsp;&nbsp;
	  <%--<a href="<%=request.getContextPath()%>/gameuser/gamescoreInfoTodayDetail.do?userID=<%=userID%>&tian=1&selectRoomType=<%=selectRoomType %>">当天</a> | <a href="<%=request.getContextPath()%>/gameuser/gamescoreInfoTodayDetail.do?userID=<%=userID%>&tian=2&selectRoomType=<%=selectRoomType %>">昨天</a> | <a href="<%=request.getContextPath()%>/gameuser/gamescoreInfoTodayDetail.do?userID=<%=userID%>&tian=3&selectRoomType=<%=selectRoomType %>">前天</a>&nbsp;&nbsp;--%></div></td>
    </tr>
	</form>
    <tr>
      <td class="tdcenter" align="center"><strong>序号</strong></td>
      <td class="tdcenter"><div align="center"><strong>百家乐局数</strong></div></td>
      <td class="tdcenter"><div align="center"><strong>房间类别</strong></div></td>
	  <td class="tdcenter"><div align="center"><strong>下注区域金额</strong></div></td>
      <td class="tdcenter"><div align="center"><strong>开牌结果</strong></div></td>
      <%--	  <td class="tdcenter"><div align="center"><strong>闲家</strong></div></td>
	  <td class="tdcenter"><div align="center"><strong>和家</strong></div></td>
	  <td class="tdcenter"><div align="center"><strong>大</strong></div></td>
	  <td class="tdcenter"><div align="center"><strong>小</strong></div></td>
	  <td class="tdcenter"><div align="center"><strong>闲对</strong></div></td>
	  <td class="tdcenter"><div align="center"><strong>庄对</strong></div></td>--%>
	  <td class="tdcenter"><div align="center"><strong>输赢</strong></div></td>
	  <td class="tdcenter"><div align="center"><strong>赔率</strong></div></td>
<%--	  <td class="tdcenter"><div align="center"><strong>返水</strong></div></td>--%>
      <td class="tdcenter"><div align="center"><strong>下注时间</strong></div></td>
	  <td class="tdcenter"><div align="center"><strong>开牌时间</strong></div></td>
    </tr>
    <c:forEach var="user" items="${userlist}">
      <tr class="tdcenter">
        <td height="25" align="center"><%=recordIndex++ %></td>
		<td>${user.betSerial}</td>
        <td>${user.kindName}</td>
		<td>${user.gameAreaName}${user.betGold}</td>
        <td>${user.winArea}</td>
        <%--		<td>${user.small}</td>
		<td>${user.single}</td>
		<td>${user.pairs}</td>
		<td>${user.serpent}</td>
		<td>${user.peace}</td>
        <td>${user.tiger}</td>--%>
       
		<td><c:if test="${user.betResult<0}"  var="true"><font color="#FF0000">${user.betResult}</font></c:if>
		<c:if test="${user.betResult>=0}"  var="true"><font color="#0000FF">${user.betResult}</font></c:if></td>
		
		<td>${user.betRate}</td>
	<%--	<td>${user.rebate}</td>--%>
	    <td><div align="center">${user.generateTime}</div></td>
		<td><div align="center">${user.generateTime1}</div></td>
      </tr>
    </c:forEach>
	<tr><td colspan="17" align="center"><font color="red"><c:out value="${returnInfo}"></c:out></font></td></tr>
  </table>
  <table width="100%" border="0" cellpadding="2" cellspacing="1">
    
    <tr class="tdcenter" align="left">
       <td colspan="4">总记录:${page.totalRecord}条/${page.totalPage}页
        <c:if test="${page.totalPage>=0}"  var="true">
　<a href="gamescoreInfoDetail.do?action=date&curPage=1&userID=<%=userID%>&checkTime=<%=checkTime%>&startTime=<%=createTimeStart%>&endTime=<%=createTimeEnd%>">首页</a>
<a href="gamescoreInfoDetail.do?action=date&curPage=${page.curPage-1}&userID=<%=userID%>&checkTime=<%=checkTime%>&startTime=<%=createTimeStart%>&endTime=<%=createTimeEnd%>">上页</a>
<select name="select" onchange="jumppage(this.value);">
          <c:forEach var="i" begin="1" end="${page.totalPage }" step="1"> <option value="${i}" 
              <c:if test="${i==page.curPage}" var="true">selected="selected"</c:if>
            >第${i}页
            </option>
          </c:forEach>
        </select>
        <a href="gamescoreInfoDetail.do?action=date&curPage=${page.curPage+1}&userID=<%=userID%>&checkTime=<%=checkTime%>&startTime=<%=createTimeStart%>&endTime=<%=createTimeEnd%>">下页</a>
        <a href="gamescoreInfoDetail.do?action=date&curPage=${page.totalPage}&userID=<%=userID%>&checkTime=<%=checkTime%>&startTime=<%=createTimeStart%>&endTime=<%=createTimeEnd%>">末页</a>	</c:if></td>
    </tr>
</table>
</body>
</html>

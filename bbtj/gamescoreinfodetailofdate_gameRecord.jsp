<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=30;
    String createTime = "";
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
			createTime=(String)request.getParameter("startTime");
			System.out.println("startTime:"+createTime);
		}
		if(request.getAttribute("checkTime")!=null){
		   checkTime =request.getAttribute("checkTime").toString();
		   System.out.println("checkTime:"+checkTime);
        }
		}catch(Exception e){
			e.printStackTrace();
	}
	recordIndex=(pageIndex-1)*pageSize+1;
	

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
function submted2(){
	var d1 = document.getElementById("startTime").value;
    if(!checkTime()){
	if (d1 == ""){
		alert("请选择查看日期");
		return false;
	}}
	return true;
}

function jumppage(value)
   {
       window.location.href="gamescoreInfoDetail.do?action=day&curPage="+value+"&userID=<%=userID%>";
   }
var hash ={checkTime:'<%=checkTime %>'};
</script>

</head>

<body onload="loadDay(hash)">


  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
  <form id="form1" name="form1" method="post" onsubmit="return submted2()" action="gamescoreInfoDetail.do?action=day">
  <input type="hidden"  value="<%=userID %>" name="userID"/>
    <tr class="menutop">
      <td colspan="17" ><div align="center">快乐8-输赢记录列表</div>
	  <hr />
      <div align="right">用户账号:&nbsp;${userlist[0].accounts}&nbsp;&nbsp;&nbsp;
	  日期
        <input size="10" id="startTime" type="text" onClick="WdatePicker()"  name="startTime" value="${startTime}<%=createTime %>" readonly/>
		<input type="hidden" value="" name="endTime" id="endTime" />
 	    &nbsp;
		<input type="button" name="reSet" onclick="reSetDate()" value="清空日期" />
		&nbsp;
	    <input id="checkTime"  type="radio" value="bday" name="checkTime" />
        昨日
        <input id="checkTime"  type="radio" value="cday" name="checkTime" />
        当日
		&nbsp;&nbsp;&nbsp;
        <input type="submit" name="Submit" value="查询" /></div></td>
    </tr>
	</form>
    <tr>
      <td class="tdcenter" align="center"><strong>序号</strong></td>
      <td class="tdcenter"><div align="center"><strong>盘口期数</strong></div></td>
      <td class="tdcenter"><div align="center"><strong>房间类别</strong></div></td>
	  <td class="tdcenter"><div align="center"><strong>大</strong></div></td>
	  <td class="tdcenter"><div align="center"><strong>小</strong></div></td>
	  <td class="tdcenter"><div align="center"><strong>单</strong></div></td>
	  <td class="tdcenter"><div align="center"><strong>双</strong></div></td>
	  <td class="tdcenter"><div align="center"><strong>龙</strong></div></td>
	  <td class="tdcenter"><div align="center"><strong>和</strong></div></td>
	  <td class="tdcenter"><div align="center"><strong>虎</strong></div></td>
	  <td class="tdcenter"><div align="center"><strong>倍率</strong></div></td>
	  <td class="tdcenter"><div align="center"><strong>倍率组号</strong></div></td>
      <td class="tdcenter"><div align="center"><strong>账户余额</strong></div></td>
      <td class="tdcenter"><div align="center"><strong>输赢</strong></div></td>
	  <td class="tdcenter"><div align="center"><strong>返水</strong></div></td>
      <td class="tdcenter"><div align="center"><strong>下注时间</strong></div></td>
	  <td class="tdcenter"><div align="center"><strong>开奖时间</strong></div></td>
    </tr>
    <c:forEach var="user" items="${userlist}">
      <tr class="tdcenter">
        <td height="25" align="center"><%=recordIndex++ %></td>
		<td>${user.peroidnum}</td>
        <td>${user.roomDisPlayName}</td>
		<td>${user.large}</td>
		<td>${user.small}</td>
		<td>${user.single}</td>
		<td>${user.pairs}</td>
		<td>${user.serpent}</td>
		<td>${user.peace}</td>
        <td>${user.tiger}</td>
		<td>${user.rate}</td>
		<td>${user.rateNumber}</td>
		<td>${user.lastMoney}</td>
		<td>${user.winLostMoney}</td>
		<td>${user.rebate}</td>
	    <td><div align="center">${user.createTime}</div></td>
		<td><div align="center">${user.createTime1}</div></td>
      </tr>
    </c:forEach>
	<tr><td colspan="17" align="center"><font color="red"><c:out value="${returnInfo}"></c:out></font></td></tr>
  </table>
  <table width="100%" border="0" cellpadding="2" cellspacing="1">
    
    <tr class="tdcenter" align="left">
       <td colspan="4">总记录:${page.totalRecord}条/${page.totalPage}页
        <c:if test="${page.totalPage>=0}"  var="true">
　<a href="gamescoreInfoDetail.do?action=day&curPage=1&userID=<%=userID%>&checkTime=<%=checkTime%>&startTime=<%=createTime%>">首页</a>
<a href="gamescoreInfoDetail.do?action=day&curPage=${page.curPage-1}&userID=<%=userID%>&checkTime=<%=checkTime%>&startTime=<%=createTime%>">上页</a>
<select name="select" onchange="jumppage(this.value);">
          <c:forEach var="i" begin="1" end="${page.totalPage }" step="1"> <option value="${i}" 
              <c:if test="${i==page.curPage}" var="true">selected="selected"</c:if>
            >第${i}页
            </option>
          </c:forEach>
        </select>
        <a href="gamescoreInfoDetail.do?action=day&curPage=${page.curPage+1}&userID=<%=userID%>&checkTime=<%=checkTime%>&startTime=<%=createTime%>">下页</a>
        <a href="gamescoreInfoDetail.do?action=day&curPage=${page.totalPage}&userID=<%=userID%>&checkTime=<%=checkTime%>&startTime=<%=createTime%>">末页</a>	</c:if></td>
    </tr>
</table>
</body>
</html>

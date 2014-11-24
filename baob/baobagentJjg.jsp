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
	String typeName = "";
	if(request.getParameter("typeName")!=null){
		typeName = request.getParameter("typeName");
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
	int proxyID = 0;
	if(request.getParameter("userID")!=null){
		proxyID=Integer.parseInt(request.getParameter("userID"));
	}
	
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
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
      window.location.href="<%=request.getContextPath()%>/baob/bbtj.do?action=search&curPage=" +value+"&pageSize=<%=pageSize %>&orderby=<%=orderby %>&termOne=<%=termOne%>&selectOne=<%=selectOne %>";
   }
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/baob/bbtj.do?action=search&curPage="+pageNo+"&termOne="+tone+"&selectOne=<%=selectOne %>";
}
function search(){
	document.location.href="<%=request.getContextPath()%>/baob/bbtj.do?action=search&termOne="+encodeURI(document.all.termOne.value)
		+"&selectOne="+document.all.selectOne.options[document.all.selectOne.selectedIndex].value+"&startTime="+document.all.startTime.value+"&endTime="+document.all.endTime.value+"&userID="+document.all.proxyID.value+"&typeName=<%=typeName%>";
}
function searchtime(ortime){
	document.location.href="<%=request.getContextPath()%>/baob/bbtj.do?action=search&termOne="+encodeURI(document.all.termOne.value)
		+"&selectOne="+document.all.selectOne.options[document.all.selectOne.selectedIndex].value+"&checkTime="+ortime+"&startTime="+document.all.startTime.value+"&endTime="+document.all.endTime.value+"&userID="+document.all.proxyID.value+"&typeName=<%=typeName%>";
}
</script>
<body>
<Div class="title">报表管理—公司报表</Div>
	<Div class="title">
	  <span>  
	   <form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/baob/bbtj.do?action=search">
	  <input name="checkTime1" id="checkTime1" type="button" class="input" value="今日" onClick="searchtime('today')"/>
	  <input name="checkTime2" id="checkTime2" type="button" class="input" value="昨日" onClick="searchtime('yestoday')"/>
	  <input name="checkTime3" id="checkTime3" type="button" class="input" value="本周" onClick="searchtime('cweek')"/>
	  <input name="checkTime4" id="checkTime4" type="button" class="input" value="上周" onClick="searchtime('bweek')"/>
	  <input name="checkTime5" id="checkTime5" type="button" class="input" value="本月" onClick="searchtime('cmonth')"/>
	  <input name="checkTime6" id="checkTime6" type="button" class="input" value="上月" onClick="searchtime('bmonth')"/>
	  <input name="checkTime7" id="checkTime7" type="button" class="input" value="全部" onClick="searchtime('all')"/>
	   <input name="termOne" type="text" size="8" id="termOne" value="<%=termOne%>" onfocus="this.select()" />
	   <input name="proxyID" type="hidden" size="8" id="proxyID" value="<%=proxyID%>" />
		&nbsp;类型
        <select name="selectOne" size="1" class="Select" id="selectOne">
		<option value="accounts">按用户名</option>
        </select>
		<input name="startTime" id="startTime" onClick="WdatePicker()" type="text" value="${startTime}" size="15" />
至
<input name="endTime" id="endTime" type="text" onClick="WdatePicker()" value="${endTime}" size="15" />
<input name="Submit2" type="button" class="input" value="搜索" onClick="search()"/>
      </form>
	</span></Div>
	<%--	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
		  <tr>
            <td colspan="10" class="menutop_left">百人类游戏报表</td>
          </tr>
          <tr>
            <td rowspan="2" class="menutop">用户</td>
            <td rowspan="2" class="menutop"> 投注金额 </td>
            <td rowspan="2" class="menutop"> 输赢结果 </td>
            <td rowspan="2" class="menutop">洗码量</td>
            <td rowspan="2" class="menutop">佣金占成</td>
            <td rowspan="2" class="menutop">洗码佣金</td>
            <td rowspan="2" class="menutop">总金额</td>
            <td class="menutop" colspan="2">交收</td></tr>
          <tr>
            <td class="menutop">输赢占成</td>
            <td class="menutop">上交金额</td> 
          </tr>
		  <c:forEach var="user" items="${userlist}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/baob/bbtj.do?action=search&userID=${user.proxyID}">${user.accounts}</a></td>
            <td class="tdcenter">${user.tzScore}</td>
            <td class="tdcenter"><c:if test="${user.totalBetScore!=0}"  var="true"> <font color="#CC0000"></c:if>${user.winlostScore}</font></td>
            <td class="tdcenter">${user.xmScore}</td>
            <td class="tdcenter">${user.brokerage}%</td>
            <td class="tdcenter">${user.xmyjScore}</td>
            <td class="tdcenter"><c:if test="${user.totalBetScore!=0}"  var="true"> <font color="#CC0000"></c:if>${user.totalBetScore}</font></td>
            <td class="menutop">${user.winner}%</td>
            <td class="menutop"><c:if test="${user.totalBetScore!=0}"  var="true"> <font color="#CC0000"></c:if>${user.sjWinnerScore}</font></td>
          </tr>
		  </c:forEach>
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td colspan="2" class="menutop">合计：</td>
            <td class="tdcenter">${bdto.totalTzScore}</td>
            <td class="tdcenter">${bdto.totalWinloseScore}</td>
            <td class="tdcenter">${bdto.totalXmScore}</td>
            <td class="tdcenter">&nbsp;</td>
            <td class="tdcenter">&nbsp;</td>
            <td class="tdcenter">&nbsp;</td>
            <td class="menutop"></td>
            <td class="menutop"></td>
          </tr>
		  <tr>
                <td class="tdcenter" colspan="23" align="center" valign="middle" bgcolor="#FFFFFF">
				<font color="red"><c:out value="${returnInfo}"></c:out></font></td>
       </tr>
        </table>--%>
		
		
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td colspan="9" class="menutop_left">竞技类游戏报表</td>
          </tr>
                    
          <tr>
            <td width="60" class="menutop">级别</td>
            <td class="menutop">用户</td>
            <td class="menutop"> 输赢结果 </td>
            <td class="menutop">税收</td>
            <td class="menutop">税收占成</td>
            <td class="menutop">税收分成金额</td>
            <td class="menutop">总金额</td>
            <td class="menutop">上交税收占成</td>
            <td class="menutop">上交金额</td>
          </tr>
          
		  <c:forEach var="user" items="${userlist1}">
          <tr <c:if test="${user.accounts=='admin'}" var="true"> style="display:none" </c:if>onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter">${user.accountTypeName}</td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/baob/bbtj.do?action=baobAgent&userID=${user.proxyID}&levelID=${user.levelID}&typeName=<%=typeName%>">${user.accounts}</a></td>
            <td class="tdcenter"><c:if test="${user.totalBetScore!=0}"  var="true"> <font color="#CC0000"></c:if>${user.winlostScore}</font></td>
            <td class="tdcenter">${user.revenue}</td>
            <td class="tdcenter">${user.taxrate}%</td>
            <td class="tdcenter">${user.revenueScore}</td>
            <td class="tdcenter"><c:if test="${user.totalBetScore!=0}"  var="true"> <font color="#CC0000"></c:if>${user.totalBetScore}</td>
            <td class="menutop">${user.agentTaxrate}%</td>
            <td class="menutop"><c:if test="${user.totalBetScore!=0}"  var="true"> <font color="#CC0000"></c:if>${user.sjRevenueScore}</td>
          </tr>
	      </c:forEach>
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td colspan="2" class="menutop">合计：</td>
            <td class="tdcenter">${jdto.totalWinloseScore}</td>
            <td class="tdcenter">${jdto.totalRevenue}</td>
            <td class="tdcenter">&nbsp;</td>
            <td class="tdcenter">&nbsp;</td>
            <td class="tdcenter">&nbsp;</td>
            <td class="menutop"></td>
            <td class="menutop"></td>
          </tr>
		   <tr>
                <td class="tdcenter" colspan="23" align="center" valign="middle" bgcolor="#FFFFFF">
				<font color="red"><c:out value="${returnInfo}"></c:out></font></td>
       </tr>
        </table>
</body>
</html>
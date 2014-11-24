<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=30;
	String orderby="generateTime";
	if(request.getParameter("orderby")!=null){
		orderby=request.getParameter("orderby");
	}
	try{
		System.out.println(request.getAttribute("pageSize")+","+request.getAttribute("pageIndex"));
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
	String selectOne="";
	if(request.getParameter("selectOne")!=null){
		selectOne=request.getParameter("selectOne");
	}
	String startTime = "";
		if(request.getParameter("startTime")!=null){
			startTime=request.getParameter("startTime");
		}
	String endTime = "";
		if(request.getParameter("endTime")!=null){
			endTime=request.getParameter("endTime");
		}
	String termOne="";
	if(request.getParameter("termOne")!=null){
		termOne=request.getParameter("termOne");
		termOne = new String(termOne.getBytes("ISO_8859_1"),"UTF-8");
	}
	int kindID = 0;
	if(request.getParameter("kindID")!=null){
	    kindID = Integer.parseInt(request.getParameter("kindID"));
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
       window.location.href="<%=request.getContextPath()%>/gameRecord/gameRecord.do?action=gameRecordList&curPage="+value;
   }
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/gameRecord/gameRecord.do?action=gameRecordList&curPage="+pageNo+"&termOne="+tone
		+"&selectOne="+document.all.selectOne.options[document.all.selectOne.selectedIndex].value
		+"&pageSize="+document.all.pageSize.options[document.all.pageSize.selectedIndex].value
		+"&orderby="+document.all.orderby.options[document.all.orderby.selectedIndex].value
		+"&startTime="+document.all.startTime.value
		+"&endTime="+document.all.endTime.value
		+"&kindID="+<%=kindID%>;
}
   
function search(){

var startTime = document.all.startTime.value;
var endTime = document.all.endTime.value;

if(startTime!=""&&endTime==""){
      alert("请选择结束时间");
	  return false;
   
}
else if(endTime!=""&&startTime==""){
      alert("请选择开始时间");
	  return false;
   }
else{
      document.location.href="<%=request.getContextPath()%>/gameRecord/gameRecord.do?action=gameRecordList&termOne="+encodeURI(document.all.termOne.value)
		+"&selectOne="+document.all.selectOne.options[document.all.selectOne.selectedIndex].value
		+"&pageSize="+document.all.pageSize.options[document.all.pageSize.selectedIndex].value
		+"&orderby="+document.all.orderby.options[document.all.orderby.selectedIndex].value
		+"&startTime="+document.all.startTime.value
		+"&endTime="+document.all.endTime.value
		+"&kindID="+<%=kindID%>;
   }
}   
</script>
</head>
<script src="../js/calendar1.js" type="text/javascript"></script>
<body onload="">
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
  <form id="form1" name="form1" method="post" onsubmit="return submted()" action="<%=request.getContextPath()%>/gameRecord/gameRecord.do?action=gameRecordList">
    <tr class="menutop">
      <td colspan="18" ><div align="center">真人-游戏记录列表</div>
	    <hr />
		<table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
    <tr>
      <td>用户查询<input name="termOne" size="12" type="text" class="input" id="termOne" value="<%=termOne%>" />
        <select name="selectOne" size="1" class="Select" id="selectOne">
		<option value="accounts">按用户名</option>
		<option value="betSerial">按局编号</option>
        </select>
		&nbsp;
        开始日期<input name="startTime" size="10" type="text" onClick="calendar()" value="<%=startTime%>"/>
		结束日期<input name="endTime" size="10" type="text" onClick="calendar()" value="<%=endTime%>"/>
		排序方式<select name="orderby" size="1" class="Select" id="orderby">
		<option value="generateTime">按下注时间</option>
		<option value="betResult">按输赢银子</option>
        </select>
        &nbsp;显示条数<select name="pageSize" size="1" class="Select" id="pageSize">
		<option value="30">显示30条</option>
		<option value="50">显示50条</option>
		<option value="100">显示100条</option>
        </select>
      <input type="button" name="button" value="查询" onclick="search()"/>|若为空则查询所有记录</td>
    </tr>
	</table>
	</form>
    <tr>
      <td class="tdcenter" align="center"><strong>序号</strong></td>
      <td class="tdcenter"><div align="center"><strong>百家乐局数</strong></div></td>
      <td class="tdcenter"><div align="center"><strong>房间类别</strong></div></td>
	  <td class="tdcenter"><div align="center"><strong>下注用户</strong></div></td>
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
		<td>${user.accounts}</td>
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
	    <td><div align="center">${user.createTime}</div></td>
		<td><div align="center">${user.createTime1}</div></td>
      </tr>
    </c:forEach>
	<tr><td colspan="18" align="center"><font color="red"><c:out value="${returnInfo}"></c:out></font></td></tr>
  </table>
  <table width="100%" border="0" cellpadding="2" cellspacing="1">
    
    <tr class="tdcenter" align="left">
       <td colspan="4">总记录:${page.totalRecord}条/${page.totalPage}页
        <c:if test="${page.totalPage>=0}"  var="true">
　<a onclick="changepage(1)" style="cursor:hand">首页</a>
<a onclick="changepage(${page.curPage-1})" style="cursor:hand">上页</a>
<select name="select" onchange="jumppage(this.value);">
          <c:forEach var="i" begin="1" end="${page.totalPage }" step="1"> <option value="${i}" 
              <c:if test="${i==page.curPage}" var="true">selected="selected"</c:if>
            >第${i}页
            </option>
          </c:forEach>
        </select>
        <a onclick="changepage(${page.curPage+1})" style="cursor:hand">下页</a>
        <a onclick="changepage(${page.totalPage})" style="cursor:hand">末页</a>	</c:if></td>
    </tr>
</table>
</body>
<script type="text/javascript">
var ttype=document.all.orderby;
var index="<%=orderby %>";
for(var i=0; i<ttype.options.length; i++){
	if(ttype.options[i].value==index){
		ttype.options[i].selected=true;break;
	}
}

var ttype1=document.all.pageSize;
var index1="<%=pageSize %>";
for(var i=0; i<ttype1.options.length; i++){
	if(ttype1.options[i].value==index1){
		ttype1.options[i].selected=true;break;
	}
}

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
</script>
</html>

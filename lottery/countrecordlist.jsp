<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page isELIgnored="false" %> 
<%
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=10;
	int roomId = 1;
	String peroidnum = "";
	String time = "";
	int num = 1000;
	String createTime = "";
	String endTime = "";
	try{
		if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("pageIndex")!=null){
			pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
		}
		if(request.getParameter("roomId")!=null){
			roomId=Integer.parseInt(request.getParameter("roomId"));
		}
		if(request.getParameter("peroidnum")!=null){
			peroidnum=request.getParameter("peroidnum");
		}
		if(request.getParameter("time")!=null){
			time=request.getParameter("time");
		}
		if(request.getAttribute("num")!=null){
			num=Integer.parseInt(request.getAttribute("num").toString());
		}
		if(request.getAttribute("startTime")!=null){
			createTime=(String)request.getAttribute("startTime");
		}
		if(request.getAttribute("endTime")!=null){
			endTime=(String)request.getAttribute("endTime");
		}
		recordIndex=(pageIndex-1)*pageSize+1;
	}catch(Exception e){
		e.printStackTrace();
	}
	recordIndex=(pageIndex-1)*pageSize+1;
	String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>即时注单管理</title>
<link href="../css/user.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
}
-->
</style>
<link href="../css/AdminCss.css" rel="stylesheet" type="text/css" />
</head>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg); 
function   refresh()   {   
  history.go(0);   
  }   
  var t = setTimeout("refresh()",<%=request.getParameter("time")==null?"60000":request.getParameter("time")%>);   

function queryrooms(){
		 var roomId=document.all.roomId.options[document.all.roomId.selectedIndex].value;
		 var roomId1=parseInt(roomId);
		 if(roomId1==16||roomId1==17){
		     document.location.href="countList.do?action=countList&roomId="+roomId;
		 }
		 else{
		     document.location.href="countList.do?action=countList&roomId="+roomId;
		 }
}

function submted(){
	var d1 = document.getElementById("date").value;
	var d2 = document.getElementById("date1").value;
	if (d1 == ""){
		alert("请选择开始日期");
		return false;
	} else if (d2 == ""){
		alert("请选择结束日期");
		return false;
	}
	return true;
}

</script>

<body>
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
    <tr>
          <td height="25" colspan="9" class="menutop"><div style="width:90%; float:left; text-align:center; line-height:25px;">统计列表</div>
      </td>
    </tr>
  </table>
  <form id="form1" name="form1" method="post" onsubmit="return submted()" action="countList.do?action=countList">
 <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
     <tr class="tdright">
      <td height="25">游戏类型 
        <select name="roomId" id="roomId" onChange="queryrooms()">
      <c:forEach var="r" items="${roomType}">
	         <option value="${r.roomId}">${r.displayName}</option>	
	 </c:forEach>
        </select>
        开始时间
        
        <input size="10" id="date" type="text" onClick="WdatePicker()"  name="startTime" value="${startTime}" readonly/>
        截止时间
        <input size="10" id="date1" type="text" onClick="WdatePicker()" name="endTime" value="${endTime}" readonly/>
        定时刷新
		<SELECT name="time" onchange="form1.submit()">    
    	<option value="60000">60秒钟刷新一次</OPTION>   
  		<option value="120000">2分钟刷新一次</OPTION>   
  		<option value="300000">5分钟刷新一次</OPTION> 
		<option value="600000">10分钟刷新一次</OPTION>   
  		</SELECT>  
        <input type="submit" name="Submit" value="查询" />
       </td>
     </tr>
</table>
</form>
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
    <tr class="tdcenter">
      <td height="30" align="center"><strong>统计下注总银子</strong></td>
      <td align="center"><strong>操作</strong></td>
    </tr>
   
    
    <tr class="tdcenter">
      <td height="25" align="center"><strong>${sumGoldBet}</strong></td>
      <td align="center"><c:if test="${roomId==16||roomId==17}" var="true"><a href="<%=request.getContextPath() %>/lottery/notesingleList.do?action=note3DsingleList&roomId=${roomId}&startTime=${startTime}&endTime=${endTime}">详情(点击查看详情)</a></c:if>
	  <c:if test="${roomId!=16&&roomId!=17}" var="false"><a href="<%=request.getContextPath() %>/lottery/notesingleList.do?action=noteSingleList&roomId=${roomId}&startTime=${startTime}&endTime=${endTime}">详情(点击查看详情)</a></c:if></td>
    </tr>
</table>
</body>
<script type="text/javascript">
var tstate=document.all.roomId;
var index1="<%=roomId %>";
for(var i=0; i<tstate.options.length; i++){
			if(tstate.options[i].value==index1){
				tstate.options[i].selected=true;break;
			}
		}
var ttime=document.all.time;
var index2="<%=time %>";
for(var i=0; i<ttime.options.length; i++){
			if(ttime.options[i].value==index2){
				ttime.options[i].selected=true;break;
			}
		}
</script> 
</html>

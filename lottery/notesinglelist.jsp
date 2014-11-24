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
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg); 
function   refresh()   {   
  history.go(0);   
  }   
  var t = setTimeout("refresh()",<%=request.getParameter("time")==null?"60000":request.getParameter("time")%>);   
  
function jumppage(value)
   {
      window.location.href="notesingleList.do?action=noteSingleList&curPage="+value+"&roomId=<%=roomId%>&peroidnum=															      <%=peroidnum %>&num=<%=num %>&startTime=<%=createTime %>&endTime=<%=endTime %>";
   }

function queryrooms(){
		 var roomId=document.all.roomId.options[document.all.roomId.selectedIndex].value;
		 var roomId1=parseInt(roomId);
		 if(roomId1==16||roomId1==17){
		     document.location.href="notesingleList.do?action=note3DsingleList&roomId="+roomId+"&num="+<%=num %>;
		 }
		 else{
		     document.location.href="notesingleList.do?action=noteSingleList&roomId="+roomId+"&num="+<%=num %>;
		 }
}
function isNumber(oNum){
 if(!oNum) return false; //line:160
 if(oNum.value=="") return false;
 var vv = oNum.value.replace(/[^\d]/g,'');
 var strP=/^\d+$/;
  if (!isNaN(oNum.value)) {
        return true;
    } 
  else{
        alert("请输入数字");
        oNum.value=vv;
 		oNum.focus();
 		return false; 
    }
}
</script>

<body>
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
    <tr>
          <td height="25" colspan="9" class="menutop"><div style="width:90%; float:left; text-align:center; line-height:25px;">即时注单列表</div>
      </td>
    </tr>
</table>
  <form id="form1" name="form1" method="post" action="notesingleList.do?action=noteSingleList">
 <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
     <tr class="tdright">
      <td height="25">游戏类型 
        <select name="roomId" id="roomId" onChange="queryrooms()">
      <c:forEach var="r" items="${rooms}">
	         <option value="${r.roomId}">${r.roomName}</option>	
	 </c:forEach>
        </select>
        <label>
        <input type="text" size="12" name="peroidnum" onKeyUp="isNumber(this)" value="<%=peroidnum %>">
		 <select name="selectOne" size="1" class="Select" id="selectOne">
		<option value="peroidnum">按期数</option>
        </select>
		定时刷新
		<SELECT name="time" onchange="form1.submit()">    
    	<option value="60000">60秒钟刷新一次</OPTION>   
  		<option value="120000">2分钟刷新一次</OPTION>   
  		<option value="300000">5分钟刷新一次</OPTION> 
		<option value="600000">10分钟刷新一次</OPTION>   
  		</SELECT>  
		警戒线
		<input type="text" maxlength="9" name="num" size="10" value="<%=num %>">
        <input type="submit" name="Submit" value="查询">
        </label></td>
     </tr>
</table>
</form>
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
    <tr class="tdcenter">
      <td align="center"><strong>序号</strong></td>
      <td align="center"><strong>期数</strong></td>
      <td align="center"><strong>总银子</strong></td>
      <td align="center"><strong>开奖时间</strong></td>
      <td align="center"><strong>单</strong></td>
      <td align="center"><strong>双</strong></td>
      <td align="center"><strong>大</strong></td>
      <td align="center"><strong>小</strong></td>
      <td align="center"><strong>龙</strong></td>
      <td align="center"><strong>和</strong></td>
      <td align="center"><strong>虎</strong></td>
      <td align="center"><strong>押数</strong></td>
      <td align="center"><strong>操作</strong></td>
    </tr>
   
    <c:forEach var="notesingle" items="${noteSingleList}">
    <tr class="tdcenter">
      <td height="25" align="center"><%=recordIndex++ %></td>
      <td align="center">${notesingle.peroidnum}</td>
      <td align="center" bgcolor="${notesingle.color[0]}">${notesingle.totalGoldBet}</td>
      <td align="center" bgcolor="${notesingle.color[0]}">${notesingle.createTime}</td>
      <td align="center" bgcolor="${notesingle.color[1]}">${notesingle.totalSingle}</td>
      <td align="center" bgcolor="${notesingle.color[2]}">${notesingle.totalPairs}</td>
      <td align="center" bgcolor="${notesingle.color[3]}">${notesingle.totalLarge}</td>
      <td align="center" bgcolor="${notesingle.color[4]}">${notesingle.totalSmall}</td>
      <td align="center" bgcolor="${notesingle.color[5]}">${notesingle.totalSerpent}</td>
      <td align="center" bgcolor="${notesingle.color[6]}">${notesingle.totalPeace}</td>
      <td align="center" bgcolor="${notesingle.color[7]}">${notesingle.totalTiger}</td>
      <td align="center" bgcolor="${notesingle.color[8]}">${notesingle.totalRate}</td>
      <td align="center"><a href="notesingleList.do?action=noteDetails&roomId=${notesingle.roomId}&peroidnum=${notesingle.peroidnum}">详情</a></td>
    </tr>
    </c:forEach>
	
	<tr class="wenzi_32">
                <td height="25" colspan="14" align="center" valign="middle" bgcolor="#FFFFFF" class="keno2">
				<font color="red"><c:out value="${returnInfo}"></c:out></font></td>
    </tr>
</table>
  <table width="100%" border="0" cellpadding="2" cellspacing="1">
	  <tr class="tdcenter">
       <td colspan="4">总记录:${page.totalRecord}条/${page.totalPage}页 
	     <c:if  test='${page.totalPage>0}'  var='true'> 
        <!--<select name="select" onChange="jumppage(this.value);">
          <c:forEach var="i" begin="1" end="${page.totalPage }" step="1"> <option value="${i}" 
              <c:if test="${i==page.curPage}" var="true">selected="selected"</c:if>
            >第${i}页
            </option>
          </c:forEach>
        </select> -->
　  <a href="notesingleList.do?action=noteSingleList&curPage=1&roomId=<%=roomId%>&peroidnum=<%=peroidnum %>&num=<%=num %>&startTime=<%=createTime %>&endTime=<%=endTime %>">首页</a> 
	<a href="notesingleList.do?action=noteSingleList&curPage=${page.curPage-1}&roomId=<%=roomId%>&peroidnum=<%=peroidnum %>&num=<%=num %>&startTime=<%=createTime %>&endTime=<%=endTime %>">上页</a> 
	&nbsp;<a href="notesingleList.do?action=noteSingleList&curPage=${page.curPage+1 }&roomId=<%=roomId%>&peroidnum=<%=peroidnum %>&num=<%=num %>&startTime=<%=createTime %>&endTime=<%=endTime %>">下页</a> 
        <a href="notesingleList.do?action=noteSingleList&curPage=${page.totalPage}&roomId=<%=roomId%>&peroidnum=<%=peroidnum %>&num=<%=num %>&startTime=<%=createTime %>&endTime=<%=endTime %>">末页</a></c:if></td>
</td>
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

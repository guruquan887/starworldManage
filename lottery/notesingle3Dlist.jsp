<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page isELIgnored="false" %> 
<%
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=10;
	int roomId = 1;
	String peroidnum = "";
	String peroidnum1 = "";
	String time="";
	int num = 1000;
	try{
		if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("pageIndex")!=null){
			pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
		}
		if(request.getAttribute("peroidnum")!=null){
			peroidnum=String.valueOf(request.getAttribute("peroidnum"));
		}
		if(request.getParameter("roomId")!=null){
			roomId=Integer.parseInt(request.getParameter("roomId"));
		}
		if(request.getParameter("peroidnum")!=null){
			peroidnum=request.getParameter("peroidnum");
		}
		if(request.getParameter("peroidnum1")!=null){
			peroidnum1=request.getParameter("peroidnum1");
		}
		if(request.getParameter("time")!=null){
			time=request.getParameter("time");
		}
		if(request.getAttribute("num")!=null){
			num=Integer.parseInt(request.getAttribute("num").toString());
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
<link href="../css/AdminCss.css" rel="stylesheet" type="text/css" /></head>
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
function refresh()   {   
  history.go(0);   
  }   
  var t = setTimeout("refresh()",<%=request.getParameter("time")==null?"60000":request.getParameter("time")%>); 
function jumppage(value)
   {
      window.location.href="notesingleList.do?action=noteSingleList&curPage="+value+"&roomId=<%=roomId%>&peroidnum=<%=peroidnum %>&num="+<%=num %>;
   }

function queryrooms(){
		 var roomId=document.all.roomId.options[document.all.roomId.selectedIndex].value;
		 var peroidnum=document.all.peroidnum.options[document.all.peroidnum.selectedIndex].value;
		 var roomId1=parseInt(roomId);
		 if(roomId1==16||roomId1==17){
		  document.location.href="notesingleList.do?action=note3DsingleList&roomId="+roomId1+"&peroidnum="+peroidnum+"&num="+<%=num %>;
		 }
		 else{
		     document.location.href="notesingleList.do?action=noteSingleList&roomId="+roomId+"&num="+<%=num %>;
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
  <form id="form1" name="form1" method="post" action="notesingleList.do?action=note3DsingleList">
 <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
     <tr class="tdright">
      <td height="25">游戏类型 
        <select name="roomId" id="roomId" onChange="queryrooms()">
      <c:forEach var="r" items="${rooms}">
	         <option value="${r.roomId}">${r.roomName}</option>	
	 </c:forEach>
        </select>
        <label>
		 <select name="peroidnum" id="peroidnum" onChange="queryrooms()">
		  <c:forEach var="p" items="${peroidnums}">
		<option value="${p.peroidnum}">${p.peroidnum}</option>
		 </c:forEach>
        </select>
		定时刷新
		<SELECT name="time" onChange="form1.submit()">    
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
    <tr class="tdcenter" bgcolor="#CCCC00" >
      <td height="30" align="center"><strong>期数</strong></td>
      <td width="15%" align="center"><%=peroidnum %></td>
      <td align="center" width="10%"><strong>下注和值大</strong></td>
      <td align="center" width="15%" bgcolor="${note3D.color[9]}">${note3D.count[9]}</td>
      <td align="center" width="10%"><strong>下注个位单</strong></td>
      <td align="center" width="15%" bgcolor="${note3D.color[19]}">${note3D.count[19]}</td>
    </tr>
    <tr class="tdcenter" bgcolor="#33CC00">
      <td height="30" width="10%" align="center"><strong>下注总银子</strong></td>
      <td align="center" bgcolor="${note3D.color[0]}">${note3D.count[0]}</td>
      <td align="center"><strong>下注和值小</strong></td>
      <td align="center" bgcolor="${note3D.color[10]}">${note3D.count[10]}</td>
      <td align="center"><strong>下注个位双</strong></td>
      <td align="center" bgcolor="${note3D.color[20]}">${note3D.count[20]}</td>
    </tr>
    <tr class="tdcenter" bgcolor="#0033CC">
      <td height="30" align="center"><strong>下注1D</strong></td>
      <td align="center" bgcolor="${note3D.color[1]}">${note3D.count[1]}</td>
      <td align="center"><strong>下注百位单</strong></td>
      <td align="center" bgcolor="${note3D.color[11]}">${note3D.count[11]}</td>
      <td align="center"><strong>下注个位大</strong></td>
      <td align="center" bgcolor="${note3D.color[21]}">${note3D.count[21]}</td>
    </tr>
    <tr class="tdcenter">
      <td height="30" align="center"><strong>下注2D</strong></td>
      <td align="center" bgcolor="${note3D.color[2]}">${note3D.count[2]}</td>
      <td align="center"><strong>下注百位双</strong></td>
      <td align="center" bgcolor="${note3D.color[12]}">${note3D.count[12]}</td>
      <td align="center"><strong>下注个位小</strong></td>
      <td align="center" bgcolor="${note3D.color[22]}">${note3D.count[22]}</td>
    </tr>
    <tr class="tdcenter">
      <td height="30" align="center"><strong>下注单选</strong></td>
      <td align="center" bgcolor="${note3D.color[3]}">${note3D.count[3]}</td>
      <td align="center"><strong>下注百位大</strong></td>
      <td align="center" bgcolor="${note3D.color[13]}">${note3D.count[13]}</td>
      <td align="center"><strong>下注对子</strong></td>
      <td align="center" bgcolor="${note3D.color[23]}">${note3D.count[23]}</td>
    </tr>
    <tr class="tdcenter">
      <td height="30" align="center"><strong>下注组一</strong></td>
      <td align="center" bgcolor="${note3D.color[4]}">${note3D.count[4]}</td>
      <td align="center"><strong>下注百位小</strong></td>
      <td align="center" bgcolor="${note3D.color[14]}">${note3D.count[14]}</td>
      <td align="center"><strong>下注杂六</strong></td>
      <td align="center" bgcolor="${note3D.color[24]}">${note3D.count[24]}</td>
    </tr>
    <tr class="tdcenter">
      <td height="30" align="center"><strong>下注组三</strong></td>
      <td align="center" bgcolor="${note3D.color[5]}">${note3D.count[5]}</td>
      <td align="center"><strong>下注十位单</strong></td>
      <td align="center" bgcolor="${note3D.color[15]}">${note3D.count[15]}</td>
      <td align="center"><strong>下注顺子</strong></td>
      <td align="center" bgcolor="${note3D.color[25]}">${note3D.count[25]}</td>
    </tr>
    <tr class="tdcenter">
      <td height="30" align="center"><strong>下注组六</strong></td>
      <td align="center" bgcolor="${note3D.color[6]}">${note3D.count[6]}</td>
      <td align="center" ><strong>下注十位双</strong></td>
      <td align="center" bgcolor="${note3D.color[16]}">${note3D.count[16]}</td>
      <td align="center"><strong>下注半顺</strong></td>
      <td align="center" bgcolor="${note3D.color[26]}">${note3D.count[26]}</td>
    </tr>
    <tr class="tdcenter">
      <td height="30" align="center"><strong>下注和值单</strong></td>
      <td align="center" bgcolor="${note3D.color[7]}">${note3D.count[7]}</td>
      <td align="center"><strong>下注十位大</strong></td>
      <td align="center" bgcolor="${note3D.color[17]}">${note3D.count[17]}</td>
      <td align="center"><strong>下注豹子</strong></td>
      <td align="center" bgcolor="${note3D.color[27]}">${note3D.count[27]}</td>
    </tr>
    <tr class="tdcenter">
      <td height="30" align="center"><strong>下注和值双</strong></td>
      <td align="center" bgcolor="${note3D.color[8]}">${note3D.count[8]}</td>
      <td align="center" ><strong>下注十位小</strong></td>
      <td align="center" bgcolor="${note3D.color[18]}">${note3D.count[18]}</td>
      <td align="center">&nbsp;</td>
      <td align="center">&nbsp;</td>
    </tr>
	<tr class="wenzi_32">
       <td height="25" colspan="19" align="center" valign="middle" bgcolor="#FFFFFF" class="keno2">
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
var tperoid=document.getElementById("peroidnum");
var index2="<%=peroidnum %>";
for(var i=0; i<tperoid.options.length; i++){
			if(tperoid.options[i].value==index2){
				tperoid.options[i].selected=true;break;
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

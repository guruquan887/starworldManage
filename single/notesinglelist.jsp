<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<% 
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires",0); 
%> 
<%	int pageIndex=1;
	int pageSize=10;
	int roomID = 281;
	String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}
	if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("pageIndex")!=null){
			pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
		}
			if(request.getParameter("roomID")!=null){
			roomID=Integer.parseInt(request.getParameter("roomID"));
		}
		String orderby="bureauID";
	if(request.getParameter("orderby")!=null){
		orderby=request.getParameter("orderby");
	}
	int zC = 1;//全部
	int time = 30000;
	try{
		if(request.getParameter("time")!=null){
			time=Integer.parseInt(request.getParameter("time"));
		}
		if(request.getParameter("zc")!=null){
			zC=Integer.parseInt(request.getParameter("zc"));
		}
	}catch(Exception e){
		e.printStackTrace();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
function   refresh()   {   
  history.go(0);   
  }   
  //var t = setTimeout("refresh()",<%=request.getParameter("time")==null?"600000":request.getParameter("time")%>);   

function jumppage(value)
   {
      window.location.href="<%=request.getContextPath()%>/single/notesingleList.do?action=noteSingleList&curPage=" +value+"&pageSize=<%=pageSize %>&orderby=<%=orderby %>&roomID=<%=roomID%>";
   }
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/single/notesingleList.do?action=noteSingleList&curPage="+pageNo+"&pageSize=<%=pageSize %>"+"&orderby=<%=orderby %>&roomID=<%=roomID%>";
}

function search(){
	document.location.href="<%=request.getContextPath()%>/single/notesingleList.do?action=noteSingleList&pageSize="+document.all.pageSize.options[document.all.pageSize.selectedIndex].value+"&orderby="+document.all.orderby.options[document.all.orderby.selectedIndex].value+"&roomID="+document.all.roomID.options[document.all.roomID.selectedIndex].value;
}
</script>
<body>
	<Div class="title">即时注单 
	<span>  
	<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/single/notesingleList.do?action=noteSingleList">
	<lable><%--下线代理<input name="accounts" id="accounts" type="text" class="input_width3" />--%>
	<select name="pageSize" size="1" class="Select" id="pageSize">
		<option value="30">30条</option>
		<option value="50">50条</option>
		<option value="100">100条</option>
		<option value="200">200条</option>
        </select>
	<select name="orderby" size="1" class="Select" id="orderby">
		<option value="bureauID">局数</option>
		<option value="score">投注金额</option>
        </select>
	<SELECT name="time" onchange="form1.submit()">    
    	<option value="60000">60秒钟</OPTION>   
  		<option value="120000">2分钟</OPTION>   
  		<option value="300000">5分钟</OPTION> 
		<option value="600000" selected="selected">10分钟</OPTION>   
  	</SELECT>
	<input name="Submit3" type="button" class="input" style="cursor:pointer" value="搜索" onClick="search()"/>
	<input name="Submit2" type="submit" class="input" style="cursor:pointer" value="即时更新" />
	
</lable>  </form></span></Div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td width="100" height="46" class="menutop">抬号</td>
            <td class="menutop">类型</td>
            <td class="menutop">日期</td>
            <td class="menutop">状态</td>
            <td class="menutop">局数</td>
			<td class="menutop">靴-局数</td>
			<td class="menutop">投注单数</td>
			<td class="menutop">投注金额</td>
			<td class="menutop">庄/大/龙</td>
			<td class="menutop">闲/小/虎</td>
			<%--<td class="menutop">开牌</td>--%>
			<td class="menutop">游戏结果</td>
            <td class="menutop">上局游戏结果</td>
          </tr>
		   <c:forEach var="note" items="${noteSingleList}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter">${note.tableID}</td>
            <td class="tdcenter">${note.serverName}</td>
            <td class="tdcenter">${note.leaveDataTime}</td>
            <td class="tdcenter"><font <c:if test="${note.status==2}" var="true">color="#FF0000"</c:if>>${note.statusName}</font></td>
            <td class="tdcenter">${note.bureauID}</td>
			<td class="tdcenter">${note.bureauID}</td>
            <td class="tdcenter">${note.betCount}</td>
            <td class="tdcenter">${note.score}</td>
			<td class="tdcenter">${note.zScore}</td>
            <td class="tdcenter">${note.xScore}</td>
            <%--<td class="tdcenter"><c:if test="${note.tableID==274}" var="true">闲</c:if>${note.cardData}<c:if test="${note.tableID==274}" var="true">庄</c:if></td>--%>
            <td class="tdcenter">${note.winAreas}</td>
            <td class="tdcenter">${note.prevWinAreas}</td>
          </tr>
		   </c:forEach>
		    <tr>
                <td class="tdcenter" colspan="27" align="center" valign="middle" bgcolor="#FFFFFF">
				<font color="red"><c:out value="${returnInfo}"></c:out></font></td>
       </tr>
		    <tr>
            <td colspan="26" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
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

<%--<Div class="title">
	即时注单 百人二张</Div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td class="menutop">顺门</td>
            <td class="menutop">顺门角</td>
            <td class="menutop">对门</td>
            <td class="menutop">倒门角</td>
			<td class="menutop">倒门</td>
			<td class="menutop">桥</td>
          </tr>
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter">1000</td>
            <td class="tdcenter">200</td>
            <td class="tdcenter">0</td>
            <td class="tdcenter">200</td>
			<td class="tdcenter">0</td>
            <td class="tdcenter">200</td>
          </tr>
        </table>
<Div class="title">
	即时注单 牛牛</Div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td class="menutop">天</td>
            <td class="menutop">地</td>
            <td class="menutop">玄</td>
            <td class="menutop">黄</td>
          </tr>
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter">1000</td>
            <td class="tdcenter">200</td>
            <td class="tdcenter">0</td>
            <td class="tdcenter">200</td>

          </tr>
        </table>--%>
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
var ttype2=document.all.roomID;
var index2="<%=roomID %>";
for(var i=0; i<ttype2.options.length; i++){
	if(ttype2.options[i].value==index2){
		ttype2.options[i].selected=true;break;
	}
}

</script>
</html>
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
	String startTime="";
	if(request.getParameter("startTime")!=null){
		startTime=request.getParameter("startTime");
	}
	String endTime="";
	if(request.getParameter("endTime")!=null){
		endTime=request.getParameter("endTime");
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<style type="text/css">
<!--
a:link { text-decoration: none}
a:active { text-decoration: none }
a:visited { text-decoration: none }
-->
</style>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">

var msg="<%=msg %>";
if(msg!='')alert(msg);
var tone=encodeURI("<%=termOne %>");
function jumppage(value)
   {
      window.location.href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gameUserInOutRecord&userID=<%=userID%>&curPage=" +value+"&pageSize=<%=pageSize %>&termOne=<%=termOne%>&selectOne=<%=selectOne %>"+"&startTime="+document.all.startTime.value+"&endTime="+document.all.endTime.value;
   }

function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gameUserInOutRecord&curPage="+pageNo+"&termOne="+tone+"&userID=<%=userID%>&selectOne=<%=selectOne %>&pageSize=<%=pageSize %>"+"&startTime="+document.all.startTime.value+"&endTime="+document.all.endTime.value;
}
function search(){
	document.location.href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gameUserInOutRecord&termOne="+encodeURI(document.all.termOne.value)
		+"&userID=<%=userID%>&selectOne="+document.all.selectOne.options[document.all.selectOne.selectedIndex].value
		+"&pageSize="+document.all.pageSize.options[document.all.pageSize.selectedIndex].value+"&startTime="+document.all.startTime.value+"&endTime="+document.all.endTime.value;
}
</script>
<body>
	<div class="tab clearfix">
		<ul>
		<li><a href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gamebankRecord&userID=<%=userID%>">钱庄记录</a></li>
			<li class="active"><a href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gameUserInOutRecord&userID=<%=userID%>">进出记录</a></li>
			<li><a href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gameUserDrawRecord&userID=<%=userID%>">游戏记录</a></li>
			<li><a href="<%=request.getContextPath()%>/gold/cardList.do?action=cardRecord&userID=<%=userID%>">充值记录</a></li>
		</ul>
	</div>
	<Div class="title"><span>  
	  <form id="form1" name="form1" method="post" action="">
	    <label>
		<select name="pageSize" size="1" class="Select" id="pageSize">
		<option value="30">显示30条</option>
		<option value="50">显示50条</option>
		<option value="100">显示100条</option>
		<option value="200">显示200条</option>
        </select>
      
		<input name="startTime" onClick="WdatePicker()" type="text"  size="15" />
至
<input name="endTime" type="text" onClick="WdatePicker()"  size="15" />
	    <input name="Submit2" type="button" class="input" value="搜索" onClick="search()"/>
	    </label>
      </form>
	</span>进出记录</Div>
	<form id="form2" name="form2" method="post" onsubmit="return delCheck()" action="<%=request.getContextPath()%>/member/gamegoldList.do?action=delAll">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margingold">
          <tr>
            <td width="60" class="menutop">进入时间</td>
            <td class="menutop" width="150">进入地址</td>
            <td class="menutop" width="80">进入机器</td>
            <td class="menutop" width="100">游戏</td>
            <td class="menutop" width="100">房间</td>
            <td class="menutop" width="150">积分</td>
            <td class="menutop" width="80">奖牌</td>
            <td class="menutop" width="100">魅力</td>
            <td class="menutop" width="150">离开时间</td>
            <td class="menutop" width="150">离开地址</td>
            <td class="menutop" width="80">离开原因</td>
            <td class="menutop" width="80">积分变化</td>
            <td class="menutop" width="80">魅力变更</td>
            <td class="menutop" width="120">经验变更</td>
            <td class="menutop" width="120">税收</td>
            <td class="menutop" width="60">游戏时长（秒）</td>
            <td class="menutop" width="60">在线时长（秒）</td>
            <td class="menutop" width="60">总局</td>
            <td class="menutop" width="60">赢局</td>
            <td class="menutop" width="60">输局</td>
            <td class="menutop" width="60">和局</td>
            <td class="menutop" width="60">逃局</td>
          </tr>
		  <c:forEach var="user" items="${userInOutList}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter">${user.enterTime}</td>
            <td class="tdcenter">&nbsp;${user.enterClientIP}</td>
            <td class="tdcenter">&nbsp;${user.enterMachine}</td>
            <td class="tdcenter">&nbsp;${user.kindName}</td>
            <td class="tdcenter">&nbsp;${user.serverName}</td>
            <td class="tdcenter">${user.enterScore}</td>
            <td class="tdcenter">${user.enterUserMedal}</td>
            <td class="tdcenter">${user.enterLoveliness}</td>
            <td class="tdcenter">${user.leaveTime}</td>
            <td class="tdcenter">${user.leaveClientIP}</td>
            <td class="tdcenter">${user.leaveReason}</td>
            <td class="tdcenter">${user.score}</td>
            <td class="tdcenter">${user.loveliness}</td>
            <td class="tdcenter">&nbsp;${user.experience}</td>
            <td class="tdcenter">&nbsp;${user.revenue}</td>
            <td class="tdcenter">&nbsp;${user.playTimeCount}</td>
            <td class="tdcenter">${user.onLineTimeCount}</td>
            <td class="tdcenter">${user.totalCount}</td>
            <td class="tdcenter">${user.winCount}</td>
            <td class="tdcenter">${user.lostCount}</td>
            <td class="tdcenter">${user.drawCount}</td>
            <td class="tdcenter">${user.fleeCount}</td>
          </tr>
		  </c:forEach>
          <tr>
            <td colspan="36" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
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
</form>
</body>
<script type="text/javascript">
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

var ttype1=document.all.pageSize;
var index1="<%=pageSize %>";
for(var i=0; i<ttype1.options.length; i++){
	if(ttype1.options[i].value==index1){
		ttype1.options[i].selected=true;break;
	}
}


</script>
</html>
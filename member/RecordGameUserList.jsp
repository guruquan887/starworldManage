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
	String accounts="";
	if(request.getParameter("accounts")!=null){
		accounts=request.getParameter("accounts");
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
      window.location.href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gameUserInfoRecord&userID=<%=userID%>&curPage=" +value+"&pageSize=<%=pageSize %>&termOne=<%=termOne%>&selectOne=<%=selectOne %>"+"&startTime="+document.all.startTime.value+"&endTime="+document.all.endTime.value+"&accounts="+document.all.accounts.value;
   }

function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gameUserInfoRecord&userID=<%=userID%>&curPage="+pageNo+"&termOne="+tone+"&selectOne=<%=selectOne %>&pageSize=<%=pageSize %>"+"&startTime="+document.all.startTime.value+"&endTime="+document.all.endTime.value+"&accounts="+document.all.accounts.value;
}
function search(){
	document.location.href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gameUserInfoRecord&userID=<%=userID%>&pageSize="+document.all.pageSize.options[document.all.pageSize.selectedIndex].value+"&startTime="+document.all.startTime.value+"&endTime="+document.all.endTime.value+"&accounts="+document.all.accounts.value;
}
</script>
<body>
<%--	<div class="tab clearfix">
		<ul>
		<li><a href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gamebankRecord&userID=<%=userID%>">钱庄记录</a></li>
			<li><a href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gameUserInOutRecord&userID=<%=userID%>">进出记录</a></li>
			<li class="active"><a href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gameUserDrawRecord&userID=<%=userID%>">游戏记录</a></li>
			<li><a href="<%=request.getContextPath()%>/gold/cardList.do?action=cardRecord&userID=<%=userID%>">充值记录</a></li>
		</ul>
	</div>--%>
	<Div class="title"><span>  
	  <form id="form1" name="form1" method="post" action="">
	    <label>
		<input name="accounts" type="text" id="accounts" value="用户名搜索"  size="15" />
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
	</span>玩家输赢记录</Div>
	<form id="form2" name="form2" method="post" onsubmit="return delCheck()" action="<%=request.getContextPath()%>/member/gamegoldList.do?action=delAll">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margingold">
          <tr>
            <td class="menutop">插入时间</td>
			<td class="menutop">用户名</td>
            <td class="menutop">游戏</td>
            <td class="menutop">房间</td>
            <td class="menutop">局数</td>
            <td class="menutop">桌子</td>
            <td class="menutop">输赢</td>
            <td class="menutop">税收/服务费</td>
            <td class="menutop">开始时间</td>
            <td class="menutop">结束时间</td>
          </tr>
		  <c:forEach var="user" items="${userDrawRecordList}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter">${user.insertTime}</td>
			<td class="tdcenter">${user.accounts}</td>
            <td class="tdcenter">&nbsp;${user.kindName}</td>
            <td class="tdcenter">&nbsp;${user.serverName}</td>
            <td class="tdcenter">&nbsp;${user.bureauID}</td>
            <td class="tdcenter">&nbsp;${user.tableID}</td>
            <td class="tdcenter">${user.waste}</td>
            <td class="tdcenter">${user.revenue}</td>
            <td class="tdcenter">${user.startTime}</td>
            <td class="tdcenter">${user.concludeTime}</td>
            </tr>
		  </c:forEach>
          <tr>
            <td colspan="23" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
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

var ttype1=document.all.pageSize;
var index1="<%=pageSize %>";
for(var i=0; i<ttype1.options.length; i++){
	if(ttype1.options[i].value==index1){
		ttype1.options[i].selected=true;break;
	}
}
function addListener(element,e,fn){    
    	 		if(element.addEventListener){    
          			element.addEventListener(e,fn,false);    
     			 } else {    
         	 		element.attachEvent("on" + e,fn);    
     	 		 }    
			}
			var username = document.getElementById("accounts");
			addListener(username,"click",function(){
				username.value = "";
			})

</script>
</html>
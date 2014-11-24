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
	int type = 0;
	if(request.getParameter("type")!=null){
	    type = Integer.parseInt(request.getParameter("type"));
	}
	String orderby="RegisterDate";
	if(request.getParameter("orderby")!=null){
		orderby=request.getParameter("orderby");
	}
	String username = "";
	if(request.getSession().getAttribute("username")!=null){
		username = (String)request.getSession().getAttribute("username");
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
	String spreaderID="";
	if(request.getParameter("spreaderID")!=null){
		spreaderID=request.getParameter("spreaderID");
	}
	int type = 0;
if(request.getParameter("type")!=null){
	    type = Integer.parseInt(request.getParameter("type"));
	}
%>
<head>
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
var tone=encodeURI("<%=termOne %>");
function jumppage(value)
   {
      window.location.href="<%=request.getContextPath()%>/member/gameuserList.do?action=gameUserList&curPage=" +value+"&pageSize=<%=pageSize %>&orderby=<%=orderby %>&termOne=<%=termOne%>&selectOne=<%=selectOne %>&spreaderID=<%=spreaderID%>&type=<%=type%>";
   }
function deleteUser(gameUserId)
	{
		document.location.href="gameuserDelete.do?gameUserID="+gameUserId+"&type=1";
	}
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/member/gameuserList.do?action=gameUserList&curPage="+pageNo+"&termOne="+tone+"&selectOne=<%=selectOne %>&pageSize=<%=pageSize %>"+"&orderby=<%=orderby %>&spreaderID=<%=spreaderID%>&type=<%=type%>";
}
function search(){
	document.location.href="<%=request.getContextPath()%>/member/gameuserList.do?action=gameUserList&termOne="+encodeURI(document.all.termOne.value)
		+"&selectOne="+document.all.selectOne.options[document.all.selectOne.selectedIndex].value
		+"&orderby="+document.all.orderby.options[document.all.orderby.selectedIndex].value
		+"&spreaderID=<%=spreaderID%>&type=<%=type%>";
}
function ejiaA1(o,a,b,c,d){
var t=document.getElementById(o).getElementsByTagName("tr");
for(var i=0;i<t.length;i++){
t[i].style.backgroundColor=(t[i].sectionRowIndex%2==0)?a:b;
t[i].onclick=function(){
if(this.x!="1"){
this.x="1";
this.style.backgroundColor=d;
}else{
this.x="0";
this.style.backgroundColor=(this.sectionRowIndex%2==0)?a:b;
}
}
t[i].onmouseover=function(){
if(this.x!="1")this.style.backgroundColor=c;
}
t[i].onmouseout=function(){
if(this.x!="1")this.style.backgroundColor=(this.sectionRowIndex%2==0)?a:b;
}
}
}

 function getAll()
{
	var elements=document.form2.checkbox;
	var length=elements.length;
	for(var i=0;i<length;i++)
	{
		var element=elements[i];
		element.checked=true;
	}
}
function checkAllBox(i){
	
		var isChecked=(document.form2.checkAll.checked == true);
	    var elements=document.form2.elements;
	    var counter=elements.length;
	    for(i=0;i<counter;i++){
			var element=elements[i];
		if(element.type=="checkbox"){
			element.checked=isChecked;
		}
	}
}
function delCheck(){
		var flag10=0;
         var radio10=document.getElementsByName("checkbox");
         for(var i=0;i<radio10.length;i++)
   {
     if(radio10.item(i).checked==true)
        {
     flag10=1;
           break;
   }
 }
  if(!flag10){
         alert("请选择删除对象");
         return false;
  }
   return true;
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<%=request.getContextPath()%>/css/main.css" rel="stylesheet" type="text/css" />
</head>
<style type="text/css">
<!--
a:link { text-decoration: none}
a:active { text-decoration: none }
a:visited { text-decoration: none }
-->
</style>
<body>
	<Div class="title"><span>  
	  <form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/member/gameuserList.do?action=gameUserList">
	    <label>
	    <input name="termOne" type="text" class="input_width2" id="termOne"/>
	    <select name="selectOne" class="input_width1" id="selectOne">
	      <option value="accounts">用户名</option>
	      <option value="gameID">游戏ID</option>
	      <option value="tyAccounts">停用账户</option>
		  <option value="xxAccounts">推广用户</option>
		  <option value="interType">内部账号</option>
        </select>
		<select name="orderby" class="input_width1" id="orderby">
          <option value="registerDate">注册时间</option>
          <option value="totalScore">银子</option>
          <option value="onLine">在线</option>
        </select>
	    <input name="Submit2" type="button" class="input" value="搜索" onClick="search()"/>
	    </label>
	    <%--<input name="Submit" type="button" class="input" value="新增" onclick="window.location.href='usernameAdd.html'" />--%>
      </form>
	</span>会员</Div>
	<form id="form2" name="form2" method="post" onsubmit="return delCheck()" action="<%=request.getContextPath()%>/member/gameuserList.do?action=delAll">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td width="30" class="menutop"><input type="checkbox" name="checkAll" value="checkAll" onClick="checkAllBox(0)" /></td>
            <td width="30" class="menutop">序号</td>
            <td class="menutop">ID</td>
            <td class="menutop">用户名</td>
            <td class="menutop">昵称</td>
            <td class="menutop">真实姓名</td>
			<td class="menutop">号码</td>
            <td class="menutop">银子</td>
            <td class="menutop">下线</td>
            <td class="menutop">税收</td>
            <td class="menutop">胜率</td>
            <td class="menutop">在线时间</td>
            <td class="menutop">游戏时间</td>
            <td class="menutop">级别</td>
            <td class="menutop">积分</td>
            <td class="menutop">注册日期</td>
            <td class="menutop">登录IP</td>
            <td class="menutop">状态</td>
            <td class="menutop">账号状态</td>
            <td class="menutop">操作</td>
          </tr>
		  <c:forEach var="user" items="${userlist}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter"><input name="type" id="type" type="hidden" value="<%=type%>" /><input type="checkbox" id="checkbox" value="${user.userID}" name="checkbox" /></td>
            <td class="tdcenter"><%=recordIndex++%></td>
            <td class="tdcenter">${user.gameID}</td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/member/gameuserList.do?action=userLogs&userID=${user.userID}">${user.accounts}</a></td>
            <td class="tdcenter">&nbsp;${user.regAccounts}</td>
            <td class="tdcenter">&nbsp;${user.realName}</td>
			<td class="tdcenter">&nbsp;${user.telphone}</td>
            <td class="tdcenter">${user.totalScore}</td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/member/gameuserList.do?action=gameUserList&spreaderID=${user.userID}">${user.number}</a></td>
            <td class="tdcenter">${user.tax}%</td>
            <td class="tdcenter">${user.winRate}%</td>
            <td class="tdcenter">${user.onLineTimeCount}</td>
            <td class="tdcenter">${user.playTimeCount}</td>
            <td class="tdcenter"><c:if test="${user.memberOrder==0}"  var="true">${user.memberName}</c:if><c:if test="${user.memberOrder==1}"  var="true"><span class="colorred">${user.memberName}</span></c:if><c:if test="${user.memberOrder==2}"  var="true"><span class="colorblue">${user.memberName}</span></c:if><c:if test="${user.memberOrder==3}"  var="true"><span class="coloryellow">${user.memberName}</span></c:if><c:if test="${user.memberOrder==4}"  var="true"><span class="colorzs">${user.memberName}</span></c:if></td>
            <td class="tdcenter">${user.jifen}</td>
            <td class="tdcenter">${user.registerDate}</td>
            <td class="tdcenter">${user.lastLogonIP}</td>
            <td class="tdcenter"><span class="${user.bgcss}">${user.onLineName}</span></td>
            <td class="tdcenter"><c:if test="${user.zhState==0}" var="true">
              <a href="<%=request.getContextPath()%>/member/gameuserList.do?action=zhFrozen&userState=${user.zhState}&userID=${user.userID}&curPage=<%=pageIndex%>&type=0"><font color="blue">${user.zhStateName}</font></a>
            </c:if><c:if test="${user.zhState==1}" var="true">
              <a href="<%=request.getContextPath()%>/member/gameuserList.do?action=zhFrozen&userState=${user.zhState}&userID=${user.userID}&curPage=<%=pageIndex%>&type=0"><font color="red">${user.zhStateName}</font></a>
            </c:if></td>
            <td class="tdcenter"><%--<a href="<%=request.getContextPath()%>/member/gameuserList.do?action=zhFrozen&userState=${user.zhState}&userID=${user.userID}&curPage=<%=pageIndex%>"><span class="${user.zhStateCss}">${user.zhStateName}</span></a> /--%> <a href="<%=request.getContextPath()%>/member/gameuserList.do?action=preUpdate&userID=${user.userID}&curPage=<%=pageIndex%>&selectOne=<%=selectOne%>&type=2">详情</a> / <a href="<%=request.getContextPath()%>/member/gameuserList.do?action=MemberPreCun&userID=${user.userID}">存入</a> / <a href="<%=request.getContextPath()%>/member/gameuserList.do?action=MemberPreQu&userID=${user.userID}">取出</a> <c:if test="${roleId==1}" var="true">/ <a href="<%=request.getContextPath()%>/member/gameuserList.do?action=deleteUser&userID=${user.userID}&curPage=<%=pageIndex%>&selectOne=<%=selectOne%>&type=2">删除</a></c:if> / <c:if test="${user.userType==2}" var="true"><a href="<%=request.getContextPath()%>/member/gameuserList.do?action=preauthRelation&userID=${user.userID}">权限</a></c:if></td>
          </tr>
		  </c:forEach>
		  <c:if test="${returnInfo!=''}"  var="true">
		  <tr>
                <td class="tdcenter" colspan="21" align="center" valign="middle" bgcolor="#FFFFFF">
				<font color="red"><c:out value="${returnInfo}"></c:out></font></td>
       </tr>
	   </c:if>
          <tr>
            <td colspan="11" align="left" class="tdlefts"><input name="curPage" type="hidden" value="<%=pageIndex%>"/><input name="Submit3" type="submit" class="input" value="删除" />           </td>
            <td colspan="10" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
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
var index2="<%=termOne%>";
for(var i=0; i<ttype3.length; i++){
	if(ttype3.value==index2){
		ttype3.selected=true;break;
	}
}

var ttype=document.all.orderby;
var index="<%=orderby %>";
for(var i=0; i<ttype.options.length; i++){
	if(ttype.options[i].value==index){
		ttype.options[i].selected=true;break;
	}
}
</script>
</html>
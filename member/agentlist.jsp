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
	int type = -1;
	int spreaderID = 100102;
	int proxyID = 1;
	int levelID = 1;
	int isFreeze = -1;
	String accounts = "admin";
if(request.getParameter("accounts")!=null){
      accounts = request.getParameter("accounts");
}
if(request.getParameter("sxaccounts")!=null){
      accounts = request.getParameter("sxaccounts");
}
	if(request.getParameter("spreaderID")!=null){
	    spreaderID = Integer.parseInt(request.getParameter("spreaderID"));
	}
	if(request.getParameter("proxyID")!=null){
	    proxyID = Integer.parseInt(request.getParameter("proxyID"));
	}
	if(request.getParameter("levelID")!=null){
	    levelID = Integer.parseInt(request.getParameter("levelID"));
	}
	if(request.getParameter("type")!=null){
	    type = Integer.parseInt(request.getParameter("type"));
	}
	String orderby="createTime";
	if(request.getParameter("orderby")!=null){
		orderby=request.getParameter("orderby");
	}
	if(request.getParameter("isFreeze")!=null){
	    isFreeze = Integer.parseInt(request.getParameter("isFreeze"));
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
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
var tone=encodeURI("<%=termOne %>");
function jumppage(value)
   {
      window.location.href="<%=request.getContextPath()%>/member/gameAgentList.do?action=gameAgentList&curPage=" +value+"&pageSize=<%=pageSize %>&orderby=<%=orderby %>&termOne=<%=termOne%>&selectOne=<%=selectOne %>&isFreeze=<%=isFreeze%>&levelID=<%=levelID%>";
   }
function deleteUser(gameUserId)
	{
		document.location.href="<%=request.getContextPath()%>/member/gameuserDelete.do?gameUserID="+gameUserId+"&type=<%=type%>";
	}
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/member/gameAgentList.do?action=gameAgentList&curPage="+pageNo+"&termOne="+tone+"&selectOne=<%=selectOne %>&pageSize=<%=pageSize %>"+"&orderby=<%=orderby %>&isFreeze=<%=isFreeze%>&levelID=<%=levelID%>";
}
function search(){
	document.location.href="<%=request.getContextPath()%>/member/gameAgentList.do?action=gameAgentList&isFreeze="+document.all.isFreeze.options[document.all.isFreeze.selectedIndex].value+"&levelID=<%=levelID%>&termOne="+encodeURI(document.all.termOne.value)+"&selectOne="+document.all.selectOne.options[document.all.selectOne.selectedIndex].value+"&orderby="+document.all.orderby.options[document.all.orderby.selectedIndex].value;
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
	
<!--输入框提示-->
function toggleColorSearch(objElement) {
  if (objElement.className=='search-normal')
    objElement.className='search-focus';
  else
    objElement.className='search-normal';
}
var g_defaultSearchText = "";

function load()
{
	g_defaultSearchText = document.search.SearchText.value;	
}
function focusSearch()
{
	var f = document.search;	
	if (f.SearchText.value == g_defaultSearchText)
		f.SearchText.value = "";
}
function blurSearch()
{
	var f = document.search;	
	if (f.SearchText.value == "")
		f.SearchText.value = g_defaultSearchText;
}
	
</script>
<body>
	<Div class="title"><span>  
	 <form id="form1" name="form1" method="post" action="">
	    <label>
		<select name="orderby" size="1" class="input_width2" id="orderby">
		<option value="gold">银子排序</option>
		<option value="createTime">注册时间排序</option>
		<option value="proxyAccounts">用户名排序</option>
        </select>
		</label>
		<label>
	    <input name="termOne" id="termOne" type="text" class="input_width2" />
	    <select name="selectOne" id="selectOne" class="input_width1">
	      <option value="accounts">用户名</option>
		  <option value="nickName">昵称</option>
        </select>
		</label>
		<label>
		账号状态
		<select name="isFreeze" id="isFreeze"  class="input_width1">
		  <option value="-1">全部</option>
	      <option value="0">启用</option>
		  <option value="1">停用</option>
		  <option value="2">冻结</option>
        </select>
		</label>
	    <input name="Submit2" type="button" class="input" value="搜索" onClick="search()"/>
	    
	    <c:if test="<%=levelID==1%>" var="true"><input name="Submit" type="button" class="input" value="新增公司" onclick="window.location.href='<%=request.getContextPath()%>/member/gameAgentList.do?action=preAdd&spreaderID=<%=spreaderID%>&proxyID=<%=proxyID%>&accounts=<%=accounts%>&levelID=<%=levelID%>'" /></c:if><c:if test="<%=levelID==2%>" var="true"><input name="Submit" type="button" class="input" value="新增股东" onclick="window.location.href='<%=request.getContextPath()%>/member/gameAgentList.do?action=preAdd&spreaderID=<%=spreaderID%>&proxyID=<%=proxyID%>&accounts=<%=accounts%>&levelID=<%=levelID%>'" /></c:if><c:if test="<%=levelID==3%>" var="true"><input name="Submit" type="button" class="input" value="新增总代理" onclick="window.location.href='<%=request.getContextPath()%>/member/gameAgentList.do?action=preAdd&spreaderID=<%=spreaderID%>&proxyID=<%=proxyID%>&accounts=<%=accounts%>&levelID=<%=levelID%>'" /></c:if><c:if test="<%=levelID==4%>" var="true"><input name="Submit" type="button" class="input" value="新增代理" onclick="window.location.href='<%=request.getContextPath()%>/member/gameAgentList.do?action=preAdd&spreaderID=<%=spreaderID%>&proxyID=<%=proxyID%>&accounts=<%=accounts%>&levelID=<%=levelID%>'" /></c:if>&nbsp;<input name="Submit" type="button" class="input" value="新增会员" onclick="window.location.href='<%=request.getContextPath()%>/member/gameuserList.do?action=preAdd&spreaderID=<%=spreaderID%>&accounts=<%=accounts%>&levelID=<%=levelID%>'" />
      </form>

<%--	    <label>
		<input type="button" name="Submit4" class="input" style="cursor:pointer" value="冻结" onclick="tjSubmit(1)" />
        <input type="button" name="Submit5" class="input" style="cursor:pointer" value="解冻" onclick="tjSubmit(2)" />
	</label>--%>
	</span><c:if test="<%=levelID==1%>" var="true">公司</c:if><c:if test="<%=levelID==2%>" var="true">股东</c:if><c:if test="<%=levelID==3%>" var="true">总代理</c:if><c:if test="<%=levelID==4%>" var="true">代理</c:if></Div>
		<form id="form2" name="form2" method="post" action="">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
		 <%-- <td class="menutop"><input type="checkbox" name="checkAll" value="checkAll" onClick="checkAllBox(0)" /></td>--%>
            <td width="60" class="menutop">序号</td>
            <td class="menutop">ID</td>
            <td class="menutop">归属</td>
            <td class="menutop">用户名</td>
            <td class="menutop">昵称</td>
            <td class="menutop">类型</td>
            <td class="menutop">银子</td>
            <td class="menutop">下属</td>
            <td class="menutop">直属会员</td>
            <td class="menutop">股份比例</td>
            <td class="menutop">真人佣金</td>
            <td class="menutop">税收提成&nbsp;</td>
            <td class="menutop">注册日期</td>
            <td class="menutop">状态</td>
            <td class="menutop">操作</td>
          </tr>
		  <c:forEach var="user" items="${userlist}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
		 <%-- <td class="tdcenter"><input type="checkbox" id="checkbox" value="${user.userID}" <c:if test="${user.userID==1}" var="true">disabled="disabled"</c:if> name="checkbox" /></td>--%>
            <td class="tdcenter"><%=recordIndex++%></td>
            <td class="tdcenter">${user.userID}</td>
            <td class="tdcenter">${user.ssAccounts}</td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/member/gameAgentList.do?action=userLogs&userID=${user.userID}" title="查看操作日志">${user.accounts}</a></td>
            <td class="tdcenter">${user.regAccounts}</td>
            <td class="tdcenter"><c:if test="${user.levelID==1}" var="true">公司-1</c:if>&nbsp;<c:if test="${user.levelID==2}" var="true">股东-2</c:if><c:if test="${user.levelID==3}" var="true">总代理-3</c:if><c:if test="${user.levelID==4}" var="true">代理-4</c:if></td>
            <td class="tdcenter">${user.gold}</td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/member/gameAgentList.do?action=gameAgentList&prevproxy=${user.userID}&proxyID=${user.userID}&spreaderID=${user.userID}&levelID=${user.levelID+1}&accounts=${user.accounts}" title="点击查看下线">${user.number}</a></td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/member/gameuserList.do?action=gameUserList&proxyID=${user.userID}&levelID=${user.levelID+1}&accounts=${user.accounts}" title="点击查看下线会员">${user.mmnumber}</a></td>
            <td class="tdcenter">${user.winner}%</td>
            <td class="tdcenter">${user.brokerage}%</td>
            <td class="tdcenter">${user.taxRate}%</td>
            <td class="tdcenter">${user.createTime}</td>
            <td class="${user.zhStateCss}"><c:if test="${user.isFreeze==0}" var="true"><font color="blue">${user.zhStateName}</a></font></c:if><c:if test="${user.isFreeze==1}" var="true"><font color="red">${user.zhStateName}</a></font></c:if><c:if test="${user.isFreeze==2}" var="true"><font color="#999999">${user.zhStateName}</a></font></c:if></td>
            <td class="tdcenter"><a onClick="<c:if test='${user.isFreeze==0}' var='true'> alert('该账号已启用，无需再次启用'); return false;</c:if>" href="<%=request.getContextPath()%>/member/gameAgentList.do?action=zhFrozen&userState=${user.isFreeze}&userID=${user.userID}&curPage=<%=pageIndex%>&type=<%=type%>&levelID=<%=levelID%>">启用</a>/<a onClick="<c:if test="${user.isFreeze==1||user.isFreeze==2}" var="true"> alert('该账号已停用或冻结，无需再次停用'); return false;</c:if>"href="<%=request.getContextPath()%>/member/gameAgentList.do?action=zhFrozen&userState=${user.isFreeze}&userID=${user.userID}&curPage=<%=pageIndex%>&type=<%=type%>&levelID=<%=levelID%>">停用</a>/<a onClick="<c:if test="${user.isFreeze==2||user.isFreeze==1}" var="true"> alert('该账号已冻结或停用，无需再次冻结'); return false;</c:if>"href="<%=request.getContextPath()%>/member/gameAgentList.do?action=zhFrozen&userState=3&userID=${user.userID}&curPage=<%=pageIndex%>&type=<%=type%>&levelID=<%=levelID%>">冻结</a>/<a onClick="<c:if test="${user.isFreeze==0}" var="true"> alert('该账号已解冻，无需再次解冻'); return false;</c:if>" href="<%=request.getContextPath()%>/member/gameAgentList.do?action=zhFrozen&userState=${user.isFreeze}&userID=${user.userID}&curPage=<%=pageIndex%>&type=<%=type%>&levelID=<%=levelID%>">解冻</a>/<a href="<%=request.getContextPath()%>/member/gameAgentList.do?action=preAgentUpdate&type=<%=type%>&userID=${user.userID}&account=${user.accounts}&curPage=<%=pageIndex%>&selectOne=<%=selectOne%>&spreaderID=<%=spreaderID%>&accounts=<%=accounts%>&levelID=${user.levelID}">详情</a>/<a href="<%=request.getContextPath()%>/member/gameAgentList.do?action=preauthRelation&userID=${user.userID}&spreaderID=<%=spreaderID%>&levelID=${user.levelID}&accounts=<%=accounts%>">权限</a>/<a href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gamebankRecord&userID=${user.userID}">操作记录</a><c:if test="${roleId==1}" var="true">/<a href="<%=request.getContextPath()%>/member/gameAgentList.do?action=MemberPreCun&userID=${user.userID}&spreaderID=${user.prevProxy}&type=<%=type%>">存入</a>/<a href="<%=request.getContextPath()%>/member/gameAgentList.do?action=MemberPreQu&userID=${user.userID}&spreaderID=${user.prevProxy}&type=<%=type%>">取出</a> <%--<c:if test="${roleId==1}" var="true"> <a href="<%=request.getContextPath()%>/member/gameuserList.do?action=deleteUser&userID=${user.userID}&curPage=<%=pageIndex%>&selectOne=<%=selectOne%>&type=1">删除</a></c:if>--%></c:if></td>
          </tr>
		  </c:forEach>
		    <tr>
                <td class="tdcenter" colspan="27" align="center" valign="middle" bgcolor="#FFFFFF">
				<font color="red"><c:out value="${returnInfo}"></c:out></font></td>
       </tr>
          <tr>
            <td colspan="17" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
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
var ttype2=document.getElementById("selectOne");
var index2="<%=selectOne %>";
for(var i=0; i<ttype2.options.length; i++){
	if(ttype2.options[i].value==index2){
		ttype2.options[i].selected=true;break;
	}
}

var ttype3=document.getElementById("termOne");
var index3="<%=termOne%>";
for(var i=0; i<ttype3.length; i++){
	if(ttype3.value==index3){
		ttype3.selected=true;break;
	}
}

var ttype=document.getElementById("orderby");
var index="<%=orderby %>";
for(var i=0; i<ttype.options.length; i++){
	if(ttype.options[i].value==index){
		ttype.options[i].selected=true;break;
	}
}

var ttype4=document.getElementById("isFreeze");
var index4="<%=isFreeze %>";
for(var i=0; i<ttype4.options.length; i++){
	if(ttype4.options[i].value==index4){
		ttype4.options[i].selected=true;break;
	}
}
</script>
</html>
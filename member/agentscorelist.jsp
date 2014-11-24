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
	int interRoom = 0;
	int spreaderID = 0;
	String accounts = "admin/公司";
	if(request.getParameter("accounts")!=null){
		  accounts = request.getParameter("accounts");
	}
	if(request.getParameter("spreaderID")!=null){
	    spreaderID = Integer.parseInt(request.getParameter("spreaderID"));
	}
	if(request.getParameter("interRoom")!=null){
	    interRoom = Integer.parseInt(request.getParameter("interRoom"));
	}
	String orderby="score";
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
      window.location.href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gameScoreList&curPage=" +value+"&pageSize=<%=pageSize %>&orderby=<%=orderby %>&termOne=<%=termOne%>&selectOne=<%=selectOne %>";
   }
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gameScoreList&curPage="+pageNo+"&termOne="+tone+"&selectOne=<%=selectOne %>&pageSize=<%=pageSize %>"+"&orderby=<%=orderby %>";
}
function search(){
	document.location.href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gameScoreList&termOne="+encodeURI(document.all.termOne.value)
		+"&selectOne="+document.all.selectOne.options[document.all.selectOne.selectedIndex].value
		+"&pageSize="+document.all.pageSize.options[document.all.pageSize.selectedIndex].value;
}
</script>
<body>
	<Div class="title"><span>  
	  <form id="form1" name="form1" method="post" action="">
	    <label>
		<select name="pageSize" size="1" class="Select" id="pageSize">
		<option value="30">显示30条</option>
		<option value="50">显示50条</option>
		<option value="100">显示100条</option>
		<option value="200">显示200条</option>
        </select>
      
	    <input name="termOne" id="termOne" type="text" class="input_width2" value="<%=termOne%>" />
	    <select name="selectOne" id="selectOne" class="input_width1">
	      <option value="accounts">用户名</option>
	      <option value="gameID">游戏ID</option>
        </select>	    <input name="Submit2" type="button" class="input" value="搜索" onClick="search()"/>
	    </label>
	    <%--<input name="Submit" type="button" class="input" value="新增" onclick="window.location.href='<%=request.getContextPath()%>/member/gameuserList.do?action=preAdd&spreaderID=<%=spreaderID%>&accounts=<%=accounts%>'" />--%>
      </form>
	</span>积分管理</Div>
	<form id="form2" name="form2" method="post" onsubmit="return delCheck()" action="<%=request.getContextPath()%>/member/gamegoldList.do?action=delAll">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margingold">
          <tr>
            <td width="60" class="menutop">序号</td>
            <td class="menutop" width="150">用户名(游戏ID)</td>
            <td class="menutop" width="100">积分</td>
            <td class="menutop" width="60">总局</td>
            <td class="menutop" width="60">赢局</td>
            <td class="menutop" width="60">输局</td>
            <td class="menutop" width="60">和局</td>
            <td class="menutop" width="60">逃局</td>
            <td class="menutop" width="120">登录次数</td>
            <td class="menutop" width="170">游戏时长(时)</td>
            <td class="menutop" width="170">在线时长(时)</td>
            <td class="menutop" width="200">最后登录时间</td>
            <td class="menutop" width="150">最后登录地址</td>
            <%--<td class="menutop">操作</td>--%>
          </tr>
		  <c:forEach var="user" items="${userlist}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter"><%=recordIndex++%></td>
            <td class="tdcenter">${user.accounts}(${user.gameID})</td>
            <td class="tdcenter">${user.score}</td>
            <td class="tdcenter">${user.totalCount}</td>
            <td class="tdcenter">${user.winCount}</td>
            <td class="tdcenter">${user.loseCount}</td>
            <td class="tdcenter">${user.drawCount}</td>
            <td class="tdcenter">${user.fleeCount}</td>
            <td class="tdcenter">${user.allLogonTimes}</td>
            <td class="tdcenter">${user.playTimeCount}</td>
            <td class="tdcenter">${user.onLineTimeCount}</td>
            <td class="tdcenter">${user.lastLoginTime}</td>
            <td class="tdcenter">${user.lastLoginIP}</td>
            <%--<td class="tdcenter"><a href="<%=request.getContextPath()%>/member/gameuserList.do?action=preUpdate&userID=${user.userID}&curPage=<%=pageIndex%>&selectOne=<%=selectOne%>&type=1">详情</a> / <a href="<%=request.getContextPath()%>/member/gameuserList.do?action=MemberPreCun&userID=${user.userID}">存入</a> / <a href="<%=request.getContextPath()%>/member/gameuserList.do?action=MemberPreQu&userID=${user.userID}">取出</a> / <c:if test="${roleId==1}" var="true"> <a href="<%=request.getContextPath()%>/member/gameuserList.do?action=deleteUser&userID=${user.userID}&curPage=<%=pageIndex%>&selectOne=<%=selectOne%>&type=1">删除</a></c:if> / <a href="<%=request.getContextPath()%>/member/gameuserList.do?action=preauthRelation&userID=${user.userID}">权限</a></td>--%>
          </tr>
		  </c:forEach>
		    <tr>
                <td class="tdcenter" colspan="13" align="center" valign="middle" bgcolor="#FFFFFF">
				<font color="red"><c:out value="${returnInfo}"></c:out></font></td>
       </tr>
          <tr>
            <td colspan="13" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
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

var ttype4=document.all.interRoom;
var index4="<%=interRoom %>";
for(var i=0; i<ttype4.options.length; i++){
	if(ttype4.options[i].value==index4){
		ttype4.options[i].selected=true;break;
	}
}

</script>
</html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=30;
	String orderby="RegisterDate";
	if(request.getParameter("orderby")!=null){
		orderby=request.getParameter("orderby");
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
<script language="javascript" type="text/javascript">
var tone=encodeURI("<%=termOne %>");
function jumpEnter(e,newpageNo) {
	// 响应回车
	var key = window.event ? e.keyCode : e.which;
	if (key == 13) {
		//alert('123123123');
		jumppage(newpageNo);
	}
}

function jumppage(value)
   {
      window.location.href="<%=request.getContextPath()%>/baob/gameUserBBTJ.do?action=gameUserBBTJ&curPage=" +value+"&pageSize=<%=pageSize %>&orderby=<%=orderby %>&termOne=<%=termOne%>&selectOne=<%=selectOne %>";
   }
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/baob/gameUserBBTJ.do?action=gameUserBBTJ&curPage="+pageNo+"&termOne="+tone+"&selectOne=<%=selectOne %>";
}
function search(){
	document.location.href="<%=request.getContextPath()%>/baob/gameUserBBTJ.do?action=gameUserBBTJ&termOne="+encodeURI(document.all.termOne.value)
		+"&selectOne="+document.all.selectOne.options[document.all.selectOne.selectedIndex].value;
}
</script>
<body>
	<Div class="title">
	  <span>  
	  <form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/baob/gameUserBBTJ.do?action=gameUserBBTJ">
	    <input name="termOne" type="text" class="input_width2" id="termOne" onkeypress="jumpEnter(event,1)"/>
	    <select name="selectOne" class="input_width1" id="selectOne">
	      <option value="accounts">用户名</option>
	      <option value="gameID">游戏ID</option>
        </select>
	    <%--<input name="textfield2" type="text" value="2011-05-06" size="15" />
至
<input name="textfield22" type="text" value="2011-05-06" size="15" />--%>
<input name="Submit2" type="button" class="input" value="搜索" onClick="search()"/>
	    <%--<input name="Submit" type="button" class="input" value="代理报表" onclick="window.location.href='baobAdmin.html'" />--%>
      </form>
	</span>
	报表管理—报表</Div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td colspan="4" class="tdleft">合计：</td>
            <td class="menutop">${sum.incomeScore}</td>
            <td class="menutop">${sum.giftScore}</td>
            <td class="menutop">${sum.transferScore}</td>
            <td class="menutop">${sum.rechargeMoney}</td>
            <td class="menutop">${sum.revenue}</td>
            <td class="menutop">${sum.totalScore}</td>
          </tr>
          <tr>
            <td width="60" class="menutop">序号</td>
            <td class="menutop">ID</td>
            <td class="menutop">用户名</td>
            <td class="menutop">昵称</td>
            <td class="menutop"><a href="<%=request.getContextPath()%>/baob/gameUserBBTJ.do?action=gameUserBBTJ&orderby=winlostScore">输赢</a></td>
            <td class="menutop"><a href="<%=request.getContextPath()%>/baob/gameUserBBTJ.do?action=gameUserBBTJ&orderby=zsGold">赠送</a></td>
            <td class="menutop"><a href="<%=request.getContextPath()%>/baob/gameUserBBTJ.do?action=gameUserBBTJ&orderby=SwapScore">转账</a></td>
            <td class="menutop"><a href="<%=request.getContextPath()%>/baob/gameUserBBTJ.do?action=gameUserBBTJ&orderby=CardGold">充值</a></td>
            <td class="menutop"><a href="<%=request.getContextPath()%>/baob/gameUserBBTJ.do?action=gameUserBBTJ&orderby=revenue">个人税收</a></td>
            <td class="menutop"><a href="<%=request.getContextPath()%>/baob/gameUserBBTJ.do?action=gameUserBBTJ&orderby=totalScore">会员结果</a></td>
          </tr>
		  <c:forEach var="user" items="${userbbtj}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter"><%=recordIndex++%></td>
            <td class="tdcenter">${user.gameID}</td>
            <td class="tdcenter">${user.accounts}</td>
            <td class="tdcenter">${user.regAccounts}</td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gamerecordList&userID=${user.userID}">${user.incomeScore}</a></td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/baob/gameUserBBTJ.do?action=userzsRecord&userID=${user.userID}">${user.giftScore}</a></td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/baob/gameUserBBTJ.do?action=userZZRecord&userID=${user.userID}">${user.transferScore}</a></td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/baob/gameUserBBTJ.do?action=userOLRecord&userID=${user.userID}">${user.rechargeMoney}</a></td>
            <td class="tdcenter">${user.revenue}</td>
            <td class="tdcenter">${user.totalScore}</td>
          </tr>
		  </c:forEach>
          <tr onmouseover="this.className='trover'" onmouseout="this.className='trout'">
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
</body>
</html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%
	String msg="";
	if(request.getAttribute("msg")!=null){
	msg=request.getAttribute("msg").toString();
	}

	int recordIndex=1;
	int pageIndex=1;
	int pageSize=10;
	String orderby="GameID";
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
	
	String termOne="输入关键字";
	if(request.getParameter("termOne")!=null){
		termOne=request.getParameter("termOne");
		termOne = new String(termOne.getBytes("ISO_8859_1"),"UTF-8");
	}
	String selectOne="";
	if(request.getParameter("selectOne")!=null){
		selectOne=request.getParameter("selectOne");
	}
	//request.getSession().setAttribute("fromurl","gameuserRaceList.do?"+request.getQueryString());
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript">
       //弹出友好提示
       var msg="<%=msg %>";
       if(msg!='')alert(msg);
       
var tone=encodeURI("<%=termOne %>");
function jumppage(value)
   {
      window.location.href="<%=request.getContextPath()%>/member/gameRaceList.do?action=userRaceList&curPage=" +value+"&pageSize=<%=pageSize %>&orderby=<%=orderby%>&termOne=<%=termOne%>&selectOne=<%=selectOne %>";
   }
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/member/gameRaceList.do?action=userRaceList&curPage="+pageNo+"&termOne="+tone
	+"&selectOne=<%=selectOne %>&pageSize=<%=pageSize %>"+
	"&orderby=<%=orderby %>";

}
function search(){
	document.location.href="<%=request.getContextPath()%>/member/gameRaceList.do?action=userRaceList&termOne="+encodeURI(document.all.termOne.value)
		+"&selectOne="+document.all.selectOne.options[document.all.selectOne.selectedIndex].value;

}
function savegroup(userID){
	var groupID=document.getElementById("g"+userID).value;
	//alert('userID:'+userID+",groupID:"+groupID);
	document.location.href="<%=request.getContextPath()%>/member/gameRaceList.do?action=savegroup&uid="+userID+"&groupid="+groupID;
}
function isNumber(oNum){

 if(!oNum) return false; 
 if(oNum.value=="") return false;
 var vv = oNum.value.replace(/[^\d]/g,'');
 var strP=/^\d+$/;
  if (!isNaN(oNum.value)) {
        return true;
    } else {
        alert("请输入数字");
        oNum.value=vv;
 		oNum.focus();
 		return false; 
    }
}
</script>
</head>
<body>
	<Div class="title"><span>
	<form id="form1" name="form1" method="post" action="">
	    <label>
	    <input name="termOne" type="text" class="input_width2" id="termOne"/>
	    <select name="selectOne" class="input_width1" id="selectOne">
	      <option value="accounts">用户名</option>
	      <option value="gameID">游戏ID</option>
	      <option value="tyAccounts">停用账户</option>
        </select>
	    <input name="Submit2" type="button" class="input" value="搜索" onclick="search()"/>
	    </label>
	  </form>
	</span>比赛账号</Div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td width="30" class="menutop">序号</td>
            <td class="menutop">ID</td>
            <td class="menutop">用户名</td>
            <td class="menutop">在线时间</td>
            <td class="menutop">银子</td>
            <td class="menutop">积份</td>
            <td class="menutop">注册日期</td>
            <td class="menutop">比赛账号</td>
            <td class="menutop">操作</td>
            <td class="menutop">分组</td>
          </tr>
		   <c:forEach var="user" items="${userlist}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter"><%=recordIndex++%></td>
            <td class="tdcenter">${user.gameID}</td>
            <td class="tdcenter"><a href="usernameLog.html">${user.accounts}</a></td>
            <td class="tdcenter">${user.onLineTimeCount}</td>
            <td class="tdcenter">${user.totalScore}</td>
            <td class="tdcenter">${user.jifen}</td>
            <td class="tdcenter">${user.registerDate}</td>
            <td class="tdcenter"><c:if test="${user.userRight>0}"  var="true">是</c:if>
			<c:if test="${user.userRight==0}"  var="true"><span class="colorred">否</span></c:if></td>
            <td class="tdcenter"><c:if test="${user.userRight==0}" var="true"><a href="<%=request.getContextPath()%>/member/gameRaceList.do?action=szbs&uid=${user.userID}">设为帐号</a></c:if>
	   <c:if test="${user.userRight>0}" var="true"><a href="<%=request.getContextPath()%>/member/gameRaceList.do?action=qxbs&uid=${user.userID}">取消帐号</a></c:if></td>
            <td class="tdcenter"><input name="g${user.userID}" id="g${user.userID}" maxlength="3" onkeyup="isNumber(this)" size="3" type="text" value="${user.groupID}"/>
              <input name="button1" type="button" class="input" onclick="savegroup(${user.userID})" value="保存分组" /></td>
          </tr>
		      </c:forEach>
<tr><td colspan="10" align="center"><font color="red"><c:out value="${returnInfo}"></c:out></font></td></tr>
          <tr>
            <td colspan="11" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
       <c:if test="${page.totalPage>0}"  var="true"> 
        
　<a onclick="changepage(1)" style="cursor:hand">首页</a>
<a onclick="changepage(${page.curPage-1})" style="cursor:hand">上页</a>
<select name="select" onChange="jumppage(this.value);">
          <c:forEach var="i" begin="1" end="${page.totalPage }" step="1"> <option value="${i}" 
              <c:if test="${i==page.curPage}" var="true">selected="selected"</c:if>
            >第${i}页
            </option>
          </c:forEach>
        </select>
        <a onclick="changepage(${page.curPage+1})" style="cursor:hand">下页</a>
        <a onclick="changepage(${page.totalPage})" style="cursor:hand">末页</a></c:if></td>
          </tr>
        </table>
</body>
</html>
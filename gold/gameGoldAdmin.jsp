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
	String orderby="totalScore";
	if(request.getParameter("orderby")!=null){
		orderby=request.getParameter("orderby");
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>"+orderby);
	}
	String selectOne="";
	if(request.getParameter("selectOne")!=null){
		selectOne=request.getParameter("selectOne");
	}
	String spreaderID="";
	if(request.getParameter("spreaderID")!=null){
		spreaderID=request.getParameter("spreaderID");
	}
%>
<head>
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
//function   refresh()   {   
//  history.go(0);   
//  }   
//  var t = setTimeout("refresh()",<%=request.getParameter("time")==null?"180000":request.getParameter("time")%>);   
var tone=encodeURI("<%=termOne %>");
//alert("<%=orderby%>");
function jumppage(value)
   {
      window.location.href="<%=request.getContextPath()%>/gold/gamegoldList.do?action=gameGoldList&curPage=" +value+"&pageSize=<%=pageSize %>&termOne=<%=termOne%>&selectOne=<%=selectOne %>&orderby=<%=orderby%>";
   }
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/gold/gamegoldList.do?action=gameGoldList&curPage="+pageNo+"&termOne="+tone+"&selectOne=<%=selectOne %>&pageSize=<%=pageSize %>&orderby=<%=orderby%>";
}
function search(){
	document.location.href="<%=request.getContextPath()%>/gold/gamegoldList.do?action=gameGoldList&termOne="+encodeURI(document.all.termOne.value)
		+"&selectOne="+document.all.selectOne.options[document.all.selectOne.selectedIndex].value+"&orderby="+document.getElementById("orderby").value;
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<Div class="title"><span>  
	  <form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/gold/gamegoldList.do?action=gameGoldList">
	    <label>
		<SELECT name="time" onchange="form1.submit()">
  		<option value="180000">3分钟刷新一次</OPTION>   
  		<option value="300000">5分钟刷新一次</OPTION> 
		<option value="600000">10分钟刷新一次</OPTION>   
  		</SELECT>  
	    <input name="termOne" type="text" class="input_width2" />
	    <select name="selectOne" class="input_width1">
	      <option value="accounts">用户名</option>
	      <option value="gameID">游戏ID</option>
		  <option value="interType">内部账号</option>
        </select>
		<select name="orderby" class="input_width1" id="orderby">
		  <option value="rechargeMoney">充值</option>
          <option value="totalScore">总银子</option>
		  <option value="exchangeMoney">兑换</option>
		  <option value="revenue">缴税</option>
        </select>
	    <input name="button1" type="button" class="input" value="搜索" onClick="search()"/>
	    </label>
	  </form>
	</span>用户银子</Div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td width="30" class="menutop">序号</td>
            <td class="menutop">ID</td>
            <td class="menutop">用户名</td>
            <td class="menutop">昵称</td>
            <td class="menutop">属性</td>
            <td class="menutop"><a href="<%=request.getContextPath()%>/gold/gamegoldList.do?action=gameGoldList&orderby=totalScore">总银子</a></td>
            <td class="menutop"><a href="<%=request.getContextPath()%>/gold/gamegoldList.do?action=gameGoldList&orderby=revenue">游戏税</a></td>
            <td class="menutop"><a href="<%=request.getContextPath()%>/gold/gamegoldList.do?action=gameGoldList&orderby=transferRevenue">转账税</a></td>
            <td class="menutop">钱庄</td>
            <td class="menutop">游戏</td>
            <td class="menutop">积分</td>
            <td class="menutop">推广提成</td>
			<td class="menutop"><a href="<%=request.getContextPath()%>/gold/gamegoldList.do?action=gameGoldList&orderby=rechargeMoney">充值银子</a></td>
            <td class="menutop"><a href="<%=request.getContextPath()%>/gold/gamegoldList.do?action=gameGoldList&orderby=exchangeMoney">兑换</a></td>
            <%--<td class="menutop">可兑换</td>--%>
          </tr>
		  <c:forEach var="user" items="${userlist}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'" style="${user.css}">
            <td class="tdcenter"><%=recordIndex++%></td>
            <td class="tdcenter">${user.gameID}</td>
            <td class="tdcenter">${user.accounts}</td>
            <td class="tdcenter">${user.regAccounts}</td>
            <td class="tdcenter">&nbsp;<c:if test="${user.bjlYJ>0}"  var="true">代理商</c:if>
			<c:if test="${user.bjlYJ==0}"  var="true">会员</c:if></td>
            <td class="tdcenter">${user.totalScore}</td>
            <td class="tdcenter">${user.revenue}</td>
            <td class="tdcenter">${user.transferRevenue}</td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/gold/gamegoldList.do?action=gamebankRecord&userID=${user.userID}" title="查看钱庄转账记录">${user.insureScore}</a></td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/gold/gamegoldList.do?action=gamerecordList&userID=${user.userID}" title="游戏输赢记录">${user.score}</a></td>
            <td class="tdcenter">${user.jifen}</td>
            <td class="tdcenter">${user.jsRevenue}</td>
			<td class="tdcenter"><a href="<%=request.getContextPath()%>/gold/gamegoldList.do?action=rechargeRecord&userID=${user.userID}">${user.rechargeMoney}</a></td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/gold/drawList.do?action=showDrawList&userID=${user.userID}">${user.exchangeMoney}</a></td>
            <%--<td class="tdcenter">${user.chaerMoney}</td>--%>
          </tr>
		  </c:forEach>
		   <tr>
                <td class="tdcenter" colspan="19" align="center" valign="middle" bgcolor="#FFFFFF">
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
</body>
<script type="text/javascript">
var ttype2=document.all.selectOne;
var index3="<%=selectOne %>";
for(var i=0; i<ttype2.options.length; i++){
	if(ttype2.options[i].value==index3){
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
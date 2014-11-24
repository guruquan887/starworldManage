<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=30;
	int applytype = 0;
	String orderby="applydate";
	if(request.getParameter("orderby")!=null){
		orderby=request.getParameter("orderby");
	}
	if(request.getParameter("applytype")!=null){
			applytype=Integer.parseInt(request.getParameter("applytype"));
		}
		if(request.getAttribute("applytype")!=null){
		    applytype = Integer.parseInt(request.getAttribute("applytype").toString());
		}
	try{
		System.out.println(request.getAttribute("pageSize")+","+request.getAttribute("pageIndex"));
		
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
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
var tone=encodeURI("<%=termOne %>");
function jumppage(value)
   {
      window.location.href="<%=request.getContextPath()%>/gold/drawList.do?action=showDrawList&curPage=" +value+"&pageSize=<%=pageSize %>&orderby=<%=orderby %>&termOne=<%=termOne%>&selectOne=<%=selectOne %>&applytype=<%=applytype%>";
   }
   
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/gold/drawList.do?action=showDrawList&curPage="+pageNo+"&termOne="+tone
	+"&selectOne=<%=selectOne %>&pageSize=<%=pageSize %>"+"&orderby=<%=orderby %>&applytype=<%=applytype%>";
}

function search(){
	document.location.href="<%=request.getContextPath()%>/gold/drawList.do?action=showDrawList&termOne="+encodeURI(document.all.termOne.value)
		+"&selectOne="+document.all.selectOne.options[document.all.selectOne.selectedIndex].value;

}
</script>
</head>
<body>
	<Div class="title"><span>  
	  <form id="form1" name="form1" method="post" action="">
	    <label>
	    <input name="termOne" type="text" class="input_width2" id="termOne" />
	    <select name="selectOne" class="input_width1" id="selectOne">
	      <option value="accounts">用户名</option>
	      <option value="gameID">游戏ID</option>
        </select>
	    <input name="Submit2" type="button" class="input" value="搜索" onClick="search()"/>
	    <%--<input name="Submit" type="button" class="input" value="存款" onclick="window.location.href='<%=request.getContextPath()%>/gold/yeepayList.do?action=showYeepayList'" />
	    <input name="Submit3" type="button" class="input" value="补单"  onclick="window.location.href='budian.html'" />--%>
	    </label>
	  </form>
	</span>提现记录</Div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td width="50" class="menutop">序号</td>
            <td class="menutop">用户名</td>
            <td class="menutop">银子</td>
            <%--<td class="menutop">方式</td>--%>
            <td class="menutop">金额</td>
            <td class="menutop">开户钱庄</td>
            <td class="menutop">持卡人姓名</td>
            <td class="menutop">电话</td>
            <td class="menutop">开户地址</td>
            <td class="menutop">开户卡号</td>
            <td class="menutop">取款申请时间</td>
            <td class="menutop">状态</td>
            <td class="menutop">操作</td>
          </tr>
		   <c:forEach var="user" items="${drawuserlist}">
          <tr onmouseover="this.className='trover'" onmouseout="this.className='trout'">
            <td class="tdcenter"><%=recordIndex++ %></td>
            <td class="tdcenter">${user.accounts}</td>
            <td class="tdcenter">${user.amount}</td>
            <%-- <td class="tdcenter">${user.typeName}</td>--%>
            <td class="tdcenter">${user.realRMB}</td>
            <td class="tdcenter">${user.bank}</td>
            <td class="tdcenter">${user.bankholdername}</td>
            <td class="tdcenter">${user.phone}</td>
            <td class="tdcenter">${user.bankaddress}</td>
            <td class="tdcenter">${user.bankaccount}</td>
            <td class="tdcenter">${user.drawdate}</td>
            <td class="tdcenter"><c:if test="${user.applytype!=1}"  var="true">${user.applystate}</c:if><c:if test="${user.applytype==1}"  var="true"><span class="red">${user.applystate}</span></c:if></td>
            <td class="tdcenter">&nbsp;<c:if test="${user.applytype==0}"  var="true"><a href="<%=request.getContextPath()%>/gold/drawList.do?action=dealDraw&userID=${user.userID}&express_ID=${user.express_ID}">审核</a> | <a href="<%=request.getContextPath()%>/gold/drawList.do?action=dealCancel&userID=${user.userID}&express_ID=${user.express_ID}">取消</a></c:if></td>
          </tr>
		  </c:forEach>
          <tr>
            <td colspan="13" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
        <c:if test="${page.totalPage>=0}"  var="true"> 
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
        <a onclick="changepage(${page.totalPage})" style="cursor:hand">末页</a>		</c:if></td>
          </tr>
        </table>
</body>
</html>
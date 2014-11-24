<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%
String msg="";
if(request.getAttribute("msg")!=null){
	msg=(String)request.getAttribute("msg");
}
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=10;
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
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户限红管理</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
}
-->
</style>
<link href="../css/user.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
function jumppage(value)
   {
      window.location.href="<%=request.getContextPath() %>/userbetlimit/betLimitList.do?action=betLimitList&curPage=" +value+"&pageSize=<%=pageSize %>&orderby=<%=orderby %>";
   }
   
function changepage(pageNo){
	document.location.href="<%=request.getContextPath() %>/userbetlimit/betLimitList.do?action=betLimitList&curPage="+pageNo
	+"&pageSize=<%=pageSize %>";
}
</script>
</head>
<body>
<form id="form2" name="form2" method="post" action="gamegoldSelect.do">
  <br/>
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
  <tr>
    <td height="25" colspan="9" class="menutop">限红管理列表</td>
  </tr>
    <tr class="tdcenter">
      <td align="center"><strong>列表ID</strong></td>
      <td><div align="center"><strong>上限</strong></div></td>
      <td><div align="center"><strong>下限</strong></div></td>
      <td><div align="center"><strong>筹码</strong></div></td>
      <td><div align="center"><strong>操作</strong></div></td>
    </tr>
    <c:forEach var="bet" items="${betLimitlist}">
    <tr class="tdcenter">
      <td height="25" align="center">${bet.id}</td>
      <td height="25"><div align="center">${bet.limit_Up}</div></td>
      <td height="25"><div align="center">${bet.limit_Down}</div></td>
      <td height="25"><div align="center">${bet.chip}</div></td>
      <td><div align="center">
	  <a href="<%=request.getContextPath()%>/userbetlimit/betLimitList.do?action=preUpdate&id=${bet.id}">修改</a></div></td>
    </tr>
    </c:forEach>
	<%--<tr class="tdcenter"><td colspan="6" align="center"><font color="red"><c:out value="${returnInfo}"></c:out></font></td></tr>--%>
  </table>
  <table width="100%" border="0" cellpadding="2" cellspacing="1">
    <tr>
       <td colspan="4">总记录:${page.totalRecord}条/${page.totalPage}页
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
        <a onclick="changepage(${page.totalPage})" style="cursor:hand">末页</a></c:if></td>
    </tr>
  </table>
</form>
</body>
</html>

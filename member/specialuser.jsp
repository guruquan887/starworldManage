<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=10;
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
<script language="javascript" type="text/javascript">
var tone=encodeURI("<%=termOne %>");
function jumppage(value)
   {
      window.location.href="<%=request.getContextPath()%>/member/gameSpecialList.do?action=gameSpecialList&curPage=" +value+"&termOne="+tone+"&selectOne=<%=selectOne %>";
   }
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/member/gameSpecialList.do?action=gameSpecialList&curPage="+pageNo+"&termOne="+tone+"&selectOne=<%=selectOne %>";
}
function search(){
	document.location.href="<%=request.getContextPath()%>/member/gameSpecialList.do?action=gameSpecialList&termOne="+encodeURI(document.all.termOne.value)
		+"&selectOne="+document.all.selectOne.options[document.all.selectOne.selectedIndex].value;
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<Div class="title"><span>  
	  <form id="form1" name="form1" method="post" action="">
	    <input name="termOne" type="text" class="input_width2" id="termOne"/>
	     <select name="selectOne" class="input_width1" id="selectOne">
	      <option value="accounts">用户名</option>
	      <option value="gameID">游戏ID</option>
        </select>
	    <input name="Submit2" type="button" class="input" value="搜索" onclick="search()" />
		<%--<input name="Submit2" type="button" class="input" value="新增" onclick="window.location.href='specialuserAdd.html'" />--%>
	  </form>
	</span>特殊账号</Div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td width="60" class="menutop">序号</td>
            <td class="menutop">游戏ID</td>
            <td class="menutop">注册用户</td>
            <td class="menutop">银子</td>
            <%--<td class="menutop">操作</td>--%>
          </tr>
		  <c:forEach var="user" items="${specialList}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter"><%=recordIndex++%></td>
            <td class="tdcenter">${user.gameID}</td>
            <td class="tdcenter">${user.accounts}</td>
            <td class="tdcenter">${user.insureScore}</td>
            <%--<td class="tdcenter"><a href="#">删除</a> </td>--%>
          </tr>
		  </c:forEach>
		   <tr>
            <td colspan="6" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
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
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
	int state = 0;
	if(request.getParameter("state")!=null){
		state = Integer.parseInt(request.getParameter("state"));
	}
	String orderby="generateTime";
	if(request.getParameter("orderby")!=null){
		orderby=request.getParameter("orderby");
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
%>
<head>
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
var tone=encodeURI("<%=termOne %>");
function jumppage(value)
   {
      window.location.href="<%=request.getContextPath()%>/integral/dhList.do?action=dhRecordList&curPage=" +value+"&pageSize=<%=pageSize %>&orderby=<%=orderby %>&termOne=<%=termOne%>&selectOne=<%=selectOne %>&state=<%=state%>";
   }
   
function search(){
	document.location.href="<%=request.getContextPath()%>/integral/dhList.do?action=dhRecordList&state=<%=state%>&termOne="+encodeURI(document.all.termOne.value);
}

function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/integral/dhList.do?action=dhRecordList&curPage="+pageNo+"&termOne="+tone+"&pageSize=<%=pageSize %>"+"&orderby=<%=orderby %>&state=<%=state%>";
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<Div class="title"><span>  
	  <form id="form1" name="form1" method="post" action="">
	    <label>
	    <input name="termOne" type="text" class="input_width2" />
	    <select name="select" class="input_width1">
	      <option selected="selected">兑换用户</option>
        </select>
	    <input name="button" onclick="search()" type="button" class="input" value="搜索" />
	    </label>
	  </form>
	</span>订单</Div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td width="60" class="menutop">序号</td>
            <td class="menutop">兑换用户</td>
            <td class="menutop">物品名称</td>
            <td class="menutop">兑换银两</td>
            <td class="menutop">兑换时间</td>
            <td class="menutop">兑换类型</td>
            <td class="menutop">订单状态</td>
            <td class="menutop">操作</td>
          </tr>
		  <c:forEach var="dhRecord" items="${dhRecordlist}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter"><%=recordIndex++%></td>
            <td class="tdcenter">${dhRecord.accounts}</td>
            <td class="tdcenter">${dhRecord.mallName}</td>
            <td class="tdcenter">${dhRecord.dhAmount}</td>
            <td class="tdcenter">${dhRecord.generateTime}</td>
            <td class="tdcenter">${dhRecord.typeName}</td>
            <td class="tdcenter">${dhRecord.stateName}</td>
            <td class="tdcenter"><c:if test="${dhRecord.state==0}" var="true"><a href="<%=request.getContextPath()%>/integral/dhList.do?action=queryDhRecord&userID=${dhRecord.userID}&express_ID=${dhRecord.express_ID}">查看/受理</a> / <a href="<%=request.getContextPath()%>/integral/dhList.do?action=cancelDhRecord&userID=${dhRecord.userID}&express_ID=${dhRecord.express_ID}">取消</a></c:if><c:if test="${dhRecord.state==1||dhRecord.state==2}" var="true"><a href="<%=request.getContextPath()%>/integral/dhList.do?action=queryDhRecord&userID=${dhRecord.userID}&express_ID=${dhRecord.express_ID}">查看</a> / <a href="<%=request.getContextPath()%>/integral/dhList.do?action=deleteDhRecord&userID=${dhRecord.userID}&express_ID=${dhRecord.express_ID}">删除</a></c:if></td>
          </tr>
		  </c:forEach>
		    <tr>
            <td colspan="9" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
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
var ttype3=document.all.termOne;
var index2="<%=termOne%>";
for(var i=0; i<ttype3.length; i++){
	if(ttype3.value==index2){
		ttype3.selected=true;break;
	}
}

</script>
</html>
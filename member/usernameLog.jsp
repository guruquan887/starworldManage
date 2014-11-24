<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=100;
	int userID = 0;
	String startTime = "";
	String endTime = "";
	String orderby="operateTime";
	String operateName = "";
	if(request.getParameter("orderby")!=null){
		orderby=request.getParameter("orderby");
	}
	if(request.getParameter("userID")!=null){
		userID = Integer.parseInt(request.getParameter("userID"));
	}
	if(request.getParameter("startTime")!=null){
			startTime = request.getParameter("startTime");
		}
		if(request.getParameter("endTime")!=null){
			endTime = request.getParameter("endTime");
		}
	try{
	
		if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("pageIndex")!=null){
			pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
		}
		if(request.getAttribute("operateName")!=null){
			operateName=(request.getAttribute("operateName").toString());
		}
		}catch(Exception e){
			e.printStackTrace();
	}
	String msg="";
	if(request.getAttribute("msg")!=null){
	msg=(String)request.getAttribute("msg");
}
	recordIndex=(pageIndex-1)*pageSize+1;

%>
<head>
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
function jumppage(value)
   {
      window.location.href="<%=request.getContextPath()%>/member/gameuserList.do?action=userLogs&curPage=" +value+"&pageSize=<%=pageSize %>&orderby=<%=orderby %>&userID=<%=userID%>&startTime=<%=startTime%>&endTime=<%=endTime%>";
   }
   
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/member/gameuserList.do?action=userLogs&curPage="+pageNo+"&pageSize=<%=pageSize %>&orderby=<%=orderby %>&userID=<%=userID%>&startTime=<%=startTime%>&endTime=<%=endTime%>";
}

function submted(){
	var d1 = document.getElementById("startTime").value;
	var d2 = document.getElementById("endTime").value;
    var r = checkTime();
	if(d2<d1){
	    alert("开始日期需小于结束日期");
		return false;
	}
	if(d1 !=""){
	    if(d2<=d1){
	      alert("开始日期需小于结束日期");
		  return false;
	    }
	}
	if(d2 !=""){
	    if(d1 == ""){
	      alert("查看日期填写不完整");
		  return false;
	    }
	}
    if(checkTime()) return r;
	if (d1 == ""||d2==""){
		alert("请选择查看日期");
		return false;
	}
	return true;
}

</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
	<Div class="title"><span>
	<form id="form1" name="form1" method="post" onsubmit="return submted()" action="<%=request.getContextPath()%>/member/gameuserList.do?action=userLogs">
<input name="startTime" id="startTime" onClick="WdatePicker()" readonly="readonly" type="text" value="" size="15" />
至
<input name="endTime" id="endTime" onClick="WdatePicker()" readonly="readonly" type="text" value="" size="15" />
<input name="userID" type="hidden" value="<%=userID%>" />
<input name="Submit2" type="submit" class="input" value="搜索" /><input name="" type="button" class="input" value="刷新"  onclick="window.location.href='<%=request.getContextPath()%>/member/gameuserList.do?action=userLogs&userID=<%=userID%>'" /><input name="" type="button" class="input" value="删除日志"  onclick="window.location.href='<%=request.getContextPath()%>/member/gameAgentList.do?action=delLogs&userID=<%=userID%>'" />
</form>
	</span>账户管理—${accounts}操作日志</Div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td width="40" class="menutop">序号</td>
            <td class="menutop">操作内容</td>
            <td width="100" class="menutop">登录地址</td>
            <td width="150" class="menutop">操作时间</td>
          </tr>
		  <c:forEach var="operatelist" items="${operatelist}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter"><%=recordIndex++ %></td>
            <td class="tdright">${operatelist.operateDetails}</td>
            <td class="tdcenter">${operatelist.operateIP}</td>
            <td class="tdcenter">${operatelist.operateTime}</td>
          </tr>
         </c:forEach>
		 <tr>
                <td class="tdcenter" colspan="4" align="center" valign="middle">
				<font color="red"><c:out value="${returnInfo}"></c:out></font></td>
       </tr>
          <tr>
            <td colspan="5" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
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
        <a onclick="changepage(${page.totalPage})" style="cursor:hand">末页</a></c:if> </td>
          </tr>
        </table>
</body>
</html>
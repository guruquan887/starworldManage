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
	int userID = 0;
	String orderby="RegisterDate";
	if(request.getParameter("orderby")!=null){
		orderby=request.getParameter("orderby");
	}
	try{
		if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("userID")!=null){
			userID=Integer.parseInt(request.getAttribute("userID").toString());
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
	String accounts="";
	if(request.getAttribute("accounts")!=null){
		accounts=request.getAttribute("accounts").toString();
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
	String startTime="";
	if(request.getParameter("startTime")!=null){
		startTime=request.getParameter("startTime");
	}
	String endTime="";
	if(request.getParameter("endTime")!=null){
		endTime=request.getParameter("endTime");
	}
	if(request.getParameter("userID")!=null){
		userID=Integer.parseInt(request.getParameter("userID"));
	}
	
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=accounts%></title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
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
      window.location.href="<%=request.getContextPath()%>/baob/gameUserBBTJ.do?action=userOLRecord&curPage=" +value+"&startTime=<%=startTime%>&endTime=<%=endTime%>";
   }
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/baob/gameUserBBTJ.do?action=userOLRecord&curPage="+pageNo+"&userID=<%=userID%>&startTime=<%=startTime%>&endTime=<%=endTime%>";
}
function search(){
var startTime = document.all.startTime.value;
var endTime = document.all.endTime.value;
if(startTime!=""){
     if(endTime==""){
	     alert("请选择结束时间");
		 return false;
	 }
}

	document.location.href="<%=request.getContextPath()%>/baob/gameUserBBTJ.do?action=userOLRecord&userID=<%=userID%>&startTime="+document.all.startTime.value
		+"&endTime="+document.all.endTime.value;
}
</script>
<body>
	<Div class="title">
		<span>  
	  <form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/baob/gameUserBBTJ.do?action=userOLRecord">
	    <input name="startTime" onClick="WdatePicker()" type="text" value="" size="15" />
至
<input name="endTime" type="text" onClick="WdatePicker()" value="" size="15" />
<input name="Submit2" type="button" class="input" value="搜索" onClick="search()"/>
      </form>
	</span>
	充值记录:<%=accounts%></Div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td width="60" class="menutop">序号</td>
            <td class="menutop">充值时间</td>
            <td class="menutop">服务类别</td>
            <td class="menutop">用户名</td>
            <td class="menutop">游戏ID</td>
            <td class="menutop">订单号码</td>
            <td class="menutop">卡名称</td>
            <td class="menutop">卡价格</td>
            <td class="menutop">赠送银子</td>
            <td class="menutop">用户银子</td>
            <td class="menutop">订单数量</td>
            <td class="menutop">订单金额</td>
            <td class="menutop">实付金额</td>
            <td class="menutop">充值地址</td>
            <td class="menutop">操作用户</td>
          </tr>
		  <c:forEach var="user" items="${userOLRecord}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter"><%=recordIndex++%></td>
            <td class="tdcenter">${user.applyDate}</td>
            <td class="tdcenter">${user.shareName}</td>
            <td class="tdcenter">${user.accounts}</td>
            <td class="tdcenter">${user.gameID}</td>
            <td class="tdcenter">${user.serialID}</td>
            <td class="tdcenter">${user.cardName}</td>
            <td class="tdcenter">${user.cardPrice}</td>
            <td class="tdcenter">${user.cardGold}</td>
            <td class="tdcenter">${user.beforeGold}</td>
            <td class="tdcenter">${user.cardTotal}</td>
            <td class="tdcenter">${user.orderAmount}</td>
            <td class="tdcenter">${user.payAmount}</td>
            <td class="tdcenter">${user.ipAddress}</td>
            <td class="tdcenter">${user.operAccounts}</td>
          </tr>
		  </c:forEach>
          <tr onmouseover="this.className='trover'" onmouseout="this.className='trout'">
            <td colspan="15" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
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
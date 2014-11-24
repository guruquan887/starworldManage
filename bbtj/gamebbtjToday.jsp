<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page isELIgnored="false" %> 
<%
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=10;
	String peroidnum = "";
	int num = 1000;
	String createTime = "";
	String checkTime = "";

	try{
		if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("pageIndex")!=null){
			pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
		}
		if(request.getParameter("peroidnum")!=null){
			peroidnum=request.getParameter("peroidnum");
		}

		if(request.getAttribute("checkTime")!=null){
		   checkTime =request.getAttribute("checkTime").toString();
		   System.out.println("checkTime:"+checkTime);
        }
		if(request.getAttribute("num")!=null){
			num=Integer.parseInt(request.getAttribute("num").toString());
		}
		if(request.getParameter("startTime")!=null){
			createTime=(String)request.getParameter("startTime");
			System.out.println("startTime:"+createTime);
		}
		//else {createTime=(String)request.getAttribute("startTime"); }
		recordIndex=(pageIndex-1)*pageSize+1;
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
		System.out.println("selectOne:"+selectOne);
	}
	String selectOrderBy="totalGoldXZ";
	if(request.getParameter("selectOrderBy")!=null){
		selectOrderBy= request.getParameter("selectOrderBy");
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>即时注单管理</title>
<link href="../css/user.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
}
-->
</style>
<link href="../css/AdminCss.css" rel="stylesheet" type="text/css" />
</head>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/js/bbtj.js"></script>
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg); 

function submted2(){
	var d1 = document.getElementById("startTime").value;
    if(!checkTime()){
	if (d1 == ""){
		alert("请选择查看日期");
		return false;
	}}
	return true;
}

var hash ={checkTime:'<%=checkTime %>'};

var tone=encodeURI("<%=termOne %>");
function jumppage(value)
   {
       window.location.href="bbtjOne.do?action=search&curPage=" +value+"&pageSize=<%=pageSize %>&termOne=<%=termOne%>&selectOne=<%=selectOne %>&selectOrderBy=<%=selectOrderBy%>&startTime=<%=createTime %>&checkTime=<%=checkTime %>";
   }
function changepage(pageNo){
	document.location.href="bbtjOne.do?action=search&curPage="+pageNo+"&termOne="+tone
	+"&selectOne=<%=selectOne %>&pageSize=<%=pageSize %>&selectOrderBy=<%=selectOrderBy%>&startTime=<%=createTime %>&checkTime=<%=checkTime %>";

}
</script>

<body onload="loadDay(hash)">
  <form id="form1" name="form1" method="post" onsubmit="return submted2()" action="bbtjOne.do?action=search">
 <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
     <tr class="title">
      <td height="25">
		字段
        <input name="termOne" type="text" class="input" id="termOne" value="<%=termOne%>" size="15" onfocus="this.select()" />
		&nbsp;&nbsp;类型
         <select name="selectOne" size="1" class="Select" id="selectOne">
		<option value="accounts">按用户名</option>
		<option value="userId">按用户ID</option>
        </select>
		&nbsp;&nbsp;排序
		<select name="selectOrderBy" size="1" class="Select" id="selectOrderBy">
		<option value="totalGoldXZ">按投总额</option>
		<option value="winlostMoney">按输赢数</option>
		<option value="ck">总存款数</option>
		<option value="qk">总取款数</option>
		<option value="userID">按用户ID</option>
        </select>
        &nbsp;&nbsp;日期
        
        <input size="10" id="startTime" type="text" onClick="WdatePicker()"  name="startTime" value="${startTime}<%=createTime %>" readonly/>
		<input type="hidden" value="" name="endTime" id="endTime" />
 	    &nbsp;
		<input type="button" name="reSet" onclick="reSetDate()" value="清空日期" />
	    &nbsp;
	    <input id="checkTime"  type="radio" value="bday" name="checkTime" />
        昨日
        <input id="checkTime"  type="radio" value="cday" name="checkTime" />
        当日
       &nbsp;&nbsp;&nbsp;
        <input type="submit" name="Submit" value="查询" />
       </td>
     </tr>
</table>
</form>


<form id="form2" name="form2" method="post" action="userDel.do">
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
        <tr>
        <td height="25" colspan="12" class="menutop">单日报表</td>
        </tr>
		<tr>
        <td colspan="3" class="menutop"><div align="right">汇总:</div></td>
		<td class="tdcenter">&nbsp;${sumNo.totalGoldXZ}</td>
		<td class="tdcenter">&nbsp;${sumNo.winlostMoney}</td>
		<td class="tdcenter">&nbsp;${sumNo.rebate}</td>
		<td class="tdcenter" title="只要有存款记录,未下注,也会统计汇总">&nbsp;${sumNo.ck}</td>
		<td class="tdcenter" title="只要有存款记录,未下注,也会统计汇总">&nbsp;${sumNo.qk}</td>
		<td class="tdcenter" colspan="2">&nbsp;银子额度</td>
        <td class="tdcenter">&nbsp;</td>
<%--	<td class="tdcenter">&nbsp;${sumNo.score}</td>
		<td class="tdcenter">&nbsp;</td>--%>
    </tr>
    <tr>
      <td class="tdcenter" align="center">序号</td>
      <td class="tdcenter"><div align="center">用户ID</div></td>
      <td class="tdcenter"><div align="center">用户名</div></td>
      <td class="tdcenter"><div align="center">投注总额</div></td>
	  <td class="tdcenter"><div align="center">输赢总额</div></td>
	  <td class="tdcenter"><div align="center">返水金额</div></td>
	  <td class="tdcenter" title="当日有存款记录,而未下注,不会显示在报表中。 有下注才会显示"><div align="center">存款</div></td>
	  <td class="tdcenter" title="当日有取款记录,而未下注,不会显示在报表中。 有下注才会显示"><div align="center">取款</div></td>
	  <td class="tdcenter"><div align="center">${userlist[0].yesterdayGoldName}</div></td>
	  <td class="tdcenter"><div align="center">现有</div></td>
	  <td class="tdcenter"><div align="center">操作</div></td>
    </tr>
    <c:forEach var="user" items="${userlist}">
      <tr class="tdcenter">
        <td height="25" align="center"><%=recordIndex++ %></td>
        <td height="25"><div align="center">${user.userID}&nbsp;</div></td>
        <td><div align="center">${user.accounts}&nbsp;</div></td>
		<td><div align="center">${user.totalGoldXZ}&nbsp;</div></td>
		<td><div align="center">${user.winlostMoney}&nbsp;</div></td>
		<td><div align="center">${user.rebate}&nbsp;</div></td>
		<td><div align="center">${user.ck}&nbsp;</div></td>
        <td><div align="center">${user.qk}&nbsp;</div></td>
		<td><div align="center">${user.yesterdayGold}&nbsp;</div></td>
		<td><div align="center">${user.score}&nbsp;</div></td>
		<td><a href="<%=request.getContextPath()%>/gameuser/gamescoreInfoDetail.do?action=day&userID=${user.userID}&startTime=<%=createTime %>&checkTime=<%=checkTime%>"><div align="center">详细</div></a></td>
<%--		<td><div align="center">${user.ck}&nbsp;</div></td>
		<td><div align="center">${user.qk}&nbsp;</div></td>
		<td><div align="center">${user.createTime}&nbsp;</div></td>--%>
      </tr>
    </c:forEach>
	<tr><td colspan="12" align="center"><font color="red"><c:out value="${returnInfo}"></c:out></font></td></tr>
  </table>
  <table width="100%" border="0" cellpadding="2" cellspacing="1">
    
    <tr class="tdcenter" align="left">
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
<script>

var radioo = document.getElementsByName("checkTime");
var index1="<%=checkTime %>";
 for(var i=0;i<radioo.length;i++){
 //  alert(radioo[i].value);
	if(radioo[i].value==index1){
//	 alert(radioo[i].checked);
		radioo[i].checked=true;break;
	}
}


var ttype2=document.all.selectOne;
var index22="<%=selectOne %>";
for(var i=0; i<ttype2.length; i++){
	if(ttype2.options[i].value==index22){
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

var ttype4=document.all.startTime;
var index3="<%=createTime%>";
	if(ttype3.value==index3){
	ttype3.selected=true;
}
var ttype5=document.all.selectOrderBy;
var index5="<%=selectOrderBy %>";
for(var i=0; i<ttype5.length; i++){
	if(ttype5.options[i].value==index5){
		ttype5.options[i].selected=true;break;
	}
}		
</script> 
</html>

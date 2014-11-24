<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=30;
	int roomId = 1;
	int retroType = 1;
	String peroidnum = "";
	try{
		System.out.println(request.getAttribute("pageSize")+","+request.getAttribute("pageIndex"));
		
		if(request.getAttribute("peroidnum")!=null){
			peroidnum=(String)request.getAttribute("peroidnum");
		}
		if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("pageIndex")!=null){
			pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
		}
		if(request.getParameter("roomId")!=null){
			roomId=Integer.parseInt(request.getParameter("roomId"));
		}
		if(request.getParameter("retroType")!=null){
			retroType=Integer.parseInt(request.getParameter("retroType"));
		}
		}catch(Exception e){
			e.printStackTrace();
	}
	recordIndex=(pageIndex-1)*pageSize+1;
	String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}
	String msg1="";
	if(request.getAttribute("msg1")!=null){
		msg1=request.getAttribute("msg1").toString();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>8kenok开奖补录</title>
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
<script type="text/javascript" src="../js/check.js" charset="UTF-8" ></script>
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
var msg1="<%=msg1 %>";
if(msg1!='')alert(msg1);

function jumppage(value)
   {
         window.location.href="perodinumResult.do?action=result3DList&curPage=" +value+"&pageSize=<%=pageSize %>&roomId=<%=roomId %>&retroType=<%=retroType %>";
   }
   
function changepage(pageNo){
	     document.location.href="perodinumResult.do?action=result3DList&curPage="+pageNo+"&roomId=<%=roomId %>&retroType=<%=retroType %>";
}

function queryrooms(){
		 var roomId=document.all.roomId.options[document.all.roomId.selectedIndex].value;
		 var retroType=document.all.retroType.options[document.all.retroType.selectedIndex].value;
		 document.location.href="perodinumResult.do?action=result3DList&roomId="+roomId+"&retroType="+retroType;
}

function checkH(){  
var peroidnum = document.getElementById("peroidnum").value;
if(peroidnum==""){
	  alert("请选择期数!");
	  return false;
	}
var arr = new Array(3);
for(var i =1 ;i<4;i++){
     var obj=document.getElementById("_num"+i);
	 var bjvalue = obj.value;
	 if(bjvalue==""){
	     alert("输入的号码不能为空!");
	     return false;
	 }
	 if(bjvalue<0||bjvalue>10){
	   alert("请输入0-10之间的数字!");
	   return false;
	 }
     arr[i] = bjvalue;
	 }

	return true;
 }
 
 
function isNumber(oNum){

 if(!oNum) return false; //line:160
 if(oNum.value=="") return false;
 var vv = oNum.value.replace(/[^\d]/g,'');
 var strP=/^\d+$/;
  if (!isNaN(oNum.value)) {
        return true;
    } 
  else{
        alert("请输入0-10区间数字");
        oNum.value=vv;
 		oNum.focus();
 		return false; 
    }
}


</script>
</head>

<body>
<form id="form1" name="form1" method="post" onsubmit="return checkH()" action="perodinumResult.do?action=add3DResult&roomId=<%=roomId %>">
  <br />
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
  <tr>
      <td class="menutop" align="center" width="24%" height="25" > <a href="perodinumResult.do?action=resultList"><strong>快乐吧开奖补录</strong></a></td>  
      <td width="76%" align="left" class="menutop1"><a href="perodinumResult.do?action=result3DList"><strong>3D福彩和上海时时乐补录</strong></a>	  </td>
    </tr>
  </table>
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
  <tr>
      <td width="24%" height="25" class="menutop">
		    开奖号码录入--期号为：<font color="#FF0000">${peroidnum}</font>
			<input name="peroidnum" id="peroidnum" type="hidden" value="${peroidnum}" />
			 <input name="retroType1" type="hidden" value="${retroType}" /></td>
			<td width="76%" align="left" class="menutop1">
			01
		    <input size="2" style="width:25px" maxlength="1" type="text" id="_num1" onkeyup="isNumber(this)" name="num1" />
		    百
			02
			<input size="2" style="width:25px" maxlength="1" type="text" id="_num2" onkeyup="isNumber(this)" name="num2"/>    十
			03
			<input size="2" style="width:25px" maxlength="1" type="text" id="_num3" onkeyup="isNumber(this)" name="num3" />   个
<input type="submit"  name="submit" value="录入">
  </table>
</form>
	<form name="form2" id="form2" action=""/>
	<table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
	  <tr class="title">
      <td colspan="40">游戏列表
        
        <select name="roomId" size="1" class="Select" id="roomId" onChange="queryrooms()">
           <c:forEach var="r" items="${roomType}">
	         <option value="${r.roomId}">${r.displayName}</option>	
	    </c:forEach>
        </select>
		 <select name="retroType" size="1" class="Select" id="retroType" onChange="queryrooms()">
		  <option value="1">后台开奖录入</option>	
          <option value="2">游戏异常期数补录</option>
		  <option value="0">服务器开奖结果</option>
        </select></td>
    </tr>
    <tr class="tdcenter">
      <td rowspan="2" align="center"><strong>序号</strong></td>
      <td rowspan="2"><div align="center"><strong>期号</strong></div></td>
      <td colspan="3"><strong>开奖号码</strong></td>
      <td colspan="2"><strong>和值</strong></td>
      <td colspan="2"><strong>百位</strong></td>
      <td colspan="2"><strong>十位</strong></td>
      <td colspan="2"><strong>个位</strong></td>
	  <td rowspan="2"><strong>特殊</strong></td>
      <td rowspan="2"><strong>操作</strong></td>
    </tr>
    <tr class="tdcenter">
      <td width="25"><strong>01</strong></td>
      <td width="25"><strong>02</strong></td>
	  <td width="25"><strong>03</strong></td>
      <td>单双</td>
      <td>大小</td>
      <td>单双</td>
      <td>大小</td>
      <td>单双</td>
      <td>大小</td>
	  <td>单双</td>
      <td>大小</td>
    </tr>

    <c:forEach var="user" items="${preodinumList}">
    <tr class="tdcenter">
      <td height="25" align="center"><%=recordIndex++ %></td>
      <td height="25" align="center">${user.peroidnum}
	  <input id="peroidnum" name="peroidnum" type="hidden" value="${user.peroidnum}" />
	 </td>

    <td height="25" background="${user.color[0]}"><strong>${user.awardnum1[0]}</strong></td>
    <td height="25" background="${user.color[1]}" ><strong>${user.awardnum1[1]}</strong></td>
	<td height="25" background="${user.color[2]}" ><strong>${user.awardnum1[2]}</strong></td>

      <td>${user.andValueSPName}</td>
      <td>${user.andValueLSName}</td>
      <td>${user.hundredSPName}</td>
	  <td>${user.hundredLSName}</td>
	  <td>${user.tenSPName}</td>
	  <td>${user.tenLSName}</td>
	  <td>${user.oneSPName}</td>
	  <td>${user.oneLSName}</td>
	  <td>${user.specialName}</td>

      <td><div align="center">
	 <c:if test="${user.retroType!=0}"  var="true">  <c:if test="${user.peroidnum!=peroidnum}"  var="true"><a href="perodinumResult.do?action=result3DList&roomId=${user.roomId}&retroType=${user.retroType}&peroidnum=${user.peroidnum}" >选择</a></c:if></c:if>
	  <c:if test="${user.peroidnum==peroidnum}"  var="false"><img src="../images/register_44.jpg" /></c:if>
	  <c:if test="${user.retroType==2}"  var="true"><a href="perodinumResult.do?action=jiesuan3DShiShiLeResult&roomId=<%=roomId%>&peroidnum=${user.peroidnum}&retroType=${user.retroType}">结算</a></c:if></div></td>
    </tr>
    </c:forEach>
	<tr class="tdcenter"><td colspan="37" align="center"><font color="red"><c:out value="${returnInfo}"></c:out></font></td></tr>
</table>
  <table width="100%" border="0" cellpadding="2" cellspacing="1">
    <tr>
       <td colspan="4">总记录:${page.totalRecord}条/${page.totalPage}页
         <c:if  test='${page.totalPage>0}'  var='true'> 
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
<script type="text/javascript">
var tstate=document.all.roomId;
var index1="<%=roomId %>";
for(var i=0; i<tstate.options.length; i++){
			if(tstate.options[i].value==index1){
				tstate.options[i].selected=true;break;
			}
		}
var tretroType=document.all.retroType;
var index2="<%=retroType %>";
for(var i=0; i<tretroType.options.length; i++){
			if(tretroType.options[i].value==index2){
				tretroType.options[i].selected=true;break;
			}
		}
</script> 
</html>

<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %> 
<%
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=10;
	int roomId = 1;
	String pperoidnum="";
	try{
	String userName = "";
	String password = "";
        if(request.getSession().getAttribute("username")!=null){
            userName = (String)request.getSession().getAttribute("username");
  		}
		if(request.getSession().getAttribute("password")!=null){
            password = (String)request.getSession().getAttribute("password");
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
	
	if(request.getParameter("pperoidnum")!=null){
		pperoidnum=request.getParameter("pperoidnum");
		//peroidnum = new String(peroidnum.getBytes("ISO_8859_1"),"UTF-8");
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
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>盘口设定管理</title>
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
<script language="javascript" src="../js/swfobject.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
var tone=encodeURI("<%=pperoidnum %>");
var myDate = null;
myDate =  new Date();
//var year = myDate.getYear()+'-'+(myDate.getMonth()+1)+'-'+(myDate.getDate())+;

function jumppage(value)
   {
      window.location.href="handicapList.do?action=handicapList&curPage="+value+"&roomId=<%=roomId%>&pperoidnum="+document.getElementById("pperoidnum").value;
   }
   
function changepage(pageNo){
	     document.location.href="handicapList.do?action=handicapList&curPage="+pageNo+"&roomId=<%=roomId %>&pperoidnum="+document.getElementById("pperoidnum").value;
}

function queryrooms(){
		 var roomId=document.all.roomId.options[document.all.roomId.selectedIndex].value;
		 document.location.href="handicapList.do?action=handicapList&roomId="+roomId+"&pperoidnum="+document.getElementById("pperoidnum").value;
}
function search(){
	document.location.href="handicapList.do?action=handicapList&pperoidnum="+document.getElementById("pperoidnum").value+"&roomId=<%=roomId%>"
}
function checknum(){
var arr = new Array(6);
var arropen = new Array(6);
for(var i =1 ;i<7;i++){
    var obj = document.getElementById("_time"+i);
	var bjvalue = obj.value;
	if(bjvalue==""){
	   alert("请输入时间!");
	   return false;
		}
		arr[i]=bjvalue;
	}
for(var j =1 ;j<7;j++){
    var openobj = document.getElementById("_opentime"+j);
	var openobjvalue = openobj.value;
	if(openobjvalue==""){
	   alert("请输入时间!");
	   return false;
		}
		arropen[j]=openobjvalue;
	}
	if(arr[2]>12||arr[2]<1){
	    alert("月份不合法!");
		return false;
		}	
	if(arr[3]>31||arr[3]<1){
		alert("日期不合法!");
		return false;
		}
	if(arr[4]>24||arr[4]<0){
		alert("小时不合法!");
		return false;
		}
	if(arr[5]>59||arr[5]<0){
		alert("分钟不合法!");
		return false;
		}
	if(arr[6]>59||arr[6]<0){
		alert("秒数不合法!");
		return false;
		}
	if(arropen[2]>12||arropen[2]<1){
	    alert("月份不合法!");
		return false;
		}	
	if(arropen[3]>31||arropen[3]<1){
		alert("日期不合法!");
		return false;
		}
	if(arropen[4]>24||arropen[4]<0){
		alert("小时不合法!");
		return false;
		}
	if(arropen[5]>59||arropen[5]<0){
		alert("分钟不合法!");
		return false;
		}
	if(arropen[6]>59||arropen[6]<0){
		alert("秒数不合法!");
		return false;
		}
	for(var k=1;k<7;k++){
		if(arr[k]>arropen[k]){
	    alert("开奖的时间要大于开盘时间");
		return false;
		}
		else {
		return true;
		}
		
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
        alert("请输入数字");
        oNum.value=vv;
 		oNum.focus();
 		return false; 
    }
}
</script>

<body>
  
  <form id="form1" name="form1" method="post" onSubmit="return checknum()" action="handicapList.do?action=addhandicap&roomId=<%=roomId %>">
    <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
    <tr>
        <td width="9%" height="25" class="menutop">期数        </td>
		<td width="38%" height="25" class="menutop">开盘时间        </td>
		<td width="40%" class="menutop">开奖时间</td>
		<td width="13%"class="menutop">功能        </td>
    </tr>
    <tr class="tdcenter">
      <td height="25"><font color="#FF0000"><input size="10" name="peroidnum" type="text" value="${peroidnum}"></font></td>
      <td height="25"><input size="1" id="_time1" style="width:35px"  maxlength="4" onKeyUp="isNumber(this)"  name="year" type="text">
      年&nbsp;<strong>-</strong>&nbsp;
	  <input size="1" id="_time2" style="width:25px" onKeyUp="isNumber(this)" maxlength="2" name="month" type="text">
	  月&nbsp;<strong>-</strong>&nbsp;
	  <input size="1" id="_time3" style="width:25px" onKeyUp="isNumber(this)" maxlength="2" name="day" type="text">日&nbsp;&nbsp;&nbsp;
	  <input size="1" id="_time4" style="width:25px" onKeyUp="isNumber(this)" maxlength="2" name="hour" type="text">时&nbsp;<strong>:</strong>&nbsp;
	  <input size="1" id="_time5" style="width:25px" onKeyUp="isNumber(this)" maxlength="2" name="minute" type="text">分&nbsp;<strong>:</strong>&nbsp;
	  <input size="1" id="_time6" style="width:25px" onKeyUp="isNumber(this)" maxlength="2" name="second" type="text">秒</td>
     <td><input style="width:35px" id="_opentime1" maxlength="4" onKeyUp="isNumber(this)" size="1" name="year1" type="text">
     年&nbsp;<strong>-</strong>&nbsp;
	  <input size="1" id="_opentime2" style="width:25px" onKeyUp="isNumber(this)" maxlength="2" name="month1" type="text">
	  月&nbsp;<strong>-</strong>&nbsp;
	  <input size="1" id="_opentime3" style="width:25px" onKeyUp="isNumber(this)" maxlength="2" name="day1" type="text">
	  日&nbsp;&nbsp;&nbsp;
	  <input size="1" id="_opentime4" style="width:25px" onKeyUp="isNumber(this)" maxlength="2" name="hour1" type="text">
	  &nbsp;时<strong>:</strong>&nbsp;
	  <input size="1" id="_opentime5" style="width:25px" onKeyUp="isNumber(this)" maxlength="2" name="minute1" type="text">
	  &nbsp;分<strong>:</strong>&nbsp;
	  <input size="1" id="_opentime6" style="width:25px" onKeyUp="isNumber(this)" maxlength="2" name="second1" type="text">
	  秒</td>
      <td>
	 <label>
      <input type="submit" name="Submit" value="添加">
      </label></td>
    </tr>
	<tr> <td colspan="4" align="center">(录入时开盘时间与开奖时间至少相隔3分钟)</td></tr>
  </table>
  </form>
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
    <tr>
          <td height="25" colspan="9" class="menutop"><div style="width:90%; float:left; text-align:center; line-height:25px;">盘口设定列表</div>
      </td>
    </tr>
  </table>
  <form id="form2" name="form2" method="post" action="handicapList.do?action=handicapList">
 <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
     <tr class="tdright">
      <td height="25">盘口类型 
        <select name="roomId" id="roomId" onChange="queryrooms()">
      <c:forEach var="r" items="${rooms}">
	         <option value="${r.roomId}">${r.roomName}</option>	
	 </c:forEach>
        </select>
		期数查询<input name="pperoidnum" size="12" type="text" class="input" id="pperoidnum" value="<%=pperoidnum%>" />
		<input type="button" name="button" value="查询" onClick="search()"/>
        </td>
     </tr>
</table>
</form>
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
    <tr class="tdcenter">
      <td align="center"><strong>序号</strong></td>
      <td align="center"><strong>盘口期数</strong></td>
      <td align="center"><strong>开奖时间</strong></td>
      <td align="center"><strong>功能</strong></td>
    </tr>
   
    <c:forEach var="handicap" items="${preodinumList}">
    <tr class="tdcenter">
      <td height="25" align="center"><%=recordIndex++ %></td>
      <td align="center">${handicap.peroidnum}</td>
      <td height="25" align="center">${handicap.openHandTime}</td>
      <td height="25" align="center"><c:if test="${handicap.retroType==0}"  var="true"><font color="#FF0000">已开奖</font></c:if><c:if test="${handicap.lotteryType=='1'}"  var="true"><font color="#FF0000">等待开奖<a href="<%=request.getContextPath() %>/lottery/perodinumResult.do?action=resultList&peroidnum=${handicap.peroidnum}&roomId=${handicap.roomId}&retroType=${handicap.retroType}">(号码录入)</a></font></c:if><c:if test="${handicap.lotteryType=='2'}"  var="true"><font color="#FF0000">等待结算</font></c:if>&nbsp;&nbsp;
      	<a href="handicapList.do?action=delete&peroidnum=${handicap.peroidnum}&roomId=${handicap.roomId}" onClick="{if(confirm('删除后该期数后将被永久删除！\n\n确定删除吗?')){return true;}return false;}">删除</a>		    </td>
    </tr>
    </c:forEach>
</table>
  <table width="100%" border="0" cellpadding="2" cellspacing="1">
	  <tr class="tdcenter">
       <td colspan="4">总记录:${page.totalRecord}条/${page.totalPage}页 
	     <c:if  test='${page.totalPage>0}'  var='true'> 
        <select name="select" onChange="jumppage(this.value);">
          <c:forEach var="i" begin="1" end="${page.totalPage }" step="1"> <option value="${i}" 
              <c:if test="${i==page.curPage}" var="true">selected="selected"</c:if>
            >第${i}页
            </option>
          </c:forEach>
        </select> 
　  <a onClick="changepage(1)" style="cursor:hand">首页</a> 
	<a onClick="changepage(${page.curPage-1})" style="cursor:hand">上页</a> 
	&nbsp;<a onClick="changepage(${page.curPage+1})" style="cursor:hand">下页</a> 
        <a onClick="changepage(${page.totalPage})" style="cursor:hand">末页</a></c:if></td>
</td>
    </tr>  
  </table>
  
<%--<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0" id="swf"  width="250"   height="200"   VIEWASTEXT>   
  <param name="movie" value="../images/HappyBusManager.swf"/>   
  <param name="quality" value="high" />
  <param name="FlashVars" value="userName=guest&password=123456" />   
  <embed src="../images/AD/ad_3.swf" quality="high" pluginspage="http://www.macromedia.com/go/getflashplayer" type="application/x-shockwave-flash" width="250" height="200"></embed></object>--%>
</body>
<div id="swfcontent" ></div>
<script type="text/javascript">

var tstate=document.all.roomId;
var index1="<%=roomId %>";
for(var i=0; i<tstate.options.length; i++){
			if(tstate.options[i].value==index1){
				tstate.options[i].selected=true;break;
			}
		}
var ttype3=document.all.pperoidnum;
var index2="<%=pperoidnum%>";
for(var i=0; i<ttype3.length; i++){
	if(ttype3.value==index2){
		ttype3.selected=true;break;
	}
}
</script> 
</html>

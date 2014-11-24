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
	String pperoidnum="";
	int pero = 0;
	try{
		System.out.println(request.getAttribute("pageSize")+","+request.getAttribute("pageIndex"));
		
//		if(request.getAttribute("peroidnum")!=null){
//			peroidnum=(String)request.getAttribute("peroidnum");
//			pero = Integer.parseInt(peroidnum);
//		}
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
function jumppage(value)
   {
         window.location.href="perodinumResult.do?action=resultList&curPage=" +value+"&pageSize=<%=pageSize %>&roomId=<%=roomId %>&pperoidnum="+document.getElementById("pperoidnum").value;
   }
   
function search(){
	document.location.href="perodinumResult.do?action=resultList&pperoidnum="+document.getElementById("pperoidnum").value+"&roomId=<%=roomId%>"
}
   
function changepage(pageNo){
	     document.location.href="perodinumResult.do?action=resultList&curPage="+pageNo+"&roomId=<%=roomId %>&pperoidnum="+document.getElementById("pperoidnum").value;
}

function queryrooms(){
		 var roomId=document.all.roomId.options[document.all.roomId.selectedIndex].value;
		 document.location.href="perodinumResult.do?action=resultList&roomId="+roomId+"&pperoidnum="+document.getElementById("pperoidnum").value;
}

function checkUpdateNum(){
var arr = new Array(20);

for(var i =1 ;i<21;i++){
    var obj = document.getElementById("_nnum"+i);
	var bjvalue = obj.value;
	if(bjvalue==""){
	   alert("输入的号码不能为空!");
	   return false;
	}

	if(bjvalue<1||bjvalue>80){
	   alert("请输入0-80之间的数字!");
	   return false;
	}
	arr[i] = bjvalue;
	}
	var nary=arr.sort(); 
	for(var i=0;i<nary.length-1;i++) 
	{
	if (nary[i]==nary[i+1]) {
	    alert("重复内容："+nary[i]);
		return false;
		} 
	}
	return true;
}

function checknum(){
var arr = new Array(20);
var peroidnum = document.getElementById("peroidnum").value;
if(peroidnum==""){
	  alert("请选择期数!");
	  return false;
	}
for(var i =1 ;i<21;i++){
    var obj = document.getElementById("_num"+i);
	var bjvalue = obj.value;
	if(bjvalue==""){
	   alert("输入的号码不能为空!");
	   return false;
	}

	if(bjvalue<1||bjvalue>80){
	   alert("请输入0-80之间的数字!");
	   return false;
	}
	arr[i] = bjvalue;
	}
	var nary=arr.sort(); 
	for(var i=0;i<nary.length-1;i++) 
	{
	if (nary[i]==nary[i+1]) {
	    alert("重复内容："+nary[i]);
		return false;
		} 
	}
	return true;
}

function isNumber(oNum){
 if(!oNum) return false; //line:160
 if(oNum.value=="") return false;
 var vv = oNum.value.replace(/[^\d]/g,'');
 var strP=/^\d+$/;
 //alert(nn);
  if (!isNaN(oNum.value)) {
        return true;
    } 
  else{
        alert("请输入0-80区间数字");
        oNum.value=vv;
 		oNum.focus();
 		return false; 
    }
}

function modfun(h1,h2,h3,h4,h5,h6,h7,h8,h9,h10,h11,h12,h13,h14,h15,h16,h17,h18,h19,h20,qibie){

	for(var i = 0;i<20;i++){
	   document.getElementById("_nnum"+(i+1)).value=arguments[i];
	}
	document.getElementById("m_table").style.display='';
	document.getElementById("a_table").style.display='none';
	document.getElementById("peroidnum1").value=qibie;
}
</script>
</head>

<body>
<form id="form1" name="form1" method="post" onsubmit="return checknum()" action="perodinumResult.do?action=addResult&roomId=<%=roomId %>">
  <table width="100%" id="a_table" border="0" cellpadding="2" cellspacing="1" class="border" style="display:block">
  <tr>
      <td width="19%" height="25" class="menutop">
		    开奖号码录入</td>
			<td width="65%" align="left" class="menutop">
			01
		    <input size="1" style="width:25px" id="_num1" maxlength="2" type="text" name="num1" onkeyup="isNumber(this)"  />
			02
			<input size="1" style="width:25px" id="_num2" maxlength="2" type="text"  name="num2" onkeyup="isNumber(this)"/>
			03
			<input size="1" style="width:25px" id="_num3" maxlength="2" type="text"  name="num3" onkeyup="isNumber(this)"/>
			04
			<input size="1" style="width:25px" id="_num4" maxlength="2" type="text"  name="num4" onkeyup="isNumber(this)"/> 
			05
			<input size="1" style="width:25px" id="_num5" maxlength="2" type="text"  name="num5" onkeyup="isNumber(this)"/>
			06
			<input size="1" style="width:25px" id="_num6" maxlength="2" type="text"  name="num6" onkeyup="isNumber(this)"/>
			07
			<input size="1" style="width:25px" id="_num7" maxlength="2" type="text"  name="num7" onkeyup="isNumber(this)"/>
			08
			<input size="1" style="width:25px" id="_num8" maxlength="2" type="text"  name="num8" onkeyup="isNumber(this)"/>
			09
			<input size="1" style="width:25px" id="_num9" maxlength="2" type="text"  name="num9" onkeyup="isNumber(this)"/>
			10
			<input size="1" style="width:25px" id="_num10" maxlength="2" type="text"  name="num10" onkeyup="isNumber(this)"/>
	  <td width="16%" class="menutop"></td>
	</tr>
			<tr>
			<td class="menutop">期号为：<font color="#FF0000">${peroidnum}</font>
			<input name="peroidnum" type="hidden" value="${peroidnum}" />
			<input name="retroType1" type="hidden" value="${retroType}" /></td>
			<td class="menutop" align="left">
			11
			<input size="1" style="width:25px" id="_num11" maxlength="2" type="text"  name="num11" onkeyup="isNumber(this)"/>
			12
			<input size="1" style="width:25px" id="_num12" maxlength="2" type="text"   name="num12" onkeyup="isNumber(this)"/>
			13
			<input size="1" style="width:25px" id="_num13" maxlength="2" type="text"  name="num13" onkeyup="isNumber(this)"/>
			14
			<input size="1" style="width:25px" id="_num14" maxlength="2" type="text"  name="num14" onkeyup="isNumber(this)"/>
			15
			<input size="1" style="width:25px" id="_num15" maxlength="2" type="text"  name="num15" onkeyup="isNumber(this)"/>
			16
			<input size="1" style="width:25px" id="_num16" maxlength="2" type="text"  name="num16" onkeyup="isNumber(this)"/>
			17
			<input size="1" style="width:25px" id="_num17" maxlength="2" type="text"  name="num17" onkeyup="isNumber(this)"/>
			18
			<input size="1" style="width:25px" id="_num18" maxlength="2" type="text"  name="num18" onkeyup="isNumber(this)"/>
			19
			<input size="1" style="width:25px" id="_num19" maxlength="2" type="text"  name="num19" onkeyup="isNumber(this)"/>
			20
			<input size="1" style="width:25px" id="_num20" maxlength="2" type="text"  name="num20" onkeyup="isNumber(this)"/>
			</td>
			  <td class="menutop1" align="left"><input type="submit"  name="submit" value="录入"></td>
    </tr>
  </table>
</form>
  <form id="form1" name="form1" method="post" onsubmit="return checkUpdateNum()" action="perodinumResult.do?action=updateResult&roomId=<%=roomId %>">
  <table width="100%" id="m_table" border="0" cellpadding="2" cellspacing="1" class="border" style="display:none">
  <tr>
      <td width="19%" height="25" class="menutop">
		    开奖号码修改</td>
			<td width="65%" align="left" class="menutop">
			01
		    <input size="1" style="width:25px" maxlength="2" type="text" name="_nnum1" onkeyup="isNumber(this)"  />
			02
			<input size="1" style="width:25px" maxlength="2" type="text"  name="_nnum2" onkeyup="isNumber(this)"/>
			03
			<input size="1" style="width:25px" maxlength="2" type="text"  name="_nnum3" onkeyup="isNumber(this)"/>
			04
			<input size="1" style="width:25px" maxlength="2" type="text"  name="_nnum4" onkeyup="isNumber(this)"/> 
			05
			<input size="1" style="width:25px"  maxlength="2" type="text"  name="_nnum5" onkeyup="isNumber(this)"/>
			06
			<input size="1" style="width:25px" maxlength="2" type="text"  name="_nnum6" onkeyup="isNumber(this)"/>
			07
			<input size="1" style="width:25px" maxlength="2" type="text"  name="_nnum7" onkeyup="isNumber(this)"/>
			08
			<input size="1" style="width:25px" maxlength="2" type="text"  name="_nnum8" onkeyup="isNumber(this)"/>
			09
			<input size="1" style="width:25px" maxlength="2" type="text"  name="_nnum9" onkeyup="isNumber(this)"/>
			10
			<input size="1" style="width:25px" maxlength="2" type="text"  name="_nnum10" onkeyup="isNumber(this)"/>
	  <td width="16%" class="menutop"></td>
	</tr>
	<tr>
			<td class="menutop" align="center">期号为：<input name="peroidnum1" type="text" id="peroidnum1" style="background:#73b3d6; border: solid 1px #73b3d6; width:50px; color:#000; font-size:12px;" maxlength="2" border="0" checked="checked"/>
			</font>
			<input name="retroType11" type="hidden" value="${retroType}" /></td>
			<td class="menutop" align="left">
			11
			<input size="1" style="width:25px" maxlength="2" type="text"  name="_nnum11" onkeyup="isNumber(this)"/>
			12
			<input size="1" style="width:25px" maxlength="2" type="text"   name="_nnum12" onkeyup="isNumber(this)"/>
			13
			<input size="1" style="width:25px" maxlength="2" type="text"  name="_nnum13" onkeyup="isNumber(this)"/>
			14
			<input size="1" style="width:25px" maxlength="2" type="text"  name="_nnum14" onkeyup="isNumber(this)"/>
			15
			<input size="1" style="width:25px" maxlength="2" type="text"  name="_nnum15" onkeyup="isNumber(this)"/>
			16
			<input size="1" style="width:25px" maxlength="2" type="text"  name="_nnum16" onkeyup="isNumber(this)"/>
			17
			<input size="1" style="width:25px" maxlength="2" type="text"  name="_nnum17" onkeyup="isNumber(this)"/>
			18
			<input size="1" style="width:25px" maxlength="2" type="text"  name="_nnum18" onkeyup="isNumber(this)"/>
			19
			<input size="1" style="width:25px" maxlength="2" type="text"  name="_nnum19" onkeyup="isNumber(this)"/>
			20
			<input size="1" style="width:25px" maxlength="2" type="text"  name="_nnum20" onkeyup="isNumber(this)"/>
			
	  </td>
			  <td class="menutop1" align="left"><input type="submit"  name="submit" value="修改"></td>
    </tr>
  </table>
</form>
	<form name="form2" id="form2" action=""/>
	<table border="0" width="100%" cellpadding="2" cellspacing="1" class="border">
	  <tr class="title">
      <td colspan="33">游戏列表
        
        <select name="roomId" size="1" class="Select" id="roomId" onChange="queryrooms()">
           <c:forEach var="r" items="${roomType}">
	         <option value="${r.roomId}">${r.displayName}</option>	
	    </c:forEach>
        </select>
		 期数查询<input name="pperoidnum" size="12" type="text" class="input" id="pperoidnum" value="<%=pperoidnum%>" />
		<input type="button" name="button" value="查询" onClick="search()"/>
<%--		<select name="retroType" size="1" class="Select" id="retroType" onChange="queryrooms()">
		  <option value="1">后台开奖录入</option>	
          <option value="2">游戏异常期数补录</option>
		  <option value="0">服务器开奖结果</option>	
        </select>--%></td>
    </tr>
    <tr class="tdcenter">
      <td rowspan="2" align="center"><strong>序号</strong></td>
      <td rowspan="2"><div align="center"><strong>期号</strong></div></td>
      <td rowspan="2"><div align="center"><strong>开奖时间</strong></div></td>
      <td colspan="20"><div align="center"><strong>开奖号码</strong></div></td>
     
    <!--  <td width="82" rowspan="2"><strong>总和</strong></td>-->
      <td rowspan="2"><strong>单双</strong></td>
      <td rowspan="2"><strong>大小</strong></td>
      <td rowspan="2"><strong>龙和虎</strong></td>
      <td rowspan="2"><strong>操作</strong></td>
    </tr>
    <tr class="tdcenter">
      <td><strong>01</strong></td>
      <td><strong>02</strong></td>
	  <td><strong>03</strong></td>
	  <td><strong>04</strong></td>
	  <td><strong>05</strong></td>
	  <td><strong>06</strong></td>
	  <td><strong>07</strong></td>
	  <td><strong>08</strong></td>
	  <td><strong>09</strong></td>
	  <td><strong>10</strong></td>
	  <td><strong>11</strong></td>
	  <td><strong>12</strong></td>
	  <td><strong>13</strong></td>
	  <td><strong>14</strong></td>
	  <td><strong>15</strong></td>
	  <td><strong>16</strong></td>
	  <td><strong>17</strong></td>
	  <td><strong>18</strong></td>
	  <td><strong>19</strong></td>
	  <td><strong>20</strong></td>
    </tr>
    <c:forEach var="user" items="${preodinumList}">
    <tr class="tdcenter">
      <td height="25" align="center"><%=recordIndex++ %></td>
      <td height="25" align="center">${user.peroidnum}
	  <input id="peroidnum" name="peroidnum" type="hidden" value="${user.peroidnum}" />
	  <input id="awardnum1" name="awardnum1" type="hidden" value="${user.awardnum1}" /></td>

      <td height="25" align="center">${user.createTime}</td>
      <td class="ball_widht_height"  background="${user.color[0]}"><strong>${user.awardnum1[0]}</strong></td>
    <td class="ball_widht_height" background="${user.color[1]}"><strong>${user.awardnum1[1]}</strong></td>
	<td class="ball_widht_height" background="${user.color[2]}"><strong>${user.awardnum1[2]}</strong></td>
	<td class="ball_widht_height" background="${user.color[3]}"><strong>${user.awardnum1[3]}</strong></td>
	<td class="ball_widht_height" background="${user.color[4]}"><strong>${user.awardnum1[4]}</strong></td>
    <td class="ball_widht_height" background="${user.color[5]}"><strong>${user.awardnum1[5]}</strong></td>
	<td class="ball_widht_height" background="${user.color[6]}"><strong>${user.awardnum1[6]}</strong></td>
	<td class="ball_widht_height" background="${user.color[7]}"><strong>${user.awardnum1[7]}</strong></td>
	<td class="ball_widht_height" background="${user.color[8]}"><strong>${user.awardnum1[8]}</strong></td>
	<td class="ball_widht_height" background="${user.color[9]}"><strong>${user.awardnum1[9]}</strong></td>
	<td class="ball_widht_height" background="${user.color[10]}"><strong>${user.awardnum1[10]}</strong></td>
	<td class="ball_widht_height" background="${user.color[11]}"><strong>${user.awardnum1[11]}</strong></td>
	<td class="ball_widht_height" background="${user.color[12]}"><strong>${user.awardnum1[12]}</strong></td>
	<td class="ball_widht_height" background="${user.color[13]}"><strong>${user.awardnum1[13]}</strong></td>
	<td class="ball_widht_height" background="${user.color[14]}"><strong>${user.awardnum1[14]}</strong></td>
	<td class="ball_widht_height" background="${user.color[15]}"><strong>${user.awardnum1[15]}</strong></td>
	<td class="ball_widht_height" background="${user.color[16]}"><strong>${user.awardnum1[16]}</strong></td>
	<td class="ball_widht_height" background="${user.color[17]}"><strong>${user.awardnum1[17]}</strong></td>  
	<td class="ball_widht_height" background="${user.color[18]}"><strong>${user.awardnum1[18]}</strong></td>
	<td class="ball_widht_height" background="${user.color[19]}"><strong>${user.awardnum1[19]}</strong></td>
      <td>${user.singleName}</td>
      <td>${user.largeName}</td>
      <td>${user.serpentName}</td>
      <td><div align="center">
	   <c:if test="${user.peroidnum!=peroidnum}"  var="true"><a href="perodinumResult.do?action=resultList&roomId=<%=roomId%>&retroType=${user.retroType}&peroidnum=${user.peroidnum}" >选择</a></c:if>
	  <c:if test="${user.peroidnum==peroidnum}"  var="true"><img src="../images/register_44.jpg" /></c:if>
	 <%--<c:if test="${user.retroType==2}"  var="true">--%> <a href="javascript:modfun('${user.awardnum1[0]}','${user.awardnum1[1]}','${user.awardnum1[2]}','${user.awardnum1[3]}','${user.awardnum1[4]}','${user.awardnum1[5]}','${user.awardnum1[6]}','${user.awardnum1[7]}','${user.awardnum1[8]}','${user.awardnum1[9]}','${user.awardnum1[10]}','${user.awardnum1[11]}','${user.awardnum1[12]}','${user.awardnum1[13]}','${user.awardnum1[14]}','${user.awardnum1[15]}','${user.awardnum1[16]}','${user.awardnum1[17]}','${user.awardnum1[18]}','${user.awardnum1[19]}','${user.peroidnum}')">修改</a>
	  <a href="perodinumResult.do?action=jiesuanResult&roomId=<%=roomId%>&peroidnum=${user.peroidnum}&retroType=${user.retroType}">结算</a><%--</c:if>--%>
		</div></td>
    </tr>
    </c:forEach>
	<tr class="tdcenter"><td colspan="30" align="center"><font color="red"><c:out value="${returnInfo}"></c:out></font></td></tr>
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
var ttype3=document.all.pperoidnum;
var index2="<%=pperoidnum%>";
for(var i=0; i<ttype3.length; i++){
	if(ttype3.value==index2){
		ttype3.selected=true;break;
	}
}
</script> 

</script> 
</html>

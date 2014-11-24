<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
String regAccounts = "";
double winner = 0;
double brokerage = 0;
double taxRate = 0;
int spreaderID = 3;
int levelID = 1;
String accounts = "";
if(request.getParameter("accounts")!=null){
      accounts = request.getParameter("accounts");
}
if(request.getParameter("spreaderID")!=null){
	    spreaderID = Integer.parseInt(request.getParameter("spreaderID"));
	}
if(request.getParameter("levelID")!=null){
	    levelID = Integer.parseInt(request.getParameter("levelID"));
	}
if(request.getParameter("regAccounts")!=null){
   regAccounts = request.getParameter("regAccounts");
}
if(request.getParameter("winner")!=null){
   winner = Double.parseDouble(request.getParameter("winner"));
}
if(request.getParameter("brokerage")!=null){
   brokerage = Double.parseDouble(request.getParameter("brokerage"));
}
double sxwinner = 0;
if(request.getAttribute("sxwinner")!=null){
	   sxwinner = (Double)(request.getAttribute("sxwinner"));
   }
double sxbrokerage = 0;
if(request.getAttribute("sxbrokerage")!=null){
	   sxbrokerage = (Double)(request.getAttribute("sxbrokerage"));
   }
double sxtaxRate = 0;
if(request.getAttribute("sxtaxRate")!=null){
	   sxtaxRate = (Double)(request.getAttribute("sxtaxRate"));
   }
if(request.getParameter("taxRate")!=null){
   taxRate = Double.parseDouble(request.getParameter("taxRate"));
}
String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<script type='text/javascript' src='<%=request.getContextPath()%>/js/jquery.js'></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-latest.pack.js"></script>
<script language="javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
function isNumber(oNum) {
 if(!oNum) return false; //line:160
 if(oNum.value=="") return false;
 var vv = oNum.value.replace(/[^\d]/g,'');
 var strP=/^\d+$/;
  if (!isNaN(oNum.value)) {
        return true;
    } else {
        alert("请输入整数");
        oNum.value=vv;
 		oNum.focus();
 		return false; 
    }
 }
 
function check(){
   if(document.getElementById("accounts").value==""){
       alert("请输入账号!");
	   return false;
   }
   if(document.getElementById("regAccounts").value==""){
       alert("请输入昵称!");
	   return false;
   }
   if(document.getElementById("password1").value==""){
       alert("请输入密码!");
	   return false;
   }
   if(document.getElementById("password2").value==""){
       alert("请输入确认密码!");
	   return false;
   }
   if(document.getElementById("password1").value!=document.getElementById("password2").value){
       alert("两次密码输入不正确!");
	   return false;
   }
   if(document.getElementById("winner").value==""){
       alert("请输入股份比例!");
	   return false;
   }
   if(document.getElementById("brokerage").value==""){
       alert("请输入佣金!");
	   return false;
   }
   if(document.getElementById("taxRate").value==""){
       alert("请输入扣税提成!");
	   return false;
   }

var winner = document.getElementById("winner").value;
var brokerage = document.getElementById("brokerage").value;
var taxRate = document.getElementById("taxRate").value;
if(parseInt(winner)>'<%=sxwinner%>'){
   alert("股份占成超出上限!");
   return false;
}
if(parseInt(brokerage)>'<%=sxbrokerage%>'){
   alert("洗码佣金超出上限!");
   return false;
}
if(parseFloat(taxRate)>'<%=sxtaxRate%>'){
   alert("税收提成超出上限!");
   return false;
}

}

function checkusername(){
var accounts=encodeURI(document.getElementById("accounts").value);
if(accounts==0||accounts==""){
  $("#accountdiv").empty(); 
  $("#accountdiv").append("用户名不能为空！"); 
   }
else{
$("#accountdiv").empty(); 
jQuery.get("<%=request.getContextPath()%>/member/gameuserList.do?action=checkMemName&accounts="+accounts,null, function(data){
if(data=="0"){
$("#accountdiv").append("恭喜，用户名可用！"); 
}
if(data=="-1"){
$("#accountdiv").append("对不起，用户名不可用！"); 
}

});
}
}

function checknickName(){
var regAccounts=encodeURI(document.getElementById("regAccounts").value);
if(regAccounts==0||regAccounts==""){
  $("#nickNamediv").empty(); 
  $("#nickNamediv").append("昵称不能为空！"); 
   }
else{
$("#nickNamediv").empty(); 
jQuery.get("<%=request.getContextPath()%>/member/gameAgentList.do?action=checkNickName&regAccounts="+regAccounts,null, function(data){
if(data=="0"){
$("#nickNamediv").append("恭喜，昵称可用！"); 
}
if(data=="-1"){
$("#nickNamediv").append("对不起，昵称不可用！"); 
}

});
}
}

</script>
<body>
	<div class="title"><span>
	  <label>
	  <input name="Submit3" type="button" class="input" value="查看代理" onclick="window.location.href='<%=request.getContextPath()%>/member/gameAgentList.do?action=gameAgentList'" />
	  </label>
	</span><c:if test="${levelID==1}" var="true">公司</c:if><c:if test="${levelID==2}" var="true">股东</c:if><c:if test="${levelID==3}" var="true">总代理</c:if><c:if test="${levelID==4}" var="true">代理</c:if>—新增</div>
    <form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/member/gameAgentList.do?action=addUser" onsubmit="return check()">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
        <tr>
          <td width="120" class="menutop">项目</td>
          <td class="menutop">内容</td>
        </tr>
        <tr>
          <td class="tdright_new">所属：</td>
          <td class="tdlefts">${dto.accounts}</td>
        </tr>
        <tr>
          <td class="tdright_new">用户名：</td>
          <td class="tdlefts"><input name="type" id="type" type="hidden" value="1" /><input name="sxAccounts" type="hidden" value="${dto.accounts}" /><input name="accounts" id="accounts" onBlur="checkusername();" type="text" class="input_width2" /><font color="#FF0000"><span id="accountdiv"></span></font></td>
        </tr>
        <tr>
          <td class="tdright_new">昵称：</td>
          <td class="tdlefts"><label><input name="spreaderID" id="spreaderID" type="hidden" value="${dto.spreaderID}" />
		  <input name="levelID" type="hidden" value="<%=levelID%>" />
            <input name="regAccounts" id="regAccounts" type="text" class="input_width2" onBlur="checknickName();"/><font color="#FF0000"><span id="nickNamediv"></span></font>
          </label></td>
        </tr>
        <tr>
          <td class="tdright_new">密码：</td>
          <td class="tdlefts"><label>
            <input name="password1" id="password1" type="password" class="input_width2" />
            <a href="#"></a></label></td>
        </tr>
        <tr>
          <td class="tdright_new">确认密码：</td>
          <td class="tdlefts"><input name="password2" id="password2" type="password" class="input_width2" /></td>
        </tr>
        <tr>
          <td class="tdright_new">钱庄密码：</td>
          <td class="tdlefts"><input name="bankPass" id="bankPass" type="password" class="input_width2" /></td>
        </tr>
        <tr>
          <td class="tdright_new">钱庄密码确认：</td>
          <td class="tdlefts"><input name="bankPass2" id="bankPass2" type="password" class="input_width2" /></td>
        </tr>
        <tr>
          <td class="tdright_new">股份比例：</td>
          <td class="tdlefts"><input name="winner" id="winner" type="text" class="input_width2" onkeyup="isNumber(this)" value="<%=winner%>"/>%上限${sxwinner}%</td>
        </tr>
        <tr>
          <td class="tdright_new">真人佣金：</td>
          <td class="tdlefts"><input name="brokerage" id="brokerage" type="text" class="input_width2" onkeyup="isNumber(this)" value="<%=brokerage%>"/>%上限${sxbrokerage}%</td>
        </tr>
        <tr>
          <td class="tdright_new">扣税提成：</td>
          <td class="tdlefts"><label>
            <input name="taxRate" id="taxRate" type="text" class="input_width2" onkeyup="isNumber(this)" value="<%=taxRate%>"/>%上限${sxtaxRate}%
          </label></td>
        </tr>
        <tr>
          <td class="tdright_new">状态：</td>
          <td class="tdlefts"><label>
            <input name="isFreeze" id="isFreeze" type="radio" value="0" checked="checked" />
            启用
            <input type="radio" name="isFreeze" id="isFreeze" value="1" />
停用 </label></td>
        </tr>
<%--        <tr>
		<td class="tdright_new">筹码类型：</td>
          <td colspan="2" class="tdright_new">
		  <table align="left" id="AgentDetail__ctl0_gridBetLtd" class="table_margin"  cellspacing="0" cellpadding="4" rules="cols" border="1">
                                
                                    <tr>
                                      <td class="tdright_new" scope="col">&nbsp;</th>
                                      <td class="tdright_new" scope="col">上限</th>
                                      <td class="tdright_new" scope="col">下限</th>
                                      <td class="tdright_new" scope="col">籌碼</th>
                                      <td class="tdright_new" scope="col">&nbsp;</th>
                                    </tr>
                                    <c:forEach var="xh" items="${xhList}">
									<tr>
                                      <td class="tdright_new"><input  id="XH_${xh.id}_manage"  type="checkbox"  name="XH_${xh.id}_manage" />
                                      </td>
                                      <td class="tdright_new">${xh.sx}</td>
                                      <td class="tdright_new">${xh.xx}</td>
                                      <td class="tdright_new">${xh.xhNumber}</td>
                                    </tr>
									</c:forEach>
                                </table>
		  </td>
        </tr>--%>
        <tr>
          <td class="tdright_new">&nbsp;</td>
          <td class="tdright"><label>
            <input name="Submit" type="submit" class="input" value="确定" />
            <input name="Submit2" type="reset" class="input" value="重置" />
          </label></td>
        </tr>
      </table>
</form>
</body>
</html>
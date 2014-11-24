<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
String regAccounts = "";
if(request.getParameter("regAccounts")!=null){
   regAccounts = request.getParameter("regAccounts");
}
String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}
int type = 0;
if(request.getParameter("type")!=null){
	    type = Integer.parseInt(request.getParameter("type"));
	}
int spreaderID = 3;
int levelID = 0;
if(request.getParameter("spreaderID")!=null){
	    spreaderID = Integer.parseInt(request.getParameter("spreaderID"));
	}
	if(request.getParameter("levelID")!=null){
	    levelID = Integer.parseInt(request.getParameter("levelID"));
	}
String accounts = "";
if(request.getParameter("accounts")!=null){
   accounts = request.getParameter("accounts");
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
 	var insureScore = document.getElementById("insureScore").value.replace(/,/g,'');
    var score = document.getElementById("score").value.replace(/,/g,'');
	var insureGold = document.getElementById("insureGold").value;
	var gold = document.getElementById("gold").value;
	//alert(score);
    if(Number(insureGold)>Number(insureScore)){
	   alert("超出上限");
	   return false;
	}
	if(Number(gold)>Number(score)){
	   alert("超出上限");
	   return false;
	}
	return true;
 
 }
</script>
<body>
	<div class="title"><span>
	  <label>
	  <input name="Submit3" type="button" class="input" value="查看用户" onclick="window.location.href='agent.jsp'" />
	  </label>
	</span>银子—取出</div>
    <form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/member/gameuserList.do?action=MemberQu" onsubmit="return check()">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
        <tr>
          <td width="120" class="menutop">项目</td>
          <td class="menutop">内容</td>
        </tr>
        <tr>
          <td class="tdright_new">用户名：</td>
          <td class="tdlefts">${dto.accounts}<input name="gameID" id="gameID" type="hidden" value="${dto.spreaderID}" /><input name="userID" id="userID" type="hidden" value="${dto.userID}" /><input name="type" id="type" type="hidden" value="<%=type%>" /><input name="spreaderID" type="hidden" value="<%=spreaderID%>" /><input name="accounts" type="hidden" value="<%=accounts%>" /></td>
        </tr>
        <tr>
          <td class="tdright_new">现有钱庄：</td>
          <td class="tdlefts">${dto.insureScore}<input name="insureScore" id="insureScore" type="hidden" value="${dto.insureScore}" />&nbsp;取<input name="insureGold" id="insureGold" type="text" class="input_width2" value="0" onkeyup="isNumber(this)" maxlength="11" onKeyPress="return event.keyCode>=48&&event.keyCode<=57||event.keyCode==46" onpaste="return !clipboardData.getData('text').match(/\D/)"
ondragenter="return false" oncontextmenu="return false" oncopy="return false" oncut="return false" /></td>
        </tr>
        <tr>
          <td class="tdright_new">现有游戏：</td>
          <td class="tdlefts">${dto.score}<input name="score" id="score" type="hidden" value="${dto.score}" />&nbsp;取<input name="gold" id="gold" type="text" class="input_width2" value="0" onkeyup="isNumber(this)" maxlength="11" onKeyPress="return event.keyCode>=48&&event.keyCode<=57||event.keyCode==46" onpaste="return !clipboardData.getData('text').match(/\D/)"
ondragenter="return false" oncontextmenu="return false" oncopy="return false" oncut="return false" /></td>
        </tr>
       
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
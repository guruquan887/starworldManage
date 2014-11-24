<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" >
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
String gameIDSelect = "";
if(request.getParameter("gameIDSelect")!=null){
   gameIDSelect = request.getParameter("gameIDSelect");
   System.out.println("gameIDSelect:"+gameIDSelect);
}
String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}
int type=0;
	if(request.getParameter("type")!=null){
	    type = Integer.parseInt(request.getParameter("type"));
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<script type='text/javascript' src='<%=request.getContextPath()%>/js/jquery.js'></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-latest.pack.js"></script>
<script type="text/javascript">
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
  	var reGameID = document.getElementById("in_ReGameID").value;
    var in_Reason = document.getElementById("in_Reason").value;
	if(reGameID == "")
		{
			alert("赠送号码不能为空！");
			return false;
		}
	else if(!IsPositiveInt64(reGameID))
			{
				alert("赠送号码输入非法！");
				return false;
			}
	else if(parseInt(reGameID)>999999)
	{
		alert("赠送号码输入非法！");
		return false;
	}
	else if(strLen(reGameID)>10)
		{
			alert("赠送号码输入非法！");
			return false;
		}
	else if(in_Reason==""){
	   alert("请输入赠送原因!");
	   return false;
	}
	else{
	return true;
	}
 }
 
  function strLen(s) {
    var len=0;
    for(var i=0;i<s.length;i++){
        if(!ischinese(s.charAt(i))){
            len+=1;
        }else {
            len+=2;
        }
    }
     return len;
}

function ischinese(s){
    var ret=false;
    for(var i=0;i<s.length;i++){
        if(s.charCodeAt(i)>=256){			
			ret=true;
			break;
		}
	}
    return ret;
}
 
 //判断给定的字符串是否是bigint类型
function IsPositiveInt64(str) {
    var reg = /^\d+$/;
    var r = str.match(reg);
    if (r == null) {
        return false;
    }
    else if (r > 9223372036854775807) {
        return false;
    }
    return true;
}
</script>
<body>
	<div class="title"><span>
	  <label>
	  <input name="Submit3" type="button" class="input" value="查看用户" onclick="window.location.href='<%=request.getContextPath()%>/member/gameuserList.do?action=gameUserList&type=0'" />
	  </label>
	</span>用户—赠送靓号</div>
    <form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/member/gameuserList.do?action=zsGameID" onsubmit="return check()">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
        <tr>
          <td width="120" class="menutop">项目</td>
          <td class="menutop">内容</td>
        </tr>
        <tr>
          <td class="menutop">用户名（gameID）：</td>
          <td class="tdlefts"><label>${user.accounts}(${user.gameID})<input name="userID" id="userID" type="hidden" value="${user.userID}" />
          </label></td>
        </tr>
        <tr>
          <td class="menutop"><span class="listTdLeft">赠送靓号ID</span>：</td>
          <td class="tdlefts"><input name="type" id="type" type="hidden" value="<%=type%>" /><input name="in_ReGameID" id="in_ReGameID" type="text" class="input_width2" value="${gameID1}" onkeyup="isNumber(this)" maxlength="11" />
            <select name="gameIDSelect" style="width:80px;" onchange="document.form1.in_ReGameID.value = this.value">
             <c:forEach var="gameIDlist" items="${gameIDlist}">
             <option value="${gameIDlist.gameID}">${gameIDlist.gameID}</option>
             </c:forEach>
            </select></td>
        </tr>
        <tr>
          <td class="menutop"><span class="listTdLeft">赠送原因：</span></td>
          <td class="tdlefts"><label>
            <textarea name="in_Reason" id="in_Reason" cols="20" rows="2"></textarea><input name="type" id="type" type="hidden" value="<%=type%>" />
          </label></td>
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
<script language="javascript">
var ttype1=document.all.gameIDSelect;
var index1="<%=gameIDSelect %>";
for(var i=0; i<ttype1.options.length; i++){
	if(ttype1.options[i].value==index1){
		ttype1.options[i].selected=true;break;
	}
}
</script>
</html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
String [] ids = {};
String ss = "";
if(request.getParameterValues("checkbox")!=null){
   ids = request.getParameterValues("checkbox");
   for(int i=0;i<ids.length;i++){
	   ss += ids[i]+",";
	}
   System.out.println("造型成字符串的ss:"+ss);
}
String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}
int gameTypeID = 1;
	if(request.getParameter("gameTypeID")!=null){
	    gameTypeID = Integer.parseInt(request.getParameter("gameTypeID"));
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
    var score = document.getElementById("score").value;
	if(score==""){
	   alert("请输入赠送的银子数!");
	   return false;
	}
	if(parseInt(score)<=0){
	   alert("输入的银子数必须大于0!");
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
	</span>机器人—赠送银子</div>
    <form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/member/gamejquserList.do?action=zsScore" onsubmit="return check()">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
        <tr>
          <td width="120" class="menutop">项目</td>
          <td class="menutop">内容</td>
        </tr>
        <tr>
          <td class="menutop">赠送银子数目：</td>
          <td class="tdlefts"><label>
            <input name="serverID" id="serverID" type="hidden" value="<%=serverID%>" /><input name="gameTypeID" id="gameTypeID" type="hidden" value="<%=gameTypeID%>" /><input name="ss" id="ss" type="hidden" value="<%=ss%>" /><input name="score" id="score" type="text" class="input_width2" value="" onkeyup="isNumber(this)" onKeyPress="return event.keyCode>=48&&event.keyCode<=57||event.keyCode==46" onpaste="return !clipboardData.getData('text').match(/\D/)"
ondragenter="return false" onpaste="return false" oncontextmenu="return false" oncopy="return false" oncut="return false" maxlength="9" />
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
</html>
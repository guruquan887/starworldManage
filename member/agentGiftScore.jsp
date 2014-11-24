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
int type = 0;
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
 	var giftScore = document.getElementById("giftScore").value;
    var giftMinute = document.getElementById("giftMinute").value;
	var giftScoreAgent = document.getElementById("giftScoreAgent").value;
	var giftMinuteAgent = document.getElementById("giftMinuteAgent").value;
    if(giftScore==""){
	   alert("赠送银子不能为空");
	   return false;
	}
	if(giftMinute==""){
	   alert("时间不能为空");
	   return false;
	}
	if(giftScoreAgent==""){
	   alert("上线赠送银子不能为空");
	   return false;
	}
	if(giftMinuteAgent==""){
	   alert("上线时间不能为空");
	   return false;
	}
	return true;
 
 }
</script>
<body>
	<div class="title"><span>
	  <label>
	  <input name="Submit3" type="button" class="input" value="查看代理" onclick="window.location.href='<%=request.getContextPath()%>/member/gameAgentList.do?action=gameAgentList&type=-1'" />
	  </label>
	</span>赠送银子-设置</div>
    <form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/member/gameuserList.do?action=giftScore" onsubmit="return check()">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
        <tr>
          <td width="120" class="menutop">项目</td>
          <td class="menutop">内容</td>
        </tr>
        <tr>
          <td class="tdright_new">赠送银子：</td>
          <td class="tdlefts"><input name="giftScore" id="giftScore" type="text" value="${dto.giftScore}" onkeyup="isNumber(this)" /><input name="ss" id="ss" type="hidden" value="<%=ss%>" />
		  </td>
        </tr>
        <tr>
          <td class="tdright_new">赠送时间：</td>
          <td class="tdlefts"><input name="giftMinute" id="giftMinute" type="text" value="${dto.giftMinute}" onkeyup="isNumber(this)" /></td>
        </tr>
        <tr>
          <td class="tdright_new">上线赠送银子：</td>
          <td class="tdlefts"><input name="giftScoreAgent" id="giftScoreAgent" type="text" value="${dto.giftScoreAgent}"  onkeyup="isNumber(this)"/></td>
        </tr>
        <tr>
          <td class="tdright_new">上线赠送时间：</td>
          <td class="tdlefts"><input name="giftMinuteAgent" id="giftMinuteAgent" type="text" value="${dto.giftMinuteAgent}" onkeyup="isNumber(this)" /></td>
        </tr>
       
        <tr>
          <td class="tdright_new">&nbsp;</td>
          <td class="tdright"><label>
            <input name="Submit" type="submit" class="input" value="设置" />
            <input name="Submit2" type="reset" class="input" value="重置" />
          </label></td>
        </tr>
      </table>
</form>
</body>
</html>
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
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
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
    var in_string = document.getElementById("in_string").value;
	if(in_string==""){
	   alert("请输入用户名!");
	   return false;
	}
	if(in_string.length>31){
	   alert("用户名字符长度不能超过31个字符！");
	   return false;
	}
	
	else{
	return true;
 }
 }
</script>
<body>
	<div class="title"><span>
	</span>新增—限制用戶</div>
    <form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/member/ConfineList.do?action=addgameUserXZ" onsubmit="return check()">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
        <tr>
          <td width="120" class="menutop">项目</td>
          <td class="menutop">内容</td>
        </tr>
        <tr>
          <td class="menutop">用戶名：</td>
          <td class="tdlefts"><input name="in_string" id="in_string" type="text" /></td>
        </tr>
        <tr>
          <td class="menutop">失效時間：</td>
          <td class="tdlefts"><input name="in_enjoinOverDate" id="in_enjoinOverDate" onClick="WdatePicker()" readonly="readonly" type="text" />   警告：  不填写默认永久限制   </td>
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
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
 
 function isDate(str) 
{
    var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
    if (r == null) return false;
    var d = new Date(r[1], r[3] - 1, r[4]);
    return (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4]);
}
 
        function CheckFormInfo()
        {
            var addrString = document.getElementById("addrString").value;
            var enjoinOverDate = document.getElementById("in_enjoinOverDate").value;
            if(addrString=="")
            {
                alert("机器不能为空！");
                return false;
            } 
            if(addrString.length()>33)
            {
                alert("机器字符长度不能超过33个字符！");
                return false;
            }      
            if(enjoinOverDate!=""&&!isDate(enjoinOverDate))
            {
                alert("日期格式不正确！");
                return false;
            }                      
        }
</script>
<body>
	<div class="title"><span>
	</span>新增—限制机器</div>
    <form id="myFormInfo" name="myFormInfo" method="post" action="<%=request.getContextPath()%>/member/ConfineList.do?action=addconfineMachine" onsubmit="return CheckFormInfo()">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
        <tr>
          <td width="120" class="menutop">项目</td>
          <td class="menutop">内容</td>
        </tr>
        <tr>
          <td class="menutop">机器：</td>
          <td class="tdlefts"><input name="addrString" id="addrString" type="text" /></td>
        </tr>
        <tr>
          <td class="menutop">用户登录：</td>
          <td class="tdlefts"><input name="in_EnjoinLogon" id="in_EnjoinLogon" type="radio" value="0" checked="checked"/><label for="in_EnjoinLogon0">正常</label>
                    <input name="in_EnjoinLogon" id="in_EnjoinLogon" type="radio" value="1"/><label for="in_EnjoinLogon1">冻结</label>                                                  
	            </td>
        </tr>
		<tr>
          <td class="menutop">用户注册：</td>
          <td class="tdlefts"><input name="in_EnjoinRegister" id="in_EnjoinRegister" type="radio" value="0" checked="checked"/><label for="in_EnjoinLogon0">正常</label>
                    <input name="in_EnjoinRegister" id="in_EnjoinRegister" type="radio" value="1"/><label for="in_EnjoinLogon1">冻结</label></td>
        </tr>
         <tr>
          <td class="menutop">失效時間：</td>
          <td class="tdlefts"><input name="in_enjoinOverDate" id="in_enjoinOverDate" onClick="WdatePicker()"  type="text" />   警告：  不填写默认永久限制   </td>
        </tr>
		 <tr>
	            <td class="menutop">备注：</td>
	            <td class="tdlefts">
	                <input name="in_CollectNote" id="in_CollectNote" type="text" class="text" maxlength="32" style="width:500px;" value="" />    
	            </td>
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
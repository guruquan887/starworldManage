<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
  <%
	//判断是否已登陆，返回登陆页面
	if (session.getAttribute("USER") == null){
	out.print("<script>alert('对不起，您还没有登录本系统或者您已经超过登录时间!');  window.target='_parent';window.location.href='../login.jsp';</script>");
	return;
	}

	String msg="";
	if(request.getAttribute("msg")!=null){
		msg=(String)request.getAttribute("msg");
	}
   %>
<head>
<script type="text/javascript">
function CheckNull(theField, fieldName) {	
	if(isBlank(theField.value)){
		alert(fieldName);
		theField.focus();
		return 1;
	}
	return 0;
}
function isNumber(oNum){

 if(!oNum) return false; //line:160
 if(oNum.value=="") return false;
 var vv = oNum.value.replace(/[^\d]/g,'');
 var strP=/^\d+$/;
  if (!isNaN(oNum.value)) {
        return true;
    } else {
        alert("请输入数字");
        oNum.value=vv;
 		oNum.focus();
 		return false; 
    }
}
function isBlank(str) {
	var blankFlag = true;
	if (str.length == 0) 
		return true;
	for (var i = 0; i < str.length; i++) {
		if ((str.charAt(i) != "") && (str.charAt(i) != " ")) {
			blankFlag = false;
			break;
		}
	}
	return blankFlag;
}
function IsIp(str)
{
    var reg = /^((2[0-4]\d|25[0-5]|[01]?\d\d?)\.){3}(2[0-4]\d|25[0-5]|[01]?\d\d?)$/;
    var r = str.match(reg);
    if (r == null) {
        return false;
    }
    return true;
}
        function CheckFormInfo()
        {
            var information = document.myFormInfo.Information.value;
            var dBAddr = document.myFormInfo.DBAddr.value;
            var dBPort = document.myFormInfo.DBPort.value;
            var dBUser = document.myFormInfo.DBUser.value;
            var dBPassword = document.myFormInfo.DBPassword.value;
            var re = /^(-?[1-9][0-9]*|0)$/; 
            if(information.trim()=="")
            {
                alert("机器名称不能为空！");
                return false;
            }
            if(dBAddr.trim()=="")
            {
                alert("地址不能为空！");
                return false;
            }
            else
            {
                if(!IsIp(dBAddr))
                {
                    alert("地址输入非法！");
                    return false;
                }
            }
            if(dBPort.trim()=="")
            {
                alert("端口不能为空！");
                return false;
            }
            else
            {
                if (!re.test(dBPort.trim()))
                {
                    alert("端口必须为正整数！");
                    return false;
                }
                else
                {
                    if(dBPort.trim()<0)
                    {
                        alert("端口必须为正整数！");
                        return false;
                    }
                }
            }
            if(dBUser.trim()=="")
            {
                alert("用户名不能为空！");
                return false;
            }
            if(dBPassword.trim()=="")
            {
                alert("密码不能为空！");
                return false;
            }
        }
	</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<Div class="title">新建机器</Div>
	
    <form id="myFormInfo" name="myFormInfo" method="post" onsubmit="return CheckFormInfo()" action="<%=request.getContextPath() %>/game/gameSystem.do?action=addMachine">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
        <tr>
          <td width="120" class="menutop">项目</td>
          <td class="menutop">内容</td>
        </tr>
        <tr>
          <td class="tdright_new">机器名称：</td>
          <td class="tdlefts"><label>
          <input name="Information" id="Information" type="text" maxlength="10" class="input_width2" />
          </label></td>
        </tr>
        <tr>
          <td class="tdright_new">地址：</td>
          <td class="tdlefts"><label>
            <input name="DBAddr" id="DBAddr" type="text" maxlength="10" class="input_width2" />
          </label></td>
        </tr>
        <tr>
          <td class="tdright_new">端口：</td>
          <td class="tdlefts"><input name="DBPort" onKeyUp="isNumber(this)" id="DBPort" maxlength="14" type="text" class="input_width2" /></td>
        </tr>
        <tr>
          <td class="tdright_new">用户名：</td>
          <td class="tdlefts"><input name="DBUser" id="DBUser" maxlength="14" type="text" class="input_width2" /></td>
        </tr>
        <tr>
          <td class="tdright_new">密码：</td>
          <td class="tdlefts"><input name="DBPassword" id="DBPassword" maxlength="14" type="text" class="input_width2" /></td>
        </tr>
        <tr>
          <td class="tdright_new">机器码：</td>
          <td class="tdlefts"><input name="MachineID" id="MachineID" maxlength="14" type="text" class="input_width2" /></td>
        </tr>
        <tr>
          <td class="tdright_new">&nbsp;</td>
          <td class="tdright"><label>
            <input name="Submit" type="submit" class="input" value="确定添加" />
            <input name="Submit2" type="reset" class="input" value="重新填写" />
          </label></td>
        </tr>
      </table>
</form>
</body>
</html>
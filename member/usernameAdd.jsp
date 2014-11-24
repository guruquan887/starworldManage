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
<body>
	<Div class="title"><span>
	  <label>
	  <input name="Submit3" type="button" class="input" value="查看" onclick="window.location.href='<%=request.getContextPath()%>/member/gameuserList.do?action=gameUserList'" />
	  </label>
	</span>会员—新增</Div>
	
    <form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/member/gameuserList.do?action=addUser">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
        <tr>
          <td width="120" class="menutop">项目</td>
          <td class="menutop">内容</td>
        </tr>
		<tr>
          <td class="tdright_new">所属：</td>
          <td class="tdlefts">${dto.accounts}/${dto.spreaderID}</td>
        </tr>
        <tr>
          <td class="tdright_new">用户名：</td>
          <td class="tdlefts"><input name="type" id="type" type="hidden" value="0" /><input name="sxAccounts" type="hidden" value="${dto.accounts}" /><input name="accounts" id="accounts" onBlur="checkusername();" type="text" class="input_width2" /><font color="#FF0000"><span id="accountdiv"></span></font></td>
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
          <td class="tdright_new">状态：</td>
          <td class="tdlefts"><label>
            <input name="isFreeze" id="isFreeze" type="radio" value="0" checked="checked" />
            启用
            <input type="radio" name="isFreeze" id="isFreeze" value="1" />
停用 </label></td>
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
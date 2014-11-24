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
long insureScore = 0;
if(request.getAttribute("insureScore")!=null){
	insureScore = (Long)(request.getAttribute("insureScore"));
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
 
</script>
<body>
	<div class="tab clearfix">
		<ul>
			<li class="active"><a href="<%=request.getContextPath()%>/member/gameuserList.do?action=spreaderSet">推广管理</a></li>
			<li><a href="<%=request.getContextPath()%>/member/spreaderUserList.jsp">推广明细</a></li>
			<li><a href="<%=request.getContextPath()%>/member/gameuserList.do?action=SpreaderUserList">财务明细</a></li>
		</ul>
	</div>
	
    <form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/member/gameuserList.do?action=spreaderSetUpdate" >
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
        <tr>
          <td colspan="2" class="menutop"><div align="left">注册赠送</div></td>
        </tr>
        <tr>
          <td width="120" class="tdright_new">银子：</td>
          <td class="tdlefts"><label>
            <input type="text" name="RegisterGrantScore" value="${dto.registerGrantScore}" />
          </label></td>
        </tr>
        <tr>
          <td colspan="2" class="menutop"><div align="left">游戏时长赠送（属于一次性赠送，推荐设置108000）</div></td>
        </tr>
		<tr>
          <td width="120" class="tdright_new">时长（秒）：</td>
          <td class="tdlefts"><label>
            <input type="text" name="PlayTimeCount" value="${dto.playTimeCount}"/>（单位：秒）
          </label></td>
        </tr>
		<tr>
          <td width="120" class="tdright_new">银子：</td>
          <td class="tdlefts"><label>
            <input type="text" name="PlayTimeGrantScore" value="${dto.playTimeGrantScore}"/>
          </label></td>
        </tr>
		<tr>
          <td colspan="2" class="menutop"><div align="left">充值赠送</div></td>
        </tr>
		<tr>
          <td width="120" class="tdright_new">比率：</td>
          <td class="tdlefts"><label>
            <input type="text" name="FillGrantRate" value="${dto.fillGrantRate}"/>（单位：%）
          </label></td>
        </tr>
		        <tr>
          <td colspan="2" class="menutop"><div align="left">结算赠送</div></td>
        </tr>
		<tr>
          <td width="120" class="tdright_new">比率：</td>
          <td class="tdlefts"><label>
            <input type="text" name="BalanceRate" value="${dto.balanceRate}"/>（单位：%）
          </label></td>
        </tr>
		<tr>
          <td width="120" class="tdright_new">最低值：</td>
          <td class="tdlefts"><label>
            <input type="text" name="MinBalanceScore" value="${dto.minBalanceScore}"/>
          </label></td>
        </tr>
        <tr>
          <td class="tdright_new">&nbsp;</td>
          <td class="tdright"><label>
            <input name="Submit" type="submit" class="input" value="保存" />
			</label></td>
        </tr>
      </table>
</form>
</body>
</html>
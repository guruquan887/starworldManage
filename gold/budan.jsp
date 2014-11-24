<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<script language="javascript">
function check(){

  var accounts = document.form2.accounts.value;
  var r3_Amt = document.form2.r3_Amt.value;
  if(accounts==''){
      alert("请输入充值用户!");
	  return false;
  }
  if(r3_Amt==''){
      alert("请输入充值金额!");
	  return false;
  }
  return true;
}

</script>
<body>
<Div class="title"><span>  
	  <form id="form1" name="form1" method="post" action="">
	    <label>
	    <input name="Submit" type="button" class="input" value="取款" onclick="window.location.href='<%=request.getContextPath()%>/gold/drawList.do?action=showDrawList'" />
	    <input name="Submit3" type="button" class="input" value="存款"  onclick="window.location.href='<%=request.getContextPath()%>/gold/yeepayList.do?action=showYeepayList'" />
	    </label>
	  </form>
	</span>网银管理—补单</Div>
    <form id="form2" name="form2" method="post" onSubmit="return check()" action="<%=request.getContextPath()%>/gold/yeepayList.do?action=addYeaapy">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
        <tr>
          <td width="120" class="menutop">项目</td>
          <td class="menutop">内容</td>
        </tr>
        <tr>
          <td class="tdright_new">订单号：</td>
          <td class="tdlefts">${dh}<input type="hidden" name="dh" value="${dh}" ></td>
        </tr>
        <tr>
          <td class="tdright_new">充值用户：</td>
          <td class="tdlefts"><label>
            <input name="accounts" type="text" class="input_width2" />
          </label></td>
        </tr>
        <tr>
          <td class="tdright_new">充值金额：</td>
          <td class="tdlefts"><label>
            <input name="r3_Amt" type="text" class="input_width2" />
          </label></td>
        </tr>
        <tr>
          <td class="tdright_new">&nbsp;</td>
          <td class="tdright"><label>
            <input name="Submit4" type="submit" class="input" value="确定补单" />
            <input name="Submit42" type="button" class="input" value="取消补单" onclick="window.location.href='<%=request.getContextPath()%>/gold/yeepayList.do?action=showYeepayList'" />
          </label></td>
        </tr>
      </table>
</form>
</body>
</html>
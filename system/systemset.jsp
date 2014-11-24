<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
String msg="";
if(request.getAttribute("msg")!=null){
	msg=(String)request.getAttribute("msg");
}
%>
<head>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<script language="javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
</script>
<body>
<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/system/NetCS.do?action=update">
  <table cellpadding="2" cellspacing="1" border="0" width="100%" align="center">
    <tr>
      <td height="25" colspan="4" class="menutop" style="text-align:left;">参数设置</td>
    </tr>
    <tr>
      <td height="25" colspan="4" class="tdright">  <A href="<%=request.getContextPath()%>/system/NetCS.do?action=preUpdate&systemParam=AllScoreIn57Game">总游戏币</A> |  <A href="<%=request.getContextPath()%>/system/NetCS.do?action=preUpdate&systemParam=AllScoreIn57GameRest">总剩余游戏币</A> | <A href="<%=request.getContextPath()%>/system/NetCS.do?action=preUpdate&systemParam=BankPrerequisite">存取条件</A> | <A href="<%=request.getContextPath()%>/system/NetCS.do?action=preUpdate&systemParam=RechargeRate">充值比率</A> | <A href="<%=request.getContextPath()%>/system/NetCS.do?action=preUpdate&systemParam=DrawTake">取款比率</A> | <A href="<%=request.getContextPath()%>/system/NetCS.do?action=preUpdate&systemParam=EnjoinInsure">钱庄服务</A> | <A href="<%=request.getContextPath()%>/system/NetCS.do?action=preUpdate&systemParam=EnjoinLogon">登录服务</A> | <A href="<%=request.getContextPath()%>/system/NetCS.do?action=preUpdate&systemParam=EnjoinRegister">注册服务</A> | <A href="<%=request.getContextPath()%>/system/NetCS.do?action=preUpdate&systemParam=GrantIPCount">注册IP限制</A> | <A href="<%=request.getContextPath()%>/system/NetCS.do?action=preUpdate&systemParam=GrantScoreCount">注册赠送</A> | <A href="<%=request.getContextPath()%>/system/NetCS.do?action=preUpdate&systemParam=MedalRate">奖牌返率</A> | <A href="<%=request.getContextPath()%>/system/NetCS.do?action=preUpdate&systemParam=RevenueRateTake">取款税率</A> | <A href="<%=request.getContextPath()%>/system/NetCS.do?action=preUpdate&systemParam=RevenueRateTransfer">转账税率</A> | <A href="<%=request.getContextPath()%>/system/NetCS.do?action=preUpdate&systemParam=TransferNullity">转账是否收税</A> | <A href="<%=request.getContextPath()%>/system/NetCS.do?action=preUpdate&systemParam=TransferPrerequisite">转账条件</A> | <A href="<%=request.getContextPath()%>/system/PreUpdateGradeCS.do">会员退水利率设置</A> | <A href="<%=request.getContextPath()%>/system/Pre3DUpdateNetCS.do">综合设置</A></td>
    </tr>
    <tr	align="center">
      <td width="20%" height="22" class="tdleft">键名：</td>
      <td width="80%" class="tdright">
      <input name="StatusName" value="${dto.statusName}" readonly="true" /></td>
    </tr>
    <tr	align="center">
      <td height="22" class="tdleft">键值：</td>
      <td class="tdright"><input name="StatusValue" value="${dto.statusValue}" /></td>
    </tr>
	 <tr align="center">
      <td height="22" class="tdleft">名称：</td>
      <td class="tdright"><input name="StatusTip" value="${dto.statusTip}" /></td>
    </tr>
	 <tr align="center">
	   <td height="22" class="tdleft">备注：</td>
	   <td class="tdright"><textarea name="StatusString" rows="3" cols="50">${dto.statusString}</textarea></td>
    </tr>
	 <tr align="center">
      <td height="22" class="tdleft">描述：</td>
      <td class="tdright"><textarea name="StatusDescription" rows="5" cols="50">${dto.statusDescription}</textarea></td>
    </tr>
	
    <tr	align="center">
      <td height="22" class="tdleft">&nbsp;</td>
      <td class="tdright"><label>
        <input type="submit" class="input" name="Submit" value="提交"/>
     &nbsp; 
     <input type="reset" class="input"  value="重置"/>
	 <input type="hidden" name="id" value="1" />
      </label></td>
    </tr>
  </table>
</form>
</body>
</html>
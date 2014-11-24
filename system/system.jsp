<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%
String msg="";
	if(request.getAttribute("msg")!=null){
	msg=(String)request.getAttribute("msg");
}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="<%=request.getContextPath()%>/js/userweb.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script> 
<script type="text/javascript" src="<%=request.getContextPath()%>/js/comm.js"></script>  
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Check.js"></script>  
<script type="text/javascript" src="<%=request.getContextPath()%>/js/Sort.js"></script> 
<script language="javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
function CheckFormInfo()
        {
            var grantMachineCount=document.myFormInfo2.in_GrantIPCount.value.trim();
            var grantScoreCount=document.myFormInfo2.in_GrantScoreCount.value.trim();
            
            if(IsNumeric(grantMachineCount)==false)
            {
                alert("每天赠送限制非数值型字符！");
                return false;
            }
            if(grantMachineCount<0)
            {
                alert("每天赠送限制不可为负数！");
                return false;
            }
            if(IsNumeric(grantScoreCount)==false)
            {
                alert("注册赠送银子数目非数值型字符！");
                return false;
            }
            if(grantScoreCount<0)
            {
                alert("注册赠送银子数目不可为负数！");
                return false;
            }
            
            if(IsNumeric(grantScoreCount)==false)
            {
                alert("注册赠送银子数目非数值型字符！");
                return false;
            }
            if(grantScoreCount<0)
            {
                alert("注册赠送银子数目不可为负数！");
                return false;
            }
        }
</script>
</head>
<body>
	<Div class="title">参数设置</Div>
    <form id="myFormInfo2" name="myFormInfo2" method="post" onsubmit="return CheckFormInfo()" action="<%=request.getContextPath()%>/system/NetCS.do?action=update">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
        <tr>
          <td width="120" class="menutop">名称</td>
          <td class="menutop">内容</td>
        </tr>
        <tr onmouseover="this.className='trover'" onmouseout="this.className='trout'">
          <td class="tdright_new">保险柜服务：</td>
          <td class="tdright"><label>状态 ：
              <input type="radio" name="in_EnjoinInsure" id="in_EnjoinInsure0" <c:if test="${dto.in_EnjoinInsure==0}" var="true">checked="checked"</c:if> value="0" />
          启用
          <input type="radio" name="in_EnjoinInsure" id="in_EnjoinInsure1" <c:if test="${dto.in_EnjoinInsure==1}" var="true">checked="checked"</c:if>value="1" />
          停用 <br />
          说明 ： 
          <input type="text" name="in_EnjoinInsureNote" class="input_width4" value="${dto.in_EnjoinInsureNote}" />
          </label></td>
        </tr>
        <tr onmouseover="this.className='trover'" onmouseout="this.className='trout'">
          <td class="tdright_new">登陆服务：</td>
          <td class="tdright">状态 ：
            <input type="radio" name="in_EnjoinLogon" id="in_EnjoinLogon0" <c:if test="${dto.in_EnjoinLogon==0}" var="true">checked="checked"</c:if> value="0" />
启用
<input type="radio" name="in_EnjoinLogon" id="in_EnjoinLogon1" <c:if test="${dto.in_EnjoinLogon==1}" var="true">checked="checked"</c:if> value="1" />
停用 <br />
说明 ：
<input type="text" name="in_EnjoinLogonNote" class="input_width4" value="${dto.in_EnjoinLogonNote}" /></td>
        </tr>
        <tr onmouseover="this.className='trover'" onmouseout="this.className='trout'">
          <td class="tdright_new">注册服务：</td>
          <td class="tdright">状态 ：
            <input type="radio" name="in_EnjoinRegister" id="in_EnjoinRegister0" <c:if test="${dto.in_EnjoinRegister==0}" var="true">checked="checked" </c:if>value="0" />
启用
<input type="radio" name="in_EnjoinRegister" id="in_EnjoinRegister1" <c:if test="${dto.in_EnjoinRegister==1}" var="true">checked="checked" </c:if> value="1" />
停用 <br />
说明 ：
<input type="text" name="in_EnjoinRegisterNote" class="input_width4" value="${dto.in_EnjoinRegisterNote}" /></td>
        </tr>
        <tr onmouseover="this.className='trover'" onmouseout="this.className='trout'">
          <td class="tdright_new">每天IP赠送限制：</td>
          <td class="tdright">数目：
            <input type="text" name="in_GrantIPCount" class="input_width5" value="${dto.in_GrantIPCount}" /><br />
          
          说明： <input type="text" name="in_GrantIPCountNote" class="input_width4" value="${dto.in_GrantIPCountNote}" /></td>
        </tr>
        <%--<tr onmouseover="this.className='trover'" onmouseout="this.className='trout'">
          <td class="tdright_new">胜率比值：</td>
          <td class="tdright"><input name="winRate" value="${dto.winRate}" onKeyUp="isNumber(this)" type="text" class="input_width2" />%</td>
        </tr>--%>
        <tr onmouseover="this.className='trover'" onmouseout="this.className='trout'">
          <td class="tdright_new">注册赠送银子：</td>
          <td class="tdright">数目：
            <input type="text" name="in_GrantScoreCount" class="input_width5" value="${dto.in_GrantScoreCount}" />
            <br />
说明：
<input type="text" name="in_GrantScoreCountNote" class="input_width4" value="${dto.in_GrantScoreCountNote}" /></td>
        </tr>
        <tr onmouseover="this.className='trover'" onmouseout="this.className='trout'">
          <td class="tdright_new">钱庄取款税收比例：</td>
          <td class="tdright">数目：
            <input type="text" name="in_RevenueRateTake" class="input_width5" value="${dto.in_RevenueRateTake}" />
            ‰<br />
说明：
<input type="text" name="in_RevenueRateTakeNote" class="input_width4" value="${dto.in_RevenueRateTakeNote}" /></td>
        </tr>
        <tr onmouseover="this.className='trover'" onmouseout="this.className='trout'">
          <td class="tdright_new">钱庄转账税收比例：</td>
          <td class="tdright">数目：
            <input type="text" name="in_RevenueRateTransfer" class="input_width5" value="${dto.in_RevenueRateTransfer}" />
            ‰<br />
说明：
<input type="text" name="in_RevenueRateTransferNote" class="input_width4" value="${dto.in_RevenueRateTransferNote}" /></td>
        </tr>
        <tr onmouseover="this.className='trover'" onmouseout="this.className='trout'">
          <td class="tdright_new">钱庄存取限制：</td>
          <td class="tdright">数目：
            <input type="text" name="in_BankPrerequisite" class="input_width5" value="${dto.in_BankPrerequisite}" />
            <br />
说明：
<input type="text" name="in_BankPrerequisiteNote" class="input_width4" value="${dto.in_BankPrerequisiteNote}" /></td>
        </tr>
        <tr onmouseover="this.className='trover'" onmouseout="this.className='trout'">
          <td class="tdright_new">钱庄转账限制：</td>
          <td class="tdright">数目：
            <input type="text" name="in_TransferPrerequisite" class="input_width5" value="${dto.in_TransferPrerequisite}" />
            <br />
说明：
<input type="text" name="in_TransferPrerequisiteNote" class="input_width4" value="${dto.in_TransferPrerequisiteNote}" /></td>
        </tr>
        <tr onmouseover="this.className='trover'" onmouseout="this.className='trout'">
          <td class="tdright_new">奖牌返还比率：</td>
          <td class="tdright">比率：
            <input type="text" name="in_MedalRate" class="input_width5" value="${dto.in_MedalRate}" />
            ‰<br />
说明：
<input type="text" name="in_MedalRateNote" class="input_width4" value="${dto.in_MedalRateNote}" /></td>
        </tr>
		<tr onmouseover="this.className='trover'" onmouseout="this.className='trout'">
          <td class="tdright_new">网银充值比率：</td>
          <td class="tdright">比率：
            <input type="text" name="in_RechargeRate" class="input_width5" value="${dto.in_RechargeRate}" />
            <br />
说明：
<input type="text" name="in_RechargeRateNote" class="input_width4" value="${dto.in_RechargeRateNote}" /></td>
        </tr>
		<tr onmouseover="this.className='trover'" onmouseout="this.className='trout'">
          <td class="tdright_new">游戏送银子：</td>
          <td class="tdright">数目：
            <input type="text" name="in_PlayGameScoreCount" class="input_width5" value="${dto.in_PlayGameScoreCount}" />
            <br />
说明：
<input type="text" name="in_PlayGameScoreNote" class="input_width4" value="${dto.in_PlayGameScoreNote}" /></td>
        </tr>
		<tr onmouseover="this.className='trover'" onmouseout="this.className='trout'">
		  <td class="tdright_new">管理后台公告：</td>
		  <td class="tdright">状态：
		  <input type="text" name="in_PlayGameScoreCount" class="input_width5" value="${dto.in_PlayGameScoreCount}" />
            <br />
说明：
<input type="text" name="in_PlayGameScoreNote" class="input_width4" value="${dto.in_PlayGameScoreNote}" />
	      </td>
	    </tr>
        <tr>
          <td class="tdright_new">&nbsp;</td>
          <td class="tdright">
            <label>
            <input name="Submit" type="submit" class="input" value="确定设置" />
            </label>
          </a></td>
        </tr>
      </table>
</form>
</body>
</html>
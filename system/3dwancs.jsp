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
<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/system/Update3DNetCS.do">
  <table cellpadding="2" cellspacing="1" border="0" width="100%" align="center">
    <tr>
      <td height="25" colspan="4" class="menutop" style="text-align:center;">网站参数设定</td>
    </tr>
    <%-- <tr	align="center">
     <td width="20%" height="22" class="tdleft"><div align="right">网站名称：</div></td>
      <td width="80%" class="tdright"><label>
        <div align="left">
          <input name="title" type="text" size="30" value="${dto.title}" />
          </div>
      </label></td>
    </tr>
    <tr	align="center">
      <td height="22" class="tdleft"><div align="right">网站地址：</div></td>
      <td class="tdright"><label>
      <div align="left">
        <input name="url" type="text" size="30" value="${dto.url}" />
      </div>
      </label></td>
    </tr>--%>
	<!-- <tr	align="center">
      <td width="20%" height="22" class="tdleft"><div align="right">银子兑换：</div></td>
      <td width="80%" class="tdright"><label>
            <div align="left">
              <input name="goldRateScore" type="text" size="12" value="${dto.goldRateScore}" />
                    <span class="tdleft"> 积分(1银子对兑的积分数)      </span></div>
      </label></td>
    </tr>-->
    <tr	align="center">
      <td height="22" class="tdleft"> <div align="right">新用户注册：</div></td>
      <td class="tdright"><label>
        <div align="left">
         <!-- <input name="regScore" type="text" size="12" value="${dto.regScore}" />
          <span class="tdleft">积分-->
          <input name="regGold" type="text" size="12" value="${dto.regGold}" />
          <span class="tdleft">银子</span></div>
      </label></td>
    </tr>
	    <tr	align="center">
      <td height="22" class="tdleft"> <div align="right">新用户注册：</div></td>
      <td class="tdright"><label>
        <div align="left">
         <!-- <input name="regScore" type="text" size="12" value="${dto.regScore}" />
          <span class="tdleft">积分-->
          <input name="regScore" type="text" size="12" value="${dto.regScore}" />
          <span class="tdleft">积分</span></div>
      </label></td>
    </tr>
    <tr	align="center">
      <td height="22" class="tdleft"><div align="right">网银充值设定：</div></td>
      <td class="tdright"><div align="left">
        <input name="yeepayRate" type="text" size="12" value="${dto.yeepayRate}" />
        <span class="tdleft">（1/元）银子</span></div></td>
    </tr>
    <tr	align="center">
      <td height="22" class="tdleft">投注额的倍数：</td>
      <td class="tdright"><label>
        <input type="text" size="12" name="beishu" value="${dto.beishu}"><span class="tdleft">达到存款额的倍数返还第一次存款的奖励</span>
      </label></td>
    </tr>
    <tr	align="center">
      <td height="22" class="tdleft">第一次存款反水的利率：</td>
      <td class="tdright"><input type="text" size="12" name="fanben" value="${dto.fanben}"><span class="tdleft">百分比&nbsp;&nbsp;例如：10.0 = 10%</span></td>
    </tr>
   <!-- <tr	align="center">
      <td height="22" class="tdleft"><div align="right">推广的下线所获得奖励：</div></td>
      <td class="tdright"><div align="left">
          <input name="onLineTimeGold" type="text" size="12" value="${dto.onLineTimeGold}" />
        (万银子)
        /
          <input name="onLineTime" type="text" size="3" value="${dto.onLineTime}" />
          小时</div></td>
    </tr>
    <tr	align="center">
      <td height="22" class="tdleft"><div align="right">推广的上线所获得奖励：</div></td>
      <td class="tdright"><div align="left">
        <input name="uponLineTimeGold" type="text" size="12" value="${dto.uponLineTimeGold}" />
        (万银子)
        /
        <input name="onLineTime" type="text" size="3" value="${dto.onLineTime}" />
      小时</div></td>
    </tr>-->
    <tr	align="center">
      <td height="22" class="tdleft"><div align="right">上线充值提成：</div></td>
      <td class="tdright"><label>
        <div align="left">
          <input name="gameTax" type="text" size="12" value="${dto.gameTax}"/><span class="tdleft">百分比&nbsp;&nbsp;例如：0.5 = 0.5%（推广的下线充值上线所获提成）</span></div>
      </label></td>
    </tr>
   <!-- <tr	align="center">
      <td height="22" class="tdleft"><div align="right">用户转账系统税收：</div></td>
      <td class="tdright"><div align="left">
	  <input name="xtss" type="text" size="12" value="${dto.xtss}"/>
	  <span class="tdleft">百分比（用户转账的时候系统征收的税收）</span></div></td>
    </tr>
    <tr	align="center">
      <td height="22" class="tdleft"><div align="right">管理员邮箱：</div></td>
      <td class="tdright"><div align="left">
        <input name="adminEmail" type="text" size="30" value="${dto.adminEmail}"/>
      </div></td>
    </tr>
    <tr	align="center">
      <td height="22" class="tdleft"><div align="right">联系人：</div></td>
      <td class="tdright"><div align="left">
        <input name="linkMan" type="text" size="30" value="${dto.linkMan}"/>
      </div></td>
    </tr>
    <tr	align="center">
      <td height="22" class="tdleft"><div align="right">ICP 备案信息：</div></td>
      <td class="tdright"><div align="left">
        <input name="recordInfo" type="text" size="30" value="${dto.recordInfo}"/>
      </div></td>
    </tr>-->
    <tr	align="center">
      <td height="22" class="tdleft">&nbsp;</td>
      <td class="tdright"><label>
        <input type="submit" class="input" name="Submit" value="提交"/>
     &nbsp; 
     <input type="reset" class="input" name="Submit2" value="重置"/>
	 <input type="hidden" name="id" value="1" />
      </label></td>
    </tr>
  </table>
</form>
</body>
</html>
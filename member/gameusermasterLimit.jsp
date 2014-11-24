<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%
String msg="";
if(request.getAttribute("msg")!=null){
	msg=request.getAttribute("msg").toString();
}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
   //弹出友好提示
var msg="<%=msg %>";
if(msg!='')alert(msg);
</script>
</head>
<body>
	<Div class="title"><span>  
	</span>管理人员—权限设置</Div>
	<c:if test="${master!=null}" var="true">
    <form id="form2" name="form2" method="post" action="<%=request.getContextPath() %>/member/gamemasterList.do?action=saveright">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
        <tr>
          <td width="120" class="menutop">项目</td>
          <td class="menutop">内容</td>
        </tr>
        <tr>
          <td class="tdright_new">游戏账号：</td>
          <td class="tdlefts">${master.accounts}</td>
        </tr>
        <tr>
          <td class="tdright_new">权限：</td>
          <td class="tdlefts"><label>
            <INPUT type="checkbox" name="master_CAN_LIMIT_PLAY" <c:if test="${master.master_CAN_LIMIT_PLAY==1}" var="true">checked</c:if>/>允许禁止游戏
            	<INPUT type="checkbox" name="master_CAN_LIMIT_LOOKON" <c:if test="${master.master_CAN_LIMIT_LOOKON==1}" var="true">checked</c:if>/>允许禁止旁观
            	<INPUT type="checkbox" name="master_CAN_CAN_LIMIT_WISPER" <c:if test="${master.master_CAN_LIMIT_WISPER==1}" var="true">checked</c:if>/>允许禁止私聊
            	<INPUT type="checkbox" name="master_CAN_LIMIT_ROOM_CHAT" <c:if test="${master.master_CAN_LIMIT_ROOM_CHAT==1}" var="true">checked</c:if>/>允许禁止房间聊天
            	<INPUT type="checkbox" name="master_CAN_LIMIT_GAME_CHAT" <c:if test="${master.master_CAN_LIMIT_GAME_CHAT==1}" var="true">checked</c:if>/>允许禁止游戏聊天
            	<br>
            	<INPUT type="checkbox" name="master_CAN_CUT_USER" <c:if test="${master.master_CAN_CUT_USER==1}" var="true">checked</c:if>/>允许踢出用户
            	<INPUT type="checkbox" name="master_CAN_FORBID_ACCOUNTS" <c:if test="${master.master_CAN_FORBID_ACCOUNTS==1}" var="true">checked</c:if>/>允许封锁帐号
            	<INPUT type="checkbox" name="master_CAN_CONFINE_IP" <c:if test="${master.master_CAN_CONFINE_IP==1}" var="true">checked</c:if>/>允许禁止IP地址
            	<INPUT type="checkbox" name="master_CAN_SEE_USER_IP" <c:if test="${master.master_CAN_SEE_USER_IP==1}" var="true">checked</c:if>/>允许查看IP地址
            	<INPUT type="checkbox" name="master_CAN_SEND_WARNING" <c:if test="${master.master_CAN_SEND_WARNING==1}" var="true">checked</c:if>/>允许发送警告
            	<br>
            	<INPUT type="checkbox" name="master_CAN_ISSUE_MESSAGE" <c:if test="${master.master_CAN_ISSUE_MESSAGE==1}" var="true">checked</c:if>/>允许发布消息
            	<INPUT type="checkbox" name="master_CAN_BIND_GAME" <c:if test="${master.master_CAN_BIND_GAME==1}" var="true">checked</c:if>/>允许游戏绑定
            	<INPUT type="checkbox" name="master_CAN_BIND_GLOBAL" <c:if test="${master.master_CAN_BIND_GLOBAL==1}" var="true">checked</c:if>/>允许全局绑定
            	<INPUT type="checkbox" name="master_CAN_SERVER_OPTION" <c:if test="${master.master_CAN_SERVER_OPTION==1}" var="true">checked</c:if>/>允许配置房间</label></td>
        </tr>
        <tr>
          <td class="tdright_new">&nbsp;</td>
          <td class="tdright"><label>
            <input name="Submit3" type="submit" class="input" value="确定保存" />
			<input type="hidden" name="userID" value="${master.uid}"/>
			<input type="hidden" name="accounts" value="${master.accounts}"/>
          </label></td>
        </tr>
      </table>
</form>
</c:if>
</body>
</html>
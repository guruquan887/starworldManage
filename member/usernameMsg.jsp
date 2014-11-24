<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
int curPage = 0;
   if(request.getParameter("curPage")!=null){
        curPage = Integer.parseInt(request.getParameter("curPage"));
   }
String selectOne="";
	if(request.getParameter("selectOne")!=null){
		selectOne=request.getParameter("selectOne");
	}
int type = 0;
   if(request.getParameter("type")!=null){
        type = Integer.parseInt(request.getParameter("type"));
   }
int userRight = 0;
   if(request.getAttribute("userRight")!=null){
	   userRight = (Integer)(request.getAttribute("userRight"));
   }
int masterRight = 0;
   if(request.getAttribute("masterRight")!=null){
	   masterRight = (Integer)(request.getAttribute("masterRight"));
   }
int spreaderID = 3;
int levelID = 0;
if(request.getParameter("spreaderID")!=null){
	    spreaderID = Integer.parseInt(request.getParameter("spreaderID"));
	}
	if(request.getParameter("levelID")!=null){
	    levelID = Integer.parseInt(request.getParameter("levelID"));
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
<script language="javascript" src="<%=request.getContextPath()%>/js/userweb.js"></script>
<script language="javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
function checkbox()
{
var str=document.getElementsByName("limit");
var objarray=str.length;
var chestr="";
for (i=0;i<objarray;i++)
{
  if(str[i].checked == true)
  {
   chestr+=str[i].value+",";
  }
}
if(chestr == "")
{
  //alert("请选择权限!");
}
else
{
  //alert("您现择的是："+chestr);
}
}
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
function SelectAllTable(flag,tableID)
{
    var m_list_table =  window.document.getElementById(tableID);
    var m_list_checkbox = GelTags("input", m_list_table);
    for (var i = m_list_checkbox.length - 1; i >= 0; i--) {
        m_list_checkbox[i].checked = flag;
    }
}
function GelTags(tag, ob) {
    return (ob || gd).getElementsByTagName(tag);
}
</script>
</head>
<body>
	<Div class="title"><span>
	 <%-- <label> <input name="Submit3" type="button" class="input" value="新增" onclick="window.location.href='usernameAdd.html'" /> </label>--%>
	  <label> <input name="Submit3" type="button" class="input" value="查看用户" onclick="window.location.href='<%=request.getContextPath()%>/member/gameuserList.do?action=gameUserList'" /> </label>
	</span>游戏用户—详情</Div>
	
    <form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/member/gameuserList.do?action=update">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
        <tr>
          <td width="120" class="menutop">项目</td>
          <td class="menutop">内容</td>
        </tr>
        <tr>
          <td class="tdright_new">游戏ID：</td>
          <td class="tdlefts">${user.gameID}</td>
        </tr>
        <tr>
          <td class="tdright_new">用户名：</td>
          <td class="tdlefts"><input type="hidden" name="userID" value="${user.userID}" /><input type="hidden" name="selectOne" value="<%=selectOne%>" /><input type="hidden" name="curPage" value="<%=curPage%>" /><input type="hidden" name="type" value="<%=type%>" />
		  <input name="accounts" type="text" class="input_width2" value="${user.accounts}" /><c:if test="${roleId==1}" var="true"><input name="specialRight" type="radio" value="1" <c:if test="${user.specialRight==1}" var="true"> checked</c:if>/>特殊账号<input name="specialRight" type="radio" value="0" <c:if test="${user.specialRight==0}" var="true">checked</c:if>/>普通用户</c:if></td>
        </tr>
        <tr>
          <td class="tdright_new">密码：</td>
          <td class="tdlefts"><span id="box">
            <input name="logonPass" type="password" class="input_width2" value="" /></span>留空不修改</td>
        </tr>
        <tr>
          <td class="tdright_new">钱庄密码：</td>
          <td class="tdlefts"><span id="box2">
            <input name="insurePass" type="password" class="input_width2" value="" /></span>留空不修改</td>
        </tr>
        <tr>
          <td class="tdright_new">用户昵称：</td>
          <td class="tdlefts"><input name="regAccounts" type="text" class="input_width3" value="${user.regAccounts}" /></td>
        </tr>
		<tr>
          <td class="tdright_new">真实姓名：</td>
          <td class="tdlefts"><input name="compellation" type="text" class="input_width3" value="${user.compellation}" /></td>
        </tr>
		<c:if test="${roleId==1}" var="true"><tr>
		  <td class="tdright_new">胜率：</td>
		  <td class="tdlefts"><input name="winRate" type="text" style="width:50px" maxlength="3" onkeyup="isNumber(this)" value="${user.winRate}" />%</td>
	    </tr></c:if>
		 <tr>
          <td class="tdright_new">用户头像：</td>
          <td class="tdlefts"><img width="25" height="25" src="<%=request.getContextPath()%>/gamepic/face${user.faceID}.gif" /></td>
        </tr>
        <tr>
          <td class="tdright_new">性别：</td>
          <td class="tdlefts"><label>
		  <input type="radio" name="gender" value="0" <c:if test="${user.gender==0}" var="true">
					checked</c:if> />
          保密
          <input type="radio" name="gender" value="1" <c:if test="${user.gender==1}" var="true">checked</c:if> />
           男
         <input type="radio" name="gender" value="2" <c:if test="${user.gender==2}" var="true">checked</c:if> />
           女
</label></td>
        </tr>
       <c:if test="${username=='admin'}" var="true">		</c:if>
        <tr>
          <td class="tdright_new">会员级别：</td>
          <td class="tdlefts"><label>
		  <input type="radio" name="memberOrder" value="0" <c:if test="${user.memberOrder==0}" var="true">checked</c:if>/>
          普通
            <input type="radio" name="memberOrder" value="1" <c:if test="${user.memberOrder==1}" var="true">checked</c:if>/>
          红钻
          <input type="radio" name="memberOrder" value="2" <c:if test="${user.memberOrder==2}" var="true">checked</c:if>/>
蓝钻
<input type="radio" name="memberOrder" value="3" <c:if test="${user.memberOrder==3}" var="true">checked</c:if> />
黄钻
<input type="radio" name="memberOrder" value="4" <c:if test="${user.memberOrder==4}" var="true">checked</c:if>/>
紫钻</label></td>
        </tr>
        <tr>
          <td class="tdright_new">个性签名：</td>
          <td class="tdlefts"><textarea name="underWrite" rows="4" cols="60">${user.underWrite}</textarea></td>
        </tr>
        <tr>
          <td class="tdright_new">手机号码：</td>
          <td class="tdlefts">&nbsp;${user.registerMobile}</td>
        </tr>
        <tr>
          <td class="tdright_new">身份证号码：</td>
          <td class="tdlefts">&nbsp;${user.passportID}</td>
        </tr>
		<tr>
          <td class="tdright_new">安全邮箱：</td>
          <td class="tdlefts">&nbsp;${user.safeEmail}</td>
        </tr>
        <tr>
          <td class="tdright_new">密保保护问题：</td>
          <td class="tdlefts">&nbsp;${user.question1}</td>
        </tr>
        <tr>
          <td class="tdright_new">密保保护答案：</td>
          <td class="tdlefts">&nbsp;${user.response1}</td>
        </tr>
        <tr>
          <td class="tdright_new">状态：</td>
          <td class="tdlefts"><span class="${user.zhStateCss}">${user.zhStateName}</span></td>
        </tr>
        <tr>
          <td class="tdright_new">最后登录时间：</td>
          <td class="tdright">${user.lastLoginTime}</td>
        </tr>
        <tr>
          <td class="tdright_new">最后登录IP：</td>
          <td class="tdright">${user.lastLogonIP}</td>
        </tr>
		        <tr>
          <td class="tdright_new">赠送银子：</td>
          <td class="tdlefts"><input name="giftScore" id="giftScore" type="text" value="${user.giftScore}" onkeyup="isNumber(this)" />
		  </td>
        </tr>
        <tr>
          <td class="tdright_new">赠送时间：</td>
          <td class="tdlefts"><input name="giftMinute" id="giftMinute" type="text" value="${user.giftMinute}" onkeyup="isNumber(this)" />单位：分钟</td>
        </tr>
        <tr>
          <td class="tdright_new">上线赠送银子：</td>
          <td class="tdlefts"><input name="giftScoreAgent" id="giftScoreAgent" type="text" value="${user.giftScoreAgent}"  onkeyup="isNumber(this)"/></td>
        </tr>
        <tr>
          <td class="tdright_new">上线赠送时间：</td>
          <td class="tdlefts"><input name="giftMinuteAgent" id="giftMinuteAgent" type="text" value="${user.giftMinuteAgent}" onkeyup="isNumber(this)" />单位：分钟</td>
        </tr>
      <c:if test="${roleId==1}" var="true"> <tr>
    <td class="tdright_new">全选<input type="checkbox" onclick="SelectAllTable(this.checked,'userOrder');" />    用户权限：</td>
    <td class="tdright">
        <table border="0" style="padding:5px 5px 5px 0;" cellpadding="0" cellspacing="0" id="userOrder">
        <tr>
            <td><input name="in_UserRight" id="in_UserRight1" type="checkbox" value="1" /><label for="in_UserRight1">不能进行游戏</label></td>           
            <td><input name="in_UserRight" id="in_UserRight2" type="checkbox" value="2" <c:if test="${user.userRight==2}" var="true">checked='checked'</c:if>/><label for="in_UserRight2">不能旁观游戏</label></td>
            <td><input name="in_UserRight" id="in_UserRight3" type="checkbox" value="4" <c:if test="${user.userRight==4}" var="true">checked='checked'</c:if>/><label for="in_UserRight3">不能发送私聊</label></td>
            <td><input name="in_UserRight" id="in_UserRight4" type="checkbox" value="8" <c:if test="${user.userRight==8}" var="true">checked='checked'</c:if>/><label for="in_UserRight4">不能大厅聊天</label></td>
            <td><input name="in_UserRight" id="in_UserRight5" type="checkbox" value="16" <c:if test="${user.userRight==16}" var="true">checked='checked'</c:if>/><label for="in_UserRight5">不能游戏聊天</label></td>
            <td><input name="in_UserRight" id="in_UserRight6" type="checkbox" value="256" <c:if test="${user.userRight==256}" var="true">checked='checked'</c:if>/><label for="in_UserRight6">游戏双倍积分</label></td>
        </tr> 
        <tr>
            <td><input name="in_UserRight" id="in_UserRight7" type="checkbox" value="512" <c:if test="${user.userRight==512}" var="true">checked='checked'</c:if>/><label for="in_UserRight7">游戏踢出用户</label></td>
            <td><input name="in_UserRight" id="in_UserRight8" type="checkbox" value="268435456" <c:if test="${user.userRight==268435456}" var="true">checked='checked'</c:if>/><label for="in_UserRight8">百家乐特殊用户</label></td>
			<td><input name="in_UserRight" id="in_UserRight8" type="checkbox" value="1879048192" <c:if test="${user.userRight==1879048192}" var="true">checked='checked'</c:if>/><label for="in_UserRight8">两张特殊用户</label></td>
        </tr>
        </table>    
		</td>
</tr>
<tr>
    <td class="tdright_new">管理级别：</td>
    <td class="tdright">
        <input name="in_MasterOrder" id="in_MasterOrder1" type="radio" value="0" <c:if test="${user.masterOrder==0}" var="true">checked='checked'</c:if>/><label for="in_MasterOrder1">普通玩家</label>
        <input name="in_MasterOrder" id="in_MasterOrder2" type="radio" value="1" <c:if test="${user.masterOrder==1}" var="true">checked='checked'</c:if>/><span style="color:#105399;font-weight:bold;"><label for="in_MasterOrder2">管理员</label></span>    </td>
</tr>
<tr>
    <td class="tdright_new">全选<input type="checkbox"  onclick="SelectAllTable(this.checked,'masterOrder');"/>    管理权限：</td>
    <td class="tdright">
        <table border="0" style="padding:5px 5px 5px 0;" cellpadding="0" cellspacing="0" id="masterOrder">
        <tr>
            <td><input name="in_MasterRight" id="in_MasterRight1" type="checkbox" value="1" <c:if test="${user.masterRight==1}" var="true">checked='checked'</c:if>/><label for="in_MasterRight1">允许禁止游戏</label></td>           
            <td><input name="in_MasterRight" id="in_MasterRight2" type="checkbox" value="2" <c:if test="${user.masterRight==2}" var="true">checked='checked'</c:if>/><label for="in_MasterRight2">允许禁止旁观</label></td>
            <td><input name="in_MasterRight" id="in_MasterRight3" type="checkbox" value="4" <c:if test="${user.masterRight==4}" var="true">checked='checked'</c:if>/><label for="in_MasterRight3">允许禁止私聊</label></td>
            <td><input name="in_MasterRight" id="in_MasterRight4" type="checkbox" value="8" <c:if test="${user.masterRight==8}" var="true">checked='checked'</c:if>/><label for="in_MasterRight4">允许禁止聊天</label></td>
            <td><input name="in_MasterRight" id="in_MasterRight5" type="checkbox" value="16" <c:if test="${user.masterRight==16}" var="true">checked='checked'</c:if>/><label for="in_MasterRight5">允许禁止聊天</label></td>
            <td><input name="in_MasterRight" id="in_MasterRight6" type="checkbox" value="256" <c:if test="${user.masterRight==256}" var="true">checked='checked'</c:if>/><label for="in_MasterRight6">允许踢出用户</label></td>
        </tr> 
        <tr>
            <td><input name="in_MasterRight" id="in_MasterRight7" type="checkbox" value="512" <c:if test="${user.masterRight==512}" var="true">checked='checked'</c:if>/><label for="in_MasterRight7">允许查看地址</label></td>           
            <td><input name="in_MasterRight" id="in_MasterRight8" type="checkbox" value="1024" <c:if test="${user.masterRight==1024}" var="true">checked='checked'</c:if>/><label for="in_MasterRight8">允许解散游戏</label></td>
            <td><input name="in_MasterRight" id="in_MasterRight9" type="checkbox" value="4096" <c:if test="${user.masterRight==4096}" var="true">checked='checked'</c:if>/><label for="in_MasterRight9">允许禁止地址</label></td>
            <td><input name="in_MasterRight" id="in_MasterRight10" type="checkbox" value="8192" <c:if test="${user.masterRight==8192}" var="true">checked='checked'</c:if>/><label for="in_MasterRight10">允许禁止机器</label></td>
            <td><input name="in_MasterRight" id="in_MasterRight11" type="checkbox" value="16384" <c:if test="${user.masterRight==16384}" var="true">checked='checked'</c:if>/><label for="in_MasterRight11">允许发送警告</label></td>
            <td><input name="in_MasterRight" id="in_MasterRight12" type="checkbox" value="32768" <c:if test="${user.masterRight==32768}" var="true">checked='checked'</c:if>/><label for="in_MasterRight12">允许修改积分</label></td>
        </tr> 
        <tr>
            <td><input name="in_MasterRight" id="in_MasterRight13" type="checkbox" value="65536" <c:if test="${user.masterRight==65536}" var="true">checked='checked'</c:if>/><label for="in_MasterRight13">允许封锁帐号</label></td>           
            <td><input name="in_MasterRight" id="in_MasterRight14" type="checkbox" value="1048576" <c:if test="${user.masterRight==1048576}" var="true">checked='checked'</c:if>/><label for="in_MasterRight14">允许游戏绑定</label></td>
            <td><input name="in_MasterRight" id="in_MasterRight15" type="checkbox" value="2097152" <c:if test="${user.masterRight==2097152}" var="true">checked='checked'</c:if>/><label for="in_MasterRight15">允许全局绑定</label></td>            
            <td><input name="in_MasterRight" id="in_MasterRight16" type="checkbox" value="16777216" <c:if test="${user.masterRight==16777216}" var="true">checked='checked'</c:if>/><label for="in_MasterRight16">允许发布消息</label></td>
            <td><input name="in_MasterRight" id="in_MasterRight17" type="checkbox" value="33554432" <c:if test="${user.masterRight==33554432}" var="true">checked='checked'</c:if>/><label for="in_MasterRight17">允许管理房间</label></td>            
            <td><input name="in_MasterRight" id="in_MasterRight18" type="checkbox" value="67108864" <c:if test="${user.masterRight==67108864}" var="true">checked='checked'</c:if>/><label for="in_MasterRight18">允许管理配置</label></td>            
        </tr> 
        <tr>
            <td><input name="in_MasterRight" id="in_MasterRight19" type="checkbox" value="134217728" <c:if test="${user.masterRight==134217728}" var="true">checked='checked'</c:if>/><label for="in_MasterRight19">允许管理机器</label></td>           
        </tr></c:if>
        </table>    </td>
</tr> 
        <tr>
          <td class="tdright_new">&nbsp;</td>
          <td class="tdright"><label>
            <input name="Submit" type="submit" class="input" value="确定修改" />
            <input name="Submit2" type="reset" class="input" value="重新填写" />
          </label></td>
        </tr>
      </table>
</form>
</body>
</html>
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
int type = -1;
   if(request.getParameter("type")!=null){
        type = Integer.parseInt(request.getParameter("type"));
   }
double sxbjlZC = 0;
   if(request.getAttribute("sxbjlZC")!=null){
	   sxbjlZC = (Double)(request.getAttribute("sxbjlZC"));
   }
double sxbjlYJ = 0;
   if(request.getAttribute("sxbjlYJ")!=null){
	   sxbjlYJ = (Double)(request.getAttribute("sxbjlYJ"));
   }
double sxtax = 0;
if(request.getAttribute("sxtax")!=null){
	   sxtax = (Double)(request.getAttribute("sxtax"));
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
function check(){
var tax = document.getElementById("tax").value;
var bjlZC = document.getElementById("bjlZC").value;
var bjlYJ = document.getElementById("bjlYJ").value;
if(parseInt(tax)>'<%=sxtax%>'){
   alert("税收提成超出上限!");
   return false;
}
if(parseInt(bjlZC)>'<%=sxbjlZC%>'){
   alert("股份占成超出上限!");
   return false;
}
if(parseInt(bjlYJ)>'<%=sxbjlYJ%>'){
   alert("洗码佣金超出上限!");
   return false;
}
return true;

}
</script>
</head>
<body>
	<Div class="title"><span>
	 <%-- <label> <input name="Submit3" type="button" class="input" value="新增" onclick="window.location.href='usernameAdd.html'" /> </label>--%>
	  <label> <input name="Submit3" type="button" class="input" value="查看代理" onclick="window.location.href='<%=request.getContextPath()%>/member/gameAgentList.do?action=gameAgentList&type=-1'" /> </label>
	</span>代理—详情</Div>
	
    <form id="form1" name="form1" method="post" onsubmit="return check()" action="<%=request.getContextPath()%>/member/gameAgentList.do?action=update">
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
		  <input name="accounts" type="text" class="input_width2" value="${user.accounts}" /><input name="spreaderID" type="hidden" value="<%=spreaderID%>" /><input name="levelID" type="hidden" value="<%=levelID%>" /><input name="sxaccounts" type="hidden" value="<%=accounts%>" /></td>
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
<%--		<tr>
		  <td class="tdright_new">胜率：</td>
		  <td class="tdlefts"><input name="winRate" type="text" style="width:50px" maxlength="3" onkeyup="isNumber(this)" value="${user.winRate}" />%</td>
	    </tr>--%>
		 <tr>
          <td class="tdright_new">扣税提成：</td>
          <td class="tdlefts"><label>
            <input name="tax" id="tax" type="text" class="input_width2" value="${user.tax}" />%
            上限${sxtax}%
          </label></td>
        </tr>
        <tr>
          <td class="tdright_new">股份比例：</td>
          <td class="tdlefts"><input name="bjlZC" id="bjlZC" type="text" class="input_width2" value="${user.bjlZC}" />%&nbsp;&nbsp;上限${sxbjlZC}%</td>
        </tr>
        <tr>
          <td class="tdright_new">真人佣金：</td>
          <td class="tdlefts"><input name="bjlYJ" id="bjlYJ" type="text" class="input_width2" value="${user.bjlYJ}" />%&nbsp;&nbsp;上限${sxbjlYJ}%</td>
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
        <tr>
          <td class="tdright_new">个性签名：</td>
          <td class="tdlefts"><textarea name="underWrite" rows="4" cols="60">${user.underWrite}</textarea></td>
        </tr>
<%--        <tr>
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
        </tr>--%>
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
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
function save(){
	if (CheckNull(document.getElementById("round_maxbet"), '本局最大限额不能为空!')){
		return false;
	}
	if (CheckNull(document.getElementById("line_maxbet"), '单线最大限额不能为空!')){
		return false;
	}
	if (CheckNull(document.getElementById("single_maxbet"), '单注最大限额不能为空!')){
		return false;
	}
	if (CheckNull(document.getElementById("limit_minbet"), '单线最小限额不能为空!')){
		return false;
	}
	if (CheckNull(document.getElementById("bonus_maxbet"), 'bouns最大限额不能为空!')){
		return false;
	}
	if (CheckNull(document.getElementById("bonus_minbet"), 'bouns最小限额不能为空!')){
		return false;
	}
	else{
	form1.submit();
}}
	</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<Div class="title">修改房间配置</Div>
	
    <form id="form1" name="form1" method="post" onsubmit="return save()" action="<%=request.getContextPath() %>/game/dzroomManage.do?action=updateRoom">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
        <tr>
          <td width="120" class="menutop">项目</td>
          <td class="menutop">内容</td>
        </tr>
        <tr>
          <td class="tdright_new">房间名称：</td>
          <td class="tdlefts"><label><input name="gamename" id="gamename" value="${roomdto.gamename}" type="text" maxlength="10" readonly="true" class="input_width2" /></label></td>
        </tr>
        <tr>
          <td class="tdright_new">本局最大限额：</td>
          <td class="tdlefts"><label>
            <input name="round_maxbet" id="round_maxbet" onKeyUp="isNumber(this)" type="text" value="${roomdto.round_maxbet}" maxlength="10" class="input_width2" /><input name="gametype" id="gametype" type="hidden" value="${roomdto.gametype}" />
          </label></td>
        </tr>
        <tr>
          <td class="tdright_new">单线最大限额：</td>
          <td class="tdlefts"><input name="line_maxbet" onKeyUp="isNumber(this)" id="line_maxbet" maxlength="14" type="text" value="${roomdto.line_maxbet}" class="input_width2" /></td>
        </tr>
        <tr>
          <td class="tdright_new">单注最大限额：</td>
          <td class="tdlefts"><input name="single_maxbet" onKeyUp="isNumber(this)" id="single_maxbet" maxlength="14" type="text" value="${roomdto.single_maxbet}" class="input_width2" /></td>
        </tr>
		<tr>
          <td class="tdright_new">单线最小限额：</td>
          <td class="tdlefts"><input name="limit_minbet" onKeyUp="isNumber(this)" id="limit_minbet" maxlength="14" type="text" value="${roomdto.limit_minbet}" class="input_width2" /></td>
        </tr>
		<tr>
          <td class="tdright_new">bouns最大限额：</td>
          <td class="tdlefts"><input name="bonus_maxbet" onKeyUp="isNumber(this)" id="bonus_maxbet" maxlength="14" type="text" value="${roomdto.bonus_maxbet}" class="input_width2" /></td>
        </tr>
		<tr>
          <td class="tdright_new">bouns最小限额：</td>
          <td class="tdlefts"><input name="bonus_minbet" onKeyUp="isNumber(this)" id="bonus_minbet" maxlength="14" type="text" value="${roomdto.bonus_minbet}" class="input_width2" /></td>
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
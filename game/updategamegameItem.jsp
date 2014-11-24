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
	if (CheckNull(document.getElementById("txtScore"), '银子不能为空!')){
		return false;
	}
	if (CheckNull(document.getElementById("txtAddNum"), '添加数量不能为空!')){
		return false;
	}
	if (CheckNull(document.getElementById("txtBatchNo"), '金额不能为空!')){
		return false;
	}
	form1.submit();
}
	</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<Div class="title">更新模块信息</Div>
	
    <form id="form1" name="form1" method="post" action="<%=request.getContextPath() %>/game/roomManage.do?action=updateRoom">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
        <tr>
          <td width="120" class="menutop">项目</td>
          <td class="menutop">内容</td>
        </tr>
        <tr>
          <td class="tdright_new">模块标识：</td>
          <td class="tdlefts"><label><input name="roomName" id="roomName" value="${dto.roomName}" type="text" maxlength="10" class="input_width2" /></label></td>
        </tr>
        <tr>
          <td class="tdright_new">模块名称：</td>
          <td class="tdlefts"><label>
            <input name="androidUserScore" id="androidUserScore" onKeyUp="isNumber(this)" type="text" value="${roomdto.androidUserScore}" maxlength="10" class="input_width2" />
          </label></td>
        </tr>
        <tr>
          <td class="tdright_new">数据库名：</td>
          <td class="tdlefts"><input name="maximum" onKeyUp="isNumber(this)" id="maximum" maxlength="14" type="text" value="${roomdto.maximum}" class="input_width2" /></td>
        </tr>
        <tr>
          <td class="tdright_new">数据库地址：</td>
          <td class="tdlefts"><input name="tax" onKeyUp="isNumber(this)" id="maximum" maxlength="14" type="text" value="${roomdto.tax}" class="input_width2" /></td>
        </tr>
        <tr>
          <td class="tdright_new">服务端版本：</td>
          <td class="tdlefts"><input name="tax2" onkeyup="isNumber(this)" id="tax" maxlength="14" type="text" value="${roomdto.tax}" class="input_width2" /></td>
        </tr>
        <tr>
          <td class="tdright_new">客户端版本：</td>
          <td class="tdlefts"><input name="tax3" onkeyup="isNumber(this)" id="tax2" maxlength="14" type="text" value="${roomdto.tax}" class="input_width2" /></td>
        </tr>
        <tr>
          <td class="tdright_new">服务端名称：</td>
          <td class="tdlefts"><input name="tax4" onkeyup="isNumber(this)" id="tax3" maxlength="14" type="text" value="${roomdto.tax}" class="input_width2" /></td>
        </tr>
        <tr>
          <td class="tdright_new">客户端名称：</td>
          <td class="tdlefts"><input name="tax5" onkeyup="isNumber(this)" id="tax4" maxlength="14" type="text" value="${roomdto.tax}" class="input_width2" /></td>
        </tr>
        <tr>
          <td class="tdright_new">支持类型：</td>
          <td class="tdlefts"><label>
            <input type="checkbox" name="checkbox" value="checkbox" />
          银子类型
          <input type="checkbox" name="checkbox2" value="checkbox" />
点值类型
<input type="checkbox" name="checkbox3" value="checkbox" />
比赛类型
<input type="checkbox" name="checkbox4" value="checkbox" />
训练类型</label></td>
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
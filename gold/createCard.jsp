<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
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
 function GetCardInfo(obj)
        {
            document.myFormInfo.in_CardGold.value = obj.split(',')[1];
            document.myFormInfo.in_MemberDays.value = obj.split(',')[2];
            document.myFormInfo.in_CardPrice.value = obj.split(',')[3]
        }
        
        function CheckFormInfo()
        {
            var count = document.myFormInfo.in_Count.value.trim();
            var cardGold = document.myFormInfo.in_CardGold.value.trim();
            var memberDays = document.myFormInfo.in_MemberDays.value.trim();
            var cardPrefix = document.myFormInfo.in_CardPrefix.value.trim();
            var cardLength = document.myFormInfo.in_CardLength.value.trim();
            var pwdLength= document.myFormInfo.in_PwdLength.value.trim();
            var validDate = document.myFormInfo.in_ValidDate.value.trim();
            if(count==""||IsPositiveInt(count)==false)
            {
                alert("生成数量不能为空或非数字字符或者超过允许大小！");
                return false;
            }   
            else
            {
                if(count==0)
                {
                    alert("生成数量必须大于0！");
                    return false;
                }
                else if(count>5000)
                {
                    alert("生成数量不可以超过5000！");
                    return false;
                }
            }         
            if(cardGold==""||IsPositiveInt(cardGold)==false)
            {
                alert("赠送银子不能为空或非数字字符或者超过允许大小！");
                return false;
            }
            if(memberDays==""||IsPositiveInt(memberDays)==false)
            {
                alert("会员天数不能为空或非数字字符或者超过允许大小！");
                return false;
            }  
            if(validDate!=""&&!IsPositiveInt(validDate))
            {
                alert("有效天数输入非法或者超过允许大小！");
                return false;
            }       
            if(cardLength==""||IsPositiveInt(cardLength)==false)
            {
                alert("卡号长度不能为空或非数字字符或者超过允许大小！");
                return false;
            }
            else
            {
                if(cardLength<15)
                {
                    alert("卡号长度长度必须大于等于15小于32！");
                    return false;
                }
                if(cardLength>31)
                {
                    alert("卡号长度长度必须大于等于15小于32！");
                    return false;
                }
            }
            if(cardPrefix.length>1)
            {
                alert("开始字符只能是一个！");
                return false;
            }
            if(pwdLength==""||IsPositiveInt(pwdLength)==false)
            {
                alert("密码长度不能为空或非数字字符！");
                return false;
            }
            else
            {
                if(pwdLength<8)
                {
                    alert("密码长度必须大于等于8！");
                    return false;
                }
                if(pwdLength>32)
                {
                    alert("密码长度必须大于等于8小于33！");
                    return false;
                }                
            }
        }
	</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<Div class="title">生成点卡</Div>
	
    <form id="myFormInfo" name="myFormInfo" method="post" action="<%=request.getContextPath() %>/gold/cardList.do?action=create">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
        <tr>
          <td width="120" class="menutop">项目</td>
          <td class="menutop">内容</td>
        </tr>
        <tr>
          <td class="tdright_new">会员卡类别：</td>
          <td class="tdlefts"><label>
            <select name="in_CardTypeID" id="in_CardTypeID" class="input_width2" onchange="GetCardInfo(this.value)">
			<c:forEach var="type" items="${listtype}">
              <option value="${type.cardTypeID},${type.cardGold},${type.memberDays},${type.cardPrice}">${type.cardName}</option>
			</c:forEach>
          </select>
          </label></td>
        </tr>
        <tr>
          <td class="tdright_new">生成数量：</td>
          <td class="tdlefts"><label>
            <input name="in_Count" id="in_Count" onKeyUp="isNumber(this)" type="text" maxlength="10" class="input_width2" />
          </label></td>
        </tr>
        <tr>
          <td class="tdright_new">赠送银子：</td>
          <td class="tdlefts"><input name="in_CardGold" onKeyUp="isNumber(this)" value="${cardGold}" id="in_CardGold" maxlength="14" type="text" class="input_width2" />
		  <input name="in_CardPrice" id="in_CardPrice" type="hidden" value="${cardPrice}" /> </td>
        </tr>
        <tr>
          <td class="tdright_new">会员天数：</td>
          <td class="tdlefts"><input name="in_MemberDays" onKeyUp="isNumber(this)" id="in_MemberDays" value="${memberDays}" maxlength="14" type="text" class="input_width2" /></td>
        </tr>
        <tr>
          <td class="tdright_new">使用范围：</td>
          <td class="tdlefts"><select name="in_UseRange" style="width:157px;">
	                <option value="0">全部用户</option>
	                <option value="1">新注册用户</option>
	                <option value="2">第一次充值用户</option>
	                </select></td>
        </tr>
        <tr>
          <td class="tdright_new">销售商：</td>
          <td class="tdlefts"><input name="in_SalesPerson" id="in_SalesPerson" maxlength="14" type="text" class="input_width2" /></td>
        </tr>
        <tr>
          <td class="tdright_new">有效天数：</td>
          <td class="tdlefts"><input name="in_ValidDate" onkeyup="isNumber(this)" id="in_ValidDate" maxlength="14" type="text" class="input_width2" /></td>
        </tr>
        <tr>
          <td class="tdright_new">备注：</td>
          <td class="tdlefts"><input name="in_NoteInfo" width="500px" id="in_NoteInfo" value="管理员【${AdminName}】 于 ${date} 生成" maxlength="14" type="text" /></td>
        </tr>
        <tr>
          <td class="menutop">卡号规则</td>
          <td class="tdlefts">&nbsp;</td>
        </tr>
        <tr>
          <td class="tdright_new">开始字符：</td>
          <td class="tdlefts"><input name="in_CardPrefix" onkeyup="isNumber(this)" id="in_CardPrefix" value="P" maxlength="14" type="text" class="input_width2" />开始字符可为空</td>
        </tr>
		 <tr>
          <td class="tdright_new">卡号长度：</td>
          <td class="tdlefts"><input name="in_CardLength" onkeyup="isNumber(this)" id="in_CardLength" value="15" maxlength="14" type="text" class="input_width2" />卡号长度必须大于等于15小于32</td>
        </tr>
		<tr>
          <td class="menutop">卡密规则</td>
          <td class="tdlefts">&nbsp;</td>
        </tr>
        <tr>
          <td class="tdright_new">密码字符类型：</td>
          <td class="tdlefts"><input type="checkbox" id="in_PasswdType1" value="1" name="in_PasswdType" checked="checked"/>数字<input type="checkbox" id="in_PasswdType2" value="2" name="in_PasswdType" />含小写字母<input type="checkbox" id="in_PasswdType3" value="4" name="in_PasswdType" />含大写字母</td>
        </tr>
		 <tr>
          <td class="tdright_new">密码长度：</td>
          <td class="tdlefts"><input name="in_PwdLength" onkeyup="isNumber(this)" id="in_PwdLength" value="8" maxlength="14" type="text" class="input_width2" />密码长度必须大于等于8小于33</td>
        </tr>
        <tr>
          <td class="tdright_new">&nbsp;</td>
          <td class="tdright"><label>
            <input name="Submit" type="submit" class="input" value="生成会员卡" />
            <input name="Submit2" type="reset" class="input" value="重新填写" />
          </label></td>
        </tr>
      </table>
</form>
</body>
</html>
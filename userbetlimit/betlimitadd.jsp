<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%
String msg="";
if(request.getAttribute("msg")!=null){
	msg=(String)request.getAttribute("msg");
}

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新增限红</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />

</head>
<script language="JavaScript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
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
function save(){
	if (CheckNull(document.forms['form1'].limit_Up, '上限不能为空!')){
		return false;
	}
	if (CheckNull(document.forms['form1'].limit_Down, '下限不能为空!')){
		return false;
	}
	var arr = new Array(5);
	for(var i =1 ;i<6;i++){
    var obj = document.getElementById("chipID"+i);
	var bjvalue = obj.value;
	if(bjvalue==""){
	   alert("请选择筹码!");
	   return false;
	}
	arr[i] = bjvalue;
	}
	var nary=arr.sort(); 
	for(var j=0;j<nary.length-1;j++) 
	{
	if (nary[j]==nary[j+1]) {
	    alert("重复内容："+nary[j]);
		return false;
		} 
	}
	form1.submit();

}

</script>
<body>
<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/userbetlimit/betLimitList.do?action=addLimit">
  <p>&nbsp;</p>
  <p></p>
  <table width="800" border="0" align="center" cellpadding="2" cellspacing="1" class="table_margin">
    <tr>
      <td height="22" colspan="2" class="title">限红管理：添加限红</td>
    </tr>
    <tr>
      <td class="menutop"><div align="right">上限：</div></td>
      <td class="tdlefts"><input name="limit_Up" type="text" class="input" onKeyUp="isNumber(this)"/>
必填</td>
    </tr>
    <tr>
      <td class="menutop"><div align="right">下限：</div></td>
      <td class="tdlefts"><input name="limit_Down" type="text" class="input" onKeyUp="isNumber(this)"/> 必填</td>
    </tr>
    <tr>
      <td class="menutop"><div align="right">筹码1：</div></td>
      <td class="tdlefts"><select name="chipID1" size="1" id="chipID1" class="Select">
        <c:forEach var="limitType" items="${limitType}">
          <option value="${limitType.chipValue}">${limitType.chipValue}</option>
        </c:forEach>
      </select>
      必选</td>
    </tr>
    
    <tr>
      <td class="menutop"><div align="right">筹码2：</div></td>
      <td class="tdlefts"><select name="chipID2" size="1" id="chipID2" class="Select">
        <c:forEach var="limitType" items="${limitType}">
          <option value="${limitType.chipValue}">${limitType.chipValue}</option>
        </c:forEach>
      </select>
      必选</td>
    </tr>
    <tr>
      <td class="menutop"><div align="right">筹码3：</div></td>
      <td class="tdlefts"><select name="chipID3" size="1" id="chipID3" class="Select">
        <c:forEach var="limitType" items="${limitType}">
          <option value="${limitType.chipValue}">${limitType.chipValue}</option>
        </c:forEach>
      </select>
      必选</td>
    </tr>
    <tr >
      <td class="menutop"><div align="right">筹码4：</div></td>
      <td class="tdlefts"><select name="chipID4" size="1" id="chipID4" class="Select">
        <c:forEach var="limitType" items="${limitType}">
          <option value="${limitType.chipValue}">${limitType.chipValue}</option>
        </c:forEach>
      </select>
      必选</td>
    </tr>
    <tr class="tdbg">
      <td class="menutop"><div align="right">筹码5：</div></td>
      <td class="tdlefts"><select name="chipID5" size="1" id="chipID5" class="Select">
        <c:forEach var="limitType" items="${limitType}">
          <option value="${limitType.chipValue}">${limitType.chipValue}</option>
        </c:forEach>
      </select>
      必选</td>
    </tr>
  </table>
  <table width="800" border="0" align="center" cellpadding="0" cellspacing="0" class="table_margin">
    <tr align="center">
      <td class="menutop"><input type="button" name="button1" value="保存" onClick="return save()"/> 　
        <input type="reset" name="button2" value="重置" />
&nbsp;</td>
    </tr>
  </table>
</form>
</body>
</html>

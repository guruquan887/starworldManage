<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%
int typeID = 0;
	if(request.getParameter("typeID")!=null){
		typeID = Integer.parseInt(request.getParameter("typeID"));
	}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>输赢报表</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
function GetDateStr(AddDayCount) {
    var dd = new Date();
    dd.setDate(dd.getDate()+AddDayCount);//获取AddDayCount天后的日期
    var y = dd.getFullYear();
    var m = dd.getMonth()+1;//获取当前月份的日期
    var d = dd.getDate();
    return y+"-"+m+"-"+d;
}
</script>
</head>
<body>
<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/baob/bbtj.do?action=search">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tables">
    <tr>
      <td height="25" class="title">公司报表</td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:5px;">
    
    <tr>
      <td class="menutop">项目</td>
      <td class="menutop">内容</td>
    </tr>
<%--    <tr>
      <td width="20%" class="tdleftall">类　　型：</td>
      <td width="80%" class="tdright"><label>
        <select name="gameType">
		<option value="0">对战游戏</option>
		<option value="1">庄对游戏</option>
        </select>
</label></td>
    </tr>--%>
    <tr>
      <td class="menutop">查询种类：</td>
      <td class="tdright"><label>
      <select name="typeName">
	  <option value="brg" <c:if test="<%=typeID==0%>" var="true"> selected="selected"</c:if>>百人馆</option>
	  <option value="jjg" <c:if test="<%=typeID==1%>" var="true"> selected="selected"</c:if>>竞技馆</option>
      </select>
      </label></td>
    </tr>
    <tr>
      <td class="menutop">时间区间：</td>
      <td class="tdright">
        <input id="startTime" name="startTime" type="text" value="" onClick="WdatePicker()" size="10" readonly="readonly"/>&nbsp;12:00:00
        到
       <input id="endTime" name="endTime" value="" type="text" onClick="WdatePicker()" size="10" readonly="readonly"/>&nbsp;11:59:59
       <input name="checkTime" id="checkTime" type="radio" value="today"/>
       今日
	  <input name="checkTime" id="checkTime" type="radio" value="yestoday" />
	  昨日
	  <input name="checkTime" id="checkTime" type="radio"  value="cweek" />
	  本周
	  <input name="checkTime" id="checkTime" type="radio"  value="bweek"/>
	  上周
	  <input name="checkTime" id="checkTime" type="radio"  value="cmonth"/>
	  本月
	  <input name="checkTime" id="checkTime" type="radio" value="bmonth" />
	  上月
	  <input name="checkTime" id="checkTime" type="radio"  value="all" />
	  全部</td>
    </tr>
    <tr>
      <td class="menutop">用户名称：</td>
      <td class="tdright"><label>
        <input type="text" name="termOne" id="termOne" />
      </label>
      <select name="selectOne" class="input_width" id="selectOne">
	      <option value="accounts">用户名</option>
	      <option value="gameID">游戏ID</option>
        </select>可不输，不输为查询全部</td>
    </tr>
    <tr>
      <td class="menutop">&nbsp;</td>
      <td class="tdright"><label>
        <input type="submit" class="input" name="Submit" value="提交" />
        &nbsp;
        <input type="reset" class="input" name="Submit2" value="重置" />
      </label></td>
    </tr>
  </table>
</form>
</body>
<script language="javascript" type="text/javascript">

	document.getElementById("startTime").value=GetDateStr(-1);
	document.getElementById("endTime").value=GetDateStr(0);
    //return currentTime;
</script>
</html>

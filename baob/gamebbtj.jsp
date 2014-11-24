<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>输赢报表</title>
<link href="../css/user.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/baob/gameUserBBTJ.do?action=gameUserBBTJ">
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tables">
    <tr>
      <td height="25" class="th">输赢报表</td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:5px;">
    
    <tr>
      <td class="th">项目</td>
      <td class="th">内容</td>
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
      <td class="tdleftall">查询种类：</td>
      <td class="tdright"><label>
      <input type="radio" id="checkTime" name="checkTime" value="day" checked="checked" />
      当日报表
      <input type="radio" id="checkTime" name="checkTime" value="all" />
      历史报表</label></td>
    </tr>
    <tr>
      <td class="tdleftall">时间区间：</td>
      <td class="tdright">
        <input id="startTime" name="startTime" type="text" value="" onClick="WdatePicker()" size="10" readonly="readonly"/>&nbsp;00:00:00
        到
        <input id="endTime" name="endTime" value="" type="text" onClick="WdatePicker()" size="10" readonly="readonly"/>&nbsp;00:00:00
        </td>
    </tr>
<!--    <tr>
      <td class="tdleftall">报表种类：</td>
      <td class="tdright"><select name="select2">
      </select></td>
    </tr>
    <tr>
      <td class="tdleftall">投注方式：</td>
      <td class="tdright"><select name="select3">
      </select></td>
    </tr>-->
    <tr>
      <td class="tdleftall">用户名称：</td>
      <td class="tdright"><label>
        <input type="text" name="termOne" id="termOne" />
      </label>
      <select name="selectOne" class="input_width" id="selectOne">
	      <option value="accounts">用户名</option>
	      <option value="gameID">游戏ID</option>
        </select>可不输，不输为查询全部</td>
    </tr>
    <tr>
      <td class="tdleftall">&nbsp;</td>
      <td class="tdright"><label>
        <input type="submit" name="Submit" value="提交" />
        &nbsp;
        <input type="reset" name="Submit2" value="重置" />
      </label></td>
    </tr>
  </table>
</form>
</body>

</html>

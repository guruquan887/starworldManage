<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<body>
	<Div class="title">银子监控</Div>
	
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
	<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/system/dateCount.do?method=usercount">
      <tr>
        <td height="28" colspan="10" class="menutop"><div align="left">开始时间
          <label>
          <input size="8" title="开始时间" id="startTime" type="text" onClick="WdatePicker()"  name="startTime" value="${startTime}" readonly="readonly"/>
          </label>
        结束时间
        <label>
        <input size="8" title="结束时间" id="endTime" type="text" onClick="WdatePicker()"  name="endTime" value="${endTime}" readonly="readonly"/>
        <input type="submit" name="Submit" value="查询" /></label>
        </div></td>
      </tr>
	  </form>
      <tr>
        <td height="28" class="menutop">名称</td>
        <td class="menutop">系统</td>
        <td class="menutop">存入</td>
        <td class="menutop">取出</td>
        <td class="menutop">充值</td>
<%--        <td class="menutop">赠送</td>
        <td class="menutop">扣税</td>--%>
        <td class="menutop">机器人输赢</td>
        <td class="menutop">机器人当前</td>
        <td class="menutop">用户当前</td>
      </tr>
      <tr>
        <td class="tdcenter">公司</td>
        <td class="tdcenter">${countuser.totalQuScore-countuser.totalCunScore-countuser.totalCardGold+countuser.totalWinLostScore-countuser.totalUserScore}</td>
        <td class="tdcenter">${countuser.totalCunScore}</td>
        <td class="tdcenter">${countuser.totalQuScore}</td>
        <td class="tdcenter">${countuser.totalCardGold}</td>
<%--        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>--%>
        <td class="tdcenter">${countuser.totalWinLostScore}</td>
        <td class="tdcenter">${countuser.totalAndroidScore}</td>
        <td class="tdcenter">${countuser.totalUserScore}</td>
      </tr>
    </table>
    <h1>&nbsp;</h1>
</body>
</html>
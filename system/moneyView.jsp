<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=30;
	String orderby="RegisterDate";
	if(request.getParameter("orderby")!=null){
		orderby=request.getParameter("orderby");
	}
	try{
		if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("pageIndex")!=null){
			pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
		}
		}catch(Exception e){
			e.printStackTrace();
	}
	recordIndex=(pageIndex-1)*pageSize+1;
	String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}

	String startTime="";
	if(request.getParameter("startTime")!=null){
		startTime=request.getParameter("startTime");
	}
	String endTime="";
	if(request.getParameter("endTime")!=null){
		endTime=request.getParameter("endTime");
	}
	int proxyID = 0;
	if(request.getParameter("userID")!=null){
		proxyID=Integer.parseInt(request.getParameter("userID"));
	}
	
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="title" style="margin:20px 0 0;">系统统计</Div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
      <tr>
        <td height="28" class="menutop">系统</td>
        <td class="menutop">充值（元）</td>
        <td class="menutop">损耗（赠送）</td>
        <td class="menutop">系统输赢</td>
        <td class="menutop">佣金收入</td>
        <td class="menutop">税收收入</td>
        <td class="menutop">新增代理</td>
        <td class="menutop">新增会员</td>
        <td class="menutop">游戏总银子</td>
        <td class="menutop">钱庄总银子</td>
      </tr>
      <tr>
        <td class="tdcenter">今日</td>
        <td class="tdcenter">${countuser[1]}</td>
        <td class="tdcenter">${countuser[0]}</td>
        <td class="tdcenter">${countuser[2]}</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">${countuser[3]}</td>
        <td class="tdcenter">${countuser[4]}</td>
        <td class="tdcenter">${countuser[5]}</td>
        <td class="tdcenter">${countuser[6]}</td>
      </tr>
      <tr>
        <td class="tdcenter">${lastDay}</td>
        <td class="tdcenter">${countuser1[1]}</td>
        <td class="tdcenter">${countuser1[0]}</td>
        <td class="tdcenter">${countuser1[2]}</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">${countuser1[3]}</td>
        <td class="tdcenter">${countuser1[4]}</td>
        <td class="tdcenter">${countuser1[5]}</td>
        <td class="tdcenter">${countuser1[6]}</td>
      </tr>
      <tr>
        <td class="tdcenter">${lastDay1}</td>
        <td class="tdcenter">${countuser2[1]}</td>
        <td class="tdcenter">${countuser2[0]}</td>
        <td class="tdcenter">${countuser2[2]}</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">${countuser2[3]}</td>
        <td class="tdcenter">${countuser2[4]}</td>
        <td class="tdcenter">${countuser2[5]}</td>
        <td class="tdcenter">${countuser2[6]}</td>
      </tr>
      <tr>
        <td class="tdcenter">${lastDay2}</td>
        <td class="tdcenter">${countuser3[1]}</td>
        <td class="tdcenter">${countuser3[0]}</td>
        <td class="tdcenter">${countuser3[2]}</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">${countuser3[3]}</td>
        <td class="tdcenter">${countuser3[4]}</td>
        <td class="tdcenter">${countuser3[5]}</td>
        <td class="tdcenter">${countuser3[6]}</td>
      </tr>
      <tr>
        <td class="tdcenter">${lastDay3}</td>
        <td class="tdcenter">${countuser4[1]}</td>
        <td class="tdcenter">${countuser4[0]}</td>
        <td class="tdcenter">${countuser4[2]}</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">${countuser4[3]}</td>
        <td class="tdcenter">${countuser4[4]}</td>
        <td class="tdcenter">${countuser4[5]}</td>
        <td class="tdcenter">${countuser4[6]}</td>
      </tr>
      <tr style="font-weight:bold;">
        <td class="tdcenter">总计</td>
        <td class="tdcenter">${countTotal1}</td>
        <td class="tdcenter">${countTotal0}</td>
        <td class="tdcenter">${countTotal2}</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">${countTotal3}</td>
        <td class="tdcenter">${countTotal4}</td>
        <td class="tdcenter"><%--${countTotal5}--%></td>
        <td class="tdcenter"><%--${countTotal6}--%></td>
      </tr>
    </table>
    <div class="title" style="margin:20px 0 0;">百人游戏</Div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
      <tr>
        <td height="28" class="menutop">百人馆</td>
        <td class="menutop">百家乐</td>
        <td class="menutop">百人二张</td>
        <td class="menutop">百人牛牛</td>
      </tr>
      <tr>
        <td class="tdcenter">今日</td>
        <td class="tdcenter">${countuser[9]}</td>
        <td class="tdcenter">${countuser[8]}</td>
        <td class="tdcenter">${countuser[7]}</td>
      </tr>
      <tr>
        <td class="tdcenter">${lastDay}</td>
        <td class="tdcenter">${countuser1[9]}</td>
        <td class="tdcenter">${countuser1[8]}</td>
        <td class="tdcenter">${countuser1[7]}</td>
      </tr>
	    <tr>
        <td class="tdcenter">${lastDay1}</td>
        <td class="tdcenter">${countuser2[9]}</td>
        <td class="tdcenter">${countuser2[8]}</td>
        <td class="tdcenter">${countuser2[7]}</td>
      </tr>
      <tr>
        <td class="tdcenter">${lastDay2}</td>
        <td class="tdcenter">${countuser3[9]}</td>
        <td class="tdcenter">${countuser3[8]}</td>
        <td class="tdcenter">${countuser3[7]}</td>
      </tr>
	    <tr>
        <td class="tdcenter">${lastDay3}</td>
        <td class="tdcenter">${countuser4[9]}</td>
        <td class="tdcenter">${countuser4[8]}</td>
        <td class="tdcenter">${countuser4[7]}</td>
      </tr>
      <tr style="font-weight:bold;">
        <td class="tdcenter">总计</td>
        <td class="tdcenter">${countTotal9}</td>
        <td class="tdcenter">${countTotal8}</td>
        <td class="tdcenter">${countTotal7}</td>
      </tr>
    </table>
    
    <div class="title" style="margin:20px 0 0;">竞技游戏</Div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
      <tr>
        <td height="28" class="menutop">竞技馆</td>
        <td class="menutop">德州</td>
        <td class="menutop">梭哈</td>
        <td class="menutop">牛牛</td>
        <td class="menutop">双扣</td>
        <td class="menutop">斗地主</td>
      </tr>
      <tr>
        <td class="tdcenter">今日</td>
        <td class="tdcenter">${countuser[10]}</td>
        <td class="tdcenter">${countuser[11]}</td>
        <td class="tdcenter">${countuser[12]}</td>
        <td class="tdcenter">${countuser[13]}</td>
        <td class="tdcenter">${countuser[14]}</td>
      </tr>
      <tr>
        <td class="tdcenter">${lastDay}</td>
        <td class="tdcenter">${countuser1[10]}</td>
        <td class="tdcenter">${countuser1[11]}</td>
        <td class="tdcenter">${countuser1[12]}</td>
        <td class="tdcenter">${countuser1[13]}</td>
        <td class="tdcenter">${countuser1[14]}</td>
      </tr>
	   <tr>
        <td class="tdcenter">${lastDay1}</td>
        <td class="tdcenter">${countuser2[10]}</td>
        <td class="tdcenter">${countuser2[11]}</td>
        <td class="tdcenter">${countuser2[12]}</td>
        <td class="tdcenter">${countuser2[13]}</td>
        <td class="tdcenter">${countuser2[14]}</td>
      </tr>
	   <tr>
        <td class="tdcenter">${lastDay2}</td>
        <td class="tdcenter">${countuser3[10]}</td>
        <td class="tdcenter">${countuser3[11]}</td>
        <td class="tdcenter">${countuser3[12]}</td>
        <td class="tdcenter">${countuser3[13]}</td>
        <td class="tdcenter">${countuser3[14]}</td>
      </tr>
	   <tr>
        <td class="tdcenter">${lastDay3}</td>
        <td class="tdcenter">${countuser4[10]}</td>
        <td class="tdcenter">${countuser4[11]}</td>
        <td class="tdcenter">${countuser4[12]}</td>
        <td class="tdcenter">${countuser4[13]}</td>
        <td class="tdcenter">${countuser4[14]}</td>
      </tr>
      <tr style="font-weight:bold;">
        <td class="tdcenter">总计</td>
        <td class="tdcenter">${countTotal10}</td>
        <td class="tdcenter">${countTotal11}</td>
        <td class="tdcenter">${countTotal12}</td>
        <td class="tdcenter">${countTotal13}</td>
        <td class="tdcenter">${countTotal14}</td>
      </tr>
    </table>
    
    <div class="title" style="margin:20px 0 0;">彩票游戏</Div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
      <tr>
        <td height="28" class="menutop">彩票馆</td>
        <td class="menutop">北京快乐吧</td>
        <td class="menutop">双色球</td>
        <td class="menutop">深圳快乐吧</td>
        <td class="menutop">上海时时彩</td>
        <td class="menutop">重庆时时彩</td>
      </tr>
      <tr>
         <td class="tdcenter">今日</td>
         <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>

      </tr>
      <tr>
         <td class="tdcenter">${lastDay}</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>

      </tr>
	   <tr>
          <td class="tdcenter">${lastDay1}</td>
          <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
      </tr>
	   <tr>
           <td class="tdcenter">${lastDay2}</td>
           <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
      </tr>
	   <tr>
        <td class="tdcenter">${lastDay3}</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>

      </tr>
      <tr style="font-weight:bold;">
        <td class="tdcenter">总计</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>
        <td class="tdcenter">0</td>

      </tr>
    </table>
</body>
</html>
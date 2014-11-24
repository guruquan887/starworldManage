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
<body>
	<Div class="title">已受理的订单—处理兑换</Div>
		<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/integral/dhList.do?action=updateDhRecord">
		  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
            <tr>
              <td width="120" class="menutop">项目</td>
              <td class="menutop">内容</td>
            </tr>
			<tr>
              <td class="tdright_new">兑换用户：</td>
              <td class="tdlefts">${dto.accounts}</td>
            </tr>
            <tr>
              <td class="tdright_new">兑换物品类别：</td>
              <td class="tdlefts">${dto.typeName}<input name="express_ID" type="hidden" value="${dto.express_ID}" /><input name="userID" type="hidden" value="${dto.userID}" /></td>
            </tr>
            <tr>
              <td class="tdright_new">兑换物品名称：</td>
              <td class="tdlefts">${dto.mallName}</td>
            </tr>
            <tr>
              <td class="tdright_new">物品总数目：</td>
              <td class="tdlefts">${dto.dhCount}</td>
            </tr>
			<tr>
              <td class="tdright_new">兑换银两：</td>
              <td class="tdlefts">${dto.dhAmount}</td>
            </tr>
			<tr>
              <td class="tdright_new">姓名：</td>
              <td class="tdlefts">${dto.realName}</td>
            </tr>
			<tr>
              <td class="tdright_new">电话：</td>
              <td class="tdlefts">${dto.telphone}</td>
            </tr>
			<tr>
              <td class="tdright_new">地址：</td>
              <td class="tdlefts">${dto.address}</td>
            </tr>
			<tr>
              <td class="tdright_new">邮编：</td>
              <td class="tdlefts">${dto.zip}</td>
            </tr>
            <tr>
              <td class="tdright_new">备注：</td>
              <td class="tdlefts"><label>
                <textarea name="textarea" cols="40" rows="5">${dto.beizhu}</textarea>
              </label></td>
            </tr>
            <tr>
              <td class="tdright_new">&nbsp;</td>
              <td class="tdright"><label>
                <input name="Submit" type="submit" class="input" value="确定发货" />
              </label></td>
            </tr>
          </table>
</form>
</body>
</html>
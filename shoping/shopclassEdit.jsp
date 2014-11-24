<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<Div class="title">商品分类—修改</Div>
		<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/shoping/gameshopList.do?action=updateMallType">
		  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
            <tr>
              <td width="120" class="menutop">项目</td>
              <td class="menutop">内容</td>
            </tr>
            <tr onmouseover="this.className='trover'" onmouseout="this.className='trout'">
              <td class="tdright_new">分类名称：</td>
              <td class="tdlefts"><label>
                <input type="text" name="typeName" value="${dto.typeName}"/><input name="typeID" type="hidden" value="${dto.typeID}" />
              </label></td>
            </tr>
            <tr onmouseover="this.className='trover'" onmouseout="this.className='trout'">
              <td class="tdright_new">&nbsp;</td>
              <td class="tdlefts"><label>
                <input name="Submit" type="submit" class="input" value="确定修改" />
                <input name="Submit2" type="reset" class="input" value="重新填写" />
              </label></td>
            </tr>
          </table>
</form>
</body>
</html>
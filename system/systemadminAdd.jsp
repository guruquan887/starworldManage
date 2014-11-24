<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="<%=request.getContextPath()%>/js/userweb.js"></script>
</head>
<body>
	<Div class="title"><span>账户管理—新增</Div>
    <form id="form1" name="form1" method="post" onsubmit="return save()" action="<%=request.getContextPath()%>/system/adminList.do?action=add">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
        <tr>
          <td width="120" class="menutop">项目</td>
          <td class="menutop">内容</td>
        </tr>
        <tr>
          <td class="tdright_new">角色：</td>
          <td class="tdright">
		         <select name="roleId" id="roleId" size="1" class="Select">
        <c:forEach var="r" items="${ROL}">
				<option value="${r.id}">
					${r.roleName}
				</option>	
			</c:forEach>
      </select>
		  </td>
        </tr>
        <tr>
          <td class="tdright_new">用户名：</td>
          <td class="tdright"><input name="username" type="text" class="input_width2" /></td>
        </tr>
        <tr>
          <td class="tdright_new">密码：</td>
          <td class="tdright"><input name="password" type="password" class="input_width2" /></td>
        </tr>
        <tr>
          <td class="tdright_new">确认密码：</td>
          <td class="tdright"><label>
            <input name="password1" type="password" class="input_width2" />
          </label></td>
        </tr>
        <tr>
          <td class="tdright_new">&nbsp;</td>
          <td class="tdright"><label>
            <input name="Submit2" type="submit" class="input" value="确定增加" />
          </label></td>
        </tr>
      </table>
</form>
</body>
</html>
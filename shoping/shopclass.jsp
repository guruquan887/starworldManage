<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %> 
<%
String typeName = "";
if(request.getParameter("typeName")!=null){
   typeName = request.getParameter("typeName");
   typeName = new String(typeName.getBytes("ISO_8859_1"),"UTF-8");
}

%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<script language="javascript">
function check(typeID){
   var typeName = document.getElementById("typeName").value;
 typeName = encodeURI(typeName);
 document.location.href="<%=request.getContextPath()%>/shoping/gameshopList.do?action=mallType&parentsID="+typeID+"&typeName="+typeName;
}

</script>
<body>
	<Div class="title"><span>
	  <label>
	  <input name="Submit" type="button" class="input" value="新增类别" onclick="window.location.href='shopclassAdd.html'" />
	  </label>
	</span>商品分类</Div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td width="60" class="menutop">序号</td>
            <td class="menutop">类别名称</td>
            
            <td class="menutop">操作</td>
          </tr>
		  <%int i=1;%>
		  <c:forEach var="typeList" items="${list}">
		  
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter"><%=i%></td>
            <td class="tdcenter"><!--<a href="#" onclick="check(${typeList.typeID})">--><input name="typeName" id="typeName" type="hidden" value="${typeList.typeName}" />${typeList.typeName}</td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/shoping/gameshopList.do?action=preUpdateMallType&typeID=${typeList.typeID}">修改</a> / <a href="#">删除</a> </td>
          </tr>
		  <% i++;%>
		  </c:forEach>
        </table>
</body>
</html>
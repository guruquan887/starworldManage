<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
String msg="";
if(request.getAttribute("msg")!=null){
	msg=(String)request.getAttribute("msg");
}
%>
<head>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<script language="javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
</script>
<body>
<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/system/systemset.do?action=update">
  <table cellpadding="2" cellspacing="1" border="0" width="100%" align="center">
    <tr>
      <td height="25" colspan="4" class="menutop" style="text-align:center;">系统设置</td>
    </tr>
    <tr	align="center">
      <td height="22" class="tdleft">网站标题：</td>
      <td class="tdright"><input name="wwwName" type="text" size="50" value="${dto.wwwName}">&nbsp;</td>
    </tr>
    <tr	align="center">
      <td width="20%" height="22" class="tdleft">后台标题：</td>
      <td width="80%" class="tdright"><input name="siteName" size="50" type="text" value="${dto.siteName}"></td>
    </tr>
    <tr	align="center">
      <td height="22" class="tdleft">版权名称：</td>
      <td class="tdright"><input name="copyRight" id="copyRight" size="50" value="${dto.copyRight}" type="text"></td>
    </tr>
	    <tr	align="center">
      <td height="22" class="tdleft">网站公告：</td>
      <td class="tdright"><input name="adsMsg" id="adsMsg" size="50" value="${dto.adsMsg}" type="text"></td>
    </tr>   
	 <tr align="center">
      <td height="22" class="tdleft">大厅公告：</td>
      <td class="tdright"><input name="gameMsg" id="gameMsg" size="50" value="${dto.gameMsg}" type="text"></td>
    </tr>
	
    <tr	align="center">
      <td height="22" class="tdleft">&nbsp;</td>
      <td class="tdright"><label>
        <input type="submit" class="input" name="Submit" value="提交"/>
     &nbsp; 
     <input type="reset" class="input" name="Submit2" value="重置"/>
	 <input type="hidden" name="siteID" value="1" />
      </label></td>
    </tr>
  </table>
</form>
</body>
</html>
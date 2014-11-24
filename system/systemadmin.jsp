<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
String msg="";
if(request.getAttribute("msg")!=null){
	msg=(String)request.getAttribute("msg");
}
String action="";
if(request.getParameter("action")!=null){
	action=request.getParameter("action");
}
int roleId = 0;
if(request.getSession().getAttribute("roleId")!=null){
	roleId = (Integer)(request.getSession().getAttribute("roleId"));
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<head>
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<Div class="title"><span>  
	  <form id="form1" name="form1" method="post" action="">
	    <c:if test="${roleId==1}" var="true"><input name="Submit" type="button" class="input" value="新增" onclick="window.location.href='<%=request.getContextPath()%>/system/adminList.do?action=preAdd'" /></c:if>
      </form>
	</span>账户管理</Div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td width="30" class="menutop">序号</td>
            <td class="menutop">用户名</td>
            <td class="menutop">级别</td>
            <td class="menutop">登录IP</td>
            <td class="menutop">最后登录时间</td>
            <td class="menutop">绑定机器</td>
            <td class="menutop">操作</td>
          </tr>
		 <%int i=1; %>
		 <c:forEach var="r" items="${admin}">
          <tr onmouseover="this.className='trover'" onmouseout="this.className='trout'">
            <td class="tdcenter"><%=i++ %></td>
            <td class="tdcenter"><c:if test="${USER.roleId==1}" var="true"><a href="<%=request.getContextPath()%>/system/adminList.do?action=logList&operateName=${r.userName}">${r.userName}</a></c:if><c:if test="${USER.roleId!=1}" var="true">${r.userName}</c:if></td>
            <td class="tdcenter">${r.roleName}</td>
            <td class="tdcenter">&nbsp;${r.loginIP}</td>
            <td class="tdcenter">&nbsp;${r.loginTime}</td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/system/adminList.do?action=bundlingManMachine&id=${r.id}" >绑定</a>/<a href="<%=request.getContextPath()%>/system/adminList.do?action=qxbundlingManMachine&id=${r.id}" ><font color="#FF0000">取消</font></a></td>
            <td class="tdcenter">&nbsp;      	
			<c:if test="${USER.roleId==1}" var="true">	
	      		<a href="<%=request.getContextPath()%>/system/adminList.do?action=preUpdate&id=${r.id}" >修改密码/设置权限</a>&nbsp;
				<c:if test="${r.userName!='admin'}" var="true">	
				<a href="<%=request.getContextPath()%>/system/adminList.do?action=del&id=${r.id}" onClick="{if(confirm('删除后该管理员将不可进入后台！\n\n确定删除吗?')){return true;}return false;}">删除</a>				</c:if> 
			</c:if>		
	      		<c:if test="${USER.roleId!=1}" var="true">
				<c:if test="${r.roleId==roleId}" var="true">	
				<a href="javascript:;" style="cursor:pointer;" onClick="window.location.href='<%=request.getContextPath()%>/system/adminList.do?action=preUpdate&id=${r.id}'">修改密码</a>&nbsp;	      		</c:if></c:if>
				<%--<a href="systemadminEdit1.html">编辑</a>--%></td>
          </tr>
		</c:forEach>
        </table>
</body>
</html>
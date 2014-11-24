<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%
 String msg="";
 if(request.getAttribute("msg")!=null){
  msg=request.getAttribute("msg").toString();
 }

 String search="";
 if(request.getParameter("roomName")!=null){
 	search = request.getParameter("roomName");
	search = java.net.URLDecoder.decode(search,"UTF-8");
 }
 int pageIndex=1;
 int pageSize=30;
 if(request.getAttribute("pageSize")!=null){
	pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
	}	
 if(request.getAttribute("pageIndex")!=null){
	pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
 }
 int i=1;
 i=(pageIndex-1)*pageSize+1;
 
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<script language="javascript" type="text/javascript">
       //弹出友好提示
       var msg="<%=msg %>";
       if(msg!='')alert(msg);
</script>
<script src="../js/jquery.min.js" type="text/javascript"></script>
<script language="javascript" src="../js/ajax.js"></script>

<body>
<%-- <div align="right">
	    <input name="gamename" id="gamename" type="text" class="input_width2" value="请输入房间名称" />
        <input name="Submit2" type="button" class="input" value="搜索" onClick="search()"/>
	  </div>
--%>
	<Div class="title">游戏房间</Div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td width="60" class="menutop">序号</td>
            <td class="menutop">游戏房间</td>
            <td class="menutop">本局最大限额</td>
            <td class="menutop">单线最大限额</td>
            <td class="menutop">单注最大限额</td>
            <td class="menutop">单线最小限额</td>
            <td class="menutop">bouns最大限额</td>
            <td class="menutop">bouns最小限额</td>
			<td class="menutop">状态</td>
            <td class="menutop">操作</td>
          </tr>
		  <c:forEach var="dto" items="${rList}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter"><%=i%></td>
            <td class="tdcenter">${dto.gamename}</td>
            <td class="tdcenter">${dto.round_maxbet}</td>
            <td class="tdcenter">${dto.line_maxbet}</td>
            <td class="tdcenter">${dto.single_maxbet}</td>
            <td class="tdcenter">${dto.limit_minbet}</td>
            <td class="tdcenter">${dto.bonus_maxbet}</td>
            <td class="tdcenter">${dto.bonus_minbet}</td>
			<td class="tdcenter"><c:if test="${dto.islock==0}" var="true"><a href="<%=request.getContextPath()%>/game/dzroomManage.do?action=updateState&gametype=${dto.gametype}&islock=${dto.islock}" style="cursor:pointer;">启用</a></c:if><c:if test="${dto.islock>=1}" var="true"><a href="<%=request.getContextPath()%>/game/dzroomManage.do?action=updateState&gametype=${dto.gametype}&islock=${dto.islock}" style="cursor:pointer;"><font color="#FF0000">停用</font></a></c:if></td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/game/dzroomManage.do?action=preUpdate&gametype=${dto.gametype}" style="cursor:pointer;">修改</a></td>
          </tr>
		  <% i++;%>
		  </c:forEach>
        </table>
	
</body>
<script>
function addListener(element,e,fn){    
    	 		if(element.addEventListener){    
          			element.addEventListener(e,fn,false);    
     			 } else {    
         	 		element.attachEvent("on" + e,fn);    
     	 		 }    
			}
			var username = document.getElementById("gamename");
			addListener(username,"click",function(){
				username.value = "";
			})


</script>
</html>
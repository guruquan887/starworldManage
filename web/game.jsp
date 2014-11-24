<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<%
	int recordIndex = 1;
	int pageIndex = 1;
	int pageSize = 10;
	int gameType = 0;
	try {
		if (request.getAttribute("pageSize") != null) {
			pageSize = Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if (request.getAttribute("pageIndex") != null) {
			pageIndex = Integer.parseInt(request.getAttribute("pageIndex").toString());
		}
		if(request.getParameter("gameType")!=null){
			gameType=Integer.parseInt(request.getParameter("gameType"));
		}
	} catch (Exception e) {
		e.printStackTrace();
	}

	recordIndex=(pageIndex-1)*pageSize+1;
	String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<script language="javascript" type="text/javascript">
  var msg="<%=msg %>";
  if(msg!='')alert(msg);
  function jumppage(value)
    {
      window.location.href="<%=request.getContextPath()%>/web/gameList.do?action=gameList&curPage=" +value;
    }
     function changg()
    {
      window.location.href="<%=request.getContextPath()%>/web/gameList.do?action=gameList&gameType="+document.all.gameType.options[document.all.gameType.selectedIndex].value;
    }
   function delCheck(){
		var flag10=0;
         var radio10=document.getElementsByName("checkbox");
         for(var i=0;i<radio10.length;i++)
   {
     if(radio10.item(i).checked==true)
        {
     flag10=1;
           break;
   }
 }
  if(!flag10){
         alert("请选择删除对象");
         return false;
  }
   return true;
	}
	function closeDiv(id){
	var idStr = "div_"+id.substring(8,id.length);
	var objtable=document.getElementById(id); 
	objtable.parentNode.removeChild(objtable);
	document.getElementById(idStr).innerHTML=""; 
	//document.getElementById(id).style.display='none';
	}
</script>
<script language="javascript" src="<%=request.getContextPath()%>/js/ajax.js"></script>
<body>
<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/web/gameList.do?action=gameDel">
	<Div class="title">游戏管理</Div>
	<table width="100%" border="0" cellpadding="2" cellspacing="1"
	class="border">
	<tr>
	  <td class="tdlefts" height="25"><input type="button" name="Submit" class="input" value="增加" onclick="window.location.href='<%=request.getContextPath()%>/web/gameadd.jsp'" />
      <input type="submit" name="Submit2" class="input" value="删除" onClick="return delCheck()" />
	  <select name="gameType" id="gameType" class="Select" onchange="changg()" >
		<option value="0">棋牌游戏</option>
		<option value="1">电子游艺</option>
		<option value="2">彩票游戏</option>
        </select>
	  </td>
	  </tr>
	</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
		    <td class="menutop">&nbsp;</td>
            <td width="60" class="menutop">序号</td>
            <td class="menutop">游戏名称</td>
            <%--<td class="menutop">游戏简介</td>--%>
            <td class="menutop">游戏图片</td>
            <td class="menutop">游戏链接</td>
            <td class="menutop">游戏类型</td>
			<td class="menutop">状态</td>
            <td class="menutop">操作</td>
          </tr>
		  <c:forEach var="gamelist" items="${gamelist}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
		  <td class="tdcenter" height="25"><input name="checkbox" type="checkbox" id="checkbox" value="${gamelist.id}" /></td>
            <td class="tdcenter"><%=recordIndex++ %></td>
            <td class="tdcenter"><a href="javascript:;" style="cursor:pointer;"onClick="startRequest('div_${gamelist.id}','<%=request.getContextPath()%>/web/gameList.do?action=display_in&p_id=${gamelist.id}');">${gamelist.gameName}</a><div id="div_${gamelist.id}" style="display:inline;"></div></td>
            <%--<td class="tdcenter">${fn:substring(gamelist.gameDes, 0, 50)}</td>--%>
            <td class="tdcenter"><a href="javascript:;" style="cursor:pointer;"onClick="startRequest('div_${gamelist.id}','<%=request.getContextPath()%>/web/gameList.do?action=display_in_image&p_id=${gamelist.id}');"><img src="<%=request.getContextPath()%>/incoming/${gamelist.gamephoto2}" width="127" height="83" border="0"/></a></td>
            <td class="tdcenter"><a href="http://${gamelist.gameUrl}" target="_blank">http://${gamelist.gameUrl}</a></td>
            <td class="tdcenter"><c:if test="${gamelist.gameType==0}" var="true">棋牌游戏</c:if><c:if test="${gamelist.gameType==1}" var="true">电子游艺</c:if><c:if test="${gamelist.gameType==2}" var="true">彩票游戏</c:if></td>
			<td class="tdcenter"><c:if test="${gamelist.isRec==1}" var="true"><a href="<%=request.getContextPath()%>/web/gameList.do?action=isRec&id=${gamelist.id}&isRec=${gamelist.isRec}">已发布</a></c:if><c:if test="${gamelist.isRec==0}" var="true"><a href="<%=request.getContextPath()%>/web/gameList.do?action=isRec&id=${gamelist.id}&isRec=${gamelist.isRec}"><font color="#999999">未发布</font></a></c:if></td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/web/gameList.do?action=gamePreUpdate&id=${gamelist.id}">编辑</a>			</td>
          </tr>
		  </c:forEach>
        </table>
		<table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
	<tr class="border">
		<td colspan="4" class="tdlefts" align="left">总记录:${page.totalRecord}条/${page.totalPage}页
		<c:if test="${page.totalPage>0}"  var="true"> 
			<select name="select" onChange="jumppage(this.value);">
			  <c:forEach var="i" begin="1" end="${page.totalPage}" step="1"> <option value="${i}" 
			    <c:if test="${i==page.curPage}" var="true">selected="selected"</c:if>
		        >第${i}页
		        </option>
		      </c:forEach>
	    </select>
	    <a href="<%=request.getContextPath()%>/web/gameList.do?action=gameList&curPage=1">首页</a> 
        <a href="<%=request.getContextPath()%>/web/gameList.do?action=gameList&curPage=${page.curPage-1}">上页</a> 
        <a href="<%=request.getContextPath()%>/web/gameList.do?action=gameList&curPage=${page.curPage+1}">下页</a> 
        <a href="<%=request.getContextPath()%>/web/gameList.do?action=gameList&curPage=${page.totalPage}">末页</a>
		</c:if></td>
	</tr>
</table>
</form>
</body>
<script type="text/javascript">
var tcalc=document.getElementById("gameType");
var index="<%=gameType %>";
for(var i=0; i<tcalc.options.length; i++){
			if(tcalc.options[i].value==index){
				tcalc.options[i].selected=true;break;
			}
		}
</script>
</html>
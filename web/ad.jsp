<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
	int recordIndex = 1;
	int pageIndex = 1;
	int pageSize = 10;
	int state = 0;
	int adTypeId = 0;
	
	try {
		if (request.getAttribute("pageSize") != null) {
			pageSize = Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if (request.getAttribute("pageIndex") != null) {
			pageIndex = Integer.parseInt(request.getAttribute("pageIndex").toString());
		}
		if (request.getParameter("state") != null) {
			state = Integer.parseInt(request.getParameter("state"));
		}
		if (request.getParameter("adstate") != null) {
			state = Integer.parseInt(request.getParameter("adstate"));
		}
		if (request.getParameter("adTypeId") != null) {
			adTypeId = Integer.parseInt(request.getParameter("adTypeId"));
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
      window.location.href="adList.do?curPage=" +value+"&state=<%=state%>&adTypeId=<%=adTypeId%>";
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
		
	function queryad(){
		var cc=document.all.adTypeId.options[document.all.adTypeId.selectedIndex].value;
		var state=document.all.adstate.options[document.all.adstate.selectedIndex].value;
		document.location.href="<%=request.getContextPath()%>/web/adList.do?state="+state+"&adTypeId="+cc;
	}
	function pubad(adid){
		var cc=document.all.adTypeId.options[document.all.adTypeId.selectedIndex].value;
		var state=document.all.adstate.options[document.all.adstate.selectedIndex].value;
		document.location.href="<%=request.getContextPath()%>/web/adChangeState.do?action=pub&adid="+adid+"&state="+1+"&adTypeId="+cc;
	}
	function unpubad(adid){
		var cc=document.all.adTypeId.options[document.all.adTypeId.selectedIndex].value;
		var state=document.all.adstate.options[document.all.adstate.selectedIndex].value;
		document.location.href="<%=request.getContextPath()%>/web/adChangeState.do?action=unpub&adid="+adid+"&state="+0+"&adTypeId="+cc;
	}
</script>
<body>
<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/web/adDel.do">
	<Div class="title">广告管理</Div>
	<table width="100%" border="0" cellpadding="2" cellspacing="1"
	class="border">
	<tr>
	  <td class="tdlefts" height="25">类型 
		  <select name="adTypeId" id="adTypeId" onChange="queryad()">
			<option value="0">所有</option>
			<c:forEach var="r" items="${class}">
				<option value="${r.id}">${r.adTypeName}</option>
			</c:forEach>
		</select> 
		状态 <select name="adstate" id="adstate" onChange="queryad()">
			<option value="0">未发布</option>
			<option value="1">已发布</option>
	  </select></td>
	</tr>
	<tr>
	  <td class="tdlefts" height="25"><input type="button" name="Submit" class="input" value="增加" onclick="window.location.href='<%=request.getContextPath()%>/web/adPreAdd.do'" />
      <input type="submit" name="Submit2" class="input" value="删除" onClick="return delCheck()" /></td>
	  </tr>
	</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
		    <td class="menutop">&nbsp;</td>
            <td width="60" class="menutop">序号</td>
            <td class="menutop">广告标题</td>
            <td class="menutop">广告简介</td>
            <td class="menutop">广告链接</td>
            <td class="menutop">广告类型</td>
            <td class="menutop">操作</td>
          </tr>
		  <c:forEach var="adlist" items="${adlist}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
		  <td class="tdcenter" height="25"><input name="checkbox" type="checkbox" id="checkbox" value="${adlist.id}" /></td>
            <td class="tdcenter"><%=recordIndex++ %></td>
            <td class="tdcenter">${adlist.adTitle}</td>
            <td class="tdcenter">${adlist.adSynopsis}</td>
            <td class="tdcenter"><a href="${adlist.adLink}" target="_blank">${adlist.adLink}</a></td>
            <td class="tdcenter">${adlist.adTypeName}</td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/web/adPreUpdate.do?id=${adlist.id}&state=${adlist.state}">编辑</a> / 
			<c:if test="${adlist.state==0}" var="true"><a style="cursor:hand" onClick="pubad(${adlist.id})">发布 </a>			</c:if>
			<c:if test="${adlist.state==1}" var="true">
      		<a style="cursor:hand" onClick="unpubad(${adlist.id})">撤销</a>			</c:if>			</td>
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
	    <a href="<%=request.getContextPath()%>/web/adList.do?curPage=1&state=<%=state %>&adTypeId=<%=adTypeId %>">首页</a> 
        <a href="<%=request.getContextPath()%>/web/adList.do?curPage=${page.curPage-1}&state=<%=state %>&adTypeId=<%=adTypeId%>">上页</a> 
        <a href="<%=request.getContextPath()%>/web/adList.do?curPage=${page.curPage+1}&state=<%=state %>&adTypeId=<%=adTypeId%>">下页</a> 
        <a href="<%=request.getContextPath()%>/web/adList.do?curPage=${page.totalPage}&state=<%=state %>&adTypeId=<%=adTypeId%>">末页</a>
		</c:if></td>
	</tr>
</table>
</form>
</body>
<script type="text/javascript">
var tcalc=document.all.adTypeId;
var index="<%=adTypeId %>";
for(var i=0; i<tcalc.options.length; i++){
			if(tcalc.options[i].value==index){
				tcalc.options[i].selected=true;break;
			}
		}
var tstate=document.all.adstate;
var index1="<%=state %>";
for(var i=0; i<tstate.options.length; i++){
			if(tstate.options[i].value==index1){
				tstate.options[i].selected=true;break;
			}
		}
</script>
</html>
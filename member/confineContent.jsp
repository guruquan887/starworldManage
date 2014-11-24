<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=10;
	try{
		System.out.println(request.getAttribute("pageSize")+","+request.getAttribute("pageIndex"));
		
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
%>
<head>
<script language="javascript" type="text/javascript">
 var msg="<%=msg %>";
 if(msg!='')alert(msg);
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
 function getAll()
{
	var elements=document.form2.checkbox;
	var length=elements.length;
	for(var i=0;i<length;i++)
	{
		var element=elements[i];
		element.checked=true;
	}
}
function checkAllBox(i){
	
		var isChecked=(document.form2.checkAll.checked == true);
	    var elements=document.form2.elements;
	    var counter=elements.length;
	    for(i=0;i<counter;i++){
			var element=elements[i];
		if(element.type=="checkbox"){
			element.checked=isChecked;
		}
	}
}
function jumppage(value)
   {
      window.location.href="<%=request.getContextPath()%>/member/ConfineList.do?action=gameUserXZ&curPage=" +value;
   }
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/member/ConfineList.do?action=gameUserXZ&curPage="+pageNo;
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<Div class="title"><span>  
	
	    <label>
	    
	    <input name="Submit2" type="button" onclick="window.location.href='<%=request.getContextPath()%>/member/addconfineContent.jsp'" class="input" value="新增" />
	    </label>
	  
	</span>保留用户名</Div>
	<form id="form2" name="form2" method="post" onsubmit="return delCheck()" action="<%=request.getContextPath()%>/member/ConfineList.do?action=delAll">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
		  <td width="30" class="menutop"><input type="checkbox" name="checkAll" value="checkAll" onClick="checkAllBox(0)" /></td>
            <td class="menutop">用户名</td>
            <td class="menutop">失效时间</td>
            <td class="menutop">收集时间</td>
            <td class="menutop">操作</td>
            <%--<td class="menutop">操作</td>--%>
          </tr>
		   <c:forEach var="confineList" items="${confineList}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
		  <td width="40" class="tdcenter">
            <input type="checkbox" id="checkbox" value="${confineList.string}" name="checkbox" /></td>
            <td class="tdcenter">${confineList.string}</td>
            <td class="tdcenter">&nbsp;${confineList.enjoinOverDate}</td>
            <td class="tdcenter">${confineList.collectDate}</td>
            <td class="tdcenter">&nbsp;</td>
            <%--<td class="tdcenter"><a href="<%=request.getContextPath()%>/member/gameConfineList.do?action=delConfine&userID=${confineList.userID}">删除</a> </td>--%>
          </tr>
		  </c:forEach>
		   <tr>
		   <td class="tdlefts"><label>
              <input name="Submit3" type="submit" class="input" value="删除" />
            </label></td>
            <td colspan="5" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
        <c:if test="${page.totalPage>=0}"  var="true"> 
　<a onClick="changepage(1)" style="cursor:hand">首页</a>
<a onClick="changepage(${page.curPage-1})" style="cursor:hand">上页</a>
<select name="select" onChange="jumppage(this.value);">
          <c:forEach var="i" begin="1" end="${page.totalPage }" step="1"> <option value="${i}" 
              <c:if test="${i==page.curPage}" var="true">selected="selected"</c:if>
            >第${i}页
            </option>
          </c:forEach>
        </select>
        <a onClick="changepage(${page.curPage+1})" style="cursor:hand">下页</a>
        <a onClick="changepage(${page.totalPage})" style="cursor:hand">末页</a></c:if></td>
          </tr>
        </table>
</form>
</body>
</html>
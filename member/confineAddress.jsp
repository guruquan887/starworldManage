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
	String termOne="";
	if(request.getParameter("termOne")!=null){
		termOne=request.getParameter("termOne");
		termOne = new String(termOne.getBytes("ISO_8859_1"),"UTF-8");
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
      window.location.href="<%=request.getContextPath()%>/member/ConfineList.do?action=confineAddress&termOne=<%=termOne%>&curPage=" +value;
   }
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/member/ConfineList.do?action=confineAddress&termOne=<%=termOne%>&curPage="+pageNo;
}
function search(){
	document.location.href="<%=request.getContextPath()%>/member/ConfineList.do?action=confineAddress&termOne="+encodeURI(document.all.termOne.value);
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<Div class="title"><span>  
	<form id="form1" name="form1" method="post" action="">
	限制地址：
	<input name="termOne" id="termOne" type="text" class="input_width3" />
	   <input name="Submit2" type="button" class="input" onClick="search()" value="查询" />
	   

	    <input name="Submit2" type="button" onclick="window.location.href='<%=request.getContextPath()%>/member/addconfineAddress.jsp'" class="input" value="新增" />
 </form>
	  
	</span>限制地址</Div>
	<form id="form2" name="form2" method="post" onsubmit="return delCheck()" action="<%=request.getContextPath()%>/member/ConfineList.do?action=delAllAddress">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
		  <td width="30" class="menutop"><input type="checkbox" name="checkAll" value="checkAll" onClick="checkAllBox(0)" /></td>
            <td class="menutop">限制地址</td>
            <td class="menutop">限制登录</td>
            <td class="menutop">限制注册</td>
            <td class="menutop">失效时间</td>
            <td class="menutop">收集时间</td>
            <td class="menutop">备注</td>
            <%--<td class="menutop">操作</td>--%>
          </tr>
		   <c:forEach var="confineList" items="${confineList}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
		  <td width="40" class="tdcenter">
            <input type="checkbox" id="checkbox" value="${confineList.addrString}" name="checkbox" /></td>
            <td class="tdcenter">${confineList.addrString}</td>
            <td class="tdcenter"><c:if test="${confineList.enjoinLogon==0}"  var="true">正常</c:if><c:if test="${confineList.enjoinLogon==1}"  var="true">禁止</c:if></td>
            <td class="tdcenter"><c:if test="${confineList.enjoinRegister==0}"  var="true">正常</c:if><c:if test="${confineList.enjoinRegister==1}"  var="true">禁止</c:if></td>
            <td class="tdcenter">${confineList.enjoinOverDate}</td>
            <td class="tdcenter">${confineList.collectDate}</td>
            <td class="tdcenter">${confineList.collectNote}&nbsp;</td>
          </tr>
		  </c:forEach>
		   <tr>
		   <td class="tdlefts"><label>
              <input name="Submit3" type="submit" class="input" value="删除" />
            </label></td>
            <td colspan="7" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
        <c:if test="${page.totalPage>=0}" var="true"> 
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
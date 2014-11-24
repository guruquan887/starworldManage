<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %> 
<%
int recordIndex=1;
	int pageIndex=1;
	int pageSize=30;
	int state=0;
	int buildID=0;
	try{
		
		if(request.getParameter("buildID")!=null){
			buildID=Integer.parseInt(request.getParameter("buildID"));
		}
		if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("pageIndex")!=null){
			pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
		}
		if(request.getParameter("state")!=null){
			state=Integer.parseInt(request.getParameter("state"));
		}
		}catch(Exception e){
			e.printStackTrace();
	}
	recordIndex=(pageIndex-1)*pageSize+1;
	String termOne="";
	if(request.getParameter("termOne")!=null){
		termOne=request.getParameter("termOne");
		termOne = new String(termOne.getBytes("ISO_8859_1"),"UTF-8");
	}
	String selectOne="";
	if(request.getParameter("selectOne")!=null){
		selectOne=request.getParameter("selectOne");
	}
	String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}
%>
<head>
<script type="text/javascript">
 var msg="<%=msg %>";
 if(msg!='')alert(msg);
 var tone=encodeURI("<%=termOne %>");
 function getAll()
{
	var elements=document.form1.checkbox;
	var length=elements.length;
	for(var i=0;i<length;i++)
	{
		var element=elements[i];
		element.checked=true;
	}
}
function checkAllBox(i){
	
		var isChecked=(document.form1.checkAll.checked == true);
	    var elements=document.form1.elements;
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
      window.location.href="<%=request.getContextPath()%>/gold/cardList.do?action=cardInfo&curPage=" +value+"&state=<%=state%>&buildID=<%=buildID%>";
   }
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/gold/cardList.do?action=cardInfo&curPage="+pageNo+"&pageSize=<%=pageSize %>&state=<%=state%>&buildID=<%=buildID%>";
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
function querystate()
	{
		var state=document.getElementById("state").options[document.getElementById("state").selectedIndex].value;
		document.location.href="<%=request.getContextPath()%>/gold/cardList.do?action=cardInfo&buildID=<%=buildID%>&state="+state;
	}
function search(){
	document.location.href="<%=request.getContextPath()%>/gold/cardList.do?action=cardInfo&termOne="+encodeURI(document.all.termOne.value)
		+"&selectOne="+document.all.selectOne.options[document.all.selectOne.selectedIndex].value+"&state="+document.all.state.options[document.all.state.selectedIndex].value;

}
function addBeizhu(){
   var beizhu = document.getElementById("beizhu").value;
   var boxes = document.getElementsByName("checkbox");   
   var checkbox = new Array();   
	//alert(checkbox);
    for(var i = 0;i<boxes.length;i++)   
    {   
    if(boxes[i].checked)   
    {   
        checkbox[i] = boxes[i].value;
		//alert(checkbox[i]);
    }   
}
//alert(checkbox);
   document.location.href="<%=request.getContextPath()%>/gold/cardList.do?action=addBeizhu&checkboxss="+checkbox+"&beizhu="+encodeURI(beizhu);
}

function excel(){
   var boxes = document.getElementsByName("checkbox");   
   var checkbox = new Array();  
   var j = 0; 
    for(var i = 0;i<boxes.length;i++)   
    {   
    if(boxes[i].checked==true)   
    {    
        checkbox[j] = boxes[i].value;
		j++;
		//alert(j);
    }   
}
   document.location.href="<%=request.getContextPath()%>/gold/cardList.do?action=excel&checkboxss="+checkbox;
}


function open1(m){
   var boxes = document.getElementsByName("checkbox");   
   var checkbox = new Array();  
   var j = 0; 
    for(var i = 0;i<boxes.length;i++)   
    {   
    if(boxes[i].checked==true)   
    {    
        checkbox[j] = boxes[i].value;
		j++;
    }   
	}
	document.getElementById("checkboxss").value=checkbox;
	//alert(document.getElementById("checkboxss").value);
	var ss = "opendiv"+m;
	document.getElementById(ss).style.display="block";	
	}
function close1(m){
	var ss = "opendiv"+m;
	document.getElementById(ss).style.display="none";	
	}
function tjSubmit(opId){
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
         alert("请选择对象");
         return false;
  			}
else{
   if(opId==0){
      form1.action="<%=request.getContextPath()%>/gold/cardList.do?action=updateNullity&type=1&buildID=<%=buildID%>";
	  form1.submit();
   }
   else if(opId==1){
	  form1.action="<%=request.getContextPath()%>/gold/cardList.do?action=updateNullity&type=0&buildID=<%=buildID%>";
	  form1.submit();  
   }
   }
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<Div class="title"><span>  
	  <form id="form2" name="form2" method="post" action="">
	  	
	   
      </form>
	</span>充值卡管理</Div>
	<form id="form1" name="form1" method="post" onsubmit="return delCheck()" action="<%=request.getContextPath()%>/gold/cardList.do?action=delete">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
		 <tr>
            <td colspan="21" class="tdlefts">
			
			<div align="right">
			状态<select name="state" id="state" onChange="querystate()">
		<option value="1">已使用</option>
      	<option value="2">未使用</option>
		<option value="3">已禁用</option>
      	<option value="4">未禁用</option>
		<option value="0">全部</option>
      		   </select>
			 <input name="Submit" type="button" class="input" value="禁用" onclick="tjSubmit(0)" />
		<input name="Submit" type="button" class="input" value="还原" onclick="tjSubmit(1)" />
		</div>
			</td>
          </tr>
          <tr>
            <td width="30" class="menutop"><input type="checkbox" name="checkAll" value="checkAll" onClick="checkAllBox(0)" />&nbsp;</td>
            <td class="menutop">卡号</td>
            <td class="menutop">实卡名称</td>
            <td class="menutop">实卡价格</td>
            <td class="menutop">赠送银子</td>
            <td class="menutop">会员等级</td>
            <td class="menutop">会员天数</td>
            <td class="menutop">有效期限</td>
            <td class="menutop">充值日期</td>
            <td class="menutop">使用范围</td>
            <td class="menutop">销售商</td>
            <td class="menutop">禁用状态</td>
		  </tr>
		  <c:forEach var="card" items="${cardlist}">
          <tr  onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter"><input type="checkbox" id="checkbox" value="${card.cardID}" name="checkbox" />&nbsp;</td>
            <td class="tdcenter">${card.serialID}</td>
            <td class="tdcenter">${card.cardName}</td>
            <td class="tdcenter">${card.cardPrice}</td>
            <td class="tdcenter">&nbsp;${card.cardGold}</td>
            <td class="tdcenter"><c:if test="${card.memberOrder==0}" var="true">普通</c:if>
			<c:if test="${card.memberOrder==1}" var="true">红钻</c:if>
			<c:if test="${card.memberOrder==2}" var="true">蓝钻</c:if>
			<c:if test="${card.memberOrder==3}" var="true">黄钻</c:if>
			<c:if test="${card.memberOrder==4}" var="true">紫钻</c:if></td>
            <td class="tdcenter">&nbsp;${card.memberDays }</td>
            <td class="tdcenter">${card.validDate}</td>
            <td class="tdcenter">${card.buildDate }</td>
            <td class="tdcenter"><c:if test="${card.useRange==0}" var="true">全部用户</c:if>
			<c:if test="${card.useRange==1}" var="true">新注册用户</c:if>
			<c:if test="${card.useRange==2}" var="true">第一次充值用户</c:if></td>
            <td class="tdcenter">&nbsp;${card.salesPerson}</td>
            <td class="tdcenter"><c:if test="${card.nullity==0}" var="true">否</c:if><c:if test="${card.nullity==1}" var="true">是</c:if></td>
			</tr>
		  </c:forEach>
          <tr>
            <td colspan="13" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
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
<script type="text/javascript">
var tstate=document.all.state;
var index1="<%=state %>";
for(var i=0; i<tstate.options.length; i++){
			if(tstate.options[i].value==index1){
				tstate.options[i].selected=true;break;
			}
		}
</script>
</html>
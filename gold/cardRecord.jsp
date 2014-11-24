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
	int state=1;
	int userID = 0;
if(request.getParameter("userID")!=null){
	    userID = Integer.parseInt(request.getParameter("userID"));
	}
	try{
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
	String startTime="";
	if(request.getParameter("startTime")!=null){
		startTime=request.getParameter("startTime");
	}
	String endTime="";
	if(request.getParameter("endTime")!=null){
		endTime=request.getParameter("endTime");
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
      window.location.href="<%=request.getContextPath()%>/gold/cardList.do?action=cardRecord&userID=<%=userID%>&termOne="+tone+"&selectOne=<%=selectOne %>&startTime=<%=startTime%>&endTime=<%=endTime%>&curPage=" +value;
   }
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/gold/cardList.do?action=cardRecord&userID=<%=userID%>&termOne="+tone+"&selectOne=<%=selectOne %>&startTime=<%=startTime%>&endTime=<%=endTime%>&curPage="+pageNo;
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
		document.location.href="<%=request.getContextPath()%>/gold/cardList.do?action=list&userID=<%=userID%>&state="+state;
	}
function search(){
	document.location.href="<%=request.getContextPath()%>/gold/cardList.do?action=cardRecord&userID=<%=userID%>&termOne="+encodeURI(document.all.termOne.value)
		+"&selectOne="+document.all.selectOne.options[document.all.selectOne.selectedIndex].value+"&startTime="+document.all.startTime.value
		+"&endTime="+document.all.endTime.value;

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
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<body>
<%--	<div class="tab clearfix">
		<ul>
		<li><a href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gamebankRecord&userID=<%=userID%>">钱庄记录</a></li>
			<li><a href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gameUserInOutRecord&userID=<%=userID%>">进出记录</a></li>
			<li><a href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gameUserDrawRecord&userID=<%=userID%>">游戏记录</a></li>
			<li class="active"><a href="<%=request.getContextPath()%>/gold/cardList.do?action=cardRecord&userID=<%=userID%>">充值记录</a></li>
		</ul>
	</div>--%>
	<Div class="title"><span>  
	  <form id="form2" name="form2" method="post" action="">
	    <label>
	   日期查询	    <input name="startTime" onClick="WdatePicker()" type="text" value="" size="15" />
至
<input name="endTime" type="text" onClick="WdatePicker()" value="" size="15" />
		用户查询
		<input name="termOne" id="termOne" type="text" class="input_width3" />
	    <select name="selectOne" id="selectOne" class="input_width2">
	      <option value="accounts">用户名</option>
	      <option value="gameID">游戏ID</option>
        </select>
	    <input name="Submit2" type="button" class="input" value="搜索" onclick="search()"/>
	    </label>
      </form>
	</span>充值记录</Div>
	<form id="form1" name="form1" method="post" onsubmit="return delCheck()" action="<%=request.getContextPath()%>/gold/cardList.do?action=delete">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td class="menutop">充值时间</td>
            <td class="menutop">服务类别</td>
            <td class="menutop">用户账号</td>
            <td class="menutop">游戏ID</td>
            <td class="menutop">充值卡号</td>
            <td class="menutop">订单号码</td>
            <td class="menutop">卡名称</td>
            <td class="menutop">卡价格</td>
            <td class="menutop">赠送银子</td>
            <td class="menutop">充值前银子</td>
            <td class="menutop">订单数量</td>
			<td class="menutop">订单金额</td>
            <td class="menutop">实付金额</td>
            <td class="menutop">充值地址</td>
            <td class="menutop">操作用户</td>
          </tr>
		  <c:forEach var="card" items="${cardlist}">
          <tr  onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter">${card.applyDate}</td>
            <td class="tdcenter">${card.shareName}</td>
            <td class="tdcenter">${card.accounts }</td>
            <td class="tdcenter">&nbsp;${card.gameID}</td>
            <td class="tdcenter">${card.serialID}</td>
            <td class="tdcenter">&nbsp;${card.orderID }</td>
            <td class="tdcenter">${card.cardName}</td>
            <td class="tdcenter">${card.cardPrice }</td>
            <td class="tdcenter">${card.cardGold}</td>
            <td class="tdcenter">${card.beforeGold}</td>
            <td class="tdcenter">&nbsp;${card.cardTotal}</td>
			<td class="tdcenter">&nbsp;${card.orderAmount}</td>
            <td class="tdcenter">&nbsp;${card.payAmount}</td>
            <td class="tdcenter">&nbsp;${card.ipAddress}</td>
            <td class="tdcenter">&nbsp;${card.operAccounts}</td>
          </tr>
		  </c:forEach>
          <tr>
            <td colspan="16" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
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
var ttype2=document.all.selectOne;
var index2="<%=selectOne %>";
for(var i=0; i<ttype2.options.length; i++){
	if(ttype2.options[i].value==index2){
		ttype2.options[i].selected=true;break;
	}
}

var ttype3=document.all.termOne;
var index3="<%=termOne%>";
for(var i=0; i<ttype3.length; i++){
	if(ttype3.value==index3){
		ttype3.selected=true;break;
	}
}
var tstate=document.all.state;
var index1="<%=state %>";
for(var i=0; i<tstate.options.length; i++){
			if(tstate.options[i].value==index1){
				tstate.options[i].selected=true;break;
			}
		}
</script>
</html>
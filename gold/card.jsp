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
      window.location.href="<%=request.getContextPath()%>/gold/cardList.do?action=list&curPage=" +value+"&termOne=<%=termOne %>&selectOne=<%=selectOne %>&state=<%=state%>";
   }
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/gold/cardList.do?action=list&curPage="+pageNo+"&pageSize=<%=pageSize %>&termOne=<%=termOne %>&selectOne=<%=selectOne %>&state=<%=state%>";
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
		document.location.href="<%=request.getContextPath()%>/gold/cardList.do?action=list&state="+state;
	}
function search(){
	document.location.href="<%=request.getContextPath()%>/gold/cardList.do?action=list&termOne="+encodeURI(document.all.termOne.value)
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
function DownloadCard(buildID)
{
	if(!confirm("确定要执行选定的操作吗？"))
	{
		return;
	}
	else
	{
		document.form1.action = "<%=request.getContextPath()%>/gold/cardList.do?action=excel&buildID="+buildID;
		document.form1.submit();
	}
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<body>
<!--备注开始-->
<div class="layer" id="opendiv1" name="opendiv1" style="display:none">
  <form id="form2" name="form2" method="post" action="<%=request.getContextPath()%>/gold/cardList.do?action=addBeizhu">
 
		<div class="menu"><span class="span" onclick="close1(1)" onmouseover="this.className='span1'" onmouseout="this.className='span'">&nbsp;</span>添加备注</div>
		<div class="content">
			<ul>
				<li class="lileft">备注：</li>
				<li class="liright">
				  <label>
				  <textarea name="beizhu" cols="25" rows="3"></textarea><input name="checkboxss" id="checkboxss" type="hidden" value="" />
				  </label>
				</li>
				<div  style=" clear:both"></div>
			</ul>
  </div>
  <div class="down">
    <label>
    <input name="Submit4" type="submit" class="input" value="确定" />
    </label>
	 <label>
    <input name="Submit4" type="reset" class="input" value="重填" />
    </label>
  </div> 
  </form>
</div>
<!--备注结束-->
	<Div class="title"><span>  
	  <form id="form2" name="form2" method="post" action="">
	    <label>
	    <input name="termOne" type="text" class="input_width2"  />
	    <select name="selectOne" size="1" class="Select" id="selectOne">
		<option value="jsUser">按用户</option>
        </select>
		<select name="state" id="state" onChange="querystate()">
		<option value="1">未充值</option>
      	<option value="0">已充值</option>
      </select>
	    <input name="Submit2" type="button" class="input" value="搜索" onclick="search()"/>
	    </label>
	    <c:if test="${roleId==1}" var="true"><%--<input type="button" name="Submit" value="导出Excel" class="input" onClick="excel()">--%> <input name="Submit" type="button" class="input" value="生成点卡" onclick="window.location.href='<%=request.getContextPath()%>/gold/cardList.do?action=preCreate'" /></c:if>
      </form>
	</span>充值卡管理</Div>
	<form id="form1" name="form1" method="post" onsubmit="return delCheck()" action="<%=request.getContextPath()%>/gold/cardList.do?action=delete">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td width="30" class="menutop">生产批次</td>
            <td class="menutop">生成日期</td>
            <td class="menutop">管理员</td>
            <td class="menutop">销售商</td>
            <td class="menutop">实卡名称</td>
            <td class="menutop">实卡数量</td>
            <td class="menutop">实卡价格</td>
            <td class="menutop">总金额</td>
            <td class="menutop">赠送银子</td>
            <td class="menutop">地址</td>
            <td class="menutop">导出次数</td>
			<td class="menutop">备注</td>
            <td class="menutop">管理</td>
          </tr>
		  <c:forEach var="card" items="${cardlist}">
          <tr  onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter">${card.buildID}</td>
            <td class="tdcenter">${card.buildDate}</td>
            <td class="tdcenter">${card.adminName }</td>
            <td class="tdcenter">&nbsp;${card.salesPerson}</td>
            <td class="tdcenter">&nbsp;${card.cardName}</td>
            <td class="tdcenter">&nbsp;${card.buildCount }</td>
            <td class="tdcenter">${card.cardPrice}</td>
            <td class="tdcenter">${card.cardTotalPrice }</td>
            <td class="tdcenter">${card.cardGold}</td>
            <td class="tdcenter">${card.buildAddr}</td>
            <td class="tdcenter">&nbsp;${card.downloadCount}</td>
			<td class="tdcenter">&nbsp;${card.noteInfo}</td>
            <td class="tdcenter">&nbsp;<a href="javascript:void(0)" onclick="DownloadCard(${card.buildID})">实卡导出</a> / <a href="<%=request.getContextPath()%>/gold/cardList.do?action=cardInfo&buildID=${card.buildID}">实卡信息</a></td>
          </tr>
		  </c:forEach>
          <tr>
            <td colspan="14" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
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
//alert(index1);
for(var i=0; i<tstate.options.length; i++){
			if(tstate.options[i].value==index1){
				tstate.options[i].selected=true;break;
			}
		}
</script>
</html>
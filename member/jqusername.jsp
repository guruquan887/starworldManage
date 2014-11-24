<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=10;
	int type = 0;
	if(request.getParameter("type")!=null){
	    type = Integer.parseInt(request.getParameter("type"));
	}
	int serverID = 0;
	if(request.getParameter("serverID")!=null){
	    serverID = Integer.parseInt(request.getParameter("serverID"));
	}
	int gameTypeID = 1;
	if(request.getParameter("gameTypeID")!=null){
	    gameTypeID = Integer.parseInt(request.getParameter("gameTypeID"));
	}
	if(request.getAttribute("gameTypeID")!=null){
			gameTypeID=Integer.parseInt(request.getAttribute("gameTypeID").toString());
		}
	String orderby="gameID";
	if(request.getParameter("orderby")!=null){
		orderby=request.getParameter("orderby");
	}
	String username = "";
	if(request.getSession().getAttribute("username")!=null){
		username = (String)request.getSession().getAttribute("username");
	}
	try{
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
	int serviceTime=0;
	if(request.getAttribute("serviceTime")!=null){
		serviceTime=(Integer)(request.getAttribute("serviceTime"));
	}
	String termOne="";
	if(request.getParameter("termOne")!=null){
		termOne=request.getParameter("termOne");
		termOne = new String(termOne.getBytes("ISO_8859_1"),"UTF-8");
	}
	String selectOne="";
	if(request.getParameter("selectOne")!=null){
		selectOne=request.getParameter("selectOne");
	}
	String spreaderID="";
	if(request.getParameter("spreaderID")!=null){
		spreaderID=request.getParameter("spreaderID");
	}
	int compareType = 0;
	if(request.getParameter("compareType")!=null){
	    compareType = Integer.parseInt(request.getParameter("compareType"));
	}
	int compareScore = 0;
	if(request.getParameter("compareScore")!=null){
	    compareScore = Integer.parseInt(request.getParameter("compareScore"));
	}
%>
<head>
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
var tone=encodeURI("<%=termOne %>");
function jumppage(value)
   {
      window.location.href="<%=request.getContextPath()%>/member/gamejquserList.do?action=gameUserList&curPage=" +value+"&pageSize=<%=pageSize %>&orderby=<%=orderby %>&termOne=<%=termOne%>&selectOne=<%=selectOne %>&spreaderID=<%=spreaderID%>&type=<%=type%>&serverID=<%=serverID%>&gameTypeID=<%=gameTypeID%>&compareType=<%=compareType%>&compareScore=<%=compareScore%>";
   }
function deleteUser(gameUserId)
	{
		document.location.href="gameuserDelete.do?gameUserID="+gameUserId+"&type=1";
	}
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/member/gamejquserList.do?action=gameUserList&curPage="+pageNo+"&termOne="+tone+"&selectOne=<%=selectOne %>&pageSize=<%=pageSize %>"+"&orderby=<%=orderby %>&spreaderID=<%=spreaderID%>&type=<%=type%>&serverID=<%=serverID%>&gameTypeID=<%=gameTypeID%>&compareType=<%=compareType%>&compareScore=<%=compareScore%>";
}
function search(){
	document.location.href="<%=request.getContextPath()%>/member/gamejquserList.do?action=gameUserList&termOne="+encodeURI(document.all.termOne.value)
		+"&selectOne="+document.all.selectOne.options[document.all.selectOne.selectedIndex].value
		+"&pageSize="+document.all.pageSize.options[document.all.pageSize.selectedIndex].value
		+"&spreaderID=<%=spreaderID%>&type=<%=type%>&serverID="+document.all.serverID.options[document.all.serverID.selectedIndex].value
		+"&gameTypeID="+document.all.gameTypeID.options[document.all.gameTypeID.selectedIndex].value
		+"&compareType="+document.all.compareType.options[document.all.compareType.selectedIndex].value
		+"&compareScore="+document.all.compareScore.value;;
}

function ejiaA1(o,a,b,c,d){
var t=document.getElementById(o).getElementsByTagName("tr");
for(var i=0;i<t.length;i++){
t[i].style.backgroundColor=(t[i].sectionRowIndex%2==0)?a:b;
t[i].onclick=function(){
if(this.x!="1"){
this.x="1";
this.style.backgroundColor=d;
}else{
this.x="0";
this.style.backgroundColor=(this.sectionRowIndex%2==0)?a:b;
}
}
t[i].onmouseover=function(){
if(this.x!="1")this.style.backgroundColor=c;
}
t[i].onmouseout=function(){
if(this.x!="1")this.style.backgroundColor=(this.sectionRowIndex%2==0)?a:b;
}
}
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
      form2.action="<%=request.getContextPath()%>/member/gamejquserList.do?action=delAll&gameTypeID=<%=gameTypeID%>";
	  form2.submit();
   }
   else if(opId==1){
	  form2.action="<%=request.getContextPath()%>/member/agentFreeze.jsp";
	  form2.submit();  
   }
   else if(opId==2){
      form2.action="<%=request.getContextPath()%>/member/gamejquserList.do?action=UnFreeze";
	  form2.submit();
   }
   else if(opId==3){
      form2.action="<%=request.getContextPath()%>/member/gamejquserList.do?action=qxJuser&gameTypeID=<%=gameTypeID%>";
	  form2.submit();
   }
   else if(opId==4){
      form2.action="<%=request.getContextPath()%>/member/jqaddscore.jsp?gameTypeID=<%=gameTypeID%>";
	  form2.submit();
   }
   else if(opId==5){
      form2.action="<%=request.getContextPath()%>/member/jqaddjifen.jsp?gameTypeID=<%=gameTypeID%>";
	  form2.submit();
   }
   else if(opId==6){
      form2.action="<%=request.getContextPath()%>/member/gameuserList.do?action=presetupJuser";
	  form2.submit();
   }
   else if(opId==7){
      form2.action="<%=request.getContextPath()%>/member/jqaddexperience.jsp?gameTypeID=<%=gameTypeID%>";
	  form2.submit();
   }
   else if(opId==8){
      form2.action="<%=request.getContextPath()%>/member/gamejquserList.do?action=OxNEW&gameTypeID=<%=gameTypeID%>";
	  form2.submit();
   }
   else if(opId==9){
      form2.action="<%=request.getContextPath()%>/member/gamejquserList.do?action=RedLine&gameTypeID=<%=gameTypeID%>";
	  form2.submit();
   }
   else if(opId==10){
      form2.action="<%=request.getContextPath()%>/member/gamejquserList.do?action=RedLine&gameTypeID=<%=gameTypeID%>";
	  form2.submit();
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
	  <form id="form1" name="form1" method="post" action="">
	    <label>
		<c:if test="${gameTypeID==1}" var="true">银子</c:if><c:if test="${gameTypeID==2}" var="true">积分</c:if><select name="compareType" id="compareType" class="Select" >
		<option value="0">≥</option>
		<option value="1">≤</option>
        </select>
		<input name="compareScore" id="compareScore" onkeyup="isNumber(this)" size="8" type="text" value="<%=compareScore%>" />
		<select name="gameTypeID" id="gameTypeID" class="input_width2">
		  <option value="1">银子类型</option>
		  <option value="2">积分类型</option>
		  </select>
		<select name="serverID" id="serverID" class="input_width1">
		<option value="0">未分配房间</option>
		<option value="-1">所有机器人</option>
		  <c:forEach var="roomlist" items="${roomlist}">
		  <option value="${roomlist.serverID}">${roomlist.serverRoom}</option>
		  </c:forEach>
		  </select>
		<select name="pageSize" size="1" class="Select" id="pageSize">
		<option value="30">显示30条</option>
		<option value="50">显示50条</option>
		<option value="100">显示100条</option>
		<option value="200">显示200条</option>
        </select>
	    <input name="termOne" id="termOne" type="text" class="input_width2" />
	    <select name="selectOne" class="input_width2">
	      <option value="accounts">用户名</option>
	      <option value="gameID">游戏ID</option>
        </select><input name="type" id="type" type="hidden" value="<%=type%>" />
	    <input name="Submit2" type="button" class="input" value="搜索" onClick="search()"/>
	    </label>
      </form>
	</span>机器人&nbsp;&nbsp;&nbsp;<strong style="font-size:12px;"></strong>人数:<strong style="font-size:12px;">${sumDTO.totalAndroidNum}</strong>&nbsp;&nbsp;&nbsp;总金额: <strong style="font-size:12px;">${sumDTO.totalAndroidScore}</strong></Div>
	<form id="form2" name="form2" method="post" onsubmit="return delCheck()" action="<%=request.getContextPath()%>/member/gamejquserList.do?action=delAll">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
		 <tr>
            <td colspan="20" class="menutop">
                <div align="left">
                  <input type="button" name="Submit3" class="input" value="删除" onclick="tjSubmit(0)" />
                  <input type="button" name="Submit4" class="input" value="冻结" onclick="tjSubmit(1)" />
                  <input type="button" name="Submit5" class="input" value="解冻" onclick="tjSubmit(2)" />
				  <input type="button" name="Submit6" class="input" value="设置房间" onclick="tjSubmit(6)"/>
				  <input type="button" name="Submit6" class="input" value="取消房间" onclick="tjSubmit(10)"/>
                  <input type="button" name="Submit6" class="input" value="取消机器人" onclick="tjSubmit(3)"/>
                  <input type="button" name="Submit7" class="input" value="赠送银子" onclick="tjSubmit(4)"/>
				  <input type="button" name="Submit8" class="input" value="赠送积分" onclick="tjSubmit(5)"/>
				  <input type="button" name="Submit9" class="input" value="赠送经验" onclick="tjSubmit(7)"/>
				  <input type="button" name="Submit10" class="input" value="牛牛特殊" onclick="tjSubmit(8)"/>
				  <input type="button" name="Submit11" class="input" value="五张特殊" onclick="tjSubmit(9)"/>
				  <input type="button" name="Submit11" class="input" value="两张特殊" onclick="tjSubmit(9)"/>
                    </div>            </td>
          </tr>
          <tr>
		  <td width="60" class="menutop"><input type="checkbox" name="checkAll" value="checkAll" onClick="checkAllBox(0)" /></td>
            <td width="30" class="menutop">序号</td>
            <td class="menutop">游戏ID</td>
            <td class="menutop">用户名</td>
            <td class="menutop">昵称</td>
            <td class="menutop"><c:if test="${gameTypeID==1}" var="true">银子</c:if><c:if test="${gameTypeID==2}" var="true">积分</c:if></td>
            <td class="menutop">经验&nbsp;</td>
            <td class="menutop">创建时间</td>
            <td class="menutop" width="200px">服务时间</td>
            <td class="menutop">注册地址</td>
            <td class="menutop">注册时间</td>
            <td class="menutop">赢局</td>
            <td class="menutop">输局</td>
            <td class="menutop">房间</td>
            <td class="menutop">机器人类型</td>
          </tr>
		  <c:forEach var="user" items="${userlist}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
		  <td class="tdcenter"><input type="checkbox" id="checkbox" value="${user.userID}" name="checkbox" /></td>
            <td class="tdcenter"><%=recordIndex++%></td>
            <td class="tdcenter">${user.gameID}</td>
            <td class="tdcenter"><a href="usernameLog.html">${user.accounts}</a></td>
            <td class="tdcenter">${user.regAccounts}</td>
            <td class="tdcenter">${user.score}</td>
            <td class="tdcenter">${user.experience}</td>
            <td class="tdcenter">${user.createDate}&nbsp;</td>
            <td class="tdcenter" title="${user.serverTime}" width="200px">${fn:substring(user.serverTime,0,25)}...&nbsp;</td>
            <td class="tdcenter">${user.registerIP}&nbsp;</td>
            <td class="tdcenter">${user.registerDate}&nbsp;</td>
            <td class="tdcenter">${user.winCount}</td>
            <td class="tdcenter">${user.loseCount}</td>
            <td class="tdcenter">${user.serverRoom}&nbsp;</td>
            <td class="tdcenter">${user.jqType}&nbsp;</td>
          </tr>
		  </c:forEach>
		  <tr>
                <td class="tdcenter" height="25" colspan="15" align="center" valign="middle" bgcolor="#FFFFFF">
				<font color="red"><c:out value="${returnInfo}"></c:out></font></td>
       </tr>
          <tr>
            <td colspan="20" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
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
var index2="<%=termOne%>";
for(var i=0; i<ttype3.length; i++){
	if(ttype3.value==index2){
		ttype3.selected=true;break;
	}
}

var ttype1=document.all.pageSize;
var index1="<%=pageSize %>";
for(var i=0; i<ttype1.options.length; i++){
	if(ttype1.options[i].value==index1){
		ttype1.options[i].selected=true;break;
	}
}

var ttype4=document.all.serverID;
var index4="<%=serverID %>";
for(var i=0; i<ttype4.options.length; i++){
	if(ttype4.options[i].value==index4){
		ttype4.options[i].selected=true;break;
	}
}

var ttype5=document.all.gameTypeID;
var index5="<%=gameTypeID %>";
for(var i=0; i<ttype5.options.length; i++){
	if(ttype5.options[i].value==index5){
		ttype5.options[i].selected=true;break;
	}
}

var ttype6=document.all.compareType;
var index6="<%=compareType %>";
for(var i=0; i<ttype6.options.length; i++){
	if(ttype6.options[i].value==index6){
		ttype6.options[i].selected=true;break;
	}
}

var ttype7=document.all.compareScore;
var index7="<%=compareScore %>";
for(var i=0; i<ttype7.length; i++){
	if(ttype7.value==index7){
		ttype7.selected=true;break;
	}
}
</script>
</html>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=10;
	int type = 0;
	int interRoom = 0;
	int spreaderID = 100102;
	String accounts = "admin";
	if(request.getParameter("accounts")!=null){
		  accounts = request.getParameter("accounts");
	}
	if(request.getParameter("spreaderID")!=null){
	    spreaderID = Integer.parseInt(request.getParameter("spreaderID"));
	}
	if(request.getParameter("interRoom")!=null){
	    interRoom = Integer.parseInt(request.getParameter("interRoom"));
	}
	if(request.getParameter("type")!=null){
	    type = Integer.parseInt(request.getParameter("type"));
	}
	String orderby="totalScore";
	if(request.getParameter("orderby")!=null){
		orderby=request.getParameter("orderby");
	}
	String username = "";
	if(request.getSession().getAttribute("username")!=null){
		username = (String)request.getSession().getAttribute("username");
	}
	if(request.getParameter("interType")!=null){
	    type = Integer.parseInt(request.getParameter("interType"));
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
	String termOne="";
	if(request.getParameter("termOne")!=null){
		termOne=request.getParameter("termOne");
		termOne = new String(termOne.getBytes("ISO_8859_1"),"UTF-8");
	}
	String selectOne="";
	if(request.getParameter("selectOne")!=null){
		selectOne=request.getParameter("selectOne");
	}
	int compareType = -1;
	if(request.getParameter("compareType")!=null){
	    compareType = Integer.parseInt(request.getParameter("compareType"));
	}
	int compareScore = 100000;
	if(request.getParameter("compareScore")!=null){
	    compareScore = Integer.parseInt(request.getParameter("compareScore"));
	}
	String time = "";
	if(request.getParameter("time")!=null){
			time=request.getParameter("time");
		}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<style type="text/css">
<!--
a:link { text-decoration: none}
a:active { text-decoration: none }
a:visited { text-decoration: none }
-->
</style>
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
function   refresh()   {   
  history.go(0);   
  }   
  //var t = setTimeout("refresh()",<%=request.getParameter("time")==null?"600000":request.getParameter("time")%>);   
var tone=encodeURI("<%=termOne %>");
function jumppage(value)
   {
      window.location.href="<%=request.getContextPath()%>/member/gameuserList.do?action=gameUserList&curPage=" +value+"&pageSize=<%=pageSize %>&orderby=<%=orderby %>&termOne=<%=termOne%>&selectOne=<%=selectOne %>&type=<%=type%>&compareType=<%=compareType%>&compareScore=<%=compareScore%>&interRoom=<%=interRoom%>&time=<%=time%>";
   }
function deleteUser(gameUserId)
	{
		document.location.href="gameuserDelete.do?gameUserID="+gameUserId+"&type=1";
	}
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/member/gameuserList.do?action=gameUserList&curPage="+pageNo+"&termOne="+tone+"&selectOne=<%=selectOne %>&pageSize=<%=pageSize %>"+"&orderby=<%=orderby %>&type=<%=type%>&compareType=<%=compareType%>&compareScore=<%=compareScore%>&interRoom=<%=interRoom%>&time=<%=time%>";
}
function search(){
	document.location.href="<%=request.getContextPath()%>/member/gameuserList.do?action=gameUserList&termOne="+encodeURI(document.all.termOne.value)
		+"&selectOne="+document.all.selectOne.options[document.all.selectOne.selectedIndex].value
		+"&pageSize="+document.all.pageSize.options[document.all.pageSize.selectedIndex].value
		+"&orderby="+document.all.orderby.options[document.all.orderby.selectedIndex].value
		+"&compareType="+document.all.compareType.options[document.all.compareType.selectedIndex].value
		+"&compareScore="+document.all.compareScore.value
		+"&interRoom="+document.all.interRoom.options[document.all.interRoom.selectedIndex].value
		+"&interType="+document.getElementById("type").value;
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

function isNumber(oNum) {
 if(!oNum) return false; //line:160
 if(oNum.value=="") return false;
 var vv = oNum.value.replace(/[^\d]/g,'');
 var strP=/^\d+$/;
  if (!isNaN(oNum.value)) {
        return true;
    } else {
        alert("请输入整数");
        oNum.value=vv;
 		oNum.focus();
 		return false; 
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
      form2.action="<%=request.getContextPath()%>/member/gameuserList.do?action=delAll&type=<%=type%>";
	  form2.submit();
   }
   else if(opId==1){
	  form2.action="<%=request.getContextPath()%>/member/agentFreeze.jsp?type=<%=type%>";
	  form2.submit();  
   }
   else if(opId==2){
      form2.action="<%=request.getContextPath()%>/member/gameuserList.do?action=UnFreeze&type=<%=type%>";
	  form2.submit();
   }
   else if(opId==3){
      form2.action="<%=request.getContextPath()%>/member/gameuserList.do?action=setupAndroid&type=<%=type%>";
	  form2.submit();
   }
   else if(opId==4){
      form2.action="<%=request.getContextPath()%>/member/agentaddscore.jsp?type=<%=type%>";
	  form2.submit();
   }
   else if(opId==5){
      form2.action="<%=request.getContextPath()%>/member/agentaddjifen.jsp?type=<%=type%>";
	  form2.submit();
   }
   else if(opId==6){
      form2.action="<%=request.getContextPath()%>/member/agentaddexperience.jsp?type=<%=type%>";
	  form2.submit();
   }
   else if(opId==7){
      form2.action="<%=request.getContextPath()%>/member/gameuserList.do?action=interType&type=<%=type%>";
	  form2.submit();
   }
   else if(opId==8){
      form2.action="<%=request.getContextPath()%>/member/gameuserList.do?action=qxinterType&type=<%=type%>";
	  form2.submit();
   }
   else if(opId==9){
      form2.action="<%=request.getContextPath()%>/member/gameuserList.do?action=clearScore&type=<%=type%>";
	  form2.submit();
   }
   else if(opId==10){
      form2.action="<%=request.getContextPath()%>/member/gameuserList.do?action=clearFlee&type=<%=type%>";
	  form2.submit();
   }
   else if(opId==11){
      form2.action="<%=request.getContextPath()%>/member/gameuserList.do?action=vipType&type=<%=type%>";
	  form2.submit();
   }
   else if(opId==12){
      form2.action="<%=request.getContextPath()%>/member/gameuserList.do?action=qxvipType&type=<%=type%>";
	  form2.submit();
   }
   else if(opId==13){
      form2.action="<%=request.getContextPath()%>/member/agentaddxx.jsp?type=<%=type%>";
	  form2.submit();
   }
   else if(opId==14){
      form2.action="<%=request.getContextPath()%>/member/gameuserList.do?action=qxSpecialRight&type=<%=type%>";
	  form2.submit();
   }
   else if(opId==15){
      form2.action="<%=request.getContextPath()%>/member/agentGiftScore.jsp?type=<%=type%>";
	  form2.submit();
   }

   }
}
</script>
<body>
	<Div class="title"><span>  
	  <form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/member/gameuserList.do?action=gameUserList">
	    <label>
		账户<select name="nullity" id="nullity" class="Select" >
		<option value="-1">所有</option>
		<option value="0">启用</option>
		<option value="1">停用</option>
		<option value="2">冻结</option>
        </select>
		<SELECT name="time" onchange="form1.submit()">    
    	<option value="60000">60秒钟</OPTION>   
  		<option value="120000">2分钟</OPTION>   
  		<option value="300000">5分钟</OPTION> 
		<option value="600000" selected="selected">10分钟</OPTION>   
  		</SELECT>
		银子<select name="compareType" id="compareType" class="Select" >
		<option value="-1" selected="selected">选择</option>
		<option value="1">≤</option>
		<option value="0">≥</option>
        </select>
		<input name="compareScore" id="compareScore" onkeyup="isNumber(this)" size="8" maxlength="10" type="text" value="<%=compareScore%>" />
		<select name="pageSize" size="1" class="Select" id="pageSize">
		<option value="30">30条</option>
		<option value="50">50条</option>
		<option value="100">100条</option>
		<option value="200">200条</option>
        </select>
		<select name="orderby" size="1" class="Select" id="orderby">
		<option value="totalScore">总银子</option>
		<option value="RegisterDate">注册时间</option>
		<option value="LastLogonDate">最后登录时间</option>
		<option value="gameID">游戏ID</option>
		<option value="accounts">用户名</option>
        </select>
	    <input name="termOne" id="termOne" type="text" class="input_width2" />
	    <select name="selectOne" id="selectOne" class="input_width2">
	      <option value="accounts">用户名</option>
	      <option value="gameID">游戏ID</option>
		  <option value="RegisterIP">注册地址</option>
		  <option value="LastLogonIP">登陆地址</option>
		  <option value="RegisterMachine">注册机器</option>
		  <option value="LastLogonMachine">登陆机器</option>
		  <option value="tyAccounts">停用账号</option>
        </select>
		<select name="interRoom" id="interRoom" class="input_width2">
		  <option value="0">所有</option>
	      <option value="1">在线</option>
		  <option value="2">手机号码</option>
		  <option value="3">特殊账号</option>
        </select>
	    <input name="Submit2" type="button" class="input" style="cursor:pointer" value="搜索" onClick="search()"/>
	   </label>
	    <%--<input name="Submit" type="button" class="input" value="新增总代" onclick="window.location.href='<%=request.getContextPath()%>/member/gameuserList.do?action=preAdd&spreaderID=<%=spreaderID%>&accounts=<%=accounts%>'" />--%>
      </form>
	</span><% if(type==0){%>游戏用户<%}%><% if(type==2){%>内部用户<%}%><% if(type==3){%>优质用户<%}%></Div>
	<form id="form2" name="form2" method="post" onsubmit="return delCheck()" action="<%=request.getContextPath()%>/member/gameuserList.do?action=delAll">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td colspan="24" class="menutop">
                <div align="left">
                 <%--<c:if test="${roleId==1}" var="true"> <input type="button" style="cursor:pointer" name="Submit3" class="input" value="删除" onclick="tjSubmit(0)" /></c:if>--%>
<%--                  <input type="button" name="Submit4" class="input" style="cursor:pointer" value="冻结" onclick="tjSubmit(1)" />
                  <input type="button" name="Submit5" class="input" style="cursor:pointer" value="解冻" onclick="tjSubmit(2)" />--%>
                  <input type="button" name="Submit6" class="input" style="cursor:pointer" value="设定机器人" onclick="tjSubmit(3)"/>
                  <c:if test="${roleId==1}" var="true"><%-- <input type="button" name="Submit7" class="input" style="cursor:pointer" value="赠银子" onclick="tjSubmit(4)"/>--%>
				 <%-- <input type="button" name="Submit8" class="input" style="cursor:pointer" value="赠积分" onclick="tjSubmit(5)"/>
				  <input type="button" name="Submit9" class="input" style="cursor:pointer" value="赠经验" onclick="tjSubmit(6)"/>
				  <input type="button" name="Submit10" class="input1" style="cursor:pointer" value="内部" onclick="tjSubmit(7)"/>
				  <input type="button" name="Submit11" class="input1" style="cursor:pointer" value="取消内部" onclick="tjSubmit(8)"/>
				  <input type="button" name="Submit12" class="input1" style="cursor:pointer" value="清零积分" onclick="tjSubmit(9)"/>
				  <input type="button" name="Submit13" class="input1" style="cursor:pointer" value="清零逃率" onclick="tjSubmit(10)"/>--%>
				 <%-- <input type="button" name="Submit14" class="input1" style="cursor:pointer" value="优质" onclick="tjSubmit(11)"/>
				  <input type="button" name="Submit15" class="input1" style="cursor:pointer" value="取消优质" onclick="tjSubmit(12)"/>--%>
				  <input type="button" name="Submit16" class="input1" style="cursor:pointer" value="设定上线" onclick="tjSubmit(13)"/>
				  <%--<input type="button" name="Submit16" class="input1" style="cursor:pointer" value="取消特殊" onclick="tjSubmit(14)"/>--%>
				 <!-- <input type="button" name="Submit16" class="input1" style="cursor:pointer" value="赠送设置" onclick="tjSubmit(15)"/>-->
				  </c:if>
                    </div>            </td>
          </tr>
          <tr>
		  	<td class="menutop"><input type="checkbox" name="checkAll" value="checkAll" onClick="checkAllBox(0)" /></td>
            <td class="menutop">序号</td>
            <td class="menutop">游戏ID</td>
            <td class="menutop">所属</td>
            <td class="menutop">用户名</td>
            <td class="menutop">昵称</td>
            <%--<td class="menutop">电话&nbsp;</td>
            <c:if test="${roleId==1}" var="true"><td class="menutop">胜率&nbsp;</td></c:if>--%>
            <td class="menutop">钱庄</td>
            <td class="menutop">游戏银子</td>
            <%-- <td class="menutop">积分</td>
            <td class="menutop">经验</td>
            <td class="menutop">魅力</td>
            <td class="menutop">兑换魅力</td>
            <td class="menutop">会员</td>
            <td class="menutop">管理</td>--%>
            <td class="menutop">最后登录</td>
            <td class="menutop">注册日期</td>
            <td class="menutop">注册IP</td>
            <td class="menutop">登陆</td>
            <td class="menutop">状态</td>
           <%-- <td class="menutop">账户</td>--%>
            <td class="menutop">游戏</td>
            <td class="menutop">操作</td>
          </tr>
		  <c:forEach var="user" items="${userlist}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
		  <td class="tdcenter"><input type="checkbox" id="checkbox" value="${user.userID}" <c:if test="${user.userID==1}" var="true">disabled="disabled"</c:if> name="checkbox" /><input name="type" type="hidden" id="type" value="<%=type%>" /></td>
            <td class="tdcenter"><%=recordIndex++%></td>
            <td class="tdcenter">${user.gameID}</td>
            <td class="tdcenter">&nbsp;<c:if test="${user.spreaderAccounts=='admin'}">总公司</c:if><c:if test="${user.spreaderAccounts!='admin'}">${user.spreaderAccounts}</c:if></td>
            <td class="tdcenter"><%--<a href="<%=request.getContextPath()%>/member/gameuserList.do?action=userLogs&userID=${user.userID}&type=<%=type%>" title="查看操作日志">--%>${user.accounts}<%--</a>--%></td>
            <td class="tdcenter">${user.regAccounts}</td>
           <%--  <td class="tdcenter">${user.registerMobile}&nbsp;</td>
           <c:if test="${roleId==1}" var="true"><td class="tdcenter">${user.winRate}%&nbsp;</td></c:if>--%>
            <td class="tdcenter">${user.insureScore}</td>
            <td class="tdcenter">${user.score}</td>
            <%--  <td class="tdcenter">${user.jifen}</td>
            <td class="tdcenter">${user.experience}</td>
          <td class="tdcenter">${user.loveLiness}</td>
            <td class="tdcenter">${user.present}</td>
            <td class="tdcenter">${user.memberName}</td>
            <td class="tdcenter">${user.memberName}</td>--%>
            <td class="tdcenter">${user.lastLoginTime}</td>
            <td class="tdcenter">${user.registerDate}</td>
            <td class="tdcenter">${user.registerIP}</td>
            <td class="tdcenter">${user.gameLogonTimes}</td>
            <td class="tdcenter">${user.zhStateName}</td>
           <%-- <td class="tdcenter"><font color="${user.accountsCss}">${user.accountsType}</font></td>--%>
            <td class="tdcenter" title="${user.serverRoom}"><c:if test="${user.kindID==0}" var="true"><font color="#999999">离线</font></c:if><c:if test="${user.kindID!=0}" var="true"><font color="blue">在线</font></c:if></td>
          <td class="tdcenter"><a onClick="<c:if test='${user.zhState==0}' var='true'> alert('该账号已启用，无需再次启用'); return false;</c:if>" href="<%=request.getContextPath()%>/member/gameuserList.do?action=zhFrozen&userState=${user.zhState}&userID=${user.userID}&curPage=<%=pageIndex%>">启用</a>/<a onClick="<c:if test="${user.zhState==1||user.zhState==2}" var="true"> alert('该账号已停用或冻结，无需再次停用'); return false;</c:if>"href="<%=request.getContextPath()%>/member/gameuserList.do?action=zhFrozen&userState=${user.zhState}&userID=${user.userID}&curPage=<%=pageIndex%>">停用</a>/<a onClick="<c:if test="${user.zhState==2||user.zhState==1}" var="true"> alert('该账号已冻结或停用，无需再次冻结'); return false;</c:if>"href="<%=request.getContextPath()%>/member/gameuserList.do?action=zhFrozen&userState=3&userID=${user.userID}&curPage=<%=pageIndex%>">冻结</a>/<a onClick="<c:if test="${user.zhState==0}" var="true"> alert('该账号已解冻，无需再次解冻'); return false;</c:if>" href="<%=request.getContextPath()%>/member/gameuserList.do?action=zhFrozen&userState=${user.zhState}&userID=${user.userID}&curPage=<%=pageIndex%>">解冻</a>/<c:if test="${user.userID!=1}" var="true"><a href="<%=request.getContextPath()%>/member/gameuserList.do?action=preUpdate&userID=${user.userID}&curPage=<%=pageIndex%>&type=<%=type%>&selectOne=<%=selectOne%>">详情</a>/</c:if><a href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gamebankRecord&userID=${user.userID}">操作记录</a><%--<c:if test="${user.isAndroid==0}"  var="true"> <a href="<%=request.getContextPath()%>/member/gameuserList.do?action=interUser&userID=${user.userID}&type=0">设为内部账号</a> </c:if> <c:if test="${user.isAndroid==2}"  var="true"> <a href="<%=request.getContextPath()%>/member/gameuserList.do?action=qxinterUser&userID=${user.userID}&type=0">取消内部账号</a> </c:if> /<c:if test="${roleId==1}" var="true">
			<c:if test="${user.spreaderID!=0}" var="true"><a href="<%=request.getContextPath()%>/member/gameAgentList.do?action=MemberPreCun&userID=${user.userID}&spreaderID=${user.spreaderID}&levelID=${user.levelID}&accounts=<%=accounts%>">存入</a></c:if>
			<c:if test="${user.spreaderID==0}" var="true"><a href="<%=request.getContextPath()%>/member/gameuserList.do?action=MemberPreCun&userID=${user.userID}&type=<%=type%>&spreaderID=${user.spreaderID}">存入</a></c:if> / 
			<c:if test="${user.spreaderID!=0}" var="true"><a href="<%=request.getContextPath()%>/member/gameAgentList.do?action=MemberPreQu&userID=${user.userID}&spreaderID=${user.spreaderID}&levelID=${user.levelID}&accounts=<%=accounts%>">取出</a></c:if>
			<c:if test="${user.spreaderID==0}" var="true"><a href="<%=request.getContextPath()%>/member/gameuserList.do?action=MemberPreQu&userID=${user.userID}&type=<%=type%>&spreaderID=${user.spreaderID}">取出</a></c:if> /  <a href="<%=request.getContextPath()%>/member/gameuserList.do?action=deleteUser&userID=${user.userID}&curPage=<%=pageIndex%>&selectOne=<%=selectOne%>&type=<%=type%>" onClick="{if(confirm('删除后该账户将不可恢复！\n\n确定删除吗?')){return true;}return false;}">删除</a></c:if>--%>/<a href="<%=request.getContextPath()%>/member/gameuserList.do?action=prezsGameID&userID=${user.userID}&type=<%=type%>">靓号</a></td>
          </tr>
		  </c:forEach>
		  <tr>
                <td class="tdcenter" colspan="26" align="center" valign="middle" bgcolor="#FFFFFF">
				<font color="red"><c:out value="${returnInfo}"></c:out></font></td>
       </tr>
          <tr>
            <td colspan="25" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
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

var ttype1=document.all.pageSize;
var index1="<%=pageSize %>";
for(var i=0; i<ttype1.options.length; i++){
	if(ttype1.options[i].value==index1){
		ttype1.options[i].selected=true;break;
	}
}

var ttype4=document.all.compareType;
var index4="<%=compareType %>";
for(var i=0; i<ttype4.options.length; i++){
	if(ttype4.options[i].value==index4){
		ttype4.options[i].selected=true;break;
	}
}

var ttype5=document.all.compareScore;
var index5="<%=compareScore %>";
for(var i=0; i<ttype5.length; i++){
	if(ttype5.value==index5){
		ttype5.selected=true;break;
	}
}

/*var ttype6=document.all.interType;
var index6="<%=type %>";
for(var i=0; i<ttype6.options.length; i++){
	if(ttype6.options[i].value==index6){
		ttype6.options[i].selected=true;break;
	}
}
*/
var ttype=document.all.orderby;
var index="<%=orderby %>";
for(var i=0; i<ttype.options.length; i++){
	if(ttype.options[i].value==index){
		ttype.options[i].selected=true;break;
	}
}

var ttype7=document.all.interRoom;
var index7="<%=interRoom %>";
for(var i=0; i<ttype7.options.length; i++){
	if(ttype7.options[i].value==index7){
		ttype7.options[i].selected=true;break;
	}
}
var ttime=document.all.time;
var index8="<%=time %>";
for(var i=0; i<ttime.options.length; i++){
			if(ttime.options[i].value==index8){
				ttime.options[i].selected=true;break;
			}
		}
		
var type10=document.getElementById("type");
var index9="<%=type %>";
for(var i=0; i<type10.options.length; i++){
			if(type10.options[i].value==index9){
				type10.options[i].selected=true;break;
			}
		}
</script>
</html>
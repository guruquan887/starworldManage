<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%
	int recordIndex=1;
	int record1=1;
	int record2=1;
	int pageIndex=1;
	int pageSize=30;
	int userID = -1;
	String orderby="insertTime";
	if(request.getParameter("orderby")!=null){
		orderby=request.getParameter("orderby");
	}
	String startTime="";
	if(request.getParameter("startTime")!=null){
		startTime=request.getParameter("startTime");
	}
	String endTime="";
	if(request.getParameter("endTime")!=null){
		endTime=request.getParameter("endTime");
	}
	if(request.getParameter("userID")!=null){
	    userID = Integer.parseInt(request.getParameter("userID"));
	}
	String accounts="";
	if(request.getAttribute("accounts")!=null){
		accounts=request.getAttribute("accounts").toString();
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
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" type="text/javascript">
function jumppage(value)
   {
      window.location.href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gamerecordList&userID=<%=userID%>&startTime=<%=startTime%>&endTime=<%=endTime%>&curPage="+value;
   }
function changepage(pageNo){
	window.location.href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gamerecordList&userID=<%=userID%>&startTime=<%=startTime%>&endTime=<%=endTime%>&curPage="+pageNo;
}
function search(){
	window.location.href="<%=request.getContextPath()%>/member/gamegoldList.do?action=gamerecordList"
		+"&userID=<%=userID%>"+"&startTime="+document.all.startTime.value+"&endTime="+document.all.endTime.value;
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
function showInfo(obj)
        {
            var ID = "record"+obj.id;
            if(document.getElementById(ID).style.display == "none")
            {
                document.getElementById(ID).style.display = "";
                obj.src = "../images/up.gif"
            }  
            else
            {
                document.getElementById(ID).style.display="none";
                obj.src = "../images/down.gif"
            }
        }
</script>
<body>
	<Div class="title"><span>  
	</span>游戏记录<%=accounts%></Div>
	<form id="form2" name="form2" method="post" onsubmit="return delCheck()" action="<%=request.getContextPath()%>/member/gameuserList.do?action=delAll">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td colspan="11" class="menutop_left" align="left">日期查询
              <input name="startTime" id="startTime" onClick="WdatePicker()" readonly="readonly" type="text"  size="15" />
至
<input name="endTime" id="endTime" onClick="WdatePicker()" readonly="readonly" type="text" size="15" />
<input name="Submit2" type="button" class="input" value="搜索" onClick="search()"/>
<font color="#FF0000">合计：${totalScore}</font></td>
          </tr>
          <tr>
            <td width="60" class="menutop">序号</td>
            <td class="menutop">插入时间</td>
            <td class="menutop">游戏</td>
            <td class="menutop">房间</td>
            <td class="menutop">用户数</td>
            <td class="menutop">机器人数</td>
            <td class="menutop">损耗</td>
            <td class="menutop">税收/服务费</td>
            <td class="menutop">奖牌</td>
            <td class="menutop">开始时间</td>
            <td class="menutop">结束时间</td>
          </tr>
		  <c:forEach var="user" items="${userlist}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter"><%=recordIndex++%></td>
            <td class="tdcenter">${user.insertTime}<img src="../images/down.gif" id="<%=record1++%>" width="11" height="10" style="cursor:pointer" onclick="showInfo(this)"/></td>
            <td class="tdcenter">${user.kindName}</td>
            <td class="tdcenter">${user.serverName}</td>
            <td class="tdcenter">${user.userCount}</td>
            <td class="tdcenter">${user.androidCount}</td>
            <td class="tdcenter">${user.waste}</td>
            <td class="tdcenter">${user.revenue}</td>
            <td class="tdcenter">${user.usermedal}</td>
            <td class="tdcenter">${user.startTime}</td>
            <td class="tdcenter">${user.concludeTime}</td>
          </tr>
		   <tr style="display:none" id="record<%=record2++%>">
                <td colspan="100"> 
                    <table width="100%" border="0" align="center">
                        <tr align="center">
                            <td class="menutop">用户帐号</td>
                            <td class="menutop">是否机器人</td>
                            <td class="menutop">椅子号</td>
                            <td class="menutop">输赢积分</td>
							<td class="menutop">输赢银子</td>
                            <td class="menutop">税收/服务费</td>
                            <td class="menutop">奖牌数目</td>
                            <td class="menutop">游戏时长</td>
                            <td class="menutop">插入时间</td>
                        </tr>   
						<c:forEach var="userRecord" items="${user.list}">
						 <tr class="list" align="center">
                            <td class="tdcenter">${userRecord.accounts}</td>
                            <td class="tdcenter"><c:if test="${userRecord.isAndroid==0}" var="true">否</c:if><c:if test="${userRecord.isAndroid==1}" var="true"><font color="#FF0000">是</font></c:if><c:if test="${userRecord.isAndroid==2}" var="true"><font color="#FF0000">内部账号</font></c:if><c:if test="${userRecord.isAndroid==3}" var="true"><font color="#FF0000">优质账号</font></c:if></td>
                            <td class="tdcenter">${userRecord.chairID}</td>
                            <td class="tdcenter">${userRecord.score}</td>
							<td class="tdcenter">${userRecord.grade}</td>
                            <td class="tdcenter">${userRecord.revenue}</td>
                            <td class="tdcenter">${userRecord.usermedal}</td>
                            <td class="tdcenter">${userRecord.playTimeCount}</td>
                            <td class="tdcenter">${userRecord.insertTime}</td>
                        </tr>   
						</c:forEach>
				  </table>
                </td> 
            </tr>    
		  </c:forEach>
		  <tr>
                <td class="tdcenter" colspan="19" align="center" valign="middle" bgcolor="#FFFFFF">
				<font color="red"><c:out value="${returnInfo}"></c:out></font></td>
       </tr>
          <tr>
            <td colspan="18" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
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
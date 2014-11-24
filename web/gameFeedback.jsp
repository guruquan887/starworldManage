<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %> 
<%
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=10;
	int nullity=0;
	try{
		if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("pageIndex")!=null){
			pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
		}
		if(request.getParameter("nullity")!=null){
			nullity=Integer.parseInt(request.getParameter("nullity"));
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理中心-比赛管理</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
var tone=encodeURI("<%=termOne %>");

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
      window.location.href="<%=request.getContextPath()%>/web/GameMatchInfoList.do?action=gameFeedBackList&curPage="+value+"&nullity=<%=nullity %>&pageSize=<%=pageSize %>&termOne="+tone;
   }
   
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/web/GameMatchInfoList.do?action=gameFeedBackList&curPage="+pageNo+"&termOne="+tone+"&nullity=<%=nullity %>&pageSize=<%=pageSize %>";
}
function undoCheck(){
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
         alert("请选择撤销对象");
         return false;
  }
   return true;
		}	

function search(){
	document.location.href="<%=request.getContextPath()%>/web/issueList.do?termOne="+encodeURI(document.all.termOne.value);
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
      form2.action="<%=request.getContextPath()%>/web/issueDelete.do";
	  form2.submit();
   }
   else if(opId==1){
	  form2.action="<%=request.getContextPath()%>/web/issueChangeState.do?action=pub";
	  form2.submit();  
   }
   else if(opId==2){
	  form2.action="<%=request.getContextPath()%>/web/issueChangeState.do?action=unpub";
	  form2.submit();  
   }

}
   }

</script>
<body>
	<Div class="title"><span>
	  <form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/web/issueList.do">
        <label>
		<select name="nullity" id="nullity" class="Select" onchange="form1.submit()" >
		<option value="0">正常</option>
		<option value="1">冻结</option>
        </select>
        <input type="text" name="termOne" id="termOne" value="" />
        <input name="Submit4" type="button" class="input" value="搜索" onClick="search()"/>
        </label>	  
		<c:forEach var="r1" items="${class}"></c:forEach>
		<label>
	  <input name="Submit" type="button" class="input" value="新增" onclick="window.location.href='<%=request.getContextPath()%>/web/issueadd.jsp'"/>
	  </label>
	  </form>
	</span>
	  反馈管理</Div>
	   <form id="form2" name="form2" method="post" action="">
	   <tr>
            <td colspan="24" class="menutop">
                <div align="left">
                 <input type="button" name="Submit3" class="input" value="删除" onclick="tjSubmit(0)" />
                  <input type="button" name="Submit4" class="input" value="解冻" onclick="tjSubmit(1)" />
                  <input type="button" name="Submit5" class="input" value="冻结" onclick="tjSubmit(2)" />
              </div>            </td>
         </tr>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td width="60" class="menutop"><label>
              <input type="checkbox" name="checkAll" value="checkAll" onClick="checkAllBox(0)" />
            </label></td>
            <td width="60" class="menutop">反馈标题</td>
            <td class="menutop">用户帐号</td>
            <td class="menutop">提交时间</td>
            <td class="menutop">提交地址</td>
            <td class="menutop">浏览次数</td>
            <td class="menutop">回复时间</td>
            <td class="menutop">回复人</td>
            <td class="menutop">禁用状态</td>
          </tr>
		  <c:forEach var="issue" items="${gameFeedBacklist}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter"><label>
              <input type="checkbox" name="checkbox" value="${issue.feedBackID}" />
            </label></td>
            <td onclick="window.location.href='<%=request.getContextPath()%>/web/issuePreUpdate.do?issueID=${issue.feedBackID}'" class="tdcenter">${issue.feedBackTitle}</td>
            <td onclick="window.location.href='<%=request.getContextPath()%>/web/issuePreUpdate.do?issueID=${issue.feedBackID}'" class="tdcenter">${issue.accounts}</td>
            <td onclick="window.location.href='<%=request.getContextPath()%>/web/issuePreUpdate.do?issueID=${issue.feedBackID}'" class="tdcenter">${issue.feedBackDate}</td>
            <td onclick="window.location.href='<%=request.getContextPath()%>/web/issuePreUpdate.do?issueID=${issue.feedBackID}'" class="tdcenter">${issue.clientIP}</td>
            <td onclick="window.location.href='<%=request.getContextPath()%>/web/issuePreUpdate.do?issueID=${issue.feedBackID}'" class="tdcenter">${issue.viewCount}</td>
            <td onclick="window.location.href='<%=request.getContextPath()%>/web/issuePreUpdate.do?issueID=${issue.feedBackID}'" class="tdcenter">${issue.revertDate}</td>
            <td onclick="window.location.href='<%=request.getContextPath()%>/web/issuePreUpdate.do?issueID=${issue.feedBackID}'" class="tdcenter">${issue.username}</td>
            <td onclick="window.location.href='<%=request.getContextPath()%>/web/issuePreUpdate.do?issueID=${issue.feedBackID}'" class="tdcenter"><c:if test="${issue.nullity==0}" var="true">否</c:if><c:if test="${issue.nullity==1}" var="true">是</c:if></td>
          </tr>
		  </c:forEach>
          <tr>
		  <tr>
                <td class="tdcenter" colspan="30" align="center" valign="middle" bgcolor="#FFFFFF">
				<font color="red"><c:out value="${returnInfo}"></c:out></font></td>
       </tr>
            <td colspan="15" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页 
          <c:if test="${page.totalPage>=0}"  var="true"> 
　<a onClick="changepage(1)" style="cursor:hand">首页</a> 
	<a onClick="changepage(${page.curPage-1})" style="cursor:hand">上页</a> 
	<select name="select" onChange="jumppage(this.value);">
          <c:forEach var="i" begin="1" end="${page.totalPage }" step="1"> <option value="${i}" 
              <c:if test="${i==page.curPage}" var="true">selected="selected"</c:if>
            >第${i}页
            </option>
          </c:forEach>
        </select> &nbsp;<a onClick="changepage(${page.curPage+1})" style="cursor:hand">下页</a> 
        <a onClick="changepage(${page.totalPage})" style="cursor:hand">末页</a>		</c:if></td>
          </tr>
        </table>
</form>
</body>
<script type="text/javascript">
var tcalc=document.getElementById("nullity");
var index="<%=nullity %>";
for(var i=0; i<tcalc.options.length; i++){
			if(tcalc.options[i].value==index){
				tcalc.options[i].selected=true;break;
			}
		}
		
var ttype3=document.all.termOne;
var index2="<%=termOne%>";
for(var i=0; i<ttype3.length; i++){
	if(ttype3.value==index2){
		ttype3.selected=true;break;
	}
}

</script>
</html>
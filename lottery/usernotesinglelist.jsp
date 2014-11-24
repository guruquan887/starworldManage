<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page isELIgnored="false" %> 
<%
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=10;
	int roomId = 0;
	String roomName = "";
	String peroidnum = "";
	String time = "";
	int num = 1000;
	String createTime = "";
	String endTime = "";
	try{
		if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("pageIndex")!=null){
			pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
		}
		if(request.getAttribute("roomId")!=null){
			roomId=Integer.parseInt(request.getParameter("roomId").toString());
		}
		if(request.getAttribute("roomName")!=null){
			roomName = String.valueOf(request.getAttribute("roomName"));
		}
		if(request.getAttribute("peroidnum")!=null){
			peroidnum = String.valueOf(request.getAttribute("peroidnum"));
		}
		
		recordIndex=(pageIndex-1)*pageSize+1;
	}catch(Exception e){
		e.printStackTrace();
	}
	recordIndex=(pageIndex-1)*pageSize+1;
	String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>即时注单管理</title>
<link href="../css/user.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
}
-->
</style>
<link href="../css/AdminCss.css" rel="stylesheet" type="text/css" />
</head>
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg); 

function jumppage(value)
   {
      window.location.href="notesingleList.do?action=noteDetails&curPage="+value+"&roomId=<%=roomId%>&peroidnum=															      <%=peroidnum %>";
   }
   	function back(){
		var url='notesingleList.do?action=noteSingleList&roomId=<%=roomId%>&peroidnum=<%=peroidnum %>';
		document.location.href=url;
	}

</script>

<body>
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
    <tr>
          <td height="25" colspan="9" class="menutop"><div style="width:90%; float:left; text-align:center; line-height:25px;">即时注单列表</div>
      </td>
    </tr>
  </table>
  <form id="form1" name="form1" method="post" action="">
 <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
     <tr class="tdright">
      <td height="25">游戏类型：<font color="#FF0000"><%=roomName %></font> 期数为：<font color="#FF0000"><%=peroidnum %></font>
        </label>
        <label>
        <input type="button" name="Submit" value="返回即时注单列表" onClick="back()" />
        </label></td>
     </tr>
</table>
</form>
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
    <tr class="tdcenter">
      <td align="center"><strong>序号</strong></td>
      <td align="center"><strong>用户名称</strong></td>
      <td align="center"><strong>总银子</strong></td>
      <td align="center"><strong>开奖时间</strong></td>
      <td align="center"><strong>下注时间</strong></td>
      <td align="center"><strong>单</strong></td>
      <td align="center"><strong>双</strong></td>
      <td align="center"><strong>大</strong></td>
      <td align="center"><strong>小</strong></td>
      <td align="center"><strong>龙</strong></td>
      <td align="center"><strong>和</strong></td>
      <td align="center"><strong>虎</strong></td>
      <td align="center"><strong>押数</strong></td>
    </tr>
   
    <c:forEach var="notesingle" items="${noteDetailsList}">
    <tr class="tdcenter">
      <td height="25" align="center"><%=recordIndex++ %></td>
      <td align="center">${notesingle.accounts}</td>
      <td align="center" bgcolor="${notesingle.color[0]}">${notesingle.goldBet}</td>
      <td align="center" bgcolor="${notesingle.color[0]}">${notesingle.createTime1}</td>
      <td align="center" bgcolor="${notesingle.color[0]}">${notesingle.createTime}</td>
      <td align="center" bgcolor="${notesingle.color[1]}">${notesingle.single}</td>
      <td align="center" bgcolor="${notesingle.color[2]}">${notesingle.pairs}</td>
      <td align="center" bgcolor="${notesingle.color[3]}">${notesingle.large}</td>
      <td align="center" bgcolor="${notesingle.color[4]}">${notesingle.small}</td>
      <td align="center" bgcolor="${notesingle.color[5]}">${notesingle.serpent}</td>
      <td align="center" bgcolor="${notesingle.color[6]}">${notesingle.peace}</td>
      <td align="center" bgcolor="${notesingle.color[7]}">${notesingle.tiger}</td>
      <td align="center" bgcolor="${notesingle.color[8]}">${notesingle.rate}</td>
    </tr>
    </c:forEach>
	
	<tr class="wenzi_32">
                <td height="25" colspan="15" align="center" valign="middle" bgcolor="#FFFFFF" class="keno2">
				<font color="red"><c:out value="${returnInfo}"></c:out></font></td>
              </tr>
</table>
  <table width="100%" border="0" cellpadding="2" cellspacing="1">
	  <tr class="tdcenter">
       <td colspan="4">总记录:${page.totalRecord}条/${page.totalPage}页 
	     <c:if  test='${page.totalPage>0}'  var='true'> 
        <!--<select name="select" onChange="jumppage(this.value);">
          <c:forEach var="i" begin="1" end="${page.totalPage }" step="1"> <option value="${i}" 
              <c:if test="${i==page.curPage}" var="true">selected="selected"</c:if>
            >第${i}页
            </option>
          </c:forEach>
        </select> -->
　  <a href="notesingleList.do?action=noteDetails&curPage=1&roomId=<%=roomId%>&peroidnum=<%=peroidnum %>">首页</a> 
	<a href="notesingleList.do?action=noteDetails&curPage=${page.curPage-1}&roomId=<%=roomId%>&peroidnum=<%=peroidnum %>">上页</a> 
	&nbsp;<a href="notesingleList.do?action=noteDetails&curPage=${page.curPage+1 }&roomId=<%=roomId%>&peroidnum=<%=peroidnum %>">下页</a> 
        <a href="notesingleList.do?action=noteDetails&curPage=${page.totalPage}&roomId=<%=roomId%>&peroidnum=<%=peroidnum %>">末页</a></c:if></td>
</td>
    </tr>  
  </table>
</body>
</html>

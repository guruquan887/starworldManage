<%@ page contentType="text/html; charset=utf-8" language="java" errorPage=""%>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %> 

<%
	//判断是否已登陆，返回登陆页面
	if (session.getAttribute("USER") == null){
	out.print("<script>alert('对不起，您还没有登录本系统或者您已经超过登录时间!');  window.target='_parent';window.location.href='../login.jsp';</script>");
	return;
	}
	String msg="";
	if(request.getAttribute("msg")!=null){
		msg=(String)request.getAttribute("msg");
	}
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=200;
	int pageIndexOfPlay =1;
	String time = "20000";
	String openCount = "";
	try{
		if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("pageIndex")!=null){
			pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
		}
		if(request.getAttribute("pageIndexOfPlay")!=null){
			pageIndexOfPlay=Integer.parseInt(request.getAttribute("pageIndexOfPlay").toString());
		}
		if(request.getParameter("openCount")!=null){
			openCount=request.getParameter("openCount");
		}
		if(request.getParameter("time")!=null){
			time=request.getParameter("time");
			}
		}catch(Exception e){
			e.printStackTrace();
	}
	recordIndex=(pageIndex-1)*pageSize+1;

%>

<html>
<head>
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>视频管理</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
}
-->
</style>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript"><!--
   function   refresh()   {   
  		/*history.go(0);*/
		document.location.href="<%=request.getContextPath() %>/bjl/sbgameResultManage.do?action=gameResultManageList&pageIndex=<%=pageIndex%>&pageSize=<%=pageSize%>";
  }   
  var t = setTimeout("refresh()",<%=request.getParameter("time")==null?"20000":request.getParameter("time")%>);   	   
   function jumppage(value)
   {
      window.location.href="<%=request.getContextPath() %>/bjl/sbgameResultManage.do?action=gameResultManageList&pageIndex="+value+"&openCount=<%=openCount%>&time=<%=time%>";
   } 
   
   function queryrooms(){
		 var roomId=document.all.roomId.options[document.all.roomId.selectedIndex].value;
		 var roomId1=parseInt(roomId);
		document.location.href="<%=request.getContextPath() %>/bjl/sbgameResultManage.do?action=gameResultManageList&openCount=<%=openCount%>&time=<%=time%>";
}

function orderAward(playID,serverID,betSerial,hgName,videofileName){
   var name = "orderType_"+playID;
   var hgName1=encodeURI(hgName);
   var orderType=document.getElementById(name).options[document.getElementById(name).selectedIndex].value;
   alert("视频ID："+playID+"   结果类别"+orderType+"   单号"+betSerial+"     荷官名称"+hgName1+"    文件名称"+videofileName);
   document.location.href="<%=request.getContextPath()%>/bjl/sbgameResultManage.do?action=updateResult&serverID="+serverID+"&orderType="+orderType+"&playID="+playID+"&betSerial="+betSerial+"&hgName="+hgName+"&videofileName="+videofileName;

}

   function deleteObj(kid)
   {
    if(confirm("确定要删除该信息吗？")){
    window.location.href="<%=request.getContextPath() %>/bjl/sbgameResultManage.do?id="+kid+"&action=deletePlayVideo";
	}
   }  
   
   function preSetOrder(id,hgName){
     window.location.href="<%=request.getContextPath() %>/bjl/sbgameResultManage.do?action=preSetOrder&beforeXh="+id+"&hgName="+hgName;
   }
   
   function isNumber(oNum){
 if(!oNum) return false; //line:160
 if(oNum.value=="") return false;
 var vv = oNum.value.replace(/[^\d]/g,'');
 var strP=/^\d+$/;
  if (!isNaN(oNum.value)) {
        return true;
    } 
  else{
        alert("请输入0-80区间数字");
        oNum.value=vv;
 		oNum.focus();
 		return false; 
    }
}
--></script>
<body>
<form id="form1" name="form1" method="post" action="<%=request.getContextPath() %>/bjl/sbgameResultManage.do?action=gameResultManageList">
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
    <tr class="topbg" align="center">
      <td height="25" colspan=6 class="menutop"><div style="width:100%; float:left; text-align:center; line-height:25px;"><strong>游戏结果管理</strong></div>
      </td>
    </tr>
  </table><br />
  <table border="0" width="100%" cellpadding="2" cellspacing="1" class="table_margin">
    <tr>
      <td colspan="16" align="center" class="menutop"><div align="left">游戏类型
		  <input type="text" size="12" name="openCount" value="<%=openCount %>">
		  <select name="selectOne" size="1" class="Select" id="selectOne">
		      <option value="openCount">按局数</option>
	        </select>
		显示条数<select name="pageSize" size="1" class="Select" id="pageSize">
		<option value="50">显示50条</option>
		<option value="100">显示100条</option>
		<option value="200" selected="selected">显示200条</option>
        </select>
		定时刷新
		<SELECT name="time" onChange="form1.submit()">    
		  <option value="20000">20秒钟刷新一次</OPTION>   
		  <option value="120000">2分钟刷新一次</OPTION>   
		  <option value="300000">5分钟刷新一次</OPTION> 
		  <option value="600000">10分钟刷新一次</OPTION>   
		  </SELECT>  
        <input type="submit" name="Submit" value="查询">
      </div></td>
    </tr>
    <tr>
      <td align="center" class="menutop" width="5%"><b>序号</b></td>
      <td align="center" class="menutop" width="10%"><b>房间号</b></td>
      <td align="center" class="menutop" width="10%"><b>局数</b></td>
      <td align="center" class="menutop" width="10%"><b>单号</b></td>
      <td align="center" class="menutop" width="10%"><b>名称</b></td>
	  <td align="center" class="menutop" width="5%"><b>荷官</b></td>
      <td align="center" class="menutop" width="10%"><b>视频号码</b></td>
      <td align="center" class="menutop" width="10%"><b>视频结果</b></td>
      <td align="center" class="menutop" width="10%"><b>下注详情</b></td>
      <td align="center" class="menutop" width="10%"><b>游戏状态</b></td>
      <td align="center" class="menutop" width="10%"><b>操作</b></td>
    </tr>
    <c:forEach var="dto" items="${videoList}">
		<tr>
	      <td height="25" align="center">
		  <input type="hidden" name="openCount" value="<%=openCount%>">
          <%=recordIndex++ %></td>
	      <td height="25" align="center" class="tdcenter">${dto.roomName}</td>
	      <td height="25" align="center" class="tdcenter">${dto.openCount}</td>
	      <td height="25" align="center" class="tdcenter">${dto.betSerial}</td>
	      <td height="25" align="center" class="tdcenter">${dto.videoName}</td>
		  <td height="25" align="center" class="tdcenter">${dto.hgName}</td>
	      <td height="25" align="center" class="tdcenter">${dto.result}</td>
	      <td height="25" align="center" class="tdcenter">${dto.spResult}</td>
	      <td height="25" align="center" class="tdcenter">${dto.betArea}</td>
	      <td height="25" align="center" class="tdcenter"><%--<c:if test="${dto.gameState==0}" var='true'>--%><font color="${dto.gameStateCss}">${dto.gameStateName}</font><%--</c:if><c:if test="${dto.gameState==1}" var='true'>${dto.gameStateName}</c:if>--%></td>
	      <td height="25" align="center" width="120" class="tdcenter">
		  <input type="button" name="button2" value="替播" onClick="preSetOrder(${dto.id},'${dto.hgName}')" style="cursor:pointer;"/>|
			<input type="button" name="button3" value="删除" onClick="deleteObj(${dto.id })" style="cursor:pointer;"/></td>
	    </tr>
	</c:forEach>
	 <tr>
    <td colspan="18">
		   <table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_margin">
			  <tr>
			   <td colspan="2" class="tdcenter">总记录:${page.totalRecord}条/${page.totalPage}页 
			<c:if test="${page.totalPage>0}" var='true'>
		　<a href="sbgameResultManage.do?action=gameResultManageList&pageIndex=1&openCount=<%=openCount%>&time=<%=time%>">首页</a> 
			<a href="sbgameResultManage.do?action=gameResultManageList&pageIndex=${page.curPage-1}&openCount=<%=openCount%>&time=<%=time%>">上页</a> 
			<select name="select" onChange="jumppage(this.value);">
				  <c:forEach var="i" begin="1" end="${page.totalPage }" step="1"> <option value="${i}" 
					  <c:if test="${i==page.curPage}" var="true">selected="selected"</c:if>
					>第${i}页
					</option>
				  </c:forEach>
				</select>&nbsp;&nbsp;<a href="sbgameResultManage.do?action=gameResultManageList&pageIndex=${page.curPage+1}&openCount=<%=openCount%>&time=<%=time%>">下页</a> 
				<a href="sbgameResultManage.do?action=gameResultManageList&pageIndex=${page.totalPage}&openCount=<%=openCount%>&time=<%=time%>">末页</a>				</c:if>				</td></tr></table>   </td>
   </tr>
  </table>
</form>
</body>
<script type="text/javascript">
var ttime=document.all.time;
var index2="<%=time %>";
for(var i=0; i<ttime.options.length; i++){
			if(ttime.options[i].value==index2){
				ttime.options[i].selected=true;break;
			}
		}

var ttype1=document.all.pageSize;
var index3="<%=pageSize %>";
for(var i=0; i<ttype1.options.length; i++){
	if(ttype1.options[i].value==index3){
		ttype1.options[i].selected=true;break;
	}
}
</script> 
</html>

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
	int roomId = 1;
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
		if(request.getParameter("roomId")!=null){
		   roomId =Integer.parseInt(request.getParameter("roomId"));
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
		document.location.href="<%=request.getContextPath() %>/bjl/gameResultManage.do?action=gameResultManageList&roomId=<%=roomId%>&pageIndex=<%=pageIndex%>&pageSize=<%=pageSize%>";
  }   
  var t = setTimeout("refresh()",<%=request.getParameter("time")==null?"20000":request.getParameter("time")%>);   	   
   function jumppage(value)
   {
      window.location.href="<%=request.getContextPath() %>/bjl/gameResultManage.do?action=gameResultManageList&pageIndex="+value+"&roomId=<%=roomId%>&openCount=<%=openCount%>&time=<%=time%>";
   } 
   
   function queryrooms(){
		 var roomId=document.all.roomId.options[document.all.roomId.selectedIndex].value;
		 var roomId1=parseInt(roomId);
		document.location.href="<%=request.getContextPath() %>/bjl/gameResultManage.do?action=gameResultManageList&roomId="+roomId1+"&openCount=<%=openCount%>&time=<%=time%>";
}

function orderAward(playID,serverID,betSerial,hgName,videofileName){
   var name = "orderType_"+playID;
   var hgName1=encodeURI(hgName);
   var orderType=document.getElementById(name).options[document.getElementById(name).selectedIndex].value;
   alert("视频ID："+playID+"   结果类别"+orderType+"   单号"+betSerial+"     荷官名称"+hgName1+"    文件名称"+videofileName);
   document.location.href="<%=request.getContextPath()%>/bjl/gameResultManage.do?action=updateResult&serverID="+serverID+"&orderType="+orderType+"&playID="+playID+"&betSerial="+betSerial+"&hgName="+hgName+"&videofileName="+videofileName;

}

   function deleteObj(kid)
   {
    if(confirm("确定要删除该信息吗？")){
    window.location.href="<%=request.getContextPath() %>/bjl/gameResultManage.do?id="+kid+"&action=deletePlayVideo";
	}
   }  
   
   function preSetOrder(id,hgName,winlost){
	//alert(id+","+hgName);
     window.location.href="<%=request.getContextPath() %>/bjl/gameResultManage.do?action=preSetOrder&beforeXh="+id+"&hgName="+hgName+"&select_resultOfWinLost="+winlost;
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
<form id="form1" name="form1" method="post" action="<%=request.getContextPath() %>/bjl/gameResultManage.do?action=gameResultManageList">
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_margin">
    <tr align="center">
      <td height="25" colspan=6 class="menutop"><div style="width:100%; float:left; text-align:center; line-height:25px;"><strong>游戏结果管理</strong></div>
      </td>	 
	   <td width="26%" align="right" class="menutop">
	  <c:forEach var="r" items="${roomList}"><a href="<%=request.getContextPath()%>/bjl/gameResultManage.do?action=gameResultManageList&roomId=${r.roomId}">${r.roomName}</a>&nbsp;
	  </c:forEach>
	  <c:if test="${roomList != null}" var='true'>
	  </c:if>
      </td>
    </tr>
    
  </table><br />
  <table border="0" width="100%" cellpadding="2" cellspacing="1" class="table_margin">
    <tr class="title">
      <td colspan="15" align="center" class="tdcenter"><div align="left">游戏类型 
          <select name="roomId" id="roomId" onChange="queryrooms()">
                <c:forEach var="r" items="${roomList}">
                  <option value="${r.roomId}">${r.roomName}</option>	
                </c:forEach>
            </select>
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
    <tr class="title">
      <td align="center" class="menutop">序号</td>
      <td align="center" class="menutop">房间号</td>
      <td align="center" class="menutop">局数</td>
      <td align="center" class="menutop">单号</td>
      <td align="center" class="menutop">名称</td>
    <%--  <td align="center" class="td1"><b>文件名</b></td>--%>
	  <td align="center" class="menutop">荷官</td>
      <td align="center" class="menutop">视频结果</td>
      <td align="center" class="menutop">庄闲结果</td>
      <td align="center" class="menutop">下注时间</td>
      <td align="center" class="menutop">庄</td>
      <td align="center" class="menutop">闲</td>
      <td align="center" class="menutop">平</td>
      <td align="center" class="menutop">游戏状态</td>
      <td align="center" class="tdcenter"><b>操作</b></td>
    </tr>
    <c:forEach var="dto" items="${videoList}">
		<tr>
	      <td height="25" align="center" class="tdcenter"><%=recordIndex++ %>
		  <input type="hidden" name="openCount" value="<%=openCount%>">&nbsp;
          </td>
	      <td width="70" height="25" align="center" class="tdcenter">${dto.roomName}&nbsp;</td>
	      <td width="40" height="25" align="center" class="tdcenter">${dto.openCount}&nbsp;</td>
	      <td align="center" class="tdcenter">${dto.betSerial}&nbsp;</td>
	      <td width="70" height="25" align="center" class="tdcenter">${dto.videoName}&nbsp;</td>
	   <%--   <td height="25" align="center">${dto.fileName}</td>--%>
		  <td width="40" height="25" align="center" class="tdcenter">${dto.hgName}&nbsp;</td>
	      <td width="270" height="25" align="center" class="tdcenter">${dto.result}&nbsp;</td>
	      <td width="70" height="25" align="center" class="tdcenter"><font color="${dto.gameCss}">${dto.resultOfWinLost}&nbsp;</font></td>
	      <td width="120" height="25" align="center" class="tdcenter">${dto.createTime}&nbsp;</td>
	      <td height="25" align="center" class="tdcenter">${dto.zgold}&nbsp;</td>
	      <td height="25" align="center" class="tdcenter">${dto.xgold}&nbsp;</td>
	      <td height="25" align="center" class="tdcenter">${dto.pgold}&nbsp;</td>
	      <td width="70" height="25" align="center" class="tdcenter"><%--<c:if test="${dto.gameState==0}" var='true'>--%><font color="${dto.gameStateCss}">${dto.gameStateName}&nbsp;</font><%--</c:if><c:if test="${dto.gameState==1}" var='true'>${dto.gameStateName}</c:if>--%></td>
	      <td width="120" height="25" align="center" class="tdcenter"><c:if test="${dto.videoType==1}" var='true'>
		  <input name="button2" type="button" style="cursor:pointer;" onClick="preSetOrder(${dto.id},'${dto.hgName}','ZY','${dto.roomID}')" value="替播"/>|
			<input name="button3" type="button" style="cursor:pointer;" onClick="deleteObj(${dto.id })" value="删除"/> <%--<select name="orderType_${dto.playID}" id="orderType_${dto.playID}" onChange="orderAward(${dto.playID},${dto.serverID},'${dto.betSerial}','${dto.hgName}','${dto.fileName}')">
	    <option value="0">选择需更换的结果</OPTION> 
	    <option value="1">庄赢</OPTION>   
  		<option value="2">闲赢</OPTION> 
		<option value="3">平赢</OPTION>           
	  </select>--%></c:if><c:if test="${dto.videoType==0}" var='true'><font color="orange">等待视频</font></c:if></td>
	    </tr>
	</c:forEach>
	 <tr>
    <td colspan="17">
		   <table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_margin">
			  <tr>
			   <td colspan="2" class="tdcenter">总记录:${page.totalRecord}条/${page.totalPage}页 
			<c:if test="${page.totalPage>0}" var='true'>
		　<a href="gameResultManage.do?action=gameResultManageList&pageIndex=1&roomId=<%=roomId%>&openCount=<%=openCount%>&time=<%=time%>">首页</a> 
			<a href="gameResultManage.do?action=gameResultManageList&pageIndex=${page.curPage-1}&roomId=<%=roomId%>&openCount=<%=openCount%>&time=<%=time%>">上页</a> 
			<select name="select" onChange="jumppage(this.value);">
				  <c:forEach var="i" begin="1" end="${page.totalPage }" step="1"> <option value="${i}" 
					  <c:if test="${i==page.curPage}" var="true">selected="selected"</c:if>
					>第${i}页
					</option>
				  </c:forEach>
				</select>&nbsp;&nbsp;<a href="gameResultManage.do?action=gameResultManageList&pageIndex=${page.curPage+1 }&roomId=<%=roomId%>&openCount=<%=openCount%>&time=<%=time%>">下页</a> 
				<a href="gameResultManage.do?action=gameResultManageList&pageIndex=${page.totalPage}&roomId=<%=roomId%>&openCount=<%=openCount%>&time=<%=time%>">末页</a>				</c:if>				</td></tr></table>   </td>
   </tr>
  </table>

</form>
</body>
<script type="text/javascript">
var tstate=document.all.roomId;
var index1="<%=roomId %>";
for(var i=0; i<tstate.options.length; i++){
			if(tstate.options[i].value==index1){
				tstate.options[i].selected=true;break;
			}
		}
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

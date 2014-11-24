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
	int pageSize=50;
	int pageIndexOfPlay =1;

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
		}catch(Exception e){
			e.printStackTrace();
	}
	recordIndex=(pageIndex-1)*pageSize+1;
%>

<html>
<head>
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
<script language="javascript" src="<%=request.getContextPath()%>/js/bbtj.js"></script>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript"><!--
		   
   function jumppage(value)
   {
      window.location.href="<%=request.getContextPath() %>/bjl/sbvideoPlayList.do?pageIndex="+value+"&action=list";
   }
   
   function deleteObj(kid)
   {
    if(confirm("确定要删除该信息吗？")){
    window.location.href="<%=request.getContextPath() %>/bjl/sbvideoPlayList.do?id="+kid+"&action=deletePlayVideo";
	}
   }  
   
   function preSetOrder(xh,hgName,winlost){
     window.location.href="<%=request.getContextPath() %>/bjl/sbvideoPlayList.do?action=preSetOrder&beforeXh="+xh+"&hgName="+hgName+"&select_resultOfWinLost="+winlost;
   }
  var hash ={pageIndexOfPlay:'<%=pageIndexOfPlay %>'};
--></script>
<body onLoad="loadRoom(hash)">
<form id="form1" name="form1" method="post" action="<%=request.getContextPath() %>/bjl/sbvideoPlayList.do?action=createlist">
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_margin">
    <tr class="topbg" align="center">
      <td height="25" colspan=6 class="menutop">&nbsp;&nbsp;<strong>播放列表</strong></td>
	  <td width="75%" align="right" class="menutop">&nbsp;&nbsp;&nbsp;&nbsp;
	  <input name="button" type="submit" value="重新生成播放列表"/>
      </td>
    </tr>
  </table>
  <br />
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_margin">
    <tr>
      <td align="center" class="menutop"><b>序号</b></td>
      <td align="center" class="menutop"><b>视频名称</b></td>
      <td align="center" class="menutop"><b>文件名</b></td>
      <td align="center" class="menutop"><b>开牌结果</b></td>
      <td align="center" class="menutop"><b>荷官</b></td>
	  <td align="center" class="menutop"><b>视频长度[秒]</b></td>
      <td align="center" class="menutop"><b>结果时间</b></td>
      <td align="center" class="menutop"><b>结果总和</b></td>
      <%--<td align="center" class="td1"><b>状态</b></td>--%>
      <td align="center" class="menutop"><b>操作</b></td>
    </tr>
    <c:forEach var="dto" items="${videoPlayList}">
		 <c:if test="${dto.state != 2}" var='true'>
		<tr onMouseOver="javascript:this.style.backgroundColor='#BFDFFF'" onMouseOut="javascript:this.style.backgroundColor=''" style="font-family:宋体;">
	      <td height="25" align="center" class="tdcenter"><%=recordIndex++ %></td>
	      <td height="25" align="center" class="tdcenter">${dto.videoName }</td>
	      <td height="25" align="center" class="tdcenter">${dto.fileName }</td>
	      <td height="25" align="center" class="tdcenter">${dto.result }</td>
	      <td height="25" align="center" class="tdcenter">${dto.hgName }</td>
		  <td height="25" align="center" class="tdcenter">${dto.videolength }</td>
		  <td height="25" align="center" class="tdcenter">${dto.resultTime }</td>
	      <td height="25" align="center" class="tdcenter">${dto.resultAnd }</td>
	      <%--<td height="25" align="center">${dto.stateName }</td>--%>
	      <td height="25" align="center" class="tdcenter">
	      <%--<input type="button" name="button2" value="插播" onClick="preSetOrder(${dto.xh},'${dto.hgName}','ZY')" style="cursor:pointer;"/>|
			--%><input type="button" name="button3" value="删除" onClick="deleteObj(${dto.playID })" style="cursor:pointer;"/>	      </td>
	    </tr>
		</c:if>
	</c:forEach>
	 <tr>
    <td colspan="10">
		   <table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_margin">
			  <tr>
			   <td colspan="2" class="kai_keno_title">总记录:${page.totalRecord}条/${page.totalPage}页 
			<c:if test="${page.totalPage>0}" var='true'>
		　<a href="sbvideoPlayList.do?action=list&pageIndex=1">首页</a> 
			<a href="sbvideoPlayList.do?action=list&pageIndex=${page.curPage-1}">上页</a> 
			<select name="select" onChange="jumppage(this.value);">
				  <c:forEach var="i" begin="1" end="${page.totalPage }" step="1"> <option value="${i}" 
					  <c:if test="${i==page.curPage}" var="true">selected="selected"</c:if>
					>第${i}页
					</option>
				  </c:forEach>
				</select>&nbsp;&nbsp;<a href="sbvideoPlayList.do?action=list&pageIndex=${page.curPage+1 }">下页</a> 
				<a href="sbvideoPlayList.do?action=list&pageIndex=${page.totalPage}">末页</a>				</c:if>				</td></tr></table>   </td>
   </tr>
  </table>

</form>
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
</script>
</body>
</html>

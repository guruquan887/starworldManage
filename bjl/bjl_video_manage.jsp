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
		   
   function jumppage(value)
   {
      window.location.href="<%=request.getContextPath() %>/bjl/video.do?pageIndex="+value+"&action=list";
   }
   
   function deleteObj(kid)
   {
    if(confirm("确定要删除该信息吗？")){
    window.location.href="<%=request.getContextPath() %>/bjl/video.do?id="+kid+"&action=deleteVideo";
	}
   }  
   
   function preUpdateObj(id){
     window.location.href="<%=request.getContextPath() %>/bjl/video.do?id="+id+"&pageIndex=<%=pageIndex %>&action=preUpdateVideo&pageSize=<%=pageSize %>";
   }
--></script>
<body>
<form id="form1" name="form1" method="post" action="adminDel.do">
<Div class="title">
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_margin">
    <tr align="center">
      <td height="25" colspan=6 class="menutop"><jsp:include page="topmenu.jsp" flush="false"></jsp:include><div style="width:75%; float:left; text-align:center; line-height:25px;"><a href="<%=request.getContextPath() %>/bjl/video.do?action=list"><strong>视频管理</strong></a></div><%--<input type="button" name="button" value="添加视频"  onclick="window.location.href='<%=request.getContextPath() %>/bjl/video.do?action=videoPreAdd'"/> <input type="button" name="button" value="生成播放列表"  onclick="window.location.href='<%=request.getContextPath() %>/bjl/videoPlayList.do?action=createlist'"/>--%></td>
    </tr>
    
  </table></div>
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_margin">
    <tr>
      <td align="center" class="menutop"><b>序号</b></td>
      <td align="center" class="menutop"><b>名称</b></td>
      <td align="center" class="menutop"><b>文件名</b></td>
      <td align="center" class="menutop"><b>视频ID</b></td>
      <td align="center" class="menutop"><b>开牌结果</b></td>
      <td align="center" class="menutop"><b>荷官</b></td>
      <td align="center" class="menutop"><b>视频长度（秒）</b></td>
      <td align="center" class="menutop"><b>视频类型</b></td>
      <td align="center" class="menutop"><b>操作</b></td>
    </tr>
    <c:forEach var="dto" items="${videoList}">
		<tr onMouseOver="this.className='trover'" onMouseOut="this.className='trout'">
	      <td height="25" class="tdcenter"><%=recordIndex++ %></td>
	      <td height="25" class="tdcenter">${dto.videoName }</td>
	      <td height="25" class="tdcenter">${dto.fileName }</td>
	      <td height="25" class="tdcenter">${dto.videoID }</td>
	      <td height="25" class="tdcenter">${dto.result }&nbsp;</td>
	      <td height="25" class="tdcenter">${dto.hgName }</td>
	      <td height="25" class="tdcenter">${dto.videolength}</td>
	      <td height="25" class="tdcenter">${dto.videoTypeName }</td>
	      <td height="25" class="tdcenter">
	      	<input type="button" name="button2" value="修改" onClick="preUpdateObj(${dto.videoID })" style="cursor:hand"/>|
			<input type="button" name="button3" value="删除" onClick="deleteObj(${dto.videoID })" style="cursor:hand"/>	      </td>
	    </tr>
	</c:forEach>
	 <tr>
    <td colspan="9">
		   <table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_margin">
			  <tr class="main_table_center_center">
			   <td colspan="2" class="tdcenter">总记录:${page.totalRecord}条/${page.totalPage}页 
			<c:if test="${page.totalPage>0}" var='true'>
		　<a href="<%=request.getContextPath()%>/bjl/video.do?action=list&pageIndex=1">首页</a> 
			<a href="<%=request.getContextPath()%>/bjl/video.do?action=list&pageIndex=${page.curPage-1}">上页</a> 
			<select name="select" onChange="jumppage(this.value);">
				  <c:forEach var="i" begin="1" end="${page.totalPage }" step="1"> <option value="${i}" 
					  <c:if test="${i==page.curPage}" var="true">selected="selected"</c:if>
					>第${i}页
					</option>
				  </c:forEach>
				</select>&nbsp;&nbsp;<a href="<%=request.getContextPath()%>/bjl/video.do?action=list&pageIndex=${page.curPage+1 }">下页</a> 
				<a href="<%=request.getContextPath()%>/bjl/video.do?action=list&pageIndex=${page.totalPage}">末页</a>				</c:if>				</td></tr></table>   </td>
   </tr>
  </table>

</form>
</body>
</html>

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
	int pageSize=10;
	int roomID = 0;

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
	
	String select_resultOfWinLost = "";
	String hgName = "";
	String beforeXh = "";
	if(request.getParameter("select_resultOfWinLost")!=null){
		select_resultOfWinLost=request.getParameter("select_resultOfWinLost");
		//result = new String(termOne.getBytes("ISO_8859_1"),"UTF-8");
	}
	if(request.getAttribute("roomID")!=null){
	   roomID = (Integer)request.getAttribute("roomID");
	}
	if(request.getParameter("hgName")!=null){
		hgName=request.getParameter("hgName");
		hgName = new String(hgName.getBytes("ISO_8859_1"),"UTF-8");
	}
	if(request.getParameter("beforeXh")!= null){
	    beforeXh=request.getParameter("beforeXh");
	}
	if(request.getAttribute("beforeXh")!=null){
		beforeXh=String.valueOf(request.getAttribute("beforeXh"));
	}

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
	var tone=encodeURI("<%=hgName %>");	   
   function jumppage(value)
   {
      window.location.href="<%=request.getContextPath() %>/bjl/videoPlayList.do?pageIndex="+value+"&action=preSetOrder&select_resultOfWinLost=<%=select_resultOfWinLost %>&hgName=tone";
   }
   
   function search(){
    //var hg_Name = document.getElementById("_hgName").value;
    var str ="<%=request.getContextPath() %>/bjl/gameResultManage.do?action=preSetOrder&beforeXh="+document.getElementById("beforeXh").value+"&select_resultOfWinLost="+document.all.select_resultOfWinLost.options[document.all.select_resultOfWinLost.selectedIndex].value+"&hgName="+encodeURI(document.getElementById("_hgName").value);
	document.location.href=str;
   }   
   
   function setOrder(){
		 var flag10=0;
         var radio10=document.getElementsByName("radio");
		 var beforeXh = document.getElementById("beforeXh").value;
		 //alert("beforeXh"+beforeXh+"  "+document.all.select_resultOfWinLost.options[document.all.select_resultOfWinLost.selectedIndex].value+"  荷官名称："+encodeURI(document.getElementById("_hgName").value));
         for(var i=0;i<radio10.length;i++)
		   {
				 if(radio10.item(i).checked==true)
					{
					   flag10=1;
					   break;
				    }
				  }
				  if(!flag10){
					   alert("请选择插播对象");
					   return false;
				  }
		  	     return true;
		   }
   
--></script>

<body>
<form id="form1" name="form1" method="post" action="gameResultManage.do?action=setOrder">
<input type="hidden" name="beforeXh" value="<%=beforeXh %>" id="beforeXh" />
<input type="hidden" name="roomID" value="<%=roomID %>" id="roomID" />
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_margin">
    <tr align="center">
      <td height="25" colspan=6 class="menutop"><div style="width:90%; float:left; text-align:center; line-height:25px;"><a href="<%=request.getContextPath() %>/bjl/video.do?action=list"><strong>插播|视频列表</strong></a></div>
      </td>
    </tr>
      <tr>
    <td class="keno_td_left_menu">&nbsp;荷官名称<input type="text"  name="_hgName" id="_hgName" value="<%=hgName %>" size="15" />&nbsp;显示&nbsp;<select name="select_resultOfWinLost" size="1" id="select_resultOfWinLost"> 
		<option value="ZY">庄赢</option>
		<option value="XY">闲赢</option>
		<option value="HJ">和局</option>
		<option value="DD">等待</option>
        </select>&nbsp;
		<input type="button" name="button" value="查询" onClick="search()"/>
		</td>
  </tr>
  </table><br />
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_margin">
    <tr class="title">
      <td align="center" class="menutop"><b>序号</b></td>
      <td align="center" class="menutop"><b>选择</b></td>
	  <td align="center" class="menutop"><b>视频名称</b></td>
      <td align="center" class="menutop"><b>文件名</b></td>
      <td align="center" class="menutop"><b>开牌结果</b></td>
      <td align="center" class="menutop"><b>视频长度</b></td>
      <td align="center" class="menutop"><b>输赢结果</b></td>
      <td align="center" class="menutop"><b>荷官</b></td>
      <td align="center" class="menutop"><b>视频类型</b></td>
    </tr>
     <c:forEach var="dto" items="${videoList}">
		<tr onMouseOver="javascript:this.style.backgroundColor='#BFDFFF'" onMouseOut="javascript:this.style.backgroundColor=''" style="font-family:宋体;">
	      <td height="25" align="center" class="tdcenter"><%=recordIndex++ %></td>
		  <td height="25" align="center" class="tdcenter"><input type="radio" name="radio" value="${dto.videoID }" id="radio" /></td>
	      <td height="25" align="center" class="tdcenter">${dto.videoName }&nbsp;</td>
	      <td height="25" align="center" class="tdcenter">${dto.fileName }&nbsp;</td>
	      <td height="25" align="center" class="tdcenter">${dto.result }&nbsp;</td>
	      <td height="25" align="center" class="tdcenter">${dto.videolength}&nbsp;</td>
	      <td height="25" align="center" class="tdcenter">${dto.resultOfWinLostName }&nbsp;</td>
		  <td height="25" align="center" class="tdcenter">${dto.hgName }&nbsp;</td>
	      <td height="25" align="center" class="tdcenter">${dto.videoTypeName }&nbsp;</td>
	    </tr>
	</c:forEach>
	<c:if test="${returnInfo != null}" var='true'>
    <tr align="center"><td colspan="9" class="tdcenter"><font color="red"><c:out value="${returnInfo}"></c:out></font></td></tr>
    </c:if>
    <c:if test="${page.totalPage>0}" var='true'>
	<tr>
      <td colspan="9" class="tdlefts"><label> &nbsp;&nbsp;&nbsp;
            <input type="submit" name="Submit" value="确定插播" onClick="return setOrder()" />
      </label></td>
    </tr>
     <tr>
    <td colspan="9">
		     <table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_margin">
			  <tr>
			   <td colspan="2" class="tdcenter">总记录:${page.totalRecord}条/${page.totalPage}页 
			<c:if test="${page.totalPage>0}" var='true'>
		　<a href="videoPlayList.do?action=preSetOrder&pageIndex=1&select_resultOfWinLost=<%=select_resultOfWinLost %>&hgName=<%=hgName %>">首页</a> 
			<a href="videoPlayList.do?action=preSetOrder&pageIndex=${page.curPage-1}&select_resultOfWinLost=<%=select_resultOfWinLost %>&hgName=<%=hgName %>">上页</a> 
			<select name="select" onChange="jumppage(this.value);">
				  <c:forEach var="i" begin="1" end="${page.totalPage }" step="1"> <option value="${i}" 
					  <c:if test="${i==page.curPage}" var="true">selected="selected"</c:if>
					>第${i}页
					</option>
				  </c:forEach>
				</select>&nbsp;&nbsp;<a href="videoPlayList.do?action=preSetOrder&pageIndex=${page.curPage+1 }&select_resultOfWinLost=<%=select_resultOfWinLost %>&hgName=<%=hgName %>">下页</a> 
				<a href="videoPlayList.do?action=preSetOrder&pageIndex=${page.totalPage}&select_resultOfWinLost=<%=select_resultOfWinLost %>&hgName=<%=hgName %>">末页</a>				</c:if>				</td></tr></table>   </td>
   </tr>
  </c:if>
  </table>

</form>
</body>
<script type="text/javascript">

var ttype1=document.all.select_resultOfWinLost;
var index1="<%=select_resultOfWinLost %>";
for(var i=0; i<ttype1.options.length; i++){
	if(ttype1.options[i].value==index1){
		ttype1.options[i].selected=true;break;
	}
}

var ttype3=document.all._hgName;
var index2="<%=hgName%>";
for(var i=0; i<ttype3.length; i++){
	if(ttype3.value==index2){
		ttype3.selected=true;break;
	}
}


  var msg="<%=msg %>";
  if(msg!='')alert(msg);
</script>
</html>

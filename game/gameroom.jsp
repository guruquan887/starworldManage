<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%
 String msg="";
 if(request.getAttribute("msg")!=null){
  msg=request.getAttribute("msg").toString();
 }

 String search="";
 if(request.getParameter("roomName")!=null){
 	search = request.getParameter("roomName");
	search = java.net.URLDecoder.decode(search,"UTF-8");
 }
 int pageIndex=1;
 int pageSize=30;
 if(request.getAttribute("pageSize")!=null){
	pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
	}	
 if(request.getAttribute("pageIndex")!=null){
	pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
 }
 int i=1;
 i=(pageIndex-1)*pageSize+1;
 
 int maximum_=0;
 int tax_=0;
 int androidUserScore_=0;
	if(request.getAttribute("maximum_")!=null){
	 maximum_=Integer.parseInt(request.getAttribute("maximum_").toString());
	}
	if(request.getAttribute("tax_")!=null){
	 tax_=Integer.parseInt(request.getAttribute("tax_").toString());
	}	
	if(request.getAttribute("androidUserScore_")!=null){
	 androidUserScore_=Integer.parseInt(request.getAttribute("androidUserScore_").toString());
	}		
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<script language="javascript" type="text/javascript">
       //弹出友好提示
       var msg="<%=msg %>";
       if(msg!='')alert(msg);
</script>
<script src="../js/jquery.min.js" type="text/javascript"></script>
<script language="javascript" src="../js/ajax.js"></script>
<script language="javascript" type="text/javascript">
  
  var orderby = "";	
  var typeNumber = "";

  function _doPost(id,url){
  var urlPath ;
  urlPath = url+"&ran="+parseInt(Math.random()*1000+1);
  //alert(id+","+urlPath);
   startRequest(id,urlPath);
 }		
		
  function closeDiv(id){
	var objtable=document.getElementById(id); 
    objtable.parentNode.removeChild(objtable); 
  	//document.getElementById(id).style.display='none';
  }	
  
  function jumppage(value)
   {
      window.location.href="<%=request.getContextPath()%>/game/roomManage.do?action=rList&curPage=" +value;
   }
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/game/roomManage.do?action=rList&curPage="+pageNo;
}
</script>
<body>
	<Div class="title">游戏房间</Div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td width="60" class="menutop">序号</td>
            <td class="menutop">游戏房间</td>
            <td class="menutop">银子下限额度</td>
            <td class="menutop">最大人数</td>
            <td class="menutop">税收</td>
            <td class="menutop">房间状态</td>
            <td class="menutop">操作</td>
          </tr>
		  <c:forEach var="dto" items="${rtypeList}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter"><%=i%></td>
            <td class="tdcenter">${dto.roomName}</td>
            <td class="tdcenter">${dto.androidUserScore}</td>
            <td class="tdcenter">${dto.maximum}</td>
            <td class="tdcenter">${dto.tax}</td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/game/roomManage.do?action=updateState&roomID=${dto.roomID}&roomState=${dto.roomState}" style="cursor:pointer;"><span class="${dto.roomStateCss}">${dto.roomStateName}</span></a></td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/game/roomManage.do?action=preUpdate&roomID=${dto.roomID}" style="cursor:pointer;">修改</a></td>
          </tr>
		  <% i++;%>
		  </c:forEach>
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
		  <div style="display:none;">
    <input name="androidUserScore_" id="androidUserScore_" value="<%=androidUserScore_%>" />
    <input name="maximum_" id="maximum_" value="<%=maximum_%>" />
    <input name="tax_" id="tax_" value="<%=tax_%>" />
  </div>
</body>
</html>
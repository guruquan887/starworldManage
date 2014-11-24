<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<% 
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires",0); 
%> 
<%	
	int zC = 1;//全部
	int time = 10000;
	int kid = 0;
	try{
		if(request.getParameter("time")!=null){
			time=Integer.parseInt(request.getParameter("time"));
		}
		if(request.getParameter("zc")!=null){
			zC=Integer.parseInt(request.getParameter("zc"));
		}
		if(request.getParameter("kid")!=null){
			kid=Integer.parseInt(request.getParameter("kid"));
		}
	}catch(Exception e){
		e.printStackTrace();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>即时注单</title>
<link href="../css/user.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="<%=request.getContextPath()%>/js/ajax.js"></script>
</head>
<script>
function refresh(){
	var d = document.getElementById("dform");
	d.submit();
} 
setTimeout("refresh()",<%=request.getParameter("time")==null?"30000":request.getParameter("time")%>); 

 function closeDiv(id){
	var objtable=document.getElementById(id); 
	objtable.parentNode.removeChild(objtable);
 }
function jumppage(value,betSerial,gameArea){
    var betSerial = document.getElementById("betSerial_").value;
	var gameArea = document.getElementById("gameArea_").value;
    var url="<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&curPage="+value+"&betSerial="+betSerial+"&gameArea="+gameArea;
	return url;
 }
function changepage(pageNo,betSerial,gameArea){
	var betSerial = document.getElementById("betSerial_").value;
	var gameArea = document.getElementById("gameArea_").value;
	var url="<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&curPage="+pageNo+"&betSerial="+betSerial+"&gameArea="+gameArea;
	return url;
 }
</script>
<body style="background:#fff;">
  <form id="dform" name="dform" method="post" action="<%=request.getContextPath()%>/note/noteList.do?action=detailInfo">
  <input type="hidden" value="${dto.betSerial}" name="betSerial" />
  <input type="hidden" value="<%=kid%>" name="kid" />
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="border">
    <tr>
          <td height="25" colspan="9" class="menutop">
		  <div style="float:left; text-align:right;line-height:25px; padding-top:3px;">
		  &nbsp;&nbsp;■&nbsp;<font color="#FF0000">轮盘</font>&nbsp;&nbsp;&nbsp; 《 期数 - ${dto.betSerial} - <font color="#0000FF">状态∽</font><font color="#FFCC66">停止下注</font> 》</div>
		  <div style="width:50%; float:left; text-align:right; line-height:25px;padding-top:3px;">
		  更新时间 :<font color="#FFCC66">2011-1-20 15:15:20</font>
		  &nbsp;&nbsp;占成<select name="zc" id="zc" onchange="dform.submit()"><option value="1">占成</option><option value="0">全部</option></select>
		  &nbsp;&nbsp;盘类<select><option>A盘</option><option>B盘</option></select>
		  &nbsp;&nbsp;<select name="time" id="time" onchange="dform.submit()"><option value="10000"> 10 秒刷新 </option><option value="30000"> 30 秒刷新 </option><option value="60000"> 1 分钟刷新 </option><option value="180000"> 3 分钟刷新 </option></select>
		  </div>
		  <div style="float:right; text-align:right;line-height:25px; padding-top:3px;">
		  <input type="button" onclick="dform.submit()" value="手动刷新" style="cursor:pointer;" />&nbsp;
		  <input type="button" onclick="document.location.href='<%=request.getContextPath()%>/note/noteList.do?action=list'" value="<<返回" style="cursor:pointer;" />
		  </div>
      </td>
    </tr>
</table>
 </form> 
  <table width="100%" border="0" cellspacing="0" cellpadding="4" class="">
    <tr>
      <td width="20%"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tables">
        <tr>
          <td width="20%" class="th">号码</td>
          <td width="25%" class="th">笔数</td>
          <td class="th">金额</td>
        </tr>
        <tr>
          <td class="thyellow"><font color="red">1</font></td>
          <td class="t_center">${dto.count_1}&nbsp;<c:if test="${dto.count_1 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2001');">${dto.num_1}</a>&nbsp;<c:if test="${dto.num_1 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow">2</td>
          <td class="t_center">${dto.count_2}&nbsp;<c:if test="${dto.count_2 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2002');">${dto.num_2}</a>&nbsp;<c:if test="${dto.num_2 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow"><font color="red">3</font></td>
          <td class="t_center">${dto.count_3}&nbsp;<c:if test="${dto.count_3 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2003');">${dto.num_3}</a>&nbsp;<c:if test="${dto.num_3 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow">4</td>
          <td class="t_center">${dto.count_4}&nbsp;<c:if test="${dto.count_4 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2004');">${dto.num_4}</a>&nbsp;<c:if test="${dto.num_4 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow"><font color="red">5</font></td>
          <td class="t_center">${dto.count_5}&nbsp;<c:if test="${dto.count_5 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2005');">${dto.num_5}</a>&nbsp;<c:if test="${dto.num_5 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow">6</td>
          <td class="t_center">${dto.count_6}&nbsp;<c:if test="${dto.count_6 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2006');">${dto.num_6}</a>&nbsp;<c:if test="${dto.num_6 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow"><font color="red">7</font></td>
          <td class="t_center">${dto.count_7}&nbsp;<c:if test="${dto.count_7 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2007');">${dto.num_7}</a>&nbsp;<c:if test="${dto.num_7 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow">8</td>
          <td class="t_center">${dto.count_8}&nbsp;<c:if test="${dto.count_8 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2008');">${dto.num_8}</a>&nbsp;<c:if test="${dto.num_8 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow"><font color="red">9</font></td>
          <td class="t_center">${dto.count_9}&nbsp;<c:if test="${dto.count_9 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2009');">${dto.num_9}</a>&nbsp;<c:if test="${dto.num_9 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
      </table></td>
      <td width="20%"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tables">
        <tr>
          <td width="20%" class="th">号码</td>
          <td width="25%" class="th">笔数</td>
          <td class="th">金额</td>
        </tr>
        <tr>
          <td class="thyellow">10</td>
          <td class="t_center">${dto.count_10}&nbsp;<c:if test="${dto.count_10 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2010');">${dto.num_10}</a><c:if test="${dto.num_10 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow">11</td>
          <td class="t_center">${dto.count_11}&nbsp;<c:if test="${dto.count_11 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2011');">${dto.num_11}</a><c:if test="${dto.num_11 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow"><font color="red">12</font></td>
          <td class="t_center">${dto.count_12}&nbsp;<c:if test="${dto.count_12 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2012');">${dto.num_12}</a><c:if test="${dto.num_12 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow">13</td>
          <td class="t_center">${dto.count_13}&nbsp;<c:if test="${dto.count_13 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2013');">${dto.num_13}</a><c:if test="${dto.num_13 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow"><font color="red">14</font></td>
          <td class="t_center">${dto.count_14}&nbsp;<c:if test="${dto.count_14 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2014');">${dto.num_14}</a><c:if test="${dto.num_14 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow">15</td>
          <td class="t_center">${dto.count_15}&nbsp;<c:if test="${dto.count_15 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2015');">${dto.num_15}</a><c:if test="${dto.num_15 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow"><font color="red">16</font></td>
          <td class="t_center">${dto.count_16}&nbsp;<c:if test="${dto.count_16 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2016');">${dto.num_16}</a><c:if test="${dto.num_16 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow">17</td>
          <td class="t_center">${dto.count_17}&nbsp;<c:if test="${dto.count_17 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2017');">${dto.num_17}</a><c:if test="${dto.num_17 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow"><font color="red">18</font></td>
          <td class="t_center">${dto.count_18}&nbsp;<c:if test="${dto.count_18 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2018');">${dto.num_18}</a><c:if test="${dto.num_18 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
      </table></td>
      <td width="20%"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tables">
        <tr>
          <td width="20%" class="th">号码</td>
          <td width="25%" class="th">笔数</td>
          <td class="th">金额</td>
        </tr>
        <tr>
          <td class="thyellow"><font color="red">19</font></td>
          <td class="t_center">${dto.count_19}&nbsp;<c:if test="${dto.count_19 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2019');">${dto.num_19}</a><c:if test="${dto.num_19 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow">20</td>
          <td class="t_center">${dto.count_20}&nbsp;<c:if test="${dto.count_20 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2020');">${dto.num_20}</a><c:if test="${dto.num_20 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow"><font color="red">21</font></td>
          <td class="t_center">${dto.count_21}&nbsp;<c:if test="${dto.count_21 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2021');">${dto.num_21}</a><c:if test="${dto.num_21 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow">22</td>
          <td class="t_center">${dto.count_22}&nbsp;<c:if test="${dto.count_22 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2022');">${dto.num_22}</a><c:if test="${dto.num_22 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow"><font color="red">23</font></td>
          <td class="t_center">${dto.count_23}&nbsp;<c:if test="${dto.count_23 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2023');">${dto.num_23}</a><c:if test="${dto.num_23 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow">24</td>
          <td class="t_center">${dto.count_24}&nbsp;<c:if test="${dto.count_24 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2024');">${dto.num_24}</a><c:if test="${dto.num_24 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow"><font color="red">25</font></td>
          <td class="t_center">${dto.count_25}&nbsp;<c:if test="${dto.count_25 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2025');">${dto.num_25}</a><c:if test="${dto.num_25 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow">26</td>
          <td class="t_center">${dto.count_26}&nbsp;<c:if test="${dto.count_26 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2026');">${dto.num_26}</a><c:if test="${dto.num_26 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow"><font color="red">27</font></td>
          <td class="t_center">${dto.count_27}&nbsp;<c:if test="${dto.count_27 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2027');">${dto.num_27}</a><c:if test="${dto.num_27 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
      </table></td>
      <td width="20%"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tables">
        <tr>
          <td width="20%" class="th">号码</td>
          <td width="25%" class="th">笔数</td>
          <td class="th">金额</td>
        </tr>
        <tr>
          <td class="thyellow">28</td>
          <td class="t_center">${dto.count_28}&nbsp;<c:if test="${dto.count_28 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2028');">${dto.num_28}</a><c:if test="${dto.num_28 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow">29</td>
          <td class="t_center">${dto.count_29}&nbsp;<c:if test="${dto.count_29 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2029');">${dto.num_29}</a><c:if test="${dto.num_29 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow"><font color="red">30</font></td>
          <td class="t_center">${dto.count_30}&nbsp;<c:if test="${dto.count_30 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2030');">${dto.num_30}</a><c:if test="${dto.num_30 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow">31</td>
          <td class="t_center">${dto.count_31}&nbsp;<c:if test="${dto.count_31 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2031');">${dto.num_31}</a><c:if test="${dto.num_31 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow"><font color="red">32</font></td>
          <td class="t_center">${dto.count_32}&nbsp;<c:if test="${dto.count_32 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2032');">${dto.num_32}</a><c:if test="${dto.num_32 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow">33</td>
          <td class="t_center">${dto.count_33}&nbsp;<c:if test="${dto.count_33 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2033');">${dto.num_33}</a><c:if test="${dto.num_33 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow"><font color="red">34</font></td>
          <td class="t_center">${dto.count_34}&nbsp;<c:if test="${dto.count_34 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2034');">${dto.num_34}</a><c:if test="${dto.num_34 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow">35</td>
          <td class="t_center">${dto.count_35}&nbsp;<c:if test="${dto.count_35 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2035');">${dto.num_35}</a><c:if test="${dto.num_35 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow"><font color="red">36</font></td>
          <td class="t_center">${dto.count_36}&nbsp;<c:if test="${dto.count_36 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2036');">${dto.num_36}</a><c:if test="${dto.num_36 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
      </table></td>
	   <td width="20%"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tables">
        <tr>
          <td width="20%" class="th">号码</td>
          <td width="25%" class="th">笔数</td>
          <td class="th">金额</td>
        </tr>
        <tr>
          <td class="thyellow"><font color="green">0</font></td>
          <td class="t_center">${dto.count_0}&nbsp;<c:if test="${dto.count_0 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2199');">${dto.num_0}</a><c:if test="${dto.num_0 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow"><font color="red">红</font></td>
          <td class="t_center">${dto.count_red}&nbsp;<c:if test="${dto.count_red ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2047');">${dto.red}</a><c:if test="${dto.red ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow">黑</td>
          <td class="t_center">${dto.count_black}&nbsp;<c:if test="${dto.count_black ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2048');">${dto.black}</a><c:if test="${dto.black ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow"><font color="blue">单</font></td>
          <td class="t_center">${dto.count_dan}&nbsp;<c:if test="${dto.count_dan ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2045');">${dto.dan}</a><c:if test="${dto.dan ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow"><font color="blue">双</font></td>
          <td class="t_center">${dto.count_shuang}&nbsp;<c:if test="${dto.count_shuang ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2046');">${dto.shuang}</a><c:if test="${dto.shuang ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow"><font color="blue">大</font></td>
          <td class="t_center">${dto.count_da}&nbsp;<c:if test="${dto.count_da ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2044');">${dto.da}</a><c:if test="${dto.da ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow"><font color="blue">小</font></td>
          <td class="t_center">${dto.count_xiao}&nbsp;<c:if test="${dto.count_xiao ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center"><a href="javascript:;" style="cursor:pointer;" onclick="startRequest('showDetail','<%=request.getContextPath()%>/note/noteList.do?action=display_DetailInfo&betSerial=${dto.betSerial}&gameArea=2043');">${dto.xiao}</a><c:if test="${dto.xiao ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow">&nbsp;</td>
          <td class="t_center">&nbsp;</td>
          <td class="t_center">&nbsp;</td>
        </tr>
        <tr>
          <td class="thyellow">&nbsp;</td>
          <td class="t_center">&nbsp;</td>
          <td class="t_center">&nbsp;</td>
        </tr>
      </table></td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="4" class="">
    <tr>
      <td width="50%"><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tables">
        <tr>
          <td width="12%" class="th">类别</td>
		   <td width="60%" class="th">内容</td>
          <td width="10%" class="th">笔数</td>
          <td class="th">金额</td>
        </tr>
        <tr>
          <td class="thyellow">一组12</td>
          <td class="t_center">1、2、3、4、5、6、7、8、9、10、11、12</td>
          <td class="t_center">${dto.count_1_12}&nbsp;<c:if test="${dto.count_1_12 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center">${dto.num_1_12}&nbsp;<c:if test="${dto.num_1_12 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow">二组12</td>
          <td class="t_center">13、14、15、16、17、18、19、20、21、22、23、24</td>
          <td class="t_center">${dto.count_13_24}&nbsp;<c:if test="${dto.count_13_24 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center">${dto.num_13_24}&nbsp;<c:if test="${dto.num_13_24 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow">三组12</td>
          <td class="t_center">25、26、27、28、29、30、31、32、33、34、35、36</td>
          <td class="t_center">${dto.count_25_36}&nbsp;<c:if test="${dto.count_25_36 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center">${dto.num_25_36}&nbsp;<c:if test="${dto.num_25_36 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
      </table></td>
      <td><table width="100%" border="0" cellspacing="0" cellpadding="0" class="tables">
        <tr>
          <td width="12%" class="th">类别</td>
		   <td width="60%" class="th">内容</td>
          <td width="10%" class="th">笔数</td>
          <td class="th">金额</td>
        </tr>
        <tr>
          <td class="thyellow">1赔2</td>
          <td class="t_center">1、4、7、10、13、16、19、22、25、28、31、34</td>
          <td class="t_center">${dto.count_1_34}&nbsp;<c:if test="${dto.count_1_34 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center">${dto.num_1_34}&nbsp;<c:if test="${dto.num_1_34 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow">1赔2</td>
          <td class="t_center">2、5、8、11、14、17、20、23、26、29、32、35</td>
          <td class="t_center">${dto.count_2_35}&nbsp;<c:if test="${dto.count_2_35 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center">${dto.num_2_35}&nbsp;<c:if test="${dto.num_2_35 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
        <tr>
          <td class="thyellow">1赔2</td>
          <td class="t_center">3、6、9、12、15、18、21、24、27、30、33、36</td>
          <td class="t_center">${dto.count_3_36}&nbsp;<c:if test="${dto.count_3_36 ==null}" var="true"><font color="#666666">-</font></c:if></td>
          <td class="t_center">${dto.num_3_36}&nbsp;<c:if test="${dto.num_3_36 ==null}" var="true"><font color="#666666">-</font></c:if></td>
        </tr>
      </table></td>
    </tr>
  </table>
   <div id="showDetail">
  </div>
</body>
<script type="text/javascript">
var zc=document.getElementById("zc");
var index="<%=zC %>";
for(var i=0; i<zc.options.length; i++){
			if(zc.options[i].value==index){
				zc.options[i].selected=true;break;
	}
}
		
var ttime=document.getElementById("time");
var index2="<%=time %>";
for(var i=0; i<ttime.options.length; i++){
			if(ttime.options[i].value==index2){
				ttime.options[i].selected=true;break;
	}
}
</script> 
</html>

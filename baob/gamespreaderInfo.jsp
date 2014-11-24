<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%

	String msg="";
	if(request.getAttribute("msg")!=null){
	msg=request.getAttribute("msg").toString();
	}   
	int userID = 0;
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=30;
	String createTimeStart = "";
	String createTimeEnd = "";
	String checkTime = "";
	try{
		System.out.println(request.getAttribute("pageSize")+","+request.getAttribute("pageIndex"));
		
		if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("pageIndex")!=null){
			pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
		}
		if(request.getParameter("startTime")!=null){
			createTimeStart= request.getParameter("startTime");
			System.out.println("createTimeStart:"+createTimeStart);
		}
		if(request.getParameter("endTime")!=null){
			createTimeEnd= request.getParameter("endTime");
			System.out.println("createTimeEnd:"+createTimeEnd);
		}
		if(request.getAttribute("checkTime")!=null){
		   checkTime =request.getAttribute("checkTime").toString();
		   System.out.println("checkTime:"+checkTime);
        }
		if(request.getParameter("checkTime")!=null){
		   checkTime =request.getParameter("checkTime");
		   System.out.println("checkTime:"+checkTime);
        }
		if(request.getSession().getAttribute("userID")!=null){
			   userID =(Integer)(request.getSession().getAttribute("userID"));
			   System.out.println("userID:"+userID);
	        }
		if(request.getParameter("userID")!=null){
		   userID =Integer.parseInt(request.getParameter("userID"));
		   System.out.println("userID:"+userID);
        }
		}catch(Exception e){
			e.printStackTrace();
	}
	recordIndex=(pageIndex-1)*pageSize+1;
	
	String termOne="";
	if(request.getParameter("termOne")!=null){
		termOne=request.getParameter("termOne");
		termOne = new String(termOne.getBytes("ISO_8859_1"),"UTF-8");
		System.out.println("termOne:"+termOne);
	}
	String selectOne="";
	if(request.getParameter("selectOne")!=null){
		selectOne=request.getParameter("selectOne");
	}
	String selectOrderBy="betRecord";
	if(request.getParameter("selectOrderBy")!=null){
		selectOrderBy= request.getParameter("selectOrderBy");
	}


%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>业绩提成</title>
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
<script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/My97DatePicker/WdatePicker.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/js/bbtj.js"></script>
<script language="javascript" type="text/javascript">
       //弹出友好提示
var msg="<%=msg %>";
if(msg!='')alert(msg);

var tone=encodeURI("<%=termOne %>");
function search(){
if(submted()){
var checkTime = document.all.checkTime.value;
for(var i=0;i<document.all.checkTime.length;i++)
	{
      if(document.all.checkTime[i].checked==true)
		{
          checkTime = document.all.checkTime[i].value;
          break;
         }
    }
document.location.href="<%=request.getContextPath() %>/baob/gameUserBBTJ.do?action=SpreaderInfoTCRecord&userID=<%=userID%>&termOne="+encodeURI(document.all.termOne.value)
		+"&selectOne="+document.all.selectOne.options[document.all.selectOne.selectedIndex].value
		+"&pageSize="+document.all.pageSize.options[document.all.pageSize.selectedIndex].value
		+"&selectOrderBy="+document.all.selectOrderBy.options[document.all.selectOrderBy.selectedIndex].value
		+"&checkTime="+checkTime+"&startTime="+document.all.startTime.value+"&endTime="+document.all.endTime.value;
}
}
function jumppage(value)
   {
       window.location.href="<%=request.getContextPath() %>/baob/gameUserBBTJ.do?action=SpreaderInfoTCRecord&userID=<%=userID%>&curPage=" +value+"&pageSize=<%=pageSize %>&termOne=<%=termOne%>&selectOne=<%=selectOne %>&checkTime=<%=checkTime%>&startTime=<%=createTimeStart%>&endTime=<%=createTimeEnd%>&selectOrderBy=<%=selectOrderBy%>";
   }
 function changepage(pageNo){
	document.location.href="<%=request.getContextPath() %>/baob/gameUserBBTJ.do?action=SpreaderInfoTCRecord&userID=<%=userID%>&curPage="+pageNo+"&termOne="+tone
	+"&selectOne=<%=selectOne %>&pageSize=<%=pageSize %>&checkTime=<%=checkTime%>&startTime=<%=createTimeStart%>&endTime=<%=createTimeEnd%>&selectOrderBy=<%=selectOrderBy%>";

}
 function checkData(){
   if(confirm('注意：\n1、任意游戏在未关闭状态 ;\n2、有用户下注而未开奖结算。\n上述两种情况下使用此功能可能会导致数据错误！！\n\n确定整理?')){
    document.location.href ="bbtjTotalScore.do";
   }
 }
var hash ={checkTime:'<%=checkTime %>'};
</script>

</head>

<body onload="loadDay(hash)">
<form id="form2" name="form2" method="post" onsubmit="return submted()" action="<%=request.getContextPath() %>/baob/gameUserBBTJ.do?action=SpreaderInfoTCRecord">
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_margin">
    <tr>
      <td class="menutop" colspan="9">用户
        <input name="termOne" type="text" size="8" id="termOne" value="<%=termOne%>" onfocus="this.select()" />
		&nbsp;类型
        <select name="selectOne" size="1" class="Select" id="selectOne">
		<option value="accounts">按用户名</option>
		<option value="gameID">按游戏ID</option>
        </select>
		&nbsp;显示条数<select name="pageSize" size="1" class="Select" id="pageSize">
		<option value="10">10条</option>
		<option value="30">30条</option>
		<option value="50">50条</option>
		<option value="100">100条</option>
		<option value="200">200条</option>
        </select>
		&nbsp;&nbsp;  排序
		<select name="selectOrderBy" size="1" class="Select" id="selectOrderBy">
		<option value="swapScore">按提成金额</option>
		<option value="gameID">按游戏ID</option>
        </select>&nbsp;
		(<input size="8" title="开始时间" id="startTime" type="text" onClick="WdatePicker()"  name="startTime" value="${startTime}<%=createTimeStart %>" readonly="readonly"/>        
          <span title="例：开始‘2010-06-06’，结束‘2010-06-08’；    取到的数据的日期为：‘2010-06-07’" style="cursor:pointer">数据日期</span>
          <input size="8" title="结束时间" id="endTime" type="text" onClick="WdatePicker()"  name="endTime" value="${endTime}<%=createTimeEnd %>" readonly="readonly"/>)
  <input type="button" name="reSet" onclick="reSetDate()" value="清空日期" />
  &nbsp;
  <input id="checkTime"  type="radio" value="all" name="checkTime" />
		  全部
  <input id="checkTime"  type="radio" value="bweek" name="checkTime" />
		  上周
  <input id="checkTime"  type="radio" value="cweek" name="checkTime" />
		  本周
  <input id="checkTime"  type="radio" value="bmonth" name="checkTime" />
		  上月份
  <input id="checkTime"  type="radio" value="cmonth" name="checkTime" />
		  本月份
		  <input type="button" name="Submit" value="查询" onclick="search()"/>
      </td>
    </tr>
  </table>
</form>
<form id="form1" name="form1" method="post" action="userDel.do">
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_margin">
    <tr>
      <td height="25" colspan="8" class="menutop">业绩提成</td>
    </tr>
    <tr>
      <td colspan="3" class="menutop"><div align="right"><strong><font color="#FF0000">合计:</font></strong>&nbsp;</div></td>
      <td class="menutop"><strong>&nbsp;<font color="#FF0000">${sumNo.totalXxScore}</font></strong></td>
      <td class="menutop"><strong>&nbsp;<font color="#FF0000">${sumNo.totalScore}</font></strong></td>
	  <td class="menutop">&nbsp;</td>
    </tr>
    <tr>
      <td class="tdcenter" align="center">序号</td>
      <td class="tdcenter"><div align="center">类别</div></td>
      <td class="tdcenter"><div align="center">被推荐人</div></td>
      <td class="tdcenter">提成</td>
      <td class="tdcenter"><div align="center">上交</div></td>
      <td class="tdcenter">操作</td>
      <%-- <td class="tdcenter">&nbsp;</td>--%>
      <%--	  <td class="tdcenter"><div align="center">日期</div></td>--%>
    </tr>
    <c:forEach var="user" items="${userSpreaderRecord}">
      <tr onmouseover="this.className='trover'" onmouseout="this.className='trout'">
        <td class="tdcenter" height="25" align="center"><%=recordIndex++ %></td>
        <td class="tdcenter"><div align="center">${user.typeName}&nbsp;</div></td>
        <td class="tdcenter"><div align="center"><a href="<%=request.getContextPath()%>/baob/gameUserBBTJ.do?action=SpreaderInfoTCRecord&userID=${user.childrenID}">${user.accounts}</a>&nbsp;</div></td>
        <td class="tdcenter">${user.score}&nbsp;</td>
        <td class="tdcenter">${user.sxScore}&nbsp;</td>
        <td class="tdcenter"><a href="<%=request.getContextPath()%>/baob/gameUserBBTJ.do?action=SpreaderDeatailTCRecord&userID=${user.childrenID}">详细</a> / 结算</td>
        <%--	<td><div align="center">${user.createTime}&nbsp;</div></td>--%>
        <%--<td><a href="<%=request.getContextPath()%>/gameuser/gamescoreInfoDetail.do?userID=${user.userId}&selectRoomType=<%=selectRoomType %>"><div align="center">详细</div></a></td>--%>
      </tr>
    </c:forEach>
    <tr>
      <td colspan="8" align="center"><font color="red">
        <c:out value="${returnInfo}"></c:out>
      </font></td>
    </tr>
  </table>
  <table width="100%" border="0" cellpadding="2" cellspacing="1" class="table_margin">
    <tr class="tdcenter" align="left">
       <td class="menutop" colspan="4">总记录:${page.totalRecord}条/${page.totalPage}页
        <c:if test="${page.totalPage>=0}"  var="true"> 
　<a onclick="changepage(1)" style="cursor:hand">首页</a>
<a onclick="changepage(${page.curPage-1})" style="cursor:hand">上页</a>
<select name="select" onChange="jumppage(this.value);">
          <c:forEach var="i" begin="1" end="${page.totalPage }" step="1"> <option value="${i}" 
              <c:if test="${i==page.curPage}" var="true">selected="selected"</c:if>
            >第${i}页
            </option>
          </c:forEach>
        </select>
        <a onclick="changepage(${page.curPage+1})" style="cursor:hand">下页</a>
        <a onclick="changepage(${page.totalPage})" style="cursor:hand">末页</a></c:if></td>
    </tr>
  </table>
</form>
</body>
<script type="text/javascript">

var ttype1=document.all.pageSize;
var index1="<%=pageSize %>";
for(var i=0; i<ttype1.options.length; i++){
	if(ttype1.options[i].value==index1){
		ttype1.options[i].selected=true;break;
	}
}

var ttype2=document.all.selectOne;
var index2="<%=selectOne %>";
for(var i=0; i<ttype2.options.length; i++){
	if(ttype2.options[i].value==index2){
		ttype2.options[i].selected=true;break;
	}
}

var ttype3=document.all.termOne;
var index2="<%=termOne%>";
for(var i=0; i<ttype3.length; i++){
	if(ttype3.value==index2){
		ttype3.selected=true;break;
	}
}
var ttype4=document.all.startTime;
var index3="<%=createTimeStart%>";
	if(ttype3.value==index3){
	ttype3.selected=true;
}

var ttype5=document.all.endTime;
var index4="<%=createTimeEnd%>";
	if(ttype5.value==index4){
	ttype5.selected=true;
}

var ttype6=document.all.selectOrderBy;
var index6="<%=selectOrderBy %>";
for(var i=0; i<ttype6.length; i++){
	if(ttype6.options[i].value==index6){
		ttype6.options[i].selected=true;break;
	}
}

//var index4="=selectOrderBy %>";
//
//var tstate1=document.all.selectOrderBy;
//alert(index1);
//for(var i=0; i<tstate1.options.length; i++){
//			if(tstate1.options[i].value==index4){
//				tstate1.options[i].selected=true;break;
//			}
//		}
//--%>
</script>
</html>

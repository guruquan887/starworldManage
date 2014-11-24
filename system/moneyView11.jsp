<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=30;
	String orderby="RegisterDate";
	if(request.getParameter("orderby")!=null){
		orderby=request.getParameter("orderby");
	}
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
	String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}

	String startTime="";
	if(request.getParameter("startTime")!=null){
		startTime=request.getParameter("startTime");
	}
	String endTime="";
	if(request.getParameter("endTime")!=null){
		endTime=request.getParameter("endTime");
	}
	int proxyID = 0;
	if(request.getParameter("userID")!=null){
		proxyID=Integer.parseInt(request.getParameter("userID"));
	}
	
%>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<script language="javascript" type="text/javascript">
function jumpEnter(e,newpageNo) {
	// 响应回车
	var key = window.event ? e.keyCode : e.which;
	if (key == 13) {
		//alert('123123123');
		jumppage(newpageNo);
	}
}

function jumppage(value)
   {
      window.location.href="<%=request.getContextPath()%>/baob/bbtj.do?action=search&curPage=" +value+"&pageSize=<%=pageSize %>&orderby=<%=orderby %>";
   }
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/baob/bbtj.do?action=search&curPage="+pageNo;
}
function search(){
	document.location.href="<%=request.getContextPath()%>/baob/bbtj.do?action=search"
		+"&startTime="+document.all.startTime.value+"&endTime="+document.all.endTime.value+"&userID="+document.all.proxyID.value;
}
function searchtime(ortime){
	document.location.href="<%=request.getContextPath()%>/baob/bbtj.do?action=search"+"&checkTime="+ortime+"&startTime="+document.all.startTime.value+"&endTime="+document.all.endTime.value+"&userID="+document.all.proxyID.value;
}
</script>
<body>
	<Div class="title">
		<span>  
	  <form id="form1" name="form1" method="post" action="">
	  <input name="checkTime1" id="checkTime1" type="button" class="input" value="今日" onClick="searchtime('today')"/>
	  <input name="checkTime2" id="checkTime2" type="button" class="input" value="昨日" onClick="searchtime('yestoday')"/>
	  <input name="checkTime3" id="checkTime3" type="button" class="input" value="本周" onClick="searchtime('cweek')"/>
	  <input name="checkTime5" id="checkTime5" type="button" class="input" value="本月" onClick="searchtime('cmonth')"/>
	  <input name="checkTime6" id="checkTime6" type="button" class="input" value="上月" onClick="searchtime('bmonth')"/>
	  <input name="checkTime7" id="checkTime7" type="button" class="input" value="全部" onClick="searchtime('all')"/>
	  <input name="button" type="button" onclick="form1.submit()" class="input" value="刷新"/>
      </form>
	</span>
	银子监控</Div>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td width="100" class="menutop">名称</td>
            <td class="menutop">昨日</td>
            <td class="menutop">充值</td>
            <td class="menutop">输赢</td>
            <td class="menutop">损耗</td>
			<td class="menutop">税收</td>
			<td class="menutop">当前</td>
			<td class="menutop">异常</td>
          </tr>
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter">系统(公司)</td>
            <td class="tdcenter">200,000</td>
            <td class="tdcenter">0</td>
            <td class="tdcenter">-5000</td>
            <td class="tdcenter">0</td>
			 <td class="tdcenter">0</td>
            <td class="tdcenter">105,000</td>
            <td class="tdcenter">&nbsp;</td>
          </tr>
		   <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter">机器人</td>
            <td class="tdcenter">200,000</td>
            <td class="tdcenter">5000</td>
            <td class="tdcenter">-5000</td>
            <td class="tdcenter">0</td>
			 <td class="tdcenter">1000</td>
             <td class="tdcenter">199,000</td>
             <td class="tdcenter">&nbsp;</td>
          </tr>
		   <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter">用户(玩家)</td>
            <td class="tdcenter">200,000</td>
            <td class="tdcenter">0</td>
            <td class="tdcenter">-5000</td>
            <td class="tdcenter">0</td>
			 <td class="tdcenter">500</td>
             <td class="tdcenter">104,500</td>
             <td class="tdcenter">&nbsp;</td>
          </tr>
        </table>
	<Div class="title" style="padding-top:20px;">
		<span>  
	  <form id="form1" name="form1" method="post" action="">
	    <input name="button" type="submit" class="input" value="玩家" />
	    <input name="button" type="button" class="input" value="机器人"/>
      </form>
	</span>
	输赢结果</Div>

		
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td width="60" class="menutop">序号</td>
            <td class="menutop">游戏ID</td>
            <td class="menutop">用户名</td>
            <td class="menutop">输赢</td>
            <td class="menutop">结存</td>
          </tr>
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter">1</td>
            <td class="tdcenter">102355</td>
            <td class="tdcenter">山东</td>
            <td class="tdcenter">15000</td>
            <td class="tdcenter">410000</td>
          </tr>
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter">2</td>
            <td class="tdcenter">102356</td>
            <td class="tdcenter">山东1</td>
            <td class="tdcenter">13000</td>
            <td class="tdcenter">150000</td>
          </tr>
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter">3</td>
            <td class="tdcenter">102357</td>
            <td class="tdcenter">山东1</td>
            <td class="tdcenter">12000</td>
            <td class="tdcenter">510000</td>
          </tr>
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter">4</td>
            <td class="tdcenter">102358</td>
            <td class="tdcenter">山东1</td>
            <td class="tdcenter">-150000</td>
            <td class="tdcenter">10000</td>
          </tr>
          <tr onmouseover="this.className='trover'" onmouseout="this.className='trout'">
            <td colspan="5" class="tdright_new">
			<select>
				<option>显示20条</option>
				<option>显示50条</option>
				<option>显示100条</option>
			</select>
			共10页 当前页1页 首页 上一页 下一页 末页</td>
          </tr>
        </table>
</body>
</html>
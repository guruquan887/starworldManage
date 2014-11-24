<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=10;
	int state = 0;
	try{
		//System.out.println(request.getAttribute("pageSize")+","+request.getAttribute("pageIndex"));
		
		if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("pageIndex")!=null){
			pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
		}
		if (request.getParameter("state") != null) {
			state = Integer.parseInt(request.getParameter("state"));
		}
		}catch(Exception e){
			e.printStackTrace();
	}
	recordIndex=(pageIndex-1)*pageSize+1;
	String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}
	String termOne="";
	if(request.getParameter("termOne")!=null){
		termOne=request.getParameter("termOne");
		termOne = new String(termOne.getBytes("ISO_8859_1"),"UTF-8");
	}
	String selectOne="";
	if(request.getParameter("selectOne")!=null){
		selectOne=request.getParameter("selectOne");
	}

%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
var tone=encodeURI("<%=termOne %>");
function jumppage(value)
   {
      window.location.href="<%=request.getContextPath()%>/gold/yeepayList.do?action=showYeepayList&curPage=" +value+"&termOne="+tone
	+"&selectOne=<%=selectOne %>";
   }
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/gold/yeepayList.do?action=showYeepayList&curPage="+pageNo+"&termOne="+tone
	+"&selectOne=<%=selectOne %>";
}
function search(){
	document.location.href="<%=request.getContextPath()%>/gold/yeepayList.do?action=showYeepayList&termOne="+encodeURI(document.all.termOne.value)
		+"&selectOne="+document.all.selectOne.options[document.all.selectOne.selectedIndex].value;

}

</script>
<body>
	<Div class="title"><span>  
	  <form id="form1" name="form1" method="post" action="">
	    <label>
	    <input name="termOne" type="text" class="input_width2" id="termOne" />
	    <select name="selectOne" class="input_width1" id="selectOne">
	      <option value="accounts">用户名</option>
	      <option value="gameID">游戏ID</option>
        </select>
	    <input name="Submit2" type="button" class="input" value="搜索" onClick="search()"/>
	    <%--<input name="Submit" type="button" class="input" value="取款记录" onclick="window.location.href='<%=request.getContextPath()%>/gold/drawList.do?action=showDrawList'" />--%>
	    <input name="Submit3" type="button" class="input" value="补单"  onclick="window.location.href='<%=request.getContextPath()%>/gold/yeepayList.do?action=preAddYeepay'" />
	    </label>
	  </form>
	</span><input name="Submit3" type="button" class="input" value="网银记录"  onclick="window.location.href='<%=request.getContextPath()%>/gold/yeepayList.do?action=showYeepayList'" /> <input name="Submit3" type="button" class="input" value="充值卡记录"  onclick="window.location.href='<%=request.getContextPath()%>/gold/cardList.do?action=cardRecord'" /></Div>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
      <tr>
        <td width="50" class="menutop">序号</td>
        <td class="menutop">订单号</td>
        <td class="menutop">充值用户</td>
        <td class="menutop">接受用户</td>
        <td class="menutop">充值金额</td>
        <td class="menutop">下单金额</td>
        <td class="menutop">订单时间</td>
        <td class="menutop">电话</td>
        <td class="menutop">订单状态</td>
        <td class="menutop">操作</td>
      </tr>
	  <c:forEach var="user" items="${useryeepay}">
      <tr onmouseover="this.className='trover'" onmouseout="this.className='trout'">
        <td class="tdcenter"><%=recordIndex++%></td>
        <td class="tdcenter">&nbsp;${user.dh}</td>
        <td class="tdcenter">&nbsp;${user.username}</td>
        <td class="tdcenter">&nbsp;${user.username1}</td>
        <td class="tdcenter">${user.r3_Amt}</td>
        <td class="tdcenter">${user.jine}</td>
        <td class="tdcenter">${user.xdtime}</td>
        <td class="tdcenter">&nbsp;${user.telphone}</td>
        <td class="tdcenter"><c:if test="${user.state==1}" var="true"><span class="colorred">成功</span></c:if><c:if test="${user.state==0}" var="true">未充值</c:if></td>
        <td class="tdcenter">&nbsp;<c:if test="${user.state==0}" var="true"><a href="<%=request.getContextPath()%>/gold/yeepayList.do?action=delYeepay&dh=${user.dh}">删除</a>　<a href="<%=request.getContextPath()%>/gold/yeepayList.do?action=updateYeepay&dh=${user.dh}&r3_Amt=${user.jine}">充值</a></c:if> </td>
      </tr>
	  </c:forEach>
      <tr>
        <td colspan="10" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
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
        <a onclick="changepage(${page.totalPage})" style="cursor:hand">末页</a>		</c:if></td>
      </tr>
    </table>
</body>
<script type="text/javascript">
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
</script>
</html>
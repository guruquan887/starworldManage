<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%
	String msg="";
	if(request.getAttribute("msg")!=null){
	msg=request.getAttribute("msg").toString();
	}
	
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=10;

	try{
		System.out.println(request.getAttribute("pageSize")+","+request.getAttribute("pageIndex"));
		
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
<script language="javascript" type="text/javascript">
       //弹出友好提示
var msg="<%=msg %>";
if(msg!='')alert(msg);
function jumppage(value)
   {
      window.location.href="<%=request.getContextPath()%>/game/gameSystem.do?action=GameRoomInfo&curPage=" +value+"&termOne="+encodeURI(document.all.termOne.value)+"&selectOne="+document.all.selectOne.options[document.all.selectOne.selectedIndex].value;
   }
function changepage(pageNo){
	document.location.href="<%=request.getContextPath()%>/game/gameSystem.do?action=GameRoomInfo&curPage="+pageNo+"&termOne="+encodeURI(document.all.termOne.value)+"&selectOne="+document.all.selectOne.options[document.all.selectOne.selectedIndex].value;
}
function search(){
	document.location.href="<%=request.getContextPath()%>/game/gameSystem.do?action=GameRoomInfo&termOne="+encodeURI(document.all.termOne.value)
		+"&selectOne="+document.all.selectOne.options[document.all.selectOne.selectedIndex].value;
}

 function getAll()
{
	var elements=document.form2.checkbox;
	var length=elements.length;
	for(var i=0;i<length;i++)
	{
		var element=elements[i];
		element.checked=true;
	}
}
function checkAllBox(i){
	
		var isChecked=(document.form2.checkAll.checked == true);
	    var elements=document.form2.elements;
	    var counter=elements.length;
	    for(i=0;i<counter;i++){
			var element=elements[i];
		if(element.type=="checkbox"){
			element.checked=isChecked;
		}
	}
}

function tjSubmit(opId){
var flag10=0;
var radio10=document.getElementsByName("checkbox");
   for(var i=0;i<radio10.length;i++)
   {
     if(radio10.item(i).checked==true)
        {
     flag10=1;
           break;
   		}
   }
  if(!flag10){
         alert("请选择对象");
         return false;
  			}
else{
   if(opId==0){
      form2.action="<%=request.getContextPath()%>/game/gameSystem.do?action=delAll";
	  form2.submit();
   }
   }
}
</script>
</head>
<body>
	<Div class="title"><span><form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/game/gameSystem.do?action=GameRoomInfo">
	   <input name="termOne" type="text" class="input_width2" id="termOne" value="<%=termOne%>"/>
	    <select name="selectOne" class="input_width1" id="selectOne">
	      <option value="serverName">房间名称</option>
        </select>	  
		 <input name="Submit2" type="button" class="input" value="搜索" onClick="search()"/>
		 </form>
	</span>房间管理</Div>
	<form id="form2" name="form2" method="post" action="">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td  class="menutop"><input type="checkbox" name="checkAll" value="checkAll" onClick="checkAllBox(0)" /></td>
            <td class="menutop">管理</td>
            <td class="menutop"> 房间标识 </td>
            <td class="menutop"> 房间名称 </td>
            <td class="menutop"> 游戏名称 </td>
            <td class="menutop"> 节点名称 </td>
            <td class="menutop"> 排序 </td>
            <td class="menutop"> 模块名称 </td>
            <td class="menutop"> 桌子数量 </td>
            <td class="menutop"> 房间类型 </td>
            <td class="menutop"> 服务端口 </td>
            <td class="menutop"> 数据库名称 </td>
            <td class="menutop"> 数据库地址 </td>
            <td class="menutop"> 单位积分 </td>
            <td class="menutop"> 税收比例 </td>
            <td class="menutop"> 限制积分 </td>
            <td class="menutop"> 最低积分 </td>
            <td class="menutop"> 最小进入积分 </td>
            <td class="menutop"> 最大进入积分 </td>
            <td class="menutop"> 最小进入等级 </td>
            <td class="menutop"> 最大进入等级 </td>
            <td class="menutop"> 最大人数 </td>
            <td class="menutop"> 房间规则 </td>
           <%-- <td class="menutop"> 运行机器 </td>--%>
            <td class="menutop"> 冻结状态 </td>
            <%--<td class="menutop"> 创建时间 </td>
            <td class="menutop"> 修改时间 </td>--%>
          </tr>
		  <c:forEach var="playPresent" items="${GameRoomInfolist}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter"><input type="checkbox" id="checkbox" value="${playPresent.serverID}" name="checkbox" /></td>
            <td class="tdcenter"><c:if test="${playPresent.nullity==1}" var="true">启用</c:if><c:if test="${playPresent.nullity==0}" var="true">禁用</c:if>&nbsp;</td>
            <td class="tdcenter">${playPresent.serverID}</td>
            <td class="tdcenter">${playPresent.serverName}&nbsp;</td>
            <td class="tdcenter">${playPresent.kindName}&nbsp;</td>
            <td class="tdcenter">${playPresent.nodeName}&nbsp;</td>
            <td class="tdcenter">${playPresent.sortID}</td>
            <td class="tdcenter">${playPresent.gameName}</td>
            <td class="tdcenter">${playPresent.tableCount}&nbsp;</td>
            <td class="tdcenter"><c:if test="${playPresent.serverType==1}" var="true">银子类型</c:if><c:if test="${playPresent.serverType==2}" var="true">积分类型</c:if>&nbsp;</td>
            <td class="tdcenter">${playPresent.serverPort}</td>
            <td class="tdcenter">${playPresent.dataBaseName}&nbsp;</td>
            <td class="tdcenter">${playPresent.dataBaseAddr}&nbsp;</td>
            <td class="tdcenter">${playPresent.cellScore}&nbsp;</td>
            <td class="tdcenter">${playPresent.revenueRatio}&nbsp;</td>
            <td class="tdcenter">${playPresent.restrictScore}&nbsp;</td>
            <td class="tdcenter">${playPresent.minTableScore}&nbsp;</td>
            <td class="tdcenter">${playPresent.minEnterScore}&nbsp;</td>
            <td class="tdcenter">${playPresent.maxEnterScore}&nbsp;</td>
            <td class="tdcenter">${playPresent.minEnterMember}&nbsp;</td>
            <td class="tdcenter">${playPresent.maxEnterMember}&nbsp;</td>
            <td class="tdcenter">${playPresent.maxPlayer}&nbsp;</td>
            <td class="tdcenter">${playPresent.serverRule}&nbsp;</td>
           <%-- <td class="tdcenter">${playPresent.serviceMachine}&nbsp;</td>--%>
            <td class="tdcenter">${playPresent.nullityName}&nbsp;</td>
           <%-- <td class="tdcenter">${playPresent.createDateTime}&nbsp;</td>
            <td class="tdcenter">${playPresent.modifyDateTime}&nbsp;</td>--%>
          </tr>
          </c:forEach>
		   <c:if test="${returnInfo!=''}"  var="true">
		  <tr>
                <td class="tdcenter" colspan="37" align="center" valign="middle" bgcolor="#FFFFFF">
				<font color="red"><c:out value="${returnInfo}"></c:out></font></td>
       </tr>
	   </c:if>
          <tr>
            <td colspan="27" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
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
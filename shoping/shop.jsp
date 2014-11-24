<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %> 
<%
	int recordIndex=1;
	int pageIndex=1;
	int pageSize=10;
	int typeId=0;
	try{
		if(request.getAttribute("pageSize")!=null){
			pageSize=Integer.parseInt(request.getAttribute("pageSize").toString());
		}
		if(request.getAttribute("pageIndex")!=null){
			pageIndex=Integer.parseInt(request.getAttribute("pageIndex").toString());
		}
		if(request.getParameter("typeId")!=null){
			typeId=Integer.parseInt(request.getParameter("typeId"));
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}
	recordIndex=(pageIndex-1)*pageSize+1;
	String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
function jumppage(value)
{
  window.location.href="<%=request.getContextPath()%>/shoping/gameshopList.do?action=mallItemsList&curPage="+value+"&typeId=<%=typeId%>";
}
function deletegameshop(gameshopid)
{
	document.location.href="<%=request.getContextPath()%>/shoping/gameshopList.do?action=delete&gameshopid="+gameshopid;
}
function querygameshop()
{
	var cc=document.all.typeId.options[document.all.typeId.selectedIndex].value;
	document.location.href="<%=request.getContextPath()%>/shoping/gameshopList.do?action=mallItemsList&typeId="+cc;
}
function checkAllBox(i){
		var isChecked=(document.form1.checkAll.checked == true);
	    var elements=document.form1.elements;
	    var counter=elements.length;
	    for(i=0;i<counter;i++){
			var element=elements[i];
		if(element.type=="checkbox"){
			element.checked=isChecked;
		}
	}
}
function delCheck(){
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
         alert("请选择删除对象");
         return false;
  }
   return true;
	}
</script>
</head>
<body>
	<Div class="title"><span>
	  <label>
	  <input name="Submit" type="button" class="input" value="新增商品" onclick="window.location.href='<%=request.getContextPath()%>/shoping/gameshopList.do?action=preAddMallItems'"/>
	  </label>
	</span>商品管理</Div>
	<form id="form1" name="form1" method="post" onsubmit="return delCheck()" action="<%=request.getContextPath()%>/shoping/gameshopList.do?action=updateState">
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr>
            <td class="menutop">选择</td>
            <td width="60" class="menutop">序号</td>
			<td class="menutop">类型</td>
            <td class="menutop">商品名称</td>
            <td class="menutop">银子普通价格</td>
            <td class="menutop">银子会员价格</td>
            <td class="menutop">兑换次数</td>
            <td class="menutop">库存</td>
            <td class="menutop">发布</td>
            <td class="menutop">新品</td>
            <%--<td class="menutop">精品</td>--%>
            <td class="menutop">人气</td>
            <td class="menutop">热卖</td>
            <td class="menutop">查看</td>
            <td class="menutop">修改</td>
            <td class="menutop">删除</td>
            <%--<td class="menutop">缩略图</td>--%>
          </tr>
		  <c:forEach var="gameshops" items="${gameshoplist}">
          <tr onmouseOver="this.className='trover'" onmouseOut="this.className='trout'">
            <td class="tdcenter">
              <label>
                <input type="checkbox" id="checkbox" name="checkbox" value="${gameshops.id}" />
              </label>
            </td>
            <td class="tdcenter"><%=recordIndex++ %></td>
			<td class="tdcenter">${gameshops.typeName}</td>
            <td class="tdcenter">${gameshops.mallName}</td>
            <td class="tdcenter">${gameshops.price_gold}</td>
            <td class="tdcenter">${gameshops.vipPrice}</td>
            <td class="tdcenter">${gameshops.dhCount}</td>
            <td class="tdcenter">${gameshops.count}</td>
            <td class="tdcenter"><c:if test="${gameshops.publish==0}"  var="true"><img src="images/toolbar_no.gif" /></c:if>
			<c:if test="${gameshops.publish==1}"  var="true"><img src="images/toolbar_ok.gif" /></c:if></td>
            <td class="tdcenter"><c:if test="${gameshops.newMall==0}"  var="true"><img src="images/toolbar_no.gif" /></c:if>
			<c:if test="${gameshops.newMall==1}"  var="true"><img src="images/toolbar_ok.gif" /></c:if></td>
            <%--<td class="tdcenter"><img src="images/toolbar_no.gif" /></td>--%>
            <td class="tdcenter"><c:if test="${gameshops.pop==0}"  var="true"><img src="images/toolbar_no.gif" /></c:if>
			<c:if test="${gameshops.pop==1}"  var="true"><img src="images/toolbar_ok.gif" /></c:if></td>
            <td class="tdcenter"><c:if test="${gameshops.recom==0}"  var="true"><img src="images/toolbar_no.gif" /></c:if>
			<c:if test="${gameshops.recom==1}"  var="true"><img src="images/toolbar_ok.gif" /></c:if></td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/shoping/gameshopList.do?action=preUpdate&id=${gameshops.id}"><img src="images/toolbar_look_up.gif" border="0"/></a></td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/shoping/gameshopList.do?action=preUpdate&id=${gameshops.id}"><img src="images/toolbar_edit_up.gif" border="0" /></a></td>
            <td class="tdcenter"><a href="<%=request.getContextPath()%>/shoping/gameshopList.do?action=deleteMall&id=${gameshops.id}"><img src="images/toolbar_delete_up.gif" border="0" /></a></td>
            <%--<td class="tdcenter"><a href="<%=request.getContextPath()%>/incoming/${gameshops.imagePath}" target="_blank"><img src="<%=request.getContextPath()%>/incoming/${gameshops.imagePath}" border="0"/></a></td>--%>
            </tr>
		  </c:forEach>
          <tr>
            <td colspan="17" class="tdright"><input name="checkAll" value="checkAll" onClick="checkAllBox(0)" type="checkbox"/>全选<input name="radio" type="radio" value="0" checked="checked" />删除<input name="radio" type="radio" value="1" />发布<input name="radio" type="radio" value="2" />取消发布<input name="radio" type="radio" value="3" />新品<input name="radio" type="radio" value="4" />取消新品<%--<input name="" type="radio" value="" />精品<input name="" type="radio" value="" />取消精品--%><input name="radio" type="radio" value="5" />人气<input name="radio" type="radio" value="6" />取消人气<input name="radio" type="radio" value="7" />热卖<input name="radio" type="radio" value="8" />取消热卖&nbsp;&nbsp;<input name="Submit3" type="submit" class="input" value="提交" /></td>
          </tr>
          <tr>
            <td colspan="17" class="tdright_new">总记录:${page.totalRecord}条/${page.totalPage}页
			  <c:if test="${page.totalPage>=0}"  var="true">  
        <select name="select" onChange="jumppage(this.value);">
          <c:forEach var="i" begin="1" end="${page.totalPage }" step="1"> <option value="${i}" 
              <c:if test="${i==page.curPage}" var="true">selected="selected"</c:if>
            >第${i}页
            </option>
          </c:forEach>
        </select> 
　   <a href="<%=request.getContextPath()%>/shoping/gameshopList.do?curPage=1&typeId=<%=typeId%>">首页</a> 
	 <a href="<%=request.getContextPath()%>/shoping/gameshopList.do?curPage=${page.curPage-1}&typeId=<%=typeId%>">上页</a> 
	&nbsp;<a href="<%=request.getContextPath()%>/shoping/gameshopList.do?curPage=${page.curPage+1 }&typeId=<%=typeId%>">下页</a> 
        <a href="<%=request.getContextPath()%>/shoping/gameshopList.do?curPage=${page.totalPage}&typeId=<%=typeId%>">末页</a>		</c:if></td>
          </tr>
        </table>
		</form>
</body>
</html>
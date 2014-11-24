<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div style="text-align:center; width:60%; z-index:99999; display:block; background:#FFF; border:solid 1px #ccc; position:absolute; top:15%; left:20%;margin:0px; padding:0px;" id="showDiv_${betSerial}" >
<table border="0" cellspacing="0" cellpadding="0" width="100%">
<tr>
<td width="5%" onClick="closeDiv('showDiv_${betSerial}')" >&nbsp;
</td>
<td width="90%">
<!-- -->
<table width="100%" border="0" cellspacing="0" cellpadding="0" class="tables">
  <tr><td class="t_center" colspan="5"><div align="left" style="padding-left:10px;">编号：${betSerial}</div></td></tr>
  <tr>
    <td width="25%" class="th">玩法</td>
    <td width="25%" class="th">用户名</td>
    <td width="20%" class="th">金额</td>
	<td width="20%" class="th">时间</td>
	<td width="10%" class="th">占成</td>
  </tr>
  <c:forEach var="dto" items="${infoList}">
  <tr>
    <td class="thyellow">${dto.gameAreaName}&nbsp;</td>
    <td class="t_center">${dto.accounts}&nbsp;</td>
    <td class="t_center">${dto.betGold}&nbsp;</td>
	<td class="t_center">${dto.createTime}&nbsp;</td>
	<td class="t_center">${dto.intoAccount}&nbsp;</td>
  </tr>
  </c:forEach>
  <c:if test="${returnInfo!=null}" var="true"><tr><td colspan="5" class="t_center">${returnInfo}</td></tr></c:if>
  <c:if test="${returnInfo==null}" var="true">
  <tr><td class="t_center" colspan="5">
  <c:if test="${page.totalPage<=1}"  var="true">&nbsp; </c:if> 
    <c:if test="${page.totalPage>1}"  var="true"> 
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
       <td>总记录:${page.totalRecord}条/${page.totalPage}页
　<a onclick="startRequest('showDetail',changepage(1))" style="cursor:hand">首页</a>
<a onclick="startRequest('showDetail',changepage(${page.curPage-1}))" style="cursor:hand">上页</a>
<select name="select" onChange="startRequest('showDetail',jumppage(this.value))">
          <c:forEach var="i" begin="1" end="${page.totalPage }" step="1"> <option value="${i}" 
              <c:if test="${i==page.curPage}" var="true">selected="selected"</c:if>
            >第${i}页
            </option>
          </c:forEach>
        </select>
        <a onclick="startRequest('showDetail',changepage(${page.curPage+1}))" style="cursor:hand">下页</a>
        <a onclick="startRequest('showDetail',changepage(${page.totalPage}))" style="cursor:hand">末页</a></td>
    </tr>
  </table></c:if>
  </td></tr></c:if>
</table>
<!-- -->
</td>
<td width="5%" valign="top" onClick="closeDiv('showDiv_${betSerial}')" >&nbsp;</td>
</tr>
</table>
<input type="hidden" id="betSerial_"  value="${betSerial}"/>
<input type="hidden" id="gameArea_"  value="${gameArea}"/>
</div> 



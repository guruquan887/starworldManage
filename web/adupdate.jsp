<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
int state = 0;
if(request.getParameter("state")!=null){
    state = Integer.parseInt(request.getParameter("state"));
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改广告页面</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
  </head>
  <script language="javascript" type="text/javascript">
  function adaddcheck(){
	var adTypeId = document.form1.adTypeId.value;
	var adTitle = document.form1.adTitle.value;
	var adImage = document.form1.adImage.value;
	if(adTypeId==0){
		alert("请选择广告类型");
		return false;
		}
	if(adTitle==""){
		alert("新闻标题不能为空");
		return false;
		}
	if(adImage==""){
		alert("新闻图片不能为空");
		return false;
		}
	}
  </script>
  
  <body>
  <form id="form1" name="form1" method="post" onSubmit="return adaddcheck()" action="adUpdate.do">
  <p>&nbsp;</p>
  <p></p>
  <table width="800" border="0" align="center" cellpadding="2" cellspacing="1" class="table_margin">
    <tr>
      <td height="22" colspan="2" class="title">广告管理：修改广告</td>
    </tr>
   <tr>
      <td class="tdright_new">广告类型</td>
      <td><select name="adTypeId" size="1" class="Select">
        <c:forEach var="adType" items="${adType}">
          <option value="${adType.id}" <c:if test="${AD.adTypeId==adType.id}" var="true">selected="selected"</c:if>>${adType.adTypeName}</option>
        </c:forEach>
      </select>
      必选</td>
    </tr>
    <tr>
      <td class="tdright_new">广告标题</td>
      <td class="tdlefts"><input name="adTitle" type="text" value="${AD.adTitle}"/>必填<input name="state" id="state" type="hidden" value="<%=state%>"></td>
    </tr>
    <tr>
      <td class="tdright_new">广告简介</td>
      <td class="tdlefts"><input name="adSynopsis" type="text" value="${AD.adSynopsis}" /></td>
    </tr>
    <tr>
      <td class="tdright_new">广告链接</td>
      <td class="tdlefts"><input name="adLink" type="text" value="${AD.adLink}" /></td>
    </tr>
    <tr>
     <td class="tdright_new">
						<div align="left">上传照片						</div>					</td>
	  <td class="tdlefts"><img src="<%=request.getContextPath()%>/incoming/${AD.adImage}" width="127" height="83" border="0"/>
						<label>
							<iframe name="uploadframe" id="uploadframe" width=100% height='120' src="<%=request.getContextPath()%>/web/FileUploadInner1.jsp" frameborder=0 scrolling=no></iframe><input type="hidden" name="photourl" id="photourl" value="${AD.adImage}" />
						</label>  		
						必选</td>
    </tr>
  </table>
  <table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr align="center">
      <td><input type="submit" name="Submit" class="input" value="保存" /> 　
        <input type="reset" name="Submit2" class="input" value="重置" />
&nbsp;<input type="hidden" name="id" value="${AD.id}" /></td>
    </tr>
  </table>
</form>
  </body>
<script type="text/javascript">
function setphotourl1(url)
        {
		document.all.photourl.value=url;
		}
</script>
</html>

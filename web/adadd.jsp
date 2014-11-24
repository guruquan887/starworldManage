<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>增加广告页面</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
  </head>
  <script language="javascript" type="text/javascript">
  function adaddcheck(){
	var adTypeId = document.form1.adTypeId.value;
	var adTitle = document.form1.adTitle.value;
	var adSynopsis = document.form1.adSynopsis.value;
	var adLink = document.form1.adLink.value;
	var photourl = document.form1.photourl.value;
	if(adTypeId==0){
		alert("请选择广告类型");
		return false;
		}
	if(adTitle==""){
		alert("广告标题不能为空");
		return false;
		}
	if(photourl==""){
		alert("广告图片不能为空");
		return false;
		}
	}
  </script>
  <body>
  <form id="form1" name="form1" method="post" onSubmit="return adaddcheck()" action="<%=request.getContextPath()%>/web/adAdd.do">
  <p>&nbsp;</p>
  <p></p>
  <table width="800" border="0" align="center" cellpadding="2" cellspacing="1" class="table_margin">
    <tr>
      <td height="22" colspan="2" class="title">广告管理：添加广告</td>
    </tr>
     <tr>
	   <td class="tdright_new">广告类型</td>
      <td><select name="adTypeId" size="1" class="Select">
      <option value="0" selected="selected" >---选择适当广告类型---</option>
        <c:forEach var="adType" items="${adType}">
          <option value="${adType.id}">${adType.adTypeName }</option>
        </c:forEach>
      </select>
      必选</td>
	      </tr>
	   <tr>
      <td class="tdright_new">广告标题</td>
      <td class="tdlefts"><input name="adTitle" id="adTitle" type="text" />
      必填</td>
    </tr>
    <tr>
      <td class="tdright_new">广告简介</td>
      <td class="tdlefts"><input name="adSynopsis" id="adSynopsis" type="text" /></td>
    </tr>    
    <tr>
      <td class="tdright_new">广告链接</td>
      <td class="tdlefts"><input name="adLink" id="adLink" type="text" /></td>
    </tr>
    <tr>
     <td class="tdright_new">
				<div align="right">上传照片</div>					</td>
					<td class="tdlefts">
						<iframe name="uploadframe" id="uploadframe" width=100% height='120' src="<%=request.getContextPath()%>/web/FileUploadInner1.jsp" frameborder=0 scrolling=no></iframe><input type="hidden" name="photourl" id="photourl" />					
						必选</td>
    </tr>
  </table>
  <table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr align="center">
      <td><input type="submit" name="Submit" value="保存" class="input"/> 　
        <input type="reset" name="Submit2" value="重置" class="input" />
&nbsp;</td>
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

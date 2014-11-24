<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改页面</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
  </head>
  <script language="javascript" type="text/javascript">
  function adaddcheck(){
	var gameName = document.form1.gameName.value;
	if(gameName==""){
		alert("游戏名称不能为空");
		return false;
		}
		return true;
	}
  </script>
  <script type="text/javascript" src="../FCKeditor/fckeditor.js"></script>
  <body>
  <form id="form1" name="form1" method="post" onSubmit="return adaddcheck()" action="<%=request.getContextPath()%>/web/gameList.do?action=gameUpdate">
  <p>&nbsp;</p>
  <p></p>
  <table width="800" border="0" align="center" cellpadding="2" cellspacing="1" class="table_margin">
    <tr>
      <td height="22" colspan="2" class="title">游戏管理：修改游戏</td>
    </tr>
   <tr>
      <td class="tdright_new">游戏类型</td>
      <td><select name="gameType" size="1" class="Select">
         <c:if test="${dto.gameType==0}"> <option value="0" selected="selected">棋牌游戏</option></c:if>
       <c:if test="${dto.gameType==2}"> <option value="2" selected="selected">彩票游戏</option></c:if>
	   <c:if test="${dto.gameType==1}"> <option value="1" selected="selected">电子游艺</option></c:if>
      </select>
      必选</td>
    </tr>
    <tr>
      <td class="tdright_new">游戏名称</td>
      <td class="tdlefts"><input name="gameName" type="text" value="${dto.gameName}"/>必填</td>
    </tr>
    <tr>
      <td class="tdright_new">游戏简介</td>
      <td class="tdlefts"><textarea id="content" name="content">${dto.gameDes}</textarea>
	  <script type="text/javascript">  
			var oFCKeditor = new FCKeditor( 'content' ) ;     
			oFCKeditor.ToolbarSet = 'Default' ;   
			oFCKeditor.Width = '100%';   
			oFCKeditor.Height = '400';   
			oFCKeditor.Value = '' ;   
			oFCKeditor.ReplaceTextarea();     
			</script>  
		必填</td>
    </tr>
    <tr>
      <td class="tdright_new">游戏链接</td>
      <td class="tdlefts"><input name="gameUrl" type="text" value="${dto.gameUrl}" size="30" /></td>
    </tr>
    <tr>
     <td class="tdright_new">
						<div align="left">上传照片						</div>					</td>
	  <td class="tdlefts"><img src="<%=request.getContextPath()%>/incoming/${dto.gamephoto2}" width="127" height="83" border="0"/>
						<label>
							<iframe name="uploadframe" id="uploadframe" width=100% height='120' src="<%=request.getContextPath()%>/web/FileUploadInner1.jsp" frameborder=0 scrolling=no></iframe><input type="hidden" name="photourl" id="photourl" value="${dto.gamephoto2}" />
						</label>  		
						必选</td>
    </tr>
  </table>
  <table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr align="center">
      <td><input type="submit" name="Submit" value="保存" /> 　
        <input type="reset" name="Submit2" value="重置" />
&nbsp;<input type="hidden" name="id" value="${dto.id}" /></td>
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

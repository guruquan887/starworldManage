<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>增加页面</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
  </head>
  <script language="javascript" type="text/javascript">
  function adaddcheck(){
	var gameName = document.form1.gameName.value;
	var content = document.form1.content.value;
	var gameUrl = document.form1.gameUrl.value;
	var photourl = document.form1.photourl.value;
	if(gameName==""){
		alert("游戏名称不能为空");
		return false;
		}
	if(content==""){
	alert("游戏简介不能为空");
	return false;
	}
	if(gameUrl==""){
	alert("游戏链接不能为空");
	return false;
	}
	if(photourl==""){
	alert("请上传游戏图片");
	return false;
	}
		return true;
	}
  </script>
  <script type="text/javascript" src="../FCKeditor/fckeditor.js"></script>
  <body>
  <form id="form1" name="form1" method="post" onSubmit="return adaddcheck()" action="<%=request.getContextPath()%>/web/gameList.do?action=gameAdd">
  <p>&nbsp;</p>
  <p></p>
  <table width="800" border="0" align="center" cellpadding="2" cellspacing="1" class="table_margin">
    <tr>
      <td height="22" colspan="2" class="title">游戏管理：增加游戏</td>
    </tr>
   <tr>
      <td class="tdright_new">游戏类型</td>
      <td><select name="gameType" size="1" class="Select">
         <option value="0" selected="selected">棋牌游戏</option>
       <option value="2" selected="selected">彩票游戏</option>
	   <option value="1" selected="selected">电子游艺</option>
      </select>
      必选</td>
    </tr>
    <tr>
      <td class="tdright_new">游戏名称</td>
      <td class="tdlefts"><input name="gameName" id="gameName" type="text" value=""/>必填</td>
    </tr>
    <tr>
      <td class="tdright_new">游戏简介</td>
      <td class="tdlefts"><textarea id="content" name="content"></textarea>
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
      <td class="tdlefts"><input name="gameUrl" id="gameUrl" type="text" value="" size="30" /></td>
    </tr>
    <tr>
     <td class="tdright_new">
						<div align="left">上传照片						</div>					</td>
	  <td class="tdlefts">
						<label>
							<iframe name="uploadframe" id="uploadframe" width=100% height='120' src="<%=request.getContextPath()%>/web/FileUploadInner1.jsp" frameborder=0 scrolling=no></iframe><input type="hidden" name="photourl" id="photourl" value="" />
						</label>  		
						必选</td>
    </tr>
  </table>
  <table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr align="center">
      <td><input type="submit" name="Submit" value="添加" /> 　
        <input type="reset" name="Submit2" value="重置" /></td>
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

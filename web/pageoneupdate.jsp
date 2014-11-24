<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
  String id = "";
  if(request.getParameter("id") != null){
     id = request.getParameter("id");
  }
  String classcode="";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<script language="javascript" type="text/javascript">
function submitform(){
	document.all.submithid.click();
}

function newsaddcheck(){
	var newstitle = document.form1.newstitle.value;
	var content = document.form1.content.value;
	if(newstitle==""){
		 alert("单页标题不能为空");
		 return false;
		}
	if(content==""){
		alert("单页内容不能为空");
		return false;
		}
}
</script>
<script type="text/javascript" src="../FCKeditor/fckeditor.js"></script>
<body>
	<Div class="title"><span>
<%--	  <label>
	  <input name="Submit" type="button" class="input" value="类别管理" />
	  </label>--%>
	</span>内容添加</Div>
    <form id="form1" name="form1" method="post" onSubmit="return newsaddcheck()" action="<%=request.getContextPath()%>/web/pageOneUpdate.do">
	<input type="hidden" value="${NEWS.classcode}" name="classcode" />
	<input type="hidden" value="${NEWS.id}" name="id" />
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
        <tr>
          <td width="120" class="menutop">项目</td>
          <td class="menutop">内容</td>
        </tr>
        <tr>
          <td class="tdright_new">新闻标题：</td>
          <td class="tdlefts"><label>
            <input name="newstitle" type="text" value="${NEWS.newstitle}"/>
          </label></td>
        </tr>
       
        <tr>
          <td class="tdright_new">单页内容：</td>
          <td class="tdlefts"><label>
            <textarea id="content" name="content">${NEWS.newsinfo}</textarea>
			<script type="text/javascript">  
			var oFCKeditor = new FCKeditor( 'content' ) ;     
			oFCKeditor.ToolbarSet = 'Default' ;   
			oFCKeditor.Width = '100%';   
			oFCKeditor.Height = '400';   
			oFCKeditor.Value = '' ;   
			oFCKeditor.ReplaceTextarea();     
			</script>  
		必填
          </label></td>
        </tr>
        <tr>
          <td class="tdright_new">&nbsp;</td>
          <td class="tdlefts"><label>
            <input name="Submit2" type="submit" class="input" value="确定修改" />
         &nbsp; 
         <input name="Submit22" type="reset" class="input" value="重新填写"/>
          </label></td>
        </tr>
      </table>
</form>
</body>
</html>
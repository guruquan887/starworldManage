<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
  String id = "";
  if(request.getParameter("id") != null){
     id = request.getParameter("id");
  }
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
	var classname = document.form1.classcode.value;
	var newstitle = document.form1.newstitle.value;
	var content = document.form1.content.value;
	if(classname==0){
		  alert("请选择新闻类型");
		  return false;
		}
	if(newstitle==""){
		  alert("新闻标题不能为空");
		  return false;
		
		}
	if(content==""){
		alert("新闻内容不能为空");
		return false;
		}
}
</script>
<script type="text/javascript" src="../FCKeditor/fckeditor.js"></script>
<body>
	<Div class="title"><span>
	  <label>
	  <input name="Submit" type="button" class="input" value="类别管理" />
	  </label>
	</span>内容添加</Div>
    <form id="form1" name="form1" method="post" onSubmit="return newsaddcheck()" action="<%=request.getContextPath()%>/web/newsUpdate.do">
	<input type="hidden" value="${NEWS.newsID}" name="newsID" />
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
	  
        <tr>
          <td width="120" class="menutop">项目</td>
          <td class="menutop">内容</td>
        </tr>
		 <tr>
          <td class="tdright_new">新闻类别：</td>
          <td class="tdlefts"><label>
            <select name="classID">
			<option value="1" <c:if test="${NEWS.classID==1}"> selected="selected" </c:if>>新闻</option>	
			<option value="2" <c:if test="${NEWS.classID==2}"> selected="selected" </c:if>>公告</option>	
        </select>
		必选
          </label></td>
        </tr>
        <tr>
          <td class="tdright_new">新闻标题：</td>
          <td class="tdlefts"><label>
            <input name="newstitle" type="text" value="${NEWS.subject}" size="50" style="width:400px"/>
          </label></td>
        </tr>
       <tr>
          <td class="tdright_new">属性：</td>
          <td class="tdlefts"><label>
            <input type="checkbox" name="in_OnTop" value="1" <c:if test="${NEWS.onTop==1}"> checked="checked"  </c:if>/>
          置顶新闻
          <input type="checkbox" name="in_OnTopAll" value="1" <c:if test="${NEWS.onTopAll==1}"> checked="checked"  </c:if>/>
          总置顶新闻
          <input type="checkbox" name="in_IsElite" value="1" <c:if test="${NEWS.isElite==1}"> checked="checked"  </c:if>/>
          推荐新闻
          <input type="checkbox" name="in_IsHot" value="1" <c:if test="${NEWS.isHot==1}"> checked="checked"  </c:if>/>
          热门新闻
          <input type="checkbox" name="in_IsLock" value="1" <c:if test="${NEWS.isLock==1}"> checked="checked"  </c:if> />
          立即发布</label></td>
        </tr>
        <tr>
          <td class="tdright_new">引用：</td>
          <td class="tdlefts"><input name="in_LinkUrl" id="in_LinkUrl" type="text" size="50" style="width:400px" value="${NEWS.linkUrl}"/>
            <label>
            <input type="checkbox" name="in_IsLinks" id="in_IsLinks" value="1" <c:if test="${NEWS.isLinks==1}"> checked="checked"  </c:if>/>
            链接地址
            </label></td>
        </tr>
        <tr>
          <td class="tdright_new">新闻内容：</td>
          <td class="tdlefts"><label>
            <textarea id="content" name="content">${NEWS.body}</textarea>
			<script type="text/javascript">  
			var oFCKeditor = new FCKeditor( 'content' ) ;     
			oFCKeditor.ToolbarSet = 'Default' ;   
			oFCKeditor.Width = '100%' ;   
			oFCKeditor.Height = '400' ;   
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
         <input name="Submit22" type="reset" class="input" value="重置"/>
          </label></td>
        </tr>
      </table>
</form>
</body>

</html>
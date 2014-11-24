<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改问题</title>
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
		  alert("标题不能为空");
		  return false;
		
		}
	if(content==""){
		alert("内容不能为空");
		return false;
		}
}
</script>
<script type="text/javascript" src="../FCKeditor/fckeditor.js"></script>
<body>
	<Div class="title"><span>
	</span>内容修改</Div>
    <form id="form1" name="form1" method="post" onSubmit="return newsaddcheck()" action="<%=request.getContextPath()%>/web/issueUpdate.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
	  <input type="hidden" value="${issue.issueID}" name="issueID" />
        <tr>
          <td width="120" class="menutop">项目</td>
          <td class="menutop">内容</td>
        </tr>
        <tr>
          <td class="tdright_new">问题标题：</td>
          <td class="tdlefts"><label>
            <input name="issuetitle" type="text" size="50" style="width:400px" value="${issue.issueTitle}"/>
          </label></td>
        </tr>
        <tr>
          <td class="tdright_new">禁用状态：</td>
          <td class="tdlefts"><label>
              <input type="radio" name="nullity" value="0" <c:if test="${issue.nullity==0}">checked="checked"</c:if>/>
          否
          <input type="radio" name="nullity" value="1" <c:if test="${issue.nullity==1}">checked="checked"</c:if>/>
          是</label></td>
        </tr>
       
        <tr>
          <td class="tdright_new">问题内容：</td>
          <td class="tdlefts"><label>
            <textarea id="content" name="content">${issue.issueContent}</textarea>
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
            <input name="Submit2" type="submit" class="input" value="保存" />
         &nbsp; 
         <input name="Submit22" type="reset" class="input" value="重置"/>
          </label></td>
        </tr>
      </table>
</form>
</body>
</html>
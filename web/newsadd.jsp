<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
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
	  <label>
	  <input name="Submit" type="button" class="input" value="类别管理" />
	  </label>
	</span>内容添加</Div>
    <form id="form1" name="form1" method="post" onSubmit="return newsaddcheck()" action="<%=request.getContextPath()%>/web/newsAdd.do">
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
	  
        <tr>
          <td width="120" class="menutop">项目</td>
          <td class="menutop">内容</td>
        </tr>
		 <tr>
          <td class="tdright_new">新闻类别：</td>
          <td class="tdlefts"><label>
           <select name="classID">
			<option value="1">新闻</option>	
			<option value="2">公告</option>	
        </select>
		必选
          </label></td>
        </tr>
        <tr>
          <td class="tdright_new">新闻标题：</td>
          <td class="tdlefts"><label>
            <input name="newstitle" type="text" size="50" style="width:400px"/>
          </label></td>
        </tr>
        <tr>
          <td class="tdright_new">属性：</td>
          <td class="tdlefts"><label>
            <input type="checkbox" name="in_OnTop" value="1" />
          置顶新闻
          <input type="checkbox" name="in_OnTopAll" value="1" />
          总置顶新闻
          <input type="checkbox" name="in_IsElite" value="1" />
          推荐新闻
          <input type="checkbox" name="in_IsHot" value="1" />
          热门新闻
          <input type="checkbox" name="in_IsLock" value="1" checked="checked" />
          立即发布</label></td>
        </tr>
        <tr>
          <td class="tdright_new">引用：</td>
          <td class="tdlefts"><input name="in_LinkUrl" id="in_LinkUrl" type="text" size="50" style="width:400px" value="http://"/>
            <label>
            <input type="checkbox" name="in_IsLinks" id="in_IsLinks" value="1" />
            链接地址
            </label></td>
        </tr>
       
        <tr>
          <td class="tdright_new">新闻内容：</td>
          <td class="tdlefts"><label>
            <textarea id="content" name="content"><br></textarea>
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
            <input name="Submit2" type="submit" class="input" value="确定添加" />
         &nbsp; 
         <input name="Submit22" type="reset" class="input" value="重新填写"/>
          </label></td>
        </tr>
      </table>
</form>
</body>
</html>
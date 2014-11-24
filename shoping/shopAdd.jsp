<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String msg="";
	if(request.getAttribute("msg")!=null){
	msg=request.getAttribute("msg").toString();
}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../FCKeditor/fckeditor.js"></script>
<script language="javascript" type="text/javascript">
  var msg="<%=msg %>";
  if(msg!='')alert(msg);
  
function check(){
  var price_gold = document.form1.price_gold.value;
  var vipScore = document.form1.vipScore.value;
  
  if(isNaN(price_gold)||price_gold==''){
     alert('清空请填0');
     return false;
  }
  if(isNaN(vipScore)||vipScore==''){
     alert('清空请填0');
	 return false;
  }
  return true;
}

function isNumber(oNum){

 if(!oNum) return false; //line:160
 if(oNum.value=="") return false;
 var vv = oNum.value.replace(/[^\d]/g,'');
 var strP=/^\d+$/;
  if (!isNaN(oNum.value)) {
        return true;
    } else {
        alert("请输入数字");
        oNum.value=vv;
 		oNum.focus();
 		return false; 
    }
}

function gameshopaddcheck(){
    var typeId = document.form1.typeId.value;
	var cateName = document.form1.mallName.value;
	var price1 = document.form1.price_gold.value;
	var price2 = document.form1.price_score.value;
	var count = document.form1.count.value;
	var image = document.form1.photourl1.value;

	if(typeId==0){
	  alert("请选择类型");
	  return false;
	}
	if(cateName==""){
	  alert("名称不能为空");
	  return false;
	}
	if(price1==""){
	  alert("请输入普通价格");
	  return false;
	}
	if(price2==""){
	  alert("请输入会员价格");
	  return false;
	}
	if(count==""){
	  alert("请输入商品数量");
	}
	if(image==""){
	  alert("请上传图片");
	  return false;
	}
  }
  </script>
</head>
<body>
	<Div class="title">商品添加</Div>
		<form id="form1" name="form1" method="post" onSubmit="return gameshopaddcheck()" action="<%=request.getContextPath()%>/shoping/gameshopList.do?action=addMallItems">
		
		  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
            <tr>
              <td width="120" class="menutop">项目</td>
              <td class="menutop">内容</td>
            </tr>
            <tr >
              <td class="tdright_new">商品类型：</td>
              <td class="tdlefts"><select name="typeId" id="typeId" size="1" class="Select">
         <option value="0" selected="selected" >---选择类型---</option>
         <c:forEach var="gsType" items="${gsType}">
           <option value="${gsType.typeID}">${gsType.typeName}</option>
         </c:forEach>
       </select></td>
            </tr>
            <tr>
              <td class="tdright_new">商品名称：</td>
              <td class="tdlefts"><input type="text" name="mallName" id="mallName" /></td>
            </tr>
            <tr>
              <td class="tdright_new">普通价格：</td>
              <td class="tdlefts"><input type="text" name="price_gold" id="price_gold" onkeyup="isNumber(this)"/></td>
            </tr>
            <tr>
              <td class="tdright_new">会员价格：</td>
              <td class="tdlefts"><input type="text" name="vipPrice" id="vipPrice" onkeyup="isNumber(this)" /></td>
            </tr>
            <tr>
              <td class="tdright_new">商品数量：</td>
              <td class="tdlefts"><input type="text" name="count" id="count" onkeyup="isNumber(this)"/></td>
            </tr>
            <tr>
              <td class="tdright_new">上传照片：</td>
              <td class="tdlefts"><iframe name="uploadframe" id="uploadframe" width=100% height='120' src="<%=request.getContextPath()%>/shoping/FileUploadInner1.jsp" frameborder=0 scrolling=no></iframe><input type="hidden" name="photourl" id="photourl" /></td>
            </tr>
            <tr>
              <td class="tdright_new">商品描述：</td>
              <td class="tdlefts"><textarea name="cateNote"></textarea>
			  <script type="text/javascript">  
				var oFCKeditor = new FCKeditor( 'cateNote' ) ;   
				//oFCKeditor.BasePath = 'FCKeditor/' ;   
				oFCKeditor.ToolbarSet = 'Default' ;   
				oFCKeditor.Width = '100%' ;   
				oFCKeditor.Height = '400' ;   
				oFCKeditor.Value = '' ;   
				oFCKeditor.ReplaceTextarea();    
				//oFCKeditor.Create() ;   
			  </script>  </td>
            </tr>
            <tr>
              <td class="tdright_new">&nbsp;</td>
              <td class="tdlefts"><label>
                <input name="Submit" type="submit" class="input" value="确定添加" />
                <input name="Submit2" type="reset" class="input" value="重新填写" />
              </label></td>
            </tr>
          </table>
</form>
<script type="text/javascript">
function setphotourl1(url)
        {
		document.all.photourl.value=url;
		}
</script>
</body>
</html>
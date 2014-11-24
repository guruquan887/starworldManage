<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />

<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/AuthorityDwr.js'> </script>
<script src="../js/jquery.min.js" type="text/javascript"></script>
<script language="javascript" src="../js/ajax.js"></script>

</head>
<%
   int userid = 0;
	if(request.getParameter("id")!= null){
		userid = Integer.parseInt(request.getParameter("id"));
	}
	String msg="";
	if(request.getAttribute("msg")!=null){
		msg= request.getAttribute("msg").toString();
	}
	
%>
<script language="javascript" type="text/javascript">
function isBlank(str) {
	var blankFlag = true;
	if (str.length == 0) 
		return true;
	for (var i = 0; i < str.length; i++) {
		if ((str.charAt(i) != "") && (str.charAt(i) != " ")) {
			blankFlag = false;
			break;
		}
	}
	return blankFlag;
}

function CheckNull(theField, fieldName) {	
	if(isBlank(theField.value)){
		alert(fieldName);
		theField.focus();
	return 1;
	}
	return 0;
}
function save(){
	
	if (CheckNull(document.forms['form2'].password, '密码不能为空!')){
		return false;
	}
	form2.submit();

}


$(document).ready(function() {
	SetContantFirst();
});

$(document).ready(function() {
$("#alls").click(function() {	
$("#form1 input").each(function(i) { //("input[type='checkbox']")
	var id = this.id.replace("menu",'');
	if(this.checked == false){
		changeState2(id);
		//this.checked = true;
	}
});
});
});

$(document).ready(function() {
$("#allno").click(function() {	
$("#form1 input").each(function(i) {
	var id = this.id.replace("menu",'');
	if(this.checked == true){
		changeState1(id);
		//this.checked = false;
	}else{
		changeState2(id);
		//this.checked = true;
	}
});
});
});

function changeQx(id){
	var tid = "td"+id;
	var t=document.getElementById(tid).getElementsByTagName("input");
	for(var i=0;i<t.length;i++){
		//alert(t[i].checked+"--"+t[i].id);
		var id = t[i].id.replace("menu",'');
		if(t[i].checked == true){
		    
			changeState1(id); //删除
		}
		else{
			changeState2(id); //添加
		}
	}
}

       //弹出友好提示
       var msg="<%=msg %>";
       if(msg!='')alert(msg);

//页面加载时  显示第一级的所有选项
function SetContantFirst()
{
	var fid = 0;
	var uid = parseInt(document.getElementById("userid").value);
	AuthorityDwr.SetContantFirst(parseInt(fid),uid,function(data) {
		dwr.util.setValue("level1", data,{ escapeHtml:false });
	});
}
//加载二级选项
function SetContantSecond(fid,open)
{
	var uid = parseInt(document.getElementById("userid").value);
	if(document.getElementById("level2-"+fid).innerHTML!=''){
		dwr.util.setValue("level2-"+fid,'',{ escapeHtml:false });
		document.getElementById("level2-"+fid).style.display="none";
		return;
	}
	AuthorityDwr.SetContantSecond(parseInt(fid),uid,function(data) {
		if(data!='')
		{
			document.getElementById("level2-"+fid).style.display="";
			dwr.util.setValue("level2-"+fid, data,{ escapeHtml:false });
			
		}
		
	});
}
//加载三级选项
function SetContantThird(fid,step)
{
	var uid = parseInt(document.getElementById("userid").value);
	//能级管理选项显示
	AuthorityDwr.SetContantThird(parseInt(fid),uid,function(data) {
		if(data!='')
		{
			dwr.util.setValue("level3-"+fid, data,{ escapeHtml:false });
		}
	});
}
//加载四级选项
function SetContantFourth(fid,step)
{
	var uid = parseInt(document.getElementById("userid").value);
	//能级管理选项显示
	AuthorityDwr.SetContantFourth(parseInt(fid),uid,function(data) {
		if(data!='')
		{
			dwr.util.setValue("level4-"+fid, data,{ escapeHtml:false });
		}
	});
}
//加载四级选项
function SetContantFifth(fid,step)
{
	var uid = parseInt(document.getElementById("userid").value);
	//能级管理选项显示
	AuthorityDwr.SetContantFifth(parseInt(fid),uid,function(data) {
		if(data!='')
		{
			dwr.util.setValue("level5-"+fid, data,{ escapeHtml:false });
		}
	});
}
function changeSelectState(id,cdname)
{
	var uid = parseInt(document.getElementById("userid").value);
	if(document.getElementById("menu"+id).checked==true)
	{
		//删除选中的权限和级联的权限
		AuthorityDwr.GetAuthority(id,uid,function(data) {
			var	idArr = new Array();  
			idArr = data.split("-");
			for(var i=0;i<idArr.length;i++)
			{
				if(document.getElementById("menu"+idArr[i])!=null)
				{
					document.getElementById("menu"+idArr[i]).checked=true;
				}
			}
			//alert("\""+cdname+"\" 添加成功！");
		});
	}else
	{
		//删除选中的权限和级联的权限
		AuthorityDwr.ReleaseAuthority(id,uid,function(data) {
			var	idArr = new Array();  
			idArr = data.split("-");
			for(var i=0;i<idArr.length;i++)
			{
				if(document.getElementById("menu"+idArr[i])!=null)
				{
					document.getElementById("menu"+idArr[i]).checked=false;
				}
			}
			//alert("\""+cdname+"\" 取消成功！");
		});
	}
}

function changeState1(id){
		var uid = parseInt(document.getElementById("userid").value);
		//删除选中的权限和级联的权限
		AuthorityDwr.ReleaseAuthority(id,uid,function(data) {
			var	idArr = new Array();  
			idArr = data.split("-");
			for(var i=0;i<idArr.length;i++)
			{
				if(document.getElementById("menu"+idArr[i])!=null)
				{
				   //alert(document.getElementById("menu"+idArr[i]).checked+",11,"+idArr[i]);
					document.getElementById("menu"+idArr[i]).checked=false;
				}
			}
		});
	}
function changeState2(id){
		var uid = parseInt(document.getElementById("userid").value);
		AuthorityDwr.GetAuthority(id,uid,function(data) {
			var	idArr = new Array();  
			idArr = data.split("-");
			for(var i=0;i<idArr.length;i++)
			{
				if(document.getElementById("menu"+idArr[i])!=null)
				{
					//alert(document.getElementById("menu"+idArr[i]).checked+",22,"+idArr[i]);
					document.getElementById("menu"+idArr[i]).checked=true;
				}
			}
		});
	}
	
//表单提交判断
function isBlank(str) {
	var blankFlag = true;
	if (str.length == 0) 
		return true;
	for (var i = 0; i < str.length; i++) {
		if ((str.charAt(i) != "") && (str.charAt(i) != " ")) {
			blankFlag = false;
			break;
		}
	}
	return blankFlag;
}
function CheckNull(theField, fieldName) {	
	if(isBlank(theField.value)){
		alert(fieldName);
		theField.focus();
	return 1;
	}
	return 0;
}
function save(){
	
	if (CheckNull(document.getElementById("password"), '密码不能为空!')){
		return false;
	}
	if(document.getElementById("password").value!=document.getElementById("password1").value){
	    alert("两次密码输入不一致，请重新输入");
		return false;
	}
	form2.submit();

}

</script>
<body>
	<Div class="title">账户管理—编辑</Div>
	
	
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
	  <form id="form2" name="form2" method="post" action="<%=request.getContextPath()%>/system/adminList.do?action=update">
        <tr>
          <td width="120" class="menutop">项目</td>
          <td class="menutop">内容</td>
        </tr>
        <tr>
          <td class="tdright_new">用户名：</td>
          <td class="tdright">${ADMIN.userName}<input type="hidden" name="id" value="<%=userid%>" /></td>
        </tr>
        <tr>
          <td class="tdright_new">密码：</td>
          <td class="tdright"><label>
            <input name="password" id="password" type="text" class="input_width2" value="" />
          </label></td>
        </tr>
		<tr>
          <td class="tdright_new">确认密码：</td>
          <td class="tdright"><label>
            <input name="password1" id="password1" type="text" class="input_width2" value="" />
          </label></td>
        </tr>
		</form>
      </table>

<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
    <tr>
      <td colspan="2">
	  <c:if test="${USER.roleId == 1}" var="true"><div id="level1"></div>
	  </c:if></td>
    </tr>
	<tr>
	<td></td>
	<td class="tdright"><label><input type="hidden" id="userid" name="userid" value="<%=userid %>">           
	<input name="back" type="button" class="input" id="back" onClick="save()" value="确定修改" />
                   <c:if test="${USER.roleId == 1}" var="true"><input type="button" name="alls" id="alls" class="input" value="全选" />
                  <input  type="button" name="allno" id="allno" class="input" value="反选" /></c:if></label></td>
	</tr>
</table>
</body>
</html>
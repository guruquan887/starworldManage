<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
String msg="";
int userID = 0;
if(request.getAttribute("msg")!=null){
	msg=request.getAttribute("msg").toString();
}
if(request.getParameter("userID")!= null){
	userID = Integer.parseInt(request.getParameter("userID"));
}
int spreaderID = 3;
int levelID = 0;
if(request.getParameter("spreaderID")!=null){
	    spreaderID = Integer.parseInt(request.getParameter("spreaderID"));
	}
	if(request.getParameter("levelID")!=null){
	    levelID = Integer.parseInt(request.getParameter("levelID"));
	}
String accounts = "";
if(request.getParameter("accounts")!=null){
   accounts = request.getParameter("accounts");
}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/AgentAuthorityDwr.js'> </script>
<script src="../js/jquery.min.js" type="text/javascript"></script>
<script language="javascript" src="../js/ajax.js"></script>
</head>
<script language="javascript" type="text/javascript">
 var msg="<%=msg %>";
 if(msg!='')alert(msg);

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

//页面加载时  显示第一级的所有选项
function SetContantFirst()
{
	var fid = 0;
	var uid = parseInt(document.getElementById("userid").value);
	AgentAuthorityDwr.SetContantFirst(parseInt(fid),uid,function(data) {
		dwr.util.setValue("level1", data,{ escapeHtml:false });
	});
}
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

function changeSelectState(id,cdname)
{
	var uid = parseInt(document.getElementById("userid").value);
	if(document.getElementById("menu"+id).checked==true)
	{
		//删除选中的权限和级联的权限
		AgentAuthorityDwr.GetAuthority(id,uid,function(data) {
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
		AgentAuthorityDwr.ReleaseAuthority(id,uid,function(data) {
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
		AgentAuthorityDwr.ReleaseAuthority(id,uid,function(data) {
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
		AgentAuthorityDwr.GetAuthority(id,uid,function(data) {
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
function save(){

	form2.submit();

}

</script>

<body>
	<div class="title">权限</Div>
	<form id="form2" name="form2" method="post" action="<%=request.getContextPath()%>/member/gameAgentList.do?action=updateauthRelation" onsubmit="return confirm('確定?');">
<input name="spreaderID" type="hidden" value="<%=spreaderID%>" /><input name="levelID" type="hidden" value="<%=levelID%>" /><input name="sxaccounts" type="hidden" value="<%=accounts%>" />
<input type="hidden" name="type" value="1" />
</form>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
          <tr align="center">
            <td class="tdlefts">权限名</td>
          </tr>
	 	<td colspan="3" ><div id="level1"></div></td>
        </table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
		    <tr><td height="5"></td></tr>
            <tr>
                <td align="center"><input type="hidden" name="userid" id="userid" value="<%=userID%>" /><input name="back" type="button" class="input" id="back" onClick="save()" value="确定修改" /><%--<input type="button" name="alls" id="alls" class="input" value="全选" />
                  <input  type="button" name="allno" id="allno" class="input" value="反选" />--%>
				<%--<input type="button" class="input" onclick='javascript:history.go(-1)' name="ctl00" value="返回"  />--%></td>
            </tr>
	  </table>

</body>
</html>
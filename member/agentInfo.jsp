<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
int curPage = 0;
   if(request.getParameter("curPage")!=null){
        curPage = Integer.parseInt(request.getParameter("curPage"));
   }
String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}
String selectOne="";
	if(request.getParameter("selectOne")!=null){
		selectOne=request.getParameter("selectOne");
	}
int type = 1;
   if(request.getParameter("type")!=null){
        type = Integer.parseInt(request.getParameter("type"));
   }
double sxwinner = 0;
   if(request.getAttribute("sxwinner")!=null){
	   sxwinner = (Double)(request.getAttribute("sxwinner"));
   }
double sxbrokerage = 0;
   if(request.getAttribute("sxbrokerage")!=null){
	   sxbrokerage = (Double)(request.getAttribute("sxbrokerage"));
   }
double sxtaxRate = 0;
   if(request.getAttribute("sxtaxRate")!=null){
	   sxtaxRate = (Double)(request.getAttribute("sxtaxRate"));
   }
int spreaderID = 3;
if(request.getParameter("spreaderID")!=null){
	    spreaderID = Integer.parseInt(request.getParameter("spreaderID"));
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
<script language="javascript" src="<%=request.getContextPath()%>/js/userweb.js"></script>
<%--<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'> </script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'> </script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/AgentAuthorityDwr.js'> </script>--%>
<script src="../js/jquery.min.js" type="text/javascript"></script>
<script language="javascript" src="../js/ajax.js"></script>
<script language="javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
function checkbox()
{
var str=document.getElementsByName("limit");
var objarray=str.length;
var chestr="";
for (i=0;i<objarray;i++)
{
  if(str[i].checked == true)
  {
   chestr+=str[i].value+",";
  }
}
if(chestr == "")
{
  //alert("请选择权限!");
}
else
{
  //alert("您现择的是："+chestr);
}
}
function isNumber(oNum) {
 if(!oNum) return false; //line:160
 if(oNum.value=="") return false;
 var vv = oNum.value.replace(/[^\d]/g,'');
 var strP=/^\d+$/;
  if (!isNaN(oNum.value)) {
        return true;
    } else {
        alert("请输入整数");
        oNum.value=vv;
 		oNum.focus();
 		return false; 
    }
 }
function SelectAllTable(flag,tableID)
{
    var m_list_table =  window.document.getElementById(tableID);
    var m_list_checkbox = GelTags("input", m_list_table);
    for (var i = m_list_checkbox.length - 1; i >= 0; i--) {
        m_list_checkbox[i].checked = flag;
    }
}
function GelTags(tag, ob) {
    return (ob || gd).getElementsByTagName(tag);
}
function check(){
var taxRate = document.getElementById("taxRate").value;
var brokerage = document.getElementById("brokerage").value;
var winner = document.getElementById("winner").value;
if(parseFloat(taxRate)>'<%=sxtaxRate%>'){
   alert("税收提成超出上限!");
   return false;
}
if(parseFloat(brokerage)>'<%=sxbrokerage%>'){
   alert("洗码佣金超出上限!");
   return false;
}
if(parseFloat(winner)>'<%=sxwinner%>'){
   alert("股份占成超出上限!");
   return false;
}
if (CheckNull(document.forms['form2'].nickName, '昵称不能为空!')){
		return false;
	}
	form2.submit();

return true;

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


//页面加载时  显示第一级的所有选项
function SetContantFirst()
{
	var fid = 0;
	var uid = parseInt(document.getElementById("proxyID").value);
	AgentAuthorityDwr.SetContantFirst(parseInt(fid),uid,function(data) {
		dwr.util.setValue("level1", data,{ escapeHtml:false });
	});
}
//加载二级选项
function SetContantSecond(fid,open)
{
	var uid = parseInt(document.getElementById("proxyID").value);
	if(document.getElementById("level2-"+fid).innerHTML!=''){
		dwr.util.setValue("level2-"+fid,'',{ escapeHtml:false });
		document.getElementById("level2-"+fid).style.display="none";
		return;
	}
	AgentAuthorityDwr.SetContantSecond(parseInt(fid),uid,function(data) {
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
	var uid = parseInt(document.getElementById("proxyID").value);
	//能级管理选项显示
	AgentAuthorityDwr.SetContantThird(parseInt(fid),uid,function(data) {
		if(data!='')
		{
			dwr.util.setValue("level3-"+fid, data,{ escapeHtml:false });
		}
	});
}
//加载四级选项
function SetContantFourth(fid,step)
{
	var uid = parseInt(document.getElementById("proxyID").value);
	//能级管理选项显示
	AgentAuthorityDwr.SetContantFourth(parseInt(fid),uid,function(data) {
		if(data!='')
		{
			dwr.util.setValue("level4-"+fid, data,{ escapeHtml:false });
		}
	});
}
//加载四级选项
function SetContantFifth(fid,step)
{
	var uid = parseInt(document.getElementById("proxyID").value);
	//能级管理选项显示
	AgentAuthorityDwr.SetContantFifth(parseInt(fid),uid,function(data) {
		if(data!='')
		{
			dwr.util.setValue("level5-"+fid, data,{ escapeHtml:false });
		}
	});
}
function changeSelectState(id,cdname)
{
	var uid = parseInt(document.getElementById("proxyID").value);
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
		var uid = parseInt(document.getElementById("proxyID").value);
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
		var uid = parseInt(document.getElementById("proxyID").value);
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
	if (CheckNull(document.forms['form2'].nickName, '昵称不能为空!')){
		return false;
	}
	form2.submit();

}
</script>
</head>
<body>
	<Div class="title"><span>
	  <label> <input name="Submit3" type="button" class="input" value="查看账户" onclick="window.location.href='<%=request.getContextPath()%>/member/gameAgentList.do?action=gameAgentList'" /> </label>
	</span>详情</Div>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
	   <form id="form2" name="form2" method="post" action="<%=request.getContextPath()%>/member/gameAgentList.do?action=updateAgent">
        <tr>
          <td width="120" class="menutop">项目</td>
          <td class="menutop">内容</td>
        </tr>
        
        <tr>
          <td class="tdright_new">用户名：</td>
          <td class="tdlefts"><input type="hidden" id="proxyID" name="proxyID" class="input_width2" value="${user.proxyID}" />
		  <input name="proxyAccounts" type="hidden" class="input_width2" value="${user.proxyAccounts}" />
		  ${user.proxyAccounts}</td>
        </tr>
<%--        <tr>
          <td class="tdright_new">游戏ID：</td>
          <td class="tdlefts">&nbsp;${dto2.gameID}</td>
        </tr>--%>
        <tr>
          <td class="tdright_new">用户昵称：</td>
          <td class="tdlefts"><input name="nickName" id="nickName" type="text" class="input_width3" value="${user.nickName}" /></td>
        </tr>
		    <tr>
          <td class="tdright_new">用户密码：</td>
          <td class="tdlefts"><input name="password" id="password" type="password" class="input_width3" value="" />&nbsp;留空不修改</td>
        </tr>
		    <tr>
		      <td class="tdright_new">确认密码：</td>
		      <td class="tdlefts"><input name="password2" id="password2" type="password" class="input_width3" value="" />
	          留空不修改</td>
         </tr>
		    <tr>
		      <td class="tdright_new">钱庄密码：</td>
		      <td class="tdlefts"><input name="bankPass" id="bankPass" type="password" class="input_width3" value="" />
	          留空不修改</td>
         </tr>
		    <tr>
		      <td class="tdright_new">钱庄确认密码：</td>
		      <td class="tdlefts"><input name="bankPass2" id="bankPass2" type="password" class="input_width3" value="" />
	          留空不修改</td>
         </tr>
		
		<tr>
		  <td class="tdright_new">银子：</td>
		  <td class="tdlefts">${user.gold}</td>
	    </tr>
		
		 <tr>
          <td class="tdright_new">扣税提成：</td>
          <td class="tdlefts"><label>
              <input type="text" name="taxRate" id="taxRate" value="${user.taxRate}" />&nbsp;&nbsp;上限${sxtaxRate}
          </label></td>
        </tr>
        <tr>
          <td class="tdright_new">股份比例：</td>
          <td class="tdlefts">
            <label>
          <input type="text" name="winner" id="winner" value="${user.winner}" />&nbsp;&nbsp;上限${sxwinner}          </label></td>
        </tr>
        <tr>
          <td class="tdright_new">真人佣金：</td>
          <td class="tdlefts">
            <label>
          <input type="text" name="brokerage" id="brokerage" value="${user.brokerage}" />&nbsp;&nbsp;上限${sxbrokerage}          </label></td>
        </tr>
	<tr>
	<td class="tdright_new"></td>
	<td class="tdlefts" colspan="3">      
	<input name="back" type="button" class="input" id="back" onClick="check()" value="确定修改" /></td>
	</tr>
	</form>
      </table>

</body>
</html>
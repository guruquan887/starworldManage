<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
<%
String [] ids = {};
String ss = "";
if(request.getParameterValues("checkbox")!=null){
   ids = request.getParameterValues("checkbox");
   for(int i=0;i<ids.length;i++){
	   ss += ids[i]+",";
	}
   System.out.println("造型成字符串的ss:"+ss);
}
String msg="";
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />
</head>
<script type='text/javascript' src='<%=request.getContextPath()%>/js/jquery.js'></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-latest.pack.js"></script>
<script language="javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
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
    var MinPlayDraw = document.getElementById("MinPlayDraw").value;
	var MaxPlayDraw = document.getElementById("MaxPlayDraw").value;
	var MinTakeScore = document.getElementById("MinTakeScore").value;
	var MaxTakeScore = document.getElementById("MaxTakeScore").value;
	var MinReposeTime = document.getElementById("MinReposeTime").value;
	var MaxReposeTime = document.getElementById("MaxReposeTime").value;
	//var ServiceGender = document.getElementById("ServiceGender").value;
	//var ServiceTime = document.getElementById("ServiceTime").value;
	if(MinPlayDraw==""){
	   alert("请输入最少局数!");
	   return false;
	}
	if(MaxPlayDraw==""){
	   alert("请输入最大局数!");
	   return false;
	}
	if(MinTakeScore==""){
	   alert("请输入最少分数!");
	   return false;
	}
	if(MaxTakeScore==""){
	   alert("请输入最大分数!");
	   return false;
	}
	if(MinReposeTime==""){
	   alert("请输入最小游戏时间!");
	   return false;
	}
	if(MaxReposeTime==""){
	   alert("请输入最大游戏时间!");
	   return false;
	}
	/*if(ServiceGender==""){
	   alert("请选择服务类型!");
	   return false;
	}
	if(ServiceTime==""){
	   alert("请选择服务时间!");
	   return false;
	}*/
	return true;
 
 }
</script>
<body>
	<div class="title"><span>
	  <label>
	  <input name="Submit3" type="button" class="input" value="查看用户" onclick="window.location.href='<%=request.getContextPath()%>/member/gameuserList.do?action=gameUserList&type=0'" />
	  </label>
	</span>用户—设置机器人</div>
    <form id="form1" name="form1" method="post" onsubmit="return check()" action="<%=request.getContextPath()%>/member/gameuserList.do?action=setupJuser" >
      <table width="100%" border="0" cellpadding="0" cellspacing="0" class="table_margin">
        <tr>
          <td width="120" class="menutop">项目</td>
          <td class="menutop">内容</td>
        </tr>
        <tr>
          <td class="menutop">请选择游戏类型：</td>
          <td class="tdlefts"><select name="gameTypeID" id="gameTypeID" class="input_width1">
		  <option value="1">银子类型</option>
		  <option value="2">积分类型</option>
		  </select></td>
        </tr>
        <tr>
          <td class="menutop">请选择游戏房间：</td>
          <td class="tdlefts"><select name="serverID" id="serverID" class="input_width1">
		  <c:forEach var="roomlist" items="${roomlist}">
		  <option value="${roomlist.serverID}">${roomlist.serverRoom}</option>
		  </c:forEach>
		  </select></td>
        </tr>
        <tr>
          <td class="menutop">最少局数：</td>
          <td class="tdlefts"><input name="ss" id="ss" type="hidden" value="<%=ss%>" /><input name="MinPlayDraw" id="MinPlayDraw" onkeyup="isNumber(this)" type="text" maxlength="5" value="" /></td>
        </tr>
		<tr>
          <td class="menutop">最大局数：</td>
          <td class="tdlefts"><input name="MaxPlayDraw" id="MaxPlayDraw" onkeyup="isNumber(this)" type="text" value="" maxlength="5" /></td>
        </tr>
		<tr>
          <td class="menutop">最少分数：</td>
          <td class="tdlefts"><input name="MinTakeScore" id="MinTakeScore" onkeyup="isNumber(this)" type="text" value="" maxlength="9" /></td>
        </tr>
		<tr>
          <td class="menutop">最高分数：</td>
          <td class="tdlefts"><input name="MaxTakeScore" id="MaxTakeScore" onkeyup="isNumber(this)" type="text" value="" maxlength="9" /></td>
        </tr>
		<tr>
          <td class="menutop">最小游戏时间：</td>
          <td class="tdlefts"><input name="MinReposeTime" id="MinReposeTime" onkeyup="isNumber(this)" type="text" value="" maxlength="5" /></td>
        </tr>
		<tr>
          <td class="menutop">最大游戏时间：</td>
          <td class="tdlefts"><input name="MaxReposeTime" id="MaxReposeTime" onkeyup="isNumber(this)" type="text" value="" maxlength="5" /></td>
        </tr>
		<tr>
          <td class="menutop">服务类型：</td>
          <td class="tdlefts"><input name="ServiceGender" checked="checked" type="checkbox" value="1" />
            相互模拟
          <input name="ServiceGender" type="checkbox" value="2" />被动陪打<input name="ServiceGender" type="checkbox" value="4" />主动陪打&nbsp;&nbsp;&nbsp;(不勾选为挂线机器人【机器人直接挂在房间，不进行任何操作。】)</td></tr>
		  <tr>
                <td class="menutop">服务时间：<br />（全选<input type="checkbox"  onclick="SelectAllTable(this.checked,'serviceTime');"/>）</td>
                <td> 
                    <table border="0" style="padding:5px 5px 5px 0;" cellpadding="0" cellspacing="0" id="serviceTime">
                    <tr>
                        <td><input name="ServiceTime" id="chkServiceTime1" type="checkbox" value="1" /><label for="chkServiceTime1">0:00-1:00</label></td>
                        <td><input name="ServiceTime" id="chkServiceTime2" type="checkbox" value="2" /><label for="chkServiceTime2">1:00-2:00</label></td>
                        <td><input name="ServiceTime" id="chkServiceTime3" type="checkbox" value="4" /><label for="chkServiceTime3">2:00-3:00</label></td>
                        <td><input name="ServiceTime" id="chkServiceTime4" type="checkbox" value="8" /><label for="chkServiceTime4">3:00-4:00</label></td>
                        <td><input name="ServiceTime" id="chkServiceTime5" type="checkbox" value="16" /><label for="chkServiceTime5">4:00-5:00</label></td>
                        <td><input name="ServiceTime" id="chkServiceTime6" type="checkbox" value="32" /><label for="chkServiceTime6">5:00-6:00</label></td>
                    </tr>
                    <tr>
                        <td><input name="ServiceTime" id="chkServiceTime7" type="checkbox" value="64" /><label for="chkServiceTime7">6:00-7:00</label></td>
                        <td><input name="ServiceTime" id="chkServiceTime8" type="checkbox" value="128" /><label for="chkServiceTime8">7:00-8:00</label></td>
                        <td><input name="ServiceTime" id="chkServiceTime9" type="checkbox" value="256" /><label for="chkServiceTime9">8:00-9:00</label></td>
                        <td><input name="ServiceTime" id="chkServiceTime10" type="checkbox" value="512" /><label for="chkServiceTime10">9:00-10:00</label></td>
                        <td><input name="ServiceTime" id="chkServiceTime11" type="checkbox" value="1024" /><label for="chkServiceTime11">10:00-11:00</label></td>
                        <td><input name="ServiceTime" id="chkServiceTime12" type="checkbox" value="2048" /><label for="chkServiceTime12">11:00-12:00</label></td>
                    </tr>
                    <tr>
                        <td><input name="ServiceTime" id="chkServiceTime13" type="checkbox" value="4096" /><label for="chkServiceTime13">12:00-13:00</label></td>
                        <td><input name="ServiceTime" id="chkServiceTime14" type="checkbox" value="8192" /><label for="chkServiceTime14">13:00-14:00</label></td>
                        <td><input name="ServiceTime" id="chkServiceTime15" type="checkbox" value="16384" /><label for="chkServiceTime15">14:00-15:00</label></td>
                        <td><input name="ServiceTime" id="chkServiceTime16" type="checkbox" value="32768" /><label for="chkServiceTime16">15:00-16:00</label></td>
                        <td><input name="ServiceTime" id="chkServiceTime17" type="checkbox" value="65536" /><label for="chkServiceTime17">16:00-17:00</label></td>
                        <td><input name="ServiceTime" id="chkServiceTime18" type="checkbox" value="131072" /><label for="chkServiceTime18">17:00-18:00</label></td>
                    </tr>
                    <tr>
                        <td><input name="ServiceTime" id="chkServiceTime19" type="checkbox" value="262144" /><label for="chkServiceTime19">18:00-19:00</label></td>
                        <td><input name="ServiceTime" id="chkServiceTime20" type="checkbox" value="524288" /><label for="chkServiceTime20">19:00-20:00</label></td>
                        <td><input name="ServiceTime" id="chkServiceTime21" type="checkbox" value="1048576" /><label for="chkServiceTime21">20:00-21:00</label></td>
                        <td><input name="ServiceTime" id="chkServiceTime22" type="checkbox" value="2097152" /><label for="chkServiceTime22">21:00-22:00</label></td>
                        <td><input name="ServiceTime" id="chkServiceTime23" type="checkbox" value="4194304" /><label for="chkServiceTime23">22:00-23:00</label></td>
                        <td><input name="ServiceTime" id="chkServiceTime24" type="checkbox" value="8388608" /><label for="chkServiceTime24">23:00-24:00</label></td>
                    </tr>
                    </table>                </td>
            </tr>
		  <tr>
          <td class="menutop">备用信息：</td>
          <td class="tdlefts"><input name="AndroidNote" id="AndroidNote" type="text" style="width:300px;" />  </td>
        </tr>
		<tr>
          <td class="menutop">禁用状态：</td>
          <td class="tdlefts"><input name="in_Nullity" type="radio" value="0" checked="checked" />启用<input name="in_Nullity" type="radio" value="1" />冻结</td>
        </tr>
        
        <tr>
          <td class="tdright_new">&nbsp;</td>
          <td class="tdright"><label>
            <input name="Submit" type="submit" class="input" value="确定" />
            <input name="Submit2" type="reset" class="input" value="重置" />
          </label></td>
        </tr>
      </table>
</form>
</body>
</html>
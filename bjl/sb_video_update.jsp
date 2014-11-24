<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %> 
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%
String msg="";
if(request.getAttribute("msg")!=null){
msg=request.getAttribute("msg").toString();
}

String fileName="";
String videolength="";
String videoName="";
String hgName="";
String hg2Name="";
String result1="";
String result2="";
String result3="";
int resultTime = 0;
int startTime = 0;
int endTime = 0;

if(request.getParameter("fileName")!=null){
	fileName=request.getParameter("fileName");
}
if(request.getParameter("videolength")!=null){
	videolength=request.getParameter("videolength");
}
if(request.getParameter("hgName")!=null){
	hgName=request.getParameter("hgName");
}
if(request.getParameter("videoName")!=null){
	videoName=request.getParameter("videoName");
}
if(request.getParameter("hg2Name")!=null){
	hg2Name=request.getParameter("hg2Name");
}
if(request.getParameter("result1")!=null){
	result1=request.getParameter("result1");
}
if(request.getParameter("result2")!=null){
	result2=request.getParameter("result2");
}
if(request.getParameter("result3")!=null){
	result3=request.getParameter("result3");
}
if(request.getParameter("resultTime")!=null){
	resultTime=Integer.parseInt(request.getParameter("resultTime"));
}
if(request.getParameter("startTime")!=null){
	startTime=Integer.parseInt(request.getParameter("startTime"));
}
if(request.getParameter("endTime")!=null){
	endTime=Integer.parseInt(request.getParameter("endTime"));
}


 %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改视频</title>
<link href="../css/main.css" rel="stylesheet" type="text/css" />

</head>
<script language="JavaScript" type="text/javascript">

       
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
function addOne(){
	if (CheckNull(document.forms['form1'].videoName, '视频名称不能为空!')){
		return false;
	}
	//if (CheckNull(document.forms['form1'].fileName, '文件名称不能为空!')){
	//	return false;
	//}
	form1.submit();

}

</script>
<body>
<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/bjl/sbvideo.do?action=updateVideo">
  <p>&nbsp;</p>
  <p></p>
  <table width="800" border="0" align="center" cellpadding="2" cellspacing="1" class="border">
    <tr>
      <td height="22" colspan="2" class="title">视频管理：修改视频</td>
    </tr>
    <tr class="tdbg">
      <td align="right">视频名称：</td>
      <td>
       <input name="videoName" type="text" size="40" value="${sbvideoDTO.videoName}"/>
	   <input type="hidden" name="videoID" id="videoID" value="${sbvideoDTO.videoID}" /></td>
    </tr>
    <tr class="tdbg">
      <td align="right">文件名称：</td>
      <td>
	  <input type="text" name="fileName" id="fileName" value="${sbvideoDTO.fileName}"></td>
    </tr>
    <tr>
      <td class="tdbg" align="right">荷官名称1：</td>
      <td class="tdbg"><input name="hgName" type="text" size="25" value="${sbvideoDTO.hgName}"/>在添加换人视频时荷官名称填离开荷官的名称</td>
    </tr>
    <tr>
      <td class="tdbg" align="right">荷官名称2：</td>
      <td class="tdbg"><input name="hg2Name" type="text" size="25" value="${sbvideoDTO.hg2Name}"/></td>
    </tr>
    <tr class="tdbg">
      <td align="right">视频时间：</td>
      <td><input name="videolength" type="text" size="10" value="${sbvideoDTO.videolength}"/>
        添加视频录入该视频段的时间（秒）</td>
    </tr>
	 <tr class="tdbg">
      <td align="right">结果时间：</td>
      <td>
        <input name="resultTime" type="text" size="8" value="${sbvideoDTO.resultTime}"/>
        秒</td>
    </tr>
	 <tr class="tdbg">
      <td align="right">开始投注时间：</td>
      <td>
        <input name="startTime" type="text" size="8" value="${sbvideoDTO.startTime}"/>
        秒</td>
    </tr>
	 <tr class="tdbg">
      <td align="right">结束投注时间：</td>
      <td>
        <input name="endTime" type="text" size="8" value="${sbvideoDTO.endTime}"/>
        秒</td>
    </tr>
	 <tr class="tdbg">
      <td align="right">结果1：</td>
      <td><input name="result1" type="text" size="8" value="${sbvideoDTO.result1}"/></td>
    </tr> 
	<tr class="tdbg">
      <td align="right">结果2：</td>
      <td><input name="result2" type="text" size="8" value="${sbvideoDTO.result2}"/></td>
    </tr> 
	<tr class="tdbg">
      <td align="right">结果3：</td>
      <td><input name="result3" type="text" size="8" value="${sbvideoDTO.result3}"/></td>
    </tr>
  </table>
  <table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr align="center">
      <td><input type="button" name="button1" value="保存" onClick="addOne()"/> 　
        <input type="reset" name="button2" value="重置" />
&nbsp;</td>
    </tr>
  </table>
</form>
</body>
<script type="text/javascript">
var msg="<%=msg %>";
if(msg!='')alert(msg);
function setphotourl1(url){
			document.all.fileName.value=url;
		}	
</script>
</html>

<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%
String msg="";
if(request.getAttribute("message")!=null)
	msg=request.getAttribute("message").toString();
String videoUrl="";
String newFileName ="";
int type = 0;
if(request.getAttribute("videoUrl")!=null&& !"".equals(request.getAttribute("videoUrl") )){
	videoUrl=(String)request.getAttribute("videoUrl");
    newFileName = videoUrl.substring(0,17);
}
if(request.getParameter("type")!=null){
	type = Integer.parseInt(request.getParameter("type"));
}
System.out.println(newFileName+"<<<<<<newFileName");
%>
<html>
<head>
<link href="../style/main.css" rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script language="javascript">
var mymsg="<%=msg%>";


function checkValidInt(text)
{
	var len = text.length;
	var valid = true;
	var dot = false;
	var reg = "0123456789";
	if(len < 1 || len > 9)
		return false;
	for(var i = 0; i < len; i++)
	{
		var ch = text.charAt(i);
		var j;
		for(j = 0; j < reg.length; j++)
		{
			if(ch == reg.charAt(j))
				break;
		}
		if(j == reg.length)
			return false;				
	}
	return true;
}
function upfile(){
	if(document.all.photofile!=""){
		document.all.uploadForm.submit();
		//uploadBegin();
	}
}
function uploadBegin(){
theFeats = "height=360,width=370,location=no,menubar=no,resizable=no,scrollbars=no,status=no,toolbar=no";
	strAppVersion = navigator.appVersion;
    if (strAppVersion.indexOf('MSIE') != -1 && strAppVersion.substr(strAppVersion.indexOf('MSIE')+5,1) > 4)
	{
		winstyle = "dialogWidth=312px; dialogHeight:220px; center:yes";
		window.showModelessDialog("<%=request.getContextPath() %>/jsp/upload/progressbar.jsp",window,winstyle) ;
	}
	return true;
}
</script>
</head>
<body>
<form action="<%=request.getContextPath() %>/bjl/uploadvideo.do?no=1&type=<%=type %>" method="post" name="uploadForm" id="uploadForm" enctype="multipart/form-data">
<table width="99%" border="0" cellpadding="0" cellspacing="0" >
<%if(videoUrl.length()==0){ %>
<tr>
<td>
<input type="file" name="fileName1" id="fileName1" >
<input name="button" type="button" class="button" onClick="upfile();" value=" 上传服务器 ">
<input type="hidden" name="articleid" id="articleid" value="">
</td>
</tr>
<%}else{ %>
<script language="javascript">
parent.setphotourl1("<%=newFileName%>");
</script>
<input type="text" name="bjl_fileName" id="bjl_fileName" value="<%=newFileName %>" />
<%} %>
</table>
</form>
<script language="javascript">

if(mymsg!=""){
	alert(mymsg);
	
}
</script>
</body>
</html>
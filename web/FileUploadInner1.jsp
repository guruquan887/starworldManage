<%@ page language="java" contentType="text/html;charset=GBK"%>
<%
String msg="";
if(request.getAttribute("message")!=null)
	msg=request.getAttribute("message").toString();
String photourl="";
if(request.getAttribute("photourl")!=null){
	photourl=(String)request.getAttribute("photourl");
}
if(request.getParameter("purl")!=null){
	String tmp=request.getParameter("purl");
	if(tmp.length()>0)
		photourl=tmp;
}
System.out.println(photourl);
%>
<html>
<head>
<link rel="stylesheet" href="/css/appform.css">
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
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
	//alert(document.all.photofile+","+document.all.photofile.value);
	if(document.all.photofile!=""){
		
		document.all.uploadForm.submit();
		uploadBegin();
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
<%if(photourl.length()==0){ %>

<table border="0" width="100%" >
<form action="<%=request.getContextPath() %>/shoping/uploadphoto.do?no=1" method="post"
				enctype="multipart/form-data" name="uploadForm" id="uploadForm">
<tr>
<td align="left"><input type="file" name="photofile" id="photofile" style="width:300px">
<input name="button" type="button" class="input" onClick="upfile();" value=" 上 传 ">

</td>
</tr>
<tr style="display:none">
<td>
<input type="hidden" name="articleid" id="articleid" value="">
</td>
</tr>
</table>
<%}else{ %>
<script language="javascript">
parent.setphotourl1("<%=photourl%>");
</script>
<a href="<%=request.getContextPath()%>/incoming/<%=photourl %>" target="_blank"><img src="<%=request.getContextPath()%>/incoming/<%=photourl %>" width="116" height="76" border="0"></a>
<a href="../web/FileUploadInner1.jsp"><strong>重新上传</strong></a>
<%} %>
<script language="javascript">

if(mymsg!=""){
	alert(mymsg);
	
}
</script>
</body>
</html>
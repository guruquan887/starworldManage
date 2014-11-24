<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %> 
<jsp:include page="../yanzheng.jsp" flush="false"></jsp:include>
<%
String msg="";
if(request.getAttribute("msg")!=null){
msg=request.getAttribute("msg").toString();
}

String bjl_fileName="";
String bjl_videolength="";
String bjl_videoName="";
String bjl_hgName="";
String bjl_hg2Name="";
String bjl_result="";

int bjl_result1Time = 0;
int bjl_result2Time = 0;
int bjl_result3Time = 0;
int bjl_result4Time = 0;
int bjl_result5Time = 0;
int bjl_result6Time = 0;



if(request.getParameter("bjl_fileName")!=null){
	bjl_fileName=request.getParameter("bjl_fileName");
}
if(request.getParameter("bjl_videolength")!=null){
	bjl_videolength=request.getParameter("bjl_videolength");
}
if(request.getParameter("bjl_hgName")!=null){
	bjl_hgName=request.getParameter("bjl_hgName");
}
if(request.getParameter("bjl_videoName")!=null){
	bjl_videoName=request.getParameter("bjl_videoName");
}
if(request.getParameter("bjl_hg2Name")!=null){
	bjl_hg2Name=request.getParameter("bjl_hg2Name");
}
if(request.getParameter("bjl_result")!=null){
	bjl_result=request.getParameter("bjl_result");
}

if(request.getParameter("bjl_result1Time")!=null){
	bjl_result1Time=Integer.parseInt(request.getParameter("bjl_result1Time"));
}
if(request.getParameter("bjl_result2Time")!=null){
	bjl_result2Time=Integer.parseInt(request.getParameter("bjl_result2Time"));
}
if(request.getParameter("bjl_result3Time")!=null){
	bjl_result3Time=Integer.parseInt(request.getParameter("bjl_result3Time"));
}
if(request.getParameter("bjl_result4Time")!=null){
	bjl_result4Time=Integer.parseInt(request.getParameter("bjl_result4Time"));
}
if(request.getParameter("bjl_result5Time")!=null){
	bjl_result5Time=Integer.parseInt(request.getParameter("bjl_result5Time"));
}
if(request.getParameter("bjl_result6Time")!=null){
	bjl_result6Time=Integer.parseInt(request.getParameter("bjl_result6Time"));
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
function updateOne(){
	if (CheckNull(document.forms['form1'].bjl_videoName, '视频名称不能为空!')){
		return false;
	}
	if (CheckNull(document.forms['form1'].bjl_fileName, '文件名称不能为空!')){
		return false;
	}
	if(CheckNull(document.forms['form1'].bjl_hgName, '荷官名称不能为空!')){
	    return false;
	}
	if(CheckNull(document.forms['form1'].bjl_videolength, '视频时间不能为空!')){
	    return false;
	}
	form1.submit();
}

</script>
<body>
<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/bjl/video.do?action=updateVideo">
  <p>&nbsp;</p>
  <p></p>
  <table width="800" border="0" align="center" cellpadding="2" cellspacing="1" class="table_margin">
    <tr>
      <td height="22" colspan="2" class="title">视频管理：修改视频</td>
    </tr>
    <tr >
      <td class="menutop" align="right">视频名称：</td>
      <td class="tdlefts">
       <input name="bjl_videoName" type="text" size="40" value="${videoDTO.videoName}"/>
		<input type="hidden" name="videoID" id="videoID" value="${videoDTO.videoID}" /></td>
    </tr>
    <tr>
      <td class="menutop" align="right">文件名称：</td>
      <td class="tdlefts"><input type="text" name="bjl_fileName" id="bjl_fileName" value="${videoDTO.fileName}" size="25" /><iframe name="uploadframe" id="uploadframe" width="90%" height="60" src="<%=request.getContextPath()%>/bjl/FileUploadInner.jsp?type=1" frameborder=0 scrolling=no> </iframe></td>
    </tr>
	<tr>
      <td class="menutop" align="right">房间类型：</td>
      <td class="tdlefts"> <select name="roomType" size="1" class="Select" id="roomType">
        <c:forEach var="roomList" items="${roomList}">
          <option value="${roomList.roomID}" <c:if test="${videoDTO.roomType==roomList.roomID}" var="true">selected="selected"</c:if>>${roomList.roomName}</option>
        </c:forEach>
      </select>
<%--	  
	  <select name="roomType" id="roomType">
          <option value="1">百家乐房间1</option>
		  <option value="2">百家乐房间2</option>
		  <option value="3">百家乐房间3</option>
        </select>--%></td>
    </tr>
    <tr>
      <td class="menutop" align="right">视频类型：</td>
      <td class="tdlefts"> <select name="bjl_videoType" id="bjl_videoType">
          <option value="0" <c:if test="${videoDTO.videoType ==0}" var="true">selected="selected"</c:if> >等待下注</option>
          <option value="1" <c:if test="${videoDTO.videoType ==1}" var="true">selected="selected"</c:if> >开牌</option>
          <option value="2" <c:if test="${videoDTO.videoType ==2}" var="true">selected="selected"</c:if> >换人</option>
          <option value="3" <c:if test="${videoDTO.videoType ==3}" var="true">selected="selected"</c:if> >黄牌或红牌、洗牌</option>
        </select></td>
    </tr>
    
    <tr>
      <td class="menutop" align="right">荷官名称1：</td>
      <td class="tdlefts"><input name="bjl_hgName" type="text" size="25" value="${videoDTO.hgName}"/>
      在添加换人视频时荷官名称填离开荷官的名称</td>
    </tr>
    <tr>
      <td class="menutop" align="right">荷官名称2：</td>
      <td class="tdlefts"><input name="bjl_hg2Name" type="text" size="25" value="${videoDTO.hg2Name}"/></td>
    </tr>
    <tr>
      <td class="menutop" align="right">开牌结果：</td>
      <td class="tdlefts"><input name="bjl_result" type="text" size="40" value="${videoDTO.result}"/>
      在添加视频时先查看开牌结果，录入进来</td>
    </tr>
	    <tr>
      <td class="menutop" align="right">视频时间：</td>
      <td class="tdlefts"><input name="bjl_videolength" type="text" size="10" value="${videoDTO.videolength}"/>
        添加视频录入该视频段的时间（秒）</td>
    </tr>
	 <tr>
      <td class="menutop" align="right">输赢结果：</td>
      <td class="tdlefts"><select name="select_resultOfWinLost" size="1" id="resultOfWinLost"> 
	  	<option value="DD" <c:if test="${videoDTO.resultOfWinLost =='DD'}" var="true">selected="selected"</c:if> >等待</option>
		<option value="ZY" <c:if test="${videoDTO.resultOfWinLost =='ZY'}" var="true">selected="selected"</c:if> >庄赢</option>
		<option value="XY" <c:if test="${videoDTO.resultOfWinLost =='XY'}" var="true">selected="selected"</c:if> >闲赢</option>
		<option value="HJ" <c:if test="${videoDTO.resultOfWinLost =='HJ'}" var="true">selected="selected"</c:if> >和局</option>
        </select></td>
    </tr>
	 <tr>
      <td class="menutop" align="right">开牌1：</td>
      <td class="tdlefts"><select name="selectresult1Type" id="selectresult1Type">
          <option value="0" <c:if test="${videoDTO.selectresult1Type ==0}" var="true">selected="selected"</c:if>>方块</option>
          <option value="1" <c:if test="${videoDTO.selectresult1Type ==1}" var="true">selected="selected"</c:if>>梅花</option>
          <option value="2" <c:if test="${videoDTO.selectresult1Type ==2}" var="true">selected="selected"</c:if>>红桃</option>
          <option value="3" <c:if test="${videoDTO.selectresult1Type ==3}" var="true">selected="selected"</c:if>>黑桃</option>
        </select>
        <select name="selectresult1" id="selectresult1">
          <option value="A" <c:if test="${videoDTO.selectresult1Points =='A'}" var="true">selected="selected"</c:if>>A</option>
          <option value="B" <c:if test="${videoDTO.selectresult1Points =='B'}" var="true">selected="selected"</c:if>>2</option>
          <option value="C" <c:if test="${videoDTO.selectresult1Points =='C'}" var="true">selected="selected"</c:if>>3</option>
          <option value="D" <c:if test="${videoDTO.selectresult1Points =='D'}" var="true">selected="selected"</c:if>>4</option>
          <option value="E" <c:if test="${videoDTO.selectresult1Points =='E'}" var="true">selected="selected"</c:if>>5</option>
          <option value="F" <c:if test="${videoDTO.selectresult1Points =='F'}" var="true">selected="selected"</c:if>>6</option>
          <option value="G" <c:if test="${videoDTO.selectresult1Points =='G'}" var="true">selected="selected"</c:if>>7</option>
          <option value="H" <c:if test="${videoDTO.selectresult1Points =='H'}" var="true">selected="selected"</c:if>>8</option>
          <option value="I" <c:if test="${videoDTO.selectresult1Points =='I'}" var="true">selected="selected"</c:if>>9</option>
          <option value="J" <c:if test="${videoDTO.selectresult1Points =='J'}" var="true">selected="selected"</c:if>>10</option>
          <option value="K" <c:if test="${videoDTO.selectresult1Points =='K'}" var="true">selected="selected"</c:if>>J</option>
          <option value="L" <c:if test="${videoDTO.selectresult1Points =='L'}" var="true">selected="selected"</c:if>>Q</option>
          <option value="M" <c:if test="${videoDTO.selectresult1Points =='M'}" var="true">selected="selected"</c:if>>K</option>
        </select>
        <select name="selectresult1A">
		  <option value="X" <c:if test="${videoDTO.selectresult1ZX =='X'}" var="true">selected="selected"</c:if>>闲</option>
          <option value="Z" <c:if test="${videoDTO.selectresult1ZX =='Z'}" var="true">selected="selected"</c:if>>庄</option>
        </select>
        <%--<input name="bjl_result1Time" type="text" size="8" value="<%=bjl_result1Time %>"/>--%>
		<input name="bjl_result1Time" type="text" size="8" value="${videoDTO.result1Time}"/>
        秒</td>
    </tr>
	 <tr>
      <td class="menutop" align="right">开牌2：</td>
      <td class="tdlefts"><select name="selectresult2Type" id="selectresult2Type">
          <option value="0" <c:if test="${videoDTO.selectresult2Type ==0}" var="true">selected="selected"</c:if>>方块</option>
          <option value="1" <c:if test="${videoDTO.selectresult2Type ==1}" var="true">selected="selected"</c:if>>梅花</option>
          <option value="2" <c:if test="${videoDTO.selectresult2Type ==2}" var="true">selected="selected"</c:if>>红桃</option>
          <option value="3" <c:if test="${videoDTO.selectresult2Type ==3}" var="true">selected="selected"</c:if>>黑桃</option>
        </select>
        <select name="selectresult2" id="selectresult2">
          <option value="A" <c:if test="${videoDTO.selectresult2Points =='A'}" var="true">selected="selected"</c:if>>A</option>
          <option value="B" <c:if test="${videoDTO.selectresult2Points =='B'}" var="true">selected="selected"</c:if>>2</option>
          <option value="C" <c:if test="${videoDTO.selectresult2Points =='C'}" var="true">selected="selected"</c:if>>3</option>
          <option value="D" <c:if test="${videoDTO.selectresult2Points =='D'}" var="true">selected="selected"</c:if>>4</option>
          <option value="E" <c:if test="${videoDTO.selectresult2Points =='E'}" var="true">selected="selected"</c:if>>5</option>
          <option value="F" <c:if test="${videoDTO.selectresult2Points =='F'}" var="true">selected="selected"</c:if>>6</option>
          <option value="G" <c:if test="${videoDTO.selectresult2Points =='G'}" var="true">selected="selected"</c:if>>7</option>
          <option value="H" <c:if test="${videoDTO.selectresult2Points =='H'}" var="true">selected="selected"</c:if>>8</option>
          <option value="I" <c:if test="${videoDTO.selectresult2Points =='I'}" var="true">selected="selected"</c:if>>9</option>
          <option value="J" <c:if test="${videoDTO.selectresult2Points =='J'}" var="true">selected="selected"</c:if>>10</option>
          <option value="K" <c:if test="${videoDTO.selectresult2Points =='K'}" var="true">selected="selected"</c:if>>J</option>
          <option value="L" <c:if test="${videoDTO.selectresult2Points =='L'}" var="true">selected="selected"</c:if>>Q</option>
          <option value="M" <c:if test="${videoDTO.selectresult2Points =='M'}" var="true">selected="selected"</c:if>>K</option>
        </select>
        <select name="selectresult2A">
          <option value="X" <c:if test="${videoDTO.selectresult2ZX =='X'}" var="true">selected="selected"</c:if>>闲</option>
          <option value="Z" <c:if test="${videoDTO.selectresult2ZX =='Z'}" var="true">selected="selected"</c:if>>庄</option>
        </select>
        <%--<input name="bjl_result2Time" type="text" size="8" value="<%=bjl_result2Time %>"/>--%>
		<input name="bjl_result2Time" type="text" size="8" value="${videoDTO.result2Time}"/>
        秒</td>
    </tr> 
	<tr>
      <td class="menutop" align="right">开牌3：</td>
      <td class="tdlefts"> <select name="selectresult3Type" id="selectresult3Type">
          <option value="0" <c:if test="${videoDTO.selectresult3Type ==0}" var="true">selected="selected"</c:if>>方块</option>
          <option value="1" <c:if test="${videoDTO.selectresult3Type ==1}" var="true">selected="selected"</c:if>>梅花</option>
          <option value="2" <c:if test="${videoDTO.selectresult3Type ==2}" var="true">selected="selected"</c:if>>红桃</option>
          <option value="3" <c:if test="${videoDTO.selectresult3Type ==3}" var="true">selected="selected"</c:if>>黑桃</option>
        </select>
        <select name="selectresult3" id="selectresult3">
          <option value="A" <c:if test="${videoDTO.selectresult3Points =='A'}" var="true">selected="selected"</c:if>>A</option>
          <option value="B" <c:if test="${videoDTO.selectresult3Points =='B'}" var="true">selected="selected"</c:if>>2</option>
          <option value="C" <c:if test="${videoDTO.selectresult3Points =='C'}" var="true">selected="selected"</c:if>>3</option>
          <option value="D" <c:if test="${videoDTO.selectresult3Points =='D'}" var="true">selected="selected"</c:if>>4</option>
          <option value="E" <c:if test="${videoDTO.selectresult3Points =='E'}" var="true">selected="selected"</c:if>>5</option>
          <option value="F" <c:if test="${videoDTO.selectresult3Points =='F'}" var="true">selected="selected"</c:if>>6</option>
          <option value="G" <c:if test="${videoDTO.selectresult3Points =='G'}" var="true">selected="selected"</c:if>>7</option>
          <option value="H" <c:if test="${videoDTO.selectresult3Points =='H'}" var="true">selected="selected"</c:if>>8</option>
          <option value="I" <c:if test="${videoDTO.selectresult3Points =='I'}" var="true">selected="selected"</c:if>>9</option>
          <option value="J" <c:if test="${videoDTO.selectresult3Points =='J'}" var="true">selected="selected"</c:if>>10</option>
          <option value="K" <c:if test="${videoDTO.selectresult3Points =='K'}" var="true">selected="selected"</c:if>>J</option>
          <option value="L" <c:if test="${videoDTO.selectresult3Points =='L'}" var="true">selected="selected"</c:if>>Q</option>
          <option value="M" <c:if test="${videoDTO.selectresult3Points =='M'}" var="true">selected="selected"</c:if>>K</option>
        </select>
        <select name="selectresult3A">
		  <option value="X" <c:if test="${videoDTO.selectresult3ZX =='X'}" var="true">selected="selected"</c:if>>闲</option>
          <option value="Z" <c:if test="${videoDTO.selectresult3ZX =='Z'}" var="true">selected="selected"</c:if>>庄</option>
        </select>
        <%--<input name="bjl_result3Time" type="text" size="8" value="<%=bjl_result3Time %>"/>--%>
		<input name="bjl_result3Time" type="text" size="8" value="${videoDTO.result3Time}"/>
		 秒</td>
    </tr> 
	<tr>
      <td class="menutop" align="right">开牌4：</td>
      <td class="tdlefts"><select name="selectresult4Type" id="selectresult4Type">
         <option value="0" <c:if test="${videoDTO.selectresult4Type ==0}" var="true">selected="selected"</c:if>>方块</option>
          <option value="1" <c:if test="${videoDTO.selectresult4Type ==1}" var="true">selected="selected"</c:if>>梅花</option>
          <option value="2" <c:if test="${videoDTO.selectresult4Type ==2}" var="true">selected="selected"</c:if>>红桃</option>
          <option value="3" <c:if test="${videoDTO.selectresult4Type ==3}" var="true">selected="selected"</c:if>>黑桃</option>
        </select>
        <select name="selectresult4" id="selectresult4">
         <option selected="selected" value="A">A</option>
          <option value="A" <c:if test="${videoDTO.selectresult4Points =='A'}" var="true">selected="selected"</c:if>>A</option>
          <option value="B" <c:if test="${videoDTO.selectresult4Points =='B'}" var="true">selected="selected"</c:if>>2</option>
          <option value="C" <c:if test="${videoDTO.selectresult4Points =='C'}" var="true">selected="selected"</c:if>>3</option>
          <option value="D" <c:if test="${videoDTO.selectresult4Points =='D'}" var="true">selected="selected"</c:if>>4</option>
          <option value="E" <c:if test="${videoDTO.selectresult4Points =='E'}" var="true">selected="selected"</c:if>>5</option>
          <option value="F" <c:if test="${videoDTO.selectresult4Points =='F'}" var="true">selected="selected"</c:if>>6</option>
          <option value="G" <c:if test="${videoDTO.selectresult4Points =='G'}" var="true">selected="selected"</c:if>>7</option>
          <option value="H" <c:if test="${videoDTO.selectresult4Points =='H'}" var="true">selected="selected"</c:if>>8</option>
          <option value="I" <c:if test="${videoDTO.selectresult4Points =='I'}" var="true">selected="selected"</c:if>>9</option>
          <option value="J" <c:if test="${videoDTO.selectresult4Points =='J'}" var="true">selected="selected"</c:if>>10</option>
          <option value="K" <c:if test="${videoDTO.selectresult4Points =='K'}" var="true">selected="selected"</c:if>>J</option>
          <option value="L" <c:if test="${videoDTO.selectresult4Points =='L'}" var="true">selected="selected"</c:if>>Q</option>
          <option value="M" <c:if test="${videoDTO.selectresult4Points =='M'}" var="true">selected="selected"</c:if>>K</option>
        </select>
        <select name="selectresult4A">
          <option value="X" <c:if test="${videoDTO.selectresult4ZX =='X'}" var="true">selected="selected"</c:if>>闲</option>
          <option value="Z" <c:if test="${videoDTO.selectresult4ZX =='Z'}" var="true">selected="selected"</c:if>>庄</option>
        </select>
<%--        <input name="bjl_result4Time" type="text" size="8" value="<%=bjl_result4Time %>"/>--%>
		<input name="bjl_result4Time" type="text" size="8" value="${videoDTO.result4Time}"/>
        秒</td>
    </tr>
	<tr>
      <td class="menutop" align="right">开牌5：</td>
      <td class="tdlefts"><select name="selectresult5Type" id="selectresult5Type">
         <option value="0" <c:if test="${videoDTO.selectresult5Type ==0}" var="true">selected="selected"</c:if>>方块</option>
          <option value="1" <c:if test="${videoDTO.selectresult5Type ==1}" var="true">selected="selected"</c:if>>梅花</option>
          <option value="2" <c:if test="${videoDTO.selectresult5Type ==2}" var="true">selected="selected"</c:if>>红桃</option>
          <option value="3" <c:if test="${videoDTO.selectresult5Type ==3}" var="true">selected="selected"</c:if>>黑桃</option>
        </select>
        <select name="selectresult5" id="selectresult5">
         <option selected="selected" value="A">A</option>
          <option value="A" <c:if test="${videoDTO.selectresult5Points =='A'}" var="true">selected="selected"</c:if>>A</option>
          <option value="B" <c:if test="${videoDTO.selectresult5Points =='B'}" var="true">selected="selected"</c:if>>2</option>
          <option value="C" <c:if test="${videoDTO.selectresult5Points =='C'}" var="true">selected="selected"</c:if>>3</option>
          <option value="D" <c:if test="${videoDTO.selectresult5Points =='D'}" var="true">selected="selected"</c:if>>4</option>
          <option value="E" <c:if test="${videoDTO.selectresult5Points =='E'}" var="true">selected="selected"</c:if>>5</option>
          <option value="F" <c:if test="${videoDTO.selectresult5Points =='F'}" var="true">selected="selected"</c:if>>6</option>
          <option value="G" <c:if test="${videoDTO.selectresult5Points =='G'}" var="true">selected="selected"</c:if>>7</option>
          <option value="H" <c:if test="${videoDTO.selectresult5Points =='H'}" var="true">selected="selected"</c:if>>8</option>
          <option value="I" <c:if test="${videoDTO.selectresult5Points =='I'}" var="true">selected="selected"</c:if>>9</option>
          <option value="J" <c:if test="${videoDTO.selectresult5Points =='J'}" var="true">selected="selected"</c:if>>10</option>
          <option value="K" <c:if test="${videoDTO.selectresult5Points =='K'}" var="true">selected="selected"</c:if>>J</option>
          <option value="L" <c:if test="${videoDTO.selectresult5Points =='L'}" var="true">selected="selected"</c:if>>Q</option>
          <option value="M" <c:if test="${videoDTO.selectresult5Points =='M'}" var="true">selected="selected"</c:if>>K</option>
        </select>
        <select name="selectresult5A">
		 <option value="X" <c:if test="${videoDTO.selectresult5ZX =='X'}" var="true">selected="selected"</c:if>>闲</option>
          <option value="Z" <c:if test="${videoDTO.selectresult5ZX =='Z'}" var="true">selected="selected"</c:if>>庄</option>
        </select>
        <%--<input name="bjl_result5Time" type="text" size="8" value="<%=bjl_result5Time %>"/>--%>
		<input name="bjl_result5Time" type="text" size="8" value="${videoDTO.result5Time}"/>
        秒</td>
    </tr>
	<tr>
      <td class="menutop" align="right">开牌6：</td>
      <td class="tdlefts"><select name="selectresult6Type" id="selectresult6Type">
          <option value="0" <c:if test="${videoDTO.selectresult6Type ==0}" var="true">selected="selected"</c:if>>方块</option>
          <option value="1" <c:if test="${videoDTO.selectresult6Type ==1}" var="true">selected="selected"</c:if>>梅花</option>
          <option value="2" <c:if test="${videoDTO.selectresult6Type ==2}" var="true">selected="selected"</c:if>>红桃</option>
          <option value="3" <c:if test="${videoDTO.selectresult6Type ==3}" var="true">selected="selected"</c:if>>黑桃</option>
        </select>
        <select name="selectresult6" id="selectresult6">
         <option selected="selected" value="A">A</option>
          <option value="A" <c:if test="${videoDTO.selectresult6Points =='A'}" var="true">selected="selected"</c:if>>A</option>
          <option value="B" <c:if test="${videoDTO.selectresult6Points =='B'}" var="true">selected="selected"</c:if>>2</option>
          <option value="C" <c:if test="${videoDTO.selectresult6Points =='C'}" var="true">selected="selected"</c:if>>3</option>
          <option value="D" <c:if test="${videoDTO.selectresult6Points =='D'}" var="true">selected="selected"</c:if>>4</option>
          <option value="E" <c:if test="${videoDTO.selectresult6Points =='E'}" var="true">selected="selected"</c:if>>5</option>
          <option value="F" <c:if test="${videoDTO.selectresult6Points =='F'}" var="true">selected="selected"</c:if>>6</option>
          <option value="G" <c:if test="${videoDTO.selectresult6Points =='G'}" var="true">selected="selected"</c:if>>7</option>
          <option value="H" <c:if test="${videoDTO.selectresult6Points =='H'}" var="true">selected="selected"</c:if>>8</option>
          <option value="I" <c:if test="${videoDTO.selectresult6Points =='I'}" var="true">selected="selected"</c:if>>9</option>
          <option value="J" <c:if test="${videoDTO.selectresult6Points =='J'}" var="true">selected="selected"</c:if>>10</option>
          <option value="K" <c:if test="${videoDTO.selectresult6Points =='K'}" var="true">selected="selected"</c:if>>J</option>
          <option value="L" <c:if test="${videoDTO.selectresult6Points =='L'}" var="true">selected="selected"</c:if>>Q</option>
          <option value="M" <c:if test="${videoDTO.selectresult6Points =='M'}" var="true">selected="selected"</c:if>>K</option>
        </select>
        <select name="selectresult6A">
         <option value="X" <c:if test="${videoDTO.selectresult6ZX =='X'}" var="true">selected="selected"</c:if>>闲</option>
          <option value="Z" <c:if test="${videoDTO.selectresult6ZX =='Z'}" var="true">selected="selected"</c:if>>庄</option>
        </select>
        <%--<input name="bjl_result6Time" type="text" size="8" value="<%=bjl_result6Time %>"/>--%>
		<input name="bjl_result6Time" type="text" size="8" value="${videoDTO.result6Time}"/>
        秒</td>
    </tr>
	
  </table>
  <table width="800" border="0" align="center" cellpadding="0" cellspacing="0" class="table_margin">
    <tr align="center">
      <td class="menutop"> <input type="button" name="button" value="修 改"  onClick="updateOne()"/>&nbsp;&nbsp;&nbsp;
		<input type="button" name="button" value="返 回"  onClick="window.location.href='<%=request.getContextPath() %>/bjl/video.do?action=list'"/>
&nbsp;</td>
    </tr>
  </table>
</form>
</body>
<script type="text/javascript">
       var msg="<%=msg %>";
       if(msg!='')alert(msg);
function setphotourl1(url){
			document.all.bjl_fileName.value=url;
		}	
</script>
</html>

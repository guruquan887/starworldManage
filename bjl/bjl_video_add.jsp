<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
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
<title>新增视频</title>
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
	if (CheckNull(document.forms['form1'].bjl_videoName, '视频名称不能为空!')){
		return false;
	}
	if(CheckNull(document.forms['form1'].bjl_hgName, '荷官名称不能为空!')){
	    return false;
	}
	if(CheckNull(document.forms['form1'].bjl_videolength, '视频时间不能为空!')){
	    return false;
	}
	//if (CheckNull(document.forms['form1'].fileName, '文件名称不能为空!')){
	//	return false;
	//}
	form1.submit();

}

</script>
<body>
<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/bjl/video.do?action=videoAdd">
  <p>&nbsp;</p>
  <p></p>
  <table width="800" border="0" align="center" cellpadding="2" cellspacing="1" class="table_margin">
    <tr>
      <td height="22" colspan="2" class="title">视频管理：添加视频</td>
    </tr>
    <tr>
      <td class="menutop" align="right">视频名称：</td>
      <td class="tdlefts">
      <input name="bjl_videoName" type="text" size="40" value="<%=bjl_videoName %>"/></td>
    </tr>
    <tr>
      <td class="menutop" align="right">文件名称：</td>
      <td class="tdlefts"><iframe name="uploadframe" id="uploadframe" width="90%" height="60" src="<%=request.getContextPath()%>/bjl/FileUploadInner.jsp?type=1" frameborder=0 scrolling=no> </iframe>
	  <input type="hidden" name="bjl_fileName" id="bjl_fileName" value=""></td>
    </tr>
	    <tr>
      <td class="menutop" align="right">房间类型：</td>
      <td class="tdlefts">
	  <select name="roomType" id="roomType">
	   <c:forEach var="roomList" items="${roomList}">
          <option value="${roomList.roomID}">${roomList.roomName}</option>
	      </c:forEach>
        </select></td>
    </tr>
    <tr>
      <td class="menutop" align="right">视频类型：</td>
      <td class="tdlefts"> <select name="bjl_videoType" id="bjl_videoType">
          <option value="0">等待下注</option>
          <option value="1">开牌</option>
          <option value="2">换人</option>
          <option value="3">黄牌或红牌、洗牌</option>
      </select></td>
    </tr>
    
    <tr>
      <td class="menutop" align="right">荷官名称1：</td>
      <td class="tdlefts"><input name="bjl_hgName" type="text" size="25" value="<%=bjl_hgName %>"/>在添加换人视频时荷官名称填离开荷官的名称</td>
    </tr>
    <tr>
      <td class="menutop" align="right">荷官名称2：</td>
      <td class="tdlefts"><input name="bjl_hg2Name" type="text" size="25" value="<%=bjl_hg2Name %>"/></td>
    </tr>
    <tr>
      <td class="menutop" align="right">开牌结果：</td>
      <td class="tdlefts"><input name="bjl_result" type="text" size="40" value="<%=bjl_result %>"/>在添加视频时先查看开牌结果，录入进来</td>
    </tr>
    <tr class="tdbg">
      <td class="menutop" align="right">视频时间：</td>
      <td class="tdlefts"><input name="bjl_videolength" type="text" size="10" value="<%=bjl_videolength %>"/>
      添加视频录入该视频段的时间（秒）</td>
    </tr>
	 <tr>
      <td class="menutop" align="right">输赢结果：</td>
      <td class="tdlefts"><select name="select_resultOfWinLost" size="1" id="resultOfWinLost"> 
	  	<option value="DD">等待</option>
		<option value="ZY">庄赢</option>
		<option value="XY">闲赢</option>
		<option value="HJ">和局</option>
       </select>在添加视频时根据视频录入输赢结果</td>
    </tr>
	 <tr>
      <td class="menutop" align="right">开牌1：</td>
      <td class="tdlefts"><select name="selectresult1Type" id="selectresult1Type">
          <option value="0">方块</option>
          <option value="1">梅花</option>
          <option value="2">红桃</option>
          <option value="3">黑桃</option>
        </select>
        <select name="selectresult1" id="selectresult1">
          <option selected="selected" value="A">A</option>
          <option value="B">2</option>
          <option value="C">3</option>
          <option value="D">4</option>
          <option value="E">5</option>
          <option value="F">6</option>
          <option value="G">7</option>
          <option value="H">8</option>
          <option value="I">9</option>
          <option value="J">10</option>
          <option value="K">J</option>
          <option value="L">Q</option>
          <option value="M">K</option>
        </select>
        <select name="selectresult1A">
		  <option value="X">闲</option>
          <option value="Z">庄</option>
        </select>
        <input name="bjl_result1Time" type="text" size="8" value="<%=bjl_result1Time %>"/>
       秒</td>
    </tr>
	 <tr>
      <td class="menutop" align="right">开牌2：</td>
      <td class="tdlefts"><select name="selectresult2Type" id="selectresult2Type">
          <option value="0">方块</option>
          <option value="1">梅花</option>
          <option value="2">红桃</option>
          <option value="3">黑桃</option>
        </select>
        <select name="selectresult2" id="selectresult2">
          <option selected="selected" value="A">A</option>
          <option value="B">2</option>
          <option value="C">3</option>
          <option value="D">4</option>
          <option value="E">5</option>
          <option value="F">6</option>
          <option value="G">7</option>
          <option value="H">8</option>
          <option value="I">9</option>
          <option value="J">10</option>
          <option value="K">J</option>
          <option value="L">Q</option>
          <option value="M">K</option>
        </select>
        <select name="selectresult2A">
          <option value="Z">庄</option>
          <option value="X">闲</option>
        </select>
        <input name="bjl_result2Time" type="text" size="8" value="<%=bjl_result2Time %>"/>
       秒</td>
    </tr> 
	<tr >
      <td class="menutop" align="right">开牌3：</td>
      <td class="tdlefts"> <select name="selectresult3Type" id="selectresult3Type">
          <option value="0">方块</option>
          <option value="1">梅花</option>
          <option value="2">红桃</option>
          <option value="3">黑桃</option>
        </select>
        <select name="selectresult3" id="selectresult3">
          <option selected="selected" value="A">A</option>
          <option value="B">2</option>
          <option value="C">3</option>
          <option value="D">4</option>
          <option value="E">5</option>
          <option value="F">6</option>
          <option value="G">7</option>
          <option value="H">8</option>
          <option value="I">9</option>
          <option value="J">10</option>
          <option value="K">J</option>
          <option value="L">Q</option>
          <option value="M">K</option>
        </select>
        <select name="selectresult3A">
		  <option value="X">闲</option>
          <option value="Z">庄</option>
        </select>
        <input name="bjl_result3Time" type="text" size="8" value="<%=bjl_result3Time %>"/>
      秒</td>
    </tr> 
	<tr>
      <td class="menutop" align="right">开牌4：</td>
      <td class="tdlefts"><select name="selectresult4Type" id="selectresult4Type">
          <option value="0">方块</option>
          <option value="1">梅花</option>
          <option value="2">红桃</option>
          <option value="3">黑桃</option>
        </select>
        <select name="selectresult4" id="selectresult4">
         <option selected="selected" value="A">A</option>
          <option value="B">2</option>
          <option value="C">3</option>
          <option value="D">4</option>
          <option value="E">5</option>
          <option value="F">6</option>
          <option value="G">7</option>
          <option value="H">8</option>
          <option value="I">9</option>
          <option value="J">10</option>
          <option value="K">J</option>
          <option value="L">Q</option>
          <option value="M">K</option>
        </select>
        <select name="selectresult4A">
          <option value="Z">庄</option>
          <option value="X">闲</option>
        </select>
        <input name="bjl_result4Time" type="text" size="8" value="<%=bjl_result4Time %>"/>
      秒</td>
    </tr>
	<tr>
      <td class="menutop" align="right">开牌5：</td>
      <td class="tdlefts"><select name="selectresult5Type" id="selectresult5Type">
          <option value="0">方块</option>
          <option value="1">梅花</option>
          <option value="2">红桃</option>
          <option value="3">黑桃</option>
        </select>
        <select name="selectresult5" id="selectresult5">
         <option selected="selected" value="A">A</option>
          <option value="B">2</option>
          <option value="C">3</option>
          <option value="D">4</option>
          <option value="E">5</option>
          <option value="F">6</option>
          <option value="G">7</option>
          <option value="H">8</option>
          <option value="I">9</option>
          <option value="J">10</option>
          <option value="K">J</option>
          <option value="L">Q</option>
          <option value="M">K</option>
        </select>
        <select name="selectresult5A">
		  <option value="X">闲</option>
          <option value="Z">庄</option>
        </select>
        <input name="bjl_result5Time" type="text" size="8" value="<%=bjl_result5Time %>"/>
      秒</td>
    </tr>
	<tr >
      <td class="menutop" align="right">开牌6：</td>
      <td class="tdlefts"><select name="selectresult6Type" id="selectresult6Type">
          <option value="0">方块</option>
          <option value="1">梅花</option>
          <option value="2">红桃</option>
          <option value="3">黑桃</option>
        </select>
        <select name="selectresult6" id="selectresult6">
         <option selected="selected" value="A">A</option>
          <option value="B">2</option>
          <option value="C">3</option>
          <option value="D">4</option>
          <option value="E">5</option>
          <option value="F">6</option>
          <option value="G">7</option>
          <option value="H">8</option>
          <option value="I">9</option>
          <option value="J">10</option>
          <option value="K">J</option>
          <option value="L">Q</option>
          <option value="M">K</option>
        </select>
        <select name="selectresult6A">
          <option value="Z">庄</option>
          <option value="X">闲</option>
        </select>
        <input name="bjl_result6Time" type="text" size="8" value="<%=bjl_result6Time %>"/>
      秒</td>
    </tr>
  </table>
  <table width="800" border="0" align="center" cellpadding="0" cellspacing="0" class="table_margin">
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
			document.all.bjl_fileName.value=url;
		}	
</script>
</html>

<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
   String no = "";
   if(request.getParameter("no")!=null){
   		no = request.getParameter("no");
   }
   String msg ="";
   if(request.getAttribute("msg")!=null){
   		msg = request.getAttribute("msg").toString();
		System.out.println("--------->>"+msg);
   }
%>
<div style="text-align:center; width:${width}; font-size:14px; font-weight:bold; z-index:99999; display:block; background:#FFF; border:solid 1px #ccc; position:absolute; left:25%;margin:0px; padding:0px;" id="showDiv_${dto.id}">
<ul style="margin:0px; padding:0px;">
<li style="text-align:right; font-size:12px; list-style:none;height:20px; line-height:20px; background:#ccc; padding-right:8px;"><a href="javascript:;" onClick="closeDiv('showDiv_${dto.id}')">×</a></li>
<li id="fm_${dto.id}" style=" text-align:left; font-size:12px; color:${css};font-weight:bold;list-style:none;padding-right:5px;padding-left:5px;padding-top:3px; padding-bottom:3px;"><iframe src="" scrolling="no" frameborder="0" id="FM_Id_${dto.id}" onLoad="{TuneHeight('fm_${dto.id}','FM_Id_${dto.id}');}" style="top:0px;left:0px;position:absolute;visibility:inherit;z-index:-1;"></iframe>&nbsp;&nbsp;&nbsp;<%=msg %></li>
</ul>
</div> 
	  
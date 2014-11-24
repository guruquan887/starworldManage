// JavaScript Document
var xmlHttp;
var number = 1;
function creatXMLHttpRequest() {
	if(window.ActiveXObject) {
		xmlHttp = new ActiveXObject('Microsoft.XMLHTTP');
	} else if(window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	}
}

function startRequest(id,url) {
	creatXMLHttpRequest();
	xmlHttp.onreadystatechange = function(){handleStateChange(id);}
	xmlHttp.open("GET",url,true);
	xmlHttp.send(null);
}

function handleStateChange(id) {
	if(xmlHttp.readyState == 4 ){
		if(xmlHttp.status == 200) {
			var allcon =  xmlHttp.responseText;
            document.getElementById(id).innerHTML = allcon;
		}
	}
}

function handleStateChange1(id) {
	if(xmlHttp.readyState == 4 ){
		if(xmlHttp.status == 200) {
			var allcon =  xmlHttp.responseText;
			var tem = allcon.split("|");
            document.getElementById(id).innerHTML = tem[0];
		}
	}
}


function ajaxLoadPage(url,request,method,container)
{
	method=method.toUpperCase();
	var loading_msg='Loading...';
    creatXMLHttpRequest();
	if (method=='GET')
	{
		urls=url.split("?");
		if (urls[1]=='' || typeof urls[1]=='undefined')
		{
			url=urls[0]+"?"+request;
		}
		else
		{
			url=urls[0]+"?"+urls[1]+"&"+request;
		}
		
		request=null;
	}
	xmlHttp.open(method,url,true);
	if (method=="POST")
	{
		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	}
	xmlHttp.onreadystatechange=function(){
		if (xmlHttp.readyState==1)
		{
			document.getElementById(container).innerHTML=loading_msg;
			
		}
		if (xmlHttp.readyState==4)
		{
			//alert(document.getElementById(container)+"~~~~~~~~~~~"+document.getElementById(container).innerHTML);
			document.getElementById(container).innerHTML = xmlHttp.responseText;
		}
	}
	xmlHttp.send(request);
}


function formToRequestString(form_obj)
{
	var query_string='';
	var and='';
	//alert(form_obj.length);
	for (i=0;i<form_obj.length ;i++ )
	{
		e=form_obj[i];
		if (e.name!='')
		{
			if (e.type=='select-one')
			{
				element_value=e.options[e.selectedIndex].value;
			}
			else if (e.type=='checkbox' || e.type=='radio')
			{
				if (e.checked==false)
				{
					break;	
				}
				element_value=e.value;
			}
			else
			{
				element_value=e.value;
			}
			query_string+=and+e.name+'='+element_value.replace(/\&/g,"%26");
			and="&"
		}
		//alert(query_string);
	}
	return query_string;
}

function ajaxFormSubmit(form_obj,container)
{
	ajaxLoadPage(form_obj.getAttributeNode("action").value,formToRequestString(form_obj),form_obj.method,container)
}

function ajaxFormSubmit2(form_Str,container)
{
	//alert(document.getElementById(form_Str)+','+form_Str);
	ajaxLoadPage(document.getElementById(form_Str).getAttributeNode("action").value,formToRequestString(document.getElementById(form_Str)),document.getElementById(form_Str).method,container)
}


function TuneHeight(fm_name,fm_id){  
    var frm=document.getElementById(fm_id);  
    var subWeb=document.getElementById(fm_name);
    if(frm != null && subWeb != null){
        frm.style.height = (subWeb.scrollHeight+20)+"px";
        frm.style.width = subWeb.scrollWidth+"px";
    }
}  

//function $(){return document.getElementById?document.getElementById(arguments[0]):eval(arguments[0]);} 与jQuery冲突




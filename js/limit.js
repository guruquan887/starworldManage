// JavaScript Document
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
	
	if (CheckNull(document.forms['form2'].password, '密码不能为空!')){
		return false;
	}
	form2.submit();

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

       //弹出友好提示
       var msg="<%=msg %>";
       if(msg!='')alert(msg);

//页面加载时  显示第一级的所有选项
function SetContantFirst()
{
	var fid = 0;
	var uid = parseInt(document.getElementById("userid").value);
	AuthorityDwr.SetContantFirst(parseInt(fid),uid,function(data) {
		dwr.util.setValue("level1", data,{ escapeHtml:false });
	});
}
//加载二级选项
function SetContantSecond(fid,open)
{
	var uid = parseInt(document.getElementById("userid").value);
	if(document.getElementById("level2-"+fid).innerHTML!=''){
		dwr.util.setValue("level2-"+fid,'',{ escapeHtml:false });
		document.getElementById("level2-"+fid).style.display="none";
		return;
	}
	AuthorityDwr.SetContantSecond(parseInt(fid),uid,function(data) {
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
	var uid = parseInt(document.getElementById("userid").value);
	//能级管理选项显示
	AuthorityDwr.SetContantThird(parseInt(fid),uid,function(data) {
		if(data!='')
		{
			dwr.util.setValue("level3-"+fid, data,{ escapeHtml:false });
		}
	});
}
//加载四级选项
function SetContantFourth(fid,step)
{
	var uid = parseInt(document.getElementById("userid").value);
	//能级管理选项显示
	AuthorityDwr.SetContantFourth(parseInt(fid),uid,function(data) {
		if(data!='')
		{
			dwr.util.setValue("level4-"+fid, data,{ escapeHtml:false });
		}
	});
}
//加载四级选项
function SetContantFifth(fid,step)
{
	var uid = parseInt(document.getElementById("userid").value);
	//能级管理选项显示
	AuthorityDwr.SetContantFifth(parseInt(fid),uid,function(data) {
		if(data!='')
		{
			dwr.util.setValue("level5-"+fid, data,{ escapeHtml:false });
		}
	});
}
function changeSelectState(id,cdname)
{
	var uid = parseInt(document.getElementById("userid").value);
	if(document.getElementById("menu"+id).checked==true)
	{
		//删除选中的权限和级联的权限
		AuthorityDwr.GetAuthority(id,uid,function(data) {
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
		AuthorityDwr.ReleaseAuthority(id,uid,function(data) {
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
		AuthorityDwr.ReleaseAuthority(id,uid,function(data) {
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
		AuthorityDwr.GetAuthority(id,uid,function(data) {
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
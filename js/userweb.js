// JavaScript Document
function validateLogin(){
	if(document.getElementById("username").value==""){
		//document.getElementById("username").innerHTML="请输入用户名";
		alert("请输入管理用户");
		document.getElementById("username").focus();
		return false;
		}
	if(document.getElementById("password").value==""){
		//document.getElementById("username").innerHTML="请输入用户名";
		alert("请输入管理密码");
		document.getElementById("password").focus();
		return false;
		}
	
	return true;
	}
	
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
	if (CheckNull(document.forms['form1'].userName, '用户名不能为空!')){
		return false;
	}
	if (CheckNull(document.forms['form1'].password, '密码不能为空!')){
		return false;
	}
	if(document.forms['form1'].password.value!=document.forms['form1'].password1.value){
	    alert("两次密码不相同!");
		return false;
	}
	form1.submit();
}
function isNumber(oNum){

 if(!oNum) return false; //line:160
 if(oNum.value=="") return false;
 var vv = oNum.value.replace(/[^\d]/g,'');
 var strP=/^\d+$/;
  if (!isNaN(oNum.value)) {
        return true;
    } else {
        alert("请输入数字");
        oNum.value=vv;
 		oNum.focus();
 		return false; 
    }
}
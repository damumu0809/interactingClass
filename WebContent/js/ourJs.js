
//startRegister_box

function inUser(){
    username_mess.style.visibility="visible";
}

function outUser(){
	u=userName.value;
	if(u==""){
		username_mess.style.visibility="hidden";
		return false;
	}
	
	reg= /.{4,20}/;             //代表着允许4到20位字符
	if(!reg.test(u)){
		username_mess.innerHTML="用户名长度只能在4-20位字符之间";
		username_mess.style.color="red";
		userName.style.color="red";
		userName.style.border="1px solid red"
		username_ok.style.visibility="hidden";
		return false;
	}

	reg= /^[\u4e00-\u9fa5 \w-]{4,20}$/; //代表着中文字符和\w（\w能够代替[a-zA-Z0-9_]）还有-
	if(reg.test(u)){
		username_ok.style.visibility="visible";
		username_mess.innerHTML="";
		userName.style.color="#555";
		userName.style.border="1px solid #ccc";
		return true;
	}else{
		username_mess.innerHTML="用户名只能由中文、英文、数字及'_'、'-'组成";
		username_mess.style.color="red";
		userName.style.color="red";
		userName.style.border="1px solid red";
		username_ok.style.visibility="hidden";
		return false;
	}
}

function inPass(){
	pass_mess.style.visibility="visible";
	passWord.style.border="1px solid gold";
}
        
function outPass(){
	p=passWord.value;
	passWord.style.border="1px solid AAAAAA";
	if(p==""){
		pass_mess.style.visibility="hidden";
		return false;
	}
	reg= /.{6,16}/;             //代表着允许4到20位字符
	if(!reg.test(p)){
		pass_mess.innerHTML="密码长度只能在6-16位字符之间";
		pass_mess.style.color="red";
		passWord.style.color="red";
		passWord.style.border="1px solid red"
		pass_ok.style.visibility="hidden";
		return false;
	}else{
		pass_mess.innerHTML="";
		pass_mess.style.color="black";
		passWord.style.color="#555";
		passWord.style.border="1px solid #ccc"
		pass_ok.style.visibility="visibe";
		return true;
	}
}

function inRPass(){
	rpass_mess.style.visibility="visible";
	confirmedPassword.style.border="1px solid gold";
}

function outRPass(){
	p=confirmedPassword.value;
	confirmedPassword.style.border="1px solid AAAAAA";
	if(p==""){
		rpass_mess.style.visibility="hidden";
		return false;
	}
	if(!(passWord.value==confirmedPassword.value)){
		rpass_mess.innerHTML="两次输入的密码不一致";
		rpass_mess.style.color="red";
        confirmedPassword.style.color="red";
		confirmedPassword.style.border="1px solid red";
		rpass_ok.style.visibility="hidden";
		return false;
    }else{
		rpass_mess.innerHTML="";
        confirmedPassword.style.color="#555";
		confirmedPassword.style.border="1px solid #ccc";
		rpass_ok.style.visibility="hidden";
		return true;
	}
}

function visibe(){
	if(viewpwd.checked){        //通过checked属性，能够得到这个复选框是否被选中的Boolean值
		passWord.style.backgroundColor="FFEEEE";
		v=passWord.value;
		v2=confirmedPassword.value;
		chang.innerHTML= "<input type='text' class='form-control' id='passWord' name='passWord' value='"+v+"' onFocus='inPass();' onBlur='outPass();' onKeyup='keyPass();'>";
		chang2.innerHTML="<input type='text' class='form-control' id='confirmedPassword' name='confirmedPassword' value='"+v2+"' onFocus='inRPass();' onBlur='outRPass();'>";
		}else{
			passWord.style.backgroundColor="#555";
			v=passWord.value;
			v2=confirmedPassword.value;
			chang.innerHTML="<input type='password' class='form-control' id='passWord' name='passWord' value='"+v+"' onFocus='inPass();' onBlur='outPass();' onKeyup='keyPass();'>";
			chang2.innerHTML="<input type='password' class='form-control' id='confirmedPassword' name='confirmedPassword' value='"+v2+"' onFocus='inRPass();' onBlur='outRPass();'>";
		}
}

function keyPass(){
	if(passWord.value.length>=6){
		pass_mess.style.display="none";
		strength.style.display="inline";
		sum=checkStrong(passWord.value);
		if(sum==1){
			l.style.backgroundColor="orange";
		}
		if(sum==2){
			l.style.backgroundColor="orange";
			m.style.backgroundColor="orange";
		}
		if(sum==3){
			l.style.backgroundColor="orange";
			m.style.backgroundColor="orange";
			h.style.backgroundColor="orange";
		}
	}
}
//判断密码强度
function checkStrong(Str){
	sum=0;
	reg= /[a-zA-Z]/;
	if(reg.test(Str)){
		sum++;
	}
	reg= /\d/;
	if(reg.test(Str)){
		sum++;
	}
	reg= /[_-]/;
	if(reg.test(Str)){
		sum++;
	}
	return sum;
}

function inMail(){
	mail_mess.style.visibility="visible";
	mailAddr.style.border="1px solid gold";
}
			
function outMail(){
	mm=mailAddr.value;
	mailAddr.style.border="1px solid AAAAAA";
	if(mm==""){
		mail_mess.style.visibility="hidden";
		return false;
	}
	reg=/\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/;
	if(reg.test(mm)){
		mail_ok.style.visibility="visible";
		mail_mess.innerHTML="";
		mailAddr.style.color="#555";
		mailAddr.style.border = "1px solid #ccc";
		return true;
	}else{
		mail_mess.innerHTML="邮箱格式不正确";
		mailAddr.style.color="red";
		mailAddr.style.border="1px solid red";
		mail_ok.style.visibility="hidden";
		return false;
	}
}

//验证输入后提交register_box表单
function checkResult(){
	if(outUser() && outPass() && outRPass() && outMail()){
		document.getElementById("registerForm").submit();
	}else{
		alert("输入信息有误,请检查后重新输入!");
		return;
	}
}
//endRegister_box
//startGetback_box
function checkResult2(){
	if(outPass() && outRPass()){
		document.getElementById("getbackForm").submit();
	}else{
		alert("密码不一致或格式错误，请检查后重新输入！");
		return;
	}
}

//endGetback_box








<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form >
<input type="text" name="username" maxlength="16" placeholder="用户名">
<span class="errorMsg" id="username_msg"></span>
<input type="password" name="password" maxlength="16" placeholder="密码">
<span class="errorMsg" id="pwd_msg"></span>
<input type="text" name="imgCode" id="imgCode" placeholder="验证码">
	<img alt="验证码" src="kaptcha.do" title="点击刷新" id="kapter">
	<span class="errorMsg" id="kapter_msg"></span>
	<button type="button" id="button">注册</button>
	<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
	<script type="text/javascript">
	//点击刷新验证码
	$("#kapter").bind("click", function(){
		$(this).attr("src", "kaptcha.do?date="+new Date());
	})
		//点击注册按钮
		$("#button").bind("click", function(){
			var username = $("input[name='username']").val();
			var password = $("input[name='password']").val();
			var validatePassword = $("input[name='validatePassword']").val();
			var imgCode = $("#imgCode").val();
			if (!username || username.length < 6) {
				$("#username_msg").html("用户名为空或者用户名长度小于6");
				return;
			}
			if (!password || password.length < 6) {
				$("#pwd_msg").html("密码为空或者密码长度小于6");
				return;
			}
			if (password != validatePassword) {
				$("#pwd_msg").html("两次输入密码不相同");
				return;
			}
			if (!imgCode) {
				$("#kapter_msg").html("请输入验证码");
				return;
			}
			
			//验证用户名是否存在，返回值包含flag, msg
			//var ajax1 = $.post("/register/validate.do",{})
	</script>
</form>

</body>
</html>
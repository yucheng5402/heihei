<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String path = request.getContextPath();
    String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
     %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basepath %>>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form>
		<input type="text" name="username" placeholder="用户名"> <input
			type="password" name="password" placeholder="密码">
		<button type="button">登录</button>
		<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
		<script type="text/javascript">
		$("button").bind("click",function(){
			var obj = {};    //new Object()
			$.ajax({
				url:"login.do",
				type:"post",
				data:$("form").serialize(),
				dataType:"json",
				success: function(result){
					//alert(result.name)
					//如果结果为true，就跳转到user/index.jsp
				
					if (result.code) {
						console.log("登录成功")
					} else {
						console.log("登录失败");
					}
				},
				error : function(){
					alert("出错啦")
				}
			})
		})
		</script>
	</form>
</body>
</html>
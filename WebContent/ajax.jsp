<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String path = request.getContextPath();
    String basepath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basepath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="submit" value="点击1" onclick="doAjax()">
	<input type="submit" value="点击2" onclick="doAjax2()">
	<input type="text" id="username" name="username">
	<input type="submit" value="点击3" onclick="doAjax3()">
 	<div id="show"></div>
	<script type="text/javascript" src="js/jquery-1.7.2.js"></script>
	<script>
	var doAjax = function() {
		$.ajax({
			url:"ajaxDemo.do",
			type:"get",
			data:{
				"name":"小明",
				"sex":"男"
				},
			
			dataType:"text",
			beforeSend:function(even){
				console.log("请求之前")
			},
			success:function(result){
				console.log("请求成功");
				$("#show").html(result)
			},
			error:function(even){
				console.log("请求失败")
			}
			})
			}
	
	var doAjax2 = function () {
		$.ajax({
			url:"ajaxDemo.do",
			type:"post",
			data:{
				"name":"小明",
				"sex":"男"
				},
				dataType:"text",
				beforeSend:function(even){
					console.log("请求之前")
				},
				success:function(result){
					console.log("请求成功");
					$("#show").html(result)
				},
				error:function(even){
					console.log("请求失败")
				}
		})
		}
	var doAjax3 = function () {
		var username = $("#username").val();
		$.get("getHtml.do",{"id":username},function(result){
			$("#show").html(result);
			},"html")
	}
	
	</script>
</body>
</html>
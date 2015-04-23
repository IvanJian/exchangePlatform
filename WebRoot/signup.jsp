<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>Find-在集大，发现更多</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="集美大学,交流平台,简一帆">
<meta http-equiv="description" content="集美大学交流平台">
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css">
<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://code.jquery.com/jquery-1.11.2.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<!-- 插件 -->
<script src="js/jsrender.js"></script>
<script src="js/jquery.cookie.js"></script>
<script type="text/javascript" src="js/jQuery.md5.js"></script>
<!-- 样式 -->
<link type="text/css" rel="stylesheet" href="css/mycss.css" />
</head>

<body class="loginTop">
	<div class="row">
		<div class="container">
			<div class="col-md-8">
				<div class="row">
					<div class="col-md-3 col-md-offset-2">
						<img src="images/logo.png" alt="logo" />
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="page-header">
					<button type="button" class="btn btn-info" data-toggle="modal"
						data-target="#loginForm">已有账号，立刻登陆</button>
				</div>
				<div class="form-group">
					<label for="username"><font color="#FFFFFF">用户名</label></font>
					<input type="text" class="form-control" id="username"
						placeholder="Enter user name">
				</div>
				<div class="form-group">
					<label for="name"><font color="#FFFFFF">昵称</font></label>
					<input type="text" class="form-control" id="name"
						placeholder="Enter your name">
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1"><font color="#FFFFFF">密码</font></label>
					<input type="password" class="form-control"
						id="passwordHash" placeholder="Password">
				</div>
				<div class="form-group">
					<label for="email"><font color="#FFFFFF">Email</font></label>
					<input type="email" class="form-control" id="email"
						placeholder="Enter email">
				</div>
				<button type="button" class="btn btn-info btn-block" id="register">
					<font color="#FFFFFF">注册集大Find社区</font>
				</button>
			</div>
		</div>
	</div>
	<!-- 登录窗口 -->
	<div class="modal fade" id="loginForm" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">登录集大Find社区</h4>
				</div>
				<div class="modal-body">
					<div class="row">
						<div class="container">
							<div class="col-md-4 col-md-offset-1">
								<hr />
								<div class="form-group">
									<label for="username_login">用户名</label> <input type="text"
										class="form-control" id="username_login"
										placeholder="Enter user name">
								</div>
								<div class="form-group">
									<label for="passwordHash_login">密码</label> <input
										type="password" class="form-control"
										id="passwordHash_login" placeholder="password">
								</div>
								<hr />
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" id="login">确认登录</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<!-- 登录窗口结束 -->
	
	<script>
		$(document).ready(function(){
			$("#register").click(function(){
				var user=new Object();
				user.username=$("#username").val();
				user.passwordHash=$("#passwordHash").val();
				user.name=$("#name").val();
				user.email=$("#email").val();
				$.ajax({
					url : "api/user/register",
					type : "POST",
					contentType : 'application/json;charset=UTF-8',
					data : JSON.stringify(user),
					success : function(data) {
						if(data.responseCode=="0101"){
							var cookieTime= new Date();
							cookieTime.setTime(parseInt(data.token.tokenDeadline,10));
							$.cookie("userId",data.token.userId+"",{expires:cookieTime, path: '/' });
							$.cookie("token",data.token.token,{expires:cookieTime, path: '/' });
							$.cookie("name",data.token.name,{expires:cookieTime, path: '/' });
							url="http://"+ window.location.host+ "/exchangePlatform";
							window.location.replace(url);
						}
						else alert(data.message);
					},
					error : function() {
						alert("Error!");
					}
				});
			});
			
			$("#login").click(function() {
				var username = $("#username_login").val();
				var password = $.md5($("#passwordHash_login").val());
				$.post("api/user/authenticate", {
					"username" : username,
					"passwordHash" : password
				}, function(data) {
					if(data.responseCode=="0201"){
						var cookieTime= new Date();
						cookieTime.setTime(parseInt(data.token.tokenDeadline,10));
						$.cookie("userId",data.token.userId+"",{expires:cookieTime});
						$.cookie("token",data.token.token,{expires:cookieTime});
						$.cookie("name",data.token.name,{expires:cookieTime});
						url="http://"+ window.location.host+ "/exchangePlatform";
						window.location.replace(url);
					}
					else{
						alert(data.message);
					}
				});
	
			});
		});
	</script>
</body>
</html>

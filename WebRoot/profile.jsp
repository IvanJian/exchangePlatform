<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
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
<script src="/exchangePlatform/js/jquery-2.1.3.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<!-- 插件 -->
<script src="/exchangePlatform/js/jsrender.js"></script>
<script src="/exchangePlatform/js/jquery.cookie.js"></script>
<script type="text/javascript" src="/exchangePlatform/js/jQuery.md5.js"></script>
<!-- 编辑器 -->
<script charset="utf-8" src="/exchangePlatform/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="/exchangePlatform/kindeditor/lang/zh_CN.js"></script>
<!-- 样式 -->
<link type="text/css" rel="stylesheet" href="/exchangePlatform/css/mycss.css"/>
<link type="text/css" rel="stylesheet" href="/exchangePlatform/kindeditor/themes/default/default.css" />
</head>

<body class="navPad">
	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/exchangePlatform">Find-在集大，发现更多</a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<form class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
				</div>
				<button type="submit" class="btn btn-default">搜索</button>
			</form>
			<ul class="nav navbar-nav">
				<li><a href="/exchangePlatform/write">开始撰写...</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false" id="name">
				<script>
					if(typeof($.cookie("name"))!="undefined"){
						var loginState=true;
						document.write($.cookie("name"));
					}else{
						var loginState=false;
						document.write("请登录");
					}
				</script>
						 <span class="caret"></span>
				</a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="/exchangePlatform/my/profile" id="profile">我的资料</a></li>
						<li><a href="/exchangePlatform/my/bookmarks" id="myarticles">我的收藏</a></li>
						<li class="divider"></li>
						<li><a href="javascript:;" id="signout" onclick="signout()">退出登录</a></li>
					</ul></li>
				<li><a href="#">下载安卓客户端</a></li>

			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
	
	<div class="container">
		<div class="page-header">
 			<h1>个人资料        <small id="tagName"></small></h1>
		</div>
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<img src="" alt="..." class="img-circle headicon center-block" id="icon">
				<p></p>
				<input type="button" id="upload" value="更改头像" class="btn btn-info center-block" />
				<hr />
				<table class="table">
					<tr>
						<td class='text-center' width="300">用户名</td>
						<td id="username"></td>
					</tr>
					<tr>
						<td class='text-center'>姓名</td>
						<td id="uname"></td>
					</tr>
					<tr>
						<td class='text-center'>邮箱</td>
						<td id="email"></td>
					</tr>
					<tr>
						<td class='text-center'>发布文章</td>
						<td id="articleAmount"></td>
					</tr>
					<tr>
						<td class='text-center'>获得赞数</td>
						<td id="likeAmount"></td>
					</tr>
					<tr>
						<td class='text-center'>文章被收藏</td>
						<td id="bookmarkAmount"></td>
					</tr>
				</table>
			</div>
		</div>
	</div>  
	
		<script src="/exchangePlatform/js/mianpage.js"></script>
	<script>
		var editor;
		var image;
		KindEditor.ready(function(K) {
			editor = K.editor({
				allowFileManager: false,
			});
			K('#upload').click(function() {
				editor.loadPlugin('image',
				function() {
					editor.plugin.imageDialog({
						showRemote : false,
						clickFn: function(url, title, width, height, border, align) {
							image=url;
							editor.hideDialog();
							var request=new Object();
							var token=new Object();
							token.userId=$.cookie("userId");
							token.token=$.cookie("token");
							request.token=token;
							request.icon=image;
							$.ajax({
								url : "/exchangePlatform/api/user/seticon",
								type : "POST",
								contentType : 'application/json;charset=UTF-8',
								data : JSON.stringify(request),
								success : (function(data) {
									if(data.responseCode=="0902"){
										alert("您的登录已过期，请重新登录");
										url="http://"+ window.location.host+ "/exchangePlatform/signup";
										window.location.replace(url);
									}
									if(data.responseCode=="0901"){
										$("#icon").attr("src",image);
									}
								}),
								error : function() {
									alert("Error");
								}
							});
						}
					});
				});
			});
		});
		$(document).ready(function(){
			if(!$.cookie("userId")){
				alert("您还没有登录，请登录后再执行操作。");
				url="http://"+ window.location.host+ "/exchangePlatform/signup";
				window.location.replace(url);
			}
			nav();
			var token=new Object();
			token.userId=$.cookie("userId");
			token.token=$.cookie("token");
			$.ajax({
				url : "/exchangePlatform/api/user/profile",
				type : "POST",
				contentType : 'application/json;charset=UTF-8',
				data : JSON.stringify(token),
				success : (function(data) {
					if(data.responseCode=="0802"){
						alert("您的登录已过期，请重新登录");
						url="http://"+ window.location.host+ "/exchangePlatform/signup";
						window.location.replace(url);
					}
					if(data.responseCode=="0801"){
						$("#username").html(data.profileModel.username);
						$("#tagName").html(data.profileModel.name);
						$("#uname").html(data.profileModel.name);
						$("#email").html(data.profileModel.email);
						$("#likeAmount").html(data.profileModel.likeAmount);
						$("#bookmarkAmount").html(data.profileModel.bookmarkAmount);
						$("#articleAmount").html(data.profileModel.articleAmount);
						$("#icon").attr("src",data.profileModel.icon);
					}
				}),
				error : function() {
					alert("Error");
				}
			});
		});
	</script>
  </body>
</html>

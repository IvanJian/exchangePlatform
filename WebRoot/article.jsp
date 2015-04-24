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
<link type="text/css" rel="stylesheet" href="css/mycss.css"/>

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
			<a class="navbar-brand" href="#">Find-在集大，发现更多</a>
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
						<li><a href="#" id="profile">我的资料</a></li>
						<li><a href="#" id="myarticles">我的收藏</a></li>
						<li class="divider"></li>
						<li><a href="javascript:;" onclick="signout()"id="signout">退出登录</a></li>
					</ul></li>
				<li><a href="#">下载安卓客户端</a></li>

			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
	
	<div class="container">
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<div class="jumbotron">
					<div class="span7 text-center" id="head">
						<h2 id="title"></h4>
					</div>
					<hr />
					<div id="content"></div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-1 col-md-offset-5">
				<button type="button" class="btn btn-warning" onclick="like(${id })">点赞</button>
			</div>
			<div class="col-md-2">
				<button type="button" class="btn btn-success" onclick="bookmark(${id })">收藏</button>
			</div>
		</div>
		<hr />
	</div>
	<script src="js/mianpage.js"></script>
	<script>
		$(document).ready(function(){
			nav();
			$.get("api/article/${id }",function(article){
				var t = new Date();
				t.setTime(parseInt(article.uploadDateTime, 10));
				article.time = new String(t.toLocaleString());
				$("#title").html(article.title);
				$("#content").html(article.content);
				$("#head").append("<h6>作者："+article.authorName+"  发布时间："+article.time+"  主题："+article.tags);
				$("#head").append("<h6>得到赞数："+article.likeAmount+"  阅读量："+article.readNumber);
			});
		});
	</script>
</body>
</html>

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

<title>My JSP 'bookmarks.jsp' starting page</title>

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
<script src="js/jquery-2.1.3.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<!-- 插件 -->
<script src="js/jsrender.js"></script>
<script src="js/jquery.cookie.js"></script>
<script type="text/javascript" src="js/jQuery.md5.js"></script>
<!-- 样式 -->
<link type="text/css" rel="stylesheet" href="css/mycss.css" />

</head>

<<body class="navPad">
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
						<li><a href="/exchangePlatform/my/profile" id="profile">我的资料</a></li>
						<li><a href="my/bookmarks" id="myarticles">我的收藏</a></li>
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
		<div class="page-header">
  			<h1>我的收藏        <small id="tagName">您可以随时查看您收藏过的文章</small></h1>
		</div>
		<div class="row">
			<div class="col-md-8 col-md-offset-3">
				<div id="titles">
				</div>
			</div>
		</div>
	</div>
	<script src="js/mianpage.js"></script>
	<script>
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
				url : "api/user/get/bookmarks",
				type : "POST",
				contentType : 'application/json;charset=UTF-8',
				data : JSON.stringify(token),
				success : (function(data) {
					if(data.responseCode==null){
						for(var i=0;i<data.length;i++){
							$("#titles").append("<table class='table' id='title-"+data[i].articleId+"'><h4><button type='button' class='btn btn-default btn-sm' name='r' value="+data[i].articleId+"'><span class='glyphicon glyphicon-remove' aria-hidden='true'>移除 </span></button><small>.    .    .</small><a href='article/"+data[i].articleId+"'>"+data[i].title+"</a></h4></table>");
						}
					}
					if(data.responseCode=="0602"){
						alert("您的登录已过期，请重新登录");
						url="http://"+ window.location.host+ "/exchangePlatform/signup";
						window.location.replace(url);
					}
					if(data.responseCode=="0603"){
						$("#titles").html("<tr><td><h3>您还没有收藏过文章。</h3><td></tr>");
					}
				}),
				error : function() {
					alert("Error");
				}
			});
			$('button[name=r]').each(function(){$(this).live('click',function(){
				alert("1");
				var request=new Object();
				var token=new Object();
				token.userId=$.cookie("userId");
				token.token=$.cookie("token");
				request.token=token;
				alert(this.val());
				request.articleId=this.val();
				$.ajax({
					url : "api/user/remove/bookmark",
					type : "POST",
					contentType : 'application/json;charset=UTF-8',
					data : JSON.stringify(request),
					success : (function(data) {
						if(data.responseCode=="0702"){
							alert("您的登录已过期，请重新登录");
							url="http://"+ window.location.host+ "/exchangePlatform/signup";
							window.location.replace(url);
						}
						if(data.responseCode=="0703"){
							alert("移除失败，出现了一些错误。");
						}
						if(data.responseCode=="0701"){
							var aId=this.val();
							this.hide();
							$('#title-'+aId).hide();
						}
					}),
					error : function() {
						alert("Error");
					}
				});				
			})});
		});
	</script>
</body>
</html>

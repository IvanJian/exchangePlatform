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
				<li><a href="#">开始撰写...</a></li>
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
						<li><a href="javascript:;" id="signout" onclick="signout()">退出登录</a></li>
					</ul></li>
				<li><a href="#">下载安卓客户端</a></li>

			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<div class="container">
		<!-- Example row of columns -->
		<div class="row">
			<div class="col-md-7 col-md-offset-1">
				<div role="tabpanel">

					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a href="#new"
							aria-controls="new" role="tab" data-toggle="tab">最新投稿</a></li>
						<li role="presentation"><a href="#hot" aria-controls="hot"
							role="tab" data-toggle="tab">全站热门</a></li>
						<li role="presentation"><a href="#classic"
							aria-controls="classic" role="tab" data-toggle="tab">精华内容</a></li>
					</ul>

					<!-- Tab panes -->
					<div class="tab-content">
						<div role="tabpanel" class="tab-pane fade in active" id="new">
							<br>
							<div id="new-articles"></div>
							<div class="span7 text-center">
								<button class="btn btn-default" type="button" id="loadNew">加载更多内容</button>
								<hr />
							</div>
							<script id="articlesTmpl" type="text/x-jsrender">
									{{if image}}
									<div class="panel panel-default">
										<div class="panel-body">
											<div class="row">
												<div class="col-md-8">
													<a href="article/{{>articleId}}">
														<h4> <b>{{>title}}</b>
														</h4>
													</a>
													<h3><small>{{>summary}}</small></h3>
												</div>
												<div class="col-md-3">
													<img src="{{>image}}" alt="{{>title}}" class="img-rounded aimg">
												</div>
												<div class="col-md-12">
													<h6>
														作者：{{>authorName}}	发布时间：{{>time}}	主题：{{>tags}}
													</h6>
												</div>
											</div>
										</div>

									</div>
									{{else}}
									<div class="panel panel-default">
										<div class="panel-body">
											<a href="article/{{>articleId}}">
												<h4> <b>{{:title}}</b>
												</h4>
											</a>
											<h3><small>{{>summary}}</small></h3>
											<br>
											<h6>
												作者：{{:authorName}}	发布时间：{{:time}}	主题：{{>tags}}
											</h6>
										</div>
									</div>
								{{/if}}
								</script>
						</div>
						<div role="tabpanel" class="tab-pane fade" id="hot">
							<br>
							<div id="hot-articles"></div>
							<div class="span7 text-center">
								<button class="btn btn-default" type="button" id="loadHot">加载更多内容</button>
								<hr />
							</div>
							<script id="articlesTmpl" type="text/x-jsrender">
									{{if image}}
									<div class="panel panel-default">

										<div class="panel-body">
											<div class="row">
												<div class="col-md-8">
													<a href="article/{{>articleId}}">
														<h4> <b>{{>title}}</b>
														</h4>
													</a>
													<h3><small>{{>summary}}</small></h3>
												</div>
												<div class="col-md-3">
													<img src="{{>image}}" alt="{{>title}}" class="img-rounded aimg">
												</div>
												<div class="col-md-12">
													<h6>
														作者：{{>authorName}}	发布时间：{{>time}}	主题：{{>tags}}
													</h6>
												</div>
											</div>
										</div>

									</div>
									{{else}}
									<div class="panel panel-default">
										<div class="panel-body">
											<a>
												<h4> <b>{{:title}}</b>
												</h4>
											</a>
											<h3><small>{{>summary}}</small></h3>
											<br>
											<h6>
												作者：{{:authorName}}	发布时间：{{:time}}	主题：{{>tags}}
											</h6>
										</div>
									</div>
								{{/if}}
								</script>
						</div>
						<div role="tabpanel" class="tab-pane fade" id="classic">
							<br>
							<div id="classic-articles"></div>
							<div class="span7 text-center">
								<button class="btn btn-default" type="button" id="loadClassic">加载更多内容</button>
								<hr />
							</div>
							<script id="articlesTmpl" type="text/x-jsrender">
									{{if image}}
									<div class="panel panel-default">

										<div class="panel-body">
											<div class="row">
												<div class="col-md-8">
													<a>
														<h4> <b>{{>title}}</b>
														</h4>
													</a>
													<h3><small>{{>summary}}</small></h3>
												</div>
												<div class="col-md-3">
													<img src="{{>image}}" alt="{{>title}}" class="img-rounded aimg">
												</div>
												<div class="col-md-12">
													<h6>
														作者：{{>authorName}}	发布时间：{{>time}}	主题：{{>tags}}
													</h6>
												</div>
											</div>
										</div>

									</div>
									{{else}}
									<div class="panel panel-default">
										<div class="panel-body">
											<a>
												<h4> <b>{{:title}}</b>
												</h4>
											</a>
											<h3><small>{{>summary}}</small></h3>
											<br>
											<h6>
												作者：{{:authorName}}	发布时间：{{:time}}	主题：{{>tags}}
											</h6>
										</div>
									</div>
								{{/if}}
								</script>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">主题</h3>
					</div>
					<table class="table table-hover" id="tags">
					</table>
					<script id="tagsTmpl" type="text/x-jsrender">
						<tr>
							<td>
								<div class="row">
									<div class="col-md-3">
										<img src="{{>image}}" alt="140x140" class="img-rounded tagimg">
									</div>
									<div class="col-md-8 offset-md-1">
										<a href="#">
											<h4>{{>tagName}}</h4>
										</a>
									</div>
								</div>
							</td>
						</tr>
						</script>
				</div>
			</div>
		</div>
	</div>
	<!-- /container -->
	<script src="js/mianpage.js"></script>
	<script>
		$(document).ready(function(){
			loadmain();
			nav();
		});
	</script>

</body>
</html>

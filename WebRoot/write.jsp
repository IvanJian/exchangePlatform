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
<link type="text/css" rel="stylesheet"
	href="kindeditor/themes/default/default.css" />
<!-- 编辑器 -->
<script charset="utf-8" src="kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="kindeditor/lang/zh_CN.js"></script>
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
					data-toggle="dropdown" role="button" aria-expanded="false"
					id="name"> <script>
						if (typeof ($.cookie("name")) != "undefined") {
							var loginState = true;
							document.write($.cookie("name"));
						} else {
							var loginState = false;
							document.write("请登录");
						}
					</script> <span class="caret"></span>
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
		<div class="row">
			<div class="col-md-8 col-md-offset-2">
				<div class="page-header">
					<h3 class="text-center">开始撰写新文章</h3>
					<div class="jumbotron">
						<h4>文章标题</h4>
						<input type="text" placeholder="请输入文章标题" class="form-control" id="title" />
						<hr />
						<h4>
							为您的文章添加缩略图。<small>非必须，添加缩略图能够提高文章的关注度。</small>
						</h4>
						<input type="button" id="upload" value="选择图片"
							class="btn btn-success" />
						<hr />
						<h4>选择文章主题。<small>精确地文章主题可以让更多人关注您的文章。</small></h4>
						<div id="tags"></div>
						<script id="tagsTmpl" type="text/x-jsrender">
						<label class="checkbox-inline">
					    	<input type="checkbox" name="tag" id="tag-{{>tagId}}" value="{{>tagId}}">{{>tagName}}
						</label>
						</script>
						<hr />
						<h5>为了文章的美观，从网络复制的内容建议您使用清理HTML代码。</h5>
						<h5>因为版式不同，实际显示效果可能与编辑结果有所差别。</h5>
						<textarea id="editor_id" name="content"
							style="width:620px;height:450px;">
						</textarea>
						<hr />
						<button type="button" id="submit"class="btn btn-primary btn-lg center">提交文章</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="js/mianpage.js"></script>
	<script>
		var editor;
		var image;
		KindEditor.ready(function(K) {
			editor = K.create('textarea[name="content"]', {
				allowFileManager: true
			});
			K('#upload').click(function() {
				editor.loadPlugin('image',
				function() {
					editor.plugin.imageDialog({
						clickFn: function(url, title, width, height, border, align) {
							image=url;
							editor.hideDialog();
						}
					});
				});
			});
		});
		
		$(document).ready(function() {
			nav();
			$.get("api/tags",
				function(tags) {
					$("#tags").html($("#tagsTmpl").render(tags));
			});
		});
		
		$("#submit").click(function(){
			if(loginState==false||typeof($.cookie("name"))=="undefined"){
				alert("您还没有登录，请登陆后再提交。");
				url="http://"+ window.location.host+ "/exchangePlatform/signup";
				window.location.replace(url);
			}else{
				if($("#title").val()==""||editor.html()=="") {alert("请填写完整后再提交。");return;}
				tagIdList=new Array();
				$('input[name="tag"]:checked').each(function(){    
   					tagIdList.push($(this).val());    
  				});
  				var token=new Object();
  				token.userId=$.cookie("userId");
  				token.token=$.cookie("token");
				data=uploadArticle(token,$("#title").val(),image,editor.html(),tagIdList);
				if(data.responseCode=="0301"){
					alert("您的文章提交成功！");
					url="http://"+ window.location.host+ "/exchangePlatform";
					window.location.replace(url);
				}else if(data.responseCode=="0304"){
					alert("请填写完整后再提交。");
				} else if(data.responseCode=="0302"){
					alert("您尚未登录或登录凭证已过期。请重新登录。");
					url="http://"+ window.location.host+ "/exchangePlatform/signup";
					window.location.replace(url);
				} else alert("Error");
			}
		});
	</script>
</body>
</html>

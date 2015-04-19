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

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript" src="js/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="js/jQuery.md5.js"></script>

</head>

<body>
	<input type="text" id="username" />
	<input type="text" id="password" />
	<input type="text" id="content" />
	<input type="button" id="login" value="login" />
	<input type="button" id="register" value="register" />
	<input type="button" id="submit" value="uploadArticle" />
	<input type="button" id="like" value="like" />
	<input type="button" id="bookmark" value="bookmark" />
	<input type="button" id="getArticle" value="getArticle" />
	<p id="p"></p>
</body>

<script type="text/javascript">
	$(document).ready(function() {
		$("#getArticle").click(function() {
			$.ajax({
				url : "api/article/28",
				type : "GET",
				success : (function(data) {
					$("#p").html(data.content);
					var date = new Date(data.uploadDateTime);
					alert(date);
				}),
				error : function() {
					alert("err");
				}
			});
		});
		$("#bookmark").click(function() {
			var request = new Object();
			request.token = token;
			request.articleId = 26;
			$("#p").html(JSON.stringify(request));
			$.ajax({
				url : "api/user/bookmark",
				type : "POST",
				contentType : 'application/json;charset=UTF-8',
				data : JSON.stringify(request),
				success : (function(data) {
					alert(data.responseCode);
				}),
				error : function() {
					alert("err");
				}
			});
		});

		$("#like").click(function() {
			var request = new Object();
			request.token = token;
			var likeId = new Object();
			likeId.userUserId = token.userId;
			likeId.articleArticleId = 22;
			request.likeId = likeId;
			$("#p").html(JSON.stringify(request));
			$.ajax({
				url : "api/user/like",
				type : "POST",
				contentType : 'application/json;charset=UTF-8',
				data : JSON.stringify(request),
				success : (function(data) {
					alert(data.responseCode);
				}),
				error : function() {
					alert("err");
				}
			});
		});

		$("#register").click(function() {
			var user = new Object();
			user.username = "aaaaaa";
			user.passwordHash = "aaaaaa";
			user.email = "awewe@sadsad.com";
			user.name = "sdsdaaa";
			$.ajax({
				url : "api/user/register",
				type : "POST",
				contentType : 'application/json;charset=UTF-8',
				data : JSON.stringify(user),
				success : function(data) {
					alert(data.responseCode);
				},
				error : function() {
					alert("ss")
				}
			});
		});

		$("#submit").click(function() {
			var article = new Object();
			article.title = "第一哦 是是是";
			article.content = $("#content").val();
			article.image = "/image/test";
			var keywords = new Array();
			var tag1 = new Object();
			tag1.tagId = 1;
			var tag2 = new Object();
			tag2.tagId = 2;
			keywords[0] = new Object();
			keywords[1] = new Object();
			//keywords[0].id=new Object();
			//keywords[1].id=new Object();
			//keywords[0].id.tagTagId=1;
			//keywords[1].id.tagTagId=2;
			keywords[0].tag = tag1;
			keywords[1].tag = tag2;
			article.keywords = keywords;
			var request = new Object();
			request.token = token;
			request.article = article;
			$("#p").html(JSON.stringify(request));
			alert(JSON.stringify(request));
			$.ajax({
				url : "api/user/uploadarticle",
				type : "POST",
				data : JSON.stringify(request),
				contentType : 'application/json;charset=UTF-8',
				success : function(data) {
					alert(data.responseCode);
				},
				error : function() {
					alert("err");
				}
			});
		});

		$("#login").click(function() {
			var username = $("#username").val();
			var password = $.md5("123456");
			$.post("api/user/authenticate", {
				"username" : "jianyifan",
				"passwordHash" : password
			}, function(data) {
				token = data.token;
				alert(data.responseCode);
			});

		});
	});
</script>
</html>

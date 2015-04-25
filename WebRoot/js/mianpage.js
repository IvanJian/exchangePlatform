		var last;//��λ��������
		var hotLast = 1;
		var classicLast = 1;
		function loadTags(){
			$.get("api/tags",
					function(tags) {
						$("#tags").html($("#tagsTmpl").render(tags));
					});
		}
		
		function loadKeyword(tagId){
			$.get("api/article/list/keyword/"+tagId+"/8",
				function(articles) {
					for (var i = 0; i < articles.length; i++) {
						var t = new Date();
						t.setTime(parseInt(articles[i].uploadDateTime, 10));
						articles[i].time = new String(t.toLocaleString());
						last = articles[i].uploadDateTime;
					}
					$("#new-articles").html($("#articlesTmpl").render(articles));
				});
				$.get("api/article/list/keyword/"+tagId+"/hot/8/1",
				function(articles) {
					for (var i = 0; i < articles.length; i++) {
						var t = new Date();
						t.setTime(parseInt(articles[i].uploadDateTime, 10));
						articles[i].time = new String(t.toLocaleString());
						hotLast += 1;
					}
					$("#hot-articles").html($("#articlesTmpl").render(articles));
				});
				$.get("api/article/list/keyword/"+tagId+"/quality/8/1",
				function(articles) {
					for (var i = 0; i < articles.length; i++) {
						var t = new Date();
						t.setTime(parseInt(articles[i].uploadDateTime, 10));
						articles[i].time = new String(t.toLocaleString());
						classicLast += 1;
					}
					$("#classic-articles").html($("#articlesTmpl").render(articles));
				});
		}
		
		function loadmain() {
			loadTags();
			$.get("api/article/list/new/8",
			function(articles) {
				for (var i = 0; i < articles.length; i++) {
					var t = new Date();
					t.setTime(parseInt(articles[i].uploadDateTime, 10));
					articles[i].time = new String(t.toLocaleString());
					last = articles[i].uploadDateTime;
				}
				$("#new-articles").html($("#articlesTmpl").render(articles));
			});
			$.get("api/article/list/hot/8/1",
			function(articles) {
				for (var i = 0; i < articles.length; i++) {
					var t = new Date();
					t.setTime(parseInt(articles[i].uploadDateTime, 10));
					articles[i].time = new String(t.toLocaleString());
					hotLast += 1;
				}
				$("#hot-articles").html($("#articlesTmpl").render(articles));
			});
			$.get("api/article/list/quality/8/1",
			function(articles) {
				for (var i = 0; i < articles.length; i++) {
					var t = new Date();
					t.setTime(parseInt(articles[i].uploadDateTime, 10));
					articles[i].time = new String(t.toLocaleString());
					classicLast += 1;
				}
				$("#classic-articles").html($("#articlesTmpl").render(articles));
			});
		}
		
		//���ظ����������
		$("#loadNew").click(function() {
			$.get("api/article/list/before/" + last + "/5",
			function(articles) {
				if (articles.length == 0 || articles.length < 5) {
					$("#loadNew").remove();
				}
				for (var i = 0; i < articles.length; i++) {
					var t = new Date();
					t.setTime(parseInt(articles[i].uploadDateTime, 10));
					articles[i].time = new String(t.toLocaleString());
					last = articles[i].uploadDateTime;
				}
				if (articles.length != 0) $("#new-articles").append($("#articlesTmpl").render(articles));
			});
		});
		$("#loadHot").click(function() {
			$.get("api/article/list/hot/5/"+hotLast,
			function(articles) {
				if (articles.length == 0 || articles.length < 5) {
					$("#loadHot").remove();
				}
				for (var i = 0; i < articles.length; i++) {
					var t = new Date();
					t.setTime(parseInt(articles[i].uploadDateTime, 10));
					articles[i].time = new String(t.toLocaleString());
					hotLast+=1;
				}
				if (articles.length != 0) $("#hot-articles").append($("#articlesTmpl").render(articles));
			});
		});
		$("#loadClassic").click(function() {
			$.get("api/article/list/quality/5/"+classicLast,
			function(articles) {
				if (articles.length == 0 || articles.length < 5) {
					$("#loadClassic").remove();
				}
				for (var i = 0; i < articles.length; i++) {
					var t = new Date();
					t.setTime(parseInt(articles[i].uploadDateTime, 10));
					articles[i].time = new String(t.toLocaleString());
					classicLast+=1;
				}
				if (articles.length != 0) $("#classic-articles").append($("#articlesTmpl").render(articles));
			});
		});
		
		$("#loadNewByTag").click(function() {
			var url="api/article/list/keyword/"+$("#tagId").val()+"/before/" + last + "/5";
			$.get(url,
			function(articles) {
				if (articles.length == 0 || articles.length < 5) {
					$("#loadNewByTag").remove();
				}
				for (var i = 0; i < articles.length; i++) {
					var t = new Date();
					t.setTime(parseInt(articles[i].uploadDateTime, 10));
					articles[i].time = new String(t.toLocaleString());
					last = articles[i].uploadDateTime;
				}
				if (articles.length != 0) $("#new-articles").append($("#articlesTmpl").render(articles));
			});
		});
		$("#loadHotByTag").click(function() {
			var url="api/article/list/keyword/"+$("#tagId").val()+"/hot/5/"+hotLast;
			$.get(url,
			function(articles) {
				if (articles.length == 0 || articles.length < 5) {
					$("#loadHotByTag").remove();
				}
				for (var i = 0; i < articles.length; i++) {
					var t = new Date();
					t.setTime(parseInt(articles[i].uploadDateTime, 10));
					articles[i].time = new String(t.toLocaleString());
					hotLast+=1;
				}
				if (articles.length != 0) $("#hot-articles").append($("#articlesTmpl").render(articles));
			});
		});
		$("#loadClassicByTag").click(function() {
			var url="api/article/list/keyword/"+$("#tagId").val()+"/quality/5/"+classicLast;
			$.get(url,
			function(articles) {
				if (articles.length == 0 || articles.length < 5) {
					$("#loadClassicByTag").remove();
				}
				for (var i = 0; i < articles.length; i++) {
					var t = new Date();
					t.setTime(parseInt(articles[i].uploadDateTime, 10));
					articles[i].time = new String(t.toLocaleString());
					classicLast+=1;
				}
				if (articles.length != 0) $("#classic-articles").append($("#articlesTmpl").render(articles));
			});
		});
		
		function like(articleId){
			if(loginState==false){
				alert("您还没有登录，请登录后再执行操作。");
				url="http://"+ window.location.host+ "/exchangePlatform/signup";
				window.location.replace(url);
				return;
			}
			if($.cookie("userId")||$.cookie("token")){
				var token=new Object();
				token.userId=$.cookie("userId");
				token.token=$.cookie("token");
				var request = new Object();
				request.token = token;
				var likeId = new Object();
				likeId.userUserId = $.cookie("userId");
				likeId.articleArticleId = articleId;
				request.likeId = likeId;
				$.ajax({
					url : "api/user/like",
					type : "POST",
					contentType : 'application/json;charset=UTF-8',
					data : JSON.stringify(request),
					success : (function(data) {
						if(data.responseCode=="0401"){
							alert("点赞成功");
						}else if(data.responseCode=="0402"){
							alert("您的登录已经过期，请重新登录。");
							url="http://"+ window.location.host+ "/exchangePlatform/signup";
							window.location.replace(url);
							return;
						}else if(data.responseCode=="0403"){
							alert("您已经赞过了这篇文章，再看看其他的吧。");
						}
					}),
					error : function() {
						alert("Error");
					}
				});
			}else{
				alert("您还没有登录，请登录后再执行操作。");
				url="http://"+ window.location.host+ "/exchangePlatform/signup";
				window.location.replace(url);
				return;
			}
		}
		
		function signout(){
			loginState=false;
			$.removeCookie("userId",{path:'/exchangePlatform'});
			$.removeCookie("name",{path:'/exchangePlatform'});
			$.removeCookie("token",{path:'/exchangePlatform'});
			url="http://"+ window.location.host+ "/exchangePlatform";
			window.location.replace(url);
		}
		
		function bookmark(articleId){
			if(loginState==false){
				alert("您还没有登录，请登录后再执行操作。");
				url="http://"+ window.location.host+ "/exchangePlatform/signup";
				window.location.replace(url);
				return;
			}
			if($.cookie("userId")||$.cookie("token")){
				var token=new Object();
				token.userId=$.cookie("userId");
				token.token=$.cookie("token");
				var request = new Object();
				request.token = token;
				request.articleId = articleId;
				$.ajax({
					url : "api/user/bookmark",
					type : "POST",
					contentType : 'application/json;charset=UTF-8',
					data : JSON.stringify(request),
					success : (function(data) {
						if(data.responseCode=="0501"){
							alert("收藏成功");
						}else if(data.responseCode=="0502"){
							alert("您的登录已经过期，请重新登录。");
							url="http://"+ window.location.host+ "/exchangePlatform/signup";
							window.location.replace(url);
							return;
						}else if(data.responseCode=="0503"){
							alert("您已经收藏过了这篇文章，再看看其他的吧。");
						}
					}),
					error : function() {
						alert("Error");
					}
				});
			}else{
				alert("您还没有登录，请登录后再执行操作。");
				url="http://"+ window.location.host+ "/exchangePlatform/signup";
				window.location.replace(url);
				return;
			}
		}
		
		function nav(){
			if(loginState==false||typeof($.cookie("name"))=="undefined"){
				$("#profile").hide();
				$("#myarticles").hide();
				$("#signout").html("登录");
				$("#signout").attr("href","http://"+ window.location.host+ "/exchangePlatform/signup");
			}
		}
		
		function uploadArticle(token,title,image,content,tagIdList){
			var r=new Object();
			var token=new Object();
			token.userId=$.cookie("userId");
			token.token=$.cookie("token");
			var article = new Object();
			article.title=title;
			article.content=content;
			article.image=image;
			var keywords = new Array();
			for(var i=0;i<tagIdList.length;i++){
				var tag = new Object();
				tag.tagId=tagIdList[i];
				keywords[i] = new Object();
				keywords[i].tag=tag;
			}
			article.keywords = keywords;
			var request = new Object();
			request.token = token;
			request.article = article;
			$.ajax({
				url : "api/user/uploadarticle",
				type : "POST",
				async: false,
				data : JSON.stringify(request),
				contentType : 'application/json;charset=UTF-8',
				success : function(data) {
					r=data;
				},
				error : function() {
					alert("Error.");
				}
			});
			return r;
		}
		
		function isEmpty( obj ) { 
			for ( var name in obj ) { 
			return false; 
			} 
			return true; 
			} 
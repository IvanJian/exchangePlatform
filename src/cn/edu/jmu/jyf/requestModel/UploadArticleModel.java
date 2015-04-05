package cn.edu.jmu.jyf.requestModel;

import cn.edu.jmu.jyf.bean.Article;
import cn.edu.jmu.jyf.model.Token;

public class UploadArticleModel {
	private Token token;
	private Article article;
	public Token getToken() {
		return token;
	}
	public void setToken(Token token) {
		this.token = token;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	
}

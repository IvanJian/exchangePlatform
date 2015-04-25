package cn.edu.jmu.jyf.requestModel;

import cn.edu.jmu.jyf.model.Token;

public class RemoveBookmarkModel {
	private Token token;
	private Integer articleId;

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

}

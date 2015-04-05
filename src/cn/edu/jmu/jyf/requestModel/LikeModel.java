package cn.edu.jmu.jyf.requestModel;

import cn.edu.jmu.jyf.bean.LikeId;
import cn.edu.jmu.jyf.model.Token;

public class LikeModel {

	private Token token;
	private LikeId likeId;
	public Token getToken() {
		return token;
	}
	public void setToken(Token token) {
		this.token = token;
	}
	public LikeId getLikeId() {
		return likeId;
	}
	public void setLikeId(LikeId likeId) {
		this.likeId = likeId;
	}
	
	
}

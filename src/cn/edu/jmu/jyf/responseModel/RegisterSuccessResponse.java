package cn.edu.jmu.jyf.responseModel;

import cn.edu.jmu.jyf.model.Token;


public class RegisterSuccessResponse extends ResponseModel {
	
	private Token token;
	
	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public RegisterSuccessResponse(String responseCode, Token token) {
		super(responseCode);
		this.token = token;
	}

	public RegisterSuccessResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


}

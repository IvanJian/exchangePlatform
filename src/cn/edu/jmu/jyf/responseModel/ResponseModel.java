package cn.edu.jmu.jyf.responseModel;

public class ResponseModel {
	public ResponseModel(String responseCode) {
		super();
		this.responseCode = responseCode;
	}

	private String responseCode;

	public String getResponseCode() {
		return responseCode;
	}
	
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public ResponseModel() {
		super();
	}

}

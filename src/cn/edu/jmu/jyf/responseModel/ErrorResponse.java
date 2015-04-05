package cn.edu.jmu.jyf.responseModel;

public class ErrorResponse extends ResponseModel {
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorResponse(String responseCode, String message) {
		super(responseCode);
		this.message = message;
	}

	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
}

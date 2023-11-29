package com.employee.exception;

public class ErrorResponse {
	
	public ErrorResponse() {
		super();
	}
	public ErrorResponse(String httpStatusCode, String message) {
		super();
		this.httpStatusCode = httpStatusCode;
		this.message = message;
	}
	private String httpStatusCode;
	private String message;
	public String getHttpStatusCode() {
		return httpStatusCode;
	}
	public void setHttpStatusCode(String httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}

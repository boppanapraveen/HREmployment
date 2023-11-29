package com.employee.exception;

import org.springframework.http.HttpStatus;

public class EmployeeHRException  extends Exception {
	
	private HttpStatus code;
	private ErrorResponse errorResponse;
	private Object payLoad;
	
	public EmployeeHRException() {
		super();
	}
	
	public EmployeeHRException(HttpStatus httpCode, ErrorResponse errorResponse) {
		super(null !=errorResponse ? errorResponse.getMessage(): "Unknown Error Occured" );
		this.code = httpCode;
		this.errorResponse = errorResponse;
	}

	public EmployeeHRException(Throwable throwable) {
		super(throwable);
	}

	public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}

	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}

	public Object getPayLoad() {
		return payLoad;
	}

	public void setPayLoad(Object payLoad) {
		this.payLoad = payLoad;
	}
	
	
}

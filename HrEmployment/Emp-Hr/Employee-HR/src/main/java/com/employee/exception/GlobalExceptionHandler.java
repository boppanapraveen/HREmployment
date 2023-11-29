package com.employee.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(EmployeeHRException.class)
	public ResponseEntity<Object> handleExceptionsOfEmployeeHR(Exception ex, WebRequest request) {
		EmployeeHRException employeeHRException = (EmployeeHRException) ex;
		return handleExceptionInternal(ex, employeeHRException.getErrorResponse(), new HttpHeaders(), employeeHRException.getCode(), request);
	}
}

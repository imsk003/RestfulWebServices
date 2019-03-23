package sk.learning.rest.webservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import sk.learning.rest.webservices.user.UserNotFoundException;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception e, WebRequest request) {
		
		ExceptionResponse response = new ExceptionResponse(new Date(), e.getMessage(), request.getDescription(false));
				
		return new ResponseEntity<Object>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e, WebRequest request) {
		
		ExceptionResponse response = new ExceptionResponse(new Date(), e.getMessage(), request.getDescription(false));
				
		return new ResponseEntity<Object>(response, HttpStatus.NOT_FOUND);
	}
	
	public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse response = new ExceptionResponse(new Date(), "Validation Failed", e.getBindingResult().toString());
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}

}

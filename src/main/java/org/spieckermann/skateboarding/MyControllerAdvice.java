package org.spieckermann.skateboarding;

import org.spieckermann.skateboarding.trucks.TruckNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MyControllerAdvice {
	
	@ResponseBody
	@ExceptionHandler(TruckNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String truckNotFoundHandler(TruckNotFoundException exception) {
		return exception.getMessage();
	}
	

}

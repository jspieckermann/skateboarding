package org.spieckermann.skateboarding;

import org.spieckermann.skateboarding.decks.DeckNotFoundException;
import org.spieckermann.skateboarding.griptape.GriptapeNotFoundException;
import org.spieckermann.skateboarding.hardware.HardwareNotFoundException;
import org.spieckermann.skateboarding.trucks.TruckNotFoundException;
import org.spieckermann.skateboarding.wheels.WheelNotFoundException;
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
	
	@ResponseBody
	@ExceptionHandler(WheelNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String wheelNotFoundHandler(WheelNotFoundException exception) {
		return exception.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(DeckNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String deckNotFoundHandler(DeckNotFoundException exception) {
		return exception.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(HardwareNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String hardwareNotFoundHandler(HardwareNotFoundException exception) {
		return exception.getMessage();
	}
	
	@ResponseBody
	@ExceptionHandler(GriptapeNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String griptapeNotFoundHandler(GriptapeNotFoundException exception) {
		return exception.getMessage();
	}

}

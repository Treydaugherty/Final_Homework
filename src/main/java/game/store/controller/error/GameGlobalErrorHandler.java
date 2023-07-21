package game.store.controller.error;

import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import game.store.controller.error.GameGlobalErrorHandler;

@RestControllerAdvice
@Slf4j
public class GameGlobalErrorHandler {
	
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public  Map<String,String> handleNoSuchElementException
	(NoSuchElementException noSuchElement){
		log.error("Exception:{} " , noSuchElement.toString());
		return Map.of("message", noSuchElement.toString());
	}
}

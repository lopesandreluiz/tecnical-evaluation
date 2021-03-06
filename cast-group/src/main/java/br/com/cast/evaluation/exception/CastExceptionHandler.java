package br.com.cast.evaluation.exception;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
 

@SuppressWarnings({"unchecked","rawtypes"})
@ControllerAdvice
public class CastExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
    MessageSource messageSource;
	
	
	@ExceptionHandler(CourseNotFoundException.class)
    public final ResponseEntity<?> handleUserNotFoundException() {
        
		return new ResponseEntity(messageSource.getMessage("course.notfound",null, Locale.getDefault()), HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(UsedPeriodException.class)
    public final ResponseEntity<?> handleUsedPeriodoException() {
       
        return new ResponseEntity(messageSource.getMessage("used.period.planing",null, Locale.getDefault()), HttpStatus.CONFLICT);
    }
	
	@ExceptionHandler(InitialDateInvalidException.class)
    public final ResponseEntity<?> handleInitialDateInvalidException() {
     
		return new ResponseEntity(messageSource.getMessage("initial.date.invalid",null, Locale.getDefault()), HttpStatus.CONFLICT);
    }
	
	@ExceptionHandler(CategoryNotValidException.class)
    public final ResponseEntity<?> handleCategoryNotValidException() {
     
		return new ResponseEntity(messageSource.getMessage("category.notfound",null, Locale.getDefault()), HttpStatus.NOT_FOUND);
    }

}

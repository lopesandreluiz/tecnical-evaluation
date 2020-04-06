package br.com.cast.evaluation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CourseNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -5220996581133717809L;

	public CourseNotFoundException() {
        super();
    }
	
}

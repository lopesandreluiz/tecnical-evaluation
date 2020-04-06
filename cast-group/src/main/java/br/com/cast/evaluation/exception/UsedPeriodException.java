package br.com.cast.evaluation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UsedPeriodException extends RuntimeException {
	
	private static final long serialVersionUID = -5220996581133717809L;

	public UsedPeriodException() {
        super();
    }
	
}

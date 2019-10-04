package sample.mikeyb303.unique_email.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "One of the listed emails was invalid. Please make sure all emails have a valid format and try again.")
public class InvalidEmailException extends RuntimeException {
	public InvalidEmailException(String errorMessage) {
		super(errorMessage);
	}
}

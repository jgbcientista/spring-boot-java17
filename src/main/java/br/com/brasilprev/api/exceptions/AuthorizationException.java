package br.com.brasilprev.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AuthorizationException extends AccessDeniedException {
	
    /**
	 * class AuthorizationException
	 */
	private static final long serialVersionUID = 1L;

	public AuthorizationException(String message) {
        super(message);
    }

}
package se.aceone.web.promo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalException extends RuntimeException {

  public InternalException(Throwable cause) {
    super(cause);
  }

  public InternalException(String message, Throwable cause) {
    super(message, cause);
  }
}

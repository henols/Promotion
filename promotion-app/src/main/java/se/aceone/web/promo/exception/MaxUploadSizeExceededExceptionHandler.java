package se.aceone.web.promo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MaxUploadSizeExceededExceptionHandler extends ResponseEntityExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(MaxUploadSizeExceededExceptionHandler.class);

  /**
   * Avoids an ugly stacktrace in the log by handling the MaxUploadSizeExceededException.
   */
  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity<Object> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
    logger.info(ex.getMessage());
    return new ResponseEntity<>("Maximum upload size exceeded", new HttpHeaders(), HttpStatus.PAYLOAD_TOO_LARGE);
  }
}

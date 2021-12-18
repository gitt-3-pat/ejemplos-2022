package info.jab.microservices;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ControllerAdviceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Contact with the operator";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.OK, request);
    }
}
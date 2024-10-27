package com.liftlab.loadBalancer_v10.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ApiException.class})
    public final ResponseEntity<Object> handleApiException(ApiException ex){
        HttpStatus httpStatus = createHttpStatus(ex);
        Map<String, Object> body = new HashMap<>();
        createBody(body, ex.getMessage(), ex.getClass().toString());
        return ResponseEntity.status(httpStatus).body(body);
    }

    @ExceptionHandler({RuntimeException.class})
    public final ResponseEntity<Object> handleRuntimeException(RuntimeException e){
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        Map<String, Object> body = new HashMap<>();
        createBody(body, "Something went wrong, please try again", e.getClass().toString());
        return ResponseEntity.status(httpStatus).body(body);
    }

    public void createBody(Map<String, Object> body, String message, String title){
        body.put("detail", message);
        body.put("title", title);
    }

    public HttpStatus createHttpStatus(ApiException ex){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        if(ex instanceof ServiceNotFound) httpStatus = HttpStatus.NOT_FOUND;
        return httpStatus;
    }

}

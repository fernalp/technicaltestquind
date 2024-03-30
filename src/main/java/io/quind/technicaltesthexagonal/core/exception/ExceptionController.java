package io.quind.technicaltesthexagonal.core.exception;

import io.quind.technicaltesthexagonal.modules.customer.application.exceptions.AlreadyExistException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> validBadRequestResponse(MethodArgumentNotValidException e){
        Map<String, Object> errorResponse = new HashMap<>();
        var error = e.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage);
        errorResponse.put("date", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("message", error);
        return errorResponse;
    }

    @ExceptionHandler({AlreadyExistException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, Object> conflictResponse(Exception e){
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("date", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.CONFLICT.value());
        errorResponse.put("message", e.getMessage());
        return errorResponse;
    }

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> internalServerError(Exception e){
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("date", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.CONFLICT.value());
        errorResponse.put("message", e.getMessage());
        return errorResponse;
    }

}

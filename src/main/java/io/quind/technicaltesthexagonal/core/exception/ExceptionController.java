package io.quind.technicaltesthexagonal.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> internalServerError(RuntimeException e){
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("date", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("message", e.getMessage());
        return errorResponse;
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> validBadRequestResponse(MethodArgumentNotValidException e){
        Map<String, Object> errorResponse = new HashMap<>();
        var message = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(err -> message.put(err.getField(), err.getDefaultMessage()));
        errorResponse.put("date", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.BAD_REQUEST.value());
        errorResponse.put("message", message);
        return errorResponse;
    }


}

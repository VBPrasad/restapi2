package com.expensemanager.restapi.exception;

import com.expensemanager.restapi.io.ErrorObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
   public ErrorObject handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
    return  ErrorObject.builder().errorCode("DATA_NOT_FOUND")
            .statusCode(HttpStatus.NOT_FOUND.value())
            .message((ex.getMessage()))
            .timeStamp(new Date())
            .build();
   }
@ResponseStatus(HttpStatus.BAD_REQUEST)
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request)
    {
Map<String, Object> errorResponse=new HashMap<>();
        List<String> errors=ex.getBindingResult().getFieldErrors().stream()
                .map( fieldError -> fieldError.getDefaultMessage())
                .collect( Collectors.toList());
        errorResponse.put("statusCode",HttpStatus.BAD_REQUEST.value() );
        errorResponse.put( "messages",errors );
        errorResponse.put( "timestamp", new Date() );
        errorResponse.put( "errorCode","VALIDATION_FIELD");

        return new ResponseEntity<Object>( errorResponse,HttpStatus.BAD_REQUEST );
    }
}

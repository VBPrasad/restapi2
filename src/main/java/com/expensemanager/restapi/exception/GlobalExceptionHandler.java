package com.expensemanager.restapi.exception;

import com.expensemanager.restapi.io.ErrorObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
@ExceptionHandler(ResourceNotFoundException.class)
   public ErrorObject handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
    return  ErrorObject.builder().errorCode("DATA_NOT_FOUND").statusCode(HttpStatus.NOT_FOUND.value()).timeStamp(new Date()).message(ex.getMessage()).build();

   }
}

package com.dolphinevents.artistservice;

import java.time.LocalDate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CostumizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, LocalDate.now(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<ErrorDetails> (errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(ArtistNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleCompanyNotFoundException(Exception ex, WebRequest request) throws Exception{
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, LocalDate.now(), ex.getMessage(), request.getDescription(false));


        return new ResponseEntity<ErrorDetails> (errorDetails, HttpStatus.NOT_FOUND);
    }    
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        
        
        ErrorDetails errorDetails = new ErrorDetails(HttpStatus.BAD_REQUEST, LocalDate.now(),"Total errors: " + ex.getErrorCount() + "|First Error:" + ex.getFieldError().getDefaultMessage(), request.getDescription(false));

        // ex.getFieldError().getDefaultMessage() --> retrieves only the default message that we wrote in User Class. But only retrieves the first error.
        //ex.getMessage() --> retrieves all the errors information 
        //ex.getErrorCount() --> retrieves the number of errors that exists.


        return handleExceptionInternal( ex, errorDetails, headers, errorDetails.getStatus(), request);
    }
}

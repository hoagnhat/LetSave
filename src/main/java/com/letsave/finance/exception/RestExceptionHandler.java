package com.letsave.finance.exception;
/*
    @Created: 20 / 06 / 2021 - 2:46 PM
    @Author: Dummy
*/

import com.letsave.finance.exception.exceptions.EntityNotFoundException;
import com.letsave.finance.exception.exceptions.PasswordException;
import com.letsave.finance.exception.model.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                HttpHeaders headers,
                                                                HttpStatus status,
                                                                WebRequest request) {

    List<String> errors = new ArrayList<>();

    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(error.getField() + ": " + error.getDefaultMessage());
    }

    for(ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    }

    ApiError apiError = ApiError.builder()
            .status(HttpStatus.BAD_REQUEST)
            .message(ex.getLocalizedMessage())
            .errors(errors)
            .build();

    return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);

  }

  @ExceptionHandler({ ConstraintViolationException.class })
  public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {

    List<String> errors = new ArrayList<>();

    for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
      errors.add(violation.getRootBeanClass().getName() + " "
              + violation.getPropertyPath() + ": "
              + violation.getMessage());
    }

    ApiError apiError = ApiError.builder()
            .status(HttpStatus.BAD_REQUEST)
            .message(ex.getLocalizedMessage())
            .errors(errors)
            .build();

    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
  }

  @ExceptionHandler({ EntityNotFoundException.class })
  public ResponseEntity<Object> handlerEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {

    List<String> errors = new ArrayList<>();

    errors.add(ex.getLocalizedMessage());

    ApiError apiError = ApiError.builder()
            .status(HttpStatus.NOT_FOUND)
            .message(ex.getLocalizedMessage())
            .errors(errors)
            .build();

    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());

  }

  @ExceptionHandler({ SQLIntegrityConstraintViolationException.class })
  public ResponseEntity<Object> handleSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException ex, WebRequest request) {

    List<String> errors = new ArrayList<>();

    errors.add(ex.getMessage());

    ApiError apiError = ApiError.builder()
            .status(HttpStatus.BAD_REQUEST)
            .message(ex.getLocalizedMessage())
            .errors(errors)
            .build();

    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());

  }

  @ExceptionHandler({ PasswordException.class })
  public ResponseEntity<Object> handlePasswordException(PasswordException ex, WebRequest request) {

    List<String> errors = new ArrayList<>();

    errors.add(ex.getLocalizedMessage());

    ApiError apiError = ApiError.builder()
                                .status(HttpStatus.BAD_REQUEST)
                                .message(ex.getLocalizedMessage())
                                .errors(errors)
                                .build();

    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());

  }

  @ExceptionHandler({ ServletException.class })
  public ResponseEntity<Object> handleServletException(ServletException ex, WebRequest request) {

    List<String> errors = new ArrayList<>();

    errors.add(ex.getLocalizedMessage());

    ApiError apiError = ApiError.builder()
                                .status(HttpStatus.BAD_REQUEST)
                                .message(ex.getLocalizedMessage())
                                .errors(errors)
                                .build();

    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());

  }

}

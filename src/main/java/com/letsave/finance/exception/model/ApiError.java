package com.letsave.finance.exception.model;
/*
    @Created: 20 / 06 / 2021 - 2:44 PM
    @Author: Dummy
*/

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Data
@Builder
public class ApiError {

  private HttpStatus status;
  private String message;
  private List<String> errors;

  public ApiError(HttpStatus status, String message, String error) {
    super();
    this.status = status;
    this.message = message;
    this.errors = Arrays.asList(error);
  }

  public ApiError(HttpStatus status, String message, List<String> errors) {
    super();
    this.status = status;
    this.message = message;
    this.errors = errors;
  }

}
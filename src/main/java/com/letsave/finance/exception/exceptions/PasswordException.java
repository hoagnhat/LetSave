package com.letsave.finance.exception.exceptions;
/*
    @Created: 20 / 06 / 2021 - 2:47 PM
    @Author: Dummy
*/

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordException extends RuntimeException {

  public PasswordException(String message) {
    super(message);
  }

}

package com.letsave.finance.exception.exceptions;
/*
    @Created: 20 / 06 / 2021 - 2:45 PM
    @Author: Dummy
*/

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

  public EntityNotFoundException(String message) {
    super(message);
  }

}

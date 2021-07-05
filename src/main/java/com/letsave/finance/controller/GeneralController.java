package com.letsave.finance.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/*
    @Created: 05 / 07 / 2021 - 3:46 PM
    @Author: Ngơ
*/
@RestController
public class GeneralController {

  @GetMapping("/")
  @ResponseStatus(value = HttpStatus.OK)
  public String homePage() {
    return "Home page";
  }

  @GetMapping("/login?error")
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public String loginError() {
    return "Login Error";
  }

  @GetMapping("/error")
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public String error404() {
    return "404 Not Found";
  }

  @GetMapping("/login")
  @ResponseStatus(value = HttpStatus.OK)
  public String loginSuccess() {
    return "Login successful!";
  }

}

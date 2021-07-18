package com.letsave.finance.controller;

import com.letsave.finance.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/*
    @Created: 05 / 07 / 2021 - 3:46 PM
    @Author: Ngơ
*/
@RestController
public class GeneralController {

  private final AccountService accountService;

  public GeneralController(AccountService accountService) {
    this.accountService = accountService;
  }

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
  public void loginSuccess(@RequestParam("username") String username, @RequestParam("password") String password) {
    accountService.login(username, password);
  }

}

package com.letsave.finance.controller;

import com.letsave.finance.model.AccountModel;
import com.letsave.finance.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/*
    @Created: 05 / 07 / 2021 - 3:46 PM
    @Author: Ngơ
*/
@RestController
@RequestMapping("/")
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

  @PostMapping("/login")
  @ResponseStatus(value = HttpStatus.OK)
  public void loginSuccess(@RequestBody AccountModel accountModel) {
    accountService.login(accountModel.getUsername(), accountModel.getPassword());
  }

}

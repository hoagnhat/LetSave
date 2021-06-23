package com.letsave.finance.controller;
/*
    @Created: 23 / 06 / 2021 - 2:29 PM
    @Author: Dummy
*/

import com.letsave.finance.common.routes.BudgetRoutes;
import com.letsave.finance.model.BugetModel;
import com.letsave.finance.service.AccountService;
import com.letsave.finance.service.BudgetService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(BudgetRoutes.BUDGET_ROOT_URL)
public class BudgetController {

  private final BudgetService service;
  private final AccountService accountService;

  public BudgetController(BudgetService service, AccountService accountService) {
    this.service = service;
    this.accountService = accountService;
  }

  @PostMapping()
  public void insertBudget(@RequestBody BugetModel bugetModel) {
    bugetModel.setAccountId(accountService.getCurrentAccount().getId());
    bugetModel.setStatus(0);
    bugetModel.setActualAmount(0);
    service.insertBudget(bugetModel);
  }

}
